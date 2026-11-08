package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StreamProviderGroups.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u0000\u001a\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u0000\u001a&\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"ALL_PROVIDERS_LABEL", "", "UNKNOWN_PROVIDER", "groupStreamsByProvider", "", "Lcom/streamsync/app/ui/detail/StreamProviderGroup;", "sources", "Lcom/streamsync/app/data/model/StreamSource;", "providerOptions", "visibleProviderGroups", "providerKey", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class StreamProviderGroupsKt {
    public static final String ALL_PROVIDERS_LABEL = "Tất cả";
    public static final String UNKNOWN_PROVIDER = "Không rõ nguồn";

    /* JADX WARN: Removed duplicated region for block: B:22:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<com.streamsync.app.ui.detail.StreamProviderGroup> groupStreamsByProvider(java.util.List<com.streamsync.app.data.model.StreamSource> r20) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.StreamProviderGroupsKt.groupStreamsByProvider(java.util.List):java.util.List");
    }

    public static final List<StreamProviderGroup> visibleProviderGroups(List<StreamSource> sources, String providerKey) {
        Intrinsics.checkNotNullParameter(sources, "sources");
        List groups = groupStreamsByProvider(sources);
        if (providerKey == null) {
            return groups;
        }
        List $this$filter$iv = groups;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            StreamProviderGroup it = (StreamProviderGroup) element$iv$iv;
            if (Intrinsics.areEqual(it.getKey(), providerKey)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public static final List<String> providerOptions(List<StreamSource> sources) {
        Intrinsics.checkNotNullParameter(sources, "sources");
        Iterable $this$map$iv = groupStreamsByProvider(sources);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            StreamProviderGroup it = (StreamProviderGroup) item$iv$iv;
            destination$iv$iv.add(it.getKey());
        }
        return (List) destination$iv$iv;
    }
}
