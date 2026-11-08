package com.streamsync.app.settings;

import androidx.autofill.HintConstants;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.ThroughputAdvice;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: StreamThroughputProbe.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001,B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\"\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0082\bJ*\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0002JR\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u00102:\b\u0002\u0010\u001c\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110!¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00160\u001dH\u0086@¢\u0006\u0002\u0010#JV\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160+H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/streamsync/app/settings/StreamThroughputProbe;", "", "()V", "CHUNK_UNBOUNDED", "", "PARALLEL_PROBES", "", "READ_BUFFER_BYTES", "TAG", "", "clientFor", "Lokhttp3/OkHttpClient;", "seconds", "measureBaseline", "Lcom/streamsync/app/settings/ThroughputAdvice$Row;", "target", "Lcom/streamsync/app/settings/LastStream;", LastStreamStore.KEY_LENGTH, "measureElapsed", "", "block", "Lkotlin/Function0;", "", "measureParallel", "chunkMb", "resolveLength", "run", "Lcom/streamsync/app/settings/StreamThroughputProbe$ProbeResult;", "onRow", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "row", "", "isBaseline", "(Lcom/streamsync/app/settings/LastStream;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "streamRange", "client", TtmlNode.START, "chunkBytes", "stop", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onBytes", "Lkotlin/Function1;", "ProbeResult", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class StreamThroughputProbe {
    public static final int $stable = 0;
    private static final long CHUNK_UNBOUNDED = Long.MAX_VALUE;
    public static final StreamThroughputProbe INSTANCE = new StreamThroughputProbe();
    public static final int PARALLEL_PROBES = 4;
    private static final int READ_BUFFER_BYTES = 65536;
    private static final String TAG = "StreamSyncData";

    private StreamThroughputProbe() {
    }

    private final OkHttpClient clientFor(long seconds) {
        return new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(5 + seconds, TimeUnit.SECONDS).callTimeout(10 + seconds, TimeUnit.SECONDS).build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object run$default(StreamThroughputProbe streamThroughputProbe, LastStream lastStream, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = new Function2<ThroughputAdvice.Row, Boolean, Unit>() { // from class: com.streamsync.app.settings.StreamThroughputProbe$run$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ThroughputAdvice.Row row, Boolean bool) {
                    invoke(row, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ThroughputAdvice.Row row, boolean z) {
                }
            };
        }
        return streamThroughputProbe.run(lastStream, function2, continuation);
    }

    public final Object run(LastStream target, Function2<? super ThroughputAdvice.Row, ? super Boolean, Unit> function2, Continuation<? super ProbeResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new StreamThroughputProbe$run$3(target, function2, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long resolveLength(LastStream target) {
        Object m6717constructorimpl;
        long contentLength;
        String substringAfterLast$default;
        String obj;
        Long longOrNull;
        try {
            Result.Companion companion = Result.INSTANCE;
            StreamThroughputProbe $this$resolveLength_u24lambda_u243 = this;
            Request.Builder $this$resolveLength_u24lambda_u243_u24lambda_u241 = new Request.Builder().url(target.getUrl()).header(HttpHeaders.RANGE, "bytes=0-0");
            Map $this$forEach$iv = target.getHeaders();
            for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                String k = element$iv.getKey();
                String v = element$iv.getValue();
                $this$resolveLength_u24lambda_u243_u24lambda_u241.header(k, v);
            }
            Request request = $this$resolveLength_u24lambda_u243_u24lambda_u241.build();
            Response execute = $this$resolveLength_u24lambda_u243.clientFor(10L).newCall(request).execute();
            try {
                Response response = execute;
                String header$default = Response.header$default(response, HttpHeaders.CONTENT_RANGE, null, 2, null);
                if (header$default == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(header$default, '/', (String) null, 2, (Object) null)) == null || (obj = StringsKt.trim((CharSequence) substringAfterLast$default).toString()) == null || (longOrNull = StringsKt.toLongOrNull(obj)) == null) {
                    ResponseBody body = response.body();
                    contentLength = body != null ? body.getContentLength() : 0L;
                } else {
                    contentLength = longOrNull.longValue();
                }
                CloseableKt.closeFinally(execute, null);
                m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(contentLength));
            } finally {
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = 0L;
        }
        return ((Number) m6717constructorimpl).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ThroughputAdvice.Row measureBaseline(LastStream target, long length, long seconds) {
        Object m6717constructorimpl;
        ThroughputAdvice.Row row;
        try {
            Result.Companion companion = Result.INSTANCE;
            StreamThroughputProbe $this$measureBaseline_u24lambda_u244 = this;
            final AtomicLong bytes = new AtomicLong(0L);
            double elapsed = streamRange$default($this$measureBaseline_u24lambda_u244, $this$measureBaseline_u24lambda_u244.clientFor(seconds), target, 0L, Long.MAX_VALUE, length, seconds, null, new Function1<Long, Unit>() { // from class: com.streamsync.app.settings.StreamThroughputProbe$measureBaseline$1$elapsed$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                    invoke(l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(long it) {
                    bytes.addAndGet(it);
                }
            }, 64, null);
            if (elapsed <= 0.0d) {
                row = null;
            } else {
                row = new ThroughputAdvice.Row(0, ((bytes.get() * 8.0d) / elapsed) / 1000000.0d);
            }
            m6717constructorimpl = Result.m6717constructorimpl(row);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        return (ThroughputAdvice.Row) (Result.m6723isFailureimpl(m6717constructorimpl) ? null : m6717constructorimpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.streamsync.app.settings.ThroughputAdvice.Row measureParallel(final com.streamsync.app.settings.LastStream r32, long r33, final long r35, int r37) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.settings.StreamThroughputProbe.measureParallel(com.streamsync.app.settings.LastStream, long, long, int):com.streamsync.app.settings.ThroughputAdvice$Row");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void measureParallel$lambda$10$lambda$7$lambda$5(StreamThroughputProbe this_runCatching, long $seconds, LastStream target, long $start, long $chunkBytes, long $end, AtomicBoolean stop, final AtomicLong total) {
        Intrinsics.checkNotNullParameter(this_runCatching, "$this_runCatching");
        Intrinsics.checkNotNullParameter(target, "$target");
        Intrinsics.checkNotNullParameter(stop, "$stop");
        Intrinsics.checkNotNullParameter(total, "$total");
        this_runCatching.streamRange(this_runCatching.clientFor($seconds), target, $start, $chunkBytes, ($end - $start) + 1, $seconds, stop, new Function1<Long, Unit>() { // from class: com.streamsync.app.settings.StreamThroughputProbe$measureParallel$1$threads$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                invoke(l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(long it) {
                total.addAndGet(it);
            }
        });
    }

    static /* synthetic */ double streamRange$default(StreamThroughputProbe streamThroughputProbe, OkHttpClient okHttpClient, LastStream lastStream, long j, long j2, long j3, long j4, AtomicBoolean atomicBoolean, Function1 function1, int i, Object obj) {
        AtomicBoolean atomicBoolean2;
        if ((i & 64) == 0) {
            atomicBoolean2 = atomicBoolean;
        } else {
            atomicBoolean2 = new AtomicBoolean(false);
        }
        return streamThroughputProbe.streamRange(okHttpClient, lastStream, j, j2, j3, j4, atomicBoolean2, function1);
    }

    private final double streamRange(OkHttpClient client, LastStream target, long start, long chunkBytes, long length, long seconds, AtomicBoolean stop, Function1<? super Long, Unit> onBytes) {
        Throwable th;
        Throwable th2;
        StreamThroughputProbe this_$iv = this;
        int $i$f$measureElapsed = 0;
        long start$iv = System.nanoTime();
        long deadline = System.nanoTime() + (C.NANOS_PER_SECOND * seconds);
        long cursor = start;
        long j = 1;
        long end = (start + length) - 1;
        while (cursor <= end && !stop.get() && System.nanoTime() < deadline) {
            long last = chunkBytes >= length ? end : RangesKt.coerceAtMost((cursor + chunkBytes) - j, end);
            Request.Builder $this$streamRange_u24lambda_u2415_u24lambda_u2412 = new Request.Builder().url(target.getUrl()).header(HttpHeaders.RANGE, "bytes=" + cursor + "-" + last);
            Map $this$forEach$iv = target.getHeaders();
            for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                StreamThroughputProbe this_$iv2 = this_$iv;
                String k = element$iv.getKey();
                int $i$f$measureElapsed2 = $i$f$measureElapsed;
                String v = element$iv.getValue();
                $this$streamRange_u24lambda_u2415_u24lambda_u2412.header(k, v);
                this_$iv = this_$iv2;
                $i$f$measureElapsed = $i$f$measureElapsed2;
            }
            StreamThroughputProbe this_$iv3 = this_$iv;
            int $i$f$measureElapsed3 = $i$f$measureElapsed;
            Request request = $this$streamRange_u24lambda_u2415_u24lambda_u2412.build();
            Response execute = client.newCall(request).execute();
            try {
                Response response = execute;
                if (response.isSuccessful()) {
                    try {
                        ResponseBody body = response.body();
                        if (body != null) {
                            InputStream byteStream = body.byteStream();
                            try {
                                InputStream input = byteStream;
                                byte[] buffer = new byte[65536];
                                while (!stop.get()) {
                                    int read = input.read(buffer);
                                    InputStream input2 = input;
                                    if (read <= 0) {
                                        break;
                                    }
                                    long cursor2 = cursor;
                                    long cursor3 = read;
                                    try {
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                    try {
                                        onBytes.invoke(Long.valueOf(cursor3));
                                        if (System.nanoTime() >= deadline) {
                                            break;
                                        }
                                        input = input2;
                                        cursor = cursor2;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        th2 = th;
                                        try {
                                            throw th2;
                                        } finally {
                                        }
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                try {
                                    CloseableKt.closeFinally(byteStream, null);
                                } catch (Throwable th5) {
                                    th = th5;
                                    try {
                                        throw th;
                                    } catch (Throwable th6) {
                                        CloseableKt.closeFinally(execute, th);
                                        throw th6;
                                    }
                                }
                            } catch (Throwable th7) {
                                th2 = th7;
                            }
                        }
                    } catch (Throwable th8) {
                        th = th8;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(execute, null);
                cursor = last + 1;
                $i$f$measureElapsed = $i$f$measureElapsed3;
                j = 1;
                this_$iv = this_$iv3;
            } catch (Throwable th9) {
                th = th9;
            }
        }
        return (System.nanoTime() - start$iv) / 1.0E9d;
    }

    private final double measureElapsed(Function0<Unit> block) {
        long start = System.nanoTime();
        block.invoke();
        return (System.nanoTime() - start) / 1.0E9d;
    }

    /* compiled from: StreamThroughputProbe.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J%\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/streamsync/app/settings/StreamThroughputProbe$ProbeResult;", "", "baseline", "Lcom/streamsync/app/settings/ThroughputAdvice$Row;", "parallelRows", "", "(Lcom/streamsync/app/settings/ThroughputAdvice$Row;Ljava/util/List;)V", "getBaseline", "()Lcom/streamsync/app/settings/ThroughputAdvice$Row;", "getParallelRows", "()Ljava/util/List;", "advice", "Lcom/streamsync/app/settings/ThroughputAdvice$Result;", "videoBitrateMbps", "", "(Ljava/lang/Double;)Lcom/streamsync/app/settings/ThroughputAdvice$Result;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final /* data */ class ProbeResult {
        public static final int $stable = 8;
        private final ThroughputAdvice.Row baseline;
        private final List<ThroughputAdvice.Row> parallelRows;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ProbeResult copy$default(ProbeResult probeResult, ThroughputAdvice.Row row, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                row = probeResult.baseline;
            }
            if ((i & 2) != 0) {
                list = probeResult.parallelRows;
            }
            return probeResult.copy(row, list);
        }

        /* renamed from: component1, reason: from getter */
        public final ThroughputAdvice.Row getBaseline() {
            return this.baseline;
        }

        public final List<ThroughputAdvice.Row> component2() {
            return this.parallelRows;
        }

        public final ProbeResult copy(ThroughputAdvice.Row baseline, List<ThroughputAdvice.Row> parallelRows) {
            Intrinsics.checkNotNullParameter(parallelRows, "parallelRows");
            return new ProbeResult(baseline, parallelRows);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProbeResult)) {
                return false;
            }
            ProbeResult probeResult = (ProbeResult) other;
            return Intrinsics.areEqual(this.baseline, probeResult.baseline) && Intrinsics.areEqual(this.parallelRows, probeResult.parallelRows);
        }

        public int hashCode() {
            return ((this.baseline == null ? 0 : this.baseline.hashCode()) * 31) + this.parallelRows.hashCode();
        }

        public String toString() {
            return "ProbeResult(baseline=" + this.baseline + ", parallelRows=" + this.parallelRows + ")";
        }

        public ProbeResult(ThroughputAdvice.Row baseline, List<ThroughputAdvice.Row> parallelRows) {
            Intrinsics.checkNotNullParameter(parallelRows, "parallelRows");
            this.baseline = baseline;
            this.parallelRows = parallelRows;
        }

        public final ThroughputAdvice.Row getBaseline() {
            return this.baseline;
        }

        public final List<ThroughputAdvice.Row> getParallelRows() {
            return this.parallelRows;
        }

        public final ThroughputAdvice.Result advice(Double videoBitrateMbps) {
            ThroughputAdvice throughputAdvice = ThroughputAdvice.INSTANCE;
            ThroughputAdvice.Row row = this.baseline;
            return throughputAdvice.interpret(row != null ? row.getMegabitsPerSecond() : 0.0d, this.parallelRows, videoBitrateMbps);
        }
    }
}
