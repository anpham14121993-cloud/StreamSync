package com.streamsync.app.ui.search;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.search.SearchViewModel$submit$1", f = "SearchScreen.kt", i = {}, l = {WebSocketProtocol.PAYLOAD_SHORT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes14.dex */
public final class SearchViewModel$submit$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $value;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$submit$1(SearchViewModel searchViewModel, String str, Continuation<? super SearchViewModel$submit$1> continuation) {
        super(2, continuation);
        this.this$0 = searchViewModel;
        this.$value = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SearchViewModel$submit$1 searchViewModel$submit$1 = new SearchViewModel$submit$1(this.this$0, this.$value, continuation);
        searchViewModel$submit$1.L$0 = obj;
        return searchViewModel$submit$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchViewModel$submit$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 1
            switch(r1) {
                case 0: goto L1d;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L12:
            r0 = r11
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L1b
            r9 = r1
            r1 = r0
            r0 = r12
            goto L41
        L1b:
            r1 = move-exception
            goto L52
        L1d:
            kotlin.ResultKt.throwOnFailure(r12)
            r1 = r11
            java.lang.Object r3 = r1.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            com.streamsync.app.ui.search.SearchViewModel r4 = r1.this$0
            java.lang.String r5 = r1.$value
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L4e
            r9 = 0
            com.streamsync.app.data.repository.AddonRepository r3 = com.streamsync.app.ui.search.SearchViewModel.access$getRepo$p(r4)     // Catch: java.lang.Throwable -> L4e
            r1.label = r2     // Catch: java.lang.Throwable -> L4e
            r6 = 0
            r7 = 2
            r8 = 0
            r4 = r5
            r5 = r6
            r6 = r1
            java.lang.Object r3 = com.streamsync.app.data.repository.AddonRepository.searchRows$default(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L4e
            if (r3 != r0) goto L3f
            return r0
        L3f:
            r0 = r12
            r12 = r3
        L41:
            java.util.List r12 = (java.util.List) r12     // Catch: java.lang.Throwable -> L48
            java.lang.Object r12 = kotlin.Result.m6717constructorimpl(r12)     // Catch: java.lang.Throwable -> L48
            goto L60
        L48:
            r12 = move-exception
            r10 = r1
            r1 = r12
            r12 = r0
            r0 = r10
            goto L52
        L4e:
            r0 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
        L52:
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
            r10 = r0
            r0 = r12
            r12 = r1
            r1 = r10
        L60:
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            boolean r4 = kotlin.Result.m6723isFailureimpl(r12)
            if (r4 == 0) goto L6b
            r12 = r3
        L6b:
            java.util.List r12 = (java.util.List) r12
            com.streamsync.app.ui.search.SearchViewModel r3 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.streamsync.app.ui.search.SearchViewModel.access$get_query$p(r3)
            java.lang.Object r3 = r3.getValue()
            java.lang.String r4 = r1.$value
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 != 0) goto L82
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        L82:
            com.streamsync.app.ui.search.SearchViewModel r3 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.streamsync.app.ui.search.SearchViewModel.access$get_rows$p(r3)
            r3.setValue(r12)
            com.streamsync.app.ui.search.SearchViewModel r3 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.streamsync.app.ui.search.SearchViewModel.access$get_searched$p(r3)
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r3.setValue(r2)
            com.streamsync.app.ui.search.SearchViewModel r2 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r2 = com.streamsync.app.ui.search.SearchViewModel.access$get_loading$p(r2)
            r3 = 0
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r2.setValue(r3)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.search.SearchViewModel$submit$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
