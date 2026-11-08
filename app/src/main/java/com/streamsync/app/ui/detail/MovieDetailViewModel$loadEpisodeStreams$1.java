package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel$loadEpisodeStreams$1", f = "MovieDetailScreen.kt", i = {}, l = {171}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
public final class MovieDetailViewModel$loadEpisodeStreams$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $addonId;
    final /* synthetic */ SeriesVideo $episode;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$loadEpisodeStreams$1(MovieDetailViewModel movieDetailViewModel, SeriesVideo seriesVideo, String str, Continuation<? super MovieDetailViewModel$loadEpisodeStreams$1> continuation) {
        super(2, continuation);
        this.this$0 = movieDetailViewModel;
        this.$episode = seriesVideo;
        this.$addonId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MovieDetailViewModel$loadEpisodeStreams$1 movieDetailViewModel$loadEpisodeStreams$1 = new MovieDetailViewModel$loadEpisodeStreams$1(this.this$0, this.$episode, this.$addonId, continuation);
        movieDetailViewModel$loadEpisodeStreams$1.L$0 = obj;
        return movieDetailViewModel$loadEpisodeStreams$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MovieDetailViewModel$loadEpisodeStreams$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00fa  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel$loadEpisodeStreams$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
