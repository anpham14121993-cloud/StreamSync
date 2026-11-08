package com.streamsync.app.data.model;

import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\\\b\u0087\b\u0018\u00002\u00020\u0001B\u0099\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010&\u001a\u00020\u0003\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\n\u0012\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u0011¢\u0006\u0002\u0010+J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\t\u0010`\u001a\u00020\u0013HÆ\u0003J\u000f\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u000f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u000f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011HÆ\u0003J\u000f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010h\u001a\u00020\u0003HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010t\u001a\u00020\u0003HÆ\u0003J\u0010\u0010u\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010AJ\u0010\u0010v\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010AJ\u000f\u0010w\u001a\b\u0012\u0004\u0012\u00020*0\u0011HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010y\u001a\u00020\bHÆ\u0003J\u0010\u0010z\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010AJ\u0010\u0010{\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010>J\u000b\u0010|\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¬\u0003\u0010~\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00112\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u0011HÆ\u0001¢\u0006\u0002\u0010\u007fJ\u001d\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020*0\u00112\b\u0010'\u001a\u0004\u0018\u00010\n¢\u0006\u0003\u0010\u0081\u0001J\u0015\u0010\u0082\u0001\u001a\u00020\u00132\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010\u0084\u0001\u001a\u00020\nHÖ\u0001J\n\u0010\u0085\u0001\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010#\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010-R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010-R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011¢\u0006\b\n\u0000\u001a\u0004\b3\u00102R\u0013\u0010$\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010-R\u0011\u00105\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b6\u0010-R\u0013\u0010%\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010-R\u0011\u0010&\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010-R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010-R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010-R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010-R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b<\u00102R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010?\u001a\u0004\b=\u0010>R\u0015\u0010(\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010B\u001a\u0004\b@\u0010AR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\bC\u00102R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bF\u0010-R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bG\u0010-R\u0011\u0010H\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\bH\u0010ER\u0011\u0010I\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\bI\u0010ER\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010-R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bK\u0010-R\u0011\u0010L\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bM\u0010-R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bN\u0010-R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bO\u0010-R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bP\u0010-R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010-R\u0015\u0010'\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010B\u001a\u0004\bR\u0010AR\u0017\u0010S\u001a\b\u0012\u0004\u0012\u00020\n0\u00118F¢\u0006\u0006\u001a\u0004\bT\u00102R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\bU\u00102R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bV\u0010-R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bW\u0010-R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u0011¢\u0006\b\n\u0000\u001a\u0004\bZ\u00102R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011¢\u0006\b\n\u0000\u001a\u0004\b[\u00102R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010B\u001a\u0004\b\\\u0010A¨\u0006\u0086\u0001"}, d2 = {"Lcom/streamsync/app/data/model/MediaItem;", "", "id", "", "title", "posterUrl", "description", "type", "Lcom/streamsync/app/data/model/MediaType;", "year", "", "durationSeconds", "", "backgroundUrl", "releaseInfo", "imdbRating", "genres", "", "genresResolved", "", "directors", "cast", "castPeople", "Lcom/streamsync/app/data/model/CastMember;", "writers", "certification", "runtimeText", "country", "language", "awards", "logoUrl", "trailerId", "released", "similarNames", "addonId", "addonName", "catalogId", "catalogName", "catalogType", "season", "episode", "videos", "Lcom/streamsync/app/data/model/SeriesVideo;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/data/model/MediaType;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)V", "getAddonId", "()Ljava/lang/String;", "getAddonName", "getAwards", "getBackgroundUrl", "getCast", "()Ljava/util/List;", "getCastPeople", "getCatalogId", "catalogKey", "getCatalogKey", "getCatalogName", "getCatalogType", "getCertification", "getCountry", "getDescription", "getDirectors", "getDurationSeconds", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEpisode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGenres", "getGenresResolved", "()Z", "getId", "getImdbRating", "isFromAddon", "isSeries", "getLanguage", "getLogoUrl", "metaLine", "getMetaLine", "getPosterUrl", "getReleaseInfo", "getReleased", "getRuntimeText", "getSeason", "seasons", "getSeasons", "getSimilarNames", "getTitle", "getTrailerId", "getType", "()Lcom/streamsync/app/data/model/MediaType;", "getVideos", "getWriters", "getYear", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/data/model/MediaType;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)Lcom/streamsync/app/data/model/MediaItem;", "episodesOf", "(Ljava/lang/Integer;)Ljava/util/List;", "equals", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class MediaItem {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonName;
    private final String awards;
    private final String backgroundUrl;
    private final List<String> cast;
    private final List<CastMember> castPeople;
    private final String catalogId;
    private final String catalogName;
    private final String catalogType;
    private final String certification;
    private final String country;
    private final String description;
    private final List<String> directors;
    private final Long durationSeconds;
    private final Integer episode;
    private final List<String> genres;
    private final boolean genresResolved;
    private final String id;
    private final String imdbRating;
    private final String language;
    private final String logoUrl;
    private final String posterUrl;
    private final String releaseInfo;
    private final String released;
    private final String runtimeText;
    private final Integer season;
    private final List<String> similarNames;
    private final String title;
    private final String trailerId;
    private final MediaType type;
    private final List<SeriesVideo> videos;
    private final List<CastMember> writers;
    private final Integer year;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final List<String> component11() {
        return this.genres;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getGenresResolved() {
        return this.genresResolved;
    }

    public final List<String> component13() {
        return this.directors;
    }

    public final List<String> component14() {
        return this.cast;
    }

    public final List<CastMember> component15() {
        return this.castPeople;
    }

    public final List<CastMember> component16() {
        return this.writers;
    }

    /* renamed from: component17, reason: from getter */
    public final String getCertification() {
        return this.certification;
    }

    /* renamed from: component18, reason: from getter */
    public final String getRuntimeText() {
        return this.runtimeText;
    }

    /* renamed from: component19, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component20, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* renamed from: component21, reason: from getter */
    public final String getAwards() {
        return this.awards;
    }

    /* renamed from: component22, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* renamed from: component23, reason: from getter */
    public final String getTrailerId() {
        return this.trailerId;
    }

    /* renamed from: component24, reason: from getter */
    public final String getReleased() {
        return this.released;
    }

    public final List<String> component25() {
        return this.similarNames;
    }

    /* renamed from: component26, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* renamed from: component27, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* renamed from: component28, reason: from getter */
    public final String getCatalogId() {
        return this.catalogId;
    }

    /* renamed from: component29, reason: from getter */
    public final String getCatalogName() {
        return this.catalogName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* renamed from: component30, reason: from getter */
    public final String getCatalogType() {
        return this.catalogType;
    }

    /* renamed from: component31, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* renamed from: component32, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    public final List<SeriesVideo> component33() {
        return this.videos;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component5, reason: from getter */
    public final MediaType getType() {
        return this.type;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    /* renamed from: component7, reason: from getter */
    public final Long getDurationSeconds() {
        return this.durationSeconds;
    }

    /* renamed from: component8, reason: from getter */
    public final String getBackgroundUrl() {
        return this.backgroundUrl;
    }

    /* renamed from: component9, reason: from getter */
    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    public final MediaItem copy(String id, String title, String posterUrl, String description, MediaType type, Integer year, Long durationSeconds, String backgroundUrl, String releaseInfo, String imdbRating, List<String> genres, boolean genresResolved, List<String> directors, List<String> cast, List<CastMember> castPeople, List<CastMember> writers, String certification, String runtimeText, String country, String language, String awards, String logoUrl, String trailerId, String released, List<String> similarNames, String addonId, String addonName, String catalogId, String catalogName, String catalogType, Integer season, Integer episode, List<SeriesVideo> videos) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(genres, "genres");
        Intrinsics.checkNotNullParameter(directors, "directors");
        Intrinsics.checkNotNullParameter(cast, "cast");
        Intrinsics.checkNotNullParameter(castPeople, "castPeople");
        Intrinsics.checkNotNullParameter(writers, "writers");
        Intrinsics.checkNotNullParameter(similarNames, "similarNames");
        Intrinsics.checkNotNullParameter(catalogType, "catalogType");
        Intrinsics.checkNotNullParameter(videos, "videos");
        return new MediaItem(id, title, posterUrl, description, type, year, durationSeconds, backgroundUrl, releaseInfo, imdbRating, genres, genresResolved, directors, cast, castPeople, writers, certification, runtimeText, country, language, awards, logoUrl, trailerId, released, similarNames, addonId, addonName, catalogId, catalogName, catalogType, season, episode, videos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) other;
        return Intrinsics.areEqual(this.id, mediaItem.id) && Intrinsics.areEqual(this.title, mediaItem.title) && Intrinsics.areEqual(this.posterUrl, mediaItem.posterUrl) && Intrinsics.areEqual(this.description, mediaItem.description) && this.type == mediaItem.type && Intrinsics.areEqual(this.year, mediaItem.year) && Intrinsics.areEqual(this.durationSeconds, mediaItem.durationSeconds) && Intrinsics.areEqual(this.backgroundUrl, mediaItem.backgroundUrl) && Intrinsics.areEqual(this.releaseInfo, mediaItem.releaseInfo) && Intrinsics.areEqual(this.imdbRating, mediaItem.imdbRating) && Intrinsics.areEqual(this.genres, mediaItem.genres) && this.genresResolved == mediaItem.genresResolved && Intrinsics.areEqual(this.directors, mediaItem.directors) && Intrinsics.areEqual(this.cast, mediaItem.cast) && Intrinsics.areEqual(this.castPeople, mediaItem.castPeople) && Intrinsics.areEqual(this.writers, mediaItem.writers) && Intrinsics.areEqual(this.certification, mediaItem.certification) && Intrinsics.areEqual(this.runtimeText, mediaItem.runtimeText) && Intrinsics.areEqual(this.country, mediaItem.country) && Intrinsics.areEqual(this.language, mediaItem.language) && Intrinsics.areEqual(this.awards, mediaItem.awards) && Intrinsics.areEqual(this.logoUrl, mediaItem.logoUrl) && Intrinsics.areEqual(this.trailerId, mediaItem.trailerId) && Intrinsics.areEqual(this.released, mediaItem.released) && Intrinsics.areEqual(this.similarNames, mediaItem.similarNames) && Intrinsics.areEqual(this.addonId, mediaItem.addonId) && Intrinsics.areEqual(this.addonName, mediaItem.addonName) && Intrinsics.areEqual(this.catalogId, mediaItem.catalogId) && Intrinsics.areEqual(this.catalogName, mediaItem.catalogName) && Intrinsics.areEqual(this.catalogType, mediaItem.catalogType) && Intrinsics.areEqual(this.season, mediaItem.season) && Intrinsics.areEqual(this.episode, mediaItem.episode) && Intrinsics.areEqual(this.videos, mediaItem.videos);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + (this.posterUrl == null ? 0 : this.posterUrl.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + this.type.hashCode()) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.durationSeconds == null ? 0 : this.durationSeconds.hashCode())) * 31) + (this.backgroundUrl == null ? 0 : this.backgroundUrl.hashCode())) * 31) + (this.releaseInfo == null ? 0 : this.releaseInfo.hashCode())) * 31) + (this.imdbRating == null ? 0 : this.imdbRating.hashCode())) * 31) + this.genres.hashCode()) * 31) + Boolean.hashCode(this.genresResolved)) * 31) + this.directors.hashCode()) * 31) + this.cast.hashCode()) * 31) + this.castPeople.hashCode()) * 31) + this.writers.hashCode()) * 31) + (this.certification == null ? 0 : this.certification.hashCode())) * 31) + (this.runtimeText == null ? 0 : this.runtimeText.hashCode())) * 31) + (this.country == null ? 0 : this.country.hashCode())) * 31) + (this.language == null ? 0 : this.language.hashCode())) * 31) + (this.awards == null ? 0 : this.awards.hashCode())) * 31) + (this.logoUrl == null ? 0 : this.logoUrl.hashCode())) * 31) + (this.trailerId == null ? 0 : this.trailerId.hashCode())) * 31) + (this.released == null ? 0 : this.released.hashCode())) * 31) + this.similarNames.hashCode()) * 31) + (this.addonId == null ? 0 : this.addonId.hashCode())) * 31) + (this.addonName == null ? 0 : this.addonName.hashCode())) * 31) + (this.catalogId == null ? 0 : this.catalogId.hashCode())) * 31) + (this.catalogName == null ? 0 : this.catalogName.hashCode())) * 31) + this.catalogType.hashCode()) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode != null ? this.episode.hashCode() : 0)) * 31) + this.videos.hashCode();
    }

    public String toString() {
        return "MediaItem(id=" + this.id + ", title=" + this.title + ", posterUrl=" + this.posterUrl + ", description=" + this.description + ", type=" + this.type + ", year=" + this.year + ", durationSeconds=" + this.durationSeconds + ", backgroundUrl=" + this.backgroundUrl + ", releaseInfo=" + this.releaseInfo + ", imdbRating=" + this.imdbRating + ", genres=" + this.genres + ", genresResolved=" + this.genresResolved + ", directors=" + this.directors + ", cast=" + this.cast + ", castPeople=" + this.castPeople + ", writers=" + this.writers + ", certification=" + this.certification + ", runtimeText=" + this.runtimeText + ", country=" + this.country + ", language=" + this.language + ", awards=" + this.awards + ", logoUrl=" + this.logoUrl + ", trailerId=" + this.trailerId + ", released=" + this.released + ", similarNames=" + this.similarNames + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ", catalogId=" + this.catalogId + ", catalogName=" + this.catalogName + ", catalogType=" + this.catalogType + ", season=" + this.season + ", episode=" + this.episode + ", videos=" + this.videos + ")";
    }

    public MediaItem(String id, String title, String posterUrl, String description, MediaType type, Integer year, Long durationSeconds, String backgroundUrl, String releaseInfo, String imdbRating, List<String> genres, boolean genresResolved, List<String> directors, List<String> cast, List<CastMember> castPeople, List<CastMember> writers, String certification, String runtimeText, String country, String language, String awards, String logoUrl, String trailerId, String released, List<String> similarNames, String addonId, String addonName, String catalogId, String catalogName, String catalogType, Integer season, Integer episode, List<SeriesVideo> videos) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(genres, "genres");
        Intrinsics.checkNotNullParameter(directors, "directors");
        Intrinsics.checkNotNullParameter(cast, "cast");
        Intrinsics.checkNotNullParameter(castPeople, "castPeople");
        Intrinsics.checkNotNullParameter(writers, "writers");
        Intrinsics.checkNotNullParameter(similarNames, "similarNames");
        Intrinsics.checkNotNullParameter(catalogType, "catalogType");
        Intrinsics.checkNotNullParameter(videos, "videos");
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.description = description;
        this.type = type;
        this.year = year;
        this.durationSeconds = durationSeconds;
        this.backgroundUrl = backgroundUrl;
        this.releaseInfo = releaseInfo;
        this.imdbRating = imdbRating;
        this.genres = genres;
        this.genresResolved = genresResolved;
        this.directors = directors;
        this.cast = cast;
        this.castPeople = castPeople;
        this.writers = writers;
        this.certification = certification;
        this.runtimeText = runtimeText;
        this.country = country;
        this.language = language;
        this.awards = awards;
        this.logoUrl = logoUrl;
        this.trailerId = trailerId;
        this.released = released;
        this.similarNames = similarNames;
        this.addonId = addonId;
        this.addonName = addonName;
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.catalogType = catalogType;
        this.season = season;
        this.episode = episode;
        this.videos = videos;
    }

    public /* synthetic */ MediaItem(String str, String str2, String str3, String str4, MediaType mediaType, Integer num, Long l, String str5, String str6, String str7, List list, boolean z, List list2, List list3, List list4, List list5, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, List list6, String str16, String str17, String str18, String str19, String str20, Integer num2, Integer num3, List list7, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, mediaType, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : l, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? CollectionsKt.emptyList() : list, (i & 2048) != 0 ? false : z, (i & 4096) != 0 ? CollectionsKt.emptyList() : list2, (i & 8192) != 0 ? CollectionsKt.emptyList() : list3, (i & 16384) != 0 ? CollectionsKt.emptyList() : list4, (32768 & i) != 0 ? CollectionsKt.emptyList() : list5, (65536 & i) != 0 ? null : str8, (131072 & i) != 0 ? null : str9, (262144 & i) != 0 ? null : str10, (524288 & i) != 0 ? null : str11, (1048576 & i) != 0 ? null : str12, (2097152 & i) != 0 ? null : str13, (4194304 & i) != 0 ? null : str14, (8388608 & i) != 0 ? null : str15, (16777216 & i) != 0 ? CollectionsKt.emptyList() : list6, (33554432 & i) != 0 ? null : str16, (67108864 & i) != 0 ? null : str17, (134217728 & i) != 0 ? null : str18, (268435456 & i) != 0 ? null : str19, (536870912 & i) != 0 ? "movie" : str20, (1073741824 & i) != 0 ? null : num2, (i & Integer.MIN_VALUE) != 0 ? null : num3, (i2 & 1) != 0 ? CollectionsKt.emptyList() : list7);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getPosterUrl() {
        return this.posterUrl;
    }

    public final String getDescription() {
        return this.description;
    }

    public final MediaType getType() {
        return this.type;
    }

    public final Integer getYear() {
        return this.year;
    }

    public final Long getDurationSeconds() {
        return this.durationSeconds;
    }

    public final String getBackgroundUrl() {
        return this.backgroundUrl;
    }

    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final List<String> getGenres() {
        return this.genres;
    }

    public final boolean getGenresResolved() {
        return this.genresResolved;
    }

    public final List<String> getDirectors() {
        return this.directors;
    }

    public final List<String> getCast() {
        return this.cast;
    }

    public final List<CastMember> getCastPeople() {
        return this.castPeople;
    }

    public final List<CastMember> getWriters() {
        return this.writers;
    }

    public final String getCertification() {
        return this.certification;
    }

    public final String getRuntimeText() {
        return this.runtimeText;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getAwards() {
        return this.awards;
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final String getTrailerId() {
        return this.trailerId;
    }

    public final String getReleased() {
        return this.released;
    }

    public final List<String> getSimilarNames() {
        return this.similarNames;
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final String getCatalogId() {
        return this.catalogId;
    }

    public final String getCatalogName() {
        return this.catalogName;
    }

    public final String getCatalogType() {
        return this.catalogType;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final List<SeriesVideo> getVideos() {
        return this.videos;
    }

    public final boolean isSeries() {
        return this.type == MediaType.SERIES;
    }

    public final List<Integer> getSeasons() {
        Iterable $this$mapNotNull$iv = this.videos;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            SeriesVideo it = (SeriesVideo) element$iv$iv$iv;
            Integer season = it.getSeason();
            if (season != null) {
                destination$iv$iv.add(season);
            }
        }
        return CollectionsKt.sorted(CollectionsKt.distinct((List) destination$iv$iv));
    }

    public final List<SeriesVideo> episodesOf(Integer season) {
        Iterable $this$filter$iv = this.videos;
        Collection destination$iv$iv = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                return CollectionsKt.sortedWith((List) destination$iv$iv, ComparisonsKt.compareBy(new Function1<SeriesVideo, Comparable<?>>() { // from class: com.streamsync.app.data.model.MediaItem$episodesOf$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Comparable<?> invoke(SeriesVideo it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        return it2.getSeason();
                    }
                }, new Function1<SeriesVideo, Comparable<?>>() { // from class: com.streamsync.app.data.model.MediaItem$episodesOf$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Comparable<?> invoke(SeriesVideo it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        return it2.getEpisode();
                    }
                }));
            }
            Object element$iv$iv = it.next();
            SeriesVideo it2 = (SeriesVideo) element$iv$iv;
            if (season != null && !Intrinsics.areEqual(it2.getSeason(), season)) {
                z = false;
            }
            if (z) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
    }

    public final boolean isFromAddon() {
        return StringsKt.contains$default((CharSequence) this.id, ':', false, 2, (Object) null);
    }

    public final String getCatalogKey() {
        return CatalogKey.INSTANCE.of(this.addonId, this.catalogType, this.catalogId);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getMetaLine() {
        /*
            r14 = this;
            r0 = 4
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = r14.releaseInfo
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L16
            r4 = r1
            r5 = 0
            r6 = r4
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)
            r6 = r6 ^ r2
            if (r6 == 0) goto L16
            goto L17
        L16:
            r1 = r3
        L17:
            r4 = 0
            r0[r4] = r1
            java.lang.String r1 = r14.imdbRating
            if (r1 == 0) goto L44
            r4 = r1
            r5 = 0
            r6 = r4
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r6 = kotlin.text.StringsKt.isBlank(r6)
            r6 = r6 ^ r2
            if (r6 == 0) goto L2b
            goto L2c
        L2b:
            r1 = r3
        L2c:
            if (r1 == 0) goto L44
        L2f:
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "IMDb "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r1 = r5.toString()
            goto L45
        L44:
            r1 = r3
        L45:
            r0[r2] = r1
            java.util.List<java.lang.String> r1 = r14.genres
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r4 = 3
            java.util.List r1 = kotlin.collections.CollectionsKt.take(r1, r4)
            r5 = r1
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.lang.String r1 = ", "
            r6 = r1
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r12 = 62
            r13 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.lang.String r1 = kotlin.collections.CollectionsKt.joinToString$default(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r5 = r1
            r6 = 0
            r7 = r5
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r7 = kotlin.text.StringsKt.isBlank(r7)
            r2 = r2 ^ r7
            if (r2 == 0) goto L72
            goto L73
        L72:
            r1 = r3
        L73:
            r2 = 2
            r0[r2] = r1
            java.lang.Long r1 = r14.durationSeconds
            if (r1 == 0) goto L87
            java.lang.Number r1 = (java.lang.Number) r1
            long r1 = r1.longValue()
            r3 = 0
            java.lang.String r3 = com.streamsync.app.data.model.MediaItemKt.access$formatDuration(r1)
        L87:
            r0[r4] = r3
            java.util.List r0 = kotlin.collections.CollectionsKt.listOfNotNull(r0)
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.lang.String r0 = " • "
            r2 = r0
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r8 = 62
            r9 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.model.MediaItem.getMetaLine():java.lang.String");
    }
}
