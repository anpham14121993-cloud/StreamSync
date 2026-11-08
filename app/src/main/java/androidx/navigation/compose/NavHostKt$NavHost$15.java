package androidx.navigation.compose;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.navigation.NavBackStackEntry;
import com.streamsync.app.settings.BufferCeiling;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
/* compiled from: NavHost.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "androidx.navigation.compose.NavHostKt$NavHost$15", f = "NavHost.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes24.dex */
public final class NavHostKt$NavHost$15 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ComposeNavigator $composeNavigator;
    final /* synthetic */ Transition<NavBackStackEntry> $transition;
    final /* synthetic */ State<List<NavBackStackEntry>> $visibleEntries$delegate;
    final /* synthetic */ Map<String, Float> $zIndices;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NavHostKt$NavHost$15(Transition<NavBackStackEntry> transition, Map<String, Float> map, State<? extends List<NavBackStackEntry>> state, ComposeNavigator composeNavigator, Continuation<? super NavHostKt$NavHost$15> continuation) {
        super(2, continuation);
        this.$transition = transition;
        this.$zIndices = map;
        this.$visibleEntries$delegate = state;
        this.$composeNavigator = composeNavigator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NavHostKt$NavHost$15(this.$transition, this.$zIndices, this.$visibleEntries$delegate, this.$composeNavigator, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NavHostKt$NavHost$15) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Iterable NavHost$lambda$6;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (Intrinsics.areEqual(this.$transition.getCurrentState(), this.$transition.getTargetState())) {
                    NavHost$lambda$6 = NavHostKt.NavHost$lambda$6(this.$visibleEntries$delegate);
                    Iterable $this$forEach$iv = NavHost$lambda$6;
                    ComposeNavigator composeNavigator = this.$composeNavigator;
                    for (Object element$iv : $this$forEach$iv) {
                        NavBackStackEntry entry = (NavBackStackEntry) element$iv;
                        composeNavigator.onTransitionComplete(entry);
                    }
                    Map $this$filterTo$iv$iv = this.$zIndices;
                    Transition<NavBackStackEntry> transition = this.$transition;
                    Map destination$iv$iv = new LinkedHashMap();
                    for (Map.Entry element$iv$iv : $this$filterTo$iv$iv.entrySet()) {
                        if (!Intrinsics.areEqual(element$iv$iv.getKey(), transition.getTargetState().getId())) {
                            destination$iv$iv.put(element$iv$iv.getKey(), element$iv$iv.getValue());
                        }
                    }
                    Map<String, Float> map = this.$zIndices;
                    for (Map.Entry element$iv2 : destination$iv$iv.entrySet()) {
                        map.remove(element$iv2.getKey());
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
