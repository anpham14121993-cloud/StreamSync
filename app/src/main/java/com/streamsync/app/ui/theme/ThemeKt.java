package com.streamsync.app.ui.theme;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.ThemeMode;
import com.streamsync.app.settings.UiSettings;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Theme.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a*\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\t0\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\u001a\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"BrandDark", "Landroidx/compose/material3/ColorScheme;", "BrandLight", "LocalUiSettings", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcom/streamsync/app/settings/UiSettings;", "getLocalUiSettings", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "StreamSyncTheme", "", "settings", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lcom/streamsync/app/settings/UiSettings;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "amoledScheme", TtmlNode.RUBY_BASE, "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class ThemeKt {
    private static final ProvidableCompositionLocal<UiSettings> LocalUiSettings = CompositionLocalKt.compositionLocalOf$default(null, new Function0<UiSettings>() { // from class: com.streamsync.app.ui.theme.ThemeKt$LocalUiSettings$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UiSettings invoke() {
            return new UiSettings(null, false, false, false, null, false, 0, 0, 0.0f, false, 0.0f, 0.0f, 0, 0, 0, false, 0, 0, 262143, null);
        }
    }, 1, null);
    private static final ColorScheme BrandLight = ColorSchemeKt.m1401lightColorSchemeCXl9yA$default(ColorKt.Color(4281293802L), Color.INSTANCE.m3446getWhite0d7_KjU(), ColorKt.Color(4292469503L), ColorKt.Color(4278196043L), 0, ColorKt.Color(4278217070L), Color.INSTANCE.m3446getWhite0d7_KjU(), ColorKt.Color(4285527804L), ColorKt.Color(4278198306L), 0, 0, 0, 0, ColorKt.Color(4294835199L), ColorKt.Color(4279966495L), ColorKt.Color(4294835199L), ColorKt.Color(4279966495L), ColorKt.Color(4292993772L), ColorKt.Color(4282664783L), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -516592, 15, null);
    private static final ColorScheme BrandDark = ColorSchemeKt.m1397darkColorSchemeCXl9yA$default(ColorKt.Color(4289775359L), ColorKt.Color(4278200952L), ColorKt.Color(4278927024L), ColorKt.Color(4292469503L), 0, ColorKt.Color(4283226592L), ColorKt.Color(4278204217L), ColorKt.Color(4278210387L), ColorKt.Color(4285527804L), 0, 0, 0, 0, ColorKt.Color(4279966495L), ColorKt.Color(4293190374L), ColorKt.Color(4279966495L), ColorKt.Color(4293190374L), ColorKt.Color(4282664783L), ColorKt.Color(4291086032L), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -516592, 15, null);

    public static final ProvidableCompositionLocal<UiSettings> getLocalUiSettings() {
        return LocalUiSettings;
    }

    private static final ColorScheme amoledScheme(ColorScheme base) {
        long m3435getBlack0d7_KjU = Color.INSTANCE.m3435getBlack0d7_KjU();
        long Color = ColorKt.Color(4293190374L);
        long m3435getBlack0d7_KjU2 = Color.INSTANCE.m3435getBlack0d7_KjU();
        long Color2 = ColorKt.Color(4293190374L);
        long Color3 = ColorKt.Color(4279900698L);
        long Color4 = ColorKt.Color(4291086032L);
        long m3435getBlack0d7_KjU3 = Color.INSTANCE.m3435getBlack0d7_KjU();
        return ColorScheme.m1353copyCXl9yA$default(base, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, m3435getBlack0d7_KjU, Color, m3435getBlack0d7_KjU2, Color2, Color3, Color4, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, ColorKt.Color(4279374354L), ColorKt.Color(4279900698L), ColorKt.Color(4280427042L), ColorKt.Color(4278848010L), m3435getBlack0d7_KjU3, 2146967551, 0, null);
    }

    public static final void StreamSyncTheme(UiSettings settings, final Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        final UiSettings settings2;
        boolean darkTheme;
        int i2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1775758180);
        ComposerKt.sourceInformation($composer2, "C(StreamSyncTheme)P(1)107@4104L7,121@4729L25,123@4760L206:Theme.kt#d1esv6");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            if ((i & 1) == 0) {
                settings2 = settings;
                if ($composer2.changed(settings2)) {
                    i2 = 4;
                    $dirty |= i2;
                }
            } else {
                settings2 = settings;
            }
            i2 = 2;
            $dirty |= i2;
        } else {
            settings2 = settings;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
            } else if ((i & 1) != 0) {
                settings2 = new UiSettings(null, false, false, false, null, false, 0, 0, 0.0f, false, 0.0f, 0.0f, 0, 0, 0, false, 0, 0, 262143, null);
                $dirty &= -15;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1775758180, $dirty, -1, "com.streamsync.app.ui.theme.StreamSyncTheme (Theme.kt:100)");
            }
            ThemeMode themeMode = settings2.getThemeMode();
            $composer2.startReplaceableGroup(-730996875);
            ComposerKt.sourceInformation($composer2, "102@3980L21");
            if (themeMode == ThemeMode.SYSTEM) {
                darkTheme = DarkThemeKt.isSystemInDarkTheme($composer2, 0);
            } else if (themeMode == ThemeMode.LIGHT) {
                darkTheme = false;
            } else {
                if (themeMode != ThemeMode.DARK) {
                    throw new NoWhenBranchMatchedException();
                }
                darkTheme = true;
            }
            $composer2.endReplaceableGroup();
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Context context = (Context) consume;
            ColorScheme base = (!settings2.getDynamicColor() || Build.VERSION.SDK_INT < 31) ? darkTheme ? BrandDark : BrandLight : darkTheme ? DynamicTonalPaletteKt.dynamicDarkColorScheme(context) : DynamicTonalPaletteKt.dynamicLightColorScheme(context);
            final ColorScheme colorScheme = (settings2.getAmoledBlack() && darkTheme) ? amoledScheme(base) : base;
            WindowWidthClass windowWidthClass = WindowWidthClassKt.currentWindowWidthClass($composer2, 0);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{LocalUiSettings.provides(settings2), WindowWidthClassKt.getLocalWindowWidthClass().provides(windowWidthClass)}, ComposableLambdaKt.composableLambda($composer2, -723406812, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.theme.ThemeKt$StreamSyncTheme$1
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C127@4901L59:Theme.kt#d1esv6");
                    if (($changed2 & 3) == 2 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-723406812, $changed2, -1, "com.streamsync.app.ui.theme.StreamSyncTheme.<anonymous> (Theme.kt:127)");
                    }
                    MaterialThemeKt.MaterialTheme(ColorScheme.this, null, null, content, $composer3, 0, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.theme.ThemeKt$StreamSyncTheme$2
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

                public final void invoke(Composer composer, int i3) {
                    ThemeKt.StreamSyncTheme(UiSettings.this, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
