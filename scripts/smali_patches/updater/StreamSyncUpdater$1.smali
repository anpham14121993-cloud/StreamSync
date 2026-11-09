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

    .line 24
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 8

    .line 28
    :try_start_0
    new-instance v0, Ljava/net/URL;

    const-string v1, "https://api.github.com/repos/anpham14121993-cloud/StreamSync/releases/latest"

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 29
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 30
    const-string v1, "GET"

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 31
    const-string v1, "User-Agent"

    const-string v2, "StreamSync-Updater"

    invoke-virtual {v0, v1, v2}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 32
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 33
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 35
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    .line 36
    const/16 v2, 0xc8

    if-ne v1, v2, :cond_b6

    .line 37
    new-instance v1, Ljava/io/BufferedReader;

    new-instance v2, Ljava/io/InputStreamReader;

    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v1, v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 38
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 40
    :goto_3c
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_46

    .line 41
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_3c

    .line 43
    :cond_46
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    .line 45
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 46
    const-string v1, "v1.8.9"

    .line 47
    const-string v2, "\"tag_name\":\""

    invoke-virtual {v0, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v2
    :try_end_55
    .catchall {:try_start_0 .. :try_end_55} :catchall_bc

    .line 48
    const-string v3, "\""

    if-ltz v2, :cond_65

    .line 49
    add-int/lit8 v2, v2, 0xc

    .line 50
    :try_start_5b
    invoke-virtual {v0, v3, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I

    move-result v4

    .line 51
    if-le v4, v2, :cond_65

    .line 52
    invoke-virtual {v0, v2, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    .line 56
    :cond_65
    const-string v2, "1.8.9"
    :try_end_67
    .catchall {:try_start_5b .. :try_end_67} :catchall_bc

    .line 58
    :try_start_67
    iget-object v4, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-virtual {v4}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v4

    iget-object v5, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-virtual {v5}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v5

    const/4 v6, 0x0

    invoke-virtual {v4, v5, v6}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v4

    iget-object v2, v4, Landroid/content/pm/PackageInfo;->versionName:Ljava/lang/String;
    :try_end_7a
    .catchall {:try_start_67 .. :try_end_7a} :catchall_7b

    goto :goto_7c

    .line 59
    :catchall_7b
    move-exception v4

    :goto_7c
    nop

    .line 61
    :try_start_7d
    invoke-virtual {v1, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_b0

    const-string v4, "1.8.8"

    invoke-virtual {v1, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_b0

    const-string v4, "0.1.88"

    invoke-virtual {v1, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_94

    goto :goto_b0

    .line 66
    :cond_94
    const-string v2, "https://github.com/anpham14121993-cloud/StreamSync/releases/latest"

    .line 67
    const-string v4, "\"browser_download_url\":\""

    invoke-virtual {v0, v4}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v4

    .line 68
    if-ltz v4, :cond_aa

    .line 69
    add-int/lit8 v4, v4, 0x18

    .line 70
    invoke-virtual {v0, v3, v4}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I

    move-result v3

    .line 71
    if-le v3, v4, :cond_aa

    .line 72
    invoke-virtual {v0, v4, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v2

    .line 76
    :cond_aa
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-static {v0, v1, v2}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smshowUpdateDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    .line 77
    goto :goto_bb

    .line 62
    :cond_b0
    :goto_b0
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-static {v0, v2}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smshowLatestDialog(Landroid/content/Context;Ljava/lang/String;)V

    .line 63
    return-void

    .line 78
    :cond_b6
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smshowErrorDialog(Landroid/content/Context;)V
    :try_end_bb
    .catchall {:try_start_7d .. :try_end_bb} :catchall_bc

    .line 82
    :goto_bb
    goto :goto_c2

    .line 80
    :catchall_bc
    move-exception v0

    .line 81
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smshowErrorDialog(Landroid/content/Context;)V

    .line 83
    :goto_c2
    return-void
.end method
