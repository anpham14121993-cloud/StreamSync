package com.streamsync.app.subtitle.autosync;

import androidx.core.app.FrameMetricsAggregator;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocalAutoSyncEngine.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine", f = "LocalAutoSyncEngine.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {FrameMetricsAggregator.EVERY_DURATION}, m = "arbitrateWithAi", n = {"client", "referenceCuesAll", "timed", "out", "cand", "referenceCues", "candidateCues", "budgetEnd"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "J$0"})
/* loaded from: classes19.dex */
public final class LocalAutoSyncEngine$arbitrateWithAi$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LocalAutoSyncEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalAutoSyncEngine$arbitrateWithAi$1(LocalAutoSyncEngine localAutoSyncEngine, Continuation<? super LocalAutoSyncEngine$arbitrateWithAi$1> continuation) {
        super(continuation);
        this.this$0 = localAutoSyncEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object arbitrateWithAi;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        arbitrateWithAi = this.this$0.arbitrateWithAi(null, null, null, null, this);
        return arbitrateWithAi;
    }
}
