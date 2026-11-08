package com.streamsync.app.data.remote;

import com.google.android.gms.actions.SearchIntents;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: ContentApi.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006H§@¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00042\b\b\u0001\u0010\t\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u0018\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\t\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/streamsync/app/data/remote/ContentApi;", "", "getCatalog", "", "Lcom/streamsync/app/data/model/MediaItem;", SearchIntents.EXTRA_QUERY, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaDetail", "id", "getStreamSource", "Lcom/streamsync/app/data/model/StreamSource;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public interface ContentApi {
    @GET("catalog")
    Object getCatalog(@Query("query") String str, Continuation<? super List<MediaItem>> continuation);

    @GET("media/{id}")
    Object getMediaDetail(@Path("id") String str, Continuation<? super MediaItem> continuation);

    @GET("media/{id}/stream")
    Object getStreamSource(@Path("id") String str, Continuation<? super StreamSource> continuation);

    /* compiled from: ContentApi.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ Object getCatalog$default(ContentApi contentApi, String str, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCatalog");
            }
            if ((i & 1) != 0) {
                str = null;
            }
            return contentApi.getCatalog(str, continuation);
        }
    }
}
