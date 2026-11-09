.class public Lcom/streamsync/app/updater/UpdaterClickLambda;
.super Ljava/lang/Object;
.source "UpdaterClickLambda.smali"
.implements Lkotlin/jvm/functions/Function0;

.method public constructor <init>()V
    .registers 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V
    return-void
.end method

.method public invoke()Lkotlin/Unit;
    .registers 3
    :try_start_0
    sget-object v0, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_0
    invoke-static {v0}, Lcom/streamsync/app/updater/StreamSyncUpdater;->checkUpdate(Landroid/content/Context;)V
    :cond_0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .registers 2
    invoke-virtual {p0}, Lcom/streamsync/app/updater/UpdaterClickLambda;->invoke()Lkotlin/Unit;
    move-result-object v0
    return-object v0
.end method
