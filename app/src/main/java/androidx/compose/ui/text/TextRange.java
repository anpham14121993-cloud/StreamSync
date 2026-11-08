package androidx.compose.ui.text;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.LastStreamStore;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TextRange.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 *2\u00020\u0001:\u0001*B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u000bH\u0086\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b#\u0010\rJ\u0018\u0010$\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0000ø\u0001\u0000¢\u0006\u0004\b%\u0010\u001bJ\u000f\u0010&\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\tR\u0011\u0010\u0016\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\r\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/ui/text/TextRange;", "", "packedValue", "", "constructor-impl", "(J)J", "collapsed", "", "getCollapsed-impl", "(J)Z", TtmlNode.END, "", "getEnd-impl", "(J)I", LastStreamStore.KEY_LENGTH, "getLength-impl", "max", "getMax-impl", "min", "getMin-impl", "reversed", "getReversed-impl", TtmlNode.START, "getStart-impl", "contains", "other", "contains-5zc-tL8", "(JJ)Z", "offset", "contains-impl", "(JI)Z", "equals", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "intersects", "intersects-5zc-tL8", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@JvmInline
/* loaded from: classes24.dex */
public final class TextRange {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Zero = TextRangeKt.TextRange(0);
    private final long packedValue;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextRange m5223boximpl(long j) {
        return new TextRange(j);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m5224constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m5227equalsimpl(long j, Object obj) {
        return (obj instanceof TextRange) && j == ((TextRange) obj).getPackedValue();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5228equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m5236hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object obj) {
        return m5227equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m5236hashCodeimpl(this.packedValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }

    private /* synthetic */ TextRange(long packedValue) {
        this.packedValue = packedValue;
    }

    /* renamed from: getStart-impl, reason: not valid java name */
    public static final int m5235getStartimpl(long arg0) {
        return (int) (arg0 >> 32);
    }

    /* renamed from: getEnd-impl, reason: not valid java name */
    public static final int m5230getEndimpl(long arg0) {
        return (int) (4294967295L & arg0);
    }

    /* renamed from: getMin-impl, reason: not valid java name */
    public static final int m5233getMinimpl(long arg0) {
        return m5235getStartimpl(arg0) > m5230getEndimpl(arg0) ? m5230getEndimpl(arg0) : m5235getStartimpl(arg0);
    }

    /* renamed from: getMax-impl, reason: not valid java name */
    public static final int m5232getMaximpl(long arg0) {
        return m5235getStartimpl(arg0) > m5230getEndimpl(arg0) ? m5235getStartimpl(arg0) : m5230getEndimpl(arg0);
    }

    /* renamed from: getCollapsed-impl, reason: not valid java name */
    public static final boolean m5229getCollapsedimpl(long arg0) {
        return m5235getStartimpl(arg0) == m5230getEndimpl(arg0);
    }

    /* renamed from: getReversed-impl, reason: not valid java name */
    public static final boolean m5234getReversedimpl(long arg0) {
        return m5235getStartimpl(arg0) > m5230getEndimpl(arg0);
    }

    /* renamed from: getLength-impl, reason: not valid java name */
    public static final int m5231getLengthimpl(long arg0) {
        return m5232getMaximpl(arg0) - m5233getMinimpl(arg0);
    }

    /* renamed from: intersects-5zc-tL8, reason: not valid java name */
    public static final boolean m5237intersects5zctL8(long arg0, long other) {
        return m5233getMinimpl(arg0) < m5232getMaximpl(other) && m5233getMinimpl(other) < m5232getMaximpl(arg0);
    }

    /* renamed from: contains-5zc-tL8, reason: not valid java name */
    public static final boolean m5225contains5zctL8(long arg0, long other) {
        return m5233getMinimpl(arg0) <= m5233getMinimpl(other) && m5232getMaximpl(other) <= m5232getMaximpl(arg0);
    }

    /* renamed from: contains-impl, reason: not valid java name */
    public static final boolean m5226containsimpl(long arg0, int offset) {
        return offset < m5232getMaximpl(arg0) && m5233getMinimpl(arg0) <= offset;
    }

    public String toString() {
        return m5238toStringimpl(this.packedValue);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m5238toStringimpl(long arg0) {
        return "TextRange(" + m5235getStartimpl(arg0) + ", " + m5230getEndimpl(arg0) + ')';
    }

    /* compiled from: TextRange.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/TextRange$Companion;", "", "()V", "Zero", "Landroidx/compose/ui/text/TextRange;", "getZero-d9O1mEE", "()J", "J", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: getZero-d9O1mEE, reason: not valid java name */
        public final long m5240getZerod9O1mEE() {
            return TextRange.Zero;
        }
    }
}
