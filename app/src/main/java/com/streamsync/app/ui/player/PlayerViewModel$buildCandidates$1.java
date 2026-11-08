package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel", f = "PlayerScreen.kt", i = {0}, l = {1550}, m = "buildCandidates", n = {"out"}, s = {"L$0"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$buildCandidates$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$buildCandidates$1(PlayerViewModel playerViewModel, Continuation<? super PlayerViewModel$buildCandidates$1> continuation) {
        super(continuation);
        this.this$0 = playerViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object buildCandidates;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        buildCandidates = this.this$0.buildCandidates(this);
        return buildCandidates;
    }
}
