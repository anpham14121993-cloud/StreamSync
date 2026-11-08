package com.streamsync.app.data.remote;

import android.content.Context;
import com.streamsync.app.settings.BufferCeiling;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: RetrofitClient.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/streamsync/app/data/remote/RetrofitClient;", "", "()V", "CONTENT_BASE_URL", "", "SYNC_BASE_URL", "appContext", "Landroid/content/Context;", "appContextOrNull", "getAppContextOrNull", "()Landroid/content/Context;", "contentApi", "Lcom/streamsync/app/data/remote/ContentApi;", "getContentApi", "()Lcom/streamsync/app/data/remote/ContentApi;", "contentApi$delegate", "Lkotlin/Lazy;", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "okHttpClient$delegate", "init", "", "context", "retrofit", "Lretrofit2/Retrofit;", "baseUrl", "syncApi", "Lcom/streamsync/app/data/remote/SyncApi;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class RetrofitClient {
    private static final String CONTENT_BASE_URL = "https://your-content-api.example.com/";
    private static final String SYNC_BASE_URL = "https://your-sync-backend.example.com/";
    private static volatile Context appContext;
    public static final RetrofitClient INSTANCE = new RetrofitClient();

    /* renamed from: okHttpClient$delegate, reason: from kotlin metadata */
    private static final Lazy okHttpClient = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: com.streamsync.app.data.remote.RetrofitClient$okHttpClient$2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            Context context;
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, 0 == true ? 1 : 0);
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient.Builder readTimeout = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).connectTimeout(15L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS);
            context = RetrofitClient.appContext;
            if (context != null) {
                NetTuning.INSTANCE.apply(context, readTimeout);
            }
            return readTimeout.build();
        }
    });

    /* renamed from: contentApi$delegate, reason: from kotlin metadata */
    private static final Lazy contentApi = LazyKt.lazy(new Function0<ContentApi>() { // from class: com.streamsync.app.data.remote.RetrofitClient$contentApi$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ContentApi invoke() {
            Retrofit retrofit;
            retrofit = RetrofitClient.INSTANCE.retrofit("https://your-content-api.example.com/");
            return (ContentApi) retrofit.create(ContentApi.class);
        }
    });
    public static final int $stable = 8;

    private RetrofitClient() {
    }

    public final OkHttpClient getOkHttpClient() {
        return (OkHttpClient) okHttpClient.getValue();
    }

    public final Context getAppContextOrNull() {
        return appContext;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        appContext = context.getApplicationContext();
    }

    public final ContentApi getContentApi() {
        Object value = contentApi.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ContentApi) value;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
    
        if (r0 == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.streamsync.app.data.remote.SyncApi syncApi(java.lang.String r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L14
            r0 = r4
            r1 = 0
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            r2 = r2 ^ 1
            if (r2 == 0) goto L11
            r0 = r4
            goto L12
        L11:
            r0 = 0
        L12:
            if (r0 != 0) goto L16
        L14:
            java.lang.String r0 = "https://your-sync-backend.example.com/"
        L16:
            retrofit2.Retrofit r0 = r3.retrofit(r0)
            java.lang.Class<com.streamsync.app.data.remote.SyncApi> r1 = com.streamsync.app.data.remote.SyncApi.class
            java.lang.Object r0 = r0.create(r1)
            java.lang.String r1 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.streamsync.app.data.remote.SyncApi r0 = (com.streamsync.app.data.remote.SyncApi) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.remote.RetrofitClient.syncApi(java.lang.String):com.streamsync.app.data.remote.SyncApi");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Retrofit retrofit(String baseUrl) {
        Retrofit build = new Retrofit.Builder().baseUrl(baseUrl).client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }
}
