package androidx.media3.extractor;

import androidx.core.location.LocationRequestCompat;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import okio.Utf8;

/* loaded from: classes24.dex */
public final class Ac3Util {
    public static final int AC3_MAX_RATE_BYTES_PER_SECOND = 80000;
    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    public static final int E_AC3_MAX_RATE_BYTES_PER_SECOND = 768000;
    public static final int TRUEHD_MAX_RATE_BYTES_PER_SECOND = 3062500;
    public static final int TRUEHD_RECHUNK_SAMPLE_COUNT = 16;
    public static final int TRUEHD_SYNCFRAME_PREFIX_LENGTH = 10;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, 16000};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, PsExtractor.AUDIO_STREAM, 224, 256, 320, RendererCapabilities.DECODER_SUPPORT_MASK, 448, 512, 576, 640};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, LocationRequestCompat.QUALITY_LOW_POWER, 121, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    /* loaded from: classes24.dex */
    public static final class SyncFrameInfo {
        public static final int STREAM_TYPE_TYPE0 = 0;
        public static final int STREAM_TYPE_TYPE1 = 1;
        public static final int STREAM_TYPE_TYPE2 = 2;
        public static final int STREAM_TYPE_UNDEFINED = -1;
        public final int bitrate;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes24.dex */
        public @interface StreamType {
        }

        private SyncFrameInfo(String mimeType, int streamType, int channelCount, int sampleRate, int frameSize, int sampleCount, int bitrate) {
            this.mimeType = mimeType;
            this.streamType = streamType;
            this.channelCount = channelCount;
            this.sampleRate = sampleRate;
            this.frameSize = frameSize;
            this.sampleCount = sampleCount;
            this.bitrate = bitrate;
        }
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray data, String trackId, String language, DrmInitData drmInitData) {
        ParsableBitArray dataBitArray = new ParsableBitArray();
        dataBitArray.reset(data);
        int fscod = dataBitArray.readBits(2);
        int sampleRate = SAMPLE_RATE_BY_FSCOD[fscod];
        dataBitArray.skipBits(8);
        int channelCount = CHANNEL_COUNT_BY_ACMOD[dataBitArray.readBits(3)];
        if (dataBitArray.readBits(1) != 0) {
            channelCount++;
        }
        int halfFrmsizecod = dataBitArray.readBits(5);
        int constantBitrate = BITRATE_BY_HALF_FRMSIZECOD[halfFrmsizecod] * 1000;
        dataBitArray.byteAlign();
        data.setPosition(dataBitArray.getBytePosition());
        return new Format.Builder().setId(trackId).setSampleMimeType(MimeTypes.AUDIO_AC3).setChannelCount(channelCount).setSampleRate(sampleRate).setDrmInitData(drmInitData).setLanguage(language).setAverageBitrate(constantBitrate).setPeakBitrate(constantBitrate).build();
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray data, String trackId, String language, DrmInitData drmInitData) {
        ParsableBitArray dataBitArray = new ParsableBitArray();
        dataBitArray.reset(data);
        int peakBitrate = dataBitArray.readBits(13) * 1000;
        dataBitArray.skipBits(3);
        int fscod = dataBitArray.readBits(2);
        int sampleRate = SAMPLE_RATE_BY_FSCOD[fscod];
        dataBitArray.skipBits(10);
        int channelCount = CHANNEL_COUNT_BY_ACMOD[dataBitArray.readBits(3)];
        if (dataBitArray.readBits(1) != 0) {
            channelCount++;
        }
        dataBitArray.skipBits(3);
        int numDepSub = dataBitArray.readBits(4);
        dataBitArray.skipBits(1);
        if (numDepSub > 0) {
            dataBitArray.skipBits(6);
            if (dataBitArray.readBits(1) != 0) {
                channelCount += 2;
            }
            dataBitArray.skipBits(1);
        }
        String mimeType = MimeTypes.AUDIO_E_AC3;
        if (dataBitArray.bitsLeft() > 7) {
            dataBitArray.skipBits(7);
            if (dataBitArray.readBits(1) != 0) {
                mimeType = MimeTypes.AUDIO_E_AC3_JOC;
            }
        }
        dataBitArray.byteAlign();
        data.setPosition(dataBitArray.getBytePosition());
        return new Format.Builder().setId(trackId).setSampleMimeType(mimeType).setChannelCount(channelCount).setSampleRate(sampleRate).setDrmInitData(drmInitData).setLanguage(language).setPeakBitrate(peakBitrate).build();
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        String str;
        int ac3SyncframeSize;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int readBits;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int position = parsableBitArray.getPosition();
        parsableBitArray.skipBits(40);
        boolean z = parsableBitArray.readBits(5) > 10;
        parsableBitArray.setPosition(position);
        int i13 = -1;
        if (z) {
            parsableBitArray.skipBits(16);
            switch (parsableBitArray.readBits(2)) {
                case 0:
                    i13 = 0;
                    break;
                case 1:
                    i13 = 1;
                    break;
                case 2:
                    i13 = 2;
                    break;
                default:
                    i13 = -1;
                    break;
            }
            parsableBitArray.skipBits(3);
            ac3SyncframeSize = (parsableBitArray.readBits(11) + 1) * 2;
            int readBits2 = parsableBitArray.readBits(2);
            if (readBits2 == 3) {
                readBits = 3;
                i2 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray.readBits(2)];
                i6 = 6;
            } else {
                readBits = parsableBitArray.readBits(2);
                int i14 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[readBits];
                i2 = SAMPLE_RATE_BY_FSCOD[readBits2];
                i6 = i14;
            }
            i3 = i6 * 256;
            i5 = calculateEac3Bitrate(ac3SyncframeSize, i2, i6);
            int readBits3 = parsableBitArray.readBits(3);
            boolean readBit = parsableBitArray.readBit();
            i4 = CHANNEL_COUNT_BY_ACMOD[readBits3] + (readBit ? 1 : 0);
            parsableBitArray.skipBits(10);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(8);
            }
            if (readBits3 == 0) {
                parsableBitArray.skipBits(5);
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(8);
                }
            }
            if (i13 == 1 && parsableBitArray.readBit()) {
                parsableBitArray.skipBits(16);
            }
            if (parsableBitArray.readBit()) {
                if (readBits3 > 2) {
                    parsableBitArray.skipBits(2);
                }
                if ((readBits3 & 1) == 0 || readBits3 <= 2) {
                    i10 = 6;
                } else {
                    i10 = 6;
                    parsableBitArray.skipBits(6);
                }
                if ((readBits3 & 4) != 0) {
                    parsableBitArray.skipBits(i10);
                }
                if (readBit && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(5);
                }
                if (i13 == 0) {
                    if (!parsableBitArray.readBit()) {
                        i11 = 6;
                    } else {
                        i11 = 6;
                        parsableBitArray.skipBits(6);
                    }
                    if (readBits3 == 0 && parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(i11);
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(i11);
                    }
                    int readBits4 = parsableBitArray.readBits(2);
                    if (readBits4 == 1) {
                        parsableBitArray.skipBits(5);
                    } else if (readBits4 == 2) {
                        parsableBitArray.skipBits(12);
                    } else if (readBits4 == 3) {
                        int readBits5 = parsableBitArray.readBits(5);
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(5);
                            if (!parsableBitArray.readBit()) {
                                i12 = 4;
                            } else {
                                i12 = 4;
                                parsableBitArray.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i12);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i12);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i12);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i12);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i12);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i12);
                            }
                            if (parsableBitArray.readBit()) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(i12);
                                }
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(i12);
                                }
                            }
                        }
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(7);
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray.skipBits((readBits5 + 2) * 8);
                        parsableBitArray.byteAlign();
                    }
                    if (readBits3 < 2) {
                        if (parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(14);
                        }
                        if (readBits3 == 0 && parsableBitArray.readBit()) {
                            parsableBitArray.skipBits(14);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        if (readBits == 0) {
                            parsableBitArray.skipBits(5);
                        } else {
                            for (int i15 = 0; i15 < i6; i15++) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (!parsableBitArray.readBit()) {
                i7 = 3;
            } else {
                parsableBitArray.skipBits(5);
                if (readBits3 == 2) {
                    parsableBitArray.skipBits(4);
                }
                if (readBits3 >= 6) {
                    parsableBitArray.skipBits(2);
                }
                if (!parsableBitArray.readBit()) {
                    i9 = 8;
                } else {
                    i9 = 8;
                    parsableBitArray.skipBits(8);
                }
                if (readBits3 == 0 && parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(i9);
                }
                i7 = 3;
                if (readBits2 < 3) {
                    parsableBitArray.skipBit();
                }
            }
            if (i13 == 0 && readBits != i7) {
                parsableBitArray.skipBit();
            }
            if (i13 != 2) {
                i8 = 6;
            } else if (readBits == i7 || parsableBitArray.readBit()) {
                i8 = 6;
                parsableBitArray.skipBits(6);
            } else {
                i8 = 6;
            }
            str = MimeTypes.AUDIO_E_AC3;
            if (parsableBitArray.readBit() && parsableBitArray.readBits(i8) == 1 && parsableBitArray.readBits(8) == 1) {
                str = MimeTypes.AUDIO_E_AC3_JOC;
            }
        } else {
            parsableBitArray.skipBits(32);
            int readBits6 = parsableBitArray.readBits(2);
            if (readBits6 != 3) {
                str = MimeTypes.AUDIO_AC3;
            } else {
                str = null;
            }
            int readBits7 = parsableBitArray.readBits(6);
            int i16 = BITRATE_BY_HALF_FRMSIZECOD[readBits7 / 2] * 1000;
            ac3SyncframeSize = getAc3SyncframeSize(readBits6, readBits7);
            parsableBitArray.skipBits(8);
            int readBits8 = parsableBitArray.readBits(3);
            if ((readBits8 & 1) == 0 || readBits8 == 1) {
                i = 2;
            } else {
                i = 2;
                parsableBitArray.skipBits(2);
            }
            if ((readBits8 & 4) != 0) {
                parsableBitArray.skipBits(i);
            }
            if (readBits8 == i) {
                parsableBitArray.skipBits(i);
            }
            i2 = readBits6 < SAMPLE_RATE_BY_FSCOD.length ? SAMPLE_RATE_BY_FSCOD[readBits6] : -1;
            i3 = AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
            i4 = CHANNEL_COUNT_BY_ACMOD[readBits8] + (parsableBitArray.readBit() ? 1 : 0);
            i5 = i16;
        }
        return new SyncFrameInfo(str, i13, i4, i2, ac3SyncframeSize, i3, i5);
    }

    public static int parseAc3SyncframeSize(byte[] data) {
        if (data.length < 6) {
            return -1;
        }
        boolean isEac3 = ((data[5] & 248) >> 3) > 10;
        if (isEac3) {
            int frmsiz = (data[2] & 7) << 8;
            return (((data[3] & 255) | frmsiz) + 1) * 2;
        }
        int fscod = (data[4] & 192) >> 6;
        int frmsizecod = data[4] & Utf8.REPLACEMENT_BYTE;
        return getAc3SyncframeSize(fscod, frmsizecod);
    }

    public static int parseAc3SyncframeAudioSampleCount(ByteBuffer buffer) {
        boolean isEac3 = ((buffer.get(buffer.position() + 5) & 248) >> 3) > 10;
        if (isEac3) {
            int fscod = (buffer.get(buffer.position() + 4) & 192) >> 6;
            int numblkscod = fscod != 3 ? (buffer.get(buffer.position() + 4) & 48) >> 4 : 3;
            return BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[numblkscod] * 256;
        }
        return AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
    }

    public static int findTrueHdSyncframeOffset(ByteBuffer buffer) {
        int startIndex = buffer.position();
        int endIndex = buffer.limit() - 10;
        for (int i = startIndex; i <= endIndex; i++) {
            if ((Util.getBigEndianInt(buffer, i + 4) & (-2)) == -126718022) {
                return i - startIndex;
            }
        }
        return -1;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] syncframe) {
        if (syncframe[4] != -8 || syncframe[5] != 114 || syncframe[6] != 111 || (syncframe[7] & 254) != 186) {
            return 0;
        }
        boolean isMlp = (syncframe[7] & 255) == 187;
        return 40 << ((syncframe[isMlp ? '\t' : '\b'] >> 4) & 7);
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer buffer, int offset) {
        boolean isMlp = (buffer.get((buffer.position() + offset) + 7) & 255) == 187;
        return 40 << ((buffer.get((buffer.position() + offset) + (isMlp ? 9 : 8)) >> 4) & 7);
    }

    private static int getAc3SyncframeSize(int fscod, int frmsizecod) {
        int halfFrmsizecod = frmsizecod / 2;
        if (fscod < 0 || fscod >= SAMPLE_RATE_BY_FSCOD.length || frmsizecod < 0 || halfFrmsizecod >= SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1.length) {
            return -1;
        }
        int sampleRate = SAMPLE_RATE_BY_FSCOD[fscod];
        if (sampleRate == 44100) {
            return (SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1[halfFrmsizecod] + (frmsizecod % 2)) * 2;
        }
        int bitrate = BITRATE_BY_HALF_FRMSIZECOD[halfFrmsizecod];
        if (sampleRate == 32000) {
            return bitrate * 6;
        }
        return bitrate * 4;
    }

    private static int calculateEac3Bitrate(int frameSize, int sampleRate, int audioBlocks) {
        return (frameSize * sampleRate) / (audioBlocks * 32);
    }

    private Ac3Util() {
    }
}
