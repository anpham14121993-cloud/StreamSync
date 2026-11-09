.class Lcom/streamsync/app/auth/SupabaseAccountManager$3$1$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;->onSuccess(Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 209
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(Ljava/lang/String;)V
    .registers 2

    .line 217
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$3;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoggedInDialog(Landroid/content/Context;)V

    .line 218
    return-void
.end method

.method public onSuccess(II)V
    .registers 3

    .line 212
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$3$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$3;

    iget-object p1, p1, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoggedInDialog(Landroid/content/Context;)V

    .line 213
    return-void
.end method
