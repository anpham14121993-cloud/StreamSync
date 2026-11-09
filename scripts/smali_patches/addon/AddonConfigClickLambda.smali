.class public Lcom/streamsync/app/data/addon/AddonConfigClickLambda;
.super Ljava/lang/Object;
.source "AddonConfigClickLambda.smali"
.implements Lkotlin/jvm/functions/Function0;

.field private final manifest:Lcom/streamsync/app/data/addon/AddonManifest;

.method public constructor <init>(Lcom/streamsync/app/data/addon/AddonManifest;)V
    .registers 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    iput-object p1, p0, Lcom/streamsync/app/data/addon/AddonConfigClickLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    return-void
.end method

.method public invoke()Lkotlin/Unit;
    .registers 3
    :try_start_0
    sget-object v0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_0
    iget-object v1, p0, Lcom/streamsync/app/data/addon/AddonConfigClickLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    if-eqz v1, :cond_0
    invoke-virtual {v1}, Lcom/streamsync/app/data/addon/AddonManifest;->getBaseUrl()Ljava/lang/String;
    move-result-object v1
    invoke-static {v0, v1}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->openAddonConfig(Landroid/content/Context;Ljava/lang/String;)V
    :cond_0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .registers 2
    invoke-virtual {p0}, Lcom/streamsync/app/data/addon/AddonConfigClickLambda;->invoke()Lkotlin/Unit;
    move-result-object v0
    return-object v0
.end method
