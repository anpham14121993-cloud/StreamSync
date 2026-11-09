.class Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

.field final synthetic val$bmp:Landroid/graphics/Bitmap;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;Landroid/graphics/Bitmap;)V
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

    .line 305
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->val$bmp:Landroid/graphics/Bitmap;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 3

    .line 308
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->val$isCancelled:[Z

    const/4 v1, 0x0

    aget-boolean v0, v0, v1

    if-nez v0, :cond_16

    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->val$bmp:Landroid/graphics/Bitmap;

    if-eqz v0, :cond_16

    .line 309
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->this$1:Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    iget-object v0, v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;->val$ivQr:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1$1;->val$bmp:Landroid/graphics/Bitmap;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageBitmap(Landroid/graphics/Bitmap;)V

    .line 311
    :cond_16
    return-void
.end method
