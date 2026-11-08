package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.SubtitleTranslator;
import java.util.List;
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
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$onTick$1", f = "PlayerScreen.kt", i = {}, l = {1695}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$onTick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $positionMs;
    final /* synthetic */ long $target;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$onTick$1(PlayerViewModel playerViewModel, long j, long j2, Continuation<? super PlayerViewModel$onTick$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$positionMs = j;
        this.$target = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$onTick$1 playerViewModel$onTick$1 = new PlayerViewModel$onTick$1(this.this$0, this.$positionMs, this.$target, continuation);
        playerViewModel$onTick$1.L$0 = obj;
        return playerViewModel$onTick$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$onTick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Throwable th;
        SubtitleTranslator subtitleTranslator;
        Unit unit;
        MutableStateFlow mutableStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PlayerViewModel playerViewModel = this.this$0;
                long j = this.$positionMs;
                long j2 = this.$target;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    subtitleTranslator = playerViewModel.translator;
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m6717constructorimpl(ResultKt.createFailure(th));
                    return Unit.INSTANCE;
                }
                if (subtitleTranslator == null) {
                    unit = null;
                    Result.m6717constructorimpl(unit);
                    return Unit.INSTANCE;
                }
                mutableStateFlow = playerViewModel._cues;
                this.label = 1;
                if (SubtitleTranslator.translateUpcoming$default(subtitleTranslator, (List) mutableStateFlow.getValue(), j - j2, 0L, this, 4, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                unit = Unit.INSTANCE;
                Result.m6717constructorimpl(unit);
                return Unit.INSTANCE;
            case 1:
                try {
                    ResultKt.throwOnFailure($result);
                    unit = Unit.INSTANCE;
                    Result.m6717constructorimpl(unit);
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
