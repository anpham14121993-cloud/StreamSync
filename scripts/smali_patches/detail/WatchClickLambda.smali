.class public Lcom/streamsync/app/ui/detail/WatchClickLambda;
.super Ljava/lang/Object;
.source "WatchClickLambda.smali"
.implements Lkotlin/jvm/functions/Function0;


# direct methods
.method public constructor <init>()V
    .registers 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public invoke()Lkotlin/Unit;
    .registers 2

    const/4 v0, 0x1

    invoke-static {v0}, Lcom/streamsync/app/data/addon/SeriesEpisodeHelper;->setStreamPickerActive(Z)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method public bridge synthetic invoke()Ljava/lang/Object;
    .registers 2

    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/WatchClickLambda;->invoke()Lkotlin/Unit;

    move-result-object v0

    return-object v0
.end method
