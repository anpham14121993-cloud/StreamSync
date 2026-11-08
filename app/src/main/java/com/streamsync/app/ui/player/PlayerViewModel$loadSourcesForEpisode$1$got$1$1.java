package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
/* synthetic */ class PlayerViewModel$loadSourcesForEpisode$1$got$1$1 extends FunctionReferenceImpl implements Function1<List<? extends StreamSource>, Unit> {
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$loadSourcesForEpisode$1$got$1$1(PlayerViewModel playerViewModel) {
        super(1, Intrinsics.Kotlin.class, "showSources", "loadSourcesForEpisode$showSources(Lcom/streamsync/app/ui/player/PlayerViewModel;Ljava/util/List;)V", 0);
        this.this$0 = playerViewModel;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(List<? extends StreamSource> list) {
        invoke2((List<StreamSource>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(List<StreamSource> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        PlayerViewModel.loadSourcesForEpisode$showSources(this.this$0, p0);
    }
}
