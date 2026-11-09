.class Lcom/streamsync/app/ui/detail/TrailerPlayerActivity$1;
.super Ljava/lang/Object;
.source "TrailerPlayerActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;


# direct methods
.method constructor <init>(Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            null
        }
    .end annotation

    .line 112
    iput-object p1, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity$1;->this$0:Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .registers 2

    .line 115
    iget-object p1, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity$1;->this$0:Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;

    invoke-virtual {p1}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->finish()V

    .line 116
    return-void
.end method
