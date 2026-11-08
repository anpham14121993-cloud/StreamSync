package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\"\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"com/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient$Companion$mergeChunks$Cand", "", "word", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "interiorMs", "", "(Lcom/streamsync/app/subtitle/autosync/TimedWord;J)V", "getInteriorMs", "()J", "getWord", "()Lcom/streamsync/app/subtitle/autosync/TimedWord;", "component1", "component2", "copy", "(Lcom/streamsync/app/subtitle/autosync/TimedWord;J)Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient$Companion$mergeChunks$Cand;", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* renamed from: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$Companion$mergeChunks$Cand, reason: from toString */
/* loaded from: classes19.dex */
public final /* data */ class Cand {
    private final long interiorMs;
    private final TimedWord word;

    public static /* synthetic */ Cand copy$default(Cand cand, TimedWord timedWord, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            timedWord = cand.word;
        }
        if ((i & 2) != 0) {
            j = cand.interiorMs;
        }
        return cand.copy(timedWord, j);
    }

    /* renamed from: component1, reason: from getter */
    public final TimedWord getWord() {
        return this.word;
    }

    /* renamed from: component2, reason: from getter */
    public final long getInteriorMs() {
        return this.interiorMs;
    }

    public final Cand copy(TimedWord word, long interiorMs) {
        Intrinsics.checkNotNullParameter(word, "word");
        return new Cand(word, interiorMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cand)) {
            return false;
        }
        Cand cand = (Cand) other;
        return Intrinsics.areEqual(this.word, cand.word) && this.interiorMs == cand.interiorMs;
    }

    public int hashCode() {
        return (this.word.hashCode() * 31) + Long.hashCode(this.interiorMs);
    }

    public String toString() {
        return "Cand(word=" + this.word + ", interiorMs=" + this.interiorMs + ")";
    }

    public Cand(TimedWord word, long interiorMs) {
        Intrinsics.checkNotNullParameter(word, "word");
        this.word = word;
        this.interiorMs = interiorMs;
    }

    public final long getInteriorMs() {
        return this.interiorMs;
    }

    public final TimedWord getWord() {
        return this.word;
    }
}
