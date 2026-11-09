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

    .line 1000
    if-nez p0, :cond_3

    return-void

    .line 1002
    :cond_3
    :try_start_3
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 1003
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 1004
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

    .line 1005
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_31
    .catchall {:try_start_3 .. :try_end_31} :catchall_32

    goto :goto_33

    .line 1006
    :catchall_32
    move-exception p0

    :goto_33
    nop

    .line 1007
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
    .catchall {:try_start_d .. :try_end_5b} :catchall_153

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

    if-ne v9, v10, :cond_154

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
    .catchall {:try_start_60 .. :try_end_88} :catchall_153

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
    .catchall {:try_start_8e .. :try_end_a9} :catchall_153

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
    .catchall {:try_start_f9 .. :try_end_144} :catchall_153

    .line 365
    return v8

    .line 318
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

    .line 367
    :catchall_153
    move-exception v0

    :cond_154
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

    .line 940
    :try_start_0
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    .line 941
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

    .line 942
    :try_start_23
    const-string p0, "community.cinemeta"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 943
    const-string p0, "Cinemeta"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 944
    const-string p0, "3.0.12"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 945
    const-string p0, "Cung c\u1ea5p danh m\u1ee5c v\u00e0 si\u00eau d\u1eef li\u1ec7u ch\u00ednh th\u1ee9c cho phim & TV series"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 946
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 947
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"type\":\"movie\",\"id\":\"top\",\"name\":\"Top Phim\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]},{\"type\":\"series\",\"id\":\"top\",\"name\":\"Top Series\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 948
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 949
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 950
    const-string p0, "https://v3-cinemeta.strem.io/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 951
    return-object v0

    .line 952
    :cond_61
    const-string v1, "opensubtitles"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1
    :try_end_67
    .catchall {:try_start_23 .. :try_end_67} :catchall_131

    const-string v13, "[]"

    if-eqz v1, :cond_a7

    .line 953
    :try_start_6b
    const-string p0, "org.stremio.opensubtitles-v3"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 954
    const-string p0, "OpenSubtitles v3"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 955
    const-string p0, "1.0.0"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 956
    const-string p0, "Ph\u1ee5 \u0111\u1ec1 \u0111a ng\u00f4n ng\u1eef ch\u00ednh th\u1ee9c t\u1eeb OpenSubtitles"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 957
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 958
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v13}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 959
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"subtitles\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 960
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 961
    const-string p0, "https://opensubtitles-v3.strem.io/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 962
    return-object v0

    .line 963
    :cond_a7
    const-string v1, "cyberflix"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_ed

    .line 964
    const-string p0, "tv.cyberflix.catalog"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 965
    const-string p0, "Cyberflix Catalog"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 966
    const-string p0, "1.5.0"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 967
    const-string p0, "Danh m\u1ee5c phim v\u00e0 series t\u1eeb c\u00e1c n\u1ec1n t\u1ea3ng tr\u1ef1c tuy\u1ebfn ph\u1ed5 bi\u1ebfn"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 968
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 969
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"type\":\"movie\",\"id\":\"cyberflix_netflix_movies\",\"name\":\"Netflix Phim\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]},{\"type\":\"series\",\"id\":\"cyberflix_netflix_series\",\"name\":\"Netflix Series\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 970
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 971
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 972
    const-string p0, ""

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 973
    return-object v0

    .line 974
    :cond_ed
    const-string v1, "torrentio"

    invoke-virtual {p0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_132

    .line 975
    const-string p0, "com.stremio.torrentio"

    invoke-virtual {v0, v12, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 976
    const-string p0, "Torrentio"

    invoke-virtual {v0, v11, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 977
    const-string p0, "1.0.13"

    invoke-virtual {v0, v10, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 978
    const-string p0, "Ngu\u1ed3n ph\u00e1t torrent v\u00e0 debrid \u0111a d\u1ea1ng"

    invoke-virtual {v0, v9, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 979
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 980
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v13}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v6, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 981
    new-instance p0, Lorg/json/JSONArray;

    const-string v1, "[{\"name\":\"stream\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {p0, v1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v5, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 982
    new-instance p0, Lorg/json/JSONArray;

    invoke-direct {p0, v3}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v4, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 983
    const-string p0, "https://torrentio.strem.fun/logo.png"

    invoke-virtual {v0, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_130
    .catchall {:try_start_6b .. :try_end_130} :catchall_131

    .line 984
    return-object v0

    .line 986
    :catchall_131
    move-exception p0

    :cond_132
    nop

    .line 987
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

    .line 747
    const-string v1, "type"

    const-string v2, "resources"

    const-string v3, "catalogs"

    const-string v4, "description"

    const-string v5, "version"

    const-string v6, "extraRequired"

    const-string v7, "extraSupported"

    const-string v8, "extra"

    const-string v9, "id"

    const-string v10, "/manifest.json"

    const-string v11, "idPrefixes"

    const-string v12, "types"

    const-string v13, "name"

    .line 748
    const-string v0, "streamsync.db"

    move-object/from16 v14, p0

    invoke-virtual {v14, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 749
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v15

    invoke-virtual {v15}, Ljava/io/File;->exists()Z

    move-result v15

    if-nez v15, :cond_33

    .line 750
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v15

    invoke-virtual {v15}, Ljava/io/File;->mkdirs()Z

    .line 752
    :cond_33
    if-eqz p1, :cond_49a

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->isEmpty()Z

    move-result v16

    if-eqz v16, :cond_3d

    goto/16 :goto_49a

    .line 754
    :cond_3d
    invoke-static/range {p0 .. p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->ensureRoomDatabase(Landroid/content/Context;)V

    .line 756
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/high16 v14, 0x10000000

    const/4 v15, 0x0

    invoke-static {v0, v15, v14}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v14

    .line 758
    :try_start_4b
    const-string v0, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"

    invoke-virtual {v14, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 759
    const-string v0, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'4201b4cf7d52e2b03ff5e210c14b7a60\')"

    invoke-virtual {v14, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 760
    const-string v0, "PRAGMA user_version = 7"

    invoke-virtual {v14, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 762
    const-string v0, "CREATE TABLE IF NOT EXISTS `installed_addons` (`baseUrl` TEXT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `version` TEXT NOT NULL, `description` TEXT, `typesCsv` TEXT NOT NULL, `catalogsJson` TEXT NOT NULL, `resourcesJson` TEXT NOT NULL, `idPrefixesCsv` TEXT NOT NULL, `logoUrl` TEXT, `sortOrder` INTEGER NOT NULL, PRIMARY KEY(`baseUrl`));"

    invoke-virtual {v14, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 764
    invoke-virtual {v14}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 765
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v17

    const/16 v18, 0x0

    :goto_68
    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_483

    invoke-interface/range {v17 .. v17}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;
    :try_end_74
    .catchall {:try_start_4b .. :try_end_74} :catchall_491

    .line 767
    :try_start_74
    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 768
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v19

    if-eqz v19, :cond_86

    move-object/from16 v19, v2

    move-object/from16 v21, v10

    move-object/from16 v20, v14

    goto/16 :goto_11b

    .line 770
    :cond_86
    invoke-virtual {v0, v10}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v19
    :try_end_8a
    .catchall {:try_start_74 .. :try_end_8a} :catchall_467

    const-string v15, "/+$"

    move-object/from16 v20, v14

    const-string v14, ""

    if-eqz v19, :cond_95

    move-object/from16 v19, v2

    goto :goto_ac

    :cond_95
    :try_start_95
    invoke-virtual {v0, v15, v14}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_99
    .catchall {:try_start_95 .. :try_end_99} :catchall_458

    move-object/from16 v19, v2

    :try_start_9b
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 771
    :goto_ac
    nop

    .line 772
    invoke-virtual {v0, v10}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v2
    :try_end_b1
    .catchall {:try_start_9b .. :try_end_b1} :catchall_449

    .line 773
    move-object/from16 v21, v10

    const/4 v10, -0x1

    if-eq v2, v10, :cond_c9

    .line 774
    const/4 v10, 0x0

    :try_start_b7
    invoke-virtual {v0, v10, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    goto :goto_ca

    .line 928
    :catchall_bc
    move-exception v0

    move-object/from16 v25, v3

    move-object v10, v4

    move-object/from16 v31, v19

    move-object/from16 v4, v20

    :goto_c4
    move-object/from16 v19, v1

    move-object v1, v11

    goto/16 :goto_456

    .line 773
    :cond_c9
    move-object v2, v0

    .line 776
    :goto_ca
    invoke-virtual {v2, v15, v14}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2
    :try_end_ce
    .catchall {:try_start_b7 .. :try_end_ce} :catchall_bc

    .line 778
    nop

    .line 780
    :try_start_cf
    new-instance v10, Ljava/net/URL;

    invoke-direct {v10, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 781
    invoke-virtual {v10}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 782
    const-string v10, "GET"

    invoke-virtual {v0, v10}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 783
    const-string v10, "User-Agent"

    const-string v15, "Mozilla/5.0 StreamSync"

    invoke-virtual {v0, v10, v15}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 784
    const/16 v10, 0x1f40

    invoke-virtual {v0, v10}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 785
    invoke-virtual {v0, v10}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 786
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    const/16 v15, 0xc8

    if-ne v10, v15, :cond_104

    .line 787
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 788
    new-instance v10, Lorg/json/JSONObject;

    invoke-direct {v10, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
    :try_end_103
    .catchall {:try_start_cf .. :try_end_103} :catchall_106

    goto :goto_105

    .line 786
    :cond_104
    const/4 v10, 0x0

    .line 790
    :goto_105
    goto :goto_108

    :catchall_106
    move-exception v0

    const/4 v10, 0x0

    .line 792
    :goto_108
    if-nez v10, :cond_10e

    .line 793
    :try_start_10a
    invoke-static {v2}, Lcom/streamsync/app/auth/SupabaseSyncService;->getDefaultManifestFallback(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v10

    .line 796
    :cond_10e
    if-nez v10, :cond_111

    goto :goto_11b

    .line 798
    :cond_111
    invoke-virtual {v10, v9, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 799
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v15

    if-eqz v15, :cond_124

    .line 765
    :goto_11b
    move-object/from16 v2, v19

    move-object/from16 v14, v20

    move-object/from16 v10, v21

    const/4 v15, 0x0

    goto/16 :goto_68

    .line 801
    :cond_124
    invoke-virtual {v10, v13, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15

    .line 802
    move-object/from16 p1, v15

    const-string v15, "1.0.0"

    invoke-virtual {v10, v5, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15
    :try_end_130
    .catchall {:try_start_10a .. :try_end_130} :catchall_bc

    .line 803
    move-object/from16 v22, v5

    :try_start_132
    invoke-virtual {v10, v4, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5
    :try_end_136
    .catchall {:try_start_132 .. :try_end_136} :catchall_43d

    .line 804
    move-object/from16 v23, v4

    :try_start_138
    const-string v4, "logo"

    move-object/from16 v24, v5

    const-string v5, "icon"

    invoke-virtual {v10, v5, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v10, v4, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 806
    const-string v5, "movie,series"

    .line 807
    move-object/from16 v25, v5

    new-instance v5, Lorg/json/JSONArray;

    move-object/from16 v26, v4

    const-string v4, "[\"movie\",\"series\"]"

    invoke-direct {v5, v4}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 808
    invoke-virtual {v10, v12}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v4
    :try_end_157
    .catchall {:try_start_138 .. :try_end_157} :catchall_430

    move-object/from16 v27, v5

    const-string v5, ","

    if-eqz v4, :cond_199

    .line 809
    :try_start_15d
    invoke-virtual {v10, v12}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 810
    if-eqz v4, :cond_192

    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v28

    if-lez v28, :cond_192

    .line 811
    nop

    .line 812
    move-object/from16 v28, v15

    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    .line 813
    move-object/from16 v29, v0

    const/4 v0, 0x0

    :goto_174
    move-object/from16 v30, v2

    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v0, v2, :cond_18d

    .line 814
    if-lez v0, :cond_181

    invoke-virtual {v15, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 815
    :cond_181
    invoke-virtual {v4, v0}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v15, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 813
    add-int/lit8 v0, v0, 0x1

    move-object/from16 v2, v30

    goto :goto_174

    .line 817
    :cond_18d
    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_1a3

    .line 810
    :cond_192
    move-object/from16 v29, v0

    move-object/from16 v30, v2

    move-object/from16 v28, v15

    goto :goto_19f

    .line 808
    :cond_199
    move-object/from16 v29, v0

    move-object/from16 v30, v2

    move-object/from16 v28, v15

    .line 821
    :goto_19f
    move-object/from16 v0, v25

    move-object/from16 v4, v27

    :goto_1a3
    new-instance v2, Lorg/json/JSONArray;

    const-string v15, "[\"tt\"]"

    invoke-direct {v2, v15}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 822
    const-string v15, "tt"

    .line 823
    invoke-virtual {v10, v11}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v25
    :try_end_1b0
    .catchall {:try_start_15d .. :try_end_1b0} :catchall_430

    if-eqz v25, :cond_1f1

    .line 824
    move-object/from16 v25, v2

    :try_start_1b4
    invoke-virtual {v10, v11}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v2

    .line 825
    if-eqz v2, :cond_1e7

    invoke-virtual {v2}, Lorg/json/JSONArray;->length()I

    move-result v27

    if-lez v27, :cond_1e7

    .line 826
    nop

    .line 827
    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V
    :try_end_1c6
    .catchall {:try_start_1b4 .. :try_end_1c6} :catchall_1ec

    .line 828
    move-object/from16 v27, v0

    const/4 v0, 0x0

    :goto_1c9
    move-object/from16 v31, v11

    :try_start_1cb
    invoke-virtual {v2}, Lorg/json/JSONArray;->length()I

    move-result v11

    if-ge v0, v11, :cond_1e2

    .line 829
    if-lez v0, :cond_1d6

    invoke-virtual {v15, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 830
    :cond_1d6
    invoke-virtual {v2, v0}, Lorg/json/JSONArray;->optString(I)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v15, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 828
    add-int/lit8 v0, v0, 0x1

    move-object/from16 v11, v31

    goto :goto_1c9

    .line 832
    :cond_1e2
    invoke-virtual {v15}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v15

    goto :goto_1f9

    .line 825
    :cond_1e7
    move-object/from16 v27, v0

    move-object/from16 v31, v11

    goto :goto_1f7

    .line 928
    :catchall_1ec
    move-exception v0

    move-object/from16 v31, v11

    goto/16 :goto_41e

    .line 823
    :cond_1f1
    move-object/from16 v27, v0

    move-object/from16 v25, v2

    move-object/from16 v31, v11

    .line 837
    :goto_1f7
    move-object/from16 v2, v25

    :goto_1f9
    new-instance v0, Lorg/json/JSONArray;

    invoke-direct {v0}, Lorg/json/JSONArray;-><init>()V

    .line 838
    invoke-virtual {v10, v3}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_2bb

    .line 839
    invoke-virtual {v10, v3}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    .line 840
    instance-of v11, v5, Lorg/json/JSONArray;

    if-eqz v11, :cond_2b6

    .line 841
    check-cast v5, Lorg/json/JSONArray;
    :try_end_20e
    .catchall {:try_start_1cb .. :try_end_20e} :catchall_41d

    .line 842
    const/4 v11, 0x0

    :goto_20f
    move-object/from16 v25, v3

    :try_start_211
    invoke-virtual {v5}, Lorg/json/JSONArray;->length()I

    move-result v3

    if-ge v11, v3, :cond_2b3

    .line 843
    invoke-virtual {v5, v11}, Lorg/json/JSONArray;->opt(I)Ljava/lang/Object;

    move-result-object v3

    .line 844
    move-object/from16 v32, v5

    instance-of v5, v3, Lorg/json/JSONObject;

    if-eqz v5, :cond_2a7

    .line 845
    check-cast v3, Lorg/json/JSONObject;

    .line 846
    invoke-virtual {v3, v9, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    .line 847
    invoke-virtual {v3, v1, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v33

    invoke-virtual/range {v33 .. v33}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v33

    .line 848
    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v34

    if-nez v34, :cond_2a4

    .line 849
    invoke-virtual/range {v33 .. v33}, Ljava/lang/String;->isEmpty()Z

    move-result v33

    if-eqz v33, :cond_247

    move-object/from16 v33, v15

    const-string v15, "movie"

    invoke-virtual {v3, v1, v15}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_249

    :cond_247
    move-object/from16 v33, v15

    .line 850
    :goto_249
    invoke-virtual {v3, v13}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v15

    if-eqz v15, :cond_259

    invoke-virtual {v3, v13, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v15}, Ljava/lang/String;->isEmpty()Z

    move-result v15

    if-eqz v15, :cond_25c

    .line 851
    :cond_259
    invoke-virtual {v3, v13, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 853
    :cond_25c
    invoke-virtual {v3, v8}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5
    :try_end_260
    .catchall {:try_start_211 .. :try_end_260} :catchall_41b

    const-string v15, "[]"

    if-eqz v5, :cond_26c

    :try_start_264
    invoke-virtual {v3, v8}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    instance-of v5, v5, Lorg/json/JSONArray;

    if-nez v5, :cond_274

    .line 854
    :cond_26c
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5, v15}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v8, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 856
    :cond_274
    invoke-virtual {v3, v7}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_282

    invoke-virtual {v3, v7}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    instance-of v5, v5, Lorg/json/JSONArray;

    if-nez v5, :cond_28a

    .line 857
    :cond_282
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5, v15}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v7, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 859
    :cond_28a
    invoke-virtual {v3, v6}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_298

    invoke-virtual {v3, v6}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    instance-of v5, v5, Lorg/json/JSONArray;

    if-nez v5, :cond_2a0

    .line 860
    :cond_298
    new-instance v5, Lorg/json/JSONArray;

    invoke-direct {v5, v15}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v6, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 862
    :cond_2a0
    invoke-virtual {v0, v3}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_2a9

    .line 848
    :cond_2a4
    move-object/from16 v33, v15

    goto :goto_2a9

    .line 844
    :cond_2a7
    move-object/from16 v33, v15

    .line 842
    :goto_2a9
    add-int/lit8 v11, v11, 0x1

    move-object/from16 v3, v25

    move-object/from16 v5, v32

    move-object/from16 v15, v33

    goto/16 :goto_20f

    :cond_2b3
    move-object/from16 v33, v15

    goto :goto_2bf

    .line 840
    :cond_2b6
    move-object/from16 v25, v3

    move-object/from16 v33, v15

    goto :goto_2bf

    .line 838
    :cond_2bb
    move-object/from16 v25, v3

    move-object/from16 v33, v15

    .line 868
    :goto_2bf
    invoke-virtual {v0}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v0

    .line 872
    new-instance v3, Lorg/json/JSONArray;

    invoke-direct {v3}, Lorg/json/JSONArray;-><init>()V
    :try_end_2c8
    .catchall {:try_start_264 .. :try_end_2c8} :catchall_41b

    .line 873
    move-object/from16 v5, v19

    :try_start_2ca
    invoke-virtual {v10, v5}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v11
    :try_end_2ce
    .catchall {:try_start_2ca .. :try_end_2ce} :catchall_40b

    if-eqz v11, :cond_37f

    .line 874
    :try_start_2d0
    invoke-virtual {v10, v5}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v10

    .line 875
    instance-of v11, v10, Lorg/json/JSONArray;

    if-eqz v11, :cond_36f

    .line 876
    check-cast v10, Lorg/json/JSONArray;

    .line 877
    const/4 v11, 0x0

    :goto_2db
    invoke-virtual {v10}, Lorg/json/JSONArray;->length()I

    move-result v15

    if-ge v11, v15, :cond_368

    .line 878
    invoke-virtual {v10, v11}, Lorg/json/JSONArray;->opt(I)Ljava/lang/Object;

    move-result-object v15
    :try_end_2e5
    .catchall {:try_start_2d0 .. :try_end_2e5} :catchall_376

    .line 879
    move-object/from16 v19, v1

    :try_start_2e7
    instance-of v1, v15, Ljava/lang/String;
    :try_end_2e9
    .catchall {:try_start_2e7 .. :try_end_2e9} :catchall_366

    if-eqz v1, :cond_31f

    .line 880
    :try_start_2eb
    check-cast v15, Ljava/lang/String;

    .line 881
    invoke-virtual {v15}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_311

    .line 882
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    .line 883
    invoke-virtual {v15}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v1, v13, v15}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 884
    invoke-virtual {v1, v12, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_306
    .catchall {:try_start_2eb .. :try_end_306} :catchall_317

    .line 885
    move-object/from16 v15, v31

    :try_start_308
    invoke-virtual {v1, v15, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 886
    invoke-virtual {v3, v1}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;
    :try_end_30e
    .catchall {:try_start_308 .. :try_end_30e} :catchall_30f

    goto :goto_313

    .line 928
    :catchall_30f
    move-exception v0

    goto :goto_31a

    .line 881
    :cond_311
    move-object/from16 v15, v31

    .line 888
    :goto_313
    move-object/from16 v31, v5

    move-object v1, v15

    goto :goto_35c

    .line 928
    :catchall_317
    move-exception v0

    move-object/from16 v15, v31

    :goto_31a
    move-object/from16 v31, v5

    move-object v1, v15

    goto/16 :goto_404

    .line 888
    :cond_31f
    move-object/from16 v1, v31

    move-object/from16 v31, v5

    :try_start_323
    instance-of v5, v15, Lorg/json/JSONObject;

    if-eqz v5, :cond_35c

    .line 889
    check-cast v15, Lorg/json/JSONObject;

    .line 890
    invoke-virtual {v15, v13, v14}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    .line 891
    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-nez v5, :cond_35c

    .line 892
    invoke-virtual {v15, v12}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_345

    invoke-virtual {v15, v12}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    instance-of v5, v5, Lorg/json/JSONArray;

    if-nez v5, :cond_348

    .line 893
    :cond_345
    invoke-virtual {v15, v12, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 895
    :cond_348
    invoke-virtual {v15, v1}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_356

    invoke-virtual {v15, v1}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    instance-of v5, v5, Lorg/json/JSONArray;

    if-nez v5, :cond_359

    .line 896
    :cond_356
    invoke-virtual {v15, v1, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 898
    :cond_359
    invoke-virtual {v3, v15}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 877
    :cond_35c
    :goto_35c
    add-int/lit8 v11, v11, 0x1

    move-object/from16 v5, v31

    move-object/from16 v31, v1

    move-object/from16 v1, v19

    goto/16 :goto_2db

    .line 928
    :catchall_366
    move-exception v0

    goto :goto_379

    .line 877
    :cond_368
    move-object/from16 v19, v1

    move-object/from16 v1, v31

    move-object/from16 v31, v5

    goto :goto_385

    .line 875
    :cond_36f
    move-object/from16 v19, v1

    move-object/from16 v1, v31

    move-object/from16 v31, v5

    goto :goto_385

    .line 928
    :catchall_376
    move-exception v0

    move-object/from16 v19, v1

    :goto_379
    move-object/from16 v1, v31

    move-object/from16 v31, v5

    goto/16 :goto_404

    .line 873
    :cond_37f
    move-object/from16 v19, v1

    move-object/from16 v1, v31

    move-object/from16 v31, v5

    .line 904
    :goto_385
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-nez v2, :cond_392

    .line 905
    new-instance v3, Lorg/json/JSONArray;

    const-string v2, "[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"stream\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"

    invoke-direct {v3, v2}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 907
    :cond_392
    invoke-virtual {v3}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v2

    .line 909
    new-instance v3, Landroid/content/ContentValues;

    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    .line 910
    const-string v4, "baseUrl"

    move-object/from16 v5, v30

    invoke-virtual {v3, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 911
    move-object/from16 v4, v29

    invoke-virtual {v3, v9, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 912
    move-object/from16 v4, p1

    invoke-virtual {v3, v13, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_3ac
    .catchall {:try_start_323 .. :try_end_3ac} :catchall_403

    .line 913
    move-object/from16 v5, v22

    move-object/from16 v4, v28

    :try_start_3b0
    invoke-virtual {v3, v5, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_3b3
    .catchall {:try_start_3b0 .. :try_end_3b3} :catchall_3ff

    .line 914
    move-object/from16 v10, v23

    move-object/from16 v4, v24

    :try_start_3b7
    invoke-virtual {v3, v10, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 915
    const-string v4, "typesCsv"

    move-object/from16 v11, v27

    invoke-virtual {v3, v4, v11}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 916
    const-string v4, "catalogsJson"

    invoke-virtual {v3, v4, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 917
    const-string v0, "resourcesJson"

    invoke-virtual {v3, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 918
    const-string v0, "idPrefixesCsv"

    move-object/from16 v15, v33

    invoke-virtual {v3, v0, v15}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 919
    invoke-virtual/range {v26 .. v26}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_3d6
    .catchall {:try_start_3b7 .. :try_end_3d6} :catchall_3fc

    const-string v2, "logoUrl"

    if-nez v0, :cond_3e0

    .line 920
    move-object/from16 v0, v26

    :try_start_3dc
    invoke-virtual {v3, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_3e3

    .line 922
    :cond_3e0
    invoke-virtual {v3, v2}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 924
    :goto_3e3
    const-string v0, "sortOrder"

    invoke-static/range {v18 .. v18}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v3, v0, v2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 926
    const-string v0, "installed_addons"
    :try_end_3ee
    .catchall {:try_start_3dc .. :try_end_3ee} :catchall_3fc

    const/4 v2, 0x5

    move-object/from16 v4, v20

    const/4 v11, 0x0

    :try_start_3f2
    invoke-virtual {v4, v0, v11, v3, v2}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    :try_end_3f5
    .catchall {:try_start_3f2 .. :try_end_3f5} :catchall_3f9

    .line 927
    add-int/lit8 v18, v18, 0x1

    goto/16 :goto_474

    .line 928
    :catchall_3f9
    move-exception v0

    goto/16 :goto_474

    :catchall_3fc
    move-exception v0

    goto/16 :goto_454

    :catchall_3ff
    move-exception v0

    move-object/from16 v4, v20

    goto :goto_408

    :catchall_403
    move-exception v0

    :goto_404
    move-object/from16 v4, v20

    move-object/from16 v5, v22

    :goto_408
    move-object/from16 v10, v23

    goto :goto_456

    :catchall_40b
    move-exception v0

    move-object/from16 v19, v1

    move-object/from16 v4, v20

    move-object/from16 v10, v23

    move-object/from16 v1, v31

    const/4 v11, 0x0

    move-object/from16 v31, v5

    move-object/from16 v5, v22

    goto/16 :goto_474

    :catchall_41b
    move-exception v0

    goto :goto_420

    :catchall_41d
    move-exception v0

    :goto_41e
    move-object/from16 v25, v3

    :goto_420
    move-object/from16 v4, v20

    move-object/from16 v5, v22

    move-object/from16 v10, v23

    const/4 v11, 0x0

    move-object/from16 v35, v19

    move-object/from16 v19, v1

    move-object/from16 v1, v31

    move-object/from16 v31, v35

    goto :goto_474

    :catchall_430
    move-exception v0

    move-object/from16 v25, v3

    move-object/from16 v31, v19

    move-object/from16 v4, v20

    move-object/from16 v5, v22

    move-object/from16 v10, v23

    goto/16 :goto_c4

    :catchall_43d
    move-exception v0

    move-object/from16 v25, v3

    move-object v10, v4

    move-object/from16 v31, v19

    move-object/from16 v4, v20

    move-object/from16 v5, v22

    goto/16 :goto_c4

    :catchall_449
    move-exception v0

    move-object/from16 v25, v3

    move-object/from16 v21, v10

    move-object/from16 v31, v19

    move-object/from16 v19, v1

    move-object v10, v4

    move-object v1, v11

    :goto_454
    move-object/from16 v4, v20

    :goto_456
    const/4 v11, 0x0

    goto :goto_474

    :catchall_458
    move-exception v0

    move-object/from16 v19, v1

    move-object/from16 v31, v2

    move-object/from16 v25, v3

    move-object/from16 v21, v10

    move-object v1, v11

    const/4 v11, 0x0

    move-object v10, v4

    move-object/from16 v4, v20

    goto :goto_474

    :catchall_467
    move-exception v0

    move-object/from16 v19, v1

    move-object/from16 v31, v2

    move-object/from16 v25, v3

    move-object/from16 v21, v10

    move-object v1, v11

    move-object v11, v15

    move-object v10, v4

    move-object v4, v14

    :goto_474
    nop

    .line 929
    move-object v14, v4

    move-object v4, v10

    move-object v15, v11

    move-object/from16 v10, v21

    move-object/from16 v3, v25

    move-object/from16 v2, v31

    move-object v11, v1

    move-object/from16 v1, v19

    goto/16 :goto_68

    .line 930
    :cond_483
    move-object v4, v14

    :try_start_484
    invoke-virtual {v4}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_487
    .catchall {:try_start_484 .. :try_end_487} :catchall_48f

    .line 932
    invoke-virtual {v4}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 933
    invoke-virtual {v4}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 934
    nop

    .line 935
    return v18

    .line 932
    :catchall_48f
    move-exception v0

    goto :goto_493

    :catchall_491
    move-exception v0

    move-object v4, v14

    :goto_493
    invoke-virtual {v4}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 933
    invoke-virtual {v4}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 934
    throw v0

    .line 752
    :cond_49a
    :goto_49a
    const/4 v1, 0x0

    return v1
.end method

.method public static installDefaultAddonsFallback(Landroid/content/Context;)I
    .registers 3

    .line 991
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 992
    const-string v1, "https://v3-cinemeta.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 993
    const-string v1, "https://opensubtitles-v3.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 994
    const-string v1, "https://catalog.cyberflix.tv/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 995
    const-string v1, "https://torrentio.strem.fun/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 996
    invoke-static {p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result p0

    return p0
.end method

.method public static login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 13

    .line 102
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
    const/4 v7, 0x1

    invoke-virtual {v2, v3, v7}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

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

    .line 675
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

    .line 676
    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p1

    check-cast p1, Ljava/net/HttpURLConnection;

    .line 677
    const-string v3, "POST"

    invoke-virtual {p1, v3}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 678
    const-string v3, "Content-Type"

    const-string v4, "application/json"

    invoke-virtual {p1, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 679
    const-string v3, "apikey"

    invoke-virtual {p1, v3, p3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 680
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

    .line 681
    const/16 p2, 0x1f40

    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 682
    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 683
    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 685
    new-instance p3, Lorg/json/JSONObject;

    invoke-direct {p3}, Lorg/json/JSONObject;-><init>()V

    .line 686
    const-string v3, "p_profile_id"

    invoke-virtual {p3, v3, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 687
    const-string p2, "p_limit"

    const/16 v3, 0x64

    invoke-virtual {p3, p2, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 688
    const-string p2, "p_offset"

    const/4 v3, 0x0

    invoke-virtual {p3, p2, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 689
    invoke-virtual {p3}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p2

    sget-object p3, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p2, p3}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p2

    .line 690
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p3
    :try_end_7e
    .catchall {:try_start_6 .. :try_end_7e} :catchall_1a3

    .line 691
    :try_start_7e
    invoke-virtual {p3, p2}, Ljava/io/OutputStream;->write([B)V
    :try_end_81
    .catchall {:try_start_7e .. :try_end_81} :catchall_197

    .line 692
    if-eqz p3, :cond_86

    :try_start_83
    invoke-virtual {p3}, Ljava/io/OutputStream;->close()V

    .line 694
    :cond_86
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result p2

    const/16 p3, 0xc8

    if-ne p2, p3, :cond_1a4

    .line 695
    invoke-virtual {p1}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p1

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p1

    .line 696
    new-instance p2, Lorg/json/JSONArray;

    invoke-direct {p2, p1}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 697
    invoke-virtual {p2}, Lorg/json/JSONArray;->length()I

    move-result p1

    if-nez p1, :cond_a2

    return-void

    .line 699
    :cond_a2
    const-string p1, "streamsync.db"

    invoke-virtual {p0, p1}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object p1

    .line 700
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result p3

    if-nez p3, :cond_af

    return-void

    .line 702
    :cond_af
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->ensureRoomDatabase(Landroid/content/Context;)V

    .line 704
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    const/4 p1, 0x0

    invoke-static {p0, p1, v3}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object p0
    :try_end_bb
    .catchall {:try_start_83 .. :try_end_bb} :catchall_1a3

    .line 706
    :try_start_bb
    const-string p3, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"

    invoke-virtual {p0, p3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 707
    const-string p3, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'4201b4cf7d52e2b03ff5e210c14b7a60\')"

    invoke-virtual {p0, p3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 708
    const-string p3, "PRAGMA user_version = 7"

    invoke-virtual {p0, p3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 710
    const-string p3, "CREATE TABLE IF NOT EXISTS `library_items` (`mediaId` TEXT NOT NULL, `title` TEXT NOT NULL, `posterUrl` TEXT, `backgroundUrl` TEXT, `description` TEXT, `type` TEXT NOT NULL, `releaseInfo` TEXT, `imdbRating` TEXT, `genresJson` TEXT NOT NULL, `season` INTEGER, `episode` INTEGER, `addedAtEpochMs` INTEGER NOT NULL, PRIMARY KEY(`mediaId`));"

    invoke-virtual {p0, p3}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 712
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 713
    nop

    :goto_d3
    invoke-virtual {p2}, Lorg/json/JSONArray;->length()I

    move-result p3

    if-ge v3, p3, :cond_185

    .line 714
    invoke-virtual {p2, v3}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object p3

    .line 715
    const-string v4, "content_id"

    invoke-virtual {p3, v4, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 716
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_eb

    goto/16 :goto_181

    .line 718
    :cond_eb
    new-instance v5, Landroid/content/ContentValues;

    invoke-direct {v5}, Landroid/content/ContentValues;-><init>()V

    .line 719
    const-string v6, "mediaId"

    invoke-virtual {v5, v6, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 720
    const-string v4, "title"

    const-string v6, "name"

    invoke-virtual {p3, v6, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 721
    const-string v4, "posterUrl"

    const-string v6, "poster"

    invoke-virtual {p3, v6, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 722
    const-string v4, "backgroundUrl"

    const-string v6, "background"

    invoke-virtual {p3, v6, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 723
    invoke-virtual {p3, v1, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 724
    const-string v4, "type"

    const-string v6, "content_type"

    const-string v7, "movie"

    invoke-virtual {p3, v6, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 725
    const-string v4, "releaseInfo"

    const-string v6, "release_info"

    invoke-virtual {p3, v6, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 726
    const-string v4, "imdbRating"

    invoke-virtual {p3, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_146

    invoke-virtual {p3, v0}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    invoke-static {v6}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    goto :goto_147

    :cond_146
    move-object v6, p1

    :goto_147
    invoke-virtual {v5, v4, v6}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 728
    const-string v4, "genres"

    invoke-virtual {p3, v4}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 729
    const-string v6, "genresJson"

    if-eqz v4, :cond_159

    invoke-virtual {v4}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v4

    goto :goto_15b

    :cond_159
    const-string v4, "[]"

    :goto_15b
    invoke-virtual {v5, v6, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 731
    const-string v4, "season"

    invoke-virtual {v5, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 732
    const-string v4, "episode"

    invoke-virtual {v5, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 733
    const-string v4, "addedAtEpochMs"

    const-string v6, "added_at"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    invoke-virtual {p3, v6, v7, v8}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v6

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p3

    invoke-virtual {v5, v4, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 735
    const-string p3, "library_items"

    const/4 v4, 0x5

    invoke-virtual {p0, p3, p1, v5, v4}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 713
    :goto_181
    add-int/lit8 v3, v3, 0x1

    goto/16 :goto_d3

    .line 737
    :cond_185
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_188
    .catchall {:try_start_bb .. :try_end_188} :catchall_18f

    .line 739
    :try_start_188
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 740
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 741
    goto :goto_1a4

    .line 739
    :catchall_18f
    move-exception p1

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 740
    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 741
    throw p1
    :try_end_197
    .catchall {:try_start_188 .. :try_end_197} :catchall_1a3

    .line 690
    :catchall_197
    move-exception p0

    if-eqz p3, :cond_1a2

    :try_start_19a
    invoke-virtual {p3}, Ljava/io/OutputStream;->close()V
    :try_end_19d
    .catchall {:try_start_19a .. :try_end_19d} :catchall_19e

    goto :goto_1a2

    :catchall_19e
    move-exception p1

    :try_start_19f
    invoke-static {p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_1a2
    :goto_1a2
    throw p0
    :try_end_1a3
    .catchall {:try_start_19f .. :try_end_1a3} :catchall_1a3

    .line 743
    :catchall_1a3
    move-exception p0

    :cond_1a4
    :goto_1a4
    nop

    .line 744
    return-void
.end method

.method public static pullWatchProgressFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 28

    .line 492
    move-object/from16 v1, p0

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

    :try_start_26
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 495
    move-object/from16 v18, v11

    new-instance v11, Ljava/util/ArrayList;

    invoke-direct {v11}, Ljava/util/ArrayList;-><init>()V

    .line 496
    move-object/from16 v19, v12

    new-instance v12, Ljava/util/HashSet;

    invoke-direct {v12}, Ljava/util/HashSet;-><init>()V
    :try_end_38
    .catchall {:try_start_26 .. :try_end_38} :catchall_4b5

    .line 500
    move-object/from16 p1, v12

    :try_start_3a
    new-instance v0, Ljava/net/URL;

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12
    :try_end_45
    .catchall {:try_start_3a .. :try_end_45} :catchall_d7

    move-object/from16 v23, v4

    :try_start_47
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
    :try_end_99
    .catchall {:try_start_47 .. :try_end_99} :catchall_d5

    .line 514
    :try_start_99
    invoke-virtual {v12, v4}, Ljava/io/OutputStream;->write([B)V
    :try_end_9c
    .catchall {:try_start_99 .. :try_end_9c} :catchall_c7

    .line 515
    if-eqz v12, :cond_a1

    :try_start_9e
    invoke-virtual {v12}, Ljava/io/OutputStream;->close()V

    .line 517
    :cond_a1
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    const/16 v12, 0xc8

    if-ne v4, v12, :cond_da

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

    :goto_b7
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v12

    if-ge v0, v12, :cond_da

    .line 521
    invoke-virtual {v4, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v12

    invoke-interface {v11, v12}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_c4
    .catchall {:try_start_9e .. :try_end_c4} :catchall_d5

    .line 520
    add-int/lit8 v0, v0, 0x1

    goto :goto_b7

    .line 513
    :catchall_c7
    move-exception v0

    move-object v4, v0

    if-eqz v12, :cond_d4

    :try_start_cb
    invoke-virtual {v12}, Ljava/io/OutputStream;->close()V
    :try_end_ce
    .catchall {:try_start_cb .. :try_end_ce} :catchall_cf

    goto :goto_d4

    :catchall_cf
    move-exception v0

    move-object v12, v0

    :try_start_d1
    invoke-static {v4, v12}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_d4
    :goto_d4
    throw v4
    :try_end_d5
    .catchall {:try_start_d1 .. :try_end_d5} :catchall_d5

    .line 524
    :catchall_d5
    move-exception v0

    goto :goto_da

    :catchall_d7
    move-exception v0

    move-object/from16 v23, v4

    :cond_da
    :goto_da
    nop

    .line 528
    :try_start_db
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
    :try_end_144
    .catchall {:try_start_db .. :try_end_144} :catchall_20d

    .line 544
    :try_start_144
    invoke-virtual {v6, v4}, Ljava/io/OutputStream;->write([B)V
    :try_end_147
    .catchall {:try_start_144 .. :try_end_147} :catchall_1f3

    .line 545
    if-eqz v6, :cond_14c

    :try_start_149
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V

    .line 547
    :cond_14c
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    const/16 v6, 0xc8

    if-ne v4, v6, :cond_1e8

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

    :goto_162
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v0, v6, :cond_1dd

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
    move-object/from16 v8, v23

    invoke-virtual {v6, v8, v1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v7, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    :try_end_181
    .catchall {:try_start_149 .. :try_end_181} :catchall_20d

    .line 555
    move-object/from16 v9, v19

    const/4 v12, 0x0

    move-object/from16 v19, v1

    :try_start_186
    invoke-virtual {v6, v9, v12}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v1

    invoke-virtual {v7, v9, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;
    :try_end_18d
    .catchall {:try_start_186 .. :try_end_18d} :catchall_1d5

    .line 556
    move-object/from16 v1, v18

    move-object/from16 v18, v4

    :try_start_191
    invoke-virtual {v6, v1, v12}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v4

    invoke-virtual {v7, v1, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;
    :try_end_198
    .catchall {:try_start_191 .. :try_end_198} :catchall_1d1

    .line 557
    const/16 v4, 0x3e8

    move-object/from16 v12, v17

    :try_start_19c
    invoke-virtual {v7, v12, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 558
    const-string v4, "duration"

    move-object/from16 v23, v8

    const/16 v8, 0x3e8

    invoke-virtual {v7, v4, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 559
    const-string v4, "last_watched"

    const-string v8, "watched_at"
    :try_end_1ac
    .catchall {:try_start_19c .. :try_end_1ac} :catchall_1cd

    move-object/from16 v22, v12

    move-object/from16 v17, v13

    :try_start_1b0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v12

    invoke-virtual {v6, v8, v12, v13}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v12

    invoke-virtual {v7, v4, v12, v13}, Lorg/json/JSONObject;->put(Ljava/lang/String;J)Lorg/json/JSONObject;

    .line 560
    invoke-interface {v11, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_1be
    .catchall {:try_start_1b0 .. :try_end_1be} :catchall_20b

    .line 550
    add-int/lit8 v0, v0, 0x1

    move-object/from16 v13, v17

    move-object/from16 v4, v18

    move-object/from16 v17, v22

    move-object/from16 v18, v1

    move-object/from16 v1, v19

    move-object/from16 v19, v9

    goto :goto_162

    .line 563
    :catchall_1cd
    move-exception v0

    move-object/from16 v22, v12

    goto :goto_1da

    :catchall_1d1
    move-exception v0

    move-object/from16 v22, v17

    goto :goto_1da

    :catchall_1d5
    move-exception v0

    move-object/from16 v22, v17

    move-object/from16 v1, v18

    :goto_1da
    move-object/from16 v17, v13

    goto :goto_218

    .line 550
    :cond_1dd
    move-object/from16 v22, v17

    move-object/from16 v9, v19

    move-object/from16 v19, v1

    move-object/from16 v17, v13

    move-object/from16 v1, v18

    goto :goto_218

    .line 547
    :cond_1e8
    move-object/from16 v22, v17

    move-object/from16 v9, v19

    move-object/from16 v19, v1

    move-object/from16 v17, v13

    move-object/from16 v1, v18

    goto :goto_218

    .line 543
    :catchall_1f3
    move-exception v0

    move-object/from16 v22, v17

    move-object/from16 v9, v19

    move-object/from16 v19, v1

    move-object/from16 v17, v13

    move-object/from16 v1, v18

    move-object v4, v0

    if-eqz v6, :cond_20a

    :try_start_201
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V
    :try_end_204
    .catchall {:try_start_201 .. :try_end_204} :catchall_205

    goto :goto_20a

    :catchall_205
    move-exception v0

    move-object v6, v0

    :try_start_207
    invoke-static {v4, v6}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_20a
    :goto_20a
    throw v4
    :try_end_20b
    .catchall {:try_start_207 .. :try_end_20b} :catchall_20b

    .line 563
    :catchall_20b
    move-exception v0

    goto :goto_218

    :catchall_20d
    move-exception v0

    move-object/from16 v22, v17

    move-object/from16 v9, v19

    move-object/from16 v19, v1

    move-object/from16 v17, v13

    move-object/from16 v1, v18

    :goto_218
    nop

    .line 566
    :try_start_219
    invoke-interface {v11}, Ljava/util/List;->isEmpty()Z

    move-result v0
    :try_end_21d
    .catchall {:try_start_219 .. :try_end_21d} :catchall_4b5

    if-eqz v0, :cond_289

    .line 568
    :try_start_21f
    new-instance v0, Ljava/net/URL;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v6, "/rest/v1/watch_progress?select=*"

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v0, v4}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 569
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 570
    const-string v4, "GET"

    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 571
    invoke-virtual {v0, v10, v3}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 572
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v15, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 573
    const/16 v4, 0x1f40

    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 574
    invoke-virtual {v0, v4}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 576
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    const/16 v6, 0xc8

    if-ne v4, v6, :cond_288

    .line 577
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 578
    new-instance v4, Lorg/json/JSONArray;

    invoke-direct {v4, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 579
    const/4 v0, 0x0

    :goto_277
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v0, v6, :cond_288

    .line 580
    invoke-virtual {v4, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v6

    invoke-interface {v11, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_284
    .catchall {:try_start_21f .. :try_end_284} :catchall_287

    .line 579
    add-int/lit8 v0, v0, 0x1

    goto :goto_277

    .line 583
    :catchall_287
    move-exception v0

    :cond_288
    nop

    .line 587
    :cond_289
    move-object/from16 v4, p0

    move-object/from16 v6, v19

    :try_start_28d
    invoke-static {v4, v5, v2, v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->pullLibraryFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 589
    invoke-interface {v11}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_298

    .line 590
    const/4 v1, 0x0

    return v1

    .line 593
    :cond_298
    const-string v0, "streamsync.db"

    invoke-virtual {v4, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 594
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v2
    :try_end_2a6
    .catchall {:try_start_28d .. :try_end_2a6} :catchall_4b5

    if-nez v2, :cond_2b3

    .line 595
    :try_start_2a8
    invoke-virtual {v0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->mkdirs()Z
    :try_end_2af
    .catchall {:try_start_2a8 .. :try_end_2af} :catchall_2b0

    goto :goto_2b3

    .line 669
    :catchall_2b0
    move-exception v0

    goto/16 :goto_4b7

    .line 598
    :cond_2b3
    :goto_2b3
    :try_start_2b3
    invoke-static/range {p0 .. p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->ensureRoomDatabase(Landroid/content/Context;)V

    .line 600
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/high16 v2, 0x10000000

    const/4 v3, 0x0

    invoke-static {v0, v3, v2}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v2
    :try_end_2c1
    .catchall {:try_start_2b3 .. :try_end_2c1} :catchall_4b5

    .line 602
    :try_start_2c1
    const-string v0, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 603
    const-string v0, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'4201b4cf7d52e2b03ff5e210c14b7a60\')"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 604
    const-string v0, "PRAGMA user_version = 7"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 606
    const-string v0, "CREATE TABLE IF NOT EXISTS `watch_progress` (`mediaId` TEXT NOT NULL, `positionMs` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, `updatedAtEpochMs` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `pendingSync` INTEGER NOT NULL, `streamUrl` TEXT, `addonId` TEXT, `addonName` TEXT, PRIMARY KEY(`mediaId`));"

    invoke-virtual {v2, v0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 608
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 609
    invoke-interface {v11}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0
    :try_end_2dc
    .catchall {:try_start_2c1 .. :try_end_2dc} :catchall_4a9

    const/4 v12, 0x0

    :goto_2dd
    :try_start_2dd
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_498

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/json/JSONObject;

    .line 610
    move-object/from16 v5, v17

    invoke-virtual {v4, v5, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 611
    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v8

    if-eqz v8, :cond_2fb

    const-string v7, "contentId"

    invoke-virtual {v4, v7, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 613
    :cond_2fb
    const-string v8, "video_id"

    invoke-virtual {v4, v8, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 614
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_30d

    const-string v8, "videoId"

    invoke-virtual {v4, v8, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 616
    :cond_30d
    const/4 v10, 0x0

    invoke-virtual {v4, v9, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v11

    .line 617
    invoke-virtual {v4, v1, v10}, Lorg/json/JSONObject;->optInt(Ljava/lang/String;I)I

    move-result v13

    .line 619
    nop

    .line 620
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_323

    const-string v8, "progress_key"

    invoke-virtual {v4, v8, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 621
    :cond_323
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_32a

    move-object v8, v7

    .line 622
    :cond_32a
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v10
    :try_end_32e
    .catchall {:try_start_2dd .. :try_end_32e} :catchall_4a6

    const-string v14, "mediaId"

    if-eqz v10, :cond_336

    :try_start_332
    invoke-virtual {v4, v14, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 624
    :cond_336
    if-lez v11, :cond_360

    if-lez v13, :cond_360

    move-object/from16 v10, v16

    invoke-virtual {v8, v10}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v15

    if-nez v15, :cond_362

    .line 625
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

    goto :goto_362

    .line 624
    :cond_360
    move-object/from16 v10, v16

    .line 628
    :cond_362
    :goto_362
    invoke-virtual {v8}, Ljava/lang/String;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_47b

    move-object/from16 v7, p1

    invoke-interface {v7, v8}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v11

    if-eqz v11, :cond_37e

    .line 629
    move-object/from16 p0, v0

    move-object/from16 v18, v1

    move-object/from16 v17, v5

    move-object/from16 v19, v6

    move-object/from16 v11, v22

    const/4 v1, 0x0

    move-object v5, v3

    goto/16 :goto_489

    .line 631
    :cond_37e
    invoke-interface {v7, v8}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 633
    move-object/from16 p0, v0

    move-object/from16 v18, v1

    const-wide/16 v0, 0x0

    move-object/from16 v11, v22

    invoke-virtual {v4, v11, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15
    :try_end_38d
    .catchall {:try_start_332 .. :try_end_38d} :catchall_4a6

    .line 634
    const-string v13, "positionMs"

    cmp-long v17, v15, v0

    if-nez v17, :cond_397

    :try_start_393
    invoke-virtual {v4, v13, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15

    .line 635
    :cond_397
    cmp-long v17, v15, v0

    if-lez v17, :cond_3a5

    const-wide/16 v19, 0x2710

    cmp-long v17, v15, v19

    if-gez v17, :cond_3a5

    const-wide/16 v19, 0x3e8

    mul-long v15, v15, v19

    .line 636
    :cond_3a5
    cmp-long v17, v15, v0

    if-gtz v17, :cond_3ac

    const-wide/32 v15, 0xea60

    .line 638
    :cond_3ac
    const-string v3, "duration"

    invoke-virtual {v4, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v19

    .line 639
    cmp-long v3, v19, v0

    if-nez v3, :cond_3bc

    const-string v3, "durationMs"

    invoke-virtual {v4, v3, v0, v1}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v19
    :try_end_3bc
    .catchall {:try_start_393 .. :try_end_3bc} :catchall_4a6

    .line 640
    :cond_3bc
    cmp-long v3, v19, v0

    if-lez v3, :cond_3cd

    const-wide/16 v21, 0x2710

    cmp-long v3, v19, v21

    if-gez v3, :cond_3cd

    const-wide/16 v21, 0x3e8

    mul-long v19, v19, v21

    move-wide/from16 v0, v19

    goto :goto_3cf

    .line 641
    :cond_3cd
    move-wide/from16 v0, v19

    :goto_3cf
    cmp-long v3, v0, v15

    if-lez v3, :cond_3e8

    move-object/from16 v17, v5

    move-object/from16 v19, v6

    long-to-double v5, v0

    const-wide v20, 0x3fef0a3d70a3d70aL    # 0.97

    invoke-static {v5, v6}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v5, v5, v20

    double-to-long v5, v5

    cmp-long v3, v15, v5

    if-ltz v3, :cond_3f8

    goto :goto_3ec

    :cond_3e8
    move-object/from16 v17, v5

    move-object/from16 v19, v6

    .line 642
    :goto_3ec
    const-wide/16 v0, 0x2

    mul-long v0, v0, v15

    const-wide/32 v5, 0x493e0

    add-long/2addr v5, v15

    :try_start_3f4
    invoke-static {v0, v1, v5, v6}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    .line 645
    :cond_3f8
    const-string v3, "last_watched"

    const-wide/16 v5, 0x0

    invoke-virtual {v4, v3, v5, v6}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v20

    .line 646
    cmp-long v3, v20, v5

    if-nez v3, :cond_40a

    const-string v3, "lastWatched"

    invoke-virtual {v4, v3, v5, v6}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v20

    .line 647
    :cond_40a
    cmp-long v3, v20, v5

    if-nez v3, :cond_414

    const-string v3, "watched_at"

    invoke-virtual {v4, v3, v5, v6}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v20

    .line 648
    :cond_414
    cmp-long v3, v20, v5

    if-nez v3, :cond_422

    const-string v3, "updatedAtEpochMs"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    invoke-virtual {v4, v3, v5, v6}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v20

    .line 650
    :cond_422
    new-instance v3, Landroid/content/ContentValues;

    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    .line 651
    invoke-virtual {v3, v14, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 652
    invoke-static/range {v15 .. v16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v13, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 653
    const-string v4, "durationMs"

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    invoke-virtual {v3, v4, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 654
    const-string v0, "updatedAtEpochMs"

    invoke-static/range {v20 .. v21}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v3, v0, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 655
    const-string v0, "deviceId"

    const-string v1, "streamsync"

    invoke-virtual {v3, v0, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 656
    const-string v0, "pendingSync"

    const/4 v1, 0x0

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, v0, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 657
    const-string v0, "streamUrl"

    invoke-virtual {v3, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 658
    const-string v0, "addonId"

    invoke-virtual {v3, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 659
    const-string v0, "addonName"

    invoke-virtual {v3, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 661
    const-string v0, "watch_progress"

    const/4 v4, 0x5

    const/4 v5, 0x0

    invoke-virtual {v2, v0, v5, v3, v4}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 662
    add-int/lit8 v12, v12, 0x1

    .line 663
    move-object/from16 v0, p0

    move-object v3, v5

    move-object/from16 p1, v7

    move-object/from16 v16, v10

    move-object/from16 v22, v11

    move-object/from16 v1, v18

    move-object/from16 v6, v19

    goto/16 :goto_2dd

    .line 628
    :cond_47b
    move-object/from16 v7, p1

    move-object/from16 p0, v0

    move-object/from16 v18, v1

    move-object/from16 v17, v5

    move-object/from16 v19, v6

    move-object/from16 v11, v22

    const/4 v1, 0x0

    move-object v5, v3

    .line 609
    :goto_489
    move-object/from16 v0, p0

    move-object v3, v5

    move-object/from16 p1, v7

    move-object/from16 v16, v10

    move-object/from16 v22, v11

    move-object/from16 v1, v18

    move-object/from16 v6, v19

    goto/16 :goto_2dd

    .line 664
    :cond_498
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_49b
    .catchall {:try_start_3f4 .. :try_end_49b} :catchall_4a6

    .line 666
    :try_start_49b
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 667
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->close()V
    :try_end_4a1
    .catchall {:try_start_49b .. :try_end_4a1} :catchall_4a3

    .line 668
    nop

    .line 669
    goto :goto_4b9

    :catchall_4a3
    move-exception v0

    move v5, v12

    goto :goto_4b8

    .line 666
    :catchall_4a6
    move-exception v0

    move v5, v12

    goto :goto_4ac

    :catchall_4a9
    move-exception v0

    const/4 v1, 0x0

    const/4 v5, 0x0

    :goto_4ac
    :try_start_4ac
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 667
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 668
    throw v0
    :try_end_4b3
    .catchall {:try_start_4ac .. :try_end_4b3} :catchall_4b3

    .line 669
    :catchall_4b3
    move-exception v0

    goto :goto_4b8

    :catchall_4b5
    move-exception v0

    const/4 v1, 0x0

    :goto_4b7
    const/4 v5, 0x0

    :goto_4b8
    move v12, v5

    .line 670
    :goto_4b9
    return v12
.end method

.method private static readStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 1027
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 1028
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1030
    :goto_11
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 1031
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_11

    .line 1033
    :cond_21
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    .line 1034
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static refreshRoom(Landroid/content/Context;)V
    .registers 8

    .line 1011
    :try_start_0
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 1012
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 1013
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

    .line 1014
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 1015
    if-eqz p0, :cond_58

    .line 1016
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "getInvalidationTracker"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 1017
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 1018
    if-eqz p0, :cond_58

    .line 1019
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "refreshVersionsAsync"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 1020
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_56
    .catchall {:try_start_0 .. :try_end_56} :catchall_57

    goto :goto_58

    .line 1023
    :catchall_57
    move-exception p0

    :cond_58
    :goto_58
    nop

    .line 1024
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
