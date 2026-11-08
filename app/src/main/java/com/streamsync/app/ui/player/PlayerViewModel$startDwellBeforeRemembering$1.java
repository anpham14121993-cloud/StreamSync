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
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$startDwellBeforeRemembering$1", f = "PlayerScreen.kt", i = {0}, l = {1794}, m = "invokeSuspend", n = {"waited"}, s = {"J$0"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$startDwellBeforeRemembering$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$startDwellBeforeRemembering$1(PlayerViewModel playerViewModel, Continuation<? super PlayerViewModel$startDwellBeforeRemembering$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerViewModel$startDwellBeforeRemembering$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$startDwellBeforeRemembering$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x003a -> B:7:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            switch(r1) {
                case 0: goto L18;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L11:
            r1 = r8
            long r2 = r1.J$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L3d
        L18:
            kotlin.ResultKt.throwOnFailure(r9)
            r1 = r8
            r2 = 0
        L1e:
            com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
            long r4 = com.streamsync.app.ui.player.PlayerViewModel.access$getRememberDwellMs$p(r4)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L57
            com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
            long r4 = com.streamsync.app.ui.player.PlayerViewModel.access$getRememberTickMs$p(r4)
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r1.J$0 = r2
            r7 = 1
            r1.label = r7
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r6)
            if (r4 != r0) goto L3d
            return r0
        L3d:
            com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
            kotlinx.coroutines.flow.MutableStateFlow r4 = com.streamsync.app.ui.player.PlayerViewModel.access$get_isPlaying$p(r4)
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L1e
            com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
            long r4 = com.streamsync.app.ui.player.PlayerViewModel.access$getRememberTickMs$p(r4)
            long r2 = r2 + r4
            goto L1e
        L57:
            com.streamsync.app.ui.player.PlayerViewModel r0 = r1.this$0
            com.streamsync.app.subtitle.SubtitleOffsetStore r0 = com.streamsync.app.ui.player.PlayerViewModel.access$getOffsetStore$p(r0)
            com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
            java.lang.String r4 = com.streamsync.app.ui.player.PlayerViewModel.access$getMediaId$p(r4)
            com.streamsync.app.ui.player.PlayerViewModel r5 = r1.this$0
            long r5 = com.streamsync.app.ui.player.PlayerViewModel.access$getRememberAtMs$p(r5)
            r0.set(r4, r5)
            com.streamsync.app.util.FileLog r0 = com.streamsync.app.util.FileLog.INSTANCE
            com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
            long r4 = com.streamsync.app.ui.player.PlayerViewModel.access$getRememberDwellMs$p(r4)
            r6 = 1000(0x3e8, float:1.401E-42)
            long r6 = (long) r6
            long r4 = r4 / r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Auto sync: đã xem đủ "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r4 = r6.append(r4)
            java.lang.String r5 = "s, ghi nhớ offset"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r5 = 2
            r6 = 0
            com.streamsync.app.util.FileLog.d$default(r0, r4, r6, r5, r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$startDwellBeforeRemembering$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
