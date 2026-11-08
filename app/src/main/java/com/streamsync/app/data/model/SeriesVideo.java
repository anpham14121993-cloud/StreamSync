package com.streamsync.app.data.model;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.detail.SeasonLabelsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003Jh\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0006HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0018\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011¨\u0006*"}, d2 = {"Lcom/streamsync/app/data/model/SeriesVideo;", "", "id", "", "title", "season", "", "episode", "released", "thumbnail", "overview", "order", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getEpisode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getId", "()Ljava/lang/String;", "label", "getLabel", "getOrder", "()I", "getOverview", "getReleased", "getSeason", "getThumbnail", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Lcom/streamsync/app/data/model/SeriesVideo;", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class SeriesVideo {
    public static final int $stable = 0;
    private final Integer episode;
    private final String id;
    private final int order;
    private final String overview;
    private final String released;
    private final Integer season;
    private final String thumbnail;
    private final String title;

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

    /* renamed from: component8, reason: from getter */
    public final int getOrder() {
        return this.order;
    }

    public final SeriesVideo copy(String id, String title, Integer season, Integer episode, String released, String thumbnail, String overview, int order) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        return new SeriesVideo(id, title, season, episode, released, thumbnail, overview, order);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeriesVideo)) {
            return false;
        }
        SeriesVideo seriesVideo = (SeriesVideo) other;
        return Intrinsics.areEqual(this.id, seriesVideo.id) && Intrinsics.areEqual(this.title, seriesVideo.title) && Intrinsics.areEqual(this.season, seriesVideo.season) && Intrinsics.areEqual(this.episode, seriesVideo.episode) && Intrinsics.areEqual(this.released, seriesVideo.released) && Intrinsics.areEqual(this.thumbnail, seriesVideo.thumbnail) && Intrinsics.areEqual(this.overview, seriesVideo.overview) && this.order == seriesVideo.order;
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode == null ? 0 : this.episode.hashCode())) * 31) + (this.released == null ? 0 : this.released.hashCode())) * 31) + (this.thumbnail == null ? 0 : this.thumbnail.hashCode())) * 31) + (this.overview != null ? this.overview.hashCode() : 0)) * 31) + Integer.hashCode(this.order);
    }

    public String toString() {
        return "SeriesVideo(id=" + this.id + ", title=" + this.title + ", season=" + this.season + ", episode=" + this.episode + ", released=" + this.released + ", thumbnail=" + this.thumbnail + ", overview=" + this.overview + ", order=" + this.order + ")";
    }

    public SeriesVideo(String id, String title, Integer season, Integer episode, String released, String thumbnail, String overview, int order) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.title = title;
        this.season = season;
        this.episode = episode;
        this.released = released;
        this.thumbnail = thumbnail;
        this.overview = overview;
        this.order = order;
    }

    public /* synthetic */ SeriesVideo(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, num, num2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : str5, (i2 & 128) != 0 ? 0 : i);
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

    public final int getOrder() {
        return this.order;
    }

    public final String getLabel() {
        if (this.season != null && this.episode != null) {
            return SeasonLabelsKt.seasonLabel(this.season.intValue()) + " · Tập " + this.episode;
        }
        if (this.episode != null) {
            return "Tập " + this.episode;
        }
        if (this.season != null) {
            return SeasonLabelsKt.seasonLabel(this.season.intValue());
        }
        if (!StringsKt.isBlank(this.title)) {
            return this.title;
        }
        return "Tập " + (this.order + 1);
    }
}
