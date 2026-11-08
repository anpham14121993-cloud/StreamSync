package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.MediaItem;
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

/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/data/model/MediaItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel$load$1$detailDeferred$1", f = "MovieDetailScreen.kt", i = {}, l = {290}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
final class MovieDetailViewModel$load$1$detailDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaItem>, Object> {
    final /* synthetic */ boolean $fromAddon;
    int label;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$load$1$detailDeferred$1(MovieDetailViewModel movieDetailViewModel, boolean z, Continuation<? super MovieDetailViewModel$load$1$detailDeferred$1> continuation) {
        super(2, continuation);
        this.this$0 = movieDetailViewModel;
        this.$fromAddon = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MovieDetailViewModel$load$1$detailDeferred$1(this.this$0, this.$fromAddon, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaItem> continuation) {
        return ((MovieDetailViewModel$load$1$detailDeferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object resolveDetail;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                resolveDetail = this.this$0.resolveDetail(this.$fromAddon, this);
                return resolveDetail == coroutine_suspended ? coroutine_suspended : resolveDetail;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
