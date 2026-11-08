package com.streamsync.app.data.local;

import androidx.core.app.NotificationCompat;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: WatchProgressDao.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH§@¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\rH'J\u0016\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/local/WatchProgressDao;", "", "get", "Lcom/streamsync/app/data/local/WatchProgressEntity;", "mediaId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPendingSync", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markSynced", "", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "upsert", NotificationCompat.CATEGORY_PROGRESS, "(Lcom/streamsync/app/data/local/WatchProgressEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public interface WatchProgressDao {
    Object get(String str, Continuation<? super WatchProgressEntity> continuation);

    Object getPendingSync(Continuation<? super List<WatchProgressEntity>> continuation);

    Object markSynced(String str, Continuation<? super Unit> continuation);

    Flow<List<WatchProgressEntity>> observeAll();

    Object upsert(WatchProgressEntity watchProgressEntity, Continuation<? super Unit> continuation);
}
