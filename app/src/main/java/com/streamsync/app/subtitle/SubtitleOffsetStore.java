package com.streamsync.app.subtitle;

import android.content.Context;
import android.content.SharedPreferences;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubtitleOffsetStore.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\tR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/streamsync/app/subtitle/SubtitleOffsetStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "get", "", "mediaId", "", "set", "", "offsetMs", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class SubtitleOffsetStore {
    public static final int $stable = 8;
    private final SharedPreferences prefs;

    public SubtitleOffsetStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.prefs = context.getApplicationContext().getSharedPreferences("subtitle_offsets", 0);
    }

    public final long get(String mediaId) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        return this.prefs.getLong(mediaId, 0L);
    }

    public final void set(String mediaId, long offsetMs) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        this.prefs.edit().putLong(mediaId, offsetMs).apply();
    }
}
