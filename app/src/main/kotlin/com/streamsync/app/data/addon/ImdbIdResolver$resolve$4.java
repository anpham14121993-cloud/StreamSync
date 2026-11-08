package com.streamsync.app.data.addon;

import androidx.appcompat.app.AppCompatDelegate;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ImdbIdResolver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.addon.ImdbIdResolver$resolve$4", f = "ImdbIdResolver.kt", i = {0, 0, 0}, l = {AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 121}, m = "invokeSuspend", n = {"jobs", "remaining", "firstOpen"}, s = {"L$0", "I$0", "I$1"})
/* loaded from: classes14.dex */
public final class ImdbIdResolver$resolve$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel<Integer> $done;
    final /* synthetic */ boolean[] $finished;
    final /* synthetic */ Semaphore $gate;
    final /* synthetic */ String[] $hits;
    final /* synthetic */ List<Triple<AddonManifest, String, String>> $plans;
    final /* synthetic */ String $title;
    final /* synthetic */ String $type;
    final /* synthetic */ Ref.ObjectRef<String> $winner;
    final /* synthetic */ Integer $year;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ImdbIdResolver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImdbIdResolver$resolve$4(List<Triple<AddonManifest, String, String>> list, Ref.ObjectRef<String> objectRef, Channel<Integer> channel, boolean[] zArr, String[] strArr, ImdbIdResolver imdbIdResolver, String str, Integer num, Semaphore semaphore, String str2, Continuation<? super ImdbIdResolver$resolve$4> continuation) {
        super(2, continuation);
        this.$plans = list;
        this.$winner = objectRef;
        this.$done = channel;
        this.$finished = zArr;
        this.$hits = strArr;
        this.this$0 = imdbIdResolver;
        this.$title = str;
        this.$year = num;
        this.$gate = semaphore;
        this.$type = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ImdbIdResolver$resolve$4 imdbIdResolver$resolve$4 = new ImdbIdResolver$resolve$4(this.$plans, this.$winner, this.$done, this.$finished, this.$hits, this.this$0, this.$title, this.$year, this.$gate, this.$type, continuation);
        imdbIdResolver$resolve$4.L$0 = obj;
        return imdbIdResolver$resolve$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImdbIdResolver$resolve$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0009. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0193 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015c A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v8, types: [T, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x011e -> B:10:0x0123). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r40) {
        /*
            Method dump skipped, instructions count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.ImdbIdResolver$resolve$4.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
