package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$flushProgressNow$1", f = "PlayerScreen.kt", i = {}, l = {1681, 1682}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
final class PlayerViewModel$flushProgressNow$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $durationMs;
    final /* synthetic */ long $positionMs;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$flushProgressNow$1(PlayerViewModel playerViewModel, long j, long j2, Continuation<? super PlayerViewModel$flushProgressNow$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$positionMs = j;
        this.$durationMs = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$flushProgressNow$1 playerViewModel$flushProgressNow$1 = new PlayerViewModel$flushProgressNow$1(this.this$0, this.$positionMs, this.$durationMs, continuation);
        playerViewModel$flushProgressNow$1.L$0 = obj;
        return playerViewModel$flushProgressNow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$flushProgressNow$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0006. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.streamsync.app.ui.player.PlayerViewModel$flushProgressNow$1, kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.streamsync.app.data.repository.SyncRepository] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            switch(r1) {
                case 0: goto L23;
                case 1: goto L19;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L11:
            r0 = r13
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L17
            goto L62
        L17:
            r1 = move-exception
            goto L6c
        L19:
            r1 = r13
            r2 = 0
            java.lang.Object r3 = r1.L$0
            com.streamsync.app.ui.player.PlayerViewModel r3 = (com.streamsync.app.ui.player.PlayerViewModel) r3
            kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.lang.Throwable -> L68
            goto L4f
        L23:
            kotlin.ResultKt.throwOnFailure(r14)
            r1 = r13
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            com.streamsync.app.ui.player.PlayerViewModel r10 = r1.this$0
            long r4 = r1.$positionMs
            long r6 = r1.$durationMs
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L68
            r11 = 0
            com.streamsync.app.data.repository.SyncRepository r2 = com.streamsync.app.ui.player.PlayerViewModel.access$getSyncRepo$p(r10)     // Catch: java.lang.Throwable -> L68
            java.lang.String r3 = com.streamsync.app.ui.player.PlayerViewModel.access$getMediaId$p(r10)     // Catch: java.lang.Throwable -> L68
            com.streamsync.app.data.model.StreamSource r8 = com.streamsync.app.ui.player.PlayerViewModel.access$getCurrentSource$p(r10)     // Catch: java.lang.Throwable -> L68
            r1.L$0 = r10     // Catch: java.lang.Throwable -> L68
            r9 = 1
            r1.label = r9     // Catch: java.lang.Throwable -> L68
            r9 = r1
            java.lang.Object r2 = r2.recordProgress(r3, r4, r6, r8, r9)     // Catch: java.lang.Throwable -> L68
            if (r2 != r0) goto L4d
            return r0
        L4d:
            r3 = r10
            r2 = r11
        L4f:
            com.streamsync.app.data.repository.SyncRepository r3 = com.streamsync.app.ui.player.PlayerViewModel.access$getSyncRepo$p(r3)     // Catch: java.lang.Throwable -> L68
            r4 = 0
            r1.L$0 = r4     // Catch: java.lang.Throwable -> L68
            r4 = 2
            r1.label = r4     // Catch: java.lang.Throwable -> L68
            java.lang.Object r3 = r3.pushPending(r1)     // Catch: java.lang.Throwable -> L68
            if (r3 != r0) goto L60
            return r0
        L60:
            r0 = r1
            r1 = r2
        L62:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L17
            kotlin.Result.m6717constructorimpl(r1)     // Catch: java.lang.Throwable -> L17
            goto L75
        L68:
            r0 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
        L6c:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            kotlin.Result.m6717constructorimpl(r1)
        L75:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$flushProgressNow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
