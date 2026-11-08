package com.streamsync.app.data.repository;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepositoryKt$mapParallelOrdered$2", f = "AddonRepository.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepositoryKt$mapParallelOrdered$2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends R>>, Object> {
    final /* synthetic */ Function2<T, Continuation<? super R>, Object> $block;
    final /* synthetic */ List<T> $items;
    final /* synthetic */ int $limit;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AddonRepositoryKt$mapParallelOrdered$2(int i, List<? extends T> list, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AddonRepositoryKt$mapParallelOrdered$2> continuation) {
        super(2, continuation);
        this.$limit = i;
        this.$items = list;
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepositoryKt$mapParallelOrdered$2 addonRepositoryKt$mapParallelOrdered$2 = new AddonRepositoryKt$mapParallelOrdered$2(this.$limit, this.$items, this.$block, continuation);
        addonRepositoryKt$mapParallelOrdered$2.L$0 = obj;
        return addonRepositoryKt$mapParallelOrdered$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends R>> continuation) {
        return ((AddonRepositoryKt$mapParallelOrdered$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Deferred async$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                Continuation continuation = null;
                Semaphore permits = SemaphoreKt.Semaphore$default(RangesKt.coerceAtLeast(this.$limit, 1), 0, 2, null);
                Iterable $this$map$iv = this.$items;
                Function2<T, Continuation<? super R>, Object> function2 = this.$block;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    CoroutineDispatcher io = Dispatchers.getIO();
                    AddonRepositoryKt$mapParallelOrdered$2$1$1 addonRepositoryKt$mapParallelOrdered$2$1$1 = new AddonRepositoryKt$mapParallelOrdered$2$1$1(permits, function2, item$iv$iv, continuation);
                    Collection destination$iv$iv2 = destination$iv$iv;
                    async$default = BuildersKt__Builders_commonKt.async$default($this$coroutineScope, io, null, addonRepositoryKt$mapParallelOrdered$2$1$1, 2, null);
                    destination$iv$iv2.add(async$default);
                    destination$iv$iv = destination$iv$iv2;
                    continuation = null;
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
