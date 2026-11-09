.class Lcom/streamsync/app/updater/StreamSyncUpdater$2;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater;->showUpdatePrompt(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$downloadUrl:Ljava/lang/String;

.field final synthetic val$fileName:Ljava/lang/String;


# direct methods
.method constructor <init>(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 199
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$context:Landroid/content/Context;

    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$downloadUrl:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$fileName:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4

    .line 202
    iget-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$context:Landroid/content/Context;

    check-cast p1, Landroid/app/Activity;

    iget-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$downloadUrl:Ljava/lang/String;

    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$fileName:Ljava/lang/String;

    # invokes: Lcom/streamsync/app/updater/StreamSyncUpdater;->startInAppDownloadAndInstall(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V
    invoke-static {p1, p2, v0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->access$600(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V

    .line 203
    return-void
.end method
