package com.streamsync.app.data.remote;

import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.BufferCeiling;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: AddonCacheControlInterceptor.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/streamsync/app/data/remote/AddonCacheControlInterceptor;", "Lokhttp3/Interceptor;", "freshSeconds", "", "(I)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class AddonCacheControlInterceptor implements Interceptor {
    public static final int $stable = 0;
    public static final int FRESH_SECONDS = 300;
    private final int freshSeconds;

    public AddonCacheControlInterceptor() {
        this(0, 1, null);
    }

    public AddonCacheControlInterceptor(int freshSeconds) {
        this.freshSeconds = freshSeconds;
    }

    public /* synthetic */ AddonCacheControlInterceptor(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 300 : i);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        if (!StringsKt.equals(request.method(), "GET", true)) {
            return chain.proceed(request);
        }
        String header = request.header(HttpHeaders.CACHE_CONTROL);
        if (header == null) {
            header = "";
        }
        if (StringsKt.contains$default((CharSequence) header, (CharSequence) "no-store", false, 2, (Object) null)) {
            return chain.proceed(request);
        }
        CacheControl fresh = new CacheControl.Builder().maxAge(this.freshSeconds, TimeUnit.SECONDS).build();
        Response response = chain.proceed(request.newBuilder().cacheControl(fresh).build());
        CacheControl existing = response.cacheControl();
        boolean usable = !existing.noStore() && existing.maxAgeSeconds() > 0;
        if (usable) {
            return response;
        }
        return response.newBuilder().header(HttpHeaders.CACHE_CONTROL, fresh.toString()).removeHeader(HttpHeaders.PRAGMA).build();
    }
}
