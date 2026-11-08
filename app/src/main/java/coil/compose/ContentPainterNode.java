package coil.compose;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* compiled from: ContentPainterModifier.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0002ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u001a\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u0010+J\f\u00100\u001a\u000201*\u000202H\u0016J\u001c\u00103\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000204H\u0016J\u001c\u00109\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u000204H\u0016J&\u0010;\u001a\u00020<*\u00020=2\u0006\u00106\u001a\u00020>2\u0006\u0010.\u001a\u00020-H\u0016ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\u001c\u0010A\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000204H\u0016J\u001c\u0010B\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u000204H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"Lcoil/compose/ContentPainterNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "getAlpha", "()F", "setAlpha", "(F)V", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getContentScale", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale", "(Landroidx/compose/ui/layout/ContentScale;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "calculateScaledSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "modifyConstraints", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ContentPainterNode extends Modifier.Node implements DrawModifierNode, LayoutModifierNode {
    public static final int $stable = 8;
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;

    public final Painter getPainter() {
        return this.painter;
    }

    public final void setPainter(Painter painter) {
        this.painter = painter;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final void setContentScale(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public ContentPainterNode(Painter painter, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter) {
        this.painter = painter;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = alpha;
        this.colorFilter = colorFilter;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo92measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo4680measureBRTryo0(m6530modifyConstraintsZezNO4M(constraints));
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: coil.compose.ContentPainterNode$measure$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                Placeable.PlacementScope.placeRelative$default($this$layout, Placeable.this, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        long $this$isSpecified$iv = this.painter.getIntrinsicSize();
        if ($this$isSpecified$iv != Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            long constraints = ConstraintsKt.Constraints$default(0, 0, 0, height, 7, null);
            int layoutWidth = measurable.minIntrinsicWidth(Constraints.m5691getMaxHeightimpl(m6530modifyConstraintsZezNO4M(constraints)));
            long scaledSize = m6529calculateScaledSizeE7KxVPU(SizeKt.Size(layoutWidth, height));
            return Math.max(MathKt.roundToInt(Size.m3237getWidthimpl(scaledSize)), layoutWidth);
        }
        return measurable.minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        long $this$isSpecified$iv = this.painter.getIntrinsicSize();
        if ($this$isSpecified$iv != Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            long constraints = ConstraintsKt.Constraints$default(0, 0, 0, height, 7, null);
            int layoutWidth = measurable.maxIntrinsicWidth(Constraints.m5691getMaxHeightimpl(m6530modifyConstraintsZezNO4M(constraints)));
            long scaledSize = m6529calculateScaledSizeE7KxVPU(SizeKt.Size(layoutWidth, height));
            return Math.max(MathKt.roundToInt(Size.m3237getWidthimpl(scaledSize)), layoutWidth);
        }
        return measurable.maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        long $this$isSpecified$iv = this.painter.getIntrinsicSize();
        if ($this$isSpecified$iv != Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            long constraints = ConstraintsKt.Constraints$default(0, width, 0, 0, 13, null);
            int layoutHeight = measurable.minIntrinsicHeight(Constraints.m5692getMaxWidthimpl(m6530modifyConstraintsZezNO4M(constraints)));
            long scaledSize = m6529calculateScaledSizeE7KxVPU(SizeKt.Size(width, layoutHeight));
            return Math.max(MathKt.roundToInt(Size.m3234getHeightimpl(scaledSize)), layoutHeight);
        }
        return measurable.minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        long $this$isSpecified$iv = this.painter.getIntrinsicSize();
        if ($this$isSpecified$iv != Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            long constraints = ConstraintsKt.Constraints$default(0, width, 0, 0, 13, null);
            int layoutHeight = measurable.maxIntrinsicHeight(Constraints.m5692getMaxWidthimpl(m6530modifyConstraintsZezNO4M(constraints)));
            long scaledSize = m6529calculateScaledSizeE7KxVPU(SizeKt.Size(width, layoutHeight));
            return Math.max(MathKt.roundToInt(Size.m3234getHeightimpl(scaledSize)), layoutHeight);
        }
        return measurable.maxIntrinsicHeight(width);
    }

    /* renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m6529calculateScaledSizeE7KxVPU(long dstSize) {
        if (Size.m3239isEmptyimpl(dstSize)) {
            return Size.INSTANCE.m3246getZeroNHjbRc();
        }
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (intrinsicSize == Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            return dstSize;
        }
        float $this$takeOrElse$iv = Size.m3237getWidthimpl(intrinsicSize);
        if (!((Float.isInfinite($this$takeOrElse$iv) || Float.isNaN($this$takeOrElse$iv)) ? false : true)) {
            $this$takeOrElse$iv = Size.m3237getWidthimpl(dstSize);
        }
        float $this$takeOrElse$iv2 = Size.m3234getHeightimpl(intrinsicSize);
        if (!((Float.isInfinite($this$takeOrElse$iv2) || Float.isNaN($this$takeOrElse$iv2)) ? false : true)) {
            $this$takeOrElse$iv2 = Size.m3234getHeightimpl(dstSize);
        }
        long srcSize = SizeKt.Size($this$takeOrElse$iv, $this$takeOrElse$iv2);
        long scaleFactor = this.contentScale.mo4671computeScaleFactorH7hwNQA(srcSize, dstSize);
        float m4750getScaleXimpl = ScaleFactor.m4750getScaleXimpl(scaleFactor);
        if ((Float.isInfinite(m4750getScaleXimpl) || Float.isNaN(m4750getScaleXimpl)) ? false : true) {
            float m4751getScaleYimpl = ScaleFactor.m4751getScaleYimpl(scaleFactor);
            if ((Float.isInfinite(m4751getScaleYimpl) || Float.isNaN(m4751getScaleYimpl)) ? false : true) {
                return ScaleFactorKt.m4766timesmw2e94(scaleFactor, srcSize);
            }
        }
        return dstSize;
    }

    /* renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m6530modifyConstraintsZezNO4M(long constraints) {
        float dstWidth;
        float dstHeight;
        long m5682copyZbe2FdA;
        long m5682copyZbe2FdA2;
        boolean hasFixedWidth = Constraints.m5690getHasFixedWidthimpl(constraints);
        boolean hasFixedHeight = Constraints.m5689getHasFixedHeightimpl(constraints);
        if (hasFixedWidth && hasFixedHeight) {
            return constraints;
        }
        boolean hasBoundedSize = Constraints.m5688getHasBoundedWidthimpl(constraints) && Constraints.m5687getHasBoundedHeightimpl(constraints);
        long intrinsicSize = this.painter.getIntrinsicSize();
        if ((intrinsicSize == Size.INSTANCE.m3245getUnspecifiedNHjbRc() ? 1 : 0) != 0) {
            if (!hasBoundedSize) {
                return constraints;
            }
            m5682copyZbe2FdA2 = Constraints.m5682copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m5694getMinWidthimpl(constraints) : Constraints.m5692getMaxWidthimpl(constraints), (r12 & 2) != 0 ? Constraints.m5692getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m5693getMinHeightimpl(constraints) : Constraints.m5691getMaxHeightimpl(constraints), (r12 & 8) != 0 ? Constraints.m5691getMaxHeightimpl(constraints) : 0);
            return m5682copyZbe2FdA2;
        }
        if (hasBoundedSize && (hasFixedWidth || hasFixedHeight)) {
            float dstWidth2 = Constraints.m5692getMaxWidthimpl(constraints);
            dstWidth = dstWidth2;
            dstHeight = Constraints.m5691getMaxHeightimpl(constraints);
        } else {
            float intrinsicWidth = Size.m3237getWidthimpl(intrinsicSize);
            float intrinsicHeight = Size.m3234getHeightimpl(intrinsicSize);
            float dstWidth3 = !Float.isInfinite(intrinsicWidth) && !Float.isNaN(intrinsicWidth) ? UtilsKt.m6561constrainWidthK40F9xA(constraints, intrinsicWidth) : Constraints.m5694getMinWidthimpl(constraints);
            dstWidth = dstWidth3;
            dstHeight = (Float.isInfinite(intrinsicHeight) || Float.isNaN(intrinsicHeight)) ? false : true ? UtilsKt.m6560constrainHeightK40F9xA(constraints, intrinsicHeight) : Constraints.m5693getMinHeightimpl(constraints);
        }
        long m6529calculateScaledSizeE7KxVPU = m6529calculateScaledSizeE7KxVPU(SizeKt.Size(dstWidth, dstHeight));
        float m3237getWidthimpl = Size.m3237getWidthimpl(m6529calculateScaledSizeE7KxVPU);
        float scaledHeight = Size.m3234getHeightimpl(m6529calculateScaledSizeE7KxVPU);
        m5682copyZbe2FdA = Constraints.m5682copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m5694getMinWidthimpl(constraints) : ConstraintsKt.m5706constrainWidthK40F9xA(constraints, MathKt.roundToInt(m3237getWidthimpl)), (r12 & 2) != 0 ? Constraints.m5692getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m5693getMinHeightimpl(constraints) : ConstraintsKt.m5705constrainHeightK40F9xA(constraints, MathKt.roundToInt(scaledHeight)), (r12 & 8) != 0 ? Constraints.m5691getMaxHeightimpl(constraints) : 0);
        return m5682copyZbe2FdA;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        long scaledSize = m6529calculateScaledSizeE7KxVPU($this$draw.mo3959getSizeNHjbRc());
        long mo3045alignKFBX0sM = this.alignment.mo3045alignKFBX0sM(UtilsKt.m6563toIntSizeuvyYCjk(scaledSize), UtilsKt.m6563toIntSizeuvyYCjk($this$draw.mo3959getSizeNHjbRc()), $this$draw.getLayoutDirection());
        int dx = IntOffset.m5856component1impl(mo3045alignKFBX0sM);
        int dy = IntOffset.m5857component2impl(mo3045alignKFBX0sM);
        ContentDrawScope $this$translate$iv = $this$draw;
        float left$iv = dx;
        float top$iv = dy;
        $this$translate$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
        Painter $this$draw_u24lambda_u243_u24lambda_u242 = this.painter;
        $this$draw_u24lambda_u243_u24lambda_u242.m4035drawx_KDEd0($this$translate$iv, scaledSize, this.alpha, this.colorFilter);
        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
        $this$draw.drawContent();
    }
}
