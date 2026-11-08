package com.streamsync.app.ui.addons;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.gson.JsonSyntaxException;
import com.streamsync.app.data.addon.AddonClientKt;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.settings.BufferCeiling;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import retrofit2.HttpException;

/* compiled from: AddonsScreen.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0007J\u0018\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\nJ\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\nJ\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\nR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/streamsync/app/ui/addons/AddonsViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_error", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_installed", "", "Lcom/streamsync/app/data/addon/AddonManifest;", "_installing", "", "_lastInstalledUrl", "error", "Lkotlinx/coroutines/flow/StateFlow;", "getError", "()Lkotlinx/coroutines/flow/StateFlow;", "installed", "getInstalled", "installing", "getInstalling", "lastInstalledUrl", "getLastInstalledUrl", "repo", "Lcom/streamsync/app/data/repository/AddonRepository;", "explain", "", "input", "install", "", "manifestUrl", "move", "addon", "delta", "", "moveDown", "moveUp", "uninstall", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes20.dex */
public final class AddonsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<String> _error;
    private final MutableStateFlow<List<AddonManifest>> _installed;
    private final MutableStateFlow<Boolean> _installing;
    private final MutableStateFlow<String> _lastInstalledUrl;
    private final StateFlow<String> error;
    private final StateFlow<List<AddonManifest>> installed;
    private final StateFlow<Boolean> installing;
    private final StateFlow<String> lastInstalledUrl;
    private final AddonRepository repo;

    public AddonsViewModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.repo = new AddonRepository(applicationContext);
        this._installed = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.installed = FlowKt.asStateFlow(this._installed);
        this._error = StateFlowKt.MutableStateFlow(null);
        this.error = FlowKt.asStateFlow(this._error);
        this._installing = StateFlowKt.MutableStateFlow(false);
        this.installing = FlowKt.asStateFlow(this._installing);
        this._lastInstalledUrl = StateFlowKt.MutableStateFlow(null);
        this.lastInstalledUrl = FlowKt.asStateFlow(this._lastInstalledUrl);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    public final StateFlow<List<AddonManifest>> getInstalled() {
        return this.installed;
    }

    public final StateFlow<String> getError() {
        return this.error;
    }

    public final StateFlow<Boolean> getInstalling() {
        return this.installing;
    }

    public final StateFlow<String> getLastInstalledUrl() {
        return this.lastInstalledUrl;
    }

    /* compiled from: AddonsScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.addons.AddonsViewModel$1", f = "AddonsScreen.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.addons.AddonsViewModel$1, reason: invalid class name */
    /* loaded from: classes20.dex */
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
                    Flow<List<AddonManifest>> observeInstalled = AddonsViewModel.this.repo.observeInstalled();
                    final AddonsViewModel addonsViewModel = AddonsViewModel.this;
                    this.label = 1;
                    if (observeInstalled.collect(new FlowCollector() { // from class: com.streamsync.app.ui.addons.AddonsViewModel.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((List<AddonManifest>) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(List<AddonManifest> list, Continuation<? super Unit> continuation) {
                            AddonsViewModel.this._installed.setValue(list);
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

    public final void install(String manifestUrl) {
        Intrinsics.checkNotNullParameter(manifestUrl, "manifestUrl");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AddonsViewModel$install$1(this, manifestUrl, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String explain(Throwable error, String input) {
        Object m6717constructorimpl;
        String detail;
        try {
            Result.Companion companion = Result.INSTANCE;
            AddonsViewModel addonsViewModel = this;
            m6717constructorimpl = Result.m6717constructorimpl(AddonClientKt.normalizeAddonUrl(input) + "/manifest.json");
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = input;
        }
        String requested = (String) m6717constructorimpl;
        Throwable cause = (Throwable) SequencesKt.last(SequencesKt.generateSequence(error, new Function1<Throwable, Throwable>() { // from class: com.streamsync.app.ui.addons.AddonsViewModel$explain$cause$1
            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getCause();
            }
        }));
        if (cause instanceof HttpException) {
            int code = ((HttpException) cause).code();
            String message = ((HttpException) cause).message();
            Intrinsics.checkNotNullExpressionValue(message, "message(...)");
            detail = "máy chủ trả HTTP " + code + " " + StringsKt.trim((CharSequence) StringsKt.substringAfter$default(message, ' ', (String) null, 2, (Object) null)).toString();
        } else if (cause instanceof UnknownHostException) {
            detail = "không phân giải được tên miền";
        } else if (cause instanceof ConnectException) {
            detail = "không kết nối được tới máy chủ";
        } else if (cause instanceof SSLException) {
            detail = "lỗi chứng thực HTTPS: " + cause.getMessage();
        } else if (cause instanceof JsonSyntaxException) {
            detail = "JSON sai định dạng: " + cause.getMessage();
        } else if (cause instanceof IllegalArgumentException) {
            detail = cause.getMessage();
            if (detail == null) {
                detail = "URL không hợp lệ";
            }
        } else {
            detail = cause.getMessage();
            if (detail == null) {
                detail = cause.getClass().getSimpleName();
            }
        }
        return "Không cài được từ " + requested + " - " + detail;
    }

    public final void uninstall(AddonManifest addon) {
        Intrinsics.checkNotNullParameter(addon, "addon");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AddonsViewModel$uninstall$1(this, addon, null), 3, null);
    }

    public final void moveUp(AddonManifest addon) {
        Intrinsics.checkNotNullParameter(addon, "addon");
        move(addon, -1);
    }

    public final void moveDown(AddonManifest addon) {
        Intrinsics.checkNotNullParameter(addon, "addon");
        move(addon, 1);
    }

    private final void move(AddonManifest addon, int delta) {
        List next = CollectionsKt.toMutableList((Collection) this._installed.getValue());
        int index$iv = 0;
        Iterator<AddonManifest> it = next.iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                AddonManifest it2 = (AddonManifest) item$iv;
                if (Intrinsics.areEqual(it2.getBaseUrl(), addon.getBaseUrl())) {
                    break;
                } else {
                    index$iv++;
                }
            } else {
                index$iv = -1;
                break;
            }
        }
        int from = index$iv;
        if (from < 0) {
            return;
        }
        int to = from + delta;
        boolean z = false;
        if (to >= 0 && to < next.size()) {
            z = true;
        }
        if (z) {
            AddonManifest moved = next.remove(from);
            next.add(to, moved);
            this._installed.setValue(next);
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AddonsViewModel$move$1(this, next, null), 3, null);
        }
    }
}
