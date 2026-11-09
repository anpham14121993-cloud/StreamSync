.class Lcom/streamsync/app/updater/StreamSyncUpdater$3;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater;->startInAppDownloadAndInstall(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$activity:Landroid/app/Activity;

.field final synthetic val$downloadUrl:Ljava/lang/String;

.field final synthetic val$fileName:Ljava/lang/String;

.field final synthetic val$progressDialog:Landroid/app/ProgressDialog;


# direct methods
.method constructor <init>(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Landroid/app/ProgressDialog;)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 225
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$activity:Landroid/app/Activity;

    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$fileName:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$downloadUrl:Ljava/lang/String;

    iput-object p4, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$progressDialog:Landroid/app/ProgressDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 12

    .line 228
    nop

    .line 230
    const/4 v0, 0x0

    :try_start_2
    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$activity:Landroid/app/Activity;

    invoke-virtual {v2}, Landroid/app/Activity;->getCacheDir()Ljava/io/File;

    move-result-object v2

    const-string v3, "updates"

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 231
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_18

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 232
    :cond_18
    new-instance v2, Ljava/io/File;

    iget-object v3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$fileName:Ljava/lang/String;

    invoke-direct {v2, v1, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_1f
    .catchall {:try_start_2 .. :try_end_1f} :catchall_b9

    .line 233
    :try_start_1f
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_28

    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    .line 235
    :cond_28
    new-instance v0, Ljava/net/URL;

    iget-object v1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$downloadUrl:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 236
    invoke-virtual {v0}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 237
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setInstanceFollowRedirects(Z)V

    .line 238
    const/16 v3, 0x3a98

    invoke-virtual {v0, v3}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 239
    invoke-virtual {v0, v3}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 242
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v3

    .line 243
    const/16 v4, 0x12e

    if-eq v3, v4, :cond_55

    const/16 v4, 0x12d

    if-eq v3, v4, :cond_55

    const/16 v4, 0x133

    if-eq v3, v4, :cond_55

    const/16 v4, 0x134

    if-ne v3, v4, :cond_69

    .line 244
    :cond_55
    const-string v3, "Location"

    invoke-virtual {v0, v3}, Ljava/net/HttpURLConnection;->getHeaderField(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 245
    new-instance v3, Ljava/net/URL;

    invoke-direct {v3, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 246
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setInstanceFollowRedirects(Z)V

    .line 249
    :cond_69
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getContentLength()I

    move-result v1

    .line 250
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    .line 251
    new-instance v3, Ljava/io/FileOutputStream;

    invoke-direct {v3, v2}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 253
    const/16 v4, 0x2000

    new-array v4, v4, [B

    .line 254
    const-wide/16 v5, 0x0

    .line 256
    :cond_7c
    :goto_7c
    invoke-virtual {v0, v4}, Ljava/io/InputStream;->read([B)I

    move-result v7

    const/4 v8, -0x1

    if-eq v7, v8, :cond_9f

    .line 257
    int-to-long v8, v7

    add-long/2addr v5, v8

    .line 258
    const/4 v8, 0x0

    invoke-virtual {v3, v4, v8, v7}, Ljava/io/FileOutputStream;->write([BII)V

    .line 259
    if-lez v1, :cond_7c

    .line 260
    const-wide/16 v7, 0x64

    mul-long v7, v7, v5

    int-to-long v9, v1

    div-long/2addr v7, v9

    long-to-int v8, v7

    .line 261
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v7

    new-instance v9, Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;

    invoke-direct {v9, p0, v8}, Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$3;I)V

    invoke-virtual {v7, v9}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 267
    goto :goto_7c

    .line 270
    :cond_9f
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->flush()V

    .line 271
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->close()V

    .line 272
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V

    .line 274
    nop

    .line 275
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$3$2;

    invoke-direct {v1, p0, v2}, Lcom/streamsync/app/updater/StreamSyncUpdater$3$2;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$3;Ljava/io/File;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z
    :try_end_b5
    .catchall {:try_start_1f .. :try_end_b5} :catchall_b6

    .line 294
    goto :goto_d1

    .line 283
    :catchall_b6
    move-exception v1

    move-object v0, v2

    goto :goto_ba

    :catchall_b9
    move-exception v1

    .line 284
    :goto_ba
    if-eqz v0, :cond_c5

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_c5

    .line 285
    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    .line 287
    :cond_c5
    invoke-static {}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$sfgetsMainHandler()Landroid/os/Handler;

    move-result-object v0

    new-instance v2, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;

    invoke-direct {v2, p0, v1}, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$3;Ljava/lang/Throwable;)V

    invoke-virtual {v0, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 295
    :goto_d1
    return-void
.end method
