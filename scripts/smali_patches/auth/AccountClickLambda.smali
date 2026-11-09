.class public Lcom/streamsync/app/auth/AccountClickLambda;
.super Ljava/lang/Object;
.source "AccountClickLambda.smali"
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
    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseAccountManager;->show(Landroid/content/Context;)V
    :cond_0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0

    :catchall_0
    move-exception v0
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .registers 2
    invoke-virtual {p0}, Lcom/streamsync/app/auth/AccountClickLambda;->invoke()Lkotlin/Unit;
    move-result-object v0
    return-object v0
.end method
