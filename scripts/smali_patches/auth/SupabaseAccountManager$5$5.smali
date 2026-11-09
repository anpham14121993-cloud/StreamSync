.class Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$5;->onSuccess(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$5;

.field final synthetic val$deviceCode:Ljava/lang/String;

.field final synthetic val$isCancelled:[Z

.field final synthetic val$nonce:Ljava/lang/String;

.field final synthetic val$pollInterval:I

.field final synthetic val$qrDialog:Landroid/app/AlertDialog;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;I[ZLjava/lang/String;Ljava/lang/String;Landroid/app/AlertDialog;)V
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

    .line 353
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    iput p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$pollInterval:I

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$isCancelled:[Z

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$deviceCode:Ljava/lang/String;

    iput-object p5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$nonce:Ljava/lang/String;

    iput-object p6, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$qrDialog:Landroid/app/AlertDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 7

    .line 356
    nop

    .line 357
    iget v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$pollInterval:I

    mul-int/lit16 v0, v0, 0x3e8

    const/16 v1, 0x7d0

    invoke-static {v1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 358
    :goto_d
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$isCancelled:[Z

    aget-boolean v3, v3, v1

    if-nez v3, :cond_61

    const/16 v3, 0x64

    if-ge v2, v3, :cond_61

    .line 360
    int-to-long v3, v0

    :try_start_18
    invoke-static {v3, v4}, Ljava/lang/Thread;->sleep(J)V
    :try_end_1b
    .catch Ljava/lang/InterruptedException; {:try_start_18 .. :try_end_1b} :catch_5f

    .line 363
    nop

    .line 364
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$isCancelled:[Z

    aget-boolean v3, v3, v1

    if-eqz v3, :cond_23

    goto :goto_61

    .line 366
    :cond_23
    add-int/lit8 v2, v2, 0x1

    .line 367
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$deviceCode:Ljava/lang/String;

    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$nonce:Ljava/lang/String;

    invoke-static {v3, v4}, Lcom/streamsync/app/auth/SupabaseSyncService;->pollDeviceStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 368
    const-string v4, "approved"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_4c

    .line 369
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    iget-object v3, v3, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$deviceCode:Ljava/lang/String;

    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;->val$nonce:Ljava/lang/String;

    invoke-static {v3, v4, v5}, Lcom/streamsync/app/auth/SupabaseSyncService;->exchangeAndSaveSession(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v3

    .line 370
    if-eqz v3, :cond_5d

    .line 371
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5$1;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 392
    goto :goto_61

    .line 394
    :cond_4c
    const-string v4, "expired"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5d

    .line 395
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5$2;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5$2;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 404
    goto :goto_61

    .line 394
    :cond_5d
    nop

    .line 406
    goto :goto_d

    .line 361
    :catch_5f
    move-exception v0

    .line 362
    nop

    .line 407
    :cond_61
    :goto_61
    return-void
.end method
