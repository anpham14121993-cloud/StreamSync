package androidx.media3.container;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes24.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int NAL_UNIT_TYPE_AUD = 9;
    public static final int NAL_UNIT_TYPE_IDR = 5;
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int NAL_UNIT_TYPE_PPS = 8;
    public static final int NAL_UNIT_TYPE_PREFIX = 14;
    public static final int NAL_UNIT_TYPE_SEI = 6;
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    /* loaded from: classes24.dex */
    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int profileIdc, int constraintsFlagsAndReservedZero2Bits, int levelIdc, int seqParameterSetId, int maxNumRefFrames, int width, int height, float pixelWidthHeightRatio, int bitDepthLumaMinus8, int bitDepthChromaMinus8, boolean separateColorPlaneFlag, boolean frameMbsOnlyFlag, int frameNumLength, int picOrderCountType, int picOrderCntLsbLength, boolean deltaPicOrderAlwaysZeroFlag, int colorSpace, int colorRange, int colorTransfer, int maxNumReorderFrames) {
            this.profileIdc = profileIdc;
            this.constraintsFlagsAndReservedZero2Bits = constraintsFlagsAndReservedZero2Bits;
            this.levelIdc = levelIdc;
            this.seqParameterSetId = seqParameterSetId;
            this.maxNumRefFrames = maxNumRefFrames;
            this.width = width;
            this.height = height;
            this.pixelWidthHeightRatio = pixelWidthHeightRatio;
            this.bitDepthLumaMinus8 = bitDepthLumaMinus8;
            this.bitDepthChromaMinus8 = bitDepthChromaMinus8;
            this.separateColorPlaneFlag = separateColorPlaneFlag;
            this.frameMbsOnlyFlag = frameMbsOnlyFlag;
            this.frameNumLength = frameNumLength;
            this.picOrderCountType = picOrderCountType;
            this.picOrderCntLsbLength = picOrderCntLsbLength;
            this.deltaPicOrderAlwaysZeroFlag = deltaPicOrderAlwaysZeroFlag;
            this.colorSpace = colorSpace;
            this.colorRange = colorRange;
            this.colorTransfer = colorTransfer;
            this.maxNumReorderFrames = maxNumReorderFrames;
        }
    }

    /* loaded from: classes24.dex */
    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;
        public final int height;
        public final int maxNumReorderPics;
        public final float pixelWidthHeightRatio;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(int generalProfileSpace, boolean generalTierFlag, int generalProfileIdc, int generalProfileCompatibilityFlags, int chromaFormatIdc, int bitDepthLumaMinus8, int bitDepthChromaMinus8, int[] constraintBytes, int generalLevelIdc, int seqParameterSetId, int width, int height, float pixelWidthHeightRatio, int maxNumReorderPics, int colorSpace, int colorRange, int colorTransfer) {
            this.generalProfileSpace = generalProfileSpace;
            this.generalTierFlag = generalTierFlag;
            this.generalProfileIdc = generalProfileIdc;
            this.generalProfileCompatibilityFlags = generalProfileCompatibilityFlags;
            this.chromaFormatIdc = chromaFormatIdc;
            this.bitDepthLumaMinus8 = bitDepthLumaMinus8;
            this.bitDepthChromaMinus8 = bitDepthChromaMinus8;
            this.constraintBytes = constraintBytes;
            this.generalLevelIdc = generalLevelIdc;
            this.seqParameterSetId = seqParameterSetId;
            this.width = width;
            this.height = height;
            this.pixelWidthHeightRatio = pixelWidthHeightRatio;
            this.maxNumReorderPics = maxNumReorderPics;
            this.colorSpace = colorSpace;
            this.colorRange = colorRange;
            this.colorTransfer = colorTransfer;
        }
    }

    /* loaded from: classes24.dex */
    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int picParameterSetId, int seqParameterSetId, boolean bottomFieldPicOrderInFramePresentFlag) {
            this.picParameterSetId = picParameterSetId;
            this.seqParameterSetId = seqParameterSetId;
            this.bottomFieldPicOrderInFramePresentFlag = bottomFieldPicOrderInFramePresentFlag;
        }
    }

    public static int unescapeStream(byte[] data, int limit) {
        int unescapedLength;
        synchronized (scratchEscapePositionsLock) {
            int position = 0;
            int scratchEscapeCount = 0;
            while (position < limit) {
                try {
                    position = findNextUnescapeIndex(data, position, limit);
                    if (position < limit) {
                        if (scratchEscapePositions.length <= scratchEscapeCount) {
                            scratchEscapePositions = Arrays.copyOf(scratchEscapePositions, scratchEscapePositions.length * 2);
                        }
                        scratchEscapePositions[scratchEscapeCount] = position;
                        position += 3;
                        scratchEscapeCount++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            unescapedLength = limit - scratchEscapeCount;
            int escapedPosition = 0;
            int unescapedPosition = 0;
            for (int i = 0; i < scratchEscapeCount; i++) {
                int nextEscapePosition = scratchEscapePositions[i];
                int copyLength = nextEscapePosition - escapedPosition;
                System.arraycopy(data, escapedPosition, data, unescapedPosition, copyLength);
                int unescapedPosition2 = unescapedPosition + copyLength;
                int unescapedPosition3 = unescapedPosition2 + 1;
                data[unescapedPosition2] = 0;
                unescapedPosition = unescapedPosition3 + 1;
                data[unescapedPosition3] = 0;
                escapedPosition += copyLength + 3;
            }
            int i2 = unescapedLength - unescapedPosition;
            System.arraycopy(data, escapedPosition, data, unescapedPosition, i2);
        }
        return unescapedLength;
    }

    public static void discardToSps(ByteBuffer data) {
        int length = data.position();
        int consecutiveZeros = 0;
        for (int offset = 0; offset + 1 < length; offset++) {
            int value = data.get(offset) & 255;
            if (consecutiveZeros == 3) {
                if (value == 1 && (data.get(offset + 1) & Ascii.US) == 7) {
                    ByteBuffer offsetData = data.duplicate();
                    offsetData.position(offset - 3);
                    offsetData.limit(length);
                    data.position(0);
                    data.put(offsetData);
                    return;
                }
            } else if (value == 0) {
                consecutiveZeros++;
            }
            if (value != 0) {
                consecutiveZeros = 0;
            }
        }
        data.clear();
    }

    public static boolean isNalUnitSei(String mimeType, byte nalUnitHeaderFirstByte) {
        if (MimeTypes.VIDEO_H264.equals(mimeType) && (nalUnitHeaderFirstByte & Ascii.US) == 6) {
            return true;
        }
        return MimeTypes.VIDEO_H265.equals(mimeType) && ((nalUnitHeaderFirstByte & 126) >> 1) == 39;
    }

    public static int getNalUnitType(byte[] data, int offset) {
        return data[offset + 3] & Ascii.US;
    }

    public static int getH265NalUnitType(byte[] data, int offset) {
        return (data[offset + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(byte[] nalData, int nalOffset, int nalLimit) {
        return parseSpsNalUnitPayload(nalData, nalOffset + 1, nalLimit);
    }

    public static SpsData parseSpsNalUnitPayload(byte[] bArr, int i, int i2) {
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        float f;
        int i13;
        int i14;
        int i15;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        int readBits = parsableNalUnitBitArray.readBits(8);
        int readBits2 = parsableNalUnitBitArray.readBits(8);
        int readBits3 = parsableNalUnitBitArray.readBits(8);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        boolean z3 = false;
        if (readBits != 100 && readBits != 110 && readBits != 122 && readBits != 244 && readBits != 44 && readBits != 83 && readBits != 86 && readBits != 118 && readBits != 128 && readBits != 138) {
            i3 = 1;
            z = false;
            i4 = 0;
            i5 = 0;
        } else {
            int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (readUnsignedExpGolombCodedInt2 == 3) {
                z3 = parsableNalUnitBitArray.readBit();
            }
            int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                int i16 = readUnsignedExpGolombCodedInt2 != 3 ? 8 : 12;
                int i17 = 0;
                while (true) {
                    int i18 = i16;
                    if (i17 >= i18) {
                        break;
                    }
                    if (parsableNalUnitBitArray.readBit()) {
                        skipScalingList(parsableNalUnitBitArray, i17 < 6 ? 16 : 64);
                    }
                    i17++;
                    i16 = i18;
                }
            }
            i3 = readUnsignedExpGolombCodedInt2;
            z = z3;
            i4 = readUnsignedExpGolombCodedInt3;
            i5 = readUnsignedExpGolombCodedInt4;
        }
        int readUnsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        int readUnsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (readUnsignedExpGolombCodedInt6 == 0) {
            i6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
            z2 = false;
        } else if (readUnsignedExpGolombCodedInt6 != 1) {
            i6 = 0;
            z2 = false;
        } else {
            boolean readBit = parsableNalUnitBitArray.readBit();
            parsableNalUnitBitArray.readSignedExpGolombCodedInt();
            parsableNalUnitBitArray.readSignedExpGolombCodedInt();
            long readUnsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            for (int i19 = 0; i19 < readUnsignedExpGolombCodedInt7; i19++) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            i6 = 0;
            z2 = readBit;
        }
        int readUnsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        int readUnsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        int readUnsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        boolean readBit2 = parsableNalUnitBitArray.readBit();
        int i20 = (2 - (readBit2 ? 1 : 0)) * readUnsignedExpGolombCodedInt10;
        if (!readBit2) {
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBit();
        int i21 = readUnsignedExpGolombCodedInt9 * 16;
        int i22 = i20 * 16;
        if (!parsableNalUnitBitArray.readBit()) {
            i7 = i21;
            i8 = i22;
        } else {
            int readUnsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt12 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt13 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt14 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (i3 == 0) {
                i14 = 1;
                i15 = 2 - (readBit2 ? 1 : 0);
            } else {
                i14 = i3 == 3 ? 1 : 2;
                i15 = (2 - (readBit2 ? 1 : 0)) * (i3 == 1 ? 2 : 1);
            }
            i7 = i21 - ((readUnsignedExpGolombCodedInt11 + readUnsignedExpGolombCodedInt12) * i14);
            i8 = i22 - ((readUnsignedExpGolombCodedInt13 + readUnsignedExpGolombCodedInt14) * i15);
        }
        int i23 = -1;
        int i24 = -1;
        int i25 = -1;
        float f2 = 1.0f;
        if ((readBits == 44 || readBits == 86 || readBits == 100 || readBits == 110 || readBits == 122 || readBits == 244) && (readBits2 & 16) != 0) {
            i9 = 0;
        } else {
            i9 = 16;
        }
        if (!parsableNalUnitBitArray.readBit()) {
            i10 = -1;
            i11 = -1;
            i12 = -1;
            f = 1.0f;
            i13 = i9;
        } else {
            if (parsableNalUnitBitArray.readBit()) {
                int readBits4 = parsableNalUnitBitArray.readBits(8);
                if (readBits4 == 255) {
                    int readBits5 = parsableNalUnitBitArray.readBits(16);
                    int readBits6 = parsableNalUnitBitArray.readBits(16);
                    if (readBits5 != 0 && readBits6 != 0) {
                        f2 = readBits5 / readBits6;
                    }
                } else if (readBits4 < ASPECT_RATIO_IDC_VALUES.length) {
                    f2 = ASPECT_RATIO_IDC_VALUES[readBits4];
                } else {
                    Log.w(TAG, "Unexpected aspect_ratio_idc value: " + readBits4);
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(3);
                i24 = parsableNalUnitBitArray.readBit() ? 1 : 2;
                if (parsableNalUnitBitArray.readBit()) {
                    int readBits7 = parsableNalUnitBitArray.readBits(8);
                    int readBits8 = parsableNalUnitBitArray.readBits(8);
                    parsableNalUnitBitArray.skipBits(8);
                    i23 = ColorInfo.isoColorPrimariesToColorSpace(readBits7);
                    i25 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readBits8);
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(65);
            }
            boolean readBit3 = parsableNalUnitBitArray.readBit();
            if (readBit3) {
                skipHrdParameters(parsableNalUnitBitArray);
            }
            boolean readBit4 = parsableNalUnitBitArray.readBit();
            if (readBit4) {
                skipHrdParameters(parsableNalUnitBitArray);
            }
            if (readBit3 || readBit4) {
                parsableNalUnitBitArray.skipBit();
            }
            parsableNalUnitBitArray.skipBit();
            if (!parsableNalUnitBitArray.readBit()) {
                i10 = i23;
                i11 = i24;
                i12 = i25;
                f = f2;
                i13 = i9;
            } else {
                parsableNalUnitBitArray.skipBit();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int readUnsignedExpGolombCodedInt15 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                i10 = i23;
                i11 = i24;
                i12 = i25;
                f = f2;
                i13 = readUnsignedExpGolombCodedInt15;
            }
        }
        return new SpsData(readBits, readBits2, readBits3, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt8, i7, i8, f, i4, i5, z, readBit2, readUnsignedExpGolombCodedInt5, readUnsignedExpGolombCodedInt6, i6, z2, i10, i11, i12, i13);
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] nalData, int nalOffset, int nalLimit) {
        return parseH265SpsNalUnitPayload(nalData, nalOffset + 2, nalLimit);
    }

    public static H265SpsData parseH265SpsNalUnitPayload(byte[] nalData, int nalOffset, int nalLimit) {
        int frameWidth;
        int colorSpace;
        int frameHeight;
        int colorRange;
        int colorTransfer;
        float pixelWidthHeightRatio;
        ParsableNalUnitBitArray data = new ParsableNalUnitBitArray(nalData, nalOffset, nalLimit);
        data.skipBits(4);
        int maxSubLayersMinus1 = data.readBits(3);
        data.skipBit();
        int generalProfileSpace = data.readBits(2);
        boolean generalTierFlag = data.readBit();
        int generalProfileIdc = data.readBits(5);
        int generalProfileCompatibilityFlags = 0;
        for (int i = 0; i < 32; i++) {
            if (data.readBit()) {
                generalProfileCompatibilityFlags |= 1 << i;
            }
        }
        int[] constraintBytes = new int[6];
        for (int i2 = 0; i2 < constraintBytes.length; i2++) {
            constraintBytes[i2] = data.readBits(8);
        }
        int generalLevelIdc = data.readBits(8);
        int toSkip = 0;
        for (int i3 = 0; i3 < maxSubLayersMinus1; i3++) {
            if (data.readBit()) {
                toSkip += 89;
            }
            if (data.readBit()) {
                toSkip += 8;
            }
        }
        data.skipBits(toSkip);
        if (maxSubLayersMinus1 > 0) {
            data.skipBits((8 - maxSubLayersMinus1) * 2);
        }
        int seqParameterSetId = data.readUnsignedExpGolombCodedInt();
        int chromaFormatIdc = data.readUnsignedExpGolombCodedInt();
        if (chromaFormatIdc == 3) {
            data.skipBit();
        }
        int frameWidth2 = data.readUnsignedExpGolombCodedInt();
        int frameHeight2 = data.readUnsignedExpGolombCodedInt();
        if (!data.readBit()) {
            frameWidth = frameWidth2;
        } else {
            int confWinLeftOffset = data.readUnsignedExpGolombCodedInt();
            int confWinRightOffset = data.readUnsignedExpGolombCodedInt();
            int confWinTopOffset = data.readUnsignedExpGolombCodedInt();
            int confWinBottomOffset = data.readUnsignedExpGolombCodedInt();
            int subWidthC = (chromaFormatIdc == 1 || chromaFormatIdc == 2) ? 2 : 1;
            int subHeightC = chromaFormatIdc == 1 ? 2 : 1;
            frameHeight2 -= (confWinTopOffset + confWinBottomOffset) * subHeightC;
            frameWidth = frameWidth2 - ((confWinLeftOffset + confWinRightOffset) * subWidthC);
        }
        int bitDepthLumaMinus8 = data.readUnsignedExpGolombCodedInt();
        int bitDepthChromaMinus8 = data.readUnsignedExpGolombCodedInt();
        int log2MaxPicOrderCntLsbMinus4 = data.readUnsignedExpGolombCodedInt();
        int maxNumReorderPics = -1;
        for (int i4 = data.readBit() ? 0 : maxSubLayersMinus1; i4 <= maxSubLayersMinus1; i4++) {
            data.readUnsignedExpGolombCodedInt();
            maxNumReorderPics = Math.max(data.readUnsignedExpGolombCodedInt(), maxNumReorderPics);
            data.readUnsignedExpGolombCodedInt();
        }
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        boolean scalingListEnabled = data.readBit();
        if (scalingListEnabled && data.readBit()) {
            skipH265ScalingList(data);
        }
        data.skipBits(2);
        if (data.readBit()) {
            data.skipBits(8);
            data.readUnsignedExpGolombCodedInt();
            data.readUnsignedExpGolombCodedInt();
            data.skipBit();
        }
        skipShortTermReferencePictureSets(data);
        if (data.readBit()) {
            int numLongTermRefPicsSps = data.readUnsignedExpGolombCodedInt();
            for (int i5 = 0; i5 < numLongTermRefPicsSps; i5++) {
                int ltRefPicPocLsbSpsLength = log2MaxPicOrderCntLsbMinus4 + 4;
                data.skipBits(ltRefPicPocLsbSpsLength + 1);
            }
        }
        data.skipBits(2);
        int colorSpace2 = -1;
        int colorRange2 = -1;
        int colorTransfer2 = -1;
        float pixelWidthHeightRatio2 = 1.0f;
        if (!data.readBit()) {
            colorSpace = -1;
            frameHeight = frameHeight2;
            colorRange = -1;
            colorTransfer = -1;
            pixelWidthHeightRatio = 1.0f;
        } else {
            if (data.readBit()) {
                int aspectRatioIdc = data.readBits(8);
                if (aspectRatioIdc == 255) {
                    int sarWidth = data.readBits(16);
                    int sarHeight = data.readBits(16);
                    if (sarWidth != 0 && sarHeight != 0) {
                        pixelWidthHeightRatio2 = sarWidth / sarHeight;
                    }
                } else if (aspectRatioIdc < ASPECT_RATIO_IDC_VALUES.length) {
                    pixelWidthHeightRatio2 = ASPECT_RATIO_IDC_VALUES[aspectRatioIdc];
                } else {
                    Log.w(TAG, "Unexpected aspect_ratio_idc value: " + aspectRatioIdc);
                }
            }
            if (data.readBit()) {
                data.skipBit();
            }
            if (data.readBit()) {
                data.skipBits(3);
                int colorRange3 = data.readBit() ? 1 : 2;
                if (!data.readBit()) {
                    colorRange2 = colorRange3;
                } else {
                    int colorPrimaries = data.readBits(8);
                    int transferCharacteristics = data.readBits(8);
                    data.skipBits(8);
                    int colorSpace3 = ColorInfo.isoColorPrimariesToColorSpace(colorPrimaries);
                    colorRange2 = colorRange3;
                    colorTransfer2 = ColorInfo.isoTransferCharacteristicsToColorTransfer(transferCharacteristics);
                    colorSpace2 = colorSpace3;
                }
            }
            if (data.readBit()) {
                data.readUnsignedExpGolombCodedInt();
                data.readUnsignedExpGolombCodedInt();
            }
            data.skipBit();
            if (!data.readBit()) {
                colorSpace = colorSpace2;
                frameHeight = frameHeight2;
                colorRange = colorRange2;
                colorTransfer = colorTransfer2;
                pixelWidthHeightRatio = pixelWidthHeightRatio2;
            } else {
                colorSpace = colorSpace2;
                frameHeight = frameHeight2 * 2;
                colorRange = colorRange2;
                colorTransfer = colorTransfer2;
                pixelWidthHeightRatio = pixelWidthHeightRatio2;
            }
        }
        return new H265SpsData(generalProfileSpace, generalTierFlag, generalProfileIdc, generalProfileCompatibilityFlags, chromaFormatIdc, bitDepthLumaMinus8, bitDepthChromaMinus8, constraintBytes, generalLevelIdc, seqParameterSetId, frameWidth, frameHeight, pixelWidthHeightRatio, maxNumReorderPics, colorSpace, colorRange, colorTransfer);
    }

    public static PpsData parsePpsNalUnit(byte[] nalData, int nalOffset, int nalLimit) {
        return parsePpsNalUnitPayload(nalData, nalOffset + 1, nalLimit);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] nalData, int nalOffset, int nalLimit) {
        ParsableNalUnitBitArray data = new ParsableNalUnitBitArray(nalData, nalOffset, nalLimit);
        int picParameterSetId = data.readUnsignedExpGolombCodedInt();
        int seqParameterSetId = data.readUnsignedExpGolombCodedInt();
        data.skipBit();
        boolean bottomFieldPicOrderInFramePresentFlag = data.readBit();
        return new PpsData(picParameterSetId, seqParameterSetId, bottomFieldPicOrderInFramePresentFlag);
    }

    public static int findNalUnit(byte[] data, int startOffset, int endOffset, boolean[] prefixFlags) {
        boolean z;
        boolean z2;
        int length = endOffset - startOffset;
        Assertions.checkState(length >= 0);
        if (length == 0) {
            return endOffset;
        }
        if (prefixFlags[0]) {
            clearPrefixFlags(prefixFlags);
            return startOffset - 3;
        }
        if (length > 1 && prefixFlags[1] && data[startOffset] == 1) {
            clearPrefixFlags(prefixFlags);
            return startOffset - 2;
        }
        if (length > 2 && prefixFlags[2] && data[startOffset] == 0 && data[startOffset + 1] == 1) {
            clearPrefixFlags(prefixFlags);
            return startOffset - 1;
        }
        int limit = endOffset - 1;
        int i = startOffset + 2;
        while (i < limit) {
            if ((data[i] & 254) == 0) {
                if (data[i - 2] == 0 && data[i - 1] == 0 && data[i] == 1) {
                    clearPrefixFlags(prefixFlags);
                    return i - 2;
                }
                i -= 2;
            }
            i += 3;
        }
        if (length > 2) {
            z = data[endOffset + (-3)] == 0 && data[endOffset + (-2)] == 0 && data[endOffset + (-1)] == 1;
        } else if (length == 2) {
            z = prefixFlags[2] && data[endOffset + (-2)] == 0 && data[endOffset + (-1)] == 1;
        } else {
            z = prefixFlags[1] && data[endOffset + (-1)] == 1;
        }
        prefixFlags[0] = z;
        if (length > 1) {
            z2 = data[endOffset + (-2)] == 0 && data[endOffset + (-1)] == 0;
        } else {
            z2 = prefixFlags[2] && data[endOffset + (-1)] == 0;
        }
        prefixFlags[1] = z2;
        prefixFlags[2] = data[endOffset + (-1)] == 0;
        return endOffset;
    }

    public static void clearPrefixFlags(boolean[] prefixFlags) {
        prefixFlags[0] = false;
        prefixFlags[1] = false;
        prefixFlags[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bytes, int offset, int limit) {
        for (int i = offset; i < limit - 2; i++) {
            if (bytes[i] == 0 && bytes[i + 1] == 0 && bytes[i + 2] == 3) {
                return i;
            }
        }
        return limit;
    }

    private static void skipScalingList(ParsableNalUnitBitArray bitArray, int size) {
        int lastScale = 8;
        int nextScale = 8;
        for (int i = 0; i < size; i++) {
            if (nextScale != 0) {
                int deltaScale = bitArray.readSignedExpGolombCodedInt();
                nextScale = ((lastScale + deltaScale) + 256) % 256;
            }
            lastScale = nextScale == 0 ? lastScale : nextScale;
        }
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray data) {
        int codedPictureBufferCount = data.readUnsignedExpGolombCodedInt() + 1;
        data.skipBits(8);
        for (int i = 0; i < codedPictureBufferCount; i++) {
            data.readUnsignedExpGolombCodedInt();
            data.readUnsignedExpGolombCodedInt();
            data.skipBit();
        }
        data.skipBits(20);
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray bitArray) {
        for (int sizeId = 0; sizeId < 4; sizeId++) {
            int matrixId = 0;
            while (matrixId < 6) {
                int i = 1;
                if (!bitArray.readBit()) {
                    bitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int coefNum = Math.min(64, 1 << ((sizeId << 1) + 4));
                    if (sizeId > 1) {
                        bitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i2 = 0; i2 < coefNum; i2++) {
                        bitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (sizeId == 3) {
                    i = 3;
                }
                matrixId += i;
            }
        }
    }

    private static void skipShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i;
        int readUnsignedExpGolombCodedInt;
        int readUnsignedExpGolombCodedInt2;
        int[] iArr;
        int[] iArr2;
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i2 = -1;
        int i3 = -1;
        boolean z = false;
        int[] iArr3 = new int[0];
        int[] iArr4 = new int[0];
        int i4 = 0;
        while (i4 < readUnsignedExpGolombCodedInt3) {
            if ((i4 == 0 || !parsableNalUnitBitArray.readBit()) ? z : true) {
                int i5 = i2 + i3;
                int readUnsignedExpGolombCodedInt4 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                boolean[] zArr = new boolean[i5 + 1];
                for (int i6 = 0; i6 <= i5; i6++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[i6] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[i6] = true;
                    }
                }
                int i7 = 0;
                int[] iArr5 = new int[i5 + 1];
                int[] iArr6 = new int[i5 + 1];
                for (int i8 = i3 - 1; i8 >= 0; i8--) {
                    int i9 = iArr4[i8] + readUnsignedExpGolombCodedInt4;
                    if (i9 < 0 && zArr[i2 + i8]) {
                        iArr5[i7] = i9;
                        i7++;
                    }
                }
                if (readUnsignedExpGolombCodedInt4 < 0 && zArr[i5]) {
                    iArr5[i7] = readUnsignedExpGolombCodedInt4;
                    i7++;
                }
                for (int i10 = 0; i10 < i2; i10++) {
                    int i11 = iArr3[i10] + readUnsignedExpGolombCodedInt4;
                    if (i11 < 0 && zArr[i10]) {
                        iArr5[i7] = i11;
                        i7++;
                    }
                }
                readUnsignedExpGolombCodedInt = i7;
                iArr = Arrays.copyOf(iArr5, readUnsignedExpGolombCodedInt);
                int i12 = 0;
                for (int i13 = i2 - 1; i13 >= 0; i13--) {
                    int i14 = iArr3[i13] + readUnsignedExpGolombCodedInt4;
                    if (i14 > 0 && zArr[i13]) {
                        iArr6[i12] = i14;
                        i12++;
                    }
                }
                if (readUnsignedExpGolombCodedInt4 > 0 && zArr[i5]) {
                    iArr6[i12] = readUnsignedExpGolombCodedInt4;
                    i12++;
                }
                i = readUnsignedExpGolombCodedInt3;
                for (int i15 = 0; i15 < i3; i15++) {
                    int i16 = iArr4[i15] + readUnsignedExpGolombCodedInt4;
                    if (i16 > 0 && zArr[i2 + i15]) {
                        iArr6[i12] = i16;
                        i12++;
                    }
                }
                readUnsignedExpGolombCodedInt2 = i12;
                iArr2 = Arrays.copyOf(iArr6, readUnsignedExpGolombCodedInt2);
            } else {
                i = readUnsignedExpGolombCodedInt3;
                readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                iArr = new int[readUnsignedExpGolombCodedInt];
                int i17 = 0;
                while (i17 < readUnsignedExpGolombCodedInt) {
                    iArr[i17] = (i17 > 0 ? iArr[i17 - 1] : 0) - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i17++;
                }
                int[] iArr7 = new int[readUnsignedExpGolombCodedInt2];
                int i18 = 0;
                while (i18 < readUnsignedExpGolombCodedInt2) {
                    iArr7[i18] = (i18 > 0 ? iArr7[i18 - 1] : 0) + parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                    i18++;
                }
                iArr2 = iArr7;
            }
            i2 = readUnsignedExpGolombCodedInt;
            i3 = readUnsignedExpGolombCodedInt2;
            iArr3 = iArr;
            iArr4 = iArr2;
            i4++;
            readUnsignedExpGolombCodedInt3 = i;
            z = false;
        }
    }

    private NalUnitUtil() {
    }
}
