package com.streamsync.app.ui.settings;

import android.content.Context;
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
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.SubtitleStyle;
import com.streamsync.app.settings.SubtitleStyleStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubtitleStyleScreen.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"SubtitleStyleScreen", "", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug", TtmlNode.TAG_STYLE, "Lcom/streamsync/app/settings/SubtitleStyle;"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class SubtitleStyleScreenKt {
    public static final void SubtitleStyleScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(822812584);
        ComposerKt.sourceInformation($composer3, "C(SubtitleStyleScreen)49@2214L7,50@2238L44,51@2312L29,53@2347L5507:SubtitleStyleScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(822812584, $dirty2, -1, "com.streamsync.app.ui.settings.SubtitleStyleScreen (SubtitleStyleScreen.kt:48)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(-767824243);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleStyleScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SubtitleStyleStore.INSTANCE.get(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final SubtitleStyleStore store = (SubtitleStyleStore) value$iv;
            $composer3.endReplaceableGroup();
            final State style$delegate = FlowExtKt.collectAsStateWithLifecycle(store.getStyle(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, -560889236, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$1
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
                    ComposerKt.sourceInformation($composer4, "C55@2388L431:SubtitleStyleScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-560889236, $changed2, -1, "com.streamsync.app.ui.settings.SubtitleStyleScreen.<anonymous> (SubtitleStyleScreen.kt:55)");
                        }
                        Function2<Composer, Integer, Unit> m6702getLambda1$app_mobileDebug = ComposableSingletons$SubtitleStyleScreenKt.INSTANCE.m6702getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 1900970470, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$1.1
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
                                ComposerKt.sourceInformation($composer5, "C58@2508L151:SubtitleStyleScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1900970470, $changed3, -1, "com.streamsync.app.ui.settings.SubtitleStyleScreen.<anonymous>.<anonymous> (SubtitleStyleScreen.kt:58)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$SubtitleStyleScreenKt.INSTANCE.m6703getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        });
                        final SubtitleStyleStore subtitleStyleStore = store;
                        AppBarKt.TopAppBar(m6702getLambda1$app_mobileDebug, null, composableLambda, ComposableLambdaKt.composableLambda($composer4, -573136611, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$1.2
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
                                ComposerKt.sourceInformation($composer5, "C63@2748L17,63@2727L60:SubtitleStyleScreen.kt#bv4dau");
                                if (($changed3 & 17) == 16 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-573136611, $changed3, -1, "com.streamsync.app.ui.settings.SubtitleStyleScreen.<anonymous>.<anonymous> (SubtitleStyleScreen.kt:63)");
                                }
                                $composer5.startReplaceableGroup(-696837968);
                                ComposerKt.sourceInformation($composer5, "CC(remember):SubtitleStyleScreen.kt#9igjgp");
                                boolean invalid$iv = $composer5.changedInstance(SubtitleStyleStore.this);
                                final SubtitleStyleStore subtitleStyleStore2 = SubtitleStyleStore.this;
                                Object it$iv2 = $composer5.rememberedValue();
                                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv2 = new Function0<Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$1$2$1$1
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
                                            SubtitleStyleStore.this.reset();
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv2);
                                } else {
                                    value$iv2 = it$iv2;
                                }
                                $composer5.endReplaceableGroup();
                                ButtonKt.TextButton((Function0) value$iv2, null, false, null, null, null, null, null, null, ComposableSingletons$SubtitleStyleScreenKt.INSTANCE.m6704getLambda3$app_mobileDebug(), $composer5, 805306368, 510);
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
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, 2020208567, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$2
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

                /* JADX WARN: Removed duplicated region for block: B:101:0x0844  */
                /* JADX WARN: Removed duplicated region for block: B:110:0x08c0  */
                /* JADX WARN: Removed duplicated region for block: B:115:0x0911  */
                /* JADX WARN: Removed duplicated region for block: B:120:0x09b5  */
                /* JADX WARN: Removed duplicated region for block: B:122:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:131:0x041f A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:132:0x03d6  */
                /* JADX WARN: Removed duplicated region for block: B:135:0x022e  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x021c  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x0228  */
                /* JADX WARN: Removed duplicated region for block: B:42:0x03c4  */
                /* JADX WARN: Removed duplicated region for block: B:45:0x03d0  */
                /* JADX WARN: Removed duplicated region for block: B:48:0x0409  */
                /* JADX WARN: Removed duplicated region for block: B:54:0x04a7  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x0551 A[EDGE_INSN: B:64:0x0551->B:65:0x0551 BREAK  A[LOOP:0: B:52:0x049d->B:60:0x050d], SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:67:0x05d6  */
                /* JADX WARN: Removed duplicated region for block: B:72:0x064d  */
                /* JADX WARN: Removed duplicated region for block: B:77:0x06b8  */
                /* JADX WARN: Removed duplicated region for block: B:82:0x070d  */
                /* JADX WARN: Removed duplicated region for block: B:87:0x0756  */
                /* JADX WARN: Removed duplicated region for block: B:96:0x07ed  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r79, androidx.compose.runtime.Composer r80, int r81) {
                    /*
                        Method dump skipped, instructions count: 2489
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleStyleScreenKt$SubtitleStyleScreen$3
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
                    SubtitleStyleScreenKt.SubtitleStyleScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SubtitleStyle SubtitleStyleScreen$lambda$1(State<SubtitleStyle> state) {
        Object thisObj$iv = state.getValue();
        return (SubtitleStyle) thisObj$iv;
    }
}
