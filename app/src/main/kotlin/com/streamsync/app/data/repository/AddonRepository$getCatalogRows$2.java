package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.model.CatalogRow;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/data/model/CatalogRow;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$getCatalogRows$2", f = "AddonRepository.kt", i = {0}, l = {241}, m = "invokeSuspend", n = {"rows"}, s = {"L$0"})
/* loaded from: classes21.dex */
public final class AddonRepository$getCatalogRows$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<CatalogRow>>, Object> {
    final /* synthetic */ List<Pair<AddonManifest, AddonCatalogDef>> $jobs;
    final /* synthetic */ Function1<CatalogRow, Unit> $onRow;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AddonRepository$getCatalogRows$2(List<Pair<AddonManifest, AddonCatalogDef>> list, Function1<? super CatalogRow, Unit> function1, AddonRepository addonRepository, Continuation<? super AddonRepository$getCatalogRows$2> continuation) {
        super(2, continuation);
        this.$jobs = list;
        this.$onRow = function1;
        this.this$0 = addonRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepository$getCatalogRows$2 addonRepository$getCatalogRows$2 = new AddonRepository$getCatalogRows$2(this.$jobs, this.$onRow, this.this$0, continuation);
        addonRepository$getCatalogRows$2.L$0 = obj;
        return addonRepository$getCatalogRows$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<CatalogRow>> continuation) {
        return ((AddonRepository$getCatalogRows$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00c9  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00be -> B:7:0x00c5). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$getCatalogRows$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
