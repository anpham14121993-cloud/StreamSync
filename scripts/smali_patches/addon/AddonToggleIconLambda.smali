.class public Lcom/streamsync/app/data/addon/AddonToggleIconLambda;
.super Lkotlin/jvm/internal/Lambda;
.source "AddonToggleIconLambda.smali"
.implements Lkotlin/jvm/functions/Function2;

.field private final manifest:Lcom/streamsync/app/data/addon/AddonManifest;

.method public constructor <init>(Lcom/streamsync/app/data/addon/AddonManifest;)V
    .registers 3
    const/4 v0, 0x2
    invoke-direct {p0, v0}, Lkotlin/jvm/internal/Lambda;-><init>(I)V
    iput-object p1, p0, Lcom/streamsync/app/data/addon/AddonToggleIconLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    return-void
.end method

.method public final invoke(Landroidx/compose/runtime/Composer;I)V
    .registers 13
    .param p1, "$composer"    # Landroidx/compose/runtime/Composer;
    .param p2, "$changed"    # I

    const/4 v0, 0x1
    sget-object v1, Lcom/streamsync/app/MainActivity;->sInstance:Lcom/streamsync/app/MainActivity;
    if-eqz v1, :cond_0
    iget-object v2, p0, Lcom/streamsync/app/data/addon/AddonToggleIconLambda;->manifest:Lcom/streamsync/app/data/addon/AddonManifest;
    if-eqz v2, :cond_0
    invoke-virtual {v2}, Lcom/streamsync/app/data/addon/AddonManifest;->getId()Ljava/lang/String;
    move-result-object v2
    invoke-static {v1, v2}, Lcom/streamsync/app/data/addon/AddonToggleHelper;->isAddonEnabled(Landroid/content/Context;Ljava/lang/String;)Z
    move-result v0

    :cond_0
    sget-object v1, Landroidx/compose/material/icons/Icons;->INSTANCE:Landroidx/compose/material/icons/Icons;
    invoke-virtual {v1}, Landroidx/compose/material/icons/Icons;->getDefault()Landroidx/compose/material/icons/Icons$Filled;
    move-result-object v1

    if-eqz v0, :cond_1
    invoke-static {v1}, Landroidx/compose/material/icons/filled/VisibilityKt;->getVisibility(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;
    move-result-object v1
    sget-object v2, Landroidx/compose/material3/MaterialTheme;->INSTANCE:Landroidx/compose/material3/MaterialTheme;
    sget v3, Landroidx/compose/material3/MaterialTheme;->$stable:I
    invoke-virtual {v2, p1, v3}, Landroidx/compose/material3/MaterialTheme;->getColorScheme(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ColorScheme;
    move-result-object v2
    invoke-virtual {v2}, Landroidx/compose/material3/ColorScheme;->getPrimary-0d7_KjU()J
    move-result-wide v4
    const-string v2, "\u0110ang b\u1eadt"
    goto :goto_0

    :cond_1
    invoke-static {v1}, Landroidx/compose/material/icons/filled/VisibilityOffKt;->getVisibilityOff(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;
    move-result-object v1
    sget-object v2, Landroidx/compose/material3/MaterialTheme;->INSTANCE:Landroidx/compose/material3/MaterialTheme;
    sget v3, Landroidx/compose/material3/MaterialTheme;->$stable:I
    invoke-virtual {v2, p1, v3}, Landroidx/compose/material3/MaterialTheme;->getColorScheme(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ColorScheme;
    move-result-object v2
    invoke-virtual {v2}, Landroidx/compose/material3/ColorScheme;->getError-0d7_KjU()J
    move-result-wide v4
    const-string v2, "\u0110ang t\u1eaft"

    :goto_0
    const/4 v3, 0x0
    const/16 v7, 0x30
    const/4 v8, 0x4
    move-object v6, p1
    invoke-static/range {v1 .. v8}, Landroidx/compose/material3/IconKt;->Icon-ww6aTOc(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V
    return-void
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 5
    check-cast p1, Landroidx/compose/runtime/Composer;
    check-cast p2, Ljava/lang/Number;
    invoke-virtual {p2}, Ljava/lang/Number;->intValue()I
    move-result v0
    invoke-virtual {p0, p1, v0}, Lcom/streamsync/app/data/addon/AddonToggleIconLambda;->invoke(Landroidx/compose/runtime/Composer;I)V
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method
