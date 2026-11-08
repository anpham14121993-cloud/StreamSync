package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Delay.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "value", "Lkotlinx/coroutines/channels/ChannelResult;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1", f = "Delay.kt", i = {0}, l = {395}, m = "invokeSuspend", n = {"$this$onSuccess_u2dWpGqRn0$iv"}, s = {"L$0"})
/* loaded from: classes25.dex */
final class FlowKt__DelayKt$timeoutInternal$1$1$1<T> extends SuspendLambda implements Function2<ChannelResult<? extends T>, Continuation<? super Boolean>, Object> {
    final /* synthetic */ FlowCollector<T> $downStream;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__DelayKt$timeoutInternal$1$1$1(FlowCollector<? super T> flowCollector, Continuation<? super FlowKt__DelayKt$timeoutInternal$1$1$1> continuation) {
        super(2, continuation);
        this.$downStream = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__DelayKt$timeoutInternal$1$1$1 flowKt__DelayKt$timeoutInternal$1$1$1 = new FlowKt__DelayKt$timeoutInternal$1$1$1(this.$downStream, continuation);
        flowKt__DelayKt$timeoutInternal$1$1$1.L$0 = obj;
        return flowKt__DelayKt$timeoutInternal$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Boolean> continuation) {
        return m8258invokeWpGqRn0(((ChannelResult) obj).getHolder(), continuation);
    }

    /* renamed from: invoke-WpGqRn0, reason: not valid java name */
    public final Object m8258invokeWpGqRn0(Object obj, Continuation<? super Boolean> continuation) {
        return ((FlowKt__DelayKt$timeoutInternal$1$1$1) create(ChannelResult.m8230boximpl(obj), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005c  */
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
            r2 = 1
            switch(r1) {
                case 0: goto L1b;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L12:
            r0 = r8
            r1 = 0
            r3 = 0
            java.lang.Object r4 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L40
        L1b:
            kotlin.ResultKt.throwOnFailure(r9)
            r1 = r8
            java.lang.Object r3 = r1.L$0
            kotlinx.coroutines.channels.ChannelResult r3 = (kotlinx.coroutines.channels.ChannelResult) r3
            java.lang.Object r3 = r3.getHolder()
            kotlinx.coroutines.flow.FlowCollector<T> r4 = r1.$downStream
            r5 = 0
            boolean r6 = r3 instanceof kotlinx.coroutines.channels.ChannelResult.Failed
            if (r6 != 0) goto L43
            r6 = r3
            r7 = 0
            r1.L$0 = r3
            r1.label = r2
            java.lang.Object r4 = r4.emit(r6, r1)
            if (r4 != r0) goto L3c
            return r0
        L3c:
            r0 = r1
            r4 = r3
            r1 = r5
            r3 = r7
        L40:
            r5 = r1
            r3 = r4
            r1 = r0
        L43:
            r0 = r3
            r3 = 0
            boolean r4 = r0 instanceof kotlinx.coroutines.channels.ChannelResult.Closed
            if (r4 == 0) goto L5c
            java.lang.Throwable r2 = kotlinx.coroutines.channels.ChannelResult.m8234exceptionOrNullimpl(r0)
            r4 = 0
            if (r2 != 0) goto L59
            r5 = 0
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        L59:
            r5 = r2
            r6 = 0
            throw r5
        L5c:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
