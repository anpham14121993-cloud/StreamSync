.class Lcom/streamsync/app/auth/SupabaseAccountManager$5;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"

# interfaces
.implements Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseAccountManager;->showDeviceLinkDialog(Landroid/content/Context;)V
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

    .line 244
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(Ljava/lang/String;)V
    .registers 5

    .line 415
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "L\u1ed7i t\u1ea1o phi\u00ean li\u00ean k\u1ebft: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 v1, 0x1

    invoke-static {v0, p1, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 416
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoginDialog(Landroid/content/Context;)V

    .line 417
    return-void
.end method

.method public onSuccess(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    .registers 14

    .line 247
    const/4 v0, 0x1

    new-array v4, v0, [Z

    const/4 v1, 0x0

    aput-boolean v1, v4, v1

    .line 249
    new-instance v1, Landroid/widget/ScrollView;

    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {v1, v2}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 250
    new-instance v2, Landroid/widget/LinearLayout;

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {v2, v3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 251
    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 252
    const/16 v3, 0x28

    const/16 v5, 0x20

    invoke-virtual {v2, v3, v5, v3, v5}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 253
    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 255
    new-instance v3, Landroid/widget/TextView;

    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {v3, v5}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 256
    const-string v5, "LI\u00caN K\u1ebeT THI\u1ebeT B\u1eca NUVIO"

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 257
    const/high16 v5, 0x41900000    # 18.0f

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setTextSize(F)V

    .line 258
    const/4 v5, 0x0

    invoke-virtual {v3, v5, v0}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 259
    const-string v5, "#4CAF50"

    invoke-static {v5}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v5

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setTextColor(I)V

    .line 260
    const/16 v5, 0x11

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 261
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 263
    new-instance v3, Landroid/widget/TextView;

    iget-object v6, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {v3, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 264
    const-string v6, "\n1. Qu\u00e9t m\u00e3 QR b\u00ean d\u01b0\u1edbi ho\u1eb7c truy c\u1eadp: nuvio.tv/link\n2. Nh\u1eadp m\u00e3 li\u00ean k\u1ebft \u0111\u1ec3 ho\u00e0n t\u1ea5t \u0111\u0103ng nh\u1eadp:\n"

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 265
    const/high16 v6, 0x41500000    # 13.0f

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setTextSize(F)V

    .line 266
    const v6, -0x333334

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setTextColor(I)V

    .line 267
    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 268
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 271
    new-instance v3, Landroid/widget/TextView;

    iget-object v6, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {v3, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 272
    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 273
    const/high16 p1, 0x41e00000    # 28.0f

    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setTextSize(F)V

    .line 274
    sget-object p1, Landroid/graphics/Typeface;->MONOSPACE:Landroid/graphics/Typeface;

    invoke-virtual {v3, p1, v0}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 275
    const-string p1, "#FFD54F"

    invoke-static {p1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 276
    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 277
    const-string p1, "#263238"

    invoke-static {p1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setBackgroundColor(I)V

    .line 278
    const/16 p1, 0x10

    const/16 v5, 0x18

    invoke-virtual {v3, v5, p1, v5, p1}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 279
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 282
    new-instance p1, Landroid/widget/ImageView;

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    invoke-direct {p1, v3}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 283
    new-instance v3, Landroid/widget/LinearLayout$LayoutParams;

    const/16 v6, 0x190

    invoke-direct {v3, v6, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 284
    iput v0, v3, Landroid/widget/LinearLayout$LayoutParams;->gravity:I

    .line 285
    iput v5, v3, Landroid/widget/LinearLayout$LayoutParams;->topMargin:I

    .line 286
    iput v5, v3, Landroid/widget/LinearLayout$LayoutParams;->bottomMargin:I

    .line 287
    invoke-virtual {p1, v3}, Landroid/widget/ImageView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 288
    const/4 v0, -0x1

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setBackgroundColor(I)V

    .line 289
    invoke-virtual {v2, p1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 292
    new-instance v0, Ljava/lang/Thread;

    new-instance v3, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;

    invoke-direct {v3, p0, p3, v4, p1}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;Ljava/lang/String;[ZLandroid/widget/ImageView;)V

    invoke-direct {v0, v3}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 316
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 319
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    const-string v0, "M\u1edf tr\u00ecnh duy\u1ec7t (nuvio.tv/link)"

    const-string v3, "#1976D2"

    invoke-static {p1, v0, v3}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smcreateStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object p1

    .line 320
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$2;

    invoke-direct {v0, p0, p3}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$2;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;Ljava/lang/String;)V

    invoke-virtual {p1, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 332
    invoke-virtual {v2, p1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 334
    invoke-virtual {v1, v2}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 336
    new-instance p1, Landroid/app/AlertDialog$Builder;

    iget-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$5;->val$context:Landroid/content/Context;

    const/4 v0, 0x4

    invoke-direct {p1, p3, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 337
    invoke-virtual {p1, v1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 338
    new-instance p3, Lcom/streamsync/app/auth/SupabaseAccountManager$5$3;

    invoke-direct {p3, p0, v4}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$3;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;[Z)V

    const-string v0, "\u0110\u00f3ng"

    invoke-virtual {p1, v0, p3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 344
    new-instance p3, Lcom/streamsync/app/auth/SupabaseAccountManager$5$4;

    invoke-direct {p3, p0, v4}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$4;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;[Z)V

    invoke-virtual {p1, p3}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    .line 350
    invoke-virtual {p1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object p1

    .line 353
    new-instance p3, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;

    move-object v1, v0

    move-object v2, p0

    move v3, p5

    move-object v5, p2

    move-object v6, p4

    move-object v7, p1

    invoke-direct/range {v1 .. v7}, Lcom/streamsync/app/auth/SupabaseAccountManager$5$5;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$5;I[ZLjava/lang/String;Ljava/lang/String;Landroid/app/AlertDialog;)V

    invoke-direct {p3, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 408
    invoke-virtual {p3}, Ljava/lang/Thread;->start()V

    .line 410
    invoke-virtual {p1}, Landroid/app/AlertDialog;->show()V

    .line 411
    return-void
.end method
