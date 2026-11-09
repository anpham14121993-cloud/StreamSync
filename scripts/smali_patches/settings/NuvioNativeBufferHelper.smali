.class public Lcom/streamsync/app/settings/NuvioNativeBufferHelper;
.super Ljava/lang/Object;
.source "NuvioNativeBufferHelper.java"


# static fields
.field private static volatile sCachedTotalMem:J


# direct methods
.method static constructor <clinit>()V
    .registers 2

    .line 13
    const-wide/16 v0, 0x0

    sput-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 12
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getAutoBufferMb(Landroid/content/Context;)I
    .registers 8

    .line 77
    invoke-static {p0}, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->getDevicePhysicalRamBytes(Landroid/content/Context;)J

    move-result-wide v0

    .line 78
    nop

    .line 79
    const-wide/16 v2, 0x0

    const/16 p0, 0xc8

    cmp-long v4, v0, v2

    if-gtz v4, :cond_e

    return p0

    .line 80
    :cond_e
    long-to-double v0, v0

    const-wide/32 v2, 0x40000000

    long-to-double v2, v2

    const-wide v4, 0x3ff2666666666666L    # 1.15

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_24

    const/16 p0, 0x64

    return p0

    .line 81
    :cond_24
    const-wide v4, 0x3ff7333333333333L    # 1.45

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_35

    const/16 p0, 0x7d

    return p0

    .line 82
    :cond_35
    const-wide v4, 0x4002666666666666L    # 2.3

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_44

    return p0

    .line 83
    :cond_44
    const-wide v4, 0x400999999999999aL    # 3.2

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double p0, v0, v4

    if-gez p0, :cond_55

    const/16 p0, 0x1f4

    return p0

    .line 84
    :cond_55
    const-wide v4, 0x4013333333333333L    # 4.8

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double p0, v0, v4

    if-gez p0, :cond_66

    const/16 p0, 0x3e8

    return p0

    .line 85
    :cond_66
    const-wide v4, 0x401b333333333333L    # 6.8

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v4

    cmpg-double p0, v0, v2

    if-gez p0, :cond_77

    const/16 p0, 0x640

    return p0

    .line 86
    :cond_77
    const/16 p0, 0x7d0

    return p0
.end method

