.class Lcom/streamsync/app/auth/SupabaseAccountManager$2;
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


# direct methods
.method constructor <init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/Context;Landroid/app/AlertDialog;)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 168
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$etEmail:Landroid/widget/EditText;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$etPassword:Landroid/widget/EditText;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$dialog:Landroid/app/AlertDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 6

    .line 171
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$etEmail:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    .line 172
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$etPassword:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 174
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    const/4 v2, 0x0

    if-nez v1, :cond_42

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_2a

    goto :goto_42

    .line 179
    :cond_2a
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    const-string v3, "\u0110ang \u0111\u0103ng nh\u1eadp..."

    invoke-static {v1, v3, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    .line 180
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    new-instance v2, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;

    invoke-direct {v2, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$2$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$2;)V

    const-string v3, "https://api.nuvio.tv"

    invoke-static {v1, v3, p1, v0, v2}, Lcom/streamsync/app/auth/SupabaseSyncService;->login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V

    .line 203
    return-void

    .line 175
    :cond_42
    :goto_42
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    const-string v0, "Vui l\u00f2ng nh\u1eadp Email v\u00e0 M\u1eadt kh\u1ea9u"

    invoke-static {p1, v0, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 176
    return-void
.end method
