package com.streamsync.app.ui.detail;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
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
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import coil.compose.SingletonAsyncImageKt;
import com.streamsync.app.data.model.CastMember;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.UiSettings;
import com.streamsync.app.ui.theme.ThemeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: DetailSections.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0000\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\n\u001a\u001b\u0010\u000b\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0003¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u0012\u001a)\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u001f\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u001c\u001a%\u0010\u001e\u001a\u00020\u00012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010 \u001a\u0015\u0010!\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\"\u001a+\u0010#\u001a\u00020\u00012\b\u0010$\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010%\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010&\u001a\u0010\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H\u0002¨\u0006*"}, d2 = {"ActionButton", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "primary", "", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "CastRow", "people", "", "Lcom/streamsync/app/data/model/CastMember;", "(Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "CreditLine", "value", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "DetailActionRow", "detail", "Lcom/streamsync/app/data/model/MediaItem;", "trailerKey", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/streamsync/app/data/model/MediaItem;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DetailCredits", "item", "(Lcom/streamsync/app/data/model/MediaItem;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DetailFactLine", "DetailGenres", "genres", "(Ljava/util/List;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DetailHero", "(Lcom/streamsync/app/data/model/MediaItem;Landroidx/compose/runtime/Composer;I)V", "StreamBadgesRow", "streamName", "compact", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "formatRating", "score", "", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class DetailSectionsKt {
    public static final void DetailHero(final MediaItem detail, Composer $composer, final int $changed) {
        Function0 factory$iv$iv$iv;
        BoxScope $this$DetailHero_u24lambda_u241;
        Composer $composer2;
        Object obj;
        float f;
        int i;
        long m3407copywmQWz5c;
        int i2;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        BoxScope $this$DetailHero_u24lambda_u2412;
        Intrinsics.checkNotNullParameter(detail, "detail");
        Composer $composer3 = $composer.startRestartGroup(-632543149);
        ComposerKt.sourceInformation($composer3, "C(DetailHero)*69@3173L7,70@3201L3163:DetailSections.kt#tl1h0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(detail) ? 4 : 2;
        }
        if (($dirty & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-632543149, $dirty, -1, "com.streamsync.app.ui.detail.DetailHero (DetailSections.kt:61)");
            }
            String backdrop = detail.getBackgroundUrl();
            if (backdrop == null) {
                backdrop = detail.getPosterUrl();
            }
            ProvidableCompositionLocal<UiSettings> localUiSettings = ThemeKt.getLocalUiSettings();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localUiSettings);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $this$dp$iv = ((UiSettings) consume).getHeroHeightDp();
            float backdropHeight = Dp.m5736constructorimpl($this$dp$iv);
            Modifier modifier$iv = SizeKt.m599height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), backdropHeight);
            $composer3.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation($composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.rememberBoxMeasurePolicy(contentAlignment$iv, false, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                factory$iv$iv$iv = factory$iv$iv$iv2;
                $composer3.createNode(factory$iv$iv$iv);
            } else {
                factory$iv$iv$iv = factory$iv$iv$iv2;
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
            int i3 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            int i4 = ((0 >> 6) & 112) | 6;
            BoxScope $this$DetailHero_u24lambda_u2413 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -1044849740, "C111@4791L11,112@4879L11,104@4499L452,133@5659L10,131@5593L765:DetailSections.kt#tl1h0o");
            if (backdrop != null) {
                $composer3.startReplaceableGroup(-1044915616);
                ComposerKt.sourceInformation($composer3, "76@3339L202");
                $this$DetailHero_u24lambda_u241 = $this$DetailHero_u24lambda_u2413;
                $composer2 = $composer3;
                SingletonAsyncImageKt.m6544AsyncImagegl8XCv8(backdrop, null, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, null, $composer2, 1573296, 0, 4024);
                $composer2.endReplaceableGroup();
                i2 = 0;
                obj = null;
                f = 0.0f;
                i = 1;
            } else {
                $this$DetailHero_u24lambda_u241 = $this$DetailHero_u24lambda_u2413;
                $composer2 = $composer3;
                $composer2.startReplaceableGroup(-1044674250);
                ComposerKt.sourceInformation($composer2, "91@3937L11,92@4024L11,85@3709L426");
                obj = null;
                f = 0.0f;
                i = 1;
                Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Brush.Companion companion = Brush.INSTANCE;
                m3407copywmQWz5c = Color.m3407copywmQWz5c(r15, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r15) : 0.35f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r15) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r15) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimary()) : 0.0f);
                i2 = 0;
                BoxKt.Box(BackgroundKt.background$default(fillMaxSize$default, Brush.Companion.m3366verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m3399boximpl(m3407copywmQWz5c), Color.m3399boximpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer2, 0);
                $composer2.endReplaceableGroup();
            }
            Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, f, i, obj);
            Brush.Companion companion2 = Brush.INSTANCE;
            Pair[] pairArr = new Pair[4];
            Float valueOf = Float.valueOf(f);
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.08f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(Color.INSTANCE.m3435getBlack0d7_KjU()) : 0.0f);
            pairArr[i2] = TuplesKt.to(valueOf, Color.m3399boximpl(m3407copywmQWz5c2));
            pairArr[i] = TuplesKt.to(Float.valueOf(0.55f), Color.m3399boximpl(Color.INSTANCE.m3444getTransparent0d7_KjU()));
            Float valueOf2 = Float.valueOf(0.82f);
            m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.62f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getBackground()) : 0.0f);
            pairArr[2] = TuplesKt.to(valueOf2, Color.m3399boximpl(m3407copywmQWz5c3));
            pairArr[3] = TuplesKt.to(Float.valueOf(1.0f), Color.m3399boximpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getBackground()));
            BoxKt.Box(BackgroundKt.background$default(fillMaxSize$default2, Brush.Companion.m3367verticalGradient8A3gB4$default(companion2, pairArr, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), $composer2, i2);
            String logo = detail.getLogoUrl();
            $composer2.startReplaceableGroup(1490373825);
            ComposerKt.sourceInformation($composer2, "*120@5225L349");
            if (logo == null) {
                $this$DetailHero_u24lambda_u2412 = $this$DetailHero_u24lambda_u241;
            } else {
                $this$DetailHero_u24lambda_u2412 = $this$DetailHero_u24lambda_u241;
                SingletonAsyncImageKt.m6544AsyncImagegl8XCv8(logo, null, SizeKt.m599height3ABfNKs(SizeKt.fillMaxWidth(PaddingKt.m568paddingqDBjuR0$default($this$DetailHero_u24lambda_u2412.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, Dp.m5736constructorimpl(24), 0.0f, 0.0f, 13, null), 0.45f), Dp.m5736constructorimpl(72)), null, null, null, ContentScale.INSTANCE.getFit(), 0.0f, null, 0, false, null, $composer2, 1572912, 0, 4024);
            }
            $composer2.endReplaceableGroup();
            TextKt.m2127Text4IGK_g(detail.getTitle(), PaddingKt.m568paddingqDBjuR0$default($this$DetailHero_u24lambda_u2412.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), Dp.m5736constructorimpl(16), 0.0f, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(8), 2, null), Color.INSTANCE.m3446getWhite0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(32), 0, true, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getHeadlineMedium(), $composer2, 196992, 390, 60376);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$DetailHero$2
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

                public final void invoke(Composer composer, int i5) {
                    DetailSectionsKt.DetailHero(MediaItem.this, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x01fe A[LOOP:0: B:45:0x01f8->B:47:0x01fe, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DetailGenres(final java.util.List<java.lang.String> r61, androidx.compose.ui.Modifier r62, androidx.compose.runtime.Composer r63, final int r64, final int r65) {
        /*
            Method dump skipped, instructions count: 751
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt.DetailGenres(java.util.List, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0282  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DetailActionRow(final com.streamsync.app.data.model.MediaItem r41, final java.lang.String r42, androidx.compose.ui.Modifier r43, androidx.compose.runtime.Composer r44, final int r45, final int r46) {
        /*
            Method dump skipped, instructions count: 819
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt.DetailActionRow(com.streamsync.app.data.model.MediaItem, java.lang.String, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ActionButton(final ImageVector icon, final String label, final boolean primary, final Function0<Unit> function0, Composer $composer, final int $changed) {
        long fg;
        Composer $composer2 = $composer.startRestartGroup(-1897179902);
        ComposerKt.sourceInformation($composer2, "C(ActionButton)P(!2,3)248@9997L11,257@10342L468:DetailSections.kt#tl1h0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(icon) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(label) ? 32 : 16;
        }
        if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(primary) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1897179902, $dirty2, -1, "com.streamsync.app.ui.detail.ActionButton (DetailSections.kt:237)");
            }
            long bg = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getPrimary();
            if (primary) {
                $composer2.startReplaceableGroup(-2063200943);
                ComposerKt.sourceInformation($composer2, "250@10067L11");
                long onPrimary = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnPrimary();
                $composer2.endReplaceableGroup();
                fg = onPrimary;
            } else {
                $composer2.startReplaceableGroup(-2063138633);
                ComposerKt.sourceInformation($composer2, "255@10310L11");
                long onPrimary2 = MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOnPrimary();
                $composer2.endReplaceableGroup();
                fg = onPrimary2;
            }
            Modifier modifier$iv = PaddingKt.m565paddingVpY3zN4(ClickableKt.m246clickableXHw0xAI$default(BackgroundKt.m212backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m834RoundedCornerShape0680j_4(Dp.m5736constructorimpl(24))), bg, null, 2, null), false, null, null, function0, 7, null), Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(9));
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            $composer2.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer2, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int $changed$iv$iv = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            $composer2.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer2, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(factory$iv$iv$iv);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2939constructorimpl($composer2);
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
            }
            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2930boximpl(SkippableUpdater.m2931constructorimpl($composer2)), $composer2, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer2.startReplaceableGroup(2058660585);
            int i = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -874251549, "C265@10609L81,266@10699L28,267@10770L10,267@10736L68:DetailSections.kt#tl1h0o");
            IconKt.m1600Iconww6aTOc(icon, (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(18)), fg, $composer2, ($dirty2 & 14) | 432, 0);
            SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(6)), $composer2, 6);
            TextKt.m2127Text4IGK_g(label, (Modifier) null, fg, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getLabelLarge(), $composer2, ($dirty2 >> 3) & 14, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$ActionButton$2
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

                public final void invoke(Composer composer, int i3) {
                    DetailSectionsKt.ActionButton(ImageVector.this, label, primary, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02ca A[LOOP:0: B:94:0x02c4->B:96:0x02ca, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DetailFactLine(final com.streamsync.app.data.model.MediaItem r62, androidx.compose.ui.Modifier r63, androidx.compose.runtime.Composer r64, final int r65, final int r66) {
        /*
            Method dump skipped, instructions count: 955
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt.DetailFactLine(com.streamsync.app.data.model.MediaItem, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String formatRating(double score) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(score)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x03c9, code lost:
    
        if ((!kotlin.text.StringsKt.isBlank(r3)) == true) goto L87;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0329  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DetailCredits(final com.streamsync.app.data.model.MediaItem r59, androidx.compose.ui.Modifier r60, androidx.compose.runtime.Composer r61, final int r62, final int r63) {
        /*
            Method dump skipped, instructions count: 1062
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt.DetailCredits(com.streamsync.app.data.model.MediaItem, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CastRow(final List<CastMember> list, Composer $composer, final int $changed) {
        ArrayList value$iv;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(1598014530);
        ComposerKt.sourceInformation($composer2, "C(CastRow)422@17038L80,425@17184L1507,425@17123L1568:DetailSections.kt#tl1h0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1598014530, $dirty2, -1, "com.streamsync.app.ui.detail.CastRow (DetailSections.kt:409)");
            }
            $composer2.startReplaceableGroup(-401997451);
            ComposerKt.sourceInformation($composer2, "CC(remember):DetailSections.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(list);
            int $i$f$cache = 0;
            Object it$iv = $composer2.rememberedValue();
            int i = 0;
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                List<CastMember> $this$distinctBy$iv = list;
                HashSet set$iv = new HashSet();
                ArrayList list$iv = new ArrayList();
                for (Object e$iv : $this$distinctBy$iv) {
                    boolean invalid$iv2 = invalid$iv;
                    CastMember it = (CastMember) e$iv;
                    int $i$f$cache2 = $i$f$cache;
                    String name = it.getName();
                    String role = it.getRole();
                    if (role == null) {
                        role = "";
                    }
                    Object it$iv2 = it$iv;
                    String str = role;
                    int i2 = i;
                    if (set$iv.add(name + str)) {
                        list$iv.add(e$iv);
                        invalid$iv = invalid$iv2;
                        $i$f$cache = $i$f$cache2;
                        i = i2;
                        it$iv = it$iv2;
                    } else {
                        invalid$iv = invalid$iv2;
                        $i$f$cache = $i$f$cache2;
                        i = i2;
                        it$iv = it$iv2;
                    }
                }
                value$iv = list$iv;
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final List uniquePeople = (List) value$iv;
            $composer2.endReplaceableGroup();
            Arrangement.HorizontalOrVertical m473spacedBy0680j_4 = Arrangement.INSTANCE.m473spacedBy0680j_4(Dp.m5736constructorimpl(12));
            $composer2.startReplaceableGroup(-401991352);
            ComposerKt.sourceInformation($composer2, "CC(remember):DetailSections.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(uniquePeople);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1<LazyListScope, Unit>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        final List items$iv = uniquePeople;
                        final Function1 key$iv = new Function1<CastMember, Object>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1.1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(CastMember it2) {
                                Intrinsics.checkNotNullParameter(it2, "it");
                                String name2 = it2.getName();
                                String role2 = it2.getRole();
                                if (role2 == null) {
                                    role2 = "";
                                }
                                return name2 + role2;
                            }
                        };
                        final Function1 contentType$iv = new Function1<CastMember, Object>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1.2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(CastMember it2) {
                                Intrinsics.checkNotNullParameter(it2, "it");
                                return "cast";
                            }
                        };
                        int size = items$iv.size();
                        if (key$iv != null) {
                            function1 = new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1$invoke$$inlined$items$1
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
                        LazyRow.items(size, function1, new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1$invoke$$inlined$items$2
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
                        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1$invoke$$inlined$items$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:42:0x028d  */
                            /* JADX WARN: Removed duplicated region for block: B:45:0x0305  */
                            /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.foundation.lazy.LazyItemScope r74, int r75, androidx.compose.runtime.Composer r76, int r77) {
                                /*
                                    Method dump skipped, instructions count: 777
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$1$1$invoke$$inlined$items$3.invoke(androidx.compose.foundation.lazy.LazyItemScope, int, androidx.compose.runtime.Composer, int):void");
                            }
                        }));
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv3;
            }
            $composer2.endReplaceableGroup();
            LazyDslKt.LazyRow(null, null, null, false, m473spacedBy0680j_4, null, null, false, (Function1) value$iv2, $composer2, 24576, 239);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.DetailSectionsKt$CastRow$2
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

                public final void invoke(Composer composer, int i3) {
                    DetailSectionsKt.CastRow(list, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0117, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r2.rememberedValue(), java.lang.Integer.valueOf(r31)) != false) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CreditLine(final java.lang.String r64, final java.lang.String r65, androidx.compose.runtime.Composer r66, final int r67) {
        /*
            Method dump skipped, instructions count: 609
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt.CreditLine(java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0301  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void StreamBadgesRow(final java.lang.String r51, androidx.compose.ui.Modifier r52, boolean r53, androidx.compose.runtime.Composer r54, final int r55, final int r56) {
        /*
            Method dump skipped, instructions count: 798
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.DetailSectionsKt.StreamBadgesRow(java.lang.String, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }
}
