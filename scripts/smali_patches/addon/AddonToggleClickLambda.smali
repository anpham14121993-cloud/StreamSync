.class public Lcom/streamsync/app/data/addon/AddonToggleClickLambda;
.super Ljava/lang/Object;
.source "AddonToggleClickLambda.smali"
.implements Lkotlin/jvm/functions/Function0;

.field private final manifest:Lcom/streamsync/app/data/addon/AddonManifest;

.method public constructor <init>(Lcom/streamsync/app/data/addon/AddonManifest;)V
    .registers 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    iput-object p1, p0, Lcom/streamsync/app/data/addon/AddonToggleClickLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    return-void
.end method

.method public invoke()Lkotlin/Unit;
    .registers 6
    :try_start_0
    sget-object v0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_0
    iget-object v1, p0, Lcom/streamsync/app/data/addon/AddonToggleClickLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    if-eqz v1, :cond_0
    invoke-virtual {v1}, Lcom/streamsync/app/data/addon/AddonManifest;->getId()Ljava/lang/String;
    move-result-object v1
    invoke-static {v0, v1}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->toggleAddon(Landroid/content/Context;Ljava/lang/String;)Z
    move-result v2

    new-instance v3, Ljava/lang/StringBuilder;
    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V
    if-eqz v2, :cond_1
    const-string v4, "\u0110\u00e3 b\u1eadt addon: "
    goto :goto_0
    :cond_1
    const-string v4, "\u0110\u00e3 t\u1eaft addon: "
    :goto_0
    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    iget-object v4, p0, Lcom/streamsync/app/data/addon/AddonToggleClickLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    invoke-virtual {v4}, Lcom/streamsync/app/data/addon/AddonManifest;->getName()Ljava/lang/String;
    move-result-object v4
    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    move-result-object v3
    const/4 v4, 0x0
    invoke-static {v0, v3, v4}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    move-result-object v0
    invoke-virtual {v0}, Landroid/widget/Toast;->show()V
    :cond_0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .registers 2
    invoke-virtual {p0}, Lcom/streamsync/app/data/addon/AddonToggleClickLambda;->invoke()Lkotlin/Unit;
    move-result-object v0
    return-object v0
.end method
