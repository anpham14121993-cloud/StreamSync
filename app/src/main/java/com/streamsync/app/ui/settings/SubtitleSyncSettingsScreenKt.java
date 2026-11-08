package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.WavUtil;
import androidx.profileinstaller.ProfileVerifier;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.SyncServerStore;
import com.streamsync.app.subtitle.autosync.GeminiApiKeyStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SubtitleSyncSettingsScreen.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\t\u001a#\u0010\n\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\f\u001a\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0002¨\u0006\u000f²\u0006\n\u0010\u0010\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\f\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\f\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u008a\u008e\u0002"}, d2 = {"InfoLine", "", "label", "", "value", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "SubtitleSyncSettingsScreen", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TextButtonish", "onClick", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "languageLabel", "code", "app_mobileDebug", "keyInput", "revealed", "", "savedMessage", "checking", "checkResult"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class SubtitleSyncSettingsScreenKt {
    public static final void SubtitleSyncSettingsScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Object value$iv7;
        Object value$iv$iv$iv;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(-734410458);
        ComposerKt.sourceInformation($composer3, "C(SubtitleSyncSettingsScreen)60@2750L7,61@2777L39,62@2839L37,64@2898L31,65@2950L34,66@3009L42,67@3072L34,68@3130L42,69@3189L24,73@3259L7338:SubtitleSyncSettingsScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-734410458, $dirty2, -1, "com.streamsync.app.ui.settings.SubtitleSyncSettingsScreen (SubtitleSyncSettingsScreen.kt:59)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            $composer3.startReplaceableGroup(-376959539);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new GeminiApiKeyStore(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final GeminiApiKeyStore keyStore = (GeminiApiKeyStore) value$iv;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-376957557);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new SyncServerStore(context);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final SyncServerStore serverStore = (SyncServerStore) value$iv2;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-376955675);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            final MutableState keyInput$delegate = (MutableState) value$iv3;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-376954008);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final MutableState revealed$delegate = (MutableState) value$iv4;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-376952112);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv5 = $composer3.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final MutableState savedMessage$delegate = (MutableState) value$iv5;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-376950104);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv6 = $composer3.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            final MutableState checking$delegate = (MutableState) value$iv6;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-376948240);
            ComposerKt.sourceInformation($composer3, "CC(remember):SubtitleSyncSettingsScreen.kt#9igjgp");
            Object it$iv7 = $composer3.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv7;
            }
            final MutableState checkResult$delegate = (MutableState) value$iv7;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(773894976);
            ComposerKt.sourceInformation($composer3, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
            $composer3.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation($composer3, "CC(remember):Composables.kt#9igjgp");
            Object it$iv$iv$iv = $composer3.rememberedValue();
            if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3));
                $composer3.updateRememberedValue(value$iv$iv$iv);
            } else {
                value$iv$iv$iv = it$iv$iv$iv;
            }
            $composer3.endReplaceableGroup();
            CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
            final CoroutineScope scope = wrapper$iv.getCoroutineScope();
            $composer3.endReplaceableGroup();
            final boolean hasKey = keyStore.isConfigured();
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, 703202538, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$SubtitleSyncSettingsScreen$1
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
                    ComposerKt.sourceInformation($composer4, "C75@3300L301:SubtitleSyncSettingsScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(703202538, $changed2, -1, "com.streamsync.app.ui.settings.SubtitleSyncSettingsScreen.<anonymous> (SubtitleSyncSettingsScreen.kt:75)");
                        }
                        Function2<Composer, Integer, Unit> m6705getLambda1$app_mobileDebug = ComposableSingletons$SubtitleSyncSettingsScreenKt.INSTANCE.m6705getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        AppBarKt.TopAppBar(m6705getLambda1$app_mobileDebug, null, ComposableLambdaKt.composableLambda($composer4, -1525848348, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$SubtitleSyncSettingsScreen$1.1
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
                                ComposerKt.sourceInformation($composer5, "C78@3418L151:SubtitleSyncSettingsScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1525848348, $changed3, -1, "com.streamsync.app.ui.settings.SubtitleSyncSettingsScreen.<anonymous>.<anonymous> (SubtitleSyncSettingsScreen.kt:78)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$SubtitleSyncSettingsScreenKt.INSTANCE.m6706getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        }), null, null, null, null, $composer4, 390, 122);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, 1766314741, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$SubtitleSyncSettingsScreen$2
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

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:102:0x0a3f  */
                /* JADX WARN: Removed duplicated region for block: B:105:0x07fa A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:107:0x079a A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:109:0x070c A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:110:0x06c3  */
                /* JADX WARN: Removed duplicated region for block: B:111:0x05c2  */
                /* JADX WARN: Removed duplicated region for block: B:112:0x0552  */
                /* JADX WARN: Removed duplicated region for block: B:114:0x043c A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:115:0x03f3  */
                /* JADX WARN: Removed duplicated region for block: B:117:0x032b A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:120:0x024d  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x023b  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x0247  */
                /* JADX WARN: Removed duplicated region for block: B:42:0x031a  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x03e1  */
                /* JADX WARN: Removed duplicated region for block: B:50:0x03ed  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x0426  */
                /* JADX WARN: Removed duplicated region for block: B:58:0x0549  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x05b4  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x06b1  */
                /* JADX WARN: Removed duplicated region for block: B:67:0x06bd  */
                /* JADX WARN: Removed duplicated region for block: B:70:0x06f6  */
                /* JADX WARN: Removed duplicated region for block: B:75:0x078d  */
                /* JADX WARN: Removed duplicated region for block: B:80:0x07ed  */
                /* JADX WARN: Removed duplicated region for block: B:85:0x0893  */
                /* JADX WARN: Removed duplicated region for block: B:90:0x0908  */
                /* JADX WARN: Removed duplicated region for block: B:93:0x0977  */
                /* JADX WARN: Removed duplicated region for block: B:96:0x0a3a  */
                /* JADX WARN: Removed duplicated region for block: B:99:0x0a7f  */
                /* JADX WARN: Type inference failed for: r0v80, types: [androidx.compose.ui.text.input.VisualTransformation] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r136, androidx.compose.runtime.Composer r137, int r138) {
                    /*
                        Method dump skipped, instructions count: 2691
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$SubtitleSyncSettingsScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$SubtitleSyncSettingsScreen$3
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
                    SubtitleSyncSettingsScreenKt.SubtitleSyncSettingsScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SubtitleSyncSettingsScreen$lambda$3(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SubtitleSyncSettingsScreen$lambda$6(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SubtitleSyncSettingsScreen$lambda$7(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SubtitleSyncSettingsScreen$lambda$9(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SubtitleSyncSettingsScreen$lambda$12(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SubtitleSyncSettingsScreen$lambda$13(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SubtitleSyncSettingsScreen$lambda$15(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TextButtonish(final Function0<Unit> function0, final String label, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-2094268150);
        ComposerKt.sourceInformation($composer3, "C(TextButtonish)P(1)245@10708L45:SubtitleSyncSettingsScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(label) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2094268150, $dirty2, -1, "com.streamsync.app.ui.settings.TextButtonish (SubtitleSyncSettingsScreen.kt:244)");
            }
            $composer2 = $composer3;
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableLambdaKt.composableLambda($composer3, 559359815, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$TextButtonish$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                    invoke(rowScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RowScope TextButton, Composer $composer4, int $changed2) {
                    Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
                    ComposerKt.sourceInformation($composer4, "C245@10740L11:SubtitleSyncSettingsScreen.kt#bv4dau");
                    if (($changed2 & 17) == 16 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(559359815, $changed2, -1, "com.streamsync.app.ui.settings.TextButtonish.<anonymous> (SubtitleSyncSettingsScreen.kt:245)");
                    }
                    TextKt.m2127Text4IGK_g(label, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer3, ($dirty2 & 14) | 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$TextButtonish$2
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
                    SubtitleSyncSettingsScreenKt.TextButtonish(function0, label, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void InfoLine(final String label, final String value, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(764490930);
        ComposerKt.sourceInformation($composer3, "C(InfoLine)250@10826L359:SubtitleSyncSettingsScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(764490930, $dirty2, -1, "com.streamsync.app.ui.settings.InfoLine (SubtitleSyncSettingsScreen.kt:249)");
            }
            Modifier modifier$iv = PaddingKt.m565paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4));
            $composer3.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            int $changed$iv$iv = (6 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(factory$iv$iv$iv);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2939constructorimpl($composer3);
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
            }
            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2930boximpl(SkippableUpdater.m2931constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i2 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -257792836, "C255@10987L10,255@10953L55,258@11076L10,256@11017L162:SubtitleSyncSettingsScreen.kt#bv4dau");
            $composer2 = $composer3;
            TextKt.m2127Text4IGK_g(label, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall(), $composer3, $dirty2 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            TextKt.m2127Text4IGK_g(value, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5678getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyMedium(), $composer3, ($dirty2 >> 3) & 14, 3120, 55294);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt$InfoLine$2
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

                public final void invoke(Composer composer, int i3) {
                    SubtitleSyncSettingsScreenKt.InfoLine(label, value, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    private static final String languageLabel(String code) {
        switch (code.hashCode()) {
            case 3201:
                if (code.equals(TranslateLanguage.GERMAN)) {
                    return "Tiếng Đức";
                }
                return code;
            case 3241:
                if (code.equals(TranslateLanguage.ENGLISH)) {
                    return "Tiếng Anh";
                }
                return code;
            case 3246:
                if (code.equals(TranslateLanguage.SPANISH)) {
                    return "Tiếng Tây Ban Nha";
                }
                return code;
            case 3276:
                if (code.equals(TranslateLanguage.FRENCH)) {
                    return "Tiếng Pháp";
                }
                return code;
            case 3355:
                if (code.equals("id")) {
                    return "Tiếng Indonesia";
                }
                return code;
            case 3383:
                if (code.equals(TranslateLanguage.JAPANESE)) {
                    return "Tiếng Nhật";
                }
                return code;
            case 3428:
                if (code.equals(TranslateLanguage.KOREAN)) {
                    return "Tiếng Hàn";
                }
                return code;
            case 3763:
                if (code.equals("vi")) {
                    return "Tiếng Việt";
                }
                return code;
            case 3886:
                if (code.equals(TranslateLanguage.CHINESE)) {
                    return "Tiếng Trung";
                }
                return code;
            default:
                return code;
        }
    }
}
