package com.google.common.cache;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public interface Weigher<K, V> {
    int weigh(K key, V value);
}
