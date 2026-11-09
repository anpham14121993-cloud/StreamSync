.class Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;

.field final synthetic val$finalCount:I


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;I)V
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

    .line 343
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;

    iput p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;->val$finalCount:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 346
    iget v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;->val$finalCount:I

    if-lez v0, :cond_15

    .line 347
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$9;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$context:Landroid/content/Context;

    const-string v1, "\u0110\u00e3 c\u00e0i \u0111\u1eb7t Addon th\u00e0nh c\u00f4ng!"

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    goto :goto_25

    .line 349
    :cond_15
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$9;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$context:Landroid/content/Context;

    const-string v1, "Kh\u00f4ng th\u1ec3 t\u1ea3i manifest t\u1eeb URL \u0111\u00e3 nh\u1eadp"

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 351
    :goto_25
    return-void
.end method
