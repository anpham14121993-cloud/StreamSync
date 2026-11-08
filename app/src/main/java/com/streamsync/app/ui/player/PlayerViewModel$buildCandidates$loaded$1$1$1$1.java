package com.streamsync.app.ui.player;

import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$buildCandidates$loaded$1$1$1$1", f = "PlayerScreen.kt", i = {0, 1, 1}, l = {1554, 5043}, m = "invokeSuspend", n = {"$this$async", TranslateLanguage.ITALIAN, "$this$withPermit$iv"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$buildCandidates$loaded$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LocalAutoSyncEngine.SubtitleTrackCandidate>, Object> {
    final /* synthetic */ Semaphore $gate;
    final /* synthetic */ SubtitleTrack $track;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$buildCandidates$loaded$1$1$1$1(SubtitleTrack subtitleTrack, PlayerViewModel playerViewModel, Semaphore semaphore, Continuation<? super PlayerViewModel$buildCandidates$loaded$1$1$1$1> continuation) {
        super(2, continuation);
        this.$track = subtitleTrack;
        this.this$0 = playerViewModel;
        this.$gate = semaphore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$buildCandidates$loaded$1$1$1$1 playerViewModel$buildCandidates$loaded$1$1$1$1 = new PlayerViewModel$buildCandidates$loaded$1$1$1$1(this.$track, this.this$0, this.$gate, continuation);
        playerViewModel$buildCandidates$loaded$1$1$1$1.L$0 = obj;
        return playerViewModel$buildCandidates$loaded$1$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LocalAutoSyncEngine.SubtitleTrackCandidate> continuation) {
        return ((PlayerViewModel$buildCandidates$loaded$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0092 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$buildCandidates$loaded$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
