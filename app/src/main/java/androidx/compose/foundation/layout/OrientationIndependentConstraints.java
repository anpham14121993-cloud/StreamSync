package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

/* compiled from: RowColumnImpl.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u00002\u00020\u0001B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0007\u0010\rB\u0011\b\u0002\u0012\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000fJ;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0011J\u0015\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b$\u0010\"J\u0013\u0010%\u001a\u00020\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u000fJ\u001b\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010\rJ\u0010\u0010)\u001a\u00020*HÖ\u0001¢\u0006\u0004\b+\u0010,R\u0012\u0010\u0006\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0005\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0012\u0010\u0004\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0012\u0010\u0002\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011R\u0016\u0010\u000e\u001a\u00020\nX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0015\u0088\u0001\u000e\u0092\u0001\u00020\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "", "mainAxisMin", "", "mainAxisMax", "crossAxisMin", "crossAxisMax", "constructor-impl", "(IIII)J", "c", "Landroidx/compose/ui/unit/Constraints;", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)J", "value", "(J)J", "getCrossAxisMax-impl", "(J)I", "getCrossAxisMin-impl", "getMainAxisMax-impl", "getMainAxisMin-impl", "J", "copy", "copy-yUG9Ft0", "(JIIII)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "maxHeight", "maxHeight-impl", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)I", "maxWidth", "maxWidth-impl", "stretchCrossAxis", "stretchCrossAxis-q4ezo7Y", "toBoxConstraints", "toBoxConstraints-OenEA2s", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@JvmInline
/* loaded from: classes.dex */
public final class OrientationIndependentConstraints {
    private final long value;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OrientationIndependentConstraints m530boximpl(long j) {
        return new OrientationIndependentConstraints(j);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    private static long m532constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m536equalsimpl(long j, Object obj) {
        return (obj instanceof OrientationIndependentConstraints) && Constraints.m5685equalsimpl0(j, ((OrientationIndependentConstraints) obj).getValue());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m537equalsimpl0(long j, long j2) {
        return Constraints.m5685equalsimpl0(j, j2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m542hashCodeimpl(long j) {
        return Constraints.m5695hashCodeimpl(j);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m547toStringimpl(long j) {
        return "OrientationIndependentConstraints(value=" + ((Object) Constraints.m5697toStringimpl(j)) + ')';
    }

    public boolean equals(Object obj) {
        return m536equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m542hashCodeimpl(this.value);
    }

    public String toString() {
        return m547toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    private /* synthetic */ OrientationIndependentConstraints(long value) {
        this.value = value;
    }

    /* renamed from: getMainAxisMin-impl, reason: not valid java name */
    public static final int m541getMainAxisMinimpl(long arg0) {
        return Constraints.m5694getMinWidthimpl(arg0);
    }

    /* renamed from: getMainAxisMax-impl, reason: not valid java name */
    public static final int m540getMainAxisMaximpl(long arg0) {
        return Constraints.m5692getMaxWidthimpl(arg0);
    }

    /* renamed from: getCrossAxisMin-impl, reason: not valid java name */
    public static final int m539getCrossAxisMinimpl(long arg0) {
        return Constraints.m5693getMinHeightimpl(arg0);
    }

    /* renamed from: getCrossAxisMax-impl, reason: not valid java name */
    public static final int m538getCrossAxisMaximpl(long arg0) {
        return Constraints.m5691getMaxHeightimpl(arg0);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m531constructorimpl(int mainAxisMin, int mainAxisMax, int crossAxisMin, int crossAxisMax) {
        return m532constructorimpl(ConstraintsKt.Constraints(mainAxisMin, mainAxisMax, crossAxisMin, crossAxisMax));
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m533constructorimpl(long c, LayoutOrientation orientation) {
        return m531constructorimpl(orientation == LayoutOrientation.Horizontal ? Constraints.m5694getMinWidthimpl(c) : Constraints.m5693getMinHeightimpl(c), orientation == LayoutOrientation.Horizontal ? Constraints.m5692getMaxWidthimpl(c) : Constraints.m5691getMaxHeightimpl(c), orientation == LayoutOrientation.Horizontal ? Constraints.m5693getMinHeightimpl(c) : Constraints.m5694getMinWidthimpl(c), orientation == LayoutOrientation.Horizontal ? Constraints.m5691getMaxHeightimpl(c) : Constraints.m5692getMaxWidthimpl(c));
    }

    /* renamed from: stretchCrossAxis-q4ezo7Y, reason: not valid java name */
    public static final long m545stretchCrossAxisq4ezo7Y(long arg0) {
        int m5693getMinHeightimpl;
        int m5694getMinWidthimpl = Constraints.m5694getMinWidthimpl(arg0);
        int m5692getMaxWidthimpl = Constraints.m5692getMaxWidthimpl(arg0);
        if (Constraints.m5691getMaxHeightimpl(arg0) != Integer.MAX_VALUE) {
            m5693getMinHeightimpl = Constraints.m5691getMaxHeightimpl(arg0);
        } else {
            m5693getMinHeightimpl = Constraints.m5693getMinHeightimpl(arg0);
        }
        return m531constructorimpl(m5694getMinWidthimpl, m5692getMaxWidthimpl, m5693getMinHeightimpl, Constraints.m5691getMaxHeightimpl(arg0));
    }

    /* renamed from: toBoxConstraints-OenEA2s, reason: not valid java name */
    public static final long m546toBoxConstraintsOenEA2s(long arg0, LayoutOrientation orientation) {
        return orientation == LayoutOrientation.Horizontal ? ConstraintsKt.Constraints(Constraints.m5694getMinWidthimpl(arg0), Constraints.m5692getMaxWidthimpl(arg0), Constraints.m5693getMinHeightimpl(arg0), Constraints.m5691getMaxHeightimpl(arg0)) : ConstraintsKt.Constraints(Constraints.m5693getMinHeightimpl(arg0), Constraints.m5691getMaxHeightimpl(arg0), Constraints.m5694getMinWidthimpl(arg0), Constraints.m5692getMaxWidthimpl(arg0));
    }

    /* renamed from: maxWidth-impl, reason: not valid java name */
    public static final int m544maxWidthimpl(long arg0, LayoutOrientation orientation) {
        if (orientation == LayoutOrientation.Horizontal) {
            return Constraints.m5692getMaxWidthimpl(arg0);
        }
        return Constraints.m5691getMaxHeightimpl(arg0);
    }

    /* renamed from: maxHeight-impl, reason: not valid java name */
    public static final int m543maxHeightimpl(long arg0, LayoutOrientation orientation) {
        if (orientation == LayoutOrientation.Horizontal) {
            return Constraints.m5691getMaxHeightimpl(arg0);
        }
        return Constraints.m5692getMaxWidthimpl(arg0);
    }

    /* renamed from: copy-yUG9Ft0, reason: not valid java name */
    public static final long m534copyyUG9Ft0(long arg0, int mainAxisMin, int mainAxisMax, int crossAxisMin, int crossAxisMax) {
        return m531constructorimpl(mainAxisMin, mainAxisMax, crossAxisMin, crossAxisMax);
    }
}
