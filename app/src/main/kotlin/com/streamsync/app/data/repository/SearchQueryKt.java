package com.streamsync.app.data.repository;

import com.google.android.gms.actions.SearchIntents;
import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonModelsKt;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: SearchQuery.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001a\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\r\u001a\u00020\u0003H\u0000\u001a\n\u0010\u000e\u001a\u00020\u000f*\u00020\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"MARK_DIACRITICS", "Lkotlin/text/Regex;", "foldDiacritics", "", "input", "matchScore", "", "title", "queryKey", "rankSearchResults", "", "Lcom/streamsync/app/data/model/MediaItem;", "items", SearchIntents.EXTRA_QUERY, "supportsSearch", "", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class SearchQueryKt {
    private static final Regex MARK_DIACRITICS = new Regex("\\p{Mn}+");

    public static final boolean supportsSearch(AddonCatalogDef $this$supportsSearch) {
        Intrinsics.checkNotNullParameter($this$supportsSearch, "<this>");
        return AddonModelsKt.supportsExtra($this$supportsSearch, "search");
    }

    public static final List<MediaItem> rankSearchResults(List<MediaItem> items, String query) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(query, "query");
        final String q = SearchQuery.INSTANCE.looseKey(query);
        return q.length() == 0 ? items : CollectionsKt.sortedWith(items, new Comparator() { // from class: com.streamsync.app.data.repository.SearchQueryKt$rankSearchResults$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                MediaItem it = (MediaItem) t2;
                Integer valueOf = Integer.valueOf(SearchQueryKt.matchScore(it.getTitle(), q));
                MediaItem it2 = (MediaItem) t;
                return ComparisonsKt.compareValues(valueOf, Integer.valueOf(SearchQueryKt.matchScore(it2.getTitle(), q)));
            }
        });
    }

    public static final int matchScore(String title, String queryKey) {
        Intrinsics.checkNotNullParameter(queryKey, "queryKey");
        String t = SearchQuery.INSTANCE.looseKey(title == null ? "" : title);
        if (t.length() == 0) {
            return 0;
        }
        if (Intrinsics.areEqual(t, queryKey)) {
            return 100;
        }
        if (StringsKt.startsWith$default(t, queryKey, false, 2, (Object) null)) {
            return 80;
        }
        return StringsKt.contains$default((CharSequence) t, (CharSequence) queryKey, false, 2, (Object) null) ? 50 : 10;
    }

    public static final String foldDiacritics(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String normalize = Normalizer.normalize(input, Normalizer.Form.NFD);
        Intrinsics.checkNotNullExpressionValue(normalize, "normalize(...)");
        return MARK_DIACRITICS.replace(normalize, "");
    }
}
