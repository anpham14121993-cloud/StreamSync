package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BÍ\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0010\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"J\f\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\u0010J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010HÆ\u0003J\u000f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0003J\u000f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0003J\u000f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0010HÆ\u0003J\u0011\u0010Q\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0010HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u001fHÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010!HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÑ\u0002\u0010[\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00102\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00102\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!HÆ\u0001J\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020A0\u0010J\u0013\u0010]\u001a\u00020^2\b\u0010_\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010`\u001a\u00020aHÖ\u0001J\b\u0010b\u001a\u0004\u0018\u00010\u0003J\t\u0010c\u001a\u00020\u0003HÖ\u0001J\b\u0010d\u001a\u0004\u0018\u00010\u0003R\u0018\u0010 \u001a\u0004\u0018\u00010!8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010+R\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010+R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010&R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010&R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010&R\u0019\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b4\u0010+R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010&R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010&R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010&R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010&R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010&R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010&R\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00108\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010+R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b=\u0010&R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b?\u0010&¨\u0006e"}, d2 = {"Lcom/streamsync/app/data/addon/AddonMeta;", "", "id", "", "type", HintConstants.AUTOFILL_HINT_NAME, "poster", "posterShape", "description", "background", "logo", "icon", "releaseInfo", "released", "imdbRating", "videos", "", "Lcom/streamsync/app/data/addon/AddonVideo;", "genres", "director", "cast", "runtime", "language", "country", "awards", "website", "trailers", "Lcom/streamsync/app/data/addon/AddonTrailer;", "links", "Lcom/streamsync/app/data/addon/AddonMetaLink;", "behaviorHints", "Lcom/streamsync/app/data/addon/AddonMetaBehaviorHints;", "appExtras", "Lcom/streamsync/app/data/addon/AddonAppExtras;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/streamsync/app/data/addon/AddonMetaBehaviorHints;Lcom/streamsync/app/data/addon/AddonAppExtras;)V", "getAppExtras", "()Lcom/streamsync/app/data/addon/AddonAppExtras;", "getAwards", "()Ljava/lang/String;", "getBackground", "getBehaviorHints", "()Lcom/streamsync/app/data/addon/AddonMetaBehaviorHints;", "getCast", "()Ljava/util/List;", "getCountry", "getDescription", "getDirector", "getGenres", "getIcon", "getId", "getImdbRating", "getLanguage", "getLinks", "getLogo", "getName", "getPoster", "getPosterShape", "getReleaseInfo", "getReleased", "getRuntime", "getTrailers", "getType", "getVideos", "getWebsite", "castPeople", "Lcom/streamsync/app/data/addon/AddonPerson;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "directorPeople", "equals", "", "other", "hashCode", "", "logoUrl", "toString", "trailerId", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonMeta {
    public static final int $stable = 8;

    @SerializedName("app_extras")
    private final AddonAppExtras appExtras;

    @JsonAdapter(AddonTextAdapter.class)
    private final String awards;
    private final String background;
    private final AddonMetaBehaviorHints behaviorHints;

    @JsonAdapter(AddonNamesAdapter.class)
    private final List<String> cast;

    @JsonAdapter(AddonTextAdapter.class)
    private final String country;

    @JsonAdapter(AddonTextAdapter.class)
    private final String description;

    @JsonAdapter(AddonNamesAdapter.class)
    private final List<String> director;

    @JsonAdapter(AddonGenresAdapter.class)
    private final List<String> genres;
    private final String icon;
    private final String id;
    private final String imdbRating;

    @JsonAdapter(AddonTextAdapter.class)
    private final String language;
    private final List<AddonMetaLink> links;
    private final String logo;
    private final String name;
    private final String poster;
    private final String posterShape;
    private final String releaseInfo;
    private final String released;

    @JsonAdapter(AddonTextAdapter.class)
    private final String runtime;

    @JsonAdapter(AddonTrailerAdapter.class)
    private final List<AddonTrailer> trailers;
    private final String type;
    private final List<AddonVideo> videos;
    private final String website;

    public AddonMeta() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 33554431, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    /* renamed from: component11, reason: from getter */
    public final String getReleased() {
        return this.released;
    }

    /* renamed from: component12, reason: from getter */
    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final List<AddonVideo> component13() {
        return this.videos;
    }

    public final List<String> component14() {
        return this.genres;
    }

    public final List<String> component15() {
        return this.director;
    }

    public final List<String> component16() {
        return this.cast;
    }

    /* renamed from: component17, reason: from getter */
    public final String getRuntime() {
        return this.runtime;
    }

    /* renamed from: component18, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* renamed from: component19, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component20, reason: from getter */
    public final String getAwards() {
        return this.awards;
    }

    /* renamed from: component21, reason: from getter */
    public final String getWebsite() {
        return this.website;
    }

    public final List<AddonTrailer> component22() {
        return this.trailers;
    }

    public final List<AddonMetaLink> component23() {
        return this.links;
    }

    /* renamed from: component24, reason: from getter */
    public final AddonMetaBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    /* renamed from: component25, reason: from getter */
    public final AddonAppExtras getAppExtras() {
        return this.appExtras;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPosterShape() {
        return this.posterShape;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBackground() {
        return this.background;
    }

    /* renamed from: component8, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    /* renamed from: component9, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    public final AddonMeta copy(String id, String type, String name, String poster, String posterShape, String description, String background, String logo, String icon, String releaseInfo, String released, String imdbRating, List<AddonVideo> videos, List<String> genres, List<String> director, List<String> cast, String runtime, String language, String country, String awards, String website, List<AddonTrailer> trailers, List<AddonMetaLink> links, AddonMetaBehaviorHints behaviorHints, AddonAppExtras appExtras) {
        Intrinsics.checkNotNullParameter(genres, "genres");
        Intrinsics.checkNotNullParameter(director, "director");
        Intrinsics.checkNotNullParameter(cast, "cast");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        return new AddonMeta(id, type, name, poster, posterShape, description, background, logo, icon, releaseInfo, released, imdbRating, videos, genres, director, cast, runtime, language, country, awards, website, trailers, links, behaviorHints, appExtras);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonMeta)) {
            return false;
        }
        AddonMeta addonMeta = (AddonMeta) other;
        return Intrinsics.areEqual(this.id, addonMeta.id) && Intrinsics.areEqual(this.type, addonMeta.type) && Intrinsics.areEqual(this.name, addonMeta.name) && Intrinsics.areEqual(this.poster, addonMeta.poster) && Intrinsics.areEqual(this.posterShape, addonMeta.posterShape) && Intrinsics.areEqual(this.description, addonMeta.description) && Intrinsics.areEqual(this.background, addonMeta.background) && Intrinsics.areEqual(this.logo, addonMeta.logo) && Intrinsics.areEqual(this.icon, addonMeta.icon) && Intrinsics.areEqual(this.releaseInfo, addonMeta.releaseInfo) && Intrinsics.areEqual(this.released, addonMeta.released) && Intrinsics.areEqual(this.imdbRating, addonMeta.imdbRating) && Intrinsics.areEqual(this.videos, addonMeta.videos) && Intrinsics.areEqual(this.genres, addonMeta.genres) && Intrinsics.areEqual(this.director, addonMeta.director) && Intrinsics.areEqual(this.cast, addonMeta.cast) && Intrinsics.areEqual(this.runtime, addonMeta.runtime) && Intrinsics.areEqual(this.language, addonMeta.language) && Intrinsics.areEqual(this.country, addonMeta.country) && Intrinsics.areEqual(this.awards, addonMeta.awards) && Intrinsics.areEqual(this.website, addonMeta.website) && Intrinsics.areEqual(this.trailers, addonMeta.trailers) && Intrinsics.areEqual(this.links, addonMeta.links) && Intrinsics.areEqual(this.behaviorHints, addonMeta.behaviorHints) && Intrinsics.areEqual(this.appExtras, addonMeta.appExtras);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.poster == null ? 0 : this.poster.hashCode())) * 31) + (this.posterShape == null ? 0 : this.posterShape.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.background == null ? 0 : this.background.hashCode())) * 31) + (this.logo == null ? 0 : this.logo.hashCode())) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + (this.releaseInfo == null ? 0 : this.releaseInfo.hashCode())) * 31) + (this.released == null ? 0 : this.released.hashCode())) * 31) + (this.imdbRating == null ? 0 : this.imdbRating.hashCode())) * 31) + (this.videos == null ? 0 : this.videos.hashCode())) * 31) + this.genres.hashCode()) * 31) + this.director.hashCode()) * 31) + this.cast.hashCode()) * 31) + (this.runtime == null ? 0 : this.runtime.hashCode())) * 31) + (this.language == null ? 0 : this.language.hashCode())) * 31) + (this.country == null ? 0 : this.country.hashCode())) * 31) + (this.awards == null ? 0 : this.awards.hashCode())) * 31) + (this.website == null ? 0 : this.website.hashCode())) * 31) + this.trailers.hashCode()) * 31) + (this.links == null ? 0 : this.links.hashCode())) * 31) + (this.behaviorHints == null ? 0 : this.behaviorHints.hashCode())) * 31) + (this.appExtras != null ? this.appExtras.hashCode() : 0);
    }

    public String toString() {
        return "AddonMeta(id=" + this.id + ", type=" + this.type + ", name=" + this.name + ", poster=" + this.poster + ", posterShape=" + this.posterShape + ", description=" + this.description + ", background=" + this.background + ", logo=" + this.logo + ", icon=" + this.icon + ", releaseInfo=" + this.releaseInfo + ", released=" + this.released + ", imdbRating=" + this.imdbRating + ", videos=" + this.videos + ", genres=" + this.genres + ", director=" + this.director + ", cast=" + this.cast + ", runtime=" + this.runtime + ", language=" + this.language + ", country=" + this.country + ", awards=" + this.awards + ", website=" + this.website + ", trailers=" + this.trailers + ", links=" + this.links + ", behaviorHints=" + this.behaviorHints + ", appExtras=" + this.appExtras + ")";
    }

    public AddonMeta(String id, String type, String name, String poster, String posterShape, String description, String background, String logo, String icon, String releaseInfo, String released, String imdbRating, List<AddonVideo> list, List<String> genres, List<String> director, List<String> cast, String runtime, String language, String country, String awards, String website, List<AddonTrailer> trailers, List<AddonMetaLink> list2, AddonMetaBehaviorHints behaviorHints, AddonAppExtras appExtras) {
        Intrinsics.checkNotNullParameter(genres, "genres");
        Intrinsics.checkNotNullParameter(director, "director");
        Intrinsics.checkNotNullParameter(cast, "cast");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        this.id = id;
        this.type = type;
        this.name = name;
        this.poster = poster;
        this.posterShape = posterShape;
        this.description = description;
        this.background = background;
        this.logo = logo;
        this.icon = icon;
        this.releaseInfo = releaseInfo;
        this.released = released;
        this.imdbRating = imdbRating;
        this.videos = list;
        this.genres = genres;
        this.director = director;
        this.cast = cast;
        this.runtime = runtime;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.website = website;
        this.trailers = trailers;
        this.links = list2;
        this.behaviorHints = behaviorHints;
        this.appExtras = appExtras;
    }

    public /* synthetic */ AddonMeta(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, List list, List list2, List list3, List list4, String str13, String str14, String str15, String str16, String str17, List list5, List list6, AddonMetaBehaviorHints addonMetaBehaviorHints, AddonAppExtras addonAppExtras, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : str10, (i & 1024) != 0 ? null : str11, (i & 2048) != 0 ? null : str12, (i & 4096) != 0 ? null : list, (i & 8192) != 0 ? CollectionsKt.emptyList() : list2, (i & 16384) != 0 ? CollectionsKt.emptyList() : list3, (i & 32768) != 0 ? CollectionsKt.emptyList() : list4, (i & 65536) != 0 ? null : str13, (i & 131072) != 0 ? null : str14, (i & 262144) != 0 ? null : str15, (i & 524288) != 0 ? null : str16, (i & 1048576) != 0 ? null : str17, (i & 2097152) != 0 ? CollectionsKt.emptyList() : list5, (i & 4194304) != 0 ? null : list6, (i & 8388608) != 0 ? null : addonMetaBehaviorHints, (i & 16777216) != 0 ? null : addonAppExtras);
    }

    public final String getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final String getPosterShape() {
        return this.posterShape;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getBackground() {
        return this.background;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getReleaseInfo() {
        return this.releaseInfo;
    }

    public final String getReleased() {
        return this.released;
    }

    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final List<AddonVideo> getVideos() {
        return this.videos;
    }

    public final List<String> getGenres() {
        return this.genres;
    }

    public final List<String> getDirector() {
        return this.director;
    }

    public final List<String> getCast() {
        return this.cast;
    }

    public final String getRuntime() {
        return this.runtime;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getAwards() {
        return this.awards;
    }

    public final String getWebsite() {
        return this.website;
    }

    public final List<AddonTrailer> getTrailers() {
        return this.trailers;
    }

    public final List<AddonMetaLink> getLinks() {
        return this.links;
    }

    public final AddonMetaBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final AddonAppExtras getAppExtras() {
        return this.appExtras;
    }

    public final List<AddonPerson> castPeople() {
        AddonAppExtras addonAppExtras = this.appExtras;
        List<AddonPerson> cast = addonAppExtras != null ? addonAppExtras.getCast() : null;
        if (cast == null) {
            cast = CollectionsKt.emptyList();
        }
        List<AddonPerson> list = cast;
        if (list.isEmpty()) {
            Iterable $this$map$iv = this.cast;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                destination$iv$iv.add(new AddonPerson(it, null, null, null, 14, null));
            }
            list = (List) destination$iv$iv;
        }
        return list;
    }

    public final List<AddonPerson> directorPeople() {
        AddonAppExtras addonAppExtras = this.appExtras;
        List<AddonPerson> directors = addonAppExtras != null ? addonAppExtras.getDirectors() : null;
        if (directors == null) {
            directors = CollectionsKt.emptyList();
        }
        List<AddonPerson> list = directors;
        if (list.isEmpty()) {
            Iterable $this$map$iv = this.director;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                destination$iv$iv.add(new AddonPerson(it, null, null, null, 14, null));
            }
            list = (List) destination$iv$iv;
        }
        return list;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
    
        if (r0 == null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String logoUrl() {
        /*
            r5 = this;
            java.lang.String r0 = r5.logo
            r1 = 0
            if (r0 == 0) goto L16
            r2 = r0
            r3 = 0
            r4 = r2
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            r4 = r4 ^ 1
            if (r4 == 0) goto L13
            goto L14
        L13:
            r0 = r1
        L14:
            if (r0 != 0) goto L27
        L16:
            java.lang.String r0 = r5.icon
            if (r0 == 0) goto L28
            r2 = r0
            r3 = 0
            r4 = r2
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            r4 = r4 ^ 1
            if (r4 == 0) goto L28
        L27:
            r1 = r0
        L28:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonMeta.logoUrl():java.lang.String");
    }

    public final String trailerId() {
        Object element$iv;
        Iterable $this$firstOrNull$iv = this.trailers;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                AddonTrailer it2 = (AddonTrailer) element$iv;
                if (!StringsKt.isBlank(it2.getYoutubeId())) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        AddonTrailer addonTrailer = (AddonTrailer) element$iv;
        if (addonTrailer != null) {
            return addonTrailer.getYoutubeId();
        }
        return null;
    }
}
