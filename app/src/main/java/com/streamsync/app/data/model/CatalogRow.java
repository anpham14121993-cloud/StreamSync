package com.streamsync.app.data.model;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ja\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010(\u001a\u00020\u00172\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000f¨\u0006-"}, d2 = {"Lcom/streamsync/app/data/model/CatalogRow;", "", "key", "", "addonId", "addonName", "type", "catalogId", HintConstants.AUTOFILL_HINT_NAME, "items", "", "Lcom/streamsync/app/data/model/MediaItem;", "error", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getAddonId", "()Ljava/lang/String;", "getAddonName", "getCatalogId", "getError", "featuredItem", "getFeaturedItem", "()Lcom/streamsync/app/data/model/MediaItem;", "hasItems", "", "getHasItems", "()Z", "getItems", "()Ljava/util/List;", "getKey", "getName", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class CatalogRow {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonName;
    private final String catalogId;
    private final String error;
    private final List<MediaItem> items;
    private final String key;
    private final String name;
    private final String type;

    /* renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCatalogId() {
        return this.catalogId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<MediaItem> component7() {
        return this.items;
    }

    /* renamed from: component8, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final CatalogRow copy(String key, String addonId, String addonName, String type, String catalogId, String name, List<MediaItem> items, String error) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(addonName, "addonName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(items, "items");
        return new CatalogRow(key, addonId, addonName, type, catalogId, name, items, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogRow)) {
            return false;
        }
        CatalogRow catalogRow = (CatalogRow) other;
        return Intrinsics.areEqual(this.key, catalogRow.key) && Intrinsics.areEqual(this.addonId, catalogRow.addonId) && Intrinsics.areEqual(this.addonName, catalogRow.addonName) && Intrinsics.areEqual(this.type, catalogRow.type) && Intrinsics.areEqual(this.catalogId, catalogRow.catalogId) && Intrinsics.areEqual(this.name, catalogRow.name) && Intrinsics.areEqual(this.items, catalogRow.items) && Intrinsics.areEqual(this.error, catalogRow.error);
    }

    public int hashCode() {
        return (((((((((((((this.key.hashCode() * 31) + this.addonId.hashCode()) * 31) + this.addonName.hashCode()) * 31) + this.type.hashCode()) * 31) + this.catalogId.hashCode()) * 31) + this.name.hashCode()) * 31) + this.items.hashCode()) * 31) + (this.error == null ? 0 : this.error.hashCode());
    }

    public String toString() {
        return "CatalogRow(key=" + this.key + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ", type=" + this.type + ", catalogId=" + this.catalogId + ", name=" + this.name + ", items=" + this.items + ", error=" + this.error + ")";
    }

    public CatalogRow(String key, String addonId, String addonName, String type, String catalogId, String name, List<MediaItem> items, String error) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(addonName, "addonName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(items, "items");
        this.key = key;
        this.addonId = addonId;
        this.addonName = addonName;
        this.type = type;
        this.catalogId = catalogId;
        this.name = name;
        this.items = items;
        this.error = error;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ CatalogRow(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.util.List r18, java.lang.String r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
        /*
            r11 = this;
            r0 = r20
            r1 = r0 & 64
            if (r1 == 0) goto Lc
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r9 = r1
            goto Le
        Lc:
            r9 = r18
        Le:
            r0 = r0 & 128(0x80, float:1.8E-43)
            if (r0 == 0) goto L15
            r0 = 0
            r10 = r0
            goto L17
        L15:
            r10 = r19
        L17:
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r8 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.model.CatalogRow.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getKey() {
        return this.key;
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final String getType() {
        return this.type;
    }

    public final String getCatalogId() {
        return this.catalogId;
    }

    public final String getName() {
        return this.name;
    }

    public final List<MediaItem> getItems() {
        return this.items;
    }

    public final String getError() {
        return this.error;
    }

    public final boolean getHasItems() {
        return !this.items.isEmpty();
    }

    public final MediaItem getFeaturedItem() {
        return (MediaItem) CollectionsKt.firstOrNull((List) this.items);
    }
}
