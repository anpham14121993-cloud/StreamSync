package com.streamsync.app.ui.detail;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MovieDetailScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel", f = "MovieDetailScreen.kt", i = {0, 1}, l = {368, 370, 372}, m = "resolveDetail", n = {"this", "this"}, s = {"L$0", "L$0"})
/* loaded from: classes16.dex */
public final class MovieDetailViewModel$resolveDetail$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$resolveDetail$1(MovieDetailViewModel movieDetailViewModel, Continuation<? super MovieDetailViewModel$resolveDetail$1> continuation) {
        super(continuation);
        this.this$0 = movieDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object resolveDetail;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        resolveDetail = this.this$0.resolveDetail(false, this);
        return resolveDetail;
    }
}
