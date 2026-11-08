package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0016Jn\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\fHÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013¨\u0006'"}, d2 = {"Lcom/streamsync/app/data/addon/AddonCatalogDef;", "", "type", "", "id", HintConstants.AUTOFILL_HINT_NAME, "extra", "", "Lcom/streamsync/app/data/addon/AddonExtraDef;", "extraSupported", "extraRequired", "pageSize", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)V", "getExtra", "()Ljava/util/List;", "getExtraRequired", "getExtraSupported", "getId", "()Ljava/lang/String;", "getName", "getPageSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)Lcom/streamsync/app/data/addon/AddonCatalogDef;", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonCatalogDef {
    public static final int $stable = 8;
    private final List<AddonExtraDef> extra;
    private final List<String> extraRequired;
    private final List<String> extraSupported;
    private final String id;
    private final String name;
    private final Integer pageSize;
    private final String type;

    public AddonCatalogDef() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ AddonCatalogDef copy$default(AddonCatalogDef addonCatalogDef, String str, String str2, String str3, List list, List list2, List list3, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonCatalogDef.type;
        }
        if ((i & 2) != 0) {
            str2 = addonCatalogDef.id;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = addonCatalogDef.name;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            list = addonCatalogDef.extra;
        }
        List list4 = list;
        if ((i & 16) != 0) {
            list2 = addonCatalogDef.extraSupported;
        }
        List list5 = list2;
        if ((i & 32) != 0) {
            list3 = addonCatalogDef.extraRequired;
        }
        List list6 = list3;
        if ((i & 64) != 0) {
            num = addonCatalogDef.pageSize;
        }
        return addonCatalogDef.copy(str, str4, str5, list4, list5, list6, num);
    }

    /* renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<AddonExtraDef> component4() {
        return this.extra;
    }

    public final List<String> component5() {
        return this.extraSupported;
    }

    public final List<String> component6() {
        return this.extraRequired;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getPageSize() {
        return this.pageSize;
    }

    public final AddonCatalogDef copy(String type, String id, String name, List<AddonExtraDef> extra, List<String> extraSupported, List<String> extraRequired, Integer pageSize) {
        Intrinsics.checkNotNullParameter(extra, "extra");
        Intrinsics.checkNotNullParameter(extraSupported, "extraSupported");
        Intrinsics.checkNotNullParameter(extraRequired, "extraRequired");
        return new AddonCatalogDef(type, id, name, extra, extraSupported, extraRequired, pageSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonCatalogDef)) {
            return false;
        }
        AddonCatalogDef addonCatalogDef = (AddonCatalogDef) other;
        return Intrinsics.areEqual(this.type, addonCatalogDef.type) && Intrinsics.areEqual(this.id, addonCatalogDef.id) && Intrinsics.areEqual(this.name, addonCatalogDef.name) && Intrinsics.areEqual(this.extra, addonCatalogDef.extra) && Intrinsics.areEqual(this.extraSupported, addonCatalogDef.extraSupported) && Intrinsics.areEqual(this.extraRequired, addonCatalogDef.extraRequired) && Intrinsics.areEqual(this.pageSize, addonCatalogDef.pageSize);
    }

    public int hashCode() {
        return ((((((((((((this.type == null ? 0 : this.type.hashCode()) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + this.extra.hashCode()) * 31) + this.extraSupported.hashCode()) * 31) + this.extraRequired.hashCode()) * 31) + (this.pageSize != null ? this.pageSize.hashCode() : 0);
    }

    public String toString() {
        return "AddonCatalogDef(type=" + this.type + ", id=" + this.id + ", name=" + this.name + ", extra=" + this.extra + ", extraSupported=" + this.extraSupported + ", extraRequired=" + this.extraRequired + ", pageSize=" + this.pageSize + ")";
    }

    public AddonCatalogDef(String type, String id, String name, List<AddonExtraDef> extra, List<String> extraSupported, List<String> extraRequired, Integer pageSize) {
        Intrinsics.checkNotNullParameter(extra, "extra");
        Intrinsics.checkNotNullParameter(extraSupported, "extraSupported");
        Intrinsics.checkNotNullParameter(extraRequired, "extraRequired");
        this.type = type;
        this.id = id;
        this.name = name;
        this.extra = extra;
        this.extraSupported = extraSupported;
        this.extraRequired = extraRequired;
        this.pageSize = pageSize;
    }

    public /* synthetic */ AddonCatalogDef(String str, String str2, String str3, List list, List list2, List list3, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? CollectionsKt.emptyList() : list2, (i & 32) != 0 ? CollectionsKt.emptyList() : list3, (i & 64) != 0 ? null : num);
    }

    public final String getType() {
        return this.type;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final List<AddonExtraDef> getExtra() {
        return this.extra;
    }

    public final List<String> getExtraSupported() {
        return this.extraSupported;
    }

    public final List<String> getExtraRequired() {
        return this.extraRequired;
    }

    public final Integer getPageSize() {
        return this.pageSize;
    }
}
