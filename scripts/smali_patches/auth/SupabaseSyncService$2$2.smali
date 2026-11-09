.class Lcom/streamsync/app/auth/SupabaseSyncService$2$2;
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

.field final synthetic val$finalErrMsg:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/String;)V
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

    .line 170
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$2;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;->val$finalErrMsg:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 173
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$2;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;->val$finalErrMsg:Ljava/lang/String;

    invoke-interface {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;->onError(Ljava/lang/String;)V

    .line 174
    return-void
.end method
