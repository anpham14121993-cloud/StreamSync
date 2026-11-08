package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/TimedWord;", "", "text", "", "startMs", "", "endMs", "(Ljava/lang/String;JJ)V", "getEndMs", "()J", "getStartMs", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final /* data */ class TimedWord {
    public static final int $stable = 0;
    private final long endMs;
    private final long startMs;
    private final String text;

    public static /* synthetic */ TimedWord copy$default(TimedWord timedWord, String str, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = timedWord.text;
        }
        if ((i & 2) != 0) {
            j = timedWord.startMs;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = timedWord.endMs;
        }
        return timedWord.copy(str, j3, j2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component2, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* renamed from: component3, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    public final TimedWord copy(String text, long startMs, long endMs) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new TimedWord(text, startMs, endMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimedWord)) {
            return false;
        }
        TimedWord timedWord = (TimedWord) other;
        return Intrinsics.areEqual(this.text, timedWord.text) && this.startMs == timedWord.startMs && this.endMs == timedWord.endMs;
    }

    public int hashCode() {
        return (((this.text.hashCode() * 31) + Long.hashCode(this.startMs)) * 31) + Long.hashCode(this.endMs);
    }

    public String toString() {
        return "TimedWord(text=" + this.text + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ")";
    }

    public TimedWord(String text, long startMs, long endMs) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.startMs = startMs;
        this.endMs = endMs;
    }

    public final String getText() {
        return this.text;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final long getEndMs() {
        return this.endMs;
    }
}
