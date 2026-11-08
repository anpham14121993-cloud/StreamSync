package com.streamsync.app.data.repository;

import android.content.Context;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.ImdbIdResolver;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$resolveStreams$imdbDeferred$1", f = "AddonRepository.kt", i = {}, l = {519}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$resolveStreams$imdbDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ List<AddonManifest> $addons;
    final /* synthetic */ String $itemId;
    final /* synthetic */ String $releaseInfo;
    final /* synthetic */ String $title;
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$resolveStreams$imdbDeferred$1(AddonRepository addonRepository, List<AddonManifest> list, String str, String str2, String str3, String str4, Continuation<? super AddonRepository$resolveStreams$imdbDeferred$1> continuation) {
        super(2, continuation);
        this.this$0 = addonRepository;
        this.$addons = list;
        this.$type = str;
        this.$itemId = str2;
        this.$title = str3;
        this.$releaseInfo = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AddonRepository$resolveStreams$imdbDeferred$1(this.this$0, this.$addons, this.$type, this.$itemId, this.$title, this.$releaseInfo, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((AddonRepository$resolveStreams$imdbDeferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                context = this.this$0.context;
                this.label = 1;
                Object resolve = new ImdbIdResolver(context).resolve(this.$addons, this.$type, this.$itemId, this.$title, this.$releaseInfo, this);
                return resolve == coroutine_suspended ? coroutine_suspended : resolve;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
