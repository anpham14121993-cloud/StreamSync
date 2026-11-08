package com.streamsync.app;

import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavHostController;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.FileLog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.MainActivityKt$AppNavHost$1$1", f = "MainActivity.kt", i = {}, l = {482}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
final class MainActivityKt$AppNavHost$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NavHostController $navController;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$AppNavHost$1$1(NavHostController navHostController, Continuation<? super MainActivityKt$AppNavHost$1$1> continuation) {
        super(2, continuation);
        this.$navController = navHostController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$AppNavHost$1$1(this.$navController, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$AppNavHost$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Flow<NavBackStackEntry> currentBackStackEntryFlow = this.$navController.getCurrentBackStackEntryFlow();
                final NavHostController navHostController = this.$navController;
                this.label = 1;
                if (currentBackStackEntryFlow.collect(new FlowCollector() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$1$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((NavBackStackEntry) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(NavBackStackEntry entry, Continuation<? super Unit> continuation) {
                        FileLog.d$default(FileLog.INSTANCE, "Man hinh: " + entry.getDestination().getRoute() + " | sau " + (NavHostController.this.getPreviousBackStackEntry() != null) + " man", null, 2, null);
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
