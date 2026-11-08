package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.SubtitleStyle;
import com.streamsync.app.settings.SubtitleStyleStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
/* synthetic */ class PlayerScreenKt$PlayerScreen$17$1$36$1 extends FunctionReferenceImpl implements Function1<Function1<? super SubtitleStyle, ? extends SubtitleStyle>, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PlayerScreenKt$PlayerScreen$17$1$36$1(Object obj) {
        super(1, obj, SubtitleStyleStore.class, "update", "update(Lkotlin/jvm/functions/Function1;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Function1<? super SubtitleStyle, ? extends SubtitleStyle> function1) {
        invoke2((Function1<? super SubtitleStyle, SubtitleStyle>) function1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Function1<? super SubtitleStyle, SubtitleStyle> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((SubtitleStyleStore) this.receiver).update(p0);
    }
}
