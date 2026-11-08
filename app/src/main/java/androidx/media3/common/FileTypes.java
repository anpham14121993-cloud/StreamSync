package androidx.media3.common;

import android.net.Uri;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

/* loaded from: classes24.dex */
public final class FileTypes {
    public static final int AC3 = 0;
    public static final int AC4 = 1;
    public static final int ADTS = 2;
    public static final int AMR = 3;
    public static final int AVI = 16;
    public static final int AVIF = 21;
    public static final int BMP = 19;
    private static final String EXTENSION_AAC = ".aac";
    private static final String EXTENSION_AC3 = ".ac3";
    private static final String EXTENSION_AC4 = ".ac4";
    private static final String EXTENSION_ADTS = ".adts";
    private static final String EXTENSION_AMR = ".amr";
    private static final String EXTENSION_AVI = ".avi";
    private static final String EXTENSION_AVIF = ".avif";
    private static final String EXTENSION_BMP = ".bmp";
    private static final String EXTENSION_DIB = ".dib";
    private static final String EXTENSION_EC3 = ".ec3";
    private static final String EXTENSION_FLAC = ".flac";
    private static final String EXTENSION_FLV = ".flv";
    private static final String EXTENSION_HEIC = ".heic";
    private static final String EXTENSION_HEIF = ".heif";
    private static final String EXTENSION_JPEG = ".jpeg";
    private static final String EXTENSION_JPG = ".jpg";
    private static final String EXTENSION_M2P = ".m2p";
    private static final String EXTENSION_MID = ".mid";
    private static final String EXTENSION_MIDI = ".midi";
    private static final String EXTENSION_MP3 = ".mp3";
    private static final String EXTENSION_MP4 = ".mp4";
    private static final String EXTENSION_MPEG = ".mpeg";
    private static final String EXTENSION_MPG = ".mpg";
    private static final String EXTENSION_OPUS = ".opus";
    private static final String EXTENSION_PNG = ".png";
    private static final String EXTENSION_PREFIX_CMF = ".cmf";
    private static final String EXTENSION_PREFIX_M4 = ".m4";
    private static final String EXTENSION_PREFIX_MK = ".mk";
    private static final String EXTENSION_PREFIX_MP4 = ".mp4";
    private static final String EXTENSION_PREFIX_OG = ".og";
    private static final String EXTENSION_PREFIX_TS = ".ts";
    private static final String EXTENSION_PS = ".ps";
    private static final String EXTENSION_SMF = ".smf";
    private static final String EXTENSION_TS = ".ts";
    private static final String EXTENSION_VTT = ".vtt";
    private static final String EXTENSION_WAV = ".wav";
    private static final String EXTENSION_WAVE = ".wave";
    private static final String EXTENSION_WEBM = ".webm";
    private static final String EXTENSION_WEBP = ".webp";
    private static final String EXTENSION_WEBVTT = ".webvtt";
    public static final int FLAC = 4;
    public static final int FLV = 5;
    static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final int HEIF = 20;
    public static final int JPEG = 14;
    public static final int MATROSKA = 6;
    public static final int MIDI = 15;
    public static final int MP3 = 7;
    public static final int MP4 = 8;
    public static final int OGG = 9;
    public static final int PNG = 17;
    public static final int PS = 10;
    public static final int TS = 11;
    public static final int UNKNOWN = -1;
    public static final int WAV = 12;
    public static final int WEBP = 18;
    public static final int WEBVTT = 13;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface Type {
    }

    private FileTypes() {
    }

