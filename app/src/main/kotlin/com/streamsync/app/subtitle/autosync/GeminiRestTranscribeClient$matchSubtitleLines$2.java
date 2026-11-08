package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/subtitle/autosync/LineMatch;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$2", f = "GeminiRestTranscribeClient.kt", i = {0}, l = {446}, m = "invokeSuspend", n = {"$this$withContext"}, s = {"L$0"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$matchSubtitleLines$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends LineMatch>>, Object> {
    final /* synthetic */ List<String> $candidateLines;
    final /* synthetic */ long $deadlineMs;
    final /* synthetic */ List<String> $referenceLines;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeminiRestTranscribeClient$matchSubtitleLines$2(GeminiRestTranscribeClient geminiRestTranscribeClient, List<String> list, List<String> list2, long j, Continuation<? super GeminiRestTranscribeClient$matchSubtitleLines$2> continuation) {
        super(2, continuation);
        this.this$0 = geminiRestTranscribeClient;
        this.$referenceLines = list;
        this.$candidateLines = list2;
        this.$deadlineMs = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GeminiRestTranscribeClient$matchSubtitleLines$2 geminiRestTranscribeClient$matchSubtitleLines$2 = new GeminiRestTranscribeClient$matchSubtitleLines$2(this.this$0, this.$referenceLines, this.$candidateLines, this.$deadlineMs, continuation);
        geminiRestTranscribeClient$matchSubtitleLines$2.L$0 = obj;
        return geminiRestTranscribeClient$matchSubtitleLines$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends LineMatch>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<LineMatch>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<LineMatch>> continuation) {
        return ((GeminiRestTranscribeClient$matchSubtitleLines$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x011a, code lost:
    
        r0 = r2.this$0.extractFirstText(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0124, code lost:
    
        r0 = r2.this$0.extractJsonArray(r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            Method dump skipped, instructions count: 498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
