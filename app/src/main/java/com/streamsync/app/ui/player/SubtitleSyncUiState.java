package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\fHÆ\u0003JQ\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\fHÖ\u0001J\t\u0010&\u001a\u00020\tHÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006'"}, d2 = {"Lcom/streamsync/app/ui/player/SubtitleSyncUiState;", "", "capturing", "", "sending", "matching", "capturedMs", "", "message", "", "failed", "sentenceCount", "", "(ZZZJLjava/lang/String;ZI)V", "busy", "getBusy", "()Z", "getCapturedMs", "()J", "getCapturing", "getFailed", "getMatching", "getMessage", "()Ljava/lang/String;", "getSending", "getSentenceCount", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final /* data */ class SubtitleSyncUiState {
    public static final int $stable = 0;
    private final long capturedMs;
    private final boolean capturing;
    private final boolean failed;
    private final boolean matching;
    private final String message;
    private final boolean sending;
    private final int sentenceCount;

    public SubtitleSyncUiState() {
        this(false, false, false, 0L, null, false, 0, 127, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getCapturing() {
        return this.capturing;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getSending() {
        return this.sending;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getMatching() {
        return this.matching;
    }

    /* renamed from: component4, reason: from getter */
    public final long getCapturedMs() {
        return this.capturedMs;
    }

    /* renamed from: component5, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getFailed() {
        return this.failed;
    }

    /* renamed from: component7, reason: from getter */
    public final int getSentenceCount() {
        return this.sentenceCount;
    }

    public final SubtitleSyncUiState copy(boolean capturing, boolean sending, boolean matching, long capturedMs, String message, boolean failed, int sentenceCount) {
        return new SubtitleSyncUiState(capturing, sending, matching, capturedMs, message, failed, sentenceCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleSyncUiState)) {
            return false;
        }
        SubtitleSyncUiState subtitleSyncUiState = (SubtitleSyncUiState) other;
        return this.capturing == subtitleSyncUiState.capturing && this.sending == subtitleSyncUiState.sending && this.matching == subtitleSyncUiState.matching && this.capturedMs == subtitleSyncUiState.capturedMs && Intrinsics.areEqual(this.message, subtitleSyncUiState.message) && this.failed == subtitleSyncUiState.failed && this.sentenceCount == subtitleSyncUiState.sentenceCount;
    }

    public int hashCode() {
        return (((((((((((Boolean.hashCode(this.capturing) * 31) + Boolean.hashCode(this.sending)) * 31) + Boolean.hashCode(this.matching)) * 31) + Long.hashCode(this.capturedMs)) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + Boolean.hashCode(this.failed)) * 31) + Integer.hashCode(this.sentenceCount);
    }

    public String toString() {
        return "SubtitleSyncUiState(capturing=" + this.capturing + ", sending=" + this.sending + ", matching=" + this.matching + ", capturedMs=" + this.capturedMs + ", message=" + this.message + ", failed=" + this.failed + ", sentenceCount=" + this.sentenceCount + ")";
    }

    public SubtitleSyncUiState(boolean capturing, boolean sending, boolean matching, long capturedMs, String message, boolean failed, int sentenceCount) {
        this.capturing = capturing;
        this.sending = sending;
        this.matching = matching;
        this.capturedMs = capturedMs;
        this.message = message;
        this.failed = failed;
        this.sentenceCount = sentenceCount;
    }

    public /* synthetic */ SubtitleSyncUiState(boolean z, boolean z2, boolean z3, long j, String str, boolean z4, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? false : z2, (i2 & 4) != 0 ? false : z3, (i2 & 8) != 0 ? 0L : j, (i2 & 16) != 0 ? null : str, (i2 & 32) != 0 ? false : z4, (i2 & 64) == 0 ? i : 0);
    }

    public final boolean getCapturing() {
        return this.capturing;
    }

    public final boolean getSending() {
        return this.sending;
    }

    public final boolean getMatching() {
        return this.matching;
    }

    public final long getCapturedMs() {
        return this.capturedMs;
    }

    public final String getMessage() {
        return this.message;
    }

    public final boolean getFailed() {
        return this.failed;
    }

    public final int getSentenceCount() {
        return this.sentenceCount;
    }

    public final boolean getBusy() {
        return this.sending || this.matching;
    }
}
