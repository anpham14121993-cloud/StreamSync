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
import com.streamsync.app.settings.DnsProvider;
import com.streamsync.app.settings.PlaybackSettingsStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DnsSettingsScreen.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0002\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f²\u0006\n\u0010\u0004\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"DnsChoice", "", "provider", "Lcom/streamsync/app/settings/DnsProvider;", "selected", "", "onSelect", "Lkotlin/Function0;", "(Lcom/streamsync/app/settings/DnsProvider;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "DnsSettingsScreen", "onBack", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class DnsSettingsScreenKt {
    public static final void DnsSettingsScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(-1226075064);
        ComposerKt.sourceInformation($composer3, "C(DnsSettingsScreen)45@1949L7,46@1973L43,47@2037L51,49@2094L3175:DnsSettingsScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1226075064, $dirty2, -1, "com.streamsync.app.ui.settings.DnsSettingsScreen (DnsSettingsScreen.kt:44)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(-1384014497);
            ComposerKt.sourceInformation($composer3, "CC(remember):DnsSettingsScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new PlaybackSettingsStore(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final PlaybackSettingsStore store = (PlaybackSettingsStore) value$iv;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-1384012441);
            ComposerKt.sourceInformation($composer3, "CC(remember):DnsSettingsScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(store.getDnsProvider(), null, 2, null);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableState selected$delegate = (MutableState) value$iv2;
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, -814089972, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.DnsSettingsScreenKt$DnsSettingsScreen$1
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
                    ComposerKt.sourceInformation($composer4, "C51@2135L303:DnsSettingsScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-814089972, $changed2, -1, "com.streamsync.app.ui.settings.DnsSettingsScreen.<anonymous> (DnsSettingsScreen.kt:51)");
                        }
                        Function2<Composer, Integer, Unit> m6685getLambda1$app_mobileDebug = ComposableSingletons$DnsSettingsScreenKt.INSTANCE.m6685getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        AppBarKt.TopAppBar(m6685getLambda1$app_mobileDebug, null, ComposableLambdaKt.composableLambda($composer4, 2118501766, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.DnsSettingsScreenKt$DnsSettingsScreen$1.1
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
                                ComposerKt.sourceInformation($composer5, "C54@2255L151:DnsSettingsScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(2118501766, $changed3, -1, "com.streamsync.app.ui.settings.DnsSettingsScreen.<anonymous>.<anonymous> (DnsSettingsScreen.kt:54)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$DnsSettingsScreenKt.INSTANCE.m6686getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
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
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, 1335542743, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.DnsSettingsScreenKt$DnsSettingsScreen$2
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

                /* JADX WARN: Removed duplicated region for block: B:31:0x01b6  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x01fe  */
                /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r29, androidx.compose.runtime.Composer r30, int r31) {
                    /*
                        Method dump skipped, instructions count: 514
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.DnsSettingsScreenKt$DnsSettingsScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
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
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.DnsSettingsScreenKt$DnsSettingsScreen$3
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
                    DnsSettingsScreenKt.DnsSettingsScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DnsProvider DnsSettingsScreen$lambda$2(MutableState<DnsProvider> mutableState) {
        MutableState<DnsProvider> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0260  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DnsChoice(final com.streamsync.app.settings.DnsProvider r100, final boolean r101, final kotlin.jvm.functions.Function0<kotlin.Unit> r102, androidx.compose.runtime.Composer r103, final int r104) {
        /*
            Method dump skipped, instructions count: 995
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.DnsSettingsScreenKt.DnsChoice(com.streamsync.app.settings.DnsProvider, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }
}
