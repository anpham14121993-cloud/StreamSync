package com.streamsync.app.ui.catalog;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.WatchProgress;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.HomeLayoutSettings;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CatalogScreen.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u001a\u0081\u0001\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\u001a\b\u0002\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u00060\bH\u0007¢\u0006\u0002\u0010\u0013\u001a+\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eH\u0003¢\u0006\u0002\u0010\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018²\u0006\u0010\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u001bX\u008a\u0084\u0002²\u0006\f\u0010\u001c\u001a\u0004\u0018\u00010\fX\u008a\u0084\u0002²\u0006\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u0011X\u008a\u0084\u0002²\u0006\u0010\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020!X\u008a\u0084\u0002"}, d2 = {"HERO_ITEM_COUNT", "", "PERSIST_COALESCE_MS", "", "PREFETCH_ROW_COUNT", "CatalogScreen", "", "onItemClick", "Lkotlin/Function1;", "Lcom/streamsync/app/data/model/MediaItem;", "onContinueClick", "onOpenCatalog", "", "onSettingsClick", "Lkotlin/Function0;", "onAddonsClick", "onRowsLoaded", "", "Lcom/streamsync/app/data/model/CatalogRow;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "EmptyState", "message", "installedAddons", "(Ljava/lang/String;ILkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug", "rows", "loading", "", "error", "continueWatching", "latestProgressSnapshot", "Lcom/streamsync/app/data/model/WatchProgress;", TtmlNode.TAG_LAYOUT, "Lcom/streamsync/app/settings/HomeLayoutSettings;"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class CatalogScreenKt {
    private static final int HERO_ITEM_COUNT = 6;
    private static final long PERSIST_COALESCE_MS = 2000;
    private static final int PREFETCH_ROW_COUNT = 3;

    /* JADX WARN: Removed duplicated region for block: B:128:0x06bc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0673  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x04c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0471 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03d3 A[LOOP:1: B:150:0x03cd->B:152:0x03d3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03b6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x034d A[LOOP:2: B:157:0x0347->B:159:0x034d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x071e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x073a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0926  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x078b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CatalogScreen(final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r71, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r72, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r73, final kotlin.jvm.functions.Function0<kotlin.Unit> r74, final kotlin.jvm.functions.Function0<kotlin.Unit> r75, kotlin.jvm.functions.Function1<? super java.util.List<com.streamsync.app.data.model.CatalogRow>, kotlin.Unit> r76, androidx.compose.runtime.Composer r77, final int r78, final int r79) {
        /*
            Method dump skipped, instructions count: 2395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.catalog.CatalogScreenKt.CatalogScreen(kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CatalogRow> CatalogScreen$lambda$2(State<? extends List<CatalogRow>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    private static final boolean CatalogScreen$lambda$3(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final String CatalogScreen$lambda$4(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<MediaItem> CatalogScreen$lambda$5(State<? extends List<MediaItem>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<WatchProgress> CatalogScreen$lambda$6(State<? extends List<WatchProgress>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    private static final int CatalogScreen$lambda$7(State<Integer> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeLayoutSettings CatalogScreen$lambda$8(State<HomeLayoutSettings> state) {
        Object thisObj$iv = state.getValue();
        return (HomeLayoutSettings) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void EmptyState(final String message, int installedAddons, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2;
        final int i;
        Composer $composer3 = $composer.startRestartGroup(-1749499923);
        ComposerKt.sourceInformation($composer3, "C(EmptyState)P(1)656@31019L1009:CatalogScreen.kt#aadnbi");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(message) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(installedAddons) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1749499923, $dirty2, -1, "com.streamsync.app.ui.catalog.EmptyState (CatalogScreen.kt:655)");
            }
            Modifier modifier$iv = PaddingKt.m564padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5736constructorimpl(32));
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getCenter();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getCenterHorizontally();
            $composer3.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            int $changed$iv$iv = (438 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(factory$iv$iv$iv);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2939constructorimpl($composer3);
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
            }
            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2930boximpl(SkippableUpdater.m2931constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i2 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1631422247, "C665@31286L10,667@31385L11,663@31225L198,669@31432L30,670@31498L81:CatalogScreen.kt#aadnbi");
            $composer2 = $composer3;
            TextKt.m2127Text4IGK_g(message, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5621boximpl(TextAlign.INSTANCE.m5628getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyMedium(), $composer3, $dirty2 & 14, 0, 65018);
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(12)), $composer3, 6);
            ButtonKt.Button(function0, null, false, null, null, null, null, null, null, ComposableSingletons$CatalogScreenKt.INSTANCE.m6620getLambda3$app_mobileDebug(), $composer3, (($dirty2 >> 6) & 14) | 805306368, 510);
            $composer3.startReplaceableGroup(52637777);
            ComposerKt.sourceInformation($composer3, "674@31627L30,678@31863L10,680@31970L11,675@31670L342");
            i = installedAddons;
            if (i > 0) {
                SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(12)), $composer3, 6);
                TextKt.m2127Text4IGK_g("Đã cài " + i + " addon nhưng chưa danh mục nào trả về phim. Kiểm tra lại manifest.json của addon.", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5621boximpl(TextAlign.INSTANCE.m5628getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelSmall(), $composer3, 0, 0, 65018);
            }
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            i = installedAddons;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.CatalogScreenKt$EmptyState$2
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

                public final void invoke(Composer composer, int i4) {
                    CatalogScreenKt.EmptyState(message, i, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
