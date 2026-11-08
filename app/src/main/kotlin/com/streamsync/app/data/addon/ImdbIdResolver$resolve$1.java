package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ImdbIdResolver.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.addon.ImdbIdResolver", f = "ImdbIdResolver.kt", i = {0, 0, 0}, l = {82}, m = "resolve", n = {"this", "cacheKey", "winner"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes14.dex */
public final class ImdbIdResolver$resolve$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ImdbIdResolver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImdbIdResolver$resolve$1(ImdbIdResolver imdbIdResolver, Continuation<? super ImdbIdResolver$resolve$1> continuation) {
        super(continuation);
        this.this$0 = imdbIdResolver;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolve(null, null, null, null, null, this);
    }
}
