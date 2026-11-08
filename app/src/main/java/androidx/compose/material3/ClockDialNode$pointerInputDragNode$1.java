package androidx.compose.material3;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.IntOffset;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TimePicker.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1", f = "TimePicker.kt", i = {}, l = {1292}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class ClockDialNode$pointerInputDragNode$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ClockDialNode this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClockDialNode$pointerInputDragNode$1(ClockDialNode clockDialNode, Continuation<? super ClockDialNode$pointerInputDragNode$1> continuation) {
        super(2, continuation);
        this.this$0 = clockDialNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClockDialNode$pointerInputDragNode$1 clockDialNode$pointerInputDragNode$1 = new ClockDialNode$pointerInputDragNode$1(this.this$0, continuation);
        clockDialNode$pointerInputDragNode$1.L$0 = obj;
        return clockDialNode$pointerInputDragNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((ClockDialNode$pointerInputDragNode$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object detectDragGestures;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                PointerInputScope $this$SuspendingPointerInputModifierNode = (PointerInputScope) this.L$0;
                final ClockDialNode clockDialNode = this.this$0;
                Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1.1
                    {
                        super(0);
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: TimePicker.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                    @DebugMetadata(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$1$1", f = "TimePicker.kt", i = {}, l = {1296, 1298}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$1$1, reason: invalid class name and collision with other inner class name */
                    /* loaded from: classes.dex */
                    public static final class C00421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        int label;
                        final /* synthetic */ ClockDialNode this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C00421(ClockDialNode clockDialNode, Continuation<? super C00421> continuation) {
                            super(2, continuation);
                            this.this$0 = clockDialNode;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00421(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C00421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            TimePickerState timePickerState;
                            TimePickerState timePickerState2;
                            TimePickerState timePickerState3;
                            C00421 c00421;
                            boolean z;
                            TimePickerState timePickerState4;
                            TimePickerState timePickerState5;
                            C00421 c004212;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    timePickerState = this.this$0.state;
                                    if (Selection.m1847equalsimpl0(timePickerState.m2175getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1851getHourJiIwxys())) {
                                        z = this.this$0.autoSwitchToMinute;
                                        if (z) {
                                            timePickerState4 = this.this$0.state;
                                            timePickerState4.m2178setSelectioniHAOin8$material3_release(Selection.INSTANCE.m1852getMinuteJiIwxys());
                                            timePickerState5 = this.this$0.state;
                                            this.label = 1;
                                            if (timePickerState5.animateToCurrent$material3_release(this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            c004212 = this;
                                            return Unit.INSTANCE;
                                        }
                                    }
                                    timePickerState2 = this.this$0.state;
                                    if (Selection.m1847equalsimpl0(timePickerState2.m2175getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1852getMinuteJiIwxys())) {
                                        timePickerState3 = this.this$0.state;
                                        this.label = 2;
                                        if (timePickerState3.settle(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        c00421 = this;
                                    }
                                    return Unit.INSTANCE;
                                case 1:
                                    c004212 = this;
                                    ResultKt.throwOnFailure($result);
                                    return Unit.INSTANCE;
                                case 2:
                                    c00421 = this;
                                    ResultKt.throwOnFailure($result);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        BuildersKt__Builders_commonKt.launch$default(ClockDialNode.this.getCoroutineScope(), null, null, new C00421(ClockDialNode.this, null), 3, null);
                    }
                };
                final ClockDialNode clockDialNode2 = this.this$0;
                this.label = 1;
                detectDragGestures = DragGestureDetectorKt.detectDragGestures($this$SuspendingPointerInputModifierNode, (r12 & 1) != 0 ? new Function1<Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m357invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m357invokek4lQ0M(long it) {
                    }
                } : null, (r12 & 2) != 0 ? new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$3
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                } : function0, (r12 & 4) != 0 ? new Function0<Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$4
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                } : null, new Function2<PointerInputChange, Offset, Unit>() { // from class: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Offset offset) {
                        m1349invokeUv8p0NA(pointerInputChange, offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: TimePicker.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                    @DebugMetadata(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2$1", f = "TimePicker.kt", i = {}, l = {1305}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2$1, reason: invalid class name */
                    /* loaded from: classes.dex */
                    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ long $dragAmount;
                        int label;
                        final /* synthetic */ ClockDialNode this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(ClockDialNode clockDialNode, long j, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.this$0 = clockDialNode;
                            this.$dragAmount = j;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.this$0, this.$dragAmount, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            float f;
                            float f2;
                            TimePickerState timePickerState;
                            float f3;
                            TimePickerState timePickerState2;
                            float f4;
                            TimePickerState timePickerState3;
                            float atan;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    ClockDialNode clockDialNode = this.this$0;
                                    f = clockDialNode.offsetX;
                                    clockDialNode.offsetX = f + Offset.m3168getXimpl(this.$dragAmount);
                                    ClockDialNode clockDialNode2 = this.this$0;
                                    f2 = clockDialNode2.offsetY;
                                    clockDialNode2.offsetY = f2 + Offset.m3169getYimpl(this.$dragAmount);
                                    timePickerState = this.this$0.state;
                                    f3 = this.this$0.offsetY;
                                    timePickerState2 = this.this$0.state;
                                    float m5865getYimpl = f3 - IntOffset.m5865getYimpl(timePickerState2.m2174getCenternOccac$material3_release());
                                    f4 = this.this$0.offsetX;
                                    timePickerState3 = this.this$0.state;
                                    atan = TimePickerKt.atan(m5865getYimpl, f4 - IntOffset.m5864getXimpl(timePickerState3.m2174getCenternOccac$material3_release()));
                                    this.label = 1;
                                    if (TimePickerState.update$material3_release$default(timePickerState, atan, false, this, 2, null) != coroutine_suspended) {
                                        break;
                                    } else {
                                        return coroutine_suspended;
                                    }
                                case 1:
                                    ResultKt.throwOnFailure($result);
                                    break;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: invoke-Uv8p0NA, reason: not valid java name */
                    public final void m1349invokeUv8p0NA(PointerInputChange pointerInputChange, long dragAmount) {
                        TimePickerState timePickerState;
                        float f;
                        float f2;
                        float maxDist;
                        BuildersKt__Builders_commonKt.launch$default(ClockDialNode.this.getCoroutineScope(), null, null, new AnonymousClass1(ClockDialNode.this, dragAmount, null), 3, null);
                        timePickerState = ClockDialNode.this.state;
                        f = ClockDialNode.this.offsetX;
                        f2 = ClockDialNode.this.offsetY;
                        maxDist = ClockDialNode.this.getMaxDist();
                        timePickerState.moveSelector$material3_release(f, f2, maxDist);
                    }
                }, this);
                if (detectDragGestures != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
