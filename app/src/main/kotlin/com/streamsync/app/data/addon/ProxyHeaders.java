package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0017\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\f\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/streamsync/app/data/addon/ProxyHeaders;", "", "request", "", "", "response", "(Ljava/util/Map;Ljava/util/Map;)V", "getRequest", "()Ljava/util/Map;", "getResponse", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class ProxyHeaders {
    public static final int $stable = 8;
    private final Map<String, String> request;
    private final Map<String, String> response;

    /* JADX WARN: Multi-variable type inference failed */
    public ProxyHeaders() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProxyHeaders copy$default(ProxyHeaders proxyHeaders, Map map, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            map = proxyHeaders.request;
        }
        if ((i & 2) != 0) {
            map2 = proxyHeaders.response;
        }
        return proxyHeaders.copy(map, map2);
    }

    public final Map<String, String> component1() {
        return this.request;
    }

    public final Map<String, String> component2() {
        return this.response;
    }

    public final ProxyHeaders copy(Map<String, String> request, Map<String, String> response) {
        return new ProxyHeaders(request, response);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProxyHeaders)) {
            return false;
        }
        ProxyHeaders proxyHeaders = (ProxyHeaders) other;
        return Intrinsics.areEqual(this.request, proxyHeaders.request) && Intrinsics.areEqual(this.response, proxyHeaders.response);
    }

    public int hashCode() {
        return ((this.request == null ? 0 : this.request.hashCode()) * 31) + (this.response != null ? this.response.hashCode() : 0);
    }

    public String toString() {
        return "ProxyHeaders(request=" + this.request + ", response=" + this.response + ")";
    }

    public ProxyHeaders(Map<String, String> map, Map<String, String> map2) {
        this.request = map;
        this.response = map2;
    }

    public /* synthetic */ ProxyHeaders(Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : map, (i & 2) != 0 ? null : map2);
    }

    public final Map<String, String> getRequest() {
        return this.request;
    }

    public final Map<String, String> getResponse() {
        return this.response;
    }
}
