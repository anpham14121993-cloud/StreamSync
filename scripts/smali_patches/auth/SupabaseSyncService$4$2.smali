.class Lcom/streamsync/app/auth/SupabaseSyncService$4$2;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService$4;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

.field final synthetic val$finalAddons:I

.field final synthetic val$finalProgress:I


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$4;II)V
    .registers 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010,
            0x1010,
            0x1010
        }
        names = {
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

    .line 411
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iput p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalAddons:I

    iput p3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalProgress:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 414
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    if-eqz v0, :cond_11

    .line 415
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    iget v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalAddons:I

    iget v2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalProgress:I

    invoke-interface {v0, v1, v2}, Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;->onSuccess(II)V

    .line 418
    :cond_11
    :try_start_11
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v0

    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v1, v1, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/content/pm/PackageManager;->getLaunchIntentForPackage(Ljava/lang/String;)Landroid/content/Intent;

    move-result-object v0

    .line 419
    if-eqz v0, :cond_35

    .line 420
    const/high16 v1, 0x14000000

    invoke-virtual {v0, v1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 421
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v1, v1, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    invoke-virtual {v1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_33
    .catchall {:try_start_11 .. :try_end_33} :catchall_34

    goto :goto_35

    .line 423
    :catchall_34
    move-exception v0

    :cond_35
    :goto_35
    nop

    .line 424
    return-void
.end method
