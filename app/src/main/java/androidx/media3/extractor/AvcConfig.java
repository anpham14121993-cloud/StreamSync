package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes24.dex */
public final class AvcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderFrames;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    public static AvcConfig parse(ParsableByteArray data) throws ParserException {
        int height;
        int height2;
        int bitdepthLuma;
        int bitdepthChroma;
        int colorSpace;
        int colorRange;
        int colorTransfer;
        float pixelWidthHeightRatio;
        String codecs;
        int maxNumReorderFrames;
        try {
            data.skipBytes(4);
            int nalUnitLengthFieldLength = (data.readUnsignedByte() & 3) + 1;
            if (nalUnitLengthFieldLength == 3) {
                throw new IllegalStateException();
            }
            List<byte[]> initializationData = new ArrayList<>();
            int numSequenceParameterSets = data.readUnsignedByte() & 31;
            for (int j = 0; j < numSequenceParameterSets; j++) {
                initializationData.add(buildNalUnitForChild(data));
            }
            int numPictureParameterSets = data.readUnsignedByte();
            for (int j2 = 0; j2 < numPictureParameterSets; j2++) {
                initializationData.add(buildNalUnitForChild(data));
            }
            if (numSequenceParameterSets <= 0) {
                height = -1;
                height2 = -1;
                bitdepthLuma = -1;
                bitdepthChroma = -1;
                colorSpace = -1;
                colorRange = -1;
                colorTransfer = -1;
                pixelWidthHeightRatio = 1.0f;
                codecs = null;
                maxNumReorderFrames = 16;
            } else {
                byte[] sps = initializationData.get(0);
                byte[] bArr = initializationData.get(0);
                int width = sps.length;
                NalUnitUtil.SpsData spsData = NalUnitUtil.parseSpsNalUnit(bArr, nalUnitLengthFieldLength, width);
                int width2 = spsData.width;
                int height3 = spsData.height;
                int bitdepthLuma2 = spsData.bitDepthLumaMinus8 + 8;
                int bitdepthChroma2 = spsData.bitDepthChromaMinus8 + 8;
                int colorSpace2 = spsData.colorSpace;
                int colorRange2 = spsData.colorRange;
                int colorTransfer2 = spsData.colorTransfer;
                int maxNumReorderFrames2 = spsData.maxNumReorderFrames;
                float pixelWidthHeightRatio2 = spsData.pixelWidthHeightRatio;
                int i = spsData.profileIdc;
                int height4 = spsData.constraintsFlagsAndReservedZero2Bits;
                bitdepthLuma = bitdepthLuma2;
                int bitdepthLuma3 = spsData.levelIdc;
                String codecs2 = CodecSpecificDataUtil.buildAvcCodecString(i, height4, bitdepthLuma3);
                bitdepthChroma = bitdepthChroma2;
                colorSpace = colorSpace2;
                colorRange = colorRange2;
                colorTransfer = colorTransfer2;
                pixelWidthHeightRatio = pixelWidthHeightRatio2;
                codecs = codecs2;
                maxNumReorderFrames = maxNumReorderFrames2;
                height2 = height3;
                height = width2;
            }
            return new AvcConfig(initializationData, nalUnitLengthFieldLength, height, height2, bitdepthLuma, bitdepthChroma, colorSpace, colorRange, colorTransfer, maxNumReorderFrames, pixelWidthHeightRatio, codecs);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing AVC config", e);
        }
    }

    private AvcConfig(List<byte[]> initializationData, int nalUnitLengthFieldLength, int width, int height, int bitdepthLuma, int bitdepthChroma, int colorSpace, int colorRange, int colorTransfer, int maxNumReorderFrames, float pixelWidthHeightRatio, String codecs) {
        this.initializationData = initializationData;
        this.nalUnitLengthFieldLength = nalUnitLengthFieldLength;
        this.width = width;
        this.height = height;
        this.bitdepthLuma = bitdepthLuma;
        this.bitdepthChroma = bitdepthChroma;
        this.colorSpace = colorSpace;
        this.colorRange = colorRange;
        this.colorTransfer = colorTransfer;
        this.maxNumReorderFrames = maxNumReorderFrames;
        this.pixelWidthHeightRatio = pixelWidthHeightRatio;
        this.codecs = codecs;
    }

    private static byte[] buildNalUnitForChild(ParsableByteArray data) {
        int length = data.readUnsignedShort();
        int offset = data.getPosition();
        data.skipBytes(length);
        return CodecSpecificDataUtil.buildNalUnit(data.getData(), offset, length);
    }
}
