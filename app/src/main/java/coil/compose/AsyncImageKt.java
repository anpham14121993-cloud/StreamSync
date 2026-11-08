package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.size.SizeResolver;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: AsyncImage.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0088\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u009a\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a®\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020!H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aØ\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010%2\u0016\b\u0002\u0010(\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010,\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001aì\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010%2\u0016\b\u0002\u0010(\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010,\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020!H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001aQ\u00102\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00103\u001a\u0002042\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0003¢\u0006\u0002\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00066"}, d2 = {"AsyncImage", "", "state", "Lcoil/compose/AsyncImageState;", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "clipToBounds", "", "AsyncImage-76YX9Dk", "(Lcoil/compose/AsyncImageState;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLandroidx/compose/runtime/Composer;II)V", "model", "", "imageLoader", "Lcoil/ImageLoader;", "AsyncImage-MvsnxeU", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "modelEqualityDelegate", "Lcoil/compose/EqualityDelegate;", "AsyncImage-QgsmV_s", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)V", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "AsyncImage-Q4Kwu38", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "AsyncImage-J-FEaFM", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)V", "Content", "painter", "Lcoil/compose/AsyncImagePainter;", "(Landroidx/compose/ui/Modifier;Lcoil/compose/AsyncImagePainter;Ljava/lang/String;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ZLandroidx/compose/runtime/Composer;I)V", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class AsyncImageKt {
    /* renamed from: AsyncImage-J-FEaFM, reason: not valid java name */
    public static final void m6512AsyncImageJFEaFM(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Painter placeholder, Painter error, Painter fallback, Function1<? super AsyncImagePainter.State.Loading, Unit> function1, Function1<? super AsyncImagePainter.State.Success, Unit> function12, Function1<? super AsyncImagePainter.State.Error, Unit> function13, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
        Painter placeholder2;
        Painter error2;
        Painter fallback2;
        Function1 onLoading;
        Function1 onSuccess;
        Function1 onError;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        boolean clipToBounds2;
        EqualityDelegate modelEqualityDelegate2;
        int filterQuality3;
        $composer.startReplaceableGroup(-1481548872);
        Modifier.Companion modifier2 = (i & 8) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 16) == 0) {
            placeholder2 = placeholder;
        } else {
            placeholder2 = null;
        }
        if ((i & 32) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 64) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 128) == 0) {
            onLoading = function1;
        } else {
            onLoading = null;
        }
        if ((i & 256) == 0) {
            onSuccess = function12;
        } else {
            onSuccess = null;
        }
        if ((i & 512) == 0) {
            onError = function13;
        } else {
            onError = null;
        }
        if ((i & 1024) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 2048) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 4096) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 8192) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 16384) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 32768) == 0) {
            clipToBounds2 = clipToBounds;
        } else {
            clipToBounds2 = true;
        }
        if ((i & 65536) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (!ComposerKt.isTraceInProgress()) {
            filterQuality3 = filterQuality2;
        } else {
            filterQuality3 = filterQuality2;
            ComposerKt.traceEventStart(-1481548872, $changed, $changed1, "coil.compose.AsyncImage (AsyncImage.kt:68)");
        }
        m6511AsyncImage76YX9Dk(new AsyncImageState(model, modelEqualityDelegate2, imageLoader), contentDescription, modifier2, UtilsKt.transformOf(placeholder2, error2, fallback2), UtilsKt.onStateOf(onLoading, onSuccess, onError), alignment2, contentScale2, alpha2, colorFilter2, filterQuality3, clipToBounds2, $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), ($changed1 >> 15) & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: AsyncImage-Q4Kwu38, reason: not valid java name */
    public static final /* synthetic */ void m6514AsyncImageQ4Kwu38(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Painter placeholder, Painter error, Painter fallback, Function1 onLoading, Function1 onSuccess, Function1 onError, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Composer $composer, int $changed, int $changed1, int i) {
        Painter placeholder2;
        Painter error2;
        Painter fallback2;
        Function1 onLoading2;
        Function1 onSuccess2;
        Function1 onError2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        $composer.startReplaceableGroup(-245964807);
        Modifier modifier2 = (i & 8) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 16) == 0) {
            placeholder2 = placeholder;
        } else {
            placeholder2 = null;
        }
        if ((i & 32) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 64) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 128) == 0) {
            onLoading2 = onLoading;
        } else {
            onLoading2 = null;
        }
        if ((i & 256) == 0) {
            onSuccess2 = onSuccess;
        } else {
            onSuccess2 = null;
        }
        if ((i & 512) == 0) {
            onError2 = onError;
        } else {
            onError2 = null;
        }
        if ((i & 1024) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 2048) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 4096) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 8192) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 16384) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-245964807, $changed, $changed1, "coil.compose.AsyncImage (AsyncImage.kt:101)");
        }
        AsyncImageState asyncImageState = new AsyncImageState(model, EqualityDelegateKt.getDefaultModelEqualityDelegate(), imageLoader);
        Function1 onError3 = UtilsKt.transformOf(placeholder2, error2, fallback2);
        m6511AsyncImage76YX9Dk(asyncImageState, contentDescription, modifier2, onError3, UtilsKt.onStateOf(onLoading2, onSuccess2, onError2), alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, true, $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    /* renamed from: AsyncImage-QgsmV_s, reason: not valid java name */
    public static final void m6515AsyncImageQgsmV_s(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform;
        Function1 onState;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        boolean clipToBounds2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(2032051394);
        Modifier modifier2 = (i & 8) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 16) == 0) {
            transform = function1;
        } else {
            transform = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i & 32) == 0) {
            onState = function12;
        } else {
            onState = null;
        }
        if ((i & 64) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 128) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 256) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 512) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 1024) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 2048) == 0) {
            clipToBounds2 = clipToBounds;
        } else {
            clipToBounds2 = true;
        }
        if ((i & 4096) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2032051394, $changed, $changed1, "coil.compose.AsyncImage (AsyncImage.kt:157)");
        }
        m6511AsyncImage76YX9Dk(new AsyncImageState(model, modelEqualityDelegate2, imageLoader), contentDescription, modifier2, transform, onState, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (($changed >> 3) & 57344) | (($changed >> 3) & 458752) | (($changed >> 3) & 3670016) | (($changed >> 3) & 29360128) | (($changed >> 3) & 234881024) | (($changed1 << 27) & 1879048192), ($changed1 >> 3) & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: AsyncImage-MvsnxeU, reason: not valid java name */
    public static final /* synthetic */ void m6513AsyncImageMvsnxeU(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Function1 transform, Function1 onState, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform2;
        Function1 onState2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        $composer.startReplaceableGroup(-2030202961);
        Modifier modifier2 = (i & 8) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 16) == 0) {
            transform2 = transform;
        } else {
            transform2 = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i & 32) == 0) {
            onState2 = onState;
        } else {
            onState2 = null;
        }
        if ((i & 64) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 128) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 256) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 512) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 1024) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2030202961, $changed, $changed1, "coil.compose.AsyncImage (AsyncImage.kt:186)");
        }
        m6511AsyncImage76YX9Dk(new AsyncImageState(model, EqualityDelegateKt.getDefaultModelEqualityDelegate(), imageLoader), contentDescription, modifier2, transform2, onState2, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, true, $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (($changed >> 3) & 57344) | (($changed >> 3) & 458752) | (($changed >> 3) & 3670016) | (($changed >> 3) & 29360128) | (234881024 & ($changed >> 3)) | (($changed1 << 27) & 1879048192), 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: AsyncImage-76YX9Dk, reason: not valid java name */
    public static final void m6511AsyncImage76YX9Dk(final AsyncImageState state, final String contentDescription, final Modifier modifier, final Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, final Function1<? super AsyncImagePainter.State, Unit> function12, final Alignment alignment, final ContentScale contentScale, final float alpha, final ColorFilter colorFilter, final int filterQuality, final boolean clipToBounds, Composer $composer, final int $changed, final int $changed1) {
        Modifier modifier2;
        Composer $composer2 = $composer.startRestartGroup(-421592773);
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 14) == 0) {
            $dirty |= $composer2.changed(state) ? 4 : 2;
        }
        if (($changed & 112) == 0) {
            $dirty |= $composer2.changed(contentDescription) ? 32 : 16;
        }
        if (($changed & 896) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 7168) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if (($changed & 57344) == 0) {
            $dirty |= $composer2.changedInstance(function12) ? 16384 : 8192;
        }
        if (($changed & 458752) == 0) {
            $dirty |= $composer2.changed(alignment) ? 131072 : 65536;
        }
        if (($changed & 3670016) == 0) {
            $dirty |= $composer2.changed(contentScale) ? 1048576 : 524288;
        }
        if (($changed & 29360128) == 0) {
            $dirty |= $composer2.changed(alpha) ? 8388608 : 4194304;
        }
        if ((234881024 & $changed) == 0) {
            $dirty |= $composer2.changed(colorFilter) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 1879048192) == 0) {
            $dirty |= $composer2.changed(filterQuality) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if (($changed1 & 14) == 0) {
            $dirty1 |= $composer2.changed(clipToBounds) ? 4 : 2;
        }
        int $dirty12 = $dirty1;
        if ((1533916891 & $dirty) != 306783378 || ($dirty12 & 11) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-421592773, $dirty, $dirty12, "coil.compose.AsyncImage (AsyncImage.kt:213)");
            }
            ImageRequest request = UtilsKt.requestOfWithSizeResolver(state.getModel(), contentScale, $composer2, (($dirty >> 15) & 112) | 8);
            int $dirty2 = $dirty;
            AsyncImagePainter painter = AsyncImagePainterKt.m6520rememberAsyncImagePainter0YpotYA(request, state.getImageLoader(), function1, function12, contentScale, filterQuality, state.getModelEqualityDelegate(), $composer2, (($dirty >> 3) & 896) | 72 | (($dirty >> 3) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 12) & 458752), 0);
            SizeResolver sizeResolver = request.getSizeResolver();
            if (sizeResolver instanceof ConstraintsSizeResolver) {
                modifier2 = modifier.then((Modifier) sizeResolver);
            } else {
                modifier2 = modifier;
            }
            Content(modifier2, painter, contentDescription, alignment, contentScale, alpha, colorFilter, clipToBounds, $composer2, (($dirty2 << 3) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752) | (($dirty2 >> 6) & 3670016) | (($dirty12 << 21) & 29360128));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: coil.compose.AsyncImageKt$AsyncImage$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    AsyncImageKt.m6511AsyncImage76YX9Dk(AsyncImageState.this, contentDescription, modifier, function1, function12, alignment, contentScale, alpha, colorFilter, filterQuality, clipToBounds, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Content(final androidx.compose.ui.Modifier r25, final coil.compose.AsyncImagePainter r26, final java.lang.String r27, final androidx.compose.ui.Alignment r28, final androidx.compose.ui.layout.ContentScale r29, final float r30, final androidx.compose.ui.graphics.ColorFilter r31, final boolean r32, androidx.compose.runtime.Composer r33, final int r34) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.compose.AsyncImageKt.Content(androidx.compose.ui.Modifier, coil.compose.AsyncImagePainter, java.lang.String, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, float, androidx.compose.ui.graphics.ColorFilter, boolean, androidx.compose.runtime.Composer, int):void");
    }
}
