package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository", f = "AddonRepository.kt", i = {0}, l = {1514}, m = "withVideosIfSeries", n = {"$this$withVideosIfSeries"}, s = {"L$0"})
/* loaded from: classes21.dex */
public final class AddonRepository$withVideosIfSeries$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$withVideosIfSeries$1(AddonRepository addonRepository, Continuation<? super AddonRepository$withVideosIfSeries$1> continuation) {
        super(continuation);
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object withVideosIfSeries;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        withVideosIfSeries = this.this$0.withVideosIfSeries(null, null, null, null, this);
        return withVideosIfSeries;
    }
}
