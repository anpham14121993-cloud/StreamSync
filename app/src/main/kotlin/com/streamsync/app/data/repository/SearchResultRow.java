package com.streamsync.app.data.repository;

import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchResultRow.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\u000e\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000e¨\u0006("}, d2 = {"Lcom/streamsync/app/data/repository/SearchResultRow;", "", "key", "", "addonId", "addonName", "catalogId", "catalogName", "type", "items", "", "Lcom/streamsync/app/data/model/MediaItem;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAddonId", "()Ljava/lang/String;", "getAddonName", "getCatalogId", "getCatalogName", "getItems", "()Ljava/util/List;", "getKey", "getType", "typeLabel", "getTypeLabel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "needsTypeLabel", "isNameColliding", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class SearchResultRow {
    public static final int $stable = 8;
    private final String addonId;
    private final String addonName;
    private final String catalogId;
    private final String catalogName;
    private final List<MediaItem> items;
    private final String key;
    private final String type;

    public static /* synthetic */ SearchResultRow copy$default(SearchResultRow searchResultRow, String str, String str2, String str3, String str4, String str5, String str6, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchResultRow.key;
        }
        if ((i & 2) != 0) {
            str2 = searchResultRow.addonId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = searchResultRow.addonName;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = searchResultRow.catalogId;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = searchResultRow.catalogName;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = searchResultRow.type;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            list = searchResultRow.items;
        }
        return searchResultRow.copy(str, str7, str8, str9, str10, str11, list);
    }

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
    public final String getCatalogId() {
        return this.catalogId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCatalogName() {
        return this.catalogName;
    }

    /* renamed from: component6, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final List<MediaItem> component7() {
        return this.items;
    }

    public final SearchResultRow copy(String key, String addonId, String addonName, String catalogId, String catalogName, String type, List<MediaItem> items) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(addonName, "addonName");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(catalogName, "catalogName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(items, "items");
        return new SearchResultRow(key, addonId, addonName, catalogId, catalogName, type, items);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchResultRow)) {
            return false;
        }
        SearchResultRow searchResultRow = (SearchResultRow) other;
        return Intrinsics.areEqual(this.key, searchResultRow.key) && Intrinsics.areEqual(this.addonId, searchResultRow.addonId) && Intrinsics.areEqual(this.addonName, searchResultRow.addonName) && Intrinsics.areEqual(this.catalogId, searchResultRow.catalogId) && Intrinsics.areEqual(this.catalogName, searchResultRow.catalogName) && Intrinsics.areEqual(this.type, searchResultRow.type) && Intrinsics.areEqual(this.items, searchResultRow.items);
    }

    public int hashCode() {
        return (((((((((((this.key.hashCode() * 31) + this.addonId.hashCode()) * 31) + this.addonName.hashCode()) * 31) + this.catalogId.hashCode()) * 31) + this.catalogName.hashCode()) * 31) + this.type.hashCode()) * 31) + this.items.hashCode();
    }

    public String toString() {
        return "SearchResultRow(key=" + this.key + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ", catalogId=" + this.catalogId + ", catalogName=" + this.catalogName + ", type=" + this.type + ", items=" + this.items + ")";
    }

    public SearchResultRow(String key, String addonId, String addonName, String catalogId, String catalogName, String type, List<MediaItem> items) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(addonName, "addonName");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(catalogName, "catalogName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(items, "items");
        this.key = key;
        this.addonId = addonId;
        this.addonName = addonName;
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.type = type;
        this.items = items;
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

    public final String getCatalogId() {
        return this.catalogId;
    }

    public final String getCatalogName() {
        return this.catalogName;
    }

    public final String getType() {
        return this.type;
    }

    public final List<MediaItem> getItems() {
        return this.items;
    }

    public final String getTypeLabel() {
        String lowerCase = this.type.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return Intrinsics.areEqual(lowerCase, "series") ? "Phim bộ" : Intrinsics.areEqual(lowerCase, "movie") ? "Phim lẻ" : this.type;
    }

    public final boolean needsTypeLabel(boolean isNameColliding) {
        return isNameColliding;
    }
}
