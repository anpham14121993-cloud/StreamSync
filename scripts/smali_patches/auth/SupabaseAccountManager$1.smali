.class Lcom/streamsync/app/auth/SupabaseAccountManager$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoggedInDialog(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$dialog:Landroid/app/AlertDialog;


# direct methods
.method constructor <init>(Landroid/content/Context;Landroid/app/AlertDialog;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 94
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$1;->val$context:Landroid/content/Context;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$1;->val$dialog:Landroid/app/AlertDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 4

    .line 97
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$1;->val$context:Landroid/content/Context;

    const-string v0, "\u0110ang \u0111\u1ed3ng b\u1ed9..."

    const/4 v1, 0x0

    invoke-static {p1, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 98
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$1;->val$context:Landroid/content/Context;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$1$1;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$1$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$1;)V

    invoke-static {p1, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->syncLive(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$SyncCallback;)V

    .line 110
    return-void
.end method
