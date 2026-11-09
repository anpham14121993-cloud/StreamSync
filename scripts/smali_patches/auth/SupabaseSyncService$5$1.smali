.class Lcom/streamsync/app/auth/SupabaseSyncService$5$1;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService$5;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseSyncService$5;

.field final synthetic val$finalCount:I


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$5;I)V
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

    .line 617
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$5;

    iput p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5$1;->val$finalCount:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 5

    .line 620
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$5;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$5;->val$context:Landroid/content/Context;

    iget v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5$1;->val$finalCount:I

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "\u0110\u00e3 kh\u00f4i ph\u1ee5c th\u00e0nh c\u00f4ng "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " Addon ph\u1ed5 bi\u1ebfn!"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 621
    return-void
.end method
