package com.streamsync.app.ui.catalog;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: CatalogBrowseScreen.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010(\u001a\u00020)R/\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR7\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u00188F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010 \u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\u00188F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u000f\u001a\u0004\b!\u0010\u001b\"\u0004\b\"\u0010\u001dR\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/streamsync/app/ui/catalog/CatalogBrowseViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "row", "Lcom/streamsync/app/data/model/CatalogRow;", "(Landroid/content/Context;Lcom/streamsync/app/data/model/CatalogRow;)V", "<set-?>", "", "error", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "error$delegate", "Landroidx/compose/runtime/MutableState;", "", "Lcom/streamsync/app/data/model/MediaItem;", "items", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "items$delegate", "", "loading", "getLoading", "()Z", "setLoading", "(Z)V", "loading$delegate", "loadingMore", "reachedEnd", "getReachedEnd", "setReachedEnd", "reachedEnd$delegate", "repo", "Lcom/streamsync/app/data/repository/AddonRepository;", "skipCursor", "", "loadMore", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class CatalogBrowseViewModel extends ViewModel {
    public static final int $stable = 8;

    /* renamed from: error$delegate, reason: from kotlin metadata */
    private final MutableState error;

    /* renamed from: items$delegate, reason: from kotlin metadata */
    private final MutableState items;

    /* renamed from: loading$delegate, reason: from kotlin metadata */
    private final MutableState loading;
    private boolean loadingMore;

    /* renamed from: reachedEnd$delegate, reason: from kotlin metadata */
    private final MutableState reachedEnd;
    private final AddonRepository repo;
    private final CatalogRow row;
    private int skipCursor;

    public CatalogBrowseViewModel(Context context, CatalogRow row) {
        MutableState mutableStateOf$default;
        MutableState mutableStateOf$default2;
        MutableState mutableStateOf$default3;
        MutableState mutableStateOf$default4;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(row, "row");
        this.row = row;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.repo = new AddonRepository(applicationContext);
        mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(this.row.getItems(), null, 2, null);
        this.items = mutableStateOf$default;
        mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.loading = mutableStateOf$default2;
        mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(this.row.getItems().isEmpty()), null, 2, null);
        this.reachedEnd = mutableStateOf$default3;
        mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.error = mutableStateOf$default4;
        this.skipCursor = this.row.getItems().size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setItems(List<MediaItem> list) {
        MutableState $this$setValue$iv = this.items;
        $this$setValue$iv.setValue(list);
    }

    public final List<MediaItem> getItems() {
        State $this$getValue$iv = this.items;
        return (List) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLoading(boolean z) {
        MutableState $this$setValue$iv = this.loading;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getLoading() {
        State $this$getValue$iv = this.loading;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setReachedEnd(boolean z) {
        MutableState $this$setValue$iv = this.reachedEnd;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getReachedEnd() {
        State $this$getValue$iv = this.reachedEnd;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setError(String str) {
        MutableState $this$setValue$iv = this.error;
        $this$setValue$iv.setValue(str);
    }

    public final String getError() {
        State $this$getValue$iv = this.error;
        return (String) $this$getValue$iv.getValue();
    }

    public final void loadMore() {
        if (this.loadingMore || getReachedEnd()) {
            return;
        }
        this.loadingMore = true;
        setLoading(true);
        setError(null);
        int skip = this.skipCursor;
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CatalogBrowseViewModel$loadMore$1(this, skip, null), 3, null);
    }
}
