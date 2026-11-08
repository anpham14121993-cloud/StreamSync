package com.streamsync.app.ui.catalog;

import android.content.Context;
import com.streamsync.app.data.local.CatalogDiskCache;
import com.streamsync.app.data.model.CatalogRow;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CatalogScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$load$1", f = "CatalogScreen.kt", i = {0, 1, 2, 3, 3}, l = {260, 283, 290, 303}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "addonRows", "addonRows", "staticItems"}, s = {"L$0", "L$0", "L$0", "L$0", "L$1"})
/* loaded from: classes22.dex */
public final class CatalogViewModel$load$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $app;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ CatalogViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogViewModel$load$1(CatalogViewModel catalogViewModel, Context context, Continuation<? super CatalogViewModel$load$1> continuation) {
        super(2, continuation);
        this.this$0 = catalogViewModel;
        this.$app = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CatalogViewModel$load$1 catalogViewModel$load$1 = new CatalogViewModel$load$1(this.this$0, this.$app, continuation);
        catalogViewModel$load$1.L$0 = obj;
        return catalogViewModel$load$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CatalogViewModel$load$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x01fe, code lost:
    
        if ((!r0.isEmpty()) != false) goto L71;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000b. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x021f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0193 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0155 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x013e  */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v18, types: [java.util.List] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.CatalogViewModel$load$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CatalogScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$load$1$2", f = "CatalogScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.catalog.CatalogViewModel$load$1$2, reason: invalid class name */
    /* loaded from: classes22.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $app;
        final /* synthetic */ List<CatalogRow> $merged;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, List<CatalogRow> list, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$app = context;
            this.$merged = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$app, this.$merged, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CatalogDiskCache catalogDiskCache = CatalogDiskCache.INSTANCE;
                    Context app = this.$app;
                    Intrinsics.checkNotNullExpressionValue(app, "$app");
                    catalogDiskCache.save(app, this.$merged);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
