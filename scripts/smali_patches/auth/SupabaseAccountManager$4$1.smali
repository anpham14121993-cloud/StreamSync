.class Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;
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

.field final synthetic val$isCancelled:[Z

.field final synthetic val$ivQr:Landroid/widget/ImageView;

.field final synthetic val$verificationUrl:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;Ljava/lang/String;[ZLandroid/widget/ImageView;)V
    .registers 5
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010,
            0x1010,
            0x1010,
            0x1010
        }
        names = {
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

    .line 299
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;->this$0:Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;->val$verificationUrl:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;->val$isCancelled:[Z

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;->val$ivQr:Landroid/widget/ImageView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 4

    .line 303
    :try_start_0
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;->val$verificationUrl:Ljava/lang/String;

    const-string v1, "UTF-8"

    invoke-static {v0, v1}, Ljava/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 304
    new-instance v1, Ljava/net/URL;

    invoke-direct {v1, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 305
    invoke-virtual {v1}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v0

    check-cast v0, Ljava/net/HttpURLConnection;

    .line 306
    const/16 v1, 0x1f40

    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 307
    invoke-virtual {v0, v1}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 308
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v1

    const/16 v2, 0xc8

    if-ne v1, v2, :cond_4b

    .line 309
    invoke-virtual {v0}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    .line 310
    invoke-static {v0}, Landroid/graphics/BitmapFactory;->decodeStream(Ljava/io/InputStream;)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 311
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V

    .line 312
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1$1;

    invoke-direct {v0, p0, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;Landroid/graphics/Bitmap;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V
    :try_end_49
    .catchall {:try_start_0 .. :try_end_49} :catchall_4a

    goto :goto_4b

    .line 321
    :catchall_4a
    move-exception v0

    :cond_4b
    :goto_4b
    nop

    .line 322
    return-void
.end method
