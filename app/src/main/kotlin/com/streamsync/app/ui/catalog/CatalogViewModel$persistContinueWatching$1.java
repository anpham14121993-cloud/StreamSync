package com.streamsync.app.ui.catalog;

import android.content.Context;
import com.streamsync.app.data.local.ContinueWatchingDiskCache;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CatalogScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$persistContinueWatching$1", f = "CatalogScreen.kt", i = {}, l = {373}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes22.dex */
public final class CatalogViewModel$persistContinueWatching$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CatalogViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogViewModel$persistContinueWatching$1(CatalogViewModel catalogViewModel, Continuation<? super CatalogViewModel$persistContinueWatching$1> continuation) {
        super(2, continuation);
        this.this$0 = catalogViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CatalogViewModel$persistContinueWatching$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CatalogViewModel$persistContinueWatching$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CatalogViewModel$persistContinueWatching$1 catalogViewModel$persistContinueWatching$1;
        List snapshot;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                catalogViewModel$persistContinueWatching$1 = this;
                break;
            case 1:
                catalogViewModel$persistContinueWatching$1 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        snapshot = catalogViewModel$persistContinueWatching$1.this$0.pendingPersist;
        if (snapshot == null) {
            return Unit.INSTANCE;
        }
        catalogViewModel$persistContinueWatching$1.this$0.pendingPersist = null;
        ContinueWatchingDiskCache continueWatchingDiskCache = ContinueWatchingDiskCache.INSTANCE;
        Context context = catalogViewModel$persistContinueWatching$1.this$0.applicationContext;
        Intrinsics.checkNotNullExpressionValue(context, "access$getApplicationContext$p(...)");
        continueWatchingDiskCache.save(context, snapshot);
        return Unit.INSTANCE;
    }
}
