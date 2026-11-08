package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jb\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0006HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0013\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006$"}, d2 = {"Lcom/streamsync/app/data/addon/AddonVideo;", "", "id", "", "title", "season", "", "episode", "released", "thumbnail", "overview", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEpisode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getId", "()Ljava/lang/String;", "getOverview", "getReleased", "getSeason", "getThumbnail", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/streamsync/app/data/addon/AddonVideo;", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonVideo {
    public static final int $stable = 0;
    private final Integer episode;
    private final String id;
    private final String overview;
    private final String released;
    private final Integer season;
    private final String thumbnail;
    private final String title;

    public AddonVideo() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ AddonVideo copy$default(AddonVideo addonVideo, String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonVideo.id;
        }
        if ((i & 2) != 0) {
            str2 = addonVideo.title;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            num = addonVideo.season;
        }
        Integer num3 = num;
        if ((i & 8) != 0) {
            num2 = addonVideo.episode;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            str3 = addonVideo.released;
        }
        String str7 = str3;
        if ((i & 32) != 0) {
            str4 = addonVideo.thumbnail;
        }
        String str8 = str4;
        if ((i & 64) != 0) {
            str5 = addonVideo.overview;
        }
        return addonVideo.copy(str, str6, num3, num4, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getReleased() {
        return this.released;
    }

    /* renamed from: component6, reason: from getter */
    public final String getThumbnail() {
        return this.thumbnail;
    }

    /* renamed from: component7, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    public final AddonVideo copy(String id, String title, Integer season, Integer episode, String released, String thumbnail, String overview) {
        return new AddonVideo(id, title, season, episode, released, thumbnail, overview);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonVideo)) {
            return false;
        }
        AddonVideo addonVideo = (AddonVideo) other;
        return Intrinsics.areEqual(this.id, addonVideo.id) && Intrinsics.areEqual(this.title, addonVideo.title) && Intrinsics.areEqual(this.season, addonVideo.season) && Intrinsics.areEqual(this.episode, addonVideo.episode) && Intrinsics.areEqual(this.released, addonVideo.released) && Intrinsics.areEqual(this.thumbnail, addonVideo.thumbnail) && Intrinsics.areEqual(this.overview, addonVideo.overview);
    }

    public int hashCode() {
        return ((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode == null ? 0 : this.episode.hashCode())) * 31) + (this.released == null ? 0 : this.released.hashCode())) * 31) + (this.thumbnail == null ? 0 : this.thumbnail.hashCode())) * 31) + (this.overview != null ? this.overview.hashCode() : 0);
    }

    public String toString() {
        return "AddonVideo(id=" + this.id + ", title=" + this.title + ", season=" + this.season + ", episode=" + this.episode + ", released=" + this.released + ", thumbnail=" + this.thumbnail + ", overview=" + this.overview + ")";
    }

    public AddonVideo(String id, String title, Integer season, Integer episode, String released, String thumbnail, String overview) {
        this.id = id;
        this.title = title;
        this.season = season;
        this.episode = episode;
        this.released = released;
        this.thumbnail = thumbnail;
        this.overview = overview;
    }

    public /* synthetic */ AddonVideo(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getReleased() {
        return this.released;
    }

    public final String getThumbnail() {
        return this.thumbnail;
    }

    public final String getOverview() {
        return this.overview;
    }
}
