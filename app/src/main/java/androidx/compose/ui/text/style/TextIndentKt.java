package androidx.compose.ui.text.style;

import androidx.compose.ui.text.SpanStyleKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: TextIndent.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"lerp", "Landroidx/compose/ui/text/style/TextIndent;", TtmlNode.START, "stop", "fraction", "", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class TextIndentKt {
    public static final TextIndent lerp(TextIndent start, TextIndent stop, float fraction) {
        return new TextIndent(SpanStyleKt.m5200lerpTextUnitInheritableC3pnCVY(start.getFirstLine(), stop.getFirstLine(), fraction), SpanStyleKt.m5200lerpTextUnitInheritableC3pnCVY(start.getRestLine(), stop.getRestLine(), fraction), null);
    }
}
