package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.transcode.Ac3EncoderAvailability;
import com.streamsync.app.ui.player.PassthroughMode;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioSettingsScreen.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\rX\u008a\u008e\u0002²\u0006\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u008a\u008e\u0002²\u0006\f\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u008a\u008e\u0002²\u0006\f\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u008a\u008e\u0002²\u0006\f\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u008a\u008e\u0002"}, d2 = {"PASSTHROUGH_MODES", "", "Lcom/streamsync/app/ui/settings/PassthroughChoice;", "AudioSettingsScreen", "", "onBack", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "choiceOf", "mode", "Lcom/streamsync/app/ui/player/PassthroughMode;", "app_mobileDebug", "opticalPassthrough", "", "ac3Passthrough", "eac3Passthrough", "dolbyAtmosPassthrough", "dtsPassthrough", "dtsHdPassthrough", "passthroughMode", "transcodeToAc3", "audioLang1", "", "audioLang2", "subLang1", "subLang2"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class AudioSettingsScreenKt {
    private static final List<PassthroughChoice> PASSTHROUGH_MODES = CollectionsKt.listOf((Object[]) new PassthroughChoice[]{new PassthroughChoice(PassthroughMode.TUDO, "Tự dò (nên dùng)", "Để hệ thống tự quyết. Với HDMI, TV tự báo hỗ trợ nên vẫn passthrough đầy đủ. Đây đúng là cách app chạy trước khi có tùy chọn ép."), new PassthroughChoice(PassthroughMode.EP, "Ép bật", "Khai báo hỗ trợ đúng những định dạng đã tick bên dưới, không cần máy báo. Dùng khi nối ngõ quang (optical/SPDIF), hoặc ampli/DAC mà Android nhận sai. Chọn sai ở đây có thể mất tiếng."), new PassthroughChoice(PassthroughMode.TAT, "Tắt (chỉ giải mã sang PCM)", "Ép giải mã hết sang PCM. Dùng khi ampli không hiểu định dạng gốc, hoặc khi cần nút Auto sync bắt được audio.")});

    public static final void AudioSettingsScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        Object value$iv7;
        Object value$iv8;
        Object value$iv9;
        Object value$iv10;
        Object value$iv11;
        Object value$iv12;
        Object value$iv13;
        Object value$iv14;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(626090920);
        ComposerKt.sourceInformation($composer3, "C(AudioSettingsScreen)44@1941L7,45@1965L43,47@2040L64,48@2131L60,49@2219L61,50@2314L67,51@2408L60,52@2497L62,53@2587L55,54@2669L60,58@2963L49,60@3036L63,61@3122L63,62@3206L66,63@3293L66,65@3365L8014:AudioSettingsScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(626090920, $dirty2, -1, "com.streamsync.app.ui.settings.AudioSettingsScreen (AudioSettingsScreen.kt:43)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(175374860);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new PlaybackSettingsStore(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final PlaybackSettingsStore store = (PlaybackSettingsStore) value$iv;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175377281);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isOpticalPassthroughEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175380189);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isAc3PassthroughEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            final MutableState ac3Passthrough$delegate = (MutableState) value$iv3;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175383006);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isEac3PassthroughEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final MutableState eac3Passthrough$delegate = (MutableState) value$iv4;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175386052);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv5 = $composer3.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isDolbyAtmosPassthroughEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final MutableState dolbyAtmosPassthrough$delegate = (MutableState) value$iv5;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175389053);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv6 = $composer3.rememberedValue();
            if (it$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isDtsPassthroughEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            final MutableState dtsPassthrough$delegate = (MutableState) value$iv6;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175391903);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv7 = $composer3.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isDtsHdPassthroughEnabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv7);
            } else {
                value$iv7 = it$iv7;
            }
            final MutableState dtsHdPassthrough$delegate = (MutableState) value$iv7;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175394776);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv8 = $composer3.rememberedValue();
            if (it$iv8 == Composer.INSTANCE.getEmpty()) {
                value$iv8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getPassthroughMode(), null, 2, null);
                $composer3.updateRememberedValue(value$iv8);
            } else {
                value$iv8 = it$iv8;
            }
            final MutableState passthroughMode$delegate = (MutableState) value$iv8;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175397405);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv9 = $composer3.rememberedValue();
            if (it$iv9 == Composer.INSTANCE.getEmpty()) {
                value$iv9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(store.isTranscodeToAc3Enabled()), null, 2, null);
                $composer3.updateRememberedValue(value$iv9);
            } else {
                value$iv9 = it$iv9;
            }
            final MutableState transcodeToAc3$delegate = (MutableState) value$iv9;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175406802);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv10 = $composer3.rememberedValue();
            if (it$iv10 == Composer.INSTANCE.getEmpty()) {
                value$iv10 = Boolean.valueOf(Ac3EncoderAvailability.INSTANCE.isAvailable());
                $composer3.updateRememberedValue(value$iv10);
            } else {
                value$iv10 = it$iv10;
            }
            final boolean canTranscodeAc3 = ((Boolean) value$iv10).booleanValue();
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175409152);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv11 = $composer3.rememberedValue();
            if (it$iv11 == Composer.INSTANCE.getEmpty()) {
                value$iv11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getPreferredAudioLanguage1(), null, 2, null);
                $composer3.updateRememberedValue(value$iv11);
            } else {
                value$iv11 = it$iv11;
            }
            final MutableState audioLang1$delegate = (MutableState) value$iv11;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175411904);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv12 = $composer3.rememberedValue();
            if (it$iv12 == Composer.INSTANCE.getEmpty()) {
                value$iv12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getPreferredAudioLanguage2(), null, 2, null);
                $composer3.updateRememberedValue(value$iv12);
            } else {
                value$iv12 = it$iv12;
            }
            final MutableState audioLang2$delegate = (MutableState) value$iv12;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175414595);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv13 = $composer3.rememberedValue();
            if (it$iv13 == Composer.INSTANCE.getEmpty()) {
                value$iv13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getPreferredSubtitleLanguage1(), null, 2, null);
                $composer3.updateRememberedValue(value$iv13);
            } else {
                value$iv13 = it$iv13;
            }
            final MutableState subLang1$delegate = (MutableState) value$iv13;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(175417379);
            ComposerKt.sourceInformation($composer3, "CC(remember):AudioSettingsScreen.kt#9igjgp");
            Object it$iv14 = $composer3.rememberedValue();
            if (it$iv14 == Composer.INSTANCE.getEmpty()) {
                value$iv14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getPreferredSubtitleLanguage2(), null, 2, null);
                $composer3.updateRememberedValue(value$iv14);
            } else {
                value$iv14 = it$iv14;
            }
            final MutableState subLang2$delegate = (MutableState) value$iv14;
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, -757610900, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AudioSettingsScreenKt$AudioSettingsScreen$1
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
                    ComposerKt.sourceInformation($composer4, "C67@3406L309:AudioSettingsScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-757610900, $changed2, -1, "com.streamsync.app.ui.settings.AudioSettingsScreen.<anonymous> (AudioSettingsScreen.kt:67)");
                        }
                        Function2<Composer, Integer, Unit> m6677getLambda1$app_mobileDebug = ComposableSingletons$AudioSettingsScreenKt.INSTANCE.m6677getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        AppBarKt.TopAppBar(m6677getLambda1$app_mobileDebug, null, ComposableLambdaKt.composableLambda($composer4, 1704248806, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AudioSettingsScreenKt$AudioSettingsScreen$1.1
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
                                ComposerKt.sourceInformation($composer5, "C70@3532L151:AudioSettingsScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1704248806, $changed3, -1, "com.streamsync.app.ui.settings.AudioSettingsScreen.<anonymous>.<anonymous> (AudioSettingsScreen.kt:70)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$AudioSettingsScreenKt.INSTANCE.m6678getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
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
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, 1823486903, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AudioSettingsScreenKt$AudioSettingsScreen$2
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

                /* JADX WARN: Removed duplicated region for block: B:36:0x020a  */
                /* JADX WARN: Removed duplicated region for block: B:41:0x0259  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x02a8  */
                /* JADX WARN: Removed duplicated region for block: B:51:0x03d5  */
                /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:56:0x0266 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:58:0x0217 A[ADDED_TO_REGION] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r57, androidx.compose.runtime.Composer r58, int r59) {
                    /*
                        Method dump skipped, instructions count: 985
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.AudioSettingsScreenKt$AudioSettingsScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.AudioSettingsScreenKt$AudioSettingsScreen$3
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
                    AudioSettingsScreenKt.AudioSettingsScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final boolean AudioSettingsScreen$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AudioSettingsScreen$lambda$5(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioSettingsScreen$lambda$6(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AudioSettingsScreen$lambda$8(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioSettingsScreen$lambda$9(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AudioSettingsScreen$lambda$11(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioSettingsScreen$lambda$12(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AudioSettingsScreen$lambda$14(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioSettingsScreen$lambda$15(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AudioSettingsScreen$lambda$17(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioSettingsScreen$lambda$18(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PassthroughMode AudioSettingsScreen$lambda$20(MutableState<PassthroughMode> mutableState) {
        MutableState<PassthroughMode> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AudioSettingsScreen$lambda$23(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioSettingsScreen$lambda$24(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AudioSettingsScreen$lambda$27(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AudioSettingsScreen$lambda$30(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AudioSettingsScreen$lambda$33(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AudioSettingsScreen$lambda$36(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    private static final PassthroughChoice choiceOf(PassthroughMode mode) {
        Iterable $this$first$iv = PASSTHROUGH_MODES;
        for (Object element$iv : $this$first$iv) {
            PassthroughChoice it = (PassthroughChoice) element$iv;
            if (it.getMode() == mode) {
                return (PassthroughChoice) element$iv;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
