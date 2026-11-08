package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EpisodeAutoLoad.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001e\u0010\t\u001a\u0004\u0018\u00010\b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/streamsync/app/ui/detail/EpisodeAutoLoad;", "", "()V", "find", "Lcom/streamsync/app/data/model/SeriesVideo;", "episodes", "", "episodeId", "", "idAt", "page", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class EpisodeAutoLoad {
    public static final int $stable = 0;
    public static final EpisodeAutoLoad INSTANCE = new EpisodeAutoLoad();

    private EpisodeAutoLoad() {
    }

    public final String idAt(List<SeriesVideo> episodes, int page) {
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        SeriesVideo at = EpisodePagerSafety.INSTANCE.at(episodes, page);
        if (at != null) {
            return at.getId();
        }
        return null;
    }

    public final SeriesVideo find(List<SeriesVideo> episodes, String episodeId) {
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Object obj = null;
        if (episodeId == null) {
            return null;
        }
        List<SeriesVideo> $this$firstOrNull$iv = episodes;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv = it.next();
            SeriesVideo it2 = (SeriesVideo) element$iv;
            if (Intrinsics.areEqual(it2.getId(), episodeId)) {
                obj = element$iv;
                break;
            }
        }
        return (SeriesVideo) obj;
    }
}
