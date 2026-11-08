package com.streamsync.app.ui.player;

import androidx.compose.runtime.State;
import androidx.media3.common.MediaItem;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerScreenKt$PlayerScreen$11$1", f = "PlayerScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerScreenKt$PlayerScreen$11$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ State<String> $streamMimeType$delegate;
    final /* synthetic */ State<String> $streamUrl$delegate;
    final /* synthetic */ PlayerViewModel $viewModel;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$11$1(State<String> state, ExoPlayer exoPlayer, PlayerViewModel playerViewModel, State<String> state2, Continuation<? super PlayerScreenKt$PlayerScreen$11$1> continuation) {
        super(2, continuation);
        this.$streamUrl$delegate = state;
        this.$exoPlayer = exoPlayer;
        this.$viewModel = playerViewModel;
        this.$streamMimeType$delegate = state2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerScreenKt$PlayerScreen$11$1(this.$streamUrl$delegate, this.$exoPlayer, this.$viewModel, this.$streamMimeType$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerScreenKt$PlayerScreen$11$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String url;
        String it;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                url = PlayerScreenKt.PlayerScreen$lambda$9(this.$streamUrl$delegate);
                if (url != null) {
                    ExoPlayer exoPlayer = this.$exoPlayer;
                    PlayerViewModel playerViewModel = this.$viewModel;
                    State<String> state = this.$streamMimeType$delegate;
                    MediaItem.Builder $this$invokeSuspend_u24lambda_u242_u24lambda_u241 = new MediaItem.Builder().setUri(url);
                    it = PlayerScreenKt.PlayerScreen$lambda$11(state);
                    if (it != null) {
                        $this$invokeSuspend_u24lambda_u242_u24lambda_u241.setMimeType(it);
                    }
                    MediaItem item = $this$invokeSuspend_u24lambda_u242_u24lambda_u241.build();
                    Intrinsics.checkNotNullExpressionValue(item, "build(...)");
                    exoPlayer.setMediaItem(item);
                    exoPlayer.prepare();
                    exoPlayer.seekTo(playerViewModel.getResumePositionMs());
                    exoPlayer.setPlayWhenReady(true);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
