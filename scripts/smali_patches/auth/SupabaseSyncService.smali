.class public Lcom/streamsync/app/auth/SupabaseSyncService;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;,
        Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;,
        Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;
    }
.end annotation


# static fields
.field public static final DEFAULT_ANON_KEY:Ljava/lang/String; = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

.field public static final DEFAULT_SERVER_URL:Ljava/lang/String; = "https://api.nuvio.tv"

.field private static final sMainHandler:Landroid/os/Handler;


# direct methods
.method static bridge synthetic -$$Nest$smreadStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .registers 2

    .line 53
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 33
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static exchangeAndSaveSession(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    .registers 19

    .line 306
    const-string v0, "user"

    const-string v1, "refresh_token"

    const-string v2, "Authorization"

    const-string v3, "apikey"

    const-string v4, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    const-string v5, ""

    const/4 v6, 0x0

    :try_start_d
    new-instance v7, Ljava/net/URL;

    const-string v8, "https://api.nuvio.tv/functions/v1/tv-logins-exchange"

    invoke-direct {v7, v8}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 307
    invoke-virtual {v7}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v7

    check-cast v7, Ljava/net/HttpURLConnection;

    .line 308
    const-string v8, "POST"

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 309
    const-string v8, "Content-Type"

    const-string v9, "application/json"

    invoke-virtual {v7, v8, v9}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 310
    invoke-virtual {v7, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 311
    const-string v8, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v7, v2, v8}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 312
    const/16 v8, 0x2710

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 313
    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 314
    const/4 v8, 0x1

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 316
    new-instance v9, Lorg/json/JSONObject;

    invoke-direct {v9}, Lorg/json/JSONObject;-><init>()V

    .line 317
    const-string v10, "code"

    move-object/from16 v11, p1

    invoke-virtual {v9, v10, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 318
    const-string v10, "device_nonce"

    move-object/from16 v11, p2

    invoke-virtual {v9, v10, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 320
    invoke-virtual {v9}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v9

    sget-object v10, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v9, v10}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v9

    .line 321
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v10
    :try_end_5b
    .catchall {:try_start_d .. :try_end_5b} :catchall_153

    .line 322
    :try_start_5b
    invoke-virtual {v10, v9}, Ljava/io/OutputStream;->write([B)V
    :try_end_5e
    .catchall {:try_start_5b .. :try_end_5e} :catchall_145

    .line 323
    if-eqz v10, :cond_63

    :try_start_60
    invoke-virtual {v10}, Ljava/io/OutputStream;->close()V

    .line 325
    :cond_63
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v9

    .line 326
    const/16 v10, 0xc8

    if-ne v9, v10, :cond_154

    .line 327
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v7

    invoke-static {v7}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v7

    .line 328
    new-instance v9, Lorg/json/JSONObject;

    invoke-direct {v9, v7}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 329
    const-string v7, "access_token"

    invoke-virtual {v9, v7, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 330
    invoke-virtual {v9, v1, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 331
    nop

    .line 332
    nop

    .line 334
    invoke-virtual {v9, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v12
    :try_end_88
    .catchall {:try_start_60 .. :try_end_88} :catchall_153

    const-string v13, "id"

    const-string v14, "email"

    if-eqz v12, :cond_9d

    .line 335
    :try_start_8e
    invoke-virtual {v9, v0}, Lorg/json/JSONObject;->getJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 336
    invoke-virtual {v0, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 337
    invoke-virtual {v0, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v12, v9

    move-object v9, v0

    goto :goto_9f

    .line 334
    :cond_9d
    move-object v9, v5

    move-object v12, v9

    .line 340
    :goto_9f
    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_f9

    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_a9
    .catchall {:try_start_8e .. :try_end_a9} :catchall_153

    if-nez v0, :cond_f9

    .line 342
    :try_start_ab
    new-instance v0, Ljava/net/URL;

    const-string v15, "https://api.nuvio.tv/auth/v1/user"

    invoke-direct {v0, v15}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 343
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 344
    const-string v15, "GET"

    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 345
    invoke-virtual {v0, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 346
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v15, "Bearer "

    invoke-virtual {v3, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 347
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v2

    if-ne v2, v10, :cond_f8

    .line 348
    new-instance v2, Lorg/json/JSONObject;

    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 349
    invoke-virtual {v2, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3
    :try_end_ed
    .catchall {:try_start_ab .. :try_end_ed} :catchall_f7

    .line 350
    :try_start_ed
    invoke-virtual {v2, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_f1
    .catchall {:try_start_ed .. :try_end_f1} :catchall_f4

    move-object v9, v0

    move-object v12, v3

    goto :goto_f8

    .line 352
    :catchall_f4
    move-exception v0

    move-object v12, v3

    goto :goto_f8

    :catchall_f7
    move-exception v0

    :cond_f8
    :goto_f8
    nop

    .line 355
    :cond_f9
    :try_start_f9
    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_101

    const-string v12, "T\u00e0i kho\u1ea3n Nuvio"

    .line 357
    :cond_101
    const-string v0, "nuvio_supabase_auth"

    move-object/from16 v2, p0

    invoke-virtual {v2, v0, v6}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 358
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "server_url"

    const-string v3, "https://api.nuvio.tv"

    .line 359
    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "anon_key"

    .line 360
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_token"

    .line 361
    invoke-interface {v0, v2, v7}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 362
    invoke-interface {v0, v1, v11}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "auth_email"

    .line 363
    invoke-interface {v0, v1, v12}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "auth_user_id"

    .line 364
    invoke-interface {v0, v1, v9}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "is_anonymous"

    .line 365
    invoke-interface {v0, v1, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "last_login_time"

    .line 366
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 367
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_144
    .catchall {:try_start_f9 .. :try_end_144} :catchall_153

    .line 368
    return v8

    .line 321
    :catchall_145
    move-exception v0

    move-object v1, v0

    if-eqz v10, :cond_152

    :try_start_149
    invoke-virtual {v10}, Ljava/io/OutputStream;->close()V
    :try_end_14c
    .catchall {:try_start_149 .. :try_end_14c} :catchall_14d

    goto :goto_152

    :catchall_14d
    move-exception v0

    move-object v2, v0

    :try_start_14f
    invoke-static {v1, v2}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_152
    :goto_152
    throw v1
    :try_end_153
    .catchall {:try_start_14f .. :try_end_153} :catchall_153

    .line 370
    :catchall_153
    move-exception v0

    :cond_154
    nop

    .line 371
    return v6
.end method

.method public static fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    .line 83
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 84
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const-string v1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    if-eqz v0, :cond_d

    return-object v1

    .line 86
    :cond_d
    :try_start_d
    new-instance v0, Ljava/net/URL;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    const-string v2, "/.well-known/nuvio"

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 87
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    .line 88
    const-string v0, "GET"

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 89
    const/16 v0, 0x1770

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 90
    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 91
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v0

    .line 92
    const/16 v2, 0xc8

    if-ne v0, v2, :cond_5d

    .line 93
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 94
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 95
    const-string p0, "publishable_key"

    const-string v2, ""

    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 96
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_59
    .catchall {:try_start_d .. :try_end_59} :catchall_5c

    if-nez v0, :cond_5d

    .line 97
    return-object p0

    .line 100
    :catchall_5c
    move-exception p0

    :cond_5d
    nop

    .line 101
    return-object v1
.end method

.method private static getDefaultManifestFallback(Ljava/lang/String;)Lorg/json/JSONObject;
    .registers 15

    .line 989
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 990
    const-string v1, "cinemeta"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1
    :try_end_b
    .catchall {:try_start_0 .. :try_end_b} :catchall_131

    const-string v2, "logo"

    const-string v3, "[\"tt\"]"

    const-string v4, "idPrefixes"

    const-string v5, "resources"

    const-string v6, "catalogs"

    const-string v7, "[\"movie\",\"series\"]"

    const-string v8, "types"

    const-string v9, "description"

    const-string v10, "version"

    const-string v11, "name"

    const-string v12, "id"

    if-eqz v1, :cond_61

    .line 991
    :try_start_23
    const-string p0, "community.cinemeta"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 992
    const-string p0, "Cinemeta"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 993
    const-string p0, "3.0.12"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 994
    const-string p0, "Cung c\u1ea5p danh m\u1ee5c v\u00e0 si\u00eau d\u1eef li\u1ec7u ch\u00ednh th\u1ee9c cho phim & TV series"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 995
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 996
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"type\":\"movie\",\"id\":\"top\",\"name\":\"Top Phim\"},{\"type\":\"series\",\"id\":\"top\",\"name\":\"Top Series\"}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 997
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 998
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 999
    const-string p0, "https://v3-cinemeta.strem.io/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1000
    return-object v0

    .line 1001
    :cond_61
    const-string v1, "opensubtitles"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1
    :try_end_67
    .catchall {:try_start_23 .. :try_end_67} :catchall_131

    const-string v13, "[]"

    if-eqz v1, :cond_a7

    .line 1002
    :try_start_6b
    const-string p0, "org.stremio.opensubtitles-v3"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1003
    const-string p0, "OpenSubtitles v3"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1004
    const-string p0, "1.0.0"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1005
    const-string p0, "Ph\u1ee5 \u0111\u1ec1 \u0111a ng\u00f4n ng\u1eef ch\u00ednh th\u1ee9c t\u1eeb OpenSubtitles"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1006
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1007
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v13}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1008
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"subtitles\",\"types\":[\"movie\",\"series\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1009
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1010
    const-string p0, "https://opensubtitles-v3.strem.io/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1011
    return-object v0

    .line 1012
    :cond_a7
    const-string v1, "cyberflix"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_ed

    .line 1013
    const-string p0, "tv.cyberflix.catalog"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1014
    const-string p0, "Cyberflix Catalog"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1015
    const-string p0, "1.5.0"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1016
    const-string p0, "Danh m\u1ee5c phim v\u00e0 series t\u1eeb c\u00e1c n\u1ec1n t\u1ea3ng tr\u1ef1c tuy\u1ebfn ph\u1ed5 bi\u1ebfn"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1017
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1018
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"type\":\"movie\",\"id\":\"cyberflix_netflix_movies\",\"name\":\"Netflix Phim\"},{\"type\":\"series\",\"id\":\"cyberflix_netflix_series\",\"name\":\"Netflix Series\"}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1019
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1020
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1021
    const-string p0, ""

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1022
    return-object v0

    .line 1023
    :cond_ed
    const-string v1, "torrentio"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_132

    .line 1024
    const-string p0, "com.stremio.torrentio"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1025
    const-string p0, "Torrentio"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1026
    const-string p0, "1.0.13"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1027
    const-string p0, "Ngu\u1ed3n ph\u00e1t torrent v\u00e0 debrid \u0111a d\u1ea1ng"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1028
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1029
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v13}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1030
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"stream\",\"types\":[\"movie\",\"series\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1031
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1032
    const-string p0, "https://torrentio.strem.fun/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_130
    .catchall {:try_start_6b .. :try_end_130} :catchall_131

    .line 1033
    return-object v0

    .line 1035
    :catchall_131
    move-exception p0

    :cond_132
    nop

    .line 1036
    const/4 p0, 0x0

    return-object p0
.end method

.method public static installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I
    .registers 28
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)I"
        }
    .end annotation

    .line 869
    const-string v1, "idPrefixes"

    const-string v2, "resources"

    const-string v3, "catalogs"

    const-string v4, "types"

    const-string v5, "description"

    const-string v6, "version"

    const-string v7, "name"

    const-string v8, "id"

    const-string v9, "/manifest.json"

    .line 870
    const-string v0, "streamsync.db"

    move-object/from16 v10, p0

    invoke-virtual {v10, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 871
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v10

    invoke-virtual {v10}, Ljava/io/File;->exists()Z

    move-result v10

    if-nez v10, :cond_2b

    .line 872
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v10

    invoke-virtual {v10}, Ljava/io/File;->mkdirs()Z

    .line 874
    :cond_2b
    if-eqz p1, :cond_24d

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->isEmpty()Z

    move-result v11

    if-eqz v11, :cond_35

    goto/16 :goto_24d

    .line 876
    :cond_35
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/high16 v11, 0x10000000

    const/4 v12, 0x0

    invoke-static {v0, v12, v11}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v11

    .line 878
    :try_start_40
    const-string v0, "CREATE TABLE IF NOT EXISTS `installed_addons` (`baseUrl` TEXT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `version` TEXT NOT NULL, `description` TEXT, `typesCsv` TEXT NOT NULL, `catalogsJson` TEXT NOT NULL, `resourcesJson` TEXT NOT NULL, `idPrefixesCsv` TEXT NOT NULL, `logoUrl` TEXT, `sortOrder` INTEGER NOT NULL, PRIMARY KEY(`baseUrl`));"

    invoke-virtual {v11, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 880
    invoke-virtual {v11}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 881
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v13

    const/4 v14, 0x0

    :goto_4d
    invoke-interface {v13}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_234

    invoke-interface {v13}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 882
    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 883
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v15

    if-eqz v15, :cond_67

    move-object/from16 v16, v9

    goto/16 :goto_ea

    .line 885
    :cond_67
    invoke-virtual {v0, v9}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v15
    :try_end_6b
    .catchall {:try_start_40 .. :try_end_6b} :catchall_244

    const-string v12, "/+$"

    const-string v10, ""

    if-eqz v15, :cond_72

    :goto_71
    goto :goto_88

    :cond_72
    :try_start_72
    invoke-virtual {v0, v12, v10}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v15, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_71

    .line 886
    :goto_88
    nop

    .line 887
    invoke-virtual {v0, v9}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v15

    .line 888
    move-object/from16 v16, v9

    const/4 v9, -0x1

    if-eq v15, v9, :cond_98

    .line 889
    const/4 v9, 0x0

    invoke-virtual {v0, v9, v15}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v15

    goto :goto_99

    .line 888
    :cond_98
    move-object v15, v0

    .line 891
    :goto_99
    invoke-virtual {v15, v12, v10}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9
    :try_end_9d
    .catchall {:try_start_72 .. :try_end_9d} :catchall_244

    .line 893
    nop

    .line 895
    :try_start_9e
    new-instance v12, Ljava/net/URL;

    invoke-direct {v12, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 896
    invoke-virtual {v12}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 897
    const-string v12, "GET"

    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 898
    const-string v12, "User-Agent"

    const-string v15, "Mozilla/5.0 StreamSync"

    invoke-virtual {v0, v12, v15}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 899
    const/16 v12, 0x1f40

    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 900
    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 901
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v12

    const/16 v15, 0xc8

    if-ne v12, v15, :cond_d3

    .line 902
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 903
    new-instance v12, Lorg/json/JSONObject;

    invoke-direct {v12, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
    :try_end_d2
    .catchall {:try_start_9e .. :try_end_d2} :catchall_d5

    goto :goto_d4

    .line 901
    :cond_d3
    const/4 v12, 0x0

    .line 905
    :goto_d4
    goto :goto_d7

    :catchall_d5
    move-exception v0

    const/4 v12, 0x0

    .line 907
    :goto_d7
    if-nez v12, :cond_dd

    .line 908
    :try_start_d9
    invoke-static {v9}, Lcom/streamsync/app/auth/SupabaseSyncService;->getDefaultManifestFallback(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v12

    .line 911
    :cond_dd
    if-nez v12, :cond_e0

    goto :goto_ea

    .line 913
    :cond_e0
    invoke-virtual {v12, v8, v10}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 914
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v15

    if-eqz v15, :cond_ef

    .line 881
    :goto_ea
    move-object/from16 v9, v16

    const/4 v12, 0x0

    goto/16 :goto_4d

    .line 916
    :cond_ef
    invoke-virtual {v12, v7, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15

    .line 917
    move-object/from16 p1, v13

    const-string v13, "1.0.0"

    invoke-virtual {v12, v6, v13}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13
    :try_end_fb
    .catchall {:try_start_d9 .. :try_end_fb} :catchall_244

    .line 918
    move-object/from16 v17, v11

    :try_start_fd
    invoke-virtual {v12, v5, v10}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 919
    move/from16 v18, v14

    const-string v14, "logo"

    move-object/from16 v19, v5

    const-string v5, "icon"

    invoke-virtual {v12, v5, v10}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v12, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 921
    const-string v14, "movie,series"

    .line 922
    invoke-virtual {v12, v4}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v20
    :try_end_117
    .catchall {:try_start_fd .. :try_end_117} :catchall_230

    move-object/from16 v21, v10

    const-string v10, ","

    if-eqz v20, :cond_15a

    .line 923
    move-object/from16 v20, v14

    :try_start_11f
    invoke-virtual {v12, v4}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v14

    .line 924
    if-eqz v14, :cond_153

    invoke-virtual {v14}, Lorg/json/JSONArray;->length()I

    move-result v22

    if-lez v22, :cond_153

    .line 925
    move-object/from16 v22, v4

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    .line 926
    move-object/from16 v23, v5

    const/4 v5, 0x0

    :goto_135
    move-object/from16 v24, v11

    invoke-virtual {v14}, Lorg/json/JSONArray;->length()I

    move-result v11

    if-ge v5, v11, :cond_14e

    .line 927
    if-lez v5, :cond_142

    invoke-virtual {v4, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 928
    :cond_142
    invoke-virtual {v14, v5}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 926
    add-int/lit8 v5, v5, 0x1

    move-object/from16 v11, v24

    goto :goto_135

    .line 930
    :cond_14e
    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    goto :goto_164

    .line 924
    :cond_153
    move-object/from16 v22, v4

    move-object/from16 v23, v5

    move-object/from16 v24, v11

    goto :goto_162

    .line 922
    :cond_15a
    move-object/from16 v22, v4

    move-object/from16 v23, v5

    move-object/from16 v24, v11

    move-object/from16 v20, v14

    .line 934
    :goto_162
    move-object/from16 v14, v20

    :goto_164
    nop

    .line 935
    invoke-virtual {v12, v3}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v4
    :try_end_169
    .catchall {:try_start_11f .. :try_end_169} :catchall_230

    const-string v5, "[]"

    if-eqz v4, :cond_178

    .line 936
    :try_start_16d
    invoke-virtual {v12, v3}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    .line 937
    if-eqz v4, :cond_178

    invoke-virtual {v4}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    goto :goto_179

    .line 940
    :cond_178
    move-object v4, v5

    :goto_179
    nop

    .line 941
    invoke-virtual {v12, v2}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_18a

    .line 942
    invoke-virtual {v12, v2}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v11

    .line 943
    if-eqz v11, :cond_18a

    invoke-virtual {v11}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v5

    .line 946
    :cond_18a
    nop

    .line 947
    invoke-virtual {v12, v1}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_1c8

    .line 948
    invoke-virtual {v12, v1}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v11

    .line 949
    if-eqz v11, :cond_1c3

    invoke-virtual {v11}, Lorg/json/JSONArray;->length()I

    move-result v12

    if-lez v12, :cond_1c3

    .line 950
    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    .line 951
    move-object/from16 v20, v1

    const/4 v1, 0x0

    :goto_1a5
    move-object/from16 v25, v2

    invoke-virtual {v11}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v1, v2, :cond_1be

    .line 952
    if-lez v1, :cond_1b2

    invoke-virtual {v12, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 953
    :cond_1b2
    invoke-virtual {v11, v1}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 951
    add-int/lit8 v1, v1, 0x1

    move-object/from16 v2, v25

    goto :goto_1a5

    .line 955
    :cond_1be
    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    goto :goto_1ce

    .line 949
    :cond_1c3
    move-object/from16 v20, v1

    move-object/from16 v25, v2

    goto :goto_1cc

    .line 947
    :cond_1c8
    move-object/from16 v20, v1

    move-object/from16 v25, v2

    .line 959
    :goto_1cc
    move-object/from16 v10, v21

    :goto_1ce
    new-instance v1, Landroid/content/ContentValues;

    invoke-direct {v1}, Landroid/content/ContentValues;-><init>()V

    .line 960
    const-string v2, "baseUrl"

    invoke-virtual {v1, v2, v9}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 961
    invoke-virtual {v1, v8, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 962
    invoke-virtual {v1, v7, v15}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 963
    invoke-virtual {v1, v6, v13}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 964
    move-object/from16 v2, v19

    move-object/from16 v0, v24

    invoke-virtual {v1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 965
    const-string v0, "typesCsv"

    invoke-virtual {v1, v0, v14}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 966
    const-string v0, "catalogsJson"

    invoke-virtual {v1, v0, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 967
    const-string v0, "resourcesJson"

    invoke-virtual {v1, v0, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 968
    const-string v0, "idPrefixesCsv"

    invoke-virtual {v1, v0, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 969
    invoke-virtual/range {v23 .. v23}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_200
    .catchall {:try_start_16d .. :try_end_200} :catchall_230

    const-string v4, "logoUrl"

    if-nez v0, :cond_20a

    .line 970
    move-object/from16 v0, v23

    :try_start_206
    invoke-virtual {v1, v4, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_20d

    .line 972
    :cond_20a
    invoke-virtual {v1, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 974
    :goto_20d
    const-string v0, "sortOrder"

    invoke-static/range {v18 .. v18}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v1, v0, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 976
    const-string v0, "installed_addons"
    :try_end_218
    .catchall {:try_start_206 .. :try_end_218} :catchall_230

    const/4 v4, 0x5

    move-object/from16 v5, v17

    const/4 v9, 0x0

    :try_start_21c
    invoke-virtual {v5, v0, v9, v1, v4}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 977
    add-int/lit8 v14, v18, 0x1

    .line 978
    move-object/from16 v13, p1

    move-object v11, v5

    move-object v12, v9

    move-object/from16 v9, v16

    move-object/from16 v1, v20

    move-object/from16 v4, v22

    move-object v5, v2

    move-object/from16 v2, v25

    goto/16 :goto_4d

    .line 981
    :catchall_230
    move-exception v0

    move-object/from16 v5, v17

    goto :goto_246

    .line 979
    :cond_234
    move-object v5, v11

    move/from16 v18, v14

    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_23a
    .catchall {:try_start_21c .. :try_end_23a} :catchall_242

    .line 981
    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 982
    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 983
    nop

    .line 984
    return v18

    .line 981
    :catchall_242
    move-exception v0

    goto :goto_246

    :catchall_244
    move-exception v0

    move-object v5, v11

    :goto_246
    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 982
    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 983
    throw v0

    .line 874
    :cond_24d
    :goto_24d
    const/4 v1, 0x0

    return v1
.end method

.method public static installDefaultAddonsFallback(Landroid/content/Context;)I
    .registers 3

    .line 1040
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 1041
    const-string v1, "https://v3-cinemeta.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1042
    const-string v1, "https://opensubtitles-v3.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1043
    const-string v1, "https://catalog.cyberflix.tv/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1044
    const-string v1, "https://torrentio.strem.fun/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1045
    invoke-static {p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result p0

    return p0
.end method

.method public static login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 13

    .line 105
    new-instance v0, Ljava/lang/Thread;

    new-instance v7, Lcom/streamsync/app/auth/SupabaseSyncService$2;

    move-object v1, v7

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    move-object v5, p0

    move-object v6, p4

    invoke-direct/range {v1 .. v6}, Lcom/streamsync/app/auth/SupabaseSyncService$2;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V

    invoke-direct {v0, v7}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 194
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 195
    return-void
.end method

.method public static normalizeUrl(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    .line 71
    if-nez p0, :cond_5

    const-string p0, ""

    return-object p0

    .line 72
    :cond_5
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 73
    :goto_9
    const-string v0, "/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1d

    .line 74
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    const/4 v1, 0x0

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    goto :goto_9

    .line 76
    :cond_1d
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_44

    const-string v0, "http://"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_44

    const-string v0, "https://"

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_44

    .line 77
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 79
    :cond_44
    return-object p0
.end method

.method public static pollDeviceStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    .line 273
    :try_start_0
    new-instance v0, Ljava/net/URL;

    const-string v1, "https://api.nuvio.tv/rest/v1/rpc/poll_tv_login_session"

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 274
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 275
    const-string v1, "POST"

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 276
    const-string v1, "Content-Type"

    const-string v2, "application/json"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 277
    const-string v1, "apikey"

    const-string v2, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 278
    const-string v1, "Authorization"

    const-string v2, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 279
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 280
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 281
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 283
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    .line 284
    const-string v2, "p_code"

    invoke-virtual {v1, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 285
    const-string p0, "p_device_nonce"

    invoke-virtual {v1, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 287
    invoke-virtual {v1}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p0

    sget-object p1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p0, p1}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p0

    .line 288
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p1
    :try_end_50
    .catchall {:try_start_0 .. :try_end_50} :catchall_95

    .line 289
    :try_start_50
    invoke-virtual {p1, p0}, Ljava/io/OutputStream;->write([B)V
    :try_end_53
    .catchall {:try_start_50 .. :try_end_53} :catchall_89

    .line 290
    if-eqz p1, :cond_58

    :try_start_55
    invoke-virtual {p1}, Ljava/io/OutputStream;->close()V

    .line 292
    :cond_58
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result p0

    .line 293
    const/16 p1, 0xc8

    if-ne p0, p1, :cond_96

    .line 294
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 295
    new-instance p1, Lorg/json/JSONArray;

    invoke-direct {p1, p0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 296
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result p0

    if-lez p0, :cond_96

    .line 297
    const/4 p0, 0x0

    invoke-virtual {p1, p0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object p0

    const-string p1, "status"

    const-string v0, ""

    invoke-virtual {p0, p1, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0
    :try_end_88
    .catchall {:try_start_55 .. :try_end_88} :catchall_95

    return-object p0

    .line 288
    :catchall_89
    move-exception p0

    if-eqz p1, :cond_94

    :try_start_8c
    invoke-virtual {p1}, Ljava/io/OutputStream;->close()V
    :try_end_8f
    .catchall {:try_start_8c .. :try_end_8f} :catchall_90

    goto :goto_94

    :catchall_90
    move-exception p1

    :try_start_91
    invoke-static {p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_94
    :goto_94
    throw p0
    :try_end_95
    .catchall {:try_start_91 .. :try_end_95} :catchall_95

    .line 300
    :catchall_95
    move-exception p0

    :cond_96
    nop

    .line 301
    const-string p0, "error"

    return-object p0
.end method

.method private static populateContinueWatchingCache(Landroid/content/Context;Ljava/util/List;)V
    .registers 38
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Lorg/json/JSONObject;",
            ">;)V"
        }
    .end annotation

    .line 680
    move-object/from16 v0, p0

    const-string v1, "INSTANCE"

    const-string v2, "episode"

    const-string v3, "season"

    const-string v4, ""

    if-eqz v0, :cond_39a

    if-eqz p1, :cond_39a

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_16

    goto/16 :goto_39a

    .line 682
    :cond_16
    :try_start_16
    const-string v5, "streamsync.db"

    invoke-virtual {v0, v5}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v5

    .line 683
    invoke-virtual {v5}, Ljava/io/File;->exists()Z

    move-result v6

    if-nez v6, :cond_23

    return-void

    .line 685
    :cond_23
    new-instance v6, Ljava/util/HashMap;

    invoke-direct {v6}, Ljava/util/HashMap;-><init>()V

    .line 686
    invoke-virtual {v5}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v5

    const/4 v7, 0x1

    const/4 v8, 0x0

    invoke-static {v5, v8, v7}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v5
    :try_end_32
    .catchall {:try_start_16 .. :try_end_32} :catchall_397

    .line 688
    :try_start_32
    const-string v9, "SELECT mediaId, title, posterUrl, backgroundUrl, description, type, releaseInfo, imdbRating FROM library_items"

    invoke-virtual {v5, v9, v8}, Landroid/database/sqlite/SQLiteDatabase;->rawQuery(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v9
    :try_end_38
    .catchall {:try_start_32 .. :try_end_38} :catchall_392

    .line 689
    const-string v10, "imdbRating"

    const-string v11, "releaseInfo"

    const-string v12, "type"

    const-string v13, "description"

    const-string v14, "backgroundUrl"

    const-string v15, "posterUrl"

    const-string v8, "title"

    const/4 v7, 0x0

    if-eqz v9, :cond_a7

    .line 690
    :goto_49
    :try_start_49
    invoke-interface {v9}, Landroid/database/Cursor;->moveToNext()Z

    move-result v17

    if-eqz v17, :cond_9f

    .line 691
    invoke-interface {v9, v7}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 692
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 693
    move-object/from16 v18, v1

    move-object/from16 v19, v2

    const/4 v1, 0x1

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v7, v8, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 694
    const/4 v1, 0x2

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v7, v15, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 695
    const/4 v1, 0x3

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v7, v14, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 696
    const/4 v1, 0x4

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v7, v13, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 697
    const/4 v1, 0x5

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v7, v12, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 698
    const/4 v1, 0x6

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v7, v11, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 699
    const/4 v1, 0x7

    invoke-interface {v9, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v7, v10, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 700
    invoke-interface {v6, v0, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 701
    move-object/from16 v0, p0

    move-object/from16 v1, v18

    move-object/from16 v2, v19

    const/4 v7, 0x0

    goto :goto_49

    .line 702
    :cond_9f
    move-object/from16 v18, v1

    move-object/from16 v19, v2

    invoke-interface {v9}, Landroid/database/Cursor;->close()V
    :try_end_a6
    .catchall {:try_start_49 .. :try_end_a6} :catchall_392

    goto :goto_ab

    .line 689
    :cond_a7
    move-object/from16 v18, v1

    move-object/from16 v19, v2

    .line 705
    :goto_ab
    :try_start_ab
    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 706
    nop

    .line 708
    new-instance v0, Lorg/json/JSONArray;

    invoke-direct {v0}, Lorg/json/JSONArray;-><init>()V

    .line 709
    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    .line 711
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_bd
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_303

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/json/JSONObject;

    .line 712
    const-string v7, "content_id"

    invoke-virtual {v5, v7, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 713
    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v9

    if-eqz v9, :cond_db

    const-string v7, "contentId"

    invoke-virtual {v5, v7, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 714
    :cond_db
    const-string v9, "video_id"

    invoke-virtual {v5, v9, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 715
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v20

    if-eqz v20, :cond_ed

    const-string v9, "videoId"

    invoke-virtual {v5, v9, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 716
    :cond_ed
    move-object/from16 v20, v0

    move-object/from16 p1, v2

    const/4 v2, 0x0

    invoke-virtual {v5, v3, v2}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v0

    .line 717
    move-object/from16 v21, v3

    move-object/from16 v3, v19

    move-object/from16 v19, v10

    invoke-virtual {v5, v3, v2}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v10

    .line 719
    nop

    .line 720
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_10d

    const-string v2, "progress_key"

    invoke-virtual {v5, v2, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 721
    :cond_10d
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_114

    move-object v9, v7

    .line 722
    :cond_114
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_120

    const-string v2, "mediaId"

    invoke-virtual {v5, v2, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9
    :try_end_120
    .catchall {:try_start_ab .. :try_end_120} :catchall_397

    .line 723
    :cond_120
    const-string v2, ":"

    if-lez v0, :cond_149

    if-lez v10, :cond_149

    :try_start_126
    invoke-virtual {v9, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v22

    if-nez v22, :cond_149

    .line 724
    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v9, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    .line 726
    :cond_149
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v22

    if-nez v22, :cond_2e8

    invoke-interface {v1, v9}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v22

    if-eqz v22, :cond_161

    move-object/from16 v2, p1

    move-object/from16 v10, v19

    move-object/from16 v0, v20

    move-object/from16 v19, v3

    move-object/from16 v3, v21

    goto/16 :goto_bd

    .line 727
    :cond_161
    invoke-interface {v1, v9}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 730
    invoke-interface {v6, v9}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v22

    check-cast v22, Lorg/json/JSONObject;

    .line 731
    if-nez v22, :cond_17d

    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v23

    if-nez v23, :cond_17d

    .line 732
    invoke-interface {v6, v7}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    move-object/from16 v22, v7

    check-cast v22, Lorg/json/JSONObject;

    move-object/from16 v7, v22

    goto :goto_17f

    .line 735
    :cond_17d
    move-object/from16 v7, v22

    :goto_17f
    if-eqz v7, :cond_18e

    invoke-virtual {v7, v8, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v22

    move-object/from16 v23, v6

    move-object/from16 v35, v22

    move-object/from16 v22, v1

    move-object/from16 v1, v35

    goto :goto_19c

    :cond_18e
    move-object/from16 v22, v1

    const-string v1, "name"

    move-object/from16 v23, v6

    invoke-virtual {v5, v8, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v1, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 736
    :goto_19c
    if-eqz v7, :cond_1a7

    const/4 v6, 0x0

    invoke-virtual {v7, v15, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    move-object/from16 v24, v3

    move-object v3, v6

    goto :goto_1b1

    :cond_1a7
    const/4 v6, 0x0

    const-string v6, "poster"

    move-object/from16 v24, v3

    const/4 v3, 0x0

    invoke-virtual {v5, v6, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    :goto_1b1
    move-object/from16 v6, v16

    .line 737
    if-eqz v7, :cond_1be

    invoke-virtual {v7, v14, v3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    move/from16 v25, v10

    move-object/from16 v3, v16

    goto :goto_1c7

    :cond_1be
    const-string v3, "background"

    move/from16 v25, v10

    const/4 v10, 0x0

    invoke-virtual {v5, v3, v10}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 738
    :goto_1c7
    if-eqz v7, :cond_1ce

    invoke-virtual {v7, v13, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    goto :goto_1cf

    :cond_1ce
    move-object v10, v4

    .line 739
    :goto_1cf
    if-eqz v7, :cond_1dc

    invoke-virtual {v7, v12, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v26

    move-object/from16 v35, v26

    move-object/from16 v26, v13

    move-object/from16 v13, v35

    goto :goto_1e4

    :cond_1dc
    move-object/from16 v26, v13

    const-string v13, "content_type"

    invoke-virtual {v5, v13, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13

    .line 740
    :goto_1e4
    if-eqz v7, :cond_1eb

    invoke-virtual {v7, v11, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v27

    goto :goto_1ed

    :cond_1eb
    move-object/from16 v27, v4

    .line 741
    :goto_1ed
    if-eqz v7, :cond_1f8

    move-object/from16 v28, v11

    move-object/from16 v11, v19

    invoke-virtual {v7, v11, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    goto :goto_1fd

    :cond_1f8
    move-object/from16 v28, v11

    move-object/from16 v11, v19

    move-object v7, v4

    .line 743
    :goto_1fd
    if-gtz v0, :cond_212

    move-object/from16 v19, v4

    const-string v4, "series"

    invoke-virtual {v4, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_214

    invoke-virtual {v9, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_210

    goto :goto_214

    :cond_210
    const/4 v2, 0x0

    goto :goto_215

    :cond_212
    move-object/from16 v19, v4

    :cond_214
    :goto_214
    const/4 v2, 0x1

    .line 744
    :goto_215
    if-eqz v2, :cond_21a

    const-string v2, "SERIES"

    goto :goto_21c

    :cond_21a
    const-string v2, "MOVIE"

    .line 746
    :goto_21c
    const-string v4, "duration"

    move-object v13, v10

    move-object/from16 v29, v11

    const-wide/16 v10, 0x0

    invoke-virtual {v5, v4, v10, v11}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v30

    .line 747
    cmp-long v4, v30, v10

    if-nez v4, :cond_231

    const-string v4, "durationMs"

    invoke-virtual {v5, v4, v10, v11}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v30

    .line 748
    :cond_231
    const-wide/16 v4, 0x3e8

    cmp-long v32, v30, v10

    if-lez v32, :cond_23f

    const-wide/16 v32, 0x2710

    cmp-long v34, v30, v32

    if-gez v34, :cond_23f

    mul-long v30, v30, v4

    .line 749
    :cond_23f
    cmp-long v32, v30, v10

    if-gtz v32, :cond_246

    const-wide/32 v30, 0x1b7740

    .line 751
    :cond_246
    new-instance v10, Lorg/json/JSONObject;

    invoke-direct {v10}, Lorg/json/JSONObject;-><init>()V

    .line 752
    const-string v11, "id"

    invoke-virtual {v10, v11, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 753
    invoke-virtual {v10, v8, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 754
    invoke-virtual {v10, v12, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_256
    .catchall {:try_start_126 .. :try_end_256} :catchall_397

    .line 755
    const-string v1, "null"

    if-eqz v6, :cond_269

    :try_start_25a
    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_269

    invoke-virtual {v1, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_269

    .line 756
    invoke-virtual {v10, v15, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 758
    :cond_269
    if-eqz v3, :cond_27a

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_27a

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_27a

    .line 759
    invoke-virtual {v10, v14, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 761
    :cond_27a
    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_286

    .line 762
    move-object/from16 v1, v26

    invoke-virtual {v10, v1, v13}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_288

    .line 761
    :cond_286
    move-object/from16 v1, v26

    .line 764
    :goto_288
    invoke-virtual/range {v27 .. v27}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_296

    .line 765
    move-object/from16 v2, v27

    move-object/from16 v3, v28

    invoke-virtual {v10, v3, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_298

    .line 764
    :cond_296
    move-object/from16 v3, v28

    .line 767
    :goto_298
    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_2a4

    .line 768
    move-object/from16 v2, v29

    invoke-virtual {v10, v2, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_2a6

    .line 767
    :cond_2a4
    move-object/from16 v2, v29

    .line 770
    :goto_2a6
    const-string v6, "durationSeconds"

    div-long v4, v30, v4

    invoke-virtual {v10, v6, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    .line 771
    if-lez v0, :cond_2b5

    move-object/from16 v4, v21

    invoke-virtual {v10, v4, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    goto :goto_2b7

    :cond_2b5
    move-object/from16 v4, v21

    .line 772
    :goto_2b7
    if-lez v25, :cond_2c1

    move-object/from16 v5, v24

    move/from16 v0, v25

    invoke-virtual {v10, v5, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    goto :goto_2c3

    :cond_2c1
    move-object/from16 v5, v24

    .line 773
    :goto_2c3
    const-string v0, "genres"

    new-instance v6, Lorg/json/JSONArray;

    invoke-direct {v6}, Lorg/json/JSONArray;-><init>()V

    invoke-virtual {v10, v0, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 774
    const-string v0, "genresResolved"

    const/4 v6, 0x1

    invoke-virtual {v10, v0, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    .line 776
    move-object/from16 v0, v20

    invoke-virtual {v0, v10}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 777
    move-object v13, v1

    move-object v10, v2

    move-object v11, v3

    move-object v3, v4

    move-object/from16 v4, v19

    move-object/from16 v1, v22

    move-object/from16 v6, v23

    move-object/from16 v2, p1

    move-object/from16 v19, v5

    goto/16 :goto_bd

    .line 726
    :cond_2e8
    move-object/from16 v22, v1

    move-object v5, v3

    move-object/from16 v23, v6

    move-object v3, v11

    move-object v1, v13

    move-object/from16 v2, v19

    move-object/from16 v0, v20

    move-object/from16 v19, v4

    move-object/from16 v4, v21

    move-object v10, v2

    move-object v3, v4

    move-object/from16 v4, v19

    move-object/from16 v1, v22

    move-object/from16 v2, p1

    move-object/from16 v19, v5

    goto/16 :goto_bd

    .line 779
    :cond_303
    invoke-virtual {v0}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-lez v1, :cond_398

    .line 780
    new-instance v1, Ljava/io/File;

    invoke-virtual/range {p0 .. p0}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v2

    const-string v3, "continue_watching_cache.json"

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 781
    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_319
    .catchall {:try_start_25a .. :try_end_319} :catchall_397

    .line 782
    :try_start_319
    invoke-virtual {v0}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v0

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v0, v1}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/io/FileOutputStream;->write([B)V
    :try_end_326
    .catchall {:try_start_319 .. :try_end_326} :catchall_386

    .line 783
    :try_start_326
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V
    :try_end_329
    .catchall {:try_start_326 .. :try_end_329} :catchall_397

    .line 787
    :try_start_329
    const-string v0, "com.streamsync.app.data.local.ContinueWatchingDiskCache"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 788
    move-object/from16 v1, v18

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v2

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    .line 789
    const-string v3, "load"

    const/4 v4, 0x1

    new-array v5, v4, [Ljava/lang/Class;

    const-class v4, Landroid/content/Context;

    const/4 v6, 0x0

    aput-object v4, v5, v6

    invoke-virtual {v0, v3, v5}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 790
    const/4 v3, 0x1

    new-array v4, v3, [Ljava/lang/Object;

    aput-object p0, v4, v6

    invoke-virtual {v0, v2, v4}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 791
    instance-of v2, v0, Ljava/util/List;

    if-eqz v2, :cond_385

    move-object v2, v0

    check-cast v2, Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_385

    .line 792
    const-string v2, "com.streamsync.app.data.local.MediaItemCache"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 793
    invoke-virtual {v2, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v1

    const/4 v3, 0x0

    invoke-virtual {v1, v3}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 794
    const-string v3, "put"

    const/4 v4, 0x1

    new-array v5, v4, [Ljava/lang/Class;

    const-class v4, Ljava/util/List;

    const/4 v6, 0x0

    aput-object v4, v5, v6

    invoke-virtual {v2, v3, v5}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v2

    .line 795
    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    aput-object v0, v3, v6

    invoke-virtual {v2, v1, v3}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_383
    .catchall {:try_start_329 .. :try_end_383} :catchall_384

    goto :goto_385

    .line 797
    :catchall_384
    move-exception v0

    :cond_385
    :goto_385
    goto :goto_398

    .line 781
    :catchall_386
    move-exception v0

    move-object v1, v0

    :try_start_388
    invoke-virtual {v2}, Ljava/io/FileOutputStream;->close()V
    :try_end_38b
    .catchall {:try_start_388 .. :try_end_38b} :catchall_38c

    goto :goto_391

    :catchall_38c
    move-exception v0

    move-object v2, v0

    :try_start_38e
    invoke-static {v1, v2}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :goto_391
    throw v1

    .line 705
    :catchall_392
    move-exception v0

    invoke-virtual {v5}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 706
    throw v0
    :try_end_397
    .catchall {:try_start_38e .. :try_end_397} :catchall_397

    .line 799
    :catchall_397
    move-exception v0

    :cond_398
    :goto_398
    nop

    .line 800
    return-void

    .line 680
    :cond_39a
    :goto_39a
    return-void
.end method

.method public static pullAddonsFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 21

    .line 441
    move-object/from16 v1, p2

    move-object/from16 v2, p3

    const-string v3, "enabled"

    const-string v4, ""

    const-string v5, "url"

    const-string v6, "Bearer "

    const-string v7, "Authorization"

    const-string v8, "apikey"

    const-string v9, "GET"

    .line 443
    :try_start_12
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 444
    new-instance v12, Ljava/util/ArrayList;

    invoke-direct {v12}, Ljava/util/ArrayList;-><init>()V
    :try_end_1b
    .catchall {:try_start_12 .. :try_end_1b} :catchall_134

    .line 448
    const/16 v13, 0xc8

    const/16 v15, 0x1f40

    :try_start_1f
    new-instance v0, Ljava/net/URL;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v14, "/rest/v1/addons?select=*&order=sort_order.asc"

    invoke-virtual {v10, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v0, v10}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 449
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 450
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 451
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 452
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v0, v7, v10}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 453
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 454
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 456
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    if-ne v10, v13, :cond_a1

    .line 457
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 458
    new-instance v10, Lorg/json/JSONArray;

    invoke-direct {v10, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 459
    const/4 v0, 0x0

    :goto_71
    invoke-virtual {v10}, Lorg/json/JSONArray;->length()I

    move-result v14

    if-ge v0, v14, :cond_a1

    .line 460
    invoke-virtual {v10, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v14

    .line 461
    invoke-virtual {v14, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    .line 462
    const/4 v15, 0x1

    invoke-virtual {v14, v3, v15}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v14

    .line 463
    if-eqz v14, :cond_99

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_99

    invoke-interface {v12, v13}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_99

    .line 464
    invoke-interface {v12, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_99
    .catchall {:try_start_1f .. :try_end_99} :catchall_a0

    .line 459
    :cond_99
    add-int/lit8 v0, v0, 0x1

    const/16 v13, 0xc8

    const/16 v15, 0x1f40

    goto :goto_71

    .line 468
    :catchall_a0
    move-exception v0

    :cond_a1
    nop

    .line 472
    :try_start_a2
    new-instance v0, Ljava/net/URL;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, "/rest/v1/plugins?select=*&order=sort_order.asc"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v0, v10}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 473
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 474
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 475
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 476
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v7, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 477
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 478
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 480
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-ne v1, v2, :cond_124

    .line 481
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 482
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 483
    const/4 v0, 0x0

    :goto_f8
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v0, v2, :cond_124

    .line 484
    invoke-virtual {v1, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v2

    .line 485
    invoke-virtual {v2, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 486
    const/4 v7, 0x1

    invoke-virtual {v2, v3, v7}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v2

    .line 487
    if-eqz v2, :cond_120

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_120

    invoke-interface {v12, v6}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_120

    .line 488
    invoke-interface {v12, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_120
    .catchall {:try_start_a2 .. :try_end_120} :catchall_123

    .line 483
    :cond_120
    add-int/lit8 v0, v0, 0x1

    goto :goto_f8

    .line 492
    :catchall_123
    move-exception v0

    :cond_124
    nop

    .line 494
    :try_start_125
    invoke-interface {v12}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_132

    .line 495
    move-object/from16 v1, p0

    invoke-static {v1, v12}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result v10
    :try_end_131
    .catchall {:try_start_125 .. :try_end_131} :catchall_134

    goto :goto_133

    .line 494
    :cond_132
    const/4 v10, 0x0

    .line 497
    :goto_133
    goto :goto_136

    :catchall_134
    move-exception v0

    const/4 v10, 0x0

    .line 498
    :goto_136
    return v10
.end method

.method private static pullLibraryFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .registers 13

    .line 804
    const-string v0, "imdb_rating"

    const-string v1, "description"

    const-string v2, ""

    :try_start_6
    new-instance v3, Ljava/net/URL;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v4, "/rest/v1/rpc/sync_pull_library"

    invoke-virtual {p1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v3, p1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 805
    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p1

    check-cast p1, Ljava/net/HttpURLConnection;

    .line 806
    const-string v3, "POST"

    invoke-virtual {p1, v3}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 807
    const-string v3, "Content-Type"

    const-string v4, "application/json"

    invoke-virtual {p1, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 808
    const-string v3, "apikey"

    invoke-virtual {p1, v3, p3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 809
    const-string p3, "Authorization"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Bearer "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p3, p2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 810
    const/16 p2, 0x1f40

    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 811
    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 812
    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 814
    new-instance p3, Lorg/json/JSONObject;

    invoke-direct {p3}, Lorg/json/JSONObject;-><init>()V

    .line 815
    const-string v3, "p_profile_id"

    invoke-virtual {p3, v3, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 816
    const-string p2, "p_limit"

    const/16 v3, 0x64

    invoke-virtual {p3, p2, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 817
    const-string p2, "p_offset"

    const/4 v3, 0x0

    invoke-virtual {p3, p2, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 818
    invoke-virtual {p3}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p2

    sget-object p3, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p2, p3}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p2

    .line 819
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p3
    :try_end_7e
    .catchall {:try_start_6 .. :try_end_7e} :catchall_191

    .line 820
    :try_start_7e
    invoke-virtual {p3, p2}, Ljava/io/OutputStream;->write([B)V
    :try_end_81
    .catchall {:try_start_7e .. :try_end_81} :catchall_185

    .line 821
    if-eqz p3, :cond_86

    :try_start_83
    invoke-virtual {p3}, Ljava/io/OutputStream;->close()V

    .line 823
    :cond_86
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result p2

    const/16 p3, 0xc8

    if-ne p2, p3, :cond_192

    .line 824
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p1

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p1

    .line 825
    new-instance p2, Lorg/json/JSONArray;

    invoke-direct {p2, p1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 826
    invoke-virtual {p2}, Lorg/json/JSONArray;->length()I

    move-result p1

    if-nez p1, :cond_a2

    return-void

    .line 828
    :cond_a2
    const-string p1, "streamsync.db"

    invoke-virtual {p0, p1}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    .line 829
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p1

    if-nez p1, :cond_af

    return-void

    .line 830
    :cond_af
    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    const/4 p1, 0x0

    invoke-static {p0, p1, v3}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object p0
    :try_end_b8
    .catchall {:try_start_83 .. :try_end_b8} :catchall_191

    .line 832
    :try_start_b8
    const-string p3, "CREATE TABLE IF NOT EXISTS `library_items` (`mediaId` TEXT NOT NULL, `title` TEXT NOT NULL, `posterUrl` TEXT, `backgroundUrl` TEXT, `description` TEXT, `type` TEXT NOT NULL, `releaseInfo` TEXT, `imdbRating` TEXT, `genresJson` TEXT NOT NULL, `season` INTEGER, `episode` INTEGER, `addedAtEpochMs` INTEGER NOT NULL, PRIMARY KEY(`mediaId`));"

    invoke-virtual {p0, p3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 834
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 835
    nop

    :goto_c1
    invoke-virtual {p2}, Lorg/json/JSONArray;->length()I

    move-result p3

    if-ge v3, p3, :cond_173

    .line 836
    invoke-virtual {p2, v3}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object p3

    .line 837
    const-string v4, "content_id"

    invoke-virtual {p3, v4, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 838
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_d9

    goto/16 :goto_16f

    .line 840
    :cond_d9
    new-instance v5, Landroid/content/ContentValues;

    invoke-direct {v5}, Landroid/content/ContentValues;-><init>()V

    .line 841
    const-string v6, "mediaId"

    invoke-virtual {v5, v6, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 842
    const-string v4, "title"

    const-string v6, "name"

    invoke-virtual {p3, v6, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 843
    const-string v4, "posterUrl"

    const-string v6, "poster"

    invoke-virtual {p3, v6, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 844
    const-string v4, "backgroundUrl"

    const-string v6, "background"

    invoke-virtual {p3, v6, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 845
    invoke-virtual {p3, v1, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 846
    const-string v4, "type"

    const-string v6, "content_type"

    const-string v7, "movie"

    invoke-virtual {p3, v6, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 847
    const-string v4, "releaseInfo"

    const-string v6, "release_info"

    invoke-virtual {p3, v6, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 848
    const-string v4, "imdbRating"

    invoke-virtual {p3, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_134

    invoke-virtual {p3, v0}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    goto :goto_135

    :cond_134
    move-object v6, p1

    :goto_135
    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 850
    const-string v4, "genres"

    invoke-virtual {p3, v4}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 851
    const-string v6, "genresJson"

    if-eqz v4, :cond_147

    invoke-virtual {v4}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v4

    goto :goto_149

    :cond_147
    const-string v4, "[]"

    :goto_149
    invoke-virtual {v5, v6, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 853
    const-string v4, "season"

    invoke-virtual {v5, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 854
    const-string v4, "episode"

    invoke-virtual {v5, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 855
    const-string v4, "addedAtEpochMs"

    const-string v6, "added_at"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    invoke-virtual {p3, v6, v7, v8}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v6

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    invoke-virtual {v5, v4, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 857
    const-string p3, "library_items"

    const/4 v4, 0x5

    invoke-virtual {p0, p3, p1, v5, v4}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 835
    :goto_16f
    add-int/lit8 v3, v3, 0x1

    goto/16 :goto_c1

    .line 859
    :cond_173
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_176
    .catchall {:try_start_b8 .. :try_end_176} :catchall_17d

    .line 861
    :try_start_176
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 862
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 863
    goto :goto_192

    .line 861
    :catchall_17d
    move-exception p1

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 862
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 863
    throw p1
    :try_end_185
    .catchall {:try_start_176 .. :try_end_185} :catchall_191

    .line 819
    :catchall_185
    move-exception p0

    if-eqz p3, :cond_190

    :try_start_188
    invoke-virtual {p3}, Ljava/io/OutputStream;->close()V
    :try_end_18b
    .catchall {:try_start_188 .. :try_end_18b} :catchall_18c

    goto :goto_190

    :catchall_18c
    move-exception p1

    :try_start_18d
    invoke-static {p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_190
    :goto_190
    throw p0
    :try_end_191
    .catchall {:try_start_18d .. :try_end_191} :catchall_191

    .line 865
    :catchall_191
    move-exception p0

    :cond_192
    :goto_192
    nop

    .line 866
    return-void
.end method

.method public static pullWatchProgressFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 30

    .line 502
    move-object/from16 v1, p0

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    const-string v4, "position"

    const-string v5, "p_profile_id"

    const-string v6, "application/json"

    const-string v7, "Content-Type"

    const-string v8, "POST"

    const-string v9, ":"

    const-string v10, "episode"

    const-string v11, "season"

    const-string v12, "content_id"

    const-string v13, "Bearer "

    const-string v14, "Authorization"

    const-string v15, "apikey"

    move-object/from16 v16, v9

    const-string v9, ""

    .line 504
    :try_start_22
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 505
    move-object/from16 v18, v4

    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 506
    move-object/from16 v19, v10

    new-instance v10, Ljava/util/HashSet;

    invoke-direct {v10}, Ljava/util/HashSet;-><init>()V
    :try_end_34
    .catchall {:try_start_22 .. :try_end_34} :catchall_492

    .line 510
    move-object/from16 p1, v10

    :try_start_36
    new-instance v0, Ljava/net/URL;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10
    :try_end_41
    .catchall {:try_start_36 .. :try_end_41} :catchall_d3

    move-object/from16 v23, v11

    :try_start_43
    const-string v11, "/rest/v1/rpc/sync_pull_watch_progress"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v0, v10}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 511
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 512
    invoke-virtual {v0, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 513
    invoke-virtual {v0, v7, v6}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 514
    invoke-virtual {v0, v15, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 515
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v0, v14, v10}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 516
    const/16 v10, 0x1f40

    invoke-virtual {v0, v10}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 517
    invoke-virtual {v0, v10}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 518
    const/4 v10, 0x1

    invoke-virtual {v0, v10}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 520
    new-instance v11, Lorg/json/JSONObject;

    invoke-direct {v11}, Lorg/json/JSONObject;-><init>()V

    .line 521
    invoke-virtual {v11, v5, v10}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 522
    invoke-virtual {v11}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v10

    sget-object v11, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v10, v11}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v10

    .line 523
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v11
    :try_end_95
    .catchall {:try_start_43 .. :try_end_95} :catchall_d1

    .line 524
    :try_start_95
    invoke-virtual {v11, v10}, Ljava/io/OutputStream;->write([B)V
    :try_end_98
    .catchall {:try_start_95 .. :try_end_98} :catchall_c3

    .line 525
    if-eqz v11, :cond_9d

    :try_start_9a
    invoke-virtual {v11}, Ljava/io/OutputStream;->close()V

    .line 527
    :cond_9d
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    const/16 v11, 0xc8

    if-ne v10, v11, :cond_d6

    .line 528
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 529
    new-instance v10, Lorg/json/JSONArray;

    invoke-direct {v10, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 530
    const/4 v0, 0x0

    :goto_b3
    invoke-virtual {v10}, Lorg/json/JSONArray;->length()I

    move-result v11

    if-ge v0, v11, :cond_d6

    .line 531
    invoke-virtual {v10, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v11

    invoke-interface {v4, v11}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_c0
    .catchall {:try_start_9a .. :try_end_c0} :catchall_d1

    .line 530
    add-int/lit8 v0, v0, 0x1

    goto :goto_b3

    .line 523
    :catchall_c3
    move-exception v0

    move-object v10, v0

    if-eqz v11, :cond_d0

    :try_start_c7
    invoke-virtual {v11}, Ljava/io/OutputStream;->close()V
    :try_end_ca
    .catchall {:try_start_c7 .. :try_end_ca} :catchall_cb

    goto :goto_d0

    :catchall_cb
    move-exception v0

    move-object v11, v0

    :try_start_cd
    invoke-static {v10, v11}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_d0
    :goto_d0
    throw v10
    :try_end_d1
    .catchall {:try_start_cd .. :try_end_d1} :catchall_d1

    .line 534
    :catchall_d1
    move-exception v0

    goto :goto_d6

    :catchall_d3
    move-exception v0

    move-object/from16 v23, v11

    :cond_d6
    :goto_d6
    nop

    .line 538
    :try_start_d7
    new-instance v0, Ljava/net/URL;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, "/rest/v1/rpc/sync_pull_watched_items"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v0, v10}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 539
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 540
    invoke-virtual {v0, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 541
    invoke-virtual {v0, v7, v6}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 542
    invoke-virtual {v0, v15, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 543
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v14, v6}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 544
    const/16 v6, 0x1f40

    invoke-virtual {v0, v6}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 545
    invoke-virtual {v0, v6}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 546
    const/4 v6, 0x1

    invoke-virtual {v0, v6}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 548
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 549
    invoke-virtual {v7, v5, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 550
    const-string v5, "p_page"

    invoke-virtual {v7, v5, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 551
    const-string v5, "p_page_size"

    const/16 v6, 0x64

    invoke-virtual {v7, v5, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 552
    invoke-virtual {v7}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v5

    sget-object v6, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v5, v6}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v5

    .line 553
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v6
    :try_end_140
    .catchall {:try_start_d7 .. :try_end_140} :catchall_1f3

    .line 554
    :try_start_140
    invoke-virtual {v6, v5}, Ljava/io/OutputStream;->write([B)V
    :try_end_143
    .catchall {:try_start_140 .. :try_end_143} :catchall_1df

    .line 555
    if-eqz v6, :cond_148

    :try_start_145
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V

    .line 557
    :cond_148
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v5

    const/16 v6, 0xc8

    if-ne v5, v6, :cond_1da

    .line 558
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 559
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 560
    const/4 v0, 0x0

    :goto_15e
    invoke-virtual {v5}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v0, v6, :cond_1d5

    .line 561
    invoke-virtual {v5, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v6

    .line 562
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 563
    invoke-virtual {v6, v12, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v12, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 564
    const-string v8, "content_type"

    const-string v10, "content_type"

    invoke-virtual {v6, v10, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v7, v8, v10}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_17f
    .catchall {:try_start_145 .. :try_end_17f} :catchall_1f3

    .line 565
    move-object/from16 v8, v23

    const/4 v10, 0x0

    :try_start_182
    invoke-virtual {v6, v8, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v11

    invoke-virtual {v7, v8, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;
    :try_end_189
    .catchall {:try_start_182 .. :try_end_189} :catchall_1d1

    .line 566
    move-object/from16 v17, v5

    move-object/from16 v11, v19

    :try_start_18d
    invoke-virtual {v6, v11, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v5

    invoke-virtual {v7, v11, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;
    :try_end_194
    .catchall {:try_start_18d .. :try_end_194} :catchall_1cb

    .line 567
    const/16 v5, 0x3e8

    move-object/from16 v10, v18

    :try_start_198
    invoke-virtual {v7, v10, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 568
    const-string v5, "duration"
    :try_end_19d
    .catchall {:try_start_198 .. :try_end_19d} :catchall_1c3

    move-object/from16 v18, v10

    const/16 v10, 0x3e8

    :try_start_1a1
    invoke-virtual {v7, v5, v10}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 569
    const-string v5, "last_watched"

    const-string v10, "watched_at"
    :try_end_1a8
    .catchall {:try_start_1a1 .. :try_end_1a8} :catchall_1cb

    move-object/from16 v23, v8

    move-object/from16 v22, v9

    :try_start_1ac
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    invoke-virtual {v6, v10, v8, v9}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v8

    invoke-virtual {v7, v5, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    .line 570
    invoke-interface {v4, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_1ba
    .catchall {:try_start_1ac .. :try_end_1ba} :catchall_1f1

    .line 560
    add-int/lit8 v0, v0, 0x1

    move-object/from16 v19, v11

    move-object/from16 v5, v17

    move-object/from16 v9, v22

    goto :goto_15e

    .line 573
    :catchall_1c3
    move-exception v0

    move-object/from16 v23, v8

    move-object/from16 v22, v9

    move-object/from16 v18, v10

    goto :goto_1f8

    :catchall_1cb
    move-exception v0

    move-object/from16 v23, v8

    move-object/from16 v22, v9

    goto :goto_1f8

    :catchall_1d1
    move-exception v0

    move-object/from16 v23, v8

    goto :goto_1f4

    .line 560
    :cond_1d5
    move-object/from16 v22, v9

    move-object/from16 v11, v19

    goto :goto_1f8

    .line 557
    :cond_1da
    move-object/from16 v22, v9

    move-object/from16 v11, v19

    goto :goto_1f8

    .line 553
    :catchall_1df
    move-exception v0

    move-object/from16 v22, v9

    move-object/from16 v11, v19

    move-object v5, v0

    if-eqz v6, :cond_1f0

    :try_start_1e7
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V
    :try_end_1ea
    .catchall {:try_start_1e7 .. :try_end_1ea} :catchall_1eb

    goto :goto_1f0

    :catchall_1eb
    move-exception v0

    move-object v6, v0

    :try_start_1ed
    invoke-static {v5, v6}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_1f0
    :goto_1f0
    throw v5
    :try_end_1f1
    .catchall {:try_start_1ed .. :try_end_1f1} :catchall_1f1

    .line 573
    :catchall_1f1
    move-exception v0

    goto :goto_1f8

    :catchall_1f3
    move-exception v0

    :goto_1f4
    move-object/from16 v22, v9

    move-object/from16 v11, v19

    :goto_1f8
    nop

    .line 576
    :try_start_1f9
    invoke-interface {v4}, Ljava/util/List;->isEmpty()Z

    move-result v0
    :try_end_1fd
    .catchall {:try_start_1f9 .. :try_end_1fd} :catchall_492

    if-eqz v0, :cond_269

    .line 578
    :try_start_1ff
    new-instance v0, Ljava/net/URL;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, "/rest/v1/watch_progress?select=*"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v0, v5}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 579
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 580
    const-string v5, "GET"

    invoke-virtual {v0, v5}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 581
    invoke-virtual {v0, v15, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 582
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v14, v5}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 583
    const/16 v5, 0x1f40

    invoke-virtual {v0, v5}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 584
    invoke-virtual {v0, v5}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 586
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v5

    const/16 v6, 0xc8

    if-ne v5, v6, :cond_268

    .line 587
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 588
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 589
    const/4 v0, 0x0

    :goto_257
    invoke-virtual {v5}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v0, v6, :cond_268

    .line 590
    invoke-virtual {v5, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v6

    invoke-interface {v4, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_264
    .catchall {:try_start_1ff .. :try_end_264} :catchall_267

    .line 589
    add-int/lit8 v0, v0, 0x1

    goto :goto_257

    .line 593
    :catchall_267
    move-exception v0

    :cond_268
    nop

    .line 597
    :cond_269
    const/4 v5, 0x0

    move-object/from16 v6, p0

    :try_start_26c
    invoke-static {v6, v1, v2, v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->pullLibraryFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 599
    invoke-interface {v4}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_276

    .line 600
    return v5

    .line 603
    :cond_276
    const-string v0, "streamsync.db"

    invoke-virtual {v6, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 604
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1
    :try_end_284
    .catchall {:try_start_26c .. :try_end_284} :catchall_492

    if-nez v1, :cond_291

    .line 605
    :try_start_286
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z
    :try_end_28d
    .catchall {:try_start_286 .. :try_end_28d} :catchall_28e

    goto :goto_291

    .line 675
    :catchall_28e
    move-exception v0

    goto/16 :goto_494

    .line 608
    :cond_291
    :goto_291
    :try_start_291
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/high16 v1, 0x10000000

    const/4 v2, 0x0

    invoke-static {v0, v2, v1}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1
    :try_end_29c
    .catchall {:try_start_291 .. :try_end_29c} :catchall_492

    .line 610
    :try_start_29c
    const-string v0, "CREATE TABLE IF NOT EXISTS `watch_progress` (`mediaId` TEXT NOT NULL, `positionMs` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, `updatedAtEpochMs` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `pendingSync` INTEGER NOT NULL, `streamUrl` TEXT, `addonId` TEXT, `addonName` TEXT, PRIMARY KEY(`mediaId`));"

    invoke-virtual {v1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 612
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 613
    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0
    :try_end_2a8
    .catchall {:try_start_29c .. :try_end_2a8} :catchall_485

    const/4 v3, 0x0

    :goto_2a9
    :try_start_2a9
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_475

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/json/JSONObject;

    .line 614
    move-object/from16 v8, v22

    invoke-virtual {v7, v12, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 615
    invoke-virtual {v9}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_2c7

    const-string v9, "contentId"

    invoke-virtual {v7, v9, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 617
    :cond_2c7
    const-string v10, "video_id"

    invoke-virtual {v7, v10, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 618
    invoke-virtual {v10}, Ljava/lang/String;->isEmpty()Z

    move-result v13

    if-eqz v13, :cond_2d9

    const-string v10, "videoId"

    invoke-virtual {v7, v10, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 620
    :cond_2d9
    move-object/from16 v13, v23

    invoke-virtual {v7, v13, v5}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v14

    .line 621
    invoke-virtual {v7, v11, v5}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v15

    .line 623
    nop

    .line 624
    invoke-virtual {v10}, Ljava/lang/String;->isEmpty()Z

    move-result v17

    if-eqz v17, :cond_2f0

    const-string v10, "progress_key"

    invoke-virtual {v7, v10, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 625
    :cond_2f0
    invoke-virtual {v10}, Ljava/lang/String;->isEmpty()Z

    move-result v17

    if-eqz v17, :cond_2f7

    move-object v10, v9

    .line 626
    :cond_2f7
    invoke-virtual {v10}, Ljava/lang/String;->isEmpty()Z

    move-result v17
    :try_end_2fb
    .catchall {:try_start_2a9 .. :try_end_2fb} :catchall_483

    const-string v2, "mediaId"

    if-eqz v17, :cond_303

    :try_start_2ff
    invoke-virtual {v7, v2, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 628
    :cond_303
    if-lez v14, :cond_32d

    if-lez v15, :cond_32d

    move-object/from16 v5, v16

    invoke-virtual {v10, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v16

    if-nez v16, :cond_32f

    .line 629
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v15}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    goto :goto_32f

    .line 628
    :cond_32d
    move-object/from16 v5, v16

    .line 632
    :cond_32f
    :goto_32f
    invoke-virtual {v10}, Ljava/lang/String;->isEmpty()Z

    move-result v9

    if-nez v9, :cond_454

    move-object/from16 v9, p1

    invoke-interface {v9, v10}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v14

    if-eqz v14, :cond_34f

    .line 633
    move-object/from16 p1, v0

    move-object/from16 v16, v5

    move-object v15, v8

    move-object/from16 p3, v9

    move-object/from16 v20, v11

    move-object v14, v12

    move-object/from16 v21, v13

    move-object/from16 v5, v18

    const/4 v2, 0x0

    const/4 v9, 0x0

    goto/16 :goto_464

    .line 635
    :cond_34f
    invoke-interface {v9, v10}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 637
    const-wide/16 v14, 0x0

    move-object/from16 v16, v5

    move-object/from16 v5, v18

    invoke-virtual {v7, v5, v14, v15}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v18
    :try_end_35c
    .catchall {:try_start_2ff .. :try_end_35c} :catchall_483

    .line 638
    move-object/from16 p1, v0

    const-string v0, "positionMs"

    cmp-long v20, v18, v14

    if-nez v20, :cond_368

    :try_start_364
    invoke-virtual {v7, v0, v14, v15}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v18

    .line 639
    :cond_368
    const-wide/16 v20, 0x2710

    cmp-long v22, v18, v14

    if-lez v22, :cond_376

    cmp-long v22, v18, v20

    if-gez v22, :cond_376

    const-wide/16 v22, 0x3e8

    mul-long v18, v18, v22

    .line 640
    :cond_376
    cmp-long v22, v18, v14

    if-gtz v22, :cond_37d

    const-wide/32 v18, 0xea60

    .line 642
    :cond_37d
    const-string v14, "duration"

    move-object v15, v8

    move-object/from16 p3, v9

    const-wide/16 v8, 0x0

    invoke-virtual {v7, v14, v8, v9}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v22

    .line 643
    cmp-long v14, v22, v8

    if-nez v14, :cond_392

    const-string v14, "durationMs"

    invoke-virtual {v7, v14, v8, v9}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v22
    :try_end_392
    .catchall {:try_start_364 .. :try_end_392} :catchall_483

    :cond_392
    move-wide/from16 v24, v22

    .line 644
    cmp-long v14, v24, v8

    if-lez v14, :cond_3a3

    cmp-long v8, v24, v20

    if-gez v8, :cond_3a3

    const-wide/16 v8, 0x3e8

    mul-long v24, v24, v8

    move-wide/from16 v8, v24

    goto :goto_3a5

    .line 645
    :cond_3a3
    move-wide/from16 v8, v24

    :goto_3a5
    cmp-long v14, v8, v18

    if-lez v14, :cond_3bd

    move-object/from16 v20, v11

    move-object v14, v12

    long-to-double v11, v8

    const-wide v24, 0x3fef0a3d70a3d70aL    # 0.97

    invoke-static {v11, v12}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v11, v11, v24

    double-to-long v11, v11

    cmp-long v21, v18, v11

    if-ltz v21, :cond_3cd

    goto :goto_3c0

    :cond_3bd
    move-object/from16 v20, v11

    move-object v14, v12

    .line 646
    :goto_3c0
    const-wide/16 v8, 0x2

    mul-long v8, v8, v18

    const-wide/32 v11, 0x493e0

    add-long v11, v18, v11

    :try_start_3c9
    invoke-static {v8, v9, v11, v12}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v8

    .line 649
    :cond_3cd
    const-string v11, "last_watched"

    move-object/from16 v21, v13

    const-wide/16 v12, 0x0

    invoke-virtual {v7, v11, v12, v13}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v22

    .line 650
    cmp-long v11, v22, v12

    if-nez v11, :cond_3e1

    const-string v11, "lastWatched"

    invoke-virtual {v7, v11, v12, v13}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v22

    .line 651
    :cond_3e1
    cmp-long v11, v22, v12

    if-nez v11, :cond_3eb

    const-string v11, "watched_at"

    invoke-virtual {v7, v11, v12, v13}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v22

    .line 652
    :cond_3eb
    cmp-long v11, v22, v12

    if-nez v11, :cond_3f9

    const-string v11, "updatedAtEpochMs"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v12

    invoke-virtual {v7, v11, v12, v13}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v22

    .line 654
    :cond_3f9
    new-instance v7, Landroid/content/ContentValues;

    invoke-direct {v7}, Landroid/content/ContentValues;-><init>()V

    .line 655
    invoke-virtual {v7, v2, v10}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 656
    invoke-static/range {v18 .. v19}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v7, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 657
    const-string v0, "durationMs"

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v7, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 658
    const-string v0, "updatedAtEpochMs"

    invoke-static/range {v22 .. v23}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v7, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 659
    const-string v0, "deviceId"

    const-string v2, "streamsync"

    invoke-virtual {v7, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 660
    const-string v0, "pendingSync"

    const/4 v2, 0x0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v7, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 661
    const-string v0, "streamUrl"

    invoke-virtual {v7, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 662
    const-string v0, "addonId"

    invoke-virtual {v7, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 663
    const-string v0, "addonName"

    invoke-virtual {v7, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 665
    const-string v0, "watch_progress"

    const/4 v8, 0x5

    const/4 v9, 0x0

    invoke-virtual {v1, v0, v9, v7, v8}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 666
    add-int/lit8 v3, v3, 0x1

    .line 667
    move-object/from16 v0, p1

    move-object/from16 p1, p3

    move-object/from16 v18, v5

    move-object v2, v9

    move-object v12, v14

    move-object/from16 v22, v15

    move-object/from16 v11, v20

    move-object/from16 v23, v21

    const/4 v5, 0x0

    goto/16 :goto_2a9

    .line 632
    :cond_454
    move-object/from16 p3, p1

    move-object/from16 p1, v0

    move-object/from16 v16, v5

    move-object v15, v8

    move-object/from16 v20, v11

    move-object v14, v12

    move-object/from16 v21, v13

    move-object/from16 v5, v18

    const/4 v2, 0x0

    const/4 v9, 0x0

    .line 613
    :goto_464
    move-object/from16 v0, p1

    move-object/from16 p1, p3

    move-object/from16 v18, v5

    move-object v2, v9

    move-object v12, v14

    move-object/from16 v22, v15

    move-object/from16 v11, v20

    move-object/from16 v23, v21

    const/4 v5, 0x0

    goto/16 :goto_2a9

    .line 668
    :cond_475
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_478
    .catchall {:try_start_3c9 .. :try_end_478} :catchall_483

    .line 670
    :try_start_478
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 671
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 672
    nop

    .line 674
    invoke-static {v6, v4}, Lcom/streamsync/app/auth/SupabaseSyncService;->populateContinueWatchingCache(Landroid/content/Context;Ljava/util/List;)V

    .line 675
    goto :goto_496

    .line 670
    :catchall_483
    move-exception v0

    goto :goto_488

    :catchall_485
    move-exception v0

    const/4 v2, 0x0

    const/4 v3, 0x0

    :goto_488
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 671
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 672
    throw v0
    :try_end_48f
    .catchall {:try_start_478 .. :try_end_48f} :catchall_48f

    .line 675
    :catchall_48f
    move-exception v0

    move v1, v3

    goto :goto_495

    :catchall_492
    move-exception v0

    const/4 v2, 0x0

    :goto_494
    const/4 v1, 0x0

    :goto_495
    move v3, v1

    .line 676
    :goto_496
    return v3
.end method

.method private static readStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 1066
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 1067
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1069
    :goto_11
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 1070
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_11

    .line 1072
    :cond_21
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    .line 1073
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static refreshRoom(Landroid/content/Context;)V
    .registers 8

    .line 1050
    :try_start_0
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 1051
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 1052
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    const-string v2, "get"

    const/4 v3, 0x1

    new-array v4, v3, [Ljava/lang/Class;

    const-class v5, Landroid/content/Context;

    const/4 v6, 0x0

    aput-object v5, v4, v6

    invoke-virtual {v1, v2, v4}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 1053
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 1054
    if-eqz p0, :cond_58

    .line 1055
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "getInvalidationTracker"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 1056
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 1057
    if-eqz p0, :cond_58

    .line 1058
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "refreshVersionsAsync"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 1059
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_56
    .catchall {:try_start_0 .. :try_end_56} :catchall_57

    goto :goto_58

    .line 1062
    :catchall_57
    move-exception p0

    :cond_58
    :goto_58
    nop

    .line 1063
    return-void
.end method

.method public static runOnMain(Ljava/lang/Runnable;)V
    .registers 2

    .line 56
    sget-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    invoke-virtual {v0, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 57
    return-void
.end method

.method public static showToast(Landroid/content/Context;Ljava/lang/String;)V
    .registers 3

    .line 60
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$1;

    invoke-direct {v0, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$1;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 68
    return-void
.end method

.method public static startDeviceLink(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V
    .registers 3

    .line 198
    new-instance p0, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$3;

    invoke-direct {v0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V

    invoke-direct {p0, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 268
    invoke-virtual {p0}, Ljava/lang/Thread;->start()V

    .line 269
    return-void
.end method

.method public static syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V
    .registers 4

    .line 375
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$4;

    invoke-direct {v1, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$4;-><init>(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 437
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 438
    return-void
.end method
