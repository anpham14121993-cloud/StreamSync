.class Lcom/streamsync/app/updater/StreamSyncUpdater$2;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/updater/StreamSyncUpdater;->showUpdateDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$downloadUrl:Ljava/lang/String;

.field final synthetic val$tag:Ljava/lang/String;


# direct methods
.method constructor <init>(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 89
    iput-object p1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$context:Landroid/content/Context;

    iput-object p2, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$tag:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$downloadUrl:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 5

    .line 93
    :try_start_0
    new-instance v0, Landroid/app/AlertDialog$Builder;

    iget-object v1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$context:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string v1, "C\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng"

    .line 94
    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    iget-object v1, p0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;->val$tag:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "\u0110\u00e3 c\u00f3 phi\u00ean b\u1ea3n m\u1edbi: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "\nB\u1ea1n c\u00f3 mu\u1ed1n t\u1ea3i v\u1ec1 v\u00e0 c\u00e0i \u0111\u1eb7t kh\u00f4ng?"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 95
    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const-string v1, "T\u1ea3i & C\u00e0i \u0111\u1eb7t"

    new-instance v2, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;

    invoke-direct {v2, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$2$1;-><init>(Lcom/streamsync/app/updater/StreamSyncUpdater$2;)V

    .line 96
    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    const-string v1, "\u0110\u1ec3 sau"

    .line 106
    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 107
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    :try_end_41
    .catchall {:try_start_0 .. :try_end_41} :catchall_42

    goto :goto_43

    .line 108
    :catchall_42
    move-exception v0

    :goto_43
    nop

    .line 109
    return-void
.end method
