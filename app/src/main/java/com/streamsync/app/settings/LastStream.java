package com.streamsync.app.settings;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LastStreamStore.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/streamsync/app/settings/LastStream;", "", "url", "", LastStreamStore.KEY_HEADERS, "", "contentLengthBytes", "", "durationMs", "savedAtMs", "(Ljava/lang/String;Ljava/util/Map;JJJ)V", "averageVideoBitrateMbps", "", "getAverageVideoBitrateMbps", "()Ljava/lang/Double;", "getContentLengthBytes", "()J", "getDurationMs", "getHeaders", "()Ljava/util/Map;", "getSavedAtMs", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class LastStream {
    public static final int $stable = 8;
    private final long contentLengthBytes;
    private final long durationMs;
    private final Map<String, String> headers;
    private final long savedAtMs;
    private final String url;

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final Map<String, String> component2() {
        return this.headers;
    }

    /* renamed from: component3, reason: from getter */
    public final long getContentLengthBytes() {
        return this.contentLengthBytes;
    }

    /* renamed from: component4, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    /* renamed from: component5, reason: from getter */
    public final long getSavedAtMs() {
        return this.savedAtMs;
    }

    public final LastStream copy(String url, Map<String, String> headers, long contentLengthBytes, long durationMs, long savedAtMs) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return new LastStream(url, headers, contentLengthBytes, durationMs, savedAtMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LastStream)) {
            return false;
        }
        LastStream lastStream = (LastStream) other;
        return Intrinsics.areEqual(this.url, lastStream.url) && Intrinsics.areEqual(this.headers, lastStream.headers) && this.contentLengthBytes == lastStream.contentLengthBytes && this.durationMs == lastStream.durationMs && this.savedAtMs == lastStream.savedAtMs;
    }

    public int hashCode() {
        return (((((((this.url.hashCode() * 31) + this.headers.hashCode()) * 31) + Long.hashCode(this.contentLengthBytes)) * 31) + Long.hashCode(this.durationMs)) * 31) + Long.hashCode(this.savedAtMs);
    }

    public String toString() {
        return "LastStream(url=" + this.url + ", headers=" + this.headers + ", contentLengthBytes=" + this.contentLengthBytes + ", durationMs=" + this.durationMs + ", savedAtMs=" + this.savedAtMs + ")";
    }

    public LastStream(String url, Map<String, String> headers, long contentLengthBytes, long durationMs, long savedAtMs) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.url = url;
        this.headers = headers;
        this.contentLengthBytes = contentLengthBytes;
        this.durationMs = durationMs;
        this.savedAtMs = savedAtMs;
    }

    public final String getUrl() {
        return this.url;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final long getContentLengthBytes() {
        return this.contentLengthBytes;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final long getSavedAtMs() {
        return this.savedAtMs;
    }

    public final Double getAverageVideoBitrateMbps() {
        if (this.contentLengthBytes > 0 && this.durationMs > 0) {
            return Double.valueOf(((this.contentLengthBytes * 8.0d) / (this.durationMs / 1000.0d)) / 1000000.0d);
        }
        return null;
    }
}
