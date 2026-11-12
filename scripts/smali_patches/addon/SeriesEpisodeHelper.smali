.class public Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;
.super Ljava/lang/Object;
.source "SeriesEpisodeHelper.java"


# static fields
.field private static final CINEMETA_MOVIE_URL:Ljava/lang/String; = "https://v3-cinemeta.strem.io/meta/movie/"

.field private static final CINEMETA_SERIES_URL:Ljava/lang/String; = "https://v3-cinemeta.strem.io/meta/series/"

.field private static final IMDB_PATTERN:Ljava/util/regex/Pattern;

.field private static final TAG:Ljava/lang/String; = "SeriesEpisodeHelper"

.field private static volatile streamPickerActive:Z

.field private static volatile streamPickerState:Landroidx/compose/runtime/MutableState;


# direct methods
.method static constructor <clinit>()V
    .registers 1

    .line 29
    const-string v0, "tt\\d+"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->IMDB_PATTERN:Ljava/util/regex/Pattern;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static applyCinemetaToMeta(Ljava/lang/Object;Ljava/lang/Object;Lorg/json/JSONObject;)Ljava/lang/Object;
    .registers 31

    .line 135
    move-object/from16 v0, p2

    const-string v1, "Trailer"

    :try_start_4
    const-string v2, "com.streamsync.app.data.addon.AddonVideo"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 136
    const/4 v3, 0x7

    new-array v4, v3, [Ljava/lang/Class;

    const-class v5, Ljava/lang/String;

    const/4 v6, 0x0

    aput-object v5, v4, v6

    const/4 v7, 0x1

    aput-object v5, v4, v7

    const-class v8, Ljava/lang/Integer;

    const/4 v9, 0x2

    aput-object v8, v4, v9

    const/4 v10, 0x3

    aput-object v8, v4, v10

    const/4 v8, 0x4

    aput-object v5, v4, v8

    const/4 v11, 0x5

    aput-object v5, v4, v11

    const/4 v12, 0x6

    aput-object v5, v4, v12

    invoke-virtual {v2, v4}, Ljava/lang/Class;->getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;

    move-result-object v2

    .line 140
    const-string v4, "com.streamsync.app.data.addon.AddonTrailer"

    invoke-static {v4}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v4

    .line 141
    new-array v5, v10, [Ljava/lang/Class;

    const-class v13, Ljava/lang/String;

    aput-object v13, v5, v6

    aput-object v13, v5, v7

    aput-object v13, v5, v9

    invoke-virtual {v4, v5}, Ljava/lang/Class;->getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;

    move-result-object v4

    .line 146
    new-instance v14, Ljava/util/ArrayList;

    invoke-direct {v14}, Ljava/util/ArrayList;-><init>()V

    .line 147
    const-string v5, "videos"

    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v5
    :try_end_49
    .catchall {:try_start_4 .. :try_end_49} :catchall_1be

    .line 148
    const-string v13, "description"

    const-string v15, "name"

    const/16 v16, 0x0

    const-string v6, "id"

    const/16 v17, 0x4

    const-string v8, ""

    const/16 v18, 0x2

    if-eqz v5, :cond_ff

    .line 149
    const/4 v11, 0x0

    const/16 v19, 0x5

    :goto_5c
    const/16 v20, 0x6

    :try_start_5e
    invoke-virtual {v5}, Lorg/json/JSONArray;->length()I

    move-result v12

    if-ge v11, v12, :cond_fc

    .line 150
    invoke-virtual {v5, v11}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v12

    .line 151
    if-nez v12, :cond_71

    move-object/from16 v24, v5

    const/4 v9, 0x7

    const/16 v22, 0x3

    goto/16 :goto_f2

    .line 152
    :cond_71
    invoke-virtual {v12, v6, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v21

    .line 153
    const/16 v22, 0x3

    const-string v10, "title"

    add-int/lit8 v3, v11, 0x1

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "T\u1eadp "

    invoke-virtual {v9, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v12, v10, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v12, v15, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 154
    const-string v9, "season"

    const/4 v10, 0x1

    invoke-virtual {v12, v9, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v9

    .line 155
    const-string v10, "number"

    move-object/from16 v24, v5

    const-string v5, "episode"

    invoke-virtual {v12, v5, v3}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v3

    invoke-virtual {v12, v10, v3}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v3

    .line 156
    const-string v5, "released"

    const-string v10, "firstAired"

    move/from16 v25, v3

    const/4 v3, 0x0

    invoke-virtual {v12, v10, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v12, v5, v10}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 157
    const-string v10, "thumbnail"

    invoke-virtual {v12, v10, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 158
    const-string v3, "overview"

    move-object/from16 v26, v5

    move-object/from16 v27, v7

    const/4 v5, 0x0

    invoke-virtual {v12, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v12, v3, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 160
    nop

    .line 161
    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-static/range {v25 .. v25}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    const/4 v9, 0x7

    new-array v12, v9, [Ljava/lang/Object;

    aput-object v21, v12, v16

    const/16 v23, 0x1

    aput-object v27, v12, v23

    aput-object v5, v12, v18

    aput-object v7, v12, v22

    aput-object v26, v12, v17

    aput-object v10, v12, v19

    aput-object v3, v12, v20

    .line 160
    invoke-virtual {v2, v12}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    .line 163
    invoke-interface {v14, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 149
    :goto_f2
    add-int/lit8 v11, v11, 0x1

    move-object/from16 v5, v24

    const/4 v3, 0x7

    const/4 v7, 0x1

    const/4 v10, 0x3

    const/4 v12, 0x6

    goto/16 :goto_5c

    :cond_fc
    const/16 v22, 0x3

    goto :goto_101

    .line 148
    :cond_ff
    const/16 v22, 0x3

    .line 168
    :goto_101
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 169
    const-string v3, "trailers"

    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    .line 170
    if-eqz v3, :cond_154

    .line 171
    const/4 v5, 0x0

    :goto_10f
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v7

    if-ge v5, v7, :cond_154

    .line 172
    invoke-virtual {v3, v5}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v7

    .line 173
    if-nez v7, :cond_11f

    const/4 v11, 0x3

    const/16 v23, 0x1

    goto :goto_14f

    .line 174
    :cond_11f
    const-string v9, "source"

    invoke-virtual {v7, v6, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v7, v9, v10}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 175
    const-string v10, "type"

    invoke-virtual {v7, v10, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 176
    invoke-virtual {v7, v15, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 177
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v11

    if-nez v11, :cond_14c

    .line 178
    const/4 v11, 0x3

    new-array v12, v11, [Ljava/lang/Object;

    aput-object v9, v12, v16

    const/16 v23, 0x1

    aput-object v10, v12, v23

    aput-object v7, v12, v18

    invoke-virtual {v4, v12}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    .line 179
    invoke-interface {v2, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_14f

    .line 177
    :cond_14c
    const/4 v11, 0x3

    const/16 v23, 0x1

    .line 171
    :goto_14f
    add-int/lit8 v5, v5, 0x1

    const/16 v22, 0x3

    goto :goto_10f

    .line 185
    :cond_154
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 186
    const-string v3, "cast"

    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    .line 187
    if-eqz v3, :cond_178

    .line 188
    const/4 v4, 0x0

    :goto_162
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v5

    if-ge v4, v5, :cond_178

    .line 189
    invoke-virtual {v3, v4, v8}, Lorg/json/JSONArray;->optString(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 190
    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-nez v6, :cond_175

    invoke-interface {v1, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 188
    :cond_175
    add-int/lit8 v4, v4, 0x1

    goto :goto_162

    .line 195
    :cond_178
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 196
    const-string v4, "genres"

    invoke-virtual {v0, v4}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 197
    if-eqz v4, :cond_19c

    .line 198
    const/4 v6, 0x0

    :goto_186
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v5

    if-ge v6, v5, :cond_19c

    .line 199
    invoke-virtual {v4, v6, v8}, Lorg/json/JSONArray;->optString(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 200
    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_199

    invoke-interface {v3, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 198
    :cond_199
    add-int/lit8 v6, v6, 0x1

    goto :goto_186

    .line 204
    :cond_19c
    const/4 v5, 0x0

    invoke-virtual {v0, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v18

    .line 205
    const-string v4, "background"

    invoke-virtual {v0, v4, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v19

    .line 206
    const-string v4, "imdbRating"

    invoke-virtual {v0, v4, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v20

    .line 209
    if-eqz p1, :cond_1b2

    move-object/from16 v13, p1

    goto :goto_1b4

    :cond_1b2
    move-object/from16 v13, p0

    .line 210
    :goto_1b4
    move-object/from16 v16, v1

    move-object v15, v2

    move-object/from16 v17, v3

    invoke-static/range {v13 .. v20}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->invokeCopyDefault(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1bd
    .catchall {:try_start_5e .. :try_end_1bd} :catchall_1be

    return-object v0

    .line 211
    :catchall_1be
    move-exception v0

    .line 212
    const-string v1, "SeriesEpisodeHelper"

    const-string v2, "applyCinemetaToMeta error"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 213
    if-eqz p1, :cond_1cb

    move-object/from16 v0, p1

    goto :goto_1cd

    :cond_1cb
    move-object/from16 v0, p0

    :goto_1cd
    return-object v0
.end method

.method public static enrichSeriesIfEmpty(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 9

    const-string v0, "getId"

    const-string v2, "getVideos"

    if-nez p0, :cond_check_base

    return-object p1

    :cond_check_base
    :try_start_enrich
    # 1. Check if baseMeta already has videos
    invoke-static {p0, v2}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    if-eqz v3, :cond_check_full_videos

    invoke-interface {v3}, Ljava/util/List;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_check_full_videos

    # baseMeta already has episodes from addon! Preserve them completely!
    return-object p0

    :cond_check_full_videos
    # 2. Check if fullMeta has videos from addon
    if-eqz p1, :cond_no_videos

    invoke-static {p1, v2}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    if-eqz v3, :cond_no_videos

    invoke-interface {v3}, Ljava/util/List;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_no_videos

    # fullMeta has videos! Merge into baseMeta
    invoke-static {p0, p1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->mergeAddonMetas(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_no_videos
    # 3. Neither has videos: Need to query Cinemeta!
    invoke-static {p0, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->extractImdbId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_check_title

    if-eqz p1, :cond_check_title

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->extractImdbId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    :cond_check_title
    if-nez v1, :cond_fetch_cinemeta

    # Resolve by series title from Cinemeta search!
    const-string v0, "getName"

    invoke-static {p0, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_resolve_name

    if-eqz p1, :cond_resolve_name

    const-string v0, "getName"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    :cond_resolve_name
    if-eqz v0, :cond_return_base

    const-string v2, "series"

    invoke-static {v2, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->searchCinemetaImdbId(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    :cond_fetch_cinemeta
    if-nez v1, :cond_do_fetch

    :cond_return_base
    return-object p0

    :cond_do_fetch
    const-string v0, "series"

    invoke-static {v0, v1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->fetchCinemetaMeta(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    if-nez v0, :cond_apply

    return-object p0

    :cond_apply
    invoke-static {p0, p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->applyCinemetaToMeta(Ljava/lang/Object;Ljava/lang/Object;Lorg/json/JSONObject;)Ljava/lang/Object;

    move-result-object p0
    :try_end_enrich
    .catchall {:try_start_enrich .. :try_end_enrich} :catchall_enrich_err

    return-object p0

    :catchall_enrich_err
    move-exception v0

    const-string v1, "SeriesEpisodeHelper"

    const-string v2, "enrichSeriesIfEmpty error"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-object p0
.end method

.method public static extractImdbId(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    .line 32
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    .line 33
    :cond_4
    sget-object v1, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->IMDB_PATTERN:Ljava/util/regex/Pattern;

    invoke-virtual {v1, p0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object p0

    .line 34
    invoke-virtual {p0}, Ljava/util/regex/Matcher;->find()Z

    move-result v1

    if-eqz v1, :cond_16

    .line 35
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 37
    :cond_16
    return-object v0
.end method

.method public static searchCinemetaImdbId(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 8

    const/4 v0, 0x0

    if-eqz p1, :cond_search_null

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_search_start

    :cond_search_null
    return-object v0

    :cond_search_start
    :try_start_search
    const-string v1, "UTF-8"

    invoke-static {p1, v1}, Ljava/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "https://v3-cinemeta.strem.io/catalog/"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "/top/search="

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ".json"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    new-instance v1, Ljava/net/URL;

    invoke-direct {v1, p0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    const-string v1, "GET"

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    const/16 v1, 0x1770

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    const-string v1, "User-Agent"

    const-string v2, "Mozilla/5.0 StreamSync"

    invoke-virtual {p0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-eq v1, v2, :cond_search_ok

    return-object v0

    :cond_search_ok
    new-instance v1, Ljava/io/BufferedReader;

    new-instance v2, Ljava/io/InputStreamReader;

    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    const-string v3, "UTF-8"

    invoke-direct {v2, p0, v3}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    invoke-direct {v1, v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    :goto_search_read
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_search_done

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_search_read

    :cond_search_done
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    new-instance v1, Lorg/json/JSONObject;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v1, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    const-string p0, "metas"

    invoke-virtual {v1, p0}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object p0

    if-eqz p0, :cond_search_end

    const/4 v1, 0x0

    :goto_search_loop
    invoke-virtual {p0}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v1, v2, :cond_search_end

    invoke-virtual {p0, v1}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v2

    if-eqz v2, :cond_search_next

    const-string v3, "id"

    const-string v4, ""

    invoke-virtual {v2, v3, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    const-string v3, "tt"

    invoke-virtual {v2, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_search_next

    return-object v2

    :cond_search_next
    add-int/lit8 v1, v1, 0x1

    goto :goto_search_loop

    :cond_search_end
    return-object v0
    :try_end_search
    .catchall {:try_start_search .. :try_end_search} :catchall_search_err

    :catchall_search_err
    move-exception p0

    const-string v1, "SeriesEpisodeHelper"

    const-string v2, "searchCinemetaImdbId error"

    invoke-static {v1, v2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return-object v0
.end method

.method private static fetchCinemetaMeta(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;
    .registers 6

    .line 105
    const/4 v0, 0x0

    :try_start_1
    const-string v1, "series"

    invoke-virtual {v1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_c

    const-string p0, "https://v3-cinemeta.strem.io/meta/series/"

    goto :goto_e

    :cond_c
    const-string p0, "https://v3-cinemeta.strem.io/meta/movie/"

    :goto_e
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, ".json"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 106
    new-instance v1, Ljava/net/URL;

    invoke-direct {v1, p0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 107
    invoke-virtual {v1}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    .line 108
    const-string v1, "GET"

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 109
    const/16 v1, 0x1f40

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 110
    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 111
    const-string v1, "User-Agent"

    const-string v2, "Mozilla/5.0 StreamSync"

    invoke-virtual {p0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 113
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-eq v1, v2, :cond_4d

    .line 114
    return-object v0

    .line 117
    :cond_4d
    new-instance v1, Ljava/io/BufferedReader;

    new-instance v2, Ljava/io/InputStreamReader;

    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    const-string v3, "UTF-8"

    invoke-direct {v2, p0, v3}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    invoke-direct {v1, v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 118
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 120
    :goto_62
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_6c

    .line 121
    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_62

    .line 123
    :cond_6c
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    .line 125
    new-instance v1, Lorg/json/JSONObject;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v1, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 126
    const-string p0, "meta"

    invoke-virtual {v1, p0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0
    :try_end_7e
    .catchall {:try_start_1 .. :try_end_7e} :catchall_7f

    return-object p0

    .line 127
    :catchall_7f
    move-exception p0

    .line 128
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "fetchCinemetaMeta error: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v1, "SeriesEpisodeHelper"

    invoke-static {v1, p1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 129
    return-object v0
.end method

.method private static getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;
    .registers 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "*>;"
        }
    .end annotation

    .line 304
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    .line 306
    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    .line 307
    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 308
    instance-of p1, p0, Ljava/util/List;

    if-eqz p1, :cond_1d

    .line 309
    check-cast p0, Ljava/util/List;
    :try_end_1b
    .catchall {:try_start_4 .. :try_end_1b} :catchall_1c

    return-object p0

    .line 311
    :catchall_1c
    move-exception p0

    :cond_1d
    nop

    .line 312
    return-object v0
.end method

.method private static getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;
    .registers 6

    .line 316
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    .line 318
    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    .line 319
    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 320
    instance-of p1, p0, Ljava/lang/String;

    if-eqz p1, :cond_1d

    .line 321
    check-cast p0, Ljava/lang/String;
    :try_end_1b
    .catchall {:try_start_4 .. :try_end_1b} :catchall_1c

    return-object p0

    .line 323
    :catchall_1c
    move-exception p0

    :cond_1d
    nop

    .line 324
    return-object v0
.end method

.method private static invokeCopyDefault(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    .registers 15
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Ljava/util/List<",
            "*>;",
            "Ljava/util/List<",
            "*>;",
            "Ljava/util/List<",
            "*>;",
            "Ljava/util/List<",
            "*>;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Object;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 243
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    .line 250
    invoke-virtual {v0}, Ljava/lang/Class;->getMethods()[Ljava/lang/reflect/Method;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    const/4 v3, 0x0

    :goto_b
    if-ge v3, v1, :cond_96

    aget-object v4, v0, v3

    .line 251
    const-string v5, "copy$default"

    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_92

    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v5

    array-length v5, v5

    const/16 v6, 0x1c

    if-ne v5, v6, :cond_92

    .line 252
    new-array v0, v6, [Ljava/lang/Object;

    .line 253
    aput-object p0, v0, v2

    .line 256
    nop

    .line 259
    if-eqz p5, :cond_32

    .line 260
    const/4 p0, 0x6

    aput-object p5, v0, p0

    .line 261
    const p0, 0x1ffffdf

    goto :goto_35

    .line 259
    :cond_32
    const p0, 0x1ffffff

    .line 264
    :goto_35
    if-eqz p6, :cond_3c

    .line 265
    const/4 p5, 0x7

    aput-object p6, v0, p5

    .line 266
    and-int/lit8 p0, p0, -0x41

    .line 269
    :cond_3c
    if-eqz p7, :cond_44

    .line 270
    const/16 p5, 0xc

    aput-object p7, v0, p5

    .line 271
    and-int/lit16 p0, p0, -0x801

    .line 274
    :cond_44
    if-eqz p1, :cond_52

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result p5

    if-nez p5, :cond_52

    .line 275
    const/16 p5, 0xd

    aput-object p1, v0, p5

    .line 276
    and-int/lit16 p0, p0, -0x1001

    .line 279
    :cond_52
    if-eqz p4, :cond_60

    invoke-interface {p4}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_60

    .line 280
    const/16 p1, 0xe

    aput-object p4, v0, p1

    .line 281
    and-int/lit16 p0, p0, -0x2001

    .line 284
    :cond_60
    if-eqz p3, :cond_70

    invoke-interface {p3}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_70

    .line 285
    const/16 p1, 0x10

    aput-object p3, v0, p1

    .line 286
    const p1, -0x8001

    and-int/2addr p0, p1

    .line 289
    :cond_70
    if-eqz p2, :cond_80

    invoke-interface {p2}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_80

    .line 290
    const/16 p1, 0x16

    aput-object p2, v0, p1

    .line 291
    const p1, -0x200001

    and-int/2addr p0, p1

    .line 294
    :cond_80
    const/16 p1, 0x1a

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    aput-object p0, v0, p1

    .line 295
    const/16 p0, 0x1b

    const/4 p1, 0x0

    aput-object p1, v0, p0

    .line 297
    invoke-virtual {v4, p1, v0}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 250
    :cond_92
    add-int/lit8 v3, v3, 0x1

    goto/16 :goto_b

    .line 300
    :cond_96
    return-object p0
.end method

.method private static mergeAddonMetas(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 11

    .line 219
    :try_start_0
    const-string v0, "getVideos"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v2

    .line 220
    const-string v0, "getTrailers"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    .line 221
    const-string v0, "getCast"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v4

    .line 222
    const-string v0, "getGenres"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v5

    .line 223
    const-string v0, "getDescription"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 224
    const-string v0, "getBackground"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 225
    const-string v0, "getImdbRating"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_2a
    .catchall {:try_start_0 .. :try_end_2a} :catchall_32

    .line 227
    move-object v1, p0

    :try_start_2b
    invoke-static/range {v1 .. v8}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->invokeCopyDefault(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0
    :try_end_2f
    .catchall {:try_start_2b .. :try_end_2f} :catchall_30

    return-object p0

    .line 228
    :catchall_30
    move-exception v0

    goto :goto_34

    :catchall_32
    move-exception v0

    move-object v1, p0

    .line 229
    :goto_34
    return-object v1
.end method

.method public static resolveTrailerId(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 6

    if-eqz p0, :cond_trailer_empty

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_trailer_empty

    return-object p0

    :cond_trailer_empty
    const/4 v0, 0x0

    :try_start_trailer
    invoke-static {p1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->extractImdbId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_trailer_fetch

    return-object v0

    :cond_trailer_fetch
    if-nez p2, :cond_trailer_type_ok

    const-string p2, "movie"

    :cond_trailer_type_ok
    invoke-static {p2, p0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->fetchCinemetaMeta(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    if-nez p0, :cond_trailer_has_meta

    return-object v0

    :cond_trailer_has_meta
    const-string p1, "trailers"

    invoke-virtual {p0, p1}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object p0

    if-eqz p0, :cond_trailer_end

    invoke-virtual {p0}, Lorg/json/JSONArray;->length()I

    move-result p1

    if-lez p1, :cond_trailer_end

    const/4 p1, 0x0

    :goto_trailer_loop
    invoke-virtual {p0}, Lorg/json/JSONArray;->length()I

    move-result p2

    if-ge p1, p2, :cond_trailer_end

    invoke-virtual {p0, p1}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object p2

    if-eqz p2, :cond_trailer_next

    const-string v1, "source"

    const-string v2, ""

    invoke-virtual {p2, v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_trailer_found

    const-string v1, "id"

    const-string v2, ""

    invoke-virtual {p2, v1, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    :cond_trailer_found
    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_trailer_next

    return-object v1

    :cond_trailer_next
    add-int/lit8 p1, p1, 0x1

    goto :goto_trailer_loop

    :cond_trailer_end
    return-object v0
    :try_end_trailer
    .catchall {:try_start_trailer .. :try_end_trailer} :catchall_trailer_err

    :catchall_trailer_err
    return-object v0
.end method

.method public static isStreamPickerActive()Z
    .registers 4

    sget-object v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerState:Landroidx/compose/runtime/MutableState;
    if-nez v0, :cond_read_state

    :try_start_init
    sget-boolean v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerActive:Z
    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;
    move-result-object v0
    const/4 v1, 0x0
    const/4 v2, 0x2
    const/4 v3, 0x0
    invoke-static {v0, v1, v2, v3}, Landroidx/compose/runtime/SnapshotStateKt;->mutableStateOf$default(Ljava/lang/Object;Landroidx/compose/runtime/SnapshotMutationPolicy;ILjava/lang/Object;)Landroidx/compose/runtime/MutableState;
    move-result-object v0
    sput-object v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerState:Landroidx/compose/runtime/MutableState;
    :try_end_init
    .catch Ljava/lang/Throwable; {:try_start_init .. :try_end_init} :catch_init

    :cond_read_state
    if-eqz v0, :cond_fallback
    :try_start_read
    invoke-interface {v0}, Landroidx/compose/runtime/MutableState;->getValue()Ljava/lang/Object;
    move-result-object v0
    check-cast v0, Ljava/lang/Boolean;
    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z
    move-result v0
    return v0
    :try_end_read
    .catch Ljava/lang/Throwable; {:try_start_read .. :try_end_read} :catch_read

    :catch_init
    :catch_read
    :cond_fallback
    sget-boolean v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerActive:Z
    return v0
.end method

.method public static setStreamPickerActive(Z)V
    .registers 4

    sput-boolean p0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerActive:Z

    :try_start_set
    sget-object v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerState:Landroidx/compose/runtime/MutableState;
    if-nez v0, :cond_set_val

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;
    move-result-object v0
    const/4 v1, 0x0
    const/4 v2, 0x2
    const/4 v3, 0x0
    invoke-static {v0, v1, v2, v3}, Landroidx/compose/runtime/SnapshotStateKt;->mutableStateOf$default(Ljava/lang/Object;Landroidx/compose/runtime/SnapshotMutationPolicy;ILjava/lang/Object;)Landroidx/compose/runtime/MutableState;
    move-result-object v0
    sput-object v0, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->streamPickerState:Landroidx/compose/runtime/MutableState;
    return-void

    :cond_set_val
    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;
    move-result-object v1
    invoke-interface {v0, v1}, Landroidx/compose/runtime/MutableState;->setValue(Ljava/lang/Object;)V
    :try_end_set
    .catch Ljava/lang/Throwable; {:try_start_set .. :try_end_set} :catch_set

    :catch_set
    return-void
.end method

.method public static resetStreamPicker()V
    .registers 1

    const/4 v0, 0x0

    invoke-static {v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->setStreamPickerActive(Z)V

    return-void
.end method

.method public static shouldScopeToOwner(Ljava/util/List;)Z
    .registers 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "*>;)Z"
        }
    .end annotation

    if-eqz p0, :cond_empty

    invoke-interface {p0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_empty

    const/4 v0, 0x1

    return v0

    :cond_empty
    const/4 v0, 0x0

    return v0
.end method
