package coil.compose;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.platform.InspectionModeKt;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.size.Dimension;
import coil.size.Dimensions;
import coil.size.Size;
import coil.transition.TransitionTarget;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* compiled from: AsyncImagePainter.kt */
@Metadata(d1 = {"\u0000\u0085\u0001\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0001\u001aT\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0003ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001af\u0010\u0003\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001ap\u0010\u0003\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a¤\u0001\u0010\u0003\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001d2\u0016\b\u0002\u0010 \u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0016\b\u0002\u0010$\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a®\u0001\u0010\u0003\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001d2\u0016\b\u0002\u0010 \u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0016\b\u0002\u0010$\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a\u001a\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020-H\u0002\u001a\u0010\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u000201H\u0002\u001a\u0018\u00102\u001a\u0004\u0018\u000103*\u000204H\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"fakeTransitionTarget", "coil/compose/AsyncImagePainterKt$fakeTransitionTarget$1", "Lcoil/compose/AsyncImagePainterKt$fakeTransitionTarget$1;", "rememberAsyncImagePainter", "Lcoil/compose/AsyncImagePainter;", "state", "Lcoil/compose/AsyncImageState;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "rememberAsyncImagePainter-GSdzBsE", "(Lcoil/compose/AsyncImageState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "model", "", "imageLoader", "Lcoil/ImageLoader;", "rememberAsyncImagePainter-5jETZwI", "(Ljava/lang/Object;Lcoil/ImageLoader;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "modelEqualityDelegate", "Lcoil/compose/EqualityDelegate;", "rememberAsyncImagePainter-0YpotYA", "(Ljava/lang/Object;Lcoil/ImageLoader;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "rememberAsyncImagePainter-3HmZ8SU", "(Ljava/lang/Object;Lcoil/ImageLoader;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "rememberAsyncImagePainter-10Xjiaw", "(Ljava/lang/Object;Lcoil/ImageLoader;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)Lcoil/compose/AsyncImagePainter;", "unsupportedData", "", HintConstants.AUTOFILL_HINT_NAME, "", "description", "validateRequest", "request", "Lcoil/request/ImageRequest;", "toSizeOrNull", "Lcoil/size/Size;", "Landroidx/compose/ui/geometry/Size;", "toSizeOrNull-uvyYCjk", "(J)Lcoil/size/Size;", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class AsyncImagePainterKt {
    private static final AsyncImagePainterKt$fakeTransitionTarget$1 fakeTransitionTarget = new TransitionTarget() { // from class: coil.compose.AsyncImagePainterKt$fakeTransitionTarget$1
        @Override // coil.transition.TransitionTarget
        public /* bridge */ /* synthetic */ View getView() {
            return (View) m6526getView();
        }

        /* renamed from: getView, reason: collision with other method in class */
        public Void m6526getView() {
            throw new UnsupportedOperationException();
        }

        @Override // coil.transition.TransitionTarget
        public Drawable getDrawable() {
            return null;
        }
    };

    /* renamed from: rememberAsyncImagePainter-10Xjiaw */
    public static final AsyncImagePainter m6521rememberAsyncImagePainter10Xjiaw(Object model, ImageLoader imageLoader, Painter placeholder, Painter error, Painter fallback, Function1<? super AsyncImagePainter.State.Loading, Unit> function1, Function1<? super AsyncImagePainter.State.Success, Unit> function12, Function1<? super AsyncImagePainter.State.Error, Unit> function13, ContentScale contentScale, int filterQuality, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
        Painter error2;
        Painter fallback2;
        Function1 onLoading;
        Function1 onSuccess;
        Function1 onError;
        ContentScale contentScale2;
        int filterQuality2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(-79978785);
        Painter placeholder2 = (i & 4) != 0 ? null : placeholder;
        if ((i & 8) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 16) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 32) == 0) {
            onLoading = function1;
        } else {
            onLoading = null;
        }
        if ((i & 64) == 0) {
            onSuccess = function12;
        } else {
            onSuccess = null;
        }
        if ((i & 128) == 0) {
            onError = function13;
        } else {
            onError = null;
        }
        if ((i & 256) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 512) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 1024) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-79978785, $changed, $changed1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:100)");
        }
        AsyncImagePainter m6524rememberAsyncImagePainterGSdzBsE = m6524rememberAsyncImagePainterGSdzBsE(new AsyncImageState(model, modelEqualityDelegate2, imageLoader), UtilsKt.transformOf(placeholder2, error2, fallback2), UtilsKt.onStateOf(onLoading, onSuccess, onError), contentScale2, filterQuality2, $composer, (($changed >> 15) & 7168) | (($changed >> 15) & 57344));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6524rememberAsyncImagePainterGSdzBsE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: rememberAsyncImagePainter-3HmZ8SU */
    public static final /* synthetic */ AsyncImagePainter m6522rememberAsyncImagePainter3HmZ8SU(Object model, ImageLoader imageLoader, Painter placeholder, Painter error, Painter fallback, Function1 onLoading, Function1 onSuccess, Function1 onError, ContentScale contentScale, int filterQuality, Composer $composer, int $changed, int i) {
        Painter error2;
        Painter fallback2;
        Function1 onLoading2;
        Function1 onSuccess2;
        Function1 onError2;
        ContentScale contentScale2;
        int filterQuality2;
        $composer.startReplaceableGroup(2140758544);
        Painter placeholder2 = (i & 4) != 0 ? null : placeholder;
        if ((i & 8) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 16) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 32) == 0) {
            onLoading2 = onLoading;
        } else {
            onLoading2 = null;
        }
        if ((i & 64) == 0) {
            onSuccess2 = onSuccess;
        } else {
            onSuccess2 = null;
        }
        if ((i & 128) == 0) {
            onError2 = onError;
        } else {
            onError2 = null;
        }
        if ((i & 256) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 512) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2140758544, $changed, -1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:122)");
        }
        AsyncImagePainter m6524rememberAsyncImagePainterGSdzBsE = m6524rememberAsyncImagePainterGSdzBsE(new AsyncImageState(model, EqualityDelegateKt.getDefaultModelEqualityDelegate(), imageLoader), UtilsKt.transformOf(placeholder2, error2, fallback2), UtilsKt.onStateOf(onLoading2, onSuccess2, onError2), contentScale2, filterQuality2, $composer, (($changed >> 15) & 7168) | (($changed >> 15) & 57344));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6524rememberAsyncImagePainterGSdzBsE;
    }

    /* renamed from: rememberAsyncImagePainter-0YpotYA */
    public static final AsyncImagePainter m6520rememberAsyncImagePainter0YpotYA(Object model, ImageLoader imageLoader, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, ContentScale contentScale, int filterQuality, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int i) {
        Function1 onState;
        ContentScale contentScale2;
        int filterQuality2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(1645646697);
        Function1 transform = (i & 4) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : function1;
        if ((i & 8) == 0) {
            onState = function12;
        } else {
            onState = null;
        }
        if ((i & 16) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 32) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 64) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1645646697, $changed, -1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:166)");
        }
        AsyncImagePainter m6524rememberAsyncImagePainterGSdzBsE = m6524rememberAsyncImagePainterGSdzBsE(new AsyncImageState(model, modelEqualityDelegate2, imageLoader), transform, onState, contentScale2, filterQuality2, $composer, (($changed >> 3) & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (57344 & ($changed >> 3)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6524rememberAsyncImagePainterGSdzBsE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: rememberAsyncImagePainter-5jETZwI */
    public static final /* synthetic */ AsyncImagePainter m6523rememberAsyncImagePainter5jETZwI(Object model, ImageLoader imageLoader, Function1 transform, Function1 onState, ContentScale contentScale, int filterQuality, Composer $composer, int $changed, int i) {
        Function1 onState2;
        ContentScale contentScale2;
        int filterQuality2;
        $composer.startReplaceableGroup(-2020614074);
        Function1 transform2 = (i & 4) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : transform;
        if ((i & 8) == 0) {
            onState2 = onState;
        } else {
            onState2 = null;
        }
        if ((i & 16) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 32) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2020614074, $changed, -1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:184)");
        }
        AsyncImagePainter m6524rememberAsyncImagePainterGSdzBsE = m6524rememberAsyncImagePainterGSdzBsE(new AsyncImageState(model, EqualityDelegateKt.getDefaultModelEqualityDelegate(), imageLoader), transform2, onState2, contentScale2, filterQuality2, $composer, (($changed >> 3) & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (57344 & ($changed >> 3)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6524rememberAsyncImagePainterGSdzBsE;
    }

    /* renamed from: rememberAsyncImagePainter-GSdzBsE */
    private static final AsyncImagePainter m6524rememberAsyncImagePainterGSdzBsE(AsyncImageState state, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, ContentScale contentScale, int filterQuality, Composer $composer, int $changed) {
        Object value$iv;
        $composer.startReplaceableGroup(952940650);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(952940650, $changed, -1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:199)");
        }
        ImageRequest request = UtilsKt.requestOf(state.getModel(), $composer, 8);
        validateRequest(request);
        $composer.startReplaceableGroup(294038899);
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new AsyncImagePainter(request, state.getImageLoader());
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        AsyncImagePainter painter = (AsyncImagePainter) value$iv;
        $composer.endReplaceableGroup();
        painter.setTransform$coil_compose_base_release(function1);
        painter.setOnState$coil_compose_base_release(function12);
        painter.setContentScale$coil_compose_base_release(contentScale);
        painter.m6518setFilterQualityvDHp3xo$coil_compose_base_release(filterQuality);
        ProvidableCompositionLocal<Boolean> localInspectionMode = InspectionModeKt.getLocalInspectionMode();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localInspectionMode);
        ComposerKt.sourceInformationMarkerEnd($composer);
        painter.setPreview$coil_compose_base_release(((Boolean) consume).booleanValue());
        painter.setImageLoader$coil_compose_base_release(state.getImageLoader());
        painter.setRequest$coil_compose_base_release(request);
        painter.onRemembered();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return painter;
    }

    private static final void validateRequest(ImageRequest request) {
        Object data = request.getData();
        if (data instanceof ImageRequest.Builder) {
            unsupportedData("ImageRequest.Builder", "Did you forget to call ImageRequest.Builder.build()?");
            throw new KotlinNothingValueException();
        }
        if (data instanceof ImageBitmap) {
            unsupportedData$default("ImageBitmap", null, 2, null);
            throw new KotlinNothingValueException();
        }
        if (data instanceof ImageVector) {
            unsupportedData$default("ImageVector", null, 2, null);
            throw new KotlinNothingValueException();
        }
        if (data instanceof Painter) {
            unsupportedData$default("Painter", null, 2, null);
            throw new KotlinNothingValueException();
        }
        if (!(request.getTarget() == null)) {
            throw new IllegalArgumentException("request.target must be null.".toString());
        }
    }

    static /* synthetic */ Void unsupportedData$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "If you wish to display this " + str + ", use androidx.compose.foundation.Image.";
        }
        return unsupportedData(str, str2);
    }

    private static final Void unsupportedData(String name, String description) {
        throw new IllegalArgumentException("Unsupported type: " + name + ". " + description);
    }

    /* renamed from: toSizeOrNull-uvyYCjk */
    public static final Size m6525toSizeOrNulluvyYCjk(long $this$toSizeOrNull_u2duvyYCjk) {
        if ($this$toSizeOrNull_u2duvyYCjk == androidx.compose.ui.geometry.Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            return Size.ORIGINAL;
        }
        if (UtilsKt.m6562isPositiveuvyYCjk($this$toSizeOrNull_u2duvyYCjk)) {
            float m3237getWidthimpl = androidx.compose.ui.geometry.Size.m3237getWidthimpl($this$toSizeOrNull_u2duvyYCjk);
            Dimension Dimension = !Float.isInfinite(m3237getWidthimpl) && !Float.isNaN(m3237getWidthimpl) ? Dimensions.Dimension(MathKt.roundToInt(androidx.compose.ui.geometry.Size.m3237getWidthimpl($this$toSizeOrNull_u2duvyYCjk))) : Dimension.Undefined.INSTANCE;
            float m3234getHeightimpl = androidx.compose.ui.geometry.Size.m3234getHeightimpl($this$toSizeOrNull_u2duvyYCjk);
            return new Size(Dimension, (Float.isInfinite(m3234getHeightimpl) || Float.isNaN(m3234getHeightimpl)) ? false : true ? Dimensions.Dimension(MathKt.roundToInt(androidx.compose.ui.geometry.Size.m3234getHeightimpl($this$toSizeOrNull_u2duvyYCjk))) : Dimension.Undefined.INSTANCE);
        }
        return null;
    }
}
