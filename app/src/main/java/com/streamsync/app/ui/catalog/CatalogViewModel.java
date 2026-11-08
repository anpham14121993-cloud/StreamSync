package com.streamsync.app.ui.catalog;

import android.content.Context;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.StreamSyncApp;
import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.local.ContinueWatchingDiskCache;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.model.CatalogKey;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.MediaType;
import com.streamsync.app.data.model.WatchProgress;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.data.repository.ContentRepository;
import com.streamsync.app.data.repository.SyncRepository;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.HomeLayoutSettings;
import com.streamsync.app.settings.HomeLayoutStore;
import com.streamsync.app.ui.player.AddonIdParser;
import com.streamsync.app.util.FileLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: CatalogScreen.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0007H\u0002J\u0010\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u000eJ\u0016\u0010<\u001a\u00020:2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010>\u001a\u00020:H\u0002J\u0012\u0010?\u001a\u0004\u0018\u00010\n2\u0006\u0010@\u001a\u00020\bH\u0002J\u0016\u0010A\u001a\u00020\u00122\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n \u0018*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001c¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u000e\u0010!\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u001c¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\n0\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001c¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u001c¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001eR\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u001c¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/streamsync/app/ui/catalog/CatalogViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_continueWatching", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/streamsync/app/data/model/MediaItem;", "_error", "", "_installedAddons", "", "_loading", "", "_progressSnapshot", "Lcom/streamsync/app/data/model/WatchProgress;", "_rows", "Lcom/streamsync/app/data/model/CatalogRow;", "addonIdsWhenLoadStarted", "", "addonRepo", "Lcom/streamsync/app/data/repository/AddonRepository;", "applicationContext", "kotlin.jvm.PlatformType", "contentRepo", "Lcom/streamsync/app/data/repository/ContentRepository;", "continueWatching", "Lkotlinx/coroutines/flow/StateFlow;", "getContinueWatching", "()Lkotlinx/coroutines/flow/StateFlow;", "error", "getError", "hasFinishedALoad", "installedAddons", "getInstalledAddons", "lastKnownAddonIds", "latestProgress", TtmlNode.TAG_LAYOUT, "Lcom/streamsync/app/settings/HomeLayoutSettings;", "getLayout", "layoutStore", "Lcom/streamsync/app/settings/HomeLayoutStore;", "loading", "getLoading", "pendingPersist", "persistJob", "Lkotlinx/coroutines/Job;", "progressSnapshot", "getProgressSnapshot", "rows", "getRows", "syncRepo", "Lcom/streamsync/app/data/repository/SyncRepository;", "addonFingerprint", "addons", "Lcom/streamsync/app/data/addon/AddonManifest;", "load", "", "force", "persistContinueWatching", "items", "rebuildContinueWatching", "showPosterOf", "item", "staticRow", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class CatalogViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<List<MediaItem>> _continueWatching;
    private final MutableStateFlow<String> _error;
    private final MutableStateFlow<Integer> _installedAddons;
    private final MutableStateFlow<Boolean> _loading;
    private final MutableStateFlow<List<WatchProgress>> _progressSnapshot;
    private final MutableStateFlow<List<CatalogRow>> _rows;
    private Set<String> addonIdsWhenLoadStarted;
    private final AddonRepository addonRepo;
    private final Context applicationContext;
    private final ContentRepository contentRepo;
    private final StateFlow<List<MediaItem>> continueWatching;
    private final StateFlow<String> error;
    private boolean hasFinishedALoad;
    private final StateFlow<Integer> installedAddons;
    private Set<String> lastKnownAddonIds;
    private List<WatchProgress> latestProgress;
    private final StateFlow<HomeLayoutSettings> layout;
    private final HomeLayoutStore layoutStore;
    private final StateFlow<Boolean> loading;
    private List<MediaItem> pendingPersist;
    private Job persistJob;
    private final StateFlow<List<WatchProgress>> progressSnapshot;
    private final StateFlow<List<CatalogRow>> rows;
    private final SyncRepository syncRepo;

    public CatalogViewModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.applicationContext = context.getApplicationContext();
        this.contentRepo = new ContentRepository();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.addonRepo = new AddonRepository(applicationContext);
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        this.syncRepo = new SyncRepository(applicationContext2);
        this.layoutStore = HomeLayoutStore.INSTANCE.get(context);
        this._rows = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.rows = FlowKt.asStateFlow(this._rows);
        this._loading = StateFlowKt.MutableStateFlow(true);
        this.loading = FlowKt.asStateFlow(this._loading);
        this._error = StateFlowKt.MutableStateFlow(null);
        this.error = FlowKt.asStateFlow(this._error);
        this._progressSnapshot = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.progressSnapshot = FlowKt.asStateFlow(this._progressSnapshot);
        this._continueWatching = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.continueWatching = FlowKt.asStateFlow(this._continueWatching);
        this._installedAddons = StateFlowKt.MutableStateFlow(0);
        this.installedAddons = FlowKt.asStateFlow(this._installedAddons);
        this.layout = this.layoutStore.getSettings();
        this.latestProgress = CollectionsKt.emptyList();
        this.lastKnownAddonIds = SetsKt.emptySet();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass2(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass3(null), 3, null);
        load$default(this, false, 1, null);
    }

    public final StateFlow<List<CatalogRow>> getRows() {
        return this.rows;
    }

    public final StateFlow<Boolean> getLoading() {
        return this.loading;
    }

    public final StateFlow<String> getError() {
        return this.error;
    }

    public final StateFlow<List<WatchProgress>> getProgressSnapshot() {
        return this.progressSnapshot;
    }

    public final StateFlow<List<MediaItem>> getContinueWatching() {
        return this.continueWatching;
    }

    public final StateFlow<Integer> getInstalledAddons() {
        return this.installedAddons;
    }

    public final StateFlow<HomeLayoutSettings> getLayout() {
        return this.layout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> addonFingerprint(List<AddonManifest> addons) {
        Iterable $this$flatMapTo$iv$iv;
        String str;
        List<AddonManifest> $this$flatMap$iv = addons;
        int $i$f$flatMap = 0;
        Collection destination$iv$iv = new ArrayList();
        Iterable $this$flatMapTo$iv$iv2 = $this$flatMap$iv;
        int $i$f$flatMapTo = 0;
        for (Object element$iv$iv : $this$flatMapTo$iv$iv2) {
            AddonManifest addon = (AddonManifest) element$iv$iv;
            Iterable catalogs = addon.getCatalogs();
            if (catalogs == null) {
                catalogs = CollectionsKt.emptyList();
            }
            Iterable $this$map$iv = catalogs;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                AddonCatalogDef it = (AddonCatalogDef) item$iv$iv;
                Iterable $this$flatMap$iv2 = $this$flatMap$iv;
                String id = addon.getId();
                String type = it.getType();
                int $i$f$flatMap2 = $i$f$flatMap;
                String str2 = type == null ? "" : type;
                String id2 = it.getId();
                if (id2 == null) {
                    $this$flatMapTo$iv$iv = $this$flatMapTo$iv$iv2;
                    str = "";
                } else {
                    $this$flatMapTo$iv$iv = $this$flatMapTo$iv$iv2;
                    str = id2;
                }
                destination$iv$iv2.add(id + "|catalog|" + str2 + "|" + str);
                $this$flatMap$iv = $this$flatMap$iv2;
                $i$f$flatMapTo = $i$f$flatMapTo;
                $this$flatMapTo$iv$iv2 = $this$flatMapTo$iv$iv;
                $i$f$flatMap = $i$f$flatMap2;
            }
            Iterable $this$flatMap$iv3 = $this$flatMap$iv;
            List catalogs2 = CollectionsKt.sorted((List) destination$iv$iv2);
            Iterable list$iv$iv = CollectionsKt.plus((Collection) CollectionsKt.listOf(addon.getId() + "|self|"), (Iterable) catalogs2);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            $this$flatMap$iv = $this$flatMap$iv3;
            $this$flatMapTo$iv$iv2 = $this$flatMapTo$iv$iv2;
            $i$f$flatMap = $i$f$flatMap;
        }
        return CollectionsKt.toSet((List) destination$iv$iv);
    }

    /* compiled from: CatalogScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$1", f = "CatalogScreen.kt", i = {}, l = {193}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.catalog.CatalogViewModel$1, reason: invalid class name */
    /* loaded from: classes22.dex */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    long startedAt = System.currentTimeMillis();
                    ContinueWatchingDiskCache continueWatchingDiskCache = ContinueWatchingDiskCache.INSTANCE;
                    Context context = CatalogViewModel.this.applicationContext;
                    Intrinsics.checkNotNullExpressionValue(context, "access$getApplicationContext$p(...)");
                    List cached = continueWatchingDiskCache.load(context);
                    long tookMs = System.currentTimeMillis() - startedAt;
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new C00811(CatalogViewModel.this, cached, tookMs, null), this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CatalogScreen.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        @DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$1$1", f = "CatalogScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.streamsync.app.ui.catalog.CatalogViewModel$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes22.dex */
        public static final class C00811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<MediaItem> $cached;
            final /* synthetic */ long $tookMs;
            int label;
            final /* synthetic */ CatalogViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00811(CatalogViewModel catalogViewModel, List<MediaItem> list, long j, Continuation<? super C00811> continuation) {
                super(2, continuation);
                this.this$0 = catalogViewModel;
                this.$cached = list;
                this.$tookMs = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00811(this.this$0, this.$cached, this.$tookMs, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        if (((List) this.this$0._continueWatching.getValue()).isEmpty()) {
                            MediaItemCache.INSTANCE.put(this.$cached);
                            this.this$0._continueWatching.setValue(this.$cached);
                        }
                        FileLog.d$default(FileLog.INSTANCE, "Tiep tuc xem: doc " + this.$cached.size() + " muc tu dia trong " + this.$tookMs + " ms", null, 2, null);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* compiled from: CatalogScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$2", f = "CatalogScreen.kt", i = {}, l = {210}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.catalog.CatalogViewModel$2, reason: invalid class name */
    /* loaded from: classes22.dex */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Flow<List<AddonManifest>> observeInstalled = CatalogViewModel.this.addonRepo.observeInstalled();
                    final CatalogViewModel catalogViewModel = CatalogViewModel.this;
                    this.label = 1;
                    if (observeInstalled.collect(new FlowCollector() { // from class: com.streamsync.app.ui.catalog.CatalogViewModel.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((List<AddonManifest>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(List<AddonManifest> list, Continuation<? super Unit> continuation) {
                            Set fingerprint = CatalogViewModel.this.addonFingerprint(list);
                            if (!Intrinsics.areEqual(fingerprint, CatalogViewModel.this.lastKnownAddonIds)) {
                                CatalogViewModel.this.lastKnownAddonIds = fingerprint;
                                CatalogViewModel.this._installedAddons.setValue(Boxing.boxInt(list.size()));
                                if (CatalogViewModel.this.hasFinishedALoad) {
                                    CatalogViewModel.this.load(true);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: CatalogScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.catalog.CatalogViewModel$3", f = "CatalogScreen.kt", i = {}, l = {233}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.catalog.CatalogViewModel$3, reason: invalid class name */
    /* loaded from: classes22.dex */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Flow<List<WatchProgress>> observeProgress = CatalogViewModel.this.syncRepo.observeProgress();
                    final CatalogViewModel catalogViewModel = CatalogViewModel.this;
                    this.label = 1;
                    if (observeProgress.collect(new FlowCollector() { // from class: com.streamsync.app.ui.catalog.CatalogViewModel.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((List<WatchProgress>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(List<WatchProgress> list, Continuation<? super Unit> continuation) {
                            CatalogViewModel.this.latestProgress = list;
                            CatalogViewModel.this._progressSnapshot.setValue(list);
                            CatalogViewModel.this.rebuildContinueWatching();
                            return Unit.INSTANCE;
                        }
                    }, this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void load$default(CatalogViewModel catalogViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        catalogViewModel.load(z);
    }

    public final void load(boolean force) {
        boolean z = true;
        if (!(!this._rows.getValue().isEmpty()) && !(!this._continueWatching.getValue().isEmpty())) {
            z = false;
        }
        boolean hasCache = z;
        if (force || !hasCache || this._loading.getValue().booleanValue()) {
            Context app = this.applicationContext;
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CatalogViewModel$load$1(this, app, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rebuildContinueWatching() {
        Iterable $this$mapNotNull$iv;
        int $i$f$mapNotNull;
        MediaItem copy;
        MediaItem item;
        Iterable $this$filter$iv = this.latestProgress;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            WatchProgress it = (WatchProgress) element$iv$iv;
            if (it.getPositionMs() > 10000 && (it.getDurationMs() <= 0 || ((double) it.getPositionMs()) < ((double) it.getDurationMs()) * 0.97d)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedByDescending$iv = (List) destination$iv$iv;
        Iterable $this$mapNotNull$iv2 = CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.streamsync.app.ui.catalog.CatalogViewModel$rebuildContinueWatching$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                WatchProgress it2 = (WatchProgress) t2;
                WatchProgress it3 = (WatchProgress) t;
                return ComparisonsKt.compareValues(Long.valueOf(it2.getUpdatedAtEpochMs()), Long.valueOf(it3.getUpdatedAtEpochMs()));
            }
        });
        int $i$f$mapNotNull2 = 0;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
            WatchProgress progress = (WatchProgress) element$iv$iv$iv;
            MediaItem item2 = MediaItemCache.INSTANCE.get(progress.getMediaId());
            if (item2 == null) {
                item = null;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                $i$f$mapNotNull = $i$f$mapNotNull2;
            } else {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                long durationMs = progress.getDurationMs() / 1000;
                String showPosterOf = showPosterOf(item2);
                if (showPosterOf == null) {
                    showPosterOf = item2.getPosterUrl();
                }
                copy = item2.copy((r51 & 1) != 0 ? item2.id : null, (r51 & 2) != 0 ? item2.title : null, (r51 & 4) != 0 ? item2.posterUrl : showPosterOf, (r51 & 8) != 0 ? item2.description : null, (r51 & 16) != 0 ? item2.type : null, (r51 & 32) != 0 ? item2.year : null, (r51 & 64) != 0 ? item2.durationSeconds : Long.valueOf(durationMs), (r51 & 128) != 0 ? item2.backgroundUrl : null, (r51 & 256) != 0 ? item2.releaseInfo : null, (r51 & 512) != 0 ? item2.imdbRating : null, (r51 & 1024) != 0 ? item2.genres : null, (r51 & 2048) != 0 ? item2.genresResolved : false, (r51 & 4096) != 0 ? item2.directors : null, (r51 & 8192) != 0 ? item2.cast : null, (r51 & 16384) != 0 ? item2.castPeople : null, (r51 & 32768) != 0 ? item2.writers : null, (r51 & 65536) != 0 ? item2.certification : null, (r51 & 131072) != 0 ? item2.runtimeText : null, (r51 & 262144) != 0 ? item2.country : null, (r51 & 524288) != 0 ? item2.language : null, (r51 & 1048576) != 0 ? item2.awards : null, (r51 & 2097152) != 0 ? item2.logoUrl : null, (r51 & 4194304) != 0 ? item2.trailerId : null, (r51 & 8388608) != 0 ? item2.released : null, (r51 & 16777216) != 0 ? item2.similarNames : null, (r51 & 33554432) != 0 ? item2.addonId : null, (r51 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? item2.addonName : null, (r51 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? item2.catalogId : null, (r51 & 268435456) != 0 ? item2.catalogName : null, (r51 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? item2.catalogType : null, (r51 & 1073741824) != 0 ? item2.season : null, (r51 & Integer.MIN_VALUE) != 0 ? item2.episode : null, (r52 & 1) != 0 ? item2.videos : null);
                item = copy;
            }
            if (item != null) {
                destination$iv$iv2.add(item);
            }
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            $i$f$mapNotNull2 = $i$f$mapNotNull;
        }
        Iterable $this$distinctBy$iv = (List) destination$iv$iv2;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            if (set$iv.add(((MediaItem) e$iv).getId())) {
                list$iv.add(e$iv);
            }
        }
        ArrayList $this$distinctBy$iv2 = list$iv;
        List items = CollectionsKt.take($this$distinctBy$iv2, 20);
        this._continueWatching.setValue(items);
        persistContinueWatching(items);
    }

    private final void persistContinueWatching(List<MediaItem> items) {
        Job launch$default;
        this.pendingPersist = items;
        Job job = this.persistJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z) {
            return;
        }
        Context context = this.applicationContext;
        StreamSyncApp app = context instanceof StreamSyncApp ? (StreamSyncApp) context : null;
        if (app == null) {
            return;
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(app.getAppScope(), null, null, new CatalogViewModel$persistContinueWatching$1(this, null), 3, null);
        this.persistJob = launch$default;
    }

    private final String showPosterOf(MediaItem item) {
        String showId;
        MediaItem mediaItem;
        String it;
        if (item.getType() != MediaType.SERIES_EPISODE || (showId = AddonIdParser.INSTANCE.showIdOfEpisode(item.getId())) == null || (mediaItem = MediaItemCache.INSTANCE.get(showId)) == null || (it = mediaItem.getPosterUrl()) == null || !(!StringsKt.isBlank(it))) {
            return null;
        }
        return it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogRow staticRow(List<MediaItem> items) {
        return new CatalogRow(CatalogKey.INSTANCE.of(null, "movie", "api"), "api", "API của bạn", "movie", "api", "Thư viện của tôi", items, null, 128, null);
    }
}
