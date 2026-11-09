.class public Lcom/streamsync/app/data/addon/AddonToggleHelper;
.super Ljava/lang/Object;
.source "AddonToggleHelper.java"


# static fields
.field private static final KEY_ENABLED_PREFIX:Ljava/lang/String; = "addon_enabled_"

.field private static final PREF_NAME:Ljava/lang/String; = "streamsync_addon_prefs"


# direct methods
.method public constructor <init>()V
    .registers 1

    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getConfigUrl(Ljava/lang/String;)Ljava/lang/String;
    .registers 6

    .line 38
    if-eqz p0, :cond_67

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_d

    goto :goto_67

    .line 39
    :cond_d
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 41
    :goto_11
    const-string v0, "/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_25

    .line 42
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    goto :goto_11

    .line 44
    :cond_25
    const-string v0, "/manifest.json"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v2

    const-string v3, "/configure"

    if-eqz v2, :cond_4e

    .line 45
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v4

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    sub-int/2addr v4, v0

    invoke-virtual {p0, v1, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 47
    :cond_4e
    invoke-virtual {p0, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_55

    .line 48
    return-object p0

    .line 50
    :cond_55
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 38
    :cond_67
    :goto_67
    const/4 p0, 0x0

    return-object p0
.end method

.method public static isAddonEnabled(Landroid/content/Context;Ljava/lang/String;)Z
    .registers 5

    .line 14
    const/4 v0, 0x1

    if-eqz p0, :cond_2d

    if-eqz p1, :cond_2d

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_c

    goto :goto_2d

    .line 16
    :cond_c
    :try_start_c
    const-string v1, "streamsync_addon_prefs"

    const/4 v2, 0x0

    invoke-virtual {p0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    .line 17
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "addon_enabled_"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p0, p1, v0}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0
    :try_end_2a
    .catchall {:try_start_c .. :try_end_2a} :catchall_2b

    return p0

    .line 18
    :catchall_2b
    move-exception p0

    .line 19
    return v0

    .line 14
    :cond_2d
    :goto_2d
    return v0
.end method

.method public static openAddonConfig(Landroid/content/Context;Ljava/lang/String;)V
    .registers 5

    .line 54
    if-nez p0, :cond_3

    return-void

    .line 55
    :cond_3
    invoke-static {p1}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->getConfigUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 56
    const/4 v0, 0x0

    if-nez p1, :cond_14

    .line 57
    const-string p1, "Addon kh\u00f4ng c\u00f3 li\u00ean k\u1ebft c\u1ea5u h\u00ecnh"

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p0

    invoke-virtual {p0}, Landroid/widget/Toast;->show()V

    .line 58
    return-void

    .line 61
    :cond_14
    :try_start_14
    new-instance v1, Landroid/content/Intent;

    const-string v2, "android.intent.action.VIEW"

    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    invoke-direct {v1, v2, p1}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    .line 62
    const/high16 p1, 0x10000000

    invoke-virtual {v1, p1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 63
    invoke-virtual {p0, v1}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_27
    .catchall {:try_start_14 .. :try_end_27} :catchall_28

    .line 68
    goto :goto_35

    .line 64
    :catchall_28
    move-exception p1

    .line 66
    :try_start_29
    const-string p1, "Kh\u00f4ng th\u1ec3 m\u1edf trang c\u1ea5u h\u00ecnh addon"

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p0

    invoke-virtual {p0}, Landroid/widget/Toast;->show()V
    :try_end_32
    .catchall {:try_start_29 .. :try_end_32} :catchall_33

    goto :goto_34

    .line 67
    :catchall_33
    move-exception p0

    :goto_34
    nop

    .line 69
    :goto_35
    return-void
.end method

.method public static setAddonEnabled(Landroid/content/Context;Ljava/lang/String;Z)V
    .registers 5

    .line 24
    if-eqz p0, :cond_34

    if-eqz p1, :cond_34

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_b

    goto :goto_34

    .line 26
    :cond_b
    :try_start_b
    const-string v0, "streamsync_addon_prefs"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    .line 27
    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "addon_enabled_"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_30
    .catchall {:try_start_b .. :try_end_30} :catchall_31

    goto :goto_32

    .line 28
    :catchall_31
    move-exception p0

    :goto_32
    nop

    .line 29
    return-void

    .line 24
    :cond_34
    :goto_34
    return-void
.end method

.method public static toggleAddon(Landroid/content/Context;Ljava/lang/String;)Z
    .registers 3

    .line 32
    invoke-static {p0, p1}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->isAddonEnabled(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v0

    .line 33
    xor-int/lit8 v0, v0, 0x1

    invoke-static {p0, p1, v0}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->setAddonEnabled(Landroid/content/Context;Ljava/lang/String;Z)V

    .line 34
    return v0
.end method
