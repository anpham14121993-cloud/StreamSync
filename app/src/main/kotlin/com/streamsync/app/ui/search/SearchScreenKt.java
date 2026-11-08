package com.streamsync.app.ui.search;

import androidx.compose.runtime.State;
import com.google.android.gms.actions.SearchIntents;
import com.streamsync.app.data.repository.SearchResultRow;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: SearchScreen.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\u001a\u0015\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u0006\u001aS\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00030\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00030\u000fH\u0003¢\u0006\u0002\u0010\u0012\u001a+\u0010\u0013\u001a\u00020\u00032\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00030\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017²\u0006\n\u0010\u0018\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\u0010\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u001aX\u008a\u0084\u0002²\u0006\n\u0010\u001b\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u0010\u001c\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rX\u008a\u0084\u0002"}, d2 = {"MIN_SEARCH_LENGTH", "", "HintBlock", "", "text", "", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "SearchResultSection", "row", "Lcom/streamsync/app/data/repository/SearchResultRow;", "showTypeLabel", "", "savedIds", "", "onItemClick", "Lkotlin/Function1;", "Lcom/streamsync/app/data/model/MediaItem;", "onToggleSaved", "(Lcom/streamsync/app/data/repository/SearchResultRow;ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "SearchScreen", "modifier", "Landroidx/compose/ui/Modifier;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_mobileDebug", SearchIntents.EXTRA_QUERY, "rows", "", "loading", "searched"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class SearchScreenKt {
    public static final int MIN_SEARCH_LENGTH = 2;

    /* JADX WARN: Removed duplicated region for block: B:138:0x0482 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0929  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0684  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchScreen(final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r142, androidx.compose.ui.Modifier r143, androidx.compose.runtime.Composer r144, final int r145, final int r146) {
        /*
            Method dump skipped, instructions count: 2392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.search.SearchScreenKt.SearchScreen(kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String SearchScreen$lambda$2(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<SearchResultRow> SearchScreen$lambda$3(State<? extends List<SearchResultRow>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    private static final boolean SearchScreen$lambda$4(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final boolean SearchScreen$lambda$5(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<String> SearchScreen$lambda$6(State<? extends Set<String>> state) {
        Object thisObj$iv = state.getValue();
        return (Set) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x011e, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1.rememberedValue(), java.lang.Integer.valueOf(r30)) != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HintBlock(final java.lang.String r48, androidx.compose.runtime.Composer r49, final int r50) {
        /*
            Method dump skipped, instructions count: 536
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.search.SearchScreenKt.HintBlock(java.lang.String, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:56:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x022d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchResultSection(final com.streamsync.app.data.repository.SearchResultRow r77, final boolean r78, final java.util.Set<java.lang.String> r79, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r80, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.MediaItem, kotlin.Unit> r81, androidx.compose.runtime.Composer r82, final int r83) {
        /*
            Method dump skipped, instructions count: 1156
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.search.SearchScreenKt.SearchResultSection(com.streamsync.app.data.repository.SearchResultRow, boolean, java.util.Set, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):void");
    }
}
