package com.streamsync.app.ui.components;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DuplicateSafeLazyKeys.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\b\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/streamsync/app/ui/components/DuplicateSafeLazyEntry;", ExifInterface.GPS_DIRECTION_TRUE, "", "value", "lazyKey", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getLazyKey", "()Ljava/lang/Object;", "getValue", "Ljava/lang/Object;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class DuplicateSafeLazyEntry<T> {
    public static final int $stable = 8;
    private final Object lazyKey;
    private final T value;

    public DuplicateSafeLazyEntry(T t, Object lazyKey) {
        Intrinsics.checkNotNullParameter(lazyKey, "lazyKey");
        this.value = t;
        this.lazyKey = lazyKey;
    }

    public final T getValue() {
        return this.value;
    }

    public final Object getLazyKey() {
        return this.lazyKey;
    }
}
