package com.streamsync.app.data.repository;

import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.StreamResponse;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/data/addon/StreamResponse;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$2", f = "AddonRepository.kt", i = {}, l = {603}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes21.dex */
public final class AddonRepository$resolveStreams$ask$2 extends SuspendLambda implements Function1<Continuation<? super StreamResponse>, Object> {
    final /* synthetic */ long $askedAt;
    final /* synthetic */ Pair<AddonManifest, String> $target;
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ AddonRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonRepository$resolveStreams$ask$2(AddonRepository addonRepository, long j, Pair<AddonManifest, String> pair, String str, Continuation<? super AddonRepository$resolveStreams$ask$2> continuation) {
        super(1, continuation);
        this.this$0 = addonRepository;
        this.$askedAt = j;
        this.$target = pair;
        this.$type = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new AddonRepository$resolveStreams$ask$2(this.this$0, this.$askedAt, this.$target, this.$type, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super StreamResponse> continuation) {
        return ((AddonRepository$resolveStreams$ask$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c5  */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
