package com.streamsync.app.subtitle.autosync;

import androidx.media3.extractor.ts.TsExtractor;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocalAutoSyncEngine.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine", f = "LocalAutoSyncEngine.kt", i = {0}, l = {TsExtractor.TS_STREAM_TYPE_AC4}, m = "finish", n = {"this"}, s = {"L$0"})
/* loaded from: classes19.dex */
public final class LocalAutoSyncEngine$finish$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LocalAutoSyncEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalAutoSyncEngine$finish$1(LocalAutoSyncEngine localAutoSyncEngine, Continuation<? super LocalAutoSyncEngine$finish$1> continuation) {
        super(continuation);
        this.this$0 = localAutoSyncEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.finish(null, 0L, null, this);
    }
}
