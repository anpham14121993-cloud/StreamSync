.class Lcom/streamsync/app/auth/SupabaseAccountManager$5;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoginDialog(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$dialog:Landroid/app/AlertDialog;

.field final synthetic val$etEmail:Landroid/widget/EditText;

.field final synthetic val$etPassword:Landroid/widget/EditText;

.field final synthetic val$etServer:Landroid/widget/EditText;


# direct methods
.method constructor <init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/Context;Landroid/app/AlertDialog;)V
    .registers 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 234
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$etEmail:Landroid/widget/EditText;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$etPassword:Landroid/widget/EditText;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$etServer:Landroid/widget/EditText;

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    iput-object p5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$dialog:Landroid/app/AlertDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 7

    .line 237
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$etEmail:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    .line 238
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$etPassword:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 239
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$etServer:Landroid/widget/EditText;

    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    .line 241
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    const/4 v3, 0x0

    if-nez v2, :cond_4e

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_38

    goto :goto_4e

    .line 246
    :cond_38
    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    const-string v4, "\u0110ang \u0111\u0103ng nh\u1eadp Supabase..."

    invoke-static {v2, v4, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/Toast;->show()V

    .line 247
    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    new-instance v3, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    invoke-direct {v3, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;)V

    invoke-static {v2, v1, p1, v0, v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V

    .line 271
    return-void

    .line 242
    :cond_4e
    :goto_4e
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    const-string v0, "Vui l\u00f2ng nh\u1eadp Email v\u00e0 M\u1eadt kh\u1ea9u"

    invoke-static {p1, v0, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 243
    return-void
.end method
