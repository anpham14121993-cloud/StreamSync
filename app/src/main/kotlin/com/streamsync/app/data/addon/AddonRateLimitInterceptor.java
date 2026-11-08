package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: AddonRateLimitInterceptor.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\n"}, d2 = {"Lcom/streamsync/app/data/addon/AddonRateLimitInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "jitterMs", "", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonRateLimitInterceptor implements Interceptor {
    public static final int $stable = 0;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long JITTER_MS = 700;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        String host = chain.request().url().host();
        long waitMs = 800;
        long waitedMs = 0;
        int attempt = 0;
        while (true) {
            AddonHostGate.INSTANCE.acquire(host);
            try {
                Response response = chain.proceed(chain.request());
                AddonHostGate.INSTANCE.release(host);
                boolean isLast = attempt >= 2;
                if (!AddonRetryPolicy.INSTANCE.shouldRetry(response.code())) {
                    AddonHostGate.INSTANCE.onSuccess(host);
                    return response;
                }
                if (isLast) {
                    return response;
                }
                Long serverWaitMs = AddonRetryPolicy.INSTANCE.retryAfterMs(Response.header$default(response, "retry-after", null, 2, null), System.currentTimeMillis());
                AddonHostGate.INSTANCE.penalize(host, serverWaitMs);
                response.close();
                long sleepMs = AddonRetryPolicy.INSTANCE.waitMs(serverWaitMs, waitMs, jitterMs());
                if (waitedMs + sleepMs > 20000) {
                    return chain.proceed(chain.request());
                }
                if (sleepMs > 0) {
                    try {
                        Thread.sleep(sleepMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Bị ngắt khi chờ thử lại addon", e);
                    }
                }
                waitedMs += sleepMs;
                attempt++;
                waitMs = RangesKt.coerceAtMost(2 * waitMs, 12000L);
            } catch (Throwable th) {
                AddonHostGate.INSTANCE.release(host);
                throw th;
            }
        }
    }

    private final long jitterMs() {
        return Random.INSTANCE.nextLong(0L, 701L);
    }

    /* compiled from: AddonRateLimitInterceptor.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/streamsync/app/data/addon/AddonRateLimitInterceptor$Companion;", "", "()V", "JITTER_MS", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
