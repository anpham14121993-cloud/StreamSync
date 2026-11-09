.class Lcom/streamsync/app/auth/SupabaseSyncService$2$3;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService$2;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseSyncService$2;

.field final synthetic val$e:Ljava/lang/Exception;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/Exception;)V
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

    .line 179
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$2;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;->val$e:Ljava/lang/Exception;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 5

    .line 182
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$2;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;->val$e:Ljava/lang/Exception;

    invoke-virtual {v1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "L\u1ed7i k\u1ebft n\u1ed1i: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;->onError(Ljava/lang/String;)V

    .line 183
    return-void
.end method
