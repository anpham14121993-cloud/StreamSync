package com.streamsync.app.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: ThroughputAdvice.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003\u0017\u0018\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fJ-\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/streamsync/app/settings/ThroughputAdvice;", "", "()V", "SUGGESTED_CONNECTIONS", "", "TEST_CHUNK_MB", "", "getTEST_CHUNK_MB", "()Ljava/util/List;", "TEST_SECONDS", "", "WIN_RATIO", "", "fmt", "", "mbps", "interpret", "Lcom/streamsync/app/settings/ThroughputAdvice$Result;", "baselineMbps", "rows", "Lcom/streamsync/app/settings/ThroughputAdvice$Row;", "videoBitrateMbps", "(DLjava/util/List;Ljava/lang/Double;)Lcom/streamsync/app/settings/ThroughputAdvice$Result;", "Advice", "Result", "Row", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class ThroughputAdvice {
    public static final int SUGGESTED_CONNECTIONS = 4;
    public static final long TEST_SECONDS = 8;
    public static final double WIN_RATIO = 1.2d;
    public static final ThroughputAdvice INSTANCE = new ThroughputAdvice();
    private static final List<Integer> TEST_CHUNK_MB = CollectionsKt.listOf((Object[]) new Integer[]{1, 4, 8, 16});
    public static final int $stable = 8;

    /* compiled from: ThroughputAdvice.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/streamsync/app/settings/ThroughputAdvice$Advice;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "KEEP_SINGLE_CONNECTION", "TURN_ON_PARALLEL", "SOURCE_TOO_HEAVY", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public enum Advice {
        UNKNOWN,
        KEEP_SINGLE_CONNECTION,
        TURN_ON_PARALLEL,
        SOURCE_TOO_HEAVY;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Advice> getEntries() {
            return $ENTRIES;
        }
    }

    private ThroughputAdvice() {
    }

    public final List<Integer> getTEST_CHUNK_MB() {
        return TEST_CHUNK_MB;
    }

    /* compiled from: ThroughputAdvice.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/streamsync/app/settings/ThroughputAdvice$Row;", "", "chunkMb", "", "megabitsPerSecond", "", "(ID)V", "getChunkMb", "()I", "getMegabitsPerSecond", "()D", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final /* data */ class Row {
        public static final int $stable = 0;
        private final int chunkMb;
        private final double megabitsPerSecond;

        public static /* synthetic */ Row copy$default(Row row, int i, double d, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = row.chunkMb;
            }
            if ((i2 & 2) != 0) {
                d = row.megabitsPerSecond;
            }
            return row.copy(i, d);
        }

        /* renamed from: component1, reason: from getter */
        public final int getChunkMb() {
            return this.chunkMb;
        }

        /* renamed from: component2, reason: from getter */
        public final double getMegabitsPerSecond() {
            return this.megabitsPerSecond;
        }

        public final Row copy(int chunkMb, double megabitsPerSecond) {
            return new Row(chunkMb, megabitsPerSecond);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Row)) {
                return false;
            }
            Row row = (Row) other;
            return this.chunkMb == row.chunkMb && Double.compare(this.megabitsPerSecond, row.megabitsPerSecond) == 0;
        }

        public int hashCode() {
            return (Integer.hashCode(this.chunkMb) * 31) + Double.hashCode(this.megabitsPerSecond);
        }

        public String toString() {
            return "Row(chunkMb=" + this.chunkMb + ", megabitsPerSecond=" + this.megabitsPerSecond + ")";
        }

        public Row(int chunkMb, double megabitsPerSecond) {
            this.chunkMb = chunkMb;
            this.megabitsPerSecond = megabitsPerSecond;
        }

        public final int getChunkMb() {
            return this.chunkMb;
        }

        public final double getMegabitsPerSecond() {
            return this.megabitsPerSecond;
        }
    }

    /* compiled from: ThroughputAdvice.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/streamsync/app/settings/ThroughputAdvice$Result;", "", "advice", "Lcom/streamsync/app/settings/ThroughputAdvice$Advice;", "bestChunkMb", "", "suggestedConnections", "message", "", "(Lcom/streamsync/app/settings/ThroughputAdvice$Advice;IILjava/lang/String;)V", "getAdvice", "()Lcom/streamsync/app/settings/ThroughputAdvice$Advice;", "getBestChunkMb", "()I", "getMessage", "()Ljava/lang/String;", "getSuggestedConnections", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final /* data */ class Result {
        public static final int $stable = 0;
        private final Advice advice;
        private final int bestChunkMb;
        private final String message;
        private final int suggestedConnections;

        public static /* synthetic */ Result copy$default(Result result, Advice advice, int i, int i2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                advice = result.advice;
            }
            if ((i3 & 2) != 0) {
                i = result.bestChunkMb;
            }
            if ((i3 & 4) != 0) {
                i2 = result.suggestedConnections;
            }
            if ((i3 & 8) != 0) {
                str = result.message;
            }
            return result.copy(advice, i, i2, str);
        }

        /* renamed from: component1, reason: from getter */
        public final Advice getAdvice() {
            return this.advice;
        }

        /* renamed from: component2, reason: from getter */
        public final int getBestChunkMb() {
            return this.bestChunkMb;
        }

        /* renamed from: component3, reason: from getter */
        public final int getSuggestedConnections() {
            return this.suggestedConnections;
        }

        /* renamed from: component4, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Result copy(Advice advice, int bestChunkMb, int suggestedConnections, String message) {
            Intrinsics.checkNotNullParameter(advice, "advice");
            Intrinsics.checkNotNullParameter(message, "message");
            return new Result(advice, bestChunkMb, suggestedConnections, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.advice == result.advice && this.bestChunkMb == result.bestChunkMb && this.suggestedConnections == result.suggestedConnections && Intrinsics.areEqual(this.message, result.message);
        }

        public int hashCode() {
            return (((((this.advice.hashCode() * 31) + Integer.hashCode(this.bestChunkMb)) * 31) + Integer.hashCode(this.suggestedConnections)) * 31) + this.message.hashCode();
        }

        public String toString() {
            return "Result(advice=" + this.advice + ", bestChunkMb=" + this.bestChunkMb + ", suggestedConnections=" + this.suggestedConnections + ", message=" + this.message + ")";
        }

        public Result(Advice advice, int bestChunkMb, int suggestedConnections, String message) {
            Intrinsics.checkNotNullParameter(advice, "advice");
            Intrinsics.checkNotNullParameter(message, "message");
            this.advice = advice;
            this.bestChunkMb = bestChunkMb;
            this.suggestedConnections = suggestedConnections;
            this.message = message;
        }

        public final Advice getAdvice() {
            return this.advice;
        }

        public final int getBestChunkMb() {
            return this.bestChunkMb;
        }

        public final int getSuggestedConnections() {
            return this.suggestedConnections;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    public static /* synthetic */ Result interpret$default(ThroughputAdvice throughputAdvice, double d, List list, Double d2, int i, Object obj) {
        if ((i & 4) != 0) {
            d2 = null;
        }
        return throughputAdvice.interpret(d, list, d2);
    }

    public final Result interpret(double baselineMbps, List<Row> rows, Double videoBitrateMbps) {
        Object maxElem$iv;
        Intrinsics.checkNotNullParameter(rows, "rows");
        List<Row> $this$filter$iv = rows;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            Row it2 = (Row) element$iv$iv;
            if (it2.getMegabitsPerSecond() > 0.0d) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$maxByOrNull$iv = (List) destination$iv$iv;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                Row it3 = (Row) maxElem$iv;
                double maxValue$iv = it3.getMegabitsPerSecond();
                do {
                    Object e$iv = iterator$iv.next();
                    Row it4 = (Row) e$iv;
                    double v$iv = it4.getMegabitsPerSecond();
                    if (Double.compare(maxValue$iv, v$iv) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        Row best = (Row) maxElem$iv;
        if (baselineMbps <= 0.0d || best == null) {
            return new Result(Advice.UNKNOWN, 0, 4, "Chưa đo được tốc độ. Thử lại, hoặc kiểm tra xem luồng vừa xem còn sống không.");
        }
        double bestMbps = best.getMegabitsPerSecond();
        boolean beatsBaseline = bestMbps >= 1.2d * baselineMbps;
        if (videoBitrateMbps != null && videoBitrateMbps.doubleValue() > 0.0d && bestMbps < videoBitrateMbps.doubleValue()) {
            return new Result(Advice.SOURCE_TOO_HEAVY, best.getChunkMb(), 4, "Cả lần đo nhanh nhất cũng chỉ đạt " + fmt(bestMbps) + " Mbps, thấp hơn bitrate luồng " + fmt(videoBitrateMbps.doubleValue()) + " Mbps. " + (beatsBaseline ? "Song song có giúp (nên bật, chunk " + best.getChunkMb() + " MB) nhưng vẫn không đủ — hãy chọn bản nhẹ hơn hoặc nguồn khác. Bộ đệm lớn hơn cũng không cứu được, vì nó chỉ mua thời gian." : "Bật song song cũng không giúp — luồng này vượt xa đường truyền. Hãy chọn bản nhẹ hơn hoặc nguồn khác."));
        }
        if (!beatsBaseline) {
            return new Result(Advice.KEEP_SINGLE_CONNECTION, 0, 1, "Một kết nối đã đủ: " + fmt(baselineMbps) + " Mbps, còn lần song song tốt nhất chỉ " + fmt(bestMbps) + " Mbps. Để tắt song song — bật lên chỉ tốn bộ nhớ và CPU.");
        }
        return new Result(Advice.TURN_ON_PARALLEL, best.getChunkMb(), 4, "Song song thắng: " + fmt(bestMbps) + " Mbps so với " + fmt(baselineMbps) + " Mbps khi chỉ một kết nối (chunk " + best.getChunkMb() + " MB). Nên bật 4 kết nối.");
    }

    public final String fmt(double mbps) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(mbps)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
