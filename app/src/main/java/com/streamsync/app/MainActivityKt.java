package com.streamsync.app;

import android.net.Uri;
import android.os.Bundle;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.FolderKt;
import androidx.compose.material.icons.filled.HomeKt;
import androidx.compose.material.icons.filled.SearchKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostKt;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.repository.StreamSelectionStore;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.catalog.CatalogBrowseScreenKt;
import com.streamsync.app.ui.catalog.CatalogScreenKt;
import com.streamsync.app.ui.library.LibraryScreenKt;
import com.streamsync.app.ui.search.SearchScreenKt;
import com.streamsync.app.ui.settings.AppearanceScreenKt;
import com.streamsync.app.ui.settings.AudioSettingsScreenKt;
import com.streamsync.app.ui.settings.BadgesSettingsScreenKt;
import com.streamsync.app.ui.settings.CacheSettingsScreenKt;
import com.streamsync.app.ui.settings.DnsSettingsScreenKt;
import com.streamsync.app.ui.settings.DpiBypassSettingsScreenKt;
import com.streamsync.app.ui.settings.HomeLayoutScreenKt;
import com.streamsync.app.ui.settings.PlayerSettingsScreenKt;
import com.streamsync.app.ui.settings.SubtitleStyleScreenKt;
import com.streamsync.app.ui.settings.SubtitleSyncSettingsScreenKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u001aG\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0018\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0003¢\u0006\u0002\u0010\u000b\u001a)\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\t\u001a\u00020\nH\u0003¢\u0006\u0002\u0010\u000f\u001a\u0015\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0011¨\u0006\u0012²\u0006\u0010\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u008a\u008e\u0002"}, d2 = {"AppNavHost", "", "navController", "Landroidx/navigation/NavHostController;", "catalogRows", "", "Lcom/streamsync/app/data/model/CatalogRow;", "onRowsLoaded", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/navigation/NavHostController;Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "BottomNavBar", "currentRoute", "", "(Landroidx/navigation/NavHostController;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "StreamSyncNavHost", "(Landroidx/navigation/NavHostController;Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class MainActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void BottomNavBar(final NavHostController navController, final String currentRoute, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long m3407copywmQWz5c;
        long glass;
        Modifier m3080shadows4CzXII;
        long m3407copywmQWz5c2;
        Composer $composer2 = $composer.startRestartGroup(1295340929);
        ComposerKt.sourceInformation($composer2, "C(BottomNavBar)P(2)274@13441L11,291@14197L4000:MainActivity.kt#b8997");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(currentRoute) ? 32 : 16;
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
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1295340929, $dirty, -1, "com.streamsync.app.BottomNavBar (MainActivity.kt:265)");
            }
            final List items = CollectionsKt.listOf((Object[]) new Triple[]{new Triple("catalog", "Trang chủ", HomeKt.getHome(Icons.INSTANCE.getDefault())), new Triple("search", "Tìm kiếm", SearchKt.getSearch(Icons.INSTANCE.getDefault())), new Triple("library", "Thư viện", FolderKt.getFolder(Icons.INSTANCE.getDefault())), new Triple("settings", "Cài đặt", SettingsKt.getSettings(Icons.INSTANCE.getDefault()))});
            if (ColorKt.m3461luminance8_81llA(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getBackground()) > 0.5f) {
                m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r10) : 0.82f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(Color.INSTANCE.m3435getBlack0d7_KjU()) : 0.0f);
                glass = m3407copywmQWz5c2;
            } else {
                m3407copywmQWz5c = Color.m3407copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r10) : 0.45f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(Color.INSTANCE.m3446getWhite0d7_KjU()) : 0.0f);
                glass = m3407copywmQWz5c;
            }
            m3080shadows4CzXII = ShadowKt.m3080shadows4CzXII(modifier3, Dp.m5736constructorimpl(18), (r15 & 2) != 0 ? RectangleShapeKt.getRectangleShape() : RoundedCornerShapeKt.m834RoundedCornerShape0680j_4(Dp.m5736constructorimpl(28)), (r15 & 4) != 0 ? Dp.m5735compareTo0680j_4(r8, Dp.m5736constructorimpl((float) 0)) > 0 : false, (r15 & 8) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : Color.INSTANCE.m3435getBlack0d7_KjU(), (r15 & 16) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L);
            SurfaceKt.m1979SurfaceT9BRK9s(m3080shadows4CzXII, RoundedCornerShapeKt.m834RoundedCornerShape0680j_4(Dp.m5736constructorimpl(28)), glass, Color.INSTANCE.m3446getWhite0d7_KjU(), 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1677412132, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$BottomNavBar$1
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

                /* JADX WARN: Removed duplicated region for block: B:25:0x0184  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x02d5  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x02e1  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x03d9  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x03e8  */
                /* JADX WARN: Removed duplicated region for block: B:52:0x03ed  */
                /* JADX WARN: Removed duplicated region for block: B:54:0x03de  */
                /* JADX WARN: Removed duplicated region for block: B:57:0x02e7  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x04f1  */
                /* JADX WARN: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r115, int r116) {
                    /*
                        Method dump skipped, instructions count: 1269
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.MainActivityKt$BottomNavBar$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, 12585984, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$BottomNavBar$2
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
                    MainActivityKt.BottomNavBar(NavHostController.this, currentRoute, modifier4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02ab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void StreamSyncNavHost(final androidx.navigation.NavHostController r46, androidx.compose.runtime.Composer r47, final int r48) {
        /*
            Method dump skipped, instructions count: 992
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.MainActivityKt.StreamSyncNavHost(androidx.navigation.NavHostController, androidx.compose.runtime.Composer, int):void");
    }

    private static final List<CatalogRow> StreamSyncNavHost$lambda$1(MutableState<List<CatalogRow>> mutableState) {
        MutableState<List<CatalogRow>> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AppNavHost(final NavHostController navController, final List<CatalogRow> list, final Function1<? super List<CatalogRow>, Unit> function1, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Object value$iv;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1524790868);
        ComposerKt.sourceInformation($composer3, "C(AppNavHost)P(2!1,3)480@23559L234,480@23529L264,492@23919L8365,488@23799L8485:MainActivity.kt#b8997");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(navController) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(list) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
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
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier3 = modifier2;
            $composer2 = $composer3;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1524790868, $dirty2, -1, "com.streamsync.app.AppNavHost (MainActivity.kt:470)");
            }
            final StreamSelectionStore selection = StreamSelectionStore.INSTANCE;
            final MediaItemCache cache = MediaItemCache.INSTANCE;
            $composer3.startReplaceableGroup(1631135399);
            ComposerKt.sourceInformation($composer3, "CC(remember):MainActivity.kt#9igjgp");
            boolean invalid$iv = $composer3.changedInstance(navController);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MainActivityKt$AppNavHost$1$1(navController, null);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer3.endReplaceableGroup();
            EffectsKt.LaunchedEffect(navController, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv, $composer3, $dirty2 & 14);
            $composer3.startReplaceableGroup(1631155050);
            ComposerKt.sourceInformation($composer3, "CC(remember):MainActivity.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(navController) | (($dirty2 & 896) == 256) | $composer3.changedInstance(list) | $composer3.changedInstance(selection) | $composer3.changedInstance(cache);
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1<NavGraphBuilder, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(NavGraphBuilder navGraphBuilder) {
                        invoke2(navGraphBuilder);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(NavGraphBuilder NavHost) {
                        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
                        final NavHostController navHostController = NavHostController.this;
                        final Function1<List<CatalogRow>, Unit> function12 = function1;
                        NavGraphBuilderKt.composable$default(NavHost, "catalog", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1283446410, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Object value$iv4;
                                Object value$iv5;
                                Object value$iv6;
                                Object value$iv7;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C495@24010L208,506@24634L103,509@24771L105,515@25124L38,512@24910L36,494@23965L1256:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1283446410, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:494)");
                                }
                                $composer4.startReplaceableGroup(69756650);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController2 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function1<MediaItem, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$1$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
                                            invoke2(mediaItem);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(MediaItem item) {
                                            Intrinsics.checkNotNullParameter(item, "item");
                                            String encodedId = Uri.encode(item.getId());
                                            NavController.navigate$default(NavHostController.this, "detail/" + encodedId, null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                Function1 function13 = (Function1) value$iv3;
                                $composer4.endReplaceableGroup();
                                $composer4.startReplaceableGroup(69776513);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv4 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController3 = NavHostController.this;
                                Object it$iv3 = $composer4.rememberedValue();
                                if (invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                    value$iv4 = new Function1<MediaItem, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$1$2$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
                                            invoke2(mediaItem);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(MediaItem item) {
                                            Intrinsics.checkNotNullParameter(item, "item");
                                            NavController.navigate$default(NavHostController.this, "player/" + Uri.encode(item.getId()), null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv4);
                                } else {
                                    value$iv4 = it$iv3;
                                }
                                Function1 function14 = (Function1) value$iv4;
                                $composer4.endReplaceableGroup();
                                $composer4.startReplaceableGroup(69780899);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv5 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController4 = NavHostController.this;
                                Object it$iv4 = $composer4.rememberedValue();
                                if (invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                    value$iv5 = new Function1<String, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$1$3$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                                            invoke2(str);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(String rowKey) {
                                            Intrinsics.checkNotNullParameter(rowKey, "rowKey");
                                            NavController.navigate$default(NavHostController.this, "catalog/" + Uri.encode(rowKey), null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv5);
                                } else {
                                    value$iv5 = it$iv4;
                                }
                                Function1 function15 = (Function1) value$iv5;
                                $composer4.endReplaceableGroup();
                                $composer4.startReplaceableGroup(69792128);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv6 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController5 = NavHostController.this;
                                Object it$iv5 = $composer4.rememberedValue();
                                if (invalid$iv6 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                                    value$iv6 = (Function0) new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$1$4$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavController.navigate$default(NavHostController.this, "settings", null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv6);
                                } else {
                                    value$iv6 = it$iv5;
                                }
                                Function0 function0 = (Function0) value$iv6;
                                $composer4.endReplaceableGroup();
                                $composer4.startReplaceableGroup(69785278);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv7 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController6 = NavHostController.this;
                                Object it$iv6 = $composer4.rememberedValue();
                                if (invalid$iv7 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                                    value$iv7 = (Function0) new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$1$5$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavController.navigate$default(NavHostController.this, "addons", null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv7);
                                } else {
                                    value$iv7 = it$iv6;
                                }
                                $composer4.endReplaceableGroup();
                                CatalogScreenKt.CatalogScreen(function13, function14, function15, function0, (Function0) value$iv7, function12, $composer4, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController2 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "search", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1849526925, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.2
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C522@25320L103,521@25276L161:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1849526925, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:521)");
                                }
                                $composer4.startReplaceableGroup(69798465);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController3 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function1<MediaItem, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$2$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
                                            invoke2(mediaItem);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(MediaItem item) {
                                            Intrinsics.checkNotNullParameter(item, "item");
                                            NavController.navigate$default(NavHostController.this, "detail/" + Uri.encode(item.getId()), null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                SearchScreenKt.SearchScreen((Function1) value$iv3, null, $composer4, 0, 2);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController3 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "library", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1583136210, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.3
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Object value$iv4;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C530@25538L103,533@25670L37,529@25493L228:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1583136210, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:529)");
                                }
                                $composer4.startReplaceableGroup(69805441);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController4 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function1<MediaItem, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$3$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
                                            invoke2(mediaItem);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(MediaItem item) {
                                            Intrinsics.checkNotNullParameter(item, "item");
                                            NavController.navigate$default(NavHostController.this, "detail/" + Uri.encode(item.getId()), null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                Function1 function13 = (Function1) value$iv3;
                                $composer4.endReplaceableGroup();
                                $composer4.startReplaceableGroup(69809599);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv4 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController5 = NavHostController.this;
                                Object it$iv3 = $composer4.rememberedValue();
                                if (invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                    value$iv4 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$3$2$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavController.navigate$default(NavHostController.this, "catalog", null, null, 6, null);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv4);
                                } else {
                                    value$iv4 = it$iv3;
                                }
                                $composer4.endReplaceableGroup();
                                LibraryScreenKt.LibraryScreen(function13, (Function0) value$iv4, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        NavGraphBuilderKt.composable$default(NavHost, "addons", null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m6596getLambda5$app_mobileDebug(), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final List<CatalogRow> list2 = list;
                        final NavHostController navHostController4 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "catalog/{rowKey}", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-141472112, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry backStackEntry, Composer $composer4, int $changed2) {
                                Object element$iv;
                                Object value$iv3;
                                Object value$iv4;
                                Object value$iv5;
                                Object value$iv6;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
                                ComposerKt.sourceInformation($composer4, "C:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-141472112, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:545)");
                                }
                                Bundle arguments = backStackEntry.getArguments();
                                String string = arguments != null ? arguments.getString("rowKey") : null;
                                if (string == null) {
                                    string = "";
                                }
                                String rowKey = Uri.decode(string);
                                Iterable $this$firstOrNull$iv = list2;
                                Iterator it = $this$firstOrNull$iv.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        element$iv = it.next();
                                        CatalogRow it2 = (CatalogRow) element$iv;
                                        if (Intrinsics.areEqual(it2.getKey(), rowKey)) {
                                            break;
                                        }
                                    } else {
                                        element$iv = null;
                                        break;
                                    }
                                }
                                CatalogRow row = (CatalogRow) element$iv;
                                if (row == null) {
                                    $composer4.startReplaceableGroup(-2130256483);
                                    ComposerKt.sourceInformation($composer4, "551@26556L32,551@26528L60,552@26626L32,552@26605L53");
                                    $composer4.startReplaceableGroup(69837946);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                    boolean invalid$iv3 = $composer4.changedInstance(navHostController4);
                                    final NavHostController navHostController5 = navHostController4;
                                    Object it$iv2 = $composer4.rememberedValue();
                                    if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                        value$iv5 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$4$1$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
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
                                                NavHostController.this.popBackStack();
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv5);
                                    } else {
                                        value$iv5 = it$iv2;
                                    }
                                    $composer4.endReplaceableGroup();
                                    BackHandlerKt.BackHandler(true, (Function0) value$iv5, $composer4, 6, 0);
                                    Unit unit = Unit.INSTANCE;
                                    $composer4.startReplaceableGroup(69840186);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                    boolean invalid$iv4 = $composer4.changedInstance(navHostController4);
                                    NavHostController navHostController6 = navHostController4;
                                    Object it$iv3 = $composer4.rememberedValue();
                                    if (invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                                        value$iv6 = new MainActivityKt$AppNavHost$2$1$4$2$1(navHostController6, null);
                                        $composer4.updateRememberedValue(value$iv6);
                                    } else {
                                        value$iv6 = it$iv3;
                                    }
                                    $composer4.endReplaceableGroup();
                                    EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv6, $composer4, 6);
                                    $composer4.endReplaceableGroup();
                                } else {
                                    $composer4.startReplaceableGroup(-2129861326);
                                    ComposerKt.sourceInformation($composer4, "556@26777L32,557@26845L111,554@26696L278");
                                    $composer4.startReplaceableGroup(69845018);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                    boolean invalid$iv5 = $composer4.changedInstance(navHostController4);
                                    final NavHostController navHostController7 = navHostController4;
                                    Object it$iv4 = $composer4.rememberedValue();
                                    if (invalid$iv5 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                        value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$4$3$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
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
                                                NavHostController.this.popBackStack();
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv3);
                                    } else {
                                        value$iv3 = it$iv4;
                                    }
                                    Function0 function0 = (Function0) value$iv3;
                                    $composer4.endReplaceableGroup();
                                    $composer4.startReplaceableGroup(69847273);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                    boolean invalid$iv6 = $composer4.changedInstance(navHostController4);
                                    final NavHostController navHostController8 = navHostController4;
                                    Object it$iv5 = $composer4.rememberedValue();
                                    if (invalid$iv6 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                                        value$iv4 = new Function1<MediaItem, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$4$4$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
                                                invoke2(mediaItem);
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(MediaItem item) {
                                                Intrinsics.checkNotNullParameter(item, "item");
                                                NavController.navigate$default(NavHostController.this, "detail/" + Uri.encode(item.getId()), null, null, 6, null);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv4);
                                    } else {
                                        value$iv4 = it$iv5;
                                    }
                                    $composer4.endReplaceableGroup();
                                    CatalogBrowseScreenKt.CatalogBrowseScreen(row, function0, (Function1) value$iv4, $composer4, 0);
                                    $composer4.endReplaceableGroup();
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController5 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1003776273, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.5
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:36:0x01bb  */
                            /* JADX WARN: Removed duplicated region for block: B:41:0x01fd  */
                            /* JADX WARN: Removed duplicated region for block: B:46:0x023f  */
                            /* JADX WARN: Removed duplicated region for block: B:51:0x0281  */
                            /* JADX WARN: Removed duplicated region for block: B:56:0x02c5  */
                            /* JADX WARN: Removed duplicated region for block: B:61:0x0307  */
                            /* JADX WARN: Removed duplicated region for block: B:66:0x0347  */
                            /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
                            /* JADX WARN: Removed duplicated region for block: B:71:0x0315 A[ADDED_TO_REGION] */
                            /* JADX WARN: Removed duplicated region for block: B:73:0x02d3 A[ADDED_TO_REGION] */
                            /* JADX WARN: Removed duplicated region for block: B:75:0x028e A[ADDED_TO_REGION] */
                            /* JADX WARN: Removed duplicated region for block: B:77:0x024c A[ADDED_TO_REGION] */
                            /* JADX WARN: Removed duplicated region for block: B:79:0x020a A[ADDED_TO_REGION] */
                            /* JADX WARN: Removed duplicated region for block: B:81:0x01c8 A[ADDED_TO_REGION] */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.animation.AnimatedContentScope r22, androidx.navigation.NavBackStackEntry r23, androidx.compose.runtime.Composer r24, int r25) {
                                /*
                                    Method dump skipped, instructions count: 843
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.MainActivityKt$AppNavHost$2$1.AnonymousClass5.invoke(androidx.compose.animation.AnimatedContentScope, androidx.navigation.NavBackStackEntry, androidx.compose.runtime.Composer, int):void");
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController6 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/player", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1866080434, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.6
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C581@28093L32,581@28063L63:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1866080434, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:581)");
                                }
                                $composer4.startReplaceableGroup(69887130);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController7 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$6$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                PlayerSettingsScreenKt.PlayerSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController7 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/badges", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1566582701, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.7
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C585@28267L32,584@28220L93:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1566582701, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:584)");
                                }
                                $composer4.startReplaceableGroup(69892698);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController8 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$7$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                BadgesSettingsScreenKt.BadgesSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController8 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/cache", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(704278540, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.8
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C590@28405L32,590@28376L62:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(704278540, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:590)");
                                }
                                $composer4.startReplaceableGroup(69897114);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController9 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$8$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                CacheSettingsScreenKt.CacheSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController9 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/dns", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-158025621, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.9
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C593@28525L32,593@28498L60:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-158025621, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:593)");
                                }
                                $composer4.startReplaceableGroup(69900954);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController10 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$9$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                DnsSettingsScreenKt.DnsSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController10 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/dpi", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(490715741, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.10
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C596@28651L32,596@28618L66:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(490715741, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:596)");
                                }
                                $composer4.startReplaceableGroup(69904986);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController11 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$10$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                DpiBypassSettingsScreenKt.DpiBypassSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController11 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/subtitle", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-371588420, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.11
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C599@28778L32,599@28749L62:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-371588420, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:599)");
                                }
                                $composer4.startReplaceableGroup(69909050);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController12 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$11$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                SubtitleStyleScreenKt.SubtitleStyleScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController12 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/appearance", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1233892581, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.12
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C602@28904L32,602@28878L59:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1233892581, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:602)");
                                }
                                $composer4.startReplaceableGroup(69913082);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController13 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$12$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                AppearanceScreenKt.AppearanceScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController13 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/home", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-2096196742, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.13
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C605@29024L32,605@28998L59:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2096196742, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:605)");
                                }
                                $composer4.startReplaceableGroup(69916922);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController14 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$13$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                HomeLayoutScreenKt.HomeLayoutScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController14 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/audio", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1336466393, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.14
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C608@29148L32,608@29119L62:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1336466393, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:608)");
                                }
                                $composer4.startReplaceableGroup(69920890);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController15 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$14$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                AudioSettingsScreenKt.AudioSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController15 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "settings/sync", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(474162232, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.15
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer4, int $changed2) {
                                Object value$iv3;
                                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                                Intrinsics.checkNotNullParameter(it, "it");
                                ComposerKt.sourceInformation($composer4, "C611@29278L32,611@29242L69:MainActivity.kt#b8997");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(474162232, $changed2, -1, "com.streamsync.app.AppNavHost.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:611)");
                                }
                                $composer4.startReplaceableGroup(69925050);
                                ComposerKt.sourceInformation($composer4, "CC(remember):MainActivity.kt#9igjgp");
                                boolean invalid$iv3 = $composer4.changedInstance(NavHostController.this);
                                final NavHostController navHostController16 = NavHostController.this;
                                Object it$iv2 = $composer4.rememberedValue();
                                if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1$15$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
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
                                            NavHostController.this.popBackStack();
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv3);
                                } else {
                                    value$iv3 = it$iv2;
                                }
                                $composer4.endReplaceableGroup();
                                SubtitleSyncSettingsScreenKt.SubtitleSyncSettingsScreen((Function0) value$iv3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final NavHostController navHostController16 = NavHostController.this;
                        final StreamSelectionStore streamSelectionStore = selection;
                        NavGraphBuilderKt.composable$default(NavHost, "detail/{mediaId}", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-388141929, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.16
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:25:0x011c  */
                            /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.animation.AnimatedContentScope r19, androidx.navigation.NavBackStackEntry r20, androidx.compose.runtime.Composer r21, int r22) {
                                /*
                                    Method dump skipped, instructions count: 298
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.MainActivityKt$AppNavHost$2$1.AnonymousClass16.invoke(androidx.compose.animation.AnimatedContentScope, androidx.navigation.NavBackStackEntry, androidx.compose.runtime.Composer, int):void");
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                        final MediaItemCache mediaItemCache = cache;
                        final NavHostController navHostController17 = NavHostController.this;
                        NavGraphBuilderKt.composable$default(NavHost, "player/{mediaId}", null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1250446090, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$2$1.17
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:23:0x00f6  */
                            /* JADX WARN: Removed duplicated region for block: B:28:0x0126  */
                            /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.animation.AnimatedContentScope r20, final androidx.navigation.NavBackStackEntry r21, androidx.compose.runtime.Composer r22, int r23) {
                                /*
                                    Method dump skipped, instructions count: 308
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.MainActivityKt$AppNavHost$2$1.AnonymousClass17.invoke(androidx.compose.animation.AnimatedContentScope, androidx.navigation.NavBackStackEntry, androidx.compose.runtime.Composer, int):void");
                            }
                        }), WebSocketProtocol.PAYLOAD_SHORT, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            NavHostKt.NavHost(navController, "catalog", modifier3, null, null, null, null, null, null, (Function1) value$iv2, $composer3, ($dirty2 & 14) | 48 | (($dirty2 >> 3) & 896), 504);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivityKt$AppNavHost$3
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
                    MainActivityKt.AppNavHost(NavHostController.this, list, function1, modifier4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
