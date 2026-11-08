package com.streamsync.app.data.local;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: LibraryDao.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH§@¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tH§@¢\u0006\u0002\u0010\nJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\rH'J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\rH'J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/local/LibraryDao;", "", "delete", "", "item", "Lcom/streamsync/app/data/local/LibraryItemEntity;", "(Lcom/streamsync/app/data/local/LibraryItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "mediaId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findId", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "observeIds", "upsert", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public interface LibraryDao {
    Object delete(LibraryItemEntity libraryItemEntity, Continuation<? super Unit> continuation);

    Object deleteById(String str, Continuation<? super Unit> continuation);

    Object findId(String str, Continuation<? super String> continuation);

    Flow<List<LibraryItemEntity>> observeAll();

    Flow<List<String>> observeIds();

    Object upsert(LibraryItemEntity libraryItemEntity, Continuation<? super Unit> continuation);
}
