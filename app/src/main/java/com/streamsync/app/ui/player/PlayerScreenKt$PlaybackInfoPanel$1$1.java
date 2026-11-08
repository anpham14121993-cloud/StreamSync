package com.streamsync.app.ui.player;

import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.ExoPlayer;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerScreenKt$PlaybackInfoPanel$1$1", f = "PlayerScreen.kt", i = {}, l = {4907}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerScreenKt$PlaybackInfoPanel$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<PlaybackInfo> $info$delegate;
    final /* synthetic */ ExoPlayer $player;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlaybackInfoPanel$1$1(ExoPlayer exoPlayer, MutableState<PlaybackInfo> mutableState, Continuation<? super PlayerScreenKt$PlaybackInfoPanel$1$1> continuation) {
        super(2, continuation);
        this.$player = exoPlayer;
        this.$info$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerScreenKt$PlaybackInfoPanel$1$1(this.$player, this.$info$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerScreenKt$PlaybackInfoPanel$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        PlayerScreenKt$PlaybackInfoPanel$1$1 playerScreenKt$PlaybackInfoPanel$1$1;
        PlaybackInfo snapshotInfo;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                playerScreenKt$PlaybackInfoPanel$1$1 = this;
                break;
            case 1:
                playerScreenKt$PlaybackInfoPanel$1$1 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            MutableState<PlaybackInfo> mutableState = playerScreenKt$PlaybackInfoPanel$1$1.$info$delegate;
            snapshotInfo = PlayerScreenKt.snapshotInfo(playerScreenKt$PlaybackInfoPanel$1$1.$player);
            mutableState.setValue(snapshotInfo);
            playerScreenKt$PlaybackInfoPanel$1$1.label = 1;
        } while (DelayKt.delay(700L, playerScreenKt$PlaybackInfoPanel$1$1) != coroutine_suspended);
        return coroutine_suspended;
    }
}
