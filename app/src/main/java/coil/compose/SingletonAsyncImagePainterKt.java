package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import coil.compose.AsyncImagePainter;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: SingletonAsyncImagePainter.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a^\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001ah\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u009c\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\u0016\b\u0002\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a¦\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\u0016\b\u0002\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"rememberAsyncImagePainter", "Lcoil/compose/AsyncImagePainter;", "model", "", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "rememberAsyncImagePainter-19ie5dc", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "modelEqualityDelegate", "Lcoil/compose/EqualityDelegate;", "rememberAsyncImagePainter-EHKIwbg", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "rememberAsyncImagePainter-MqR-F_0", "(Ljava/lang/Object;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "rememberAsyncImagePainter-HtA5bXE", "(Ljava/lang/Object;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/layout/ContentScale;ILcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class SingletonAsyncImagePainterKt {
    /* renamed from: rememberAsyncImagePainter-HtA5bXE, reason: not valid java name */
    public static final AsyncImagePainter m6548rememberAsyncImagePainterHtA5bXE(Object model, Painter placeholder, Painter error, Painter fallback, Function1<? super AsyncImagePainter.State.Loading, Unit> function1, Function1<? super AsyncImagePainter.State.Success, Unit> function12, Function1<? super AsyncImagePainter.State.Error, Unit> function13, ContentScale contentScale, int filterQuality, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int i) {
        Painter error2;
        Painter fallback2;
        Function1 onLoading;
        Function1 onSuccess;
        Function1 onError;
        ContentScale contentScale2;
        int filterQuality2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(1445305568);
        Painter placeholder2 = (i & 2) != 0 ? null : placeholder;
        if ((i & 4) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 8) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 16) == 0) {
            onLoading = function1;
        } else {
            onLoading = null;
        }
        if ((i & 32) == 0) {
            onSuccess = function12;
        } else {
            onSuccess = null;
        }
        if ((i & 64) == 0) {
            onError = function13;
        } else {
            onError = null;
        }
        if ((i & 128) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 256) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 512) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1445305568, $changed, -1, "coil.compose.rememberAsyncImagePainter (SingletonAsyncImagePainter.kt:59)");
        }
        AsyncImagePainter m6521rememberAsyncImagePainter10Xjiaw = AsyncImagePainterKt.m6521rememberAsyncImagePainter10Xjiaw(model, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), placeholder2, error2, fallback2, onLoading, onSuccess, onError, contentScale2, filterQuality2, modelEqualityDelegate2, $composer, (($changed << 3) & 458752) | 37448 | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (1879048192 & ($changed << 3)), ($changed >> 27) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6521rememberAsyncImagePainter10Xjiaw;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: rememberAsyncImagePainter-MqR-F_0, reason: not valid java name */
    public static final /* synthetic */ AsyncImagePainter m6549rememberAsyncImagePainterMqRF_0(Object model, Painter placeholder, Painter error, Painter fallback, Function1 onLoading, Function1 onSuccess, Function1 onError, ContentScale contentScale, int filterQuality, Composer $composer, int $changed, int i) {
        Painter error2;
        Painter fallback2;
        Function1 onLoading2;
        Function1 onSuccess2;
        Function1 onError2;
        ContentScale contentScale2;
        int filterQuality2;
        $composer.startReplaceableGroup(533921043);
        Painter placeholder2 = (i & 2) != 0 ? null : placeholder;
        if ((i & 4) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 8) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 16) == 0) {
            onLoading2 = onLoading;
        } else {
            onLoading2 = null;
        }
        if ((i & 32) == 0) {
            onSuccess2 = onSuccess;
        } else {
            onSuccess2 = null;
        }
        if ((i & 64) == 0) {
            onError2 = onError;
        } else {
            onError2 = null;
        }
        if ((i & 128) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 256) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(533921043, $changed, -1, "coil.compose.rememberAsyncImagePainter (SingletonAsyncImagePainter.kt:86)");
        }
        AsyncImagePainter m6521rememberAsyncImagePainter10Xjiaw = AsyncImagePainterKt.m6521rememberAsyncImagePainter10Xjiaw(model, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), placeholder2, error2, fallback2, onLoading2, onSuccess2, onError2, contentScale2, filterQuality2, null, $composer, (($changed << 3) & 458752) | 37448 | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (1879048192 & ($changed << 3)), 0, 1024);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6521rememberAsyncImagePainter10Xjiaw;
    }

    /* renamed from: rememberAsyncImagePainter-EHKIwbg, reason: not valid java name */
    public static final AsyncImagePainter m6547rememberAsyncImagePainterEHKIwbg(Object model, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, ContentScale contentScale, int filterQuality, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int i) {
        Function1 onState;
        ContentScale contentScale2;
        int filterQuality2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(236159766);
        Function1 transform = (i & 2) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : function1;
        if ((i & 4) == 0) {
            onState = function12;
        } else {
            onState = null;
        }
        if ((i & 8) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 16) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 32) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(236159766, $changed, -1, "coil.compose.rememberAsyncImagePainter (SingletonAsyncImagePainter.kt:133)");
        }
        AsyncImagePainter m6520rememberAsyncImagePainter0YpotYA = AsyncImagePainterKt.m6520rememberAsyncImagePainter0YpotYA(model, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), transform, onState, contentScale2, filterQuality2, modelEqualityDelegate2, $composer, (($changed << 3) & 896) | 72 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (3670016 & ($changed << 3)), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6520rememberAsyncImagePainter0YpotYA;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: rememberAsyncImagePainter-19ie5dc, reason: not valid java name */
    public static final /* synthetic */ AsyncImagePainter m6546rememberAsyncImagePainter19ie5dc(Object model, Function1 transform, Function1 onState, ContentScale contentScale, int filterQuality, Composer $composer, int $changed, int i) {
        Function1 onState2;
        ContentScale contentScale2;
        int filterQuality2;
        $composer.startReplaceableGroup(-1494234083);
        Function1 transform2 = (i & 2) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : transform;
        if ((i & 4) == 0) {
            onState2 = onState;
        } else {
            onState2 = null;
        }
        if ((i & 8) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 16) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1494234083, $changed, -1, "coil.compose.rememberAsyncImagePainter (SingletonAsyncImagePainter.kt:152)");
        }
        AsyncImagePainter m6520rememberAsyncImagePainter0YpotYA = AsyncImagePainterKt.m6520rememberAsyncImagePainter0YpotYA(model, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), transform2, onState2, contentScale2, filterQuality2, null, $composer, (($changed << 3) & 896) | 72 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (458752 & ($changed << 3)), 64);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m6520rememberAsyncImagePainter0YpotYA;
    }
}
