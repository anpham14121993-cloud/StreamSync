package coil.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSizeKt;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.request.NullRequestDataException;
import coil.size.Dimension;
import coil.size.Dimensions;
import coil.size.Scale;
import coil.size.Size;
import coil.size.SizeResolver;
import coil.size.SizeResolvers;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: utils.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aX\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000fH\u0001\u001a\u0017\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0001¢\u0006\u0002\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\u0010 \u001a2\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010#H\u0001\u001a\u001e\u0010&\u001a\u00020'*\u00020\u00052\u0006\u0010(\u001a\u00020'H\u0000ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u001e\u0010+\u001a\u00020'*\u00020\u00052\u0006\u0010,\u001a\u00020'H\u0000ø\u0001\u0000¢\u0006\u0004\b-\u0010*\u001a\u0016\u0010.\u001a\u00020/*\u00020/2\b\u0010.\u001a\u0004\u0018\u000100H\u0001\u001a\u001b\u00101\u001a\u00020'*\u00020'2\f\u00102\u001a\b\u0012\u0004\u0012\u00020'03H\u0080\b\u001a\u0016\u00104\u001a\u000205*\u00020\u000bH\u0000ø\u0001\u0000¢\u0006\u0004\b6\u00107\u001a\f\u00108\u001a\u000209*\u00020\u001fH\u0001\u001a\u0018\u0010:\u001a\u0004\u0018\u00010;*\u00020\u0005H\u0001ø\u0001\u0000¢\u0006\u0004\b<\u0010=\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\"\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006>"}, d2 = {"OriginalSizeResolver", "Lcoil/size/SizeResolver;", "getOriginalSizeResolver", "()Lcoil/size/SizeResolver;", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "isPositive", "", "Landroidx/compose/ui/geometry/Size;", "isPositive-uvyYCjk", "(J)Z", "onStateOf", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "requestOf", "Lcoil/request/ImageRequest;", "model", "", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Lcoil/request/ImageRequest;", "requestOfWithSizeResolver", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "(Ljava/lang/Object;Landroidx/compose/ui/layout/ContentScale;Landroidx/compose/runtime/Composer;I)Lcoil/request/ImageRequest;", "transformOf", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "constrainHeight", "", "height", "constrainHeight-K40F9xA", "(JF)F", "constrainWidth", "width", "constrainWidth-K40F9xA", "contentDescription", "Landroidx/compose/ui/Modifier;", "", "takeOrElse", "block", "Lkotlin/Function0;", "toIntSize", "Landroidx/compose/ui/unit/IntSize;", "toIntSize-uvyYCjk", "(J)J", "toScale", "Lcoil/size/Scale;", "toSizeOrNull", "Lcoil/size/Size;", "toSizeOrNull-BRTryo0", "(J)Lcoil/size/Size;", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class UtilsKt {
    private static final long ZeroConstraints = Constraints.INSTANCE.m5700fixedJhjzzOo(0, 0);
    private static final SizeResolver OriginalSizeResolver = SizeResolvers.create(Size.ORIGINAL);

    public static final ImageRequest requestOf(Object model, Composer $composer, int $changed) {
        Object value$iv;
        $composer.startReplaceableGroup(1087186730);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1087186730, $changed, -1, "coil.compose.requestOf (utils.kt:31)");
        }
        if (model instanceof ImageRequest) {
            ImageRequest imageRequest = (ImageRequest) model;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return imageRequest;
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        $composer.startReplaceableGroup(375474364);
        boolean invalid$iv = $composer.changed(context) | $composer.changed(model);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new ImageRequest.Builder(context).data(model).build();
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ImageRequest imageRequest2 = (ImageRequest) value$iv;
        $composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return imageRequest2;
    }

    public static final ImageRequest requestOfWithSizeResolver(Object model, ContentScale contentScale, Composer $composer, int $changed) {
        Object value$iv;
        ConstraintsSizeResolver sizeResolver;
        Object value$iv2;
        Object value$iv3;
        $composer.startReplaceableGroup(1677680258);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1677680258, $changed, -1, "coil.compose.requestOfWithSizeResolver (utils.kt:50)");
        }
        if ((model instanceof ImageRequest) && ((ImageRequest) model).getDefined().getSizeResolver() != null) {
            ImageRequest imageRequest = (ImageRequest) model;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return imageRequest;
        }
        $composer.startReplaceableGroup(-679565543);
        if (Intrinsics.areEqual(contentScale, ContentScale.INSTANCE.getNone())) {
            sizeResolver = OriginalSizeResolver;
        } else {
            $composer.startReplaceableGroup(-679565452);
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new ConstraintsSizeResolver();
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer.endReplaceableGroup();
            sizeResolver = (ConstraintsSizeResolver) value$iv;
        }
        $composer.endReplaceableGroup();
        if (model instanceof ImageRequest) {
            $composer.startReplaceableGroup(-679565365);
            $composer.startReplaceableGroup(-679565358);
            boolean invalid$iv = $composer.changed(model) | $composer.changed(sizeResolver);
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = ImageRequest.newBuilder$default((ImageRequest) model, null, 1, null).size(sizeResolver).build();
                $composer.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv2;
            }
            ImageRequest imageRequest2 = (ImageRequest) value$iv3;
            $composer.endReplaceableGroup();
            $composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceableGroup();
            return imageRequest2;
        }
        $composer.startReplaceableGroup(-679565199);
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        $composer.startReplaceableGroup(-679565153);
        boolean invalid$iv2 = $composer.changed(context) | $composer.changed(model) | $composer.changed(sizeResolver);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new ImageRequest.Builder(context).data(model).size(sizeResolver).build();
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv3;
        }
        ImageRequest imageRequest3 = (ImageRequest) value$iv2;
        $composer.endReplaceableGroup();
        $composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return imageRequest3;
    }

    public static final Function1<AsyncImagePainter.State, AsyncImagePainter.State> transformOf(final Painter placeholder, final Painter error, final Painter fallback) {
        if (placeholder != null || error != null || fallback != null) {
            return new Function1<AsyncImagePainter.State, AsyncImagePainter.State>() { // from class: coil.compose.UtilsKt$transformOf$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final AsyncImagePainter.State invoke(AsyncImagePainter.State state) {
                    AsyncImagePainter.State.Error copy$default;
                    if (state instanceof AsyncImagePainter.State.Loading) {
                        return Painter.this != null ? ((AsyncImagePainter.State.Loading) state).copy(Painter.this) : (AsyncImagePainter.State.Loading) state;
                    }
                    if (!(state instanceof AsyncImagePainter.State.Error)) {
                        return state;
                    }
                    if (((AsyncImagePainter.State.Error) state).getResult().getThrowable() instanceof NullRequestDataException) {
                        copy$default = fallback != null ? AsyncImagePainter.State.Error.copy$default((AsyncImagePainter.State.Error) state, fallback, null, 2, null) : (AsyncImagePainter.State.Error) state;
                    } else {
                        copy$default = error != null ? AsyncImagePainter.State.Error.copy$default((AsyncImagePainter.State.Error) state, error, null, 2, null) : (AsyncImagePainter.State.Error) state;
                    }
                    return copy$default;
                }
            };
        }
        return AsyncImagePainter.INSTANCE.getDefaultTransform();
    }

    public static final Function1<AsyncImagePainter.State, Unit> onStateOf(final Function1<? super AsyncImagePainter.State.Loading, Unit> function1, final Function1<? super AsyncImagePainter.State.Success, Unit> function12, final Function1<? super AsyncImagePainter.State.Error, Unit> function13) {
        if (function1 != null || function12 != null || function13 != null) {
            return new Function1<AsyncImagePainter.State, Unit>() { // from class: coil.compose.UtilsKt$onStateOf$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AsyncImagePainter.State state) {
                    invoke2(state);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AsyncImagePainter.State state) {
                    if (state instanceof AsyncImagePainter.State.Loading) {
                        Function1<AsyncImagePainter.State.Loading, Unit> function14 = function1;
                        if (function14 != null) {
                            function14.invoke(state);
                            return;
                        }
                        return;
                    }
                    if (state instanceof AsyncImagePainter.State.Success) {
                        Function1<AsyncImagePainter.State.Success, Unit> function15 = function12;
                        if (function15 != null) {
                            function15.invoke(state);
                            return;
                        }
                        return;
                    }
                    if (!(state instanceof AsyncImagePainter.State.Error)) {
                        boolean z = state instanceof AsyncImagePainter.State.Empty;
                        return;
                    }
                    Function1<AsyncImagePainter.State.Error, Unit> function16 = function13;
                    if (function16 != null) {
                        function16.invoke(state);
                    }
                }
            };
        }
        return null;
    }

    public static final Modifier contentDescription(Modifier $this$contentDescription, final String contentDescription) {
        if (contentDescription != null) {
            return SemanticsModifierKt.semantics$default($this$contentDescription, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: coil.compose.UtilsKt$contentDescription$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.setContentDescription($this$semantics, contentDescription);
                    SemanticsPropertiesKt.m5079setRolekuIjeqM($this$semantics, Role.INSTANCE.m5066getImageo7Vup1c());
                }
            }, 1, null);
        }
        return $this$contentDescription;
    }

    public static final Scale toScale(ContentScale $this$toScale) {
        return Intrinsics.areEqual($this$toScale, ContentScale.INSTANCE.getFit()) ? true : Intrinsics.areEqual($this$toScale, ContentScale.INSTANCE.getInside()) ? Scale.FIT : Scale.FILL;
    }

    /* renamed from: toSizeOrNull-BRTryo0, reason: not valid java name */
    public static final Size m6564toSizeOrNullBRTryo0(long $this$toSizeOrNull_u2dBRTryo0) {
        if (Constraints.m5696isZeroimpl($this$toSizeOrNull_u2dBRTryo0)) {
            return null;
        }
        Dimension width = Constraints.m5688getHasBoundedWidthimpl($this$toSizeOrNull_u2dBRTryo0) ? Dimensions.Dimension(Constraints.m5692getMaxWidthimpl($this$toSizeOrNull_u2dBRTryo0)) : Dimension.Undefined.INSTANCE;
        Dimension height = Constraints.m5687getHasBoundedHeightimpl($this$toSizeOrNull_u2dBRTryo0) ? Dimensions.Dimension(Constraints.m5691getMaxHeightimpl($this$toSizeOrNull_u2dBRTryo0)) : Dimension.Undefined.INSTANCE;
        return new Size(width, height);
    }

    /* renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final float m6561constrainWidthK40F9xA(long $this$constrainWidth_u2dK40F9xA, float width) {
        return RangesKt.coerceIn(width, Constraints.m5694getMinWidthimpl($this$constrainWidth_u2dK40F9xA), Constraints.m5692getMaxWidthimpl($this$constrainWidth_u2dK40F9xA));
    }

    /* renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final float m6560constrainHeightK40F9xA(long $this$constrainHeight_u2dK40F9xA, float height) {
        return RangesKt.coerceIn(height, Constraints.m5693getMinHeightimpl($this$constrainHeight_u2dK40F9xA), Constraints.m5691getMaxHeightimpl($this$constrainHeight_u2dK40F9xA));
    }

    public static final float takeOrElse(float $this$takeOrElse, Function0<Float> function0) {
        return !Float.isInfinite($this$takeOrElse) && !Float.isNaN($this$takeOrElse) ? $this$takeOrElse : function0.invoke().floatValue();
    }

    /* renamed from: toIntSize-uvyYCjk, reason: not valid java name */
    public static final long m6563toIntSizeuvyYCjk(long $this$toIntSize_u2duvyYCjk) {
        return IntSizeKt.IntSize(MathKt.roundToInt(androidx.compose.ui.geometry.Size.m3237getWidthimpl($this$toIntSize_u2duvyYCjk)), MathKt.roundToInt(androidx.compose.ui.geometry.Size.m3234getHeightimpl($this$toIntSize_u2duvyYCjk)));
    }

    /* renamed from: isPositive-uvyYCjk, reason: not valid java name */
    public static final boolean m6562isPositiveuvyYCjk(long $this$isPositive) {
        return ((double) androidx.compose.ui.geometry.Size.m3237getWidthimpl($this$isPositive)) >= 0.5d && ((double) androidx.compose.ui.geometry.Size.m3234getHeightimpl($this$isPositive)) >= 0.5d;
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final SizeResolver getOriginalSizeResolver() {
        return OriginalSizeResolver;
    }
}
