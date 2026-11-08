package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.data.addon.AddonVideo;
import com.streamsync.app.data.addon.CacheDirective;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;

/* compiled from: MetaCache.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0014JP\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u0016\u001a\u00020\n2*\u0010\u0017\u001a&\b\u0001\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0086@¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\u0006\u0010\u001d\u001a\u00020\u0014J\u001a\u0010\u001e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u0007J\u0018\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u0007J\u001e\u0010\u0016\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nJ\u0018\u0010%\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u0007J(\u0010&\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u000e2\b\b\u0002\u0010\u001f\u001a\u00020\u0007J\u0018\u0010)\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R.\u0010\b\u001a\"\u0012\u0004\u0012\u00020\n\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f0\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/streamsync/app/data/repository/MetaCache;", "", "()V", "MAX_META", "", "MAX_MISSES", "MISS_TTL_MS", "", "inFlight", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonMeta;", "Lcom/streamsync/app/data/addon/CacheDirective;", "knownSeries", "metas", "Lcom/streamsync/app/data/repository/MetaCache$Entry;", "misses", "clear", "", "coalesce", "key", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "evictOldest", "evictOldestKnownSeries", "forgetMisses", "get", "nowMs", "isKnownSeries", "", "addonId", "type", "id", "markMissed", "put", "meta", "directive", "wasMissed", "Entry", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class MetaCache {
    private static final int MAX_META = 80;
    private static final int MAX_MISSES = 120;
    private static final long MISS_TTL_MS = 600000;
    public static final MetaCache INSTANCE = new MetaCache();
    private static final ConcurrentHashMap<String, Entry> metas = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> misses = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> knownSeries = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, CompletableDeferred<Pair<AddonMeta, CacheDirective>>> inFlight = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private MetaCache() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MetaCache.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/streamsync/app/data/repository/MetaCache$Entry;", "", "meta", "Lcom/streamsync/app/data/addon/AddonMeta;", "expiresAtMs", "", "(Lcom/streamsync/app/data/addon/AddonMeta;J)V", "getExpiresAtMs", "()J", "getMeta", "()Lcom/streamsync/app/data/addon/AddonMeta;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final class Entry {
        private final long expiresAtMs;
        private final AddonMeta meta;

        public Entry(AddonMeta meta, long expiresAtMs) {
            Intrinsics.checkNotNullParameter(meta, "meta");
            this.meta = meta;
            this.expiresAtMs = expiresAtMs;
        }

        public final long getExpiresAtMs() {
            return this.expiresAtMs;
        }

        public final AddonMeta getMeta() {
            return this.meta;
        }
    }

    public final String key(String addonId, String type, String id) {
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
        return addonId + "|" + type + "|" + id;
    }

    public static /* synthetic */ AddonMeta get$default(MetaCache metaCache, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return metaCache.get(str, j);
    }

    public final AddonMeta get(String key, long nowMs) {
        Intrinsics.checkNotNullParameter(key, "key");
        Entry entry = metas.get(key);
        if (entry == null) {
            return null;
        }
        if (nowMs >= entry.getExpiresAtMs()) {
            metas.remove(key);
            return null;
        }
        return entry.getMeta();
    }

    public static /* synthetic */ void put$default(MetaCache metaCache, String str, AddonMeta addonMeta, CacheDirective cacheDirective, long j, int i, Object obj) {
        if ((i & 8) != 0) {
            j = System.currentTimeMillis();
        }
        metaCache.put(str, addonMeta, cacheDirective, j);
    }

    public final void put(String key, AddonMeta meta, CacheDirective directive, long nowMs) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(meta, "meta");
        Intrinsics.checkNotNullParameter(directive, "directive");
        if (metas.size() >= MAX_META) {
            evictOldest();
        }
        metas.put(key, new Entry(meta, directive.getTtlMs() + nowMs));
        misses.remove(key);
        List<AddonVideo> videos = meta.getVideos();
        if (!(videos == null || videos.isEmpty())) {
            if (knownSeries.size() >= 120) {
                evictOldestKnownSeries();
            }
            knownSeries.put(key, Long.valueOf(nowMs));
        }
    }

    private final void evictOldest() {
        Object next;
        Iterator<T> it = metas.entrySet().iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                Map.Entry it2 = (Map.Entry) next;
                long expiresAtMs = ((Entry) it2.getValue()).getExpiresAtMs();
                do {
                    Object next2 = it.next();
                    Map.Entry it3 = (Map.Entry) next2;
                    long expiresAtMs2 = ((Entry) it3.getValue()).getExpiresAtMs();
                    if (expiresAtMs > expiresAtMs2) {
                        next = next2;
                        expiresAtMs = expiresAtMs2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Map.Entry entry = (Map.Entry) next;
        String oldest = entry != null ? (String) entry.getKey() : null;
        if (oldest != null) {
            metas.remove(oldest);
        }
    }

    private final void evictOldestKnownSeries() {
        Object next;
        Iterator<T> it = knownSeries.entrySet().iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                Map.Entry it2 = (Map.Entry) next;
                long longValue = ((Number) it2.getValue()).longValue();
                do {
                    Object next2 = it.next();
                    Map.Entry it3 = (Map.Entry) next2;
                    long longValue2 = ((Number) it3.getValue()).longValue();
                    if (longValue > longValue2) {
                        next = next2;
                        longValue = longValue2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        Map.Entry entry = (Map.Entry) next;
        String oldest = entry != null ? (String) entry.getKey() : null;
        if (oldest != null) {
            knownSeries.remove(oldest);
        }
    }

    public static /* synthetic */ boolean wasMissed$default(MetaCache metaCache, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return metaCache.wasMissed(str, j);
    }

    public final boolean wasMissed(String key, long nowMs) {
        Intrinsics.checkNotNullParameter(key, "key");
        Long l = misses.get(key);
        if (l == null) {
            return false;
        }
        long at = l.longValue();
        if (nowMs - at > MISS_TTL_MS) {
            misses.remove(key);
            return false;
        }
        return true;
    }

    public static /* synthetic */ void markMissed$default(MetaCache metaCache, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        metaCache.markMissed(str, j);
    }

    public final void markMissed(String key, long nowMs) {
        Object next;
        Intrinsics.checkNotNullParameter(key, "key");
        if (misses.size() >= 120) {
            Iterator<T> it = misses.entrySet().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    Map.Entry it2 = (Map.Entry) next;
                    long longValue = ((Number) it2.getValue()).longValue();
                    do {
                        Object next2 = it.next();
                        Map.Entry it3 = (Map.Entry) next2;
                        long longValue2 = ((Number) it3.getValue()).longValue();
                        if (longValue > longValue2) {
                            next = next2;
                            longValue = longValue2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            Map.Entry entry = (Map.Entry) next;
            String oldest = entry != null ? (String) entry.getKey() : null;
            if (oldest != null) {
                misses.remove(oldest);
            }
        }
        misses.put(key, Long.valueOf(nowMs));
    }

    public final void forgetMisses() {
        misses.clear();
    }

    public static /* synthetic */ boolean isKnownSeries$default(MetaCache metaCache, String str, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return metaCache.isKnownSeries(str, j);
    }

    public final boolean isKnownSeries(String key, long nowMs) {
        Intrinsics.checkNotNullParameter(key, "key");
        Long l = knownSeries.get(key);
        if (l == null) {
            return false;
        }
        long at = l.longValue();
        if (nowMs - at > MISS_TTL_MS) {
            knownSeries.remove(key);
            return false;
        }
        return true;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0023. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v5, types: [kotlinx.coroutines.CompletableDeferred, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0, types: [kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Pair<com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.CacheDirective>>, ? extends java.lang.Object>, kotlin.jvm.functions.Function1] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object coalesce(java.lang.String r7, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Pair<com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.CacheDirective>>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlin.Pair<com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.CacheDirective>> r9) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.MetaCache.coalesce(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void clear() {
        metas.clear();
        misses.clear();
        knownSeries.clear();
        inFlight.clear();
    }
}