    public static int inferFileTypeFromResponseHeaders(Map<String, List<String>> responseHeaders) {
        List<String> contentTypes = responseHeaders.get("Content-Type");
        String mimeType = (contentTypes == null || contentTypes.isEmpty()) ? null : contentTypes.get(0);
        return inferFileTypeFromMimeType(mimeType);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int inferFileTypeFromMimeType(String mimeType) {
        char c;
        if (mimeType == null) {
            return -1;
        }
        String mimeType2 = MimeTypes.normalizeMimeType(mimeType);
        switch (mimeType2.hashCode()) {
            case -2123537834:
                if (mimeType2.equals(MimeTypes.AUDIO_E_AC3_JOC)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1662384011:
                if (mimeType2.equals(MimeTypes.VIDEO_PS)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1662384007:
                if (mimeType2.equals(MimeTypes.VIDEO_MP2T)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1662095187:
                if (mimeType2.equals(MimeTypes.VIDEO_WEBM)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1606874997:
                if (mimeType2.equals(MimeTypes.AUDIO_AMR_WB)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1487656890:
                if (mimeType2.equals(MimeTypes.IMAGE_AVIF)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -1487464693:
                if (mimeType2.equals("image/heic")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -1487464690:
                if (mimeType2.equals("image/heif")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -1487394660:
                if (mimeType2.equals("image/jpeg")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -1487018032:
                if (mimeType2.equals("image/webp")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -1248337486:
                if (mimeType2.equals(MimeTypes.APPLICATION_MP4)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1079884372:
                if (mimeType2.equals(MimeTypes.VIDEO_AVI)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -1004728940:
                if (mimeType2.equals(MimeTypes.TEXT_VTT)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -879272239:
                if (mimeType2.equals(MimeTypes.IMAGE_BMP)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -879258763:
                if (mimeType2.equals(MimeTypes.IMAGE_PNG)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -387023398:
                if (mimeType2.equals(MimeTypes.AUDIO_MATROSKA)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -43467528:
                if (mimeType2.equals(MimeTypes.APPLICATION_WEBM)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 13915911:
                if (mimeType2.equals(MimeTypes.VIDEO_FLV)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 187078296:
                if (mimeType2.equals(MimeTypes.AUDIO_AC3)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 187078297:
                if (mimeType2.equals(MimeTypes.AUDIO_AC4)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 187078669:
                if (mimeType2.equals(MimeTypes.AUDIO_AMR)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 187090232:
                if (mimeType2.equals(MimeTypes.AUDIO_MP4)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 187091926:
                if (mimeType2.equals(MimeTypes.AUDIO_OGG)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 187099443:
                if (mimeType2.equals(MimeTypes.AUDIO_WAV)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 1331848029:
                if (mimeType2.equals(MimeTypes.VIDEO_MP4)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1503095341:
                if (mimeType2.equals(MimeTypes.AUDIO_AMR_NB)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1504578661:
                if (mimeType2.equals(MimeTypes.AUDIO_E_AC3)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1504619009:
                if (mimeType2.equals(MimeTypes.AUDIO_FLAC)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1504824762:
                if (mimeType2.equals(MimeTypes.AUDIO_MIDI)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1504831518:
                if (mimeType2.equals(MimeTypes.AUDIO_MPEG)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1505118770:
                if (mimeType2.equals(MimeTypes.AUDIO_WEBM)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 2039520277:
                if (mimeType2.equals(MimeTypes.VIDEO_MATROSKA)) {
                    c = '\n';
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
            case 1:
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
            case 5:
            case 6:
                return 3;
            case 7:
                return 4;
            case '\b':
                return 5;
            case '\t':
                return 15;
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
                return 6;
            case 15:
                return 7;
            case 16:
            case 17:
            case 18:
                return 8;
            case 19:
                return 9;
            case 20:
                return 10;
            case 21:
                return 11;
            case 22:
                return 12;
            case 23:
                return 13;
            case 24:
                return 14;
            case 25:
                return 16;
            case 26:
                return 17;
            case 27:
                return 18;
            case 28:
                return 19;
            case 29:
            case 30:
                return 20;
            case 31:
                return 21;
            default:
                return -1;
        }
    }

    public static int inferFileTypeFromUri(Uri uri) {
        String filename = uri.getLastPathSegment();
        if (filename == null) {
            return -1;
        }
        if (filename.endsWith(EXTENSION_AC3) || filename.endsWith(EXTENSION_EC3)) {
            return 0;
        }
        if (filename.endsWith(EXTENSION_AC4)) {
            return 1;
        }
        if (filename.endsWith(EXTENSION_ADTS) || filename.endsWith(EXTENSION_AAC)) {
            return 2;
        }
        if (filename.endsWith(EXTENSION_AMR)) {
            return 3;
        }
        if (filename.endsWith(EXTENSION_FLAC)) {
            return 4;
        }
        if (filename.endsWith(EXTENSION_FLV)) {
            return 5;
        }
        if (filename.endsWith(EXTENSION_MID) || filename.endsWith(EXTENSION_MIDI) || filename.endsWith(EXTENSION_SMF)) {
            return 15;
        }
        if (filename.startsWith(EXTENSION_PREFIX_MK, filename.length() - (EXTENSION_PREFIX_MK.length() + 1)) || filename.endsWith(EXTENSION_WEBM)) {
            return 6;
        }
        if (filename.endsWith(EXTENSION_MP3)) {
            return 7;
        }
        if (filename.endsWith(".mp4") || filename.startsWith(EXTENSION_PREFIX_M4, filename.length() - (EXTENSION_PREFIX_M4.length() + 1)) || filename.startsWith(".mp4", filename.length() - (".mp4".length() + 1)) || filename.startsWith(EXTENSION_PREFIX_CMF, filename.length() - (EXTENSION_PREFIX_CMF.length() + 1))) {
            return 8;
        }
        if (filename.startsWith(EXTENSION_PREFIX_OG, filename.length() - (EXTENSION_PREFIX_OG.length() + 1)) || filename.endsWith(EXTENSION_OPUS)) {
            return 9;
        }
        if (filename.endsWith(EXTENSION_PS) || filename.endsWith(EXTENSION_MPEG) || filename.endsWith(EXTENSION_MPG) || filename.endsWith(EXTENSION_M2P)) {
            return 10;
        }
        if (filename.endsWith(".ts") || filename.startsWith(".ts", filename.length() - (".ts".length() + 1))) {
            return 11;
        }
        if (filename.endsWith(EXTENSION_WAV) || filename.endsWith(EXTENSION_WAVE)) {
            return 12;
        }
        if (filename.endsWith(EXTENSION_VTT) || filename.endsWith(EXTENSION_WEBVTT)) {
            return 13;
        }
        if (filename.endsWith(EXTENSION_JPG) || filename.endsWith(EXTENSION_JPEG)) {
            return 14;
        }
        if (filename.endsWith(EXTENSION_AVI)) {
            return 16;
        }
        if (filename.endsWith(EXTENSION_PNG)) {
            return 17;
        }
        if (filename.endsWith(EXTENSION_WEBP)) {
            return 18;
        }
        if (filename.endsWith(EXTENSION_BMP) || filename.endsWith(EXTENSION_DIB)) {
            return 19;
        }
        if (filename.endsWith(EXTENSION_HEIC) || filename.endsWith(EXTENSION_HEIF)) {
            return 20;
        }
        if (!filename.endsWith(EXTENSION_AVIF)) {
            return -1;
        }
        return 21;
    }
}
