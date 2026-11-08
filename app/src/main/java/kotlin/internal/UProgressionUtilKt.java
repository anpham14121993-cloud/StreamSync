package kotlin.internal;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport1;
import kotlin.UByte$$ExternalSyntheticBackport2;
import kotlin.UInt;
import kotlin.ULong;

/* compiled from: UProgressionUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", TtmlNode.START, TtmlNode.END, "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes25.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m7923differenceModuloWZ9TVnA(int a, int b, int c) {
        int compare;
        int ac = UByte$$ExternalSyntheticBackport1.m(a, c);
        int bc = UByte$$ExternalSyntheticBackport1.m(b, c);
        compare = Integer.compare(ac ^ Integer.MIN_VALUE, bc ^ Integer.MIN_VALUE);
        return UInt.m6812constructorimpl(compare >= 0 ? ac - bc : UInt.m6812constructorimpl(ac - bc) + c);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m7924differenceModulosambcqE(long a, long b, long c) {
        int compare;
        long ac = UByte$$ExternalSyntheticBackport2.m(a, c);
        long bc = UByte$$ExternalSyntheticBackport2.m(b, c);
        compare = Long.compare(ac ^ Long.MIN_VALUE, bc ^ Long.MIN_VALUE);
        return ULong.m6891constructorimpl(compare >= 0 ? ac - bc : ULong.m6891constructorimpl(ac - bc) + c);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m7926getProgressionLastElementNkh28Cs(int start, int end, int step) {
        int compare;
        int compare2;
        if (step > 0) {
            compare2 = Integer.compare(start ^ Integer.MIN_VALUE, end ^ Integer.MIN_VALUE);
            if (compare2 < 0) {
                return UInt.m6812constructorimpl(end - m7923differenceModuloWZ9TVnA(end, start, UInt.m6812constructorimpl(step)));
            }
        } else {
            if (step >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            compare = Integer.compare(start ^ Integer.MIN_VALUE, end ^ Integer.MIN_VALUE);
            if (compare > 0) {
                return UInt.m6812constructorimpl(m7923differenceModuloWZ9TVnA(start, end, UInt.m6812constructorimpl(-step)) + end);
            }
        }
        return end;
    }

    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m7925getProgressionLastElement7ftBX0g(long start, long end, long step) {
        int compare;
        int compare2;
        if (step > 0) {
            compare2 = Long.compare(start ^ Long.MIN_VALUE, end ^ Long.MIN_VALUE);
            if (compare2 < 0) {
                return ULong.m6891constructorimpl(end - m7924differenceModulosambcqE(end, start, ULong.m6891constructorimpl(step)));
            }
        } else {
            if (step >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            compare = Long.compare(start ^ Long.MIN_VALUE, end ^ Long.MIN_VALUE);
            if (compare > 0) {
                return ULong.m6891constructorimpl(m7924differenceModulosambcqE(start, end, ULong.m6891constructorimpl(-step)) + end);
            }
        }
        return end;
    }
}
