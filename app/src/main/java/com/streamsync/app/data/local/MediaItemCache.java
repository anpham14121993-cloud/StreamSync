package com.streamsync.app.data.local;

import android.content.Context;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaItemCache.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u000bJ\u0014\u0010\u001a\u001a\u00020\u00162\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dJ\u0014\u0010\u001e\u001a\u00020\u00162\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dJ\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\n0\rj\b\u0012\u0004\u0012\u00020\n`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/streamsync/app/data/local/MediaItemCache;", "", "()V", "MAX_ITEMS_IN_RAM", "", "appContext", "Landroid/content/Context;", "attachLock", "items", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/streamsync/app/data/model/MediaItem;", "ordered", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "persistGeneration", "persistLock", "persistScheduled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "attach", "context", "clear", "", "evictOverflow", "get", "mediaId", "put", "item", "list", "", "putLoaded", "schedulePersist", "ctx", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class MediaItemCache {
    private static final int MAX_ITEMS_IN_RAM = 400;
    private static volatile Context appContext;
    private static int persistGeneration;
    public static final MediaItemCache INSTANCE = new MediaItemCache();
    private static final ConcurrentHashMap<String, MediaItem> items = new ConcurrentHashMap<>();
    private static final ArrayList<String> ordered = new ArrayList<>();
    private static final Object persistLock = new Object();
    private static final Object attachLock = new Object();
    private static final AtomicBoolean persistScheduled = new AtomicBoolean(false);
    public static final int $stable = 8;

    private MediaItemCache() {
    }

    public final int attach(Context context) {
        int size;
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (attachLock) {
            appContext = context.getApplicationContext();
            size = items.isEmpty() ^ true ? items.size() : MediaItemDiskCache.INSTANCE.loadIntoMemory(context);
        }
        return size;
    }

    public final void put(MediaItem item) {
        boolean z;
        Intrinsics.checkNotNullParameter(item, "item");
        if (StringsKt.isBlank(item.getId())) {
            return;
        }
        synchronized (persistLock) {
            z = items.put(item.getId(), item) == null;
        }
        boolean isNew = z;
        if (isNew) {
            synchronized (persistLock) {
                ordered.add(item.getId());
            }
        }
        evictOverflow();
        Context ctx = appContext;
        if (ctx == null) {
            return;
        }
        schedulePersist(ctx);
    }

    private final void evictOverflow() {
        if (ordered.size() <= MAX_ITEMS_IN_RAM) {
            return;
        }
        synchronized (persistLock) {
            for (int drop = ordered.size() - MAX_ITEMS_IN_RAM; drop > 0 && (!ordered.isEmpty()); drop--) {
                items.remove(ordered.remove(0));
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void put(List<MediaItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List<MediaItem> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            MediaItem it = (MediaItem) element$iv;
            INSTANCE.put(it);
        }
    }

    public final void putLoaded(List<MediaItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        synchronized (persistLock) {
            List<MediaItem> $this$forEach$iv = list;
            for (Object element$iv : $this$forEach$iv) {
                MediaItem item = (MediaItem) element$iv;
                if (!StringsKt.isBlank(item.getId())) {
                    items.put(item.getId(), item);
                    ordered.add(item.getId());
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        evictOverflow();
    }

    public final MediaItem get(String mediaId) {
        if (mediaId != null) {
            return items.get(mediaId);
        }
        return null;
    }

    public final void clear() {
        items.clear();
        synchronized (persistLock) {
            ordered.clear();
            persistGeneration++;
        }
    }

    private final void schedulePersist(final Context ctx) {
        if (persistScheduled.compareAndSet(false, true)) {
            Thread $this$schedulePersist_u24lambda_u2413 = new Thread(new Runnable() { // from class: com.streamsync.app.data.local.MediaItemCache$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaItemCache.schedulePersist$lambda$12(ctx);
                }
            });
            $this$schedulePersist_u24lambda_u2413.setDaemon(true);
            $this$schedulePersist_u24lambda_u2413.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void schedulePersist$lambda$12(Context ctx) {
        Pair pair;
        int i;
        Intrinsics.checkNotNullParameter(ctx, "$ctx");
        try {
            Thread.sleep(400L);
            synchronized (persistLock) {
                int i2 = 0;
                Integer valueOf = Integer.valueOf(persistGeneration);
                Iterable $this$mapNotNull$iv = ordered;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    String it = (String) element$iv$iv$iv;
                    int i3 = i2;
                    MediaItem mediaItem = items.get(it);
                    if (mediaItem != null) {
                        destination$iv$iv.add(mediaItem);
                    }
                    i2 = i3;
                }
                pair = TuplesKt.to(valueOf, (List) destination$iv$iv);
            }
            int gen = ((Number) pair.component1()).intValue();
            List snapshot = (List) pair.component2();
            synchronized (persistLock) {
                i = gen == persistGeneration ? 1 : 0;
            }
            if (i == 0) {
                return;
            }
            if (!snapshot.isEmpty()) {
                MediaItemDiskCache.INSTANCE.save(ctx, snapshot);
            }
        } finally {
            persistScheduled.set(false);
        }
    }
}
