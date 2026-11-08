package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.StreamResponse;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/data/model/StreamSource;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$resolveStreams$mergedSources$1", f = "AddonRepository.kt", i = {0, 0, 1, 1}, l = {640, 660}, m = "invokeSuspend", n = {"$this$coroutineScope", "rawJobs", "imdbTargets", "imdbAnswers"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes21.dex */
public final class AddonRepository$resolveStreams$mergedSources$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ String $compositeId;
    final /* synthetic */ Deferred<String> $imdbDeferred;
    final /* synthetic */ String $itemId;
    final /* synthetic */ Function1<List<StreamSource>, Unit> $onPartial;
    final /* synthetic */ Semaphore $permits;
    final /* synthetic */ StreamResponse[] $rawAnswers;
    final /* synthetic */ List<Pair<AddonManifest, String>> $rawTargets;
    final /* synthetic */ List<String> $skipped;
    final /* synthetic */ List<AddonManifest> $streamAddons;
    final /* synthetic */ long $t0;
    final /* synthetic */ String $type;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AddonRepository$resolveStreams$mergedSources$1(List<Pair<AddonManifest, String>> list, Deferred<String> deferred, String str, List<AddonManifest> list2, String str2, long j, AddonRepository addonRepository, StreamResponse[] streamResponseArr, Semaphore semaphore, Function1<? super List<StreamSource>, Unit> function1, String str3, List<String> list3, Continuation<? super AddonRepository$resolveStreams$mergedSources$1> continuation) {
        super(2, continuation);
        this.$rawTargets = list;
        this.$imdbDeferred = deferred;
        this.$itemId = str;
        this.$streamAddons = list2;
        this.$compositeId = str2;
        this.$t0 = j;
        this.this$0 = addonRepository;
        this.$rawAnswers = streamResponseArr;
        this.$permits = semaphore;
        this.$onPartial = function1;
        this.$type = str3;
        this.$skipped = list3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepository$resolveStreams$mergedSources$1 addonRepository$resolveStreams$mergedSources$1 = new AddonRepository$resolveStreams$mergedSources$1(this.$rawTargets, this.$imdbDeferred, this.$itemId, this.$streamAddons, this.$compositeId, this.$t0, this.this$0, this.$rawAnswers, this.$permits, this.$onPartial, this.$type, this.$skipped, continuation);
        addonRepository$resolveStreams$mergedSources$1.L$0 = obj;
        return addonRepository$resolveStreams$mergedSources$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends StreamSource>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<StreamSource>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<StreamSource>> continuation) {
        return ((AddonRepository$resolveStreams$mergedSources$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x010d A[LOOP:2: B:57:0x0107->B:59:0x010d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02ab A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02ac  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r37) {
        /*
            Method dump skipped, instructions count: 968
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$resolveStreams$mergedSources$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
