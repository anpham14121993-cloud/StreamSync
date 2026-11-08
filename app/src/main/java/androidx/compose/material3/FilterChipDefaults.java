package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.FilterChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: Chip.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0015\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0016J\u008a\u0001\u0010\u0015\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%JN\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u00042\b\b\u0002\u0010-\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/J^\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00182\b\b\u0002\u00106\u001a\u00020\u00182\b\b\u0002\u00107\u001a\u00020\u00182\b\b\u0002\u00108\u001a\u00020\u00182\b\b\u0002\u00109\u001a\u00020\u00042\b\b\u0002\u0010:\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b;\u0010<J\r\u0010=\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0016J\u008a\u0001\u0010=\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b>\u0010%JN\u0010?\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u00042\b\b\u0002\u0010-\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010/R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0011\u0010\n\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u000f*\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006A"}, d2 = {"Landroidx/compose/material3/FilterChipDefaults;", "", "()V", "Height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM", "()F", "F", "IconSize", "getIconSize-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultElevatedFilterChipColors", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultElevatedFilterChipColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SelectableChipColors;", "defaultFilterChipColors", "getDefaultFilterChipColors$material3_release", "elevatedFilterChipColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SelectableChipColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "iconColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "selectedContainerColor", "disabledSelectedContainerColor", "selectedLabelColor", "selectedLeadingIconColor", "selectedTrailingIconColor", "elevatedFilterChipColors-XqyqHi0", "(JJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SelectableChipColors;", "elevatedFilterChipElevation", "Landroidx/compose/material3/SelectableChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "elevatedFilterChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SelectableChipElevation;", "filterChipBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "selected", "borderColor", "selectedBorderColor", "disabledBorderColor", "disabledSelectedBorderColor", "borderWidth", "selectedBorderWidth", "filterChipBorder-_7El2pE", "(ZZJJJJFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "filterChipColors", "filterChipColors-XqyqHi0", "filterChipElevation", "filterChipElevation-aqJV_2Y", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class FilterChipDefaults {
    public static final int $stable = 0;
    public static final FilterChipDefaults INSTANCE = new FilterChipDefaults();
    private static final float Height = FilterChipTokens.INSTANCE.m2576getContainerHeightD9Ej5fM();
    private static final float IconSize = FilterChipTokens.INSTANCE.m2592getIconSizeD9Ej5fM();

    private FilterChipDefaults() {
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m1567getHeightD9Ej5fM() {
        return Height;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1568getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final SelectableChipColors filterChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-1743772077);
        ComposerKt.sourceInformation($composer, "C(filterChipColors)1197@59368L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1743772077, $changed, -1, "androidx.compose.material3.FilterChipDefaults.filterChipColors (Chip.kt:1197)");
        }
        SelectableChipColors defaultFilterChipColors$material3_release = getDefaultFilterChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultFilterChipColors$material3_release;
    }

    /* renamed from: filterChipColors-XqyqHi0, reason: not valid java name */
    public final SelectableChipColors m1565filterChipColorsXqyqHi0(long containerColor, long labelColor, long iconColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconColor, long disabledTrailingIconColor, long selectedContainerColor, long disabledSelectedContainerColor, long selectedLabelColor, long selectedLeadingIconColor, long selectedTrailingIconColor, Composer $composer, int $changed, int $changed1, int i) {
        long labelColor2;
        long iconColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long selectedContainerColor2;
        long disabledSelectedContainerColor2;
        long selectedLabelColor2;
        long selectedLeadingIconColor2;
        long selectedTrailingIconColor2;
        $composer.startReplaceableGroup(-1831479801);
        ComposerKt.sourceInformation($composer, "C(filterChipColors)P(0:c#ui.graphics.Color,7:c#ui.graphics.Color,6:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,8:c#ui.graphics.Color,4:c#ui.graphics.Color,9:c#ui.graphics.Color,10:c#ui.graphics.Color,11:c#ui.graphics.Color)1231@61356L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            iconColor2 = iconColor;
        } else {
            iconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) == 0) {
            disabledContainerColor2 = disabledContainerColor;
        } else {
            disabledContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 16) == 0) {
            disabledLabelColor2 = disabledLabelColor;
        } else {
            disabledLabelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 32) == 0) {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        } else {
            disabledLeadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 64) == 0) {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        } else {
            disabledTrailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 128) == 0) {
            selectedContainerColor2 = selectedContainerColor;
        } else {
            selectedContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 256) == 0) {
            disabledSelectedContainerColor2 = disabledSelectedContainerColor;
        } else {
            disabledSelectedContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 512) == 0) {
            selectedLabelColor2 = selectedLabelColor;
        } else {
            selectedLabelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 1024) == 0) {
            selectedLeadingIconColor2 = selectedLeadingIconColor;
        } else {
            selectedLeadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 2048) == 0) {
            selectedTrailingIconColor2 = selectedTrailingIconColor;
        } else {
            selectedTrailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1831479801, $changed, $changed1, "androidx.compose.material3.FilterChipDefaults.filterChipColors (Chip.kt:1231)");
        }
        SelectableChipColors m1831copydaRQuJA = getDefaultFilterChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1831copydaRQuJA(containerColor2, labelColor2, iconColor2, iconColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconColor2, disabledTrailingIconColor2, selectedContainerColor2, disabledSelectedContainerColor2, selectedLabelColor2, selectedLeadingIconColor2, selectedTrailingIconColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1831copydaRQuJA;
    }

    public final SelectableChipColors getDefaultFilterChipColors$material3_release(ColorScheme $this$defaultFilterChipColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        long m3407copywmQWz5c4;
        SelectableChipColors defaultFilterChipColorsCached = $this$defaultFilterChipColors.getDefaultFilterChipColorsCached();
        if (defaultFilterChipColorsCached != null) {
            return defaultFilterChipColorsCached;
        }
        long m3444getTransparent0d7_KjU = Color.INSTANCE.m3444getTransparent0d7_KjU();
        long fromToken = ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getUnselectedLabelTextColor());
        long fromToken2 = ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getLeadingIconUnselectedColor());
        long fromToken3 = ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getLeadingIconUnselectedColor());
        long m3444getTransparent0d7_KjU2 = Color.INSTANCE.m3444getTransparent0d7_KjU();
        m3407copywmQWz5c = Color.m3407copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r14) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
        m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
        m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r17) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
        long fromToken4 = ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getFlatSelectedContainerColor());
        m3407copywmQWz5c4 = Color.m3407copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r21) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getFlatDisabledSelectedContainerColor())) : 0.0f);
        SelectableChipColors it = new SelectableChipColors(m3444getTransparent0d7_KjU, fromToken, fromToken2, fromToken3, m3444getTransparent0d7_KjU2, m3407copywmQWz5c, m3407copywmQWz5c2, m3407copywmQWz5c3, fromToken4, m3407copywmQWz5c4, ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getSelectedLabelTextColor()), ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getSelectedLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultFilterChipColors, FilterChipTokens.INSTANCE.getSelectedLeadingIconColor()), null);
        $this$defaultFilterChipColors.setDefaultFilterChipColorsCached$material3_release(it);
        return it;
    }

    /* renamed from: filterChipElevation-aqJV_2Y, reason: not valid java name */
    public final SelectableChipElevation m1566filterChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(-757972185);
        ComposerKt.sourceInformation($composer, "C(filterChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? FilterChipTokens.INSTANCE.m2583getFlatContainerElevationD9Ej5fM() : elevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = FilterChipTokens.INSTANCE.m2587getFlatSelectedPressedContainerElevationD9Ej5fM();
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = FilterChipTokens.INSTANCE.m2584getFlatSelectedFocusContainerElevationD9Ej5fM();
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = FilterChipTokens.INSTANCE.m2585getFlatSelectedHoverContainerElevationD9Ej5fM();
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = FilterChipTokens.INSTANCE.m2577getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = elevation2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-757972185, $changed, -1, "androidx.compose.material3.FilterChipDefaults.filterChipElevation (Chip.kt:1294)");
        }
        SelectableChipElevation selectableChipElevation = new SelectableChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return selectableChipElevation;
    }

    /* renamed from: filterChipBorder-_7El2pE, reason: not valid java name */
    public final BorderStroke m1564filterChipBorder_7El2pE(boolean enabled, boolean selected, long borderColor, long selectedBorderColor, long disabledBorderColor, long disabledSelectedBorderColor, float borderWidth, float selectedBorderWidth, Composer $composer, int $changed, int i) {
        long selectedBorderColor2;
        long disabledBorderColor2;
        long disabledSelectedBorderColor2;
        float borderWidth2;
        float selectedBorderWidth2;
        long color;
        $composer.startReplaceableGroup(-1138342447);
        ComposerKt.sourceInformation($composer, "C(filterChipBorder)P(4,5,0:c#ui.graphics.Color,6:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,1:c#ui.unit.Dp,7:c#ui.unit.Dp)1324@66465L5,1326@66617L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(FilterChipTokens.INSTANCE.getFlatUnselectedOutlineColor(), $composer, 6) : borderColor;
        if ((i & 8) == 0) {
            selectedBorderColor2 = selectedBorderColor;
        } else {
            selectedBorderColor2 = Color.INSTANCE.m3444getTransparent0d7_KjU();
        }
        if ((i & 16) != 0) {
            disabledBorderColor2 = Color.m3407copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r9) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilterChipTokens.INSTANCE.getFlatDisabledUnselectedOutlineColor(), $composer, 6)) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        if ((i & 32) == 0) {
            disabledSelectedBorderColor2 = disabledSelectedBorderColor;
        } else {
            disabledSelectedBorderColor2 = Color.INSTANCE.m3444getTransparent0d7_KjU();
        }
        if ((i & 64) == 0) {
            borderWidth2 = borderWidth;
        } else {
            borderWidth2 = FilterChipTokens.INSTANCE.m2590getFlatUnselectedOutlineWidthD9Ej5fM();
        }
        if ((i & 128) == 0) {
            selectedBorderWidth2 = selectedBorderWidth;
        } else {
            selectedBorderWidth2 = FilterChipTokens.INSTANCE.m2586getFlatSelectedOutlineWidthD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1138342447, $changed, -1, "androidx.compose.material3.FilterChipDefaults.filterChipBorder (Chip.kt:1331)");
        }
        if (enabled) {
            color = selected ? selectedBorderColor2 : borderColor2;
        } else {
            color = selected ? disabledSelectedBorderColor2 : disabledBorderColor2;
        }
        BorderStroke m239BorderStrokecXLIe8U = BorderStrokeKt.m239BorderStrokecXLIe8U(selected ? selectedBorderWidth2 : borderWidth2, color);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m239BorderStrokecXLIe8U;
    }

    public final SelectableChipColors elevatedFilterChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1082953289);
        ComposerKt.sourceInformation($composer, "C(elevatedFilterChipColors)1345@67458L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1082953289, $changed, -1, "androidx.compose.material3.FilterChipDefaults.elevatedFilterChipColors (Chip.kt:1345)");
        }
        SelectableChipColors defaultElevatedFilterChipColors$material3_release = getDefaultElevatedFilterChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultElevatedFilterChipColors$material3_release;
    }

    /* renamed from: elevatedFilterChipColors-XqyqHi0, reason: not valid java name */
    public final SelectableChipColors m1562elevatedFilterChipColorsXqyqHi0(long containerColor, long labelColor, long iconColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconColor, long disabledTrailingIconColor, long selectedContainerColor, long disabledSelectedContainerColor, long selectedLabelColor, long selectedLeadingIconColor, long selectedTrailingIconColor, Composer $composer, int $changed, int $changed1, int i) {
        long labelColor2;
        long iconColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long selectedContainerColor2;
        long disabledSelectedContainerColor2;
        long selectedLabelColor2;
        long selectedLeadingIconColor2;
        long selectedTrailingIconColor2;
        $composer.startReplaceableGroup(-915841711);
        ComposerKt.sourceInformation($composer, "C(elevatedFilterChipColors)P(0:c#ui.graphics.Color,7:c#ui.graphics.Color,6:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,8:c#ui.graphics.Color,4:c#ui.graphics.Color,9:c#ui.graphics.Color,10:c#ui.graphics.Color,11:c#ui.graphics.Color)1379@69467L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            iconColor2 = iconColor;
        } else {
            iconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) == 0) {
            disabledContainerColor2 = disabledContainerColor;
        } else {
            disabledContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 16) == 0) {
            disabledLabelColor2 = disabledLabelColor;
        } else {
            disabledLabelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 32) == 0) {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        } else {
            disabledLeadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 64) == 0) {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        } else {
            disabledTrailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 128) == 0) {
            selectedContainerColor2 = selectedContainerColor;
        } else {
            selectedContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 256) == 0) {
            disabledSelectedContainerColor2 = disabledSelectedContainerColor;
        } else {
            disabledSelectedContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 512) == 0) {
            selectedLabelColor2 = selectedLabelColor;
        } else {
            selectedLabelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 1024) == 0) {
            selectedLeadingIconColor2 = selectedLeadingIconColor;
        } else {
            selectedLeadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 2048) == 0) {
            selectedTrailingIconColor2 = selectedTrailingIconColor;
        } else {
            selectedTrailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-915841711, $changed, $changed1, "androidx.compose.material3.FilterChipDefaults.elevatedFilterChipColors (Chip.kt:1379)");
        }
        SelectableChipColors m1831copydaRQuJA = getDefaultElevatedFilterChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1831copydaRQuJA(containerColor2, labelColor2, iconColor2, iconColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconColor2, disabledTrailingIconColor2, selectedContainerColor2, disabledSelectedContainerColor2, selectedLabelColor2, selectedLeadingIconColor2, selectedTrailingIconColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1831copydaRQuJA;
    }

    public final SelectableChipColors getDefaultElevatedFilterChipColors$material3_release(ColorScheme $this$defaultElevatedFilterChipColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        long m3407copywmQWz5c4;
        long m3407copywmQWz5c5;
        SelectableChipColors defaultElevatedFilterChipColorsCached = $this$defaultElevatedFilterChipColors.getDefaultElevatedFilterChipColorsCached();
        if (defaultElevatedFilterChipColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getElevatedUnselectedContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getUnselectedLabelTextColor());
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getLeadingIconUnselectedColor());
            long fromToken4 = ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getLeadingIconUnselectedColor());
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r12) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getElevatedDisabledContainerColor())) : 0.0f);
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r14) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
            m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
            m3407copywmQWz5c4 = Color.m3407copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r17) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
            long fromToken5 = ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getElevatedSelectedContainerColor());
            m3407copywmQWz5c5 = Color.m3407copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r21) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getElevatedDisabledContainerColor())) : 0.0f);
            SelectableChipColors it = new SelectableChipColors(fromToken, fromToken2, fromToken3, fromToken4, m3407copywmQWz5c, m3407copywmQWz5c2, m3407copywmQWz5c3, m3407copywmQWz5c4, fromToken5, m3407copywmQWz5c5, ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getSelectedLabelTextColor()), ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getSelectedLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultElevatedFilterChipColors, FilterChipTokens.INSTANCE.getSelectedLeadingIconColor()), null);
            $this$defaultElevatedFilterChipColors.setDefaultElevatedFilterChipColorsCached$material3_release(it);
            return it;
        }
        return defaultElevatedFilterChipColorsCached;
    }

    /* renamed from: elevatedFilterChipElevation-aqJV_2Y, reason: not valid java name */
    public final SelectableChipElevation m1563elevatedFilterChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(684803697);
        ComposerKt.sourceInformation($composer, "C(elevatedFilterChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? FilterChipTokens.INSTANCE.m2578getElevatedContainerElevationD9Ej5fM() : elevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = FilterChipTokens.INSTANCE.m2582getElevatedPressedContainerElevationD9Ej5fM();
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = FilterChipTokens.INSTANCE.m2580getElevatedFocusContainerElevationD9Ej5fM();
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = FilterChipTokens.INSTANCE.m2581getElevatedHoverContainerElevationD9Ej5fM();
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = FilterChipTokens.INSTANCE.m2577getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = FilterChipTokens.INSTANCE.m2579getElevatedDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(684803697, $changed, -1, "androidx.compose.material3.FilterChipDefaults.elevatedFilterChipElevation (Chip.kt:1442)");
        }
        SelectableChipElevation selectableChipElevation = new SelectableChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return selectableChipElevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-1598643637);
        ComposerKt.sourceInformation($composer, "C1452@73786L5:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1598643637, $changed, -1, "androidx.compose.material3.FilterChipDefaults.<get-shape> (Chip.kt:1452)");
        }
        Shape value = ShapesKt.getValue(FilterChipTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }
}
