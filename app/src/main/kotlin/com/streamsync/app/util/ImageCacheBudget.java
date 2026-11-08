package com.streamsync.app.util;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DeviceMemory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: StreamSyncImageLoader.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"Lcom/streamsync/app/util/ImageCacheBudget;", "", "()V", "diskCacheBytes", "", "context", "Landroid/content/Context;", "httpCacheBytes", "memoryCacheBytes", "useWeakOnly", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class ImageCacheBudget {
    public static final int $stable = 0;
    public static final ImageCacheBudget INSTANCE = new ImageCacheBudget();

    private ImageCacheBudget() {
    }

    public final long memoryCacheBytes(Context context) {
        long mb;
        Intrinsics.checkNotNullParameter(context, "context");
        int totalMb = DeviceMemory.INSTANCE.read(context).getTotalMb();
        if (Integer.MIN_VALUE <= totalMb && totalMb < 2049) {
            mb = 24;
        } else {
            mb = 2049 <= totalMb && totalMb < 4097 ? 48L : 96L;
        }
        long heapMb = Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        return RangesKt.coerceAtMost(mb * 1024 * 1024, (heapMb / 8) * 1024 * 1024);
    }

    public final long diskCacheBytes(Context context) {
        long mb;
        Intrinsics.checkNotNullParameter(context, "context");
        int totalMb = DeviceMemory.INSTANCE.read(context).getTotalMb();
        if (Integer.MIN_VALUE <= totalMb && totalMb < 2049) {
            mb = 64;
        } else {
            mb = 2049 <= totalMb && totalMb < 4097 ? 128L : 200L;
        }
        return mb * 1024 * 1024;
    }

    public final long httpCacheBytes(Context context) {
        long mb;
        Intrinsics.checkNotNullParameter(context, "context");
        int totalMb = DeviceMemory.INSTANCE.read(context).getTotalMb();
        if (Integer.MIN_VALUE <= totalMb && totalMb < 2049) {
            mb = 48;
        } else {
            mb = Integer.MIN_VALUE <= totalMb && totalMb < 4097 ? 96L : 160L;
        }
        return mb * 1024 * 1024;
    }

    public final boolean useWeakOnly(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceMemory.INSTANCE.read(context).getTotalMb() <= 2048;
    }
}
