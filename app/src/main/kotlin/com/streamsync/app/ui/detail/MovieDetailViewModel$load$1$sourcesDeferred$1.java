package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.StreamSource;
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
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/data/model/StreamSource;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel$load$1$sourcesDeferred$1", f = "MovieDetailScreen.kt", i = {}, l = {292}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
final class MovieDetailViewModel$load$1$sourcesDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ MediaItem $cached;
    final /* synthetic */ boolean $fromAddon;
    int label;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$load$1$sourcesDeferred$1(MovieDetailViewModel movieDetailViewModel, boolean z, MediaItem mediaItem, Continuation<? super MovieDetailViewModel$load$1$sourcesDeferred$1> continuation) {
        super(2, continuation);
        this.this$0 = movieDetailViewModel;
        this.$fromAddon = z;
        this.$cached = mediaItem;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MovieDetailViewModel$load$1$sourcesDeferred$1(this.this$0, this.$fromAddon, this.$cached, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends StreamSource>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<StreamSource>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<StreamSource>> continuation) {
        return ((MovieDetailViewModel$load$1$sourcesDeferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object resolveSources;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                resolveSources = this.this$0.resolveSources(this.$fromAddon, this.$cached, this);
                return resolveSources == coroutine_suspended ? coroutine_suspended : resolveSources;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
