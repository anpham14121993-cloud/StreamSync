package com.streamsync.app.ui.search;

import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
/* synthetic */ class SearchScreenKt$SearchScreen$2$5$1$2$1$1 extends FunctionReferenceImpl implements Function1<MediaItem, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchScreenKt$SearchScreen$2$5$1$2$1$1(Object obj) {
        super(1, obj, SearchViewModel.class, "toggleSaved", "toggleSaved(Lcom/streamsync/app/data/model/MediaItem;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
        invoke2(mediaItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(MediaItem p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((SearchViewModel) this.receiver).toggleSaved(p0);
    }
}
