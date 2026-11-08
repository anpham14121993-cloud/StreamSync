package com.streamsync.app.ui.settings;

import androidx.compose.runtime.MutableState;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.BufferSteps;
import com.streamsync.app.settings.PlaybackSettingsStore;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerSettingsScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$1$1", f = "PlayerSettingsScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes17.dex */
public final class PlayerSettingsScreenKt$PlayerSettingsScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Integer> $maxBuffer$delegate;
    final /* synthetic */ MutableState<Integer> $minBuffer$delegate;
    final /* synthetic */ PlaybackSettingsStore $store;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerSettingsScreenKt$PlayerSettingsScreen$1$1(PlaybackSettingsStore playbackSettingsStore, MutableState<Integer> mutableState, MutableState<Integer> mutableState2, Continuation<? super PlayerSettingsScreenKt$PlayerSettingsScreen$1$1> continuation) {
        super(2, continuation);
        this.$store = playbackSettingsStore;
        this.$maxBuffer$delegate = mutableState;
        this.$minBuffer$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerSettingsScreenKt$PlayerSettingsScreen$1$1(this.$store, this.$maxBuffer$delegate, this.$minBuffer$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerSettingsScreenKt$PlayerSettingsScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int PlayerSettingsScreen$lambda$23;
        int PlayerSettingsScreen$lambda$20;
        int PlayerSettingsScreen$lambda$202;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                PlayerSettingsScreen$lambda$23 = PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$23(this.$maxBuffer$delegate);
                PlayerSettingsScreen$lambda$20 = PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$20(this.$minBuffer$delegate);
                if (PlayerSettingsScreen$lambda$23 <= PlayerSettingsScreen$lambda$20) {
                    BufferSteps bufferSteps = BufferSteps.INSTANCE;
                    PlayerSettingsScreen$lambda$202 = PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$20(this.$minBuffer$delegate);
                    int fixed = bufferSteps.normalizeMaxBufferSeconds(PlayerSettingsScreen$lambda$202 + 10);
                    PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$24(this.$maxBuffer$delegate, fixed);
                    this.$store.setMaxBufferSeconds(fixed);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
