package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/AudioChunk;", "", "index", "", "startSample", "endSample", "(III)V", "getEndSample", "()I", "getIndex", "getStartSample", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final /* data */ class AudioChunk {
    public static final int $stable = 0;
    private final int endSample;
    private final int index;
    private final int startSample;

    public static /* synthetic */ AudioChunk copy$default(AudioChunk audioChunk, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = audioChunk.index;
        }
        if ((i4 & 2) != 0) {
            i2 = audioChunk.startSample;
        }
        if ((i4 & 4) != 0) {
            i3 = audioChunk.endSample;
        }
        return audioChunk.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStartSample() {
        return this.startSample;
    }

    /* renamed from: component3, reason: from getter */
    public final int getEndSample() {
        return this.endSample;
    }

    public final AudioChunk copy(int index, int startSample, int endSample) {
        return new AudioChunk(index, startSample, endSample);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioChunk)) {
            return false;
        }
        AudioChunk audioChunk = (AudioChunk) other;
        return this.index == audioChunk.index && this.startSample == audioChunk.startSample && this.endSample == audioChunk.endSample;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.index) * 31) + Integer.hashCode(this.startSample)) * 31) + Integer.hashCode(this.endSample);
    }

    public String toString() {
        return "AudioChunk(index=" + this.index + ", startSample=" + this.startSample + ", endSample=" + this.endSample + ")";
    }

    public AudioChunk(int index, int startSample, int endSample) {
        this.index = index;
        this.startSample = startSample;
        this.endSample = endSample;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getStartSample() {
        return this.startSample;
    }

    public final int getEndSample() {
        return this.endSample;
    }
}
