package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;

/* loaded from: classes24.dex */
public final class AtomSizeTooSmallSniffFailure implements SniffFailure {
    public final long atomSize;
    public final int atomType;
    public final int minimumHeaderSize;

    public AtomSizeTooSmallSniffFailure(int atomType, long atomSize, int minimumHeaderSize) {
        this.atomType = atomType;
        this.atomSize = atomSize;
        this.minimumHeaderSize = minimumHeaderSize;
    }
}
