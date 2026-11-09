.class Lcom/streamsync/app/auth/SupabaseSyncService$5;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService;->restoreDefaultNuvioAddons(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .registers 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 603
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 606
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5;->val$context:Landroid/content/Context;

    const-string v1, "\u0110ang kh\u00f4i ph\u1ee5c 4 Addon Nuvio ph\u1ed5 bi\u1ebfn..."

    invoke-static {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->showToast(Landroid/content/Context;Ljava/lang/String;)V

    .line 607
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 608
    const-string v1, "https://v3-cinemeta.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 609
    const-string v1, "https://opensubtitles-v3.strem.io/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 610
    const-string v1, "https://catalog.cyberflix.info/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 611
    const-string v1, "https://torrentio.strem.fun/manifest.json"

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 613
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5;->val$context:Landroid/content/Context;

    invoke-static {v1, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result v0

    .line 614
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$5;->val$context:Landroid/content/Context;

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->refreshRoom(Landroid/content/Context;)V

    .line 616
    nop

    .line 617
    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$5$1;

    invoke-direct {v1, p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$5$1;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$5;I)V

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 623
    return-void
.end method
