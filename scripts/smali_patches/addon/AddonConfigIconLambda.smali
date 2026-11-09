.class public Lcom/streamsync/app/data/addon/AddonConfigIconLambda;
.super Lkotlin/jvm/internal/Lambda;
.source "AddonConfigIconLambda.smali"
.implements Lkotlin/jvm/functions/Function2;

.method public constructor <init>()V
    .registers 2
    const/4 v0, 0x2
    invoke-direct {p0, v0}, Lkotlin/jvm/internal/Lambda;-><init>(I)V
    return-void
.end method

.method public final invoke(Landroidx/compose/runtime/Composer;I)V
    .registers 12
    .param p1, "$composer"    # Landroidx/compose/runtime/Composer;
    .param p2, "$changed"    # I

    sget-object v0, Landroidx/compose/material/icons/Icons;->INSTANCE:Landroidx/compose/material/icons/Icons;
    invoke-virtual {v0}, Landroidx/compose/material/icons/Icons;->getDefault()Landroidx/compose/material/icons/Icons$Filled;
    move-result-object v0
    invoke-static {v0}, Landroidx/compose/material/icons/filled/SettingsKt;->getSettings(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;
    move-result-object v1

    sget-object v0, Landroidx/compose/material3/MaterialTheme;->INSTANCE:Landroidx/compose/material3/MaterialTheme;
    sget v2, Landroidx/compose/material3/MaterialTheme;->$stable:I
    invoke-virtual {v0, p1, v2}, Landroidx/compose/material3/MaterialTheme;->getColorScheme(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/ColorScheme;
    move-result-object v0
    invoke-virtual {v0}, Landroidx/compose/material3/ColorScheme;->getPrimary-0d7_KjU()J
    move-result-wide v4

    const-string v2, "C\u1ea5u h\u00ecnh addon"
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
    invoke-virtual {p0, p1, v0}, Lcom/streamsync/app/data/addon/AddonConfigIconLambda;->invoke(Landroidx/compose/runtime/Composer;I)V
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    return-object v0
.end method
