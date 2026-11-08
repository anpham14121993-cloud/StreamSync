package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient", f = "GeminiRestTranscribeClient.kt", i = {0, 0}, l = {589}, m = "translateBatch", n = {"this", "batch"}, s = {"L$0", "L$1"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$translateBatch$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeminiRestTranscribeClient$translateBatch$1(GeminiRestTranscribeClient geminiRestTranscribeClient, Continuation<? super GeminiRestTranscribeClient$translateBatch$1> continuation) {
        super(continuation);
        this.this$0 = geminiRestTranscribeClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object translateBatch;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        translateBatch = this.this$0.translateBatch(null, null, 0L, this);
        return translateBatch;
    }
}
