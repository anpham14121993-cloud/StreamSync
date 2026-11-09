.class public Lcom/streamsync/app/auth/SupabaseAccountManager;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"


# static fields
.field private static final PREFS_NAME:Ljava/lang/String; = "nuvio_supabase_auth"


# direct methods
.method static bridge synthetic -$$Nest$smcreateStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;
    .registers 3

    invoke-static {p0, p1, p2}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object p0

    return-object p0
.end method

.method static bridge synthetic -$$Nest$smshowDeviceLinkDialog(Landroid/content/Context;)V
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showDeviceLinkDialog(Landroid/content/Context;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smshowLoggedInDialog(Landroid/content/Context;)V
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoggedInDialog(Landroid/content/Context;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smshowLoginDialog(Landroid/content/Context;)V
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoginDialog(Landroid/content/Context;)V

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 31
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;
    .registers 4

    .line 396
    new-instance v0, Landroid/widget/Button;

    invoke-direct {v0, p0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    .line 397
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 398
    const/4 p0, -0x1

    invoke-virtual {v0, p0}, Landroid/widget/Button;->setTextColor(I)V

    .line 399
    const/high16 p1, 0x41500000    # 13.0f

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setTextSize(F)V

    .line 400
    invoke-static {p2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setBackgroundColor(I)V

    .line 401
    new-instance p1, Landroid/widget/LinearLayout$LayoutParams;

    const/4 p2, -0x2

    invoke-direct {p1, p0, p2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 405
    const/4 p0, 0x0

    const/16 p2, 0x10

    invoke-virtual {p1, p0, p2, p0, p0}, Landroid/widget/LinearLayout$LayoutParams;->setMargins(IIII)V

    .line 406
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 407
    return-object v0
.end method

.method public static isLoggedIn(Landroid/content/Context;)Z
    .registers 4

    .line 36
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    .line 37
    :cond_4
    const-string v1, "nuvio_supabase_auth"

    invoke-virtual {p0, v1, v0}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    .line 38
    const-string v1, "is_anonymous"

    invoke-interface {p0, v1, v0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_14

    return v1

    .line 39
    :cond_14
    const-string v0, "auth_token"

    const-string v2, ""

    invoke-interface {p0, v0, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 40
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    xor-int/2addr p0, v1

    return p0
.end method

.method public static show(Landroid/content/Context;)V
    .registers 2

    .line 44
    if-nez p0, :cond_3

    return-void

    .line 45
    :cond_3
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isLoggedIn(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_d

    .line 46
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoggedInDialog(Landroid/content/Context;)V

    goto :goto_10

    .line 48
    :cond_d
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoginDialog(Landroid/content/Context;)V

    .line 50
    :goto_10
    return-void
.end method

.method private static showDeviceLinkDialog(Landroid/content/Context;)V
    .registers 3

    .line 216
    const-string v0, "\u0110ang kh\u1edfi t\u1ea1o li\u00ean k\u1ebft thi\u1ebft b\u1ecb..."

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 218
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$4;-><init>(Landroid/content/Context;)V

    invoke-static {p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->startDeviceLink(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V

    .line 393
    return-void
.end method

.method private static showLoggedInDialog(Landroid/content/Context;)V
    .registers 11

    .line 53
    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 54
    const-string v2, "auth_email"

    const-string v3, "T\u00e0i kho\u1ea3n Nuvio"

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 55
    const-string v3, "synced_addons"

    invoke-interface {v0, v3, v1}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v3

    .line 56
    const-string v4, "synced_progress"

    invoke-interface {v0, v4, v1}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v1

    .line 58
    new-instance v4, Landroid/widget/ScrollView;

    invoke-direct {v4, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 59
    new-instance v5, Landroid/widget/LinearLayout;

    invoke-direct {v5, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 60
    const/4 v6, 0x1

    invoke-virtual {v5, v6}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 61
    const/16 v7, 0x30

    const/16 v8, 0x24

    invoke-virtual {v5, v7, v8, v7, v8}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 63
    new-instance v7, Landroid/widget/TextView;

    invoke-direct {v7, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 64
    const-string v8, "T\u00c0I KHO\u1ea2N"

    invoke-virtual {v7, v8}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 65
    const/high16 v8, 0x41900000    # 18.0f

    invoke-virtual {v7, v8}, Landroid/widget/TextView;->setTextSize(F)V

    .line 66
    const/4 v8, 0x0

    invoke-virtual {v7, v8, v6}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 67
    const-string v6, "#4CAF50"

    invoke-static {v6}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v6

    invoke-virtual {v7, v6}, Landroid/widget/TextView;->setTextColor(I)V

    .line 68
    const/16 v6, 0x11

    invoke-virtual {v7, v6}, Landroid/widget/TextView;->setGravity(I)V

    .line 69
    invoke-virtual {v5, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 71
    new-instance v6, Landroid/widget/TextView;

    invoke-direct {v6, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 72
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "\nTr\u1ea1ng th\u00e1i: \u0110\u00e3 \u0111\u0103ng nh\u1eadp\nEmail: "

    invoke-virtual {v7, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v7, "\nT\u1ef1 \u0111\u1ed9ng \u0111\u1ed3ng b\u1ed9: \u0110ang b\u1eadt\nD\u1eef li\u1ec7u: "

    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " ti\u1ec7n \u00edch \u2022 "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " m\u1ee5c xem\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 76
    invoke-virtual {v6, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 77
    const/high16 v1, 0x41600000    # 14.0f

    invoke-virtual {v6, v1}, Landroid/widget/TextView;->setTextSize(F)V

    .line 78
    const/4 v1, -0x1

    invoke-virtual {v6, v1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 79
    invoke-virtual {v5, v6}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 81
    const-string v1, "\u0110\u0103ng xu\u1ea5t"

    const-string v2, "#D32F2F"

    invoke-static {p0, v1, v2}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v1

    .line 82
    invoke-virtual {v5, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 84
    invoke-virtual {v4, v5}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 86
    new-instance v2, Landroid/app/AlertDialog$Builder;

    const/4 v3, 0x4

    invoke-direct {v2, p0, v3}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 87
    invoke-virtual {v2, v4}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 88
    const-string v3, "\u0110\u00f3ng"

    invoke-virtual {v2, v3, v8}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 89
    invoke-virtual {v2}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v2

    .line 91
    new-instance v3, Lcom/streamsync/app/auth/SupabaseAccountManager$1;

    invoke-direct {v3, v0, p0, v2}, Lcom/streamsync/app/auth/SupabaseAccountManager$1;-><init>(Landroid/content/SharedPreferences;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v1, v3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 101
    invoke-virtual {v2}, Landroid/app/AlertDialog;->show()V

    .line 102
    return-void
.end method

.method private static showLoginDialog(Landroid/content/Context;)V
    .registers 12

    .line 105
    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 106
    const-string v1, "auth_email"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 108
    new-instance v1, Landroid/widget/ScrollView;

    invoke-direct {v1, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 109
    new-instance v2, Landroid/widget/LinearLayout;

    invoke-direct {v2, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 110
    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 111
    const/16 v4, 0x30

    const/16 v5, 0x24

    invoke-virtual {v2, v4, v5, v4, v5}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 113
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 114
    const-string v5, "T\u00c0I KHO\u1ea2N"

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 115
    const/high16 v5, 0x41900000    # 18.0f

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setTextSize(F)V

    .line 116
    const/4 v5, 0x0

    invoke-virtual {v4, v5, v3}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 117
    const-string v6, "#4CAF50"

    invoke-static {v6}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 118
    const/16 v7, 0x11

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setGravity(I)V

    .line 119
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 121
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 122
    const-string v7, "\n\u0110\u0103ng nh\u1eadp t\u00e0i kho\u1ea3n Nuvio \u0111\u1ec3 t\u1ef1 \u0111\u1ed9ng \u0111\u1ed3ng b\u1ed9 ti\u1ec7n \u00edch v\u00e0 ti\u1ebfn \u0111\u1ed9 xem.\n"

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 123
    const/high16 v7, 0x41500000    # 13.0f

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextSize(F)V

    .line 124
    const-string v7, "#B0BEC5"

    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 125
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 127
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 128
    const-string v7, "Email:"

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 129
    const v7, -0x333334

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 130
    const/high16 v8, 0x41400000    # 12.0f

    invoke-virtual {v4, v8}, Landroid/widget/TextView;->setTextSize(F)V

    .line 131
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 133
    new-instance v4, Landroid/widget/EditText;

    invoke-direct {v4, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 134
    const-string v9, "email@example.com"

    invoke-virtual {v4, v9}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 135
    invoke-virtual {v4, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 136
    const/16 v0, 0x20

    invoke-virtual {v4, v0}, Landroid/widget/EditText;->setInputType(I)V

    .line 137
    const/4 v0, -0x1

    invoke-virtual {v4, v0}, Landroid/widget/EditText;->setTextColor(I)V

    .line 138
    invoke-virtual {v4, v3}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 139
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 141
    new-instance v9, Landroid/widget/TextView;

    invoke-direct {v9, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 142
    const-string v10, "M\u1eadt kh\u1ea9u:"

    invoke-virtual {v9, v10}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 143
    invoke-virtual {v9, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 144
    invoke-virtual {v9, v8}, Landroid/widget/TextView;->setTextSize(F)V

    .line 145
    invoke-virtual {v2, v9}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 147
    new-instance v7, Landroid/widget/EditText;

    invoke-direct {v7, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 148
    const/16 v8, 0x81

    invoke-virtual {v7, v8}, Landroid/widget/EditText;->setInputType(I)V

    .line 149
    invoke-virtual {v7, v0}, Landroid/widget/EditText;->setTextColor(I)V

    .line 150
    invoke-virtual {v7, v3}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 151
    invoke-virtual {v2, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 153
    const-string v0, "\u0110\u0102NG NH\u1eacP"

    invoke-static {p0, v0, v6}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v0

    .line 154
    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 156
    const-string v3, "\u0110\u0103ng nh\u1eadp b\u1eb1ng m\u00e3 QR / Li\u00ean k\u1ebft"

    const-string v6, "#00897B"

    invoke-static {p0, v3, v6}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v3

    .line 157
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 159
    invoke-virtual {v1, v2}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 161
    new-instance v2, Landroid/app/AlertDialog$Builder;

    const/4 v6, 0x4

    invoke-direct {v2, p0, v6}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 162
    invoke-virtual {v2, v1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 163
    const-string v1, "H\u1ee7y"

    invoke-virtual {v2, v1, v5}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 164
    invoke-virtual {v2}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v1

    .line 166
    new-instance v2, Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    invoke-direct {v2, v4, v7, p0, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager$2;-><init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 204
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;

    invoke-direct {v0, v1, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$3;-><init>(Landroid/app/AlertDialog;Landroid/content/Context;)V

    invoke-virtual {v3, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 212
    invoke-virtual {v1}, Landroid/app/AlertDialog;->show()V

    .line 213
    return-void
.end method
