package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WarmWindowSkip.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"SKIP_BUFFER_BYTES", "", "skipForwardBytes", "", "stream", "Ljava/io/InputStream;", "count", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class WarmWindowSkipKt {
    private static final int SKIP_BUFFER_BYTES = 65536;

    public static final long skipForwardBytes(InputStream stream, long count) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        if (count <= 0) {
            return 0L;
        }
        long skipped = 0;
        byte[] scratch = new byte[65536];
        while (skipped < count) {
            int want = (int) Math.min(scratch.length, count - skipped);
            int n = stream.read(scratch, 0, want);
            if (n <= 0) {
                break;
            }
            skipped += n;
        }
        return skipped;
    }
}
