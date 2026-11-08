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
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient", f = "GeminiRestTranscribeClient.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {626, 643, 651}, m = "translateWithAnyModel", n = {"this", TtmlNode.TAG_BODY, "model", "deadlineMs", "round", "this", TtmlNode.TAG_BODY, "lastError", "deadlineMs", "round", "this", TtmlNode.TAG_BODY, "lastError", "deadlineMs", "round"}, s = {"L$0", "L$1", "L$3", "J$0", "I$0", "L$0", "L$1", "L$2", "J$0", "I$0", "L$0", "L$1", "L$2", "J$0", "I$0"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$translateWithAnyModel$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeminiRestTranscribeClient$translateWithAnyModel$1(GeminiRestTranscribeClient geminiRestTranscribeClient, Continuation<? super GeminiRestTranscribeClient$translateWithAnyModel$1> continuation) {
        super(continuation);
        this.this$0 = geminiRestTranscribeClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object translateWithAnyModel;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        translateWithAnyModel = this.this$0.translateWithAnyModel(null, 0L, this);
        return translateWithAnyModel;
    }
}
