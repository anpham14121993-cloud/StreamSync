.class Lcom/streamsync/app/auth/SupabaseSyncService$3$2;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService$3;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseSyncService$3;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$3;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 243
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$3;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 246
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$2;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$3;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    const-string v1, "\u0110\u00e3 g\u1eedi email x\u00e1c nh\u1eadn. Vui l\u00f2ng ki\u1ec3m tra h\u1ed9p th\u01b0!"

    invoke-interface {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;->onError(Ljava/lang/String;)V

    .line 247
    return-void
.end method
