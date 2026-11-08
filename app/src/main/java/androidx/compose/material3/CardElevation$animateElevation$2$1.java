package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
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
/* compiled from: Card.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "androidx.compose.material3.CardElevation$animateElevation$2$1", f = "Card.kt", i = {}, l = {732, 741}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class CardElevation$animateElevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ CardElevation this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, CardElevation cardElevation, Interaction interaction, Continuation<? super CardElevation$animateElevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = cardElevation;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CardElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CardElevation$animateElevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        float f;
        float f2;
        float f3;
        float f4;
        CardElevation$animateElevation$2$1 cardElevation$animateElevation$2$1;
        CardElevation$animateElevation$2$1 cardElevation$animateElevation$2$12;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!Dp.m5741equalsimpl0(this.$animatable.getTargetValue().m5750unboximpl(), this.$target)) {
                    if (!this.$enabled) {
                        this.label = 1;
                        if (this.$animatable.snapTo(Dp.m5734boximpl(this.$target), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        cardElevation$animateElevation$2$12 = this;
                    } else {
                        float m5750unboximpl = this.$animatable.getTargetValue().m5750unboximpl();
                        f = this.this$0.pressedElevation;
                        DragInteraction.Start start = null;
                        if (Dp.m5741equalsimpl0(m5750unboximpl, f)) {
                            start = new PressInteraction.Press(Offset.INSTANCE.m3184getZeroF1C5BW0(), null);
                        } else {
                            f2 = this.this$0.hoveredElevation;
                            if (Dp.m5741equalsimpl0(m5750unboximpl, f2)) {
                                start = new HoverInteraction.Enter();
                            } else {
                                f3 = this.this$0.focusedElevation;
                                if (Dp.m5741equalsimpl0(m5750unboximpl, f3)) {
                                    start = new FocusInteraction.Focus();
                                } else {
                                    f4 = this.this$0.draggedElevation;
                                    if (Dp.m5741equalsimpl0(m5750unboximpl, f4)) {
                                        start = new DragInteraction.Start();
                                    }
                                }
                            }
                        }
                        Interaction interaction = start;
                        this.label = 2;
                        if (ElevationKt.m1542animateElevationrAjV9yQ(this.$animatable, this.$target, interaction, this.$interaction, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        cardElevation$animateElevation$2$1 = this;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                cardElevation$animateElevation$2$12 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 2:
                cardElevation$animateElevation$2$1 = this;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
