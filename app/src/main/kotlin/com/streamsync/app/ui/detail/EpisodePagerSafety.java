package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: EpisodePagerSafety.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bJ\u001c\u0010\f\u001a\u00020\r2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lcom/streamsync/app/ui/detail/EpisodePagerSafety;", "", "()V", "at", "Lcom/streamsync/app/data/model/SeriesVideo;", "episodes", "", "index", "", "clamp", "page", "size", "key", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class EpisodePagerSafety {
    public static final int $stable = 0;
    public static final EpisodePagerSafety INSTANCE = new EpisodePagerSafety();

    private EpisodePagerSafety() {
    }

    public final SeriesVideo at(List<SeriesVideo> episodes, int index) {
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        return (SeriesVideo) CollectionsKt.getOrNull(episodes, index);
    }

    public final String key(List<SeriesVideo> episodes, int index) {
        String id;
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        SeriesVideo seriesVideo = (SeriesVideo) CollectionsKt.getOrNull(episodes, index);
        return (seriesVideo == null || (id = seriesVideo.getId()) == null) ? "het-" + index : id;
    }

    public final int clamp(int page, int size) {
        if (size <= 0) {
            return 0;
        }
        return RangesKt.coerceIn(page, 0, size - 1);
    }
}
