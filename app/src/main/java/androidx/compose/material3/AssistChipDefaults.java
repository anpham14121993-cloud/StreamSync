package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.AssistChipTokens;
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
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ8\u0010\u0015\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010#Jb\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u00182\b\b\u0002\u0010%\u001a\u00020\u00182\b\b\u0002\u0010&\u001a\u00020\u00182\b\b\u0002\u0010'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020\u00182\b\b\u0002\u0010)\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020\u00182\b\b\u0002\u0010+\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-JN\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020\u00042\b\b\u0002\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u00042\b\b\u0002\u00103\u001a\u00020\u00042\b\b\u0002\u00104\u001a\u00020\u00042\b\b\u0002\u00105\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u00107J\r\u00108\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010#Jb\u00108\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u00182\b\b\u0002\u0010%\u001a\u00020\u00182\b\b\u0002\u0010&\u001a\u00020\u00182\b\b\u0002\u0010'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020\u00182\b\b\u0002\u0010)\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020\u00182\b\b\u0002\u0010+\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010-JN\u0010:\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020\u00042\b\b\u0002\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u00042\b\b\u0002\u00103\u001a\u00020\u00042\b\b\u0002\u00104\u001a\u00020\u00042\b\b\u0002\u00105\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b;\u00107R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0011\u0010\n\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u000f*\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"}, d2 = {"Landroidx/compose/material3/AssistChipDefaults;", "", "()V", "Height", "Landroidx/compose/ui/unit/Dp;", "getHeight-D9Ej5fM", "()F", "F", "IconSize", "getIconSize-D9Ej5fM", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "defaultAssistChipColors", "Landroidx/compose/material3/ChipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultAssistChipColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "defaultElevatedAssistChipColors", "getDefaultElevatedAssistChipColors$material3_release", "assistChipBorder", "Landroidx/compose/material3/ChipBorder;", "borderColor", "Landroidx/compose/ui/graphics/Color;", "disabledBorderColor", "borderWidth", "assistChipBorder-d_3_b6Q", "(JJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipBorder;", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "assistChipBorder-h1eT-Ww", "(ZJJFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "assistChipColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ChipColors;", "containerColor", "labelColor", "leadingIconContentColor", "trailingIconContentColor", "disabledContainerColor", "disabledLabelColor", "disabledLeadingIconContentColor", "disabledTrailingIconContentColor", "assistChipColors-oq7We08", "(JJJJJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipColors;", "assistChipElevation", "Landroidx/compose/material3/ChipElevation;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "assistChipElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/ChipElevation;", "elevatedAssistChipColors", "elevatedAssistChipColors-oq7We08", "elevatedAssistChipElevation", "elevatedAssistChipElevation-aqJV_2Y", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class AssistChipDefaults {
    public static final int $stable = 0;
    public static final AssistChipDefaults INSTANCE = new AssistChipDefaults();
    private static final float Height = AssistChipTokens.INSTANCE.m2303getContainerHeightD9Ej5fM();
    private static final float IconSize = AssistChipTokens.INSTANCE.m2312getIconSizeD9Ej5fM();

    private AssistChipDefaults() {
    }

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public final float m1245getHeightD9Ej5fM() {
        return Height;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1246getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ChipColors assistChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1961061417);
        ComposerKt.sourceInformation($composer, "C(assistChipColors)952@47331L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1961061417, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipColors (Chip.kt:952)");
        }
        ChipColors defaultAssistChipColors$material3_release = getDefaultAssistChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultAssistChipColors$material3_release;
    }

    /* renamed from: assistChipColors-oq7We08, reason: not valid java name */
    public final ChipColors m1241assistChipColorsoq7We08(long containerColor, long labelColor, long leadingIconContentColor, long trailingIconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconContentColor, long disabledTrailingIconContentColor, Composer $composer, int $changed, int i) {
        long labelColor2;
        long leadingIconContentColor2;
        long trailingIconContentColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledLeadingIconContentColor2;
        long disabledTrailingIconContentColor2;
        $composer.startReplaceableGroup(-391745725);
        ComposerKt.sourceInformation($composer, "C(assistChipColors)P(0:c#ui.graphics.Color,5:c#ui.graphics.Color,6:c#ui.graphics.Color,7:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color)977@48744L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            leadingIconContentColor2 = leadingIconContentColor;
        } else {
            leadingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) == 0) {
            trailingIconContentColor2 = trailingIconContentColor;
        } else {
            trailingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
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
            disabledLeadingIconContentColor2 = disabledLeadingIconContentColor;
        } else {
            disabledLeadingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 128) == 0) {
            disabledTrailingIconContentColor2 = disabledTrailingIconContentColor;
        } else {
            disabledTrailingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-391745725, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipColors (Chip.kt:977)");
        }
        ChipColors m1324copyFD3wquc = getDefaultAssistChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1324copyFD3wquc(containerColor2, labelColor2, leadingIconContentColor2, trailingIconContentColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconContentColor2, disabledTrailingIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1324copyFD3wquc;
    }

    public final ChipColors getDefaultAssistChipColors$material3_release(ColorScheme $this$defaultAssistChipColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        ChipColors defaultAssistChipColorsCached = $this$defaultAssistChipColors.getDefaultAssistChipColorsCached();
        if (defaultAssistChipColorsCached != null) {
            return defaultAssistChipColorsCached;
        }
        long m3444getTransparent0d7_KjU = Color.INSTANCE.m3444getTransparent0d7_KjU();
        long fromToken = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getLabelTextColor());
        long fromToken2 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
        long fromToken3 = ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
        long m3444getTransparent0d7_KjU2 = Color.INSTANCE.m3444getTransparent0d7_KjU();
        m3407copywmQWz5c = Color.m3407copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r14) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
        m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor())) : 0.0f);
        m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r21) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor())) : 0.0f);
        ChipColors it = new ChipColors(m3444getTransparent0d7_KjU, fromToken, fromToken2, fromToken3, m3444getTransparent0d7_KjU2, m3407copywmQWz5c, m3407copywmQWz5c2, m3407copywmQWz5c3, null);
        $this$defaultAssistChipColors.setDefaultAssistChipColorsCached$material3_release(it);
        return it;
    }

    /* renamed from: assistChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m1242assistChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(245366099);
        ComposerKt.sourceInformation($composer, "C(assistChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? AssistChipTokens.INSTANCE.m2310getFlatContainerElevationD9Ej5fM() : elevation;
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
            draggedElevation2 = AssistChipTokens.INSTANCE.m2304getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = elevation2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(245366099, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipElevation (Chip.kt:1029)");
        }
        ChipElevation chipElevation = new ChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return chipElevation;
    }

    /* renamed from: assistChipBorder-h1eT-Ww, reason: not valid java name */
    public final BorderStroke m1240assistChipBorderh1eTWw(boolean enabled, long borderColor, long disabledBorderColor, float borderWidth, Composer $composer, int $changed, int i) {
        long disabledBorderColor2;
        float borderWidth2;
        $composer.startReplaceableGroup(-1458649561);
        ComposerKt.sourceInformation($composer, "C(assistChipBorder)P(3,0:c#ui.graphics.Color,2:c#ui.graphics.Color,1:c#ui.unit.Dp)1049@52199L5,1050@52285L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatOutlineColor(), $composer, 6) : borderColor;
        if ((i & 4) != 0) {
            disabledBorderColor2 = Color.m3407copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r6) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatDisabledOutlineColor(), $composer, 6)) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        if ((i & 8) == 0) {
            borderWidth2 = borderWidth;
        } else {
            borderWidth2 = AssistChipTokens.INSTANCE.m2311getFlatOutlineWidthD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1458649561, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipBorder (Chip.kt:1053)");
        }
        BorderStroke m239BorderStrokecXLIe8U = BorderStrokeKt.m239BorderStrokecXLIe8U(borderWidth2, enabled ? borderColor2 : disabledBorderColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m239BorderStrokecXLIe8U;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Maintained for binary compatibility. Use the assistChipBorder function that returns BorderStroke instead", replaceWith = @ReplaceWith(expression = "assistChipBorder(enabled, borderColor, disabledBorderColor, borderWidth)", imports = {}))
    /* renamed from: assistChipBorder-d_3_b6Q, reason: not valid java name */
    public final ChipBorder m1239assistChipBorderd_3_b6Q(long borderColor, long disabledBorderColor, float borderWidth, Composer $composer, int $changed, int i) {
        long disabledBorderColor2;
        float borderWidth2;
        $composer.startReplaceableGroup(382372847);
        ComposerKt.sourceInformation($composer, "C(assistChipBorder)P(0:c#ui.graphics.Color,2:c#ui.graphics.Color,1:c#ui.unit.Dp)1072@53313L5,1073@53399L5:Chip.kt#uh7d8r");
        long borderColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatOutlineColor(), $composer, 6) : borderColor;
        if ((i & 2) != 0) {
            disabledBorderColor2 = Color.m3407copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r6) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(AssistChipTokens.INSTANCE.getFlatDisabledOutlineColor(), $composer, 6)) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        if ((i & 4) == 0) {
            borderWidth2 = borderWidth;
        } else {
            borderWidth2 = AssistChipTokens.INSTANCE.m2311getFlatOutlineWidthD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(382372847, $changed, -1, "androidx.compose.material3.AssistChipDefaults.assistChipBorder (Chip.kt:1076)");
        }
        ChipBorder chipBorder = new ChipBorder(borderColor2, disabledBorderColor2, borderWidth2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return chipBorder;
    }

    public final ChipColors elevatedAssistChipColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(655175583);
        ComposerKt.sourceInformation($composer, "C(elevatedAssistChipColors)1087@53913L11:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(655175583, $changed, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipColors (Chip.kt:1087)");
        }
        ChipColors defaultElevatedAssistChipColors$material3_release = getDefaultElevatedAssistChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultElevatedAssistChipColors$material3_release;
    }

    /* renamed from: elevatedAssistChipColors-oq7We08, reason: not valid java name */
    public final ChipColors m1243elevatedAssistChipColorsoq7We08(long containerColor, long labelColor, long leadingIconContentColor, long trailingIconContentColor, long disabledContainerColor, long disabledLabelColor, long disabledLeadingIconContentColor, long disabledTrailingIconContentColor, Composer $composer, int $changed, int i) {
        long labelColor2;
        long leadingIconContentColor2;
        long trailingIconContentColor2;
        long disabledContainerColor2;
        long disabledLabelColor2;
        long disabledLeadingIconContentColor2;
        long disabledTrailingIconContentColor2;
        $composer.startReplaceableGroup(-535762675);
        ComposerKt.sourceInformation($composer, "C(elevatedAssistChipColors)P(0:c#ui.graphics.Color,5:c#ui.graphics.Color,6:c#ui.graphics.Color,7:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color,4:c#ui.graphics.Color)1112@55342L11:Chip.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            labelColor2 = labelColor;
        } else {
            labelColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 4) == 0) {
            leadingIconContentColor2 = leadingIconContentColor;
        } else {
            leadingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) == 0) {
            trailingIconContentColor2 = trailingIconContentColor;
        } else {
            trailingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
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
            disabledLeadingIconContentColor2 = disabledLeadingIconContentColor;
        } else {
            disabledLeadingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 128) == 0) {
            disabledTrailingIconContentColor2 = disabledTrailingIconContentColor;
        } else {
            disabledTrailingIconContentColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-535762675, $changed, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipColors (Chip.kt:1112)");
        }
        ChipColors m1324copyFD3wquc = SuggestionChipDefaults.INSTANCE.getDefaultElevatedSuggestionChipColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1324copyFD3wquc(containerColor2, labelColor2, leadingIconContentColor2, trailingIconContentColor2, disabledContainerColor2, disabledLabelColor2, disabledLeadingIconContentColor2, disabledTrailingIconContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1324copyFD3wquc;
    }

    public final ChipColors getDefaultElevatedAssistChipColors$material3_release(ColorScheme $this$defaultElevatedAssistChipColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        long m3407copywmQWz5c4;
        ChipColors defaultElevatedAssistChipColorsCached = $this$defaultElevatedAssistChipColors.getDefaultElevatedAssistChipColorsCached();
        if (defaultElevatedAssistChipColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getElevatedContainerColor());
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getLabelTextColor());
            long fromToken3 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
            long fromToken4 = ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getIconColor());
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r12, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r12) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r12) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r12) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getElevatedDisabledContainerColor())) : 0.0f);
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r14) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
            m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor())) : 0.0f);
            m3407copywmQWz5c4 = Color.m3407copywmQWz5c(r21, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r21) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r21) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r21) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedAssistChipColors, AssistChipTokens.INSTANCE.getDisabledIconColor())) : 0.0f);
            ChipColors it = new ChipColors(fromToken, fromToken2, fromToken3, fromToken4, m3407copywmQWz5c, m3407copywmQWz5c2, m3407copywmQWz5c3, m3407copywmQWz5c4, null);
            $this$defaultElevatedAssistChipColors.setDefaultElevatedAssistChipColorsCached$material3_release(it);
            return it;
        }
        return defaultElevatedAssistChipColorsCached;
    }

    /* renamed from: elevatedAssistChipElevation-aqJV_2Y, reason: not valid java name */
    public final ChipElevation m1244elevatedAssistChipElevationaqJV_2Y(float elevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(1457698077);
        ComposerKt.sourceInformation($composer, "C(elevatedAssistChipElevation)P(2:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,1:c#ui.unit.Dp,0:c#ui.unit.Dp):Chip.kt#uh7d8r");
        float elevation2 = (i & 1) != 0 ? AssistChipTokens.INSTANCE.m2305getElevatedContainerElevationD9Ej5fM() : elevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = AssistChipTokens.INSTANCE.m2309getElevatedPressedContainerElevationD9Ej5fM();
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = AssistChipTokens.INSTANCE.m2307getElevatedFocusContainerElevationD9Ej5fM();
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = AssistChipTokens.INSTANCE.m2308getElevatedHoverContainerElevationD9Ej5fM();
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = AssistChipTokens.INSTANCE.m2304getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = AssistChipTokens.INSTANCE.m2306getElevatedDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1457698077, $changed, -1, "androidx.compose.material3.AssistChipDefaults.elevatedAssistChipElevation (Chip.kt:1164)");
        }
        ChipElevation chipElevation = new ChipElevation(elevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return chipElevation;
    }

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1988153916);
        ComposerKt.sourceInformation($composer, "C1174@58755L5:Chip.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1988153916, $changed, -1, "androidx.compose.material3.AssistChipDefaults.<get-shape> (Chip.kt:1174)");
        }
        Shape value = ShapesKt.getValue(AssistChipTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }
}
