package coil.compose;

import android.os.SystemClock;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.IntState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* compiled from: CrossfadePainter.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B9\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020!H\u0014J\u0012\u0010-\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0014J\"\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u0015\u00103\u001a\u00020\u0016H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001e\u00105\u001a\u000206*\u0002072\b\u00108\u001a\u0004\u0018\u00010\u00012\u0006\u0010,\u001a\u00020!H\u0002J\f\u00109\u001a\u000206*\u000207H\u0014R/\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R+\u0010\u0019\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\"\u001a\u00020!2\u0006\u0010\f\u001a\u00020!8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006:"}, d2 = {"Lcoil/compose/CrossfadePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", TtmlNode.START, TtmlNode.END, "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "durationMillis", "", "fadeStart", "", "preferExactIntrinsicSize", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/layout/ContentScale;IZZ)V", "<set-?>", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "colorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "intrinsicSize", "Landroidx/compose/ui/geometry/Size;", "getIntrinsicSize-NH-jbRc", "()J", "invalidateTick", "getInvalidateTick", "()I", "setInvalidateTick", "(I)V", "invalidateTick$delegate", "Landroidx/compose/runtime/MutableIntState;", "isDone", "", "maxAlpha", "getMaxAlpha", "()F", "setMaxAlpha", "(F)V", "maxAlpha$delegate", "Landroidx/compose/runtime/MutableFloatState;", "startTimeMillis", "", "applyAlpha", "alpha", "applyColorFilter", "computeDrawSize", "srcSize", "dstSize", "computeDrawSize-x8L_9b0", "(JJ)J", "computeIntrinsicSize", "computeIntrinsicSize-NH-jbRc", "drawPainter", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "painter", "onDraw", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class CrossfadePainter extends Painter {
    public static final int $stable = 0;

    /* renamed from: colorFilter$delegate, reason: from kotlin metadata */
    private final MutableState colorFilter;
    private final ContentScale contentScale;
    private final int durationMillis;
    private final Painter end;
    private final boolean fadeStart;
    private boolean isDone;
    private final boolean preferExactIntrinsicSize;
    private Painter start;

    /* renamed from: invalidateTick$delegate, reason: from kotlin metadata */
    private final MutableIntState invalidateTick = SnapshotIntStateKt.mutableIntStateOf(0);
    private long startTimeMillis = -1;

    /* renamed from: maxAlpha$delegate, reason: from kotlin metadata */
    private final MutableFloatState maxAlpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);

    public CrossfadePainter(Painter start, Painter end, ContentScale contentScale, int durationMillis, boolean fadeStart, boolean preferExactIntrinsicSize) {
        MutableState mutableStateOf$default;
        this.start = start;
        this.end = end;
        this.contentScale = contentScale;
        this.durationMillis = durationMillis;
        this.fadeStart = fadeStart;
        this.preferExactIntrinsicSize = preferExactIntrinsicSize;
        mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.colorFilter = mutableStateOf$default;
    }

    private final int getInvalidateTick() {
        IntState $this$getValue$iv = this.invalidateTick;
        return $this$getValue$iv.getIntValue();
    }

    private final void setInvalidateTick(int i) {
        MutableIntState $this$setValue$iv = this.invalidateTick;
        $this$setValue$iv.setIntValue(i);
    }

    private final float getMaxAlpha() {
        FloatState $this$getValue$iv = this.maxAlpha;
        return $this$getValue$iv.getFloatValue();
    }

    private final void setMaxAlpha(float f) {
        MutableFloatState $this$setValue$iv = this.maxAlpha;
        $this$setValue$iv.setFloatValue(f);
    }

    private final ColorFilter getColorFilter() {
        State $this$getValue$iv = this.colorFilter;
        return (ColorFilter) $this$getValue$iv.getValue();
    }

    private final void setColorFilter(ColorFilter colorFilter) {
        MutableState $this$setValue$iv = this.colorFilter;
        $this$setValue$iv.setValue(colorFilter);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        return m6532computeIntrinsicSizeNHjbRc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.graphics.painter.Painter
    public void onDraw(DrawScope $this$onDraw) {
        if (this.isDone) {
            drawPainter($this$onDraw, this.end, getMaxAlpha());
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.startTimeMillis == -1) {
            this.startTimeMillis = uptimeMillis;
        }
        float percent = ((float) (uptimeMillis - this.startTimeMillis)) / this.durationMillis;
        float endAlpha = RangesKt.coerceIn(percent, 0.0f, 1.0f) * getMaxAlpha();
        float startAlpha = this.fadeStart ? getMaxAlpha() - endAlpha : getMaxAlpha();
        this.isDone = percent >= 1.0f;
        drawPainter($this$onDraw, this.start, startAlpha);
        drawPainter($this$onDraw, this.end, endAlpha);
        if (this.isDone) {
            this.start = null;
        } else {
            setInvalidateTick(getInvalidateTick() + 1);
        }
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float alpha) {
        setMaxAlpha(alpha);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        setColorFilter(colorFilter);
        return true;
    }

    /* renamed from: computeIntrinsicSize-NH-jbRc, reason: not valid java name */
    private final long m6532computeIntrinsicSizeNHjbRc() {
        Painter painter = this.start;
        long startSize = painter != null ? painter.getIntrinsicSize() : Size.INSTANCE.m3246getZeroNHjbRc();
        Painter painter2 = this.end;
        long endSize = painter2 != null ? painter2.getIntrinsicSize() : Size.INSTANCE.m3246getZeroNHjbRc();
        long $this$isSpecified$iv = startSize;
        boolean isStartSpecified = $this$isSpecified$iv != Size.INSTANCE.m3245getUnspecifiedNHjbRc();
        long $this$isSpecified$iv2 = endSize;
        boolean isEndSpecified = $this$isSpecified$iv2 != Size.INSTANCE.m3245getUnspecifiedNHjbRc();
        if (isStartSpecified && isEndSpecified) {
            return SizeKt.Size(Math.max(Size.m3237getWidthimpl(startSize), Size.m3237getWidthimpl(endSize)), Math.max(Size.m3234getHeightimpl(startSize), Size.m3234getHeightimpl(endSize)));
        }
        if (this.preferExactIntrinsicSize) {
            if (isStartSpecified) {
                return startSize;
            }
            if (isEndSpecified) {
                return endSize;
            }
        }
        return Size.INSTANCE.m3245getUnspecifiedNHjbRc();
    }

    private final void drawPainter(DrawScope $this$drawPainter, Painter painter, float alpha) {
        if (painter != null && alpha > 0.0f) {
            long size = $this$drawPainter.mo3959getSizeNHjbRc();
            long drawSize = m6531computeDrawSizex8L_9b0(painter.getIntrinsicSize(), size);
            if ((size == Size.INSTANCE.m3245getUnspecifiedNHjbRc()) || Size.m3239isEmptyimpl(size)) {
                painter.m4035drawx_KDEd0($this$drawPainter, drawSize, alpha, getColorFilter());
                return;
            }
            float f = 2;
            float horizontal$iv = (Size.m3237getWidthimpl(size) - Size.m3237getWidthimpl(drawSize)) / f;
            float vertical$iv = (Size.m3234getHeightimpl(size) - Size.m3234getHeightimpl(drawSize)) / f;
            $this$drawPainter.getDrawContext().getTransform().inset(horizontal$iv, vertical$iv, horizontal$iv, vertical$iv);
            painter.m4035drawx_KDEd0($this$drawPainter, drawSize, alpha, getColorFilter());
            $this$drawPainter.getDrawContext().getTransform().inset(-horizontal$iv, -vertical$iv, -horizontal$iv, -vertical$iv);
        }
    }

    /* renamed from: computeDrawSize-x8L_9b0, reason: not valid java name */
    private final long m6531computeDrawSizex8L_9b0(long srcSize, long dstSize) {
        if ((srcSize == Size.INSTANCE.m3245getUnspecifiedNHjbRc()) || Size.m3239isEmptyimpl(srcSize)) {
            return dstSize;
        }
        if ((dstSize == Size.INSTANCE.m3245getUnspecifiedNHjbRc()) || Size.m3239isEmptyimpl(dstSize)) {
            return dstSize;
        }
        return ScaleFactorKt.m4765timesUQTWf7w(srcSize, this.contentScale.mo4671computeScaleFactorH7hwNQA(srcSize, dstSize));
    }
}
