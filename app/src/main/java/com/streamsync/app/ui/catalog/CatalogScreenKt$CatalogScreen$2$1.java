package com.streamsync.app.ui.catalog;

import android.content.Context;
import androidx.compose.runtime.State;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.StreamSyncImageLoaderKt;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CatalogScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogScreenKt$CatalogScreen$2$1", f = "CatalogScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes22.dex */
public final class CatalogScreenKt$CatalogScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Function1<List<CatalogRow>, Unit> $onRowsLoaded;
    final /* synthetic */ int $prefetchPerRow;
    final /* synthetic */ State<List<CatalogRow>> $rows$delegate;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CatalogScreenKt$CatalogScreen$2$1(Function1<? super List<CatalogRow>, Unit> function1, Context context, State<? extends List<CatalogRow>> state, int i, Continuation<? super CatalogScreenKt$CatalogScreen$2$1> continuation) {
        super(2, continuation);
        this.$onRowsLoaded = function1;
        this.$context = context;
        this.$rows$delegate = state;
        this.$prefetchPerRow = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CatalogScreenKt$CatalogScreen$2$1(this.$onRowsLoaded, this.$context, this.$rows$delegate, this.$prefetchPerRow, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CatalogScreenKt$CatalogScreen$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List<CatalogRow> CatalogScreen$lambda$2;
        List CatalogScreen$lambda$22;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Function1<List<CatalogRow>, Unit> function1 = this.$onRowsLoaded;
                CatalogScreen$lambda$2 = CatalogScreenKt.CatalogScreen$lambda$2(this.$rows$delegate);
                function1.invoke(CatalogScreen$lambda$2);
                CatalogScreen$lambda$22 = CatalogScreenKt.CatalogScreen$lambda$2(this.$rows$delegate);
                Iterable $this$flatMapTo$iv$iv = CollectionsKt.take(CatalogScreen$lambda$22, 3);
                int i = this.$prefetchPerRow;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$flatMapTo$iv$iv) {
                    CatalogRow it = (CatalogRow) element$iv$iv;
                    Iterable list$iv$iv = CollectionsKt.take(it.getItems(), i);
                    CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
                }
                List warm = (List) destination$iv$iv;
                StreamSyncImageLoaderKt.prefetchPosters(this.$context, warm);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
