.class public Lcom/streamsync/app/auth/SupabaseAccountManager;
.super Ljava/lang/Object;
.source "SupabaseAccountManager.java"


# static fields
.field private static final PREFS_NAME:Ljava/lang/String; = "nuvio_supabase_auth"


# direct methods
.method static bridge synthetic -$$Nest$smshowAddManualAddonDialog(Landroid/content/Context;)V
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showAddManualAddonDialog(Landroid/content/Context;)V

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

    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;
    .registers 4

    .line 363
    new-instance v0, Landroid/widget/Button;

    invoke-direct {v0, p0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    .line 364
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 365
    const/high16 p0, 0x41500000    # 13.0f

    invoke-virtual {v0, p0}, Landroid/widget/Button;->setTextSize(F)V

    .line 366
    const/4 p0, -0x1

    invoke-virtual {v0, p0}, Landroid/widget/Button;->setTextColor(I)V

    .line 367
    invoke-static {p2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result p1

    invoke-virtual {v0, p1}, Landroid/widget/Button;->setBackgroundColor(I)V

    .line 368
    new-instance p1, Landroid/widget/LinearLayout$LayoutParams;

    const/4 p2, -0x2

    invoke-direct {p1, p0, p2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 372
    const/4 p0, 0x0

    const/16 p2, 0xc

    invoke-virtual {p1, p0, p2, p0, p2}, Landroid/widget/LinearLayout$LayoutParams;->setMargins(IIII)V

    .line 373
    invoke-virtual {v0, p1}, Landroid/widget/Button;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 374
    return-object v0
.end method

.method public static isLoggedIn(Landroid/content/Context;)Z
    .registers 4

    .line 32
    const/4 v0, 0x0

    if-nez p0, :cond_4

    return v0

    .line 33
    :cond_4
    const-string v1, "nuvio_supabase_auth"

    invoke-virtual {p0, v1, v0}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    .line 34
    const-string v1, "is_anonymous"

    invoke-interface {p0, v1, v0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_14

    return v1

    .line 35
    :cond_14
    const-string v0, "auth_token"

    const-string v2, ""

    invoke-interface {p0, v0, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 36
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    xor-int/2addr p0, v1

    return p0
.end method

.method public static show(Landroid/content/Context;)V
    .registers 2

    .line 40
    if-nez p0, :cond_3

    return-void

    .line 41
    :cond_3
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->isLoggedIn(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_d

    .line 42
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoggedInDialog(Landroid/content/Context;)V

    goto :goto_10

    .line 44
    :cond_d
    invoke-static {p0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->showLoginDialog(Landroid/content/Context;)V

    .line 46
    :goto_10
    return-void
.end method

.method private static showAddManualAddonDialog(Landroid/content/Context;)V
    .registers 4

    .line 321
    new-instance v0, Landroid/widget/EditText;

    invoke-direct {v0, p0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 322
    const-string v1, "https://.../manifest.json"

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 323
    const/4 v1, -0x1

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setTextColor(I)V

    .line 324
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 326
    new-instance v1, Landroid/app/AlertDialog$Builder;

    const/4 v2, 0x4

    invoke-direct {v1, p0, v2}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 327
    const-string v2, "Nh\u1eadp link Addon"

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    .line 328
    const-string v2, "D\u00e1n \u0111\u01b0\u1eddng link manifest c\u1ee7a Addon (Stremio / Nuvio) \u0111\u1ec3 c\u00e0i \u0111\u1eb7t tr\u1ef1c ti\u1ebfp:"

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    .line 329
    invoke-virtual {v1, v0}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 330
    new-instance v2, Lcom/streamsync/app/auth/SupabaseAccountManager$9;

    invoke-direct {v2, v0, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$9;-><init>(Landroid/widget/EditText;Landroid/content/Context;)V

    const-string p0, "C\u00e0i \u0111\u1eb7t"

    invoke-virtual {v1, p0, v2}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 358
    const-string p0, "H\u1ee7y"

    const/4 v0, 0x0

    invoke-virtual {v1, p0, v0}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 359
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    .line 360
    return-void
.end method

.method private static showLoggedInDialog(Landroid/content/Context;)V
    .registers 12

    .line 49
    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 50
    const-string v2, "auth_email"

    const-string v3, "T\u00e0i kho\u1ea3n kh\u00f4ng r\u00f5"

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 51
    const-string v3, "server_url"

    const-string v4, "https://api.nuvio.tv"

    invoke-interface {v0, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 52
    const-string v4, "last_sync_time"

    const-wide/16 v5, 0x0

    invoke-interface {v0, v4, v5, v6}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v7

    .line 53
    const-string v4, "synced_addons"

    invoke-interface {v0, v4, v1}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v4

    .line 54
    const-string v9, "synced_progress"

    invoke-interface {v0, v9, v1}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v1

    .line 56
    nop

    .line 57
    cmp-long v9, v7, v5

    if-lez v9, :cond_45

    .line 58
    new-instance v5, Ljava/text/SimpleDateFormat;

    const-string v6, "dd/MM/yyyy HH:mm"

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v9

    invoke-direct {v5, v6, v9}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    .line 59
    new-instance v6, Ljava/util/Date;

    invoke-direct {v6, v7, v8}, Ljava/util/Date;-><init>(J)V

    invoke-virtual {v5, v6}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object v5

    goto :goto_47

    .line 57
    :cond_45
    const-string v5, "Ch\u01b0a \u0111\u1ed3ng b\u1ed9"

    .line 62
    :goto_47
    new-instance v6, Landroid/widget/ScrollView;

    invoke-direct {v6, p0}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 63
    new-instance v7, Landroid/widget/LinearLayout;

    invoke-direct {v7, p0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 64
    const/4 v8, 0x1

    invoke-virtual {v7, v8}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 65
    const/16 v8, 0x30

    const/16 v9, 0x20

    invoke-virtual {v7, v8, v9, v8, v9}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 67
    new-instance v8, Landroid/widget/TextView;

    invoke-direct {v8, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 68
    const-string v9, "T\u00c0I KHO\u1ea2N SUPABASE (NUVIO)"

    invoke-virtual {v8, v9}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 69
    const/high16 v9, 0x41900000    # 18.0f

    invoke-virtual {v8, v9}, Landroid/widget/TextView;->setTextSize(F)V

    .line 70
    const-string v9, "#4CAF50"

    invoke-static {v9}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v9

    invoke-virtual {v8, v9}, Landroid/widget/TextView;->setTextColor(I)V

    .line 71
    const/16 v9, 0x11

    invoke-virtual {v8, v9}, Landroid/widget/TextView;->setGravity(I)V

    .line 72
    invoke-virtual {v7, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 74
    new-instance v8, Landroid/widget/TextView;

    invoke-direct {v8, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 75
    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    const-string v10, "\nTr\u1ea1ng th\u00e1i: \u0110\u00e3 k\u1ebft n\u1ed1i Supabase\nEmail: "

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v9, "\nM\u00e1y ch\u1ee7: "

    invoke-virtual {v2, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "\n\u0110\u1ed3ng b\u1ed9 g\u1ea7n nh\u1ea5t: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "\nD\u1eef li\u1ec7u: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, " Addon \u2022 "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " ti\u1ebfn \u0111\u1ed9 xem\n"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 80
    invoke-virtual {v8, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 81
    const/high16 v1, 0x41600000    # 14.0f

    invoke-virtual {v8, v1}, Landroid/widget/TextView;->setTextSize(F)V

    .line 82
    const/4 v1, -0x1

    invoke-virtual {v8, v1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 83
    invoke-virtual {v7, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 86
    const-string v1, "\u0110\u1ed2NG B\u1ed8 NGAY (ADDON & L\u1ecaCH S\u1eec)"

    const-string v2, "#2196F3"

    invoke-static {p0, v1, v2}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v1

    .line 87
    invoke-virtual {v7, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 90
    const-string v2, "Kh\u00f4i ph\u1ee5c 4 Addon Nuvio ph\u1ed5 bi\u1ebfn"

    const-string v3, "#37474F"

    invoke-static {p0, v2, v3}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v2

    .line 91
    invoke-virtual {v7, v2}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 94
    const-string v3, "Nh\u1eadp link Addon th\u1ee7 c\u00f4ng"

    const-string v4, "#455A64"

    invoke-static {p0, v3, v4}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v3

    .line 95
    invoke-virtual {v7, v3}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 98
    const-string v4, "\u0110\u0103ng xu\u1ea5t t\u00e0i kho\u1ea3n"

    const-string v5, "#D32F2F"

    invoke-static {p0, v4, v5}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v4

    .line 99
    invoke-virtual {v7, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 101
    invoke-virtual {v6, v7}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 103
    new-instance v5, Landroid/app/AlertDialog$Builder;

    const/4 v7, 0x4

    invoke-direct {v5, p0, v7}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 104
    invoke-virtual {v5, v6}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 105
    const-string v6, "\u0110\u00f3ng"

    const/4 v7, 0x0

    invoke-virtual {v5, v6, v7}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 106
    invoke-virtual {v5}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v5

    .line 108
    new-instance v6, Lcom/streamsync/app/auth/SupabaseAccountManager$1;

    invoke-direct {v6, p0, v5}, Lcom/streamsync/app/auth/SupabaseAccountManager$1;-><init>(Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v1, v6}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 126
    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$2;

    invoke-direct {v1, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$2;-><init>(Landroid/content/Context;)V

    invoke-virtual {v2, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 133
    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$3;

    invoke-direct {v1, p0}, Lcom/streamsync/app/auth/SupabaseAccountManager$3;-><init>(Landroid/content/Context;)V

    invoke-virtual {v3, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 140
    new-instance v1, Lcom/streamsync/app/auth/SupabaseAccountManager$4;

    invoke-direct {v1, v0, p0, v5}, Lcom/streamsync/app/auth/SupabaseAccountManager$4;-><init>(Landroid/content/SharedPreferences;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v4, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 150
    invoke-virtual {v5}, Landroid/app/AlertDialog;->show()V

    .line 151
    return-void
.end method

.method private static showLoginDialog(Landroid/content/Context;)V
    .registers 17

    .line 154
    move-object/from16 v6, p0

    const-string v0, "nuvio_supabase_auth"

    const/4 v1, 0x0

    invoke-virtual {v6, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 155
    const-string v1, "server_url"

    const-string v2, "https://api.nuvio.tv"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 156
    const-string v2, "auth_email"

    const-string v3, ""

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 158
    new-instance v2, Landroid/widget/ScrollView;

    invoke-direct {v2, v6}, Landroid/widget/ScrollView;-><init>(Landroid/content/Context;)V

    .line 159
    new-instance v3, Landroid/widget/LinearLayout;

    invoke-direct {v3, v6}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 160
    const/4 v4, 0x1

    invoke-virtual {v3, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 161
    const/16 v5, 0x30

    const/16 v7, 0x20

    invoke-virtual {v3, v5, v7, v5, v7}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 163
    new-instance v5, Landroid/widget/TextView;

    invoke-direct {v5, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 164
    const-string v8, "\u0110\u0102NG NH\u1eacP SUPABASE (NUVIO)"

    invoke-virtual {v5, v8}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 165
    const/high16 v8, 0x41900000    # 18.0f

    invoke-virtual {v5, v8}, Landroid/widget/TextView;->setTextSize(F)V

    .line 166
    const-string v8, "#4CAF50"

    invoke-static {v8}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    .line 167
    const/16 v9, 0x11

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setGravity(I)V

    .line 168
    invoke-virtual {v3, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 170
    new-instance v5, Landroid/widget/TextView;

    invoke-direct {v5, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 171
    const-string v9, "\n\u0110\u0103ng nh\u1eadp \u0111\u1ec3 t\u1ef1 \u0111\u1ed9ng \u0111\u1ed3ng b\u1ed9 Addon v\u00e0 l\u1ecbch s\u1eed xem t\u1eeb m\u00e1y ch\u1ee7 Nuvio / Supabase.\n"

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 172
    const/high16 v9, 0x41500000    # 13.0f

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextSize(F)V

    .line 173
    const-string v9, "#B0BEC5"

    invoke-static {v9}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v9

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    .line 174
    invoke-virtual {v3, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 176
    new-instance v5, Landroid/widget/TextView;

    invoke-direct {v5, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 177
    const-string v9, "M\u00e1y ch\u1ee7 (Server URL):"

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 178
    const v9, -0x333334

    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    .line 179
    const/high16 v10, 0x41400000    # 12.0f

    invoke-virtual {v5, v10}, Landroid/widget/TextView;->setTextSize(F)V

    .line 180
    invoke-virtual {v3, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 182
    new-instance v11, Landroid/widget/EditText;

    invoke-direct {v11, v6}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 183
    invoke-virtual {v11, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 184
    const/4 v1, -0x1

    invoke-virtual {v11, v1}, Landroid/widget/EditText;->setTextColor(I)V

    .line 185
    invoke-virtual {v11, v4}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 186
    invoke-virtual {v3, v11}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 188
    new-instance v5, Landroid/widget/TextView;

    invoke-direct {v5, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 189
    const-string v12, "Email:"

    invoke-virtual {v5, v12}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 190
    invoke-virtual {v5, v9}, Landroid/widget/TextView;->setTextColor(I)V

    .line 191
    invoke-virtual {v5, v10}, Landroid/widget/TextView;->setTextSize(F)V

    .line 192
    invoke-virtual {v3, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 194
    new-instance v12, Landroid/widget/EditText;

    invoke-direct {v12, v6}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 195
    const-string v5, "email@example.com"

    invoke-virtual {v12, v5}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 196
    invoke-virtual {v12, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 197
    invoke-virtual {v12, v7}, Landroid/widget/EditText;->setInputType(I)V

    .line 198
    invoke-virtual {v12, v1}, Landroid/widget/EditText;->setTextColor(I)V

    .line 199
    invoke-virtual {v12, v4}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 200
    invoke-virtual {v3, v12}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 202
    new-instance v0, Landroid/widget/TextView;

    invoke-direct {v0, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 203
    const-string v5, "M\u1eadt kh\u1ea9u (Password):"

    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 204
    invoke-virtual {v0, v9}, Landroid/widget/TextView;->setTextColor(I)V

    .line 205
    invoke-virtual {v0, v10}, Landroid/widget/TextView;->setTextSize(F)V

    .line 206
    invoke-virtual {v3, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 208
    new-instance v7, Landroid/widget/EditText;

    invoke-direct {v7, v6}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 209
    const/16 v0, 0x81

    invoke-virtual {v7, v0}, Landroid/widget/EditText;->setInputType(I)V

    .line 210
    invoke-virtual {v7, v1}, Landroid/widget/EditText;->setTextColor(I)V

    .line 211
    invoke-virtual {v7, v4}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 212
    invoke-virtual {v3, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 215
    const-string v0, "\u0110\u0102NG NH\u1eacP & \u0110\u1ed2NG B\u1ed8"

    invoke-static {v6, v0, v8}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v8

    .line 216
    invoke-virtual {v3, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 218
    const-string v0, "\u0110\u0103ng k\u00fd t\u00e0i kho\u1ea3n m\u1edbi"

    const-string v1, "#26A69A"

    invoke-static {v6, v0, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v9

    .line 219
    invoke-virtual {v3, v9}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 221
    const-string v0, "Kh\u00f4i ph\u1ee5c 4 Addon Nuvio (kh\u00f4ng c\u1ea7n t\u00e0i kho\u1ea3n)"

    const-string v1, "#37474F"

    invoke-static {v6, v0, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v10

    .line 222
    invoke-virtual {v3, v10}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 224
    const-string v0, "Nh\u1eadp link Addon th\u1ee7 c\u00f4ng"

    const-string v1, "#455A64"

    invoke-static {v6, v0, v1}, Lcom/streamsync/app/auth/SupabaseAccountManager;->createStyledButton(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/widget/Button;

    move-result-object v13

    .line 225
    invoke-virtual {v3, v13}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 227
    invoke-virtual {v2, v3}, Landroid/widget/ScrollView;->addView(Landroid/view/View;)V

    .line 229
    new-instance v0, Landroid/app/AlertDialog$Builder;

    const/4 v1, 0x4

    invoke-direct {v0, v6, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 230
    invoke-virtual {v0, v2}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    .line 231
    const-string v1, "H\u1ee7y"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 232
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v14

    .line 234
    new-instance v15, Lcom/streamsync/app/auth/SupabaseAccountManager$5;

    move-object v0, v15

    move-object v1, v12

    move-object v2, v7

    move-object v3, v11

    move-object/from16 v4, p0

    move-object v5, v14

    invoke-direct/range {v0 .. v5}, Lcom/streamsync/app/auth/SupabaseAccountManager$5;-><init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v8, v15}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 274
    new-instance v8, Lcom/streamsync/app/auth/SupabaseAccountManager$6;

    move-object v0, v8

    invoke-direct/range {v0 .. v5}, Lcom/streamsync/app/auth/SupabaseAccountManager$6;-><init>(Landroid/widget/EditText;Landroid/widget/EditText;Landroid/widget/EditText;Landroid/content/Context;Landroid/app/AlertDialog;)V

    invoke-virtual {v9, v8}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 303
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$7;

    invoke-direct {v0, v6}, Lcom/streamsync/app/auth/SupabaseAccountManager$7;-><init>(Landroid/content/Context;)V

    invoke-virtual {v10, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 310
    new-instance v0, Lcom/streamsync/app/auth/SupabaseAccountManager$8;

    invoke-direct {v0, v6}, Lcom/streamsync/app/auth/SupabaseAccountManager$8;-><init>(Landroid/content/Context;)V

    invoke-virtual {v13, v0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 317
    invoke-virtual {v14}, Landroid/app/AlertDialog;->show()V

    .line 318
    return-void
.end method
