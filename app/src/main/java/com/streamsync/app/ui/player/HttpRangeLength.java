package com.streamsync.app.ui.player;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HttpRangeLength.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/ui/player/HttpRangeLength;", "", "()V", "parseContentRange", "Lcom/streamsync/app/ui/player/HttpRangeLength$ContentRange;", "header", "", "remaining", "", "httpCode", "", "contentRangeHeader", "contentLength", TtmlNode.START, "skipped", "ContentRange", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class HttpRangeLength {
    public static final int $stable = 0;
    public static final HttpRangeLength INSTANCE = new HttpRangeLength();

    private HttpRangeLength() {
    }

    /* compiled from: HttpRangeLength.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/ui/player/HttpRangeLength$ContentRange;", "", TtmlNode.START, "", TtmlNode.END, "total", "(JJJ)V", "getEnd", "()J", "getStart", "getTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final /* data */ class ContentRange {
        public static final int $stable = 0;
        private final long end;
        private final long start;
        private final long total;

        public static /* synthetic */ ContentRange copy$default(ContentRange contentRange, long j, long j2, long j3, int i, Object obj) {
            if ((i & 1) != 0) {
                j = contentRange.start;
            }
            long j4 = j;
            if ((i & 2) != 0) {
                j2 = contentRange.end;
            }
            long j5 = j2;
            if ((i & 4) != 0) {
                j3 = contentRange.total;
            }
            return contentRange.copy(j4, j5, j3);
        }

        /* renamed from: component1, reason: from getter */
        public final long getStart() {
            return this.start;
        }

        /* renamed from: component2, reason: from getter */
        public final long getEnd() {
            return this.end;
        }

        /* renamed from: component3, reason: from getter */
        public final long getTotal() {
            return this.total;
        }

        public final ContentRange copy(long start, long end, long total) {
            return new ContentRange(start, end, total);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ContentRange)) {
                return false;
            }
            ContentRange contentRange = (ContentRange) other;
            return this.start == contentRange.start && this.end == contentRange.end && this.total == contentRange.total;
        }

        public int hashCode() {
            return (((Long.hashCode(this.start) * 31) + Long.hashCode(this.end)) * 31) + Long.hashCode(this.total);
        }

        public String toString() {
            return "ContentRange(start=" + this.start + ", end=" + this.end + ", total=" + this.total + ")";
        }

        public ContentRange(long start, long end, long total) {
            this.start = start;
            this.end = end;
            this.total = total;
        }

        public final long getEnd() {
            return this.end;
        }

        public final long getStart() {
            return this.start;
        }

        public final long getTotal() {
            return this.total;
        }
    }

    public final ContentRange parseContentRange(String header) {
        String str = header;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String value = StringsKt.trim((CharSequence) header).toString();
        if (!StringsKt.startsWith(value, "bytes", true)) {
            return null;
        }
        String substring = value.substring(5);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String rest = StringsKt.trim((CharSequence) substring).toString();
        int slash = StringsKt.indexOf$default((CharSequence) rest, '/', 0, false, 6, (Object) null);
        if (slash <= 0) {
            return null;
        }
        String substring2 = rest.substring(0, slash);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        String range = StringsKt.trim((CharSequence) substring2).toString();
        String substring3 = rest.substring(slash + 1);
        Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
        String totalPart = StringsKt.trim((CharSequence) substring3).toString();
        int dash = StringsKt.indexOf$default((CharSequence) range, '-', 0, false, 6, (Object) null);
        if (dash <= 0) {
            return null;
        }
        String substring4 = range.substring(0, dash);
        Intrinsics.checkNotNullExpressionValue(substring4, "substring(...)");
        Long longOrNull = StringsKt.toLongOrNull(StringsKt.trim((CharSequence) substring4).toString());
        if (longOrNull == null) {
            return null;
        }
        long start = longOrNull.longValue();
        String substring5 = range.substring(dash + 1);
        Intrinsics.checkNotNullExpressionValue(substring5, "substring(...)");
        Long longOrNull2 = StringsKt.toLongOrNull(StringsKt.trim((CharSequence) substring5).toString());
        if (longOrNull2 == null) {
            return null;
        }
        long end = longOrNull2.longValue();
        Long longOrNull3 = StringsKt.toLongOrNull(totalPart);
        long total = longOrNull3 != null ? longOrNull3.longValue() : -1L;
        if (start < 0 || end < start) {
            return null;
        }
        return new ContentRange(start, end, total);
    }

    public final long remaining(int httpCode, String contentRangeHeader, long contentLength, long start, long skipped) {
        ContentRange range = parseContentRange(contentRangeHeader);
        if (httpCode != 206 || range == null) {
            if (contentLength < 0) {
                return -1L;
            }
            long left = contentLength - skipped;
            if (left < 0) {
                return -1L;
            }
            return left;
        }
        long available = (range.getEnd() - start) + 1;
        if (available <= 0) {
            return -1L;
        }
        boolean z = false;
        if (0 <= contentLength && contentLength <= available) {
            z = true;
        }
        return z ? contentLength : available;
    }
}
