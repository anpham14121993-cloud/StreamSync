package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
/* synthetic */ class PlayerScreenKt$PlayerScreen$17$1$37$1 extends FunctionReferenceImpl implements Function1<SubtitleTrack, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PlayerScreenKt$PlayerScreen$17$1$37$1(Object obj) {
        super(1, obj, PlayerViewModel.class, "selectSubtitleTrack", "selectSubtitleTrack(Lcom/streamsync/app/data/model/SubtitleTrack;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(SubtitleTrack subtitleTrack) {
        invoke2(subtitleTrack);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(SubtitleTrack p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((PlayerViewModel) this.receiver).selectSubtitleTrack(p0);
    }
}
