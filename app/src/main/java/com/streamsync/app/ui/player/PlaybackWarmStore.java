package com.streamsync.app.ui.player;

import android.os.SystemClock;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.LastStreamStore;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlaybackWarmStore.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001$B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u000fJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u000fJ\u0010\u0010 \u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\fJ\u000e\u0010#\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/streamsync/app/ui/player/PlaybackWarmStore;", "", "()V", "HEAD_BYTES", "", "HEAD_TTL_MS", "MAX_MIME_ENTRIES", "", "MIME_TTL_MS", "TAIL_BYTES", "TAIL_TTL_MS", TtmlNode.TAG_HEAD, "Lcom/streamsync/app/ui/player/PlaybackWarmStore$Window;", "knownMimes", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlin/Pair;", "nowMs", "Lkotlin/Function0;", "getNowMs$app_mobileDebug", "()Lkotlin/jvm/functions/Function0;", "setNowMs$app_mobileDebug", "(Lkotlin/jvm/functions/Function0;)V", "tail", "clear", "", "getHead", "url", "getTail", "hasHead", "", "hasTail", "mimeTypeOf", "putHead", "window", "putTail", "Window", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlaybackWarmStore {
    public static final long HEAD_BYTES = 262144;
    public static final long HEAD_TTL_MS = 60000;
    private static final int MAX_MIME_ENTRIES = 8;
    private static final long MIME_TTL_MS = 300000;
    public static final long TAIL_BYTES = 4194304;
    public static final long TAIL_TTL_MS = 300000;
    private static volatile Window head;
    private static volatile Window tail;
    public static final PlaybackWarmStore INSTANCE = new PlaybackWarmStore();
    private static Function0<Long> nowMs = new Function0<Long>() { // from class: com.streamsync.app.ui.player.PlaybackWarmStore$nowMs$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Long invoke() {
            return Long.valueOf(SystemClock.uptimeMillis());
        }
    };
    private static final ConcurrentHashMap<String, Pair<String, Long>> knownMimes = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private PlaybackWarmStore() {
    }

    /* compiled from: PlaybackWarmStore.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005J\u0016\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/streamsync/app/ui/player/PlaybackWarmStore$Window;", "", "url", "", "startPosition", "", "totalLength", "bytes", "", "atUptimeMs", "mimeType", "(Ljava/lang/String;JJ[BJLjava/lang/String;)V", "getAtUptimeMs", "()J", "getBytes", "()[B", "endExclusive", "getEndExclusive", "getMimeType", "()Ljava/lang/String;", "getStartPosition", "getTotalLength", "getUrl", "covers", "", "position", LastStreamStore.KEY_LENGTH, "fresh", "nowMs", "ttlMs", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final class Window {
        public static final int $stable = 8;
        private final long atUptimeMs;
        private final byte[] bytes;
        private final String mimeType;
        private final long startPosition;
        private final long totalLength;
        private final String url;

        public Window(String url, long startPosition, long totalLength, byte[] bytes, long atUptimeMs, String mimeType) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            this.url = url;
            this.startPosition = startPosition;
            this.totalLength = totalLength;
            this.bytes = bytes;
            this.atUptimeMs = atUptimeMs;
            this.mimeType = mimeType;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ Window(java.lang.String r12, long r13, long r15, byte[] r17, long r18, java.lang.String r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
            /*
                r11 = this;
                r0 = r21 & 32
                if (r0 == 0) goto L7
                r0 = 0
                r10 = r0
                goto L9
            L7:
                r10 = r20
            L9:
                r1 = r11
                r2 = r12
                r3 = r13
                r5 = r15
                r7 = r17
                r8 = r18
                r1.<init>(r2, r3, r5, r7, r8, r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlaybackWarmStore.Window.<init>(java.lang.String, long, long, byte[], long, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final String getUrl() {
            return this.url;
        }

        public final long getStartPosition() {
            return this.startPosition;
        }

        public final long getTotalLength() {
            return this.totalLength;
        }

        public final byte[] getBytes() {
            return this.bytes;
        }

        public final long getAtUptimeMs() {
            return this.atUptimeMs;
        }

        public final String getMimeType() {
            return this.mimeType;
        }

        public final long getEndExclusive() {
            return this.startPosition + this.bytes.length;
        }

        public final boolean fresh(long nowMs, long ttlMs) {
            return nowMs - this.atUptimeMs <= ttlMs;
        }

        public final boolean covers(long position, long length) {
            return length > 0 && position >= this.startPosition && position + length <= getEndExclusive();
        }
    }

    public final Function0<Long> getNowMs$app_mobileDebug() {
        return nowMs;
    }

    public final void setNowMs$app_mobileDebug(Function0<Long> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        nowMs = function0;
    }

    public final void putHead(Window window) {
        Object next;
        Intrinsics.checkNotNullParameter(window, "window");
        head = window;
        String mime = window.getMimeType();
        if (mime == null) {
            return;
        }
        if (knownMimes.size() >= 8) {
            Iterator<T> it = knownMimes.entrySet().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    Map.Entry it2 = (Map.Entry) next;
                    long longValue = ((Number) ((Pair) it2.getValue()).getSecond()).longValue();
                    do {
                        Object next2 = it.next();
                        Map.Entry it3 = (Map.Entry) next2;
                        long longValue2 = ((Number) ((Pair) it3.getValue()).getSecond()).longValue();
                        if (longValue > longValue2) {
                            next = next2;
                            longValue = longValue2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            Map.Entry entry = (Map.Entry) next;
            String oldest = entry != null ? (String) entry.getKey() : null;
            if (oldest != null) {
                knownMimes.remove(oldest);
            }
        }
        knownMimes.put(window.getUrl(), TuplesKt.to(mime, nowMs.invoke()));
    }

    public final Window getHead(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        long now = nowMs.invoke().longValue();
        Window h = head;
        if (h == null || !Intrinsics.areEqual(h.getUrl(), url) || !h.fresh(now, 60000L)) {
            return null;
        }
        head = null;
        return h;
    }

    public final boolean hasHead(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Window h = head;
        return h != null && Intrinsics.areEqual(h.getUrl(), url) && h.fresh(nowMs.invoke().longValue(), 60000L);
    }

    public final String mimeTypeOf(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Window h = head;
        if (h != null && Intrinsics.areEqual(h.getUrl(), url) && h.fresh(nowMs.invoke().longValue(), 60000L) && h.getMimeType() != null) {
            return h.getMimeType();
        }
        Pair known = knownMimes.get(url);
        if (known == null) {
            return null;
        }
        if (nowMs.invoke().longValue() - known.getSecond().longValue() > 300000) {
            knownMimes.remove(url);
            return null;
        }
        return known.getFirst();
    }

    public final void putTail(Window window) {
        Object next;
        Intrinsics.checkNotNullParameter(window, "window");
        tail = window;
        String mime = window.getMimeType();
        if (mime == null) {
            return;
        }
        if (knownMimes.size() >= 8) {
            Iterator<T> it = knownMimes.entrySet().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    Map.Entry it2 = (Map.Entry) next;
                    long longValue = ((Number) ((Pair) it2.getValue()).getSecond()).longValue();
                    do {
                        Object next2 = it.next();
                        Map.Entry it3 = (Map.Entry) next2;
                        long longValue2 = ((Number) ((Pair) it3.getValue()).getSecond()).longValue();
                        if (longValue > longValue2) {
                            next = next2;
                            longValue = longValue2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            Map.Entry entry = (Map.Entry) next;
            String oldest = entry != null ? (String) entry.getKey() : null;
            if (oldest != null) {
                knownMimes.remove(oldest);
            }
        }
        knownMimes.put(window.getUrl(), TuplesKt.to(mime, nowMs.invoke()));
    }

    public final boolean hasTail(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Window t = tail;
        return t != null && Intrinsics.areEqual(t.getUrl(), url) && t.fresh(nowMs.invoke().longValue(), 300000L);
    }

    public final Window getTail(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Window t = tail;
        if (t != null && Intrinsics.areEqual(t.getUrl(), url) && t.fresh(nowMs.invoke().longValue(), 300000L)) {
            return t;
        }
        return null;
    }

    public final void clear() {
        head = null;
        tail = null;
        knownMimes.clear();
    }
}
