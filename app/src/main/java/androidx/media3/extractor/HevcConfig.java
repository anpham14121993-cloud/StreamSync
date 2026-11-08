package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes24.dex */
public final class HevcConfig {
    private static final int SPS_NAL_UNIT_TYPE = 33;
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    public static HevcConfig parse(ParsableByteArray data) throws ParserException {
        int numberOfArrays;
        int csdStartPosition;
        int nalUnitType;
        try {
            data.skipBytes(21);
            int lengthSizeMinusOne = data.readUnsignedByte() & 3;
            int numberOfArrays2 = data.readUnsignedByte();
            int csdLength = 0;
            int csdStartPosition2 = data.getPosition();
            for (int i = 0; i < numberOfArrays2; i++) {
                data.skipBytes(1);
                int numberOfNalUnits = data.readUnsignedShort();
                for (int j = 0; j < numberOfNalUnits; j++) {
                    int nalUnitLength = data.readUnsignedShort();
                    csdLength += nalUnitLength + 4;
                    data.skipBytes(nalUnitLength);
                }
            }
            data.setPosition(csdStartPosition2);
            byte[] buffer = new byte[csdLength];
            int bufferPosition = 0;
            int width = -1;
            int height = -1;
            int bitdepthLuma = -1;
            int bitdepthChroma = -1;
            int colorSpace = -1;
            int colorRange = -1;
            int colorTransfer = -1;
            float pixelWidthHeightRatio = 1.0f;
            int maxNumReorderPics = -1;
            String codecs = null;
            for (int width2 = 0; width2 < numberOfArrays2; width2++) {
                int nalUnitType2 = data.readUnsignedByte() & 63;
                int numberOfNalUnits2 = data.readUnsignedShort();
                int j2 = 0;
                while (j2 < numberOfNalUnits2) {
                    int nalUnitLength2 = data.readUnsignedShort();
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, buffer, bufferPosition, NalUnitUtil.NAL_START_CODE.length);
                    int bufferPosition2 = bufferPosition + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(data.getData(), data.getPosition(), buffer, bufferPosition2, nalUnitLength2);
                    if (nalUnitType2 != 33 || j2 != 0) {
                        numberOfArrays = numberOfArrays2;
                        csdStartPosition = csdStartPosition2;
                        nalUnitType = nalUnitType2;
                    } else {
                        NalUnitUtil.H265SpsData spsData = NalUnitUtil.parseH265SpsNalUnit(buffer, bufferPosition2, bufferPosition2 + nalUnitLength2);
                        width = spsData.width;
                        height = spsData.height;
                        bitdepthLuma = spsData.bitDepthLumaMinus8 + 8;
                        bitdepthChroma = spsData.bitDepthChromaMinus8 + 8;
                        colorSpace = spsData.colorSpace;
                        colorRange = spsData.colorRange;
                        colorTransfer = spsData.colorTransfer;
                        pixelWidthHeightRatio = spsData.pixelWidthHeightRatio;
                        maxNumReorderPics = spsData.maxNumReorderPics;
                        int i2 = spsData.generalProfileSpace;
                        boolean z = spsData.generalTierFlag;
                        int i3 = spsData.generalProfileIdc;
                        numberOfArrays = numberOfArrays2;
                        int numberOfArrays3 = spsData.generalProfileCompatibilityFlags;
                        csdStartPosition = csdStartPosition2;
                        int[] iArr = spsData.constraintBytes;
                        nalUnitType = nalUnitType2;
                        int nalUnitType3 = spsData.generalLevelIdc;
                        codecs = CodecSpecificDataUtil.buildHevcCodecString(i2, z, i3, numberOfArrays3, iArr, nalUnitType3);
                    }
                    bufferPosition = bufferPosition2 + nalUnitLength2;
                    data.skipBytes(nalUnitLength2);
                    j2++;
                    nalUnitType2 = nalUnitType;
                    numberOfArrays2 = numberOfArrays;
                    csdStartPosition2 = csdStartPosition;
                }
            }
            List<byte[]> initializationData = csdLength == 0 ? Collections.emptyList() : Collections.singletonList(buffer);
            return new HevcConfig(initializationData, lengthSizeMinusOne + 1, width, height, bitdepthLuma, bitdepthChroma, colorSpace, colorRange, colorTransfer, pixelWidthHeightRatio, maxNumReorderPics, codecs);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing HEVC config", e);
        }
    }

    private HevcConfig(List<byte[]> initializationData, int nalUnitLengthFieldLength, int width, int height, int bitdepthLuma, int bitdepthChroma, int colorSpace, int colorRange, int colorTransfer, float pixelWidthHeightRatio, int maxNumReorderPics, String codecs) {
        this.initializationData = initializationData;
        this.nalUnitLengthFieldLength = nalUnitLengthFieldLength;
        this.width = width;
        this.height = height;
        this.bitdepthLuma = bitdepthLuma;
        this.bitdepthChroma = bitdepthChroma;
        this.colorSpace = colorSpace;
        this.colorRange = colorRange;
        this.colorTransfer = colorTransfer;
        this.pixelWidthHeightRatio = pixelWidthHeightRatio;
        this.maxNumReorderPics = maxNumReorderPics;
        this.codecs = codecs;
    }
}
