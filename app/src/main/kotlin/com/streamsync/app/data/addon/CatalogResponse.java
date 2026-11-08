package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/addon/CatalogResponse;", "", "metas", "", "Lcom/streamsync/app/data/addon/AddonMeta;", "(Ljava/util/List;)V", "getMetas", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class CatalogResponse {
    public static final int $stable = 8;
    private final List<AddonMeta> metas;

    /* JADX WARN: Multi-variable type inference failed */
    public CatalogResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CatalogResponse copy$default(CatalogResponse catalogResponse, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = catalogResponse.metas;
        }
        return catalogResponse.copy(list);
    }

    public final List<AddonMeta> component1() {
        return this.metas;
    }

    public final CatalogResponse copy(List<AddonMeta> metas) {
        return new CatalogResponse(metas);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CatalogResponse) && Intrinsics.areEqual(this.metas, ((CatalogResponse) other).metas);
    }

    public int hashCode() {
        if (this.metas == null) {
            return 0;
        }
        return this.metas.hashCode();
    }

    public String toString() {
        return "CatalogResponse(metas=" + this.metas + ")";
    }

    public CatalogResponse(List<AddonMeta> list) {
        this.metas = list;
    }

    public /* synthetic */ CatalogResponse(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<AddonMeta> getMetas() {
        return this.metas;
    }
}
