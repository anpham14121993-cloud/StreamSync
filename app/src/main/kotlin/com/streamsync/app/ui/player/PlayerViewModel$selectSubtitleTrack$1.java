package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.PlaybackSettingsStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$selectSubtitleTrack$1", f = "PlayerScreen.kt", i = {}, l = {804}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$selectSubtitleTrack$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SubtitleTrack $track;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$selectSubtitleTrack$1(PlayerViewModel playerViewModel, SubtitleTrack subtitleTrack, Continuation<? super PlayerViewModel$selectSubtitleTrack$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$track = subtitleTrack;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerViewModel$selectSubtitleTrack$1(this.this$0, this.$track, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$selectSubtitleTrack$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        PlaybackSettingsStore playbackSettingsStore;
        PlaybackSettingsStore playbackSettingsStore2;
        Object loadSubtitleTrack;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                playbackSettingsStore = this.this$0.settingsStore;
                playbackSettingsStore2 = this.this$0.settingsStore;
                List preferred = CollectionsKt.listOfNotNull((Object[]) new String[]{playbackSettingsStore.getPreferredSubtitleLanguage1(), playbackSettingsStore2.getPreferredSubtitleLanguage2()});
                PlayerViewModel playerViewModel = this.this$0;
                SubtitleTrack subtitleTrack = this.$track;
                String str = (String) CollectionsKt.firstOrNull(preferred);
                if (str == null) {
                    str = this.$track.getLanguageCode();
                }
                this.label = 1;
                loadSubtitleTrack = playerViewModel.loadSubtitleTrack(subtitleTrack, str, this);
                if (loadSubtitleTrack != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
