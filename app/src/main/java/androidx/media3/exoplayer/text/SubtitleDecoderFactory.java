package androidx.media3.exoplayer.text;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.cea.Cea608Decoder;
import androidx.media3.extractor.text.cea.Cea708Decoder;
import java.util.Objects;

/* loaded from: classes10.dex */
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() { // from class: androidx.media3.exoplayer.text.SubtitleDecoderFactory.1
        private final DefaultSubtitleParserFactory delegate = new DefaultSubtitleParserFactory();

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format format) {
            String mimeType = format.sampleMimeType;
            return this.delegate.supportsFormat(format) || Objects.equals(mimeType, MimeTypes.APPLICATION_CEA608) || Objects.equals(mimeType, MimeTypes.APPLICATION_MP4CEA608) || Objects.equals(mimeType, MimeTypes.APPLICATION_CEA708);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format format) {
            char c;
            String mimeType = format.sampleMimeType;
            if (mimeType != null) {
                switch (mimeType.hashCode()) {
                    case 930165504:
                        if (mimeType.equals(MimeTypes.APPLICATION_MP4CEA608)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1566015601:
                        if (mimeType.equals(MimeTypes.APPLICATION_CEA608)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1566016562:
                        if (mimeType.equals(MimeTypes.APPLICATION_CEA708)) {
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
                    case 1:
                        return new Cea608Decoder(mimeType, format.accessibilityChannel, Cea608Decoder.MIN_DATA_CHANNEL_TIMEOUT_MS);
                    case 2:
                        return new Cea708Decoder(format.accessibilityChannel, format.initializationData);
                }
            }
            if (this.delegate.supportsFormat(format)) {
                SubtitleParser subtitleParser = this.delegate.create(format);
                return new DelegatingSubtitleDecoder(subtitleParser.getClass().getSimpleName() + "Decoder", subtitleParser);
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported MIME type: " + mimeType);
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
