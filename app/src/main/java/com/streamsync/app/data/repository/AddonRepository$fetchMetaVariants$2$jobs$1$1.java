package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/data/addon/AddonMeta;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$2$jobs$1$1", f = "AddonRepository.kt", i = {}, l = {1425}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$fetchMetaVariants$2$jobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AddonMeta>, Object> {
    final /* synthetic */ AddonManifest $addon;
    final /* synthetic */ String $itemId;
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$fetchMetaVariants$2$jobs$1$1(AddonRepository addonRepository, AddonManifest addonManifest, String str, String str2, Continuation<? super AddonRepository$fetchMetaVariants$2$jobs$1$1> continuation) {
        super(2, continuation);
        this.this$0 = addonRepository;
        this.$addon = addonManifest;
        this.$type = str;
        this.$itemId = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AddonRepository$fetchMetaVariants$2$jobs$1$1(this.this$0, this.$addon, this.$type, this.$itemId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AddonMeta> continuation) {
        return ((AddonRepository$fetchMetaVariants$2$jobs$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object fetchMetaCached;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                fetchMetaCached = this.this$0.fetchMetaCached(this.$addon, this.$type, this.$itemId, this);
                return fetchMetaCached == coroutine_suspended ? coroutine_suspended : fetchMetaCached;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
