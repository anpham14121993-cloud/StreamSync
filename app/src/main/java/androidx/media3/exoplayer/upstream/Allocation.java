package androidx.media3.exoplayer.upstream;

import java.nio.ByteBuffer;

/* loaded from: classes10.dex */
public final class Allocation {
    public final ByteBuffer buffer;
    public final int offset;

    public Allocation(ByteBuffer buffer, int offset) {
        this.buffer = buffer;
        this.offset = offset;
    }
}
