package kotlin.math;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.comparisons.UComparisonsKt;

/* compiled from: UMath.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\n\u0010\u0005\u001a \u0010\t\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"max", "Lkotlin/UInt;", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "max-J1ME1BU", "(II)I", "Lkotlin/ULong;", "max-eb3DHEI", "(JJ)J", "min", "min-J1ME1BU", "min-eb3DHEI", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes25.dex */
public final class UMathKt {
    /* renamed from: min-J1ME1BU, reason: not valid java name */
    private static final int m7929minJ1ME1BU(int a, int b) {
        return UComparisonsKt.m7911minOfJ1ME1BU(a, b);
    }

    /* renamed from: min-eb3DHEI, reason: not valid java name */
    private static final long m7930mineb3DHEI(long a, long b) {
        return UComparisonsKt.m7919minOfeb3DHEI(a, b);
    }

    /* renamed from: max-J1ME1BU, reason: not valid java name */
    private static final int m7927maxJ1ME1BU(int a, int b) {
        return UComparisonsKt.m7899maxOfJ1ME1BU(a, b);
    }

    /* renamed from: max-eb3DHEI, reason: not valid java name */
    private static final long m7928maxeb3DHEI(long a, long b) {
        return UComparisonsKt.m7907maxOfeb3DHEI(a, b);
    }
}
