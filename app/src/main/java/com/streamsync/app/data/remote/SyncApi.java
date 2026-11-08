package com.streamsync.app.data.remote;

import androidx.core.app.NotificationCompat;
import com.streamsync.app.data.model.WatchProgress;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* compiled from: SyncApi.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/streamsync/app/data/remote/SyncApi;", "", "pullProgress", "", "Lcom/streamsync/app/data/model/WatchProgress;", "sinceEpochMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pushProgress", "", NotificationCompat.CATEGORY_PROGRESS, "(Lcom/streamsync/app/data/model/WatchProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public interface SyncApi {
    @GET("sync/progress")
    Object pullProgress(@Query("since") long j, Continuation<? super List<WatchProgress>> continuation);

    @POST("sync/progress")
    Object pushProgress(@Body WatchProgress watchProgress, Continuation<? super Unit> continuation);
}
