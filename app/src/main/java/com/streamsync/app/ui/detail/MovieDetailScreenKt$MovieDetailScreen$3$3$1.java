package com.streamsync.app.ui.detail;

import androidx.compose.foundation.pager.PagerState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailScreenKt$MovieDetailScreen$3$3$1", f = "MovieDetailScreen.kt", i = {}, l = {702}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
final class MovieDetailScreenKt$MovieDetailScreen$3$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<List<SeriesVideo>> $episodesNow;
    final /* synthetic */ PagerState $pagerState;
    final /* synthetic */ MovieDetailViewModel $viewModel;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MovieDetailScreenKt$MovieDetailScreen$3$3$1(PagerState pagerState, State<? extends List<SeriesVideo>> state, MovieDetailViewModel movieDetailViewModel, Continuation<? super MovieDetailScreenKt$MovieDetailScreen$3$3$1> continuation) {
        super(2, continuation);
        this.$pagerState = pagerState;
        this.$episodesNow = state;
        this.$viewModel = movieDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MovieDetailScreenKt$MovieDetailScreen$3$3$1(this.$pagerState, this.$episodesNow, this.$viewModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MovieDetailScreenKt$MovieDetailScreen$3$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final PagerState pagerState = this.$pagerState;
                final State<List<SeriesVideo>> state = this.$episodesNow;
                Flow distinctUntilChanged = FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0<String>() { // from class: com.streamsync.app.ui.detail.MovieDetailScreenKt$MovieDetailScreen$3$3$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        if (PagerState.this.isScrollInProgress()) {
                            return null;
                        }
                        return EpisodeAutoLoad.INSTANCE.idAt(state.getValue(), PagerState.this.getCurrentPage());
                    }
                }));
                final State<List<SeriesVideo>> state2 = this.$episodesNow;
                final MovieDetailViewModel movieDetailViewModel = this.$viewModel;
                this.label = 1;
                if (distinctUntilChanged.collect(new FlowCollector() { // from class: com.streamsync.app.ui.detail.MovieDetailScreenKt$MovieDetailScreen$3$3$1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((String) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(String episodeId, Continuation<? super Unit> continuation) {
                        SeriesVideo it = EpisodeAutoLoad.INSTANCE.find(state2.getValue(), episodeId);
                        if (it != null) {
                            movieDetailViewModel.loadEpisodeStreams(it);
                        }
                        return Unit.INSTANCE;
                    }
                }, this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
