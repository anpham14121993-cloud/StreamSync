.class Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;
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


# direct methods
.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$1;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 127
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 130
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$1$2;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$1;

    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$1;->val$context:Landroid/content/Context;

    const-string v1, "v1.9.0"

    invoke-static {v0, v1}, Lcom/streamsync/app/updater/StreamSyncUpdater;->-$$Nest$smshowLatestDialog(Landroid/content/Context;Ljava/lang/String;)V

    .line 131
    return-void
.end method
