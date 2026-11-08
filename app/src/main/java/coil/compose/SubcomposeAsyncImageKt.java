package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* compiled from: SubcomposeAsyncImage.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¨\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a¸\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aÌ\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010$\u001a\u00020%2\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001aÖ\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2&\b\u0002\u0010(\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2&\b\u0002\u0010+\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2&\b\u0002\u0010-\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u00100\u001a\u0010\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u00101\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010$\u001a\u00020%H\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a¬\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\b\u0002\u0010(\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2&\b\u0002\u0010+\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2&\b\u0002\u0010-\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2\u0016\b\u0002\u0010/\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u00100\u001a\u0010\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u00101\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a\u0095\u0001\u00106\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2$\u0010(\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2$\u0010+\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2$\u0010-\u001a \u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001\u0018\u00010)¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0003¢\u0006\u0002\u00107\u001a[\u00108\u001a\u00020\u0001*\u00020\u00192\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0002\u0010;\u001ae\u00108\u001a\u00020\u0001*\u00020\u00192\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010<\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"SubcomposeAsyncImage", "", "state", "Lcoil/compose/AsyncImageState;", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "clipToBounds", "", "content", "Lcoil/compose/SubcomposeAsyncImageScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "SubcomposeAsyncImage-gl8XCv8", "(Lcoil/compose/AsyncImageState;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "model", "", "imageLoader", "Lcoil/ImageLoader;", "SubcomposeAsyncImage-sKDTAoQ", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "modelEqualityDelegate", "Lcoil/compose/EqualityDelegate;", "SubcomposeAsyncImage-FSyRiR8", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "loading", "Lkotlin/Function2;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "success", "Lcoil/compose/AsyncImagePainter$State$Success;", "error", "Lcoil/compose/AsyncImagePainter$State$Error;", "onLoading", "onSuccess", "onError", "SubcomposeAsyncImage-TCQMD7g", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;IZLcoil/compose/EqualityDelegate;Landroidx/compose/runtime/Composer;III)V", "SubcomposeAsyncImage-Q4Kwu38", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "contentOf", "(Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;)Lkotlin/jvm/functions/Function3;", "SubcomposeAsyncImageContent", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Lcoil/compose/SubcomposeAsyncImageScope;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;II)V", "(Lcoil/compose/SubcomposeAsyncImageScope;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ZLandroidx/compose/runtime/Composer;II)V", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class SubcomposeAsyncImageKt {
    /* renamed from: SubcomposeAsyncImage-TCQMD7g, reason: not valid java name */
    public static final void m6556SubcomposeAsyncImageTCQMD7g(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, Unit> function4, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, Unit> function42, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, Unit> function43, Function1<? super AsyncImagePainter.State.Loading, Unit> function12, Function1<? super AsyncImagePainter.State.Success, Unit> function13, Function1<? super AsyncImagePainter.State.Error, Unit> function14, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Composer $composer, int $changed, int $changed1, int i) {
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
        $composer.startReplaceableGroup(428575962);
        Modifier modifier2 = (i & 8) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 16) == 0) {
            transform = function1;
        } else {
            transform = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i & 32) == 0) {
            loading = function4;
        } else {
            loading = null;
        }
        if ((i & 64) == 0) {
            success = function42;
        } else {
            success = null;
        }
        if ((i & 128) == 0) {
            error = function43;
        } else {
            error = null;
        }
        if ((i & 256) == 0) {
            onLoading = function12;
        } else {
            onLoading = null;
        }
        if ((i & 512) == 0) {
            onSuccess = function13;
        } else {
            onSuccess = null;
        }
        if ((i & 1024) == 0) {
            onError = function14;
        } else {
            onError = null;
        }
        if ((i & 2048) == 0) {
            alignment2 = alignment;
        } else {
            alignment2 = Alignment.INSTANCE.getCenter();
        }
        if ((i & 4096) == 0) {
            contentScale2 = contentScale;
        } else {
            contentScale2 = ContentScale.INSTANCE.getFit();
        }
        if ((i & 8192) == 0) {
            alpha2 = alpha;
        } else {
            alpha2 = 1.0f;
        }
        if ((i & 16384) == 0) {
            colorFilter2 = colorFilter;
        } else {
            colorFilter2 = null;
        }
        if ((32768 & i) == 0) {
            filterQuality2 = filterQuality;
        } else {
            filterQuality2 = DrawScope.INSTANCE.m3961getDefaultFilterQualityfv9h1I();
        }
        if ((65536 & i) == 0) {
            clipToBounds2 = clipToBounds;
        } else {
            clipToBounds2 = true;
        }
        if ((i & 131072) == 0) {
            modelEqualityDelegate2 = modelEqualityDelegate;
        } else {
            modelEqualityDelegate2 = EqualityDelegateKt.getDefaultModelEqualityDelegate();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(428575962, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SubcomposeAsyncImage.kt:75)");
        }
        m6557SubcomposeAsyncImagegl8XCv8(new AsyncImageState(model, modelEqualityDelegate2, imageLoader), contentDescription, modifier2, transform, UtilsKt.onStateOf(onLoading, onSuccess, onError), alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, contentOf(loading, success, error), $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (($changed1 << 12) & 458752) | (($changed1 << 12) & 3670016) | (($changed1 << 12) & 29360128) | (($changed1 << 12) & 234881024) | (1879048192 & ($changed1 << 12)), ($changed1 >> 18) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: SubcomposeAsyncImage-Q4Kwu38, reason: not valid java name */
    public static final /* synthetic */ void m6555SubcomposeAsyncImageQ4Kwu38(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Function4 loading, Function4 success, Function4 error, Function1 onLoading, Function1 onSuccess, Function1 onError, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Composer $composer, int $changed, int $changed1, int i) {
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
        $composer.startReplaceableGroup(934816934);
        Modifier modifier2 = (i & 8) != 0 ? Modifier.INSTANCE : modifier;
        if ((i & 16) == 0) {
            loading2 = loading;
        } else {
            loading2 = null;
        }
        if ((i & 32) == 0) {
            success2 = success;
        } else {
            success2 = null;
        }
        if ((i & 64) == 0) {
            error2 = error;
        } else {
            error2 = null;
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
            ComposerKt.traceEventStart(934816934, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SubcomposeAsyncImage.kt:109)");
        }
        AsyncImageState asyncImageState = new AsyncImageState(model, EqualityDelegateKt.getDefaultModelEqualityDelegate(), imageLoader);
        Function1 onError3 = AsyncImagePainter.INSTANCE.getDefaultTransform();
        m6557SubcomposeAsyncImagegl8XCv8(asyncImageState, contentDescription, modifier2, onError3, UtilsKt.onStateOf(onLoading2, onSuccess2, onError2), alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, true, contentOf(loading2, success2, error2), $composer, ($changed & 112) | 3072 | (($changed >> 3) & 896) | (($changed1 << 15) & 458752) | (($changed1 << 15) & 3670016) | (($changed1 << 15) & 29360128) | (($changed1 << 15) & 234881024) | (($changed1 << 15) & 1879048192), 6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    /* renamed from: SubcomposeAsyncImage-FSyRiR8, reason: not valid java name */
    public static final void m6554SubcomposeAsyncImageFSyRiR8(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, boolean clipToBounds, EqualityDelegate modelEqualityDelegate, Function3<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform;
        Function1 onState;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        boolean clipToBounds2;
        EqualityDelegate modelEqualityDelegate2;
        $composer.startReplaceableGroup(-2125038415);
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
            ComposerKt.traceEventStart(-2125038415, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SubcomposeAsyncImage.kt:168)");
        }
        m6557SubcomposeAsyncImagegl8XCv8(new AsyncImageState(model, modelEqualityDelegate2, imageLoader), contentDescription, modifier2, transform, onState, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, clipToBounds2, function3, $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (($changed >> 3) & 57344) | (($changed >> 3) & 458752) | (($changed >> 3) & 3670016) | (($changed >> 3) & 29360128) | (($changed >> 3) & 234881024) | (($changed1 << 27) & 1879048192), (($changed1 >> 3) & 14) | (($changed1 >> 6) & 112), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    /* renamed from: SubcomposeAsyncImage-sKDTAoQ, reason: not valid java name */
    public static final /* synthetic */ void m6558SubcomposeAsyncImagesKDTAoQ(Object model, String contentDescription, ImageLoader imageLoader, Modifier modifier, Function1 transform, Function1 onState, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, int filterQuality, Function3 content, Composer $composer, int $changed, int $changed1, int i) {
        Function1 transform2;
        Function1 onState2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        int filterQuality2;
        $composer.startReplaceableGroup(10937794);
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
            ComposerKt.traceEventStart(10937794, $changed, $changed1, "coil.compose.SubcomposeAsyncImage (SubcomposeAsyncImage.kt:199)");
        }
        m6557SubcomposeAsyncImagegl8XCv8(new AsyncImageState(model, EqualityDelegateKt.getDefaultModelEqualityDelegate(), imageLoader), contentDescription, modifier2, transform2, onState2, alignment2, contentScale2, alpha2, colorFilter2, filterQuality2, true, content, $composer, ($changed & 112) | (($changed >> 3) & 896) | (($changed >> 3) & 7168) | (($changed >> 3) & 57344) | (($changed >> 3) & 458752) | (($changed >> 3) & 3670016) | (($changed >> 3) & 29360128) | (234881024 & ($changed >> 3)) | (($changed1 << 27) & 1879048192), ($changed1 & 112) | 6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0133  */
    /* renamed from: SubcomposeAsyncImage-gl8XCv8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6557SubcomposeAsyncImagegl8XCv8(final coil.compose.AsyncImageState r39, final java.lang.String r40, androidx.compose.ui.Modifier r41, final kotlin.jvm.functions.Function1<? super coil.compose.AsyncImagePainter.State, ? extends coil.compose.AsyncImagePainter.State> r42, final kotlin.jvm.functions.Function1<? super coil.compose.AsyncImagePainter.State, kotlin.Unit> r43, final androidx.compose.ui.Alignment r44, final androidx.compose.ui.layout.ContentScale r45, final float r46, final androidx.compose.ui.graphics.ColorFilter r47, final int r48, final boolean r49, final kotlin.jvm.functions.Function3<? super coil.compose.SubcomposeAsyncImageScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, androidx.compose.runtime.Composer r51, final int r52, final int r53, final int r54) {
        /*
            Method dump skipped, instructions count: 1009
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.compose.SubcomposeAsyncImageKt.m6557SubcomposeAsyncImagegl8XCv8(coil.compose.AsyncImageState, java.lang.String, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, float, androidx.compose.ui.graphics.ColorFilter, int, boolean, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x02e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SubcomposeAsyncImageContent(final coil.compose.SubcomposeAsyncImageScope r25, androidx.compose.ui.Modifier r26, androidx.compose.ui.graphics.painter.Painter r27, java.lang.String r28, androidx.compose.ui.Alignment r29, androidx.compose.ui.layout.ContentScale r30, float r31, androidx.compose.ui.graphics.ColorFilter r32, boolean r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 799
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.compose.SubcomposeAsyncImageKt.SubcomposeAsyncImageContent(coil.compose.SubcomposeAsyncImageScope, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.painter.Painter, java.lang.String, androidx.compose.ui.Alignment, androidx.compose.ui.layout.ContentScale, float, androidx.compose.ui.graphics.ColorFilter, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public static final /* synthetic */ void SubcomposeAsyncImageContent(final SubcomposeAsyncImageScope $this$SubcomposeAsyncImageContent, Modifier modifier, Painter painter, String contentDescription, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String contentDescription2;
        Alignment alignment2;
        ContentScale contentScale2;
        float alpha2;
        ColorFilter colorFilter2;
        Modifier.Companion modifier3;
        AsyncImagePainter painter2;
        ColorFilter colorFilter3;
        float alpha3;
        int $dirty;
        ContentScale contentScale3;
        ContentScale contentScale4;
        Modifier modifier4;
        Painter painter3;
        float alpha4;
        ColorFilter colorFilter4;
        String contentDescription3;
        Alignment alignment3;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer2 = $composer.startRestartGroup(-263982313);
        int $dirty2 = $changed;
        if ((i & Integer.MIN_VALUE) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 14) == 0) {
            $dirty2 |= $composer2.changed($this$SubcomposeAsyncImageContent) ? 4 : 2;
        }
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 112) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty2 |= 128;
        }
        if (($changed & 7168) == 0) {
            if ((i & 4) == 0) {
                contentDescription2 = contentDescription;
                if ($composer2.changed(contentDescription2)) {
                    i6 = 2048;
                    $dirty2 |= i6;
                }
            } else {
                contentDescription2 = contentDescription;
            }
            i6 = 1024;
            $dirty2 |= i6;
        } else {
            contentDescription2 = contentDescription;
        }
        if (($changed & 57344) == 0) {
            if ((i & 8) == 0) {
                alignment2 = alignment;
                if ($composer2.changed(alignment2)) {
                    i5 = 16384;
                    $dirty2 |= i5;
                }
            } else {
                alignment2 = alignment;
            }
            i5 = 8192;
            $dirty2 |= i5;
        } else {
            alignment2 = alignment;
        }
        if (($changed & 458752) == 0) {
            if ((i & 16) == 0) {
                contentScale2 = contentScale;
                if ($composer2.changed(contentScale2)) {
                    i4 = 131072;
                    $dirty2 |= i4;
                }
            } else {
                contentScale2 = contentScale;
            }
            i4 = 65536;
            $dirty2 |= i4;
        } else {
            contentScale2 = contentScale;
        }
        if (($changed & 3670016) == 0) {
            if ((i & 32) == 0) {
                alpha2 = alpha;
                if ($composer2.changed(alpha2)) {
                    i3 = 1048576;
                    $dirty2 |= i3;
                }
            } else {
                alpha2 = alpha;
            }
            i3 = 524288;
            $dirty2 |= i3;
        } else {
            alpha2 = alpha;
        }
        if (($changed & 29360128) == 0) {
            if ((i & 64) == 0) {
                colorFilter2 = colorFilter;
                if ($composer2.changed(colorFilter2)) {
                    i2 = 8388608;
                    $dirty2 |= i2;
                }
            } else {
                colorFilter2 = colorFilter;
            }
            i2 = 4194304;
            $dirty2 |= i2;
        } else {
            colorFilter2 = colorFilter;
        }
        if (i8 == 2 && (23967451 & $dirty2) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            contentScale4 = contentScale2;
            colorFilter4 = colorFilter2;
            alpha4 = alpha2;
            painter3 = painter;
            contentDescription3 = contentDescription2;
            alignment3 = alignment2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                if (i8 != 0) {
                    painter2 = $this$SubcomposeAsyncImageContent.getPainter();
                    $dirty2 &= -897;
                } else {
                    painter2 = painter;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -7169;
                    contentDescription2 = $this$SubcomposeAsyncImageContent.getContentDescription();
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -57345;
                    alignment2 = $this$SubcomposeAsyncImageContent.getAlignment();
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -458753;
                    contentScale2 = $this$SubcomposeAsyncImageContent.getContentScale();
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                    alpha2 = $this$SubcomposeAsyncImageContent.getAlpha();
                }
                if ((i & 64) != 0) {
                    colorFilter3 = $this$SubcomposeAsyncImageContent.getColorFilter();
                    alpha3 = alpha2;
                    $dirty = $dirty2 & (-29360129);
                    contentScale3 = contentScale2;
                } else {
                    colorFilter3 = colorFilter2;
                    alpha3 = alpha2;
                    $dirty = $dirty2;
                    contentScale3 = contentScale2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if (i8 != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 64) != 0) {
                    painter2 = painter;
                    modifier3 = modifier2;
                    colorFilter3 = colorFilter2;
                    alpha3 = alpha2;
                    $dirty = $dirty2 & (-29360129);
                    contentScale3 = contentScale2;
                } else {
                    painter2 = painter;
                    modifier3 = modifier2;
                    colorFilter3 = colorFilter2;
                    alpha3 = alpha2;
                    $dirty = $dirty2;
                    contentScale3 = contentScale2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-263982313, $dirty, -1, "coil.compose.SubcomposeAsyncImageContent (SubcomposeAsyncImage.kt:363)");
            }
            SubcomposeAsyncImageContent($this$SubcomposeAsyncImageContent, modifier3, painter2, contentDescription2, alignment2, contentScale3, alpha3, colorFilter3, false, $composer2, ($dirty & 14) | 512 | ($dirty & 112) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | ($dirty & 29360128), 128);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentScale4 = contentScale3;
            modifier4 = modifier3;
            painter3 = painter2;
            alpha4 = alpha3;
            colorFilter4 = colorFilter3;
            contentDescription3 = contentDescription2;
            alignment3 = alignment2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final Painter painter4 = painter3;
            final String str = contentDescription3;
            final Alignment alignment4 = alignment3;
            final ContentScale contentScale5 = contentScale4;
            final float f = alpha4;
            final ColorFilter colorFilter5 = colorFilter4;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: coil.compose.SubcomposeAsyncImageKt$SubcomposeAsyncImageContent$4
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    SubcomposeAsyncImageKt.SubcomposeAsyncImageContent(SubcomposeAsyncImageScope.this, modifier5, painter4, str, alignment4, contentScale5, f, colorFilter5, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final Function3<SubcomposeAsyncImageScope, Composer, Integer, Unit> contentOf(final Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, Unit> function4, final Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, Unit> function42, final Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, Unit> function43) {
        return (function4 == null && function42 == null && function43 == null) ? ComposableSingletons$SubcomposeAsyncImageKt.INSTANCE.m6527getLambda1$coil_compose_base_release() : ComposableLambdaKt.composableLambdaInstance(-1302781228, true, new Function3<SubcomposeAsyncImageScope, Composer, Integer, Unit>() { // from class: coil.compose.SubcomposeAsyncImageKt$contentOf$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SubcomposeAsyncImageScope subcomposeAsyncImageScope, Composer composer, Integer num) {
                invoke(subcomposeAsyncImageScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(SubcomposeAsyncImageScope $this$null, Composer $composer, int $changed) {
                boolean draw;
                int $dirty = $changed;
                if (($changed & 14) == 0) {
                    $dirty |= $composer.changed($this$null) ? 4 : 2;
                }
                int $dirty2 = $dirty;
                if (($dirty2 & 91) != 18 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1302781228, $dirty2, -1, "coil.compose.contentOf.<anonymous> (SubcomposeAsyncImage.kt:381)");
                    }
                    boolean draw2 = true;
                    AsyncImagePainter.State state = $this$null.getPainter().getState();
                    if (state instanceof AsyncImagePainter.State.Loading) {
                        $composer.startReplaceableGroup(-418317557);
                        if (function4 != null) {
                            function4.invoke($this$null, state, $composer, Integer.valueOf(($dirty2 & 14) | 64));
                            Unit unit = Unit.INSTANCE;
                            draw2 = false;
                        }
                        $composer.endReplaceableGroup();
                        draw = draw2;
                    } else if (state instanceof AsyncImagePainter.State.Success) {
                        $composer.startReplaceableGroup(-418317557);
                        if (function42 != null) {
                            function42.invoke($this$null, state, $composer, Integer.valueOf(($dirty2 & 14) | 64));
                            Unit unit2 = Unit.INSTANCE;
                            draw2 = false;
                        }
                        $composer.endReplaceableGroup();
                        draw = draw2;
                    } else if (state instanceof AsyncImagePainter.State.Error) {
                        $composer.startReplaceableGroup(-418317557);
                        if (function43 != null) {
                            function43.invoke($this$null, state, $composer, Integer.valueOf(($dirty2 & 14) | 64));
                            Unit unit3 = Unit.INSTANCE;
                            draw2 = false;
                        }
                        $composer.endReplaceableGroup();
                        draw = draw2;
                    } else {
                        if (state instanceof AsyncImagePainter.State.Empty) {
                            $composer.startReplaceableGroup(-418301399);
                            $composer.endReplaceableGroup();
                        } else {
                            $composer.startReplaceableGroup(-418301339);
                            $composer.endReplaceableGroup();
                        }
                        draw = true;
                    }
                    if (draw) {
                        SubcomposeAsyncImageKt.SubcomposeAsyncImageContent($this$null, null, null, null, null, null, 0.0f, null, false, $composer, $dirty2 & 14, 255);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        });
    }
}
