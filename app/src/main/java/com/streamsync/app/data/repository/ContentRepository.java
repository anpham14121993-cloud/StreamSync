package com.streamsync.app.data.repository;

import com.google.android.gms.actions.SearchIntents;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.remote.RetrofitClient;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: ContentRepository.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0086@¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\bJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\b¨\u0006\r"}, d2 = {"Lcom/streamsync/app/data/repository/ContentRepository;", "", "()V", "getCatalog", "", "Lcom/streamsync/app/data/model/MediaItem;", SearchIntents.EXTRA_QUERY, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDetail", "id", "getStreamSource", "Lcom/streamsync/app/data/model/StreamSource;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class ContentRepository {
    public static final int $stable = 0;

    public static /* synthetic */ Object getCatalog$default(ContentRepository contentRepository, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return contentRepository.getCatalog(str, continuation);
    }

    public final Object getCatalog(String query, Continuation<? super List<MediaItem>> continuation) {
        return RetrofitClient.INSTANCE.getContentApi().getCatalog(query, continuation);
    }

    public final Object getDetail(String id, Continuation<? super MediaItem> continuation) {
        return RetrofitClient.INSTANCE.getContentApi().getMediaDetail(id, continuation);
    }

    public final Object getStreamSource(String id, Continuation<? super StreamSource> continuation) {
        return RetrofitClient.INSTANCE.getContentApi().getStreamSource(id, continuation);
    }
}
