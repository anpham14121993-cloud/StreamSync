package androidx.compose.material3;

import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* compiled from: NavigationBar.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\b\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\tJD\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011JX\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/material3/NavigationBarItemDefaults;", "", "()V", "defaultNavigationBarItemColors", "Landroidx/compose/material3/NavigationBarItemColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultNavigationBarItemColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/NavigationBarItemColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/NavigationBarItemColors;", "selectedIconColor", "Landroidx/compose/ui/graphics/Color;", "selectedTextColor", "indicatorColor", "unselectedIconColor", "unselectedTextColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationBarItemColors;", "disabledIconColor", "disabledTextColor", "colors-69fazGs", "(JJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/NavigationBarItemColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class NavigationBarItemDefaults {
    public static final int $stable = 0;
    public static final NavigationBarItemDefaults INSTANCE = new NavigationBarItemDefaults();

    private NavigationBarItemDefaults() {
    }

    public final NavigationBarItemColors colors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1018883954);
        ComposerKt.sourceInformation($composer, "C(colors)297@12710L11:NavigationBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1018883954, $changed, -1, "androidx.compose.material3.NavigationBarItemDefaults.colors (NavigationBar.kt:297)");
        }
        NavigationBarItemColors defaultNavigationBarItemColors$material3_release = getDefaultNavigationBarItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultNavigationBarItemColors$material3_release;
    }

    /* renamed from: colors-69fazGs, reason: not valid java name */
    public final NavigationBarItemColors m1691colors69fazGs(long selectedIconColor, long selectedTextColor, long indicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor, Composer $composer, int $changed, int i) {
        long selectedTextColor2;
        long indicatorColor2;
        long unselectedIconColor2;
        long unselectedTextColor2;
        long disabledIconColor2;
        long disabledTextColor2;
        $composer.startReplaceableGroup(-1618564327);
        ComposerKt.sourceInformation($composer, "C(colors)P(3:c#ui.graphics.Color,4:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color)321@14086L11:NavigationBar.kt#uh7d8r");
        long selectedIconColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : selectedIconColor;
        if ((i & 2) == 0) {
            selectedTextColor2 = selectedTextColor;
        } else {
            selectedTextColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            indicatorColor2 = indicatorColor;
        } else {
            indicatorColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) == 0) {
            unselectedIconColor2 = unselectedIconColor;
        } else {
            unselectedIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 16) == 0) {
            unselectedTextColor2 = unselectedTextColor;
        } else {
            unselectedTextColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 32) == 0) {
            disabledIconColor2 = disabledIconColor;
        } else {
            disabledIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 64) == 0) {
            disabledTextColor2 = disabledTextColor;
        } else {
            disabledTextColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1618564327, $changed, -1, "androidx.compose.material3.NavigationBarItemDefaults.colors (NavigationBar.kt:321)");
        }
        NavigationBarItemColors m1682copy4JmcsL4 = getDefaultNavigationBarItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1682copy4JmcsL4(selectedIconColor2, selectedTextColor2, indicatorColor2, unselectedIconColor2, unselectedTextColor2, disabledIconColor2, disabledTextColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1682copy4JmcsL4;
    }

    public final NavigationBarItemColors getDefaultNavigationBarItemColors$material3_release(ColorScheme $this$defaultNavigationBarItemColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        NavigationBarItemColors defaultNavigationBarItemColorsCached = $this$defaultNavigationBarItemColors.getDefaultNavigationBarItemColorsCached();
        if (defaultNavigationBarItemColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getActiveIconColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getActiveLabelTextColor());
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getActiveIndicatorColor());
            long fromToken4 = ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getInactiveIconColor());
            long fromToken5 = ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getInactiveLabelTextColor());
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r13) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getInactiveIconColor())) : 0.0f);
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r15, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r15) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r15) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r15) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultNavigationBarItemColors, NavigationBarTokens.INSTANCE.getInactiveLabelTextColor())) : 0.0f);
            NavigationBarItemColors it = new NavigationBarItemColors(fromToken, fromToken2, fromToken3, fromToken4, fromToken5, m3407copywmQWz5c, m3407copywmQWz5c2, null);
            $this$defaultNavigationBarItemColors.setDefaultNavigationBarItemColorsCached$material3_release(it);
            return it;
        }
        return defaultNavigationBarItemColorsCached;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use overload with disabledIconColor and disabledTextColor")
    /* renamed from: colors-zjMxDiM, reason: not valid java name */
    public final /* synthetic */ NavigationBarItemColors m1692colorszjMxDiM(long selectedIconColor, long selectedTextColor, long indicatorColor, long unselectedIconColor, long unselectedTextColor, Composer $composer, int $changed, int i) {
        long selectedTextColor2;
        long indicatorColor2;
        long unselectedIconColor2;
        long unselectedTextColor2;
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        $composer.startReplaceableGroup(-213647161);
        ComposerKt.sourceInformation($composer, "C(colors)P(1:c#ui.graphics.Color,2:c#ui.graphics.Color,0:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color)354@15718L5,355@15801L5,356@15881L5,357@15963L5,358@16050L5:NavigationBar.kt#uh7d8r");
        long selectedIconColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getActiveIconColor(), $composer, 6) : selectedIconColor;
        if ((i & 2) == 0) {
            selectedTextColor2 = selectedTextColor;
        } else {
            selectedTextColor2 = ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getActiveLabelTextColor(), $composer, 6);
        }
        if ((i & 4) == 0) {
            indicatorColor2 = indicatorColor;
        } else {
            indicatorColor2 = ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorColor(), $composer, 6);
        }
        if ((i & 8) == 0) {
            unselectedIconColor2 = unselectedIconColor;
        } else {
            unselectedIconColor2 = ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getInactiveIconColor(), $composer, 6);
        }
        if ((i & 16) == 0) {
            unselectedTextColor2 = unselectedTextColor;
        } else {
            unselectedTextColor2 = ColorSchemeKt.getValue(NavigationBarTokens.INSTANCE.getInactiveLabelTextColor(), $composer, 6);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-213647161, $changed, -1, "androidx.compose.material3.NavigationBarItemDefaults.colors (NavigationBar.kt:359)");
        }
        m3407copywmQWz5c = Color.m3407copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r29) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(unselectedIconColor2) : 0.0f);
        m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r29) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(unselectedTextColor2) : 0.0f);
        NavigationBarItemColors navigationBarItemColors = new NavigationBarItemColors(selectedIconColor2, selectedTextColor2, indicatorColor2, unselectedIconColor2, unselectedTextColor2, m3407copywmQWz5c, m3407copywmQWz5c2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return navigationBarItemColors;
    }
}
