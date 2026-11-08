package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$loadSourcesForEpisode$1", f = "PlayerScreen.kt", i = {0}, l = {1187, 1192}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$loadSourcesForEpisode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $episodeId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$loadSourcesForEpisode$1(PlayerViewModel playerViewModel, String str, Continuation<? super PlayerViewModel$loadSourcesForEpisode$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$episodeId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$loadSourcesForEpisode$1 playerViewModel$loadSourcesForEpisode$1 = new PlayerViewModel$loadSourcesForEpisode$1(this.this$0, this.$episodeId, continuation);
        playerViewModel$loadSourcesForEpisode$1.L$0 = obj;
        return playerViewModel$loadSourcesForEpisode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$loadSourcesForEpisode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:15:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b3 A[Catch: all -> 0x00f2, TryCatch #1 {all -> 0x00f2, blocks: (B:38:0x00a9, B:40:0x00b3, B:42:0x00bb, B:43:0x00c1), top: B:37:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00bb A[Catch: all -> 0x00f2, TryCatch #1 {all -> 0x00f2, blocks: (B:38:0x00a9, B:40:0x00b3, B:42:0x00bb, B:43:0x00c1), top: B:37:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b8  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$loadSourcesForEpisode$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
