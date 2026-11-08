package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.material3.tokens.ElevatedCardTokens;
import androidx.compose.material3.tokens.FilledCardTokens;
import androidx.compose.material3.tokens.OutlinedCardTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: Card.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0014\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0015J:\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJN\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0015J:\u0010(\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010\u001cJN\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010'J\u0017\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020/H\u0007¢\u0006\u0002\u00100J\r\u00101\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\u0015J:\u00101\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u0010\u001cJN\u00103\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020 2\b\b\u0002\u0010$\u001a\u00020 2\b\b\u0002\u0010%\u001a\u00020 H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u0010'R\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\u0012\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"Landroidx/compose/material3/CardDefaults;", "", "()V", "elevatedShape", "Landroidx/compose/ui/graphics/Shape;", "getElevatedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape", "shape", "getShape", "defaultCardColors", "Landroidx/compose/material3/CardColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultCardColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/CardColors;", "defaultElevatedCardColors", "getDefaultElevatedCardColors$material3_release", "defaultOutlinedCardColors", "getDefaultOutlinedCardColors$material3_release", "cardColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/CardColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledContainerColor", "disabledContentColor", "cardColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardColors;", "cardElevation", "Landroidx/compose/material3/CardElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "cardElevation-aqJV_2Y", "(FFFFFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/CardElevation;", "elevatedCardColors", "elevatedCardColors-ro_MJ88", "elevatedCardElevation", "elevatedCardElevation-aqJV_2Y", "outlinedCardBorder", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "(ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BorderStroke;", "outlinedCardColors", "outlinedCardColors-ro_MJ88", "outlinedCardElevation", "outlinedCardElevation-aqJV_2Y", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class CardDefaults {
    public static final int $stable = 0;
    public static final CardDefaults INSTANCE = new CardDefaults();

    private CardDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1266660211);
        ComposerKt.sourceInformation($composer, "C352@16204L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1266660211, $changed, -1, "androidx.compose.material3.CardDefaults.<get-shape> (Card.kt:352)");
        }
        Shape value = ShapesKt.getValue(FilledCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }

    public final Shape getElevatedShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-133496185);
        ComposerKt.sourceInformation($composer, "C355@16341L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-133496185, $changed, -1, "androidx.compose.material3.CardDefaults.<get-elevatedShape> (Card.kt:355)");
        }
        Shape value = ShapesKt.getValue(ElevatedCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1095404023);
        ComposerKt.sourceInformation($composer, "C358@16478L5:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1095404023, $changed, -1, "androidx.compose.material3.CardDefaults.<get-outlinedShape> (Card.kt:358)");
        }
        Shape value = ShapesKt.getValue(OutlinedCardTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }

    /* renamed from: cardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m1291cardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(-574898487);
        ComposerKt.sourceInformation($composer, "C(cardElevation)P(0:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp):Card.kt#uh7d8r");
        float defaultElevation2 = (i & 1) != 0 ? FilledCardTokens.INSTANCE.m2551getContainerElevationD9Ej5fM() : defaultElevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = FilledCardTokens.INSTANCE.m2557getPressedContainerElevationD9Ej5fM();
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = FilledCardTokens.INSTANCE.m2554getFocusContainerElevationD9Ej5fM();
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = FilledCardTokens.INSTANCE.m2555getHoverContainerElevationD9Ej5fM();
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = FilledCardTokens.INSTANCE.m2553getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = FilledCardTokens.INSTANCE.m2552getDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-574898487, $changed, -1, "androidx.compose.material3.CardDefaults.cardElevation (Card.kt:378)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return cardElevation;
    }

    /* renamed from: elevatedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m1293elevatedCardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(1154241939);
        ComposerKt.sourceInformation($composer, "C(elevatedCardElevation)P(0:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp):Card.kt#uh7d8r");
        float defaultElevation2 = (i & 1) != 0 ? ElevatedCardTokens.INSTANCE.m2467getContainerElevationD9Ej5fM() : defaultElevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = ElevatedCardTokens.INSTANCE.m2473getPressedContainerElevationD9Ej5fM();
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = ElevatedCardTokens.INSTANCE.m2470getFocusContainerElevationD9Ej5fM();
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = ElevatedCardTokens.INSTANCE.m2471getHoverContainerElevationD9Ej5fM();
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = ElevatedCardTokens.INSTANCE.m2469getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = ElevatedCardTokens.INSTANCE.m2468getDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1154241939, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardElevation (Card.kt:406)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return cardElevation;
    }

    /* renamed from: outlinedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m1295outlinedCardElevationaqJV_2Y(float defaultElevation, float pressedElevation, float focusedElevation, float hoveredElevation, float draggedElevation, float disabledElevation, Composer $composer, int $changed, int i) {
        float pressedElevation2;
        float focusedElevation2;
        float hoveredElevation2;
        float draggedElevation2;
        float disabledElevation2;
        $composer.startReplaceableGroup(-97678773);
        ComposerKt.sourceInformation($composer, "C(outlinedCardElevation)P(0:c#ui.unit.Dp,5:c#ui.unit.Dp,3:c#ui.unit.Dp,4:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp):Card.kt#uh7d8r");
        float defaultElevation2 = (i & 1) != 0 ? OutlinedCardTokens.INSTANCE.m2653getContainerElevationD9Ej5fM() : defaultElevation;
        if ((i & 2) == 0) {
            pressedElevation2 = pressedElevation;
        } else {
            pressedElevation2 = defaultElevation2;
        }
        if ((i & 4) == 0) {
            focusedElevation2 = focusedElevation;
        } else {
            focusedElevation2 = defaultElevation2;
        }
        if ((i & 8) == 0) {
            hoveredElevation2 = hoveredElevation;
        } else {
            hoveredElevation2 = defaultElevation2;
        }
        if ((i & 16) == 0) {
            draggedElevation2 = draggedElevation;
        } else {
            draggedElevation2 = OutlinedCardTokens.INSTANCE.m2655getDraggedContainerElevationD9Ej5fM();
        }
        if ((i & 32) == 0) {
            disabledElevation2 = disabledElevation;
        } else {
            disabledElevation2 = OutlinedCardTokens.INSTANCE.m2654getDisabledContainerElevationD9Ej5fM();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-97678773, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardElevation (Card.kt:434)");
        }
        CardElevation cardElevation = new CardElevation(defaultElevation2, pressedElevation2, focusedElevation2, hoveredElevation2, draggedElevation2, disabledElevation2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return cardElevation;
    }

    public final CardColors cardColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-1876034303);
        ComposerKt.sourceInformation($composer, "C(cardColors)448@20842L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1876034303, $changed, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:448)");
        }
        CardColors defaultCardColors$material3_release = getDefaultCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultCardColors$material3_release;
    }

    /* renamed from: cardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m1290cardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContainerColor2;
        long disabledContentColor2;
        long m3407copywmQWz5c;
        $composer.startReplaceableGroup(-1589582123);
        ComposerKt.sourceInformation($composer, "C(cardColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)462@21453L31,465@21651L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            contentColor2 = contentColor;
        } else {
            contentColor2 = ColorSchemeKt.m1395contentColorForek8zF_U(containerColor2, $composer, $changed & 14);
        }
        if ((i & 4) == 0) {
            disabledContainerColor2 = disabledContainerColor;
        } else {
            disabledContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) != 0) {
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(contentColor2) : 0.0f);
            disabledContentColor2 = m3407copywmQWz5c;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589582123, $changed, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:465)");
        }
        CardColors m1285copyjRlVdoo = getDefaultCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1285copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1285copyjRlVdoo;
    }

    public final CardColors getDefaultCardColors$material3_release(ColorScheme $this$defaultCardColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        CardColors defaultCardColorsCached = $this$defaultCardColors.getDefaultCardColorsCached();
        if (defaultCardColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor());
            long m1394contentColorFor4WTKRHQ = ColorSchemeKt.m1394contentColorFor4WTKRHQ($this$defaultCardColors, ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()));
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            long m3454compositeOverOWjLjI = ColorKt.m3454compositeOverOWjLjI(m3407copywmQWz5c, ColorSchemeKt.m1404surfaceColorAtElevation3ABfNKs($this$defaultCardColors, FilledCardTokens.INSTANCE.m2552getDisabledContainerElevationD9Ej5fM()));
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r11) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.m1394contentColorFor4WTKRHQ($this$defaultCardColors, ColorSchemeKt.fromToken($this$defaultCardColors, FilledCardTokens.INSTANCE.getContainerColor()))) : 0.0f);
            CardColors it = new CardColors(fromToken, m1394contentColorFor4WTKRHQ, m3454compositeOverOWjLjI, m3407copywmQWz5c2, null);
            $this$defaultCardColors.setDefaultCardColorsCached$material3_release(it);
            return it;
        }
        return defaultCardColorsCached;
    }

    public final CardColors elevatedCardColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1610137975);
        ComposerKt.sourceInformation($composer, "C(elevatedCardColors)498@23021L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1610137975, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:498)");
        }
        CardColors defaultElevatedCardColors$material3_release = getDefaultElevatedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultElevatedCardColors$material3_release;
    }

    /* renamed from: elevatedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m1292elevatedCardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContainerColor2;
        long disabledContentColor2;
        long m3407copywmQWz5c;
        $composer.startReplaceableGroup(139558303);
        ComposerKt.sourceInformation($composer, "C(elevatedCardColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)512@23689L31,515@23887L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            contentColor2 = contentColor;
        } else {
            contentColor2 = ColorSchemeKt.m1395contentColorForek8zF_U(containerColor2, $composer, $changed & 14);
        }
        if ((i & 4) == 0) {
            disabledContainerColor2 = disabledContainerColor;
        } else {
            disabledContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) != 0) {
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(contentColor2) : 0.0f);
            disabledContentColor2 = m3407copywmQWz5c;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(139558303, $changed, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:515)");
        }
        CardColors m1285copyjRlVdoo = getDefaultElevatedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1285copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1285copyjRlVdoo;
    }

    public final CardColors getDefaultElevatedCardColors$material3_release(ColorScheme $this$defaultElevatedCardColors) {
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        CardColors defaultElevatedCardColorsCached = $this$defaultElevatedCardColors.getDefaultElevatedCardColorsCached();
        if (defaultElevatedCardColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor());
            long m1394contentColorFor4WTKRHQ = ColorSchemeKt.m1394contentColorFor4WTKRHQ($this$defaultElevatedCardColors, ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor()));
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getDisabledContainerColor())) : 0.0f);
            long m3454compositeOverOWjLjI = ColorKt.m3454compositeOverOWjLjI(m3407copywmQWz5c, ColorSchemeKt.m1404surfaceColorAtElevation3ABfNKs($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.m2468getDisabledContainerElevationD9Ej5fM()));
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r11) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.m1394contentColorFor4WTKRHQ($this$defaultElevatedCardColors, ColorSchemeKt.fromToken($this$defaultElevatedCardColors, ElevatedCardTokens.INSTANCE.getContainerColor()))) : 0.0f);
            CardColors it = new CardColors(fromToken, m1394contentColorFor4WTKRHQ, m3454compositeOverOWjLjI, m3407copywmQWz5c2, null);
            $this$defaultElevatedCardColors.setDefaultElevatedCardColorsCached$material3_release(it);
            return it;
        }
        return defaultElevatedCardColorsCached;
    }

    public final CardColors outlinedCardColors(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-1204388929);
        ComposerKt.sourceInformation($composer, "C(outlinedCardColors)547@25299L11:Card.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1204388929, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:547)");
        }
        CardColors defaultOutlinedCardColors$material3_release = getDefaultOutlinedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return defaultOutlinedCardColors$material3_release;
    }

    /* renamed from: outlinedCardColors-ro_MJ88, reason: not valid java name */
    public final CardColors m1294outlinedCardColorsro_MJ88(long containerColor, long contentColor, long disabledContainerColor, long disabledContentColor, Composer $composer, int $changed, int i) {
        long contentColor2;
        long disabledContainerColor2;
        long disabledContentColor2;
        long m3407copywmQWz5c;
        $composer.startReplaceableGroup(-1112362409);
        ComposerKt.sourceInformation($composer, "C(outlinedCardColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)561@25967L31,563@26097L31,564@26184L11:Card.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : containerColor;
        if ((i & 2) == 0) {
            contentColor2 = contentColor;
        } else {
            contentColor2 = ColorSchemeKt.m1395contentColorForek8zF_U(containerColor2, $composer, $changed & 14);
        }
        if ((i & 4) == 0) {
            disabledContainerColor2 = disabledContainerColor;
        } else {
            disabledContainerColor2 = Color.INSTANCE.m3445getUnspecified0d7_KjU();
        }
        if ((i & 8) != 0) {
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r6) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.m1395contentColorForek8zF_U(containerColor2, $composer, $changed & 14)) : 0.0f);
            disabledContentColor2 = m3407copywmQWz5c;
        } else {
            disabledContentColor2 = disabledContentColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1112362409, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardColors (Card.kt:564)");
        }
        CardColors m1285copyjRlVdoo = getDefaultOutlinedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme($composer, 6)).m1285copyjRlVdoo(containerColor2, contentColor2, disabledContainerColor2, disabledContentColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1285copyjRlVdoo;
    }

    public final CardColors getDefaultOutlinedCardColors$material3_release(ColorScheme $this$defaultOutlinedCardColors) {
        long m3407copywmQWz5c;
        CardColors defaultOutlinedCardColorsCached = $this$defaultOutlinedCardColors.getDefaultOutlinedCardColorsCached();
        if (defaultOutlinedCardColorsCached == null) {
            long fromToken = ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor());
            long m1394contentColorFor4WTKRHQ = ColorSchemeKt.m1394contentColorFor4WTKRHQ($this$defaultOutlinedCardColors, ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor()));
            long fromToken2 = ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor());
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r11, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r11) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r11) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r11) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.m1394contentColorFor4WTKRHQ($this$defaultOutlinedCardColors, ColorSchemeKt.fromToken($this$defaultOutlinedCardColors, OutlinedCardTokens.INSTANCE.getContainerColor()))) : 0.0f);
            CardColors it = new CardColors(fromToken, m1394contentColorFor4WTKRHQ, fromToken2, m3407copywmQWz5c, null);
            $this$defaultOutlinedCardColors.setDefaultOutlinedCardColorsCached$material3_release(it);
            return it;
        }
        return defaultOutlinedCardColorsCached;
    }

    public final BorderStroke outlinedCardBorder(boolean enabled, Composer $composer, int $changed, int i) {
        long m3407copywmQWz5c;
        long color;
        Object value$iv;
        $composer.startReplaceableGroup(-392936593);
        ComposerKt.sourceInformation($composer, "C(outlinedCardBorder)602@27787L72:Card.kt#uh7d8r");
        if ((i & 1) != 0) {
            enabled = true;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-392936593, $changed, -1, "androidx.compose.material3.CardDefaults.outlinedCardBorder (Card.kt:590)");
        }
        if (enabled) {
            $composer.startReplaceableGroup(-31426386);
            ComposerKt.sourceInformation($composer, "592@27395L5");
            color = ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getOutlineColor(), $composer, 6);
            $composer.endReplaceableGroup();
        } else {
            $composer.startReplaceableGroup(-31426319);
            ComposerKt.sourceInformation($composer, "594@27470L5,597@27615L11");
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r1, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r1) : 0.12f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r1) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r1) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(OutlinedCardTokens.INSTANCE.getDisabledOutlineColor(), $composer, 6)) : 0.0f);
            color = ColorKt.m3454compositeOverOWjLjI(m3407copywmQWz5c, ColorSchemeKt.m1404surfaceColorAtElevation3ABfNKs(MaterialTheme.INSTANCE.getColorScheme($composer, 6), OutlinedCardTokens.INSTANCE.m2654getDisabledContainerElevationD9Ej5fM()));
            $composer.endReplaceableGroup();
        }
        $composer.startReplaceableGroup(-31425948);
        ComposerKt.sourceInformation($composer, "CC(remember):Card.kt#9igjgp");
        boolean invalid$iv = $composer.changed(color);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = BorderStrokeKt.m239BorderStrokecXLIe8U(OutlinedCardTokens.INSTANCE.m2659getOutlineWidthD9Ej5fM(), color);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        BorderStroke borderStroke = (BorderStroke) value$iv;
        $composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return borderStroke;
    }
}
