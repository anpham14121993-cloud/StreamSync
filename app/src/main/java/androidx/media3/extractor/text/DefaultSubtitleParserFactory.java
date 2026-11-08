package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.dvb.DvbParser;
import androidx.media3.extractor.text.pgs.PgsParser;
import androidx.media3.extractor.text.ssa.SsaParser;
import androidx.media3.extractor.text.subrip.SubripParser;
import androidx.media3.extractor.text.ttml.TtmlParser;
import androidx.media3.extractor.text.tx3g.Tx3gParser;
import androidx.media3.extractor.text.webvtt.Mp4WebvttParser;
import androidx.media3.extractor.text.webvtt.WebvttParser;
import java.util.Objects;

/* loaded from: classes24.dex */
public final class DefaultSubtitleParserFactory implements SubtitleParser.Factory {
    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public boolean supportsFormat(Format format) {
        String mimeType = format.sampleMimeType;
        return Objects.equals(mimeType, MimeTypes.TEXT_SSA) || Objects.equals(mimeType, MimeTypes.TEXT_VTT) || Objects.equals(mimeType, MimeTypes.APPLICATION_MP4VTT) || Objects.equals(mimeType, MimeTypes.APPLICATION_SUBRIP) || Objects.equals(mimeType, MimeTypes.APPLICATION_TX3G) || Objects.equals(mimeType, MimeTypes.APPLICATION_PGS) || Objects.equals(mimeType, MimeTypes.APPLICATION_DVBSUBS) || Objects.equals(mimeType, MimeTypes.APPLICATION_TTML);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public int getCueReplacementBehavior(Format format) {
        char c;
        String mimeType = format.sampleMimeType;
        if (mimeType != null) {
            switch (mimeType.hashCode()) {
                case -1351681404:
                    if (mimeType.equals(MimeTypes.APPLICATION_DVBSUBS)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1248334819:
                    if (mimeType.equals(MimeTypes.APPLICATION_PGS)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -1026075066:
                    if (mimeType.equals(MimeTypes.APPLICATION_MP4VTT)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1004728940:
                    if (mimeType.equals(MimeTypes.TEXT_VTT)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 691401887:
                    if (mimeType.equals(MimeTypes.APPLICATION_TX3G)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 822864842:
                    if (mimeType.equals(MimeTypes.TEXT_SSA)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1668750253:
                    if (mimeType.equals(MimeTypes.APPLICATION_SUBRIP)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1693976202:
                    if (mimeType.equals(MimeTypes.APPLICATION_TTML)) {
                        c = 7;
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
                    return 1;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 1;
                case 4:
                    return 2;
                case 5:
                    return 2;
                case 6:
                    return 2;
                case 7:
                    return 1;
            }
        }
        throw new IllegalArgumentException("Unsupported MIME type: " + mimeType);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public SubtitleParser create(Format format) {
        char c;
        String mimeType = format.sampleMimeType;
        if (mimeType != null) {
            switch (mimeType.hashCode()) {
                case -1351681404:
                    if (mimeType.equals(MimeTypes.APPLICATION_DVBSUBS)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1248334819:
                    if (mimeType.equals(MimeTypes.APPLICATION_PGS)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -1026075066:
                    if (mimeType.equals(MimeTypes.APPLICATION_MP4VTT)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1004728940:
                    if (mimeType.equals(MimeTypes.TEXT_VTT)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 691401887:
                    if (mimeType.equals(MimeTypes.APPLICATION_TX3G)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 822864842:
                    if (mimeType.equals(MimeTypes.TEXT_SSA)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1668750253:
                    if (mimeType.equals(MimeTypes.APPLICATION_SUBRIP)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1693976202:
                    if (mimeType.equals(MimeTypes.APPLICATION_TTML)) {
                        c = 7;
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
                    return new SsaParser(format.initializationData);
                case 1:
                    return new WebvttParser();
                case 2:
                    return new Mp4WebvttParser();
                case 3:
                    return new SubripParser();
                case 4:
                    return new Tx3gParser(format.initializationData);
                case 5:
                    return new PgsParser();
                case 6:
                    return new DvbParser(format.initializationData);
                case 7:
                    return new TtmlParser();
            }
        }
        throw new IllegalArgumentException("Unsupported MIME type: " + mimeType);
    }
}
