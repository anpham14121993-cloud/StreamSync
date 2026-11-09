.class public Lcom/streamsync/app/ExitConfirmListener;
.super Ljava/lang/Object;

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# instance fields
.field private final activity:Landroid/app/Activity;


# direct methods
.method public constructor <init>(Landroid/app/Activity;)V
    .registers 2
    .param p1, "activity"    # Landroid/app/Activity;

    .prologue
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/streamsync/app/ExitConfirmListener;->activity:Landroid/app/Activity;

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 4
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    iget-object v0, p0, Lcom/streamsync/app/ExitConfirmListener;->activity:Landroid/app/Activity;

    if-eqz v0, :cond_7

    invoke-virtual {v0}, Landroid/app/Activity;->finish()V

    :cond_7
    return-void
.end method
