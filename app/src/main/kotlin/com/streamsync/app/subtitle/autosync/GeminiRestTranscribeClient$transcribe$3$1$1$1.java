package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$transcribe$3$1$1$1", f = "GeminiRestTranscribeClient.kt", i = {0, 1}, l = {870, 351}, m = "invokeSuspend", n = {"$this$withPermit$iv", "$this$withPermit$iv"}, s = {"L$0", "L$0"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$transcribe$3$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $captureStartMs;
    final /* synthetic */ AudioChunk $chunk;
    final /* synthetic */ List<AudioChunk> $chunks;
    final /* synthetic */ AtomicInteger $doneCount;
    final /* synthetic */ Function2<Integer, Integer, Unit> $onProgress;
    final /* synthetic */ byte[] $pcm;
    final /* synthetic */ List<TimedWord>[] $results;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public GeminiRestTranscribeClient$transcribe$3$1$1$1(GeminiRestTranscribeClient geminiRestTranscribeClient, byte[] bArr, AudioChunk audioChunk, long j, List<TimedWord>[] listArr, Function2<? super Integer, ? super Integer, Unit> function2, AtomicInteger atomicInteger, List<AudioChunk> list, Continuation<? super GeminiRestTranscribeClient$transcribe$3$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = geminiRestTranscribeClient;
        this.$pcm = bArr;
        this.$chunk = audioChunk;
        this.$captureStartMs = j;
        this.$results = listArr;
        this.$onProgress = function2;
        this.$doneCount = atomicInteger;
        this.$chunks = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeminiRestTranscribeClient$transcribe$3$1$1$1(this.this$0, this.$pcm, this.$chunk, this.$captureStartMs, this.$results, this.$onProgress, this.$doneCount, this.$chunks, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeminiRestTranscribeClient$transcribe$3$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r33) {
        /*
            Method dump skipped, instructions count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$transcribe$3$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
