.class public Lcom/streamsync/app/HomeBackCallback;
.super Landroidx/activity/OnBackPressedCallback;

.field private final activity:Lcom/streamsync/app/MainActivity;

.method public constructor <init>(Lcom/streamsync/app/MainActivity;)V
    .registers 3
    const/4 v0, 0x1
    invoke-direct {p0, v0}, Landroidx/activity/OnBackPressedCallback;-><init>(Z)V
    iput-object p1, p0, Lcom/streamsync/app/HomeBackCallback;->activity:Lcom/streamsync/app/MainActivity;
    return-void
.end method

.method public handleOnBackPressed()V
    .registers 4

    .prologue
    sget-object v0, Lcom/streamsync/app/MainActivity;->sNavController:Landroidx/navigation/NavHostController;
    if-eqz v0, :cond_fallback

    invoke-virtual {v0}, Landroidx/navigation/NavHostController;->getPreviousBackStackEntry()Landroidx/navigation/NavBackStackEntry;
    move-result-object v1
    if-eqz v1, :cond_dialog

    invoke-virtual {v0}, Landroidx/navigation/NavHostController;->popBackStack()Z
    return-void

    :cond_dialog
    iget-object v0, p0, Lcom/streamsync/app/HomeBackCallback;->activity:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_end
    new-instance v1, Landroid/app/AlertDialog$Builder;
    invoke-direct {v1, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V
    const-string v2, "Tho\u00e1t \u1ee9ng d\u1ee5ng"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    const-string v2, "B\u1ea1n c\u00f3 ch\u1eafc ch\u1eafn mu\u1ed1n tho\u00e1t \u1ee9ng d\u1ee5ng kh\u00f4ng?"
    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    const-string v2, "\u0110\u1ed3ng \u00fd"
    new-instance v3, Lcom/streamsync/app/ExitConfirmListener;
    invoke-direct {v3, v0}, Lcom/streamsync/app/ExitConfirmListener;-><init>(Landroid/app/Activity;)V
    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    const-string v2, "H\u1ee7y"
    const/4 v3, 0x0
    invoke-virtual {v1, v2, v3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;
    return-void

    :cond_fallback
    iget-object v0, p0, Lcom/streamsync/app/HomeBackCallback;->activity:Lcom/streamsync/app/MainActivity;
    if-eqz v0, :cond_end
    const/4 v1, 0x0
    invoke-virtual {p0, v1}, Landroidx/activity/OnBackPressedCallback;->setEnabled(Z)V
    invoke-virtual {v0}, Lcom/streamsync/app/MainActivity;->getOnBackPressedDispatcher()Landroidx/activity/OnBackPressedDispatcher;
    move-result-object v1
    invoke-virtual {v1}, Landroidx/activity/OnBackPressedDispatcher;->onBackPressed()V
    const/4 v1, 0x1
    invoke-virtual {p0, v1}, Landroidx/activity/OnBackPressedCallback;->setEnabled(Z)V

    :cond_end
    return-void
.end method
