package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ImdbIdResolver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.addon.ImdbIdResolver$resolve$4$jobs$1$1", f = "ImdbIdResolver.kt", i = {0, 1}, l = {247, 88, 93, 93}, m = "invokeSuspend", n = {"$this$withPermit$iv", "$this$withPermit$iv"}, s = {"L$4", "L$0"})
/* loaded from: classes14.dex */
public final class ImdbIdResolver$resolve$4$jobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AddonManifest $addon;
    final /* synthetic */ String $catalogId;
    final /* synthetic */ Channel<Integer> $done;
    final /* synthetic */ Semaphore $gate;
    final /* synthetic */ String[] $hits;
    final /* synthetic */ int $index;
    final /* synthetic */ String $query;
    final /* synthetic */ String $title;
    final /* synthetic */ String $type;
    final /* synthetic */ Integer $year;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ ImdbIdResolver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImdbIdResolver$resolve$4$jobs$1$1(String[] strArr, int i, ImdbIdResolver imdbIdResolver, String str, Integer num, Channel<Integer> channel, Semaphore semaphore, AddonManifest addonManifest, String str2, String str3, String str4, Continuation<? super ImdbIdResolver$resolve$4$jobs$1$1> continuation) {
        super(2, continuation);
        this.$hits = strArr;
        this.$index = i;
        this.this$0 = imdbIdResolver;
        this.$title = str;
        this.$year = num;
        this.$done = channel;
        this.$gate = semaphore;
        this.$addon = addonManifest;
        this.$type = str2;
        this.$catalogId = str3;
        this.$query = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ImdbIdResolver$resolve$4$jobs$1$1 imdbIdResolver$resolve$4$jobs$1$1 = new ImdbIdResolver$resolve$4$jobs$1$1(this.$hits, this.$index, this.this$0, this.$title, this.$year, this.$done, this.$gate, this.$addon, this.$type, this.$catalogId, this.$query, continuation);
        imdbIdResolver$resolve$4$jobs$1$1.L$0 = obj;
        return imdbIdResolver$resolve$4$jobs$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImdbIdResolver$resolve$4$jobs$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0007. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ea A[Catch: all -> 0x012c, TryCatch #5 {all -> 0x012c, blocks: (B:22:0x00df, B:25:0x00e6, B:27:0x00ea, B:29:0x00f2, B:30:0x00f6), top: B:21:0x00df }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f2 A[Catch: all -> 0x012c, TryCatch #5 {all -> 0x012c, blocks: (B:22:0x00df, B:25:0x00e6, B:27:0x00ea, B:29:0x00f2, B:30:0x00f6), top: B:21:0x00df }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0125 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0153 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a9  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [com.streamsync.app.data.addon.ImdbIdResolver$resolve$4$jobs$1$1] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.streamsync.app.data.addon.ImdbIdResolver$resolve$4$jobs$1$1] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v3 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.ImdbIdResolver$resolve$4$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
