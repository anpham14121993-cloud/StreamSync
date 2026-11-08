package com.streamsync.app.ui.catalog;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope;
import androidx.compose.foundation.lazy.grid.LazyGridSpanKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: CatalogBrowseScreen.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\u001a7\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH\u0007¢\u0006\u0002\u0010\u000b\u001a@\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\b\u0013H\u0003¢\u0006\u0002\u0010\u0014\u001a\u0011\u0010\u0015\u001a\u00020\u0016*\u00020\u0017H\u0002¢\u0006\u0002\u0010\u0018\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019²\u0006\n\u0010\u001a\u001a\u00020\u001bX\u008a\u0084\u0002"}, d2 = {"TRIGGER_AHEAD_ITEMS", "", "CatalogBrowseScreen", "", "row", "Lcom/streamsync/app/data/model/CatalogRow;", "onBack", "Lkotlin/Function0;", "onItemClick", "Lkotlin/Function1;", "Lcom/streamsync/app/data/model/MediaItem;", "(Lcom/streamsync/app/data/model/CatalogRow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "CatalogGlassButton", "onClick", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "GridItemSpanFull", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;)J", "app_mobileDebug", "loadMore", ""}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class CatalogBrowseScreenKt {
    private static final int TRIGGER_AHEAD_ITEMS = 8;

    /* JADX WARN: Removed duplicated region for block: B:102:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03f3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0365  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CatalogBrowseScreen(final com.streamsync.app.data.model.CatalogRow r66, final kotlin.jvm.functions.Function0<kotlin.Unit> r67, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r68, androidx.compose.runtime.Composer r69, final int r70) {
        /*
            Method dump skipped, instructions count: 1403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.CatalogBrowseScreenKt.CatalogBrowseScreen(com.streamsync.app.data.model.CatalogRow, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CatalogBrowseScreen$lambda$6$lambda$3(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CatalogGlassButton(final Function0<Unit> function0, final String contentDescription, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long m3407copywmQWz5c;
        Modifier m3080shadows4CzXII;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-216495148);
        ComposerKt.sourceInformation($composer2, "C(CatalogGlassButton)P(3,1,2)317@13813L477:CatalogBrowseScreen.kt#aadnbi");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(contentDescription) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            modifier2 = modifier;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-216495148, $dirty2, -1, "com.streamsync.app.ui.catalog.CatalogGlassButton (CatalogBrowseScreen.kt:316)");
            }
            RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r10) : 0.45f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(Color.INSTANCE.m3435getBlack0d7_KjU()) : 0.0f);
            long m3446getWhite0d7_KjU = Color.INSTANCE.m3446getWhite0d7_KjU();
            m3080shadows4CzXII = ShadowKt.m3080shadows4CzXII(SizeKt.m613size3ABfNKs(modifier4, Dp.m5736constructorimpl(40)), Dp.m5736constructorimpl(8), (r15 & 2) != 0 ? RectangleShapeKt.getRectangleShape() : RoundedCornerShapeKt.getCircleShape(), (r15 & 4) != 0 ? Dp.m5735compareTo0680j_4(r8, Dp.m5736constructorimpl((float) 0)) > 0 : false, (r15 & 8) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : Color.INSTANCE.m3435getBlack0d7_KjU(), (r15 & 16) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L);
            SurfaceKt.m1982Surfaceo_FOJdg(function0, m3080shadows4CzXII, false, circleShape, m3407copywmQWz5c, m3446getWhite0d7_KjU, 0.0f, 0.0f, null, null, ComposableLambdaKt.composableLambda($composer2, 732090655, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.CatalogBrowseScreenKt$CatalogGlassButton$1
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

                /* JADX WARN: Removed duplicated region for block: B:29:0x01aa  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r26, int r27) {
                    /*
                        Method dump skipped, instructions count: 430
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.CatalogBrowseScreenKt$CatalogGlassButton$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, ($dirty2 & 14) | 221184, 6, 964);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.CatalogBrowseScreenKt$CatalogGlassButton$2
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

                public final void invoke(Composer composer, int i3) {
                    CatalogBrowseScreenKt.CatalogGlassButton(function0, contentDescription, modifier5, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long GridItemSpanFull(LazyGridItemSpanScope $this$GridItemSpanFull) {
        return LazyGridSpanKt.GridItemSpan($this$GridItemSpanFull.getMaxLineSpan());
    }
}
