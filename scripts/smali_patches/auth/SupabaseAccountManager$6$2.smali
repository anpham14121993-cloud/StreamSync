.class Lcom/streamsync/app/auth/SupabaseAccountManager$6$2;
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

    .line 170
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$2;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 173
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$6$2;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;->val$activity:Landroid/app/Activity;

    const-string v1, "L\u1ed7i k\u1ebft n\u1ed1i m\u00e1y ch\u1ee7 Supabase. Vui l\u00f2ng th\u1eed l\u1ea1i sau."

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 174
    return-void
.end method
