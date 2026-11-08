package androidx.media3.extractor.mp3;

import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.Id3Peeker;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.id3.MlltFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.extractor.mp3.Seeker;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes24.dex */
public final class Mp3Extractor implements Extractor {
    public static final int FLAG_DISABLE_ID3_METADATA = 8;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    public static final int FLAG_ENABLE_INDEX_SEEKING = 4;
    private static final int MAX_SNIFF_BYTES = 32768;
    private static final int MAX_SYNC_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_INFO = 1231971951;
    private static final int SEEK_HEADER_UNSET = 0;
    private static final int SEEK_HEADER_VBRI = 1447187017;
    private static final int SEEK_HEADER_XING = 1483304551;
    private static final String TAG = "Mp3Extractor";
    private long basisTimeUs;
    private TrackOutput currentTrackOutput;
    private boolean disableSeeking;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private boolean isSeekInProgress;
    private Metadata metadata;
    private TrackOutput realTrackOutput;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private long seekTimeUs;
    private Seeker seeker;
    private final TrackOutput skippingTrackOutput;
    private final MpegAudioUtil.Header synchronizedHeader;
    private int synchronizedHeaderData;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.mp3.Mp3Extractor$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return Mp3Extractor.lambda$static$0();
        }
    };
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = new Id3Decoder.FramePredicate() { // from class: androidx.media3.extractor.mp3.Mp3Extractor$$ExternalSyntheticLambda1
        @Override // androidx.media3.extractor.metadata.id3.Id3Decoder.FramePredicate
        public final boolean evaluate(int i, int i2, int i3, int i4, int i5) {
            return Mp3Extractor.lambda$static$1(i, i2, i3, i4, i5);
        }
    };

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface Flags {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp3Extractor()};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1(int majorVersion, int id0, int id1, int id2, int id3) {
        return (id0 == 67 && id1 == 79 && id2 == 77 && (id3 == 77 || majorVersion == 2)) || (id0 == 77 && id1 == 76 && id2 == 76 && (id3 == 84 || majorVersion == 2));
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int flags) {
        this(flags, C.TIME_UNSET);
    }

    public Mp3Extractor(int flags, long forcedFirstSampleTimestampUs) {
        this.flags = (flags & 2) != 0 ? flags | 1 : flags;
        this.forcedFirstSampleTimestampUs = forcedFirstSampleTimestampUs;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = C.TIME_UNSET;
        this.id3Peeker = new Id3Peeker();
        this.skippingTrackOutput = new DiscardingTrackOutput();
        this.currentTrackOutput = this.skippingTrackOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        return synchronize(input, true);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        this.extractorOutput = output;
        this.realTrackOutput = this.extractorOutput.track(0, 1);
        this.currentTrackOutput = this.realTrackOutput;
        this.extractorOutput.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = C.TIME_UNSET;
        this.samplesRead = 0L;
        this.sampleBytesRemaining = 0;
        this.seekTimeUs = timeUs;
        if ((this.seeker instanceof IndexSeeker) && !((IndexSeeker) this.seeker).isTimeUsInIndex(timeUs)) {
            this.isSeekInProgress = true;
            this.currentTrackOutput = this.skippingTrackOutput;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        assertInitialized();
        int readResult = readInternal(input);
        if (readResult == -1 && (this.seeker instanceof IndexSeeker)) {
            long durationUs = computeTimeUs(this.samplesRead);
            if (this.seeker.getDurationUs() != durationUs) {
                ((IndexSeeker) this.seeker).setDurationUs(durationUs);
                this.extractorOutput.seekMap(this.seeker);
            }
        }
        return readResult;
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    private int readInternal(ExtractorInput input) throws IOException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(input, false);
            } catch (EOFException e) {
                return -1;
            }
        }
        if (this.seeker == null) {
            this.seeker = computeSeeker(input);
            this.extractorOutput.seekMap(this.seeker);
            Format.Builder format = new Format.Builder().setSampleMimeType(this.synchronizedHeader.mimeType).setMaxInputSize(4096).setChannelCount(this.synchronizedHeader.channels).setSampleRate(this.synchronizedHeader.sampleRate).setEncoderDelay(this.gaplessInfoHolder.encoderDelay).setEncoderPadding(this.gaplessInfoHolder.encoderPadding).setMetadata((this.flags & 8) != 0 ? null : this.metadata);
            if (this.seeker.getAverageBitrate() != -2147483647) {
                format.setAverageBitrate(this.seeker.getAverageBitrate());
            }
            this.currentTrackOutput.format(format.build());
            this.firstSamplePosition = input.getPosition();
        } else if (this.firstSamplePosition != 0) {
            long inputPosition = input.getPosition();
            if (inputPosition < this.firstSamplePosition) {
                input.skipFully((int) (this.firstSamplePosition - inputPosition));
            }
        }
        return readSample(input);
    }

    @RequiresNonNull({"realTrackOutput", "seeker"})
    private int readSample(ExtractorInput extractorInput) throws IOException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int sampleHeaderData = this.scratch.readInt();
            if (!headersMatch(sampleHeaderData, this.synchronizedHeaderData) || MpegAudioUtil.getFrameSize(sampleHeaderData) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            this.synchronizedHeader.setForHeaderData(sampleHeaderData);
            if (this.basisTimeUs == C.TIME_UNSET) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != C.TIME_UNSET) {
                    long embeddedFirstSampleTimestampUs = this.seeker.getTimeUs(0L);
                    this.basisTimeUs += this.forcedFirstSampleTimestampUs - embeddedFirstSampleTimestampUs;
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
            if (this.seeker instanceof IndexSeeker) {
                IndexSeeker indexSeeker = (IndexSeeker) this.seeker;
                indexSeeker.maybeAddSeekPoint(computeTimeUs(this.samplesRead + this.synchronizedHeader.samplesPerFrame), extractorInput.getPosition() + this.synchronizedHeader.frameSize);
                if (this.isSeekInProgress && indexSeeker.isTimeUsInIndex(this.seekTimeUs)) {
                    this.isSeekInProgress = false;
                    this.currentTrackOutput = this.realTrackOutput;
                }
            }
        }
        int bytesAppended = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.sampleBytesRemaining, true);
        if (bytesAppended == -1) {
            return -1;
        }
        this.sampleBytesRemaining -= bytesAppended;
        if (this.sampleBytesRemaining > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private long computeTimeUs(long samplesRead) {
        return this.basisTimeUs + ((1000000 * samplesRead) / this.synchronizedHeader.sampleRate);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a2, code lost:
    
        if (r14 == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a4, code lost:
    
        r13.skipFully(r2 + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ad, code lost:
    
        r12.synchronizedHeaderData = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00af, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00aa, code lost:
    
        r13.resetPeekPosition();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean synchronize(androidx.media3.extractor.ExtractorInput r13, boolean r14) throws java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            if (r14 == 0) goto La
            r4 = 32768(0x8000, float:4.5918E-41)
            goto Lc
        La:
            r4 = 131072(0x20000, float:1.83671E-40)
        Lc:
            r13.resetPeekPosition()
            long r5 = r13.getPosition()
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r6 = 0
            r7 = 0
            r8 = 1
            if (r5 != 0) goto L48
            int r5 = r12.flags
            r5 = r5 & 8
            if (r5 != 0) goto L24
            r5 = r8
            goto L25
        L24:
            r5 = r7
        L25:
            if (r5 == 0) goto L29
            r9 = r6
            goto L2b
        L29:
            androidx.media3.extractor.metadata.id3.Id3Decoder$FramePredicate r9 = androidx.media3.extractor.mp3.Mp3Extractor.REQUIRED_ID3_FRAME_PREDICATE
        L2b:
            androidx.media3.extractor.Id3Peeker r10 = r12.id3Peeker
            androidx.media3.common.Metadata r10 = r10.peekId3Data(r13, r9)
            r12.metadata = r10
            androidx.media3.common.Metadata r10 = r12.metadata
            if (r10 == 0) goto L3e
            androidx.media3.extractor.GaplessInfoHolder r10 = r12.gaplessInfoHolder
            androidx.media3.common.Metadata r11 = r12.metadata
            r10.setFromMetadata(r11)
        L3e:
            long r10 = r13.getPeekPosition()
            int r2 = (int) r10
            if (r14 != 0) goto L48
            r13.skipFully(r2)
        L48:
            boolean r5 = r12.peekEndOfStreamOrHeader(r13)
            if (r5 == 0) goto L57
            if (r0 <= 0) goto L51
            goto La2
        L51:
            java.io.EOFException r5 = new java.io.EOFException
            r5.<init>()
            throw r5
        L57:
            androidx.media3.common.util.ParsableByteArray r5 = r12.scratch
            r5.setPosition(r7)
            androidx.media3.common.util.ParsableByteArray r5 = r12.scratch
            int r5 = r5.readInt()
            if (r1 == 0) goto L6b
            long r9 = (long) r1
            boolean r9 = headersMatch(r5, r9)
            if (r9 == 0) goto L73
        L6b:
            int r9 = androidx.media3.extractor.MpegAudioUtil.getFrameSize(r5)
            r10 = r9
            r11 = -1
            if (r9 != r11) goto L93
        L73:
            int r9 = r3 + 1
            if (r3 != r4) goto L81
            if (r14 == 0) goto L7a
            return r7
        L7a:
            java.lang.String r3 = "Searched too many bytes."
            androidx.media3.common.ParserException r3 = androidx.media3.common.ParserException.createForMalformedContainer(r3, r6)
            throw r3
        L81:
            r0 = 0
            r1 = 0
            if (r14 == 0) goto L8e
            r13.resetPeekPosition()
            int r3 = r2 + r9
            r13.advancePeekPosition(r3)
            goto L91
        L8e:
            r13.skipFully(r8)
        L91:
            r3 = r9
            goto Lb5
        L93:
            int r0 = r0 + 1
            if (r0 != r8) goto L9e
            androidx.media3.extractor.MpegAudioUtil$Header r9 = r12.synchronizedHeader
            r9.setForHeaderData(r5)
            r1 = r5
            goto Lb0
        L9e:
            r9 = 4
            if (r0 != r9) goto Lb0
        La2:
            if (r14 == 0) goto Laa
            int r5 = r2 + r3
            r13.skipFully(r5)
            goto Lad
        Laa:
            r13.resetPeekPosition()
        Lad:
            r12.synchronizedHeaderData = r1
            return r8
        Lb0:
            int r9 = r10 + (-4)
            r13.advancePeekPosition(r9)
        Lb5:
            goto L48
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp3.Mp3Extractor.synchronize(androidx.media3.extractor.ExtractorInput, boolean):boolean");
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput extractorInput) throws IOException {
        if (this.seeker != null) {
            long dataEndPosition = this.seeker.getDataEndPosition();
            if (dataEndPosition != -1 && extractorInput.getPeekPosition() > dataEndPosition - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.peekFully(this.scratch.getData(), 0, 4, true);
        } catch (EOFException e) {
            return true;
        }
    }

    private Seeker computeSeeker(ExtractorInput input) throws IOException {
        long durationUs;
        Seeker seekFrameSeeker = maybeReadSeekFrame(input);
        Seeker metadataSeeker = maybeHandleSeekMetadata(this.metadata, input.getPosition());
        if (this.disableSeeking) {
            return new Seeker.UnseekableSeeker();
        }
        Seeker resultSeeker = null;
        if ((this.flags & 4) != 0) {
            long dataEndPosition = -1;
            if (metadataSeeker != null) {
                durationUs = metadataSeeker.getDurationUs();
                dataEndPosition = metadataSeeker.getDataEndPosition();
            } else if (seekFrameSeeker != null) {
                durationUs = seekFrameSeeker.getDurationUs();
                dataEndPosition = seekFrameSeeker.getDataEndPosition();
            } else {
                durationUs = getId3TlenUs(this.metadata);
            }
            resultSeeker = new IndexSeeker(durationUs, input.getPosition(), dataEndPosition);
        } else if (metadataSeeker != null) {
            resultSeeker = metadataSeeker;
        } else if (seekFrameSeeker != null) {
            resultSeeker = seekFrameSeeker;
        }
        if (resultSeeker == null || (!resultSeeker.isSeekable() && (this.flags & 1) != 0)) {
            Seeker resultSeeker2 = getConstantBitrateSeeker(input, (this.flags & 2) != 0);
            return resultSeeker2;
        }
        return resultSeeker;
    }

    private Seeker maybeReadSeekFrame(ExtractorInput input) throws IOException {
        ParsableByteArray frame = new ParsableByteArray(this.synchronizedHeader.frameSize);
        input.peekFully(frame.getData(), 0, this.synchronizedHeader.frameSize);
        int i = 21;
        if ((this.synchronizedHeader.version & 1) != 0) {
            if (this.synchronizedHeader.channels != 1) {
                i = 36;
            }
        } else if (this.synchronizedHeader.channels == 1) {
            i = 13;
        }
        int xingBase = i;
        int seekHeader = getSeekFrameHeader(frame, xingBase);
        switch (seekHeader) {
            case SEEK_HEADER_INFO /* 1231971951 */:
            case SEEK_HEADER_XING /* 1483304551 */:
                XingFrame xingFrame = XingFrame.parse(this.synchronizedHeader, frame);
                if (!this.gaplessInfoHolder.hasGaplessInfo() && xingFrame.encoderDelay != -1 && xingFrame.encoderPadding != -1) {
                    this.gaplessInfoHolder.encoderDelay = xingFrame.encoderDelay;
                    this.gaplessInfoHolder.encoderPadding = xingFrame.encoderPadding;
                }
                long startPosition = input.getPosition();
                if (input.getLength() != -1 && xingFrame.dataSize != -1 && input.getLength() != xingFrame.dataSize + startPosition) {
                    Log.i(TAG, "Data size mismatch between stream (" + input.getLength() + ") and Xing frame (" + (xingFrame.dataSize + startPosition) + "), using Xing value.");
                }
                input.skipFully(this.synchronizedHeader.frameSize);
                if (seekHeader == SEEK_HEADER_XING) {
                    Seeker seeker = XingSeeker.create(xingFrame, startPosition);
                    return seeker;
                }
                Seeker seeker2 = getConstantBitrateSeeker(startPosition, xingFrame, input.getLength());
                return seeker2;
            case SEEK_HEADER_VBRI /* 1447187017 */:
                Seeker seeker3 = VbriSeeker.create(input.getLength(), input.getPosition(), this.synchronizedHeader, frame);
                input.skipFully(this.synchronizedHeader.frameSize);
                return seeker3;
            default:
                input.resetPeekPosition();
                return null;
        }
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput input, boolean allowSeeksIfLengthUnknown) throws IOException {
        input.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(input.getLength(), input.getPosition(), this.synchronizedHeader, allowSeeksIfLengthUnknown);
    }

    private Seeker getConstantBitrateSeeker(long infoFramePosition, XingFrame infoFrame, long fallbackStreamLength) {
        long streamLength;
        long audioLength;
        long durationUs = infoFrame.computeDurationUs();
        if (durationUs == C.TIME_UNSET) {
            return null;
        }
        if (infoFrame.dataSize != -1) {
            long streamLength2 = infoFramePosition + infoFrame.dataSize;
            streamLength = streamLength2;
            audioLength = infoFrame.dataSize - infoFrame.header.frameSize;
        } else {
            if (fallbackStreamLength == -1) {
                return null;
            }
            streamLength = fallbackStreamLength;
            audioLength = (fallbackStreamLength - infoFramePosition) - infoFrame.header.frameSize;
        }
        int averageBitrate = Ints.checkedCast(Util.scaleLargeValue(audioLength, 8000000L, durationUs, RoundingMode.HALF_UP));
        int frameSize = Ints.checkedCast(LongMath.divide(audioLength, infoFrame.frameCount, RoundingMode.HALF_UP));
        return new ConstantBitrateSeeker(streamLength, infoFramePosition + infoFrame.header.frameSize, averageBitrate, frameSize, false);
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private static boolean headersMatch(int headerA, long headerB) {
        return ((long) (MPEG_AUDIO_HEADER_MASK & headerA)) == ((-128000) & headerB);
    }

    private static int getSeekFrameHeader(ParsableByteArray frame, int xingBase) {
        if (frame.limit() >= xingBase + 4) {
            frame.setPosition(xingBase);
            int headerData = frame.readInt();
            if (headerData == SEEK_HEADER_XING || headerData == SEEK_HEADER_INFO) {
                return headerData;
            }
        }
        if (frame.limit() >= 40) {
            frame.setPosition(36);
            if (frame.readInt() == SEEK_HEADER_VBRI) {
                return SEEK_HEADER_VBRI;
            }
            return 0;
        }
        return 0;
    }

    private static MlltSeeker maybeHandleSeekMetadata(Metadata metadata, long firstFramePosition) {
        if (metadata != null) {
            int length = metadata.length();
            for (int i = 0; i < length; i++) {
                Metadata.Entry entry = metadata.get(i);
                if (entry instanceof MlltFrame) {
                    return MlltSeeker.create(firstFramePosition, (MlltFrame) entry, getId3TlenUs(metadata));
                }
            }
            return null;
        }
        return null;
    }

    private static long getId3TlenUs(Metadata metadata) {
        if (metadata != null) {
            int length = metadata.length();
            for (int i = 0; i < length; i++) {
                Metadata.Entry entry = metadata.get(i);
                if ((entry instanceof TextInformationFrame) && ((TextInformationFrame) entry).id.equals("TLEN")) {
                    return Util.msToUs(Long.parseLong(((TextInformationFrame) entry).values.get(0)));
                }
            }
            return C.TIME_UNSET;
        }
        return C.TIME_UNSET;
    }
}
