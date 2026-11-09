.class Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater$3;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$3;

.field final synthetic val$progressPercent:I


# direct methods
.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$3;I)V
    .registers 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010,
            0x1010
        }
        names = {
            null,
            null
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 244
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    iput p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;->val$progressPercent:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 247
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$progressDialog:Landroid/app/ProgressDialog;

    iget v1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$1;->val$progressPercent:I

    invoke-virtual {v0, v1}, Landroid/app/ProgressDialog;->setProgress(I)V

    .line 248
    return-void
.end method
