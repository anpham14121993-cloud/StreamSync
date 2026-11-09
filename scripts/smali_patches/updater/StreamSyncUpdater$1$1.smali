.class Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater$1;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

.field final synthetic val$finalDownloadUrl:Ljava/lang/String;

.field final synthetic val$finalFileName:Ljava/lang/String;

.field final synthetic val$tag:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .registers 5
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010,
            0x1010,
            0x1010,
            0x1010
        }
        names = {
            null,
            null,
            null,
            null
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 131
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->val$tag:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->val$finalDownloadUrl:Ljava/lang/String;

    iput-object p4, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->val$finalFileName:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 6

    .line 134
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    iget-object v1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->val$tag:Ljava/lang/String;

    iget-object v2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->val$finalDownloadUrl:Ljava/lang/String;

    iget-object v3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$1;->val$finalFileName:Ljava/lang/String;

    const/4 v4, 0x0

    invoke-static {v0, v1, v2, v3, v4}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smshowUpdatePrompt(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    .line 135
    return-void
.end method
