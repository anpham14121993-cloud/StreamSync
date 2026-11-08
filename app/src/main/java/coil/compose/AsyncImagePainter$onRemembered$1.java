package coil.compose;

import androidx.compose.runtime.SnapshotStateKt;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AsyncImagePainter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "coil.compose.AsyncImagePainter$onRemembered$1", f = "AsyncImagePainter.kt", i = {}, l = {StatusLine.HTTP_TEMP_REDIRECT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes24.dex */
public final class AsyncImagePainter$onRemembered$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AsyncImagePainter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncImagePainter$onRemembered$1(AsyncImagePainter asyncImagePainter, Continuation<? super AsyncImagePainter$onRemembered$1> continuation) {
        super(2, continuation);
        this.this$0 = asyncImagePainter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AsyncImagePainter$onRemembered$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AsyncImagePainter$onRemembered$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final AsyncImagePainter asyncImagePainter = this.this$0;
                this.label = 1;
                if (FlowKt.mapLatest(SnapshotStateKt.snapshotFlow(new Function0<ImageRequest>() { // from class: coil.compose.AsyncImagePainter$onRemembered$1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final ImageRequest invoke() {
                        return AsyncImagePainter.this.getRequest();
                    }
                }), new AnonymousClass2(this.this$0, null)).collect(new AnonymousClass3(this.this$0), this) != coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AsyncImagePainter.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lcoil/compose/AsyncImagePainter$State;", TranslateLanguage.ITALIAN, "Lcoil/request/ImageRequest;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "coil.compose.AsyncImagePainter$onRemembered$1$2", f = "AsyncImagePainter.kt", i = {}, l = {306}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: coil.compose.AsyncImagePainter$onRemembered$1$2, reason: invalid class name */
    /* loaded from: classes24.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<ImageRequest, Continuation<? super AsyncImagePainter.State>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AsyncImagePainter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AsyncImagePainter asyncImagePainter, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = asyncImagePainter;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ImageRequest imageRequest, Continuation<? super AsyncImagePainter.State> continuation) {
            return ((AnonymousClass2) create(imageRequest, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AsyncImagePainter asyncImagePainter;
            ImageRequest updateRequest;
            AsyncImagePainter.State state;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    ImageRequest it = (ImageRequest) this.L$0;
                    asyncImagePainter = this.this$0;
                    ImageLoader imageLoader = this.this$0.getImageLoader();
                    updateRequest = this.this$0.updateRequest(it);
                    this.L$0 = asyncImagePainter;
                    this.label = 1;
                    Object execute = imageLoader.execute(updateRequest, this);
                    if (execute != coroutine_suspended) {
                        $result = execute;
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    AsyncImagePainter asyncImagePainter2 = (AsyncImagePainter) this.L$0;
                    ResultKt.throwOnFailure($result);
                    asyncImagePainter = asyncImagePainter2;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            state = asyncImagePainter.toState((ImageResult) $result);
            return state;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AsyncImagePainter.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* renamed from: coil.compose.AsyncImagePainter$onRemembered$1$3, reason: invalid class name */
    /* loaded from: classes24.dex */
    public /* synthetic */ class AnonymousClass3 implements FlowCollector, FunctionAdapter {
        final /* synthetic */ AsyncImagePainter $tmp0;

        AnonymousClass3(AsyncImagePainter asyncImagePainter) {
            this.$tmp0 = asyncImagePainter;
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new AdaptedFunctionReference(2, this.$tmp0, AsyncImagePainter.class, "updateState", "updateState(Lcoil/compose/AsyncImagePainter$State;)V", 4);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        public final Object emit(AsyncImagePainter.State p0, Continuation<? super Unit> continuation) {
            Object invokeSuspend$updateState = AsyncImagePainter$onRemembered$1.invokeSuspend$updateState(this.$tmp0, p0, continuation);
            return invokeSuspend$updateState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invokeSuspend$updateState : Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
            return emit((AsyncImagePainter.State) value, (Continuation<? super Unit>) $completion);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object invokeSuspend$updateState(AsyncImagePainter $this$invokeSuspend_u24updateState, AsyncImagePainter.State p0, Continuation $completion) {
        $this$invokeSuspend_u24updateState.updateState(p0);
        return Unit.INSTANCE;
    }
}
