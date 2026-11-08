package com.streamsync.app.subtitle.audio;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: PlayerAudioCapture.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/subtitle/audio/AudioProcessorAudioFormat;", "", "sampleRate", "", "channelCount", "encoding", "(III)V", "bytesPerFrame", "getBytesPerFrame", "()I", "bytesPerSample", "getBytesPerSample", "getChannelCount", "getEncoding", "getSampleRate", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class AudioProcessorAudioFormat {
    public static final int $stable = 0;
    private final int channelCount;
    private final int encoding;
    private final int sampleRate;

    public static /* synthetic */ AudioProcessorAudioFormat copy$default(AudioProcessorAudioFormat audioProcessorAudioFormat, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = audioProcessorAudioFormat.sampleRate;
        }
        if ((i4 & 2) != 0) {
            i2 = audioProcessorAudioFormat.channelCount;
        }
        if ((i4 & 4) != 0) {
            i3 = audioProcessorAudioFormat.encoding;
        }
        return audioProcessorAudioFormat.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSampleRate() {
        return this.sampleRate;
    }

    /* renamed from: component2, reason: from getter */
    public final int getChannelCount() {
        return this.channelCount;
    }

    /* renamed from: component3, reason: from getter */
    public final int getEncoding() {
        return this.encoding;
    }

    public final AudioProcessorAudioFormat copy(int sampleRate, int channelCount, int encoding) {
        return new AudioProcessorAudioFormat(sampleRate, channelCount, encoding);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioProcessorAudioFormat)) {
            return false;
        }
        AudioProcessorAudioFormat audioProcessorAudioFormat = (AudioProcessorAudioFormat) other;
        return this.sampleRate == audioProcessorAudioFormat.sampleRate && this.channelCount == audioProcessorAudioFormat.channelCount && this.encoding == audioProcessorAudioFormat.encoding;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.sampleRate) * 31) + Integer.hashCode(this.channelCount)) * 31) + Integer.hashCode(this.encoding);
    }

    public String toString() {
        return "AudioProcessorAudioFormat(sampleRate=" + this.sampleRate + ", channelCount=" + this.channelCount + ", encoding=" + this.encoding + ")";
    }

    public AudioProcessorAudioFormat(int sampleRate, int channelCount, int encoding) {
        this.sampleRate = sampleRate;
        this.channelCount = channelCount;
        this.encoding = encoding;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public final int getChannelCount() {
        return this.channelCount;
    }

    public final int getEncoding() {
        return this.encoding;
    }

    public final int getBytesPerSample() {
        return this.encoding == 4 ? 4 : 2;
    }

    public final int getBytesPerFrame() {
        return this.channelCount * getBytesPerSample();
    }
}
