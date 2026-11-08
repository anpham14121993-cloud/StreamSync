package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel", f = "PlayerScreen.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {899, 915}, m = "loadSubtitleTrack", n = {"this", "track", "translateTarget", "this", "track", "translateTarget", "raw"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$loadSubtitleTrack$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$loadSubtitleTrack$1(PlayerViewModel playerViewModel, Continuation<? super PlayerViewModel$loadSubtitleTrack$1> continuation) {
        super(continuation);
        this.this$0 = playerViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object loadSubtitleTrack;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        loadSubtitleTrack = this.this$0.loadSubtitleTrack(null, null, this);
        return loadSubtitleTrack;
    }
}
