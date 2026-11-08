package androidx.compose.ui.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.style.IndentationFixSpan;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: AndroidParagraph.android.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001a\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\n\u001a\u001a\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a\u001a\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\n\u001a\u001a\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\n\u001a\f\u0010\u001b\u001a\u00020\u001c*\u00020\u001cH\u0002\u001a\u0014\u0010\u001d\u001a\u00020\u0006*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"shouldAttachIndentationFixSpan", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "ellipsis", "toLayoutAlign", "", "align", "Landroidx/compose/ui/text/style/TextAlign;", "toLayoutAlign-aXe7zB0", "(I)I", "toLayoutBreakStrategy", "breakStrategy", "Landroidx/compose/ui/text/style/LineBreak$Strategy;", "toLayoutBreakStrategy-xImikfE", "toLayoutHyphenationFrequency", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "toLayoutHyphenationFrequency--3fSNIE", "toLayoutLineBreakStyle", "lineBreakStrictness", "Landroidx/compose/ui/text/style/LineBreak$Strictness;", "toLayoutLineBreakStyle-hpcqdu8", "toLayoutLineBreakWordStyle", "lineBreakWordStyle", "Landroidx/compose/ui/text/style/LineBreak$WordBreak;", "toLayoutLineBreakWordStyle-wPN0Rpw", "attachIndentationFixSpan", "", "numberOfLinesThatFitMaxHeight", "Landroidx/compose/ui/text/android/TextLayout;", "maxHeight", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class AndroidParagraph_androidKt {
    public static final /* synthetic */ CharSequence access$attachIndentationFixSpan(CharSequence $receiver) {
        return attachIndentationFixSpan($receiver);
    }

    public static final /* synthetic */ int access$numberOfLinesThatFitMaxHeight(TextLayout $receiver, int maxHeight) {
        return numberOfLinesThatFitMaxHeight($receiver, maxHeight);
    }

    public static final /* synthetic */ boolean access$shouldAttachIndentationFixSpan(TextStyle textStyle, boolean ellipsis) {
        return shouldAttachIndentationFixSpan(textStyle, ellipsis);
    }

    /* renamed from: access$toLayoutAlign-aXe7zB0, reason: not valid java name */
    public static final /* synthetic */ int m5088access$toLayoutAlignaXe7zB0(int align) {
        return m5093toLayoutAlignaXe7zB0(align);
    }

    /* renamed from: access$toLayoutBreakStrategy-xImikfE, reason: not valid java name */
    public static final /* synthetic */ int m5089access$toLayoutBreakStrategyxImikfE(int breakStrategy) {
        return m5094toLayoutBreakStrategyxImikfE(breakStrategy);
    }

    /* renamed from: access$toLayoutHyphenationFrequency--3fSNIE, reason: not valid java name */
    public static final /* synthetic */ int m5090access$toLayoutHyphenationFrequency3fSNIE(int hyphens) {
        return m5095toLayoutHyphenationFrequency3fSNIE(hyphens);
    }

    /* renamed from: access$toLayoutLineBreakStyle-hpcqdu8, reason: not valid java name */
    public static final /* synthetic */ int m5091access$toLayoutLineBreakStylehpcqdu8(int lineBreakStrictness) {
        return m5096toLayoutLineBreakStylehpcqdu8(lineBreakStrictness);
    }

    /* renamed from: access$toLayoutLineBreakWordStyle-wPN0Rpw, reason: not valid java name */
    public static final /* synthetic */ int m5092access$toLayoutLineBreakWordStylewPN0Rpw(int lineBreakWordStyle) {
        return m5097toLayoutLineBreakWordStylewPN0Rpw(lineBreakWordStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutAlign-aXe7zB0, reason: not valid java name */
    public static final int m5093toLayoutAlignaXe7zB0(int align) {
        if (TextAlign.m5624equalsimpl0(align, TextAlign.INSTANCE.m5631getLefte0LSkKk())) {
            return 3;
        }
        if (TextAlign.m5624equalsimpl0(align, TextAlign.INSTANCE.m5632getRighte0LSkKk())) {
            return 4;
        }
        if (TextAlign.m5624equalsimpl0(align, TextAlign.INSTANCE.m5628getCentere0LSkKk())) {
            return 2;
        }
        return (!TextAlign.m5624equalsimpl0(align, TextAlign.INSTANCE.m5633getStarte0LSkKk()) && TextAlign.m5624equalsimpl0(align, TextAlign.INSTANCE.m5629getEnde0LSkKk())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutHyphenationFrequency--3fSNIE, reason: not valid java name */
    public static final int m5095toLayoutHyphenationFrequency3fSNIE(int hyphens) {
        if (!Hyphens.m5534equalsimpl0(hyphens, Hyphens.INSTANCE.m5538getAutovmbZdU8())) {
            Hyphens.m5534equalsimpl0(hyphens, Hyphens.INSTANCE.m5539getNonevmbZdU8());
            return 0;
        }
        if (Build.VERSION.SDK_INT <= 32) {
            return 2;
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutBreakStrategy-xImikfE, reason: not valid java name */
    public static final int m5094toLayoutBreakStrategyxImikfE(int breakStrategy) {
        if (LineBreak.Strategy.m5565equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m5571getSimplefcGXIks())) {
            return 0;
        }
        if (LineBreak.Strategy.m5565equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m5570getHighQualityfcGXIks())) {
            return 1;
        }
        return LineBreak.Strategy.m5565equalsimpl0(breakStrategy, LineBreak.Strategy.INSTANCE.m5569getBalancedfcGXIks()) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutLineBreakStyle-hpcqdu8, reason: not valid java name */
    public static final int m5096toLayoutLineBreakStylehpcqdu8(int lineBreakStrictness) {
        if (LineBreak.Strictness.m5576equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m5580getDefaultusljTpc())) {
            return 0;
        }
        if (LineBreak.Strictness.m5576equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m5581getLooseusljTpc())) {
            return 1;
        }
        if (LineBreak.Strictness.m5576equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m5582getNormalusljTpc())) {
            return 2;
        }
        return LineBreak.Strictness.m5576equalsimpl0(lineBreakStrictness, LineBreak.Strictness.INSTANCE.m5583getStrictusljTpc()) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: toLayoutLineBreakWordStyle-wPN0Rpw, reason: not valid java name */
    public static final int m5097toLayoutLineBreakWordStylewPN0Rpw(int lineBreakWordStyle) {
        return (!LineBreak.WordBreak.m5588equalsimpl0(lineBreakWordStyle, LineBreak.WordBreak.INSTANCE.m5592getDefaultjp8hJ3c()) && LineBreak.WordBreak.m5588equalsimpl0(lineBreakWordStyle, LineBreak.WordBreak.INSTANCE.m5593getPhrasejp8hJ3c())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int numberOfLinesThatFitMaxHeight(TextLayout $this$numberOfLinesThatFitMaxHeight, int maxHeight) {
        int lineCount = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
            if ($this$numberOfLinesThatFitMaxHeight.getLineBottom(lineIndex) > maxHeight) {
                return lineIndex;
            }
        }
        int lineIndex2 = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        return lineIndex2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldAttachIndentationFixSpan(TextStyle textStyle, boolean ellipsis) {
        return (!ellipsis || TextUnit.m5924equalsimpl0(textStyle.m5271getLetterSpacingXSAIIZE(), TextUnitKt.getSp(0)) || TextUnit.m5924equalsimpl0(textStyle.m5271getLetterSpacingXSAIIZE(), TextUnit.INSTANCE.m5938getUnspecifiedXSAIIZE()) || TextAlign.m5624equalsimpl0(textStyle.m5276getTextAligne0LSkKk(), TextAlign.INSTANCE.m5634getUnspecifiede0LSkKk()) || TextAlign.m5624equalsimpl0(textStyle.m5276getTextAligne0LSkKk(), TextAlign.INSTANCE.m5633getStarte0LSkKk()) || TextAlign.m5624equalsimpl0(textStyle.m5276getTextAligne0LSkKk(), TextAlign.INSTANCE.m5630getJustifye0LSkKk())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence attachIndentationFixSpan(CharSequence $this$attachIndentationFixSpan) {
        if ($this$attachIndentationFixSpan.length() == 0) {
            return $this$attachIndentationFixSpan;
        }
        SpannableString spannable = $this$attachIndentationFixSpan instanceof Spannable ? (Spannable) $this$attachIndentationFixSpan : new SpannableString($this$attachIndentationFixSpan);
        SpannableExtensions_androidKt.setSpan(spannable, new IndentationFixSpan(), spannable.length() - 1, spannable.length() - 1);
        return spannable;
    }
}
