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

    .line 42
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 15

    .line 46
    const-string v0, "armeabi-v7a"

    const-string v1, "arm64-v8a"

    const-string v2, ""

    :try_start_6
    new-instance v3, Ljava/net/URL;

    const-string v4, "https://api.github.com/repos/anpham14121993-cloud/StreamSync/releases/latest"

    invoke-direct {v3, v4}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 47
    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v3

    check-cast v3, Ljava/net/HttpURLConnection;

    .line 48
    const-string v4, "GET"

    invoke-virtual {v3, v4}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 49
    const-string v4, "Accept"

    const-string v5, "application/vnd.github.v3+json"

    invoke-virtual {v3, v4, v5}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 50
    const-string v4, "User-Agent"

    const-string v5, "StreamSync-Updater"

    invoke-virtual {v3, v4, v5}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 51
    const/16 v4, 0x1f40

    invoke-virtual {v3, v4}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 52
    invoke-virtual {v3, v4}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 54
    invoke-virtual {v3}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v4

    .line 55
    const/16 v5, 0xc8

    if-ne v4, v5, :cond_136

    .line 56
    invoke-virtual {v3}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v3

    invoke-static {v3}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smreadStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v3

    .line 57
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 58
    const-string v3, "tag_name"

    invoke-virtual {v4, v3, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    .line 59
    const-string v5, "body"

    invoke-virtual {v4, v5, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    .line 60
    const-string v5, "assets"

    invoke-virtual {v4, v5}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v4

    .line 62
    const-string v5, "v1.9.0"

    invoke-static {v3, v5}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smisNewerVersion(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_129

    .line 64
    nop

    .line 65
    nop

    .line 67
    nop

    .line 68
    sget-object v5, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;
    :try_end_65
    .catchall {:try_start_6 .. :try_end_65} :catchall_143

    const-string v6, "universal"

    const/4 v7, 0x0

    if-eqz v5, :cond_8b

    :try_start_6a
    sget-object v5, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;

    array-length v5, v5

    if-lez v5, :cond_8b

    .line 69
    sget-object v5, Landroid/os/Build;->SUPPORTED_ABIS:[Ljava/lang/String;

    array-length v8, v5

    const/4 v9, 0x0

    :goto_73
    if-ge v9, v8, :cond_8b

    aget-object v10, v5, v9

    .line 70
    invoke-virtual {v1, v10}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_80

    .line 71
    nop

    .line 72
    move-object v0, v1

    goto :goto_8c

    .line 73
    :cond_80
    invoke-virtual {v0, v10}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v10

    if-eqz v10, :cond_88

    .line 74
    nop

    .line 75
    goto :goto_8c

    .line 69
    :cond_88
    add-int/lit8 v9, v9, 0x1

    goto :goto_73

    .line 80
    :cond_8b
    move-object v0, v6

    :goto_8c
    if-eqz v4, :cond_112

    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-lez v1, :cond_112

    .line 82
    const/4 v1, 0x0

    :goto_95
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v5
    :try_end_99
    .catchall {:try_start_6a .. :try_end_99} :catchall_143

    const-string v8, "browser_download_url"

    const-string v9, ".apk"

    const-string v10, "name"

    if-ge v1, v5, :cond_be

    .line 83
    :try_start_a1
    invoke-virtual {v4, v1}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v5

    .line 84
    invoke-virtual {v5, v10, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 85
    invoke-virtual {v11, v9}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_bb

    invoke-virtual {v11, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v12

    if-eqz v12, :cond_bb

    .line 86
    invoke-virtual {v5, v8, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 87
    nop

    .line 88
    goto :goto_c0

    .line 82
    :cond_bb
    add-int/lit8 v1, v1, 0x1

    goto :goto_95

    :cond_be
    move-object v0, v2

    move-object v11, v0

    .line 92
    :goto_c0
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_eb

    .line 93
    const/4 v1, 0x0

    :goto_c7
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v5

    if-ge v1, v5, :cond_eb

    .line 94
    invoke-virtual {v4, v1}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v5

    .line 95
    invoke-virtual {v5, v10, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    .line 96
    invoke-virtual {v12, v9}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v13

    if-eqz v13, :cond_e8

    invoke-virtual {v12, v6}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v13

    if-eqz v13, :cond_e8

    .line 97
    invoke-virtual {v5, v8, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 98
    nop

    .line 99
    move-object v11, v12

    goto :goto_eb

    .line 93
    :cond_e8
    add-int/lit8 v1, v1, 0x1

    goto :goto_c7

    .line 104
    :cond_eb
    :goto_eb
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_10f

    .line 105
    nop

    :goto_f2
    invoke-virtual {v4}, Lorg/json/JSONArray;->length()I

    move-result v1

    if-ge v7, v1, :cond_10f

    .line 106
    invoke-virtual {v4, v7}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object v1

    .line 107
    invoke-virtual {v1, v10, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 108
    invoke-virtual {v5, v9}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_10c

    .line 109
    invoke-virtual {v1, v8, v2}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 110
    nop

    .line 111
    goto :goto_113

    .line 105
    :cond_10c
    add-int/lit8 v7, v7, 0x1

    goto :goto_f2

    .line 117
    :cond_10f
    move-object v2, v0

    move-object v5, v11

    goto :goto_113

    :cond_112
    move-object v5, v2

    :goto_113
    nop

    .line 118
    invoke-virtual {v5}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_11c

    const-string v5, "StreamSync-update.apk"

    .line 120
    :cond_11c
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;

    invoke-direct {v1, p0, v3, v2, v5}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 126
    goto :goto_135

    .line 127
    :cond_129
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 134
    :goto_135
    goto :goto_142

    .line 135
    :cond_136
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$3;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$3;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_142
    .catchall {:try_start_a1 .. :try_end_142} :catchall_143

    .line 149
    :goto_142
    goto :goto_150

    .line 142
    :catchall_143
    move-exception v0

    .line 143
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1$4;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1$4;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 150
    :goto_150
    return-void
.end method
