package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.IntSize;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: TextPainter.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/text/TextPainter;", "", "()V", "paint", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class TextPainter {
    public static final int $stable = 0;
    public static final TextPainter INSTANCE = new TextPainter();

    private TextPainter() {
    }

    public final void paint(Canvas canvas, TextLayoutResult textLayoutResult) {
        long color;
        float alpha;
        boolean needClipping = textLayoutResult.getHasVisualOverflow() && !TextOverflow.m5670equalsimpl0(textLayoutResult.getLayoutInput().getOverflow(), TextOverflow.INSTANCE.m5679getVisiblegIe3tQ8());
        if (needClipping) {
            float width = IntSize.m5906getWidthimpl(textLayoutResult.getSize());
            float height = IntSize.m5905getHeightimpl(textLayoutResult.getSize());
            Rect bounds = RectKt.m3208Recttz77jQw(Offset.INSTANCE.m3184getZeroF1C5BW0(), SizeKt.Size(width, height));
            canvas.save();
            Canvas.m3382clipRectmtrdDE$default(canvas, bounds, 0, 2, null);
        }
        SpanStyle style = textLayoutResult.getLayoutInput().getStyle().getSpanStyle();
        TextDecoration textDecoration = style.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.INSTANCE.getNone();
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = style.getShadow();
        if (shadow == null) {
            shadow = Shadow.INSTANCE.getNone();
        }
        Shadow shadow2 = shadow;
        Fill drawStyle = style.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        DrawStyle drawStyle2 = drawStyle;
        try {
            Brush brush = style.getBrush();
            if (brush != null) {
                if (style.getTextForegroundStyle() != TextForegroundStyle.Unspecified.INSTANCE) {
                    alpha = style.getTextForegroundStyle().getAlpha();
                } else {
                    alpha = 1.0f;
                }
                textLayoutResult.getMultiParagraph().m5116painthn5TExg(canvas, brush, (r17 & 4) != 0 ? Float.NaN : alpha, (r17 & 8) != 0 ? null : shadow2, (r17 & 16) != 0 ? null : textDecoration2, (r17 & 32) != 0 ? null : drawStyle2, (r17 & 64) != 0 ? DrawScope.INSTANCE.m3960getDefaultBlendMode0nO6VwU() : 0);
            } else {
                if (style.getTextForegroundStyle() != TextForegroundStyle.Unspecified.INSTANCE) {
                    color = style.getTextForegroundStyle().mo5526getColor0d7_KjU();
                } else {
                    color = Color.INSTANCE.m3435getBlack0d7_KjU();
                }
                textLayoutResult.getMultiParagraph().m5114paintLG529CI(canvas, (r14 & 2) != 0 ? Color.INSTANCE.m3445getUnspecified0d7_KjU() : color, (r14 & 4) != 0 ? null : shadow2, (r14 & 8) != 0 ? null : textDecoration2, (r14 & 16) == 0 ? drawStyle2 : null, (r14 & 32) != 0 ? DrawScope.INSTANCE.m3960getDefaultBlendMode0nO6VwU() : 0);
            }
        } finally {
            if (needClipping) {
                canvas.restore();
            }
        }
    }
}
