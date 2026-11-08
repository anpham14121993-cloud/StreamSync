package com.streamsync.app.settings;

import android.content.Context;
import com.streamsync.app.settings.DeviceMemory;
import com.streamsync.app.util.ImageCacheBudget;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: BufferCeiling.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nJ\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/settings/BufferCeiling;", "", "()V", "AVAILABLE_SHARE", "", "HEAP_SHARE", "LOW_MEMORY_SHARE", "MB", "", "MIN_CEILING_MB", "", "UI_RESERVE_MB", "compute", "tier", "Lcom/streamsync/app/settings/RamTier;", "heapLimitMb", "availableMb", "lowMemory", "", "imageCacheMb", "forDevice", "context", "Landroid/content/Context;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class BufferCeiling {
    public static final int $stable = 0;
    public static final float AVAILABLE_SHARE = 0.6f;
    public static final float HEAP_SHARE = 0.75f;
    public static final BufferCeiling INSTANCE = new BufferCeiling();
    public static final float LOW_MEMORY_SHARE = 0.3f;
    private static final long MB = 1048576;
    public static final int MIN_CEILING_MB = 48;
    public static final int UI_RESERVE_MB = 96;

    private BufferCeiling() {
    }

    public final int compute(RamTier tier, int heapLimitMb, int availableMb, boolean lowMemory, int imageCacheMb) {
        Intrinsics.checkNotNullParameter(tier, "tier");
        int byTier = tier.getSafetyLimitMb();
        int heapForBuffer = (heapLimitMb - imageCacheMb) - 96;
        int byHeap = (int) (heapForBuffer * 0.75f);
        float share = lowMemory ? 0.3f : 0.6f;
        int byAvailable = (int) (availableMb * share);
        return RangesKt.coerceAtLeast(Math.min(byTier, Math.min(byHeap, byAvailable)), 48);
    }

    public final int forDevice(Context context, RamTier tier) {
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tier, "tier");
        try {
            Result.Companion companion = Result.INSTANCE;
            BufferCeiling $this$forDevice_u24lambda_u240 = this;
            DeviceMemory.Info info = DeviceMemory.INSTANCE.read(context);
            int heapMb = (int) (Runtime.getRuntime().maxMemory() / 1048576);
            int imageMb = (int) (ImageCacheBudget.INSTANCE.memoryCacheBytes(context) / 1048576);
            m6717constructorimpl = Result.m6717constructorimpl(Integer.valueOf($this$forDevice_u24lambda_u240.compute(tier, heapMb, info.getAvailableMb(), info.getLowMemory(), imageMb)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = 48;
        }
        return ((Number) m6717constructorimpl).intValue();
    }
}
