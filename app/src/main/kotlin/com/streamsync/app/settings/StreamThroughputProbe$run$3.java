package com.streamsync.app.settings;

import android.util.Log;
import com.streamsync.app.settings.StreamThroughputProbe;
import com.streamsync.app.settings.ThroughputAdvice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StreamThroughputProbe.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/settings/StreamThroughputProbe$ProbeResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.settings.StreamThroughputProbe$run$3", f = "StreamThroughputProbe.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes14.dex */
public final class StreamThroughputProbe$run$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super StreamThroughputProbe.ProbeResult>, Object> {
    final /* synthetic */ Function2<ThroughputAdvice.Row, Boolean, Unit> $onRow;
    final /* synthetic */ LastStream $target;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public StreamThroughputProbe$run$3(LastStream lastStream, Function2<? super ThroughputAdvice.Row, ? super Boolean, Unit> function2, Continuation<? super StreamThroughputProbe$run$3> continuation) {
        super(2, continuation);
        this.$target = lastStream;
        this.$onRow = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StreamThroughputProbe$run$3(this.$target, this.$onRow, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super StreamThroughputProbe.ProbeResult> continuation) {
        return ((StreamThroughputProbe$run$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ThroughputAdvice.Row baseline;
        ThroughputAdvice.Row row;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Long boxLong = Boxing.boxLong(this.$target.getContentLengthBytes());
                long it = boxLong.longValue();
                if (!(it > 0)) {
                    boxLong = null;
                }
                long length = boxLong != null ? boxLong.longValue() : StreamThroughputProbe.INSTANCE.resolveLength(this.$target);
                if (length > 0) {
                    baseline = StreamThroughputProbe.INSTANCE.measureBaseline(this.$target, length, 8L);
                    this.$onRow.invoke(baseline, Boxing.boxBoolean(true));
                    Log.i("StreamSyncData", "Đo một kết nối: " + (baseline != null ? ThroughputAdvice.INSTANCE.fmt(baseline.getMegabitsPerSecond()) : null) + " Mbps");
                    List rows = new ArrayList();
                    Iterator<Integer> it2 = ThroughputAdvice.INSTANCE.getTEST_CHUNK_MB().iterator();
                    while (it2.hasNext()) {
                        int chunkMb = it2.next().intValue();
                        row = StreamThroughputProbe.INSTANCE.measureParallel(this.$target, length, 8L, chunkMb);
                        if (row != null) {
                            rows.add(row);
                            Log.i("StreamSyncData", "Đo song song " + chunkMb + " MB: " + ThroughputAdvice.INSTANCE.fmt(row.getMegabitsPerSecond()) + " Mbps");
                        }
                        this.$onRow.invoke(row, Boxing.boxBoolean(false));
                    }
                    return new StreamThroughputProbe.ProbeResult(baseline, rows);
                }
                Log.i("StreamSyncData", "Bỏ qua đo tốc độ: không biết kích thước file");
                return new StreamThroughputProbe.ProbeResult(null, CollectionsKt.emptyList());
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
