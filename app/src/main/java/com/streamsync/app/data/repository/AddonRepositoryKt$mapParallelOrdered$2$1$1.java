package com.streamsync.app.data.repository;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
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
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "R", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepositoryKt$mapParallelOrdered$2$1$1", f = "AddonRepository.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
final class AddonRepositoryKt$mapParallelOrdered$2$1$1<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    final /* synthetic */ Function2<T, Continuation<? super R>, Object> $block;
    final /* synthetic */ T $item;
    final /* synthetic */ Semaphore $permits;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AddonRepositoryKt$mapParallelOrdered$2$1$1(Semaphore semaphore, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, T t, Continuation<? super AddonRepositoryKt$mapParallelOrdered$2$1$1> continuation) {
        super(2, continuation);
        this.$permits = semaphore;
        this.$block = function2;
        this.$item = t;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepositoryKt$mapParallelOrdered$2$1$1 addonRepositoryKt$mapParallelOrdered$2$1$1 = new AddonRepositoryKt$mapParallelOrdered$2$1$1(this.$permits, this.$block, this.$item, continuation);
        addonRepositoryKt$mapParallelOrdered$2$1$1.L$0 = obj;
        return addonRepositoryKt$mapParallelOrdered$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
        return ((AddonRepositoryKt$mapParallelOrdered$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AddonRepository.kt */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", "R", ExifInterface.GPS_DIRECTION_TRUE}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepositoryKt$mapParallelOrdered$2$1$1$1", f = "AddonRepository.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.data.repository.AddonRepositoryKt$mapParallelOrdered$2$1$1$1, reason: invalid class name */
    /* loaded from: classes21.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
        final /* synthetic */ CoroutineScope $$this$async;
        final /* synthetic */ Function2<T, Continuation<? super R>, Object> $block;
        final /* synthetic */ T $item;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(CoroutineScope coroutineScope, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, T t, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.$$this$async = coroutineScope;
            this.$block = function2;
            this.$item = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.$$this$async, this.$block, this.$item, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super R> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0056 A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                switch(r1) {
                    case 0: goto L1c;
                    case 1: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L11:
                r0 = r7
                r1 = 0
                kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L1a
                r4 = r1
                r1 = r0
                r0 = r8
                goto L33
            L1a:
                r1 = move-exception
                goto L42
            L1c:
                kotlin.ResultKt.throwOnFailure(r8)
                r1 = r7
                kotlin.jvm.functions.Function2<T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r1.$block
                T r3 = r1.$item
                kotlin.Result$Companion r4 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L3e
                r4 = 0
                r5 = 1
                r1.label = r5     // Catch: java.lang.Throwable -> L3e
                java.lang.Object r2 = r2.invoke(r3, r1)     // Catch: java.lang.Throwable -> L3e
                if (r2 != r0) goto L31
                return r0
            L31:
                r0 = r8
                r8 = r2
            L33:
                java.lang.Object r8 = kotlin.Result.m6717constructorimpl(r8)     // Catch: java.lang.Throwable -> L38
                goto L50
            L38:
                r8 = move-exception
                r6 = r1
                r1 = r8
                r8 = r0
                r0 = r6
                goto L42
            L3e:
                r0 = move-exception
                r6 = r1
                r1 = r0
                r0 = r6
            L42:
                kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
                java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
                java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
                r6 = r0
                r0 = r8
                r8 = r1
                r1 = r6
            L50:
                boolean r2 = kotlin.Result.m6723isFailureimpl(r8)
                if (r2 == 0) goto L57
                r8 = 0
            L57:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepositoryKt$mapParallelOrdered$2$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$async = (CoroutineScope) this.L$0;
                this.label = 1;
                Object withPermit = StreamCacheKt.withPermit(this.$permits, new AnonymousClass1($this$async, this.$block, this.$item, null), this);
                return withPermit == coroutine_suspended ? coroutine_suspended : withPermit;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
