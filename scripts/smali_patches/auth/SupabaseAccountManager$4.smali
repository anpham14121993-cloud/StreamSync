.class Lcom/streamsync/app/auth/SupabaseAccountManager$4;
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

    .line 250
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onError(Ljava/lang/String;)V
    .registers 5

    .line 434
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

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

    .line 435
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result p1

    if-eqz p1, :cond_2a

    .line 436
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smshowLoginDialog(Landroid/content/Context;)V

    .line 438
    :cond_2a
    return-void
.end method

.method public onSuccess(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    .registers 14

    .line 253
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_9

    return-void

    .line 254
    :cond_9
    const/4 v0, 0x1

    new-array v4, v0, [Z

    const/4 v1, 0x0

    aput-boolean v1, v4, v1

    .line 256
    new-instance v1, Landroid/widget/ScrollView;

    iget-object v2, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {v1, v2}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 257
    new-instance v2, Landroid/widget/LinearLayout;

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {v2, v3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 258
    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 259
    const/16 v3, 0x28

    const/16 v5, 0x20

    invoke-virtual {v2, v3, v5, v3, v5}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 260
    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->setGravity(I)V

    .line 262
    new-instance v3, Landroid/widget/TextView;

    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {v3, v5}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 263
    const-string v5, "LI\u00caN K\u1ebeT THI\u1ebeT B\u1eca NUVIO"

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 264
    const/high16 v5, 0x41900000    # 18.0f

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setTextSize(F)V

    .line 265
    const/4 v5, 0x0

    invoke-virtual {v3, v5, v0}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 266
    const-string v5, "#4CAF50"

    invoke-static {v5}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v5

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setTextColor(I)V

    .line 267
    const/16 v5, 0x11

    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 268
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 270
    new-instance v3, Landroid/widget/TextView;

    iget-object v6, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {v3, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 271
    const-string v6, "\n1. Qu\u00e9t m\u00e3 QR b\u00ean d\u01b0\u1edbi ho\u1eb7c truy c\u1eadp: nuvio.tv/link\n2. Nh\u1eadp m\u00e3 li\u00ean k\u1ebft \u0111\u1ec3 ho\u00e0n t\u1ea5t \u0111\u0103ng nh\u1eadp:\n"

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 272
    const/high16 v6, 0x41500000    # 13.0f

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setTextSize(F)V

    .line 273
    const v6, -0x333334

    invoke-virtual {v3, v6}, Landroid/widget/TextView;->setTextColor(I)V

    .line 274
    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 275
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 278
    new-instance v3, Landroid/widget/TextView;

    iget-object v6, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {v3, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 279
    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 280
    const/high16 p1, 0x41e00000    # 28.0f

    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setTextSize(F)V

    .line 281
    sget-object p1, Landroid/graphics/Typeface;->MONOSPACE:Landroid/graphics/Typeface;

    invoke-virtual {v3, p1, v0}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 282
    const-string p1, "#FFD54F"

    invoke-static {p1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 283
    invoke-virtual {v3, v5}, Landroid/widget/TextView;->setGravity(I)V

    .line 284
    const-string p1, "#263238"

    invoke-static {p1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v3, p1}, Landroid/widget/TextView;->setBackgroundColor(I)V

    .line 285
    const/16 p1, 0x18

    const/16 v5, 0x10

    invoke-virtual {v3, p1, v5, p1, v5}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 286
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 289
    new-instance v3, Landroid/widget/ImageView;

    iget-object v5, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {v3, v5}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 290
    new-instance v5, Landroid/widget/LinearLayout$LayoutParams;

    const/16 v6, 0x190

    invoke-direct {v5, v6, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 291
    iput v0, v5, Landroid/widget/LinearLayout$LayoutParams;->gravity:I

    .line 292
    iput p1, v5, Landroid/widget/LinearLayout$LayoutParams;->topMargin:I

    .line 293
    iput p1, v5, Landroid/widget/LinearLayout$LayoutParams;->bottomMargin:I

    .line 294
    invoke-virtual {v3, v5}, Landroid/widget/ImageView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 295
    const/4 p1, -0x1

    invoke-virtual {v3, p1}, Landroid/widget/ImageView;->setBackgroundColor(I)V

    .line 296
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 299
    new-instance p1, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;

    invoke-direct {v0, p0, p3, v4, v3}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$1;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;Ljava/lang/String;[ZLandroid/widget/ImageView;)V

    invoke-direct {p1, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 323
    invoke-virtual {p1}, Ljava/lang/Thread;->start()V

    .line 326
    iget-object p1, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    const-string v0, "M\u1edf tr\u00ecnh duy\u1ec7t (nuvio.tv/link)"

    const-string v3, "#1976D2"

    invoke-static {p1, v0, v3}, Lcom/streamsync/app/auth/SupabaseAccountManager;->-$$Nest$smcreateStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object p1

    .line 327
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;

    invoke-direct {v0, p0, p3}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$2;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;Ljava/lang/String;)V

    invoke-virtual {p1, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 339
    invoke-virtual {v2, p1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 341
    invoke-virtual {v1, v2}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 343
    new-instance p1, Landroid/app/AlertDialog$Builder;

    iget-object p3, p0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-direct {p1, p3}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 344
    invoke-virtual {p1, v1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 345
    new-instance p3, Lcom/streamsync/app/auth/SupabaseAccountManager$4$3;

    invoke-direct {p3, p0, v4}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$3;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;[Z)V

    const-string v0, "\u0110\u00f3ng"

    invoke-virtual {p1, v0, p3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 351
    new-instance p3, Lcom/streamsync/app/auth/SupabaseAccountManager$4$4;

    invoke-direct {p3, p0, v4}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$4;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;[Z)V

    invoke-virtual {p1, p3}, Landroid/app/AlertDialog$Builder;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;

    .line 357
    invoke-virtual {p1}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v7

    .line 360
    new-instance p1, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;

    move-object v2, p0

    move-object v5, p2

    move-object v6, p4

    move v3, p5

    invoke-direct/range {v1 .. v7}, Lcom/streamsync/app/auth/SupabaseAccountManager$4$5;-><init>(Lcom/streamsync/app/auth/SupabaseAccountManager$4;I[ZLjava/lang/String;Ljava/lang/String;Landroid/app/AlertDialog;)V

    invoke-direct {p1, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 423
    invoke-virtual {p1}, Ljava/lang/Thread;->start()V

    .line 425
    iget-object p1, v2, Lcom/streamsync/app/auth/SupabaseAccountManager$4;->val$context:Landroid/content/Context;

    invoke-static {p1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result p1

    if-eqz p1, :cond_122

    .line 427
    :try_start_11c
    invoke-virtual {v7}, Landroid/app/AlertDialog;->show()V
    :try_end_11f
    .catchall {:try_start_11c .. :try_end_11f} :catchall_120

    .line 428
    :goto_11f
    goto :goto_122

    :catchall_120
    move-exception v0

    goto :goto_11f

    .line 430
    :cond_122
    :goto_122
    return-void
.end method
