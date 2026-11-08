package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.MediaType;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: SourcePrefetch.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/streamsync/app/ui/player/SourcePrefetch;", "", "()V", "shouldLoadSources", "", "itemType", "Lcom/streamsync/app/data/model/MediaType;", "showItemType", "showItemEpisodeCount", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class SourcePrefetch {
    public static final int $stable = 0;
    public static final SourcePrefetch INSTANCE = new SourcePrefetch();

    private SourcePrefetch() {
    }

    public final boolean shouldLoadSources(MediaType itemType, MediaType showItemType, int showItemEpisodeCount) {
        return itemType == MediaType.SERIES_EPISODE || itemType != MediaType.SERIES || showItemType != MediaType.SERIES || showItemEpisodeCount <= 0;
    }
}
