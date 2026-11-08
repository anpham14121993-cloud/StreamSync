package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository", f = "AddonRepository.kt", i = {}, l = {1421, 1423}, m = "fetchMetaVariants", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$fetchMetaVariants$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$fetchMetaVariants$1(AddonRepository addonRepository, Continuation<? super AddonRepository$fetchMetaVariants$1> continuation) {
        super(continuation);
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object fetchMetaVariants;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        fetchMetaVariants = this.this$0.fetchMetaVariants(null, null, null, this);
        return fetchMetaVariants;
    }
}
