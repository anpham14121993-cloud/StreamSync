.class Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->onSuccess(Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 252
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(Ljava/lang/String;)V
    .registers 5

    .line 260
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "\u0110\u0103ng nh\u1eadp th\u00e0nh c\u00f4ng! L\u1ed7i \u0111\u1ed3ng b\u1ed9: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 v1, 0x1

    invoke-static {v0, p1, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 261
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoggedInDialog(Landroid/content/Context;)V

    .line 262
    return-void
.end method

.method public onSuccess(II)V
    .registers 3

    .line 255
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoggedInDialog(Landroid/content/Context;)V

    .line 256
    return-void
.end method
