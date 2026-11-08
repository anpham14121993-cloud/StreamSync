package com.streamsync.app.data.model;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002\u001a\u0014\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\u0007\u001a\u00020\tH\u0000¨\u0006\n"}, d2 = {"formatDuration", "", "seconds", "", "mergedType", "Lcom/streamsync/app/data/model/MediaType;", "mine", "other", "mergedWith", "Lcom/streamsync/app/data/model/MediaItem;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class MediaItemKt {
    public static final String formatDuration(long seconds) {
        long j = 3600;
        long h = seconds / j;
        long m = (seconds % j) / 60;
        return (h > 0 ? new StringBuilder().append(h).append("h ") : new StringBuilder()).append(m).append("m").toString();
    }

    private static final MediaType mergedType(MediaType mine, MediaType other) {
        if (mine == MediaType.SERIES_EPISODE || other == MediaType.SERIES_EPISODE) {
            return MediaType.SERIES_EPISODE;
        }
        if (mine == MediaType.SERIES || other == MediaType.SERIES) {
            return MediaType.SERIES;
        }
        return other;
    }

    private static final String mergedWith$str(String a, String b) {
        if (b != null) {
            String str = StringsKt.isBlank(b) ^ true ? b : null;
            if (str != null) {
                return str;
            }
        }
        return a;
    }

    private static final List<String> mergedWith$list(List<String> list, List<String> list2) {
        List<String> list3 = list2.isEmpty() ^ true ? list2 : null;
        return list3 == null ? list : list3;
    }

    public static final MediaItem mergedWith(MediaItem $this$mergedWith, MediaItem other) {
        MediaItem merged;
        Intrinsics.checkNotNullParameter($this$mergedWith, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        String title = other.getTitle();
        if (StringsKt.isBlank(title)) {
            title = $this$mergedWith.getTitle();
        }
        String str = title;
        String posterUrl = $this$mergedWith.getPosterUrl();
        if (posterUrl == null) {
            posterUrl = other.getPosterUrl();
        }
        String str2 = posterUrl;
        String description = $this$mergedWith.getDescription();
        if (description == null) {
            description = other.getDescription();
        }
        String str3 = description;
        MediaType mergedType = mergedType($this$mergedWith.getType(), other.getType());
        Integer year = $this$mergedWith.getYear();
        if (year == null) {
            year = other.getYear();
        }
        Integer num = year;
        Long durationSeconds = $this$mergedWith.getDurationSeconds();
        if (durationSeconds == null) {
            durationSeconds = other.getDurationSeconds();
        }
        Long l = durationSeconds;
        String backgroundUrl = $this$mergedWith.getBackgroundUrl();
        if (backgroundUrl == null) {
            backgroundUrl = other.getBackgroundUrl();
        }
        String str4 = backgroundUrl;
        String mergedWith$str = mergedWith$str($this$mergedWith.getReleaseInfo(), other.getReleaseInfo());
        String mergedWith$str2 = mergedWith$str($this$mergedWith.getImdbRating(), other.getImdbRating());
        List<String> mergedWith$list = mergedWith$list($this$mergedWith.getGenres(), other.getGenres());
        boolean z = $this$mergedWith.getGenresResolved() || other.getGenresResolved();
        List<String> mergedWith$list2 = mergedWith$list($this$mergedWith.getDirectors(), other.getDirectors());
        List<String> mergedWith$list3 = mergedWith$list($this$mergedWith.getCast(), other.getCast());
        List<CastMember> castPeople = other.getCastPeople().isEmpty() ^ true ? other.getCastPeople() : $this$mergedWith.getCastPeople();
        List<CastMember> writers = other.getWriters().isEmpty() ^ true ? other.getWriters() : $this$mergedWith.getWriters();
        String certification = $this$mergedWith.getCertification();
        if (certification == null) {
            certification = other.getCertification();
        }
        String str5 = certification;
        String mergedWith$str3 = mergedWith$str($this$mergedWith.getRuntimeText(), other.getRuntimeText());
        String mergedWith$str4 = mergedWith$str($this$mergedWith.getCountry(), other.getCountry());
        String mergedWith$str5 = mergedWith$str($this$mergedWith.getLanguage(), other.getLanguage());
        String mergedWith$str6 = mergedWith$str($this$mergedWith.getAwards(), other.getAwards());
        String logoUrl = $this$mergedWith.getLogoUrl();
        if (logoUrl == null) {
            logoUrl = other.getLogoUrl();
        }
        String str6 = logoUrl;
        String trailerId = $this$mergedWith.getTrailerId();
        if (trailerId == null) {
            trailerId = other.getTrailerId();
        }
        String str7 = trailerId;
        String released = $this$mergedWith.getReleased();
        if (released == null) {
            released = other.getReleased();
        }
        String str8 = released;
        List<String> mergedWith$list4 = mergedWith$list($this$mergedWith.getSimilarNames(), other.getSimilarNames());
        List<SeriesVideo> videos = other.getVideos().size() > $this$mergedWith.getVideos().size() ? other.getVideos() : $this$mergedWith.getVideos();
        Integer season = $this$mergedWith.getSeason();
        if (season == null) {
            season = other.getSeason();
        }
        Integer num2 = season;
        Integer episode = $this$mergedWith.getEpisode();
        if (episode == null) {
            episode = other.getEpisode();
        }
        merged = $this$mergedWith.copy((r51 & 1) != 0 ? $this$mergedWith.id : null, (r51 & 2) != 0 ? $this$mergedWith.title : str, (r51 & 4) != 0 ? $this$mergedWith.posterUrl : str2, (r51 & 8) != 0 ? $this$mergedWith.description : str3, (r51 & 16) != 0 ? $this$mergedWith.type : mergedType, (r51 & 32) != 0 ? $this$mergedWith.year : num, (r51 & 64) != 0 ? $this$mergedWith.durationSeconds : l, (r51 & 128) != 0 ? $this$mergedWith.backgroundUrl : str4, (r51 & 256) != 0 ? $this$mergedWith.releaseInfo : mergedWith$str, (r51 & 512) != 0 ? $this$mergedWith.imdbRating : mergedWith$str2, (r51 & 1024) != 0 ? $this$mergedWith.genres : mergedWith$list, (r51 & 2048) != 0 ? $this$mergedWith.genresResolved : z, (r51 & 4096) != 0 ? $this$mergedWith.directors : mergedWith$list2, (r51 & 8192) != 0 ? $this$mergedWith.cast : mergedWith$list3, (r51 & 16384) != 0 ? $this$mergedWith.castPeople : castPeople, (r51 & 32768) != 0 ? $this$mergedWith.writers : writers, (r51 & 65536) != 0 ? $this$mergedWith.certification : str5, (r51 & 131072) != 0 ? $this$mergedWith.runtimeText : mergedWith$str3, (r51 & 262144) != 0 ? $this$mergedWith.country : mergedWith$str4, (r51 & 524288) != 0 ? $this$mergedWith.language : mergedWith$str5, (r51 & 1048576) != 0 ? $this$mergedWith.awards : mergedWith$str6, (r51 & 2097152) != 0 ? $this$mergedWith.logoUrl : str6, (r51 & 4194304) != 0 ? $this$mergedWith.trailerId : str7, (r51 & 8388608) != 0 ? $this$mergedWith.released : str8, (r51 & 16777216) != 0 ? $this$mergedWith.similarNames : mergedWith$list4, (r51 & 33554432) != 0 ? $this$mergedWith.addonId : null, (r51 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? $this$mergedWith.addonName : null, (r51 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? $this$mergedWith.catalogId : null, (r51 & 268435456) != 0 ? $this$mergedWith.catalogName : null, (r51 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? $this$mergedWith.catalogType : null, (r51 & 1073741824) != 0 ? $this$mergedWith.season : num2, (r51 & Integer.MIN_VALUE) != 0 ? $this$mergedWith.episode : episode, (r52 & 1) != 0 ? $this$mergedWith.videos : videos);
        return Intrinsics.areEqual(merged, $this$mergedWith) ? $this$mergedWith : merged;
    }
}
