.class Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$2;->onClick(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$2;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$2;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 178
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(Ljava/lang/String;)V
    .registers 5

    .line 198
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "L\u1ed7i \u0111\u0103ng nh\u1eadp: "

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

    .line 199
    return-void
.end method

.method public onSuccess(Ljava/lang/String;Ljava/lang/String;)V
    .registers 3

    .line 181
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$dialog:Landroid/app/AlertDialog;

    invoke-virtual {p1}, Landroid/app/AlertDialog;->dismiss()V

    .line 183
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    new-instance p2, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1$1;

    invoke-direct {p2, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;)V

    invoke-static {p1, p2}, Lcom/streamsync/app/auth/SupabaseSyncService;->syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V

    .line 194
    return-void
.end method
