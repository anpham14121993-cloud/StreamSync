package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.IntegrationStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BadgesSettingsScreen.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\u0010\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u008a\u008e\u0002²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u000b\u001a\u00020\fX\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\bX\u008a\u008e\u0002²\u0006\u0018\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b\u0018\u00010\u000fX\u008a\u008e\u0002²\u0006\f\u0010\u0010\u001a\u0004\u0018\u00010\bX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\fX\u008a\u008e\u0002"}, d2 = {"BadgesSettingsScreen", "", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug", "urls", "", "", "selected", "", "enabled", "", "newUrl", "notice", "Lkotlin/Pair;", "previewUrl", "previewLoading"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class BadgesSettingsScreenKt {
    public static final void BadgesSettingsScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv$iv$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Object value$iv7;
        Object value$iv8;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(357054828);
        ComposerKt.sourceInformation($composer3, "C(BadgesSettingsScreen)73@3240L7,74@3264L38,75@3319L24,79@3482L45,80@3548L46,81@3614L48,82@3681L31,83@3731L57,86@3868L42,87@3937L34,91@4031L10608:BadgesSettingsScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(357054828, $dirty2, -1, "com.streamsync.app.ui.settings.BadgesSettingsScreen (BadgesSettingsScreen.kt:72)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(968668681);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new IntegrationStore(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final IntegrationStore store = (IntegrationStore) value$iv;
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
            $composer3.startReplaceableGroup(968675664);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getBadgesUrls(), null, 2, null);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableState urls$delegate = (MutableState) value$iv2;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(968677777);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf(store.getBadgesIndex()), null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            final MutableState selected$delegate = (MutableState) value$iv3;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(968679891);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.getBadgesEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final MutableState enabled$delegate = (MutableState) value$iv4;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(968682018);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv5 = $composer3.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final MutableState newUrl$delegate = (MutableState) value$iv5;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(968683644);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv6 = $composer3.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            final MutableState notice$delegate = (MutableState) value$iv6;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(968688013);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv7 = $composer3.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv7;
            }
            final MutableState previewUrl$delegate = (MutableState) value$iv7;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(968690213);
            ComposerKt.sourceInformation($composer3, "CC(remember):BadgesSettingsScreen.kt#9igjgp");
            Object it$iv8 = $composer3.rememberedValue();
            if (it$iv8 == Composer.INSTANCE.getEmpty()) {
                value$iv8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(value$iv8);
            } else {
                value$iv8 = it$iv8;
            }
            final MutableState previewLoading$delegate = (MutableState) value$iv8;
            $composer3.endReplaceableGroup();
            String str = (String) CollectionsKt.getOrNull(BadgesSettingsScreen$lambda$2(urls$delegate), BadgesSettingsScreen$lambda$5(selected$delegate));
            final String selectedUrl = str == null ? "" : str;
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, 2059467568, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.BadgesSettingsScreenKt$BadgesSettingsScreen$1
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
                    ComposerKt.sourceInformation($composer4, "C93@4072L293:BadgesSettingsScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2059467568, $changed2, -1, "com.streamsync.app.ui.settings.BadgesSettingsScreen.<anonymous> (BadgesSettingsScreen.kt:93)");
                        }
                        Function2<Composer, Integer, Unit> m6679getLambda1$app_mobileDebug = ComposableSingletons$BadgesSettingsScreenKt.INSTANCE.m6679getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        AppBarKt.TopAppBar(m6679getLambda1$app_mobileDebug, null, ComposableLambdaKt.composableLambda($composer4, 1379664938, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.BadgesSettingsScreenKt$BadgesSettingsScreen$1.1
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
                                ComposerKt.sourceInformation($composer5, "C96@4182L151:BadgesSettingsScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1379664938, $changed3, -1, "com.streamsync.app.ui.settings.BadgesSettingsScreen.<anonymous>.<anonymous> (BadgesSettingsScreen.kt:96)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$BadgesSettingsScreenKt.INSTANCE.m6680getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
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
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, 3359163, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.BadgesSettingsScreenKt$BadgesSettingsScreen$2
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

                /* JADX WARN: Removed duplicated region for block: B:110:0x0b79  */
                /* JADX WARN: Removed duplicated region for block: B:113:0x0b85  */
                /* JADX WARN: Removed duplicated region for block: B:116:0x0bbe  */
                /* JADX WARN: Removed duplicated region for block: B:121:0x0d01  */
                /* JADX WARN: Removed duplicated region for block: B:126:0x0d78  */
                /* JADX WARN: Removed duplicated region for block: B:135:0x0bd4 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:136:0x0b8b  */
                /* JADX WARN: Removed duplicated region for block: B:148:0x0eb1  */
                /* JADX WARN: Removed duplicated region for block: B:151:0x0ebd  */
                /* JADX WARN: Removed duplicated region for block: B:154:0x0eed  */
                /* JADX WARN: Removed duplicated region for block: B:159:0x0fa8  */
                /* JADX WARN: Removed duplicated region for block: B:162:0x103c  */
                /* JADX WARN: Removed duplicated region for block: B:167:0x1083  */
                /* JADX WARN: Removed duplicated region for block: B:172:0x10da  */
                /* JADX WARN: Removed duplicated region for block: B:179:0x1262  */
                /* JADX WARN: Removed duplicated region for block: B:181:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:184:0x0fc2  */
                /* JADX WARN: Removed duplicated region for block: B:186:0x0f03 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:187:0x0ec1  */
                /* JADX WARN: Removed duplicated region for block: B:191:0x06a9  */
                /* JADX WARN: Removed duplicated region for block: B:192:0x06ae  */
                /* JADX WARN: Removed duplicated region for block: B:194:0x05db A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:195:0x0592  */
                /* JADX WARN: Removed duplicated region for block: B:197:0x04a3 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:198:0x045a  */
                /* JADX WARN: Removed duplicated region for block: B:201:0x0234  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x0222  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x022e  */
                /* JADX WARN: Removed duplicated region for block: B:42:0x0448  */
                /* JADX WARN: Removed duplicated region for block: B:45:0x0454  */
                /* JADX WARN: Removed duplicated region for block: B:48:0x048d  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x0580  */
                /* JADX WARN: Removed duplicated region for block: B:56:0x058c  */
                /* JADX WARN: Removed duplicated region for block: B:59:0x05c5  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x067b  */
                /* JADX WARN: Removed duplicated region for block: B:69:0x0729  */
                /* JADX WARN: Removed duplicated region for block: B:74:0x07eb  */
                /* JADX WARN: Removed duplicated region for block: B:78:0x0850  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r155, androidx.compose.runtime.Composer r156, int r157) {
                    /*
                        Method dump skipped, instructions count: 4710
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.BadgesSettingsScreenKt$BadgesSettingsScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.BadgesSettingsScreenKt$BadgesSettingsScreen$3
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
                    BadgesSettingsScreenKt.BadgesSettingsScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<String> BadgesSettingsScreen$lambda$2(MutableState<List<String>> mutableState) {
        MutableState<List<String>> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int BadgesSettingsScreen$lambda$5(MutableState<Integer> mutableState) {
        MutableState<Integer> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BadgesSettingsScreen$lambda$6(MutableState<Integer> mutableState, int value) {
        mutableState.setValue(Integer.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BadgesSettingsScreen$lambda$8(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BadgesSettingsScreen$lambda$9(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String BadgesSettingsScreen$lambda$11(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Boolean, String> BadgesSettingsScreen$lambda$14(MutableState<Pair<Boolean, String>> mutableState) {
        MutableState<Pair<Boolean, String>> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String BadgesSettingsScreen$lambda$17(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BadgesSettingsScreen$lambda$20(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BadgesSettingsScreen$lambda$21(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }
}
