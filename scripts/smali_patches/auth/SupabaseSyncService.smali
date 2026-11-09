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

    .line 50
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 30
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static ensureRoomDatabase(Landroid/content/Context;)V
    .registers 8

    .line 1021
    if-nez p0, :cond_3

    return-void

    .line 1023
    :cond_3
    :try_start_3
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 1024
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 1025
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

    .line 1026
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_31
    .catchall {:try_start_3 .. :try_end_31} :catchall_32

    goto :goto_33

    .line 1027
    :catchall_32
    move-exception p0

    :goto_33
    nop

    .line 1028
    return-void
.end method

.method public static exchangeAndSaveSession(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    .registers 19

    .line 303
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

    .line 304
    invoke-virtual {v7}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v7

    check-cast v7, Ljava/net/HttpURLConnection;

    .line 305
    const-string v8, "POST"

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 306
    const-string v8, "Content-Type"

    const-string v9, "application/json"

    invoke-virtual {v7, v8, v9}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 307
    invoke-virtual {v7, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 308
    const-string v8, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v7, v2, v8}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 309
    const/16 v8, 0x2710

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 310
    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 311
    const/4 v8, 0x1

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 313
    new-instance v9, Lorg/json/JSONObject;

    invoke-direct {v9}, Lorg/json/JSONObject;-><init>()V

    .line 314
    const-string v10, "code"

    move-object/from16 v11, p1

    invoke-virtual {v9, v10, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 315
    const-string v10, "device_nonce"

    move-object/from16 v11, p2

    invoke-virtual {v9, v10, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 317
    invoke-virtual {v9}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v9

    sget-object v10, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v9, v10}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v9

    .line 318
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v10
    :try_end_5b
    .catchall {:try_start_d .. :try_end_5b} :catchall_152

    .line 319
    :try_start_5b
    invoke-virtual {v10, v9}, Ljava/io/OutputStream;->write([B)V
    :try_end_5e
    .catchall {:try_start_5b .. :try_end_5e} :catchall_145

    .line 320
    if-eqz v10, :cond_63

    :try_start_60
    invoke-virtual {v10}, Ljava/io/OutputStream;->close()V

    .line 322
    :cond_63
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v9

    .line 323
    const/16 v10, 0xc8

    if-ne v9, v10, :cond_153

    .line 324
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v7

    invoke-static {v7}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v7

    .line 325
    new-instance v9, Lorg/json/JSONObject;

    invoke-direct {v9, v7}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 326
    const-string v7, "access_token"

    invoke-virtual {v9, v7, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 327
    invoke-virtual {v9, v1, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 328
    nop

    .line 329
    nop

    .line 331
    invoke-virtual {v9, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v12
    :try_end_88
    .catchall {:try_start_60 .. :try_end_88} :catchall_152

    const-string v13, "id"

    const-string v14, "email"

    if-eqz v12, :cond_9d

    .line 332
    :try_start_8e
    invoke-virtual {v9, v0}, Lorg/json/JSONObject;->getJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 333
    invoke-virtual {v0, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 334
    invoke-virtual {v0, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v12, v9

    move-object v9, v0

    goto :goto_9f

    .line 331
    :cond_9d
    move-object v9, v5

    move-object v12, v9

    .line 337
    :goto_9f
    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_f9

    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_a9
    .catchall {:try_start_8e .. :try_end_a9} :catchall_152

    if-nez v0, :cond_f9

    .line 339
    :try_start_ab
    new-instance v0, Ljava/net/URL;

    const-string v15, "https://api.nuvio.tv/auth/v1/user"

    invoke-direct {v0, v15}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 340
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 341
    const-string v15, "GET"

    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 342
    invoke-virtual {v0, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 343
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

    .line 344
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v2

    if-ne v2, v10, :cond_f8

    .line 345
    new-instance v2, Lorg/json/JSONObject;

    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 346
    invoke-virtual {v2, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3
    :try_end_ed
    .catchall {:try_start_ab .. :try_end_ed} :catchall_f7

    .line 347
    :try_start_ed
    invoke-virtual {v2, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_f1
    .catchall {:try_start_ed .. :try_end_f1} :catchall_f4

    move-object v9, v0

    move-object v12, v3

    goto :goto_f8

    .line 349
    :catchall_f4
    move-exception v0

    move-object v12, v3

    goto :goto_f8

    :catchall_f7
    move-exception v0

    :cond_f8
    :goto_f8
    nop

    .line 352
    :cond_f9
    :try_start_f9
    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_101

    const-string v12, "T\u00e0i kho\u1ea3n Nuvio"

    .line 354
    :cond_101
    const-string v0, "nuvio_supabase_auth"

    move-object/from16 v2, p0

    invoke-virtual {v2, v0, v6}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 355
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "server_url"

    const-string v3, "https://api.nuvio.tv"

    .line 356
    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "anon_key"

    .line 357
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_token"

    .line 358
    invoke-interface {v0, v2, v7}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 359
    invoke-interface {v0, v1, v11}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "auth_email"

    .line 360
    invoke-interface {v0, v1, v12}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "auth_user_id"

    .line 361
    invoke-interface {v0, v1, v9}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "is_anonymous"

    .line 362
    invoke-interface {v0, v1, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "last_login_time"

    .line 363
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 364
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_144
    .catchall {:try_start_f9 .. :try_end_144} :catchall_152

    .line 365
    return v8

    .line 318
    :catchall_145
    move-exception v0

    move-object v1, v0

    if-eqz v10, :cond_151

    :try_start_149
    invoke-virtual {v10}, Ljava/io/OutputStream;->close()V
    :try_end_14c
    .catchall {:try_start_149 .. :try_end_14c} :catchall_14d

    goto :goto_151

    :catchall_14d
    move-exception v0

    :try_start_14e
    invoke-static {v1, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_151
    :goto_151
    throw v1
    :try_end_152
    .catchall {:try_start_14e .. :try_end_152} :catchall_152

    .line 367
    :catchall_152
    move-exception v0

    :cond_153
    nop

    .line 368
    return v6
.end method

.method public static fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    .line 80
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 81
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const-string v1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    if-eqz v0, :cond_d

    return-object v1

    .line 83
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

    .line 84
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    .line 85
    const-string v0, "GET"

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 86
    const/16 v0, 0x1770

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 87
    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 88
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v0

    .line 89
    const/16 v2, 0xc8

    if-ne v0, v2, :cond_5d

    .line 90
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 91
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 92
    const-string p0, "publishable_key"

    const-string v2, ""

    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 93
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_59
    .catchall {:try_start_d .. :try_end_59} :catchall_5c

    if-nez v0, :cond_5d

    .line 94
    return-object p0

    .line 97
    :catchall_5c
    move-exception p0

    :cond_5d
    nop

    .line 98
    return-object v1
.end method

.method private static getDefaultManifestFallback(Ljava/lang/String;)Lorg/json/JSONObject;
    .registers 15

    .line 961
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 962
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

    .line 963
    :try_start_23
    const-string p0, "community.cinemeta"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 964
    const-string p0, "Cinemeta"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 965
    const-string p0, "3.0.12"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 966
    const-string p0, "Cung c\u1ea5p danh m\u1ee5c v\u00e0 si\u00eau d\u1eef li\u1ec7u ch\u00ednh th\u1ee9c cho phim & TV series"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 967
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 968
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"type\":\"movie\",\"id\":\"top\",\"name\":\"Top Phim\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]},{\"type\":\"series\",\"id\":\"top\",\"name\":\"Top Series\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 969
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 970
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 971
    const-string p0, "https://v3-cinemeta.strem.io/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 972
    return-object v0

    .line 973
    :cond_61
    const-string v1, "opensubtitles"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1
    :try_end_67
    .catchall {:try_start_23 .. :try_end_67} :catchall_131

    const-string v13, "[]"

    if-eqz v1, :cond_a7

    .line 974
    :try_start_6b
    const-string p0, "org.stremio.opensubtitles-v3"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 975
    const-string p0, "OpenSubtitles v3"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 976
    const-string p0, "1.0.0"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 977
    const-string p0, "Ph\u1ee5 \u0111\u1ec1 \u0111a ng\u00f4n ng\u1eef ch\u00ednh th\u1ee9c t\u1eeb OpenSubtitles"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 978
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 979
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v13}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 980
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"subtitles\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 981
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 982
    const-string p0, "https://opensubtitles-v3.strem.io/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 983
    return-object v0

    .line 984
    :cond_a7
    const-string v1, "cyberflix"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_ed

    .line 985
    const-string p0, "tv.cyberflix.catalog"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 986
    const-string p0, "Cyberflix Catalog"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 987
    const-string p0, "1.5.0"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 988
    const-string p0, "Danh m\u1ee5c phim v\u00e0 series t\u1eeb c\u00e1c n\u1ec1n t\u1ea3ng tr\u1ef1c tuy\u1ebfn ph\u1ed5 bi\u1ebfn"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 989
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 990
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"type\":\"movie\",\"id\":\"cyberflix_netflix_movies\",\"name\":\"Netflix Phim\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]},{\"type\":\"series\",\"id\":\"cyberflix_netflix_series\",\"name\":\"Netflix Series\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 991
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 992
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 993
    const-string p0, ""

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 994
    return-object v0

    .line 995
    :cond_ed
    const-string v1, "torrentio"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_132

    .line 996
    const-string p0, "com.stremio.torrentio"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 997
    const-string p0, "Torrentio"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 998
    const-string p0, "1.0.13"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 999
    const-string p0, "Ngu\u1ed3n ph\u00e1t torrent v\u00e0 debrid \u0111a d\u1ea1ng"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1000
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1001
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v13}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1002
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"stream\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1003
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 1004
    const-string p0, "https://torrentio.strem.fun/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_130
    .catchall {:try_start_6b .. :try_end_130} :catchall_131

    .line 1005
    return-object v0

    .line 1007
    :catchall_131
    move-exception p0

    :cond_132
    nop

    .line 1008
    const/4 p0, 0x0

    return-object p0
.end method

.method public static installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I
    .registers 38
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)I"
        }
    .end annotation

    .line 755
    const-string v2, "type"

    const-string v3, "resources"

    const-string v4, "catalogs"

    const-string v5, "description"

    const-string v6, "version"

    const-string v7, "extraRequired"

    const-string v8, "extraSupported"

    const-string v9, "extra"

    const-string v10, "id"

    const-string v11, "/manifest.json"

    const-string v12, "idPrefixes"

    const-string v13, "types"

    const-string v14, "name"

    if-eqz p0, :cond_4ae

    if-eqz p1, :cond_4ae

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_26

    goto/16 :goto_4ae

    .line 757
    :cond_26
    new-instance v15, Ljava/util/ArrayList;

    invoke-direct {v15}, Ljava/util/ArrayList;-><init>()V

    .line 758
    nop

    .line 760
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v17

    const/16 v18, 0x0

    :goto_32
    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_436

    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 762
    :try_start_3e
    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 763
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v19

    if-eqz v19, :cond_50

    move-object/from16 v19, v3

    move-object/from16 v21, v11

    move-object/from16 v20, v15

    goto/16 :goto_e0

    .line 765
    :cond_50
    invoke-virtual {v0, v11}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v19
    :try_end_54
    .catchall {:try_start_3e .. :try_end_54} :catchall_416

    const-string v1, "/+$"

    move-object/from16 v20, v15

    const-string v15, ""

    if-eqz v19, :cond_5f

    move-object/from16 v19, v3

    goto :goto_76

    :cond_5f
    :try_start_5f
    invoke-virtual {v0, v1, v15}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_63
    .catchall {:try_start_5f .. :try_end_63} :catchall_404

    move-object/from16 v19, v3

    :try_start_65
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 766
    :goto_76
    nop

    .line 767
    invoke-virtual {v0, v11}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v3
    :try_end_7b
    .catchall {:try_start_65 .. :try_end_7b} :catchall_3f6

    .line 768
    move-object/from16 v21, v11

    const/4 v11, -0x1

    if-eq v3, v11, :cond_8e

    .line 769
    const/4 v11, 0x0

    :try_start_81
    invoke-virtual {v0, v11, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    goto :goto_8f

    .line 923
    :catchall_86
    move-exception v0

    move-object v3, v6

    move-object v6, v5

    move-object v5, v3

    move-object/from16 v25, v4

    goto/16 :goto_3fe

    .line 768
    :cond_8e
    move-object v3, v0

    .line 771
    :goto_8f
    invoke-virtual {v3, v1, v15}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1
    :try_end_93
    .catchall {:try_start_81 .. :try_end_93} :catchall_86

    .line 773
    nop

    .line 775
    :try_start_94
    new-instance v3, Ljava/net/URL;

    invoke-direct {v3, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 776
    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 777
    const-string v3, "GET"

    invoke-virtual {v0, v3}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 778
    const-string v3, "User-Agent"

    const-string v11, "Mozilla/5.0 StreamSync"

    invoke-virtual {v0, v3, v11}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 779
    const/16 v3, 0x1770

    invoke-virtual {v0, v3}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 780
    invoke-virtual {v0, v3}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 781
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v3

    const/16 v11, 0xc8

    if-ne v3, v11, :cond_c9

    .line 782
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 783
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
    :try_end_c8
    .catchall {:try_start_94 .. :try_end_c8} :catchall_cb

    goto :goto_ca

    .line 781
    :cond_c9
    const/4 v3, 0x0

    .line 785
    :goto_ca
    goto :goto_cd

    :catchall_cb
    move-exception v0

    const/4 v3, 0x0

    .line 787
    :goto_cd
    if-nez v3, :cond_d3

    .line 788
    :try_start_cf
    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->getDefaultManifestFallback(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v3

    .line 791
    :cond_d3
    if-nez v3, :cond_d6

    goto :goto_e0

    .line 793
    :cond_d6
    invoke-virtual {v3, v10, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 794
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v11

    if-eqz v11, :cond_e8

    .line 760
    :goto_e0
    move-object/from16 v3, v19

    move-object/from16 v15, v20

    move-object/from16 v11, v21

    goto/16 :goto_32

    .line 796
    :cond_e8
    invoke-virtual {v3, v14, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 797
    move-object/from16 p1, v11

    const-string v11, "1.0.0"

    invoke-virtual {v3, v6, v11}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11
    :try_end_f4
    .catchall {:try_start_cf .. :try_end_f4} :catchall_86

    .line 798
    move-object/from16 v22, v6

    :try_start_f6
    invoke-virtual {v3, v5, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6
    :try_end_fa
    .catchall {:try_start_f6 .. :try_end_fa} :catchall_3ec

    .line 799
    move-object/from16 v23, v5

    :try_start_fc
    const-string v5, "logo"

    move-object/from16 v24, v6

    const-string v6, "icon"

    invoke-virtual {v3, v6, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v5, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 801
    const-string v6, "movie,series"

    .line 802
    move-object/from16 v25, v6

    new-instance v6, Lorg/json/JSONArray;

    move-object/from16 v26, v5

    const-string v5, "[\"movie\",\"series\"]"

    invoke-direct {v6, v5}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 803
    invoke-virtual {v3, v13}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5
    :try_end_11b
    .catchall {:try_start_fc .. :try_end_11b} :catchall_3e1

    move/from16 v27, v5

    const-string v5, ","

    if-eqz v27, :cond_164

    .line 804
    move-object/from16 v27, v6

    :try_start_123
    invoke-virtual {v3, v13}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v6

    .line 805
    if-eqz v6, :cond_15d

    invoke-virtual {v6}, Lorg/json/JSONArray;->length()I

    move-result v28

    if-lez v28, :cond_15d

    .line 806
    nop

    .line 807
    move-object/from16 v28, v11

    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V

    .line 808
    move-object/from16 v29, v0

    const/4 v0, 0x0

    :goto_13a
    move-object/from16 v30, v1

    invoke-virtual {v6}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-ge v0, v1, :cond_153

    .line 809
    if-lez v0, :cond_147

    invoke-virtual {v11, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 810
    :cond_147
    invoke-virtual {v6, v0}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v11, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 808
    add-int/lit8 v0, v0, 0x1

    move-object/from16 v1, v30

    goto :goto_13a

    .line 812
    :cond_153
    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    move-object/from16 v35, v6

    move-object v6, v0

    move-object/from16 v0, v35

    goto :goto_170

    .line 805
    :cond_15d
    move-object/from16 v29, v0

    move-object/from16 v30, v1

    move-object/from16 v28, v11

    goto :goto_16c

    .line 803
    :cond_164
    move-object/from16 v29, v0

    move-object/from16 v30, v1

    move-object/from16 v27, v6

    move-object/from16 v28, v11

    .line 816
    :goto_16c
    move-object/from16 v6, v25

    move-object/from16 v0, v27

    :goto_170
    new-instance v1, Lorg/json/JSONArray;

    const-string v11, "[\"tt\"]"

    invoke-direct {v1, v11}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 817
    const-string v11, "tt"

    .line 818
    invoke-virtual {v3, v12}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v25
    :try_end_17d
    .catchall {:try_start_123 .. :try_end_17d} :catchall_3e1

    if-eqz v25, :cond_1be

    .line 819
    move-object/from16 v25, v1

    :try_start_181
    invoke-virtual {v3, v12}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v1

    .line 820
    if-eqz v1, :cond_1b4

    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v27

    if-lez v27, :cond_1b4

    .line 821
    nop

    .line 822
    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V
    :try_end_193
    .catchall {:try_start_181 .. :try_end_193} :catchall_1b9

    .line 823
    move-object/from16 v27, v6

    const/4 v6, 0x0

    :goto_196
    move-object/from16 v31, v12

    :try_start_198
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v12

    if-ge v6, v12, :cond_1af

    .line 824
    if-lez v6, :cond_1a3

    invoke-virtual {v11, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 825
    :cond_1a3
    invoke-virtual {v1, v6}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 823
    add-int/lit8 v6, v6, 0x1

    move-object/from16 v12, v31

    goto :goto_196

    .line 827
    :cond_1af
    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v11

    goto :goto_1c6

    .line 820
    :cond_1b4
    move-object/from16 v27, v6

    move-object/from16 v31, v12

    goto :goto_1c4

    .line 923
    :catchall_1b9
    move-exception v0

    move-object/from16 v31, v12

    goto/16 :goto_3d8

    .line 818
    :cond_1be
    move-object/from16 v25, v1

    move-object/from16 v27, v6

    move-object/from16 v31, v12

    .line 832
    :goto_1c4
    move-object/from16 v1, v25

    :goto_1c6
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5}, Lorg/json/JSONArray;-><init>()V

    .line 833
    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_281

    .line 834
    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    .line 835
    instance-of v12, v6, Lorg/json/JSONArray;

    if-eqz v12, :cond_27e

    .line 836
    check-cast v6, Lorg/json/JSONArray;
    :try_end_1db
    .catchall {:try_start_198 .. :try_end_1db} :catchall_3d7

    .line 837
    const/4 v12, 0x0

    :goto_1dc
    move-object/from16 v25, v4

    :try_start_1de
    invoke-virtual {v6}, Lorg/json/JSONArray;->length()I

    move-result v4

    if-ge v12, v4, :cond_283

    .line 838
    invoke-virtual {v6, v12}, Lorg/json/JSONArray;->opt(I)Ljava/lang/Object;

    move-result-object v4

    .line 839
    move-object/from16 v32, v6

    instance-of v6, v4, Lorg/json/JSONObject;

    if-eqz v6, :cond_274

    .line 840
    check-cast v4, Lorg/json/JSONObject;

    .line 841
    invoke-virtual {v4, v10, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 842
    invoke-virtual {v4, v2, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v33

    invoke-virtual/range {v33 .. v33}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v33

    .line 843
    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v34

    if-nez v34, :cond_271

    .line 844
    invoke-virtual/range {v33 .. v33}, Ljava/lang/String;->isEmpty()Z

    move-result v33

    if-eqz v33, :cond_214

    move/from16 v33, v12

    const-string v12, "movie"

    invoke-virtual {v4, v2, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_216

    :cond_214
    move/from16 v33, v12

    .line 845
    :goto_216
    invoke-virtual {v4, v14}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_226

    invoke-virtual {v4, v14, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v12

    if-eqz v12, :cond_229

    .line 846
    :cond_226
    invoke-virtual {v4, v14, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 848
    :cond_229
    invoke-virtual {v4, v9}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6
    :try_end_22d
    .catchall {:try_start_1de .. :try_end_22d} :catchall_3d5

    const-string v12, "[]"

    if-eqz v6, :cond_239

    :try_start_231
    invoke-virtual {v4, v9}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    instance-of v6, v6, Lorg/json/JSONArray;

    if-nez v6, :cond_241

    .line 849
    :cond_239
    new-instance v6, Lorg/json/JSONArray;

    invoke-direct {v6, v12}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v9, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 851
    :cond_241
    invoke-virtual {v4, v8}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_24f

    invoke-virtual {v4, v8}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    instance-of v6, v6, Lorg/json/JSONArray;

    if-nez v6, :cond_257

    .line 852
    :cond_24f
    new-instance v6, Lorg/json/JSONArray;

    invoke-direct {v6, v12}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v8, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 854
    :cond_257
    invoke-virtual {v4, v7}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_265

    invoke-virtual {v4, v7}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    instance-of v6, v6, Lorg/json/JSONArray;

    if-nez v6, :cond_26d

    .line 855
    :cond_265
    new-instance v6, Lorg/json/JSONArray;

    invoke-direct {v6, v12}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v7, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 857
    :cond_26d
    invoke-virtual {v5, v4}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_276

    .line 843
    :cond_271
    move/from16 v33, v12

    goto :goto_276

    .line 839
    :cond_274
    move/from16 v33, v12

    .line 837
    :goto_276
    add-int/lit8 v12, v33, 0x1

    move-object/from16 v4, v25

    move-object/from16 v6, v32

    goto/16 :goto_1dc

    .line 835
    :cond_27e
    move-object/from16 v25, v4

    goto :goto_283

    .line 833
    :cond_281
    move-object/from16 v25, v4

    .line 863
    :cond_283
    :goto_283
    invoke-virtual {v5}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v4

    .line 867
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5}, Lorg/json/JSONArray;-><init>()V
    :try_end_28c
    .catchall {:try_start_231 .. :try_end_28c} :catchall_3d5

    .line 868
    move-object/from16 v6, v19

    :try_start_28e
    invoke-virtual {v3, v6}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v12
    :try_end_292
    .catchall {:try_start_28e .. :try_end_292} :catchall_3c6

    if-eqz v12, :cond_342

    .line 869
    :try_start_294
    invoke-virtual {v3, v6}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    .line 870
    instance-of v12, v3, Lorg/json/JSONArray;

    if-eqz v12, :cond_332

    .line 871
    check-cast v3, Lorg/json/JSONArray;
    :try_end_29e
    .catchall {:try_start_294 .. :try_end_29e} :catchall_339

    .line 872
    const/4 v12, 0x0

    :goto_29f
    move-object/from16 v19, v2

    :try_start_2a1
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v12, v2, :cond_32b

    .line 873
    invoke-virtual {v3, v12}, Lorg/json/JSONArray;->opt(I)Ljava/lang/Object;

    move-result-object v2

    .line 874
    move-object/from16 v32, v3

    instance-of v3, v2, Ljava/lang/String;
    :try_end_2af
    .catchall {:try_start_2a1 .. :try_end_2af} :catchall_330

    if-eqz v3, :cond_2e2

    .line 875
    :try_start_2b1
    check-cast v2, Ljava/lang/String;

    .line 876
    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_2d7

    .line 877
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3}, Lorg/json/JSONObject;-><init>()V

    .line 878
    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v3, v14, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 879
    invoke-virtual {v3, v13, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_2cc
    .catchall {:try_start_2b1 .. :try_end_2cc} :catchall_2dd

    .line 880
    move-object/from16 v2, v31

    :try_start_2ce
    invoke-virtual {v3, v2, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 881
    invoke-virtual {v5, v3}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;
    :try_end_2d4
    .catchall {:try_start_2ce .. :try_end_2d4} :catchall_2d5

    goto :goto_2d9

    .line 923
    :catchall_2d5
    move-exception v0

    goto :goto_2e0

    .line 876
    :cond_2d7
    move-object/from16 v2, v31

    .line 883
    :goto_2d9
    move-object v3, v2

    move-object/from16 v31, v6

    goto :goto_31f

    .line 923
    :catchall_2dd
    move-exception v0

    move-object/from16 v2, v31

    :goto_2e0
    move-object v3, v2

    goto :goto_33e

    .line 883
    :cond_2e2
    move-object/from16 v3, v31

    move-object/from16 v31, v6

    :try_start_2e6
    instance-of v6, v2, Lorg/json/JSONObject;

    if-eqz v6, :cond_31f

    .line 884
    check-cast v2, Lorg/json/JSONObject;

    .line 885
    invoke-virtual {v2, v14, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 886
    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v6

    if-nez v6, :cond_31f

    .line 887
    invoke-virtual {v2, v13}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_308

    invoke-virtual {v2, v13}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    instance-of v6, v6, Lorg/json/JSONArray;

    if-nez v6, :cond_30b

    .line 888
    :cond_308
    invoke-virtual {v2, v13, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 890
    :cond_30b
    invoke-virtual {v2, v3}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_319

    invoke-virtual {v2, v3}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    instance-of v6, v6, Lorg/json/JSONArray;

    if-nez v6, :cond_31c

    .line 891
    :cond_319
    invoke-virtual {v2, v3, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 893
    :cond_31c
    invoke-virtual {v5, v2}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 872
    :cond_31f
    :goto_31f
    add-int/lit8 v12, v12, 0x1

    move-object/from16 v2, v19

    move-object/from16 v6, v31

    move-object/from16 v31, v3

    move-object/from16 v3, v32

    goto/16 :goto_29f

    :cond_32b
    move-object/from16 v3, v31

    move-object/from16 v31, v6

    goto :goto_348

    .line 923
    :catchall_330
    move-exception v0

    goto :goto_33c

    .line 870
    :cond_332
    move-object/from16 v19, v2

    move-object/from16 v3, v31

    move-object/from16 v31, v6

    goto :goto_348

    .line 923
    :catchall_339
    move-exception v0

    move-object/from16 v19, v2

    :goto_33c
    move-object/from16 v3, v31

    :goto_33e
    move-object/from16 v31, v6

    goto/16 :goto_3c1

    .line 868
    :cond_342
    move-object/from16 v19, v2

    move-object/from16 v3, v31

    move-object/from16 v31, v6

    .line 899
    :goto_348
    invoke-virtual {v5}, Lorg/json/JSONArray;->length()I

    move-result v0

    if-nez v0, :cond_355

    .line 900
    new-instance v5, Lorg/json/JSONArray;

    const-string v0, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"stream\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {v5, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 902
    :cond_355
    invoke-virtual {v5}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v0

    .line 904
    new-instance v1, Landroid/content/ContentValues;

    invoke-direct {v1}, Landroid/content/ContentValues;-><init>()V

    .line 905
    const-string v2, "baseUrl"

    move-object/from16 v5, v30

    invoke-virtual {v1, v2, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 906
    move-object/from16 v2, v29

    invoke-virtual {v1, v10, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 907
    move-object/from16 v2, p1

    invoke-virtual {v1, v14, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_36f
    .catchall {:try_start_2e6 .. :try_end_36f} :catchall_3c0

    .line 908
    move-object/from16 v5, v22

    move-object/from16 v2, v28

    :try_start_373
    invoke-virtual {v1, v5, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_376
    .catchall {:try_start_373 .. :try_end_376} :catchall_3bc

    .line 909
    move-object/from16 v6, v23

    move-object/from16 v2, v24

    :try_start_37a
    invoke-virtual {v1, v6, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 910
    const-string v2, "typesCsv"

    move-object/from16 v12, v27

    invoke-virtual {v1, v2, v12}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 911
    const-string v2, "catalogsJson"

    invoke-virtual {v1, v2, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 912
    const-string v2, "resourcesJson"

    invoke-virtual {v1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 913
    const-string v0, "idPrefixesCsv"

    invoke-virtual {v1, v0, v11}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 914
    invoke-virtual/range {v26 .. v26}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_397
    .catchall {:try_start_37a .. :try_end_397} :catchall_3b9

    const-string v2, "logoUrl"

    if-nez v0, :cond_3a1

    .line 915
    move-object/from16 v0, v26

    :try_start_39d
    invoke-virtual {v1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_3a4

    .line 917
    :cond_3a1
    invoke-virtual {v1, v2}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 919
    :goto_3a4
    const-string v0, "sortOrder"

    invoke-static/range {v18 .. v18}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V
    :try_end_3ad
    .catchall {:try_start_39d .. :try_end_3ad} :catchall_3b9

    .line 921
    move-object/from16 v2, v20

    :try_start_3af
    invoke-interface {v2, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_3b2
    .catchall {:try_start_3af .. :try_end_3b2} :catchall_3b6

    .line 922
    add-int/lit8 v18, v18, 0x1

    goto/16 :goto_426

    .line 923
    :catchall_3b6
    move-exception v0

    goto/16 :goto_426

    :catchall_3b9
    move-exception v0

    goto/16 :goto_413

    :catchall_3bc
    move-exception v0

    move-object/from16 v2, v20

    goto :goto_3d1

    :catchall_3c0
    move-exception v0

    :goto_3c1
    move-object/from16 v2, v20

    move-object/from16 v5, v22

    goto :goto_3d1

    :catchall_3c6
    move-exception v0

    move-object/from16 v19, v2

    move-object/from16 v2, v20

    move-object/from16 v5, v22

    move-object/from16 v3, v31

    move-object/from16 v31, v6

    :goto_3d1
    move-object/from16 v6, v23

    goto/16 :goto_426

    :catchall_3d5
    move-exception v0

    goto :goto_3da

    :catchall_3d7
    move-exception v0

    :goto_3d8
    move-object/from16 v25, v4

    :goto_3da
    move-object/from16 v5, v22

    move-object/from16 v6, v23

    move-object/from16 v3, v31

    goto :goto_3ff

    :catchall_3e1
    move-exception v0

    move-object/from16 v25, v4

    move-object v3, v12

    move-object/from16 v31, v19

    move-object/from16 v5, v22

    move-object/from16 v6, v23

    goto :goto_401

    :catchall_3ec
    move-exception v0

    move-object/from16 v25, v4

    move-object v6, v5

    move-object v3, v12

    move-object/from16 v31, v19

    move-object/from16 v5, v22

    goto :goto_401

    :catchall_3f6
    move-exception v0

    move-object v3, v6

    move-object v6, v5

    move-object v5, v3

    move-object/from16 v25, v4

    move-object/from16 v21, v11

    :goto_3fe
    move-object v3, v12

    :goto_3ff
    move-object/from16 v31, v19

    :goto_401
    move-object/from16 v19, v2

    goto :goto_413

    :catchall_404
    move-exception v0

    move-object/from16 v19, v6

    move-object v6, v5

    move-object/from16 v5, v19

    move-object/from16 v19, v2

    move-object/from16 v31, v3

    move-object/from16 v25, v4

    move-object/from16 v21, v11

    move-object v3, v12

    :goto_413
    move-object/from16 v2, v20

    goto :goto_426

    :catchall_416
    move-exception v0

    move-object/from16 v19, v6

    move-object v6, v5

    move-object/from16 v5, v19

    move-object/from16 v19, v2

    move-object/from16 v31, v3

    move-object/from16 v25, v4

    move-object/from16 v21, v11

    move-object v3, v12

    move-object v2, v15

    :goto_426
    nop

    .line 924
    move-object v4, v6

    move-object v6, v5

    move-object v5, v4

    move-object v15, v2

    move-object v12, v3

    move-object/from16 v2, v19

    move-object/from16 v11, v21

    move-object/from16 v4, v25

    move-object/from16 v3, v31

    goto/16 :goto_32

    .line 926
    :cond_436
    move-object v2, v15

    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_440

    const/16 v16, 0x0

    return v16

    .line 928
    :cond_440
    const-string v0, "streamsync.db"

    move-object/from16 v1, p0

    invoke-virtual {v1, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 929
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v3

    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v3

    if-nez v3, :cond_459

    .line 930
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v3

    invoke-virtual {v3}, Ljava/io/File;->mkdirs()Z

    .line 933
    :cond_459
    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->ensureRoomDatabase(Landroid/content/Context;)V

    .line 936
    :try_start_45c
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/high16 v1, 0x20000000

    const/4 v3, 0x0

    invoke-static {v0, v3, v1}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1
    :try_end_467
    .catchall {:try_start_45c .. :try_end_467} :catchall_4ab

    .line 938
    :try_start_467
    const-string v0, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"

    invoke-virtual {v1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 939
    const-string v0, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'4201b4cf7d52e2b03ff5e210c14b7a60\')"

    invoke-virtual {v1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 940
    const-string v0, "PRAGMA user_version = 7"

    invoke-virtual {v1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 941
    const-string v0, "CREATE TABLE IF NOT EXISTS `installed_addons` (`baseUrl` TEXT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `version` TEXT NOT NULL, `description` TEXT, `typesCsv` TEXT NOT NULL, `catalogsJson` TEXT NOT NULL, `resourcesJson` TEXT NOT NULL, `idPrefixesCsv` TEXT NOT NULL, `logoUrl` TEXT, `sortOrder` INTEGER NOT NULL, PRIMARY KEY(`baseUrl`));"

    invoke-virtual {v1, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 943
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V
    :try_end_47e
    .catchall {:try_start_467 .. :try_end_47e} :catchall_4a6

    .line 945
    :try_start_47e
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_482
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_496

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/content/ContentValues;

    .line 946
    const-string v3, "installed_addons"

    const/4 v4, 0x5

    const/4 v5, 0x0

    invoke-virtual {v1, v3, v5, v2, v4}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 947
    goto :goto_482

    .line 948
    :cond_496
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_499
    .catchall {:try_start_47e .. :try_end_499} :catchall_4a1

    .line 950
    :try_start_499
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V
    :try_end_49c
    .catchall {:try_start_499 .. :try_end_49c} :catchall_4a6

    .line 951
    nop

    .line 953
    :try_start_49d
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V
    :try_end_4a0
    .catchall {:try_start_49d .. :try_end_4a0} :catchall_4ab

    .line 954
    goto :goto_4ac

    .line 950
    :catchall_4a1
    move-exception v0

    :try_start_4a2
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 951
    throw v0
    :try_end_4a6
    .catchall {:try_start_4a2 .. :try_end_4a6} :catchall_4a6

    .line 953
    :catchall_4a6
    move-exception v0

    :try_start_4a7
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 954
    throw v0
    :try_end_4ab
    .catchall {:try_start_4a7 .. :try_end_4ab} :catchall_4ab

    .line 955
    :catchall_4ab
    move-exception v0

    :goto_4ac
    nop

    .line 956
    return v18

    .line 755
    :cond_4ae
    :goto_4ae
    const/16 v16, 0x0

    return v16
.end method

.method public static installDefaultAddonsFallback(Landroid/content/Context;)I
    .registers 3

    .line 1012
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 1013
    const-string v1, "https://v3-cinemeta.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1014
    const-string v1, "https://opensubtitles-v3.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1015
    const-string v1, "https://catalog.cyberflix.tv/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1016
    const-string v1, "https://torrentio.strem.fun/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1017
    invoke-static {p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result p0

    return p0
.end method

.method public static login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 12

    .line 102
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$2;

    move-object v5, p0

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    move-object v6, p4

    invoke-direct/range {v1 .. v6}, Lcom/streamsync/app/auth/SupabaseSyncService$2;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 191
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 192
    return-void
.end method

.method public static normalizeUrl(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    .line 68
    if-nez p0, :cond_5

    const-string p0, ""

    return-object p0

    .line 69
    :cond_5
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 70
    :goto_9
    const-string v0, "/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1d

    .line 71
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    const/4 v1, 0x0

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    goto :goto_9

    .line 73
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

    .line 74
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 76
    :cond_44
    return-object p0
.end method

.method public static pollDeviceStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    .line 270
    :try_start_0
    new-instance v0, Ljava/net/URL;

    const-string v1, "https://api.nuvio.tv/rest/v1/rpc/poll_tv_login_session"

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 271
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 272
    const-string v1, "POST"

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 273
    const-string v1, "Content-Type"

    const-string v2, "application/json"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 274
    const-string v1, "apikey"

    const-string v2, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 275
    const-string v1, "Authorization"

    const-string v2, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 276
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 277
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 278
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 280
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    .line 281
    const-string v2, "p_code"

    invoke-virtual {v1, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 282
    const-string p0, "p_device_nonce"

    invoke-virtual {v1, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 284
    invoke-virtual {v1}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p0

    sget-object p1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p0, p1}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p0

    .line 285
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p1
    :try_end_50
    .catchall {:try_start_0 .. :try_end_50} :catchall_95

    .line 286
    :try_start_50
    invoke-virtual {p1, p0}, Ljava/io/OutputStream;->write([B)V
    :try_end_53
    .catchall {:try_start_50 .. :try_end_53} :catchall_89

    .line 287
    if-eqz p1, :cond_58

    :try_start_55
    invoke-virtual {p1}, Ljava/io/OutputStream;->close()V

    .line 289
    :cond_58
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result p0

    .line 290
    const/16 p1, 0xc8

    if-ne p0, p1, :cond_96

    .line 291
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 292
    new-instance p1, Lorg/json/JSONArray;

    invoke-direct {p1, p0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 293
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result p0

    if-lez p0, :cond_96

    .line 294
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

    .line 285
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

    .line 297
    :catchall_95
    move-exception p0

    :cond_96
    nop

    .line 298
    const-string p0, "error"

    return-object p0
.end method

.method public static pullAddonsFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 21

    .line 431
    move-object/from16 v1, p2

    move-object/from16 v2, p3

    const-string v3, "enabled"

    const-string v4, ""

    const-string v5, "url"

    const-string v6, "Bearer "

    const-string v7, "Authorization"

    const-string v8, "apikey"

    const-string v9, "GET"

    .line 433
    :try_start_12
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 434
    new-instance v12, Ljava/util/ArrayList;

    invoke-direct {v12}, Ljava/util/ArrayList;-><init>()V
    :try_end_1b
    .catchall {:try_start_12 .. :try_end_1b} :catchall_134

    .line 438
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

    .line 439
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 440
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 441
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 442
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v0, v7, v10}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 443
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 444
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 446
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    if-ne v10, v13, :cond_a1

    .line 447
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 448
    new-instance v10, Lorg/json/JSONArray;

    invoke-direct {v10, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 449
    const/4 v0, 0x0

    :goto_71
    invoke-virtual {v10}, Lorg/json/JSONArray;->length()I

    move-result v14

    if-ge v0, v14, :cond_a1

    .line 450
    invoke-virtual {v10, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v14

    .line 451
    invoke-virtual {v14, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    .line 452
    const/4 v15, 0x1

    invoke-virtual {v14, v3, v15}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v14

    .line 453
    if-eqz v14, :cond_99

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_99

    invoke-interface {v12, v13}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_99

    .line 454
    invoke-interface {v12, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_99
    .catchall {:try_start_1f .. :try_end_99} :catchall_a0

    .line 449
    :cond_99
    add-int/lit8 v0, v0, 0x1

    const/16 v13, 0xc8

    const/16 v15, 0x1f40

    goto :goto_71

    .line 458
    :catchall_a0
    move-exception v0

    :cond_a1
    nop

    .line 462
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

    .line 463
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 464
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 465
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 466
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v7, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 467
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 468
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 470
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-ne v1, v2, :cond_124

    .line 471
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 472
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 473
    const/4 v0, 0x0

    :goto_f8
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v0, v2, :cond_124

    .line 474
    invoke-virtual {v1, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v2

    .line 475
    invoke-virtual {v2, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 476
    const/4 v15, 0x1

    invoke-virtual {v2, v3, v15}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v2

    .line 477
    if-eqz v2, :cond_120

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_120

    invoke-interface {v12, v6}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_120

    .line 478
    invoke-interface {v12, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_120
    .catchall {:try_start_a2 .. :try_end_120} :catchall_123

    .line 473
    :cond_120
    add-int/lit8 v0, v0, 0x1

    goto :goto_f8

    .line 482
    :catchall_123
    move-exception v0

    :cond_124
    nop

    .line 484
    :try_start_125
    invoke-interface {v12}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_132

    .line 485
    move-object/from16 v1, p0

    invoke-static {v1, v12}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result v10
    :try_end_131
    .catchall {:try_start_125 .. :try_end_131} :catchall_134

    goto :goto_133

    .line 484
    :cond_132
    const/4 v10, 0x0

    .line 487
    :goto_133
    goto :goto_136

    :catchall_134
    move-exception v0

    const/4 v10, 0x0

    .line 488
    :goto_136
    return v10
.end method

.method private static pullLibraryFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .registers 13

    .line 679
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

    .line 680
    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p1

    check-cast p1, Ljava/net/HttpURLConnection;

    .line 681
    const-string v3, "POST"

    invoke-virtual {p1, v3}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 682
    const-string v3, "Content-Type"

    const-string v4, "application/json"

    invoke-virtual {p1, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 683
    const-string v3, "apikey"

    invoke-virtual {p1, v3, p3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 684
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

    .line 685
    const/16 p2, 0x1f40

    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 686
    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 687
    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 689
    new-instance p3, Lorg/json/JSONObject;

    invoke-direct {p3}, Lorg/json/JSONObject;-><init>()V

    .line 690
    const-string v3, "p_profile_id"

    invoke-virtual {p3, v3, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 691
    const-string p2, "p_limit"

    const/16 v3, 0x64

    invoke-virtual {p3, p2, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 692
    const-string p2, "p_offset"

    const/4 v3, 0x0

    invoke-virtual {p3, p2, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 693
    invoke-virtual {p3}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p2

    sget-object p3, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p2, p3}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p2

    .line 694
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p3
    :try_end_7e
    .catchall {:try_start_6 .. :try_end_7e} :catchall_1aa

    .line 695
    :try_start_7e
    invoke-virtual {p3, p2}, Ljava/io/OutputStream;->write([B)V
    :try_end_81
    .catchall {:try_start_7e .. :try_end_81} :catchall_19e

    .line 696
    if-eqz p3, :cond_86

    :try_start_83
    invoke-virtual {p3}, Ljava/io/OutputStream;->close()V

    .line 698
    :cond_86
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result p2

    const/16 p3, 0xc8

    if-ne p2, p3, :cond_1ab

    .line 699
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p1

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p1

    .line 700
    new-instance p2, Lorg/json/JSONArray;

    invoke-direct {p2, p1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 701
    invoke-virtual {p2}, Lorg/json/JSONArray;->length()I

    move-result p1

    if-nez p1, :cond_a2

    return-void

    .line 703
    :cond_a2
    const-string p1, "streamsync.db"

    invoke-virtual {p0, p1}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object p1

    .line 704
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result p3

    if-nez p3, :cond_af

    return-void

    .line 706
    :cond_af
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->ensureRoomDatabase(Landroid/content/Context;)V
    :try_end_b2
    .catchall {:try_start_83 .. :try_end_b2} :catchall_1aa

    .line 709
    :try_start_b2
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    const/high16 p1, 0x20000000

    const/4 p3, 0x0

    invoke-static {p0, p3, p1}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object p0
    :try_end_bd
    .catchall {:try_start_b2 .. :try_end_bd} :catchall_19c

    .line 711
    :try_start_bd
    const-string p1, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"

    invoke-virtual {p0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 712
    const-string p1, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'4201b4cf7d52e2b03ff5e210c14b7a60\')"

    invoke-virtual {p0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 713
    const-string p1, "PRAGMA user_version = 7"

    invoke-virtual {p0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 714
    const-string p1, "CREATE TABLE IF NOT EXISTS `library_items` (`mediaId` TEXT NOT NULL, `title` TEXT NOT NULL, `posterUrl` TEXT, `backgroundUrl` TEXT, `description` TEXT, `type` TEXT NOT NULL, `releaseInfo` TEXT, `imdbRating` TEXT, `genresJson` TEXT NOT NULL, `season` INTEGER, `episode` INTEGER, `addedAtEpochMs` INTEGER NOT NULL, PRIMARY KEY(`mediaId`));"

    invoke-virtual {p0, p1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 716
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V
    :try_end_d4
    .catchall {:try_start_bd .. :try_end_d4} :catchall_197

    .line 718
    nop

    :goto_d5
    :try_start_d5
    invoke-virtual {p2}, Lorg/json/JSONArray;->length()I

    move-result p1

    if-ge v3, p1, :cond_187

    .line 719
    invoke-virtual {p2, v3}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object p1

    .line 720
    const-string v4, "content_id"

    invoke-virtual {p1, v4, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 721
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_ed

    goto/16 :goto_183

    .line 723
    :cond_ed
    new-instance v5, Landroid/content/ContentValues;

    invoke-direct {v5}, Landroid/content/ContentValues;-><init>()V

    .line 724
    const-string v6, "mediaId"

    invoke-virtual {v5, v6, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 725
    const-string v4, "title"

    const-string v6, "name"

    invoke-virtual {p1, v6, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 726
    const-string v4, "posterUrl"

    const-string v6, "poster"

    invoke-virtual {p1, v6, p3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 727
    const-string v4, "backgroundUrl"

    const-string v6, "background"

    invoke-virtual {p1, v6, p3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 728
    invoke-virtual {p1, v1, p3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 729
    const-string v4, "type"

    const-string v6, "content_type"

    const-string v7, "movie"

    invoke-virtual {p1, v6, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 730
    const-string v4, "releaseInfo"

    const-string v6, "release_info"

    invoke-virtual {p1, v6, p3}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 731
    const-string v4, "imdbRating"

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_148

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    goto :goto_149

    :cond_148
    move-object v6, p3

    :goto_149
    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 733
    const-string v4, "genres"

    invoke-virtual {p1, v4}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 734
    const-string v6, "genresJson"

    if-eqz v4, :cond_15b

    invoke-virtual {v4}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v4

    goto :goto_15d

    :cond_15b
    const-string v4, "[]"

    :goto_15d
    invoke-virtual {v5, v6, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 736
    const-string v4, "season"

    invoke-virtual {v5, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 737
    const-string v4, "episode"

    invoke-virtual {v5, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 738
    const-string v4, "addedAtEpochMs"

    const-string v6, "added_at"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    invoke-virtual {p1, v6, v7, v8}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v6

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-virtual {v5, v4, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 740
    const-string p1, "library_items"

    const/4 v4, 0x5

    invoke-virtual {p0, p1, p3, v5, v4}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 718
    :goto_183
    add-int/lit8 v3, v3, 0x1

    goto/16 :goto_d5

    .line 742
    :cond_187
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_18a
    .catchall {:try_start_d5 .. :try_end_18a} :catchall_192

    .line 744
    :try_start_18a
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V
    :try_end_18d
    .catchall {:try_start_18a .. :try_end_18d} :catchall_197

    .line 745
    nop

    .line 747
    :try_start_18e
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->close()V
    :try_end_191
    .catchall {:try_start_18e .. :try_end_191} :catchall_19c

    .line 748
    goto :goto_19d

    .line 744
    :catchall_192
    move-exception p1

    :try_start_193
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 745
    throw p1
    :try_end_197
    .catchall {:try_start_193 .. :try_end_197} :catchall_197

    .line 747
    :catchall_197
    move-exception p1

    :try_start_198
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 748
    throw p1
    :try_end_19c
    .catchall {:try_start_198 .. :try_end_19c} :catchall_19c

    .line 749
    :catchall_19c
    move-exception p0

    :goto_19d
    goto :goto_1ab

    .line 694
    :catchall_19e
    move-exception p0

    if-eqz p3, :cond_1a9

    :try_start_1a1
    invoke-virtual {p3}, Ljava/io/OutputStream;->close()V
    :try_end_1a4
    .catchall {:try_start_1a1 .. :try_end_1a4} :catchall_1a5

    goto :goto_1a9

    :catchall_1a5
    move-exception p1

    :try_start_1a6
    invoke-static {p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_1a9
    :goto_1a9
    throw p0
    :try_end_1aa
    .catchall {:try_start_1a6 .. :try_end_1aa} :catchall_1aa

    .line 751
    :catchall_1aa
    move-exception p0

    :cond_1ab
    :goto_1ab
    nop

    .line 752
    return-void
.end method

.method public static pullWatchProgressFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 30

    .line 492
    move-object/from16 v2, p2

    move-object/from16 v3, p3

    const-string v4, "content_type"

    const-string v5, "position"

    const-string v6, "p_profile_id"

    const-string v7, "application/json"

    const-string v8, "Content-Type"

    const-string v9, "POST"

    const-string v10, ":"

    const-string v11, "episode"

    const-string v12, "season"

    const-string v13, "content_id"

    const-string v14, "Bearer "

    const-string v15, "Authorization"

    move-object/from16 v16, v10

    const-string v10, "apikey"

    const-string v1, ""

    .line 494
    move-object/from16 v17, v5

    :try_start_24
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 495
    move-object/from16 v19, v11

    new-instance v11, Ljava/util/ArrayList;

    invoke-direct {v11}, Ljava/util/ArrayList;-><init>()V

    .line 496
    move-object/from16 v20, v12

    new-instance v12, Ljava/util/HashSet;

    invoke-direct {v12}, Ljava/util/HashSet;-><init>()V
    :try_end_36
    .catchall {:try_start_24 .. :try_end_36} :catchall_4b9

    .line 500
    move-object/from16 p1, v12

    :try_start_38
    new-instance v0, Ljava/net/URL;

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12
    :try_end_43
    .catchall {:try_start_38 .. :try_end_43} :catchall_d4

    move-object/from16 v24, v4

    :try_start_45
    const-string v4, "/rest/v1/rpc/sync_pull_watch_progress"

    invoke-virtual {v12, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v0, v4}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 501
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 502
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 503
    invoke-virtual {v0, v8, v7}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 504
    invoke-virtual {v0, v10, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 505
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v15, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 506
    const/16 v4, 0x1f40

    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 507
    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 508
    const/4 v4, 0x1

    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 510
    new-instance v12, Lorg/json/JSONObject;

    invoke-direct {v12}, Lorg/json/JSONObject;-><init>()V

    .line 511
    invoke-virtual {v12, v6, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 512
    invoke-virtual {v12}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v4

    sget-object v12, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v4, v12}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v4

    .line 513
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v12
    :try_end_97
    .catchall {:try_start_45 .. :try_end_97} :catchall_d2

    .line 514
    :try_start_97
    invoke-virtual {v12, v4}, Ljava/io/OutputStream;->write([B)V
    :try_end_9a
    .catchall {:try_start_97 .. :try_end_9a} :catchall_c5

    .line 515
    if-eqz v12, :cond_9f

    :try_start_9c
    invoke-virtual {v12}, Ljava/io/OutputStream;->close()V

    .line 517
    :cond_9f
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    const/16 v12, 0xc8

    if-ne v4, v12, :cond_d7

    .line 518
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 519
    new-instance v4, Lorg/json/JSONArray;

    invoke-direct {v4, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 520
    const/4 v0, 0x0

    :goto_b5
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v12

    if-ge v0, v12, :cond_d7

    .line 521
    invoke-virtual {v4, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v12

    invoke-interface {v11, v12}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_c2
    .catchall {:try_start_9c .. :try_end_c2} :catchall_d2

    .line 520
    add-int/lit8 v0, v0, 0x1

    goto :goto_b5

    .line 513
    :catchall_c5
    move-exception v0

    move-object v4, v0

    if-eqz v12, :cond_d1

    :try_start_c9
    invoke-virtual {v12}, Ljava/io/OutputStream;->close()V
    :try_end_cc
    .catchall {:try_start_c9 .. :try_end_cc} :catchall_cd

    goto :goto_d1

    :catchall_cd
    move-exception v0

    :try_start_ce
    invoke-static {v4, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_d1
    :goto_d1
    throw v4
    :try_end_d2
    .catchall {:try_start_ce .. :try_end_d2} :catchall_d2

    .line 524
    :catchall_d2
    move-exception v0

    goto :goto_d7

    :catchall_d4
    move-exception v0

    move-object/from16 v24, v4

    :cond_d7
    :goto_d7
    nop

    .line 528
    :try_start_d8
    new-instance v0, Ljava/net/URL;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v12, "/rest/v1/rpc/sync_pull_watched_items"

    invoke-virtual {v4, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v0, v4}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 529
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 530
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 531
    invoke-virtual {v0, v8, v7}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 532
    invoke-virtual {v0, v10, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 533
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v15, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 534
    const/16 v4, 0x1f40

    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 535
    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 536
    const/4 v4, 0x1

    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 538
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 539
    invoke-virtual {v7, v6, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 540
    const-string v6, "p_page"

    invoke-virtual {v7, v6, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 541
    const-string v4, "p_page_size"

    const/16 v6, 0x64

    invoke-virtual {v7, v4, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 542
    invoke-virtual {v7}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v4

    sget-object v6, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v4, v6}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v4

    .line 543
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v6
    :try_end_141
    .catchall {:try_start_d8 .. :try_end_141} :catchall_202

    .line 544
    :try_start_141
    invoke-virtual {v6, v4}, Ljava/io/OutputStream;->write([B)V
    :try_end_144
    .catchall {:try_start_141 .. :try_end_144} :catchall_1eb

    .line 545
    if-eqz v6, :cond_149

    :try_start_146
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V

    .line 547
    :cond_149
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    const/16 v12, 0xc8

    if-ne v4, v12, :cond_1e2

    .line 548
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 549
    new-instance v4, Lorg/json/JSONArray;

    invoke-direct {v4, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 550
    const/4 v0, 0x0

    :goto_15f
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v0, v6, :cond_1d9

    .line 551
    invoke-virtual {v4, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v6

    .line 552
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 553
    invoke-virtual {v6, v13, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v13, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 554
    move-object/from16 v8, v24

    invoke-virtual {v6, v8, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v7, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_17e
    .catchall {:try_start_146 .. :try_end_17e} :catchall_202

    .line 555
    move-object/from16 v9, v20

    const/4 v12, 0x0

    move/from16 v20, v0

    :try_start_183
    invoke-virtual {v6, v9, v12}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v0

    invoke-virtual {v7, v9, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;
    :try_end_18a
    .catchall {:try_start_183 .. :try_end_18a} :catchall_1d3

    .line 556
    move-object/from16 v25, v19

    move-object/from16 v19, v4

    move-object/from16 v4, v25

    :try_start_190
    invoke-virtual {v6, v4, v12}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v0

    invoke-virtual {v7, v4, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;
    :try_end_197
    .catchall {:try_start_190 .. :try_end_197} :catchall_1cf

    .line 557
    const/16 v0, 0x3e8

    move-object/from16 v12, v17

    :try_start_19b
    invoke-virtual {v7, v12, v0}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 558
    const-string v0, "duration"

    move-object/from16 v24, v8

    const/16 v8, 0x3e8

    invoke-virtual {v7, v0, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 559
    const-string v0, "last_watched"

    const-string v8, "watched_at"
    :try_end_1ab
    .catchall {:try_start_19b .. :try_end_1ab} :catchall_1cb

    move-object/from16 v23, v12

    move-object/from16 v17, v13

    :try_start_1af
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v12

    invoke-virtual {v6, v8, v12, v13}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v12

    invoke-virtual {v7, v0, v12, v13}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    .line 560
    invoke-interface {v11, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_1bd
    .catchall {:try_start_1af .. :try_end_1bd} :catchall_200

    .line 550
    add-int/lit8 v0, v20, 0x1

    move-object/from16 v13, v19

    move-object/from16 v19, v4

    move-object v4, v13

    move-object/from16 v20, v9

    move-object/from16 v13, v17

    move-object/from16 v17, v23

    goto :goto_15f

    .line 563
    :catchall_1cb
    move-exception v0

    move-object/from16 v23, v12

    goto :goto_209

    :catchall_1cf
    move-exception v0

    move-object/from16 v23, v17

    goto :goto_209

    :catchall_1d3
    move-exception v0

    move-object/from16 v23, v17

    move-object/from16 v4, v19

    goto :goto_209

    .line 550
    :cond_1d9
    move-object/from16 v23, v17

    move-object/from16 v4, v19

    move-object/from16 v9, v20

    move-object/from16 v17, v13

    goto :goto_20b

    .line 547
    :cond_1e2
    move-object/from16 v23, v17

    move-object/from16 v4, v19

    move-object/from16 v9, v20

    move-object/from16 v17, v13

    goto :goto_20b

    .line 543
    :catchall_1eb
    move-exception v0

    move-object/from16 v23, v17

    move-object/from16 v4, v19

    move-object/from16 v9, v20

    move-object/from16 v17, v13

    move-object v7, v0

    if-eqz v6, :cond_1ff

    :try_start_1f7
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V
    :try_end_1fa
    .catchall {:try_start_1f7 .. :try_end_1fa} :catchall_1fb

    goto :goto_1ff

    :catchall_1fb
    move-exception v0

    :try_start_1fc
    invoke-static {v7, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_1ff
    :goto_1ff
    throw v7
    :try_end_200
    .catchall {:try_start_1fc .. :try_end_200} :catchall_200

    .line 563
    :catchall_200
    move-exception v0

    goto :goto_20b

    :catchall_202
    move-exception v0

    move-object/from16 v23, v17

    move-object/from16 v4, v19

    move-object/from16 v9, v20

    :goto_209
    move-object/from16 v17, v13

    :goto_20b
    nop

    .line 566
    :try_start_20c
    invoke-interface {v11}, Ljava/util/List;->isEmpty()Z

    move-result v0
    :try_end_210
    .catchall {:try_start_20c .. :try_end_210} :catchall_4b9

    if-eqz v0, :cond_27c

    .line 568
    :try_start_212
    new-instance v0, Ljava/net/URL;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, "/rest/v1/watch_progress?select=*"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v0, v6}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 569
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 570
    const-string v6, "GET"

    invoke-virtual {v0, v6}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 571
    invoke-virtual {v0, v10, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 572
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v15, v6}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 573
    const/16 v6, 0x1f40

    invoke-virtual {v0, v6}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 574
    invoke-virtual {v0, v6}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 576
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v6

    const/16 v12, 0xc8

    if-ne v6, v12, :cond_27b

    .line 577
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 578
    new-instance v6, Lorg/json/JSONArray;

    invoke-direct {v6, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 579
    const/4 v0, 0x0

    :goto_26a
    invoke-virtual {v6}, Lorg/json/JSONArray;->length()I

    move-result v7

    if-ge v0, v7, :cond_27b

    .line 580
    invoke-virtual {v6, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v7

    invoke-interface {v11, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_277
    .catchall {:try_start_212 .. :try_end_277} :catchall_27a

    .line 579
    add-int/lit8 v0, v0, 0x1

    goto :goto_26a

    .line 583
    :catchall_27a
    move-exception v0

    :cond_27b
    nop

    .line 587
    :cond_27c
    move-object/from16 v6, p0

    :try_start_27e
    invoke-static {v6, v5, v2, v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->pullLibraryFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 589
    invoke-interface {v11}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_28a

    .line 590
    const/16 v18, 0x0

    return v18

    .line 593
    :cond_28a
    const-string v0, "streamsync.db"

    invoke-virtual {v6, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 594
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_2a1

    .line 595
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->mkdirs()Z

    .line 598
    :cond_2a1
    invoke-static {v6}, Lcom/streamsync/app/auth/SupabaseSyncService;->ensureRoomDatabase(Landroid/content/Context;)V
    :try_end_2a4
    .catchall {:try_start_27e .. :try_end_2a4} :catchall_4b9

    .line 601
    :try_start_2a4
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/high16 v2, 0x20000000

    const/4 v3, 0x0

    invoke-static {v0, v3, v2}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v2
    :try_end_2af
    .catchall {:try_start_2a4 .. :try_end_2af} :catchall_4b4

    .line 603
    :try_start_2af
    const-string v0, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 604
    const-string v0, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'4201b4cf7d52e2b03ff5e210c14b7a60\')"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 605
    const-string v0, "PRAGMA user_version = 7"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 606
    const-string v0, "CREATE TABLE IF NOT EXISTS `watch_progress` (`mediaId` TEXT NOT NULL, `positionMs` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, `updatedAtEpochMs` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `pendingSync` INTEGER NOT NULL, `streamUrl` TEXT, `addonId` TEXT, `addonName` TEXT, PRIMARY KEY(`mediaId`));"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 608
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V
    :try_end_2c6
    .catchall {:try_start_2af .. :try_end_2c6} :catchall_4aa

    .line 610
    :try_start_2c6
    invoke-interface {v11}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0
    :try_end_2ca
    .catchall {:try_start_2c6 .. :try_end_2ca} :catchall_4a0

    const/4 v12, 0x0

    :goto_2cb
    :try_start_2cb
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_48a

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/json/JSONObject;

    .line 611
    move-object/from16 v6, v17

    invoke-virtual {v5, v6, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 612
    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v8

    if-eqz v8, :cond_2e9

    const-string v7, "contentId"

    invoke-virtual {v5, v7, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 614
    :cond_2e9
    const-string v8, "video_id"

    invoke-virtual {v5, v8, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 615
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_2fb

    const-string v8, "videoId"

    invoke-virtual {v5, v8, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 617
    :cond_2fb
    const/4 v10, 0x0

    invoke-virtual {v5, v9, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v11

    .line 618
    invoke-virtual {v5, v4, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v13

    .line 620
    nop

    .line 621
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_311

    const-string v8, "progress_key"

    invoke-virtual {v5, v8, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 622
    :cond_311
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_318

    move-object v8, v7

    .line 623
    :cond_318
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10
    :try_end_31c
    .catchall {:try_start_2cb .. :try_end_31c} :catchall_49d

    const-string v14, "mediaId"

    if-eqz v10, :cond_324

    :try_start_320
    invoke-virtual {v5, v14, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 625
    :cond_324
    if-lez v11, :cond_34e

    if-lez v13, :cond_34e

    move-object/from16 v10, v16

    invoke-virtual {v8, v10}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v15

    if-nez v15, :cond_350

    .line 626
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v11}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v13}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    goto :goto_350

    .line 625
    :cond_34e
    move-object/from16 v10, v16

    .line 629
    :cond_350
    :goto_350
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_46c

    move-object/from16 v7, p1

    invoke-interface {v7, v8}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_36b

    .line 630
    move-object/from16 p3, v0

    move-object/from16 v17, v1

    move-object/from16 v19, v4

    move-object/from16 v11, v23

    const/16 v18, 0x0

    move-object v4, v3

    goto/16 :goto_479

    .line 632
    :cond_36b
    invoke-interface {v7, v8}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 634
    move-object/from16 v19, v4

    const-wide/16 v3, 0x0

    move-object/from16 v11, v23

    invoke-virtual {v5, v11, v3, v4}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15
    :try_end_378
    .catchall {:try_start_320 .. :try_end_378} :catchall_49d

    .line 635
    const-string v13, "positionMs"

    cmp-long v17, v15, v3

    if-nez v17, :cond_382

    :try_start_37e
    invoke-virtual {v5, v13, v3, v4}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15

    .line 636
    :cond_382
    cmp-long v17, v15, v3

    if-lez v17, :cond_390

    const-wide/16 v20, 0x2710

    cmp-long v17, v15, v20

    if-gez v17, :cond_390

    const-wide/16 v20, 0x3e8

    mul-long v15, v15, v20

    .line 637
    :cond_390
    cmp-long v17, v15, v3

    if-gtz v17, :cond_397

    const-wide/32 v15, 0xea60

    .line 639
    :cond_397
    const-string v3, "duration"

    move-object/from16 p3, v0

    move-object v4, v1

    const-wide/16 v0, 0x0

    invoke-virtual {v5, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v20

    .line 640
    cmp-long v3, v20, v0

    if-nez v3, :cond_3ac

    const-string v3, "durationMs"

    invoke-virtual {v5, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v20
    :try_end_3ac
    .catchall {:try_start_37e .. :try_end_3ac} :catchall_49d

    .line 641
    :cond_3ac
    cmp-long v3, v20, v0

    if-lez v3, :cond_3bd

    const-wide/16 v0, 0x2710

    cmp-long v3, v20, v0

    if-gez v3, :cond_3bd

    const-wide/16 v0, 0x3e8

    mul-long v20, v20, v0

    move-wide/from16 v0, v20

    goto :goto_3bf

    .line 642
    :cond_3bd
    move-wide/from16 v0, v20

    :goto_3bf
    cmp-long v3, v0, v15

    if-lez v3, :cond_3d6

    move-object/from16 v17, v4

    long-to-double v3, v0

    const-wide v20, 0x3fef0a3d70a3d70aL    # 0.97

    invoke-static {v3, v4}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v3, v3, v20

    double-to-long v3, v3

    cmp-long v20, v15, v3

    if-ltz v20, :cond_3e4

    goto :goto_3d8

    :cond_3d6
    move-object/from16 v17, v4

    .line 643
    :goto_3d8
    const-wide/16 v0, 0x2

    mul-long v0, v0, v15

    const-wide/32 v3, 0x493e0

    add-long/2addr v3, v15

    :try_start_3e0
    invoke-static {v0, v1, v3, v4}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    .line 646
    :cond_3e4
    const-string v3, "last_watched"

    move-wide/from16 v20, v0

    const-wide/16 v0, 0x0

    invoke-virtual {v5, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v3

    .line 647
    cmp-long v22, v3, v0

    if-nez v22, :cond_3f8

    const-string v3, "lastWatched"

    invoke-virtual {v5, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v3

    .line 648
    :cond_3f8
    cmp-long v22, v3, v0

    if-nez v22, :cond_402

    const-string v3, "watched_at"

    invoke-virtual {v5, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v3

    .line 649
    :cond_402
    cmp-long v22, v3, v0

    if-nez v22, :cond_410

    const-string v0, "updatedAtEpochMs"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-virtual {v5, v0, v3, v4}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v3

    .line 651
    :cond_410
    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    .line 652
    invoke-virtual {v0, v14, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 653
    invoke-static/range {v15 .. v16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v0, v13, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 654
    const-string v1, "durationMs"

    invoke-static/range {v20 .. v21}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v0, v1, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 655
    const-string v1, "updatedAtEpochMs"

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v0, v1, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 656
    const-string v1, "deviceId"

    const-string v3, "streamsync"

    invoke-virtual {v0, v1, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 657
    const-string v1, "pendingSync"

    const/16 v18, 0x0

    invoke-static/range {v18 .. v18}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v0, v1, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 658
    const-string v1, "streamUrl"

    invoke-virtual {v0, v1}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 659
    const-string v1, "addonId"

    invoke-virtual {v0, v1}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 660
    const-string v1, "addonName"

    invoke-virtual {v0, v1}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 662
    const-string v1, "watch_progress"

    const/4 v3, 0x5

    const/4 v4, 0x0

    invoke-virtual {v2, v1, v4, v0, v3}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 663
    add-int/lit8 v12, v12, 0x1

    .line 664
    move-object/from16 v0, p3

    move-object v3, v4

    move-object/from16 p1, v7

    move-object/from16 v16, v10

    move-object/from16 v23, v11

    move-object/from16 v1, v17

    move-object/from16 v4, v19

    move-object/from16 v17, v6

    goto/16 :goto_2cb

    .line 629
    :cond_46c
    move-object/from16 v7, p1

    move-object/from16 p3, v0

    move-object/from16 v17, v1

    move-object/from16 v19, v4

    move-object/from16 v11, v23

    const/16 v18, 0x0

    move-object v4, v3

    .line 610
    :goto_479
    move-object/from16 v0, p3

    move-object v3, v4

    move-object/from16 p1, v7

    move-object/from16 v16, v10

    move-object/from16 v23, v11

    move-object/from16 v1, v17

    move-object/from16 v4, v19

    move-object/from16 v17, v6

    goto/16 :goto_2cb

    .line 665
    :cond_48a
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_48d
    .catchall {:try_start_3e0 .. :try_end_48d} :catchall_49d

    .line 667
    :try_start_48d
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V
    :try_end_490
    .catchall {:try_start_48d .. :try_end_490} :catchall_49a

    .line 668
    nop

    .line 670
    :try_start_491
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->close()V
    :try_end_494
    .catchall {:try_start_491 .. :try_end_494} :catchall_497

    .line 671
    nop

    .line 672
    move v5, v12

    goto :goto_4b8

    :catchall_497
    move-exception v0

    move v5, v12

    goto :goto_4b8

    .line 670
    :catchall_49a
    move-exception v0

    move v5, v12

    goto :goto_4ae

    .line 667
    :catchall_49d
    move-exception v0

    move v5, v12

    goto :goto_4a4

    :catchall_4a0
    move-exception v0

    const/16 v18, 0x0

    const/4 v5, 0x0

    :goto_4a4
    :try_start_4a4
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 668
    throw v0
    :try_end_4a8
    .catchall {:try_start_4a4 .. :try_end_4a8} :catchall_4a8

    .line 670
    :catchall_4a8
    move-exception v0

    goto :goto_4ae

    :catchall_4aa
    move-exception v0

    const/16 v18, 0x0

    const/4 v5, 0x0

    :goto_4ae
    :try_start_4ae
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 671
    throw v0
    :try_end_4b2
    .catchall {:try_start_4ae .. :try_end_4b2} :catchall_4b2

    .line 672
    :catchall_4b2
    move-exception v0

    goto :goto_4b8

    :catchall_4b4
    move-exception v0

    const/16 v18, 0x0

    const/4 v5, 0x0

    .line 673
    :goto_4b8
    goto :goto_4bd

    :catchall_4b9
    move-exception v0

    const/16 v18, 0x0

    const/4 v5, 0x0

    .line 674
    :goto_4bd
    return v5
.end method

.method private static readStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 1048
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 1049
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1051
    :goto_11
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 1052
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_11

    .line 1054
    :cond_21
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    .line 1055
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static refreshRoom(Landroid/content/Context;)V
    .registers 8

    .line 1032
    :try_start_0
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 1033
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 1034
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

    .line 1035
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 1036
    if-eqz p0, :cond_58

    .line 1037
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "getInvalidationTracker"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 1038
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 1039
    if-eqz p0, :cond_58

    .line 1040
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "refreshVersionsAsync"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 1041
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_56
    .catchall {:try_start_0 .. :try_end_56} :catchall_57

    goto :goto_58

    .line 1044
    :catchall_57
    move-exception p0

    :cond_58
    :goto_58
    nop

    .line 1045
    return-void
.end method

.method public static runOnMain(Ljava/lang/Runnable;)V
    .registers 2

    .line 53
    sget-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    invoke-virtual {v0, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 54
    return-void
.end method

.method public static showToast(Landroid/content/Context;Ljava/lang/String;)V
    .registers 3

    .line 57
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$1;

    invoke-direct {v0, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$1;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 65
    return-void
.end method

.method public static startDeviceLink(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V
    .registers 3

    .line 195
    new-instance p0, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$3;

    invoke-direct {v0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V

    invoke-direct {p0, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 265
    invoke-virtual {p0}, Ljava/lang/Thread;->start()V

    .line 266
    return-void
.end method

.method public static syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V
    .registers 4

    .line 372
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$4;

    invoke-direct {v1, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$4;-><init>(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 427
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 428
    return-void
.end method
