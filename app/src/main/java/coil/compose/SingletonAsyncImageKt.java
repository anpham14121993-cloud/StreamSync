package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
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

/* compiled from: SingletonAsyncImage.kt */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0092\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a¦\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001aÐ\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010$\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010&\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001aä\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001f2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010$\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010&\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"AsyncImage", "", "model", "", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "AsyncImage-3HmZ8SU", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;II)V", "clipToBounds", "", "modelEqualityDelegate", "Lcoil/compose/EqualityDelegate;", "AsyncImage-gl8XCv8", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)V", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "AsyncImage-ylYTKUw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "AsyncImage-Vb_qNX0", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)V", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class SingletonAsyncImageKt {
    /* renamed from: AsyncImage-Vb_qNX0, reason: not valid java name */
    public static final void m6543AsyncImageVb_qNX0(Object model, String contentDescription, Modifier modifier, Painter placeholder, Painter error, Painter fallback, Function1<? super AsyncImagePainter.State.Loading, Unit> function1, Function1<? super AsyncImagePainter.State.Success, Unit> function12, Function1<? super AsyncImagePainter.State.Error, Unit> function13, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
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
        $composer.startReplaceableGroup(1693837359);
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) == 0) {
            placeholder2 = placeholder;
        } else {
            placeholder2 = null;
        }
        if ((i & 16) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 32) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 64) == 0) {
            onLoading = function1;
        } else {
            onLoading = null;
        }
        if ((i & 128) == 0) {
            onSuccess = function12;
        } else {
            onSuccess = null;
        }
        if ((i & 256) == 0) {
            onError = function13;
        } else {
            onError = null;
        }
        if ((i & 512) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 1024) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 2048) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 4096) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 8192) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 16384) == 0) {
            clipToBounds2 = clipToBounds;
        } else {
            clipToBounds2 = true;
        }
        if ((i & 32768) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1693837359, $changed, $changed1, "coil.compose.AsyncImage (SingletonAsyncImage.kt:65)");
        }
        AsyncImageKt.m6512AsyncImageJFEaFM(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, placeholder2, error2, fallback2, onLoading, onSuccess, onError, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, modelEqualityDelegate2, $composer, ($changed & 112) | 2392584 | (($changed << 3) & 7168) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 3) & 896) | (($changed1 << 3) & 7168) | (($changed1 << 3) & 57344) | (($changed1 << 3) & 458752) | (3670016 & ($changed1 << 3)), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: AsyncImage-ylYTKUw, reason: not valid java name */
    public static final /* synthetic */ void m6545AsyncImageylYTKUw(Object model, String contentDescription, Modifier modifier, Painter placeholder, Painter error, Painter fallback, Function1 onLoading, Function1 onSuccess, Function1 onError, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Composer $composer, int $changed, int $changed1, int i) {
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
        $composer.startReplaceableGroup(2027616330);
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) == 0) {
            placeholder2 = placeholder;
        } else {
            placeholder2 = null;
        }
        if ((i & 16) == 0) {
            error2 = error;
        } else {
            error2 = null;
        }
        if ((i & 32) == 0) {
            fallback2 = fallback;
        } else {
            fallback2 = error2;
        }
        if ((i & 64) == 0) {
            onLoading2 = onLoading;
        } else {
            onLoading2 = null;
        }
        if ((i & 128) == 0) {
            onSuccess2 = onSuccess;
        } else {
            onSuccess2 = null;
        }
        if ((i & 256) == 0) {
            onError2 = onError;
        } else {
            onError2 = null;
        }
        if ((i & 512) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 1024) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 2048) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 4096) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 8192) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2027616330, $changed, $changed1, "coil.compose.AsyncImage (SingletonAsyncImage.kt:103)");
        }
        AsyncImageKt.m6512AsyncImageJFEaFM(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, placeholder2, error2, fallback2, onLoading2, onSuccess2, onError2, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, false, null, $composer, ($changed & 112) | 2392584 | (($changed << 3) & 7168) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 3) & 896) | (($changed1 << 3) & 7168) | (57344 & ($changed1 << 3)), 98304);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    /* renamed from: AsyncImage-gl8XCv8, reason: not valid java name */
    public static final void m6544AsyncImagegl8XCv8(Object model, String contentDescription, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform;
        Function1 onState;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        boolean clipToBounds2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(1451072229);
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) == 0) {
            transform = function1;
        } else {
            transform = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i & 16) == 0) {
            onState = function12;
        } else {
            onState = null;
        }
        if ((i & 32) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 64) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 128) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 256) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 512) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((i & 1024) == 0) {
            clipToBounds2 = clipToBounds;
        } else {
            clipToBounds2 = true;
        }
        if ((i & 2048) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1451072229, $changed, $changed1, "coil.compose.AsyncImage (SingletonAsyncImage.kt:161)");
        }
        AsyncImageKt.m6515AsyncImageQgsmV_s(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, transform, onState, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, modelEqualityDelegate2, $composer, ($changed & 112) | 520 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 3) & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: AsyncImage-3HmZ8SU, reason: not valid java name */
    public static final /* synthetic */ void m6542AsyncImage3HmZ8SU(Object model, String contentDescription, Modifier modifier, Function1 transform, Function1 onState, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Composer $composer, int $changed, int i) {
        Function1 transform2;
        Function1 onState2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        $composer.startReplaceableGroup(-941517612);
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) == 0) {
            transform2 = transform;
        } else {
            transform2 = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i & 16) == 0) {
            onState2 = onState;
        } else {
            onState2 = null;
        }
        if ((i & 32) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 64) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 128) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 256) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((i & 512) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-941517612, $changed, -1, "coil.compose.AsyncImage (SingletonAsyncImage.kt:191)");
        }
        AsyncImageKt.m6515AsyncImageQgsmV_s(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, transform2, onState2, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, false, null, $composer, ($changed & 112) | 520 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (1879048192 & ($changed << 3)), ($changed >> 27) & 14, 6144);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }
}
