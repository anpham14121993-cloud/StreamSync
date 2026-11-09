.class public Lcom/streamsync/app/auth/SupabaseAccountManager;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"


# static fields
.field private static final KEY_ANONYMOUS:Ljava/lang/String; = "is_anonymous"

.field private static final KEY_EMAIL:Ljava/lang/String; = "auth_email"

.field private static final KEY_TOKEN:Ljava/lang/String; = "auth_token"

.field private static final KEY_USER_ID:Ljava/lang/String; = "auth_user_id"

.field private static final PREFS_NAME:Ljava/lang/String; = "nuvio_supabase_auth"


# direct methods
.method static bridge synthetic -$$Nest$smauthenticateUser(Landroid/app/Activity;Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;Z)V
    .registers 5

    invoke-static {p0, p1, p2, p3, p4}, Lcom/streamsync/app/auth/SupabaseAccountManager;->authenticateUser(Landroid/app/Activity;Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;Z)V

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 16
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static authenticateUser(Landroid/app/Activity;Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;Z)V
    .registers 6

    .line 145
    if-eqz p4, :cond_5

    const-string p3, "\u0110ang t\u1ea1o t\u00e0i kho\u1ea3n..."

    goto :goto_7

    :cond_5
    const-string p3, "\u0110ang \u0111\u0103ng nh\u1eadp..."

    :goto_7
    const/4 v0, 0x0

    invoke-static {p0, p3, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p3

    invoke-virtual {p3}, Landroid/widget/Toast;->show()V

    .line 147
    new-instance p3, Ljava/lang/Thread;

    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    invoke-direct {v0, p1, p2, p0, p4}, Lcom/streamsync/app/auth/SupabaseAccountManager$6;-><init>(Landroid/content/SharedPreferences;Ljava/lang/String;Landroid/app/Activity;Z)V

    invoke-direct {p3, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 178
    invoke-virtual {p3}, Ljava/lang/Thread;->start()V

    .line 179
    return-void
.end method

.method public static isLoggedIn(Landroid/content/Context;)Z
    .registers 4

    .line 24
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    .line 25
    :cond_4
    const-string v1, "nuvio_supabase_auth"

    invoke-virtual {p0, v1, v0}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    .line 26
    const-string v1, "is_anonymous"

    invoke-interface {p0, v1, v0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    if-nez v1, :cond_20

    const-string v1, "auth_token"

    const-string v2, ""

    invoke-interface {p0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_21

    :cond_20
    const/4 v0, 0x1

    :cond_21
    return v0
.end method

.method public static show(Landroid/content/Context;)V
    .registers 9

    .line 30
    if-nez p0, :cond_3

    return-void

    .line 31
    :cond_3
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_8

    return-void

    .line 32
    :cond_8
    check-cast p0, Landroid/app/Activity;

    .line 33
    invoke-virtual {p0}, Landroid/app/Activity;->isFinishing()Z

    move-result v0

    if-eqz v0, :cond_11

    return-void

    .line 35
    :cond_11
    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/app/Activity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 36
    const-string v2, "is_anonymous"

    invoke-interface {v0, v2, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v2

    .line 37
    const-string v3, "auth_token"

    const-string v4, ""

    invoke-interface {v0, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 38
    const-string v5, "auth_email"

    invoke-interface {v0, v5, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 39
    const-string v6, "auth_user_id"

    invoke-interface {v0, v6, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 41
    if-nez v2, :cond_bd

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_3c

    goto/16 :goto_bd

    .line 75
    :cond_3c
    new-instance v2, Landroid/app/AlertDialog$Builder;

    invoke-direct {v2, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 76
    const-string v3, "\u0110\u0103ng nh\u1eadp Supabase"

    invoke-virtual {v2, v3}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    .line 78
    new-instance v3, Landroid/widget/LinearLayout;

    invoke-direct {v3, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 79
    const/4 v4, 0x1

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 80
    invoke-virtual {p0}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v4

    iget v4, v4, Landroid/util/DisplayMetrics;->density:F

    const/high16 v5, 0x41800000    # 16.0f

    mul-float v4, v4, v5

    float-to-int v4, v4

    .line 81
    div-int/lit8 v5, v4, 0x2

    invoke-virtual {v3, v4, v5, v4, v5}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 83
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 84
    const-string v6, "\u0110\u0103ng nh\u1eadp ho\u1eb7c t\u1ea1o t\u00e0i kho\u1ea3n Supabase (c\u01a1 ch\u1ebf Nuvio) \u0111\u1ec3 \u0111\u1ed3ng b\u1ed9 d\u1eef li\u1ec7u xem tr\u00ean m\u1ecdi thi\u1ebft b\u1ecb."

    invoke-virtual {v4, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 85
    invoke-virtual {v4, v1, v1, v1, v5}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 86
    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 88
    new-instance v1, Landroid/widget/EditText;

    invoke-direct {v1, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 89
    const-string v4, "Email (vd: user@example.com)"

    invoke-virtual {v1, v4}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 90
    const/16 v4, 0x21

    invoke-virtual {v1, v4}, Landroid/widget/EditText;->setInputType(I)V

    .line 91
    invoke-virtual {v3, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 93
    new-instance v4, Landroid/widget/EditText;

    invoke-direct {v4, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 94
    const-string v5, "M\u1eadt kh\u1ea9u"

    invoke-virtual {v4, v5}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 95
    const/16 v5, 0x81

    invoke-virtual {v4, v5}, Landroid/widget/EditText;->setInputType(I)V

    .line 96
    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 98
    invoke-virtual {v2, v3}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 100
    new-instance v3, Lcom/streamsync/app/auth/SupabaseAccountManager$3;

    invoke-direct {v3, v1, v4, p0, v0}, Lcom/streamsync/app/auth/SupabaseAccountManager$3;-><init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/app/Activity;Landroid/content/SharedPreferences;)V

    const-string v5, "\u0110\u0103ng nh\u1eadp"

    invoke-virtual {v2, v5, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 113
    new-instance v3, Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    invoke-direct {v3, v1, v4, p0, v0}, Lcom/streamsync/app/auth/SupabaseAccountManager$4;-><init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/app/Activity;Landroid/content/SharedPreferences;)V

    const-string v1, "T\u1ea1o t\u00e0i kho\u1ea3n"

    invoke-virtual {v2, v1, v3}, Landroid/app/AlertDialog$Builder;->setNeutralButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 126
    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    invoke-direct {v1, v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$5;-><init>(Landroid/content/SharedPreferences;Landroid/app/Activity;)V

    const-string p0, "\u1ea8n danh"

    invoke-virtual {v2, p0, v1}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 140
    invoke-virtual {v2}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    goto/16 :goto_13e

    .line 42
    :cond_bd
    :goto_bd
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-direct {v1, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 43
    const-string v3, "T\u00e0i kho\u1ea3n Supabase"

    invoke-virtual {v1, v3}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    .line 44
    if-eqz v2, :cond_cc

    const-string v3, "\u0110\u00e3 \u0111\u0103ng nh\u1eadp (\u1ea8n danh)"

    goto :goto_ce

    :cond_cc
    const-string v3, "\u0110\u00e3 \u0111\u0103ng nh\u1eadp"

    .line 45
    :goto_ce
    if-eqz v2, :cond_d2

    const-string v5, "T\u00e0i kho\u1ea3n kh\u00e1ch \u1ea9n danh"

    .line 46
    :cond_d2
    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    const-string v7, "\n"

    if-eqz v2, :cond_db

    goto :goto_f2

    :cond_db
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "M\u00e3 \u0111\u1ecbnh danh: "

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    :goto_f2
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Tr\u1ea1ng th\u00e1i: "

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "\nEmail: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "\n\u0110\u1ed3ng b\u1ed9 \u0111\u00e1m m\u00e2y Supabase \u0111ang ho\u1ea1t \u0111\u1ed9ng: Ti\u1ebfn \u0111\u1ed9 xem, l\u1ecbch s\u1eed v\u00e0 c\u1ea5u h\u00ecnh \u0111\u01b0\u1ee3c \u0111\u1ed3ng b\u1ed9 t\u1ef1 \u0111\u1ed9ng gi\u1eefa c\u00e1c thi\u1ebft b\u1ecb."

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 48
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    .line 50
    new-instance v2, Lcom/streamsync/app/auth/SupabaseAccountManager$1;

    invoke-direct {v2, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$1;-><init>(Landroid/app/Activity;)V

    const-string v3, "\u0110\u1ed3ng b\u1ed9 ngay"

    invoke-virtual {v1, v3, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 63
    new-instance v2, Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    invoke-direct {v2, v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$2;-><init>(Landroid/content/SharedPreferences;Landroid/app/Activity;)V

    const-string p0, "\u0110\u0103ng xu\u1ea5t"

    invoke-virtual {v1, p0, v2}, Landroid/app/AlertDialog$Builder;->setNeutralButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 72
    const-string p0, "\u0110\u00f3ng"

    const/4 v0, 0x0

    invoke-virtual {v1, p0, v0}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 73
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    .line 74
    nop

    .line 142
    :goto_13e
    return-void
.end method
