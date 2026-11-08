package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.AssistChipTokens;
import androidx.compose.material3.tokens.SuggestionChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: Chip.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0013\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0014JN\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJN\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00042\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J0\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\u00162\b\b\u0002\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.J8\u0010(\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\b\u0002\u0010*\u001a\u00020\u00162\b\b\u0002\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103J\r\u00104\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0014JN\u00104\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b5\u0010\u001dJN\u00106\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00042\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b7\u0010'R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0011\u0010\n\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00068"}, d2 = {"Landroidx/compose/material3/SuggestionChipDefaults;", "", "()V", "Height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM", "()F", "F", "IconSize", "getIconSize-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultElevatedSuggestionChipColors", "Landroidx/compose/material3/ChipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultElevatedSuggestionChipColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "elevatedSuggestionChipColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ChipColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "labelColor", "iconContentColor", "disabledContainerColor", "disabledLabelColor", "disabledIconContentColor", "elevatedSuggestionChipColors-5tl4gsc", "(JJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipColors;", "elevatedSuggestionChipElevation", "Landroidx/compose/material3/ChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "elevatedSuggestionChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipElevation;", "suggestionChipBorder", "Landroidx/compose/material3/ChipBorder;", "borderColor", "disabledBorderColor", "borderWidth", "suggestionChipBorder-d_3_b6Q", "(JJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipBorder;", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "suggestionChipBorder-h1eT-Ww", "(ZJJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "suggestionChipColors", "suggestionChipColors-5tl4gsc", "suggestionChipElevation", "suggestionChipElevation-aqJV_2Y", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class SuggestionChipDefaults {
    public static final int $stable = 0;
    public static final SuggestionChipDefaults INSTANCE = new SuggestionChipDefaults();
    private static final float Height = SuggestionChipTokens.INSTANCE.m2803getContainerHeightD9Ej5fM();
    private static final float IconSize = SuggestionChipTokens.INSTANCE.m2812getLeadingIconSizeD9Ej5fM();

    private SuggestionChipDefaults() {
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m1973getHeightD9Ej5fM() {
        return Height;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1974getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ChipColors suggestionChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1918570697);
        ComposerKt.sourceInformation($composer, "C(suggestionChipColors)1649@83085L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1918570697, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.suggestionChipColors (Chip.kt:1649)");
        }
        ChipColors defaultSuggestionChipColors = ChipKt.getDefaultSuggestionChipColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultSuggestionChipColors;
    }

    /* renamed from: suggestionChipColors-5tl4gsc, reason: not valid java name */
    public final ChipColors m1977suggestionChipColors5tl4gsc(long containerColor, long labelColor, long iconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledIconContentColor, Composer $composer, int $changed, int i) {
        long labelColor2;
        long iconContentColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledIconContentColor2;
        $composer.startReplaceableGroup(1882647883);
        ComposerKt.sourceInformation($composer, "C(suggestionChipColors)P(0:c#ui.graphics.Color,5:c#ui.graphics.Color,4:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color)1670@84154L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            iconContentColor2 = iconContentColor;
        } else {
            iconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
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
            disabledIconContentColor2 = disabledIconContentColor;
        } else {
            disabledIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1882647883, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.suggestionChipColors (Chip.kt:1670)");
        }
        ChipColors m1324copyFD3wquc = ChipKt.getDefaultSuggestionChipColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1324copyFD3wquc(containerColor2, labelColor2, iconContentColor2, Color.INSTANCE.m3445getUnspecified0d7_KjU(), disabledContainerColor2, disabledLabelColor2, disabledIconContentColor2, Color.INSTANCE.m3445getUnspecified0d7_KjU());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1324copyFD3wquc;
    }

    /* renamed from: suggestionChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m1978suggestionChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(1929994057);
        ComposerKt.sourceInformation($composer, "C(suggestionChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? SuggestionChipTokens.INSTANCE.m2810getFlatContainerElevationD9Ej5fM() : elevation;
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
            draggedElevation2 = SuggestionChipTokens.INSTANCE.m2804getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = elevation2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1929994057, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.suggestionChipElevation (Chip.kt:1700)");
        }
        ChipElevation chipElevation = new ChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return chipElevation;
    }

    /* renamed from: suggestionChipBorder-h1eT-Ww, reason: not valid java name */
    public final BorderStroke m1976suggestionChipBorderh1eTWw(boolean enabled, long borderColor, long disabledBorderColor, float borderWidth, Composer $composer, int $changed, int i) {
        long disabledBorderColor2;
        float borderWidth2;
        $composer.startReplaceableGroup(-637354809);
        ComposerKt.sourceInformation($composer, "C(suggestionChipBorder)P(3,0:c#ui.graphics.Color,2:c#ui.graphics.Color,1:c#ui.unit.Dp)1720@86462L5,1721@86552L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(SuggestionChipTokens.INSTANCE.getFlatOutlineColor(), $composer, 6) : borderColor;
        if ((i & 4) != 0) {
            disabledBorderColor2 = Color.m3407copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r6) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(SuggestionChipTokens.INSTANCE.getFlatDisabledOutlineColor(), $composer, 6)) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        if ((i & 8) == 0) {
            borderWidth2 = borderWidth;
        } else {
            borderWidth2 = SuggestionChipTokens.INSTANCE.m2811getFlatOutlineWidthD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-637354809, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.suggestionChipBorder (Chip.kt:1724)");
        }
        BorderStroke m239BorderStrokecXLIe8U = BorderStrokeKt.m239BorderStrokecXLIe8U(borderWidth2, enabled ? borderColor2 : disabledBorderColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m239BorderStrokecXLIe8U;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Maintained for binary compatibility. Use the suggestChipBorder functions instead", replaceWith = @ReplaceWith(expression = "suggestionChipBorder(enabled, borderColor, disabledBorderColor, borderWidth)", imports = {}))
    /* renamed from: suggestionChipBorder-d_3_b6Q, reason: not valid java name */
    public final ChipBorder m1975suggestionChipBorderd_3_b6Q(long borderColor, long disabledBorderColor, float borderWidth, Composer $composer, int $changed, int i) {
        long disabledBorderColor2;
        float borderWidth2;
        $composer.startReplaceableGroup(439283919);
        ComposerKt.sourceInformation($composer, "C(suggestionChipBorder)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color,1:c#ui.unit.Dp)1742@87563L5,1743@87653L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SuggestionChipTokens.INSTANCE.getFlatOutlineColor(), $composer, 6) : borderColor;
        if ((i & 2) != 0) {
            disabledBorderColor2 = Color.m3407copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r6) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(SuggestionChipTokens.INSTANCE.getFlatDisabledOutlineColor(), $composer, 6)) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        if ((i & 4) == 0) {
            borderWidth2 = borderWidth;
        } else {
            borderWidth2 = SuggestionChipTokens.INSTANCE.m2811getFlatOutlineWidthD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(439283919, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.suggestionChipBorder (Chip.kt:1746)");
        }
        ChipBorder chipBorder = new ChipBorder(borderColor2, disabledBorderColor2, borderWidth2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return chipBorder;
    }

    public final ChipColors elevatedSuggestionChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1671233087);
        ComposerKt.sourceInformation($composer, "C(elevatedSuggestionChipColors)1758@88191L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1671233087, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.elevatedSuggestionChipColors (Chip.kt:1758)");
        }
        ChipColors defaultElevatedSuggestionChipColors$material3_release = getDefaultElevatedSuggestionChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultElevatedSuggestionChipColors$material3_release;
    }

    /* renamed from: elevatedSuggestionChipColors-5tl4gsc, reason: not valid java name */
    public final ChipColors m1971elevatedSuggestionChipColors5tl4gsc(long containerColor, long labelColor, long iconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledIconContentColor, Composer $composer, int $changed, int i) {
        long labelColor2;
        long iconContentColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledIconContentColor2;
        $composer.startReplaceableGroup(1269423125);
        ComposerKt.sourceInformation($composer, "C(elevatedSuggestionChipColors)P(0:c#ui.graphics.Color,5:c#ui.graphics.Color,4:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.graphics.Color,2:c#ui.graphics.Color)1779@89281L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            iconContentColor2 = iconContentColor;
        } else {
            iconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
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
            disabledIconContentColor2 = disabledIconContentColor;
        } else {
            disabledIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1269423125, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.elevatedSuggestionChipColors (Chip.kt:1779)");
        }
        ChipColors m1324copyFD3wquc = getDefaultElevatedSuggestionChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1324copyFD3wquc(containerColor2, labelColor2, iconContentColor2, Color.INSTANCE.m3445getUnspecified0d7_KjU(), disabledContainerColor2, disabledLabelColor2, disabledIconContentColor2, Color.INSTANCE.m3445getUnspecified0d7_KjU());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1324copyFD3wquc;
    }

    public final ChipColors getDefaultElevatedSuggestionChipColors$material3_release(ColorScheme $this$defaultElevatedSuggestionChipColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        ChipColors defaultElevatedSuggestionChipColorsCached = $this$defaultElevatedSuggestionChipColors.getDefaultElevatedSuggestionChipColorsCached();
        if (defaultElevatedSuggestionChipColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultElevatedSuggestionChipColors, SuggestionChipTokens.INSTANCE.getElevatedContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLabelTextColor());
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultElevatedSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLeadingIconColor());
            long m3445getUnspecified0d7_KjU = Color.INSTANCE.m3445getUnspecified0d7_KjU();
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r12) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedSuggestionChipColors, SuggestionChipTokens.INSTANCE.getElevatedDisabledContainerColor())) : 0.0f);
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r14) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
            m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedSuggestionChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor())) : 0.0f);
            ChipColors it = new ChipColors(fromToken, fromToken2, fromToken3, m3445getUnspecified0d7_KjU, m3407copywmQWz5c, m3407copywmQWz5c2, m3407copywmQWz5c3, Color.INSTANCE.m3445getUnspecified0d7_KjU(), null);
            $this$defaultElevatedSuggestionChipColors.setDefaultElevatedSuggestionChipColorsCached$material3_release(it);
            return it;
        }
        return defaultElevatedSuggestionChipColorsCached;
    }

    /* renamed from: elevatedSuggestionChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m1972elevatedSuggestionChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(1118088467);
        ComposerKt.sourceInformation($composer, "C(elevatedSuggestionChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? SuggestionChipTokens.INSTANCE.m2805getElevatedContainerElevationD9Ej5fM() : elevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = SuggestionChipTokens.INSTANCE.m2809getElevatedPressedContainerElevationD9Ej5fM();
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = SuggestionChipTokens.INSTANCE.m2807getElevatedFocusContainerElevationD9Ej5fM();
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = SuggestionChipTokens.INSTANCE.m2808getElevatedHoverContainerElevationD9Ej5fM();
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = SuggestionChipTokens.INSTANCE.m2804getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = SuggestionChipTokens.INSTANCE.m2806getElevatedDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1118088467, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.elevatedSuggestionChipElevation (Chip.kt:1831)");
        }
        ChipElevation chipElevation = new ChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return chipElevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(641188183);
        ComposerKt.sourceInformation($composer, "C1841@92627L5:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(641188183, $changed, -1, "androidx.compose.material3.SuggestionChipDefaults.<get-shape> (Chip.kt:1841)");
        }
        Shape value = ShapesKt.getValue(SuggestionChipTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }
}
