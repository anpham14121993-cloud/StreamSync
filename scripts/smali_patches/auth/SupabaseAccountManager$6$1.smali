.class Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$6;->run()V
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

    .line 162
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 165
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$activity:Landroid/app/Activity;

    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-boolean v1, v1, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$isSignUp:Z

    if-eqz v1, :cond_d

    const-string v1, "T\u1ea1o t\u00e0i kho\u1ea3n Supabase th\u00e0nh c\u00f4ng!"

    goto :goto_f

    :cond_d
    const-string v1, "\u0110\u0103ng nh\u1eadp Supabase th\u00e0nh c\u00f4ng!"

    :goto_f
    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 166
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$activity:Landroid/app/Activity;

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->show(Landroid/content/Context;)V

    .line 167
    return-void
.end method
