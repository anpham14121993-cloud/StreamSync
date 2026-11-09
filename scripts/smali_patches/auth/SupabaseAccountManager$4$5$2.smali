.class Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 408
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 411
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$isCancelled:[Z

    const/4 v1, 0x0

    aget-boolean v0, v0, v1

    if-nez v0, :cond_2e

    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_2e

    .line 413
    :try_start_15
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$qrDialog:Landroid/app/AlertDialog;

    invoke-virtual {v0}, Landroid/app/AlertDialog;->dismiss()V
    :try_end_1c
    .catchall {:try_start_15 .. :try_end_1c} :catchall_1d

    goto :goto_1e

    .line 414
    :catchall_1d
    move-exception v0

    :goto_1e
    nop

    .line 415
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    const-string v2, "M\u00e3 li\u00ean k\u1ebft \u0111\u00e3 h\u1ebft h\u1ea1n"

    invoke-static {v0, v2, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 417
    :cond_2e
    return-void
.end method
