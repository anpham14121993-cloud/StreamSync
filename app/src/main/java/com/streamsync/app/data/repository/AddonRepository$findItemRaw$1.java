package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository", f = "AddonRepository.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7}, l = {1037, 1069, 1100, 1107, 1140, 1156, 1181, 1185}, m = "findItemRaw", n = {"this", "compositeId", "type", "cached", "t0", "this", "compositeId", "type", "addonId", "itemId", "t0", "this", "compositeId", "type", "addonId", "itemId", "addon", "t0", "this", "compositeId", "type", "itemId", "addon", "candidateAddon", "t0", "this", "compositeId", "type", "itemId", "addon", "t0", "this", "compositeId", "itemId", "addon", "t0", "this", "addon", "def", "meta", "defType", "this", "addon", "def", "defType", "full"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes21.dex */
public final class AddonRepository$findItemRaw$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$findItemRaw$1(AddonRepository addonRepository, Continuation<? super AddonRepository$findItemRaw$1> continuation) {
        super(continuation);
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object findItemRaw;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        findItemRaw = this.this$0.findItemRaw(null, null, this);
        return findItemRaw;
    }
}
