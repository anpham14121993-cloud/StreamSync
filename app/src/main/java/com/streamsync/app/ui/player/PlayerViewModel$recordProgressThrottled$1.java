package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.repository.SyncRepository;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$recordProgressThrottled$1", f = "PlayerScreen.kt", i = {}, l = {1648}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$recordProgressThrottled$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $durationMs;
    final /* synthetic */ long $positionMs;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$recordProgressThrottled$1(PlayerViewModel playerViewModel, long j, long j2, Continuation<? super PlayerViewModel$recordProgressThrottled$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$positionMs = j;
        this.$durationMs = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$recordProgressThrottled$1 playerViewModel$recordProgressThrottled$1 = new PlayerViewModel$recordProgressThrottled$1(this.this$0, this.$positionMs, this.$durationMs, continuation);
        playerViewModel$recordProgressThrottled$1.L$0 = obj;
        return playerViewModel$recordProgressThrottled$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$recordProgressThrottled$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Throwable th;
        SyncRepository syncRepository;
        String str;
        StreamSource streamSource;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PlayerViewModel playerViewModel = this.this$0;
                long j = this.$positionMs;
                long j2 = this.$durationMs;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    syncRepository = playerViewModel.syncRepo;
                    str = playerViewModel.mediaId;
                    streamSource = playerViewModel.currentSource;
                    this.label = 1;
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m6717constructorimpl(ResultKt.createFailure(th));
                    return Unit.INSTANCE;
                }
                if (syncRepository.recordProgress(str, j, j2, streamSource, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Result.m6717constructorimpl(Unit.INSTANCE);
                return Unit.INSTANCE;
            case 1:
                try {
                    ResultKt.throwOnFailure($result);
                    Result.m6717constructorimpl(Unit.INSTANCE);
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion22 = Result.INSTANCE;
                    Result.m6717constructorimpl(ResultKt.createFailure(th));
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
