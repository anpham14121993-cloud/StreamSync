package com.streamsync.app.data.local;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LibraryItemEntity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010%\u001a\u00020\u0010HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u0094\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\rHÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u00065"}, d2 = {"Lcom/streamsync/app/data/local/LibraryItemEntity;", "", "mediaId", "", "title", "posterUrl", "backgroundUrl", "description", "type", "releaseInfo", "imdbRating", "genresJson", "season", "", "episode", "addedAtEpochMs", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;J)V", "getAddedAtEpochMs", "()J", "getBackgroundUrl", "()Ljava/lang/String;", "getDescription", "getEpisode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGenresJson", "getImdbRating", "getMediaId", "getPosterUrl", "getReleaseInfo", "getSeason", "getTitle", "getType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;J)Lcom/streamsync/app/data/local/LibraryItemEntity;", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final /* data */ class LibraryItemEntity {
    public static final int $stable = 0;
    private final long addedAtEpochMs;
    private final String backgroundUrl;
    private final String description;
    private final Integer episode;
    private final String genresJson;
    private final String imdbRating;
    private final String mediaId;
    private final String posterUrl;
    private final String releaseInfo;
    private final Integer season;
    private final String title;
    private final String type;

    /* renamed from: component1, reason: from getter */
    public final String getMediaId() {
        return this.mediaId;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* renamed from: component11, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* renamed from: component12, reason: from getter */
    public final long getAddedAtEpochMs() {
        return this.addedAtEpochMs;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBackgroundUrl() {
        return this.backgroundUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component6, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component7, reason: from getter */
    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    /* renamed from: component8, reason: from getter */
    public final String getImdbRating() {
        return this.imdbRating;
    }

    /* renamed from: component9, reason: from getter */
    public final String getGenresJson() {
        return this.genresJson;
    }

    public final LibraryItemEntity copy(String mediaId, String title, String posterUrl, String backgroundUrl, String description, String type, String releaseInfo, String imdbRating, String genresJson, Integer season, Integer episode, long addedAtEpochMs) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(genresJson, "genresJson");
        return new LibraryItemEntity(mediaId, title, posterUrl, backgroundUrl, description, type, releaseInfo, imdbRating, genresJson, season, episode, addedAtEpochMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryItemEntity)) {
            return false;
        }
        LibraryItemEntity libraryItemEntity = (LibraryItemEntity) other;
        return Intrinsics.areEqual(this.mediaId, libraryItemEntity.mediaId) && Intrinsics.areEqual(this.title, libraryItemEntity.title) && Intrinsics.areEqual(this.posterUrl, libraryItemEntity.posterUrl) && Intrinsics.areEqual(this.backgroundUrl, libraryItemEntity.backgroundUrl) && Intrinsics.areEqual(this.description, libraryItemEntity.description) && Intrinsics.areEqual(this.type, libraryItemEntity.type) && Intrinsics.areEqual(this.releaseInfo, libraryItemEntity.releaseInfo) && Intrinsics.areEqual(this.imdbRating, libraryItemEntity.imdbRating) && Intrinsics.areEqual(this.genresJson, libraryItemEntity.genresJson) && Intrinsics.areEqual(this.season, libraryItemEntity.season) && Intrinsics.areEqual(this.episode, libraryItemEntity.episode) && this.addedAtEpochMs == libraryItemEntity.addedAtEpochMs;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.mediaId.hashCode() * 31) + this.title.hashCode()) * 31) + (this.posterUrl == null ? 0 : this.posterUrl.hashCode())) * 31) + (this.backgroundUrl == null ? 0 : this.backgroundUrl.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + this.type.hashCode()) * 31) + (this.releaseInfo == null ? 0 : this.releaseInfo.hashCode())) * 31) + (this.imdbRating == null ? 0 : this.imdbRating.hashCode())) * 31) + this.genresJson.hashCode()) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode != null ? this.episode.hashCode() : 0)) * 31) + Long.hashCode(this.addedAtEpochMs);
    }

    public String toString() {
        return "LibraryItemEntity(mediaId=" + this.mediaId + ", title=" + this.title + ", posterUrl=" + this.posterUrl + ", backgroundUrl=" + this.backgroundUrl + ", description=" + this.description + ", type=" + this.type + ", releaseInfo=" + this.releaseInfo + ", imdbRating=" + this.imdbRating + ", genresJson=" + this.genresJson + ", season=" + this.season + ", episode=" + this.episode + ", addedAtEpochMs=" + this.addedAtEpochMs + ")";
    }

    public LibraryItemEntity(String mediaId, String title, String posterUrl, String backgroundUrl, String description, String type, String releaseInfo, String imdbRating, String genresJson, Integer season, Integer episode, long addedAtEpochMs) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(genresJson, "genresJson");
        this.mediaId = mediaId;
        this.title = title;
        this.posterUrl = posterUrl;
        this.backgroundUrl = backgroundUrl;
        this.description = description;
        this.type = type;
        this.releaseInfo = releaseInfo;
        this.imdbRating = imdbRating;
        this.genresJson = genresJson;
        this.season = season;
        this.episode = episode;
        this.addedAtEpochMs = addedAtEpochMs;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getPosterUrl() {
        return this.posterUrl;
    }

    public final String getBackgroundUrl() {
        return this.backgroundUrl;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getType() {
        return this.type;
    }

    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final String getGenresJson() {
        return this.genresJson;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final long getAddedAtEpochMs() {
        return this.addedAtEpochMs;
    }
}
