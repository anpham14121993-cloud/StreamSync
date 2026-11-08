package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: Rect.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u0006\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a \u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\rH\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"}, d2 = {"Rect", "Landroidx/compose/ui/geometry/Rect;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "bottomRight", "Rect-0a9Yr6o", "(JJ)Landroidx/compose/ui/geometry/Rect;", "offset", "size", "Landroidx/compose/ui/geometry/Size;", "Rect-tz77jQw", TtmlNode.CENTER, "radius", "", "Rect-3MmeM6k", "(JF)Landroidx/compose/ui/geometry/Rect;", "lerp", TtmlNode.START, "stop", "fraction", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class RectKt {
    /* renamed from: Rect-tz77jQw, reason: not valid java name */
    public static final Rect m3208Recttz77jQw(long offset, long size) {
        return new Rect(Offset.m3168getXimpl(offset), Offset.m3169getYimpl(offset), Offset.m3168getXimpl(offset) + Size.m3237getWidthimpl(size), Offset.m3169getYimpl(offset) + Size.m3234getHeightimpl(size));
    }

    /* renamed from: Rect-0a9Yr6o, reason: not valid java name */
    public static final Rect m3206Rect0a9Yr6o(long topLeft, long bottomRight) {
        return new Rect(Offset.m3168getXimpl(topLeft), Offset.m3169getYimpl(topLeft), Offset.m3168getXimpl(bottomRight), Offset.m3169getYimpl(bottomRight));
    }

    /* renamed from: Rect-3MmeM6k, reason: not valid java name */
    public static final Rect m3207Rect3MmeM6k(long center, float radius) {
        return new Rect(Offset.m3168getXimpl(center) - radius, Offset.m3169getYimpl(center) - radius, Offset.m3168getXimpl(center) + radius, Offset.m3169getYimpl(center) + radius);
    }

    public static final Rect lerp(Rect start, Rect stop, float fraction) {
        return new Rect(MathHelpersKt.lerp(start.getLeft(), stop.getLeft(), fraction), MathHelpersKt.lerp(start.getTop(), stop.getTop(), fraction), MathHelpersKt.lerp(start.getRight(), stop.getRight(), fraction), MathHelpersKt.lerp(start.getBottom(), stop.getBottom(), fraction));
    }
}
