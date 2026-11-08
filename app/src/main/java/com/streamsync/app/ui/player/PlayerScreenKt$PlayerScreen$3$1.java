package com.streamsync.app.ui.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerScreenKt$PlayerScreen$3$1", f = "PlayerScreen.kt", i = {}, l = {2348}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerScreenKt$PlayerScreen$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $overlayVisible$delegate;
    final /* synthetic */ MutableState<Boolean> $syncBannerVisible$delegate;
    final /* synthetic */ State<SubtitleSyncUiState> $syncState$delegate;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$3$1(State<SubtitleSyncUiState> state, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, Continuation<? super PlayerScreenKt$PlayerScreen$3$1> continuation) {
        super(2, continuation);
        this.$syncState$delegate = state;
        this.$syncBannerVisible$delegate = mutableState;
        this.$overlayVisible$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerScreenKt$PlayerScreen$3$1(this.$syncState$delegate, this.$syncBannerVisible$delegate, this.$overlayVisible$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerScreenKt$PlayerScreen$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SubtitleSyncUiState PlayerScreen$lambda$18;
        PlayerScreenKt$PlayerScreen$3$1 playerScreenKt$PlayerScreen$3$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PlayerScreen$lambda$18 = PlayerScreenKt.PlayerScreen$lambda$18(this.$syncState$delegate);
                String message = PlayerScreen$lambda$18.getMessage();
                String str = message;
                if (!(str == null || StringsKt.isBlank(str))) {
                    PlayerScreenKt.PlayerScreen$lambda$27(this.$syncBannerVisible$delegate, true);
                    PlayerScreenKt.PlayerScreen$lambda$30(this.$overlayVisible$delegate, true);
                    this.label = 1;
                    if (DelayKt.delay(SubtitleSyncMatcher.SINGLE_CUE_MAX_MS, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    playerScreenKt$PlayerScreen$3$1 = this;
                    PlayerScreenKt.PlayerScreen$lambda$27(playerScreenKt$PlayerScreen$3$1.$syncBannerVisible$delegate, false);
                }
                return Unit.INSTANCE;
            case 1:
                playerScreenKt$PlayerScreen$3$1 = this;
                ResultKt.throwOnFailure($result);
                PlayerScreenKt.PlayerScreen$lambda$27(playerScreenKt$PlayerScreen$3$1.$syncBannerVisible$delegate, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
