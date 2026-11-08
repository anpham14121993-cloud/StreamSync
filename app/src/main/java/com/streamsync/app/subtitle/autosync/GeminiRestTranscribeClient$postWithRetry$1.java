package com.streamsync.app.subtitle.autosync;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient", f = "GeminiRestTranscribeClient.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {733, 746}, m = "postWithRetry", n = {"this", TtmlNode.TAG_BODY, "endpoint", "contentType", "attempt", "backoff", "chunkIndex", "maxAttempts", "maxWaitMs", "this", TtmlNode.TAG_BODY, "endpoint", "contentType", "attempt", "backoff", "chunkIndex", "maxAttempts", "maxWaitMs"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "J$0"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$postWithRetry$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeminiRestTranscribeClient$postWithRetry$1(GeminiRestTranscribeClient geminiRestTranscribeClient, Continuation<? super GeminiRestTranscribeClient$postWithRetry$1> continuation) {
        super(continuation);
        this.this$0 = geminiRestTranscribeClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object postWithRetry;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        postWithRetry = this.this$0.postWithRetry(null, 0, null, null, 0, 0L, this);
        return postWithRetry;
    }
}
