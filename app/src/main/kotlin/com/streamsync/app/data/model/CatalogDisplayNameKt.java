package com.streamsync.app.data.model;

import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CatalogDisplayName.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004*\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000\u001a\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¨\u0006\u0007"}, d2 = {"typeLabel", "", "type", "displayNames", "", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "withDistinctCatalogNames", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class CatalogDisplayNameKt {
    /* JADX WARN: Removed duplicated region for block: B:39:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<java.lang.String> displayNames(java.util.List<com.streamsync.app.data.addon.AddonCatalogDef> r24) {
        /*
            Method dump skipped, instructions count: 405
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.model.CatalogDisplayNameKt.displayNames(java.util.List):java.util.List");
    }

    public static final String typeLabel(String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (Intrinsics.areEqual(type, "movie")) {
            return "Phim lẻ";
        }
        if (Intrinsics.areEqual(type, "series")) {
            return "Phim bộ";
        }
        String str = type;
        if (str.length() == 0) {
            str = "Danh mục";
        }
        return str;
    }

    public static final List<AddonCatalogDef> withDistinctCatalogNames(List<AddonCatalogDef> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        List names = displayNames(list);
        List<AddonCatalogDef> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            AddonCatalogDef it = (AddonCatalogDef) item$iv$iv;
            String name = it.getName();
            if (name == null) {
                name = "";
            }
            destination$iv$iv.add(StringsKt.trim((CharSequence) name).toString());
        }
        if (Intrinsics.areEqual(names, (List) destination$iv$iv)) {
            return list;
        }
        List<AddonCatalogDef> $this$mapIndexed$iv = list;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv2 : $this$mapIndexed$iv) {
            int index$iv$iv2 = index$iv$iv + 1;
            if (index$iv$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AddonCatalogDef def = (AddonCatalogDef) item$iv$iv2;
            destination$iv$iv2.add(AddonCatalogDef.copy$default(def, null, null, names.get(index$iv$iv), null, null, null, null, 123, null));
            index$iv$iv = index$iv$iv2;
        }
        return (List) destination$iv$iv2;
    }
}
