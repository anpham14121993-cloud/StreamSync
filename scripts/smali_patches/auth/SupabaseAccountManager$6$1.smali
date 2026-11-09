.class Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$6;->onClick(Landroid/view/View;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$6;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 287
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(Ljava/lang/String;)V
    .registers 4

    .line 297
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$context:Landroid/content/Context;

    const/4 v1, 0x1

    invoke-static {v0, p1, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 298
    return-void
.end method

.method public onSuccess(Ljava/lang/String;Ljava/lang/String;)V
    .registers 4

    .line 290
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$context:Landroid/content/Context;

    const-string p2, "\u0110\u0103ng k\u00fd th\u00e0nh c\u00f4ng!"

    const/4 v0, 0x0

    invoke-static {p1, p2, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 291
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$dialog:Landroid/app/AlertDialog;

    invoke-virtual {p1}, Landroid/app/AlertDialog;->dismiss()V

    .line 292
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoggedInDialog(Landroid/content/Context;)V

    .line 293
    return-void
.end method
