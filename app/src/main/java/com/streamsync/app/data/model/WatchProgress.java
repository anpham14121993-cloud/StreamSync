package com.streamsync.app.data.model;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubtitleModels.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J_\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006*"}, d2 = {"Lcom/streamsync/app/data/model/WatchProgress;", "", "mediaId", "", "positionMs", "", "durationMs", "updatedAtEpochMs", "deviceId", "streamUrl", "addonId", "addonName", "(Ljava/lang/String;JJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddonId", "()Ljava/lang/String;", "getAddonName", "getDeviceId", "getDurationMs", "()J", "fractionWatched", "", "getFractionWatched", "()F", "getMediaId", "getPositionMs", "getStreamUrl", "getUpdatedAtEpochMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class WatchProgress {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonName;
    private final String deviceId;
    private final long durationMs;
    private final String mediaId;
    private final long positionMs;
    private final String streamUrl;
    private final long updatedAtEpochMs;

    /* renamed from: component1, reason: from getter */
    public final String getMediaId() {
        return this.mediaId;
    }

    /* renamed from: component2, reason: from getter */
    public final long getPositionMs() {
        return this.positionMs;
    }

    /* renamed from: component3, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    /* renamed from: component4, reason: from getter */
    public final long getUpdatedAtEpochMs() {
        return this.updatedAtEpochMs;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getStreamUrl() {
        return this.streamUrl;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* renamed from: component8, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    public final WatchProgress copy(String mediaId, long positionMs, long durationMs, long updatedAtEpochMs, String deviceId, String streamUrl, String addonId, String addonName) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return new WatchProgress(mediaId, positionMs, durationMs, updatedAtEpochMs, deviceId, streamUrl, addonId, addonName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchProgress)) {
            return false;
        }
        WatchProgress watchProgress = (WatchProgress) other;
        return Intrinsics.areEqual(this.mediaId, watchProgress.mediaId) && this.positionMs == watchProgress.positionMs && this.durationMs == watchProgress.durationMs && this.updatedAtEpochMs == watchProgress.updatedAtEpochMs && Intrinsics.areEqual(this.deviceId, watchProgress.deviceId) && Intrinsics.areEqual(this.streamUrl, watchProgress.streamUrl) && Intrinsics.areEqual(this.addonId, watchProgress.addonId) && Intrinsics.areEqual(this.addonName, watchProgress.addonName);
    }

    public int hashCode() {
        return (((((((((((((this.mediaId.hashCode() * 31) + Long.hashCode(this.positionMs)) * 31) + Long.hashCode(this.durationMs)) * 31) + Long.hashCode(this.updatedAtEpochMs)) * 31) + this.deviceId.hashCode()) * 31) + (this.streamUrl == null ? 0 : this.streamUrl.hashCode())) * 31) + (this.addonId == null ? 0 : this.addonId.hashCode())) * 31) + (this.addonName != null ? this.addonName.hashCode() : 0);
    }

    public String toString() {
        return "WatchProgress(mediaId=" + this.mediaId + ", positionMs=" + this.positionMs + ", durationMs=" + this.durationMs + ", updatedAtEpochMs=" + this.updatedAtEpochMs + ", deviceId=" + this.deviceId + ", streamUrl=" + this.streamUrl + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ")";
    }

    public WatchProgress(String mediaId, long positionMs, long durationMs, long updatedAtEpochMs, String deviceId, String streamUrl, String addonId, String addonName) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        this.mediaId = mediaId;
        this.positionMs = positionMs;
        this.durationMs = durationMs;
        this.updatedAtEpochMs = updatedAtEpochMs;
        this.deviceId = deviceId;
        this.streamUrl = streamUrl;
        this.addonId = addonId;
        this.addonName = addonName;
    }

    public /* synthetic */ WatchProgress(String str, long j, long j2, long j3, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, j2, j3, str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5);
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final long getPositionMs() {
        return this.positionMs;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final long getUpdatedAtEpochMs() {
        return this.updatedAtEpochMs;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getStreamUrl() {
        return this.streamUrl;
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final float getFractionWatched() {
        if (this.durationMs > 0) {
            return ((float) this.positionMs) / ((float) this.durationMs);
        }
        return 0.0f;
    }
}
