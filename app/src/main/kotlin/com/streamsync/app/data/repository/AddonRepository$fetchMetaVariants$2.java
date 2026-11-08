package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "", "Lcom/streamsync/app/data/addon/AddonMeta;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$2", f = "AddonRepository.kt", i = {0}, l = {1427}, m = "invokeSuspend", n = {"destination$iv$iv"}, s = {"L$0"})
/* loaded from: classes21.dex */
public final class AddonRepository$fetchMetaVariants$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<String, ? extends AddonMeta>>, Object> {
    final /* synthetic */ AddonManifest $addon;
    final /* synthetic */ String $itemId;
    final /* synthetic */ List<String> $wanted;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$fetchMetaVariants$2(List<String> list, AddonRepository addonRepository, AddonManifest addonManifest, String str, Continuation<? super AddonRepository$fetchMetaVariants$2> continuation) {
        super(2, continuation);
        this.$wanted = list;
        this.this$0 = addonRepository;
        this.$addon = addonManifest;
        this.$itemId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepository$fetchMetaVariants$2 addonRepository$fetchMetaVariants$2 = new AddonRepository$fetchMetaVariants$2(this.$wanted, this.this$0, this.$addon, this.$itemId, continuation);
        addonRepository$fetchMetaVariants$2.L$0 = obj;
        return addonRepository$fetchMetaVariants$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Map<String, ? extends AddonMeta>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Map<String, AddonMeta>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Map<String, AddonMeta>> continuation) {
        return ((AddonRepository$fetchMetaVariants$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0121 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0108 -> B:7:0x0112). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
