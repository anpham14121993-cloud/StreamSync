package com.streamsync.app.data.repository;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository", f = "AddonRepository.kt", i = {0, 0, 0, 0, 0}, l = {285}, m = "fetchCatalogRow", n = {"this", "addon", "def", "defType", TtmlNode.RUBY_BASE}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes21.dex */
public final class AddonRepository$fetchCatalogRow$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$fetchCatalogRow$1(AddonRepository addonRepository, Continuation<? super AddonRepository$fetchCatalogRow$1> continuation) {
        super(continuation);
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object fetchCatalogRow;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        fetchCatalogRow = this.this$0.fetchCatalogRow(null, null, this);
        return fetchCatalogRow;
    }
}
