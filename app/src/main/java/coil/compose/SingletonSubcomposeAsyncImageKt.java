package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.ContentScale;
import androidx.profileinstaller.ProfileVerifier;
import coil.compose.AsyncImagePainter;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* compiled from: SingletonSubcomposeAsyncImage.kt */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a°\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001aÄ\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001aÎ\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2&\b\u0002\u0010\"\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010#¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192&\b\u0002\u0010%\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0001\u0018\u00010#¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192&\b\u0002\u0010'\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0001\u0018\u00010#¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192\u0016\b\u0002\u0010)\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010+\u001a\u0010\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a¤\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\b\u0002\u0010\"\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010#¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192&\b\u0002\u0010%\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0001\u0018\u00010#¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192&\b\u0002\u0010'\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0001\u0018\u00010#¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192\u0016\b\u0002\u0010)\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010*\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010+\u001a\u0010\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00060"}, d2 = {"SubcomposeAsyncImage", "", "model", "", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "content", "Lcoil/compose/SubcomposeAsyncImageScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "SubcomposeAsyncImage-10Xjiaw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "clipToBounds", "", "modelEqualityDelegate", "Lcoil/compose/EqualityDelegate;", "SubcomposeAsyncImage-OsCPg7o", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "loading", "Lkotlin/Function2;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "success", "Lcoil/compose/AsyncImagePainter$State$Success;", "error", "Lcoil/compose/AsyncImagePainter$State$Error;", "onLoading", "onSuccess", "onError", "SubcomposeAsyncImage-J-FEaFM", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)V", "SubcomposeAsyncImage-ylYTKUw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class SingletonSubcomposeAsyncImageKt {
    /* renamed from: SubcomposeAsyncImage-J-FEaFM, reason: not valid java name */
    public static final void m6551SubcomposeAsyncImageJFEaFM(Object model, String contentDescription, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, Unit> function4, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, Unit> function42, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, Unit> function43, Function1<? super AsyncImagePainter.State.Loading, Unit> function12, Function1<? super AsyncImagePainter.State.Success, Unit> function13, Function1<? super AsyncImagePainter.State.Error, Unit> function14, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform;
        Function4 loading;
        Function4 success;
        Function4 error;
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
        $composer.startReplaceableGroup(1925822313);
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) == 0) {
            transform = function1;
        } else {
            transform = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i & 16) == 0) {
            loading = function4;
        } else {
            loading = null;
        }
        if ((i & 32) == 0) {
            success = function42;
        } else {
            success = null;
        }
        if ((i & 64) == 0) {
            error = function43;
        } else {
            error = null;
        }
        if ((i & 128) == 0) {
            onLoading = function12;
        } else {
            onLoading = null;
        }
        if ((i & 256) == 0) {
            onSuccess = function13;
        } else {
            onSuccess = null;
        }
        if ((i & 512) == 0) {
            onError = function14;
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
        if ((32768 & i) == 0) {
            clipToBounds2 = clipToBounds;
        } else {
            clipToBounds2 = true;
        }
        if ((i & 65536) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1925822313, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:66)");
        }
        SubcomposeAsyncImageKt.m6556SubcomposeAsyncImageTCQMD7g(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, transform, loading, success, error, onLoading, onSuccess, onError, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, modelEqualityDelegate2, $composer, ($changed & 112) | 520 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 3) & 896) | (($changed1 << 3) & 7168) | (($changed1 << 3) & 57344) | (($changed1 << 3) & 458752) | (($changed1 << 3) & 3670016) | (($changed1 << 3) & 29360128), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: SubcomposeAsyncImage-ylYTKUw, reason: not valid java name */
    public static final /* synthetic */ void m6553SubcomposeAsyncImageylYTKUw(Object model, String contentDescription, Modifier modifier, Function4 loading, Function4 success, Function4 error, Function1 onLoading, Function1 onSuccess, Function1 onError, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Composer $composer, int $changed, int $changed1, int i) {
        Function4 loading2;
        Function4 success2;
        Function4 error2;
        Function1 onLoading2;
        Function1 onSuccess2;
        Function1 onError2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        $composer.startReplaceableGroup(1047090393);
        Modifier modifier2 = (i & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 8) == 0) {
            loading2 = loading;
        } else {
            loading2 = null;
        }
        if ((i & 16) == 0) {
            success2 = success;
        } else {
            success2 = null;
        }
        if ((i & 32) == 0) {
            error2 = error;
        } else {
            error2 = null;
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
            ComposerKt.traceEventStart(1047090393, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:105)");
        }
        SubcomposeAsyncImageKt.m6556SubcomposeAsyncImageTCQMD7g(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, AsyncImagePainter.INSTANCE.getDefaultTransform(), loading2, success2, error2, onLoading2, onSuccess2, onError2, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, false, null, $composer, ($changed & 112) | 520 | (($changed << 3) & 7168) | (($changed << 6) & 458752) | (($changed << 6) & 3670016) | (($changed << 6) & 29360128) | (($changed << 6) & 234881024) | (($changed << 6) & 1879048192), (($changed >> 24) & 14) | (($changed >> 24) & 112) | (($changed1 << 6) & 896) | (($changed1 << 6) & 7168) | (($changed1 << 6) & 57344) | (($changed1 << 6) & 458752), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    /* renamed from: SubcomposeAsyncImage-OsCPg7o, reason: not valid java name */
    public static final void m6552SubcomposeAsyncImageOsCPg7o(Object model, String contentDescription, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Function3<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform;
        Function1 onState;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        boolean clipToBounds2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(241832818);
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
            ComposerKt.traceEventStart(241832818, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:166)");
        }
        SubcomposeAsyncImageKt.m6554SubcomposeAsyncImageFSyRiR8(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, transform, onState, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, modelEqualityDelegate2, function3, $composer, ($changed & 112) | 520 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 3) & 896) | (($changed1 << 3) & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: SubcomposeAsyncImage-10Xjiaw, reason: not valid java name */
    public static final /* synthetic */ void m6550SubcomposeAsyncImage10Xjiaw(Object model, String contentDescription, Modifier modifier, Function1 transform, Function1 onState, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Function3 content, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform2;
        Function1 onState2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        $composer.startReplaceableGroup(1571662781);
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
            ComposerKt.traceEventStart(1571662781, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:198)");
        }
        SubcomposeAsyncImageKt.m6554SubcomposeAsyncImageFSyRiR8(model, contentDescription, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), $composer, 6), modifier2, transform2, onState2, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, false, null, content, $composer, ($changed & 112) | 520 | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (1879048192 & ($changed << 3)), (($changed >> 27) & 14) | (($changed1 << 9) & 7168), 6144);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }
}
