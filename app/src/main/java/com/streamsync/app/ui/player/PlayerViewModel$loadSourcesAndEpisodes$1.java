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
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$loadSourcesAndEpisodes$1", f = "PlayerScreen.kt", i = {0, 1, 1}, l = {1041, 1050, 1075, 1087}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "detail"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$loadSourcesAndEpisodes$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$loadSourcesAndEpisodes$1(PlayerViewModel playerViewModel, Continuation<? super PlayerViewModel$loadSourcesAndEpisodes$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$loadSourcesAndEpisodes$1 playerViewModel$loadSourcesAndEpisodes$1 = new PlayerViewModel$loadSourcesAndEpisodes$1(this.this$0, continuation);
        playerViewModel$loadSourcesAndEpisodes$1.L$0 = obj;
        return playerViewModel$loadSourcesAndEpisodes$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$loadSourcesAndEpisodes$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000b. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0161 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00d8 A[Catch: all -> 0x0239, TryCatch #16 {all -> 0x0239, blocks: (B:130:0x00cc, B:133:0x00d3, B:135:0x00d8, B:137:0x00e2, B:139:0x00ea, B:141:0x00f8), top: B:129:0x00cc }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x017f A[Catch: all -> 0x0234, TryCatch #14 {all -> 0x0234, blocks: (B:108:0x0161, B:68:0x017b, B:70:0x017f, B:72:0x0187, B:74:0x018f, B:76:0x0195, B:77:0x019b, B:79:0x01a1), top: B:107:0x0161 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0187 A[Catch: all -> 0x0234, TryCatch #14 {all -> 0x0234, blocks: (B:108:0x0161, B:68:0x017b, B:70:0x017f, B:72:0x0187, B:74:0x018f, B:76:0x0195, B:77:0x019b, B:79:0x01a1), top: B:107:0x0161 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x018f A[Catch: all -> 0x0234, TryCatch #14 {all -> 0x0234, blocks: (B:108:0x0161, B:68:0x017b, B:70:0x017f, B:72:0x0187, B:74:0x018f, B:76:0x0195, B:77:0x019b, B:79:0x01a1), top: B:107:0x0161 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a1 A[Catch: all -> 0x0234, TRY_LEAVE, TryCatch #14 {all -> 0x0234, blocks: (B:108:0x0161, B:68:0x017b, B:70:0x017f, B:72:0x0187, B:74:0x018f, B:76:0x0195, B:77:0x019b, B:79:0x01a1), top: B:107:0x0161 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r29) {
        /*
            Method dump skipped, instructions count: 702
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$loadSourcesAndEpisodes$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
