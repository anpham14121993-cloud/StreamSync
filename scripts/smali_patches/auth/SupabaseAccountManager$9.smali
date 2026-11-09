.class Lcom/streamsync/app/auth/SupabaseAccountManager$9;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->showAddManualAddonDialog(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$etUrl:Landroid/widget/EditText;


# direct methods
.method constructor <init>(Landroid/widget/EditText;Landroid/content/Context;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 330
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$etUrl:Landroid/widget/EditText;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4

    .line 333
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$9;->val$etUrl:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    .line 334
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_21

    .line 335
    new-instance p2, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;

    invoke-direct {v0, p0, p1}, Lcom/streamsync/app/auth/SupabaseAccountManager$9$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$9;Ljava/lang/String;)V

    invoke-direct {p2, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 354
    invoke-virtual {p2}, Ljava/lang/Thread;->start()V

    .line 356
    :cond_21
    return-void
.end method
