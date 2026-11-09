.class Lcom/streamsync/app/auth/SupabaseAccountManager$3;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->show(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$activity:Landroid/app/Activity;

.field final synthetic val$etEmail:Landroid/widget/EditText;

.field final synthetic val$etPassword:Landroid/widget/EditText;

.field final synthetic val$prefs:Landroid/content/SharedPreferences;


# direct methods
.method constructor <init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/app/Activity;Landroid/content/SharedPreferences;)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 100
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$etEmail:Landroid/widget/EditText;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$etPassword:Landroid/widget/EditText;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$activity:Landroid/app/Activity;

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$prefs:Landroid/content/SharedPreferences;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 6

    .line 103
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$etEmail:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    .line 104
    iget-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$etPassword:Landroid/widget/EditText;

    invoke-virtual {p2}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p2

    .line 105
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_32

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2a

    goto :goto_32

    .line 109
    :cond_2a
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$activity:Landroid/app/Activity;

    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$prefs:Landroid/content/SharedPreferences;

    invoke-static {v0, v2, p1, p2, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smauthenticateUser(Landroid/app/Activity;Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;Z)V

    .line 110
    return-void

    .line 106
    :cond_32
    :goto_32
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$activity:Landroid/app/Activity;

    const-string p2, "Vui l\u00f2ng nh\u1eadp \u0111\u1ea7y \u0111\u1ee7 email v\u00e0 m\u1eadt kh\u1ea9u."

    invoke-static {p1, p2, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 107
    return-void
.end method
