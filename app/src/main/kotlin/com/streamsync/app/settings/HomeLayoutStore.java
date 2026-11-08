package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: HomeLayoutStore.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J$\u0010\u000f\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0012J\b\u0010\u0018\u001a\u00020\u0007H\u0002J\u0006\u0010\u0019\u001a\u00020\u0010J\u001a\u0010\u001a\u001a\u00020\u00102\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u001cR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/streamsync/app/settings/HomeLayoutStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_settings", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/streamsync/app/settings/HomeLayoutSettings;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "move", "", "from", "", "to", "allKeys", "", "", "key", "read", "reset", "update", "transform", "Lkotlin/Function1;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class HomeLayoutStore {
    private static final String KEY_CONTINUE = "show_continue_watching";
    private static final String KEY_FEATURED = "featured_catalog";
    private static final String KEY_FEATURED_POSITION = "featured_position";
    private static final String KEY_HIDDEN = "hidden_catalogs";
    private static final String KEY_ORDER = "catalog_order";
    private static volatile HomeLayoutStore instance;
    private final MutableStateFlow<HomeLayoutSettings> _settings;
    private final SharedPreferences prefs;
    private final StateFlow<HomeLayoutSettings> settings;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ HomeLayoutStore(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private HomeLayoutStore(Context context) {
        this.prefs = context.getApplicationContext().getSharedPreferences("home_layout", 0);
        this._settings = StateFlowKt.MutableStateFlow(read());
        this.settings = FlowKt.asStateFlow(this._settings);
    }

    public final StateFlow<HomeLayoutSettings> getSettings() {
        return this.settings;
    }

    private final HomeLayoutSettings read() {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            HomeLayoutStore $this$read_u24lambda_u240 = this;
            String string = $this$read_u24lambda_u240.prefs.getString(KEY_FEATURED_POSITION, null);
            if (string == null) {
                string = "TOP";
            }
            Intrinsics.checkNotNull(string);
            m6717constructorimpl = Result.m6717constructorimpl(FeaturedPosition.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        FeaturedPosition featuredPosition = FeaturedPosition.TOP;
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = featuredPosition;
        }
        FeaturedPosition position = (FeaturedPosition) m6717constructorimpl;
        String string2 = this.prefs.getString(KEY_FEATURED, null);
        if (string2 == null) {
            string2 = "";
        }
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) string2, new String[]{","}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List featured = CollectionsKt.take((List) destination$iv$iv, 2);
        String string3 = this.prefs.getString(KEY_ORDER, "");
        if (string3 == null) {
            string3 = "";
        }
        Iterable $this$filter$iv2 = StringsKt.split$default((CharSequence) string3, new String[]{","}, false, 0, 6, (Object) null);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            String it2 = (String) element$iv$iv2;
            if (!StringsKt.isBlank(it2)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        ArrayList arrayList = (List) destination$iv$iv2;
        String string4 = this.prefs.getString(KEY_HIDDEN, "");
        Iterable $this$filter$iv3 = StringsKt.split$default((CharSequence) (string4 != null ? string4 : ""), new String[]{","}, false, 0, 6, (Object) null);
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv3 : $this$filter$iv3) {
            String it3 = (String) element$iv$iv3;
            if (!StringsKt.isBlank(it3)) {
                destination$iv$iv3.add(element$iv$iv3);
            }
        }
        return new HomeLayoutSettings(arrayList, CollectionsKt.toSet((List) destination$iv$iv3), featured, position, this.prefs.getBoolean(KEY_CONTINUE, true));
    }

    public final void update(Function1<? super HomeLayoutSettings, HomeLayoutSettings> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        HomeLayoutSettings next = transform.invoke(this._settings.getValue());
        this.prefs.edit().putString(KEY_ORDER, CollectionsKt.joinToString$default(next.getOrder(), ",", null, null, 0, null, null, 62, null)).putString(KEY_HIDDEN, CollectionsKt.joinToString$default(next.getHidden(), ",", null, null, 0, null, null, 62, null)).putString(KEY_FEATURED, CollectionsKt.joinToString$default(CollectionsKt.take(next.getFeaturedKeys(), 2), ",", null, null, 0, null, null, 62, null)).putString(KEY_FEATURED_POSITION, next.getFeaturedPosition().name()).putBoolean(KEY_CONTINUE, next.getShowContinueWatching()).apply();
        this._settings.setValue(next);
    }

    public final void move(final int from, final int to) {
        update(new Function1<HomeLayoutSettings, HomeLayoutSettings>() { // from class: com.streamsync.app.settings.HomeLayoutStore$move$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final HomeLayoutSettings invoke(HomeLayoutSettings current) {
                Intrinsics.checkNotNullParameter(current, "current");
                List list = CollectionsKt.toMutableList((Collection) current.getOrder());
                if (from >= 0 && from < list.size()) {
                    if (to >= 0 && to < list.size()) {
                        list.add(to, list.remove(from));
                        return HomeLayoutSettings.copy$default(current, list, null, null, null, false, 30, null);
                    }
                }
                return current;
            }
        });
    }

    public final void move(final List<String> allKeys, final String key, final int to) {
        Intrinsics.checkNotNullParameter(allKeys, "allKeys");
        Intrinsics.checkNotNullParameter(key, "key");
        update(new Function1<HomeLayoutSettings, HomeLayoutSettings>() { // from class: com.streamsync.app.settings.HomeLayoutStore$move$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final HomeLayoutSettings invoke(HomeLayoutSettings current) {
                Intrinsics.checkNotNullParameter(current, "current");
                int from = allKeys.indexOf(key);
                if (from >= 0) {
                    boolean z = false;
                    if (to >= 0 && to < allKeys.size()) {
                        z = true;
                    }
                    if (z) {
                        List list = CollectionsKt.toMutableList((Collection) allKeys);
                        list.add(to, list.remove(from));
                        return HomeLayoutSettings.copy$default(current, list, null, null, null, false, 30, null);
                    }
                }
                return current;
            }
        });
    }

    public final void reset() {
        update(new Function1<HomeLayoutSettings, HomeLayoutSettings>() { // from class: com.streamsync.app.settings.HomeLayoutStore$reset$1
            @Override // kotlin.jvm.functions.Function1
            public final HomeLayoutSettings invoke(HomeLayoutSettings it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new HomeLayoutSettings(null, null, null, null, false, 31, null);
            }
        });
    }

    /* compiled from: HomeLayoutStore.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/streamsync/app/settings/HomeLayoutStore$Companion;", "", "()V", "KEY_CONTINUE", "", "KEY_FEATURED", "KEY_FEATURED_POSITION", "KEY_HIDDEN", "KEY_ORDER", "instance", "Lcom/streamsync/app/settings/HomeLayoutStore;", "get", "context", "Landroid/content/Context;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HomeLayoutStore get(Context context) {
            HomeLayoutStore it;
            Intrinsics.checkNotNullParameter(context, "context");
            HomeLayoutStore homeLayoutStore = HomeLayoutStore.instance;
            if (homeLayoutStore == null) {
                synchronized (this) {
                    it = HomeLayoutStore.instance;
                    if (it == null) {
                        it = new HomeLayoutStore(context, null);
                        Companion companion = HomeLayoutStore.INSTANCE;
                        HomeLayoutStore.instance = it;
                    }
                }
                return it;
            }
            return homeLayoutStore;
        }
    }
}