.method public static getDevicePhysicalRamBytes(Landroid/content/Context;)J
    .registers 7

    .line 16
    sget-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-lez v4, :cond_b

    .line 17
    sget-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    return-wide v0

    .line 20
    :cond_b
    if-eqz p0, :cond_2d

    .line 21
    :try_start_d
    const-string v0, "activity"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/app/ActivityManager;

    .line 22
    if-eqz p0, :cond_2d

    .line 23
    new-instance v0, Landroid/app/ActivityManager$MemoryInfo;

    invoke-direct {v0}, Landroid/app/ActivityManager$MemoryInfo;-><init>()V

    .line 24
    invoke-virtual {p0, v0}, Landroid/app/ActivityManager;->getMemoryInfo(Landroid/app/ActivityManager$MemoryInfo;)V

    .line 25
    iget-wide v4, v0, Landroid/app/ActivityManager$MemoryInfo;->totalMem:J

    cmp-long p0, v4, v2

    if-lez p0, :cond_2d

    .line 26
    iget-wide v1, v0, Landroid/app/ActivityManager$MemoryInfo;->totalMem:J

    sput-wide v1, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    .line 27
    iget-wide v0, v0, Landroid/app/ActivityManager$MemoryInfo;->totalMem:J
    :try_end_2b
    .catchall {:try_start_d .. :try_end_2b} :catchall_2c

    return-wide v0

    .line 31
    :catchall_2c
    move-exception p0

    :cond_2d
    nop

    .line 33
    :try_start_2e
    new-instance p0, Ljava/io/BufferedReader;

    new-instance v0, Ljava/io/FileReader;

    const-string v1, "/proc/meminfo"

    invoke-direct {v0, v1}, Ljava/io/FileReader;-><init>(Ljava/lang/String;)V

    invoke-direct {p0, v0}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_3a
    .catchall {:try_start_2e .. :try_end_3a} :catchall_74

    .line 35
    :cond_3a
    :goto_3a
    :try_start_3a
    invoke-virtual {p0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_66

    .line 36
    const-string v1, "MemTotal:"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_3a

    .line 37
    const-string v1, "\\s+"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .line 38
    array-length v1, v0

    const/4 v2, 0x2

    if-lt v1, v2, :cond_65

    .line 39
    const/4 v1, 0x1

    aget-object v0, v0, v1

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    const-wide/16 v2, 0x400

    mul-long v0, v0, v2

    sput-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    .line 40
    sget-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J
    :try_end_61
    .catchall {:try_start_3a .. :try_end_61} :catchall_6a

    .line 44
    :try_start_61
    invoke-virtual {p0}, Ljava/io/BufferedReader;->close()V

    .line 40
    return-wide v0

    .line 42
    :cond_65
    goto :goto_3a

    .line 44
    :cond_66
    invoke-virtual {p0}, Ljava/io/BufferedReader;->close()V
    :try_end_69
    .catchall {:try_start_61 .. :try_end_69} :catchall_74

    goto :goto_75

    .line 33
    :catchall_6a
    move-exception v0

    :try_start_6b
    invoke-virtual {p0}, Ljava/io/BufferedReader;->close()V
    :try_end_6e
    .catchall {:try_start_6b .. :try_end_6e} :catchall_6f

    goto :goto_73

    :catchall_6f
    move-exception p0

    :try_start_70
    invoke-static {v0, p0}, Lcom/streamsync/app/settings/NuvioNativeBufferHelper$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :goto_73
    throw v0
    :try_end_74
    .catchall {:try_start_70 .. :try_end_74} :catchall_74

    .line 44
    :catchall_74
    move-exception p0

    .line 46
    :goto_75
    const-wide v0, 0x80000000L

    sput-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    .line 47
    sget-wide v0, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->sCachedTotalMem:J

    return-wide v0
.end method

.method public static getSafeNativeMemoryLimitMb(Landroid/content/Context;)I
    .registers 8

    .line 51
    invoke-static {p0}, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->getDevicePhysicalRamBytes(Landroid/content/Context;)J

    move-result-wide v0

    .line 52
    nop

    .line 53
    const-wide/16 v2, 0x0

    const/16 p0, 0xfa

    cmp-long v4, v0, v2

    if-gtz v4, :cond_e

    return p0

    .line 54
    :cond_e
    long-to-double v0, v0

    const-wide/32 v2, 0x40000000

    long-to-double v2, v2

    const-wide v4, 0x3ff2666666666666L    # 1.15

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_24

    const/16 p0, 0x96

    return p0

    .line 55
    :cond_24
    const-wide v4, 0x3ff7333333333333L    # 1.45

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_35

    const/16 p0, 0xc8

    return p0

    .line 56
    :cond_35
    const-wide v4, 0x4002666666666666L    # 2.3

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_44

    return p0

    .line 57
    :cond_44
    const-wide v4, 0x400999999999999aL    # 3.2

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double p0, v0, v4

    if-gez p0, :cond_55

    const/16 p0, 0x1f4

    return p0

    .line 58
    :cond_55
    const-wide v4, 0x4013333333333333L    # 4.8

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double p0, v0, v4

    if-gez p0, :cond_66

    const/16 p0, 0x3e8

    return p0

    .line 59
    :cond_66
    const-wide v4, 0x401b333333333333L    # 6.8

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v4

    cmpg-double p0, v0, v2

    if-gez p0, :cond_77

    const/16 p0, 0x640

    return p0

    .line 60
    :cond_77
    const/16 p0, 0x7d0

    return p0
.end method

.method public static getWarningNativeMemoryLimitMb(Landroid/content/Context;)I
    .registers 8

    .line 64
    invoke-static {p0}, Lcom/streamsync/app/settings/NuvioNativeBufferHelper;->getDevicePhysicalRamBytes(Landroid/content/Context;)J

    move-result-wide v0

    .line 65
    nop

    .line 66
    const-wide/16 v2, 0x0

    const/16 p0, 0x145

    cmp-long v4, v0, v2

    if-gtz v4, :cond_e

    return p0

    .line 67
    :cond_e
    long-to-double v0, v0

    const-wide/32 v2, 0x40000000

    long-to-double v2, v2

    const-wide v4, 0x3ff2666666666666L    # 1.15

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_24

    const/16 p0, 0xb4

    return p0

    .line 68
    :cond_24
    const-wide v4, 0x3ff7333333333333L    # 1.45

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_35

    const/16 p0, 0xfa

    return p0

    .line 69
    :cond_35
    const-wide v4, 0x4002666666666666L    # 2.3

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double v6, v0, v4

    if-gez v6, :cond_44

    return p0

    .line 70
    :cond_44
    const-wide v4, 0x400999999999999aL    # 3.2

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double p0, v0, v4

    if-gez p0, :cond_55

    const/16 p0, 0x28a

    return p0

    .line 71
    :cond_55
    const-wide v4, 0x4013333333333333L    # 4.8

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v4, v4, v2

    cmpg-double p0, v0, v4

    if-gez p0, :cond_66

    const/16 p0, 0x4b0

    return p0

    .line 72
    :cond_66
    const-wide v4, 0x401b333333333333L    # 6.8

    invoke-static {v2, v3}, Ljava/lang/Double;->isNaN(D)Z

    mul-double v2, v2, v4

    cmpg-double p0, v0, v2

    if-gez p0, :cond_77

    const/16 p0, 0x7d0

    return p0

    .line 73
    :cond_77
    const/16 p0, 0x9c4

    return p0
.end method
