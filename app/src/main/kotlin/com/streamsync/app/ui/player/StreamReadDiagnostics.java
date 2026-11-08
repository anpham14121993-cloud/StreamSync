package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.player.StreamReadDiagnostics;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StreamReadDiagnostics.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ&\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/streamsync/app/ui/player/StreamReadDiagnostics;", "", "()V", "CAPACITY", "", "records", "Lkotlin/collections/ArrayDeque;", "Lcom/streamsync/app/ui/player/StreamReadDiagnostics$Record;", "clear", "", "record", "position", "", "specLength", "resolved", "source", "", "summary", "Record", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class StreamReadDiagnostics {
    private static final int CAPACITY = 24;
    public static final StreamReadDiagnostics INSTANCE = new StreamReadDiagnostics();
    private static final ArrayDeque<Record> records = new ArrayDeque<>();
    public static final int $stable = 8;

    private StreamReadDiagnostics() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: StreamReadDiagnostics.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/ui/player/StreamReadDiagnostics$Record;", "", "position", "", "specLength", "resolved", "source", "", "(JJJLjava/lang/String;)V", "getPosition", "()J", "getResolved", "getSource", "()Ljava/lang/String;", "getSpecLength", "render", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final class Record {
        private final long position;
        private final long resolved;
        private final String source;
        private final long specLength;

        public Record(long position, long specLength, long resolved, String source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.position = position;
            this.specLength = specLength;
            this.resolved = resolved;
            this.source = source;
        }

        public final long getPosition() {
            return this.position;
        }

        public final long getSpecLength() {
            return this.specLength;
        }

        public final long getResolved() {
            return this.resolved;
        }

        public final String getSource() {
            return this.source;
        }

        public final String render() {
            String spec = this.specLength < 0 ? "unset" : String.valueOf(this.specLength);
            String promised = this.resolved >= 0 ? String.valueOf(this.resolved) : "unset";
            return "pos=" + this.position + " specLen=" + spec + " camKet=" + promised + " (" + this.source + ")";
        }
    }

    public final synchronized void record(long position, long specLength, long resolved, String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        records.addLast(new Record(position, specLength, resolved, source));
        while (records.size() > 24) {
            records.removeFirst();
        }
    }

    public final synchronized void clear() {
        records.clear();
    }

    public final synchronized String summary() {
        if (records.isEmpty()) {
            return "";
        }
        return CollectionsKt.joinToString$default(records, "\n", null, null, 0, null, new Function1<Record, CharSequence>() { // from class: com.streamsync.app.ui.player.StreamReadDiagnostics$summary$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(StreamReadDiagnostics.Record it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return "  • " + it.render();
            }
        }, 30, null);
    }
}
