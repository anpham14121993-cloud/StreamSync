.class Lcom/streamsync/app/auth/SupabaseAccountManager$2;
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

.field final synthetic val$prefs:Landroid/content/SharedPreferences;


# direct methods
.method constructor <init>(Landroid/content/SharedPreferences;Landroid/app/Activity;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 63
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$prefs:Landroid/content/SharedPreferences;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$activity:Landroid/app/Activity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4

    .line 66
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$prefs:Landroid/content/SharedPreferences;

    invoke-interface {p1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->clear()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 67
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$activity:Landroid/app/Activity;

    const-string p2, "\u0110\u00e3 \u0111\u0103ng xu\u1ea5t t\u00e0i kho\u1ea3n."

    const/4 v0, 0x0

    invoke-static {p1, p2, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 68
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$2;->val$activity:Landroid/app/Activity;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->show(Landroid/content/Context;)V

    .line 69
    return-void
.end method
