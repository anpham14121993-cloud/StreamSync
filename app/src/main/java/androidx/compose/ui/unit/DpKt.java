package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt;

/* compiled from: Dp.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b:\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\"\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a\"\u0010:\u001a\u00020\u00022\u0006\u00102\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b;\u00109\u001a*\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020\b2\u0006\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a*\u0010<\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a*\u0010<\u001a\u00020\u00022\u0006\u0010=\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\bD\u0010C\u001a#\u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\bH\u0010I\u001a#\u0010J\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\bK\u0010I\u001a\u001f\u0010L\u001a\u00020\b*\u00020\b2\u0006\u0010M\u001a\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\bN\u0010I\u001a\u001f\u0010O\u001a\u00020\b*\u00020\b2\u0006\u0010P\u001a\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\bQ\u0010I\u001a'\u0010R\u001a\u00020\b*\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010P\u001a\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0004\bS\u0010A\u001a%\u0010T\u001a\u00020\b*\u00020\b2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020\b0VH\u0086\bø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a%\u0010T\u001a\u00020\u0001*\u00020\u00012\f\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00010VH\u0086\bø\u0001\u0000¢\u0006\u0004\bY\u0010Z\u001a%\u0010T\u001a\u00020\u0002*\u00020\u00022\f\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00020VH\u0086\bø\u0001\u0000¢\u0006\u0004\b[\u0010Z\u001a\u001f\u0010\\\u001a\u00020\b*\u00020\t2\u0006\u0010]\u001a\u00020\bH\u0087\nø\u0001\u0000¢\u0006\u0004\b^\u0010_\u001a\u001f\u0010\\\u001a\u00020\b*\u00020\u000e2\u0006\u0010]\u001a\u00020\bH\u0087\nø\u0001\u0000¢\u0006\u0004\b^\u0010I\u001a\u001f\u0010\\\u001a\u00020\u0002*\u00020\u000e2\u0006\u0010.\u001a\u00020\u0002H\u0087\nø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a\u001f\u0010\\\u001a\u00020\b*\u00020\u00112\u0006\u0010]\u001a\u00020\bH\u0087\nø\u0001\u0000¢\u0006\u0004\b^\u0010b\u001a\u001f\u0010\\\u001a\u00020\u0002*\u00020\u00112\u0006\u0010.\u001a\u00020\u0002H\u0087\nø\u0001\u0000¢\u0006\u0004\b`\u0010c\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0007\u001a\u00020\b*\u00020\t8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u001f\u0010\u0007\u001a\u00020\b*\u00020\u000e8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000f\u001a\u0004\b\f\u0010\u0010\"\u001f\u0010\u0007\u001a\u00020\b*\u00020\u00118Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0012\u001a\u0004\b\f\u0010\u0013\"\u001f\u0010\u0014\u001a\u00020\b*\u00020\u00158Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u001f\u0010\u001a\u001a\u00020\u001b*\u00020\b8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u001e\"\u001f\u0010\u001f\u001a\u00020\u001b*\u00020\b8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010\u000f\u001a\u0004\b!\u0010\u001e\"\u001f\u0010\u001f\u001a\u00020\u001b*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010$\"\u001f\u0010\u001f\u001a\u00020\u001b*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0004\u001a\u0004\b&\u0010$\"\u001f\u0010'\u001a\u00020\u001b*\u00020\b8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\u000f\u001a\u0004\b)\u0010\u001e\"\u001f\u0010'\u001a\u00020\u001b*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b*\u0010\u0004\u001a\u0004\b+\u0010$\"\u001f\u0010'\u001a\u00020\u001b*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b,\u0010\u0004\u001a\u0004\b-\u0010$\"\u001f\u0010.\u001a\u00020\u0002*\u00020\u00158Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b/\u0010\u0017\u001a\u0004\b0\u00101\"\u001f\u00102\u001a\u00020\b*\u00020\u00158Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b3\u0010\u0017\u001a\u0004\b4\u0010\u0019\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006d"}, d2 = {TtmlNode.CENTER, "Landroidx/compose/ui/unit/DpOffset;", "Landroidx/compose/ui/unit/DpSize;", "getCenter-EaSLcWc$annotations", "(J)V", "getCenter-EaSLcWc", "(J)J", "dp", "Landroidx/compose/ui/unit/Dp;", "", "getDp$annotations", "(D)V", "getDp", "(D)F", "", "(F)V", "(F)F", "", "(I)V", "(I)F", "height", "Landroidx/compose/ui/unit/DpRect;", "getHeight$annotations", "(Landroidx/compose/ui/unit/DpRect;)V", "getHeight", "(Landroidx/compose/ui/unit/DpRect;)F", "isFinite", "", "isFinite-0680j_4$annotations", "isFinite-0680j_4", "(F)Z", "isSpecified", "isSpecified-0680j_4$annotations", "isSpecified-0680j_4", "isSpecified-jo-Fl9I$annotations", "isSpecified-jo-Fl9I", "(J)Z", "isSpecified-EaSLcWc$annotations", "isSpecified-EaSLcWc", "isUnspecified", "isUnspecified-0680j_4$annotations", "isUnspecified-0680j_4", "isUnspecified-jo-Fl9I$annotations", "isUnspecified-jo-Fl9I", "isUnspecified-EaSLcWc$annotations", "isUnspecified-EaSLcWc", "size", "getSize$annotations", "getSize", "(Landroidx/compose/ui/unit/DpRect;)J", "width", "getWidth$annotations", "getWidth", "DpOffset", "x", "y", "DpOffset-YgX7TsA", "(FF)J", "DpSize", "DpSize-YgX7TsA", "lerp", TtmlNode.START, "stop", "fraction", "lerp-Md-fbLM", "(FFF)F", "lerp-xhh869w", "(JJF)J", "lerp-IDex15A", "max", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "max-YgX7TsA", "(FF)F", "min", "min-YgX7TsA", "coerceAtLeast", "minimumValue", "coerceAtLeast-YgX7TsA", "coerceAtMost", "maximumValue", "coerceAtMost-YgX7TsA", "coerceIn", "coerceIn-2z7ARbQ", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-D5KLDUw", "(FLkotlin/jvm/functions/Function0;)F", "takeOrElse-gVKV90s", "(JLkotlin/jvm/functions/Function0;)J", "takeOrElse-itqla9I", "times", "other", "times-3ABfNKs", "(DF)F", "times-6HolHcs", "(FJ)J", "(IF)F", "(IJ)J", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class DpKt {
    /* renamed from: getCenter-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m5763getCenterEaSLcWc$annotations(long j) {
    }

    public static /* synthetic */ void getDp$annotations(double d) {
    }

    public static /* synthetic */ void getDp$annotations(float f) {
    }

    public static /* synthetic */ void getDp$annotations(int i) {
    }

    public static /* synthetic */ void getHeight$annotations(DpRect dpRect) {
    }

    public static /* synthetic */ void getSize$annotations(DpRect dpRect) {
    }

    public static /* synthetic */ void getWidth$annotations(DpRect dpRect) {
    }

    /* renamed from: isFinite-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m5765isFinite0680j_4$annotations(float f) {
    }

    /* renamed from: isSpecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m5767isSpecified0680j_4$annotations(float f) {
    }

    /* renamed from: isSpecified-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m5769isSpecifiedEaSLcWc$annotations(long j) {
    }

    /* renamed from: isSpecified-jo-Fl9I$annotations, reason: not valid java name */
    public static /* synthetic */ void m5771isSpecifiedjoFl9I$annotations(long j) {
    }

    /* renamed from: isUnspecified-0680j_4$annotations, reason: not valid java name */
    public static /* synthetic */ void m5773isUnspecified0680j_4$annotations(float f) {
    }

    /* renamed from: isUnspecified-EaSLcWc$annotations, reason: not valid java name */
    public static /* synthetic */ void m5775isUnspecifiedEaSLcWc$annotations(long j) {
    }

    /* renamed from: isUnspecified-jo-Fl9I$annotations, reason: not valid java name */
    public static /* synthetic */ void m5777isUnspecifiedjoFl9I$annotations(long j) {
    }

    /* renamed from: isSpecified-0680j_4, reason: not valid java name */
    public static final boolean m5766isSpecified0680j_4(float $this$isSpecified) {
        return !Float.isNaN($this$isSpecified);
    }

    /* renamed from: isUnspecified-0680j_4, reason: not valid java name */
    public static final boolean m5772isUnspecified0680j_4(float $this$isUnspecified) {
        return Float.isNaN($this$isUnspecified);
    }

    /* renamed from: takeOrElse-D5KLDUw, reason: not valid java name */
    public static final float m5783takeOrElseD5KLDUw(float $this$takeOrElse_u2dD5KLDUw, Function0<Dp> function0) {
        return Float.isNaN($this$takeOrElse_u2dD5KLDUw) ^ true ? $this$takeOrElse_u2dD5KLDUw : function0.invoke().m5750unboximpl();
    }

    public static final float getDp(int $this$dp) {
        return Dp.m5736constructorimpl($this$dp);
    }

    public static final float getDp(double $this$dp) {
        return Dp.m5736constructorimpl((float) $this$dp);
    }

    public static final float getDp(float $this$dp) {
        return Dp.m5736constructorimpl($this$dp);
    }

    /* renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m5787times3ABfNKs(float $this$times_u2d3ABfNKs, float other) {
        return Dp.m5736constructorimpl($this$times_u2d3ABfNKs * other);
    }

    /* renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m5786times3ABfNKs(double $this$times_u2d3ABfNKs, float other) {
        return Dp.m5736constructorimpl(((float) $this$times_u2d3ABfNKs) * other);
    }

    /* renamed from: times-3ABfNKs, reason: not valid java name */
    public static final float m5788times3ABfNKs(int $this$times_u2d3ABfNKs, float other) {
        return Dp.m5736constructorimpl($this$times_u2d3ABfNKs * other);
    }

    /* renamed from: min-YgX7TsA, reason: not valid java name */
    public static final float m5782minYgX7TsA(float a, float b) {
        return Dp.m5736constructorimpl(Math.min(a, b));
    }

    /* renamed from: max-YgX7TsA, reason: not valid java name */
    public static final float m5781maxYgX7TsA(float a, float b) {
        return Dp.m5736constructorimpl(Math.max(a, b));
    }

    /* renamed from: coerceIn-2z7ARbQ, reason: not valid java name */
    public static final float m5761coerceIn2z7ARbQ(float $this$coerceIn_u2d2z7ARbQ, float minimumValue, float maximumValue) {
        return Dp.m5736constructorimpl(RangesKt.coerceIn($this$coerceIn_u2d2z7ARbQ, minimumValue, maximumValue));
    }

    /* renamed from: coerceAtLeast-YgX7TsA, reason: not valid java name */
    public static final float m5759coerceAtLeastYgX7TsA(float $this$coerceAtLeast_u2dYgX7TsA, float minimumValue) {
        return Dp.m5736constructorimpl(RangesKt.coerceAtLeast($this$coerceAtLeast_u2dYgX7TsA, minimumValue));
    }

    /* renamed from: coerceAtMost-YgX7TsA, reason: not valid java name */
    public static final float m5760coerceAtMostYgX7TsA(float $this$coerceAtMost_u2dYgX7TsA, float maximumValue) {
        return Dp.m5736constructorimpl(RangesKt.coerceAtMost($this$coerceAtMost_u2dYgX7TsA, maximumValue));
    }

    /* renamed from: isFinite-0680j_4, reason: not valid java name */
    public static final boolean m5764isFinite0680j_4(float $this$isFinite) {
        return !($this$isFinite == Float.POSITIVE_INFINITY);
    }

    /* renamed from: lerp-Md-fbLM, reason: not valid java name */
    public static final float m5779lerpMdfbLM(float start, float stop, float fraction) {
        return Dp.m5736constructorimpl(MathHelpersKt.lerp(start, stop, fraction));
    }

    /* renamed from: DpOffset-YgX7TsA, reason: not valid java name */
    public static final long m5757DpOffsetYgX7TsA(float x, float y) {
        long v1$iv = Float.floatToRawIntBits(x);
        long v2$iv = Float.floatToRawIntBits(y);
        return DpOffset.m5792constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: isSpecified-jo-Fl9I, reason: not valid java name */
    public static final boolean m5770isSpecifiedjoFl9I(long $this$isSpecified) {
        return $this$isSpecified != DpOffset.INSTANCE.m5806getUnspecifiedRKDOV3M();
    }

    /* renamed from: isUnspecified-jo-Fl9I, reason: not valid java name */
    public static final boolean m5776isUnspecifiedjoFl9I(long $this$isUnspecified) {
        return $this$isUnspecified == DpOffset.INSTANCE.m5806getUnspecifiedRKDOV3M();
    }

    /* renamed from: takeOrElse-gVKV90s, reason: not valid java name */
    public static final long m5784takeOrElsegVKV90s(long $this$takeOrElse_u2dgVKV90s, Function0<DpOffset> function0) {
        return ($this$takeOrElse_u2dgVKV90s > DpOffset.INSTANCE.m5806getUnspecifiedRKDOV3M() ? 1 : ($this$takeOrElse_u2dgVKV90s == DpOffset.INSTANCE.m5806getUnspecifiedRKDOV3M() ? 0 : -1)) != 0 ? $this$takeOrElse_u2dgVKV90s : function0.invoke().getPackedValue();
    }

    /* renamed from: lerp-xhh869w, reason: not valid java name */
    public static final long m5780lerpxhh869w(long start, long stop, float fraction) {
        return m5757DpOffsetYgX7TsA(m5779lerpMdfbLM(DpOffset.m5797getXD9Ej5fM(start), DpOffset.m5797getXD9Ej5fM(stop), fraction), m5779lerpMdfbLM(DpOffset.m5799getYD9Ej5fM(start), DpOffset.m5799getYD9Ej5fM(stop), fraction));
    }

    /* renamed from: DpSize-YgX7TsA, reason: not valid java name */
    public static final long m5758DpSizeYgX7TsA(float width, float height) {
        long v1$iv = Float.floatToRawIntBits(width);
        long v2$iv = Float.floatToRawIntBits(height);
        return DpSize.m5825constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* renamed from: isSpecified-EaSLcWc, reason: not valid java name */
    public static final boolean m5768isSpecifiedEaSLcWc(long $this$isSpecified) {
        return $this$isSpecified != DpSize.INSTANCE.m5843getUnspecifiedMYxV2XQ();
    }

    /* renamed from: isUnspecified-EaSLcWc, reason: not valid java name */
    public static final boolean m5774isUnspecifiedEaSLcWc(long $this$isUnspecified) {
        return $this$isUnspecified == DpSize.INSTANCE.m5843getUnspecifiedMYxV2XQ();
    }

    /* renamed from: takeOrElse-itqla9I, reason: not valid java name */
    public static final long m5785takeOrElseitqla9I(long $this$takeOrElse_u2ditqla9I, Function0<DpSize> function0) {
        return ($this$takeOrElse_u2ditqla9I > DpSize.INSTANCE.m5843getUnspecifiedMYxV2XQ() ? 1 : ($this$takeOrElse_u2ditqla9I == DpSize.INSTANCE.m5843getUnspecifiedMYxV2XQ() ? 0 : -1)) != 0 ? $this$takeOrElse_u2ditqla9I : function0.invoke().getPackedValue();
    }

    /* renamed from: getCenter-EaSLcWc, reason: not valid java name */
    public static final long m5762getCenterEaSLcWc(long $this$center) {
        float arg0$iv = DpSize.m5834getWidthD9Ej5fM($this$center);
        float arg0$iv2 = Dp.m5736constructorimpl(arg0$iv / 2.0f);
        float arg0$iv3 = DpSize.m5832getHeightD9Ej5fM($this$center);
        return m5757DpOffsetYgX7TsA(arg0$iv2, Dp.m5736constructorimpl(arg0$iv3 / 2.0f));
    }

    /* renamed from: times-6HolHcs, reason: not valid java name */
    public static final long m5790times6HolHcs(int $this$times_u2d6HolHcs, long size) {
        return DpSize.m5840timesGh9hcWk(size, $this$times_u2d6HolHcs);
    }

    /* renamed from: times-6HolHcs, reason: not valid java name */
    public static final long m5789times6HolHcs(float $this$times_u2d6HolHcs, long size) {
        return DpSize.m5839timesGh9hcWk(size, $this$times_u2d6HolHcs);
    }

    /* renamed from: lerp-IDex15A, reason: not valid java name */
    public static final long m5778lerpIDex15A(long start, long stop, float fraction) {
        return m5758DpSizeYgX7TsA(m5779lerpMdfbLM(DpSize.m5834getWidthD9Ej5fM(start), DpSize.m5834getWidthD9Ej5fM(stop), fraction), m5779lerpMdfbLM(DpSize.m5832getHeightD9Ej5fM(start), DpSize.m5832getHeightD9Ej5fM(stop), fraction));
    }

    public static final float getWidth(DpRect $this$width) {
        float arg0$iv = $this$width.m5820getRightD9Ej5fM();
        float other$iv = $this$width.m5819getLeftD9Ej5fM();
        return Dp.m5736constructorimpl(arg0$iv - other$iv);
    }

    public static final float getHeight(DpRect $this$height) {
        float arg0$iv = $this$height.m5818getBottomD9Ej5fM();
        float other$iv = $this$height.m5821getTopD9Ej5fM();
        return Dp.m5736constructorimpl(arg0$iv - other$iv);
    }

    public static final long getSize(DpRect $this$size) {
        float arg0$iv$iv = $this$size.m5820getRightD9Ej5fM();
        float other$iv$iv = $this$size.m5819getLeftD9Ej5fM();
        float arg0$iv$iv2 = Dp.m5736constructorimpl(arg0$iv$iv - other$iv$iv);
        float arg0$iv$iv3 = $this$size.m5818getBottomD9Ej5fM();
        float other$iv$iv2 = $this$size.m5821getTopD9Ej5fM();
        return m5758DpSizeYgX7TsA(arg0$iv$iv2, Dp.m5736constructorimpl(arg0$iv$iv3 - other$iv$iv2));
    }
}
