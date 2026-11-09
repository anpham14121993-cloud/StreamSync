.class Lcom/streamsync/app/auth/SupabaseSyncService$3$1;
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

.field final synthetic val$deviceCode:Ljava/lang/String;

.field final synthetic val$nonce:Ljava/lang/String;

.field final synthetic val$pollInterval:I

.field final synthetic val$userCode:Ljava/lang/String;

.field final synthetic val$verifUrl:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseSyncService$3;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    .registers 7
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010,
            0x1010,
            0x1010,
            0x1010,
            0x1010,
            0x1010
        }
        names = {
            null,
            null,
            null,
            null,
            null,
            null
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 240
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$3;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$userCode:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$deviceCode:Ljava/lang/String;

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$verifUrl:Ljava/lang/String;

    iput-object p5, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$nonce:Ljava/lang/String;

    iput p6, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$pollInterval:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 8

    .line 243
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$3;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;

    if-eqz v0, :cond_17

    .line 244
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->this$0:Lcom/streamsync/app/auth/SupabaseSyncService$3;

    iget-object v1, v0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;

    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$userCode:Ljava/lang/String;

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$deviceCode:Ljava/lang/String;

    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$verifUrl:Ljava/lang/String;

    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$nonce:Ljava/lang/String;

    iget v6, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;->val$pollInterval:I

    invoke-interface/range {v1 .. v6}, Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;->onSuccess(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V

    .line 246
    :cond_17
    return-void
.end method
