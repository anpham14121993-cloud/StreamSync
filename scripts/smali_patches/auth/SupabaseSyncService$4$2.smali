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

    .line 321
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iput p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalAddons:I

    iput p3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalProgress:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 6

    .line 324
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    if-eqz v0, :cond_11

    .line 325
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    iget v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalAddons:I

    iget v2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalProgress:I

    invoke-interface {v0, v1, v2}, Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;->onSuccess(II)V

    .line 327
    :cond_11
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    iget v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalAddons:I

    iget v2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;->val$finalProgress:I

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "\u0110\u1ed3ng b\u1ed9 ho\u00e0n t\u1ea5t: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v3, " Addon, "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " m\u1ee5c xem!"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 328
    return-void
.end method
