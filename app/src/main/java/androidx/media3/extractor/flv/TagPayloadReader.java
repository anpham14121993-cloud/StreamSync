package androidx.media3.extractor.flv;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes24.dex */
abstract class TagPayloadReader {
    protected final TrackOutput output;

    protected abstract boolean parseHeader(ParsableByteArray parsableByteArray) throws ParserException;

    protected abstract boolean parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException;

    public abstract void seek();

    /* loaded from: classes24.dex */
    public static final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String msg) {
            super(msg, null, false, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TagPayloadReader(TrackOutput output) {
        this.output = output;
    }

    public final boolean consume(ParsableByteArray data, long timeUs) throws ParserException {
        return parseHeader(data) && parsePayload(data, timeUs);
    }
}
