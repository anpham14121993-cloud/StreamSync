package com.streamsync.app.net;

import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: DpiBypassSocketFactory.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"planFragments", "", "Lkotlin/ranges/IntRange;", "size", "", "fragmentSize", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class DpiBypassSocketFactoryKt {
    public static final List<IntRange> planFragments(int size, int fragmentSize) {
        if (size <= 0 || fragmentSize <= 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList out = new ArrayList();
        int offset = 0;
        while (offset < size) {
            int end = Math.min(offset + fragmentSize, size);
            out.add(RangesKt.until(offset, end));
            offset = end;
        }
        return out;
    }
}
