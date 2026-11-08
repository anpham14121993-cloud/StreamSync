package com.streamsync.app.ui.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
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
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerScreenKt$PlayerScreen$13$1", f = "PlayerScreen.kt", i = {}, l = {2762}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerScreenKt$PlayerScreen$13$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Long> $currentPositionMs$delegate;
    final /* synthetic */ MutableState<Long> $durationMs$delegate;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ State<Long> $offsetMs$delegate;
    final /* synthetic */ PlayerCueIndex $playerCueIndex;
    final /* synthetic */ MutableState<String> $subtitleText$delegate;
    final /* synthetic */ PlayerViewModel $viewModel;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$13$1(ExoPlayer exoPlayer, PlayerCueIndex playerCueIndex, PlayerViewModel playerViewModel, MutableState<Long> mutableState, MutableState<Long> mutableState2, State<Long> state, MutableState<String> mutableState3, Continuation<? super PlayerScreenKt$PlayerScreen$13$1> continuation) {
        super(2, continuation);
        this.$exoPlayer = exoPlayer;
        this.$playerCueIndex = playerCueIndex;
        this.$viewModel = playerViewModel;
        this.$currentPositionMs$delegate = mutableState;
        this.$durationMs$delegate = mutableState2;
        this.$offsetMs$delegate = state;
        this.$subtitleText$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerScreenKt$PlayerScreen$13$1(this.$exoPlayer, this.$playerCueIndex, this.$viewModel, this.$currentPositionMs$delegate, this.$durationMs$delegate, this.$offsetMs$delegate, this.$subtitleText$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerScreenKt$PlayerScreen$13$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        PlayerScreenKt$PlayerScreen$13$1 playerScreenKt$PlayerScreen$13$1;
        long PlayerScreen$lambda$33;
        long PlayerScreen$lambda$17;
        long PlayerScreen$lambda$36;
        long PlayerScreen$lambda$332;
        long PlayerScreen$lambda$362;
        long PlayerScreen$lambda$333;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                playerScreenKt$PlayerScreen$13$1 = this;
                break;
            case 1:
                playerScreenKt$PlayerScreen$13$1 = this;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            PlayerScreenKt.PlayerScreen$lambda$34(playerScreenKt$PlayerScreen$13$1.$currentPositionMs$delegate, playerScreenKt$PlayerScreen$13$1.$exoPlayer.getCurrentPosition());
            PlayerScreenKt.PlayerScreen$lambda$37(playerScreenKt$PlayerScreen$13$1.$durationMs$delegate, RangesKt.coerceAtLeast(playerScreenKt$PlayerScreen$13$1.$exoPlayer.getDuration(), 0L));
            PlayerScreen$lambda$33 = PlayerScreenKt.PlayerScreen$lambda$33(playerScreenKt$PlayerScreen$13$1.$currentPositionMs$delegate);
            PlayerScreen$lambda$17 = PlayerScreenKt.PlayerScreen$lambda$17(playerScreenKt$PlayerScreen$13$1.$offsetMs$delegate);
            long adjusted = PlayerScreen$lambda$33 - PlayerScreen$lambda$17;
            MutableState<String> mutableState = playerScreenKt$PlayerScreen$13$1.$subtitleText$delegate;
            String textAt = playerScreenKt$PlayerScreen$13$1.$playerCueIndex.textAt(adjusted);
            if (textAt == null) {
                PlayerViewModel playerViewModel = playerScreenKt$PlayerScreen$13$1.$viewModel;
                PlayerScreen$lambda$333 = PlayerScreenKt.PlayerScreen$lambda$33(playerScreenKt$PlayerScreen$13$1.$currentPositionMs$delegate);
                textAt = playerViewModel.activeCueText(PlayerScreen$lambda$333);
            }
            mutableState.setValue(textAt);
            PlayerScreen$lambda$36 = PlayerScreenKt.PlayerScreen$lambda$36(playerScreenKt$PlayerScreen$13$1.$durationMs$delegate);
            if (PlayerScreen$lambda$36 > 0) {
                PlayerViewModel playerViewModel2 = playerScreenKt$PlayerScreen$13$1.$viewModel;
                PlayerScreen$lambda$332 = PlayerScreenKt.PlayerScreen$lambda$33(playerScreenKt$PlayerScreen$13$1.$currentPositionMs$delegate);
                PlayerScreen$lambda$362 = PlayerScreenKt.PlayerScreen$lambda$36(playerScreenKt$PlayerScreen$13$1.$durationMs$delegate);
                playerViewModel2.onTick(PlayerScreen$lambda$332, PlayerScreen$lambda$362);
            }
            playerScreenKt$PlayerScreen$13$1.label = 1;
        } while (DelayKt.delay(200L, playerScreenKt$PlayerScreen$13$1) != coroutine_suspended);
        return coroutine_suspended;
    }
}
