.class Lcom/streamsync/app/auth/SupabaseAccountManager$2;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoggedInDialog(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$dialog:Landroid/app/AlertDialog;

.field final synthetic val$prefs:Landroid/content/SharedPreferences;


# direct methods
.method constructor <init>(Landroid/content/SharedPreferences;Landroid/content/Context;Landroid/app/AlertDialog;)V
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 113
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$prefs:Landroid/content/SharedPreferences;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$dialog:Landroid/app/AlertDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 4

    .line 116
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$prefs:Landroid/content/SharedPreferences;

    invoke-interface {p1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->clear()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 117
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    const-string v0, "\u0110\u00e3 \u0111\u0103ng xu\u1ea5t t\u00e0i kho\u1ea3n"

    const/4 v1, 0x0

    invoke-static {p1, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 118
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$dialog:Landroid/app/AlertDialog;

    invoke-virtual {p1}, Landroid/app/AlertDialog;->dismiss()V

    .line 119
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoginDialog(Landroid/content/Context;)V

    .line 120
    return-void
.end method
