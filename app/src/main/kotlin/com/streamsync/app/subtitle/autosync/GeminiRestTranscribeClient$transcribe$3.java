package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.FileLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$transcribe$3", f = "GeminiRestTranscribeClient.kt", i = {0, 0}, l = {344}, m = "invokeSuspend", n = {"chunks", "results"}, s = {"L$0", "L$1"})
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient$transcribe$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TimedWord>>, Object> {
    final /* synthetic */ long $captureStartMs;
    final /* synthetic */ Function2<Integer, Integer, Unit> $onProgress;
    final /* synthetic */ byte[] $pcm;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ GeminiRestTranscribeClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public GeminiRestTranscribeClient$transcribe$3(byte[] bArr, long j, GeminiRestTranscribeClient geminiRestTranscribeClient, Function2<? super Integer, ? super Integer, Unit> function2, Continuation<? super GeminiRestTranscribeClient$transcribe$3> continuation) {
        super(2, continuation);
        this.$pcm = bArr;
        this.$captureStartMs = j;
        this.this$0 = geminiRestTranscribeClient;
        this.$onProgress = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GeminiRestTranscribeClient$transcribe$3(this.$pcm, this.$captureStartMs, this.this$0, this.$onProgress, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TimedWord>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<TimedWord>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<TimedWord>> continuation) {
        return ((GeminiRestTranscribeClient$transcribe$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        List chunks;
        GeminiRestTranscribeClient$transcribe$3 geminiRestTranscribeClient$transcribe$3;
        List[] results;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                chunks = GeminiRestTranscribeClient.INSTANCE.planChunks(this.$pcm.length / 2);
                if (chunks.isEmpty()) {
                    return CollectionsKt.emptyList();
                }
                AtomicInteger doneCount = new AtomicInteger(0);
                List[] results2 = new List[chunks.size()];
                this.L$0 = chunks;
                this.L$1 = results2;
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(chunks, this.this$0, this.$pcm, this.$captureStartMs, results2, this.$onProgress, doneCount, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                geminiRestTranscribeClient$transcribe$3 = this;
                results = results2;
                break;
            case 1:
                geminiRestTranscribeClient$transcribe$3 = this;
                results = (List[]) geminiRestTranscribeClient$transcribe$3.L$1;
                chunks = (List) geminiRestTranscribeClient$transcribe$3.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int size = chunks.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            int it = i;
            List list = results[it];
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            arrayList.add(list);
        }
        ArrayList perChunk = arrayList;
        List merged = GeminiRestTranscribeClient.INSTANCE.mergeChunks(chunks, perChunk, geminiRestTranscribeClient$transcribe$3.$captureStartMs);
        FileLog.d$default(FileLog.INSTANCE, "REST: gop " + chunks.size() + " doan -> " + merged.size() + " tu (tieu " + (geminiRestTranscribeClient$transcribe$3.$pcm.length / 1024) + " KB audio)", null, 2, null);
        return merged;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeminiRestTranscribeClient.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$transcribe$3$1", f = "GeminiRestTranscribeClient.kt", i = {}, l = {368}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$transcribe$3$1, reason: invalid class name */
    /* loaded from: classes19.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Unit>>, Object> {
        final /* synthetic */ long $captureStartMs;
        final /* synthetic */ List<AudioChunk> $chunks;
        final /* synthetic */ AtomicInteger $doneCount;
        final /* synthetic */ Function2<Integer, Integer, Unit> $onProgress;
        final /* synthetic */ byte[] $pcm;
        final /* synthetic */ List<TimedWord>[] $results;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ GeminiRestTranscribeClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(List<AudioChunk> list, GeminiRestTranscribeClient geminiRestTranscribeClient, byte[] bArr, long j, List<TimedWord>[] listArr, Function2<? super Integer, ? super Integer, Unit> function2, AtomicInteger atomicInteger, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$chunks = list;
            this.this$0 = geminiRestTranscribeClient;
            this.$pcm = bArr;
            this.$captureStartMs = j;
            this.$results = listArr;
            this.$onProgress = function2;
            this.$doneCount = atomicInteger;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$chunks, this.this$0, this.$pcm, this.$captureStartMs, this.$results, this.$onProgress, this.$doneCount, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Unit>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<Unit>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Unit>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Deferred async$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Iterable $this$map$iv = this.$chunks;
                    GeminiRestTranscribeClient geminiRestTranscribeClient = this.this$0;
                    byte[] bArr = this.$pcm;
                    long j = this.$captureStartMs;
                    List<TimedWord>[] listArr = this.$results;
                    Function2<Integer, Integer, Unit> function2 = this.$onProgress;
                    AtomicInteger atomicInteger = this.$doneCount;
                    List<AudioChunk> list = this.$chunks;
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        AudioChunk chunk = (AudioChunk) item$iv$iv;
                        Collection destination$iv$iv2 = destination$iv$iv;
                        List<AudioChunk> list2 = list;
                        AtomicInteger atomicInteger2 = atomicInteger;
                        List<TimedWord>[] listArr2 = listArr;
                        Function2<Integer, Integer, Unit> function22 = function2;
                        async$default = BuildersKt__Builders_commonKt.async$default($this$coroutineScope, null, null, new GeminiRestTranscribeClient$transcribe$3$1$1$1(geminiRestTranscribeClient, bArr, chunk, j, listArr2, function22, atomicInteger2, list2, null), 3, null);
                        destination$iv$iv2.add(async$default);
                        destination$iv$iv = destination$iv$iv2;
                        list = list2;
                        atomicInteger = atomicInteger2;
                        listArr = listArr2;
                        function2 = function22;
                        j = j;
                        bArr = bArr;
                        geminiRestTranscribeClient = geminiRestTranscribeClient;
                    }
                    this.label = 1;
                    Object awaitAll = AwaitKt.awaitAll((List) destination$iv$iv, this);
                    return awaitAll == coroutine_suspended ? coroutine_suspended : awaitAll;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
