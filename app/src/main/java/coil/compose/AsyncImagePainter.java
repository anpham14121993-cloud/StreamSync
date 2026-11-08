package coil.compose;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.BitmapPainterKt;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.exifinterface.media.ExifInterface;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.SuccessResult;
import coil.size.Precision;
import coil.size.SizeResolver;
import coil.target.Target;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import com.google.accompanist.drawablepainter.DrawablePainter;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: AsyncImagePainter.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 m2\u00020\u00012\u00020\u0002:\u0002mnB\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010Z\u001a\u00020:2\u0006\u0010\u0012\u001a\u00020\u0011H\u0014J\u0012\u0010[\u001a\u00020:2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\\\u001a\u00020AH\u0002J\u001a\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020\f2\u0006\u0010`\u001a\u00020\fH\u0002J\b\u0010a\u001a\u00020AH\u0016J\b\u0010b\u001a\u00020AH\u0016J\b\u0010c\u001a\u00020AH\u0016J\u0010\u0010d\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010e\u001a\u00020A2\u0006\u0010f\u001a\u00020\fH\u0002J\f\u0010g\u001a\u00020A*\u00020hH\u0014J\f\u0010i\u001a\u00020\u0001*\u00020jH\u0002J\f\u0010k\u001a\u00020\f*\u00020lH\u0002R\"\u0010\t\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0001@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R/\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0010\u001a\u0004\u0018\u00010\u00198B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010!\u001a\u00020\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010*\u001a\u00020+X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u00100\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00068F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010 \u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00106\u001a\u00020)8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b7\u00108R\u001a\u00109\u001a\u00020:X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R(\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020A\u0018\u00010@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER/\u0010F\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bJ\u0010 \u001a\u0004\bG\u0010H\"\u0004\bI\u0010\u000bR\u0010\u0010K\u001a\u0004\u0018\u00010LX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00048F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u0010 \u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR+\u0010R\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bV\u0010 \u001a\u0004\bS\u0010T\"\u0004\bU\u0010\u000fR&\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010C\"\u0004\bY\u0010E\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006o"}, d2 = {"Lcoil/compose/AsyncImagePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/runtime/RememberObserver;", "request", "Lcoil/request/ImageRequest;", "imageLoader", "Lcoil/ImageLoader;", "(Lcoil/request/ImageRequest;Lcoil/ImageLoader;)V", "value", "_painter", "set_painter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "Lcoil/compose/AsyncImagePainter$State;", "_state", "set_state", "(Lcoil/compose/AsyncImagePainter$State;)V", "<set-?>", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "alpha$delegate", "Landroidx/compose/runtime/MutableFloatState;", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "colorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "getContentScale$coil_compose_base_release", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale$coil_compose_base_release", "(Landroidx/compose/ui/layout/ContentScale;)V", "drawSize", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/ui/geometry/Size;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getFilterQuality-f-v9h1I$coil_compose_base_release", "()I", "setFilterQuality-vDHp3xo$coil_compose_base_release", "(I)V", "I", "getImageLoader", "()Lcoil/ImageLoader;", "setImageLoader$coil_compose_base_release", "(Lcoil/ImageLoader;)V", "imageLoader$delegate", "intrinsicSize", "getIntrinsicSize-NH-jbRc", "()J", "isPreview", "", "isPreview$coil_compose_base_release", "()Z", "setPreview$coil_compose_base_release", "(Z)V", "onState", "Lkotlin/Function1;", "", "getOnState$coil_compose_base_release", "()Lkotlin/jvm/functions/Function1;", "setOnState$coil_compose_base_release", "(Lkotlin/jvm/functions/Function1;)V", "painter", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "painter$delegate", "rememberScope", "Lkotlinx/coroutines/CoroutineScope;", "getRequest", "()Lcoil/request/ImageRequest;", "setRequest$coil_compose_base_release", "(Lcoil/request/ImageRequest;)V", "request$delegate", "state", "getState", "()Lcoil/compose/AsyncImagePainter$State;", "setState", "state$delegate", "transform", "getTransform$coil_compose_base_release", "setTransform$coil_compose_base_release", "applyAlpha", "applyColorFilter", "clear", "maybeNewCrossfadePainter", "Lcoil/compose/CrossfadePainter;", "previous", "current", "onAbandoned", "onForgotten", "onRemembered", "updateRequest", "updateState", "input", "onDraw", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "toPainter", "Landroid/graphics/drawable/Drawable;", "toState", "Lcoil/request/ImageResult;", "Companion", "State", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class AsyncImagePainter extends Painter implements RememberObserver {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Function1<State, State> DefaultTransform = new Function1<State, State>() { // from class: coil.compose.AsyncImagePainter$Companion$DefaultTransform$1
        @Override // kotlin.jvm.functions.Function1
        public final AsyncImagePainter.State invoke(AsyncImagePainter.State it) {
            return it;
        }
    };
    private Painter _painter;
    private State _state;

    /* renamed from: alpha$delegate, reason: from kotlin metadata */
    private final MutableFloatState alpha;

    /* renamed from: colorFilter$delegate, reason: from kotlin metadata */
    private final MutableState colorFilter;
    private ContentScale contentScale;
    private final MutableStateFlow<Size> drawSize = StateFlowKt.MutableStateFlow(Size.m3225boximpl(Size.INSTANCE.m3246getZeroNHjbRc()));
    private int filterQuality;

    /* renamed from: imageLoader$delegate, reason: from kotlin metadata */
    private final MutableState imageLoader;
    private boolean isPreview;
    private Function1<? super State, Unit> onState;

    /* renamed from: painter$delegate, reason: from kotlin metadata */
    private final MutableState painter;
    private CoroutineScope rememberScope;

    /* renamed from: request$delegate, reason: from kotlin metadata */
    private final MutableState request;

    /* renamed from: state$delegate, reason: from kotlin metadata */
    private final MutableState state;
    private Function1<? super State, ? extends State> transform;

    public AsyncImagePainter(ImageRequest request, ImageLoader imageLoader) {
        MutableState mutableStateOf$default;
        MutableState mutableStateOf$default2;
        MutableState mutableStateOf$default3;
        MutableState mutableStateOf$default4;
        MutableState mutableStateOf$default5;
        mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.painter = mutableStateOf$default;
        this.alpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);
        mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.colorFilter = mutableStateOf$default2;
        this._state = State.Empty.INSTANCE;
        this.transform = DefaultTransform;
        this.contentScale = ContentScale.INSTANCE.getFit();
        this.filterQuality = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(State.Empty.INSTANCE, null, 2, null);
        this.state = mutableStateOf$default3;
        mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(request, null, 2, null);
        this.request = mutableStateOf$default4;
        mutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(imageLoader, null, 2, null);
        this.imageLoader = mutableStateOf$default5;
    }

    private final Painter getPainter() {
        androidx.compose.runtime.State $this$getValue$iv = this.painter;
        return (Painter) $this$getValue$iv.getValue();
    }

    private final void setPainter(Painter painter) {
        MutableState $this$setValue$iv = this.painter;
        $this$setValue$iv.setValue(painter);
    }

    private final float getAlpha() {
        FloatState $this$getValue$iv = this.alpha;
        return $this$getValue$iv.getFloatValue();
    }

    private final void setAlpha(float f) {
        MutableFloatState $this$setValue$iv = this.alpha;
        $this$setValue$iv.setFloatValue(f);
    }

    private final ColorFilter getColorFilter() {
        androidx.compose.runtime.State $this$getValue$iv = this.colorFilter;
        return (ColorFilter) $this$getValue$iv.getValue();
    }

    private final void setColorFilter(ColorFilter colorFilter) {
        MutableState $this$setValue$iv = this.colorFilter;
        $this$setValue$iv.setValue(colorFilter);
    }

    private final void set_state(State value) {
        this._state = value;
        setState(value);
    }

    private final void set_painter(Painter value) {
        this._painter = value;
        setPainter(value);
    }

    public final Function1<State, State> getTransform$coil_compose_base_release() {
        return this.transform;
    }

    public final void setTransform$coil_compose_base_release(Function1<? super State, ? extends State> function1) {
        this.transform = function1;
    }

    public final Function1<State, Unit> getOnState$coil_compose_base_release() {
        return this.onState;
    }

    public final void setOnState$coil_compose_base_release(Function1<? super State, Unit> function1) {
        this.onState = function1;
    }

    /* renamed from: getContentScale$coil_compose_base_release, reason: from getter */
    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final void setContentScale$coil_compose_base_release(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    /* renamed from: getFilterQuality-f-v9h1I$coil_compose_base_release, reason: not valid java name and from getter */
    public final int getFilterQuality() {
        return this.filterQuality;
    }

    /* renamed from: setFilterQuality-vDHp3xo$coil_compose_base_release, reason: not valid java name */
    public final void m6518setFilterQualityvDHp3xo$coil_compose_base_release(int i) {
        this.filterQuality = i;
    }

    /* renamed from: isPreview$coil_compose_base_release, reason: from getter */
    public final boolean getIsPreview() {
        return this.isPreview;
    }

    public final void setPreview$coil_compose_base_release(boolean z) {
        this.isPreview = z;
    }

    private final void setState(State state) {
        MutableState $this$setValue$iv = this.state;
        $this$setValue$iv.setValue(state);
    }

    public final State getState() {
        androidx.compose.runtime.State $this$getValue$iv = this.state;
        return (State) $this$getValue$iv.getValue();
    }

    public final ImageRequest getRequest() {
        androidx.compose.runtime.State $this$getValue$iv = this.request;
        return (ImageRequest) $this$getValue$iv.getValue();
    }

    public final void setRequest$coil_compose_base_release(ImageRequest imageRequest) {
        MutableState $this$setValue$iv = this.request;
        $this$setValue$iv.setValue(imageRequest);
    }

    public final ImageLoader getImageLoader() {
        androidx.compose.runtime.State $this$getValue$iv = this.imageLoader;
        return (ImageLoader) $this$getValue$iv.getValue();
    }

    public final void setImageLoader$coil_compose_base_release(ImageLoader imageLoader) {
        MutableState $this$setValue$iv = this.imageLoader;
        $this$setValue$iv.setValue(imageLoader);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        Painter painter = getPainter();
        return painter != null ? painter.getIntrinsicSize() : Size.INSTANCE.m3245getUnspecifiedNHjbRc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.graphics.painter.Painter
    public void onDraw(DrawScope $this$onDraw) {
        this.drawSize.setValue(Size.m3225boximpl($this$onDraw.mo3959getSizeNHjbRc()));
        Painter $this$onDraw_u24lambda_u240 = getPainter();
        if ($this$onDraw_u24lambda_u240 != null) {
            $this$onDraw_u24lambda_u240.m4035drawx_KDEd0($this$onDraw, $this$onDraw.mo3959getSizeNHjbRc(), getAlpha(), getColorFilter());
        }
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float alpha) {
        setAlpha(alpha);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        setColorFilter(colorFilter);
        return true;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        if (this.rememberScope != null) {
            return;
        }
        CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));
        this.rememberScope = scope;
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onRemembered();
        }
        if (!this.isPreview) {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new AsyncImagePainter$onRemembered$1(this, null), 3, null);
            return;
        }
        ImageRequest request = ImageRequest.newBuilder$default(getRequest(), null, 1, null).defaults(getImageLoader().getDefaults()).build();
        Drawable placeholder = request.getPlaceholder();
        updateState(new State.Loading(placeholder != null ? toPainter(placeholder) : null));
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        clear();
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onForgotten();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        clear();
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onAbandoned();
        }
    }

    private final void clear() {
        CoroutineScope coroutineScope = this.rememberScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.rememberScope = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageRequest updateRequest(ImageRequest request) {
        ImageRequest.Builder $this$iv = ImageRequest.newBuilder$default(request, null, 1, null);
        ImageRequest.Builder $this$updateRequest_u24lambda_u242 = $this$iv.target(new Target() { // from class: coil.compose.AsyncImagePainter$updateRequest$$inlined$target$default$1
            @Override // coil.target.Target
            public void onStart(Drawable placeholder) {
                AsyncImagePainter.this.updateState(new AsyncImagePainter.State.Loading(placeholder != null ? AsyncImagePainter.this.toPainter(placeholder) : null));
            }

            @Override // coil.target.Target
            public void onError(Drawable error) {
            }

            @Override // coil.target.Target
            public void onSuccess(Drawable result) {
            }
        });
        if (request.getDefined().getSizeResolver() == null) {
            $this$updateRequest_u24lambda_u242.size(new SizeResolver() { // from class: coil.compose.AsyncImagePainter$updateRequest$2$1
                @Override // coil.size.SizeResolver
                public final Object size(Continuation<? super coil.size.Size> continuation) {
                    Flow flow;
                    flow = AsyncImagePainter.this.drawSize;
                    final Flow $this$mapNotNull$iv = flow;
                    return FlowKt.first(new Flow<coil.size.Size>() { // from class: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1

                        /* compiled from: Emitters.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                        /* renamed from: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2, reason: invalid class name */
                        /* loaded from: classes24.dex */
                        public static final class AnonymousClass2<T> implements FlowCollector {
                            final /* synthetic */ FlowCollector $this_unsafeFlow;

                            /* compiled from: Emitters.kt */
                            @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                            @DebugMetadata(c = "coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2", f = "AsyncImagePainter.kt", i = {}, l = {225}, m = "emit", n = {}, s = {})
                            /* renamed from: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1, reason: invalid class name */
                            /* loaded from: classes24.dex */
                            public static final class AnonymousClass1 extends ContinuationImpl {
                                Object L$0;
                                int label;
                                /* synthetic */ Object result;

                                public AnonymousClass1(Continuation continuation) {
                                    super(continuation);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    this.result = obj;
                                    this.label |= Integer.MIN_VALUE;
                                    return AnonymousClass2.this.emit(null, this);
                                }
                            }

                            public AnonymousClass2(FlowCollector flowCollector) {
                                this.$this_unsafeFlow = flowCollector;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
                            /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
                            /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                                /*
                                    r6 = this;
                                    boolean r0 = r8 instanceof coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                    if (r0 == 0) goto L14
                                    r0 = r8
                                    coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1 r0 = (coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                    int r1 = r0.label
                                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                    r1 = r1 & r2
                                    if (r1 == 0) goto L14
                                    int r8 = r0.label
                                    int r8 = r8 - r2
                                    r0.label = r8
                                    goto L19
                                L14:
                                    coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1 r0 = new coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1
                                    r0.<init>(r8)
                                L19:
                                    r8 = r0
                                    java.lang.Object r0 = r8.result
                                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                    int r2 = r8.label
                                    switch(r2) {
                                        case 0: goto L32;
                                        case 1: goto L2d;
                                        default: goto L25;
                                    }
                                L25:
                                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                                    r7.<init>(r8)
                                    throw r7
                                L2d:
                                    r7 = 0
                                    kotlin.ResultKt.throwOnFailure(r0)
                                    goto L55
                                L32:
                                    kotlin.ResultKt.throwOnFailure(r0)
                                    r2 = r6
                                    kotlinx.coroutines.flow.FlowCollector r2 = r2.$this_unsafeFlow
                                    r3 = 0
                                    r4 = r8
                                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                                    r4 = r7
                                    androidx.compose.ui.geometry.Size r4 = (androidx.compose.ui.geometry.Size) r4
                                    long r4 = r4.getPackedValue()
                                    r7 = 0
                                    coil.size.Size r7 = coil.compose.AsyncImagePainterKt.m6519access$toSizeOrNulluvyYCjk(r4)
                                    if (r7 == 0) goto L56
                                    r4 = 1
                                    r8.label = r4
                                    java.lang.Object r7 = r2.emit(r7, r8)
                                    if (r7 != r1) goto L54
                                    return r1
                                L54:
                                    r7 = r3
                                L55:
                                L56:
                                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                                    return r7
                                */
                                throw new UnsupportedOperationException("Method not decompiled: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                            }
                        }

                        @Override // kotlinx.coroutines.flow.Flow
                        public Object collect(FlowCollector<? super coil.size.Size> flowCollector, Continuation $completion) {
                            Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), $completion);
                            return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
                        }
                    }, continuation);
                }
            });
        }
        if (request.getDefined().getScale() == null) {
            $this$updateRequest_u24lambda_u242.scale(UtilsKt.toScale(this.contentScale));
        }
        if (request.getDefined().getPrecision() != Precision.EXACT) {
            $this$updateRequest_u24lambda_u242.precision(Precision.INEXACT);
        }
        return $this$updateRequest_u24lambda_u242.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState(State input) {
        State previous = this._state;
        State current = this.transform.invoke(input);
        set_state(current);
        CrossfadePainter maybeNewCrossfadePainter = maybeNewCrossfadePainter(previous, current);
        set_painter(maybeNewCrossfadePainter != null ? maybeNewCrossfadePainter : current.getPainter());
        if (this.rememberScope != null && previous.getPainter() != current.getPainter()) {
            Object painter = previous.getPainter();
            RememberObserver rememberObserver = painter instanceof RememberObserver ? (RememberObserver) painter : null;
            if (rememberObserver != null) {
                rememberObserver.onForgotten();
            }
            Object painter2 = current.getPainter();
            RememberObserver rememberObserver2 = painter2 instanceof RememberObserver ? (RememberObserver) painter2 : null;
            if (rememberObserver2 != null) {
                rememberObserver2.onRemembered();
            }
        }
        Function1<? super State, Unit> function1 = this.onState;
        if (function1 != null) {
            function1.invoke(current);
        }
    }

    private final CrossfadePainter maybeNewCrossfadePainter(State previous, State current) {
        ErrorResult result;
        if (current instanceof State.Success) {
            result = ((State.Success) current).getResult();
        } else {
            if (!(current instanceof State.Error)) {
                return null;
            }
            result = ((State.Error) current).getResult();
        }
        Transition transition = result.getRequest().getTransitionFactory().create(AsyncImagePainterKt.access$getFakeTransitionTarget$p(), result);
        if (transition instanceof CrossfadeTransition) {
            return new CrossfadePainter(previous instanceof State.Loading ? previous.getPainter() : null, current.getPainter(), this.contentScale, ((CrossfadeTransition) transition).getDurationMillis(), ((result instanceof SuccessResult) && ((SuccessResult) result).getIsPlaceholderCached()) ? false : true, ((CrossfadeTransition) transition).getPreferExactIntrinsicSize());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final State toState(ImageResult $this$toState) {
        if ($this$toState instanceof SuccessResult) {
            return new State.Success(toPainter(((SuccessResult) $this$toState).getDrawable()), (SuccessResult) $this$toState);
        }
        if (!($this$toState instanceof ErrorResult)) {
            throw new NoWhenBranchMatchedException();
        }
        Drawable drawable = $this$toState.getDrawable();
        return new State.Error(drawable != null ? toPainter(drawable) : null, (ErrorResult) $this$toState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Painter toPainter(Drawable $this$toPainter) {
        BitmapPainter m4031BitmapPainterQZhYCtY;
        if ($this$toPainter instanceof BitmapDrawable) {
            m4031BitmapPainterQZhYCtY = BitmapPainterKt.m4031BitmapPainterQZhYCtY(r1, (r12 & 2) != 0 ? IntOffset.INSTANCE.m5874getZeronOccac() : 0L, (r12 & 4) != 0 ? IntSizeKt.IntSize(r1.getWidth(), AndroidImageBitmap_androidKt.asImageBitmap(((BitmapDrawable) $this$toPainter).getBitmap()).getHeight()) : 0L, (r12 & 8) != 0 ? FilterQuality.INSTANCE.m3510getLowfv9h1I() : this.filterQuality);
            return m4031BitmapPainterQZhYCtY;
        }
        return new DrawablePainter($this$toPainter.mutate());
    }

    /* compiled from: AsyncImagePainter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcoil/compose/AsyncImagePainter$State;", "", "()V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "Empty", "Error", "Loading", "Success", "Lcoil/compose/AsyncImagePainter$State$Empty;", "Lcoil/compose/AsyncImagePainter$State$Error;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lcoil/compose/AsyncImagePainter$State$Success;", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static abstract class State {
        public static final int $stable = 0;

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract Painter getPainter();

        private State() {
        }

        /* compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Empty;", "Lcoil/compose/AsyncImagePainter$State;", "()V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes24.dex */
        public static final /* data */ class Empty extends State {
            public static final int $stable = 0;
            public static final Empty INSTANCE = new Empty();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Empty)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -1515560141;
            }

            public String toString() {
                return "Empty";
            }

            private Empty() {
                super(null);
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return null;
            }
        }

        /* compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Loading;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes24.dex */
        public static final /* data */ class Loading extends State {
            public static final int $stable = 8;
            private final Painter painter;

            public static /* synthetic */ Loading copy$default(Loading loading, Painter painter, int i, Object obj) {
                if ((i & 1) != 0) {
                    painter = loading.painter;
                }
                return loading.copy(painter);
            }

            /* renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            public final Loading copy(Painter painter) {
                return new Loading(painter);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Loading) && Intrinsics.areEqual(this.painter, ((Loading) other).painter);
            }

            public int hashCode() {
                if (this.painter == null) {
                    return 0;
                }
                return this.painter.hashCode();
            }

            public String toString() {
                return "Loading(painter=" + this.painter + ')';
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public Loading(Painter painter) {
                super(null);
                this.painter = painter;
            }
        }

        /* compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Success;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "result", "Lcoil/request/SuccessResult;", "(Landroidx/compose/ui/graphics/painter/Painter;Lcoil/request/SuccessResult;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "getResult", "()Lcoil/request/SuccessResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes24.dex */
        public static final /* data */ class Success extends State {
            public static final int $stable = 8;
            private final Painter painter;
            private final SuccessResult result;

            public static /* synthetic */ Success copy$default(Success success, Painter painter, SuccessResult successResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    painter = success.painter;
                }
                if ((i & 2) != 0) {
                    successResult = success.result;
                }
                return success.copy(painter, successResult);
            }

            /* renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            /* renamed from: component2, reason: from getter */
            public final SuccessResult getResult() {
                return this.result;
            }

            public final Success copy(Painter painter, SuccessResult result) {
                return new Success(painter, result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Success)) {
                    return false;
                }
                Success success = (Success) other;
                return Intrinsics.areEqual(this.painter, success.painter) && Intrinsics.areEqual(this.result, success.result);
            }

            public int hashCode() {
                return (this.painter.hashCode() * 31) + this.result.hashCode();
            }

            public String toString() {
                return "Success(painter=" + this.painter + ", result=" + this.result + ')';
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public final SuccessResult getResult() {
                return this.result;
            }

            public Success(Painter painter, SuccessResult result) {
                super(null);
                this.painter = painter;
                this.result = result;
            }
        }

        /* compiled from: AsyncImagePainter.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Error;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "result", "Lcoil/request/ErrorResult;", "(Landroidx/compose/ui/graphics/painter/Painter;Lcoil/request/ErrorResult;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "getResult", "()Lcoil/request/ErrorResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes24.dex */
        public static final /* data */ class Error extends State {
            public static final int $stable = 8;
            private final Painter painter;
            private final ErrorResult result;

            public static /* synthetic */ Error copy$default(Error error, Painter painter, ErrorResult errorResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    painter = error.painter;
                }
                if ((i & 2) != 0) {
                    errorResult = error.result;
                }
                return error.copy(painter, errorResult);
            }

            /* renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            /* renamed from: component2, reason: from getter */
            public final ErrorResult getResult() {
                return this.result;
            }

            public final Error copy(Painter painter, ErrorResult result) {
                return new Error(painter, result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Error)) {
                    return false;
                }
                Error error = (Error) other;
                return Intrinsics.areEqual(this.painter, error.painter) && Intrinsics.areEqual(this.result, error.result);
            }

            public int hashCode() {
                return ((this.painter == null ? 0 : this.painter.hashCode()) * 31) + this.result.hashCode();
            }

            public String toString() {
                return "Error(painter=" + this.painter + ", result=" + this.result + ')';
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public final ErrorResult getResult() {
                return this.result;
            }

            public Error(Painter painter, ErrorResult result) {
                super(null);
                this.painter = painter;
                this.result = result;
            }
        }
    }

    /* compiled from: AsyncImagePainter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcoil/compose/AsyncImagePainter$Companion;", "", "()V", "DefaultTransform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "getDefaultTransform", "()Lkotlin/jvm/functions/Function1;", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Function1<State, State> getDefaultTransform() {
            return AsyncImagePainter.DefaultTransform;
        }
    }
}
