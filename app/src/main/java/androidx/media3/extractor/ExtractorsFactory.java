package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

/* loaded from: classes24.dex */
public interface ExtractorsFactory {
    public static final ExtractorsFactory EMPTY = new ExtractorsFactory() { // from class: androidx.media3.extractor.ExtractorsFactory$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return ExtractorsFactory.lambda$static$0();
        }
    };

    Extractor[] createExtractors();

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[0];
    }

    @Deprecated
    default ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean textTrackTranscodingEnabled) {
        return this;
    }

    default ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory subtitleParserFactory) {
        return this;
    }

    default Extractor[] createExtractors(Uri uri, Map<String, List<String>> responseHeaders) {
        return createExtractors();
    }
}
