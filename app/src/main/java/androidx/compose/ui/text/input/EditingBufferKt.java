package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: EditingBuffer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", "target", "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m5395updateRangeAfterDeletepWDy79M(long target, long deleted) {
        int targetMin = TextRange.m5233getMinimpl(target);
        int targetMax = TextRange.m5232getMaximpl(target);
        if (TextRange.m5237intersects5zctL8(deleted, target)) {
            if (TextRange.m5225contains5zctL8(deleted, target)) {
                targetMin = TextRange.m5233getMinimpl(deleted);
                targetMax = targetMin;
            } else if (TextRange.m5225contains5zctL8(target, deleted)) {
                targetMax -= TextRange.m5231getLengthimpl(deleted);
            } else if (TextRange.m5226containsimpl(deleted, targetMin)) {
                targetMin = TextRange.m5233getMinimpl(deleted);
                targetMax -= TextRange.m5231getLengthimpl(deleted);
            } else {
                targetMax = TextRange.m5233getMinimpl(deleted);
            }
        } else if (targetMax > TextRange.m5233getMinimpl(deleted)) {
            targetMin -= TextRange.m5231getLengthimpl(deleted);
            targetMax -= TextRange.m5231getLengthimpl(deleted);
        }
        return TextRangeKt.TextRange(targetMin, targetMax);
    }
}
