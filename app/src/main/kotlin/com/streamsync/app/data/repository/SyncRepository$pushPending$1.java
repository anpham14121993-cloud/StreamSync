package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SyncRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.SyncRepository", f = "SyncRepository.kt", i = {0, 1, 1, 2}, l = {92, 95, 97}, m = "pushPending", n = {"this", "this", "entry", "this"}, s = {"L$0", "L$0", "L$2", "L$0"})
/* loaded from: classes21.dex */
public final class SyncRepository$pushPending$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SyncRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncRepository$pushPending$1(SyncRepository syncRepository, Continuation<? super SyncRepository$pushPending$1> continuation) {
        super(continuation);
        this.this$0 = syncRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.pushPending(this);
    }
}
