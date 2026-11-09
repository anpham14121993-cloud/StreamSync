.class Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$4;->onSuccess(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

.field final synthetic val$verificationUrl:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;Ljava/lang/String;)V
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

    .line 323
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;->val$verificationUrl:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 5

    .line 327
    :try_start_0
    new-instance p1, Landroid/content/Intent;

    const-string v0, "android.intent.action.VIEW"

    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;->val$verificationUrl:Ljava/lang/String;

    invoke-static {v1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    invoke-direct {p1, v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    .line 328
    const/high16 v0, 0x10000000

    invoke-virtual {p1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 329
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-virtual {v0, p1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_19
    .catchall {:try_start_0 .. :try_end_19} :catchall_1a

    .line 332
    goto :goto_3e

    .line 330
    :catchall_1a
    move-exception p1

    .line 331
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Kh\u00f4ng th\u1ec3 m\u1edf tr\u00ecnh duy\u1ec7t: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 v1, 0x0

    invoke-static {v0, p1, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 333
    :goto_3e
    return-void
.end method
