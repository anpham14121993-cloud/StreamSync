package com.streamsync.app.settings;

import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.data.model.MediaItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: HomeLayoutStore.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00042\u0006\u0010\b\u001a\u00020\tJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\b\u001a\u00020\tJ\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\u0012"}, d2 = {"Lcom/streamsync/app/settings/HomeLayout;", "", "()V", "featuredItems", "", "Lcom/streamsync/app/data/model/MediaItem;", "rows", "Lcom/streamsync/app/data/model/CatalogRow;", "settings", "Lcom/streamsync/app/settings/HomeLayoutSettings;", "limit", "", "excludeIds", "", "", "featuredKeys", "featuredRows", "visibleRows", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class HomeLayout {
    public static final int $stable = 0;
    public static final HomeLayout INSTANCE = new HomeLayout();

    private HomeLayout() {
    }

    public final List<CatalogRow> visibleRows(List<CatalogRow> rows, HomeLayoutSettings settings) {
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(settings, "settings");
        List<CatalogRow> $this$associateBy$iv = rows;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            CatalogRow it = (CatalogRow) element$iv$iv;
            destination$iv$iv.put(it.getKey(), element$iv$iv);
        }
        LinkedHashMap result = new LinkedHashMap();
        Iterable $this$forEach$iv = settings.getOrder();
        for (Object element$iv : $this$forEach$iv) {
            String key = (String) element$iv;
            CatalogRow row = (CatalogRow) destination$iv$iv.get(key);
            if (row != null && !settings.getHidden().contains(row.getKey())) {
                result.put(row.getKey(), row);
            }
        }
        List<CatalogRow> $this$forEach$iv2 = rows;
        for (Object element$iv2 : $this$forEach$iv2) {
            CatalogRow row2 = (CatalogRow) element$iv2;
            if (!settings.getHidden().contains(row2.getKey()) && !result.containsKey(row2.getKey())) {
                result.put(row2.getKey(), row2);
            }
        }
        Iterable $this$forEach$iv3 = result.values();
        Intrinsics.checkNotNullExpressionValue($this$forEach$iv3, "<get-values>(...)");
        return CollectionsKt.toList($this$forEach$iv3);
    }

    public final List<CatalogRow> featuredRows(List<CatalogRow> rows, HomeLayoutSettings settings) {
        Object element$iv;
        Iterable rows2 = rows;
        Intrinsics.checkNotNullParameter(rows2, "rows");
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (settings.getFeaturedPosition() == FeaturedPosition.OFF) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$mapNotNull$iv = featuredKeys(settings);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            String key = (String) element$iv$iv$iv;
            CatalogRow catalogRow = null;
            if (!settings.getHidden().contains(key)) {
                Iterable $this$firstOrNull$iv = rows2;
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        element$iv = null;
                        break;
                    }
                    element$iv = it.next();
                    if (Intrinsics.areEqual(((CatalogRow) element$iv).getKey(), key)) {
                        break;
                    }
                }
                CatalogRow it2 = (CatalogRow) element$iv;
                if (it2 != null && it2.getHasItems()) {
                    catalogRow = it2;
                }
            }
            if (catalogRow != null) {
                destination$iv$iv.add(catalogRow);
            }
            rows2 = rows;
        }
        return (List) destination$iv$iv;
    }

    public final List<String> featuredKeys(HomeLayoutSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (settings.getFeaturedPosition() == FeaturedPosition.OFF) {
            return CollectionsKt.emptyList();
        }
        return CollectionsKt.take(settings.getFeaturedKeys(), 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List featuredItems$default(HomeLayout homeLayout, List list, HomeLayoutSettings homeLayoutSettings, int i, Set set, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 6;
        }
        if ((i2 & 8) != 0) {
            set = SetsKt.emptySet();
        }
        return homeLayout.featuredItems(list, homeLayoutSettings, i, set);
    }

    public final List<MediaItem> featuredItems(List<CatalogRow> rows, HomeLayoutSettings settings, int limit, Set<String> excludeIds) {
        Integer num;
        int extra;
        Intrinsics.checkNotNullParameter(rows, "rows");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(excludeIds, "excludeIds");
        List picked = featuredRows(rows, settings);
        if (picked.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        int perCatalog = picked.isEmpty() ? 0 : limit / picked.size();
        List $this$map$iv = picked;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            CatalogRow row = (CatalogRow) item$iv$iv;
            Set seen = new LinkedHashSet();
            Iterable $this$mapNotNull$iv = row.getItems();
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                MediaItem item = (MediaItem) element$iv$iv$iv;
                Iterable $this$map$iv2 = $this$map$iv;
                String key = item.getAddonId() + "::" + item.getId();
                if (excludeIds.contains(key) || excludeIds.contains(item.getId())) {
                    item = null;
                } else if (!seen.add(key)) {
                    item = null;
                }
                if (item != null) {
                    destination$iv$iv2.add(item);
                }
                $this$map$iv = $this$map$iv2;
            }
            destination$iv$iv.add(CollectionsKt.toMutableList((Collection) CollectionsKt.take(CollectionsKt.shuffled((List) destination$iv$iv2), perCatalog)));
        }
        List<List> buckets = (List) destination$iv$iv;
        int i = 0;
        for (List it : buckets) {
            i += it.size();
        }
        int extra2 = limit - i;
        if (extra2 > 0) {
            int i2 = 0;
            int size = buckets.size();
            while (true) {
                if (i2 >= size) {
                    extra = extra2;
                    break;
                }
                if (extra2 <= 0) {
                    extra = extra2;
                    break;
                }
                CatalogRow row2 = picked.get(i2);
                Iterable $this$mapTo$iv = (Iterable) buckets.get(i2);
                Collection destination$iv = (Set) new LinkedHashSet();
                for (Object item$iv : $this$mapTo$iv) {
                    MediaItem it2 = (MediaItem) item$iv;
                    destination$iv.add(it2.getAddonId() + "::" + it2.getId());
                    picked = picked;
                    extra2 = extra2;
                    perCatalog = perCatalog;
                }
                List picked2 = picked;
                int extra3 = extra2;
                int perCatalog2 = perCatalog;
                Set seen2 = (Set) destination$iv;
                for (MediaItem item2 : row2.getItems()) {
                    if (extra3 > 0) {
                        String key2 = item2.getAddonId() + "::" + item2.getId();
                        if (!excludeIds.contains(key2) && !excludeIds.contains(item2.getId()) && seen2.add(key2)) {
                            ((Collection) buckets.get(i2)).add(item2);
                            extra3--;
                        }
                    }
                }
                i2++;
                picked = picked2;
                extra2 = extra3;
                perCatalog = perCatalog2;
            }
        }
        int i3 = 0;
        for (List it3 : buckets) {
            i3 += it3.size();
        }
        ArrayList out = new ArrayList(Math.min(limit, i3));
        Iterator it4 = buckets.iterator();
        if (it4.hasNext()) {
            List it5 = (List) it4.next();
            Integer valueOf = Integer.valueOf(it5.size());
            loop9: while (true) {
                num = valueOf;
                while (it4.hasNext()) {
                    List it6 = (List) it4.next();
                    valueOf = Integer.valueOf(it6.size());
                    if (num.compareTo(valueOf) < 0) {
                        break;
                    }
                }
            }
        } else {
            num = null;
        }
        Integer num2 = num;
        int longest = num2 != null ? num2.intValue() : 0;
        for (int i4 = 0; i4 < longest; i4++) {
            for (List bucket : buckets) {
                if (i4 < bucket.size()) {
                    out.add(bucket.get(i4));
                    if (out.size() == limit) {
                        return out;
                    }
                }
            }
        }
        return out;
    }
}
