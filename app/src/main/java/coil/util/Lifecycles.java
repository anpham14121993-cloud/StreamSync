package coil.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: Lifecycles.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0081@¢\u0006\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¨\u0006\u0007"}, d2 = {"awaitStarted", "", "Landroidx/lifecycle/Lifecycle;", "(Landroidx/lifecycle/Lifecycle;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeAndAddObserver", "observer", "Landroidx/lifecycle/LifecycleObserver;", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* renamed from: coil.util.-Lifecycles, reason: invalid class name */
/* loaded from: classes24.dex */
public final class Lifecycles {
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r9v0, types: [T, coil.util.-Lifecycles$awaitStarted$2$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitStarted(androidx.lifecycle.Lifecycle r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof coil.util.Lifecycles$awaitStarted$1
            if (r0 == 0) goto L14
            r0 = r12
            coil.util.-Lifecycles$awaitStarted$1 r0 = (coil.util.Lifecycles$awaitStarted$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            coil.util.-Lifecycles$awaitStarted$1 r0 = new coil.util.-Lifecycles$awaitStarted$1
            r0.<init>(r12)
        L19:
            r12 = r0
            java.lang.Object r0 = r12.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r12.label
            switch(r2) {
                case 0: goto L3d;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2d:
            r11 = 0
            java.lang.Object r1 = r12.L$1
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
            java.lang.Object r2 = r12.L$0
            androidx.lifecycle.Lifecycle r2 = (androidx.lifecycle.Lifecycle) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3a
            goto L9b
        L3a:
            r11 = move-exception
            goto Laf
        L3d:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r11
            androidx.lifecycle.Lifecycle$State r11 = r2.getState()
            androidx.lifecycle.Lifecycle$State r3 = androidx.lifecycle.Lifecycle.State.STARTED
            boolean r11 = r11.isAtLeast(r3)
            if (r11 == 0) goto L50
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L50:
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            r3 = 0
            r12.L$0 = r2     // Catch: java.lang.Throwable -> Lab
            r12.L$1 = r11     // Catch: java.lang.Throwable -> Lab
            r4 = 1
            r12.label = r4     // Catch: java.lang.Throwable -> Lab
            r5 = r12
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Throwable -> Lab
            r6 = 0
            kotlinx.coroutines.CancellableContinuationImpl r7 = new kotlinx.coroutines.CancellableContinuationImpl     // Catch: java.lang.Throwable -> Lab
            kotlin.coroutines.Continuation r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r5)     // Catch: java.lang.Throwable -> Lab
            r7.<init>(r8, r4)     // Catch: java.lang.Throwable -> Lab
            r4 = r7
            r4.initCancellability()     // Catch: java.lang.Throwable -> Lab
            r7 = r4
            kotlinx.coroutines.CancellableContinuation r7 = (kotlinx.coroutines.CancellableContinuation) r7     // Catch: java.lang.Throwable -> Lab
            r8 = 0
            coil.util.-Lifecycles$awaitStarted$2$1 r9 = new coil.util.-Lifecycles$awaitStarted$2$1     // Catch: java.lang.Throwable -> Lab
            r9.<init>()     // Catch: java.lang.Throwable -> Lab
            r11.element = r9     // Catch: java.lang.Throwable -> Lab
            T r9 = r11.element     // Catch: java.lang.Throwable -> Lab
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch: java.lang.Throwable -> Lab
            androidx.lifecycle.LifecycleObserver r9 = (androidx.lifecycle.LifecycleObserver) r9     // Catch: java.lang.Throwable -> Lab
            r2.addObserver(r9)     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r7 = r4.getResult()     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch: java.lang.Throwable -> Lab
            if (r7 != r4) goto L96
            r4 = r12
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> Lab
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r4)     // Catch: java.lang.Throwable -> Lab
        L96:
            if (r7 != r1) goto L99
            return r1
        L99:
            r1 = r11
            r11 = r3
        L9b:
            T r11 = r1.element
            androidx.lifecycle.LifecycleObserver r11 = (androidx.lifecycle.LifecycleObserver) r11
            if (r11 == 0) goto La7
            r1 = 0
            r2.removeObserver(r11)
        La7:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        Lab:
            r1 = move-exception
            r10 = r1
            r1 = r11
            r11 = r10
        Laf:
            T r3 = r1.element
            androidx.lifecycle.LifecycleObserver r3 = (androidx.lifecycle.LifecycleObserver) r3
            if (r3 == 0) goto Lbb
            r1 = r3
            r3 = 0
            r2.removeObserver(r1)
        Lbb:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.util.Lifecycles.awaitStarted(androidx.lifecycle.Lifecycle, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void removeAndAddObserver(Lifecycle $this$removeAndAddObserver, LifecycleObserver observer) {
        $this$removeAndAddObserver.removeObserver(observer);
        $this$removeAndAddObserver.addObserver(observer);
    }
}
