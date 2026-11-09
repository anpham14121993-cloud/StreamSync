.class Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$9;->onClick(Landroid/content/DialogInterface;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$9;

.field final synthetic val$url:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$9;Ljava/lang/String;)V
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

    .line 335
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$9;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->val$url:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 338
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 339
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->val$url:Ljava/lang/String;

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 340
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$9;

    iget-object v1, v1, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$context:Landroid/content/Context;

    invoke-static {v1, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->installAddonsDirectly(Landroid/content/Context;Ljava/util/List;)I

    move-result v0

    .line 341
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$9;

    iget-object v1, v1, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$context:Landroid/content/Context;

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->refreshRoom(Landroid/content/Context;)V

    .line 342
    nop

    .line 343
    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;

    invoke-direct {v1, p0, v0}, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;I)V

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 353
    return-void
.end method
