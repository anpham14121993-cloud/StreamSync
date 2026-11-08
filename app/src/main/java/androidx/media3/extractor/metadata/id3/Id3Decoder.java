package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes24.dex */
public final class Id3Decoder extends SimpleMetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = 4801587;
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = new FramePredicate() { // from class: androidx.media3.extractor.metadata.id3.Id3Decoder$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate
        public final boolean evaluate(int i, int i2, int i3, int i4, int i5) {
            return Id3Decoder.lambda$static$0(i, i2, i3, i4, i5);
        }
    };
    private static final String TAG = "Id3Decoder";
    private final FramePredicate framePredicate;

    /* loaded from: classes24.dex */
    public interface FramePredicate {
        boolean evaluate(int i, int i2, int i3, int i4, int i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(int majorVersion, int id0, int id1, int id2, int id3) {
        return false;
    }

    public Id3Decoder() {
        this(null);
    }

    public Id3Decoder(FramePredicate framePredicate) {
        this.framePredicate = framePredicate;
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    protected Metadata decode(MetadataInputBuffer inputBuffer, ByteBuffer buffer) {
        return decode(buffer.array(), buffer.limit());
    }

    public Metadata decode(byte[] data, int size) {
        List<Id3Frame> id3Frames = new ArrayList<>();
        ParsableByteArray id3Data = new ParsableByteArray(data, size);
        Id3Header id3Header = decodeHeader(id3Data);
        if (id3Header == null) {
            return null;
        }
        int startPosition = id3Data.getPosition();
        int frameHeaderSize = id3Header.majorVersion == 2 ? 6 : 10;
        int framesSize = id3Header.framesSize;
        if (id3Header.isUnsynchronized) {
            framesSize = removeUnsynchronization(id3Data, id3Header.framesSize);
        }
        id3Data.setLimit(startPosition + framesSize);
        boolean unsignedIntFrameSizeHack = false;
        if (!validateFrames(id3Data, id3Header.majorVersion, frameHeaderSize, false)) {
            if (id3Header.majorVersion == 4 && validateFrames(id3Data, 4, frameHeaderSize, true)) {
                unsignedIntFrameSizeHack = true;
            } else {
                Log.w(TAG, "Failed to validate ID3 tag with majorVersion=" + id3Header.majorVersion);
                return null;
            }
        }
        while (id3Data.bytesLeft() >= frameHeaderSize) {
            Id3Frame frame = decodeFrame(id3Header.majorVersion, id3Data, unsignedIntFrameSizeHack, frameHeaderSize, this.framePredicate);
            if (frame != null) {
                id3Frames.add(frame);
            }
        }
        return new Metadata(id3Frames);
    }

    private static Id3Header decodeHeader(ParsableByteArray data) {
        if (data.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int id = data.readUnsignedInt24();
        if (id != 4801587) {
            Log.w(TAG, "Unexpected first three bytes of ID3 tag header: 0x" + String.format("%06X", Integer.valueOf(id)));
            return null;
        }
        int majorVersion = data.readUnsignedByte();
        data.skipBytes(1);
        int flags = data.readUnsignedByte();
        int framesSize = data.readSynchSafeInt();
        if (majorVersion == 2) {
            boolean isCompressed = (flags & 64) != 0;
            if (isCompressed) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (majorVersion == 3) {
            boolean hasExtendedHeader = (flags & 64) != 0;
            if (hasExtendedHeader) {
                int extendedHeaderSize = data.readInt();
                data.skipBytes(extendedHeaderSize);
                framesSize -= extendedHeaderSize + 4;
            }
        } else if (majorVersion == 4) {
            boolean hasExtendedHeader2 = (flags & 64) != 0;
            if (hasExtendedHeader2) {
                int extendedHeaderSize2 = data.readSynchSafeInt();
                data.skipBytes(extendedHeaderSize2 - 4);
                framesSize -= extendedHeaderSize2;
            }
            boolean hasFooter = (flags & 16) != 0;
            if (hasFooter) {
                framesSize -= 10;
            }
        } else {
            Log.w(TAG, "Skipped ID3 tag with unsupported majorVersion=" + majorVersion);
            return null;
        }
        boolean isUnsynchronized = majorVersion < 4 && (flags & 128) != 0;
        return new Id3Header(majorVersion, isUnsynchronized, framesSize);
    }

    private static boolean validateFrames(ParsableByteArray id3Data, int majorVersion, int frameHeaderSize, boolean unsignedIntFrameSizeHack) {
        int id;
        long frameSize;
        int flags;
        int startPosition = id3Data.getPosition();
        while (true) {
            try {
                if (id3Data.bytesLeft() < frameHeaderSize) {
                    id3Data.setPosition(startPosition);
                    return true;
                }
                if (majorVersion >= 3) {
                    try {
                        id = id3Data.readInt();
                        frameSize = id3Data.readUnsignedInt();
                        flags = id3Data.readUnsignedShort();
                    } catch (Throwable th) {
                        th = th;
                        id3Data.setPosition(startPosition);
                        throw th;
                    }
                } else {
                    id = id3Data.readUnsignedInt24();
                    frameSize = id3Data.readUnsignedInt24();
                    flags = 0;
                }
                if (id == 0 && frameSize == 0 && flags == 0) {
                    id3Data.setPosition(startPosition);
                    return true;
                }
                if (majorVersion == 4 && !unsignedIntFrameSizeHack) {
                    if ((8421504 & frameSize) != 0) {
                        id3Data.setPosition(startPosition);
                        return false;
                    }
                    frameSize = (frameSize & 255) | (((frameSize >> 8) & 255) << 7) | (((frameSize >> 16) & 255) << 14) | (((frameSize >> 24) & 255) << 21);
                }
                boolean hasGroupIdentifier = false;
                boolean hasDataLength = false;
                if (majorVersion == 4) {
                    hasGroupIdentifier = (flags & 64) != 0;
                    hasDataLength = (flags & 1) != 0;
                } else if (majorVersion == 3) {
                    hasGroupIdentifier = (flags & 32) != 0;
                    hasDataLength = (flags & 128) != 0;
                }
                int minimumFrameSize = hasGroupIdentifier ? 0 + 1 : 0;
                if (hasDataLength) {
                    minimumFrameSize += 4;
                }
                if (frameSize < minimumFrameSize) {
                    id3Data.setPosition(startPosition);
                    return false;
                }
                if (id3Data.bytesLeft() < frameSize) {
                    id3Data.setPosition(startPosition);
                    return false;
                }
                id3Data.skipBytes((int) frameSize);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:130:0x01cc, code lost:
    
        if (r13 == 67) goto L136;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x024e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.extractor.metadata.id3.Id3Frame decodeFrame(int r24, androidx.media3.common.util.ParsableByteArray r25, boolean r26, int r27, androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate r28) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.id3.Id3Decoder.decodeFrame(int, androidx.media3.common.util.ParsableByteArray, boolean, int, androidx.media3.extractor.metadata.id3.Id3Decoder$FramePredicate):androidx.media3.extractor.metadata.id3.Id3Frame");
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray id3Data, int frameSize) {
        if (frameSize < 1) {
            return null;
        }
        int encoding = id3Data.readUnsignedByte();
        byte[] data = new byte[frameSize - 1];
        id3Data.readBytes(data, 0, frameSize - 1);
        int descriptionEndIndex = indexOfTerminator(data, 0, encoding);
        String description = new String(data, 0, descriptionEndIndex, getCharset(encoding));
        ImmutableList<String> values = decodeTextInformationFrameValues(data, encoding, delimiterLength(encoding) + descriptionEndIndex);
        return new TextInformationFrame("TXXX", description, values);
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray id3Data, int frameSize, String id) {
        if (frameSize < 1) {
            return null;
        }
        int encoding = id3Data.readUnsignedByte();
        byte[] data = new byte[frameSize - 1];
        id3Data.readBytes(data, 0, frameSize - 1);
        ImmutableList<String> values = decodeTextInformationFrameValues(data, encoding, 0);
        return new TextInformationFrame(id, (String) null, values);
    }

    private static ImmutableList<String> decodeTextInformationFrameValues(byte[] data, int encoding, int index) {
        if (index >= data.length) {
            return ImmutableList.of("");
        }
        ImmutableList.Builder<String> values = ImmutableList.builder();
        int valueStartIndex = index;
        int valueEndIndex = indexOfTerminator(data, valueStartIndex, encoding);
        while (valueStartIndex < valueEndIndex) {
            String value = new String(data, valueStartIndex, valueEndIndex - valueStartIndex, getCharset(encoding));
            values.add((ImmutableList.Builder<String>) value);
            valueStartIndex = valueEndIndex + delimiterLength(encoding);
            valueEndIndex = indexOfTerminator(data, valueStartIndex, encoding);
        }
        ImmutableList<String> result = values.build();
        return result.isEmpty() ? ImmutableList.of("") : result;
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray id3Data, int frameSize) {
        if (frameSize < 1) {
            return null;
        }
        int encoding = id3Data.readUnsignedByte();
        byte[] data = new byte[frameSize - 1];
        id3Data.readBytes(data, 0, frameSize - 1);
        int descriptionEndIndex = indexOfTerminator(data, 0, encoding);
        String description = new String(data, 0, descriptionEndIndex, getCharset(encoding));
        int urlStartIndex = delimiterLength(encoding) + descriptionEndIndex;
        int urlEndIndex = indexOfZeroByte(data, urlStartIndex);
        String url = decodeStringIfValid(data, urlStartIndex, urlEndIndex, Charsets.ISO_8859_1);
        return new UrlLinkFrame("WXXX", description, url);
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray id3Data, int frameSize, String id) {
        byte[] data = new byte[frameSize];
        id3Data.readBytes(data, 0, frameSize);
        int urlEndIndex = indexOfZeroByte(data, 0);
        String url = new String(data, 0, urlEndIndex, Charsets.ISO_8859_1);
        return new UrlLinkFrame(id, null, url);
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray id3Data, int frameSize) {
        byte[] data = new byte[frameSize];
        id3Data.readBytes(data, 0, frameSize);
        int ownerEndIndex = indexOfZeroByte(data, 0);
        String owner = new String(data, 0, ownerEndIndex, Charsets.ISO_8859_1);
        int privateDataStartIndex = ownerEndIndex + 1;
        byte[] privateData = copyOfRangeIfValid(data, privateDataStartIndex, data.length);
        return new PrivFrame(owner, privateData);
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray id3Data, int frameSize) {
        int encoding = id3Data.readUnsignedByte();
        Charset charset = getCharset(encoding);
        byte[] data = new byte[frameSize - 1];
        id3Data.readBytes(data, 0, frameSize - 1);
        int mimeTypeEndIndex = indexOfZeroByte(data, 0);
        String mimeType = MimeTypes.normalizeMimeType(new String(data, 0, mimeTypeEndIndex, Charsets.ISO_8859_1));
        int filenameStartIndex = mimeTypeEndIndex + 1;
        int filenameEndIndex = indexOfTerminator(data, filenameStartIndex, encoding);
        String filename = decodeStringIfValid(data, filenameStartIndex, filenameEndIndex, charset);
        int descriptionStartIndex = delimiterLength(encoding) + filenameEndIndex;
        int descriptionEndIndex = indexOfTerminator(data, descriptionStartIndex, encoding);
        String description = decodeStringIfValid(data, descriptionStartIndex, descriptionEndIndex, charset);
        int objectDataStartIndex = delimiterLength(encoding) + descriptionEndIndex;
        byte[] objectData = copyOfRangeIfValid(data, objectDataStartIndex, data.length);
        return new GeobFrame(mimeType, filename, description, objectData);
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray id3Data, int frameSize, int majorVersion) {
        int mimeTypeEndIndex;
        String mimeType;
        int encoding = id3Data.readUnsignedByte();
        Charset charset = getCharset(encoding);
        byte[] data = new byte[frameSize - 1];
        id3Data.readBytes(data, 0, frameSize - 1);
        if (majorVersion == 2) {
            mimeTypeEndIndex = 2;
            mimeType = "image/" + Ascii.toLowerCase(new String(data, 0, 3, Charsets.ISO_8859_1));
            if ("image/jpg".equals(mimeType)) {
                mimeType = "image/jpeg";
            }
        } else {
            mimeTypeEndIndex = indexOfZeroByte(data, 0);
            mimeType = Ascii.toLowerCase(new String(data, 0, mimeTypeEndIndex, Charsets.ISO_8859_1));
            if (mimeType.indexOf(47) == -1) {
                mimeType = "image/" + mimeType;
            }
        }
        int pictureType = data[mimeTypeEndIndex + 1] & 255;
        int descriptionStartIndex = mimeTypeEndIndex + 2;
        int descriptionEndIndex = indexOfTerminator(data, descriptionStartIndex, encoding);
        String description = new String(data, descriptionStartIndex, descriptionEndIndex - descriptionStartIndex, charset);
        int pictureDataStartIndex = delimiterLength(encoding) + descriptionEndIndex;
        byte[] pictureData = copyOfRangeIfValid(data, pictureDataStartIndex, data.length);
        return new ApicFrame(mimeType, description, pictureType, pictureData);
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray id3Data, int frameSize) {
        if (frameSize < 4) {
            return null;
        }
        int encoding = id3Data.readUnsignedByte();
        Charset charset = getCharset(encoding);
        byte[] data = new byte[3];
        id3Data.readBytes(data, 0, 3);
        String language = new String(data, 0, 3);
        byte[] data2 = new byte[frameSize - 4];
        id3Data.readBytes(data2, 0, frameSize - 4);
        int descriptionEndIndex = indexOfTerminator(data2, 0, encoding);
        String description = new String(data2, 0, descriptionEndIndex, charset);
        int textStartIndex = delimiterLength(encoding) + descriptionEndIndex;
        int textEndIndex = indexOfTerminator(data2, textStartIndex, encoding);
        String text = decodeStringIfValid(data2, textStartIndex, textEndIndex, charset);
        return new CommentFrame(language, description, text);
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray id3Data, int frameSize, int majorVersion, boolean unsignedIntFrameSizeHack, int frameHeaderSize, FramePredicate framePredicate) {
        long startOffset;
        long endOffset;
        int framePosition = id3Data.getPosition();
        int chapterIdEndIndex = indexOfZeroByte(id3Data.getData(), framePosition);
        String chapterId = new String(id3Data.getData(), framePosition, chapterIdEndIndex - framePosition, Charsets.ISO_8859_1);
        id3Data.setPosition(chapterIdEndIndex + 1);
        int startTime = id3Data.readInt();
        int endTime = id3Data.readInt();
        long startOffset2 = id3Data.readUnsignedInt();
        if (startOffset2 != 4294967295L) {
            startOffset = startOffset2;
        } else {
            startOffset = -1;
        }
        long endOffset2 = id3Data.readUnsignedInt();
        if (endOffset2 != 4294967295L) {
            endOffset = endOffset2;
        } else {
            endOffset = -1;
        }
        ArrayList<Id3Frame> subFrames = new ArrayList<>();
        int limit = framePosition + frameSize;
        while (id3Data.getPosition() < limit) {
            Id3Frame frame = decodeFrame(majorVersion, id3Data, unsignedIntFrameSizeHack, frameHeaderSize, framePredicate);
            if (frame != null) {
                subFrames.add(frame);
            }
        }
        Id3Frame[] subFrameArray = (Id3Frame[]) subFrames.toArray(new Id3Frame[0]);
        return new ChapterFrame(chapterId, startTime, endTime, startOffset, endOffset, subFrameArray);
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray id3Data, int frameSize, int majorVersion, boolean unsignedIntFrameSizeHack, int frameHeaderSize, FramePredicate framePredicate) {
        int framePosition = id3Data.getPosition();
        int elementIdEndIndex = indexOfZeroByte(id3Data.getData(), framePosition);
        String elementId = new String(id3Data.getData(), framePosition, elementIdEndIndex - framePosition, Charsets.ISO_8859_1);
        id3Data.setPosition(elementIdEndIndex + 1);
        int ctocFlags = id3Data.readUnsignedByte();
        boolean isRoot = (ctocFlags & 2) != 0;
        boolean isOrdered = (ctocFlags & 1) != 0;
        int childCount = id3Data.readUnsignedByte();
        String[] children = new String[childCount];
        for (int i = 0; i < childCount; i++) {
            int startIndex = id3Data.getPosition();
            int endIndex = indexOfZeroByte(id3Data.getData(), startIndex);
            children[i] = new String(id3Data.getData(), startIndex, endIndex - startIndex, Charsets.ISO_8859_1);
            id3Data.setPosition(endIndex + 1);
        }
        ArrayList<Id3Frame> subFrames = new ArrayList<>();
        int limit = framePosition + frameSize;
        while (id3Data.getPosition() < limit) {
            Id3Frame frame = decodeFrame(majorVersion, id3Data, unsignedIntFrameSizeHack, frameHeaderSize, framePredicate);
            if (frame != null) {
                subFrames.add(frame);
            }
        }
        Id3Frame[] subFrameArray = (Id3Frame[]) subFrames.toArray(new Id3Frame[0]);
        return new ChapterTocFrame(elementId, isRoot, isOrdered, children, subFrameArray);
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray id3Data, int frameSize) {
        int mpegFramesBetweenReference = id3Data.readUnsignedShort();
        int bytesBetweenReference = id3Data.readUnsignedInt24();
        int millisecondsBetweenReference = id3Data.readUnsignedInt24();
        int bitsForBytesDeviation = id3Data.readUnsignedByte();
        int bitsForMillisecondsDeviation = id3Data.readUnsignedByte();
        ParsableBitArray references = new ParsableBitArray();
        references.reset(id3Data);
        int referencesBits = (frameSize - 10) * 8;
        int bitsPerReference = bitsForBytesDeviation + bitsForMillisecondsDeviation;
        int referencesCount = referencesBits / bitsPerReference;
        int[] bytesDeviations = new int[referencesCount];
        int[] millisecondsDeviations = new int[referencesCount];
        for (int i = 0; i < referencesCount; i++) {
            int bytesDeviation = references.readBits(bitsForBytesDeviation);
            int millisecondsDeviation = references.readBits(bitsForMillisecondsDeviation);
            bytesDeviations[i] = bytesDeviation;
            millisecondsDeviations[i] = millisecondsDeviation;
        }
        return new MlltFrame(mpegFramesBetweenReference, bytesBetweenReference, millisecondsBetweenReference, bytesDeviations, millisecondsDeviations);
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray id3Data, int frameSize, String id) {
        byte[] frame = new byte[frameSize];
        id3Data.readBytes(frame, 0, frameSize);
        return new BinaryFrame(id, frame);
    }

    private static int removeUnsynchronization(ParsableByteArray data, int length) {
        byte[] bytes = data.getData();
        int startPosition = data.getPosition();
        for (int i = startPosition; i + 1 < startPosition + length; i++) {
            if ((bytes[i] & 255) == 255 && bytes[i + 1] == 0) {
                int relativePosition = i - startPosition;
                System.arraycopy(bytes, i + 2, bytes, i + 1, (length - relativePosition) - 2);
                length--;
            }
        }
        return length;
    }

    private static Charset getCharset(int encodingByte) {
        switch (encodingByte) {
            case 1:
                return Charsets.UTF_16;
            case 2:
                return Charsets.UTF_16BE;
            case 3:
                return Charsets.UTF_8;
            default:
                return Charsets.ISO_8859_1;
        }
    }

    private static String getFrameId(int majorVersion, int frameId0, int frameId1, int frameId2, int frameId3) {
        if (majorVersion == 2) {
            return String.format(Locale.US, "%c%c%c", Integer.valueOf(frameId0), Integer.valueOf(frameId1), Integer.valueOf(frameId2));
        }
        return String.format(Locale.US, "%c%c%c%c", Integer.valueOf(frameId0), Integer.valueOf(frameId1), Integer.valueOf(frameId2), Integer.valueOf(frameId3));
    }

    private static int indexOfTerminator(byte[] data, int fromIndex, int encoding) {
        int terminationPos = indexOfZeroByte(data, fromIndex);
        if (encoding == 0 || encoding == 3) {
            return terminationPos;
        }
        while (terminationPos < data.length - 1) {
            if ((terminationPos - fromIndex) % 2 == 0 && data[terminationPos + 1] == 0) {
                return terminationPos;
            }
            terminationPos = indexOfZeroByte(data, terminationPos + 1);
        }
        return data.length;
    }

    private static int indexOfZeroByte(byte[] data, int fromIndex) {
        for (int i = fromIndex; i < data.length; i++) {
            if (data[i] == 0) {
                return i;
            }
        }
        int i2 = data.length;
        return i2;
    }

    private static int delimiterLength(int encodingByte) {
        if (encodingByte == 0 || encodingByte == 3) {
            return 1;
        }
        return 2;
    }

    private static byte[] copyOfRangeIfValid(byte[] data, int from, int to) {
        if (to <= from) {
            return Util.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(data, from, to);
    }

    private static String decodeStringIfValid(byte[] data, int from, int to, Charset charset) {
        if (to <= from || to > data.length) {
            return "";
        }
        return new String(data, from, to - from, charset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class Id3Header {
        private final int framesSize;
        private final boolean isUnsynchronized;
        private final int majorVersion;

        public Id3Header(int majorVersion, boolean isUnsynchronized, int framesSize) {
            this.majorVersion = majorVersion;
            this.isUnsynchronized = isUnsynchronized;
            this.framesSize = framesSize;
        }
    }
}
