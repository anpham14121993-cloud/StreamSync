package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;
import java.util.List;

/* loaded from: classes12.dex */
public interface ChunkExtractor {

    /* loaded from: classes12.dex */
    public interface TrackOutputProvider {
        TrackOutput track(int i, int i2);
    }

    ChunkIndex getChunkIndex();

    Format[] getSampleFormats();

    void init(TrackOutputProvider trackOutputProvider, long j, long j2);

    boolean read(ExtractorInput extractorInput) throws IOException;

    void release();

    /* loaded from: classes12.dex */
    public interface Factory {
        ChunkExtractor createProgressiveMediaExtractor(int i, Format format, boolean z, List<Format> list, TrackOutput trackOutput, PlayerId playerId);

        default Factory setSubtitleParserFactory(SubtitleParser.Factory subtitleParserFactory) {
            return this;
        }

        default Factory experimentalParseSubtitlesDuringExtraction(boolean parseSubtitlesDuringExtraction) {
            return this;
        }

        default Format getOutputTextFormat(Format sourceFormat) {
            return sourceFormat;
        }
    }
}
