package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* compiled from: AddonEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u000eHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J{\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u000eHÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006."}, d2 = {"Lcom/streamsync/app/data/addon/InstalledAddonEntity;", "", "baseUrl", "", "id", HintConstants.AUTOFILL_HINT_NAME, "version", "description", "typesCsv", "catalogsJson", "resourcesJson", "idPrefixesCsv", "logoUrl", "sortOrder", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getBaseUrl", "()Ljava/lang/String;", "getCatalogsJson", "getDescription", "getId", "getIdPrefixesCsv", "getLogoUrl", "getName", "getResourcesJson", "getSortOrder", "()I", "getTypesCsv", "getVersion", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class InstalledAddonEntity {
    public static final int $stable = 0;
    private final String baseUrl;
    private final String catalogsJson;
    private final String description;
    private final String id;
    private final String idPrefixesCsv;
    private final String logoUrl;
    private final String name;
    private final String resourcesJson;
    private final int sortOrder;
    private final String typesCsv;
    private final String version;

    /* renamed from: component1, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* renamed from: component10, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* renamed from: component11, reason: from getter */
    public final int getSortOrder() {
        return this.sortOrder;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTypesCsv() {
        return this.typesCsv;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCatalogsJson() {
        return this.catalogsJson;
    }

    /* renamed from: component8, reason: from getter */
    public final String getResourcesJson() {
        return this.resourcesJson;
    }

    /* renamed from: component9, reason: from getter */
    public final String getIdPrefixesCsv() {
        return this.idPrefixesCsv;
    }

    public final InstalledAddonEntity copy(String baseUrl, String id, String name, String version, String description, String typesCsv, String catalogsJson, String resourcesJson, String idPrefixesCsv, String logoUrl, int sortOrder) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(typesCsv, "typesCsv");
        Intrinsics.checkNotNullParameter(catalogsJson, "catalogsJson");
        Intrinsics.checkNotNullParameter(resourcesJson, "resourcesJson");
        Intrinsics.checkNotNullParameter(idPrefixesCsv, "idPrefixesCsv");
        return new InstalledAddonEntity(baseUrl, id, name, version, description, typesCsv, catalogsJson, resourcesJson, idPrefixesCsv, logoUrl, sortOrder);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InstalledAddonEntity)) {
            return false;
        }
        InstalledAddonEntity installedAddonEntity = (InstalledAddonEntity) other;
        return Intrinsics.areEqual(this.baseUrl, installedAddonEntity.baseUrl) && Intrinsics.areEqual(this.id, installedAddonEntity.id) && Intrinsics.areEqual(this.name, installedAddonEntity.name) && Intrinsics.areEqual(this.version, installedAddonEntity.version) && Intrinsics.areEqual(this.description, installedAddonEntity.description) && Intrinsics.areEqual(this.typesCsv, installedAddonEntity.typesCsv) && Intrinsics.areEqual(this.catalogsJson, installedAddonEntity.catalogsJson) && Intrinsics.areEqual(this.resourcesJson, installedAddonEntity.resourcesJson) && Intrinsics.areEqual(this.idPrefixesCsv, installedAddonEntity.idPrefixesCsv) && Intrinsics.areEqual(this.logoUrl, installedAddonEntity.logoUrl) && this.sortOrder == installedAddonEntity.sortOrder;
    }

    public int hashCode() {
        return (((((((((((((((((((this.baseUrl.hashCode() * 31) + this.id.hashCode()) * 31) + this.name.hashCode()) * 31) + this.version.hashCode()) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + this.typesCsv.hashCode()) * 31) + this.catalogsJson.hashCode()) * 31) + this.resourcesJson.hashCode()) * 31) + this.idPrefixesCsv.hashCode()) * 31) + (this.logoUrl != null ? this.logoUrl.hashCode() : 0)) * 31) + Integer.hashCode(this.sortOrder);
    }

    public String toString() {
        return "InstalledAddonEntity(baseUrl=" + this.baseUrl + ", id=" + this.id + ", name=" + this.name + ", version=" + this.version + ", description=" + this.description + ", typesCsv=" + this.typesCsv + ", catalogsJson=" + this.catalogsJson + ", resourcesJson=" + this.resourcesJson + ", idPrefixesCsv=" + this.idPrefixesCsv + ", logoUrl=" + this.logoUrl + ", sortOrder=" + this.sortOrder + ")";
    }

    public InstalledAddonEntity(String baseUrl, String id, String name, String version, String description, String typesCsv, String catalogsJson, String resourcesJson, String idPrefixesCsv, String logoUrl, int sortOrder) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(typesCsv, "typesCsv");
        Intrinsics.checkNotNullParameter(catalogsJson, "catalogsJson");
        Intrinsics.checkNotNullParameter(resourcesJson, "resourcesJson");
        Intrinsics.checkNotNullParameter(idPrefixesCsv, "idPrefixesCsv");
        this.baseUrl = baseUrl;
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.typesCsv = typesCsv;
        this.catalogsJson = catalogsJson;
        this.resourcesJson = resourcesJson;
        this.idPrefixesCsv = idPrefixesCsv;
        this.logoUrl = logoUrl;
        this.sortOrder = sortOrder;
    }

    public /* synthetic */ InstalledAddonEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, (i2 & 128) != 0 ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : str8, (i2 & 256) != 0 ? "" : str9, (i2 & 512) != 0 ? null : str10, (i2 & 1024) != 0 ? 0 : i);
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getTypesCsv() {
        return this.typesCsv;
    }

    public final String getCatalogsJson() {
        return this.catalogsJson;
    }

    public final String getResourcesJson() {
        return this.resourcesJson;
    }

    public final String getIdPrefixesCsv() {
        return this.idPrefixesCsv;
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }
}
