package com.streamsync.app.data.addon;

import com.google.gson.annotations.JsonAdapter;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@JsonAdapter(AddonBehaviorHintsAdapter.class)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/addon/AddonBehaviorHints;", "", "mimeType", "", "filename", "proxyHeaders", "Lcom/streamsync/app/data/addon/ProxyHeaders;", "(Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/data/addon/ProxyHeaders;)V", "getFilename", "()Ljava/lang/String;", "getMimeType", "getProxyHeaders", "()Lcom/streamsync/app/data/addon/ProxyHeaders;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonBehaviorHints {
    public static final int $stable = 8;
    private final String filename;
    private final String mimeType;
    private final ProxyHeaders proxyHeaders;

    public AddonBehaviorHints() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ AddonBehaviorHints copy$default(AddonBehaviorHints addonBehaviorHints, String str, String str2, ProxyHeaders proxyHeaders, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonBehaviorHints.mimeType;
        }
        if ((i & 2) != 0) {
            str2 = addonBehaviorHints.filename;
        }
        if ((i & 4) != 0) {
            proxyHeaders = addonBehaviorHints.proxyHeaders;
        }
        return addonBehaviorHints.copy(str, str2, proxyHeaders);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    /* renamed from: component3, reason: from getter */
    public final ProxyHeaders getProxyHeaders() {
        return this.proxyHeaders;
    }

    public final AddonBehaviorHints copy(String mimeType, String filename, ProxyHeaders proxyHeaders) {
        return new AddonBehaviorHints(mimeType, filename, proxyHeaders);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonBehaviorHints)) {
            return false;
        }
        AddonBehaviorHints addonBehaviorHints = (AddonBehaviorHints) other;
        return Intrinsics.areEqual(this.mimeType, addonBehaviorHints.mimeType) && Intrinsics.areEqual(this.filename, addonBehaviorHints.filename) && Intrinsics.areEqual(this.proxyHeaders, addonBehaviorHints.proxyHeaders);
    }

    public int hashCode() {
        return ((((this.mimeType == null ? 0 : this.mimeType.hashCode()) * 31) + (this.filename == null ? 0 : this.filename.hashCode())) * 31) + (this.proxyHeaders != null ? this.proxyHeaders.hashCode() : 0);
    }

    public String toString() {
        return "AddonBehaviorHints(mimeType=" + this.mimeType + ", filename=" + this.filename + ", proxyHeaders=" + this.proxyHeaders + ")";
    }

    public AddonBehaviorHints(String mimeType, String filename, ProxyHeaders proxyHeaders) {
        this.mimeType = mimeType;
        this.filename = filename;
        this.proxyHeaders = proxyHeaders;
    }

    public /* synthetic */ AddonBehaviorHints(String str, String str2, ProxyHeaders proxyHeaders, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : proxyHeaders);
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final ProxyHeaders getProxyHeaders() {
        return this.proxyHeaders;
    }
}
