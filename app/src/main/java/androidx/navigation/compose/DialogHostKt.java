package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateHolderKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.platform.InspectionModeKt;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.compose.DialogNavigator;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DialogHost.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0001¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00070\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0001¢\u0006\u0002\u0010\r¨\u0006\u000e²\u0006\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u008a\u0084\u0002²\u0006\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012X\u008a\u0084\u0002"}, d2 = {"DialogHost", "", "dialogNavigator", "Landroidx/navigation/compose/DialogNavigator;", "(Landroidx/navigation/compose/DialogNavigator;Landroidx/compose/runtime/Composer;I)V", "rememberVisibleList", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/navigation/NavBackStackEntry;", "backStack", "", "(Ljava/util/Collection;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/snapshots/SnapshotStateList;", "PopulateVisibleList", "", "(Ljava/util/List;Ljava/util/Collection;Landroidx/compose/runtime/Composer;I)V", "navigation-compose_release", "dialogBackStack", "", "transitionInProgress", ""}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class DialogHostKt {
    public static final void DialogHost(final DialogNavigator dialogNavigator, Composer $composer, final int $changed) {
        Object value$iv$iv;
        Object value$iv$iv2;
        Composer $composer2 = $composer.startRestartGroup(294589392);
        ComposerKt.sourceInformation($composer2, "C(DialogHost)41@1668L29,42@1751L16,43@1795L36,44@1853L36,46@1960L16,47@2004L52,73@3243L260,73@3188L315:DialogHost.kt#opm8kd");
        int $dirty = $changed;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(dialogNavigator) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 11) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(294589392, $dirty2, -1, "androidx.navigation.compose.DialogHost (DialogHost.kt:40)");
            }
            final SaveableStateHolder saveableStateHolder = SaveableStateHolderKt.rememberSaveableStateHolder($composer2, 0);
            Continuation continuation = null;
            boolean z = true;
            State dialogBackStack$delegate = SnapshotStateKt.collectAsState(dialogNavigator.getBackStack$navigation_compose_release(), null, $composer2, 8, 1);
            Iterable visibleBackStack = rememberVisibleList(DialogHost$lambda$0(dialogBackStack$delegate), $composer2, 8);
            PopulateVisibleList((List) visibleBackStack, DialogHost$lambda$0(dialogBackStack$delegate), $composer2, 64);
            State transitionInProgress$delegate = SnapshotStateKt.collectAsState(dialogNavigator.getTransitionInProgress$navigation_compose_release(), null, $composer2, 8, 1);
            $composer2.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer2, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = SnapshotStateKt.mutableStateListOf();
                $composer2.updateRememberedValue(value$iv$iv);
            } else {
                value$iv$iv = it$iv$iv;
            }
            $composer2.endReplaceableGroup();
            SnapshotStateList dialogsToDispose = (SnapshotStateList) value$iv$iv;
            $composer2.startReplaceableGroup(875188318);
            ComposerKt.sourceInformation($composer2, "*51@2183L736");
            Iterable $this$forEach$iv = visibleBackStack;
            for (Object element$iv : $this$forEach$iv) {
                final NavBackStackEntry backStackEntry = (NavBackStackEntry) element$iv;
                NavDestination destination = backStackEntry.getDestination();
                Intrinsics.checkNotNull(destination, "null cannot be cast to non-null type androidx.navigation.compose.DialogNavigator.Destination");
                final DialogNavigator.Destination destination2 = (DialogNavigator.Destination) destination;
                final SnapshotStateList dialogsToDispose2 = dialogsToDispose;
                AndroidDialog_androidKt.Dialog(new Function0<Unit>() { // from class: androidx.navigation.compose.DialogHostKt$DialogHost$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        DialogNavigator.this.dismiss$navigation_compose_release(backStackEntry);
                    }
                }, destination2.getDialogProperties(), ComposableLambdaKt.composableLambda($composer2, 1129586364, z, new Function2<Composer, Integer, Unit>() { // from class: androidx.navigation.compose.DialogHostKt$DialogHost$1$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C55@2345L280,65@2801L108:DialogHost.kt#opm8kd");
                        if (($changed2 & 11) != 2 || !$composer3.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1129586364, $changed2, -1, "androidx.navigation.compose.DialogHost.<anonymous>.<anonymous> (DialogHost.kt:55)");
                            }
                            NavBackStackEntry navBackStackEntry = NavBackStackEntry.this;
                            final SnapshotStateList<NavBackStackEntry> snapshotStateList = dialogsToDispose2;
                            final NavBackStackEntry navBackStackEntry2 = NavBackStackEntry.this;
                            final DialogNavigator dialogNavigator2 = dialogNavigator;
                            EffectsKt.DisposableEffect(navBackStackEntry, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.navigation.compose.DialogHostKt$DialogHost$1$2.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                                    snapshotStateList.add(navBackStackEntry2);
                                    final DialogNavigator dialogNavigator3 = dialogNavigator2;
                                    final NavBackStackEntry navBackStackEntry3 = navBackStackEntry2;
                                    final SnapshotStateList<NavBackStackEntry> snapshotStateList2 = snapshotStateList;
                                    return new DisposableEffectResult() { // from class: androidx.navigation.compose.DialogHostKt$DialogHost$1$2$1$invoke$$inlined$onDispose$1
                                        @Override // androidx.compose.runtime.DisposableEffectResult
                                        public void dispose() {
                                            DialogNavigator.this.onTransitionComplete$navigation_compose_release(navBackStackEntry3);
                                            snapshotStateList2.remove(navBackStackEntry3);
                                        }
                                    };
                                }
                            }, $composer3, 8);
                            NavBackStackEntry navBackStackEntry3 = NavBackStackEntry.this;
                            SaveableStateHolder saveableStateHolder2 = saveableStateHolder;
                            final DialogNavigator.Destination destination3 = destination2;
                            final NavBackStackEntry navBackStackEntry4 = NavBackStackEntry.this;
                            NavBackStackEntryProviderKt.LocalOwnersProvider(navBackStackEntry3, saveableStateHolder2, ComposableLambdaKt.composableLambda($composer3, -497631156, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.navigation.compose.DialogHostKt$DialogHost$1$2.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer4, int $changed3) {
                                    ComposerKt.sourceInformation($composer4, "C66@2872L23:DialogHost.kt#opm8kd");
                                    if (($changed3 & 11) == 2 && $composer4.getSkipping()) {
                                        $composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-497631156, $changed3, -1, "androidx.navigation.compose.DialogHost.<anonymous>.<anonymous>.<anonymous> (DialogHost.kt:66)");
                                    }
                                    DialogNavigator.Destination.this.getContent$navigation_compose_release().invoke(navBackStackEntry4, $composer4, 8);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }), $composer3, 456);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer3.skipToGroupEnd();
                    }
                }), $composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                transitionInProgress$delegate = transitionInProgress$delegate;
                continuation = null;
                dialogsToDispose = dialogsToDispose2;
                z = z;
            }
            SnapshotStateList dialogsToDispose3 = dialogsToDispose;
            State transitionInProgress$delegate2 = transitionInProgress$delegate;
            Continuation continuation2 = continuation;
            $composer2.endReplaceableGroup();
            Set<NavBackStackEntry> DialogHost$lambda$1 = DialogHost$lambda$1(transitionInProgress$delegate2);
            int i = (($dirty2 << 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK;
            $composer2.startReplaceableGroup(1618982084);
            ComposerKt.sourceInformation($composer2, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean invalid$iv$iv = $composer2.changed(transitionInProgress$delegate2) | $composer2.changed(dialogNavigator) | $composer2.changed(dialogsToDispose3);
            Object it$iv$iv2 = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv$iv2 = new DialogHostKt$DialogHost$2$1(transitionInProgress$delegate2, dialogNavigator, dialogsToDispose3, continuation2);
                $composer2.updateRememberedValue(value$iv$iv2);
            } else {
                value$iv$iv2 = it$iv$iv2;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(DialogHost$lambda$1, dialogsToDispose3, (Function2) value$iv$iv2, $composer2, 568);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.navigation.compose.DialogHostKt$DialogHost$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i2) {
                DialogHostKt.DialogHost(DialogNavigator.this, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    private static final List<NavBackStackEntry> DialogHost$lambda$0(State<? extends List<NavBackStackEntry>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<NavBackStackEntry> DialogHost$lambda$1(State<? extends Set<NavBackStackEntry>> state) {
        Object thisObj$iv = state.getValue();
        return (Set) thisObj$iv;
    }

    public static final void PopulateVisibleList(final List<NavBackStackEntry> list, final Collection<NavBackStackEntry> collection, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1537894851);
        ComposerKt.sourceInformation($composer2, "C(PopulateVisibleList)87@3676L7,*89@3725L1023:DialogHost.kt#opm8kd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1537894851, $changed, -1, "androidx.navigation.compose.PopulateVisibleList (DialogHost.kt:86)");
        }
        ProvidableCompositionLocal<Boolean> localInspectionMode = InspectionModeKt.getLocalInspectionMode();
        ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer2.consume(localInspectionMode);
        ComposerKt.sourceInformationMarkerEnd($composer2);
        final boolean isInspecting = ((Boolean) consume).booleanValue();
        Collection<NavBackStackEntry> $this$forEach$iv = collection;
        for (Object element$iv : $this$forEach$iv) {
            final NavBackStackEntry entry = (NavBackStackEntry) element$iv;
            EffectsKt.DisposableEffect(entry.getLifecycle(), new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.navigation.compose.DialogHostKt$PopulateVisibleList$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                    final boolean z = isInspecting;
                    final List<NavBackStackEntry> list2 = list;
                    final NavBackStackEntry navBackStackEntry = NavBackStackEntry.this;
                    final LifecycleEventObserver observer = new LifecycleEventObserver() { // from class: androidx.navigation.compose.DialogHostKt$PopulateVisibleList$1$1$observer$1
                        @Override // androidx.lifecycle.LifecycleEventObserver
                        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                            if (z && !list2.contains(navBackStackEntry)) {
                                list2.add(navBackStackEntry);
                            }
                            if (event == Lifecycle.Event.ON_START && !list2.contains(navBackStackEntry)) {
                                list2.add(navBackStackEntry);
                            }
                            if (event == Lifecycle.Event.ON_STOP) {
                                list2.remove(navBackStackEntry);
                            }
                        }
                    };
                    NavBackStackEntry.this.getLifecycle().addObserver(observer);
                    final NavBackStackEntry navBackStackEntry2 = NavBackStackEntry.this;
                    return new DisposableEffectResult() { // from class: androidx.navigation.compose.DialogHostKt$PopulateVisibleList$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            NavBackStackEntry.this.getLifecycle().removeObserver(observer);
                        }
                    };
                }
            }, $composer2, 8);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.navigation.compose.DialogHostKt$PopulateVisibleList$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                DialogHostKt.PopulateVisibleList(list, collection, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
            }
        });
    }

    public static final SnapshotStateList<NavBackStackEntry> rememberVisibleList(Collection<NavBackStackEntry> collection, Composer $composer, int $changed) {
        Object value$iv$iv;
        boolean isInspecting;
        boolean isAtLeast;
        $composer.startReplaceableGroup(467378629);
        ComposerKt.sourceInformation($composer, "C(rememberVisibleList)121@4964L7,122@4983L399:DialogHost.kt#opm8kd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(467378629, $changed, -1, "androidx.navigation.compose.rememberVisibleList (DialogHost.kt:119)");
        }
        ProvidableCompositionLocal<Boolean> localInspectionMode = InspectionModeKt.getLocalInspectionMode();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localInspectionMode);
        ComposerKt.sourceInformationMarkerEnd($composer);
        boolean isInspecting2 = ((Boolean) consume).booleanValue();
        $composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation($composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean invalid$iv$iv = $composer.changed(collection);
        Object it$iv$iv = $composer.rememberedValue();
        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
            SnapshotStateList it = SnapshotStateKt.mutableStateListOf();
            Collection<NavBackStackEntry> $this$filter$iv = collection;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                NavBackStackEntry entry = (NavBackStackEntry) element$iv$iv;
                if (isInspecting2) {
                    isAtLeast = true;
                    isInspecting = isInspecting2;
                } else {
                    isInspecting = isInspecting2;
                    isAtLeast = entry.getLifecycle().getState().isAtLeast(Lifecycle.State.STARTED);
                }
                if (isAtLeast) {
                    destination$iv$iv.add(element$iv$iv);
                }
                isInspecting2 = isInspecting;
            }
            it.addAll((List) destination$iv$iv);
            value$iv$iv = it;
            $composer.updateRememberedValue(value$iv$iv);
        } else {
            value$iv$iv = it$iv$iv;
        }
        $composer.endReplaceableGroup();
        SnapshotStateList<NavBackStackEntry> snapshotStateList = (SnapshotStateList) value$iv$iv;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return snapshotStateList;
    }
}
