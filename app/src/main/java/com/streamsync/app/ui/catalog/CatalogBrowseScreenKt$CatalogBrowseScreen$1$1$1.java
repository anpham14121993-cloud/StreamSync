package com.streamsync.app.ui.catalog;

import androidx.compose.runtime.State;
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
/* compiled from: CatalogBrowseScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogBrowseScreenKt$CatalogBrowseScreen$1$1$1", f = "CatalogBrowseScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes22.dex */
public final class CatalogBrowseScreenKt$CatalogBrowseScreen$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<Boolean> $loadMore$delegate;
    final /* synthetic */ CatalogBrowseViewModel $viewModel;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogBrowseScreenKt$CatalogBrowseScreen$1$1$1(CatalogBrowseViewModel catalogBrowseViewModel, State<Boolean> state, Continuation<? super CatalogBrowseScreenKt$CatalogBrowseScreen$1$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = catalogBrowseViewModel;
        this.$loadMore$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CatalogBrowseScreenKt$CatalogBrowseScreen$1$1$1(this.$viewModel, this.$loadMore$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CatalogBrowseScreenKt$CatalogBrowseScreen$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean CatalogBrowseScreen$lambda$6$lambda$3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                CatalogBrowseScreen$lambda$6$lambda$3 = CatalogBrowseScreenKt.CatalogBrowseScreen$lambda$6$lambda$3(this.$loadMore$delegate);
                if (CatalogBrowseScreen$lambda$6$lambda$3) {
                    this.$viewModel.loadMore();
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
