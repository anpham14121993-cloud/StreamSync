package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: SearchQuery.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/streamsync/app/data/repository/SearchQuery;", "", "()V", "looseKey", "", "raw", "normalize", "toFileKey", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class SearchQuery {
    public static final int $stable = 0;
    public static final SearchQuery INSTANCE = new SearchQuery();

    private SearchQuery() {
    }

    public final String normalize(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String lowerCase = new Regex("\\s+").replace(StringsKt.trim((CharSequence) raw).toString(), " ").toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    public final String looseKey(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(new Regex("[^a-z0-9\\s]").replace(SearchQueryKt.foldDiacritics(lowerCase), " "), " ")).toString();
    }

    public final String toFileKey(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        return StringsKt.replace$default(looseKey(raw), ' ', '-', false, 4, (Object) null);
    }
}
