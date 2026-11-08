package com.streamsync.app.data.repository;

import androidx.media3.extractor.ts.TsExtractor;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StreamCache.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.StreamCacheKt", f = "StreamCache.kt", i = {0, 0, 1}, l = {TsExtractor.TS_STREAM_TYPE_DVBSUBS, 84}, m = "withPermit", n = {"block", "$this$withPermit$iv", "$this$withPermit$iv"}, s = {"L$0", "L$1", "L$0"})
/* loaded from: classes21.dex */
public final class StreamCacheKt$withPermit$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamCacheKt$withPermit$1(Continuation<? super StreamCacheKt$withPermit$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StreamCacheKt.withPermit(null, null, this);
    }
}
