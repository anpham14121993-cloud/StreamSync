package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextIndentKt;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ParagraphStyle.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a&\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001af\u0010\u000f\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u0018\u0010\"\u001a\u0004\u0018\u00010\n*\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"DefaultLineHeight", "Landroidx/compose/ui/unit/TextUnit;", "J", "lerp", "Landroidx/compose/ui/text/ParagraphStyle;", TtmlNode.START, "stop", "fraction", "", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "resolveParagraphStyleDefaults", TtmlNode.TAG_STYLE, "direction", "Landroidx/compose/ui/unit/LayoutDirection;", "fastMerge", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextAlign;", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "lineHeight", "textIndent", "Landroidx/compose/ui/text/style/TextIndent;", "platformStyle", "lineHeightStyle", "Landroidx/compose/ui/text/style/LineHeightStyle;", "lineBreak", "Landroidx/compose/ui/text/style/LineBreak;", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "textMotion", "Landroidx/compose/ui/text/style/TextMotion;", "fastMerge-j5T8yCg", "(Landroidx/compose/ui/text/ParagraphStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/ParagraphStyle;", "mergePlatformStyle", "other", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ParagraphStyleKt {
    private static final long DefaultLineHeight = TextUnit.INSTANCE.m5938getUnspecifiedXSAIIZE();

    public static final ParagraphStyle lerp(ParagraphStyle start, ParagraphStyle stop, float fraction) {
        int value = ((TextAlign) SpanStyleKt.lerpDiscrete(TextAlign.m5621boximpl(start.getTextAlign()), TextAlign.m5621boximpl(stop.getTextAlign()), fraction)).getValue();
        int value2 = ((TextDirection) SpanStyleKt.lerpDiscrete(TextDirection.m5635boximpl(start.getTextDirection()), TextDirection.m5635boximpl(stop.getTextDirection()), fraction)).getValue();
        long m5200lerpTextUnitInheritableC3pnCVY = SpanStyleKt.m5200lerpTextUnitInheritableC3pnCVY(start.getLineHeight(), stop.getLineHeight(), fraction);
        TextIndent textIndent = start.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = stop.getTextIndent();
        if (textIndent2 == null) {
            textIndent2 = TextIndent.INSTANCE.getNone();
        }
        return new ParagraphStyle(value, value2, m5200lerpTextUnitInheritableC3pnCVY, TextIndentKt.lerp(textIndent, textIndent2, fraction), lerpPlatformStyle(start.getPlatformStyle(), stop.getPlatformStyle(), fraction), (LineHeightStyle) SpanStyleKt.lerpDiscrete(start.getLineHeightStyle(), stop.getLineHeightStyle(), fraction), ((LineBreak) SpanStyleKt.lerpDiscrete(LineBreak.m5541boximpl(start.getLineBreak()), LineBreak.m5541boximpl(stop.getLineBreak()), fraction)).getMask(), ((Hyphens) SpanStyleKt.lerpDiscrete(Hyphens.m5531boximpl(start.getHyphens()), Hyphens.m5531boximpl(stop.getHyphens()), fraction)).getValue(), (TextMotion) SpanStyleKt.lerpDiscrete(start.getTextMotion(), stop.getTextMotion(), fraction), (DefaultConstructorMarker) null);
    }

    private static final PlatformParagraphStyle lerpPlatformStyle(PlatformParagraphStyle start, PlatformParagraphStyle stop, float fraction) {
        if (start == null && stop == null) {
            return null;
        }
        PlatformParagraphStyle startNonNull = start == null ? PlatformParagraphStyle.INSTANCE.getDefault() : start;
        PlatformParagraphStyle stopNonNull = stop == null ? PlatformParagraphStyle.INSTANCE.getDefault() : stop;
        return AndroidTextStyle_androidKt.lerp(startNonNull, stopNonNull, fraction);
    }

    public static final ParagraphStyle resolveParagraphStyleDefaults(ParagraphStyle style, LayoutDirection direction) {
        int m5633getStarte0LSkKk = TextAlign.m5624equalsimpl0(style.getTextAlign(), TextAlign.INSTANCE.m5634getUnspecifiede0LSkKk()) ? TextAlign.INSTANCE.m5633getStarte0LSkKk() : style.getTextAlign();
        int m5281resolveTextDirectionIhaHGbI = TextStyleKt.m5281resolveTextDirectionIhaHGbI(direction, style.getTextDirection());
        long lineHeight = TextUnitKt.m5945isUnspecifiedR2X_6o(style.getLineHeight()) ? DefaultLineHeight : style.getLineHeight();
        TextIndent textIndent = style.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = textIndent;
        PlatformParagraphStyle platformStyle = style.getPlatformStyle();
        LineHeightStyle lineHeightStyle = style.getLineHeightStyle();
        int m5560getSimplerAG3T2k = LineBreak.m5547equalsimpl0(style.getLineBreak(), LineBreak.INSTANCE.m5561getUnspecifiedrAG3T2k()) ? LineBreak.INSTANCE.m5560getSimplerAG3T2k() : style.getLineBreak();
        int m5539getNonevmbZdU8 = Hyphens.m5534equalsimpl0(style.getHyphens(), Hyphens.INSTANCE.m5540getUnspecifiedvmbZdU8()) ? Hyphens.INSTANCE.m5539getNonevmbZdU8() : style.getHyphens();
        TextMotion textMotion = style.getTextMotion();
        if (textMotion == null) {
            textMotion = TextMotion.INSTANCE.getStatic();
        }
        return new ParagraphStyle(m5633getStarte0LSkKk, m5281resolveTextDirectionIhaHGbI, lineHeight, textIndent2, platformStyle, lineHeightStyle, m5560getSimplerAG3T2k, m5539getNonevmbZdU8, textMotion, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0043, code lost:
    
        if (androidx.compose.ui.unit.TextUnit.m5924equalsimpl0(r13, r27.getLineHeight()) != false) goto L14;
     */
    /* renamed from: fastMerge-j5T8yCg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.text.ParagraphStyle m5153fastMergej5T8yCg(androidx.compose.ui.text.ParagraphStyle r27, int r28, int r29, long r30, androidx.compose.ui.text.style.TextIndent r32, androidx.compose.ui.text.PlatformParagraphStyle r33, androidx.compose.ui.text.style.LineHeightStyle r34, int r35, int r36, androidx.compose.ui.text.style.TextMotion r37) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.ParagraphStyleKt.m5153fastMergej5T8yCg(androidx.compose.ui.text.ParagraphStyle, int, int, long, androidx.compose.ui.text.style.TextIndent, androidx.compose.ui.text.PlatformParagraphStyle, androidx.compose.ui.text.style.LineHeightStyle, int, int, androidx.compose.ui.text.style.TextMotion):androidx.compose.ui.text.ParagraphStyle");
    }

    private static final PlatformParagraphStyle mergePlatformStyle(ParagraphStyle $this$mergePlatformStyle, PlatformParagraphStyle other) {
        return $this$mergePlatformStyle.getPlatformStyle() == null ? other : other == null ? $this$mergePlatformStyle.getPlatformStyle() : $this$mergePlatformStyle.getPlatformStyle().merge(other);
    }
}
