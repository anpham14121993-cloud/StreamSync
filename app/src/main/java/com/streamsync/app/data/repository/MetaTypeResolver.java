package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MetaTypeResolver.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005J$\u0010\r\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000f\u001a\u00020\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/data/repository/MetaTypeResolver;", "", "()V", "KNOWN_TYPES", "", "", "SERIES_PREFIXES", "", "looksLikeEpisodeId", "", "rawId", "normalizeType", "type", "resolve", "requestedType", "knownItemIsSeries", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class MetaTypeResolver {
    public static final MetaTypeResolver INSTANCE = new MetaTypeResolver();
    private static final Set<String> KNOWN_TYPES = SetsKt.setOf((Object[]) new String[]{"movie", "series", "channel", "tv", "anime"});
    private static final List<String> SERIES_PREFIXES = CollectionsKt.listOf((Object[]) new String[]{"kitsu:", "mal:", "anilist:", "crunchyroll:", "hidive:", "otv:", "tmdb:", "videos_"});
    public static final int $stable = 8;

    private MetaTypeResolver() {
    }

    public final String normalizeType(String type) {
        String obj;
        if (type != null && (obj = StringsKt.trim((CharSequence) type).toString()) != null) {
            String value = obj.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(value, "toLowerCase(...)");
            if (value != null) {
                if (!(value.length() > 0)) {
                    value = null;
                }
                if (value != null && KNOWN_TYPES.contains(value)) {
                    return value;
                }
                return null;
            }
        }
        return null;
    }

    public final boolean looksLikeEpisodeId(String rawId) {
        String id;
        Integer intOrNull;
        if (rawId != null && (id = StringsKt.trim((CharSequence) rawId).toString()) != null) {
            if (!(id.length() > 0)) {
                id = null;
            }
            if (id != null) {
                List parts = StringsKt.split$default((CharSequence) id, new char[]{':'}, false, 0, 6, (Object) null);
                if (parts.size() < 3 || (intOrNull = StringsKt.toIntOrNull((String) parts.get(parts.size() - 2))) == null) {
                    return false;
                }
                int season = intOrNull.intValue();
                Integer intOrNull2 = StringsKt.toIntOrNull((String) parts.get(parts.size() - 1));
                if (intOrNull2 == null) {
                    return false;
                }
                int episode = intOrNull2.intValue();
                return season >= 0 && episode >= 0;
            }
        }
        return false;
    }

    public static /* synthetic */ String resolve$default(MetaTypeResolver metaTypeResolver, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return metaTypeResolver.resolve(str, str2, z);
    }

    public final String resolve(String rawId, String requestedType, boolean knownItemIsSeries) {
        String obj;
        String requested = normalizeType(requestedType);
        if (requested == null) {
            requested = "movie";
        }
        if (knownItemIsSeries || looksLikeEpisodeId(rawId)) {
            return "series";
        }
        if (rawId != null && (obj = StringsKt.trim((CharSequence) rawId).toString()) != null) {
            String id = obj.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(id, "toLowerCase(...)");
            if (id != null) {
                boolean z = true;
                if (!(id.length() > 0)) {
                    id = null;
                }
                if (id != null) {
                    Iterable $this$any$iv = SERIES_PREFIXES;
                    if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                        Iterator it = $this$any$iv.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object element$iv = it.next();
                                String it2 = (String) element$iv;
                                if (StringsKt.startsWith$default(id, it2, false, 2, (Object) null)) {
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                    } else {
                        z = false;
                    }
                    if (z) {
                        return "series";
                    }
                    return requested;
                }
            }
        }
        return requested;
    }
}
