.class public Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;
.super Ljava/lang/Object;
.source "CombinedIntegrationsLambda.smali"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# static fields
.field public static sDnsLambda:Lkotlin/jvm/functions/Function2;

.field public static sIntegrationsLambda:Lkotlin/jvm/functions/Function2;


# direct methods
.method public constructor <init>()V
    .registers 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 4

    check-cast p1, Landroidx/compose/runtime/Composer;

    check-cast p2, Ljava/lang/Number;

    invoke-virtual {p2}, Ljava/lang/Number;->intValue()I

    move-result v0

    invoke-virtual {p0, p1, v0}, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->invoke(Landroidx/compose/runtime/Composer;I)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method public invoke(Landroidx/compose/runtime/Composer;I)V
    .registers 5

    sget-object v0, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->sDnsLambda:Lkotlin/jvm/functions/Function2;

    if-eqz v0, :cond_c

    const/4 v1, 0x0

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, p1, v1}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_c
    sget-object v0, Lcom/streamsync/app/ui/settings/CombinedIntegrationsLambda;->sIntegrationsLambda:Lkotlin/jvm/functions/Function2;

    if-eqz v0, :cond_18

    const/4 v1, 0x0

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, p1, v1}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_18
    return-void
.end method
