package com.streamsync.app.ui.detail;

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
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel$refreshStreams$1", f = "MovieDetailScreen.kt", i = {}, l = {225}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
public final class MovieDetailViewModel$refreshStreams$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$refreshStreams$1(MovieDetailViewModel movieDetailViewModel, Continuation<? super MovieDetailViewModel$refreshStreams$1> continuation) {
        super(2, continuation);
        this.this$0 = movieDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MovieDetailViewModel$refreshStreams$1 movieDetailViewModel$refreshStreams$1 = new MovieDetailViewModel$refreshStreams$1(this.this$0, continuation);
        movieDetailViewModel$refreshStreams$1.L$0 = obj;
        return movieDetailViewModel$refreshStreams$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MovieDetailViewModel$refreshStreams$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0088  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            switch(r1) {
                case 0: goto L1d;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L12:
            r0 = r12
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L1b
            r3 = r1
            r1 = r0
            r0 = r13
            goto L5f
        L1b:
            r1 = move-exception
            goto L70
        L1d:
            kotlin.ResultKt.throwOnFailure(r13)
            r1 = r12
            java.lang.Object r3 = r1.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            com.streamsync.app.ui.detail.MovieDetailViewModel r4 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r4 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$get_streamsLoading$p(r4)
            r5 = 1
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            r4.setValue(r6)
            com.streamsync.app.ui.detail.MovieDetailViewModel r4 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r4 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$get_item$p(r4)
            java.lang.Object r4 = r4.getValue()
            com.streamsync.app.data.model.MediaItem r4 = (com.streamsync.app.data.model.MediaItem) r4
            com.streamsync.app.ui.detail.MovieDetailViewModel r6 = r1.this$0
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L6c
            r3 = 0
            java.lang.String r7 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$getMediaId$p(r6)     // Catch: java.lang.Throwable -> L6c
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch: java.lang.Throwable -> L6c
            java.lang.String r8 = ":"
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch: java.lang.Throwable -> L6c
            r9 = 2
            r10 = 0
            boolean r7 = kotlin.text.StringsKt.contains$default(r7, r8, r2, r9, r10)     // Catch: java.lang.Throwable -> L6c
            r1.label = r5     // Catch: java.lang.Throwable -> L6c
            java.lang.Object r5 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$resolveSources(r6, r7, r4, r1)     // Catch: java.lang.Throwable -> L6c
            if (r5 != r0) goto L5d
            return r0
        L5d:
            r0 = r13
            r13 = r5
        L5f:
            java.util.List r13 = (java.util.List) r13     // Catch: java.lang.Throwable -> L66
            java.lang.Object r13 = kotlin.Result.m6717constructorimpl(r13)     // Catch: java.lang.Throwable -> L66
            goto L7e
        L66:
            r13 = move-exception
            r11 = r1
            r1 = r13
            r13 = r0
            r0 = r11
            goto L70
        L6c:
            r0 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
        L70:
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
            r11 = r0
            r0 = r13
            r13 = r1
            r1 = r11
        L7e:
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            boolean r4 = kotlin.Result.m6723isFailureimpl(r13)
            if (r4 == 0) goto L89
            r13 = r3
        L89:
            java.util.List r13 = (java.util.List) r13
            com.streamsync.app.ui.detail.MovieDetailViewModel r3 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$get_streams$p(r3)
            r3.setValue(r13)
            com.streamsync.app.ui.detail.MovieDetailViewModel r3 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$get_streamsLoading$p(r3)
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r3.setValue(r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel$refreshStreams$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
