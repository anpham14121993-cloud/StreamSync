package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LineMatch;", "", "referenceIndex", "", "candidateIndex", "(II)V", "getCandidateIndex", "()I", "getReferenceIndex", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final /* data */ class LineMatch {
    public static final int $stable = 0;
    private final int candidateIndex;
    private final int referenceIndex;

    public static /* synthetic */ LineMatch copy$default(LineMatch lineMatch, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = lineMatch.referenceIndex;
        }
        if ((i3 & 2) != 0) {
            i2 = lineMatch.candidateIndex;
        }
        return lineMatch.copy(i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getReferenceIndex() {
        return this.referenceIndex;
    }

    /* renamed from: component2, reason: from getter */
    public final int getCandidateIndex() {
        return this.candidateIndex;
    }

    public final LineMatch copy(int referenceIndex, int candidateIndex) {
        return new LineMatch(referenceIndex, candidateIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LineMatch)) {
            return false;
        }
        LineMatch lineMatch = (LineMatch) other;
        return this.referenceIndex == lineMatch.referenceIndex && this.candidateIndex == lineMatch.candidateIndex;
    }

    public int hashCode() {
        return (Integer.hashCode(this.referenceIndex) * 31) + Integer.hashCode(this.candidateIndex);
    }

    public String toString() {
        return "LineMatch(referenceIndex=" + this.referenceIndex + ", candidateIndex=" + this.candidateIndex + ")";
    }

    public LineMatch(int referenceIndex, int candidateIndex) {
        this.referenceIndex = referenceIndex;
        this.candidateIndex = candidateIndex;
    }

    public final int getReferenceIndex() {
        return this.referenceIndex;
    }

    public final int getCandidateIndex() {
        return this.candidateIndex;
    }
}
