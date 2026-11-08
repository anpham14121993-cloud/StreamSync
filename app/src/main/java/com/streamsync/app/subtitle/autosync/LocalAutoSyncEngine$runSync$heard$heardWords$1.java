package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocalAutoSyncEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$runSync$heard$heardWords$1", f = "LocalAutoSyncEngine.kt", i = {}, l = {320}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes19.dex */
public final class LocalAutoSyncEngine$runSync$heard$heardWords$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TimedWord>>, Object> {
    final /* synthetic */ byte[] $bytes;
    final /* synthetic */ long $captureStartMs;
    final /* synthetic */ long $capturedMs;
    final /* synthetic */ GeminiRestTranscribeClient $client;
    int label;
    final /* synthetic */ LocalAutoSyncEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalAutoSyncEngine$runSync$heard$heardWords$1(GeminiRestTranscribeClient geminiRestTranscribeClient, byte[] bArr, long j, LocalAutoSyncEngine localAutoSyncEngine, long j2, Continuation<? super LocalAutoSyncEngine$runSync$heard$heardWords$1> continuation) {
        super(2, continuation);
        this.$client = geminiRestTranscribeClient;
        this.$bytes = bArr;
        this.$captureStartMs = j;
        this.this$0 = localAutoSyncEngine;
        this.$capturedMs = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocalAutoSyncEngine$runSync$heard$heardWords$1(this.$client, this.$bytes, this.$captureStartMs, this.this$0, this.$capturedMs, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TimedWord>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<TimedWord>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<TimedWord>> continuation) {
        return ((LocalAutoSyncEngine$runSync$heard$heardWords$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                GeminiRestTranscribeClient geminiRestTranscribeClient = this.$client;
                byte[] bArr = this.$bytes;
                long j = this.$captureStartMs;
                final LocalAutoSyncEngine localAutoSyncEngine = this.this$0;
                final long j2 = this.$capturedMs;
                this.label = 1;
                Object transcribe = geminiRestTranscribeClient.transcribe(bArr, j, new Function2<Integer, Integer, Unit>() { // from class: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$runSync$heard$heardWords$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                        invoke(num.intValue(), num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int done, int total) {
                        Function1 function1;
                        function1 = LocalAutoSyncEngine.this.onState;
                        function1.invoke(new LocalAutoSyncEngine.State.Transcribing(done, total, j2));
                    }
                }, this);
                return transcribe == coroutine_suspended ? coroutine_suspended : transcribe;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
