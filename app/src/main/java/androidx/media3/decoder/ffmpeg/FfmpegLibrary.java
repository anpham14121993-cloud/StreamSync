package androidx.media3.decoder.ffmpeg;

import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.LibraryLoader;
import androidx.media3.common.util.Log;

/* loaded from: classes23.dex */
public final class FfmpegLibrary {
    private static final LibraryLoader LOADER;
    private static final String TAG = "FfmpegLibrary";
    private static int inputBufferPaddingSize;
    private static String version;

    private static native int ffmpegGetInputBufferPaddingSize();

    private static native String ffmpegGetVersion();

    private static native boolean ffmpegHasDecoder(String codecName);

    static {
        MediaLibraryInfo.registerModule("media3.decoder.ffmpeg");
        LOADER = new LibraryLoader("ffmpegJNI") { // from class: androidx.media3.decoder.ffmpeg.FfmpegLibrary.1
            @Override // androidx.media3.common.util.LibraryLoader
            protected void loadLibrary(String name) {
                System.loadLibrary(name);
            }
        };
        inputBufferPaddingSize = -1;
    }

    private FfmpegLibrary() {
    }

    public static void setLibraries(String... libraries) {
        LOADER.setLibraries(libraries);
    }

    public static boolean isAvailable() {
        return LOADER.isAvailable();
    }

    public static String getVersion() {
        if (!isAvailable()) {
            return null;
        }
        if (version == null) {
            version = ffmpegGetVersion();
        }
        return version;
    }

    public static int getInputBufferPaddingSize() {
        if (!isAvailable()) {
            return -1;
        }
        if (inputBufferPaddingSize == -1) {
            inputBufferPaddingSize = ffmpegGetInputBufferPaddingSize();
        }
        return inputBufferPaddingSize;
    }

    public static boolean supportsFormat(String mimeType) {
        String codecName;
        if (!isAvailable() || (codecName = getCodecName(mimeType)) == null) {
            return false;
        }
        if (!ffmpegHasDecoder(codecName)) {
            Log.w(TAG, "No " + codecName + " decoder available. Check the FFmpeg build configuration.");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getCodecName(String mimeType) {
        char c;
        switch (mimeType.hashCode()) {
            case -2123537834:
                if (mimeType.equals(MimeTypes.AUDIO_E_AC3_JOC)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1662541442:
                if (mimeType.equals(MimeTypes.VIDEO_H265)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -1606874997:
                if (mimeType.equals(MimeTypes.AUDIO_AMR_WB)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1095064472:
                if (mimeType.equals(MimeTypes.AUDIO_DTS)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1003765268:
                if (mimeType.equals(MimeTypes.AUDIO_VORBIS)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -432837260:
                if (mimeType.equals(MimeTypes.AUDIO_MPEG_L1)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -432837259:
                if (mimeType.equals(MimeTypes.AUDIO_MPEG_L2)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -53558318:
                if (mimeType.equals(MimeTypes.AUDIO_AAC)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 187078296:
                if (mimeType.equals(MimeTypes.AUDIO_AC3)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1331836730:
                if (mimeType.equals(MimeTypes.VIDEO_H264)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1503095341:
                if (mimeType.equals(MimeTypes.AUDIO_AMR_NB)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1504470054:
                if (mimeType.equals(MimeTypes.AUDIO_ALAC)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1504578661:
                if (mimeType.equals(MimeTypes.AUDIO_E_AC3)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1504619009:
                if (mimeType.equals(MimeTypes.AUDIO_FLAC)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1504831518:
                if (mimeType.equals(MimeTypes.AUDIO_MPEG)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1504891608:
                if (mimeType.equals(MimeTypes.AUDIO_OPUS)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1505942594:
                if (mimeType.equals(MimeTypes.AUDIO_DTS_HD)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1556697186:
                if (mimeType.equals(MimeTypes.AUDIO_TRUEHD)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1903231877:
                if (mimeType.equals(MimeTypes.AUDIO_ALAW)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 1903589369:
                if (mimeType.equals(MimeTypes.AUDIO_MLAW)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "aac";
            case 1:
            case 2:
            case 3:
                return "mp3";
            case 4:
                return "ac3";
            case 5:
            case 6:
                return "eac3";
            case 7:
                return "truehd";
            case '\b':
            case '\t':
                return "dca";
            case '\n':
                return "vorbis";
            case 11:
                return "opus";
            case '\f':
                return "amrnb";
            case '\r':
                return "amrwb";
            case 14:
                return "flac";
            case 15:
                return "alac";
            case 16:
                return "pcm_mulaw";
            case 17:
                return "pcm_alaw";
            case 18:
                return "h264";
            case 19:
                return "hevc";
            default:
                return null;
        }
    }
}
