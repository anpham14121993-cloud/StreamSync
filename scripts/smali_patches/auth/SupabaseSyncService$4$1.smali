.class Lcom/streamsync/app/auth/SupabaseSyncService$4$1;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService$4;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$4;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 383
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 386
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$4;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    const-string v1, "Ch\u01b0a \u0111\u0103ng nh\u1eadp t\u00e0i kho\u1ea3n"

    invoke-interface {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;->onError(Ljava/lang/String;)V

    .line 387
    return-void
.end method
