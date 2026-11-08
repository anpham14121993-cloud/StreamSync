package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: AddonRetryPolicy.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006J%\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/data/addon/AddonRetryPolicy;", "", "()V", "FIRST_WAIT_MS", "", "MAX_ATTEMPTS", "", "MAX_WAIT_MS", "TOTAL_WAIT_MS", "retryAfterMs", "header", "", "nowMs", "(Ljava/lang/String;J)Ljava/lang/Long;", "shouldRetry", "", "code", "waitMs", "serverWaitMs", "fallbackMs", "jitterMs", "(Ljava/lang/Long;JJ)J", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonRetryPolicy {
    public static final int $stable = 0;
    public static final long FIRST_WAIT_MS = 800;
    public static final AddonRetryPolicy INSTANCE = new AddonRetryPolicy();
    public static final int MAX_ATTEMPTS = 3;
    public static final long MAX_WAIT_MS = 12000;
    public static final long TOTAL_WAIT_MS = 20000;

    private AddonRetryPolicy() {
    }

    public final boolean shouldRetry(int code) {
        return code == 429 || code == 502 || code == 503 || code == 504;
    }

    public final Long retryAfterMs(String header, long nowMs) {
        String raw;
        Object m6717constructorimpl;
        if (header != null && (raw = StringsKt.trim((CharSequence) header).toString()) != null) {
            if (!(raw.length() > 0)) {
                raw = null;
            }
            if (raw != null) {
                Long longOrNull = StringsKt.toLongOrNull(raw);
                if (longOrNull != null) {
                    long it = longOrNull.longValue();
                    return Long.valueOf(RangesKt.coerceAtLeast(it, 0L) * 1000);
                }
                try {
                    Result.Companion companion = Result.INSTANCE;
                    AddonRetryPolicy addonRetryPolicy = this;
                    long target = ZonedDateTime.parse(raw, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant().toEpochMilli();
                    m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(RangesKt.coerceAtLeast(target - nowMs, 0L)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
                }
                return (Long) (Result.m6723isFailureimpl(m6717constructorimpl) ? null : m6717constructorimpl);
            }
        }
        return null;
    }

    public final long waitMs(Long serverWaitMs, long fallbackMs, long jitterMs) {
        long usableServerWait = (serverWaitMs == null || !new LongRange(0L, 12000L).contains(serverWaitMs.longValue())) ? fallbackMs : serverWaitMs.longValue();
        return Math.min(usableServerWait + jitterMs, 12000L);
    }
}
