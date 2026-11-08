package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AddonIdParser.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lcom/streamsync/app/ui/player/AddonIdParser;", "", "()V", "isEpisodeId", "", "id", "", "seasonEpisodeOf", "Lkotlin/Pair;", "", "showIdOfEpisode", "episodeId", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class AddonIdParser {
    public static final int $stable = 0;
    public static final AddonIdParser INSTANCE = new AddonIdParser();

    private AddonIdParser() {
    }

    public final String showIdOfEpisode(String episodeId) {
        Intrinsics.checkNotNullParameter(episodeId, "episodeId");
        List parts = StringsKt.split$default((CharSequence) episodeId, new char[]{':'}, false, 0, 6, (Object) null);
        if (parts.size() < 4) {
            return null;
        }
        String season = StringsKt.trim((CharSequence) parts.get(parts.size() - 2)).toString();
        String episode = StringsKt.trim((CharSequence) parts.get(parts.size() - 1)).toString();
        if (StringsKt.toIntOrNull(season) == null || StringsKt.toIntOrNull(episode) == null) {
            return null;
        }
        String addon = StringsKt.trim((CharSequence) CollectionsKt.first(parts)).toString();
        String show = StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(parts.subList(1, parts.size() - 2), ":", null, null, 0, null, null, 62, null)).toString();
        if (!(addon.length() == 0)) {
            if (!(show.length() == 0)) {
                return addon + ":" + show;
            }
        }
        return null;
    }

    public final Pair<Integer, Integer> seasonEpisodeOf(String id) {
        Integer intOrNull;
        Intrinsics.checkNotNullParameter(id, "id");
        List parts = StringsKt.split$default((CharSequence) id, new char[]{':'}, false, 0, 6, (Object) null);
        if (parts.size() < 4 || (intOrNull = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) parts.get(parts.size() - 2)).toString())) == null) {
            return null;
        }
        int season = intOrNull.intValue();
        Integer intOrNull2 = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) parts.get(parts.size() - 1)).toString());
        if (intOrNull2 == null) {
            return null;
        }
        int episode = intOrNull2.intValue();
        return TuplesKt.to(Integer.valueOf(season), Integer.valueOf(episode));
    }

    public final boolean isEpisodeId(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return seasonEpisodeOf(id) != null;
    }
}
