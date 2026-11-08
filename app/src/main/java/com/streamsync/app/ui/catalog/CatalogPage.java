package com.streamsync.app.ui.catalog;

import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CatalogBrowseScreen.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/ui/catalog/CatalogPage;", "", "items", "", "Lcom/streamsync/app/data/model/MediaItem;", "served", "", "(Ljava/util/List;I)V", "getItems", "()Ljava/util/List;", "getServed", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final /* data */ class CatalogPage {
    public static final int $stable = 8;
    private final List<MediaItem> items;
    private final int served;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CatalogPage copy$default(CatalogPage catalogPage, List list, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = catalogPage.items;
        }
        if ((i2 & 2) != 0) {
            i = catalogPage.served;
        }
        return catalogPage.copy(list, i);
    }

    public final List<MediaItem> component1() {
        return this.items;
    }

    /* renamed from: component2, reason: from getter */
    public final int getServed() {
        return this.served;
    }

    public final CatalogPage copy(List<MediaItem> items, int served) {
        Intrinsics.checkNotNullParameter(items, "items");
        return new CatalogPage(items, served);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogPage)) {
            return false;
        }
        CatalogPage catalogPage = (CatalogPage) other;
        return Intrinsics.areEqual(this.items, catalogPage.items) && this.served == catalogPage.served;
    }

    public int hashCode() {
        return (this.items.hashCode() * 31) + Integer.hashCode(this.served);
    }

    public String toString() {
        return "CatalogPage(items=" + this.items + ", served=" + this.served + ")";
    }

    public CatalogPage(List<MediaItem> items, int served) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.items = items;
        this.served = served;
    }

    public final List<MediaItem> getItems() {
        return this.items;
    }

    public final int getServed() {
        return this.served;
    }
}
