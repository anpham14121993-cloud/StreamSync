.class public Lcom/streamsync/app/updater/StreamSyncUpdater;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"


# direct methods
.method static bridge synthetic -$$Nest$smshowErrorDialog(Landroid/content/Context;)V
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->showErrorDialog(Landroid/content/Context;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smshowLatestDialog(Landroid/content/Context;Ljava/lang/String;)V
    .registers 2

    invoke-static {p0, p1}, Lcom/streamsync/app/updater/StreamSyncUpdater;->showLatestDialog(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smshowUpdateDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    .registers 3

    invoke-static {p0, p1, p2}, Lcom/streamsync/app/updater/StreamSyncUpdater;->showUpdateDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 15
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static checkUpdate(Landroid/content/Context;)V
    .registers 3

    .line 18
    if-nez p0, :cond_3

    return-void

    .line 21
    :cond_3
    :try_start_3
    const-string v0, "\u0110ang ki\u1ec3m tra b\u1ea3n c\u1eadp nh\u1eadt..."

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V
    :try_end_d
    .catchall {:try_start_3 .. :try_end_d} :catchall_e

    goto :goto_f

    .line 22
    :catchall_e
    move-exception v0

    :goto_f
    nop

    .line 24
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1;-><init>(Landroid/content/Context;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 84
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 85
    return-void
.end method

.method private static showErrorDialog(Landroid/content/Context;)V
    .registers 3

    .line 130
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_5

    return-void

    .line 131
    :cond_5
    move-object v0, p0

    check-cast v0, Landroid/app/Activity;

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$4;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$4;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 143
    return-void
.end method

.method private static showLatestDialog(Landroid/content/Context;Ljava/lang/String;)V
    .registers 4

    .line 114
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_5

    return-void

    .line 115
    :cond_5
    move-object v0, p0

    check-cast v0, Landroid/app/Activity;

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    invoke-direct {v1, p0, p1}, Lcom/streamsync/app/updater/StreamSyncUpdater$3;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 127
    return-void
.end method

.method private static showUpdateDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    .registers 5

    .line 88
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_5

    return-void

    .line 89
    :cond_5
    move-object v0, p0

    check-cast v0, Landroid/app/Activity;

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$2;

    invoke-direct {v1, p0, p1, p2}, Lcom/streamsync/app/updater/StreamSyncUpdater$2;-><init>(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 111
    return-void
.end method
