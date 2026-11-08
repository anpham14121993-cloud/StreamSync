package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

/* compiled from: AddonClient.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/data/addon/AddonHttpApi;", "", "getCatalog", "Lcom/streamsync/app/data/addon/CatalogResponse;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getManifest", "Lcom/streamsync/app/data/addon/AddonManifest;", "getMeta", "Lretrofit2/Response;", "Lcom/streamsync/app/data/addon/MetaResponse;", "getStream", "Lcom/streamsync/app/data/addon/StreamResponse;", "getSubtitles", "Lcom/streamsync/app/data/addon/SubtitleResponse;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
interface AddonHttpApi {
    @GET
    Object getCatalog(@Url String str, Continuation<? super CatalogResponse> continuation);

    @GET
    Object getManifest(@Url String str, Continuation<? super AddonManifest> continuation);

    @GET
    Object getMeta(@Url String str, Continuation<? super Response<MetaResponse>> continuation);

    @GET
    Object getStream(@Url String str, Continuation<? super StreamResponse> continuation);

    @GET
    Object getSubtitles(@Url String str, Continuation<? super SubtitleResponse> continuation);
}
