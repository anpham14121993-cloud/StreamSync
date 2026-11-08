package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.math.MathKt;

/* compiled from: IntOffset.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a*\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001f\u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u001f\u0010\u0013\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0011\u001a\u001f\u0010\u0013\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0011\u001a\u0017\u0010\u0016\u001a\u00020\u0001*\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u0019\u001a\u00020\u000e*\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"IntOffset", "Landroidx/compose/ui/unit/IntOffset;", "x", "", "y", "(II)J", "lerp", TtmlNode.START, "stop", "fraction", "", "lerp-81ZRxRo", "(JJF)J", "minus", "Landroidx/compose/ui/geometry/Offset;", "offset", "minus-Nv-tHpc", "(JJ)J", "minus-oCl6YwE", "plus", "plus-Nv-tHpc", "plus-oCl6YwE", "round", "round-k-4lQ0M", "(J)J", "toOffset", "toOffset--gyyYBs", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class IntOffsetKt {
    public static final long IntOffset(int x, int y) {
        return IntOffset.m5858constructorimpl((x << 32) | (y & 4294967295L));
    }

    /* renamed from: lerp-81ZRxRo, reason: not valid java name */
    public static final long m5875lerp81ZRxRo(long start, long stop, float fraction) {
        return IntOffset(MathHelpersKt.lerp(IntOffset.m5864getXimpl(start), IntOffset.m5864getXimpl(stop), fraction), MathHelpersKt.lerp(IntOffset.m5865getYimpl(start), IntOffset.m5865getYimpl(stop), fraction));
    }

    /* renamed from: toOffset--gyyYBs, reason: not valid java name */
    public static final long m5881toOffsetgyyYBs(long $this$toOffset_u2d_u2dgyyYBs) {
        return OffsetKt.Offset(IntOffset.m5864getXimpl($this$toOffset_u2d_u2dgyyYBs), IntOffset.m5865getYimpl($this$toOffset_u2d_u2dgyyYBs));
    }

    /* renamed from: plus-Nv-tHpc, reason: not valid java name */
    public static final long m5878plusNvtHpc(long $this$plus_u2dNv_u2dtHpc, long offset) {
        return OffsetKt.Offset(Offset.m3168getXimpl($this$plus_u2dNv_u2dtHpc) + IntOffset.m5864getXimpl(offset), Offset.m3169getYimpl($this$plus_u2dNv_u2dtHpc) + IntOffset.m5865getYimpl(offset));
    }

    /* renamed from: minus-Nv-tHpc, reason: not valid java name */
    public static final long m5876minusNvtHpc(long $this$minus_u2dNv_u2dtHpc, long offset) {
        return OffsetKt.Offset(Offset.m3168getXimpl($this$minus_u2dNv_u2dtHpc) - IntOffset.m5864getXimpl(offset), Offset.m3169getYimpl($this$minus_u2dNv_u2dtHpc) - IntOffset.m5865getYimpl(offset));
    }

    /* renamed from: plus-oCl6YwE, reason: not valid java name */
    public static final long m5879plusoCl6YwE(long $this$plus_u2doCl6YwE, long offset) {
        return OffsetKt.Offset(IntOffset.m5864getXimpl($this$plus_u2doCl6YwE) + Offset.m3168getXimpl(offset), IntOffset.m5865getYimpl($this$plus_u2doCl6YwE) + Offset.m3169getYimpl(offset));
    }

    /* renamed from: minus-oCl6YwE, reason: not valid java name */
    public static final long m5877minusoCl6YwE(long $this$minus_u2doCl6YwE, long offset) {
        return OffsetKt.Offset(IntOffset.m5864getXimpl($this$minus_u2doCl6YwE) - Offset.m3168getXimpl(offset), IntOffset.m5865getYimpl($this$minus_u2doCl6YwE) - Offset.m3169getYimpl(offset));
    }

    /* renamed from: round-k-4lQ0M, reason: not valid java name */
    public static final long m5880roundk4lQ0M(long $this$round_u2dk_u2d4lQ0M) {
        return IntOffset(MathKt.roundToInt(Offset.m3168getXimpl($this$round_u2dk_u2d4lQ0M)), MathKt.roundToInt(Offset.m3169getYimpl($this$round_u2dk_u2d4lQ0M)));
    }
}
