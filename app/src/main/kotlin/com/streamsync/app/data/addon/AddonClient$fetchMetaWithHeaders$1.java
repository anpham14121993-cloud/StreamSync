package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonClient.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.addon.AddonClient", f = "AddonClient.kt", i = {0}, l = {270}, m = "fetchMetaWithHeaders", n = {"$this$fetchMetaWithHeaders_u24lambda_u243"}, s = {"L$0"})
/* loaded from: classes14.dex */
public final class AddonClient$fetchMetaWithHeaders$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddonClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonClient$fetchMetaWithHeaders$1(AddonClient addonClient, Continuation<? super AddonClient$fetchMetaWithHeaders$1> continuation) {
        super(continuation);
        this.this$0 = addonClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetchMetaWithHeaders(null, null, null, this);
    }
}
