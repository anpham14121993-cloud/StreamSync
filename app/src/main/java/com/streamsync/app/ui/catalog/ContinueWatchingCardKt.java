package com.streamsync.app.ui.catalog;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.MediaType;
import com.streamsync.app.data.model.WatchProgress;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.detail.SeasonLabelsKt;
import com.streamsync.app.ui.player.AddonIdParser;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: ContinueWatchingCard.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\u001a?\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001aG\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\b\b\u0002\u0010\f\u001a\u00020\r2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a\u0015\u0010\u0018\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010\u0019\u001a\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u0000\u001a\u0017\u0010\u001f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003¢\u0006\u0002\u0010 \u001a\f\u0010!\u001a\u00020\t*\u00020\u0007H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\""}, d2 = {"POSTER_HEIGHT", "Landroidx/compose/ui/unit/Dp;", "F", "POSTER_WIDTH", "ContinueWatchingCard", "", "item", "Lcom/streamsync/app/data/model/MediaItem;", "showTitle", "", NotificationCompat.CATEGORY_PROGRESS, "Lcom/streamsync/app/data/model/WatchProgress;", "modifier", "Landroidx/compose/ui/Modifier;", "onClick", "Lkotlin/Function0;", "(Lcom/streamsync/app/data/model/MediaItem;Ljava/lang/String;Lcom/streamsync/app/data/model/WatchProgress;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "ContinueWatchingPager", "items", "", "progressSnapshot", "onItemClick", "Lkotlin/Function1;", "(Ljava/util/List;Ljava/util/List;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ProgressBarWatched", "(Lcom/streamsync/app/data/model/WatchProgress;Landroidx/compose/runtime/Composer;I)V", "episodeSeasonEpisodeLine", "fallbackProgressOf", "formatWatchedDuration", "positionMs", "", "rememberShowTitle", "(Lcom/streamsync/app/data/model/MediaItem;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "episodeTitle", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class ContinueWatchingCardKt {
    private static final float POSTER_WIDTH = Dp.m5736constructorimpl(124);
    private static final float POSTER_HEIGHT = Dp.m5736constructorimpl(186);

    /* JADX WARN: Removed duplicated region for block: B:100:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0632  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x073d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0816  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x067d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0440  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContinueWatchingCard(final com.streamsync.app.data.model.MediaItem r109, final java.lang.String r110, final com.streamsync.app.data.model.WatchProgress r111, androidx.compose.ui.Modifier r112, final kotlin.jvm.functions.Function0<kotlin.Unit> r113, androidx.compose.runtime.Composer r114, final int r115, final int r116) {
        /*
            Method dump skipped, instructions count: 2105
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.ContinueWatchingCardKt.ContinueWatchingCard(com.streamsync.app.data.model.MediaItem, java.lang.String, com.streamsync.app.data.model.WatchProgress, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String episodeSeasonEpisodeLine(MediaItem item) {
        List $this$episodeSeasonEpisodeLine_u24lambda_u247 = CollectionsKt.createListBuilder();
        Integer season = item.getSeason();
        if (season != null) {
            int it = season.intValue();
            $this$episodeSeasonEpisodeLine_u24lambda_u247.add(SeasonLabelsKt.seasonLabel(it));
        }
        Integer episode = item.getEpisode();
        if (episode != null) {
            int it2 = episode.intValue();
            $this$episodeSeasonEpisodeLine_u24lambda_u247.add("Tập " + it2);
        }
        return CollectionsKt.joinToString$default(CollectionsKt.build($this$episodeSeasonEpisodeLine_u24lambda_u247), " - ", null, null, 0, null, null, 62, null);
    }

    private static final String episodeTitle(MediaItem $this$episodeTitle) {
        String tail;
        List parts = StringsKt.split$default((CharSequence) $this$episodeTitle.getTitle(), new String[]{" - "}, false, 0, 6, (Object) null);
        if (parts.size() > 3) {
            tail = CollectionsKt.joinToString$default(CollectionsKt.drop(parts, 3), " - ", null, null, 0, null, null, 62, null);
        } else {
            tail = (String) CollectionsKt.lastOrNull(parts);
            if (tail == null) {
                tail = "";
            }
        }
        String str = tail;
        if (StringsKt.isBlank(str)) {
            str = $this$episodeTitle.getTitle();
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ProgressBarWatched(final com.streamsync.app.data.model.WatchProgress r36, androidx.compose.runtime.Composer r37, final int r38) {
        /*
            Method dump skipped, instructions count: 512
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.ContinueWatchingCardKt.ProgressBarWatched(com.streamsync.app.data.model.WatchProgress, androidx.compose.runtime.Composer, int):void");
    }

    public static final String formatWatchedDuration(long positionMs) {
        long totalSeconds = RangesKt.coerceAtLeast(positionMs / 1000, 0L);
        long j = 3600;
        long h = totalSeconds / j;
        long j2 = 60;
        long m = (totalSeconds % j) / j2;
        long s = totalSeconds % j2;
        String format = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(h), Long.valueOf(m), Long.valueOf(s)}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x02fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContinueWatchingPager(final java.util.List<com.streamsync.app.data.model.MediaItem> r48, final java.util.List<com.streamsync.app.data.model.WatchProgress> r49, androidx.compose.ui.Modifier r50, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instructions count: 794
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.ContinueWatchingCardKt.ContinueWatchingPager(java.util.List, java.util.List, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WatchProgress fallbackProgressOf(MediaItem item) {
        String id = item.getId();
        Long durationSeconds = item.getDurationSeconds();
        return new WatchProgress(id, 0L, 1000 * (durationSeconds != null ? durationSeconds.longValue() : 0L), 0L, "", null, null, null, 224, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String rememberShowTitle(MediaItem item, Composer $composer, int $changed) {
        String showId;
        Object value$iv;
        $composer.startReplaceableGroup(962124990);
        ComposerKt.sourceInformation($composer, "C(rememberShowTitle)342@13328L280:ContinueWatchingCard.kt#aadnbi");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(962124990, $changed, -1, "com.streamsync.app.ui.catalog.rememberShowTitle (ContinueWatchingCard.kt:342)");
        }
        String id = item.getId();
        $composer.startReplaceableGroup(533343146);
        ComposerKt.sourceInformation($composer, "CC(remember):ContinueWatchingCard.kt#9igjgp");
        boolean invalid$iv = $composer.changed(id);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            if (item.getType() != MediaType.SERIES_EPISODE) {
                showId = item.getTitle();
            } else {
                String showId2 = AddonIdParser.INSTANCE.showIdOfEpisode(item.getId());
                if (showId2 == null) {
                    showId = null;
                } else {
                    MediaItem mediaItem = MediaItemCache.INSTANCE.get(showId2);
                    showId = mediaItem != null ? mediaItem.getTitle() : null;
                }
            }
            value$iv = showId;
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        String str = (String) value$iv;
        $composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return str;
    }
}
