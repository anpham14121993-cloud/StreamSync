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
@DebugMetadata(c = "com.streamsync.app.ui.detail.MovieDetailViewModel$load$1", f = "MovieDetailScreen.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3}, l = {300, 318, 348, 355, 362}, m = "invokeSuspend", n = {"$this$launch", "sourcesDeferred", "fromAddon", "$this$launch", "detail", "fromAddon", "$this$launch", "detail", "$this$launch", "prefix", "destination$iv$iv"}, s = {"L$0", "L$1", "Z$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$0", "L$1", "L$3"})
/* loaded from: classes16.dex */
public final class MovieDetailViewModel$load$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    boolean Z$0;
    int label;
    final /* synthetic */ MovieDetailViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailViewModel$load$1(MovieDetailViewModel movieDetailViewModel, Continuation<? super MovieDetailViewModel$load$1> continuation) {
        super(2, continuation);
        this.this$0 = movieDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MovieDetailViewModel$load$1 movieDetailViewModel$load$1 = new MovieDetailViewModel$load$1(this.this$0, continuation);
        movieDetailViewModel$load$1.L$0 = obj;
        return movieDetailViewModel$load$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MovieDetailViewModel$load$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:28|(1:29)|30|31|32|33|(1:35)(13:36|13|14|(1:16)(1:50)|17|18|19|(1:21)|22|(1:24)(1:49)|25|26|(3:44|45|(1:47)(3:48|7|8))(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x02e3, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x02e4, code lost:
    
        r7 = r21;
        r16 = r3;
        r3 = r14;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x017a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x02c8 A[Catch: all -> 0x02e0, TryCatch #0 {all -> 0x02e0, blocks: (B:14:0x02c4, B:16:0x02c8, B:17:0x02d2), top: B:13:0x02c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x035c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x020b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01c5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x02bd -> B:13:0x02c4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x02f0 -> B:19:0x0302). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 882
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel$load$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
