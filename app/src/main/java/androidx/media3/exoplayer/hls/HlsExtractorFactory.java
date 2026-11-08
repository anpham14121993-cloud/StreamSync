package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public interface HlsExtractorFactory {
    public static final HlsExtractorFactory DEFAULT = new DefaultHlsExtractorFactory();

    HlsMediaChunkExtractor createExtractor(Uri uri, Format format, List<Format> list, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput, PlayerId playerId) throws IOException;

    default HlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory subtitleParserFactory) {
        return this;
    }

    default HlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean parseSubtitlesDuringExtraction) {
        return this;
    }

    default Format getOutputTextFormat(Format sourceFormat) {
        return sourceFormat;
    }
}
