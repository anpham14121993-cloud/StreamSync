package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.model.CatalogRow;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/data/model/CatalogRow;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$getCatalogRows$2$outcomes$1$1", f = "AddonRepository.kt", i = {}, l = {233}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$getCatalogRows$2$outcomes$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CatalogRow>, Object> {
    final /* synthetic */ AddonManifest $addon;
    final /* synthetic */ AddonCatalogDef $def;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$getCatalogRows$2$outcomes$1$1(AddonRepository addonRepository, AddonManifest addonManifest, AddonCatalogDef addonCatalogDef, Continuation<? super AddonRepository$getCatalogRows$2$outcomes$1$1> continuation) {
        super(2, continuation);
        this.this$0 = addonRepository;
        this.$addon = addonManifest;
        this.$def = addonCatalogDef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AddonRepository$getCatalogRows$2$outcomes$1$1(this.this$0, this.$addon, this.$def, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CatalogRow> continuation) {
        return ((AddonRepository$getCatalogRows$2$outcomes$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AddonRepository.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/data/model/CatalogRow;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$getCatalogRows$2$outcomes$1$1$1", f = "AddonRepository.kt", i = {}, l = {233}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.data.repository.AddonRepository$getCatalogRows$2$outcomes$1$1$1, reason: invalid class name */
    /* loaded from: classes21.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super CatalogRow>, Object> {
        final /* synthetic */ AddonManifest $addon;
        final /* synthetic */ AddonCatalogDef $def;
        int label;
        final /* synthetic */ AddonRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AddonRepository addonRepository, AddonManifest addonManifest, AddonCatalogDef addonCatalogDef, Continuation<? super AnonymousClass1> continuation) {
            super(1, continuation);
            this.this$0 = addonRepository;
            this.$addon = addonManifest;
            this.$def = addonCatalogDef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$addon, this.$def, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super CatalogRow> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object fetchCatalogRow;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    fetchCatalogRow = this.this$0.fetchCatalogRow(this.$addon, this.$def, this);
                    return fetchCatalogRow == coroutine_suspended ? coroutine_suspended : fetchCatalogRow;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Semaphore semaphore;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                semaphore = this.this$0.catalogPermit;
                this.label = 1;
                Object withPermit = StreamCacheKt.withPermit(semaphore, new AnonymousClass1(this.this$0, this.$addon, this.$def, null), this);
                return withPermit == coroutine_suspended ? coroutine_suspended : withPermit;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
