.class Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;
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

.field final synthetic val$e:Ljava/lang/Throwable;


# direct methods
.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$3;Ljava/lang/Throwable;)V
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

    .line 287
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;->val$e:Ljava/lang/Throwable;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 290
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$progressDialog:Landroid/app/ProgressDialog;

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    .line 291
    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$3;->val$activity:Landroid/app/Activity;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "L\u1ed7i t\u1ea3i b\u1ea3n c\u1eadp nh\u1eadt: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$3$3;->val$e:Ljava/lang/Throwable;

    invoke-virtual {v2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 292
    return-void
.end method
