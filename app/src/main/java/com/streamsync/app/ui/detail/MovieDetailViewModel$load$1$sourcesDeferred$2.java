package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/data/model/StreamSource;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel$load$1$sourcesDeferred$2", f = "MovieDetailScreen.kt", i = {}, l = {295}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
final class MovieDetailViewModel$load$1$sourcesDeferred$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends StreamSource>>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$load$1$sourcesDeferred$2(MovieDetailViewModel movieDetailViewModel, Continuation<? super MovieDetailViewModel$load$1$sourcesDeferred$2> continuation) {
        super(2, continuation);
        this.this$0 = movieDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MovieDetailViewModel$load$1$sourcesDeferred$2 movieDetailViewModel$load$1$sourcesDeferred$2 = new MovieDetailViewModel$load$1$sourcesDeferred$2(this.this$0, continuation);
        movieDetailViewModel$load$1$sourcesDeferred$2.L$0 = obj;
        return movieDetailViewModel$load$1$sourcesDeferred$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends StreamSource>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<StreamSource>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<StreamSource>> continuation) {
        return ((MovieDetailViewModel$load$1$sourcesDeferred$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L1c;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L11:
            r0 = r7
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L1a
            r2 = r1
            r1 = r0
            r0 = r8
            goto L3d
        L1a:
            r1 = move-exception
            goto L50
        L1c:
            kotlin.ResultKt.throwOnFailure(r8)
            r1 = r7
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            com.streamsync.app.ui.detail.MovieDetailViewModel r3 = r1.this$0
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L4c
            r2 = 0
            com.streamsync.app.data.repository.ContentRepository r4 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$getContentRepo$p(r3)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r3 = com.streamsync.app.ui.detail.MovieDetailViewModel.access$getMediaId$p(r3)     // Catch: java.lang.Throwable -> L4c
            r5 = 1
            r1.label = r5     // Catch: java.lang.Throwable -> L4c
            java.lang.Object r3 = r4.getStreamSource(r3, r1)     // Catch: java.lang.Throwable -> L4c
            if (r3 != r0) goto L3b
            return r0
        L3b:
            r0 = r8
            r8 = r3
        L3d:
            java.util.List r8 = kotlin.collections.CollectionsKt.listOf(r8)     // Catch: java.lang.Throwable -> L46
            java.lang.Object r8 = kotlin.Result.m6717constructorimpl(r8)     // Catch: java.lang.Throwable -> L46
            goto L5e
        L46:
            r8 = move-exception
            r6 = r1
            r1 = r8
            r8 = r0
            r0 = r6
            goto L50
        L4c:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L50:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
            r6 = r0
            r0 = r8
            r8 = r1
            r1 = r6
        L5e:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            boolean r3 = kotlin.Result.m6723isFailureimpl(r8)
            if (r3 == 0) goto L69
            r8 = r2
        L69:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel$load$1$sourcesDeferred$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
