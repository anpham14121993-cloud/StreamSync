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
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$startCaptureWatchdog$1", f = "PlayerScreen.kt", i = {}, l = {1595}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$startCaptureWatchdog$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $deadline;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$startCaptureWatchdog$1(PlayerViewModel playerViewModel, long j, Continuation<? super PlayerViewModel$startCaptureWatchdog$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$deadline = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerViewModel$startCaptureWatchdog$1(this.this$0, this.$deadline, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$startCaptureWatchdog$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L16;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L11:
            r1 = r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L63
        L16:
            kotlin.ResultKt.throwOnFailure(r7)
            r1 = r6
        L1a:
            com.streamsync.app.ui.player.PlayerViewModel r2 = r1.this$0
            com.streamsync.app.subtitle.audio.PlayerAudioCapture r2 = r2.getAudioCapture()
            boolean r2 = r2.isRunning()
            if (r2 == 0) goto L6e
            com.streamsync.app.ui.player.PlayerViewModel r2 = r1.this$0
            com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine r2 = r2.getAutoSyncEngine()
            com.streamsync.app.ui.player.PlayerViewModel r3 = r1.this$0
            com.streamsync.app.subtitle.audio.PlayerAudioCapture r3 = r3.getAudioCapture()
            long r3 = r3.getCapturedMs()
            r2.onCaptureProgress(r3)
            com.streamsync.app.ui.player.PlayerViewModel r2 = r1.this$0
            com.streamsync.app.subtitle.audio.PlayerAudioCapture r2 = r2.getAudioCapture()
            long r2 = r2.getCapturedMs()
            r4 = 20000(0x4e20, double:9.8813E-320)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L64
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r4 = r1.$deadline
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L54
            goto L64
        L54:
            r2 = r1
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 1
            r1.label = r3
            r3 = 200(0xc8, double:9.9E-322)
            java.lang.Object r2 = kotlinx.coroutines.DelayKt.delay(r3, r2)
            if (r2 != r0) goto L63
            return r0
        L63:
            goto L1a
        L64:
            com.streamsync.app.ui.player.PlayerViewModel r0 = r1.this$0
            com.streamsync.app.subtitle.audio.PlayerAudioCapture r0 = r0.getAudioCapture()
            r0.stop()
        L6e:
            com.streamsync.app.ui.player.PlayerViewModel r0 = r1.this$0
            com.streamsync.app.subtitle.audio.PlayerAudioCapture r0 = r0.getAudioCapture()
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L7f
            com.streamsync.app.ui.player.PlayerViewModel r0 = r1.this$0
            com.streamsync.app.ui.player.PlayerViewModel.access$finishAutoSync(r0)
        L7f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$startCaptureWatchdog$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
