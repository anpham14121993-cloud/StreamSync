.class Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$4;->onSuccess(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

.field final synthetic val$deviceCode:Ljava/lang/String;

.field final synthetic val$isCancelled:[Z

.field final synthetic val$nonce:Ljava/lang/String;

.field final synthetic val$pollInterval:I

.field final synthetic val$qrDialog:Landroid/app/AlertDialog;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;I[ZLjava/lang/String;Ljava/lang/String;Landroid/app/AlertDialog;)V
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

    .line 327
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iput p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$pollInterval:I

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$isCancelled:[Z

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$deviceCode:Ljava/lang/String;

    iput-object p5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$nonce:Ljava/lang/String;

    iput-object p6, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$qrDialog:Landroid/app/AlertDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 7

    .line 330
    nop

    .line 331
    iget v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$pollInterval:I

    mul-int/lit16 v0, v0, 0x3e8

    const/16 v1, 0x7d0

    invoke-static {v1, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 332
    :goto_d
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$isCancelled:[Z

    aget-boolean v3, v3, v1

    if-nez v3, :cond_61

    const/16 v3, 0x64

    if-ge v2, v3, :cond_61

    .line 334
    int-to-long v3, v0

    :try_start_18
    invoke-static {v3, v4}, Ljava/lang/Thread;->sleep(J)V
    :try_end_1b
    .catch Ljava/lang/InterruptedException; {:try_start_18 .. :try_end_1b} :catch_5f

    .line 337
    nop

    .line 338
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$isCancelled:[Z

    aget-boolean v3, v3, v1

    if-eqz v3, :cond_23

    goto :goto_61

    .line 340
    :cond_23
    add-int/lit8 v2, v2, 0x1

    .line 341
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$deviceCode:Ljava/lang/String;

    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$nonce:Ljava/lang/String;

    invoke-static {v3, v4}, Lcom/streamsync/app/auth/SupabaseSyncService;->pollDeviceStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 342
    const-string v4, "approved"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_4c

    .line 343
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iget-object v3, v3, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$deviceCode:Ljava/lang/String;

    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;->val$nonce:Ljava/lang/String;

    invoke-static {v3, v4, v5}, Lcom/streamsync/app/auth/SupabaseSyncService;->exchangeAndSaveSession(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v3

    .line 344
    if-eqz v3, :cond_5d

    .line 345
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$1;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 366
    goto :goto_61

    .line 368
    :cond_4c
    const-string v4, "expired"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5d

    .line 369
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5$2;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 378
    goto :goto_61

    .line 368
    :cond_5d
    nop

    .line 380
    goto :goto_d

    .line 335
    :catch_5f
    move-exception v0

    .line 336
    nop

    .line 381
    :cond_61
    :goto_61
    return-void
.end method
