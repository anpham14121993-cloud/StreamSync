package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b8\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B¹\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u0012\b\b\u0002\u0010\u0011\u001a\u00020\n\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003¢\u0006\u0002\u0010\u0017J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\nHÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\bHÆ\u0003J\t\u00107\u001a\u00020\bHÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\bHÆ\u0003J\t\u0010=\u001a\u00020\nHÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\nHÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J½\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u0003HÆ\u0001J\u0013\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010E\u001a\u00020\u0003HÖ\u0001J\t\u0010F\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0018\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\u0014\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u0011\u0010\u0015\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001c¨\u0006G"}, d2 = {"Lcom/streamsync/app/ui/player/PlaybackInfo;", "", "width", "", "height", "displayWidth", "displayHeight", "frameRate", "", "videoCodec", "", "videoBitrate", "audioCodec", "audioChannels", "audioSampleRate", "audioBitrate", "dynamicRange", "colorSpace", "bitDepth", "bufferedPercent", "bufferedAheadSec", "totalBufferedSec", "trackGroups", "(IIIIFLjava/lang/String;ILjava/lang/String;IIILjava/lang/String;Ljava/lang/String;IIFFI)V", "aspectLabel", "getAspectLabel", "()Ljava/lang/String;", "getAudioBitrate", "()I", "getAudioChannels", "getAudioCodec", "getAudioSampleRate", "getBitDepth", "getBufferedAheadSec", "()F", "getBufferedPercent", "getColorSpace", "getDisplayHeight", "getDisplayWidth", "getDynamicRange", "getFrameRate", "getHeight", "getTotalBufferedSec", "getTrackGroups", "getVideoBitrate", "getVideoCodec", "getWidth", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final /* data */ class PlaybackInfo {
    private final int audioBitrate;
    private final int audioChannels;
    private final String audioCodec;
    private final int audioSampleRate;
    private final int bitDepth;
    private final float bufferedAheadSec;
    private final int bufferedPercent;
    private final String colorSpace;
    private final int displayHeight;
    private final int displayWidth;
    private final String dynamicRange;
    private final float frameRate;
    private final int height;
    private final float totalBufferedSec;
    private final int trackGroups;
    private final int videoBitrate;
    private final String videoCodec;
    private final int width;

    public PlaybackInfo() {
        this(0, 0, 0, 0, 0.0f, null, 0, null, 0, 0, 0, null, null, 0, 0, 0.0f, 0.0f, 0, 262143, null);
    }

    /* renamed from: component1, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component10, reason: from getter */
    public final int getAudioSampleRate() {
        return this.audioSampleRate;
    }

    /* renamed from: component11, reason: from getter */
    public final int getAudioBitrate() {
        return this.audioBitrate;
    }

    /* renamed from: component12, reason: from getter */
    public final String getDynamicRange() {
        return this.dynamicRange;
    }

    /* renamed from: component13, reason: from getter */
    public final String getColorSpace() {
        return this.colorSpace;
    }

    /* renamed from: component14, reason: from getter */
    public final int getBitDepth() {
        return this.bitDepth;
    }

    /* renamed from: component15, reason: from getter */
    public final int getBufferedPercent() {
        return this.bufferedPercent;
    }

    /* renamed from: component16, reason: from getter */
    public final float getBufferedAheadSec() {
        return this.bufferedAheadSec;
    }

    /* renamed from: component17, reason: from getter */
    public final float getTotalBufferedSec() {
        return this.totalBufferedSec;
    }

    /* renamed from: component18, reason: from getter */
    public final int getTrackGroups() {
        return this.trackGroups;
    }

    /* renamed from: component2, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component3, reason: from getter */
    public final int getDisplayWidth() {
        return this.displayWidth;
    }

    /* renamed from: component4, reason: from getter */
    public final int getDisplayHeight() {
        return this.displayHeight;
    }

    /* renamed from: component5, reason: from getter */
    public final float getFrameRate() {
        return this.frameRate;
    }

    /* renamed from: component6, reason: from getter */
    public final String getVideoCodec() {
        return this.videoCodec;
    }

    /* renamed from: component7, reason: from getter */
    public final int getVideoBitrate() {
        return this.videoBitrate;
    }

    /* renamed from: component8, reason: from getter */
    public final String getAudioCodec() {
        return this.audioCodec;
    }

    /* renamed from: component9, reason: from getter */
    public final int getAudioChannels() {
        return this.audioChannels;
    }

    public final PlaybackInfo copy(int width, int height, int displayWidth, int displayHeight, float frameRate, String videoCodec, int videoBitrate, String audioCodec, int audioChannels, int audioSampleRate, int audioBitrate, String dynamicRange, String colorSpace, int bitDepth, int bufferedPercent, float bufferedAheadSec, float totalBufferedSec, int trackGroups) {
        Intrinsics.checkNotNullParameter(videoCodec, "videoCodec");
        Intrinsics.checkNotNullParameter(audioCodec, "audioCodec");
        Intrinsics.checkNotNullParameter(dynamicRange, "dynamicRange");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        return new PlaybackInfo(width, height, displayWidth, displayHeight, frameRate, videoCodec, videoBitrate, audioCodec, audioChannels, audioSampleRate, audioBitrate, dynamicRange, colorSpace, bitDepth, bufferedPercent, bufferedAheadSec, totalBufferedSec, trackGroups);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackInfo)) {
            return false;
        }
        PlaybackInfo playbackInfo = (PlaybackInfo) other;
        return this.width == playbackInfo.width && this.height == playbackInfo.height && this.displayWidth == playbackInfo.displayWidth && this.displayHeight == playbackInfo.displayHeight && Float.compare(this.frameRate, playbackInfo.frameRate) == 0 && Intrinsics.areEqual(this.videoCodec, playbackInfo.videoCodec) && this.videoBitrate == playbackInfo.videoBitrate && Intrinsics.areEqual(this.audioCodec, playbackInfo.audioCodec) && this.audioChannels == playbackInfo.audioChannels && this.audioSampleRate == playbackInfo.audioSampleRate && this.audioBitrate == playbackInfo.audioBitrate && Intrinsics.areEqual(this.dynamicRange, playbackInfo.dynamicRange) && Intrinsics.areEqual(this.colorSpace, playbackInfo.colorSpace) && this.bitDepth == playbackInfo.bitDepth && this.bufferedPercent == playbackInfo.bufferedPercent && Float.compare(this.bufferedAheadSec, playbackInfo.bufferedAheadSec) == 0 && Float.compare(this.totalBufferedSec, playbackInfo.totalBufferedSec) == 0 && this.trackGroups == playbackInfo.trackGroups;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((Integer.hashCode(this.width) * 31) + Integer.hashCode(this.height)) * 31) + Integer.hashCode(this.displayWidth)) * 31) + Integer.hashCode(this.displayHeight)) * 31) + Float.hashCode(this.frameRate)) * 31) + this.videoCodec.hashCode()) * 31) + Integer.hashCode(this.videoBitrate)) * 31) + this.audioCodec.hashCode()) * 31) + Integer.hashCode(this.audioChannels)) * 31) + Integer.hashCode(this.audioSampleRate)) * 31) + Integer.hashCode(this.audioBitrate)) * 31) + this.dynamicRange.hashCode()) * 31) + this.colorSpace.hashCode()) * 31) + Integer.hashCode(this.bitDepth)) * 31) + Integer.hashCode(this.bufferedPercent)) * 31) + Float.hashCode(this.bufferedAheadSec)) * 31) + Float.hashCode(this.totalBufferedSec)) * 31) + Integer.hashCode(this.trackGroups);
    }

    public String toString() {
        return "PlaybackInfo(width=" + this.width + ", height=" + this.height + ", displayWidth=" + this.displayWidth + ", displayHeight=" + this.displayHeight + ", frameRate=" + this.frameRate + ", videoCodec=" + this.videoCodec + ", videoBitrate=" + this.videoBitrate + ", audioCodec=" + this.audioCodec + ", audioChannels=" + this.audioChannels + ", audioSampleRate=" + this.audioSampleRate + ", audioBitrate=" + this.audioBitrate + ", dynamicRange=" + this.dynamicRange + ", colorSpace=" + this.colorSpace + ", bitDepth=" + this.bitDepth + ", bufferedPercent=" + this.bufferedPercent + ", bufferedAheadSec=" + this.bufferedAheadSec + ", totalBufferedSec=" + this.totalBufferedSec + ", trackGroups=" + this.trackGroups + ")";
    }

    public PlaybackInfo(int width, int height, int displayWidth, int displayHeight, float frameRate, String videoCodec, int videoBitrate, String audioCodec, int audioChannels, int audioSampleRate, int audioBitrate, String dynamicRange, String colorSpace, int bitDepth, int bufferedPercent, float bufferedAheadSec, float totalBufferedSec, int trackGroups) {
        Intrinsics.checkNotNullParameter(videoCodec, "videoCodec");
        Intrinsics.checkNotNullParameter(audioCodec, "audioCodec");
        Intrinsics.checkNotNullParameter(dynamicRange, "dynamicRange");
        Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
        this.width = width;
        this.height = height;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.frameRate = frameRate;
        this.videoCodec = videoCodec;
        this.videoBitrate = videoBitrate;
        this.audioCodec = audioCodec;
        this.audioChannels = audioChannels;
        this.audioSampleRate = audioSampleRate;
        this.audioBitrate = audioBitrate;
        this.dynamicRange = dynamicRange;
        this.colorSpace = colorSpace;
        this.bitDepth = bitDepth;
        this.bufferedPercent = bufferedPercent;
        this.bufferedAheadSec = bufferedAheadSec;
        this.totalBufferedSec = totalBufferedSec;
        this.trackGroups = trackGroups;
    }

    public /* synthetic */ PlaybackInfo(int i, int i2, int i3, int i4, float f, String str, int i5, String str2, int i6, int i7, int i8, String str3, String str4, int i9, int i10, float f2, float f3, int i11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this((i12 & 1) != 0 ? 0 : i, (i12 & 2) != 0 ? 0 : i2, (i12 & 4) != 0 ? 0 : i3, (i12 & 8) != 0 ? 0 : i4, (i12 & 16) != 0 ? 0.0f : f, (i12 & 32) != 0 ? "" : str, (i12 & 64) != 0 ? 0 : i5, (i12 & 128) != 0 ? "" : str2, (i12 & 256) != 0 ? 0 : i6, (i12 & 512) != 0 ? 0 : i7, (i12 & 1024) != 0 ? 0 : i8, (i12 & 2048) != 0 ? "" : str3, (i12 & 4096) == 0 ? str4 : "", (i12 & 8192) != 0 ? 0 : i9, (i12 & 16384) != 0 ? 0 : i10, (i12 & 32768) != 0 ? 0.0f : f2, (i12 & 65536) != 0 ? 0.0f : f3, (i12 & 131072) != 0 ? 0 : i11);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getDisplayWidth() {
        return this.displayWidth;
    }

    public final int getDisplayHeight() {
        return this.displayHeight;
    }

    public final float getFrameRate() {
        return this.frameRate;
    }

    public final String getVideoCodec() {
        return this.videoCodec;
    }

    public final int getVideoBitrate() {
        return this.videoBitrate;
    }

    public final String getAudioCodec() {
        return this.audioCodec;
    }

    public final int getAudioChannels() {
        return this.audioChannels;
    }

    public final int getAudioSampleRate() {
        return this.audioSampleRate;
    }

    public final int getAudioBitrate() {
        return this.audioBitrate;
    }

    public final String getDynamicRange() {
        return this.dynamicRange;
    }

    public final String getColorSpace() {
        return this.colorSpace;
    }

    public final int getBitDepth() {
        return this.bitDepth;
    }

    public final int getBufferedPercent() {
        return this.bufferedPercent;
    }

    public final float getBufferedAheadSec() {
        return this.bufferedAheadSec;
    }

    public final float getTotalBufferedSec() {
        return this.totalBufferedSec;
    }

    public final int getTrackGroups() {
        return this.trackGroups;
    }

    public final String getAspectLabel() {
        int g;
        if (this.width > 0 && this.height > 0) {
            g = PlayerScreenKt.gcd(this.width, this.height);
            String ratio = (this.width / g) + ":" + (this.height / g);
            return this.height > this.width ? ratio + " (dọc)" : ratio;
        }
        return "—";
    }
}
