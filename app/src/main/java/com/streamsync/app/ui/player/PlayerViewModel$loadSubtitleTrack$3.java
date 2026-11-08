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
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$loadSubtitleTrack$3", f = "PlayerScreen.kt", i = {}, l = {951}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$loadSubtitleTrack$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$loadSubtitleTrack$3(PlayerViewModel playerViewModel, Continuation<? super PlayerViewModel$loadSubtitleTrack$3> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$loadSubtitleTrack$3 playerViewModel$loadSubtitleTrack$3 = new PlayerViewModel$loadSubtitleTrack$3(this.this$0, continuation);
        playerViewModel$loadSubtitleTrack$3.L$0 = obj;
        return playerViewModel$loadSubtitleTrack$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$loadSubtitleTrack$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L19;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L11:
            r0 = r7
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L17
            goto L39
        L17:
            r1 = move-exception
            goto L48
        L19:
            kotlin.ResultKt.throwOnFailure(r8)
            r1 = r7
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            com.streamsync.app.ui.player.PlayerViewModel r3 = r1.this$0
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L44
            r2 = 0
            com.streamsync.app.subtitle.SubtitleTranslator r3 = com.streamsync.app.ui.player.PlayerViewModel.access$getTranslator$p(r3)     // Catch: java.lang.Throwable -> L44
            if (r3 == 0) goto L3c
            r4 = 1
            r1.label = r4     // Catch: java.lang.Throwable -> L44
            r4 = 0
            java.lang.Object r3 = r3.ensureModelDownloaded(r4, r1)     // Catch: java.lang.Throwable -> L44
            if (r3 != r0) goto L37
            return r0
        L37:
            r0 = r1
            r1 = r2
        L39:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L17
            goto L3f
        L3c:
            r0 = 0
            r2 = r0
            r0 = r1
        L3f:
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r2)     // Catch: java.lang.Throwable -> L17
            goto L52
        L44:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L48:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
        L52:
            com.streamsync.app.ui.player.PlayerViewModel r2 = r0.this$0
            java.lang.Throwable r1 = kotlin.Result.m6720exceptionOrNullimpl(r1)
            if (r1 == 0) goto L8b
            r3 = 0
            kotlinx.coroutines.flow.MutableStateFlow r2 = com.streamsync.app.ui.player.PlayerViewModel.access$get_subtitleError$p(r2)
            java.lang.String r4 = r1.getMessage()
            if (r4 != 0) goto L6d
            java.lang.Class r4 = r1.getClass()
            java.lang.String r4 = r4.getSimpleName()
        L6d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r5 = "Không tải được model dịch ("
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r4 = "). Đang hiện phụ đề gốc."
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r2.setValue(r1)
        L8b:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$loadSubtitleTrack$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
