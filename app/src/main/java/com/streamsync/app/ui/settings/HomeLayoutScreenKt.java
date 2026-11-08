package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.model.CatalogEntry;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.HomeLayoutSettings;
import com.streamsync.app.settings.HomeLayoutStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: HomeLayoutScreen.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001am\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0003¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002¨\u0006\u001a²\u0006\n\u0010\u001b\u001a\u00020\u001cX\u008a\u0084\u0002²\u0006\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0017X\u008a\u0084\u0002²\u0006\u0010\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0017X\u008a\u0084\u0002"}, d2 = {"CatalogLayoutRow", "", "entry", "Lcom/streamsync/app/data/model/CatalogEntry;", "index", "", "total", "isHidden", "", "isFeatured", "onMoveUp", "Lkotlin/Function0;", "onMoveDown", "onToggleVisible", "onToggleFeatured", "(Lcom/streamsync/app/data/model/CatalogEntry;IIZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "HomeLayoutScreen", "onBack", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "moveEntry", "store", "Lcom/streamsync/app/settings/HomeLayoutStore;", "ordered", "", "from", "to", "app_mobileDebug", "settings", "Lcom/streamsync/app/settings/HomeLayoutSettings;", "installed", "Lcom/streamsync/app/data/addon/AddonManifest;", "entries"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class HomeLayoutScreenKt {
    public static final void HomeLayoutScreen(final Function0<Unit> onBack, Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(313335040);
        ComposerKt.sourceInformation($composer3, "C(HomeLayoutScreen)59@2783L7,61@2894L41,62@2956L56,63@3048L29,64@3128L55,65@3203L57,66@3270L55,70@3461L195,75@3662L6037:HomeLayoutScreen.kt#bv4dau");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(313335040, $dirty2, -1, "com.streamsync.app.ui.settings.HomeLayoutScreen (HomeLayoutScreen.kt:58)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(-1471614142);
            ComposerKt.sourceInformation($composer3, "CC(remember):HomeLayoutScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = HomeLayoutStore.INSTANCE.get(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final HomeLayoutStore store = (HomeLayoutStore) value$iv;
            $composer3.endReplaceableGroup();
            $composer3.startReplaceableGroup(-1471612143);
            ComposerKt.sourceInformation($composer3, "CC(remember):HomeLayoutScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                value$iv2 = new AddonRepository(applicationContext);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            AddonRepository addonRepo = (AddonRepository) value$iv2;
            $composer3.endReplaceableGroup();
            final State settings$delegate = FlowExtKt.collectAsStateWithLifecycle(store.getSettings(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            final State installed$delegate = FlowExtKt.collectAsStateWithLifecycle(addonRepo.observeInstalled(), CollectionsKt.emptyList(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 48, 14);
            $composer3.startReplaceableGroup(-1471604238);
            ComposerKt.sourceInformation($composer3, "CC(remember):HomeLayoutScreen.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(addonRepo);
            Object it$iv3 = $composer3.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = addonRepo.observeCatalogEntries();
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            $composer3.endReplaceableGroup();
            State entries$delegate = FlowExtKt.collectAsStateWithLifecycle((Flow<? extends List>) value$iv3, CollectionsKt.emptyList(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 48, 14);
            List<CatalogEntry> HomeLayoutScreen$lambda$5 = HomeLayoutScreen$lambda$5(entries$delegate);
            List<String> order = HomeLayoutScreen$lambda$2(settings$delegate).getOrder();
            $composer3.startReplaceableGroup(-1471595844);
            ComposerKt.sourceInformation($composer3, "CC(remember):HomeLayoutScreen.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changed(HomeLayoutScreen$lambda$5) | $composer3.changed(order);
            int $i$f$cache = 0;
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Iterable $this$associateBy$iv = HomeLayoutScreen$lambda$5(entries$delegate);
                int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
                Map destination$iv$iv = new LinkedHashMap(capacity$iv);
                for (Object element$iv$iv : $this$associateBy$iv) {
                    int capacity$iv2 = capacity$iv;
                    CatalogEntry it = (CatalogEntry) element$iv$iv;
                    destination$iv$iv.put(it.getKey(), element$iv$iv);
                    capacity$iv = capacity$iv2;
                    invalid$iv2 = invalid$iv2;
                }
                Map byKey = destination$iv$iv;
                Iterable $this$mapNotNull$iv = HomeLayoutScreen$lambda$2(settings$delegate).getOrder();
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                    String it2 = (String) element$iv$iv$iv;
                    CatalogEntry catalogEntry = (CatalogEntry) byKey.get(it2);
                    if (catalogEntry != null) {
                        destination$iv$iv2.add(catalogEntry);
                    }
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                }
                ArrayList arrayList = (List) destination$iv$iv2;
                Iterable $this$filter$iv = HomeLayoutScreen$lambda$5(entries$delegate);
                Collection destination$iv$iv3 = new ArrayList();
                for (Object element$iv$iv2 : $this$filter$iv) {
                    Map byKey2 = byKey;
                    CatalogEntry it3 = (CatalogEntry) element$iv$iv2;
                    int $i$f$cache2 = $i$f$cache;
                    Object it$iv5 = it$iv4;
                    if (!CollectionsKt.toSet(HomeLayoutScreen$lambda$2(settings$delegate).getOrder()).contains(it3.getKey())) {
                        destination$iv$iv3.add(element$iv$iv2);
                    }
                    byKey = byKey2;
                    it$iv4 = it$iv5;
                    $i$f$cache = $i$f$cache2;
                }
                value$iv4 = CollectionsKt.plus((Collection) arrayList, destination$iv$iv3);
                $composer3.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final List ordered = (List) value$iv4;
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableLambdaKt.composableLambda($composer3, 1918231236, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$1
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C77@3703L431:HomeLayoutScreen.kt#bv4dau");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1918231236, $changed2, -1, "com.streamsync.app.ui.settings.HomeLayoutScreen.<anonymous> (HomeLayoutScreen.kt:77)");
                        }
                        Function2<Composer, Integer, Unit> m6691getLambda1$app_mobileDebug = ComposableSingletons$HomeLayoutScreenKt.INSTANCE.m6691getLambda1$app_mobileDebug();
                        final Function0<Unit> function0 = onBack;
                        ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer4, 415139262, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer5, int $changed3) {
                                ComposerKt.sourceInformation($composer5, "C80@3823L151:HomeLayoutScreen.kt#bv4dau");
                                if (($changed3 & 3) != 2 || !$composer5.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(415139262, $changed3, -1, "com.streamsync.app.ui.settings.HomeLayoutScreen.<anonymous>.<anonymous> (HomeLayoutScreen.kt:80)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$HomeLayoutScreenKt.INSTANCE.m6692getLambda2$app_mobileDebug(), $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer5.skipToGroupEnd();
                            }
                        });
                        final HomeLayoutStore homeLayoutStore = store;
                        AppBarKt.TopAppBar(m6691getLambda1$app_mobileDebug, null, composableLambda, ComposableLambdaKt.composableLambda($composer4, -1732240843, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$1.2
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                invoke(rowScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(RowScope TopAppBar, Composer $composer5, int $changed3) {
                                Object value$iv5;
                                Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
                                ComposerKt.sourceInformation($composer5, "C85@4063L17,85@4042L60:HomeLayoutScreen.kt#bv4dau");
                                if (($changed3 & 17) == 16 && $composer5.getSkipping()) {
                                    $composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1732240843, $changed3, -1, "com.streamsync.app.ui.settings.HomeLayoutScreen.<anonymous>.<anonymous> (HomeLayoutScreen.kt:85)");
                                }
                                $composer5.startReplaceableGroup(-523909176);
                                ComposerKt.sourceInformation($composer5, "CC(remember):HomeLayoutScreen.kt#9igjgp");
                                boolean invalid$iv3 = $composer5.changedInstance(HomeLayoutStore.this);
                                final HomeLayoutStore homeLayoutStore2 = HomeLayoutStore.this;
                                Object it$iv6 = $composer5.rememberedValue();
                                if (invalid$iv3 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                                    value$iv5 = new Function0<Unit>() { // from class: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$1$2$1$1
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
                                            HomeLayoutStore.this.reset();
                                        }
                                    };
                                    $composer5.updateRememberedValue(value$iv5);
                                } else {
                                    value$iv5 = it$iv6;
                                }
                                $composer5.endReplaceableGroup();
                                ButtonKt.TextButton((Function0) value$iv5, null, false, null, null, null, null, null, null, ComposableSingletons$HomeLayoutScreenKt.INSTANCE.m6693getLambda3$app_mobileDebug(), $composer5, 805306368, 510);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), null, null, null, $composer4, 3462, 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, 1026614351, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
                    invoke(paddingValues, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:36:0x020f  */
                /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r43, androidx.compose.runtime.Composer r44, int r45) {
                    /*
                        Method dump skipped, instructions count: 531
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$2.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
                }
            }), $composer3, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.HomeLayoutScreenKt$HomeLayoutScreen$3
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

                public final void invoke(Composer composer, int i) {
                    HomeLayoutScreenKt.HomeLayoutScreen(onBack, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeLayoutSettings HomeLayoutScreen$lambda$2(State<HomeLayoutSettings> state) {
        Object thisObj$iv = state.getValue();
        return (HomeLayoutSettings) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AddonManifest> HomeLayoutScreen$lambda$3(State<? extends List<AddonManifest>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    private static final List<CatalogEntry> HomeLayoutScreen$lambda$5(State<? extends List<CatalogEntry>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0627  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x068f  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x05f2  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x058b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0398  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CatalogLayoutRow(final com.streamsync.app.data.model.CatalogEntry r85, final int r86, final int r87, final boolean r88, final boolean r89, final kotlin.jvm.functions.Function0<kotlin.Unit> r90, final kotlin.jvm.functions.Function0<kotlin.Unit> r91, final kotlin.jvm.functions.Function0<kotlin.Unit> r92, final kotlin.jvm.functions.Function0<kotlin.Unit> r93, androidx.compose.runtime.Composer r94, final int r95) {
        /*
            Method dump skipped, instructions count: 1732
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.HomeLayoutScreenKt.CatalogLayoutRow(com.streamsync.app.data.model.CatalogEntry, int, int, boolean, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void moveEntry(HomeLayoutStore store, List<CatalogEntry> list, int from, int to) {
        boolean z = false;
        if (to >= 0 && to < list.size()) {
            z = true;
        }
        if (z) {
            List<CatalogEntry> $this$map$iv = list;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                CatalogEntry it = (CatalogEntry) item$iv$iv;
                destination$iv$iv.add(it.getKey());
            }
            store.move((List) destination$iv$iv, list.get(from).getKey(), to);
        }
    }
}
