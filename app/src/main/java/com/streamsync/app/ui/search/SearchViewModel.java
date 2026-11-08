package com.streamsync.app.ui.search;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.actions.SearchIntents;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.data.repository.LibraryRepository;
import com.streamsync.app.data.repository.SearchResultRow;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: SearchScreen.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\tJ\u0006\u0010&\u001a\u00020#J\u000e\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020)R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0015¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017¨\u0006+"}, d2 = {"Lcom/streamsync/app/ui/search/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_loading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_query", "", "_rows", "", "Lcom/streamsync/app/data/repository/SearchResultRow;", "_savedIds", "", "_searched", "job", "Lkotlinx/coroutines/Job;", "library", "Lcom/streamsync/app/data/repository/LibraryRepository;", "loading", "Lkotlinx/coroutines/flow/StateFlow;", "getLoading", "()Lkotlinx/coroutines/flow/StateFlow;", SearchIntents.EXTRA_QUERY, "getQuery", "repo", "Lcom/streamsync/app/data/repository/AddonRepository;", "rows", "getRows", "savedIds", "getSavedIds", "searched", "getSearched", "clear", "", "onQueryChange", "value", "submit", "toggleSaved", "item", "Lcom/streamsync/app/data/model/MediaItem;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class SearchViewModel extends ViewModel {

    @Deprecated
    public static final long SEARCH_DEBOUNCE_MS = 400;
    private final MutableStateFlow<Boolean> _loading;
    private final MutableStateFlow<String> _query;
    private final MutableStateFlow<List<SearchResultRow>> _rows;
    private final MutableStateFlow<Set<String>> _savedIds;
    private final MutableStateFlow<Boolean> _searched;
    private Job job;
    private final LibraryRepository library;
    private final StateFlow<Boolean> loading;
    private final StateFlow<String> query;
    private final AddonRepository repo;
    private final StateFlow<List<SearchResultRow>> rows;
    private final StateFlow<Set<String>> savedIds;
    private final StateFlow<Boolean> searched;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public SearchViewModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.repo = new AddonRepository(applicationContext);
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        this.library = new LibraryRepository(applicationContext2);
        this._query = StateFlowKt.MutableStateFlow("");
        this.query = FlowKt.asStateFlow(this._query);
        this._rows = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.rows = FlowKt.asStateFlow(this._rows);
        this._loading = StateFlowKt.MutableStateFlow(false);
        this.loading = FlowKt.asStateFlow(this._loading);
        this._searched = StateFlowKt.MutableStateFlow(false);
        this.searched = FlowKt.asStateFlow(this._searched);
        this._savedIds = StateFlowKt.MutableStateFlow(SetsKt.emptySet());
        this.savedIds = FlowKt.asStateFlow(this._savedIds);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    public final StateFlow<String> getQuery() {
        return this.query;
    }

    public final StateFlow<List<SearchResultRow>> getRows() {
        return this.rows;
    }

    public final StateFlow<Boolean> getLoading() {
        return this.loading;
    }

    public final StateFlow<Boolean> getSearched() {
        return this.searched;
    }

    public final StateFlow<Set<String>> getSavedIds() {
        return this.savedIds;
    }

    /* compiled from: SearchScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.search.SearchViewModel$1", f = "SearchScreen.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.search.SearchViewModel$1, reason: invalid class name */
    /* loaded from: classes14.dex */
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
                    Flow<Set<String>> observeIds = SearchViewModel.this.library.observeIds();
                    final SearchViewModel searchViewModel = SearchViewModel.this;
                    this.label = 1;
                    if (observeIds.collect(new FlowCollector() { // from class: com.streamsync.app.ui.search.SearchViewModel.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Set<String>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Set<String> set, Continuation<? super Unit> continuation) {
                            SearchViewModel.this._savedIds.setValue(set);
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

    public final void onQueryChange(String value) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(value, "value");
        this._query.setValue(value);
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (StringsKt.isBlank(value) || StringsKt.trim((CharSequence) value).toString().length() < 2) {
            this._rows.setValue(CollectionsKt.emptyList());
            this._searched.setValue(false);
            this._loading.setValue(false);
        } else {
            this._loading.setValue(true);
            launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SearchViewModel$onQueryChange$1(this, value, null), 3, null);
            this.job = launch$default;
        }
    }

    public final void submit() {
        Job launch$default;
        String value = this._query.getValue();
        if (StringsKt.isBlank(value) || StringsKt.trim((CharSequence) value).toString().length() < 2) {
            return;
        }
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this._loading.setValue(true);
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SearchViewModel$submit$1(this, value, null), 3, null);
        this.job = launch$default;
    }

    public final void clear() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this._query.setValue("");
        this._rows.setValue(CollectionsKt.emptyList());
        this._searched.setValue(false);
        this._loading.setValue(false);
    }

    public final void toggleSaved(MediaItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SearchViewModel$toggleSaved$1(this, item, null), 3, null);
    }

    /* compiled from: SearchScreen.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/streamsync/app/ui/search/SearchViewModel$Companion;", "", "()V", "SEARCH_DEBOUNCE_MS", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
