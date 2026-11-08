package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/data/model/MediaItem;", "<name for destructuring parameter 0>", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonManifest;", "Lcom/streamsync/app/data/addon/AddonCatalogDef;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$searchRows$found$1", f = "AddonRepository.kt", i = {0, 0, 0}, l = {345}, m = "invokeSuspend", n = {"addon", "def", "defType"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes21.dex */
public final class AddonRepository$searchRows$found$1 extends SuspendLambda implements Function2<Pair<? extends AddonManifest, ? extends AddonCatalogDef>, Continuation<? super List<? extends MediaItem>>, Object> {
    final /* synthetic */ String $q;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$searchRows$found$1(AddonRepository addonRepository, String str, Continuation<? super AddonRepository$searchRows$found$1> continuation) {
        super(2, continuation);
        this.this$0 = addonRepository;
        this.$q = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AddonRepository$searchRows$found$1 addonRepository$searchRows$found$1 = new AddonRepository$searchRows$found$1(this.this$0, this.$q, continuation);
        addonRepository$searchRows$found$1.L$0 = obj;
        return addonRepository$searchRows$found$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Pair<? extends AddonManifest, ? extends AddonCatalogDef> pair, Continuation<? super List<? extends MediaItem>> continuation) {
        return invoke2((Pair<AddonManifest, AddonCatalogDef>) pair, (Continuation<? super List<MediaItem>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Pair<AddonManifest, AddonCatalogDef> pair, Continuation<? super List<MediaItem>> continuation) {
        return ((AddonRepository$searchRows$found$1) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00aa  */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$searchRows$found$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
