package com.streamsync.app.settings;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;

/* compiled from: DeviceProfile.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/streamsync/app/settings/DeviceMemory;", "", "()V", "ramLabel", "", "context", "Landroid/content/Context;", "read", "Lcom/streamsync/app/settings/DeviceMemory$Info;", "Info", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class DeviceMemory {
    public static final int $stable = 0;
    public static final DeviceMemory INSTANCE = new DeviceMemory();

    private DeviceMemory() {
    }

    /* compiled from: DeviceProfile.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/settings/DeviceMemory$Info;", "", "totalMb", "", "availableMb", "lowMemory", "", "(IIZ)V", "getAvailableMb", "()I", "getLowMemory", "()Z", "getTotalMb", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final /* data */ class Info {
        public static final int $stable = 0;
        private final int availableMb;
        private final boolean lowMemory;
        private final int totalMb;

        public static /* synthetic */ Info copy$default(Info info, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = info.totalMb;
            }
            if ((i3 & 2) != 0) {
                i2 = info.availableMb;
            }
            if ((i3 & 4) != 0) {
                z = info.lowMemory;
            }
            return info.copy(i, i2, z);
        }

        /* renamed from: component1, reason: from getter */
        public final int getTotalMb() {
            return this.totalMb;
        }

        /* renamed from: component2, reason: from getter */
        public final int getAvailableMb() {
            return this.availableMb;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getLowMemory() {
            return this.lowMemory;
        }

        public final Info copy(int totalMb, int availableMb, boolean lowMemory) {
            return new Info(totalMb, availableMb, lowMemory);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Info)) {
                return false;
            }
            Info info = (Info) other;
            return this.totalMb == info.totalMb && this.availableMb == info.availableMb && this.lowMemory == info.lowMemory;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.totalMb) * 31) + Integer.hashCode(this.availableMb)) * 31) + Boolean.hashCode(this.lowMemory);
        }

        public String toString() {
            return "Info(totalMb=" + this.totalMb + ", availableMb=" + this.availableMb + ", lowMemory=" + this.lowMemory + ")";
        }

        public Info(int totalMb, int availableMb, boolean lowMemory) {
            this.totalMb = totalMb;
            this.availableMb = availableMb;
            this.lowMemory = lowMemory;
        }

        public final int getTotalMb() {
            return this.totalMb;
        }

        public final int getAvailableMb() {
            return this.availableMb;
        }

        public final boolean getLowMemory() {
            return this.lowMemory;
        }
    }

    public final Info read(Context context) {
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            DeviceMemory deviceMemory = this;
            Object systemService = context.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            ActivityManager am = (ActivityManager) systemService;
            ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(info);
            m6717constructorimpl = Result.m6717constructorimpl(new Info(RangesKt.coerceAtLeast((int) (info.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), 1024), (int) (info.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), info.lowMemory));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6720exceptionOrNullimpl(m6717constructorimpl) != null) {
            m6717constructorimpl = new Info(2048, 512, true);
        }
        return (Info) m6717constructorimpl;
    }

    public final String ramLabel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int mb = read(context).getTotalMb();
        float gb = mb / 1024.0f;
        if (gb < 1.5f) {
            return mb + " MB";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.0f GB", Arrays.copyOf(new Object[]{Float.valueOf(gb)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
