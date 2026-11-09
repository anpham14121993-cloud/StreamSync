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

    .line 47
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static exchangeAndSaveSession(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    .registers 19

    .line 300
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

    .line 301
    invoke-virtual {v7}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v7

    check-cast v7, Ljava/net/HttpURLConnection;

    .line 302
    const-string v8, "POST"

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 303
    const-string v8, "Content-Type"

    const-string v9, "application/json"

    invoke-virtual {v7, v8, v9}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 304
    invoke-virtual {v7, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 305
    const-string v8, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v7, v2, v8}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 306
    const/16 v8, 0x2710

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 307
    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 308
    const/4 v8, 0x1

    invoke-virtual {v7, v8}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 310
    new-instance v9, Lorg/json/JSONObject;

    invoke-direct {v9}, Lorg/json/JSONObject;-><init>()V

    .line 311
    const-string v10, "code"

    move-object/from16 v11, p1

    invoke-virtual {v9, v10, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 312
    const-string v10, "device_nonce"

    move-object/from16 v11, p2

    invoke-virtual {v9, v10, v11}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 314
    invoke-virtual {v9}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v9

    sget-object v10, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v9, v10}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v9

    .line 315
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v10
    :try_end_5b
    .catchall {:try_start_d .. :try_end_5b} :catchall_153

    .line 316
    :try_start_5b
    invoke-virtual {v10, v9}, Ljava/io/OutputStream;->write([B)V
    :try_end_5e
    .catchall {:try_start_5b .. :try_end_5e} :catchall_145

    .line 317
    if-eqz v10, :cond_63

    :try_start_60
    invoke-virtual {v10}, Ljava/io/OutputStream;->close()V

    .line 319
    :cond_63
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v9

    .line 320
    const/16 v10, 0xc8

    if-ne v9, v10, :cond_154

    .line 321
    invoke-virtual {v7}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v7

    invoke-static {v7}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v7

    .line 322
    new-instance v9, Lorg/json/JSONObject;

    invoke-direct {v9, v7}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 323
    const-string v7, "access_token"

    invoke-virtual {v9, v7, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 324
    invoke-virtual {v9, v1, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 325
    nop

    .line 326
    nop

    .line 328
    invoke-virtual {v9, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v12
    :try_end_88
    .catchall {:try_start_60 .. :try_end_88} :catchall_153

    const-string v13, "id"

    const-string v14, "email"

    if-eqz v12, :cond_9d

    .line 329
    :try_start_8e
    invoke-virtual {v9, v0}, Lorg/json/JSONObject;->getJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 330
    invoke-virtual {v0, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 331
    invoke-virtual {v0, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v12, v9

    move-object v9, v0

    goto :goto_9f

    .line 328
    :cond_9d
    move-object v9, v5

    move-object v12, v9

    .line 334
    :goto_9f
    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_f9

    invoke-virtual {v7}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_a9
    .catchall {:try_start_8e .. :try_end_a9} :catchall_153

    if-nez v0, :cond_f9

    .line 336
    :try_start_ab
    new-instance v0, Ljava/net/URL;

    const-string v15, "https://api.nuvio.tv/auth/v1/user"

    invoke-direct {v0, v15}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 337
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 338
    const-string v15, "GET"

    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 339
    invoke-virtual {v0, v3, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 340
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

    .line 341
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v2

    if-ne v2, v10, :cond_f8

    .line 342
    new-instance v2, Lorg/json/JSONObject;

    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 343
    invoke-virtual {v2, v14, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3
    :try_end_ed
    .catchall {:try_start_ab .. :try_end_ed} :catchall_f7

    .line 344
    :try_start_ed
    invoke-virtual {v2, v13, v5}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_f1
    .catchall {:try_start_ed .. :try_end_f1} :catchall_f4

    move-object v9, v0

    move-object v12, v3

    goto :goto_f8

    .line 346
    :catchall_f4
    move-exception v0

    move-object v12, v3

    goto :goto_f8

    :catchall_f7
    move-exception v0

    :cond_f8
    :goto_f8
    nop

    .line 349
    :cond_f9
    :try_start_f9
    invoke-virtual {v12}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_101

    const-string v12, "T\u00e0i kho\u1ea3n Nuvio"

    .line 351
    :cond_101
    const-string v0, "nuvio_supabase_auth"

    move-object/from16 v2, p0

    invoke-virtual {v2, v0, v6}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 352
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "server_url"

    const-string v3, "https://api.nuvio.tv"

    .line 353
    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "anon_key"

    .line 354
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_token"

    .line 355
    invoke-interface {v0, v2, v7}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 356
    invoke-interface {v0, v1, v11}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "auth_email"

    .line 357
    invoke-interface {v0, v1, v12}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "auth_user_id"

    .line 358
    invoke-interface {v0, v1, v9}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "is_anonymous"

    .line 359
    invoke-interface {v0, v1, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "last_login_time"

    .line 360
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 361
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_144
    .catchall {:try_start_f9 .. :try_end_144} :catchall_153

    .line 362
    return v8

    .line 315
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

    .line 364
    :catchall_153
    move-exception v0

    :cond_154
    nop

    .line 365
    return v6
.end method

.method public static fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    .line 77
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 78
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const-string v1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    if-eqz v0, :cond_d

    return-object v1

    .line 80
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

    .line 81
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    .line 82
    const-string v0, "GET"

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 83
    const/16 v0, 0x1770

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 84
    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 85
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v0

    .line 86
    const/16 v2, 0xc8

    if-ne v0, v2, :cond_5d

    .line 87
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 88
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 89
    const-string p0, "publishable_key"

    const-string v2, ""

    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 90
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_59
    .catchall {:try_start_d .. :try_end_59} :catchall_5c

    if-nez v0, :cond_5d

    .line 91
    return-object p0

    .line 94
    :catchall_5c
    move-exception p0

    :cond_5d
    nop

    .line 95
    return-object v1
.end method

.method public static installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I
    .registers 19
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)I"
        }
    .end annotation

    .line 591
    const-string v1, "icon"

    const-string v2, "description"

    const-string v3, "version"

    const-string v4, "name"

    const-string v5, "id"

    const-string v6, ""

    .line 592
    const-string v0, "streamsync.db"

    move-object/from16 v7, p0

    invoke-virtual {v7, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 593
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v7

    const/4 v8, 0x0

    if-eqz v7, :cond_10d

    if-eqz p1, :cond_10d

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->isEmpty()Z

    move-result v7

    if-eqz v7, :cond_25

    goto/16 :goto_10d

    .line 595
    :cond_25
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/4 v7, 0x0

    invoke-static {v0, v7, v8}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v9

    .line 597
    :try_start_2e
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 598
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v10

    :goto_35
    invoke-interface {v10}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_fa

    invoke-interface {v10}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 599
    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 600
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v11
    :try_end_49
    .catchall {:try_start_2e .. :try_end_49} :catchall_105

    if-eqz v11, :cond_4c

    goto :goto_35

    .line 603
    :cond_4c
    :try_start_4c
    new-instance v11, Ljava/net/URL;

    invoke-direct {v11, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 604
    invoke-virtual {v11}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v11

    check-cast v11, Ljava/net/HttpURLConnection;

    .line 605
    const-string v12, "GET"

    invoke-virtual {v11, v12}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 606
    const/16 v12, 0x1f40

    invoke-virtual {v11, v12}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 607
    invoke-virtual {v11, v12}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 608
    invoke-virtual {v11}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v12

    const/16 v13, 0xc8

    if-ne v12, v13, :cond_ec

    .line 609
    invoke-virtual {v11}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v11

    invoke-static {v11}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v11

    .line 610
    new-instance v12, Lorg/json/JSONObject;

    invoke-direct {v12, v11}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 612
    invoke-virtual {v12, v5, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13

    .line 613
    invoke-virtual {v12, v4, v13}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v14

    .line 614
    const-string v15, "1.0.0"

    invoke-virtual {v12, v3, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15
    :try_end_87
    .catchall {:try_start_4c .. :try_end_87} :catchall_f0

    .line 615
    :try_start_87
    invoke-virtual {v12, v2, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 616
    invoke-virtual {v12, v1, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    .line 618
    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v16
    :try_end_93
    .catchall {:try_start_87 .. :try_end_93} :catchall_e7

    if-nez v16, :cond_e3

    .line 619
    move-object/from16 v16, v6

    :try_start_97
    new-instance v6, Landroid/content/ContentValues;

    invoke-direct {v6}, Landroid/content/ContentValues;-><init>()V

    .line 620
    invoke-virtual {v6, v5, v13}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 621
    invoke-virtual {v6, v4, v14}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 622
    invoke-virtual {v6, v3, v15}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 623
    invoke-virtual {v6, v2, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 624
    invoke-virtual {v6, v1, v12}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 625
    const-string v7, "manifestUrl"

    invoke-virtual {v6, v7, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 626
    const-string v0, "manifestJson"

    invoke-virtual {v6, v0, v11}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 627
    const-string v0, "installedAt"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v11

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v7

    invoke-virtual {v6, v0, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 628
    const-string v0, "isEnabled"

    const/4 v7, 0x1

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v6, v0, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 629
    const-string v0, "orderIndex"

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v6, v0, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 631
    const-string v0, "addons"
    :try_end_d7
    .catchall {:try_start_97 .. :try_end_d7} :catchall_e1

    const/4 v7, 0x5

    const/4 v11, 0x0

    :try_start_d9
    invoke-virtual {v9, v0, v11, v6, v7}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    :try_end_dc
    .catchall {:try_start_d9 .. :try_end_dc} :catchall_df

    .line 632
    add-int/lit8 v8, v8, 0x1

    goto :goto_f4

    .line 635
    :catchall_df
    move-exception v0

    goto :goto_f4

    :catchall_e1
    move-exception v0

    goto :goto_ea

    .line 618
    :cond_e3
    move-object/from16 v16, v6

    const/4 v11, 0x0

    goto :goto_f4

    .line 635
    :catchall_e7
    move-exception v0

    move-object/from16 v16, v6

    :goto_ea
    const/4 v11, 0x0

    goto :goto_f4

    .line 608
    :cond_ec
    move-object/from16 v16, v6

    move-object v11, v7

    goto :goto_f4

    .line 635
    :catchall_f0
    move-exception v0

    move-object/from16 v16, v6

    move-object v11, v7

    :goto_f4
    nop

    .line 636
    move-object v7, v11

    move-object/from16 v6, v16

    goto/16 :goto_35

    .line 637
    :cond_fa
    :try_start_fa
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_fd
    .catchall {:try_start_fa .. :try_end_fd} :catchall_105

    .line 639
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 640
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 641
    nop

    .line 642
    return v8

    .line 639
    :catchall_105
    move-exception v0

    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 640
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 641
    throw v0

    .line 593
    :cond_10d
    :goto_10d
    return v8
.end method

.method public static login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 13

    .line 99
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

    .line 188
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 189
    return-void
.end method

.method public static normalizeUrl(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    .line 65
    if-nez p0, :cond_5

    const-string p0, ""

    return-object p0

    .line 66
    :cond_5
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 67
    :goto_9
    const-string v0, "/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1d

    .line 68
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    const/4 v1, 0x0

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    goto :goto_9

    .line 70
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

    .line 71
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 73
    :cond_44
    return-object p0
.end method

.method public static pollDeviceStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .registers 5

    .line 267
    :try_start_0
    new-instance v0, Ljava/net/URL;

    const-string v1, "https://api.nuvio.tv/rest/v1/rpc/poll_tv_login_session"

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 268
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 269
    const-string v1, "POST"

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 270
    const-string v1, "Content-Type"

    const-string v2, "application/json"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 271
    const-string v1, "apikey"

    const-string v2, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 272
    const-string v1, "Authorization"

    const-string v2, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 273
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 274
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 275
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 277
    new-instance v1, Lorg/json/JSONObject;

    invoke-direct {v1}, Lorg/json/JSONObject;-><init>()V

    .line 278
    const-string v2, "p_code"

    invoke-virtual {v1, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 279
    const-string p0, "p_device_nonce"

    invoke-virtual {v1, p0, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 281
    invoke-virtual {v1}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p0

    sget-object p1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p0, p1}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p0

    .line 282
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object p1
    :try_end_50
    .catchall {:try_start_0 .. :try_end_50} :catchall_95

    .line 283
    :try_start_50
    invoke-virtual {p1, p0}, Ljava/io/OutputStream;->write([B)V
    :try_end_53
    .catchall {:try_start_50 .. :try_end_53} :catchall_89

    .line 284
    if-eqz p1, :cond_58

    :try_start_55
    invoke-virtual {p1}, Ljava/io/OutputStream;->close()V

    .line 286
    :cond_58
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result p0

    .line 287
    const/16 p1, 0xc8

    if-ne p0, p1, :cond_96

    .line 288
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 289
    new-instance p1, Lorg/json/JSONArray;

    invoke-direct {p1, p0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 290
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result p0

    if-lez p0, :cond_96

    .line 291
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

    .line 282
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

    .line 294
    :catchall_95
    move-exception p0

    :cond_96
    nop

    .line 295
    const-string p0, "error"

    return-object p0
.end method

.method public static pullAddonsFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 21

    .line 429
    move-object/from16 v1, p2

    move-object/from16 v2, p3

    const-string v3, "enabled"

    const-string v4, ""

    const-string v5, "url"

    const-string v6, "Bearer "

    const-string v7, "Authorization"

    const-string v8, "apikey"

    const-string v9, "GET"

    .line 431
    :try_start_12
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 432
    new-instance v12, Ljava/util/ArrayList;

    invoke-direct {v12}, Ljava/util/ArrayList;-><init>()V
    :try_end_1b
    .catchall {:try_start_12 .. :try_end_1b} :catchall_137

    .line 436
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

    .line 437
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 438
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 439
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 440
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v0, v7, v10}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 441
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 442
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 444
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    if-ne v10, v13, :cond_a1

    .line 445
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 446
    new-instance v10, Lorg/json/JSONArray;

    invoke-direct {v10, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 447
    const/4 v0, 0x0

    :goto_71
    invoke-virtual {v10}, Lorg/json/JSONArray;->length()I

    move-result v14

    if-ge v0, v14, :cond_a1

    .line 448
    invoke-virtual {v10, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v14

    .line 449
    invoke-virtual {v14, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    .line 450
    const/4 v15, 0x1

    invoke-virtual {v14, v3, v15}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v14

    .line 451
    if-eqz v14, :cond_99

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_99

    invoke-interface {v12, v13}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_99

    .line 452
    invoke-interface {v12, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_99
    .catchall {:try_start_1f .. :try_end_99} :catchall_a0

    .line 447
    :cond_99
    add-int/lit8 v0, v0, 0x1

    const/16 v13, 0xc8

    const/16 v15, 0x1f40

    goto :goto_71

    .line 456
    :catchall_a0
    move-exception v0

    :cond_a1
    nop

    .line 460
    :try_start_a2
    new-instance v0, Ljava/net/URL;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, "/rest/v1/plugins?select=*"

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v0, v10}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 461
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 462
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 463
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 464
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v7, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 465
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 466
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 468
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-ne v1, v2, :cond_124

    .line 469
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 470
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 471
    const/4 v0, 0x0

    :goto_f8
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v0, v2, :cond_124

    .line 472
    invoke-virtual {v1, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v2

    .line 473
    invoke-virtual {v2, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 474
    const/4 v7, 0x1

    invoke-virtual {v2, v3, v7}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v2

    .line 475
    if-eqz v2, :cond_120

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_120

    invoke-interface {v12, v6}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_120

    .line 476
    invoke-interface {v12, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_120
    .catchall {:try_start_a2 .. :try_end_120} :catchall_123

    .line 471
    :cond_120
    add-int/lit8 v0, v0, 0x1

    goto :goto_f8

    .line 480
    :catchall_123
    move-exception v0

    :cond_124
    nop

    .line 482
    :try_start_125
    invoke-interface {v12}, Ljava/util/List;->isEmpty()Z

    move-result v0
    :try_end_129
    .catchall {:try_start_125 .. :try_end_129} :catchall_137

    if-eqz v0, :cond_12d

    .line 483
    const/4 v1, 0x0

    return v1

    .line 486
    :cond_12d
    const/4 v1, 0x0

    move-object/from16 v2, p0

    :try_start_130
    invoke-static {v2, v12}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result v10
    :try_end_134
    .catchall {:try_start_130 .. :try_end_134} :catchall_135

    .line 487
    goto :goto_13a

    :catchall_135
    move-exception v0

    goto :goto_139

    :catchall_137
    move-exception v0

    const/4 v1, 0x0

    :goto_139
    const/4 v10, 0x0

    .line 488
    :goto_13a
    return v10
.end method

.method public static pullWatchProgressFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 26

    .line 492
    move-object/from16 v1, p2

    move-object/from16 v2, p3

    const-string v3, "Bearer "

    const-string v4, "Authorization"

    const-string v5, "apikey"

    const-string v6, ""

    .line 494
    const/4 v7, 0x0

    :try_start_d
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_11
    .catchall {:try_start_d .. :try_end_11} :catchall_208

    .line 495
    nop

    .line 499
    const/16 v9, 0xc8

    const/4 v10, 0x0

    const/16 v11, 0x1f40

    :try_start_17
    new-instance v0, Ljava/net/URL;

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    const-string v13, "/rest/v1/rpc/sync_pull_watch_progress"

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    invoke-direct {v0, v12}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 500
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 501
    const-string v12, "POST"

    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 502
    const-string v12, "Content-Type"

    const-string v13, "application/json"

    invoke-virtual {v0, v12, v13}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 503
    invoke-virtual {v0, v5, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 504
    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-virtual {v12, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v0, v4, v12}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 505
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 506
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 507
    const/4 v12, 0x1

    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 509
    new-instance v13, Lorg/json/JSONObject;

    invoke-direct {v13}, Lorg/json/JSONObject;-><init>()V

    .line 510
    const-string v14, "p_profile_id"

    invoke-virtual {v13, v14, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 511
    invoke-virtual {v13}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v12

    sget-object v13, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v12, v13}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v12

    .line 512
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v13
    :try_end_7a
    .catchall {:try_start_17 .. :try_end_7a} :catchall_a6

    .line 513
    :try_start_7a
    invoke-virtual {v13, v12}, Ljava/io/OutputStream;->write([B)V
    :try_end_7d
    .catchall {:try_start_7a .. :try_end_7d} :catchall_98

    .line 514
    if-eqz v13, :cond_82

    :try_start_7f
    invoke-virtual {v13}, Ljava/io/OutputStream;->close()V

    .line 516
    :cond_82
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v12

    if-ne v12, v9, :cond_96

    .line 517
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 518
    new-instance v12, Lorg/json/JSONArray;

    invoke-direct {v12, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
    :try_end_95
    .catchall {:try_start_7f .. :try_end_95} :catchall_a6

    goto :goto_97

    .line 516
    :cond_96
    move-object v12, v10

    .line 520
    :goto_97
    goto :goto_a8

    .line 512
    :catchall_98
    move-exception v0

    move-object v12, v0

    if-eqz v13, :cond_a5

    :try_start_9c
    invoke-virtual {v13}, Ljava/io/OutputStream;->close()V
    :try_end_9f
    .catchall {:try_start_9c .. :try_end_9f} :catchall_a0

    goto :goto_a5

    :catchall_a0
    move-exception v0

    move-object v13, v0

    :try_start_a2
    invoke-static {v12, v13}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_a5
    :goto_a5
    throw v12
    :try_end_a6
    .catchall {:try_start_a2 .. :try_end_a6} :catchall_a6

    .line 520
    :catchall_a6
    move-exception v0

    move-object v12, v10

    .line 523
    :goto_a8
    if-eqz v12, :cond_b4

    :try_start_aa
    invoke-virtual {v12}, Lorg/json/JSONArray;->length()I

    move-result v0
    :try_end_ae
    .catchall {:try_start_aa .. :try_end_ae} :catchall_b1

    if-nez v0, :cond_10b

    goto :goto_b4

    .line 586
    :catchall_b1
    move-exception v0

    goto/16 :goto_20b

    .line 525
    :cond_b4
    :goto_b4
    :try_start_b4
    new-instance v0, Ljava/net/URL;

    new-instance v13, Ljava/lang/StringBuilder;

    invoke-direct {v13}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v13, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v13, "/rest/v1/watch_progress?select=*"

    invoke-virtual {v8, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-direct {v0, v8}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 526
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 527
    const-string v8, "GET"

    invoke-virtual {v0, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 528
    invoke-virtual {v0, v5, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 529
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v4, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 530
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 531
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 533
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    if-ne v1, v9, :cond_108

    .line 534
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 535
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
    :try_end_107
    .catchall {:try_start_b4 .. :try_end_107} :catchall_109

    move-object v12, v1

    .line 537
    :cond_108
    :goto_108
    goto :goto_10b

    :catchall_109
    move-exception v0

    goto :goto_108

    .line 540
    :cond_10b
    :goto_10b
    if-eqz v12, :cond_206

    :try_start_10d
    invoke-virtual {v12}, Lorg/json/JSONArray;->length()I

    move-result v0

    if-nez v0, :cond_115

    goto/16 :goto_206

    .line 544
    :cond_115
    const-string v0, "streamsync.db"

    move-object/from16 v1, p0

    invoke-virtual {v1, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 545
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_124

    return v7

    .line 547
    :cond_124
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v10, v7}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1
    :try_end_12c
    .catchall {:try_start_10d .. :try_end_12c} :catchall_204

    .line 549
    :try_start_12c
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V
    :try_end_12f
    .catchall {:try_start_12c .. :try_end_12f} :catchall_1fb

    .line 550
    const/4 v0, 0x0

    const/4 v2, 0x0

    :goto_131
    :try_start_131
    invoke-virtual {v12}, Lorg/json/JSONArray;->length()I

    move-result v3

    if-ge v0, v3, :cond_1ea

    .line 551
    invoke-virtual {v12, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    .line 552
    const-string v4, "contentId"

    invoke-virtual {v3, v4, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 553
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_14d

    const-string v4, "videoId"

    invoke-virtual {v3, v4, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 554
    :cond_14d
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5
    :try_end_151
    .catchall {:try_start_131 .. :try_end_151} :catchall_1f8

    const-string v8, "mediaId"

    if-eqz v5, :cond_159

    :try_start_155
    invoke-virtual {v3, v8, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 556
    :cond_159
    const-string v5, "position"

    const-wide/16 v13, 0x0

    invoke-virtual {v3, v5, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15
    :try_end_161
    .catchall {:try_start_155 .. :try_end_161} :catchall_1f8

    .line 557
    const-string v5, "positionMs"

    cmp-long v9, v15, v13

    if-nez v9, :cond_16b

    :try_start_167
    invoke-virtual {v3, v5, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15

    .line 559
    :cond_16b
    const-string v9, "duration"

    invoke-virtual {v3, v9, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v17
    :try_end_171
    .catchall {:try_start_167 .. :try_end_171} :catchall_1f8

    .line 560
    const-string v9, "durationMs"

    cmp-long v11, v17, v13

    if-nez v11, :cond_17b

    :try_start_177
    invoke-virtual {v3, v9, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v17

    .line 562
    :cond_17b
    const-string v11, "lastWatched"

    invoke-virtual {v3, v11, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v19
    :try_end_181
    .catchall {:try_start_177 .. :try_end_181} :catchall_1f8

    .line 563
    const-string v11, "updatedAtEpochMs"

    cmp-long v21, v19, v13

    if-nez v21, :cond_192

    move-object/from16 p0, v8

    :try_start_189
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v7

    invoke-virtual {v3, v11, v7, v8}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v19

    goto :goto_194

    :cond_192
    move-object/from16 p0, v8

    .line 565
    :goto_194
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1e5

    cmp-long v3, v17, v13

    if-lez v3, :cond_1e5

    .line 566
    new-instance v3, Landroid/content/ContentValues;

    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    .line 567
    move-object/from16 v7, p0

    invoke-virtual {v3, v7, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 568
    invoke-static/range {v15 .. v16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v5, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 569
    invoke-static/range {v17 .. v18}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v9, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 570
    invoke-static/range {v19 .. v20}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v11, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 571
    const-string v4, "deviceId"

    const-string v5, "streamsync"

    invoke-virtual {v3, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 572
    const-string v4, "pendingSync"

    const/4 v5, 0x0

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v3, v4, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 573
    const-string v4, "streamUrl"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 574
    const-string v4, "addonId"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 575
    const-string v4, "addonName"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 577
    const-string v4, "watch_progress"

    const/4 v5, 0x5

    invoke-virtual {v1, v4, v10, v3, v5}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 578
    add-int/lit8 v2, v2, 0x1

    .line 550
    :cond_1e5
    add-int/lit8 v0, v0, 0x1

    const/4 v7, 0x0

    goto/16 :goto_131

    .line 581
    :cond_1ea
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_1ed
    .catchall {:try_start_189 .. :try_end_1ed} :catchall_1f8

    .line 583
    :try_start_1ed
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 584
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V
    :try_end_1f3
    .catchall {:try_start_1ed .. :try_end_1f3} :catchall_1f5

    .line 585
    nop

    .line 586
    goto :goto_20c

    :catchall_1f5
    move-exception v0

    move v7, v2

    goto :goto_20b

    .line 583
    :catchall_1f8
    move-exception v0

    move v7, v2

    goto :goto_1fd

    :catchall_1fb
    move-exception v0

    const/4 v7, 0x0

    :goto_1fd
    :try_start_1fd
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 584
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 585
    throw v0
    :try_end_204
    .catchall {:try_start_1fd .. :try_end_204} :catchall_b1

    .line 586
    :catchall_204
    move-exception v0

    goto :goto_20a

    .line 541
    :cond_206
    :goto_206
    const/4 v1, 0x0

    return v1

    .line 586
    :catchall_208
    move-exception v0

    const/4 v1, 0x0

    :goto_20a
    const/4 v7, 0x0

    :goto_20b
    move v2, v7

    .line 587
    :goto_20c
    return v2
.end method

.method private static readStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 663
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 664
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 666
    :goto_11
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 667
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_11

    .line 669
    :cond_21
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    .line 670
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static refreshRoom(Landroid/content/Context;)V
    .registers 8

    .line 647
    :try_start_0
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 648
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 649
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

    .line 650
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 651
    if-eqz p0, :cond_58

    .line 652
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "getInvalidationTracker"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 653
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 654
    if-eqz p0, :cond_58

    .line 655
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "refreshVersionsAsync"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 656
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_56
    .catchall {:try_start_0 .. :try_end_56} :catchall_57

    goto :goto_58

    .line 659
    :catchall_57
    move-exception p0

    :cond_58
    :goto_58
    nop

    .line 660
    return-void
.end method

.method public static runOnMain(Ljava/lang/Runnable;)V
    .registers 2

    .line 50
    sget-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    invoke-virtual {v0, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 51
    return-void
.end method

.method public static showToast(Landroid/content/Context;Ljava/lang/String;)V
    .registers 3

    .line 54
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$1;

    invoke-direct {v0, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$1;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 62
    return-void
.end method

.method public static startDeviceLink(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V
    .registers 3

    .line 192
    new-instance p0, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$3;

    invoke-direct {v0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V

    invoke-direct {p0, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 262
    invoke-virtual {p0}, Ljava/lang/Thread;->start()V

    .line 263
    return-void
.end method

.method public static syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V
    .registers 4

    .line 369
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$4;

    invoke-direct {v1, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$4;-><init>(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 425
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 426
    return-void
.end method
