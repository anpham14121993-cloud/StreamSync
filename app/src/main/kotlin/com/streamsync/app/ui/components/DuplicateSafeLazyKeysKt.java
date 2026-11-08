package com.streamsync.app.ui.components;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DuplicateSafeLazyKeys.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a8\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¨\u0006\u0007"}, d2 = {"withDuplicateSafeLazyKeys", "", "Lcom/streamsync/app/ui/components/DuplicateSafeLazyEntry;", ExifInterface.GPS_DIRECTION_TRUE, "key", "Lkotlin/Function1;", "", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class DuplicateSafeLazyKeysKt {
    public static final <T> List<DuplicateSafeLazyEntry<T>> withDuplicateSafeLazyKeys(List<? extends T> list, final Function1<? super T, ? extends Object> key) {
        Object lazyKey;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        final List<? extends T> $this$groupingBy$iv = list;
        Map keyCounts = GroupingKt.eachCount(new Grouping<T, Object>() { // from class: com.streamsync.app.ui.components.DuplicateSafeLazyKeysKt$withDuplicateSafeLazyKeys$$inlined$groupingBy$1
            @Override // kotlin.collections.Grouping
            public Iterator<T> sourceIterator() {
                return $this$groupingBy$iv.iterator();
            }

            @Override // kotlin.collections.Grouping
            public Object keyOf(T element) {
                return key.invoke(element);
            }
        });
        HashMap occurrences = new HashMap();
        List<? extends T> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Object baseKey = key.invoke(item$iv$iv);
            Integer num = (Integer) keyCounts.get(baseKey);
            if (num != null && num.intValue() == 1) {
                lazyKey = baseKey;
            } else {
                Object obj = occurrences.get(baseKey);
                if (obj == null) {
                    obj = 0;
                }
                int n = ((Number) obj).intValue();
                occurrences.put(baseKey, Integer.valueOf(n + 1));
                lazyKey = baseKey + "#" + n;
            }
            destination$iv$iv.add(new DuplicateSafeLazyEntry(item$iv$iv, lazyKey));
        }
        return (List) destination$iv$iv;
    }
}
