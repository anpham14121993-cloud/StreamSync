package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003¢\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0010HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\bHÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\bHÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u009b\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0010HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014¨\u00062"}, d2 = {"Lcom/streamsync/app/data/addon/AddonManifest;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "version", "description", "types", "", "catalogs", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "resources", "Lcom/streamsync/app/data/addon/AddonResourceDef;", "idPrefixes", "logo", "sortOrder", "", "baseUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "getCatalogs", "()Ljava/util/List;", "getDescription", "getId", "getIdPrefixes", "getLogo", "getName", "getResources", "getSortOrder", "()I", "getTypes", "getVersion", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonManifest {
    public static final int $stable = 8;
    private final String baseUrl;
    private final List<AddonCatalogDef> catalogs;
    private final String description;
    private final String id;
    private final List<String> idPrefixes;
    private final String logo;
    private final String name;
    private final List<AddonResourceDef> resources;
    private final int sortOrder;
    private final List<String> types;
    private final String version;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final int getSortOrder() {
        return this.sortOrder;
    }

    /* renamed from: component11, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component5() {
        return this.types;
    }

    public final List<AddonCatalogDef> component6() {
        return this.catalogs;
    }

    public final List<AddonResourceDef> component7() {
        return this.resources;
    }

    public final List<String> component8() {
        return this.idPrefixes;
    }

    /* renamed from: component9, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    public final AddonManifest copy(String id, String name, String version, String description, List<String> types, List<AddonCatalogDef> catalogs, List<AddonResourceDef> resources, List<String> idPrefixes, String logo, int sortOrder, String baseUrl) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        return new AddonManifest(id, name, version, description, types, catalogs, resources, idPrefixes, logo, sortOrder, baseUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonManifest)) {
            return false;
        }
        AddonManifest addonManifest = (AddonManifest) other;
        return Intrinsics.areEqual(this.id, addonManifest.id) && Intrinsics.areEqual(this.name, addonManifest.name) && Intrinsics.areEqual(this.version, addonManifest.version) && Intrinsics.areEqual(this.description, addonManifest.description) && Intrinsics.areEqual(this.types, addonManifest.types) && Intrinsics.areEqual(this.catalogs, addonManifest.catalogs) && Intrinsics.areEqual(this.resources, addonManifest.resources) && Intrinsics.areEqual(this.idPrefixes, addonManifest.idPrefixes) && Intrinsics.areEqual(this.logo, addonManifest.logo) && this.sortOrder == addonManifest.sortOrder && Intrinsics.areEqual(this.baseUrl, addonManifest.baseUrl);
    }

    public int hashCode() {
        return (((((((((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.version.hashCode()) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.types == null ? 0 : this.types.hashCode())) * 31) + (this.catalogs == null ? 0 : this.catalogs.hashCode())) * 31) + (this.resources == null ? 0 : this.resources.hashCode())) * 31) + (this.idPrefixes == null ? 0 : this.idPrefixes.hashCode())) * 31) + (this.logo != null ? this.logo.hashCode() : 0)) * 31) + Integer.hashCode(this.sortOrder)) * 31) + this.baseUrl.hashCode();
    }

    public String toString() {
        return "AddonManifest(id=" + this.id + ", name=" + this.name + ", version=" + this.version + ", description=" + this.description + ", types=" + this.types + ", catalogs=" + this.catalogs + ", resources=" + this.resources + ", idPrefixes=" + this.idPrefixes + ", logo=" + this.logo + ", sortOrder=" + this.sortOrder + ", baseUrl=" + this.baseUrl + ")";
    }

    public AddonManifest(String id, String name, String version, String description, List<String> list, List<AddonCatalogDef> list2, List<AddonResourceDef> list3, List<String> list4, String logo, int sortOrder, String baseUrl) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.types = list;
        this.catalogs = list2;
        this.resources = list3;
        this.idPrefixes = list4;
        this.logo = logo;
        this.sortOrder = sortOrder;
        this.baseUrl = baseUrl;
    }

    public /* synthetic */ AddonManifest(String str, String str2, String str3, String str4, List list, List list2, List list3, List list4, String str5, int i, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : list, (i2 & 32) != 0 ? null : list2, (i2 & 64) != 0 ? null : list3, (i2 & 128) != 0 ? null : list4, (i2 & 256) != 0 ? null : str5, (i2 & 512) != 0 ? 0 : i, (i2 & 1024) != 0 ? "" : str6);
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

    public final List<String> getTypes() {
        return this.types;
    }

    public final List<AddonCatalogDef> getCatalogs() {
        return this.catalogs;
    }

    public final List<AddonResourceDef> getResources() {
        return this.resources;
    }

    public final List<String> getIdPrefixes() {
        return this.idPrefixes;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }
}
