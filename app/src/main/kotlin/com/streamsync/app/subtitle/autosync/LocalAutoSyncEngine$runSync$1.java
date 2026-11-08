package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocalAutoSyncEngine.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine", f = "LocalAutoSyncEngine.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {319, 386, StatusLine.HTTP_MISDIRECTED_REQUEST}, m = "runSync", n = {"this", "usable", "client", "referenceCues", "scored", "heardScored", "captureStartMs", "capturedMs", "this", "usable", "referenceCues", "scored", "capturedMs"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "J$1", "L$0", "L$1", "L$2", "L$3", "J$0"})
/* loaded from: classes19.dex */
public final class LocalAutoSyncEngine$runSync$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LocalAutoSyncEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalAutoSyncEngine$runSync$1(LocalAutoSyncEngine localAutoSyncEngine, Continuation<? super LocalAutoSyncEngine$runSync$1> continuation) {
        super(continuation);
        this.this$0 = localAutoSyncEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object runSync;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        runSync = this.this$0.runSync(null, 0L, null, this);
        return runSync;
    }
}
