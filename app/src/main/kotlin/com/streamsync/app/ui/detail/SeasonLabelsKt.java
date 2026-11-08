package com.streamsync.app.ui.detail;

import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SeasonLabels.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\u0010\b\u001a)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\u0010\f\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a-\u0010\u000e\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\u0010\u000f\u001a\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011H\u0000¨\u0006\u0013"}, d2 = {"episodePageTitle", "", "showTitle", "season", "", "episode", "rawName", "fallbackTitle", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "isComposedEpisodeTitle", "", "title", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Z", "seasonLabel", "showTitleForLookup", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "sortSeasons", "", "seasons", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class SeasonLabelsKt {
    public static final String seasonLabel(int season) {
        return season == 0 ? "Tập đặc biệt" : "Mùa " + season;
    }

    public static final List<Integer> sortSeasons(List<Integer> seasons) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        List<Integer> $this$filter$iv = seasons;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            int it2 = ((Number) element$iv$iv).intValue();
            if (it2 != 0) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List sorted = CollectionsKt.sorted((List) destination$iv$iv);
        List<Integer> $this$filter$iv2 = seasons;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            int it3 = ((Number) element$iv$iv2).intValue();
            int it4 = it3 == 0 ? 1 : 0;
            if (it4 != 0) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        return CollectionsKt.plus((Collection) sorted, (Iterable) CollectionsKt.sorted((List) destination$iv$iv2));
    }

    public static final boolean isComposedEpisodeTitle(String title, Integer season, Integer episode) {
        Iterable $this$any$iv;
        Iterable $this$any$iv2;
        Intrinsics.checkNotNullParameter(title, "title");
        if (season == null || episode == null) {
            return false;
        }
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) title, new String[]{" - "}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable parts = (List) destination$iv$iv;
        Iterable $this$any$iv3 = parts;
        if (($this$any$iv3 instanceof Collection) && ((Collection) $this$any$iv3).isEmpty()) {
            $this$any$iv = null;
        } else {
            Iterator it2 = $this$any$iv3.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv = it2.next();
                    String it3 = (String) element$iv;
                    if (StringsKt.equals(it3, seasonLabel(season.intValue()), true)) {
                        $this$any$iv = 1;
                        break;
                    }
                } else {
                    $this$any$iv = null;
                    break;
                }
            }
        }
        Iterable $this$any$iv4 = parts;
        if (($this$any$iv4 instanceof Collection) && ((Collection) $this$any$iv4).isEmpty()) {
            $this$any$iv2 = null;
        } else {
            Iterator it4 = $this$any$iv4.iterator();
            while (true) {
                if (it4.hasNext()) {
                    Object element$iv2 = it4.next();
                    String it5 = (String) element$iv2;
                    if (StringsKt.equals(it5, "Tập " + episode, true)) {
                        $this$any$iv2 = 1;
                        break;
                    }
                } else {
                    $this$any$iv2 = null;
                    break;
                }
            }
        }
        return ($this$any$iv == null || $this$any$iv2 == null) ? false : true;
    }

    public static final String showTitleForLookup(String title, Integer season, Integer episode) {
        String raw = title != null ? StringsKt.trim((CharSequence) title).toString() : null;
        if (raw == null) {
            raw = "";
        }
        if ((raw.length() == 0) || (season == null && episode == null)) {
            return title;
        }
        List $this$indexOfFirst$iv = StringsKt.split$default((CharSequence) raw, new String[]{" - "}, false, 0, 6, (Object) null);
        int index$iv = 0;
        Iterator it = $this$indexOfFirst$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                String part = (String) item$iv;
                String p = StringsKt.trim((CharSequence) part).toString();
                if (StringsKt.equals(p, new StringBuilder().append("Tập ").append(episode).toString(), true) || (season != null && StringsKt.equals(p, seasonLabel(season.intValue()), true))) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int cut = index$iv;
        String name = cut <= 0 ? raw : StringsKt.trim((CharSequence) CollectionsKt.joinToString$default(CollectionsKt.take(StringsKt.split$default((CharSequence) raw, new String[]{" - "}, false, 0, 6, (Object) null), cut), " - ", null, null, 0, null, null, 62, null)).toString();
        String str = name;
        if (str.length() == 0) {
            str = title;
        }
        return str;
    }

    public static /* synthetic */ String episodePageTitle$default(String str, Integer num, Integer num2, String str2, String str3, int i, Object obj) {
        if ((i & 16) != 0) {
            str3 = null;
        }
        return episodePageTitle(str, num, num2, str2, str3);
    }

    public static final String episodePageTitle(String showTitle, Integer season, Integer episode, String rawName, String fallbackTitle) {
        String title;
        Intrinsics.checkNotNullParameter(showTitle, "showTitle");
        String name = StringsKt.trim((CharSequence) (rawName == null ? "" : rawName)).toString();
        boolean looksLikeId = season != null && episode != null && Intrinsics.areEqual(StringsKt.toIntOrNull(StringsKt.substringAfterLast$default(name, ':', (String) null, 2, (Object) null)), episode) && Intrinsics.areEqual(StringsKt.toIntOrNull(StringsKt.substringAfterLast$default(StringsKt.substringBeforeLast$default(name, ':', (String) null, 2, (Object) null), ':', (String) null, 2, (Object) null)), season);
        if (name.length() == 0) {
            title = StringsKt.trim((CharSequence) (fallbackTitle != null ? fallbackTitle : "")).toString();
        } else if (looksLikeId) {
            title = StringsKt.trim((CharSequence) (fallbackTitle != null ? fallbackTitle : "")).toString();
        } else {
            title = name;
        }
        List $this$episodePageTitle_u24lambda_u2411 = CollectionsKt.createListBuilder();
        String it = StringsKt.trim((CharSequence) showTitle).toString();
        String it2 = it.length() > 0 ? it : null;
        if (it2 != null) {
            $this$episodePageTitle_u24lambda_u2411.add(it2);
        }
        if (season != null) {
            $this$episodePageTitle_u24lambda_u2411.add(seasonLabel(season.intValue()));
        }
        if (episode != null) {
            $this$episodePageTitle_u24lambda_u2411.add("Tập " + episode.intValue());
        }
        if (title.length() > 0) {
            $this$episodePageTitle_u24lambda_u2411.add(title);
        }
        return CollectionsKt.joinToString$default(CollectionsKt.build($this$episodePageTitle_u24lambda_u2411), " - ", null, null, 0, null, null, 62, null);
    }
}
