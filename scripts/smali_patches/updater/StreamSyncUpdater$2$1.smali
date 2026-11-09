.class Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater$2;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$2;


# direct methods
.method constructor <init>(Lcom/streamsync/app/updater/StreamSyncUpdater$2;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 96
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$2;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4

    .line 100
    :try_start_0
    new-instance p1, Landroid/content/Intent;

    const-string p2, "android.intent.action.VIEW"

    iget-object v0, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$2;

    iget-object v0, v0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$downloadUrl:Ljava/lang/String;

    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    invoke-direct {p1, p2, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    .line 101
    const/high16 p2, 0x10000000

    invoke-virtual {p1, p2}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 102
    iget-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;->this$0:Lcom/streamsync/app/updater/StreamSyncUpdater$2;

    iget-object p2, p2, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$context:Landroid/content/Context;

    invoke-virtual {p2, p1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_1b
    .catchall {:try_start_0 .. :try_end_1b} :catchall_1c

    goto :goto_1d

    .line 103
    :catchall_1c
    move-exception p1

    :goto_1d
    nop

    .line 104
    return-void
.end method
