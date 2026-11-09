.class Lcom/streamsync/app/auth/SupabaseAccountManager$1$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$1;->onClick(Landroid/content/DialogInterface;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$1;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$1;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 54
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$1$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 57
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$1$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$1;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$1;->val$activity:Landroid/app/Activity;

    const-string v1, "\u0110\u1ed3ng b\u1ed9 Supabase th\u00e0nh c\u00f4ng!"

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 58
    return-void
.end method
