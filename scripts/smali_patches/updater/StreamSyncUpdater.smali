.class public Lcom/streamsync/app/updater/StreamSyncUpdater;
.super Ljava/lang/Object;
.source "StreamSyncUpdater.java"


# static fields
.field private static final DEFAULT_VERSION:Ljava/lang/String; = "v1.9.8"

.field private static final GITHUB_REPO_API:Ljava/lang/String; = "https://api.github.com/repos/anpham14121993-cloud/StreamSync/releases/latest"

.field private static final sMainHandler:Landroid/os/Handler;


# direct methods
.method static bridge synthetic -$$Nest$sfgetsMainHandler()Landroid/os/Handler;
    .registers 1

    sget-object v0, Lcom/streamsync/app/updater/StreamSyncUpdater;->sMainHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static bridge synthetic -$$Nest$smgetAppVersion(Landroid/content/Context;)Ljava/lang/String;
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->getAppVersion(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static bridge synthetic -$$Nest$smisNewerVersion(Ljava/lang/String;Ljava/lang/String;)Z
    .registers 2

    invoke-static {p0, p1}, Lcom/streamsync/app/updater/StreamSyncUpdater;->isNewerVersion(Ljava/lang/String;Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method static bridge synthetic -$$Nest$smlaunchPackageInstaller(Landroid/app/Activity;Ljava/io/File;)V
    .registers 2

    invoke-static {p0, p1}, Lcom/streamsync/app/updater/StreamSyncUpdater;->launchPackageInstaller(Landroid/app/Activity;Ljava/io/File;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smreadStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->readStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static bridge synthetic -$$Nest$smshowErrorDialog(Landroid/content/Context;)V
    .registers 1

    invoke-static {p0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->showErrorDialog(Landroid/content/Context;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smshowUpdatePrompt(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    .registers 5

    invoke-static {p0, p1, p2, p3, p4}, Lcom/streamsync/app/updater/StreamSyncUpdater;->showUpdatePrompt(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    return-void
.end method

.method static bridge synthetic -$$Nest$smstartInAppDownloadAndInstall(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V
    .registers 3

    invoke-static {p0, p1, p2}, Lcom/streamsync/app/updater/StreamSyncUpdater;->startInAppDownloadAndInstall(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method static constructor <clinit>()V
    .registers 2

    .line 33
    new-instance v0, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v0, Lcom/streamsync/app/updater/StreamSyncUpdater;->sMainHandler:Landroid/os/Handler;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 28
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static checkUpdate(Landroid/content/Context;)V
    .registers 3

    .line 46
    if-nez p0, :cond_3

    return-void

    .line 49
    :cond_3
    :try_start_3
    const-string v0, "\u0110ang ki\u1ec3m tra b\u1ea3n c\u1eadp nh\u1eadt..."

    const/4 v1, 0x0

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V
    :try_end_d
    .catchall {:try_start_3 .. :try_end_d} :catchall_e

    goto :goto_f

    .line 50
    :catchall_e
    move-exception v0

    :goto_f
    nop

    .line 52
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/streamsync/app/updater/StreamSyncUpdater$1;

    invoke-direct {v1, p0}, Lcom/streamsync/app/updater/StreamSyncUpdater$1;-><init>(Landroid/content/Context;)V

    invoke-direct {v0, v1}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 162
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 163
    return-void
.end method

.method private static getAppVersion(Landroid/content/Context;)Ljava/lang/String;
    .registers 4

    .line 37
    const-string v0, "v"

    :try_start_2
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p0

    const/4 v2, 0x0

    invoke-virtual {v1, p0, v2}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object p0

    iget-object p0, p0, Landroid/content/pm/PackageInfo;->versionName:Ljava/lang/String;

    .line 38
    if-eqz p0, :cond_34

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_34

    .line 39
    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_20

    :goto_1f
    goto :goto_32

    :cond_20
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0
    :try_end_31
    .catchall {:try_start_2 .. :try_end_31} :catchall_33

    goto :goto_1f

    :goto_32
    return-object p0

    .line 41
    :catchall_33
    move-exception p0

    :cond_34
    nop

    .line 42
    const-string p0, "v1.9.8"

    return-object p0
.end method

.method private static isNewerVersion(Ljava/lang/String;Ljava/lang/String;)Z
    .registers 8

    .line 166
    const/4 v0, 0x0

    if-eqz p0, :cond_5d

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_a

    goto :goto_5d

    .line 167
    :cond_a
    const-string v1, "[^0-9.]"

    const-string v2, ""

    invoke-virtual {p0, v1, v2}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 168
    invoke-virtual {p1, v1, v2}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 169
    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    const/4 v4, 0x1

    if-nez v2, :cond_57

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_24

    goto :goto_57

    .line 171
    :cond_24
    const-string p0, "\\."

    invoke-virtual {v3, p0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    .line 172
    invoke-virtual {v1, p0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    .line 173
    array-length v1, p1

    array-length v2, p0

    invoke-static {v1, v2}, Ljava/lang/Math;->max(II)I

    move-result v1

    .line 174
    const/4 v2, 0x0

    :goto_35
    if-ge v2, v1, :cond_56

    .line 175
    array-length v3, p1

    if-ge v2, v3, :cond_41

    aget-object v3, p1, v2

    invoke-static {v3}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v3

    goto :goto_42

    :cond_41
    const/4 v3, 0x0

    .line 176
    :goto_42
    array-length v5, p0

    if-ge v2, v5, :cond_4c

    aget-object v5, p0, v2

    invoke-static {v5}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v5

    goto :goto_4d

    :cond_4c
    const/4 v5, 0x0

    .line 177
    :goto_4d
    if-le v3, v5, :cond_50

    return v4

    .line 178
    :cond_50
    if-ge v3, v5, :cond_53

    return v0

    .line 174
    :cond_53
    add-int/lit8 v2, v2, 0x1

    goto :goto_35

    .line 180
    :cond_56
    return v0

    .line 169
    :cond_57
    :goto_57
    invoke-virtual {p0, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    xor-int/2addr p0, v4

    return p0

    .line 166
    :cond_5d
    :goto_5d
    return v0
.end method

.method private static launchPackageInstaller(Landroid/app/Activity;Ljava/io/File;)V
    .registers 11

    .line 301
    const/4 v0, 0x1

    :try_start_1
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_11

    .line 302
    const-string p1, "File c\u1eadp nh\u1eadt kh\u00f4ng t\u1ed3n t\u1ea1i"

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    .line 303
    return-void

    .line 307
    :cond_11
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I
    :try_end_13
    .catchall {:try_start_1 .. :try_end_13} :catchall_b3

    const/16 v2, 0x1a

    const/high16 v3, 0x10000000

    if-lt v1, v2, :cond_5a

    .line 309
    :try_start_19
    invoke-virtual {p0}, Landroid/app/Activity;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/pm/PackageManager;->canRequestPackageInstalls()Z

    move-result v1

    if-nez v1, :cond_59

    .line 310
    const-string v1, "Vui l\u00f2ng cho ph\u00e9p c\u00e0i \u0111\u1eb7t t\u1eeb ngu\u1ed3n n\u00e0y \u0111\u1ec3 ti\u1ebfp t\u1ee5c"

    invoke-static {p0, v1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    .line 311
    new-instance v1, Landroid/content/Intent;

    const-string v2, "android.settings.MANAGE_UNKNOWN_APP_SOURCES"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 312
    invoke-virtual {p0}, Landroid/app/Activity;->getPackageName()Ljava/lang/String;

    move-result-object v2

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "package:"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    .line 313
    invoke-virtual {v1, v3}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 314
    invoke-virtual {p0, v1}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V
    :try_end_57
    .catchall {:try_start_19 .. :try_end_57} :catchall_58

    goto :goto_59

    .line 316
    :catchall_58
    move-exception v1

    :cond_59
    :goto_59
    nop

    .line 320
    :cond_5a
    :try_start_5a
    const-string v1, "androidx.core.content.FileProvider"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1

    .line 321
    const-string v2, "getUriForFile"

    const/4 v4, 0x3

    new-array v5, v4, [Ljava/lang/Class;

    const-class v6, Landroid/content/Context;

    const/4 v7, 0x0

    aput-object v6, v5, v7

    const-class v6, Ljava/lang/String;

    aput-object v6, v5, v0

    const-class v6, Ljava/io/File;

    const/4 v8, 0x2

    aput-object v6, v5, v8

    invoke-virtual {v1, v2, v5}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v1

    .line 322
    invoke-virtual {p0}, Landroid/app/Activity;->getPackageName()Ljava/lang/String;

    move-result-object v2

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v5, ".fileprovider"

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    new-array v4, v4, [Ljava/lang/Object;

    aput-object p0, v4, v7

    aput-object v2, v4, v0

    aput-object p1, v4, v8

    const/4 p1, 0x0

    invoke-virtual {v1, p1, v4}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/net/Uri;

    .line 324
    new-instance v1, Landroid/content/Intent;

    const-string v2, "android.intent.action.VIEW"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 325
    const-string v2, "application/vnd.android.package-archive"

    invoke-virtual {v1, p1, v2}, Landroid/content/Intent;->setDataAndType(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;

    .line 326
    invoke-virtual {v1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 327
    invoke-virtual {v1, v3}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 328
    invoke-virtual {p0, v1}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V
    :try_end_b2
    .catchall {:try_start_5a .. :try_end_b2} :catchall_b3

    .line 332
    goto :goto_d2

    .line 330
    :catchall_b3
    move-exception p1

    .line 331
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Kh\u00f4ng th\u1ec3 m\u1edf b\u1ed9 c\u00e0i \u0111\u1eb7t: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p0

    invoke-virtual {p0}, Landroid/widget/Toast;->show()V

    .line 333
    :goto_d2
    return-void
.end method

.method private static readStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 358
    new-instance v0, Ljava/io/BufferedReader;

    new-instance v1, Ljava/io/InputStreamReader;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    invoke-direct {v0, v1}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 359
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 361
    :goto_11
    invoke-virtual {v0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_21

    .line 362
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/16 v2, 0xa

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_11

    .line 364
    :cond_21
    invoke-virtual {v0}, Ljava/io/BufferedReader;->close()V

    .line 365
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static showErrorDialog(Landroid/content/Context;)V
    .registers 3

    .line 347
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_5

    return-void

    .line 349
    :cond_5
    :try_start_5
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-direct {v0, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string p0, "C\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng"

    .line 350
    invoke-virtual {v0, p0}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    const-string v0, "Kh\u00f4ng th\u1ec3 k\u1ebft n\u1ed1i \u0111\u1ebfn m\u00e1y ch\u1ee7 c\u1eadp nh\u1eadt. Vui l\u00f2ng ki\u1ec3m tra l\u1ea1i k\u1ebft n\u1ed1i m\u1ea1ng."

    .line 351
    invoke-virtual {p0, v0}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    const-string v0, "\u0110\u00f3ng"

    .line 352
    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    .line 353
    invoke-virtual {p0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    :try_end_20
    .catchall {:try_start_5 .. :try_end_20} :catchall_21

    goto :goto_22

    .line 354
    :catchall_21
    move-exception p0

    :goto_22
    nop

    .line 355
    return-void
.end method

.method private static showLatestDialog(Landroid/content/Context;Ljava/lang/String;)V
    .registers 4

    .line 336
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_5

    return-void

    .line 338
    :cond_5
    :try_start_5
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-direct {v0, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    const-string p0, "C\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng"

    .line 339
    invoke-virtual {v0, p0}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "B\u1ea1n \u0111ang s\u1eed d\u1ee5ng phi\u00ean b\u1ea3n m\u1edbi nh\u1ea5t ("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, ")."

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 340
    invoke-virtual {p0, p1}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    const-string p1, "\u0110\u00e3 hi\u1ec3u"

    .line 341
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    .line 342
    invoke-virtual {p0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    :try_end_37
    .catchall {:try_start_5 .. :try_end_37} :catchall_38

    goto :goto_39

    .line 343
    :catchall_38
    move-exception p0

    :goto_39
    nop

    .line 344
    return-void
.end method

.method private static showUpdatePrompt(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    .registers 9

    .line 184
    instance-of v0, p0, Landroid/app/Activity;

    if-nez v0, :cond_25

    .line 185
    if-eqz p4, :cond_9

    const-string p2, "B\u1ea1n \u0111ang d\u00f9ng b\u1ea3n: "

    goto :goto_b

    :cond_9
    const-string p2, "\u0110\u00e3 c\u00f3 b\u1ea3n m\u1edbi: "

    :goto_b
    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x1

    invoke-static {p0, p1, p2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p0

    invoke-virtual {p0}, Landroid/widget/Toast;->show()V

    .line 186
    return-void

    .line 190
    :cond_25
    if-eqz p4, :cond_2a

    :try_start_27
    const-string v0, "Phi\u00ean b\u1ea3n hi\u1ec7n t\u1ea1i"

    goto :goto_2c

    :cond_2a
    const-string v0, "C\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng"
    :try_end_2c
    .catchall {:try_start_27 .. :try_end_2c} :catchall_a0

    .line 191
    :goto_2c
    const-string v1, " ("

    if-eqz p4, :cond_52

    .line 192
    :try_start_30
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "B\u1ea1n \u0111ang s\u1eed d\u1ee5ng phi\u00ean b\u1ea3n: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v1, ").\n\nB\u1ea1n c\u00f3 mu\u1ed1n t\u1ea3i l\u1ea1i v\u00e0 c\u00e0i \u0111\u1eb7t tr\u1ef1c ti\u1ebfp b\u1ea3n n\u00e0y (OTA) kh\u00f4ng?"

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_73

    .line 193
    :cond_52
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "\u0110\u00e3 c\u00f3 phi\u00ean b\u1ea3n m\u1edbi: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v1, ").\n\nB\u1ea1n c\u00f3 mu\u1ed1n t\u1ea3i v\u1ec1 v\u00e0 c\u00e0i \u0111\u1eb7t tr\u1ef1c ti\u1ebfp (OTA) kh\u00f4ng?"

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 194
    :goto_73
    if-eqz p4, :cond_78

    const-string v1, "C\u00e0i \u0111\u00e8 l\u1ea1i (OTA)"

    goto :goto_7a

    :cond_78
    const-string v1, "T\u1ea3i & C\u00e0i \u0111\u1eb7t (OTA)"

    .line 196
    :goto_7a
    new-instance v2, Landroid/app/AlertDialog$Builder;

    invoke-direct {v2, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 197
    invoke-virtual {v2, v0}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 198
    invoke-virtual {v0, p1}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object p1

    new-instance v0, Lcom/streamsync/app/updater/StreamSyncUpdater$2;

    invoke-direct {v0, p0, p2, p3}, Lcom/streamsync/app/updater/StreamSyncUpdater$2;-><init>(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    .line 199
    invoke-virtual {p1, v1, v0}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    .line 205
    if-eqz p4, :cond_95

    const-string p1, "\u0110\u00f3ng"

    goto :goto_97

    :cond_95
    const-string p1, "\u0110\u1ec3 sau"

    :goto_97
    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object p0

    .line 206
    invoke-virtual {p0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    :try_end_9f
    .catchall {:try_start_30 .. :try_end_9f} :catchall_a0

    goto :goto_a1

    .line 207
    :catchall_a0
    move-exception p0

    :goto_a1
    nop

    .line 208
    return-void
.end method

.method private static startInAppDownloadAndInstall(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V
    .registers 7

    .line 211
    const/4 v0, 0x1

    if-eqz p1, :cond_51

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_a

    goto :goto_51

    .line 216
    :cond_a
    new-instance v1, Landroid/app/ProgressDialog;

    const/4 v2, 0x4

    invoke-direct {v1, p0, v2}, Landroid/app/ProgressDialog;-><init>(Landroid/content/Context;I)V

    .line 217
    const-string v2, "C\u1eadp nh\u1eadt OTA"

    invoke-virtual {v1, v2}, Landroid/app/ProgressDialog;->setTitle(Ljava/lang/CharSequence;)V

    .line 218
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "\u0110ang t\u1ea3i b\u1ea3n c\u1eadp nh\u1eadt "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "..."

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 219
    invoke-virtual {v1, v0}, Landroid/app/ProgressDialog;->setProgressStyle(I)V

    .line 220
    const/16 v0, 0x64

    invoke-virtual {v1, v0}, Landroid/app/ProgressDialog;->setMax(I)V

    .line 221
    const/4 v0, 0x0

    invoke-virtual {v1, v0}, Landroid/app/ProgressDialog;->setIndeterminate(Z)V

    .line 222
    invoke-virtual {v1, v0}, Landroid/app/ProgressDialog;->setCancelable(Z)V

    .line 223
    invoke-virtual {v1}, Landroid/app/ProgressDialog;->show()V

    .line 225
    new-instance v0, Ljava/lang/Thread;

    new-instance v2, Lcom/streamsync/app/updater/StreamSyncUpdater$3;

    invoke-direct {v2, p0, p2, p1, v1}, Lcom/streamsync/app/updater/StreamSyncUpdater$3;-><init>(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Landroid/app/ProgressDialog;)V

    invoke-direct {v0, v2}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;)V

    .line 296
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 297
    return-void

    .line 212
    :cond_51
    :goto_51
    const-string p1, "Kh\u00f4ng t\u00ecm th\u1ea5y \u0111\u01b0\u1eddng d\u1eabn t\u1ea3i g\u00f3i c\u1eadp nh\u1eadt"

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p0

    invoke-virtual {p0}, Landroid/widget/Toast;->show()V

    .line 213
    return-void
.end method
