package com.streamsync.app.data.addon;

import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;

/* compiled from: CacheDirective.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0010\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/data/addon/CacheDirective;", "", "ttlMs", "", "(J)V", "expiresAtMs", "getExpiresAtMs", "()J", "getTtlMs", "component1", "copy", "equals", "", "other", "hashCode", "", "isExpired", "atMs", "toString", "", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class CacheDirective {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CacheDirective DEFAULT = new CacheDirective(21600000);
    public static final long MAX_MS = 604800000;
    public static final long MIN_MS = 300000;
    private final long ttlMs;

    public static /* synthetic */ CacheDirective copy$default(CacheDirective cacheDirective, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = cacheDirective.ttlMs;
        }
        return cacheDirective.copy(j);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTtlMs() {
        return this.ttlMs;
    }

    public final CacheDirective copy(long ttlMs) {
        return new CacheDirective(ttlMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CacheDirective) && this.ttlMs == ((CacheDirective) other).ttlMs;
    }

    public int hashCode() {
        return Long.hashCode(this.ttlMs);
    }

    public String toString() {
        return "CacheDirective(ttlMs=" + this.ttlMs + ")";
    }

    public CacheDirective(long ttlMs) {
        this.ttlMs = ttlMs;
    }

    public final long getTtlMs() {
        return this.ttlMs;
    }

    public final long getExpiresAtMs() {
        return System.currentTimeMillis() + this.ttlMs;
    }

    public static /* synthetic */ boolean isExpired$default(CacheDirective cacheDirective, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis();
        }
        return cacheDirective.isExpired(j);
    }

    public final boolean isExpired(long atMs) {
        return atMs >= getExpiresAtMs();
    }

    /* compiled from: CacheDirective.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/streamsync/app/data/addon/CacheDirective$Companion;", "", "()V", "DEFAULT", "Lcom/streamsync/app/data/addon/CacheDirective;", "getDEFAULT", "()Lcom/streamsync/app/data/addon/CacheDirective;", "MAX_MS", "", "MIN_MS", "parse", "cacheControl", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CacheDirective getDEFAULT() {
            return CacheDirective.DEFAULT;
        }

        public final CacheDirective parse(String cacheControl) {
            Object m6717constructorimpl;
            String str = cacheControl;
            if (str == null || StringsKt.isBlank(str)) {
                return getDEFAULT();
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                m6717constructorimpl = Result.m6717constructorimpl(CacheControl.INSTANCE.parse(Headers.INSTANCE.of(HttpHeaders.CACHE_CONTROL, cacheControl)));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                m6717constructorimpl = null;
            }
            CacheControl parsed = (CacheControl) m6717constructorimpl;
            if (parsed == null) {
                return getDEFAULT();
            }
            if (!parsed.noStore() && !parsed.noCache()) {
                int seconds = parsed.maxAgeSeconds();
                return seconds < 0 ? getDEFAULT() : new CacheDirective(RangesKt.coerceAtMost(seconds * 1000, CacheDirective.MAX_MS));
            }
            return new CacheDirective(300000L);
        }
    }
}
