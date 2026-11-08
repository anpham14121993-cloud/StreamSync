package com.streamsync.app.ui.player;

import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.media3.exoplayer.RendererCapabilities;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerSeekBar.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\u001a8\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001aI\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016\u001a;\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u001bH\u0007¢\u0006\u0002\u0010\u001c\u001a9\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00102\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u001bH\u0007¢\u0006\u0002\u0010\u001f\u001a\u0010\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0001H\u0000\u001a\u0015\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0007H\u0003¢\u0006\u0002\u0010$\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006%²\u0006\f\u0010&\u001a\u0004\u0018\u00010'X\u008a\u008e\u0002"}, d2 = {"SEEK_STEP_MS", "", "CenterCircleButton", "", "onClick", "Lkotlin/Function0;", "contentDescription", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "size", "Landroidx/compose/ui/unit/Dp;", "CenterCircleButton-gwO9Abs", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;FLandroidx/compose/runtime/Composer;I)V", "CenterControlsRow", "playing", "", "onBack", "onPlayPause", "onForward", "modifier", "Landroidx/compose/ui/Modifier;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "PlayerSeekBar", "positionMs", "durationMs", "onSeek", "Lkotlin/Function1;", "(JJLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "PlayerSeekBarOrNothing", "hidden", "(JJZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "formatPlayerTime", TranslateLanguage.MALAY, "shadowedLabel", "text", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug", "dragging", ""}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlayerSeekBarKt {
    public static final long SEEK_STEP_MS = 10000;

    /* JADX WARN: Removed duplicated region for block: B:57:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x047c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x029e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PlayerSeekBar(final long r81, final long r83, androidx.compose.ui.Modifier r85, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r86, androidx.compose.runtime.Composer r87, final int r88, final int r89) {
        /*
            Method dump skipped, instructions count: 1372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerSeekBarKt.PlayerSeekBar(long, long, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Float PlayerSeekBar$lambda$1(MutableState<Float> mutableState) {
        MutableState<Float> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final String formatPlayerTime(long ms) {
        if (ms <= 0) {
            return "0:00";
        }
        long total = ms / 1000;
        long j = 3600;
        long h = total / j;
        long j2 = 60;
        long m = (total % j) / j2;
        long s = total % j2;
        String format = h > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(h), Long.valueOf(m), Long.valueOf(s)}, 3)) : String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(m), Long.valueOf(s)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public static final void PlayerSeekBarOrNothing(final long positionMs, final long durationMs, final boolean hidden, final Function1<? super Long, Unit> onSeek, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(onSeek, "onSeek");
        Composer $composer2 = $composer.startRestartGroup(419930133);
        ComposerKt.sourceInformation($composer2, "C(PlayerSeekBarOrNothing)P(3)136@5230L110:PlayerSeekBar.kt#nsz36g");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(positionMs) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(durationMs) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(hidden) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(onSeek) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(419930133, $dirty2, -1, "com.streamsync.app.ui.player.PlayerSeekBarOrNothing (PlayerSeekBar.kt:134)");
            }
            if (hidden || durationMs <= 0) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerSeekBarKt$PlayerSeekBarOrNothing$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer, int i) {
                            PlayerSeekBarKt.PlayerSeekBarOrNothing(positionMs, durationMs, hidden, onSeek, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                        }
                    });
                    return;
                }
                return;
            }
            PlayerSeekBar(positionMs, durationMs, null, onSeek, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168), 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerSeekBarKt$PlayerSeekBarOrNothing$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    PlayerSeekBarKt.PlayerSeekBarOrNothing(positionMs, durationMs, hidden, onSeek, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x022a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CenterControlsRow(final boolean r36, final kotlin.jvm.functions.Function0<kotlin.Unit> r37, final kotlin.jvm.functions.Function0<kotlin.Unit> r38, final kotlin.jvm.functions.Function0<kotlin.Unit> r39, androidx.compose.ui.Modifier r40, androidx.compose.runtime.Composer r41, final int r42, final int r43) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerSeekBarKt.CenterControlsRow(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:54:0x021f  */
    /* renamed from: CenterCircleButton-gwO9Abs, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6669CenterCircleButtongwO9Abs(final kotlin.jvm.functions.Function0<kotlin.Unit> r35, final java.lang.String r36, final androidx.compose.ui.graphics.vector.ImageVector r37, final float r38, androidx.compose.runtime.Composer r39, final int r40) {
        /*
            Method dump skipped, instructions count: 577
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerSeekBarKt.m6669CenterCircleButtongwO9Abs(kotlin.jvm.functions.Function0, java.lang.String, androidx.compose.ui.graphics.vector.ImageVector, float, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void shadowedLabel(final String text, Composer $composer, final int $changed) {
        long m3407copywmQWz5c;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-172926052);
        ComposerKt.sourceInformation($composer3, "C(shadowedLabel)228@8158L10,225@8052L379:PlayerSeekBar.kt#nsz36g");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) != 2 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-172926052, $dirty2, -1, "com.streamsync.app.ui.player.shadowedLabel (PlayerSeekBar.kt:224)");
            }
            long m3446getWhite0d7_KjU = Color.INSTANCE.m3446getWhite0d7_KjU();
            TextStyle labelSmall = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelSmall();
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r5, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r5) : 0.9f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r5) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r5) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(Color.INSTANCE.m3435getBlack0d7_KjU()) : 0.0f);
            $composer2 = $composer3;
            TextKt.m2127Text4IGK_g(text, (Modifier) null, m3446getWhite0d7_KjU, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m5248copyp1EtxEg$default(labelSmall, 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, new Shadow(m3407copywmQWz5c, OffsetKt.Offset(0.0f, 1.0f), 3.0f, null), null, 0, 0, 0L, null, null, null, 0, 0, null, 16769023, null), $composer2, ($dirty2 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerSeekBarKt$shadowedLabel$1
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
                    PlayerSeekBarKt.shadowedLabel(text, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
