package com.streamsync.app.ui.components;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
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
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
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
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaComponents.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\u001aW\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\b\u0002\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u000f\u001aW\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u000f\u001a%\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0016\u001aM\u0010\u0017\u001a\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u001e\u001aA\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010!\u001a\u00020\u00012\b\b\u0002\u0010\"\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010#\u001aM\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&H\u0007¢\u0006\u0002\u0010(\u001a+\u0010)\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&H\u0007¢\u0006\u0002\u0010*\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"HERO_MAX_GENRES", "", "CatalogRowSection", "", "row", "Lcom/streamsync/app/data/model/CatalogRow;", "onItemClick", "Lkotlin/Function1;", "Lcom/streamsync/app/data/model/MediaItem;", "modifier", "Landroidx/compose/ui/Modifier;", "onOpenAll", "Lkotlin/Function0;", "postersPerRow", "titleSp", "(Lcom/streamsync/app/data/model/CatalogRow;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;IILandroidx/compose/runtime/Composer;II)V", "CollapsibleCatalogRow", "previewCount", "HeroPage", "item", "scrimStrength", "", "(Lcom/streamsync/app/data/model/MediaItem;FILandroidx/compose/runtime/Composer;I)V", "HeroSlider", "items", "", "onOpenDetail", "autoSlide", "", "slideIntervalMs", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZILandroidx/compose/runtime/Composer;II)V", "PosterCard", "onClick", "width", "showMeta", "(Lcom/streamsync/app/data/model/MediaItem;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;IZLandroidx/compose/runtime/Composer;II)V", "PosterRow", "title", "", "subtitle", "(Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "RowHeader", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class MediaComponentsKt {
    private static final int HERO_MAX_GENRES = 3;

    /* JADX WARN: Removed duplicated region for block: B:107:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x05d5  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x04a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0451  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x05d3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x05e9  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x06e6  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x05f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PosterCard(final com.streamsync.app.data.model.MediaItem r86, final kotlin.jvm.functions.Function0<kotlin.Unit> r87, androidx.compose.ui.Modifier r88, int r89, boolean r90, androidx.compose.runtime.Composer r91, final int r92, final int r93) {
        /*
            Method dump skipped, instructions count: 1808
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.MediaComponentsKt.PosterCard(com.streamsync.app.data.model.MediaItem, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, int, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void RowHeader(final String title, Modifier modifier, String subtitle, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String subtitle2;
        Modifier modifier3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(576544889);
        ComposerKt.sourceInformation($composer3, "C(RowHeader)P(2)299@14340L634:MediaComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            subtitle2 = subtitle;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            subtitle2 = subtitle;
            $dirty |= $composer3.changed(subtitle2) ? 256 : 128;
        } else {
            subtitle2 = subtitle;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            $composer2 = $composer3;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            String subtitle3 = i3 != 0 ? null : subtitle2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(576544889, $dirty2, -1, "com.streamsync.app.ui.components.RowHeader (MediaComponents.kt:298)");
            }
            Modifier modifier$iv = PaddingKt.m567paddingqDBjuR0(modifier4, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(12), Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(8));
            $composer3.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
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
            int i4 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1451841995, "C302@14490L10,303@14548L11,300@14431L220:MediaComponents.kt#qvfdnh");
            modifier3 = modifier4;
            $composer2 = $composer3;
            TextKt.m2127Text4IGK_g(title, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurface(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5678getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getTitleMedium(), $composer3, $dirty2 & 14, 3120, 55290);
            $composer3.startReplaceableGroup(-1293752503);
            ComposerKt.sourceInformation($composer3, "310@14775L10,311@14836L11,308@14705L253");
            String str = subtitle3;
            if (!(str == null || StringsKt.isBlank(str))) {
                TextKt.m2127Text4IGK_g(subtitle3, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m5678getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelSmall(), $composer3, ($dirty2 >> 6) & 14, 3120, 55290);
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
            subtitle2 = subtitle3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final String str2 = subtitle2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$RowHeader$2
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

                public final void invoke(Composer composer, int i6) {
                    MediaComponentsKt.RowHeader(title, modifier5, str2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void PosterRow(final String title, final List<MediaItem> items, final Function1<? super MediaItem, Unit> onItemClick, Modifier modifier, String subtitle, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        String str;
        Modifier modifier3;
        String subtitle2;
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        Composer $composer3 = $composer.startRestartGroup(1480179126);
        ComposerKt.sourceInformation($composer3, "C(PosterRow)P(4!1,2)331@15386L61,332@15452L58,336@15685L388,333@15515L558:MediaComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(items) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(onItemClick) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            str = subtitle;
        } else if (($changed & 24576) == 0) {
            str = subtitle;
            $dirty |= $composer3.changed(str) ? 16384 : 8192;
        } else {
            str = subtitle;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            subtitle2 = str;
            $composer2 = $composer3;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            subtitle2 = i3 != 0 ? null : str;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1480179126, $dirty2, -1, "com.streamsync.app.ui.components.PosterRow (MediaComponents.kt:327)");
            }
            if (items.isEmpty()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
                if (endRestartGroup != null) {
                    final Modifier modifier4 = modifier3;
                    final String str2 = subtitle2;
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$1
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

                        public final void invoke(Composer composer, int i4) {
                            MediaComponentsKt.PosterRow(title, items, onItemClick, modifier4, str2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                        }
                    });
                    return;
                }
                return;
            }
            $composer3.startReplaceableGroup(-994566439);
            ComposerKt.sourceInformation($composer3, "CC(remember):MediaComponents.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(items);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = DuplicateSafeLazyKeysKt.withDuplicateSafeLazyKeys(items, new Function1<MediaItem, Object>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$keyed$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(MediaItem it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return it.getId();
                    }
                });
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final List keyed = (List) value$iv;
            $composer3.endReplaceableGroup();
            RowHeader(title, modifier3, subtitle2, $composer3, ($dirty2 & 14) | (($dirty2 >> 6) & 112) | (($dirty2 >> 6) & 896), 0);
            PaddingValues m559PaddingValuesYgX7TsA$default = PaddingKt.m559PaddingValuesYgX7TsA$default(Dp.m5736constructorimpl(16), 0.0f, 2, null);
            Arrangement.HorizontalOrVertical m473spacedBy0680j_4 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5736constructorimpl(10));
            $composer3.startReplaceableGroup(-994556544);
            ComposerKt.sourceInformation($composer3, "CC(remember):MediaComponents.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(keyed) | (($dirty2 & 896) == 256);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1<LazyListScope, Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                        invoke2(lazyListScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LazyListScope LazyRow) {
                        Function1<Integer, Object> function1;
                        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
                        final List items$iv = keyed;
                        final Function1 key$iv = new Function1<DuplicateSafeLazyEntry<MediaItem>, Object>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1.1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(DuplicateSafeLazyEntry<MediaItem> it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                return it.getLazyKey();
                            }
                        };
                        final Function1 contentType$iv = new Function1<DuplicateSafeLazyEntry<MediaItem>, Object>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1.2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(DuplicateSafeLazyEntry<MediaItem> it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                return "poster";
                            }
                        };
                        final Function1<MediaItem, Unit> function12 = onItemClick;
                        int size = items$iv.size();
                        if (key$iv != null) {
                            function1 = new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1$invoke$$inlined$items$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                    return invoke(num.intValue());
                                }

                                public final Object invoke(int index) {
                                    return Function1.this.invoke(items$iv.get(index));
                                }
                            };
                        } else {
                            function1 = null;
                        }
                        LazyRow.items(size, function1, new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1$invoke$$inlined$items$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                return invoke(num.intValue());
                            }

                            public final Object invoke(int index) {
                                return Function1.this.invoke(items$iv.get(index));
                            }
                        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1$invoke$$inlined$items$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LazyItemScope $this$items, int it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                ComposerKt.sourceInformation($composer4, "C148@6730L22:LazyDsl.kt#428nma");
                                int $dirty3 = $changed2;
                                if (($changed2 & 14) == 0) {
                                    $dirty3 |= $composer4.changed($this$items) ? 4 : 2;
                                }
                                if (($changed2 & 112) == 0) {
                                    $dirty3 |= $composer4.changed(it) ? 32 : 16;
                                }
                                if (($dirty3 & 731) == 146 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-632812321, $dirty3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:148)");
                                }
                                int i4 = $dirty3 & 14;
                                DuplicateSafeLazyEntry entry = (DuplicateSafeLazyEntry) items$iv.get(it);
                                $composer4.startReplaceableGroup(816128655);
                                ComposerKt.sourceInformation($composer4, "C*345@16035L21,345@16001L56:MediaComponents.kt#qvfdnh");
                                final MediaItem item = (MediaItem) entry.getValue();
                                $composer4.startReplaceableGroup(1411802190);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MediaComponents.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changed(function12) | $composer4.changed(item);
                                Object it$iv3 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                    final Function1 function13 = function12;
                                    value$iv3 = (Function0) new Function0<Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$2$1$3$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function13.invoke(item);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv3;
                                }
                                $composer4.endReplaceableGroup();
                                MediaComponentsKt.PosterCard(item, (Function0) value$iv3, null, 0, false, $composer4, 0, 28);
                                $composer4.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            LazyDslKt.LazyRow(null, null, m559PaddingValuesYgX7TsA$default, false, m473spacedBy0680j_4, null, null, false, (Function1) value$iv2, $composer2, 24960, 235);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            final Modifier modifier5 = modifier3;
            final String str3 = subtitle2;
            endRestartGroup2.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$PosterRow$3
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

                public final void invoke(Composer composer, int i4) {
                    MediaComponentsKt.PosterRow(title, items, onItemClick, modifier5, str3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void CatalogRowSection(final CatalogRow row, final Function1<? super MediaItem, Unit> onItemClick, Modifier modifier, Function0<Unit> function0, int postersPerRow, int titleSp, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function0 function02;
        int i2;
        int i3;
        Modifier modifier3;
        Function0 onOpenAll;
        int postersPerRow2;
        int titleSp2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        Composer $composer3 = $composer.startRestartGroup(-720571436);
        ComposerKt.sourceInformation($composer3, "C(CatalogRowSection)P(4,1):MediaComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(row) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onItemClick) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
            modifier2 = modifier;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            function02 = function0;
        } else if (($changed & 3072) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 2048 : 1024;
        } else {
            function02 = function0;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = postersPerRow;
        } else if (($changed & 24576) == 0) {
            i2 = postersPerRow;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = postersPerRow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i3 = titleSp;
        } else if ((196608 & $changed) == 0) {
            i3 = titleSp;
            $dirty |= $composer3.changed(i3) ? 131072 : 65536;
        } else {
            i3 = titleSp;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            onOpenAll = function02;
            postersPerRow2 = i2;
            $composer2 = $composer3;
            titleSp2 = i3;
        } else {
            modifier3 = i4 != 0 ? Modifier.INSTANCE : modifier2;
            onOpenAll = i5 != 0 ? new Function0<Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$CatalogRowSection$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            } : function02;
            postersPerRow2 = i6 != 0 ? 8 : i2;
            titleSp2 = i7 != 0 ? 18 : i3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-720571436, $dirty2, -1, "com.streamsync.app.ui.components.CatalogRowSection (MediaComponents.kt:367)");
            }
            if (row.getError() != null) {
                $composer3.startReplaceableGroup(-1294201905);
                ComposerKt.sourceInformation($composer3, "376@17192L71,379@17347L10,380@17407L11,377@17276L244");
                RowHeader(row.getName(), modifier3, "Lỗi tải danh mục", $composer3, (($dirty2 >> 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                TextKt.m2127Text4IGK_g(row.getError(), PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall(), $composer3, 48, 0, 65528);
                $composer3.endReplaceableGroup();
                $composer2 = $composer3;
            } else if (row.getItems().isEmpty()) {
                $composer3.startReplaceableGroup(-1293818900);
                ComposerKt.sourceInformation($composer3, "386@17577L67,389@17756L10,390@17816L11,387@17657L283");
                RowHeader(row.getName(), modifier3, "Chưa có phim", $composer3, (($dirty2 >> 3) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                TextKt.m2127Text4IGK_g("Danh mục này hiện chưa có phim nào.", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall(), $composer3, 54, 0, 65528);
                $composer3.endReplaceableGroup();
                $composer2 = $composer3;
            } else {
                $composer3.startReplaceableGroup(-457364988);
                ComposerKt.sourceInformation($composer3, "395@17968L529");
                $composer2 = $composer3;
                CollapsibleCatalogRow(row, onItemClick, modifier3, onOpenAll, postersPerRow2, titleSp2, $composer3, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2), 0);
                $composer2.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final Function0 function03 = onOpenAll;
            final int i8 = postersPerRow2;
            final int i9 = titleSp2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.MediaComponentsKt$CatalogRowSection$2
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

                public final void invoke(Composer composer, int i10) {
                    MediaComponentsKt.CatalogRowSection(CatalogRow.this, onItemClick, modifier4, function03, i8, i9, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x067f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0505 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x04be  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03bc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x04ac  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x06e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CollapsibleCatalogRow(final com.streamsync.app.data.model.CatalogRow r132, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r133, androidx.compose.ui.Modifier r134, kotlin.jvm.functions.Function0<kotlin.Unit> r135, int r136, int r137, androidx.compose.runtime.Composer r138, final int r139, final int r140) {
        /*
            Method dump skipped, instructions count: 1808
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.MediaComponentsKt.CollapsibleCatalogRow(com.streamsync.app.data.model.CatalogRow, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, int, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0700  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x07ae  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x090e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x09eb  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x09b0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x072a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0691 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0566 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0642  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HeroSlider(final java.util.List<com.streamsync.app.data.model.MediaItem> r93, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r94, androidx.compose.ui.Modifier r95, boolean r96, int r97, androidx.compose.runtime.Composer r98, final int r99, final int r100) {
        /*
            Method dump skipped, instructions count: 2575
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.MediaComponentsKt.HeroSlider(java.util.List, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0940  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0960  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0a3b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x08ab  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0568 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0519  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x066f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x07e1 A[LOOP:0: B:90:0x07db->B:92:0x07e1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x08d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HeroPage(final com.streamsync.app.data.model.MediaItem r129, final float r130, final int r131, androidx.compose.runtime.Composer r132, final int r133) {
        /*
            Method dump skipped, instructions count: 2648
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.MediaComponentsKt.HeroPage(com.streamsync.app.data.model.MediaItem, float, int, androidx.compose.runtime.Composer, int):void");
    }
}
