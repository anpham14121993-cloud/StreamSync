package androidx.media3.extractor.ogg;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes24.dex */
public class OggExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.ogg.OggExtractor$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return OggExtractor.lambda$static$0();
        }
    };
    private static final int MAX_VERIFICATION_BYTES = 8;
    private ExtractorOutput output;
    private StreamReader streamReader;
    private boolean streamReaderInitialized;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new OggExtractor()};
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        try {
            return sniffInternal(input);
        } catch (ParserException e) {
            return false;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        this.output = output;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
        if (this.streamReader != null) {
            this.streamReader.seek(position, timeUs);
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        Assertions.checkStateNotNull(this.output);
        if (this.streamReader == null) {
            if (!sniffInternal(input)) {
                throw ParserException.createForMalformedContainer("Failed to determine bitstream type", null);
            }
            input.resetPeekPosition();
        }
        if (!this.streamReaderInitialized) {
            TrackOutput trackOutput = this.output.track(0, 1);
            this.output.endTracks();
            this.streamReader.init(this.output, trackOutput);
            this.streamReaderInitialized = true;
        }
        return this.streamReader.read(input, seekPosition);
    }

    @EnsuresNonNullIf(expression = {"streamReader"}, result = true)
    private boolean sniffInternal(ExtractorInput input) throws IOException {
        OggPageHeader header = new OggPageHeader();
        if (!header.populate(input, true) || (header.type & 2) != 2) {
            return false;
        }
        int length = Math.min(header.bodySize, 8);
        ParsableByteArray scratch = new ParsableByteArray(length);
        input.peekFully(scratch.getData(), 0, length);
        if (FlacReader.verifyBitstreamType(resetPosition(scratch))) {
            this.streamReader = new FlacReader();
        } else if (VorbisReader.verifyBitstreamType(resetPosition(scratch))) {
            this.streamReader = new VorbisReader();
        } else {
            if (!OpusReader.verifyBitstreamType(resetPosition(scratch))) {
                return false;
            }
            this.streamReader = new OpusReader();
        }
        return true;
    }

    private static ParsableByteArray resetPosition(ParsableByteArray scratch) {
        scratch.setPosition(0);
        return scratch;
    }
}
