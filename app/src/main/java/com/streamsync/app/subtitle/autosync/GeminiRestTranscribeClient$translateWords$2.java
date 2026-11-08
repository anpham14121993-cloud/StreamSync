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
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$2", f = "GeminiRestTranscribeClient.kt", i = {0}, l = {545}, m = "invokeSuspend", n = {"translated"}, s = {"L$0"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$translateWords$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<TimedWord>>, Object> {
    final /* synthetic */ long $deadlineMs;
    final /* synthetic */ String $targetLanguage;
    final /* synthetic */ List<TimedWord> $words;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeminiRestTranscribeClient$translateWords$2(List<TimedWord> list, long j, GeminiRestTranscribeClient geminiRestTranscribeClient, String str, Continuation<? super GeminiRestTranscribeClient$translateWords$2> continuation) {
        super(2, continuation);
        this.$words = list;
        this.$deadlineMs = j;
        this.this$0 = geminiRestTranscribeClient;
        this.$targetLanguage = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeminiRestTranscribeClient$translateWords$2(this.$words, this.$deadlineMs, this.this$0, this.$targetLanguage, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<TimedWord>> continuation) {
        return ((GeminiRestTranscribeClient$translateWords$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00ae A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00a3 -> B:7:0x00aa). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 0
            switch(r1) {
                case 0: goto L25;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L12:
            r1 = r13
            java.lang.Object r3 = r1.L$1
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r1.L$0
            java.util.List r4 = (java.util.List) r4
            kotlin.ResultKt.throwOnFailure(r14)
            r5 = r4
            r4 = r3
            r3 = r1
            r1 = r0
            r0 = r14
            goto Laa
        L25:
            kotlin.ResultKt.throwOnFailure(r14)
            r1 = r13
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r3 = (java.util.List) r3
            java.util.List<com.streamsync.app.subtitle.autosync.TimedWord> r4 = r1.$words
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            r5 = 120(0x78, float:1.68E-43)
            java.util.List r4 = kotlin.collections.CollectionsKt.chunked(r4, r5)
            java.util.Iterator r4 = r4.iterator()
            r12 = r4
            r4 = r3
            r3 = r12
        L41:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto Lbe
            java.lang.Object r5 = r3.next()
            java.util.List r5 = (java.util.List) r5
            long r6 = android.os.SystemClock.elapsedRealtime()
            long r8 = r1.$deadlineMs
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 < 0) goto L8b
            com.streamsync.app.util.FileLog r0 = com.streamsync.app.util.FileLog.INSTANCE
            int r3 = r4.size()
            java.util.List<com.streamsync.app.subtitle.autosync.TimedWord> r5 = r1.$words
            int r5 = r5.size()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Dịch từ: hết thời gian, dừng sau "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r3 = r6.append(r3)
            java.lang.String r6 = "/"
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = " từ"
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            r5 = 2
            com.streamsync.app.util.FileLog.w$default(r0, r3, r2, r5, r2)
            return r2
        L8b:
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient r6 = r1.this$0
            java.lang.String r8 = r1.$targetLanguage
            long r9 = r1.$deadlineMs
            r11 = r1
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r1.L$0 = r4
            r1.L$1 = r3
            r7 = 1
            r1.label = r7
            r7 = r5
            java.lang.Object r5 = com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.access$translateBatch(r6, r7, r8, r9, r11)
            if (r5 != r0) goto La3
            return r0
        La3:
            r12 = r0
            r0 = r14
            r14 = r5
            r5 = r4
            r4 = r3
            r3 = r1
            r1 = r12
        Laa:
            java.util.List r14 = (java.util.List) r14
            if (r14 != 0) goto Laf
            return r2
        Laf:
            r6 = r5
            java.util.Collection r6 = (java.util.Collection) r6
            r7 = r14
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            kotlin.collections.CollectionsKt.addAll(r6, r7)
            r14 = r0
            r0 = r1
            r1 = r3
            r3 = r4
            r4 = r5
            goto L41
        Lbe:
            int r0 = r4.size()
            java.util.List<com.streamsync.app.subtitle.autosync.TimedWord> r3 = r1.$words
            int r3 = r3.size()
            if (r0 == r3) goto Lcb
            goto Lcc
        Lcb:
            r2 = r4
        Lcc:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
