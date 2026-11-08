package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/data/model/CatalogRow;", "<name for destructuring parameter 0>", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonManifest;", "Lcom/streamsync/app/data/addon/AddonCatalogDef;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$getCatalogRows$rows$1", f = "AddonRepository.kt", i = {}, l = {224}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$getCatalogRows$rows$1 extends SuspendLambda implements Function2<Pair<? extends AddonManifest, ? extends AddonCatalogDef>, Continuation<? super CatalogRow>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$getCatalogRows$rows$1(AddonRepository addonRepository, Continuation<? super AddonRepository$getCatalogRows$rows$1> continuation) {
        super(2, continuation);
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepository$getCatalogRows$rows$1 addonRepository$getCatalogRows$rows$1 = new AddonRepository$getCatalogRows$rows$1(this.this$0, continuation);
        addonRepository$getCatalogRows$rows$1.L$0 = obj;
        return addonRepository$getCatalogRows$rows$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Pair<? extends AddonManifest, ? extends AddonCatalogDef> pair, Continuation<? super CatalogRow> continuation) {
        return invoke2((Pair<AddonManifest, AddonCatalogDef>) pair, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Pair<AddonManifest, AddonCatalogDef> pair, Continuation<? super CatalogRow> continuation) {
        return ((AddonRepository$getCatalogRows$rows$1) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object fetchCatalogRow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Pair pair = (Pair) this.L$0;
                AddonManifest addon = (AddonManifest) pair.component1();
                AddonCatalogDef def = (AddonCatalogDef) pair.component2();
                this.label = 1;
                fetchCatalogRow = this.this$0.fetchCatalogRow(addon, def, this);
                return fetchCatalogRow == coroutine_suspended ? coroutine_suspended : fetchCatalogRow;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
