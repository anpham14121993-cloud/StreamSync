package coil.intercept;

import coil.EventListener;
import coil.intercept.Interceptor;
import coil.request.ImageRequest;
import coil.request.NullRequestData;
import coil.size.Size;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;

/* compiled from: RealInterceptorChain.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0006H\u0002J&\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0002J\u0016\u0010!\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0010\u0010%\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0019R\u0014\u0010\t\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006&"}, d2 = {"Lcoil/intercept/RealInterceptorChain;", "Lcoil/intercept/Interceptor$Chain;", "initialRequest", "Lcoil/request/ImageRequest;", "interceptors", "", "Lcoil/intercept/Interceptor;", "index", "", "request", "size", "Lcoil/size/Size;", "eventListener", "Lcoil/EventListener;", "isPlaceholderCached", "", "(Lcoil/request/ImageRequest;Ljava/util/List;ILcoil/request/ImageRequest;Lcoil/size/Size;Lcoil/EventListener;Z)V", "getEventListener", "()Lcoil/EventListener;", "getIndex", "()I", "getInitialRequest", "()Lcoil/request/ImageRequest;", "getInterceptors", "()Ljava/util/List;", "()Z", "getRequest", "getSize", "()Lcoil/size/Size;", "checkRequest", "", "interceptor", "copy", "proceed", "Lcoil/request/ImageResult;", "(Lcoil/request/ImageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withRequest", "withSize", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final EventListener eventListener;
    private final int index;
    private final ImageRequest initialRequest;
    private final List<Interceptor> interceptors;
    private final boolean isPlaceholderCached;
    private final ImageRequest request;
    private final Size size;

    /* JADX WARN: Multi-variable type inference failed */
    public RealInterceptorChain(ImageRequest initialRequest, List<? extends Interceptor> list, int index, ImageRequest request, Size size, EventListener eventListener, boolean isPlaceholderCached) {
        this.initialRequest = initialRequest;
        this.interceptors = list;
        this.index = index;
        this.request = request;
        this.size = size;
        this.eventListener = eventListener;
        this.isPlaceholderCached = isPlaceholderCached;
    }

    public final ImageRequest getInitialRequest() {
        return this.initialRequest;
    }

    public final List<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // coil.intercept.Interceptor.Chain
    public ImageRequest getRequest() {
        return this.request;
    }

    @Override // coil.intercept.Interceptor.Chain
    public Size getSize() {
        return this.size;
    }

    public final EventListener getEventListener() {
        return this.eventListener;
    }

    /* renamed from: isPlaceholderCached, reason: from getter */
    public final boolean getIsPlaceholderCached() {
        return this.isPlaceholderCached;
    }

    @Override // coil.intercept.Interceptor.Chain
    public Interceptor.Chain withRequest(ImageRequest request) {
        if (this.index > 0) {
            checkRequest(request, this.interceptors.get(this.index - 1));
        }
        return copy$default(this, 0, request, null, 5, null);
    }

    @Override // coil.intercept.Interceptor.Chain
    public Interceptor.Chain withSize(Size size) {
        return copy$default(this, 0, null, size, 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // coil.intercept.Interceptor.Chain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object proceed(coil.request.ImageRequest r12, kotlin.coroutines.Continuation<? super coil.request.ImageResult> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof coil.intercept.RealInterceptorChain$proceed$1
            if (r0 == 0) goto L14
            r0 = r13
            coil.intercept.RealInterceptorChain$proceed$1 r0 = (coil.intercept.RealInterceptorChain$proceed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            coil.intercept.RealInterceptorChain$proceed$1 r0 = new coil.intercept.RealInterceptorChain$proceed$1
            r0.<init>(r11, r13)
        L19:
            r13 = r0
            java.lang.Object r0 = r13.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r13.label
            switch(r2) {
                case 0: goto L3b;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2d:
            java.lang.Object r12 = r13.L$1
            coil.intercept.Interceptor r12 = (coil.intercept.Interceptor) r12
            java.lang.Object r1 = r13.L$0
            coil.intercept.RealInterceptorChain r1 = (coil.intercept.RealInterceptorChain) r1
            kotlin.ResultKt.throwOnFailure(r0)
            r10 = r12
            r12 = r0
            goto L7b
        L3b:
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r11
            int r2 = r8.index
            r9 = 1
            if (r2 <= 0) goto L52
            java.util.List<coil.intercept.Interceptor> r2 = r8.interceptors
            int r3 = r8.index
            int r3 = r3 - r9
            java.lang.Object r2 = r2.get(r3)
            coil.intercept.Interceptor r2 = (coil.intercept.Interceptor) r2
            r8.checkRequest(r12, r2)
        L52:
            java.util.List<coil.intercept.Interceptor> r2 = r8.interceptors
            int r3 = r8.index
            java.lang.Object r2 = r2.get(r3)
            r10 = r2
            coil.intercept.Interceptor r10 = (coil.intercept.Interceptor) r10
            int r2 = r8.index
            int r3 = r2 + 1
            r6 = 4
            r7 = 0
            r5 = 0
            r2 = r8
            r4 = r12
            coil.intercept.RealInterceptorChain r12 = copy$default(r2, r3, r4, r5, r6, r7)
            r2 = r12
            coil.intercept.Interceptor$Chain r2 = (coil.intercept.Interceptor.Chain) r2
            r13.L$0 = r8
            r13.L$1 = r10
            r13.label = r9
            java.lang.Object r12 = r10.intercept(r2, r13)
            if (r12 != r1) goto L7a
            return r1
        L7a:
            r1 = r8
        L7b:
            coil.request.ImageResult r12 = (coil.request.ImageResult) r12
            coil.request.ImageRequest r2 = r12.getRequest()
            r1.checkRequest(r2, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.RealInterceptorChain.proceed(coil.request.ImageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void checkRequest(ImageRequest request, Interceptor interceptor) {
        if (!(request.getContext() == this.initialRequest.getContext())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's context.").toString());
        }
        if (!(request.getData() != NullRequestData.INSTANCE)) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot set the request's data to null.").toString());
        }
        if (!(request.getTarget() == this.initialRequest.getTarget())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's target.").toString());
        }
        if (!(request.getLifecycle() == this.initialRequest.getLifecycle())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's lifecycle.").toString());
        }
        if (!(request.getSizeResolver() == this.initialRequest.getSizeResolver())) {
            throw new IllegalStateException(("Interceptor '" + interceptor + "' cannot modify the request's size resolver. Use `Interceptor.Chain.withSize` instead.").toString());
        }
    }

    static /* synthetic */ RealInterceptorChain copy$default(RealInterceptorChain realInterceptorChain, int i, ImageRequest imageRequest, Size size, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = realInterceptorChain.index;
        }
        if ((i2 & 2) != 0) {
            imageRequest = realInterceptorChain.getRequest();
        }
        if ((i2 & 4) != 0) {
            size = realInterceptorChain.getSize();
        }
        return realInterceptorChain.copy(i, imageRequest, size);
    }

    private final RealInterceptorChain copy(int index, ImageRequest request, Size size) {
        return new RealInterceptorChain(this.initialRequest, this.interceptors, index, request, size, this.eventListener, this.isPlaceholderCached);
    }
}
