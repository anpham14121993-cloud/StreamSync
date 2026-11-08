package androidx.media3.common.text;

import android.text.Spannable;

/* loaded from: classes24.dex */
public final class SpanUtil {
    public static void addOrReplaceSpan(Spannable spannable, Object span, int start, int end, int spanFlags) {
        Object[] existingSpans = spannable.getSpans(start, end, span.getClass());
        for (Object existingSpan : existingSpans) {
            if (spannable.getSpanStart(existingSpan) == start && spannable.getSpanEnd(existingSpan) == end && spannable.getSpanFlags(existingSpan) == spanFlags) {
                spannable.removeSpan(existingSpan);
            }
        }
        spannable.setSpan(span, start, end, spanFlags);
    }

    private SpanUtil() {
    }
}
