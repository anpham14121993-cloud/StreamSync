.class Lcom/streamsync/app/auth/SupabaseAccountManager$3;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoginDialog(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$dialog:Landroid/app/AlertDialog;


# direct methods
.method constructor <init>(Landroid/app/AlertDialog;Landroid/content/Context;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 204
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$dialog:Landroid/app/AlertDialog;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 2

    .line 207
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$dialog:Landroid/app/AlertDialog;

    invoke-virtual {p1}, Landroid/app/AlertDialog;->dismiss()V

    .line 208
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowDeviceLinkDialog(Landroid/content/Context;)V

    .line 209
    return-void
.end method
