package com.google.common.cache;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> notification);
}
