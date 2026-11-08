package com.streamsync.app.data.addon;

import android.content.Context;
import androidx.media3.common.MimeTypes;
import com.google.android.gms.actions.SearchIntents;
import com.streamsync.app.data.remote.AddonCacheControlInterceptor;
import com.streamsync.app.data.remote.NetTuning;
import com.streamsync.app.data.remote.OkHttpCacheDir;
import com.streamsync.app.data.remote.RetrofitClient;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: AddonClient.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u001eJ.\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!H\u0086@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010%J(\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u001eJ4\u0010)\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010'\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u001eJ&\u0010,\u001a\u00020-2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u001eJ,\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u001eJ\u0010\u00102\u001a\u0004\u0018\u00010\u00042\u0006\u00103\u001a\u00020\u0004J\u0018\u00104\u001a\u0004\u0018\u00010\u00042\u0006\u00103\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010%J\u0018\u00105\u001a\u0004\u0018\u00010\u00042\u0006\u00103\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010%J.\u00106\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u00108J\u000e\u00109\u001a\u0004\u0018\u00010:*\u00020:H\u0002J\u0014\u00109\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010;\u001a\u00020\u0004H\u0002J\f\u00109\u001a\u00020'*\u00020'H\u0002J\u000e\u00109\u001a\u0004\u0018\u00010<*\u00020<H\u0002J\f\u00109\u001a\u00020\u0019*\u00020\u0019H\u0002J\f\u00109\u001a\u00020-*\u00020-H\u0002J\u0018\u00109\u001a\b\u0012\u0004\u0012\u0002000/*\b\u0012\u0004\u0012\u0002000/H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0011\u0010\u000eR\u001b\u0010\u0013\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0014\u0010\bR\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/streamsync/app/data/addon/AddonClient;", "", "()V", "MIME_UNKNOWN", "", "addonClient", "Lokhttp3/OkHttpClient;", "getAddonClient", "()Lokhttp3/OkHttpClient;", "addonClient$delegate", "Lkotlin/Lazy;", "api", "Lcom/streamsync/app/data/addon/AddonHttpApi;", "getApi", "()Lcom/streamsync/app/data/addon/AddonHttpApi;", "api$delegate", "probeApi", "getProbeApi", "probeApi$delegate", "probeClient", "getProbeClient", "probeClient$delegate", "probeMimeCache", "Ljava/util/concurrent/ConcurrentHashMap;", "fetchCatalog", "Lcom/streamsync/app/data/addon/CatalogResponse;", "addon", "Lcom/streamsync/app/data/addon/AddonManifest;", "type", "catalogId", "(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCatalogPage", "skip", "", "(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchManifest", "inputUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMeta", "Lcom/streamsync/app/data/addon/AddonMeta;", "itemId", "fetchMetaWithHeaders", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/CacheDirective;", "fetchStream", "Lcom/streamsync/app/data/addon/StreamResponse;", "fetchSubtitles", "", "Lcom/streamsync/app/data/addon/AddonSubtitle;", "id", "guessMimeTypeFromUrl", "url", "probeMimeType", "probeMimeTypeCached", "searchCatalog", SearchIntents.EXTRA_QUERY, "(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "normalized", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "baseUrl", "Lcom/streamsync/app/data/addon/AddonResourceDef;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonClient {
    private static final String MIME_UNKNOWN = "\u0000no-mime";
    public static final AddonClient INSTANCE = new AddonClient();

    /* renamed from: addonClient$delegate, reason: from kotlin metadata */
    private static final Lazy addonClient = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: com.streamsync.app.data.addon.AddonClient$addonClient$2
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            OkHttpClient.Builder $this$invoke_u24lambda_u241 = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).callTimeout(60L, TimeUnit.SECONDS).cache(new Cache(OkHttpCacheDir.INSTANCE.addon(null), 33554432L)).addInterceptor(new AddonCacheControlInterceptor(0, 1, null)).addInterceptor(new AddonRateLimitInterceptor());
            Context it = RetrofitClient.INSTANCE.getAppContextOrNull();
            if (it != null) {
                NetTuning.INSTANCE.apply(it, $this$invoke_u24lambda_u241);
            }
            return $this$invoke_u24lambda_u241.build();
        }
    });

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api = LazyKt.lazy(new Function0<AddonHttpApi>() { // from class: com.streamsync.app.data.addon.AddonClient$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AddonHttpApi invoke() {
            OkHttpClient addonClient2;
            Retrofit.Builder addConverterFactory = new Retrofit.Builder().baseUrl("https://placeholder.invalid/").addConverterFactory(GsonConverterFactory.create());
            addonClient2 = AddonClient.INSTANCE.getAddonClient();
            return (AddonHttpApi) addConverterFactory.client(addonClient2).build().create(AddonHttpApi.class);
        }
    });

    /* renamed from: probeClient$delegate, reason: from kotlin metadata */
    private static final Lazy probeClient = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: com.streamsync.app.data.addon.AddonClient$probeClient$2
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            return new OkHttpClient.Builder().connectTimeout(2L, TimeUnit.SECONDS).readTimeout(2L, TimeUnit.SECONDS).callTimeout(3L, TimeUnit.SECONDS).build();
        }
    });

    /* renamed from: probeApi$delegate, reason: from kotlin metadata */
    private static final Lazy probeApi = LazyKt.lazy(new Function0<AddonHttpApi>() { // from class: com.streamsync.app.data.addon.AddonClient$probeApi$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AddonHttpApi invoke() {
            OkHttpClient probeClient2;
            Retrofit.Builder addConverterFactory = new Retrofit.Builder().baseUrl("https://placeholder.invalid/").addConverterFactory(GsonConverterFactory.create());
            probeClient2 = AddonClient.INSTANCE.getProbeClient();
            return (AddonHttpApi) addConverterFactory.client(probeClient2).build().create(AddonHttpApi.class);
        }
    });
    private static final ConcurrentHashMap<String, String> probeMimeCache = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private AddonClient() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getAddonClient() {
        return (OkHttpClient) addonClient.getValue();
    }

    private final AddonHttpApi getApi() {
        Object value = api.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (AddonHttpApi) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getProbeClient() {
        return (OkHttpClient) probeClient.getValue();
    }

    private final AddonHttpApi getProbeApi() {
        Object value = probeApi.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (AddonHttpApi) value;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0025. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object probeMimeTypeCached(java.lang.String r9, kotlin.coroutines.Continuation<? super java.lang.String> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.streamsync.app.data.addon.AddonClient$probeMimeTypeCached$1
            if (r0 == 0) goto L14
            r0 = r10
            com.streamsync.app.data.addon.AddonClient$probeMimeTypeCached$1 r0 = (com.streamsync.app.data.addon.AddonClient$probeMimeTypeCached$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$probeMimeTypeCached$1 r0 = new com.streamsync.app.data.addon.AddonClient$probeMimeTypeCached$1
            r0.<init>(r8, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            r3 = 0
            java.lang.String r4 = "\u0000no-mime"
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L30;
                default: goto L28;
            }
        L28:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L30:
            r9 = 0
            java.lang.Object r1 = r10.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3a
            r6 = r0
            goto L6e
        L3a:
            r9 = move-exception
            goto L79
        L3c:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r8
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r5 = com.streamsync.app.data.addon.AddonClient.probeMimeCache
            boolean r5 = r5.containsKey(r9)
            if (r5 == 0) goto L5a
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r1 = com.streamsync.app.data.addon.AddonClient.probeMimeCache
            java.lang.Object r1 = r1.get(r9)
            r9 = r1
            java.lang.String r9 = (java.lang.String) r9
            r2 = 0
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r4)
            if (r9 != 0) goto L59
            r3 = r1
        L59:
            return r3
        L5a:
            kotlin.Result$Companion r5 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L75
            com.streamsync.app.data.addon.AddonClient r2 = (com.streamsync.app.data.addon.AddonClient) r2     // Catch: java.lang.Throwable -> L75
            r5 = 0
            r10.L$0 = r9     // Catch: java.lang.Throwable -> L75
            r6 = 1
            r10.label = r6     // Catch: java.lang.Throwable -> L75
            java.lang.Object r6 = r2.probeMimeType(r9, r10)     // Catch: java.lang.Throwable -> L75
            if (r6 != r1) goto L6c
            return r1
        L6c:
            r1 = r9
            r9 = r5
        L6e:
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Throwable -> L3a
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r6)     // Catch: java.lang.Throwable -> L3a
            goto L83
        L75:
            r1 = move-exception
            r7 = r1
            r1 = r9
            r9 = r7
        L79:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r9)
        L83:
            boolean r2 = kotlin.Result.m6723isFailureimpl(r9)
            if (r2 == 0) goto L8a
            goto L8b
        L8a:
            r3 = r9
        L8b:
            r9 = r3
            java.lang.String r9 = (java.lang.String) r9
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = com.streamsync.app.data.addon.AddonClient.probeMimeCache
            int r2 = r2.size()
            r3 = 256(0x100, float:3.59E-43)
            if (r2 < r3) goto L9d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = com.streamsync.app.data.addon.AddonClient.probeMimeCache
            r2.clear()
        L9d:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = com.streamsync.app.data.addon.AddonClient.probeMimeCache
            java.util.Map r2 = (java.util.Map) r2
            if (r9 != 0) goto La4
            goto La5
        La4:
            r4 = r9
        La5:
            r2.put(r1, r4)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.probeMimeTypeCached(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchManifest(java.lang.String r7, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.AddonManifest> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.streamsync.app.data.addon.AddonClient$fetchManifest$1
            if (r0 == 0) goto L14
            r0 = r8
            com.streamsync.app.data.addon.AddonClient$fetchManifest$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchManifest$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchManifest$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchManifest$1
            r0.<init>(r6, r8)
        L19:
            r8 = r0
            java.lang.Object r0 = r8.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L3a;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            java.lang.Object r7 = r8.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r1 = r8.L$0
            com.streamsync.app.data.addon.AddonClient r1 = (com.streamsync.app.data.addon.AddonClient) r1
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r0
            goto L68
        L3a:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r6
            java.lang.String r7 = com.streamsync.app.data.addon.AddonClientKt.normalizeAddonUrl(r7)
            com.streamsync.app.data.addon.AddonHttpApi r3 = r2.getApi()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.String r5 = "/manifest.json"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r8.L$0 = r2
            r8.L$1 = r7
            r5 = 1
            r8.label = r5
            java.lang.Object r3 = r3.getManifest(r4, r8)
            if (r3 != r1) goto L67
            return r1
        L67:
            r1 = r2
        L68:
            r2 = r3
            com.streamsync.app.data.addon.AddonManifest r2 = (com.streamsync.app.data.addon.AddonManifest) r2
            com.streamsync.app.data.addon.AddonManifest r3 = r1.normalized(r2, r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchManifest(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchCatalog(com.streamsync.app.data.addon.AddonManifest r7, java.lang.String r8, java.lang.String r9, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.CatalogResponse> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.streamsync.app.data.addon.AddonClient$fetchCatalog$1
            if (r0 == 0) goto L14
            r0 = r10
            com.streamsync.app.data.addon.AddonClient$fetchCatalog$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchCatalog$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchCatalog$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchCatalog$1
            r0.<init>(r6, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            java.lang.Object r7 = r10.L$0
            com.streamsync.app.data.addon.AddonClient r7 = (com.streamsync.app.data.addon.AddonClient) r7
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r0
            goto L77
        L36:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r6
            com.streamsync.app.data.addon.AddonHttpApi r3 = r2.getApi()
            java.lang.String r4 = r7.getBaseUrl()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = "/catalog/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r5 = "/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.String r5 = ".json"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r10.L$0 = r2
            r5 = 1
            r10.label = r5
            java.lang.Object r7 = r3.getCatalog(r4, r10)
            if (r7 != r1) goto L75
            return r1
        L75:
            r8 = r7
            r7 = r2
        L77:
            com.streamsync.app.data.addon.CatalogResponse r8 = (com.streamsync.app.data.addon.CatalogResponse) r8
            com.streamsync.app.data.addon.CatalogResponse r7 = r7.normalized(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchCatalog(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r7v4, types: [com.streamsync.app.data.addon.AddonHttpApi, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchCatalogPage(com.streamsync.app.data.addon.AddonManifest r6, java.lang.String r7, java.lang.String r8, int r9, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.CatalogResponse> r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof com.streamsync.app.data.addon.AddonClient$fetchCatalogPage$1
            if (r0 == 0) goto L14
            r0 = r10
            com.streamsync.app.data.addon.AddonClient$fetchCatalogPage$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchCatalogPage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchCatalogPage$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchCatalogPage$1
            r0.<init>(r5, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            java.lang.Object r6 = r10.L$0
            com.streamsync.app.data.addon.AddonClient r6 = (com.streamsync.app.data.addon.AddonClient) r6
            kotlin.ResultKt.throwOnFailure(r0)
            r7 = r0
            goto L99
        L36:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r5
            java.lang.String r3 = r6.getBaseUrl()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = "/catalog/"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r4 = "/"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r8)
            java.lang.String r6 = r3.toString()
            java.lang.String r7 = ".json"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            if (r9 > 0) goto L6d
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r6)
            goto L7e
        L6d:
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r3 = "/skip="
            java.lang.StringBuilder r8 = r8.append(r3)
            java.lang.StringBuilder r8 = r8.append(r9)
        L7e:
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            r6 = r7
            com.streamsync.app.data.addon.AddonHttpApi r7 = r2.getApi()
            r10.L$0 = r2
            r8 = 1
            r10.label = r8
            java.lang.Object r6 = r7.getCatalog(r6, r10)
            if (r6 != r1) goto L97
            return r1
        L97:
            r7 = r6
            r6 = r2
        L99:
            com.streamsync.app.data.addon.CatalogResponse r7 = (com.streamsync.app.data.addon.CatalogResponse) r7
            com.streamsync.app.data.addon.CatalogResponse r6 = r6.normalized(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchCatalogPage(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchCatalog(com.streamsync.app.data.addon.AddonManifest r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.CatalogResponse> r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof com.streamsync.app.data.addon.AddonClient$searchCatalog$1
            if (r0 == 0) goto L14
            r0 = r10
            com.streamsync.app.data.addon.AddonClient$searchCatalog$1 r0 = (com.streamsync.app.data.addon.AddonClient$searchCatalog$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$searchCatalog$1 r0 = new com.streamsync.app.data.addon.AddonClient$searchCatalog$1
            r0.<init>(r5, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            java.lang.Object r6 = r10.L$0
            com.streamsync.app.data.addon.AddonClient r6 = (com.streamsync.app.data.addon.AddonClient) r6
            kotlin.ResultKt.throwOnFailure(r0)
            r7 = r0
            goto L91
        L36:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r5
            com.streamsync.app.data.addon.AddonHttpApi r3 = r2.getApi()
            java.lang.String r4 = r6.getBaseUrl()
            r6 = r9
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.CharSequence r6 = kotlin.text.StringsKt.trim(r6)
            java.lang.String r6 = r6.toString()
            java.lang.String r6 = com.streamsync.app.data.addon.AddonClientKt.pathEncode(r6)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.StringBuilder r9 = r9.append(r4)
            java.lang.String r4 = "/catalog/"
            java.lang.StringBuilder r9 = r9.append(r4)
            java.lang.StringBuilder r7 = r9.append(r7)
            java.lang.String r9 = "/"
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = "/search="
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r7 = ".json"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r10.L$0 = r2
            r7 = 1
            r10.label = r7
            java.lang.Object r6 = r3.getCatalog(r6, r10)
            if (r6 != r1) goto L8f
            return r1
        L8f:
            r7 = r6
            r6 = r2
        L91:
            com.streamsync.app.data.addon.CatalogResponse r7 = (com.streamsync.app.data.addon.CatalogResponse) r7
            com.streamsync.app.data.addon.CatalogResponse r6 = r6.normalized(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.searchCatalog(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchSubtitles(com.streamsync.app.data.addon.AddonManifest r8, java.lang.String r9, java.lang.String r10, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.addon.AddonSubtitle>> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof com.streamsync.app.data.addon.AddonClient$fetchSubtitles$1
            if (r0 == 0) goto L14
            r0 = r11
            com.streamsync.app.data.addon.AddonClient$fetchSubtitles$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchSubtitles$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchSubtitles$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchSubtitles$1
            r0.<init>(r7, r11)
        L19:
            r11 = r0
            java.lang.Object r0 = r11.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            switch(r2) {
                case 0: goto L3a;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            r8 = 0
            java.lang.Object r9 = r11.L$0
            com.streamsync.app.data.addon.AddonClient r9 = (com.streamsync.app.data.addon.AddonClient) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L38
            r4 = r8
            r8 = r0
            goto L91
        L38:
            r8 = move-exception
            goto L9a
        L3a:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r7
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L98
            r3 = r2
            com.streamsync.app.data.addon.AddonClient r3 = (com.streamsync.app.data.addon.AddonClient) r3     // Catch: java.lang.Throwable -> L98
            r4 = 0
            com.streamsync.app.data.addon.AddonHttpApi r5 = r3.getApi()     // Catch: java.lang.Throwable -> L98
            java.lang.String r6 = r8.getBaseUrl()     // Catch: java.lang.Throwable -> L98
            r8 = r10
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch: java.lang.Throwable -> L98
            java.lang.CharSequence r8 = kotlin.text.StringsKt.trim(r8)     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = com.streamsync.app.data.addon.AddonClientKt.pathEncode(r8)     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
            r10.<init>()     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r10 = r10.append(r6)     // Catch: java.lang.Throwable -> L98
            java.lang.String r3 = "/subtitles/"
            java.lang.StringBuilder r10 = r10.append(r3)     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r9 = r10.append(r9)     // Catch: java.lang.Throwable -> L98
            java.lang.String r10 = "/"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r8 = r9.append(r8)     // Catch: java.lang.Throwable -> L98
            java.lang.String r9 = ".json"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> L98
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L98
            r11.L$0 = r2     // Catch: java.lang.Throwable -> L98
            r9 = 1
            r11.label = r9     // Catch: java.lang.Throwable -> L98
            java.lang.Object r8 = r5.getSubtitles(r8, r11)     // Catch: java.lang.Throwable -> L98
            if (r8 != r1) goto L90
            return r1
        L90:
            r9 = r2
        L91:
            com.streamsync.app.data.addon.SubtitleResponse r8 = (com.streamsync.app.data.addon.SubtitleResponse) r8     // Catch: java.lang.Throwable -> L38
            java.lang.Object r8 = kotlin.Result.m6717constructorimpl(r8)     // Catch: java.lang.Throwable -> L38
            goto La4
        L98:
            r8 = move-exception
            r9 = r2
        L9a:
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m6717constructorimpl(r8)
        La4:
            boolean r10 = kotlin.Result.m6723isFailureimpl(r8)
            r1 = 0
            if (r10 == 0) goto Lac
            r8 = r1
        Lac:
            com.streamsync.app.data.addon.SubtitleResponse r8 = (com.streamsync.app.data.addon.SubtitleResponse) r8
            if (r8 == 0) goto Lb4
            java.util.List r1 = r8.getSubtitles()
        Lb4:
            if (r1 != 0) goto Lba
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        Lba:
            java.util.List r8 = r9.normalized(r1)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchSubtitles(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchStream(com.streamsync.app.data.addon.AddonManifest r7, java.lang.String r8, java.lang.String r9, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.StreamResponse> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.streamsync.app.data.addon.AddonClient$fetchStream$1
            if (r0 == 0) goto L14
            r0 = r10
            com.streamsync.app.data.addon.AddonClient$fetchStream$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchStream$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchStream$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchStream$1
            r0.<init>(r6, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            java.lang.Object r7 = r10.L$0
            com.streamsync.app.data.addon.AddonClient r7 = (com.streamsync.app.data.addon.AddonClient) r7
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r0
            goto L77
        L36:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r6
            com.streamsync.app.data.addon.AddonHttpApi r3 = r2.getApi()
            java.lang.String r4 = r7.getBaseUrl()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = "/stream/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r5 = "/"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.String r5 = ".json"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r10.L$0 = r2
            r5 = 1
            r10.label = r5
            java.lang.Object r7 = r3.getStream(r4, r10)
            if (r7 != r1) goto L75
            return r1
        L75:
            r8 = r7
            r7 = r2
        L77:
            com.streamsync.app.data.addon.StreamResponse r8 = (com.streamsync.app.data.addon.StreamResponse) r8
            com.streamsync.app.data.addon.StreamResponse r7 = r7.normalized(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchStream(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchMeta(com.streamsync.app.data.addon.AddonManifest r5, java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.AddonMeta> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.streamsync.app.data.addon.AddonClient$fetchMeta$1
            if (r0 == 0) goto L14
            r0 = r8
            com.streamsync.app.data.addon.AddonClient$fetchMeta$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchMeta$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchMeta$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchMeta$1
            r0.<init>(r4, r8)
        L19:
            r8 = r0
            java.lang.Object r0 = r8.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L32;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r0)
            r5 = r0
            goto L40
        L32:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r4
            r3 = 1
            r8.label = r3
            java.lang.Object r5 = r2.fetchMetaWithHeaders(r5, r6, r7, r8)
            if (r5 != r1) goto L40
            return r1
        L40:
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r5 = r5.getFirst()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchMeta(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(2:3|(11:5|6|7|8|15|(1:30)(1:19)|20|21|(1:23)|24|(2:26|27)(1:28)))|38|6|7|8|15|(1:17)|30|20|21|(0)|24|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00af, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
    
        r10 = kotlin.Result.INSTANCE;
        r9 = kotlin.Result.m6717constructorimpl(kotlin.ResultKt.createFailure(r9));
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchMetaWithHeaders(com.streamsync.app.data.addon.AddonManifest r9, java.lang.String r10, java.lang.String r11, kotlin.coroutines.Continuation<? super kotlin.Pair<com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.CacheDirective>> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof com.streamsync.app.data.addon.AddonClient$fetchMetaWithHeaders$1
            if (r0 == 0) goto L14
            r0 = r12
            com.streamsync.app.data.addon.AddonClient$fetchMetaWithHeaders$1 r0 = (com.streamsync.app.data.addon.AddonClient$fetchMetaWithHeaders$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$fetchMetaWithHeaders$1 r0 = new com.streamsync.app.data.addon.AddonClient$fetchMetaWithHeaders$1
            r0.<init>(r8, r12)
        L19:
            r12 = r0
            java.lang.Object r0 = r12.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r12.label
            r3 = 0
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2e:
            r9 = 0
            java.lang.Object r10 = r12.L$0
            com.streamsync.app.data.addon.AddonClient r10 = (com.streamsync.app.data.addon.AddonClient) r10
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> Laf
            r5 = r0
            goto L7f
        L38:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r8
            kotlin.Result$Companion r4 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> Laf
            com.streamsync.app.data.addon.AddonClient r2 = (com.streamsync.app.data.addon.AddonClient) r2     // Catch: java.lang.Throwable -> Laf
            r4 = 0
            com.streamsync.app.data.addon.AddonHttpApi r5 = r2.getApi()     // Catch: java.lang.Throwable -> Laf
            java.lang.String r6 = r9.getBaseUrl()     // Catch: java.lang.Throwable -> Laf
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Laf
            r7.<init>()     // Catch: java.lang.Throwable -> Laf
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r7 = "/meta/"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> Laf
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r7 = "/"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> Laf
            java.lang.StringBuilder r6 = r6.append(r11)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r7 = ".json"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Laf
            r12.L$0 = r2     // Catch: java.lang.Throwable -> Laf
            r7 = 1
            r12.label = r7     // Catch: java.lang.Throwable -> Laf
            java.lang.Object r5 = r5.getMeta(r6, r12)     // Catch: java.lang.Throwable -> Laf
            if (r5 != r1) goto L7d
            return r1
        L7d:
            r10 = r2
            r9 = r4
        L7f:
            retrofit2.Response r5 = (retrofit2.Response) r5     // Catch: java.lang.Throwable -> Laf
            r11 = r5
            java.lang.Object r1 = r11.body()     // Catch: java.lang.Throwable -> Laf
            com.streamsync.app.data.addon.MetaResponse r1 = (com.streamsync.app.data.addon.MetaResponse) r1     // Catch: java.lang.Throwable -> Laf
            if (r1 == 0) goto L95
            com.streamsync.app.data.addon.AddonMeta r1 = r1.mergedMeta()     // Catch: java.lang.Throwable -> Laf
            if (r1 == 0) goto L95
            com.streamsync.app.data.addon.AddonMeta r1 = r10.normalized(r1)     // Catch: java.lang.Throwable -> Laf
            goto L96
        L95:
            r1 = r3
        L96:
            com.streamsync.app.data.addon.CacheDirective$Companion r10 = com.streamsync.app.data.addon.CacheDirective.INSTANCE     // Catch: java.lang.Throwable -> Laf
            okhttp3.Headers r2 = r11.headers()     // Catch: java.lang.Throwable -> Laf
            java.lang.String r4 = "Cache-Control"
            java.lang.String r2 = r2.get(r4)     // Catch: java.lang.Throwable -> Laf
            com.streamsync.app.data.addon.CacheDirective r10 = r10.parse(r2)     // Catch: java.lang.Throwable -> Laf
            kotlin.Pair r10 = kotlin.TuplesKt.to(r1, r10)     // Catch: java.lang.Throwable -> Laf
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r10)     // Catch: java.lang.Throwable -> Laf
            goto Lba
        Laf:
            r9 = move-exception
            kotlin.Result$Companion r10 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r9)
        Lba:
            boolean r10 = kotlin.Result.m6723isFailureimpl(r9)
            if (r10 == 0) goto Lc1
            r9 = r3
        Lc1:
            kotlin.Pair r9 = (kotlin.Pair) r9
            if (r9 != 0) goto Lcf
            com.streamsync.app.data.addon.CacheDirective$Companion r9 = com.streamsync.app.data.addon.CacheDirective.INSTANCE
            com.streamsync.app.data.addon.CacheDirective r9 = r9.getDEFAULT()
            kotlin.Pair r9 = kotlin.TuplesKt.to(r3, r9)
        Lcf:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.fetchMetaWithHeaders(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(2:3|(7:5|6|7|8|15|16|(1:20)(2:18|19)))|29|6|7|8|15|16|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0072, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
    
        r1 = kotlin.Result.INSTANCE;
        r9 = kotlin.Result.m6717constructorimpl(kotlin.ResultKt.createFailure(r9));
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object probeMimeType(java.lang.String r9, kotlin.coroutines.Continuation<? super java.lang.String> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.streamsync.app.data.addon.AddonClient$probeMimeType$1
            if (r0 == 0) goto L14
            r0 = r10
            com.streamsync.app.data.addon.AddonClient$probeMimeType$1 r0 = (com.streamsync.app.data.addon.AddonClient$probeMimeType$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            com.streamsync.app.data.addon.AddonClient$probeMimeType$1 r0 = new com.streamsync.app.data.addon.AddonClient$probeMimeType$1
            r0.<init>(r8, r10)
        L19:
            r10 = r0
            java.lang.Object r0 = r10.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r10.label
            r3 = 0
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2e:
            r9 = 0
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L72
            r5 = r0
            goto L6b
        L34:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r8
            kotlin.Result$Companion r4 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L72
            com.streamsync.app.data.addon.AddonClient r2 = (com.streamsync.app.data.addon.AddonClient) r2     // Catch: java.lang.Throwable -> L72
            r4 = 0
            okhttp3.Request$Builder r5 = new okhttp3.Request$Builder     // Catch: java.lang.Throwable -> L72
            r5.<init>()     // Catch: java.lang.Throwable -> L72
            okhttp3.Request$Builder r5 = r5.url(r9)     // Catch: java.lang.Throwable -> L72
            java.lang.String r9 = "Range"
            java.lang.String r6 = "bytes=0-0"
            okhttp3.Request$Builder r9 = r5.header(r9, r6)     // Catch: java.lang.Throwable -> L72
            okhttp3.Request r9 = r9.build()     // Catch: java.lang.Throwable -> L72
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Throwable -> L72
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5     // Catch: java.lang.Throwable -> L72
            com.streamsync.app.data.addon.AddonClient$probeMimeType$2$1 r6 = new com.streamsync.app.data.addon.AddonClient$probeMimeType$2$1     // Catch: java.lang.Throwable -> L72
            r6.<init>(r2, r9, r3)     // Catch: java.lang.Throwable -> L72
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6     // Catch: java.lang.Throwable -> L72
            r7 = 1
            r10.label = r7     // Catch: java.lang.Throwable -> L72
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r6, r10)     // Catch: java.lang.Throwable -> L72
            if (r5 != r1) goto L6a
            return r1
        L6a:
            r9 = r4
        L6b:
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L72
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r5)     // Catch: java.lang.Throwable -> L72
            goto L7d
        L72:
            r9 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r9)
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r9)
        L7d:
            boolean r1 = kotlin.Result.m6723isFailureimpl(r9)
            if (r1 == 0) goto L84
            goto L85
        L84:
            r3 = r9
        L85:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.probeMimeType(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String guessMimeTypeFromUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        String path = StringsKt.substringBefore$default(StringsKt.substringBefore$default(url, '?', (String) null, 2, (Object) null), '#', (String) null, 2, (Object) null);
        String last = StringsKt.substringAfterLast$default(path, '/', (String) null, 2, (Object) null);
        int dot = StringsKt.lastIndexOf$default((CharSequence) last, '.', 0, false, 6, (Object) null);
        if (dot <= 0 || dot == last.length() - 1) {
            return null;
        }
        String substring = last.substring(dot + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String ext = substring.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(ext, "toLowerCase(...)");
        switch (ext.hashCode()) {
            case 108184:
                if (ext.equals("mkv")) {
                    return MimeTypes.VIDEO_MATROSKA;
                }
                return null;
            case 108321:
                if (ext.equals("mpd")) {
                    return MimeTypes.APPLICATION_MPD;
                }
                return null;
            case 3299913:
                if (ext.equals("m3u8")) {
                    return MimeTypes.APPLICATION_M3U8;
                }
                return null;
            case 3645337:
                if (ext.equals("webm")) {
                    return MimeTypes.VIDEO_WEBM;
                }
                return null;
            default:
                return null;
        }
    }

    private final AddonManifest normalized(AddonManifest $this$normalized, String baseUrl) {
        AddonManifest copy;
        List<String> types = $this$normalized.getTypes();
        if (types == null) {
            types = CollectionsKt.emptyList();
        }
        List<String> list = types;
        Iterable catalogs = $this$normalized.getCatalogs();
        if (catalogs == null) {
            catalogs = CollectionsKt.emptyList();
        }
        Iterable $this$mapNotNull$iv = catalogs;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            AddonCatalogDef it = INSTANCE.normalized((AddonCatalogDef) element$iv$iv$iv);
            if (it != null) {
                destination$iv$iv.add(it);
            }
        }
        ArrayList arrayList = (List) destination$iv$iv;
        Iterable resources = $this$normalized.getResources();
        if (resources == null) {
            resources = CollectionsKt.emptyList();
        }
        Iterable $this$mapNotNull$iv2 = resources;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv$iv2 : $this$mapNotNull$iv2) {
            AddonResourceDef it2 = INSTANCE.normalized((AddonResourceDef) element$iv$iv$iv2);
            if (it2 != null) {
                destination$iv$iv2.add(it2);
            }
        }
        ArrayList arrayList2 = (List) destination$iv$iv2;
        List<String> idPrefixes = $this$normalized.getIdPrefixes();
        if (idPrefixes == null) {
            idPrefixes = CollectionsKt.emptyList();
        }
        copy = $this$normalized.copy((r24 & 1) != 0 ? $this$normalized.id : null, (r24 & 2) != 0 ? $this$normalized.name : null, (r24 & 4) != 0 ? $this$normalized.version : null, (r24 & 8) != 0 ? $this$normalized.description : null, (r24 & 16) != 0 ? $this$normalized.types : list, (r24 & 32) != 0 ? $this$normalized.catalogs : arrayList, (r24 & 64) != 0 ? $this$normalized.resources : arrayList2, (r24 & 128) != 0 ? $this$normalized.idPrefixes : idPrefixes, (r24 & 256) != 0 ? $this$normalized.logo : null, (r24 & 512) != 0 ? $this$normalized.sortOrder : 0, (r24 & 1024) != 0 ? $this$normalized.baseUrl : baseUrl);
        return copy;
    }

    private final AddonResourceDef normalized(AddonResourceDef $this$normalized) {
        String n = StringsKt.trim((CharSequence) $this$normalized.getName()).toString();
        if (n.length() == 0) {
            return null;
        }
        Iterable $this$map$iv = $this$normalized.getTypes();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        ArrayList arrayList = (List) destination$iv$iv2;
        Iterable $this$filter$iv2 = $this$normalized.getIdPrefixes();
        Iterable $this$map$iv2 = $this$filter$iv2;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            String it3 = (String) item$iv$iv2;
            destination$iv$iv3.add(StringsKt.trim((CharSequence) it3).toString());
        }
        Iterable $this$filter$iv3 = (List) destination$iv$iv3;
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv3) {
            String it4 = (String) element$iv$iv2;
            if (it4.length() > 0) {
                destination$iv$iv4.add(element$iv$iv2);
            }
        }
        return $this$normalized.copy(n, arrayList, (List) destination$iv$iv4);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.streamsync.app.data.addon.AddonSubtitle> normalized(java.util.List<com.streamsync.app.data.addon.AddonSubtitle> r25) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.normalized(java.util.List):java.util.List");
    }

    private final AddonCatalogDef normalized(AddonCatalogDef $this$normalized) {
        String it;
        String id;
        String it2;
        String str;
        String it3;
        String type = $this$normalized.getType();
        if (type != null && (it = StringsKt.trim((CharSequence) type).toString()) != null) {
            String type2 = it.length() > 0 ? it : null;
            if (type2 != null && (id = $this$normalized.getId()) != null && (it2 = StringsKt.trim((CharSequence) id).toString()) != null) {
                String id2 = it2.length() > 0 ? it2 : null;
                if (id2 != null) {
                    String name = $this$normalized.getName();
                    if (name != null && (it3 = StringsKt.trim((CharSequence) name).toString()) != null) {
                        String str2 = it3.length() > 0 ? it3 : null;
                        if (str2 != null) {
                            str = str2;
                            return AddonCatalogDef.copy$default($this$normalized, type2, id2, str, null, null, null, null, 120, null);
                        }
                    }
                    str = id2;
                    return AddonCatalogDef.copy$default($this$normalized, type2, id2, str, null, null, null, null, 120, null);
                }
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.streamsync.app.data.addon.CatalogResponse normalized(com.streamsync.app.data.addon.CatalogResponse r43) {
        /*
            Method dump skipped, instructions count: 375
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.normalized(com.streamsync.app.data.addon.CatalogResponse):com.streamsync.app.data.addon.CatalogResponse");
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0102 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0079 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.streamsync.app.data.addon.AddonMeta normalized(com.streamsync.app.data.addon.AddonMeta r32) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.normalized(com.streamsync.app.data.addon.AddonMeta):com.streamsync.app.data.addon.AddonMeta");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0117 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0150 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x001d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.streamsync.app.data.addon.StreamResponse normalized(com.streamsync.app.data.addon.StreamResponse r40) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClient.normalized(com.streamsync.app.data.addon.StreamResponse):com.streamsync.app.data.addon.StreamResponse");
    }
}
