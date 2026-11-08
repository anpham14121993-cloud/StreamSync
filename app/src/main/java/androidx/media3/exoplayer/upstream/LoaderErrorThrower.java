package androidx.media3.exoplayer.upstream;

import java.io.IOException;

/* loaded from: classes10.dex */
public interface LoaderErrorThrower {
    void maybeThrowError() throws IOException;

    void maybeThrowError(int i) throws IOException;

    /* loaded from: classes10.dex */
    public static final class Placeholder implements LoaderErrorThrower {
        @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
        public void maybeThrowError() {
        }

        @Override // androidx.media3.exoplayer.upstream.LoaderErrorThrower
        public void maybeThrowError(int minRetryCount) {
        }
    }
}
