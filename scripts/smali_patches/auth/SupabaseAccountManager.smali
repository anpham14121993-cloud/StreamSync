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

    .line 32
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;
    .registers 4

    .line 443
    new-instance v0, Landroid/widget/Button;

    invoke-direct {v0, p0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    .line 444
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 445
    const/4 p0, -0x1

    invoke-virtual {v0, p0}, Landroid/widget/Button;->setTextColor(I)V

    .line 446
    const/high16 p1, 0x41500000    # 13.0f

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setTextSize(F)V

    .line 447
    invoke-static {p2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setBackgroundColor(I)V

    .line 448
    const/4 p1, 0x1

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setFocusable(Z)V

    .line 449
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setFocusableInTouchMode(Z)V

    .line 450
    new-instance p1, Landroid/widget/LinearLayout$LayoutParams;

    const/4 p2, -0x2

    invoke-direct {p1, p0, p2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 454
    const/4 p0, 0x0

    const/16 p2, 0x10

    invoke-virtual {p1, p0, p2, p0, p0}, Landroid/widget/LinearLayout$LayoutParams;->setMargins(IIII)V

    .line 455
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 456
    return-object v0
.end method

.method public static isActivityValid(Landroid/content/Context;)Z
    .registers 4

    .line 37
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    .line 38
    :cond_4
    instance-of v1, p0, Landroid/app/Activity;

    const/4 v2, 0x1

    if-eqz v1, :cond_19

    .line 39
    check-cast p0, Landroid/app/Activity;

    .line 40
    invoke-virtual {p0}, Landroid/app/Activity;->isFinishing()Z

    move-result v1

    if-nez v1, :cond_18

    invoke-virtual {p0}, Landroid/app/Activity;->isDestroyed()Z

    move-result p0

    if-nez p0, :cond_18

    const/4 v0, 0x1

    :cond_18
    return v0

    .line 42
    :cond_19
    return v2
.end method

.method public static isLoggedIn(Landroid/content/Context;)Z
    .registers 4

    .line 46
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    .line 47
    :cond_4
    const-string v1, "nuvio_supabase_auth"

    invoke-virtual {p0, v1, v0}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    .line 48
    const-string v1, "is_anonymous"

    invoke-interface {p0, v1, v0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_14

    return v1

    .line 49
    :cond_14
    const-string v0, "auth_token"

    const-string v2, ""

    invoke-interface {p0, v0, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 50
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    xor-int/2addr p0, v1

    return p0
.end method

.method public static show(Landroid/content/Context;)V
    .registers 2

    .line 54
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_7

    return-void

    .line 55
    :cond_7
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isLoggedIn(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_11

    .line 56
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoggedInDialog(Landroid/content/Context;)V

    goto :goto_14

    .line 58
    :cond_11
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoginDialog(Landroid/content/Context;)V

    .line 60
    :goto_14
    return-void
.end method

.method private static showDeviceLinkDialog(Landroid/content/Context;)V
    .registers 3

    .line 247
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_7

    return-void

    .line 248
    :cond_7
    const-string v0, "\u0110ang kh\u1edfi t\u1ea1o li\u00ean k\u1ebft thi\u1ebft b\u1ecb..."

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 250
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$4;-><init>(Landroid/content/Context;)V

    invoke-static {p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->startDeviceLink(Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$DeviceLinkStartCallback;)V

    .line 440
    return-void
.end method

.method private static showLoggedInDialog(Landroid/content/Context;)V
    .registers 9

    .line 63
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_7

    return-void

    .line 64
    :cond_7
    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 65
    const-string v1, "auth_email"

    const-string v2, "T\u00e0i kho\u1ea3n Nuvio"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 67
    new-instance v2, Landroid/widget/ScrollView;

    invoke-direct {v2, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 68
    new-instance v3, Landroid/widget/LinearLayout;

    invoke-direct {v3, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 69
    const/4 v4, 0x1

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 70
    const/16 v5, 0x30

    const/16 v6, 0x24

    invoke-virtual {v3, v5, v6, v5, v6}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 72
    new-instance v5, Landroid/widget/TextView;

    invoke-direct {v5, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 73
    const-string v6, "T\u00c0I KHO\u1ea2N"

    invoke-virtual {v5, v6}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 74
    const/high16 v6, 0x41900000    # 18.0f

    invoke-virtual {v5, v6}, Landroid/widget/TextView;->setTextSize(F)V

    .line 75
    const/4 v6, 0x0

    invoke-virtual {v5, v6, v4}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 76
    const-string v4, "#4CAF50"

    invoke-static {v4}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v4

    invoke-virtual {v5, v4}, Landroid/widget/TextView;->setTextColor(I)V

    .line 77
    const/16 v4, 0x11

    invoke-virtual {v5, v4}, Landroid/widget/TextView;->setGravity(I)V

    .line 78
    invoke-virtual {v3, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 80
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 81
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "\nTr\u1ea1ng th\u00e1i: \u0110\u00e3 \u0111\u0103ng nh\u1eadp\nEmail: "

    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v5, "\n"

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 83
    invoke-virtual {v4, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 84
    const/high16 v1, 0x41600000    # 14.0f

    invoke-virtual {v4, v1}, Landroid/widget/TextView;->setTextSize(F)V

    .line 85
    const/4 v1, -0x1

    invoke-virtual {v4, v1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 86
    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 88
    const-string v1, "\u0110\u0103ng xu\u1ea5t"

    const-string v4, "#D32F2F"

    invoke-static {p0, v1, v4}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v1

    .line 89
    invoke-virtual {v3, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 91
    invoke-virtual {v2, v3}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 93
    new-instance v3, Landroid/app/AlertDialog$Builder;

    invoke-direct {v3, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 94
    invoke-virtual {v3, v2}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 95
    const-string v2, "\u0110\u00f3ng"

    invoke-virtual {v3, v2, v6}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 96
    invoke-virtual {v3}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v2

    .line 98
    new-instance v3, Lcom/streamsync/app/auth/SupabaseAccountManager$1;

    invoke-direct {v3, v0, p0, v2}, Lcom/streamsync/app/auth/SupabaseAccountManager$1;-><init>(Landroid/content/SharedPreferences;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v1, v3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 110
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_af

    .line 112
    :try_start_a9
    invoke-virtual {v2}, Landroid/app/AlertDialog;->show()V
    :try_end_ac
    .catchall {:try_start_a9 .. :try_end_ac} :catchall_ad

    .line 113
    :goto_ac
    goto :goto_af

    :catchall_ad
    move-exception p0

    goto :goto_ac

    .line 115
    :cond_af
    :goto_af
    return-void
.end method

.method private static showLoginDialog(Landroid/content/Context;)V
    .registers 12

    .line 118
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :cond_7

    return-void

    .line 119
    :cond_7
    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 120
    const-string v1, "auth_email"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 122
    new-instance v1, Landroid/widget/ScrollView;

    invoke-direct {v1, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 123
    new-instance v2, Landroid/widget/LinearLayout;

    invoke-direct {v2, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 124
    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 125
    const/16 v4, 0x30

    const/16 v5, 0x24

    invoke-virtual {v2, v4, v5, v4, v5}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 127
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 128
    const-string v5, "T\u00c0I KHO\u1ea2N"

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 129
    const/high16 v5, 0x41900000    # 18.0f

    invoke-virtual {v4, v5}, Landroid/widget/TextView;->setTextSize(F)V

    .line 130
    const/4 v5, 0x0

    invoke-virtual {v4, v5, v3}, Landroid/widget/TextView;->setTypeface(Landroid/graphics/Typeface;I)V

    .line 131
    const-string v6, "#4CAF50"

    invoke-static {v6}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 132
    const/16 v7, 0x11

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setGravity(I)V

    .line 133
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 135
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 136
    const-string v7, "\n\u0110\u0103ng nh\u1eadp t\u00e0i kho\u1ea3n Nuvio \u0111\u1ec3 t\u1ef1 \u0111\u1ed9ng \u0111\u1ed3ng b\u1ed9 ti\u1ec7n \u00edch v\u00e0 ti\u1ebfn \u0111\u1ed9 xem.\n"

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 137
    const/high16 v7, 0x41500000    # 13.0f

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextSize(F)V

    .line 138
    const-string v7, "#B0BEC5"

    invoke-static {v7}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v7

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 139
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 141
    new-instance v4, Landroid/widget/TextView;

    invoke-direct {v4, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 142
    const-string v7, "Email:"

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 143
    const v7, -0x333334

    invoke-virtual {v4, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 144
    const/high16 v8, 0x41400000    # 12.0f

    invoke-virtual {v4, v8}, Landroid/widget/TextView;->setTextSize(F)V

    .line 145
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 147
    new-instance v4, Landroid/widget/EditText;

    invoke-direct {v4, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 148
    const-string v9, "email@example.com"

    invoke-virtual {v4, v9}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 149
    invoke-virtual {v4, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 150
    const/16 v0, 0x20

    invoke-virtual {v4, v0}, Landroid/widget/EditText;->setInputType(I)V

    .line 151
    const/4 v0, -0x1

    invoke-virtual {v4, v0}, Landroid/widget/EditText;->setTextColor(I)V

    .line 152
    invoke-virtual {v4, v3}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 153
    invoke-virtual {v4, v3}, Landroid/widget/EditText;->setFocusable(Z)V

    .line 154
    invoke-virtual {v4, v3}, Landroid/widget/EditText;->setFocusableInTouchMode(Z)V

    .line 155
    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 157
    new-instance v9, Landroid/widget/TextView;

    invoke-direct {v9, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 158
    const-string v10, "M\u1eadt kh\u1ea9u:"

    invoke-virtual {v9, v10}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 159
    invoke-virtual {v9, v7}, Landroid/widget/TextView;->setTextColor(I)V

    .line 160
    invoke-virtual {v9, v8}, Landroid/widget/TextView;->setTextSize(F)V

    .line 161
    invoke-virtual {v2, v9}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 163
    new-instance v7, Landroid/widget/EditText;

    invoke-direct {v7, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 164
    const/16 v8, 0x81

    invoke-virtual {v7, v8}, Landroid/widget/EditText;->setInputType(I)V

    .line 165
    invoke-virtual {v7, v0}, Landroid/widget/EditText;->setTextColor(I)V

    .line 166
    invoke-virtual {v7, v3}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 167
    invoke-virtual {v7, v3}, Landroid/widget/EditText;->setFocusable(Z)V

    .line 168
    invoke-virtual {v7, v3}, Landroid/widget/EditText;->setFocusableInTouchMode(Z)V

    .line 169
    invoke-virtual {v2, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 171
    const-string v0, "\u0110\u0102NG NH\u1eacP"

    invoke-static {p0, v0, v6}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v0

    .line 172
    invoke-virtual {v2, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 174
    const-string v3, "\u0110\u0103ng nh\u1eadp b\u1eb1ng m\u00e3 QR / Li\u00ean k\u1ebft"

    const-string v6, "#00897B"

    invoke-static {p0, v3, v6}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v3

    .line 175
    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 177
    invoke-virtual {v1, v2}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 179
    new-instance v2, Landroid/app/AlertDialog$Builder;

    invoke-direct {v2, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 180
    invoke-virtual {v2, v1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 181
    const-string v1, "H\u1ee7y"

    invoke-virtual {v2, v1, v5}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 182
    invoke-virtual {v2}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v1

    .line 184
    new-instance v2, Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    invoke-direct {v2, v4, v7, p0, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager$2;-><init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 229
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$3;

    invoke-direct {v0, v1, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$3;-><init>(Landroid/app/AlertDialog;Landroid/content/Context;)V

    invoke-virtual {v3, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 239
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isActivityValid(Landroid/content/Context;)Z

    move-result p0

    if-eqz p0, :cond_114

    .line 241
    :try_start_10e
    invoke-virtual {v1}, Landroid/app/AlertDialog;->show()V
    :try_end_111
    .catchall {:try_start_10e .. :try_end_111} :catchall_112

    .line 242
    :goto_111
    goto :goto_114

    :catchall_112
    move-exception p0

    goto :goto_111

    .line 244
    :cond_114
    :goto_114
    return-void
.end method
