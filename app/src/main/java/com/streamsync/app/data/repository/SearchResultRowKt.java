package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SearchResultRow.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0000\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¨\u0006\b"}, d2 = {"isExactCatalogName", "", "catalogName", "", "rankSearchRows", "", "Lcom/streamsync/app/data/repository/SearchResultRow;", "rows", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class SearchResultRowKt {
    public static final boolean isExactCatalogName(String catalogName) {
        String key = SearchQuery.INSTANCE.looseKey(catalogName == null ? "" : catalogName);
        if (key.length() == 0) {
            return false;
        }
        return StringsKt.contains$default((CharSequence) key, (CharSequence) "exact", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) key, (CharSequence) "chinh xac", false, 2, (Object) null);
    }

    public static final List<SearchResultRow> rankSearchRows(List<SearchResultRow> rows) {
        Intrinsics.checkNotNullParameter(rows, "rows");
        List<SearchResultRow> $this$sortedByDescending$iv = rows;
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.streamsync.app.data.repository.SearchResultRowKt$rankSearchRows$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(SearchResultRowKt.isExactCatalogName(((SearchResultRow) t2).getCatalogName()) ? 1 : 0), Integer.valueOf(SearchResultRowKt.isExactCatalogName(((SearchResultRow) t).getCatalogName()) ? 1 : 0));
            }
        });
    }
}
