package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "Lcom/streamsync/app/data/addon/AddonMeta;", "def"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$findItemRaw$hits$1", f = "AddonRepository.kt", i = {0}, l = {1159}, m = "invokeSuspend", n = {"def"}, s = {"L$0"})
/* loaded from: classes21.dex */
public final class AddonRepository$findItemRaw$hits$1 extends SuspendLambda implements Function2<AddonCatalogDef, Continuation<? super Pair<? extends AddonCatalogDef, ? extends AddonMeta>>, Object> {
    final /* synthetic */ AddonManifest $addon;
    final /* synthetic */ String $itemId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$findItemRaw$hits$1(AddonRepository addonRepository, AddonManifest addonManifest, String str, Continuation<? super AddonRepository$findItemRaw$hits$1> continuation) {
        super(2, continuation);
        this.this$0 = addonRepository;
        this.$addon = addonManifest;
        this.$itemId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepository$findItemRaw$hits$1 addonRepository$findItemRaw$hits$1 = new AddonRepository$findItemRaw$hits$1(this.this$0, this.$addon, this.$itemId, continuation);
        addonRepository$findItemRaw$hits$1.L$0 = obj;
        return addonRepository$findItemRaw$hits$1;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(AddonCatalogDef addonCatalogDef, Continuation<? super Pair<AddonCatalogDef, AddonMeta>> continuation) {
        return ((AddonRepository$findItemRaw$hits$1) create(addonCatalogDef, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(AddonCatalogDef addonCatalogDef, Continuation<? super Pair<? extends AddonCatalogDef, ? extends AddonMeta>> continuation) {
        return invoke2(addonCatalogDef, (Continuation<? super Pair<AddonCatalogDef, AddonMeta>>) continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            switch(r1) {
                case 0: goto L20;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L11:
            r0 = r11
            r1 = 0
            java.lang.Object r2 = r0.L$0
            com.streamsync.app.data.addon.AddonCatalogDef r2 = (com.streamsync.app.data.addon.AddonCatalogDef) r2
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L1e
            r6 = r1
            r1 = r0
            r0 = r12
            goto L4f
        L1e:
            r1 = move-exception
            goto L60
        L20:
            kotlin.ResultKt.throwOnFailure(r12)
            r1 = r11
            java.lang.Object r2 = r1.L$0
            com.streamsync.app.data.addon.AddonCatalogDef r2 = (com.streamsync.app.data.addon.AddonCatalogDef) r2
            java.lang.String r3 = r2.getType()
            java.lang.String r4 = ""
            if (r3 != 0) goto L31
            r3 = r4
        L31:
            java.lang.String r5 = r2.getId()
            if (r5 != 0) goto L38
            goto L39
        L38:
            r4 = r5
        L39:
            com.streamsync.app.data.addon.AddonManifest r5 = r1.$addon
            kotlin.Result$Companion r6 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L5c
            r6 = 0
            com.streamsync.app.data.addon.AddonClient r7 = com.streamsync.app.data.addon.AddonClient.INSTANCE     // Catch: java.lang.Throwable -> L5c
            r1.L$0 = r2     // Catch: java.lang.Throwable -> L5c
            r8 = 1
            r1.label = r8     // Catch: java.lang.Throwable -> L5c
            java.lang.Object r5 = r7.fetchCatalog(r5, r3, r4, r1)     // Catch: java.lang.Throwable -> L5c
            if (r5 != r0) goto L4d
            return r0
        L4d:
            r0 = r12
            r12 = r5
        L4f:
            com.streamsync.app.data.addon.CatalogResponse r12 = (com.streamsync.app.data.addon.CatalogResponse) r12     // Catch: java.lang.Throwable -> L56
            java.lang.Object r12 = kotlin.Result.m6717constructorimpl(r12)     // Catch: java.lang.Throwable -> L56
            goto L6e
        L56:
            r12 = move-exception
            r10 = r1
            r1 = r12
            r12 = r0
            r0 = r10
            goto L60
        L5c:
            r0 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
        L60:
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
            r10 = r0
            r0 = r12
            r12 = r1
            r1 = r10
        L6e:
            boolean r3 = kotlin.Result.m6723isFailureimpl(r12)
            r4 = 0
            if (r3 == 0) goto L76
            r12 = r4
        L76:
            com.streamsync.app.data.addon.CatalogResponse r12 = (com.streamsync.app.data.addon.CatalogResponse) r12
            if (r12 == 0) goto L7f
            java.util.List r12 = r12.getMetas()
            goto L80
        L7f:
            r12 = r4
        L80:
            if (r12 != 0) goto L86
            java.util.List r12 = kotlin.collections.CollectionsKt.emptyList()
        L86:
            r3 = r12
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.lang.String r5 = r1.$itemId
            r12 = 0
            java.util.Iterator r6 = r3.iterator()
        L91:
            boolean r3 = r6.hasNext()
            if (r3 == 0) goto Lab
            java.lang.Object r3 = r6.next()
            r7 = r3
            com.streamsync.app.data.addon.AddonMeta r7 = (com.streamsync.app.data.addon.AddonMeta) r7
            r8 = 0
            java.lang.String r9 = r7.getId()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r5)
            if (r7 == 0) goto L91
            r4 = r3
            goto Lac
        Lab:
        Lac:
            kotlin.Pair r12 = kotlin.TuplesKt.to(r2, r4)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$findItemRaw$hits$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
