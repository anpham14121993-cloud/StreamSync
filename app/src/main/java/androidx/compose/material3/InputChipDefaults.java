package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.InputChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: Chip.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J^\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010$J\u0094\u0001\u0010#\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u001b2\b\b\u0002\u0010&\u001a\u00020\u001b2\b\b\u0002\u0010'\u001a\u00020\u001b2\b\b\u0002\u0010(\u001a\u00020\u001b2\b\b\u0002\u0010)\u001a\u00020\u001b2\b\b\u0002\u0010*\u001a\u00020\u001b2\b\b\u0002\u0010+\u001a\u00020\u001b2\b\b\u0002\u0010,\u001a\u00020\u001b2\b\b\u0002\u0010-\u001a\u00020\u001b2\b\b\u0002\u0010.\u001a\u00020\u001b2\b\b\u0002\u0010/\u001a\u00020\u001b2\b\b\u0002\u00100\u001a\u00020\u001b2\b\b\u0002\u00101\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103JN\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u00020\u00042\b\b\u0002\u00107\u001a\u00020\u00042\b\b\u0002\u00108\u001a\u00020\u00042\b\b\u0002\u00109\u001a\u00020\u00042\b\b\u0002\u0010:\u001a\u00020\u00042\b\b\u0002\u0010;\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006>"}, d2 = {"Landroidx/compose/material3/InputChipDefaults;", "", "()V", "AvatarSize", "Landroidx/compose/ui/unit/Dp;", "getAvatarSize-D9Ej5fM", "()F", "F", "Height", "getHeight-D9Ej5fM", "IconSize", "getIconSize-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultInputChipColors", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultInputChipColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SelectableChipColors;", "inputChipBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "selected", "borderColor", "Landroidx/compose/ui/graphics/Color;", "selectedBorderColor", "disabledBorderColor", "disabledSelectedBorderColor", "borderWidth", "selectedBorderWidth", "inputChipBorder-_7El2pE", "(ZZJJJJFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "inputChipColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SelectableChipColors;", "containerColor", "labelColor", "leadingIconColor", "trailingIconColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconColor", "disabledTrailingIconColor", "selectedContainerColor", "disabledSelectedContainerColor", "selectedLabelColor", "selectedLeadingIconColor", "selectedTrailingIconColor", "inputChipColors-kwJvTHA", "(JJJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SelectableChipColors;", "inputChipElevation", "Landroidx/compose/material3/SelectableChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "inputChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SelectableChipElevation;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class InputChipDefaults {
    public static final int $stable = 0;
    public static final InputChipDefaults INSTANCE = new InputChipDefaults();
    private static final float Height = InputChipTokens.INSTANCE.m2597getContainerHeightD9Ej5fM();
    private static final float IconSize = InputChipTokens.INSTANCE.m2599getLeadingIconSizeD9Ej5fM();
    private static final float AvatarSize = InputChipTokens.INSTANCE.m2595getAvatarSizeD9Ej5fM();

    private InputChipDefaults() {
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m1609getHeightD9Ej5fM() {
        return Height;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1610getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* renamed from: getAvatarSize-D9Ej5fM, reason: not valid java name */
    public final float m1608getAvatarSizeD9Ej5fM() {
        return AvatarSize;
    }

    public final SelectableChipColors inputChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-770375587);
        ComposerKt.sourceInformation($composer, "C(inputChipColors)1480@74502L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-770375587, $changed, -1, "androidx.compose.material3.InputChipDefaults.inputChipColors (Chip.kt:1480)");
        }
        SelectableChipColors defaultInputChipColors$material3_release = getDefaultInputChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultInputChipColors$material3_release;
    }

    /* renamed from: inputChipColors-kwJvTHA, reason: not valid java name */
    public final SelectableChipColors m1612inputChipColorskwJvTHA(long containerColor, long labelColor, long leadingIconColor, long trailingIconColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconColor, long disabledTrailingIconColor, long selectedContainerColor, long disabledSelectedContainerColor, long selectedLabelColor, long selectedLeadingIconColor, long selectedTrailingIconColor, Composer $composer, int $changed, int $changed1, int i) {
        long labelColor2;
        long leadingIconColor2;
        long trailingIconColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long selectedContainerColor2;
        long disabledSelectedContainerColor2;
        long selectedLabelColor2;
        long selectedLeadingIconColor2;
        long selectedTrailingIconColor2;
        $composer.startReplaceableGroup(1312840646);
        ComposerKt.sourceInformation($composer, "C(inputChipColors)P(0:c#ui.graphics.Color,6:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,8:c#ui.graphics.Color,4:c#ui.graphics.Color,9:c#ui.graphics.Color,10:c#ui.graphics.Color,11:c#ui.graphics.Color)1516@76628L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            leadingIconColor2 = leadingIconColor;
        } else {
            leadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) == 0) {
            trailingIconColor2 = trailingIconColor;
        } else {
            trailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 16) == 0) {
            disabledContainerColor2 = disabledContainerColor;
        } else {
            disabledContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 32) == 0) {
            disabledLabelColor2 = disabledLabelColor;
        } else {
            disabledLabelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 64) == 0) {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        } else {
            disabledLeadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 128) == 0) {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        } else {
            disabledTrailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 256) == 0) {
            selectedContainerColor2 = selectedContainerColor;
        } else {
            selectedContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 512) == 0) {
            disabledSelectedContainerColor2 = disabledSelectedContainerColor;
        } else {
            disabledSelectedContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 1024) == 0) {
            selectedLabelColor2 = selectedLabelColor;
        } else {
            selectedLabelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 2048) == 0) {
            selectedLeadingIconColor2 = selectedLeadingIconColor;
        } else {
            selectedLeadingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4096) == 0) {
            selectedTrailingIconColor2 = selectedTrailingIconColor;
        } else {
            selectedTrailingIconColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1312840646, $changed, $changed1, "androidx.compose.material3.InputChipDefaults.inputChipColors (Chip.kt:1516)");
        }
        SelectableChipColors m1831copydaRQuJA = getDefaultInputChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1831copydaRQuJA(containerColor2, labelColor2, leadingIconColor2, trailingIconColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconColor2, disabledTrailingIconColor2, selectedContainerColor2, disabledSelectedContainerColor2, selectedLabelColor2, selectedLeadingIconColor2, selectedTrailingIconColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1831copydaRQuJA;
    }

    public final SelectableChipColors getDefaultInputChipColors$material3_release(ColorScheme $this$defaultInputChipColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        long m3407copywmQWz5c4;
        SelectableChipColors defaultInputChipColorsCached = $this$defaultInputChipColors.getDefaultInputChipColorsCached();
        if (defaultInputChipColorsCached != null) {
            return defaultInputChipColorsCached;
        }
        long m3444getTransparent0d7_KjU = Color.INSTANCE.m3444getTransparent0d7_KjU();
        long fromToken = ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getUnselectedLabelTextColor());
        long fromToken2 = ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getUnselectedLeadingIconColor());
        long fromToken3 = ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getUnselectedTrailingIconColor());
        long m3444getTransparent0d7_KjU2 = Color.INSTANCE.m3444getTransparent0d7_KjU();
        m3407copywmQWz5c = Color.m3407copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r14) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
        m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
        m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r17, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r17) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r17) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r17) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getDisabledTrailingIconColor())) : 0.0f);
        long fromToken4 = ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getSelectedContainerColor());
        m3407copywmQWz5c4 = Color.m3407copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r21) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getDisabledSelectedContainerColor())) : 0.0f);
        SelectableChipColors it = new SelectableChipColors(m3444getTransparent0d7_KjU, fromToken, fromToken2, fromToken3, m3444getTransparent0d7_KjU2, m3407copywmQWz5c, m3407copywmQWz5c2, m3407copywmQWz5c3, fromToken4, m3407copywmQWz5c4, ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getSelectedLabelTextColor()), ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getSelectedLeadingIconColor()), ColorSchemeKt.fromToken($this$defaultInputChipColors, InputChipTokens.INSTANCE.getSelectedTrailingIconColor()), null);
        $this$defaultInputChipColors.setDefaultInputChipColorsCached$material3_release(it);
        return it;
    }

    /* renamed from: inputChipElevation-aqJV_2Y, reason: not valid java name */
    public final SelectableChipElevation m1613inputChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(1745270109);
        ComposerKt.sourceInformation($composer, "C(inputChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? InputChipTokens.INSTANCE.m2596getContainerElevationD9Ej5fM() : elevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = elevation2;
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = elevation2;
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = elevation2;
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = InputChipTokens.INSTANCE.m2598getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = elevation2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1745270109, $changed, -1, "androidx.compose.material3.InputChipDefaults.inputChipElevation (Chip.kt:1580)");
        }
        SelectableChipElevation selectableChipElevation = new SelectableChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return selectableChipElevation;
    }

    /* renamed from: inputChipBorder-_7El2pE, reason: not valid java name */
    public final BorderStroke m1611inputChipBorder_7El2pE(boolean enabled, boolean selected, long borderColor, long selectedBorderColor, long disabledBorderColor, long disabledSelectedBorderColor, float borderWidth, float selectedBorderWidth, Composer $composer, int $changed, int i) {
        long selectedBorderColor2;
        long disabledBorderColor2;
        long disabledSelectedBorderColor2;
        float borderWidth2;
        float selectedBorderWidth2;
        long color;
        $composer.startReplaceableGroup(2050575347);
        ComposerKt.sourceInformation($composer, "C(inputChipBorder)P(4,5,0:c#ui.graphics.Color,6:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,1:c#ui.unit.Dp,7:c#ui.unit.Dp)1609@81579L5,1611@81726L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(InputChipTokens.INSTANCE.getUnselectedOutlineColor(), $composer, 6) : borderColor;
        if ((i & 8) == 0) {
            selectedBorderColor2 = selectedBorderColor;
        } else {
            selectedBorderColor2 = Color.INSTANCE.m3444getTransparent0d7_KjU();
        }
        if ((i & 16) != 0) {
            disabledBorderColor2 = Color.m3407copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r9) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(InputChipTokens.INSTANCE.getDisabledUnselectedOutlineColor(), $composer, 6)) : 0.0f);
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
            borderWidth2 = InputChipTokens.INSTANCE.m2602getUnselectedOutlineWidthD9Ej5fM();
        }
        if ((i & 128) == 0) {
            selectedBorderWidth2 = selectedBorderWidth;
        } else {
            selectedBorderWidth2 = InputChipTokens.INSTANCE.m2600getSelectedOutlineWidthD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2050575347, $changed, -1, "androidx.compose.material3.InputChipDefaults.inputChipBorder (Chip.kt:1616)");
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

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1052444143);
        ComposerKt.sourceInformation($composer, "C1626@82446L5:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1052444143, $changed, -1, "androidx.compose.material3.InputChipDefaults.<get-shape> (Chip.kt:1626)");
        }
        Shape value = ShapesKt.getValue(InputChipTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }
}
