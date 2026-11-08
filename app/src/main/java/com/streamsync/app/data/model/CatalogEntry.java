package com.streamsync.app.data.model;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/streamsync/app/data/model/CatalogEntry;", "", "key", "", "addonId", "addonName", "type", "catalogId", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddonId", "()Ljava/lang/String;", "getAddonName", "getCatalogId", "getKey", "getName", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class CatalogEntry {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonName;
    private final String catalogId;
    private final String key;
    private final String name;
    private final String type;

    public static /* synthetic */ CatalogEntry copy$default(CatalogEntry catalogEntry, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = catalogEntry.key;
        }
        if ((i & 2) != 0) {
            str2 = catalogEntry.addonId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = catalogEntry.addonName;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = catalogEntry.type;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = catalogEntry.catalogId;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = catalogEntry.name;
        }
        return catalogEntry.copy(str, str7, str8, str9, str10, str6);
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

    public final CatalogEntry copy(String key, String addonId, String addonName, String type, String catalogId, String name) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(addonName, "addonName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(name, "name");
        return new CatalogEntry(key, addonId, addonName, type, catalogId, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogEntry)) {
            return false;
        }
        CatalogEntry catalogEntry = (CatalogEntry) other;
        return Intrinsics.areEqual(this.key, catalogEntry.key) && Intrinsics.areEqual(this.addonId, catalogEntry.addonId) && Intrinsics.areEqual(this.addonName, catalogEntry.addonName) && Intrinsics.areEqual(this.type, catalogEntry.type) && Intrinsics.areEqual(this.catalogId, catalogEntry.catalogId) && Intrinsics.areEqual(this.name, catalogEntry.name);
    }

    public int hashCode() {
        return (((((((((this.key.hashCode() * 31) + this.addonId.hashCode()) * 31) + this.addonName.hashCode()) * 31) + this.type.hashCode()) * 31) + this.catalogId.hashCode()) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "CatalogEntry(key=" + this.key + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ", type=" + this.type + ", catalogId=" + this.catalogId + ", name=" + this.name + ")";
    }

    public CatalogEntry(String key, String addonId, String addonName, String type, String catalogId, String name) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(addonId, "addonId");
        Intrinsics.checkNotNullParameter(addonName, "addonName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(catalogId, "catalogId");
        Intrinsics.checkNotNullParameter(name, "name");
        this.key = key;
        this.addonId = addonId;
        this.addonName = addonName;
        this.type = type;
        this.catalogId = catalogId;
        this.name = name;
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
}
