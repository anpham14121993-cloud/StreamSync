package com.streamsync.app.data.repository;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: StreamCache.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0080@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"withPermit", ExifInterface.GPS_DIRECTION_TRUE, "permits", "Lkotlinx/coroutines/sync/Semaphore;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/sync/Semaphore;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class StreamCacheKt {
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object withPermit(kotlinx.coroutines.sync.Semaphore r6, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super T> r8) {
        /*
            boolean r0 = r8 instanceof com.streamsync.app.data.repository.StreamCacheKt$withPermit$1
            if (r0 == 0) goto L14
            r0 = r8
            com.streamsync.app.data.repository.StreamCacheKt$withPermit$1 r0 = (com.streamsync.app.data.repository.StreamCacheKt$withPermit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.streamsync.app.data.repository.StreamCacheKt$withPermit$1 r0 = new com.streamsync.app.data.repository.StreamCacheKt$withPermit$1
            r0.<init>(r8)
        L19:
            r8 = r0
            java.lang.Object r0 = r8.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L47;
                case 1: goto L3a;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            r6 = 0
            r7 = 0
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.sync.Semaphore r1 = (kotlinx.coroutines.sync.Semaphore) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L38
            r4 = r0
            goto L72
        L38:
            r7 = move-exception
            goto L7e
        L3a:
            r6 = 0
            java.lang.Object r7 = r8.L$1
            kotlinx.coroutines.sync.Semaphore r7 = (kotlinx.coroutines.sync.Semaphore) r7
            java.lang.Object r2 = r8.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.throwOnFailure(r0)
            goto L5f
        L47:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r7
            r7 = 0
            r8.L$0 = r2
            r8.L$1 = r6
            r3 = 1
            r8.label = r3
            java.lang.Object r3 = r6.acquire(r8)
            if (r3 != r1) goto L5c
            return r1
        L5c:
            r5 = r7
            r7 = r6
            r6 = r5
        L5f:
            r3 = 0
            r8.L$0 = r7     // Catch: java.lang.Throwable -> L7a
            r4 = 0
            r8.L$1 = r4     // Catch: java.lang.Throwable -> L7a
            r4 = 2
            r8.label = r4     // Catch: java.lang.Throwable -> L7a
            java.lang.Object r4 = r2.invoke(r8)     // Catch: java.lang.Throwable -> L7a
            if (r4 != r1) goto L70
            return r1
        L70:
            r1 = r7
            r7 = r3
        L72:
            r1.release()
            r7 = r1
            return r4
        L7a:
            r1 = move-exception
            r5 = r1
            r1 = r7
            r7 = r5
        L7e:
            r1.release()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.StreamCacheKt.withPermit(kotlinx.coroutines.sync.Semaphore, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
