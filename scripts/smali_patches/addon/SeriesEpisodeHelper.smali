.class public Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;
.super Ljava/lang/Object;
.source "SeriesEpisodeHelper.java"


# static fields
.field private static final CINEMETA_MOVIE_URL:Ljava/lang/String; = "https://v3-cinemeta.strem.io/meta/movie/"

.field private static final CINEMETA_SERIES_URL:Ljava/lang/String; = "https://v3-cinemeta.strem.io/meta/series/"

.field private static final IMDB_PATTERN:Ljava/util/regex/Pattern;

.field private static final TAG:Ljava/lang/String; = "SeriesEpisodeHelper"


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
    .registers 29

    .line 158
    move-object/from16 v0, p2

    const-string v1, "Trailer"

    :try_start_4
    const-string v2, "com.streamsync.app.data.addon.AddonVideo"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 159
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

    .line 163
    const-string v4, "com.streamsync.app.data.addon.AddonTrailer"

    invoke-static {v4}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v4

    .line 164
    new-array v5, v10, [Ljava/lang/Class;

    const-class v13, Ljava/lang/String;

    aput-object v13, v5, v6

    aput-object v13, v5, v7

    aput-object v13, v5, v9

    invoke-virtual {v4, v5}, Ljava/lang/Class;->getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;

    move-result-object v4

    .line 169
    new-instance v14, Ljava/util/ArrayList;

    invoke-direct {v14}, Ljava/util/ArrayList;-><init>()V

    .line 170
    const-string v5, "videos"

    invoke-virtual {v0, v5}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v5
    :try_end_49
    .catchall {:try_start_4 .. :try_end_49} :catchall_1e2

    .line 171
    const-string v13, "description"

    const-string v15, "name"

    const-string v12, "id"

    const-string v11, ""

    if-eqz v5, :cond_108

    .line 172
    const/4 v10, 0x0

    :goto_54
    :try_start_54
    invoke-virtual {v5}, Lorg/json/JSONArray;->length()I

    move-result v9

    if-ge v10, v9, :cond_101

    .line 173
    invoke-virtual {v5, v10}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v9

    .line 174
    if-nez v9, :cond_6e

    move-object/from16 v25, v1

    move-object/from16 v23, v4

    move-object/from16 v22, v5

    move-object/from16 v24, v15

    const/4 v3, 0x4

    const/4 v4, 0x5

    const/4 v5, 0x6

    const/4 v6, 0x7

    goto/16 :goto_f1

    .line 175
    :cond_6e
    invoke-virtual {v9, v12, v11}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v21

    .line 176
    const-string v6, "title"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "T\u1eadp "

    invoke-virtual {v3, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    add-int/lit8 v8, v10, 0x1

    invoke-virtual {v3, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v9, v6, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v9, v15, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 177
    const-string v6, "season"

    invoke-virtual {v9, v6, v7}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v6

    .line 178
    const-string v7, "number"

    move-object/from16 v22, v5

    const-string v5, "episode"

    invoke-virtual {v9, v5, v8}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v5

    invoke-virtual {v9, v7, v5}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v5

    .line 179
    const-string v7, "released"

    const-string v8, "firstAired"

    move-object/from16 v23, v4

    const/4 v4, 0x0

    invoke-virtual {v9, v8, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v9, v7, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 180
    const-string v8, "thumbnail"

    invoke-virtual {v9, v8, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 181
    const-string v4, "overview"

    move-object/from16 v25, v1

    move-object/from16 v24, v15

    const/4 v15, 0x0

    invoke-virtual {v9, v13, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v9, v4, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 183
    nop

    .line 184
    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    const/4 v6, 0x7

    new-array v9, v6, [Ljava/lang/Object;

    const/4 v15, 0x0

    aput-object v21, v9, v15

    const/4 v15, 0x1

    aput-object v3, v9, v15

    const/4 v3, 0x2

    aput-object v4, v9, v3

    const/4 v3, 0x3

    aput-object v5, v9, v3

    const/4 v3, 0x4

    aput-object v7, v9, v3

    const/4 v4, 0x5

    aput-object v8, v9, v4

    const/4 v5, 0x6

    aput-object v1, v9, v5

    .line 183
    invoke-virtual {v2, v9}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 186
    invoke-interface {v14, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 172
    :goto_f1
    add-int/lit8 v10, v10, 0x1

    move-object/from16 v5, v22

    move-object/from16 v4, v23

    move-object/from16 v15, v24

    move-object/from16 v1, v25

    const/4 v3, 0x7

    const/4 v6, 0x0

    const/4 v7, 0x1

    const/4 v9, 0x2

    goto/16 :goto_54

    :cond_101
    move-object/from16 v25, v1

    move-object/from16 v23, v4

    move-object/from16 v24, v15

    goto :goto_10e

    .line 171
    :cond_108
    move-object/from16 v25, v1

    move-object/from16 v23, v4

    move-object/from16 v24, v15

    .line 191
    :goto_10e
    new-instance v15, Ljava/util/ArrayList;

    invoke-direct {v15}, Ljava/util/ArrayList;-><init>()V

    .line 192
    const-string v1, "trailers"

    invoke-virtual {v0, v1}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v1

    .line 193
    if-eqz v1, :cond_178

    .line 194
    const/4 v2, 0x0

    :goto_11c
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v3

    if-ge v2, v3, :cond_176

    .line 195
    invoke-virtual {v1, v2}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    .line 196
    if-nez v3, :cond_133

    move-object/from16 v3, v23

    move-object/from16 v7, v24

    move-object/from16 v6, v25

    const/4 v4, 0x1

    const/4 v5, 0x2

    const/4 v8, 0x3

    const/4 v10, 0x0

    goto :goto_16d

    .line 197
    :cond_133
    const-string v4, "source"

    invoke-virtual {v3, v12, v11}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v4, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 198
    const-string v5, "type"

    move-object/from16 v6, v25

    invoke-virtual {v3, v5, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 199
    move-object/from16 v7, v24

    invoke-virtual {v3, v7, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 200
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v8

    if-nez v8, :cond_167

    .line 201
    const/4 v8, 0x3

    new-array v9, v8, [Ljava/lang/Object;

    const/4 v10, 0x0

    aput-object v4, v9, v10

    const/4 v4, 0x1

    aput-object v5, v9, v4

    const/4 v5, 0x2

    aput-object v3, v9, v5

    move-object/from16 v3, v23

    invoke-virtual {v3, v9}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    .line 202
    invoke-interface {v15, v9}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_16d

    .line 200
    :cond_167
    move-object/from16 v3, v23

    const/4 v4, 0x1

    const/4 v5, 0x2

    const/4 v8, 0x3

    const/4 v10, 0x0

    .line 194
    :goto_16d
    add-int/lit8 v2, v2, 0x1

    move-object/from16 v23, v3

    move-object/from16 v25, v6

    move-object/from16 v24, v7

    goto :goto_11c

    :cond_176
    const/4 v10, 0x0

    goto :goto_179

    .line 193
    :cond_178
    const/4 v10, 0x0

    .line 208
    :goto_179
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 209
    const-string v2, "cast"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v2

    .line 210
    if-eqz v2, :cond_19d

    .line 211
    const/4 v3, 0x0

    :goto_187
    invoke-virtual {v2}, Lorg/json/JSONArray;->length()I

    move-result v4

    if-ge v3, v4, :cond_19d

    .line 212
    invoke-virtual {v2, v3, v11}, Lorg/json/JSONArray;->optString(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 213
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_19a

    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 211
    :cond_19a
    add-int/lit8 v3, v3, 0x1

    goto :goto_187

    .line 218
    :cond_19d
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 219
    const-string v3, "genres"

    invoke-virtual {v0, v3}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    .line 220
    if-eqz v3, :cond_1c1

    .line 221
    const/4 v6, 0x0

    :goto_1ab
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v4

    if-ge v6, v4, :cond_1c1

    .line 222
    invoke-virtual {v3, v6, v11}, Lorg/json/JSONArray;->optString(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 223
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_1be

    invoke-interface {v2, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 221
    :cond_1be
    add-int/lit8 v6, v6, 0x1

    goto :goto_1ab

    .line 227
    :cond_1c1
    const/4 v3, 0x0

    invoke-virtual {v0, v13, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v18

    .line 228
    const-string v4, "background"

    invoke-virtual {v0, v4, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v19

    .line 229
    const-string v4, "imdbRating"

    invoke-virtual {v0, v4, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v20

    .line 232
    if-eqz p1, :cond_1d7

    move-object/from16 v13, p1

    goto :goto_1d9

    :cond_1d7
    move-object/from16 v13, p0

    .line 233
    :goto_1d9
    move-object/from16 v16, v1

    move-object/from16 v17, v2

    invoke-static/range {v13 .. v20}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->invokeCopyDefault(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1e1
    .catchall {:try_start_54 .. :try_end_1e1} :catchall_1e2

    return-object v0

    .line 234
    :catchall_1e2
    move-exception v0

    .line 235
    const-string v1, "SeriesEpisodeHelper"

    const-string v2, "applyCinemetaToMeta error"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 236
    if-eqz p1, :cond_1ef

    move-object/from16 v0, p1

    goto :goto_1f1

    :cond_1ef
    move-object/from16 v0, p0

    :goto_1f1
    return-object v0
.end method

.method public static enrichSeriesIfEmpty(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 9

    .line 45
    const-string v0, "getId"

    const-string v1, "getTrailers"

    const-string v2, "getVideos"

    if-nez p0, :cond_9

    return-object p1

    .line 49
    :cond_9
    :try_start_9
    invoke-static {p0, v2}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    .line 50
    const/4 v4, 0x0

    if-eqz p1, :cond_15

    invoke-static {p1, v2}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v2

    goto :goto_16

    :cond_15
    move-object v2, v4

    .line 52
    :goto_16
    const/4 v5, 0x1

    const/4 v6, 0x0

    if-eqz v3, :cond_20

    invoke-interface {v3}, Ljava/util/List;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_28

    :cond_20
    if-eqz v2, :cond_2a

    .line 53
    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_2a

    :cond_28
    const/4 v2, 0x1

    goto :goto_2b

    :cond_2a
    const/4 v2, 0x0

    .line 55
    :goto_2b
    invoke-static {p0, v1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    .line 56
    if-eqz p1, :cond_35

    invoke-static {p1, v1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v4

    .line 57
    :cond_35
    if-eqz v3, :cond_3d

    invoke-interface {v3}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_45

    :cond_3d
    if-eqz v4, :cond_46

    .line 58
    invoke-interface {v4}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_46

    :cond_45
    goto :goto_47

    :cond_46
    const/4 v5, 0x0

    .line 60
    :goto_47
    if-eqz v2, :cond_53

    if-eqz v5, :cond_53

    .line 62
    if-eqz p1, :cond_52

    .line 63
    invoke-static {p0, p1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->mergeAddonMetas(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 65
    :cond_52
    return-object p0

    .line 69
    :cond_53
    invoke-static {p0, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 70
    invoke-static {v1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->extractImdbId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 71
    if-nez v1, :cond_67

    if-eqz p1, :cond_67

    .line 72
    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->extractImdbId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 74
    :cond_67
    if-nez v1, :cond_6d

    .line 76
    if-eqz p1, :cond_6c

    move-object p0, p1

    :cond_6c
    return-object p0

    .line 80
    :cond_6d
    const-string v0, "series"

    invoke-static {v0, v1}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->fetchCinemetaMeta(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 81
    if-nez v0, :cond_79

    .line 83
    if-eqz p1, :cond_78

    move-object p0, p1

    :cond_78
    return-object p0

    .line 86
    :cond_79
    invoke-static {p0, p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->applyCinemetaToMeta(Ljava/lang/Object;Ljava/lang/Object;Lorg/json/JSONObject;)Ljava/lang/Object;

    move-result-object p0
    :try_end_7d
    .catchall {:try_start_9 .. :try_end_7d} :catchall_7e

    return-object p0

    .line 87
    :catchall_7e
    move-exception v0

    .line 88
    const-string v1, "SeriesEpisodeHelper"

    const-string v2, "enrichSeriesIfEmpty error"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 89
    if-eqz p1, :cond_89

    move-object p0, p1

    :cond_89
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

.method private static fetchCinemetaMeta(Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;
    .registers 6

    .line 128
    const/4 v0, 0x0

    :try_start_1
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "series"

    invoke-virtual {v2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_11

    const-string p0, "https://v3-cinemeta.strem.io/meta/series/"

    goto :goto_13

    :cond_11
    const-string p0, "https://v3-cinemeta.strem.io/meta/movie/"

    :goto_13
    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v1, ".json"

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 129
    new-instance v1, Ljava/net/URL;

    invoke-direct {v1, p0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 130
    invoke-virtual {v1}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    .line 131
    const-string v1, "GET"

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 132
    const/16 v1, 0x1f40

    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 133
    invoke-virtual {p0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 134
    const-string v1, "User-Agent"

    const-string v2, "Mozilla/5.0 StreamSync"

    invoke-virtual {p0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 136
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-eq v1, v2, :cond_4d

    .line 137
    return-object v0

    .line 140
    :cond_4d
    new-instance v1, Ljava/io/BufferedReader;

    new-instance v2, Ljava/io/InputStreamReader;

    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    const-string v3, "UTF-8"

    invoke-direct {v2, p0, v3}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/lang/String;)V

    invoke-direct {v1, v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 141
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 143
    :goto_62
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_6c

    .line 144
    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_62

    .line 146
    :cond_6c
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    .line 148
    new-instance v1, Lorg/json/JSONObject;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v1, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 149
    const-string p0, "meta"

    invoke-virtual {v1, p0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0
    :try_end_7e
    .catchall {:try_start_1 .. :try_end_7e} :catchall_7f

    return-object p0

    .line 150
    :catchall_7f
    move-exception p0

    .line 151
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

    .line 152
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

    .line 327
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    .line 329
    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    .line 330
    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 331
    instance-of p1, p0, Ljava/util/List;

    if-eqz p1, :cond_1d

    .line 332
    check-cast p0, Ljava/util/List;
    :try_end_1b
    .catchall {:try_start_4 .. :try_end_1b} :catchall_1c

    return-object p0

    .line 334
    :catchall_1c
    move-exception p0

    :cond_1d
    nop

    .line 335
    return-object v0
.end method

.method private static getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;
    .registers 6

    .line 339
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return-object v0

    .line 341
    :cond_4
    :try_start_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Class;

    invoke-virtual {v1, p1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object p1

    .line 342
    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 343
    instance-of p1, p0, Ljava/lang/String;

    if-eqz p1, :cond_1d

    .line 344
    check-cast p0, Ljava/lang/String;
    :try_end_1b
    .catchall {:try_start_4 .. :try_end_1b} :catchall_1c

    return-object p0

    .line 346
    :catchall_1c
    move-exception p0

    :cond_1d
    nop

    .line 347
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

    .line 266
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    .line 273
    invoke-virtual {v0}, Ljava/lang/Class;->getMethods()[Ljava/lang/reflect/Method;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    const/4 v3, 0x0

    :goto_b
    if-ge v3, v1, :cond_96

    aget-object v4, v0, v3

    .line 274
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

    .line 275
    new-array v0, v6, [Ljava/lang/Object;

    .line 276
    aput-object p0, v0, v2

    .line 279
    nop

    .line 282
    if-eqz p5, :cond_32

    .line 283
    const/4 p0, 0x6

    aput-object p5, v0, p0

    .line 284
    const p0, 0x1ffffdf

    goto :goto_35

    .line 282
    :cond_32
    const p0, 0x1ffffff

    .line 287
    :goto_35
    if-eqz p6, :cond_3c

    .line 288
    const/4 p5, 0x7

    aput-object p6, v0, p5

    .line 289
    and-int/lit8 p0, p0, -0x41

    .line 292
    :cond_3c
    if-eqz p7, :cond_44

    .line 293
    const/16 p5, 0xc

    aput-object p7, v0, p5

    .line 294
    and-int/lit16 p0, p0, -0x801

    .line 297
    :cond_44
    if-eqz p1, :cond_52

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result p5

    if-nez p5, :cond_52

    .line 298
    const/16 p5, 0xd

    aput-object p1, v0, p5

    .line 299
    and-int/lit16 p0, p0, -0x1001

    .line 302
    :cond_52
    if-eqz p4, :cond_60

    invoke-interface {p4}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_60

    .line 303
    const/16 p1, 0xe

    aput-object p4, v0, p1

    .line 304
    and-int/lit16 p0, p0, -0x2001

    .line 307
    :cond_60
    if-eqz p3, :cond_70

    invoke-interface {p3}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_70

    .line 308
    const/16 p1, 0x10

    aput-object p3, v0, p1

    .line 309
    const p1, -0x8001

    and-int/2addr p0, p1

    .line 312
    :cond_70
    if-eqz p2, :cond_80

    invoke-interface {p2}, Ljava/util/List;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_80

    .line 313
    const/16 p1, 0x16

    aput-object p2, v0, p1

    .line 314
    const p1, -0x200001

    and-int/2addr p0, p1

    .line 317
    :cond_80
    const/16 p1, 0x1a

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    aput-object p0, v0, p1

    .line 318
    const/16 p0, 0x1b

    const/4 p1, 0x0

    aput-object p1, v0, p0

    .line 320
    invoke-virtual {v4, p1, v0}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 273
    :cond_92
    add-int/lit8 v3, v3, 0x1

    goto/16 :goto_b

    .line 323
    :cond_96
    return-object p0
.end method

.method private static mergeAddonMetas(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 11

    .line 242
    :try_start_0
    const-string v0, "getVideos"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v2

    .line 243
    const-string v0, "getTrailers"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    .line 244
    const-string v0, "getCast"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v4

    .line 245
    const-string v0, "getGenres"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getListField(Ljava/lang/Object;Ljava/lang/String;)Ljava/util/List;

    move-result-object v5

    .line 246
    const-string v0, "getDescription"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 247
    const-string v0, "getBackground"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 248
    const-string v0, "getImdbRating"

    invoke-static {p1, v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->getStringField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 250
    move-object v1, p0

    invoke-static/range {v1 .. v8}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->invokeCopyDefault(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0
    :try_end_2f
    .catchall {:try_start_0 .. :try_end_2f} :catchall_30

    return-object p0

    .line 251
    :catchall_30
    move-exception p1

    .line 252
    return-object p0
.end method

.method public static resolveTrailerId(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    return-object p0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method
