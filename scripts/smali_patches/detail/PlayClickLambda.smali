.class public Lcom/streamsync/app/ui/detail/PlayClickLambda;
.super Ljava/lang/Object;
.source "PlayClickLambda.smali"
.implements Lkotlin/jvm/functions/Function0;

# static fields
.field public static sWatchRequested:Z

# direct methods
.method static constructor <clinit>()V
    .registers 1

    const/4 v0, 0x0

    sput-boolean v0, Lcom/streamsync/app/ui/detail/PlayClickLambda;->sWatchRequested:Z

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

# virtual methods
.method public invoke()Ljava/lang/Object;
    .registers 2

    const/4 v0, 0x1

    sput-boolean v0, Lcom/streamsync/app/ui/detail/PlayClickLambda;->sWatchRequested:Z

    sget-object v0, Lcom/streamsync/app/ui/detail/MovieDetailScreenKt;->sCurrentViewModel:Lcom/streamsync/app/ui/detail/MovieDetailViewModel;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/streamsync/app/ui/detail/MovieDetailViewModel;->refreshStreams()V

    :cond_0
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method
