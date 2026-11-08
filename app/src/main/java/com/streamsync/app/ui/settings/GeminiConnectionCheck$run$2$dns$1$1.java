package com.streamsync.app.ui.settings;

import com.streamsync.app.settings.BufferCeiling;
import java.net.InetAddress;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: GeminiConnectionCheck.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a(\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0014\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "Ljava/net/InetAddress;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.settings.GeminiConnectionCheck$run$2$dns$1$1", f = "GeminiConnectionCheck.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes17.dex */
final class GeminiConnectionCheck$run$2$dns$1$1 extends SuspendLambda implements Function1<Continuation<? super InetAddress[]>, Object> {
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeminiConnectionCheck$run$2$dns$1$1(Continuation<? super GeminiConnectionCheck$run$2$dns$1$1> continuation) {
        super(1, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new GeminiConnectionCheck$run$2$dns$1$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super InetAddress[]> continuation) {
        return ((GeminiConnectionCheck$run$2$dns$1$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                return InetAddress.getAllByName("generativelanguage.googleapis.com");
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
