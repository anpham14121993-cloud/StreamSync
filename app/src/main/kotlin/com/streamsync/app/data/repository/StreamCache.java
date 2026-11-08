package com.streamsync.app.data.repository;

import android.os.SystemClock;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StreamCache.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\tJ\u001c\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/streamsync/app/data/repository/StreamCache;", "", "()V", "MAX_ENTRIES", "", "TTL_MS", "", "entries", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/streamsync/app/data/repository/StreamCache$Entry;", "clear", "", "getFresh", "", "Lcom/streamsync/app/data/model/StreamSource;", "key", "put", "sources", "Entry", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class StreamCache {
    private static final int MAX_ENTRIES = 40;
    public static final long TTL_MS = 300000;
    public static final StreamCache INSTANCE = new StreamCache();
    private static final ConcurrentHashMap<String, Entry> entries = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private StreamCache() {
    }

    /* compiled from: StreamCache.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\u0006J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/repository/StreamCache$Entry;", "", "sources", "", "Lcom/streamsync/app/data/model/StreamSource;", "at", "", "(Ljava/util/List;J)V", "getAt", "()J", "getSources", "()Ljava/util/List;", "ageMs", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    private static final /* data */ class Entry {
        private final long at;
        private final List<StreamSource> sources;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Entry copy$default(Entry entry, List list, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                list = entry.sources;
            }
            if ((i & 2) != 0) {
                j = entry.at;
            }
            return entry.copy(list, j);
        }

        public final List<StreamSource> component1() {
            return this.sources;
        }

        /* renamed from: component2, reason: from getter */
        public final long getAt() {
            return this.at;
        }

        public final Entry copy(List<StreamSource> sources, long at) {
            Intrinsics.checkNotNullParameter(sources, "sources");
            return new Entry(sources, at);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return Intrinsics.areEqual(this.sources, entry.sources) && this.at == entry.at;
        }

        public int hashCode() {
            return (this.sources.hashCode() * 31) + Long.hashCode(this.at);
        }

        public String toString() {
            return "Entry(sources=" + this.sources + ", at=" + this.at + ")";
        }

        public Entry(List<StreamSource> sources, long at) {
            Intrinsics.checkNotNullParameter(sources, "sources");
            this.sources = sources;
            this.at = at;
        }

        public final long getAt() {
            return this.at;
        }

        public final List<StreamSource> getSources() {
            return this.sources;
        }

        public final long ageMs() {
            return SystemClock.elapsedRealtime() - this.at;
        }
    }

    public final List<StreamSource> getFresh(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Entry hit = entries.get(key);
        if (hit == null) {
            return null;
        }
        if (hit.ageMs() > 300000) {
            entries.remove(key);
            return null;
        }
        List it = hit.getSources();
        if (!it.isEmpty()) {
            return it;
        }
        return null;
    }

    public final void put(String key, List<StreamSource> sources) {
        Object next;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(sources, "sources");
        if (sources.isEmpty()) {
            return;
        }
        if (entries.size() >= 40) {
            Iterator<T> it = entries.entrySet().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    Map.Entry it2 = (Map.Entry) next;
                    long at = ((Entry) it2.getValue()).getAt();
                    do {
                        Object next2 = it.next();
                        Map.Entry it3 = (Map.Entry) next2;
                        long at2 = ((Entry) it3.getValue()).getAt();
                        if (at > at2) {
                            next = next2;
                            at = at2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            Map.Entry entry = (Map.Entry) next;
            String oldest = entry != null ? (String) entry.getKey() : null;
            if (oldest != null) {
                entries.remove(oldest);
            }
        }
        entries.put(key, new Entry(sources, SystemClock.elapsedRealtime()));
    }

    public final void clear() {
        entries.clear();
    }
}
