package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository", f = "AddonRepository.kt", i = {0, 0, 0, 0, 0}, l = {599}, m = "resolveStreams$ask", n = {"$onPartial", "this$0", "$compositeId", "answers", "list"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes21.dex */
public final class AddonRepository$resolveStreams$ask$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AddonRepository$resolveStreams$ask$1(Continuation<? super AddonRepository$resolveStreams$ask$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object resolveStreams$ask;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        resolveStreams$ask = AddonRepository.resolveStreams$ask(null, null, null, null, null, 0, null, null, this);
        return resolveStreams$ask;
    }
}
