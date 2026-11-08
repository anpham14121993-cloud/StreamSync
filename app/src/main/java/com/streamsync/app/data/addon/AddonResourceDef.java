package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.google.gson.annotations.JsonAdapter;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@JsonAdapter(AddonResourceDefAdapter.class)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J3\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/addon/AddonResourceDef;", "", HintConstants.AUTOFILL_HINT_NAME, "", "types", "", "idPrefixes", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getIdPrefixes", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "getTypes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonResourceDef {
    public static final int $stable = 8;
    private final List<String> idPrefixes;
    private final String name;
    private final List<String> types;

    public AddonResourceDef() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonResourceDef copy$default(AddonResourceDef addonResourceDef, String str, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonResourceDef.name;
        }
        if ((i & 2) != 0) {
            list = addonResourceDef.types;
        }
        if ((i & 4) != 0) {
            list2 = addonResourceDef.idPrefixes;
        }
        return addonResourceDef.copy(str, list, list2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component2() {
        return this.types;
    }

    public final List<String> component3() {
        return this.idPrefixes;
    }

    public final AddonResourceDef copy(String name, List<String> types, List<String> idPrefixes) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(idPrefixes, "idPrefixes");
        return new AddonResourceDef(name, types, idPrefixes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonResourceDef)) {
            return false;
        }
        AddonResourceDef addonResourceDef = (AddonResourceDef) other;
        return Intrinsics.areEqual(this.name, addonResourceDef.name) && Intrinsics.areEqual(this.types, addonResourceDef.types) && Intrinsics.areEqual(this.idPrefixes, addonResourceDef.idPrefixes);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.types.hashCode()) * 31) + this.idPrefixes.hashCode();
    }

    public String toString() {
        return "AddonResourceDef(name=" + this.name + ", types=" + this.types + ", idPrefixes=" + this.idPrefixes + ")";
    }

    public AddonResourceDef(String name, List<String> types, List<String> idPrefixes) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(idPrefixes, "idPrefixes");
        this.name = name;
        this.types = types;
        this.idPrefixes = idPrefixes;
    }

    public /* synthetic */ AddonResourceDef(String str, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getTypes() {
        return this.types;
    }

    public final List<String> getIdPrefixes() {
        return this.idPrefixes;
    }
}
