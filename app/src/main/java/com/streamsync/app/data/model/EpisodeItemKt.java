package com.streamsync.app.data.model;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.detail.SeasonLabelsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: EpisodeItem.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\u0010\u0007\u001a\u0014\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\u0005\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"episodeDisplayTitle", "", "seriesName", "season", "", "episode", "episodeTitle", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Ljava/lang/String;", "toEpisodeItem", "Lcom/streamsync/app/data/model/MediaItem;", "Lcom/streamsync/app/data/model/SeriesVideo;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class EpisodeItemKt {
    public static final MediaItem toEpisodeItem(MediaItem $this$toEpisodeItem, SeriesVideo episode) {
        Intrinsics.checkNotNullParameter($this$toEpisodeItem, "<this>");
        Intrinsics.checkNotNullParameter(episode, "episode");
        String addonId = $this$toEpisodeItem.getAddonId();
        if (addonId == null) {
            addonId = "";
        }
        String str = addonId + ":" + episode.getId();
        String episodeDisplayTitle = episodeDisplayTitle($this$toEpisodeItem.getTitle(), episode.getSeason(), episode.getEpisode(), episode.getTitle());
        String thumbnail = episode.getThumbnail();
        if (thumbnail == null) {
            thumbnail = $this$toEpisodeItem.getPosterUrl();
        }
        return new MediaItem(str, episodeDisplayTitle, thumbnail, episode.getOverview(), MediaType.SERIES_EPISODE, null, null, $this$toEpisodeItem.getBackgroundUrl(), episode.getReleased(), null, null, false, null, null, null, null, null, null, null, null, null, $this$toEpisodeItem.getLogoUrl(), null, null, null, $this$toEpisodeItem.getAddonId(), $this$toEpisodeItem.getAddonName(), $this$toEpisodeItem.getCatalogId(), $this$toEpisodeItem.getCatalogName(), $this$toEpisodeItem.getCatalogType(), episode.getSeason(), episode.getEpisode(), null, 31456864, 1, null);
    }

    public static final String episodeDisplayTitle(String seriesName, Integer season, Integer episode, String episodeTitle) {
        return SeasonLabelsKt.episodePageTitle(StringsKt.trim((CharSequence) (seriesName == null ? "" : seriesName)).toString(), season, episode, null, episodeTitle);
    }
}
