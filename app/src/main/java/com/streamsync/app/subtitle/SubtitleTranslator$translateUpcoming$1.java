package com.streamsync.app.subtitle;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SubtitleTranslator.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.SubtitleTranslator", f = "SubtitleTranslator.kt", i = {0, 0, 0}, l = {87}, m = "translateUpcoming", n = {"this", "client", "cue"}, s = {"L$0", "L$1", "L$3"})
/* loaded from: classes23.dex */
public final class SubtitleTranslator$translateUpcoming$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SubtitleTranslator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleTranslator$translateUpcoming$1(SubtitleTranslator subtitleTranslator, Continuation<? super SubtitleTranslator$translateUpcoming$1> continuation) {
        super(continuation);
        this.this$0 = subtitleTranslator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.translateUpcoming(null, 0L, 0L, this);
    }
}
