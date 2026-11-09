.class public Lcom/streamsync/app/auth/SupabaseSyncService;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;,
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

    .line 41
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 26
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;
    .registers 4

    .line 71
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 72
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const-string v1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    if-eqz v0, :cond_d

    return-object v1

    .line 74
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

    .line 75
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object p0

    check-cast p0, Ljava/net/HttpURLConnection;

    .line 76
    const-string v0, "GET"

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 77
    const/16 v0, 0x1770

    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 78
    invoke-virtual {p0, v0}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 79
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v0

    .line 80
    const/16 v2, 0xc8

    if-ne v0, v2, :cond_5d

    .line 81
    invoke-virtual {p0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object p0

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    .line 82
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 83
    const-string p0, "publishable_key"

    const-string v2, ""

    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 84
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0
    :try_end_59
    .catchall {:try_start_d .. :try_end_59} :catchall_5c

    if-nez v0, :cond_5d

    .line 85
    return-object p0

    .line 88
    :catchall_5c
    move-exception p0

    :cond_5d
    nop

    .line 89
    return-object v1
.end method

.method public static installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I
    .registers 26
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)I"
        }
    .end annotation

    .line 507
    const-string v1, "description"

    const-string v2, "version"

    const-string v3, "name"

    const-string v4, ""

    const-string v5, "id"

    const-string v6, "/manifest.json"

    .line 508
    const-string v0, "streamsync.db"

    move-object/from16 v7, p0

    invoke-virtual {v7, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 509
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v7

    const/4 v8, 0x0

    if-nez v7, :cond_1c

    return v8

    .line 511
    :cond_1c
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    const/4 v7, 0x0

    invoke-static {v0, v7, v8}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v9

    .line 513
    :try_start_25
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    .line 514
    const/4 v10, 0x0

    const/4 v11, 0x0

    :goto_2a
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->size()I

    move-result v0

    if-ge v10, v0, :cond_21b

    .line 515
    move-object/from16 v12, p1

    invoke-interface {v12, v10}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 516
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v13

    if-eqz v13, :cond_4d

    move-object/from16 v16, v4

    move/from16 v19, v10

    move/from16 v17, v11

    const/4 v4, 0x0

    move-object v10, v7

    move-object v7, v1

    goto/16 :goto_20f

    .line 518
    :cond_4d
    nop

    .line 519
    invoke-virtual {v0, v6}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v13
    :try_end_52
    .catchall {:try_start_25 .. :try_end_52} :catchall_228

    const-string v14, "/"

    if-nez v13, :cond_71

    .line 520
    :try_start_56
    invoke-virtual {v0, v14}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v13

    if-eqz v13, :cond_5f

    const-string v13, "manifest.json"

    goto :goto_60

    :cond_5f
    move-object v13, v6

    :goto_60
    new-instance v15, Ljava/lang/StringBuilder;

    invoke-direct {v15}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v15, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_71
    .catchall {:try_start_56 .. :try_end_71} :catchall_228

    .line 524
    :cond_71
    :try_start_71
    new-instance v13, Ljava/net/URL;

    invoke-direct {v13, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 525
    invoke-virtual {v13}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v13

    check-cast v13, Ljava/net/HttpURLConnection;

    .line 526
    const-string v15, "GET"

    invoke-virtual {v13, v15}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 527
    const/16 v15, 0x1770

    invoke-virtual {v13, v15}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 528
    invoke-virtual {v13, v15}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 530
    invoke-virtual {v13}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v15

    const/16 v8, 0xc8

    if-ne v15, v8, :cond_1fb

    .line 531
    invoke-virtual {v13}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v8

    invoke-static {v8}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v8

    .line 532
    new-instance v13, Lorg/json/JSONObject;

    invoke-direct {v13, v8}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 534
    invoke-virtual {v13, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 535
    const-string v15, "Addon"

    invoke-virtual {v13, v3, v15}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v15
    :try_end_a8
    .catchall {:try_start_71 .. :try_end_a8} :catchall_205

    .line 536
    :try_start_a8
    const-string v7, "1.0.0"

    invoke-virtual {v13, v2, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 537
    invoke-virtual {v13, v1, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12
    :try_end_b2
    .catchall {:try_start_a8 .. :try_end_b2} :catchall_1f0

    .line 538
    move-object/from16 v16, v4

    :try_start_b4
    const-string v4, "logo"
    :try_end_b6
    .catchall {:try_start_b4 .. :try_end_b6} :catchall_1ed

    move/from16 v17, v11

    const/4 v11, 0x0

    :try_start_b9
    invoke-virtual {v13, v4, v11}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4
    :try_end_bd
    .catchall {:try_start_b9 .. :try_end_bd} :catchall_1e6

    .line 540
    :try_start_bd
    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V
    :try_end_c2
    .catchall {:try_start_bd .. :try_end_c2} :catchall_1e1

    .line 541
    move-object/from16 v18, v9

    :try_start_c4
    const-string v9, "types"

    invoke-virtual {v13, v9}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v9
    :try_end_ca
    .catchall {:try_start_c4 .. :try_end_ca} :catchall_1da

    .line 542
    move/from16 v19, v10

    const-string v10, ","

    if-eqz v9, :cond_f0

    .line 543
    move-object/from16 v20, v4

    const/4 v4, 0x0

    :goto_d3
    move-object/from16 v21, v1

    :try_start_d5
    invoke-virtual {v9}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-ge v4, v1, :cond_f4

    .line 544
    invoke-virtual {v11}, Ljava/lang/StringBuilder;->length()I

    move-result v1

    if-lez v1, :cond_e4

    invoke-virtual {v11, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 545
    :cond_e4
    invoke-virtual {v9, v4}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v11, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 543
    add-int/lit8 v4, v4, 0x1

    move-object/from16 v1, v21

    goto :goto_d3

    .line 542
    :cond_f0
    move-object/from16 v21, v1

    move-object/from16 v20, v4

    .line 549
    :cond_f4
    const-string v1, "catalogs"

    invoke-virtual {v13, v1}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v1
    :try_end_fa
    .catchall {:try_start_d5 .. :try_end_fa} :catchall_1d4

    .line 550
    const-string v4, "[]"

    if-eqz v1, :cond_103

    :try_start_fe
    invoke-virtual {v1}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v1

    goto :goto_104

    :cond_103
    move-object v1, v4

    .line 552
    :goto_104
    const-string v9, "resources"

    invoke-virtual {v13, v9}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v9

    .line 553
    if-eqz v9, :cond_110

    invoke-virtual {v9}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object v4

    .line 555
    :cond_110
    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    .line 556
    move-object/from16 v22, v4

    const-string v4, "idPrefixes"

    invoke-virtual {v13, v4}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 557
    if-eqz v4, :cond_13d

    .line 558
    const/4 v13, 0x0

    :goto_120
    move-object/from16 v23, v1

    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-ge v13, v1, :cond_13f

    .line 559
    invoke-virtual {v9}, Ljava/lang/StringBuilder;->length()I

    move-result v1

    if-lez v1, :cond_131

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 560
    :cond_131
    invoke-virtual {v4, v13}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 558
    add-int/lit8 v13, v13, 0x1

    move-object/from16 v1, v23

    goto :goto_120

    .line 557
    :cond_13d
    move-object/from16 v23, v1

    .line 564
    :cond_13f
    nop

    .line 565
    invoke-virtual {v0, v6}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_151

    .line 566
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    add-int/lit8 v1, v1, -0xe

    const/4 v4, 0x0

    invoke-virtual {v0, v4, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    .line 568
    :cond_151
    :goto_151
    invoke-virtual {v0, v14}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1
    :try_end_155
    .catchall {:try_start_fe .. :try_end_155} :catchall_1d4

    if-eqz v1, :cond_166

    .line 569
    :try_start_157
    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1
    :try_end_15b
    .catchall {:try_start_157 .. :try_end_15b} :catchall_163

    add-int/lit8 v1, v1, -0x1

    const/4 v4, 0x0

    :try_start_15e
    invoke-virtual {v0, v4, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    goto :goto_151

    .line 592
    :catchall_163
    move-exception v0

    const/4 v4, 0x0

    goto :goto_1cf

    .line 572
    :cond_166
    const/4 v4, 0x0

    new-instance v1, Landroid/content/ContentValues;

    invoke-direct {v1}, Landroid/content/ContentValues;-><init>()V

    .line 573
    const-string v10, "baseUrl"

    invoke-virtual {v1, v10, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 574
    invoke-virtual {v1, v5, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 575
    invoke-virtual {v1, v3, v15}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 576
    invoke-virtual {v1, v2, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_17a
    .catchall {:try_start_15e .. :try_end_17a} :catchall_1ce

    .line 577
    move-object/from16 v7, v21

    :try_start_17c
    invoke-virtual {v1, v7, v12}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 578
    const-string v0, "typesCsv"

    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v1, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 579
    const-string v0, "catalogsJson"

    move-object/from16 v8, v23

    invoke-virtual {v1, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 580
    const-string v0, "resourcesJson"

    move-object/from16 v8, v22

    invoke-virtual {v1, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 581
    const-string v0, "idPrefixesCsv"

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v1, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_19f
    .catchall {:try_start_17c .. :try_end_19f} :catchall_1ca

    .line 582
    const-string v0, "logoUrl"

    if-eqz v20, :cond_1af

    :try_start_1a3
    invoke-virtual/range {v20 .. v20}, Ljava/lang/String;->isEmpty()Z

    move-result v8

    if-nez v8, :cond_1af

    .line 583
    move-object/from16 v8, v20

    invoke-virtual {v1, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1b2

    .line 585
    :cond_1af
    invoke-virtual {v1, v0}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 587
    :goto_1b2
    const-string v0, "sortOrder"

    invoke-static/range {v19 .. v19}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v1, v0, v8}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 589
    const-string v0, "installed_addons"
    :try_end_1bd
    .catchall {:try_start_1a3 .. :try_end_1bd} :catchall_1ca

    const/4 v8, 0x5

    move-object/from16 v9, v18

    const/4 v10, 0x0

    :try_start_1c1
    invoke-virtual {v9, v0, v10, v1, v8}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    :try_end_1c4
    .catchall {:try_start_1c1 .. :try_end_1c4} :catchall_1c7

    .line 590
    add-int/lit8 v11, v17, 0x1

    goto :goto_204

    .line 592
    :catchall_1c7
    move-exception v0

    goto/16 :goto_20f

    :catchall_1ca
    move-exception v0

    move-object/from16 v9, v18

    goto :goto_1f9

    :catchall_1ce
    move-exception v0

    :goto_1cf
    move-object/from16 v9, v18

    move-object/from16 v7, v21

    goto :goto_1f9

    :catchall_1d4
    move-exception v0

    move-object/from16 v9, v18

    move-object/from16 v7, v21

    goto :goto_1f8

    :catchall_1da
    move-exception v0

    move-object v7, v1

    move/from16 v19, v10

    move-object/from16 v9, v18

    goto :goto_1f8

    :catchall_1e1
    move-exception v0

    move-object v7, v1

    move/from16 v19, v10

    goto :goto_1f8

    :catchall_1e6
    move-exception v0

    move-object v7, v1

    move/from16 v19, v10

    move-object v10, v11

    const/4 v4, 0x0

    goto :goto_20f

    :catchall_1ed
    move-exception v0

    move-object v7, v1

    goto :goto_1f4

    :catchall_1f0
    move-exception v0

    move-object v7, v1

    move-object/from16 v16, v4

    :goto_1f4
    move/from16 v19, v10

    move/from16 v17, v11

    :goto_1f8
    const/4 v4, 0x0

    :goto_1f9
    const/4 v10, 0x0

    goto :goto_20f

    .line 530
    :cond_1fb
    move-object/from16 v16, v4

    move/from16 v19, v10

    move/from16 v17, v11

    const/4 v4, 0x0

    move-object v10, v7

    move-object v7, v1

    .line 592
    :goto_204
    goto :goto_211

    :catchall_205
    move-exception v0

    move-object/from16 v16, v4

    move/from16 v19, v10

    move/from16 v17, v11

    const/4 v4, 0x0

    move-object v10, v7

    move-object v7, v1

    .line 514
    :goto_20f
    move/from16 v11, v17

    :goto_211
    add-int/lit8 v0, v19, 0x1

    move-object v1, v7

    move-object v7, v10

    move-object/from16 v4, v16

    const/4 v8, 0x0

    move v10, v0

    goto/16 :goto_2a

    .line 594
    :cond_21b
    move/from16 v17, v11

    :try_start_21d
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_220
    .catchall {:try_start_21d .. :try_end_220} :catchall_228

    .line 596
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 597
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 598
    nop

    .line 599
    return v17

    .line 596
    :catchall_228
    move-exception v0

    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 597
    invoke-virtual {v9}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 598
    goto :goto_231

    :goto_230
    throw v0

    :goto_231
    goto :goto_230
.end method

.method public static login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 13

    .line 93
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

    .line 180
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 181
    return-void
.end method

.method public static normalizeUrl(Ljava/lang/String;)Ljava/lang/String;
    .registers 3

    .line 59
    if-nez p0, :cond_5

    const-string p0, ""

    return-object p0

    .line 60
    :cond_5
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 61
    :goto_9
    const-string v0, "/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1d

    .line 62
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    const/4 v1, 0x0

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    goto :goto_9

    .line 64
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

    .line 65
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 67
    :cond_44
    return-object p0
.end method

.method public static pullAddonsFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 21

    .line 345
    move-object/from16 v1, p2

    move-object/from16 v2, p3

    const-string v3, "enabled"

    const-string v4, ""

    const-string v5, "url"

    const-string v6, "Bearer "

    const-string v7, "Authorization"

    const-string v8, "apikey"

    const-string v9, "GET"

    .line 347
    :try_start_12
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 348
    new-instance v12, Ljava/util/ArrayList;

    invoke-direct {v12}, Ljava/util/ArrayList;-><init>()V
    :try_end_1b
    .catchall {:try_start_12 .. :try_end_1b} :catchall_137

    .line 352
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

    .line 353
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 354
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 355
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 356
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v0, v7, v10}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 357
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 358
    invoke-virtual {v0, v15}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 360
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v10

    if-ne v10, v13, :cond_a1

    .line 361
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 362
    new-instance v10, Lorg/json/JSONArray;

    invoke-direct {v10, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 363
    const/4 v0, 0x0

    :goto_71
    invoke-virtual {v10}, Lorg/json/JSONArray;->length()I

    move-result v14

    if-ge v0, v14, :cond_a1

    .line 364
    invoke-virtual {v10, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v14

    .line 365
    invoke-virtual {v14, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v16

    invoke-virtual/range {v16 .. v16}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v13

    .line 366
    const/4 v15, 0x1

    invoke-virtual {v14, v3, v15}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v14

    .line 367
    if-eqz v14, :cond_99

    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_99

    invoke-interface {v12, v13}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_99

    .line 368
    invoke-interface {v12, v13}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_99
    .catchall {:try_start_1f .. :try_end_99} :catchall_a0

    .line 363
    :cond_99
    add-int/lit8 v0, v0, 0x1

    const/16 v13, 0xc8

    const/16 v15, 0x1f40

    goto :goto_71

    .line 372
    :catchall_a0
    move-exception v0

    :cond_a1
    nop

    .line 376
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

    .line 377
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 378
    invoke-virtual {v0, v9}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 379
    invoke-virtual {v0, v8, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 380
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v7, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 381
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 382
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 384
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-ne v1, v2, :cond_124

    .line 385
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 386
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 387
    const/4 v0, 0x0

    :goto_f8
    invoke-virtual {v1}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-ge v0, v2, :cond_124

    .line 388
    invoke-virtual {v1, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v2

    .line 389
    invoke-virtual {v2, v5, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 390
    const/4 v7, 0x1

    invoke-virtual {v2, v3, v7}, Lorg/json/JSONObject;->optBoolean(Ljava/lang/String;Z)Z

    move-result v2

    .line 391
    if-eqz v2, :cond_120

    invoke-virtual {v6}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_120

    invoke-interface {v12, v6}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_120

    .line 392
    invoke-interface {v12, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_120
    .catchall {:try_start_a2 .. :try_end_120} :catchall_123

    .line 387
    :cond_120
    add-int/lit8 v0, v0, 0x1

    goto :goto_f8

    .line 396
    :catchall_123
    move-exception v0

    :cond_124
    nop

    .line 398
    :try_start_125
    invoke-interface {v12}, Ljava/util/List;->isEmpty()Z

    move-result v0
    :try_end_129
    .catchall {:try_start_125 .. :try_end_129} :catchall_137

    if-eqz v0, :cond_12d

    .line 399
    const/4 v1, 0x0

    return v1

    .line 402
    :cond_12d
    const/4 v1, 0x0

    move-object/from16 v2, p0

    :try_start_130
    invoke-static {v2, v12}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result v10
    :try_end_134
    .catchall {:try_start_130 .. :try_end_134} :catchall_135

    .line 403
    goto :goto_13a

    :catchall_135
    move-exception v0

    goto :goto_139

    :catchall_137
    move-exception v0

    const/4 v1, 0x0

    :goto_139
    const/4 v10, 0x0

    .line 404
    :goto_13a
    return v10
.end method

.method public static pullWatchProgressFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    .registers 26

    .line 408
    move-object/from16 v1, p2

    move-object/from16 v2, p3

    const-string v3, "Bearer "

    const-string v4, "Authorization"

    const-string v5, "apikey"

    const-string v6, ""

    .line 410
    const/4 v7, 0x0

    :try_start_d
    invoke-static/range {p1 .. p1}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8
    :try_end_11
    .catchall {:try_start_d .. :try_end_11} :catchall_208

    .line 411
    nop

    .line 415
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

    .line 416
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 417
    const-string v12, "POST"

    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 418
    const-string v12, "Content-Type"

    const-string v13, "application/json"

    invoke-virtual {v0, v12, v13}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 419
    invoke-virtual {v0, v5, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 420
    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-virtual {v12, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v12

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v0, v4, v12}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 421
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 422
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 423
    const/4 v12, 0x1

    invoke-virtual {v0, v12}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 425
    new-instance v13, Lorg/json/JSONObject;

    invoke-direct {v13}, Lorg/json/JSONObject;-><init>()V

    .line 426
    const-string v14, "p_profile_id"

    invoke-virtual {v13, v14, v12}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    .line 427
    invoke-virtual {v13}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v12

    sget-object v13, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v12, v13}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v12

    .line 428
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v13
    :try_end_7a
    .catchall {:try_start_17 .. :try_end_7a} :catchall_a6

    .line 429
    :try_start_7a
    invoke-virtual {v13, v12}, Ljava/io/OutputStream;->write([B)V
    :try_end_7d
    .catchall {:try_start_7a .. :try_end_7d} :catchall_98

    .line 430
    if-eqz v13, :cond_82

    :try_start_7f
    invoke-virtual {v13}, Ljava/io/OutputStream;->close()V

    .line 432
    :cond_82
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v12

    if-ne v12, v9, :cond_96

    .line 433
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 434
    new-instance v12, Lorg/json/JSONArray;

    invoke-direct {v12, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
    :try_end_95
    .catchall {:try_start_7f .. :try_end_95} :catchall_a6

    goto :goto_97

    .line 432
    :cond_96
    move-object v12, v10

    .line 436
    :goto_97
    goto :goto_a8

    .line 428
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

    .line 436
    :catchall_a6
    move-exception v0

    move-object v12, v10

    .line 439
    :goto_a8
    if-eqz v12, :cond_b4

    :try_start_aa
    invoke-virtual {v12}, Lorg/json/JSONArray;->length()I

    move-result v0
    :try_end_ae
    .catchall {:try_start_aa .. :try_end_ae} :catchall_b1

    if-nez v0, :cond_10b

    goto :goto_b4

    .line 502
    :catchall_b1
    move-exception v0

    goto/16 :goto_20b

    .line 441
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

    .line 442
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 443
    const-string v8, "GET"

    invoke-virtual {v0, v8}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 444
    invoke-virtual {v0, v5, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 445
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v4, v1}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 446
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 447
    invoke-virtual {v0, v11}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 449
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    if-ne v1, v9, :cond_108

    .line 450
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v0

    .line 451
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1, v0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
    :try_end_107
    .catchall {:try_start_b4 .. :try_end_107} :catchall_109

    move-object v12, v1

    .line 453
    :cond_108
    :goto_108
    goto :goto_10b

    :catchall_109
    move-exception v0

    goto :goto_108

    .line 456
    :cond_10b
    :goto_10b
    if-eqz v12, :cond_206

    :try_start_10d
    invoke-virtual {v12}, Lorg/json/JSONArray;->length()I

    move-result v0

    if-nez v0, :cond_115

    goto/16 :goto_206

    .line 460
    :cond_115
    const-string v0, "streamsync.db"

    move-object/from16 v1, p0

    invoke-virtual {v1, v0}, Landroid/content/Context;->getDatabasePath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    .line 461
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_124

    return v7

    .line 463
    :cond_124
    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v10, v7}, Landroid/database/sqlite/SQLiteDatabase;->openDatabase(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1
    :try_end_12c
    .catchall {:try_start_10d .. :try_end_12c} :catchall_204

    .line 465
    :try_start_12c
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V
    :try_end_12f
    .catchall {:try_start_12c .. :try_end_12f} :catchall_1fb

    .line 466
    const/4 v0, 0x0

    const/4 v2, 0x0

    :goto_131
    :try_start_131
    invoke-virtual {v12}, Lorg/json/JSONArray;->length()I

    move-result v3

    if-ge v0, v3, :cond_1ea

    .line 467
    invoke-virtual {v12, v0}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    .line 468
    const-string v4, "contentId"

    invoke-virtual {v3, v4, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 469
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_14d

    const-string v4, "videoId"

    invoke-virtual {v3, v4, v6}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 470
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

    .line 472
    :cond_159
    const-string v5, "position"

    const-wide/16 v13, 0x0

    invoke-virtual {v3, v5, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15
    :try_end_161
    .catchall {:try_start_155 .. :try_end_161} :catchall_1f8

    .line 473
    const-string v5, "positionMs"

    cmp-long v9, v15, v13

    if-nez v9, :cond_16b

    :try_start_167
    invoke-virtual {v3, v5, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v15

    .line 475
    :cond_16b
    const-string v9, "duration"

    invoke-virtual {v3, v9, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v17
    :try_end_171
    .catchall {:try_start_167 .. :try_end_171} :catchall_1f8

    .line 476
    const-string v9, "durationMs"

    cmp-long v11, v17, v13

    if-nez v11, :cond_17b

    :try_start_177
    invoke-virtual {v3, v9, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v17

    .line 478
    :cond_17b
    const-string v11, "lastWatched"

    invoke-virtual {v3, v11, v13, v14}, Lorg/json/JSONObject;->optLong(Ljava/lang/String;J)J

    move-result-wide v19
    :try_end_181
    .catchall {:try_start_177 .. :try_end_181} :catchall_1f8

    .line 479
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

    .line 481
    :goto_194
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1e5

    cmp-long v3, v17, v13

    if-lez v3, :cond_1e5

    .line 482
    new-instance v3, Landroid/content/ContentValues;

    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    .line 483
    move-object/from16 v7, p0

    invoke-virtual {v3, v7, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 484
    invoke-static/range {v15 .. v16}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v5, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 485
    invoke-static/range {v17 .. v18}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v9, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 486
    invoke-static/range {v19 .. v20}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v11, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    .line 487
    const-string v4, "deviceId"

    const-string v5, "streamsync"

    invoke-virtual {v3, v4, v5}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 488
    const-string v4, "pendingSync"

    const/4 v5, 0x0

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    invoke-virtual {v3, v4, v7}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    .line 489
    const-string v4, "streamUrl"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 490
    const-string v4, "addonId"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 491
    const-string v4, "addonName"

    invoke-virtual {v3, v4}, Landroid/content/ContentValues;->putNull(Ljava/lang/String;)V

    .line 493
    const-string v4, "watch_progress"

    const/4 v5, 0x5

    invoke-virtual {v1, v4, v10, v3, v5}, Landroid/database/sqlite/SQLiteDatabase;->insertWithOnConflict(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J

    .line 494
    add-int/lit8 v2, v2, 0x1

    .line 466
    :cond_1e5
    add-int/lit8 v0, v0, 0x1

    const/4 v7, 0x0

    goto/16 :goto_131

    .line 497
    :cond_1ea
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_1ed
    .catchall {:try_start_189 .. :try_end_1ed} :catchall_1f8

    .line 499
    :try_start_1ed
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    .line 500
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V
    :try_end_1f3
    .catchall {:try_start_1ed .. :try_end_1f3} :catchall_1f5

    .line 501
    nop

    .line 502
    goto :goto_20c

    :catchall_1f5
    move-exception v0

    move v7, v2

    goto :goto_20b

    .line 499
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

    .line 500
    invoke-virtual {v1}, Landroid/database/sqlite/SQLiteDatabase;->close()V

    .line 501
    throw v0
    :try_end_204
    .catchall {:try_start_1fd .. :try_end_204} :catchall_b1

    .line 502
    :catchall_204
    move-exception v0

    goto :goto_20a

    .line 457
    :cond_206
    :goto_206
    const/4 v1, 0x0

    return v1

    .line 502
    :catchall_208
    move-exception v0

    const/4 v1, 0x0

    :goto_20a
    const/4 v7, 0x0

    :goto_20b
    move v2, v7

    .line 503
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

    .line 645
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 646
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 648
    :goto_11
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 649
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_11

    .line 651
    :cond_21
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    .line 652
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static refreshRoom(Landroid/content/Context;)V
    .registers 8

    .line 629
    :try_start_0
    const-string v0, "com.streamsync.app.data.local.AppDatabase"

    invoke-static {v0}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v0

    .line 630
    const-string v1, "Companion"

    invoke-virtual {v0, v1}, Ljava/lang/Class;->getField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 631
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

    .line 632
    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    new-array v2, v3, [Ljava/lang/Object;

    aput-object p0, v2, v6

    invoke-virtual {v1, v0, v2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 633
    if-eqz p0, :cond_58

    .line 634
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "getInvalidationTracker"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 635
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 636
    if-eqz p0, :cond_58

    .line 637
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    const-string v1, "refreshVersionsAsync"

    new-array v2, v6, [Ljava/lang/Class;

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    .line 638
    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {v0, p0, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_56
    .catchall {:try_start_0 .. :try_end_56} :catchall_57

    goto :goto_58

    .line 641
    :catchall_57
    move-exception p0

    :cond_58
    :goto_58
    nop

    .line 642
    return-void
.end method

.method public static restoreDefaultNuvioAddons(Landroid/content/Context;)V
    .registers 3

    .line 603
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$5;

    invoke-direct {v1, p0}, Lcom/streamsync/app/auth/SupabaseSyncService$5;-><init>(Landroid/content/Context;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 624
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 625
    return-void
.end method

.method public static runOnMain(Ljava/lang/Runnable;)V
    .registers 2

    .line 44
    sget-object v0, Lcom/streamsync/app/auth/SupabaseSyncService;->sMainHandler:Landroid/os/Handler;

    invoke-virtual {v0, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 45
    return-void
.end method

.method public static showToast(Landroid/content/Context;Ljava/lang/String;)V
    .registers 3

    .line 48
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$1;

    invoke-direct {v0, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$1;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 56
    return-void
.end method

.method public static signUp(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 13

    .line 184
    new-instance v0, Ljava/lang/Thread;

    new-instance v7, Lcom/streamsync/app/auth/SupabaseSyncService$3;

    move-object v1, v7

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    move-object v5, p0

    move-object v6, p4

    invoke-direct/range {v1 .. v6}, Lcom/streamsync/app/auth/SupabaseSyncService$3;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V

    invoke-direct {v0, v7}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 281
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 282
    return-void
.end method

.method public static syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V
    .registers 4

    .line 285
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$4;

    invoke-direct {v1, p0, p1}, Lcom/streamsync/app/auth/SupabaseSyncService$4;-><init>(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 341
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 342
    return-void
.end method
