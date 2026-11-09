.class Lcom/streamsync/app/auth/SupabaseSyncService$4;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService;->syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 285
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 8

    .line 289
    :try_start_0
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    const-string v1, "nuvio_supabase_auth"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 290
    const-string v1, "server_url"

    const-string v2, "https://api.nuvio.tv"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 291
    const-string v2, "auth_token"

    const-string v3, ""

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 292
    const-string v3, "anon_key"

    const-string v4, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI"

    invoke-interface {v0, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 294
    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_34

    .line 295
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    if-eqz v0, :cond_33

    .line 296
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$4$1;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseSyncService$4$1;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$4;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 303
    :cond_33
    return-void

    .line 306
    :cond_34
    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    const-string v5, "\u0110ang \u0111\u1ed3ng b\u1ed9 Addon v\u00e0 l\u1ecbch s\u1eed t\u1eeb Nuvio..."

    invoke-static {v4, v5}, Lcom/streamsync/app/auth/SupabaseSyncService;->showToast(Landroid/content/Context;Ljava/lang/String;)V

    .line 308
    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    invoke-static {v4, v1, v2, v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->pullAddonsFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v4

    .line 309
    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    invoke-static {v5, v1, v2, v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->pullWatchProgressFromSupabase(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    .line 311
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "last_sync_time"

    .line 312
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    invoke-interface {v0, v2, v5, v6}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "synced_addons"

    .line 313
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "synced_progress"

    .line 314
    invoke-interface {v0, v2, v1}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 315
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 317
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->refreshRoom(Landroid/content/Context;)V

    .line 319
    nop

    .line 320
    nop

    .line 321
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;

    invoke-direct {v0, p0, v4, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$4$2;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$4;II)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V
    :try_end_73
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_73} :catch_74

    .line 339
    goto :goto_81

    .line 330
    :catch_74
    move-exception v0

    .line 331
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$4;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;

    if-eqz v1, :cond_81

    .line 332
    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$4$3;

    invoke-direct {v1, p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$4$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$4;Ljava/lang/Exception;)V

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 340
    :cond_81
    :goto_81
    return-void
.end method
