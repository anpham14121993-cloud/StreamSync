package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonClient;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.data.addon.CacheDirective;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonMeta;", "Lcom/streamsync/app/data/addon/CacheDirective;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$3", f = "AddonRepository.kt", i = {}, l = {1389}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$fetchMetaCached$3 extends SuspendLambda implements Function1<Continuation<? super Pair<? extends AddonMeta, ? extends CacheDirective>>, Object> {
    final /* synthetic */ AddonManifest $addon;
    final /* synthetic */ String $itemId;
    final /* synthetic */ String $type;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$fetchMetaCached$3(AddonManifest addonManifest, String str, String str2, Continuation<? super AddonRepository$fetchMetaCached$3> continuation) {
        super(1, continuation);
        this.$addon = addonManifest;
        this.$type = str;
        this.$itemId = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AddonRepository$fetchMetaCached$3(this.$addon, this.$type, this.$itemId, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Pair<? extends AddonMeta, ? extends CacheDirective>> continuation) {
        return invoke2((Continuation<? super Pair<AddonMeta, CacheDirective>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Pair<AddonMeta, CacheDirective>> continuation) {
        return ((AddonRepository$fetchMetaCached$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object fetchMetaWithHeaders = AddonClient.INSTANCE.fetchMetaWithHeaders(this.$addon, this.$type, this.$itemId, this);
                return fetchMetaWithHeaders == coroutine_suspended ? coroutine_suspended : fetchMetaWithHeaders;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
