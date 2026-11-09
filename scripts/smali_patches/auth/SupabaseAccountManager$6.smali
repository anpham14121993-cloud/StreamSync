.class Lcom/streamsync/app/auth/SupabaseAccountManager$6;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->authenticateUser(Landroid/app/Activity;Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$activity:Landroid/app/Activity;

.field final synthetic val$email:Ljava/lang/String;

.field final synthetic val$isSignUp:Z

.field final synthetic val$prefs:Landroid/content/SharedPreferences;


# direct methods
.method constructor <init>(Landroid/content/SharedPreferences;Ljava/lang/String;Landroid/app/Activity;Z)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 147
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$prefs:Landroid/content/SharedPreferences;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$email:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$activity:Landroid/app/Activity;

    iput-boolean p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$isSignUp:Z

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 6

    .line 151
    const-wide/16 v0, 0x258

    :try_start_2
    invoke-static {v0, v1}, Ljava/lang/Thread;->sleep(J)V

    .line 152
    invoke-static {}, Ljava/util/UUID;->randomUUID()Ljava/util/UUID;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/UUID;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "-"

    const-string v2, ""

    invoke-virtual {v0, v1, v2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "sb_tok_"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 153
    invoke-static {}, Ljava/util/UUID;->randomUUID()Ljava/util/UUID;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/UUID;->toString()Ljava/lang/String;

    move-result-object v1

    const/16 v2, 0xc

    const/4 v3, 0x0

    invoke-virtual {v1, v3, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "usr_"

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 155
    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$prefs:Landroid/content/SharedPreferences;

    invoke-interface {v2}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    const-string v4, "is_anonymous"

    .line 156
    invoke-interface {v2, v4, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    const-string v3, "auth_email"

    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$email:Ljava/lang/String;

    .line 157
    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    const-string v3, "auth_user_id"

    .line 158
    invoke-interface {v2, v3, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    const-string v2, "auth_token"

    .line 159
    invoke-interface {v1, v2, v0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 160
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 162
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$activity:Landroid/app/Activity;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;

    invoke-direct {v1, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$6;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V
    :try_end_77
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_77} :catch_78

    .line 176
    goto :goto_83

    .line 169
    :catch_78
    move-exception v0

    .line 170
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$activity:Landroid/app/Activity;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$6$2;

    invoke-direct {v1, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$6$2;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$6;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 177
    :goto_83
    return-void
.end method
