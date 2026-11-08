package androidx.media3.exoplayer.metadata;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.metadata.MetadataDecoder;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import androidx.media3.extractor.metadata.icy.IcyDecoder;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.scte35.SpliceInfoDecoder;

/* loaded from: classes8.dex */
public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new MetadataDecoderFactory() { // from class: androidx.media3.exoplayer.metadata.MetadataDecoderFactory.1
        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format format) {
            String mimeType = format.sampleMimeType;
            return MimeTypes.APPLICATION_ID3.equals(mimeType) || MimeTypes.APPLICATION_EMSG.equals(mimeType) || MimeTypes.APPLICATION_SCTE35.equals(mimeType) || MimeTypes.APPLICATION_ICY.equals(mimeType) || MimeTypes.APPLICATION_AIT.equals(mimeType);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public MetadataDecoder createDecoder(Format format) {
            char c;
            String mimeType = format.sampleMimeType;
            if (mimeType != null) {
                switch (mimeType.hashCode()) {
                    case -1354451219:
                        if (mimeType.equals(MimeTypes.APPLICATION_AIT)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1348231605:
                        if (mimeType.equals(MimeTypes.APPLICATION_ICY)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1248341703:
                        if (mimeType.equals(MimeTypes.APPLICATION_ID3)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1154383568:
                        if (mimeType.equals(MimeTypes.APPLICATION_EMSG)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1652648887:
                        if (mimeType.equals(MimeTypes.APPLICATION_SCTE35)) {
                            c = 2;
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
                        return new Id3Decoder();
                    case 1:
                        return new EventMessageDecoder();
                    case 2:
                        return new SpliceInfoDecoder();
                    case 3:
                        return new IcyDecoder();
                    case 4:
                        return new AppInfoTableDecoder();
                }
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported MIME type: " + mimeType);
        }
    };

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
