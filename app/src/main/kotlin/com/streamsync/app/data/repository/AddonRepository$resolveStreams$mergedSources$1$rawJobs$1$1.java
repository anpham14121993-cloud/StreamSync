package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.StreamResponse;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Semaphore;

/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$resolveStreams$mergedSources$1$rawJobs$1$1", f = "AddonRepository.kt", i = {}, l = {637}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
final class AddonRepository$resolveStreams$mergedSources$1$rawJobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $compositeId;
    final /* synthetic */ int $index;
    final /* synthetic */ Function1<List<StreamSource>, Unit> $onPartial;
    final /* synthetic */ Semaphore $permits;
    final /* synthetic */ StreamResponse[] $rawAnswers;
    final /* synthetic */ List<Pair<AddonManifest, String>> $rawTargets;
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AddonRepository$resolveStreams$mergedSources$1$rawJobs$1$1(int i, StreamResponse[] streamResponseArr, List<Pair<AddonManifest, String>> list, Semaphore semaphore, Function1<? super List<StreamSource>, Unit> function1, AddonRepository addonRepository, String str, String str2, Continuation<? super AddonRepository$resolveStreams$mergedSources$1$rawJobs$1$1> continuation) {
        super(2, continuation);
        this.$index = i;
        this.$rawAnswers = streamResponseArr;
        this.$rawTargets = list;
        this.$permits = semaphore;
        this.$onPartial = function1;
        this.this$0 = addonRepository;
        this.$compositeId = str;
        this.$type = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AddonRepository$resolveStreams$mergedSources$1$rawJobs$1$1(this.$index, this.$rawAnswers, this.$rawTargets, this.$permits, this.$onPartial, this.this$0, this.$compositeId, this.$type, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AddonRepository$resolveStreams$mergedSources$1$rawJobs$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object resolveStreams$ask;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                resolveStreams$ask = AddonRepository.resolveStreams$ask(this.$permits, this.$onPartial, this.this$0, this.$compositeId, this.$type, this.$index, this.$rawAnswers, this.$rawTargets, this);
                if (resolveStreams$ask != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
