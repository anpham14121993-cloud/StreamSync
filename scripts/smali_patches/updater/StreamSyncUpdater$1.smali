.class Lcom/streamsync/app/updater/StreamSyncUpdater$1;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater;->checkUpdate(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .registers 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 52
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 16

    .line 56
    const-string v0, "armeabi-v7a"

    const-string v1, "arm64-v8a"

    const-string v2, ""

    :try_start_6
    iget-object v3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    # invokes: Lcom/streamsync/app/updater/StreamSyncUpdater;->getAppVersion(Landroid/content/Context;)Ljava/lang/String;
    invoke-static {v3}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$000(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v7

    .line 57
    new-instance v3, Ljava/net/URL;

    const-string v4, "https://api.github.com/repos/anpham14121993-cloud/StreamSync/releases/latest"

    invoke-direct {v3, v4}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 58
    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v3

    check-cast v3, Ljava/net/HttpURLConnection;

    .line 59
    const-string v4, "GET"

    invoke-virtual {v3, v4}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 60
    const-string v4, "Accept"

    const-string v5, "application/vnd.github.v3+json"

    invoke-virtual {v3, v4, v5}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 61
    const-string v4, "User-Agent"

    const-string v5, "StreamSync-Updater"

    invoke-virtual {v3, v4, v5}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 62
    const/16 v4, 0x1f40

    invoke-virtual {v3, v4}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 63
    invoke-virtual {v3, v4}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 65
    invoke-virtual {v3}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    .line 66
    const/16 v5, 0xc8

    if-ne v4, v5, :cond_141

    .line 67
    invoke-virtual {v3}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v3

    # invokes: Lcom/streamsync/app/updater/StreamSyncUpdater;->readStream(Ljava/io/InputStream;)Ljava/lang/String;
    invoke-static {v3}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$100(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    .line 68
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 69
    const-string v3, "tag_name"

    invoke-virtual {v4, v3, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 70
    const-string v3, "body"

    invoke-virtual {v4, v3, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    .line 71
    const-string v3, "assets"

    invoke-virtual {v4, v3}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    .line 74
    nop

    .line 75
    nop

    .line 77
    nop

    .line 78
    sget-object v4, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;
    :try_end_63
    .catchall {:try_start_6 .. :try_end_63} :catchall_14e

    const-string v5, "universal"

    const/4 v8, 0x0

    if-eqz v4, :cond_89

    :try_start_68
    sget-object v4, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;

    array-length v4, v4

    if-lez v4, :cond_89

    .line 79
    sget-object v4, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;

    array-length v9, v4

    const/4 v10, 0x0

    :goto_71
    if-ge v10, v9, :cond_89

    aget-object v11, v4, v10

    .line 80
    invoke-virtual {v1, v11}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_7e

    .line 81
    nop

    .line 82
    move-object v0, v1

    goto :goto_8a

    .line 83
    :cond_7e
    invoke-virtual {v0, v11}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_86

    .line 84
    nop

    .line 85
    goto :goto_8a

    .line 79
    :cond_86
    add-int/lit8 v10, v10, 0x1

    goto :goto_71

    .line 90
    :cond_89
    move-object v0, v5

    :goto_8a
    if-eqz v3, :cond_112

    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-lez v1, :cond_112

    .line 92
    const/4 v1, 0x0

    :goto_93
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v4
    :try_end_97
    .catchall {:try_start_68 .. :try_end_97} :catchall_14e

    const-string v9, "browser_download_url"

    const-string v10, ".apk"

    const-string v11, "name"

    if-ge v1, v4, :cond_bc

    .line 93
    :try_start_9f
    invoke-virtual {v3, v1}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v4

    .line 94
    invoke-virtual {v4, v11, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    .line 95
    invoke-virtual {v12, v10}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v13

    if-eqz v13, :cond_b9

    invoke-virtual {v12, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v13

    if-eqz v13, :cond_b9

    .line 96
    invoke-virtual {v4, v9, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 97
    nop

    .line 98
    goto :goto_be

    .line 92
    :cond_b9
    add-int/lit8 v1, v1, 0x1

    goto :goto_93

    :cond_bc
    move-object v0, v2

    move-object v12, v0

    .line 102
    :goto_be
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_e9

    .line 103
    const/4 v1, 0x0

    :goto_c5
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v4

    if-ge v1, v4, :cond_e9

    .line 104
    invoke-virtual {v3, v1}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v4

    .line 105
    invoke-virtual {v4, v11, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13

    .line 106
    invoke-virtual {v13, v10}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v14

    if-eqz v14, :cond_e6

    invoke-virtual {v13, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v14

    if-eqz v14, :cond_e6

    .line 107
    invoke-virtual {v4, v9, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 108
    nop

    .line 109
    move-object v12, v13

    goto :goto_e9

    .line 103
    :cond_e6
    add-int/lit8 v1, v1, 0x1

    goto :goto_c5

    .line 114
    :cond_e9
    :goto_e9
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_10f

    .line 115
    nop

    :goto_f0
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-ge v8, v1, :cond_10f

    .line 116
    invoke-virtual {v3, v8}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v1

    .line 117
    invoke-virtual {v1, v11, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 118
    invoke-virtual {v4, v10}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_10c

    .line 119
    invoke-virtual {v1, v9, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 120
    nop

    .line 121
    move-object v8, v2

    move-object v2, v4

    goto :goto_113

    .line 115
    :cond_10c
    add-int/lit8 v8, v8, 0x1

    goto :goto_f0

    .line 127
    :cond_10f
    move-object v8, v0

    move-object v2, v12

    goto :goto_113

    :cond_112
    move-object v8, v2

    :goto_113
    nop

    .line 128
    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_11e

    const-string v0, "StreamSync-update.apk"

    move-object v9, v0

    goto :goto_11f

    :cond_11e
    move-object v9, v2

    .line 130
    :goto_11f
    # invokes: Lcom/streamsync/app/updater/StreamSyncUpdater;->isNewerVersion(Ljava/lang/String;Ljava/lang/String;)Z
    invoke-static {v6, v7}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$200(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_132

    .line 131
    # getter for: Lcom/streamsync/app/updater/StreamSyncUpdater;->sMainHandler:Landroid/os/Handler;
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$400()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;

    invoke-direct {v1, p0, v6, v8, v9}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    goto :goto_140

    .line 138
    :cond_132
    # getter for: Lcom/streamsync/app/updater/StreamSyncUpdater;->sMainHandler:Landroid/os/Handler;
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$400()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;

    move-object v4, v1

    move-object v5, p0

    invoke-direct/range {v4 .. v9}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 145
    :goto_140
    goto :goto_14d

    .line 146
    :cond_141
    # getter for: Lcom/streamsync/app/updater/StreamSyncUpdater;->sMainHandler:Landroid/os/Handler;
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$400()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$3;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$3;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_14d
    .catchall {:try_start_9f .. :try_end_14d} :catchall_14e

    .line 160
    :goto_14d
    goto :goto_15b

    .line 153
    :catchall_14e
    move-exception v0

    .line 154
    # getter for: Lcom/streamsync/app/updater/StreamSyncUpdater;->sMainHandler:Landroid/os/Handler;
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$400()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$4;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$4;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 161
    :goto_15b
    return-void
.end method
