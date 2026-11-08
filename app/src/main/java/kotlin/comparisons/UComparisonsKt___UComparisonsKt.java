package kotlin.comparisons;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _UComparisons.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0001H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a(\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a#\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\n\u0010\t\u001a\u00020\u0012\"\u00020\rH\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001f\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a(\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0019\u001a#\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u001a\"\u00020\u0015H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a(\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u001dH\u0087\b¢\u0006\u0004\b \u0010!\u001a#\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\n\u0010\t\u001a\u00020\"\"\u00020\u001dH\u0007¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b&\u0010\u0005\u001a(\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\b¢\u0006\u0004\b'\u0010\b\u001a#\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0001H\u0007¢\u0006\u0004\b(\u0010\f\u001a\u001f\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0007¢\u0006\u0004\b)\u0010\u000f\u001a(\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b¢\u0006\u0004\b*\u0010\u0011\u001a#\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\n\u0010\t\u001a\u00020\u0012\"\u00020\rH\u0007¢\u0006\u0004\b+\u0010\u0014\u001a\u001f\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0007¢\u0006\u0004\b,\u0010\u0017\u001a(\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b-\u0010\u0019\u001a#\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u001a\"\u00020\u0015H\u0007¢\u0006\u0004\b.\u0010\u001c\u001a\u001f\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001dH\u0007¢\u0006\u0004\b/\u0010\u001f\u001a(\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u001dH\u0087\b¢\u0006\u0004\b0\u0010!\u001a#\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\n\u0010\t\u001a\u00020\"\"\u00020\u001dH\u0007¢\u0006\u0004\b1\u0010$¨\u00062"}, d2 = {"maxOf", "Lkotlin/UByte;", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "maxOf-Kr8caGY", "(BB)B", "c", "maxOf-b33U2AM", "(BBB)B", "other", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UInt;", "maxOf-J1ME1BU", "(II)I", "maxOf-WZ9TVnA", "(III)I", "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "maxOf-sambcqE", "(JJJ)J", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "maxOf-VKSA0NQ", "(SSS)S", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-Kr8caGY", "minOf-b33U2AM", "minOf-Wr6uiD8", "minOf-J1ME1BU", "minOf-WZ9TVnA", "minOf-Md2H83M", "minOf-eb3DHEI", "minOf-sambcqE", "minOf-R03FKyM", "minOf-5PvTz6A", "minOf-VKSA0NQ", "minOf-t1qELG4", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/comparisons/UComparisonsKt")
/* loaded from: classes25.dex */
public class UComparisonsKt___UComparisonsKt {
    /* renamed from: maxOf-J1ME1BU, reason: not valid java name */
    public static final int m7899maxOfJ1ME1BU(int a, int b) {
        int compare;
        compare = Integer.compare(a ^ Integer.MIN_VALUE, b ^ Integer.MIN_VALUE);
        return compare >= 0 ? a : b;
    }

    /* renamed from: maxOf-eb3DHEI, reason: not valid java name */
    public static final long m7907maxOfeb3DHEI(long a, long b) {
        int compare;
        compare = Long.compare(a ^ Long.MIN_VALUE, b ^ Long.MIN_VALUE);
        return compare >= 0 ? a : b;
    }

    /* renamed from: maxOf-Kr8caGY, reason: not valid java name */
    public static final byte m7900maxOfKr8caGY(byte a, byte b) {
        return Intrinsics.compare(a & 255, b & 255) >= 0 ? a : b;
    }

    /* renamed from: maxOf-5PvTz6A, reason: not valid java name */
    public static final short m7898maxOf5PvTz6A(short a, short b) {
        return Intrinsics.compare(a & UShort.MAX_VALUE, 65535 & b) >= 0 ? a : b;
    }

    /* renamed from: maxOf-WZ9TVnA, reason: not valid java name */
    private static final int m7904maxOfWZ9TVnA(int a, int b, int c) {
        return UComparisonsKt.m7899maxOfJ1ME1BU(a, UComparisonsKt.m7899maxOfJ1ME1BU(b, c));
    }

    /* renamed from: maxOf-sambcqE, reason: not valid java name */
    private static final long m7908maxOfsambcqE(long a, long b, long c) {
        return UComparisonsKt.m7907maxOfeb3DHEI(a, UComparisonsKt.m7907maxOfeb3DHEI(b, c));
    }

    /* renamed from: maxOf-b33U2AM, reason: not valid java name */
    private static final byte m7906maxOfb33U2AM(byte a, byte b, byte c) {
        return UComparisonsKt.m7900maxOfKr8caGY(a, UComparisonsKt.m7900maxOfKr8caGY(b, c));
    }

    /* renamed from: maxOf-VKSA0NQ, reason: not valid java name */
    private static final short m7903maxOfVKSA0NQ(short a, short b, short c) {
        return UComparisonsKt.m7898maxOf5PvTz6A(a, UComparisonsKt.m7898maxOf5PvTz6A(b, c));
    }

    /* renamed from: maxOf-Md2H83M, reason: not valid java name */
    public static final int m7901maxOfMd2H83M(int a, int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int max = a;
        int m6873getSizeimpl = UIntArray.m6873getSizeimpl(other);
        for (int i = 0; i < m6873getSizeimpl; i++) {
            int e = UIntArray.m6872getpVg5ArA(other, i);
            max = UComparisonsKt.m7899maxOfJ1ME1BU(max, e);
        }
        return max;
    }

    /* renamed from: maxOf-R03FKyM, reason: not valid java name */
    public static final long m7902maxOfR03FKyM(long a, long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        long max = a;
        int m6952getSizeimpl = ULongArray.m6952getSizeimpl(other);
        for (int i = 0; i < m6952getSizeimpl; i++) {
            long e = ULongArray.m6951getsVKNKU(other, i);
            max = UComparisonsKt.m7907maxOfeb3DHEI(max, e);
        }
        return max;
    }

    /* renamed from: maxOf-Wr6uiD8, reason: not valid java name */
    public static final byte m7905maxOfWr6uiD8(byte a, byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        byte max = a;
        int m6794getSizeimpl = UByteArray.m6794getSizeimpl(other);
        for (int i = 0; i < m6794getSizeimpl; i++) {
            byte e = UByteArray.m6793getw2LRezQ(other, i);
            max = UComparisonsKt.m7900maxOfKr8caGY(max, e);
        }
        return max;
    }

    /* renamed from: maxOf-t1qELG4, reason: not valid java name */
    public static final short m7909maxOft1qELG4(short a, short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        short max = a;
        int m7057getSizeimpl = UShortArray.m7057getSizeimpl(other);
        for (int i = 0; i < m7057getSizeimpl; i++) {
            short e = UShortArray.m7056getMh2AYeg(other, i);
            max = UComparisonsKt.m7898maxOf5PvTz6A(max, e);
        }
        return max;
    }

    /* renamed from: minOf-J1ME1BU, reason: not valid java name */
    public static final int m7911minOfJ1ME1BU(int a, int b) {
        int compare;
        compare = Integer.compare(a ^ Integer.MIN_VALUE, b ^ Integer.MIN_VALUE);
        return compare <= 0 ? a : b;
    }

    /* renamed from: minOf-eb3DHEI, reason: not valid java name */
    public static final long m7919minOfeb3DHEI(long a, long b) {
        int compare;
        compare = Long.compare(a ^ Long.MIN_VALUE, b ^ Long.MIN_VALUE);
        return compare <= 0 ? a : b;
    }

    /* renamed from: minOf-Kr8caGY, reason: not valid java name */
    public static final byte m7912minOfKr8caGY(byte a, byte b) {
        return Intrinsics.compare(a & 255, b & 255) <= 0 ? a : b;
    }

    /* renamed from: minOf-5PvTz6A, reason: not valid java name */
    public static final short m7910minOf5PvTz6A(short a, short b) {
        return Intrinsics.compare(a & UShort.MAX_VALUE, 65535 & b) <= 0 ? a : b;
    }

    /* renamed from: minOf-WZ9TVnA, reason: not valid java name */
    private static final int m7916minOfWZ9TVnA(int a, int b, int c) {
        return UComparisonsKt.m7911minOfJ1ME1BU(a, UComparisonsKt.m7911minOfJ1ME1BU(b, c));
    }

    /* renamed from: minOf-sambcqE, reason: not valid java name */
    private static final long m7920minOfsambcqE(long a, long b, long c) {
        return UComparisonsKt.m7919minOfeb3DHEI(a, UComparisonsKt.m7919minOfeb3DHEI(b, c));
    }

    /* renamed from: minOf-b33U2AM, reason: not valid java name */
    private static final byte m7918minOfb33U2AM(byte a, byte b, byte c) {
        return UComparisonsKt.m7912minOfKr8caGY(a, UComparisonsKt.m7912minOfKr8caGY(b, c));
    }

    /* renamed from: minOf-VKSA0NQ, reason: not valid java name */
    private static final short m7915minOfVKSA0NQ(short a, short b, short c) {
        return UComparisonsKt.m7910minOf5PvTz6A(a, UComparisonsKt.m7910minOf5PvTz6A(b, c));
    }

    /* renamed from: minOf-Md2H83M, reason: not valid java name */
    public static final int m7913minOfMd2H83M(int a, int... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int min = a;
        int m6873getSizeimpl = UIntArray.m6873getSizeimpl(other);
        for (int i = 0; i < m6873getSizeimpl; i++) {
            int e = UIntArray.m6872getpVg5ArA(other, i);
            min = UComparisonsKt.m7911minOfJ1ME1BU(min, e);
        }
        return min;
    }

    /* renamed from: minOf-R03FKyM, reason: not valid java name */
    public static final long m7914minOfR03FKyM(long a, long... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        long min = a;
        int m6952getSizeimpl = ULongArray.m6952getSizeimpl(other);
        for (int i = 0; i < m6952getSizeimpl; i++) {
            long e = ULongArray.m6951getsVKNKU(other, i);
            min = UComparisonsKt.m7919minOfeb3DHEI(min, e);
        }
        return min;
    }

    /* renamed from: minOf-Wr6uiD8, reason: not valid java name */
    public static final byte m7917minOfWr6uiD8(byte a, byte... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        byte min = a;
        int m6794getSizeimpl = UByteArray.m6794getSizeimpl(other);
        for (int i = 0; i < m6794getSizeimpl; i++) {
            byte e = UByteArray.m6793getw2LRezQ(other, i);
            min = UComparisonsKt.m7912minOfKr8caGY(min, e);
        }
        return min;
    }

    /* renamed from: minOf-t1qELG4, reason: not valid java name */
    public static final short m7921minOft1qELG4(short a, short... other) {
        Intrinsics.checkNotNullParameter(other, "other");
        short min = a;
        int m7057getSizeimpl = UShortArray.m7057getSizeimpl(other);
        for (int i = 0; i < m7057getSizeimpl; i++) {
            short e = UShortArray.m7056getMh2AYeg(other, i);
            min = UComparisonsKt.m7910minOf5PvTz6A(min, e);
        }
        return min;
    }
}
