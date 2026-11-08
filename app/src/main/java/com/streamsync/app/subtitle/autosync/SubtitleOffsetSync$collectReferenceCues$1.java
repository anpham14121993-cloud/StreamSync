package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SubtitleOffsetSync.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.SubtitleOffsetSync", f = "SubtitleOffsetSync.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {208, 209}, m = "collectReferenceCues", n = {"provider", "seenTexts", "onPoll", "collected", "allowNonLatin", "startedAt", "provider", "seenTexts", "onPoll", "collected", "allowNonLatin", "startedAt"}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "J$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "J$0"})
/* loaded from: classes19.dex */
public final class SubtitleOffsetSync$collectReferenceCues$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SubtitleOffsetSync this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleOffsetSync$collectReferenceCues$1(SubtitleOffsetSync subtitleOffsetSync, Continuation<? super SubtitleOffsetSync$collectReferenceCues$1> continuation) {
        super(continuation);
        this.this$0 = subtitleOffsetSync;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collectReferenceCues(null, null, false, null, this);
    }
}
