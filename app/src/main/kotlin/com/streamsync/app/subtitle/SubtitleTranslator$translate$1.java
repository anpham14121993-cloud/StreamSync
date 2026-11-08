package com.streamsync.app.subtitle;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SubtitleTranslator.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.SubtitleTranslator", f = "SubtitleTranslator.kt", i = {0}, l = {65}, m = "translate", n = {"text"}, s = {"L$0"})
/* loaded from: classes23.dex */
public final class SubtitleTranslator$translate$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SubtitleTranslator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleTranslator$translate$1(SubtitleTranslator subtitleTranslator, Continuation<? super SubtitleTranslator$translate$1> continuation) {
        super(continuation);
        this.this$0 = subtitleTranslator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.translate(null, this);
    }
}
