package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository", f = "AddonRepository.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, l = {1333, 1335, 1347}, m = "resolveViaMeta", n = {"this", "addon", "wanted", "this", "addon", "wanted", "this", "addon", "wanted"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes21.dex */
public final class AddonRepository$resolveViaMeta$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$resolveViaMeta$1(AddonRepository addonRepository, Continuation<? super AddonRepository$resolveViaMeta$1> continuation) {
        super(continuation);
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object resolveViaMeta;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        resolveViaMeta = this.this$0.resolveViaMeta(null, null, null, false, this);
        return resolveViaMeta;
    }
}
