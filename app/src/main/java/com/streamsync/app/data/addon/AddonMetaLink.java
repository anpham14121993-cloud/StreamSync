package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/data/addon/AddonMetaLink;", "", HintConstants.AUTOFILL_HINT_NAME, "", "category", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getName", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonMetaLink {
    public static final int $stable = 0;
    private final String category;
    private final String name;
    private final String url;

    public AddonMetaLink() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ AddonMetaLink copy$default(AddonMetaLink addonMetaLink, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonMetaLink.name;
        }
        if ((i & 2) != 0) {
            str2 = addonMetaLink.category;
        }
        if ((i & 4) != 0) {
            str3 = addonMetaLink.url;
        }
        return addonMetaLink.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final AddonMetaLink copy(String name, String category, String url) {
        return new AddonMetaLink(name, category, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonMetaLink)) {
            return false;
        }
        AddonMetaLink addonMetaLink = (AddonMetaLink) other;
        return Intrinsics.areEqual(this.name, addonMetaLink.name) && Intrinsics.areEqual(this.category, addonMetaLink.category) && Intrinsics.areEqual(this.url, addonMetaLink.url);
    }

    public int hashCode() {
        return ((((this.name == null ? 0 : this.name.hashCode()) * 31) + (this.category == null ? 0 : this.category.hashCode())) * 31) + (this.url != null ? this.url.hashCode() : 0);
    }

    public String toString() {
        return "AddonMetaLink(name=" + this.name + ", category=" + this.category + ", url=" + this.url + ")";
    }

    public AddonMetaLink(String name, String category, String url) {
        this.name = name;
        this.category = category;
        this.url = url;
    }

    public /* synthetic */ AddonMetaLink(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getName() {
        return this.name;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getUrl() {
        return this.url;
    }
}
