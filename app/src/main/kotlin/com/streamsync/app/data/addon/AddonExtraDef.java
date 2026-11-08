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
@JsonAdapter(AddonExtraDefAdapter.class)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/addon/AddonExtraDef;", "", HintConstants.AUTOFILL_HINT_NAME, "", "isRequired", "", "options", "", "(Ljava/lang/String;ZLjava/util/List;)V", "()Z", "getName", "()Ljava/lang/String;", "getOptions", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonExtraDef {
    public static final int $stable = 8;
    private final boolean isRequired;
    private final String name;
    private final List<String> options;

    public AddonExtraDef() {
        this(null, false, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonExtraDef copy$default(AddonExtraDef addonExtraDef, String str, boolean z, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonExtraDef.name;
        }
        if ((i & 2) != 0) {
            z = addonExtraDef.isRequired;
        }
        if ((i & 4) != 0) {
            list = addonExtraDef.options;
        }
        return addonExtraDef.copy(str, z, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsRequired() {
        return this.isRequired;
    }

    public final List<String> component3() {
        return this.options;
    }

    public final AddonExtraDef copy(String name, boolean isRequired, List<String> options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        return new AddonExtraDef(name, isRequired, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonExtraDef)) {
            return false;
        }
        AddonExtraDef addonExtraDef = (AddonExtraDef) other;
        return Intrinsics.areEqual(this.name, addonExtraDef.name) && this.isRequired == addonExtraDef.isRequired && Intrinsics.areEqual(this.options, addonExtraDef.options);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + Boolean.hashCode(this.isRequired)) * 31) + this.options.hashCode();
    }

    public String toString() {
        return "AddonExtraDef(name=" + this.name + ", isRequired=" + this.isRequired + ", options=" + this.options + ")";
    }

    public AddonExtraDef(String name, boolean isRequired, List<String> options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        this.name = name;
        this.isRequired = isRequired;
        this.options = options;
    }

    public /* synthetic */ AddonExtraDef(String str, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isRequired() {
        return this.isRequired;
    }

    public final List<String> getOptions() {
        return this.options;
    }
}
