.class public Lcom/streamsync/app/data/MetaHelper;
.super Ljava/lang/Object;
.source "MetaHelper.java"

# direct methods
.method public constructor <init>()V
    .registers 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public static isValidParts(Ljava/util/List;)Z
    .registers 2
    if-eqz p0, :cond_0
    invoke-interface {p0}, Ljava/util/List;->isEmpty()Z
    move-result v0
    if-nez v0, :cond_0
    const/4 v0, 0x1
    return v0
    :cond_0
    const/4 v0, 0x0
    return v0
.end method

.method public static getAddonId(Ljava/util/List;)Ljava/lang/String;
    .registers 3
    if-eqz p0, :cond_empty
    invoke-interface {p0}, Ljava/util/List;->size()I
    move-result v0
    const/4 v1, 0x1
    if-gt v0, v1, :cond_first
    :cond_empty
    const-string v0, ""
    return-object v0
    :cond_first
    const/4 v0, 0x0
    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;
    move-result-object v0
    check-cast v0, Ljava/lang/String;
    return-object v0
.end method

.method public static getItemId(Ljava/util/List;)Ljava/lang/String;
    .registers 3
    if-eqz p0, :cond_empty
    invoke-interface {p0}, Ljava/util/List;->isEmpty()Z
    move-result v0
    if-eqz v0, :cond_check_size
    :cond_empty
    const-string v0, ""
    return-object v0
    :cond_check_size
    invoke-interface {p0}, Ljava/util/List;->size()I
    move-result v0
    const/4 v1, 0x1
    if-ne v0, v1, :cond_second
    const/4 v0, 0x0
    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;
    move-result-object v0
    check-cast v0, Ljava/lang/String;
    return-object v0
    :cond_second
    invoke-interface {p0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;
    move-result-object v0
    check-cast v0, Ljava/lang/String;
    return-object v0
.end method

.method public static matchesAddon(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;)Z
    .registers 3
    if-nez p0, :cond_not_null
    const/4 v0, 0x0
    return v0
    :cond_not_null
    if-eqz p1, :cond_match
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z
    move-result v0
    if-eqz v0, :cond_check_id
    :cond_match
    const/4 v0, 0x1
    return v0
    :cond_check_id
    invoke-virtual {p0}, Lcom/streamsync/app/data/addon/AddonManifest;->getId()Ljava/lang/String;
    move-result-object v0
    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z
    move-result v0
    return v0
.end method
