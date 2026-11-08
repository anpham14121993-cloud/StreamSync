package com.streamsync.app.ui.settings;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.UiSettings;
import com.streamsync.app.settings.UiSettingsStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppearanceScreen.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"AppearanceScreen", "", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug", "settings", "Lcom/streamsync/app/settings/UiSettings;"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class AppearanceScreenKt {
    public static final void AppearanceScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(-1957319222);
        ComposerKt.sourceInformation($composer3, "C(AppearanceScreen)46@2040L7,49@2201L41,50@2278L29,53@2386L8114:AppearanceScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1957319222, $dirty2, -1, "com.streamsync.app.ui.settings.AppearanceScreen (AppearanceScreen.kt:45)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(1893152029);
            ComposerKt.sourceInformation($composer3, "CC(remember):AppearanceScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = UiSettingsStore.INSTANCE.get(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final UiSettingsStore store = (UiSettingsStore) value$iv;
            $composer3.endReplaceableGroup();
            final State settings$delegate = FlowExtKt.collectAsStateWithLifecycle(store.getSettings(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            final boolean supportsDynamic = Build.VERSION.SDK_INT >= 31;
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, -352423026, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$1
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C55@2427L424:AppearanceScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-352423026, $changed2, -1, "com.streamsync.app.ui.settings.AppearanceScreen.<anonymous> (AppearanceScreen.kt:55)");
                        }
                        Function2<Composer, Integer, Unit> m6674getLambda1$app_mobileDebug = ComposableSingletons$AppearanceScreenKt.INSTANCE.m6674getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, -1855515000, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C58@2540L151:AppearanceScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1855515000, $changed3, -1, "com.streamsync.app.ui.settings.AppearanceScreen.<anonymous>.<anonymous> (AppearanceScreen.kt:58)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$AppearanceScreenKt.INSTANCE.m6675getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        });
                        final UiSettingsStore uiSettingsStore = store;
                        AppBarKt.TopAppBar(m6674getLambda1$app_mobileDebug, null, composableLambda, ComposableLambdaKt.composableLambda($composer4, 292072191, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$1.2
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                invoke(rowScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(RowScope TopAppBar, Composer $composer5, int $changed3) {
                                Object value$iv2;
                                Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
                                ComposerKt.sourceInformation($composer5, "C63@2780L17,63@2759L60:AppearanceScreen.kt#bv4dau");
                                if (($changed3 & 17) == 16 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(292072191, $changed3, -1, "com.streamsync.app.ui.settings.AppearanceScreen.<anonymous>.<anonymous> (AppearanceScreen.kt:63)");
                                }
                                $composer5.startReplaceableGroup(-1874805021);
                                ComposerKt.sourceInformation($composer5, "CC(remember):AppearanceScreen.kt#9igjgp");
                                boolean invalid$iv = $composer5.changedInstance(UiSettingsStore.this);
                                final UiSettingsStore uiSettingsStore2 = UiSettingsStore.this;
                                Object it$iv2 = $composer5.rememberedValue();
                                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv2 = new Function0<Unit>() { // from class: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$1$2$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            UiSettingsStore.this.reset();
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv2);
                                } else {
                                    value$iv2 = it$iv2;
                                }
                                $composer5.endReplaceableGroup();
                                ButtonKt.TextButton((Function0) value$iv2, null, false, null, null, null, null, null, null, ComposableSingletons$AppearanceScreenKt.INSTANCE.m6676getLambda3$app_mobileDebug(), $composer5, 805306368, 510);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), null, null, null, $composer4, 3462, 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, -1244039911, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
                    invoke(paddingValues, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:73:0x045b, code lost:
                
                    if (r10 == false) goto L87;
                 */
                /* JADX WARN: Removed duplicated region for block: B:105:0x06ae  */
                /* JADX WARN: Removed duplicated region for block: B:110:0x0738  */
                /* JADX WARN: Removed duplicated region for block: B:115:0x0780  */
                /* JADX WARN: Removed duplicated region for block: B:130:0x08d5  */
                /* JADX WARN: Removed duplicated region for block: B:135:0x094f  */
                /* JADX WARN: Removed duplicated region for block: B:140:0x09cb  */
                /* JADX WARN: Removed duplicated region for block: B:145:0x0a78  */
                /* JADX WARN: Removed duplicated region for block: B:147:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:154:0x0866  */
                /* JADX WARN: Removed duplicated region for block: B:159:0x0468  */
                /* JADX WARN: Removed duplicated region for block: B:164:0x03fd A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:165:0x03cc  */
                /* JADX WARN: Removed duplicated region for block: B:168:0x023f  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x022d  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x0239  */
                /* JADX WARN: Removed duplicated region for block: B:43:0x030a  */
                /* JADX WARN: Removed duplicated region for block: B:57:0x03a8 A[EDGE_INSN: B:57:0x03a8->B:58:0x03a8 BREAK  A[LOOP:0: B:41:0x0304->B:52:0x0363], SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:60:0x03c9  */
                /* JADX WARN: Removed duplicated region for block: B:63:0x03f0  */
                /* JADX WARN: Removed duplicated region for block: B:68:0x0444  */
                /* JADX WARN: Removed duplicated region for block: B:76:0x0463  */
                /* JADX WARN: Removed duplicated region for block: B:79:0x048c  */
                /* JADX WARN: Removed duplicated region for block: B:84:0x0511  */
                /* JADX WARN: Removed duplicated region for block: B:89:0x055a  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r78, androidx.compose.runtime.Composer r79, int r80) {
                    /*
                        Method dump skipped, instructions count: 2684
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
                }
            }), $composer3, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AppearanceScreenKt$AppearanceScreen$3
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
                    AppearanceScreenKt.AppearanceScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UiSettings AppearanceScreen$lambda$1(State<UiSettings> state) {
        Object thisObj$iv = state.getValue();
        return (UiSettings) thisObj$iv;
    }
}
