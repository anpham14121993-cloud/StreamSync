package com.streamsync.app.data.repository;

import androidx.media3.extractor.ts.TsExtractor;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SyncRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.SyncRepository", f = "SyncRepository.kt", i = {0, 0, 0, 0, 0}, l = {TsExtractor.TS_SYNC_BYTE, 72}, m = "recordProgress", n = {"this", "mediaId", "stream", "positionMs", "durationMs"}, s = {"L$0", "L$1", "L$2", "J$0", "J$1"})
/* loaded from: classes21.dex */
public final class SyncRepository$recordProgress$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SyncRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncRepository$recordProgress$1(SyncRepository syncRepository, Continuation<? super SyncRepository$recordProgress$1> continuation) {
        super(continuation);
        this.this$0 = syncRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.recordProgress(null, 0L, 0L, null, this);
    }
}
