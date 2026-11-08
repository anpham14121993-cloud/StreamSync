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
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DnsProvider;
import com.streamsync.app.settings.IntegrationStore;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.subtitle.autosync.GeminiApiKeyStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\u001aE\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u000bH\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a3\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003¢\u0006\u0002\u0010\u0010\u001aµ\u0001\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007¢\u0006\u0002\u0010\u001e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f²\u0006\n\u0010 \u001a\u00020!X\u008a\u008e\u0002"}, d2 = {"SettingsGroup", "", "title", "", "subtitle", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "tint", "Landroidx/compose/ui/graphics/Color;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "SettingsGroup-ww6aTOc", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SettingsNavRow", "onClick", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SettingsScreen", "onBack", "onAddonsClick", "onSubtitleStyleClick", "onAppearanceClick", "onHomeLayoutClick", "onAudioClick", "onPlayerClick", "onClearCacheClick", "onOpenDns", "onOpenDpi", "onSyncServerClick", "onBadgesClick", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "app_mobileDebug", "showStreamProvider", ""}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class SettingsScreenKt {
    public static final void SettingsScreen(final Function0<Unit> onBack, final Function0<Unit> onAddonsClick, final Function0<Unit> onSubtitleStyleClick, final Function0<Unit> onAppearanceClick, final Function0<Unit> onHomeLayoutClick, final Function0<Unit> onAudioClick, final Function0<Unit> onPlayerClick, final Function0<Unit> onClearCacheClick, final Function0<Unit> onOpenDns, final Function0<Unit> onOpenDpi, final Function0<Unit> onSyncServerClick, final Function0<Unit> onBadgesClick, Composer $composer, final int $changed, final int $changed1) {
        int $dirty1;
        int $dirty;
        Object value$iv;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(onAddonsClick, "onAddonsClick");
        Intrinsics.checkNotNullParameter(onSubtitleStyleClick, "onSubtitleStyleClick");
        Intrinsics.checkNotNullParameter(onAppearanceClick, "onAppearanceClick");
        Intrinsics.checkNotNullParameter(onHomeLayoutClick, "onHomeLayoutClick");
        Intrinsics.checkNotNullParameter(onAudioClick, "onAudioClick");
        Intrinsics.checkNotNullParameter(onPlayerClick, "onPlayerClick");
        Intrinsics.checkNotNullParameter(onClearCacheClick, "onClearCacheClick");
        Intrinsics.checkNotNullParameter(onOpenDns, "onOpenDns");
        Intrinsics.checkNotNullParameter(onOpenDpi, "onOpenDpi");
        Intrinsics.checkNotNullParameter(onSyncServerClick, "onSyncServerClick");
        Intrinsics.checkNotNullParameter(onBadgesClick, "onBadgesClick");
        Composer $composer2 = $composer.startRestartGroup(749068744);
        ComposerKt.sourceInformation($composer2, "C(SettingsScreen)P(3!1,10!1,6!1,9,5,7,8,11)89@3915L7,93@4164L66,100@4648L64,102@4801L43,107@5000L7533:SettingsScreen.kt#bv4dau");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(onBack) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(onAddonsClick) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty2 |= $composer2.changedInstance(onSubtitleStyleClick) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(onAppearanceClick) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changedInstance(onHomeLayoutClick) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(onAudioClick) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(onPlayerClick) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(onClearCacheClick) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(onOpenDns) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((805306368 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(onOpenDpi) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty12 |= $composer2.changedInstance(onSyncServerClick) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty12 |= $composer2.changedInstance(onBadgesClick) ? 32 : 16;
        }
        int $dirty13 = $dirty12;
        if (($dirty2 & 306783379) != 306783378 || ($dirty13 & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(749068744, $dirty2, $dirty13, "com.streamsync.app.ui.settings.SettingsScreen (SettingsScreen.kt:88)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            $dirty1 = $dirty13;
            $dirty = $dirty2;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Context context = (Context) consume;
            final GeminiApiKeyStore keyStore = new GeminiApiKeyStore(context);
            $composer2.startReplaceableGroup(-855792555);
            ComposerKt.sourceInformation($composer2, "CC(remember):SettingsScreen.kt#9igjgp");
            Object value$iv2 = $composer2.rememberedValue();
            if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new IntegrationStore(context);
                $composer2.updateRememberedValue(value$iv2);
            }
            final IntegrationStore integrationStore = (IntegrationStore) value$iv2;
            $composer2.endReplaceableGroup();
            final int badgeUrlCount = integrationStore.getBadgesUrls().size();
            final int badgesIndex = integrationStore.getBadgesIndex();
            final boolean badgesActive = integrationStore.getBadgesActive();
            $composer2.startReplaceableGroup(-855777069);
            ComposerKt.sourceInformation($composer2, "CC(remember):SettingsScreen.kt#9igjgp");
            Object value$iv3 = $composer2.rememberedValue();
            if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(integrationStore.getShowStreamProvider()), null, 2, null);
                $composer2.updateRememberedValue(value$iv3);
            }
            final MutableState showStreamProvider$delegate = (MutableState) value$iv3;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(-855772194);
            ComposerKt.sourceInformation($composer2, "CC(remember):SettingsScreen.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new PlaybackSettingsStore(context);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            PlaybackSettingsStore netStore = (PlaybackSettingsStore) value$iv;
            $composer2.endReplaceableGroup();
            final DnsProvider dnsProvider = netStore.getDnsProvider();
            final boolean dpiBypass = netStore.isDpiBypassEnabled();
            final int dpiFragment = netStore.getDpiFragmentSize();
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer2, 1589187212, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SettingsScreenKt$SettingsScreen$1
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
                    ComposerKt.sourceInformation($composer3, "C109@5041L294:SettingsScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1589187212, $changed2, -1, "com.streamsync.app.ui.settings.SettingsScreen.<anonymous> (SettingsScreen.kt:109)");
                        }
                        Function2<Composer, Integer, Unit> m6700getLambda1$app_mobileDebug = ComposableSingletons$SettingsScreenKt.INSTANCE.m6700getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        AppBarKt.TopAppBar(m6700getLambda1$app_mobileDebug, null, ComposableLambdaKt.composableLambda($composer3, 319589638, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SettingsScreenKt$SettingsScreen$1.1
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
                                ComposerKt.sourceInformation($composer4, "C112@5152L151:SettingsScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(319589638, $changed3, -1, "com.streamsync.app.ui.settings.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:112)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.m6701getLambda2$app_mobileDebug(), $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer4.skipToGroupEnd();
                            }
                        }), null, null, null, null, $composer3, 390, 122);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer2, 302390871, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SettingsScreenKt$SettingsScreen$2
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

                /* JADX WARN: Removed duplicated region for block: B:31:0x037f  */
                /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r81, androidx.compose.runtime.Composer r82, int r83) {
                    /*
                        Method dump skipped, instructions count: 899
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.SettingsScreenKt$SettingsScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            $dirty1 = $dirty13;
            $dirty = $dirty2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.SettingsScreenKt$SettingsScreen$3
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
                    SettingsScreenKt.SettingsScreen(onBack, onAddonsClick, onSubtitleStyleClick, onAppearanceClick, onHomeLayoutClick, onAudioClick, onPlayerClick, onClearCacheClick, onOpenDns, onOpenDpi, onSyncServerClick, onBadgesClick, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SettingsScreen$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SettingsScreen$lambda$3(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x05ba  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x05c6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x07e1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0485 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x043c  */
    /* renamed from: SettingsGroup-ww6aTOc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6711SettingsGroupww6aTOc(final java.lang.String r116, final java.lang.String r117, final androidx.compose.ui.graphics.vector.ImageVector r118, final long r119, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r121, androidx.compose.runtime.Composer r122, final int r123) {
        /*
            Method dump skipped, instructions count: 2053
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.SettingsScreenKt.m6711SettingsGroupww6aTOc(java.lang.String, java.lang.String, androidx.compose.ui.graphics.vector.ImageVector, long, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0462 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0278  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SettingsNavRow(final androidx.compose.ui.graphics.vector.ImageVector r93, final java.lang.String r94, final java.lang.String r95, final kotlin.jvm.functions.Function0<kotlin.Unit> r96, androidx.compose.runtime.Composer r97, final int r98) {
        /*
            Method dump skipped, instructions count: 1494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.SettingsScreenKt.SettingsNavRow(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }
}
