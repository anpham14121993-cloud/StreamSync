package com.streamsync.app.ui.player;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.datasource.BaseDataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.streamsync.app.data.remote.NetTuning;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.LastStreamStore;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.ui.player.PlaybackWarmStore;
import com.streamsync.app.util.FileLog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Dispatcher;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;

/* compiled from: OkHttpVideoDataSource.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0002ABB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0015H\u0016J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\"H\u0002J\b\u0010'\u001a\u00020\"H\u0002J\b\u0010(\u001a\u00020\fH\u0016J\u001a\u0010)\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150+0*H\u0016J\n\u0010,\u001a\u0004\u0018\u00010\u0012H\u0016J8\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\tH\u0002J\u0010\u00104\u001a\u00020\t2\u0006\u0010.\u001a\u00020\u0010H\u0016J \u00105\u001a\u00020\t2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\t2\u0006\u00106\u001a\u00020\tH\u0002J \u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0016J\n\u0010<\u001a\u0004\u0018\u00010\u0019H\u0002J\u0018\u0010=\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u0015H\u0016J\u001a\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\tH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/streamsync/app/ui/player/OkHttpVideoDataSource;", "Landroidx/media3/datasource/BaseDataSource;", "Landroidx/media3/datasource/HttpDataSource;", "context", "Landroid/content/Context;", "options", "Lcom/streamsync/app/ui/player/OkHttpVideoDataSource$BufferNetworkOptions;", "(Landroid/content/Context;Lcom/streamsync/app/ui/player/OkHttpVideoDataSource$BufferNetworkOptions;)V", "bytesRemaining", "", "bytesServed", "chunkSize", "", "client", "Lokhttp3/OkHttpClient;", "currentDataSpec", "Landroidx/media3/datasource/DataSpec;", "currentUri", "Landroid/net/Uri;", "extraHeaders", "", "", "httpResponse", "Lokhttp3/Response;", "input", "Ljava/io/InputStream;", "openStart", "opened", "", "store", "Lcom/streamsync/app/settings/PlaybackSettingsStore;", "warmInput", "warmStart", "clearAllRequestProperties", "", "clearRequestProperty", "key", "close", "closeQuietly", "discardPreviousSource", "getResponseCode", "getResponseHeaders", "", "", "getUri", "logOpen", "dataSpec", TtmlNode.START, "source", "resolved", "skipped", "elapsedMs", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "openViaHttp", "openAtMs", "read", "buffer", "", "offset", LastStreamStore.KEY_LENGTH, "reopenAfterIoError", "setRequestProperty", "value", "warmWindow", "Lcom/streamsync/app/ui/player/PlaybackWarmStore$Window;", "BufferNetworkOptions", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class OkHttpVideoDataSource extends BaseDataSource implements HttpDataSource {
    private static final boolean DEBUG_LOG = true;
    private static final String DEBUG_TAG = "StreamSyncData";
    private static final int IDLE_POOL_MAX = 32;
    private static final long IDLE_POOL_MINUTES = 3;
    private static final int READ_RESUME_ATTEMPTS = 3;
    private static volatile OkHttpClient cached;
    private static volatile String cachedSignature;
    private static volatile ConnectionPool sharedPool;
    private long bytesRemaining;
    private long bytesServed;
    private final int chunkSize;
    private final OkHttpClient client;
    private DataSpec currentDataSpec;
    private Uri currentUri;
    private final Map<String, String> extraHeaders;
    private Response httpResponse;
    private InputStream input;
    private long openStart;
    private boolean opened;
    private final BufferNetworkOptions options;
    private final PlaybackSettingsStore store;
    private InputStream warmInput;
    private long warmStart;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static volatile int sharedPoolParallel = -1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpVideoDataSource(Context context, BufferNetworkOptions options) {
        super(true);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        this.options = options;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.store = new PlaybackSettingsStore(applicationContext);
        this.client = INSTANCE.clientFor$app_mobileDebug(context);
        this.chunkSize = RangesKt.coerceIn(this.options.getChunkSizeMb() * 1024 * 1024, 65536, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        this.extraHeaders = new LinkedHashMap();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) {
        long j;
        Intrinsics.checkNotNullParameter(dataSpec, "dataSpec");
        long openAtMs = SystemClock.elapsedRealtime();
        transferInitializing(dataSpec);
        transferStarted(dataSpec);
        this.opened = true;
        Uri uri = dataSpec.uri;
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        this.currentUri = uri;
        this.currentDataSpec = dataSpec;
        this.bytesServed = 0L;
        this.bytesRemaining = -1L;
        discardPreviousSource();
        long start = dataSpec.position == -1 ? 0L : dataSpec.position;
        this.openStart = start;
        PlaybackWarmStore.Window window = warmWindow(dataSpec, start);
        if (window != null) {
            this.warmStart = window.getStartPosition();
            long skip = start - window.getStartPosition();
            ByteArrayInputStream source = new ByteArrayInputStream(window.getBytes());
            if (skip > 0) {
                long skipped = WarmWindowSkipKt.skipForwardBytes(source, skip);
                if (skipped < skip) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        OkHttpVideoDataSource okHttpVideoDataSource = this;
                        source.close();
                        Result.m6717constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        Result.m6717constructorimpl(ResultKt.createFailure(th));
                    }
                    this.warmInput = null;
                } else {
                    this.warmInput = source;
                }
            } else {
                this.warmInput = source;
            }
            if (this.warmInput == null) {
                return openViaHttp(dataSpec, start, openAtMs);
            }
            if (dataSpec.length == -1) {
                j = window.getEndExclusive() - start;
            } else {
                j = dataSpec.length;
            }
            this.bytesRemaining = j;
            logOpen(dataSpec, start, "warm", this.bytesRemaining, 0L, SystemClock.elapsedRealtime() - openAtMs);
            StreamReadDiagnostics.INSTANCE.record(start, dataSpec.length, this.bytesRemaining, "warm");
            return this.bytesRemaining;
        }
        return openViaHttp(dataSpec, start, openAtMs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v7, types: [long, int] */
    private final long openViaHttp(DataSpec dataSpec, long start, long openAtMs) {
        Long valueOf;
        Uri target;
        Long end;
        long skipped;
        Uri uri = dataSpec.uri;
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        Uri target2 = uri;
        this.currentUri = target2;
        this.bytesServed = 0L;
        if (dataSpec.length == -1) {
            valueOf = null;
        } else {
            valueOf = Long.valueOf((dataSpec.length + start) - 1);
        }
        Long end2 = valueOf;
        Request.Builder builder = new Request.Builder();
        String uri2 = target2.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        Request.Builder $this$openViaHttp_u24lambda_u244 = builder.url(uri2).header(HttpHeaders.ACCEPT, "*/*").header(HttpHeaders.ACCEPT_ENCODING, "identity");
        for (Map.Entry element$iv : StreamRequestHeaders.INSTANCE.get().entrySet()) {
            String k = element$iv.getKey();
            String v = element$iv.getValue();
            $this$openViaHttp_u24lambda_u244.header(k, v);
        }
        Map httpRequestHeaders = dataSpec.httpRequestHeaders;
        Intrinsics.checkNotNullExpressionValue(httpRequestHeaders, "httpRequestHeaders");
        if (httpRequestHeaders != null) {
            for (Map.Entry<String, String> entry : httpRequestHeaders.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!StringsKt.equals(key, HttpHeaders.RANGE, true)) {
                    Intrinsics.checkNotNull(key);
                    Intrinsics.checkNotNull(value);
                    $this$openViaHttp_u24lambda_u244.header(key, value);
                }
            }
        }
        Map $this$forEach$iv = this.extraHeaders;
        int $i$f$forEach = 0;
        for (Map.Entry element$iv2 : $this$forEach$iv.entrySet()) {
            String k2 = element$iv2.getKey();
            Map $this$forEach$iv2 = $this$forEach$iv;
            String v2 = element$iv2.getValue();
            int $i$f$forEach2 = $i$f$forEach;
            if (!StringsKt.equals(k2, HttpHeaders.RANGE, true)) {
                $this$openViaHttp_u24lambda_u244.header(k2, v2);
            }
            $i$f$forEach = $i$f$forEach2;
            $this$forEach$iv = $this$forEach$iv2;
        }
        if (start != 0 || end2 != null) {
            $this$openViaHttp_u24lambda_u244.header(HttpHeaders.RANGE, (end2 != null ? new StringBuilder().append("bytes=").append(start).append("-").append(end2) : new StringBuilder().append("bytes=").append(start).append("-")).toString());
        }
        try {
            Response res = this.client.newCall($this$openViaHttp_u24lambda_u244.build()).execute();
            this.httpResponse = res;
            int code = res.code();
            if (code != 200 && code != 206) {
                res.close();
                this.httpResponse = null;
                closeQuietly();
                throw new HttpDataSource.HttpDataSourceException("Nguồn phát trả về lỗi HTTP " + code, dataSpec, 1, code);
            }
            boolean partial = code == 206;
            ResponseBody body = res.body();
            long declared = body != null ? body.getContentLength() : -1L;
            ResponseBody body2 = res.body();
            InputStream stream = body2 != null ? body2.byteStream() : null;
            if (stream == null) {
                closeQuietly();
                throw new DataSourceException("Nguồn phát trả về nội dung rỗng", 2008);
            }
            this.input = stream;
            long skipped2 = 0;
            if (partial || start <= 0) {
                target = target2;
                end = end2;
                skipped = 0;
            } else {
                byte[] scratch = new byte[Math.min(this.chunkSize, 65536)];
                while (true) {
                    if (skipped2 >= start) {
                        target = target2;
                        end = end2;
                        break;
                    }
                    target = target2;
                    end = end2;
                    int want = (int) Math.min(scratch.length, start - skipped2);
                    int n = stream.read(scratch, 0, want);
                    if (n <= 0) {
                        break;
                    }
                    skipped2 += n;
                    target2 = target;
                    end2 = end;
                }
                bytesTransferred((int) skipped2);
                skipped = skipped2;
            }
            this.bytesRemaining = HttpRangeLength.INSTANCE.remaining(code, Response.header$default(res, HttpHeaders.CONTENT_RANGE, null, 2, null), declared, start, skipped);
            long j = this.bytesRemaining;
            long j2 = skipped;
            logOpen(dataSpec, start, "http" + code, j2, j2, SystemClock.elapsedRealtime() - openAtMs);
            StreamReadDiagnostics streamReadDiagnostics = StreamReadDiagnostics.INSTANCE;
            long j3 = dataSpec.length;
            ?? r6 = this.bytesRemaining;
            streamReadDiagnostics.record(start, j3, r6, "http" + ((int) r6));
            return this.bytesRemaining;
        } catch (IOException e) {
            closeQuietly();
            HttpDataSource.HttpDataSourceException createForIOException = HttpDataSource.HttpDataSourceException.createForIOException(e, dataSpec, 1);
            Intrinsics.checkNotNullExpressionValue(createForIOException, "createForIOException(...)");
            throw createForIOException;
        }
    }

    private final void logOpen(DataSpec dataSpec, long start, String source, long resolved, long skipped, long elapsedMs) {
        String text = "open " + elapsedMs + "ms pos=" + start + " specLen=" + dataSpec.length + " -> " + resolved + " (" + source + (skipped > 0 ? ", skipped=" + skipped : "") + ") host=" + dataSpec.uri.getHost() + " flags=" + dataSpec.flags;
        FileLog.d$default(FileLog.INSTANCE, text, null, 2, null);
        Log.i(DEBUG_TAG, text);
    }

    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [okhttp3.Response, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v10 */
    private final InputStream reopenAfterIoError() {
        Object m6717constructorimpl;
        Unit unit;
        DataSpec spec = this.currentDataSpec;
        ?? r2 = 0;
        if (spec == null) {
            return null;
        }
        long resumeAt = this.openStart + this.bytesServed;
        DataSpec resumed = spec.subrange(resumeAt);
        Intrinsics.checkNotNullExpressionValue(resumed, "subrange(...)");
        long reopenAtMs = SystemClock.elapsedRealtime();
        int i = 0;
        while (i < 3) {
            int attempt = i;
            try {
                Result.Companion companion = Result.INSTANCE;
                OkHttpVideoDataSource $this$reopenAfterIoError_u24lambda_u247_u24lambda_u245 = this;
                InputStream inputStream = $this$reopenAfterIoError_u24lambda_u247_u24lambda_u245.input;
                if (inputStream != null) {
                    inputStream.close();
                }
                Response response = $this$reopenAfterIoError_u24lambda_u247_u24lambda_u245.httpResponse;
                if (response != null) {
                    response.close();
                    unit = Unit.INSTANCE;
                } else {
                    unit = r2;
                }
                Result.m6717constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            this.input = r2;
            this.httpResponse = r2;
            try {
                Result.Companion companion3 = Result.INSTANCE;
                OkHttpVideoDataSource $this$reopenAfterIoError_u24lambda_u247_u24lambda_u246 = this;
                m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf($this$reopenAfterIoError_u24lambda_u247_u24lambda_u246.openViaHttp(resumed, resumeAt, reopenAtMs)));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th2));
            }
            if (!Result.m6724isSuccessimpl(m6717constructorimpl)) {
                i++;
                r2 = 0;
            } else {
                FileLog.d$default(FileLog.INSTANCE, "Mất kết nối khi đọc, đã mở lại tại " + resumeAt + " (lần " + (attempt + 1) + ") sau " + this.bytesServed + " byte, mất " + (SystemClock.elapsedRealtime() - reopenAtMs) + " ms", null, 2, null);
                Log.i(DEBUG_TAG, "Mất kết nối khi đọc, đã mở lại tại " + resumeAt + " (lần " + (attempt + 1) + ") sau " + this.bytesServed + " byte");
                this.bytesServed = 0L;
                this.openStart = resumeAt;
                return this.input;
            }
        }
        return null;
    }

    private final void discardPreviousSource() {
        try {
            InputStream inputStream = this.warmInput;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
        }
        this.warmInput = null;
        try {
            InputStream inputStream2 = this.input;
            if (inputStream2 != null) {
                inputStream2.close();
            }
        } catch (IOException e2) {
        }
        this.input = null;
        Response response = this.httpResponse;
        if (response != null) {
            response.close();
        }
        this.httpResponse = null;
    }

    private final PlaybackWarmStore.Window warmWindow(DataSpec dataSpec, long start) {
        if (dataSpec.length == -1 || dataSpec.length <= 0) {
            return null;
        }
        String url = dataSpec.uri.toString();
        Intrinsics.checkNotNullExpressionValue(url, "toString(...)");
        PlaybackWarmStore.Window head = PlaybackWarmStore.INSTANCE.getHead(url);
        if (head != null && head.covers(start, dataSpec.length)) {
            return head;
        }
        PlaybackWarmStore.Window tail = PlaybackWarmStore.INSTANCE.getTail(url);
        if (tail == null || !tail.covers(start, dataSpec.length)) {
            return null;
        }
        return tail;
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] buffer, int offset, int length) {
        int read;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        InputStream warm = this.warmInput;
        if (warm != null) {
            int read2 = warm.read(buffer, offset, Math.min(length, this.chunkSize));
            if (read2 == -1) {
                return -1;
            }
            if (this.bytesRemaining != -1) {
                this.bytesRemaining -= read2;
            }
            bytesTransferred(read2);
            return read2;
        }
        if (length == 0) {
            return 0;
        }
        InputStream stream = this.input;
        if (stream == null) {
            throw new DataSourceException("Chưa mở DataSource, phải gọi open() trước", 2008);
        }
        try {
            read = stream.read(buffer, offset, Math.min(length, this.chunkSize));
        } catch (IOException io) {
            InputStream recovered = reopenAfterIoError();
            if (recovered == null) {
                DataSpec dataSpec = this.currentDataSpec;
                if (dataSpec == null) {
                    throw io;
                }
                HttpDataSource.HttpDataSourceException createForIOException = HttpDataSource.HttpDataSourceException.createForIOException(io, dataSpec, 2);
                Intrinsics.checkNotNullExpressionValue(createForIOException, "createForIOException(...)");
                throw createForIOException;
            }
            read = recovered.read(buffer, offset, Math.min(length, this.chunkSize));
        }
        if (read > 0) {
            this.bytesServed += read;
            if (length > 65536) {
                Log.w(DEBUG_TAG, "read() biet len=" + length + " > 64KiB, da doc " + read + " byte, tong da phuc vu=" + this.bytesServed);
            }
        }
        if (read == -1) {
            return -1;
        }
        if (this.bytesRemaining != -1) {
            this.bytesRemaining -= read;
        }
        bytesTransferred(read);
        return read;
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void setRequestProperty(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.extraHeaders.put(key, value);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearRequestProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.extraHeaders.remove(key);
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearAllRequestProperties() {
        this.extraHeaders.clear();
    }

    @Override // androidx.media3.datasource.DataSource
    /* renamed from: getUri, reason: from getter */
    public Uri getCurrentUri() {
        return this.currentUri;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        Headers headers;
        Map<String, List<String>> multimap;
        Response response = this.httpResponse;
        return (response == null || (headers = response.headers()) == null || (multimap = headers.toMultimap()) == null) ? MapsKt.emptyMap() : multimap;
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public int getResponseCode() {
        Response response = this.httpResponse;
        if (response != null) {
            return response.code();
        }
        return -1;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        try {
            InputStream inputStream = this.warmInput;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
        }
        this.warmInput = null;
        try {
            InputStream inputStream2 = this.input;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            this.input = null;
            try {
                Response response = this.httpResponse;
                if (response != null) {
                    response.close();
                }
            } catch (IOException e2) {
            } catch (Throwable th) {
                this.httpResponse = null;
                throw th;
            }
            this.httpResponse = null;
            if (!this.opened) {
                return;
            }
        } catch (IOException e3) {
            this.input = null;
            try {
                Response response2 = this.httpResponse;
                if (response2 != null) {
                    response2.close();
                }
            } catch (IOException e4) {
            } catch (Throwable th2) {
                this.httpResponse = null;
                throw th2;
            }
            this.httpResponse = null;
            if (!this.opened) {
                return;
            }
        } catch (Throwable th3) {
            this.input = null;
            try {
                Response response3 = this.httpResponse;
                if (response3 != null) {
                    response3.close();
                }
            } catch (IOException e5) {
            } catch (Throwable th4) {
                this.httpResponse = null;
                throw th4;
            }
            this.httpResponse = null;
            if (!this.opened) {
                throw th3;
            }
            this.opened = false;
            transferEnded();
            throw th3;
        }
        this.opened = false;
        transferEnded();
    }

    private final void closeQuietly() {
        try {
            InputStream inputStream = this.input;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
        } catch (Throwable th) {
            this.input = null;
            throw th;
        }
        this.input = null;
        try {
            Response response = this.httpResponse;
            if (response != null) {
                response.close();
            }
        } catch (IOException e2) {
        } catch (Throwable th2) {
            this.httpResponse = null;
            throw th2;
        }
        this.httpResponse = null;
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
    }

    /* compiled from: OkHttpVideoDataSource.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/ui/player/OkHttpVideoDataSource$BufferNetworkOptions;", "", "http2", "", "parallelConnections", "", "chunkSizeMb", "chunkCount", "(ZIII)V", "getChunkCount", "()I", "getChunkSizeMb", "getHttp2", "()Z", "getParallelConnections", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final /* data */ class BufferNetworkOptions {
        public static final int $stable = 0;
        private final int chunkCount;
        private final int chunkSizeMb;
        private final boolean http2;
        private final int parallelConnections;

        public BufferNetworkOptions() {
            this(false, 0, 0, 0, 15, null);
        }

        public static /* synthetic */ BufferNetworkOptions copy$default(BufferNetworkOptions bufferNetworkOptions, boolean z, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                z = bufferNetworkOptions.http2;
            }
            if ((i4 & 2) != 0) {
                i = bufferNetworkOptions.parallelConnections;
            }
            if ((i4 & 4) != 0) {
                i2 = bufferNetworkOptions.chunkSizeMb;
            }
            if ((i4 & 8) != 0) {
                i3 = bufferNetworkOptions.chunkCount;
            }
            return bufferNetworkOptions.copy(z, i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getHttp2() {
            return this.http2;
        }

        /* renamed from: component2, reason: from getter */
        public final int getParallelConnections() {
            return this.parallelConnections;
        }

        /* renamed from: component3, reason: from getter */
        public final int getChunkSizeMb() {
            return this.chunkSizeMb;
        }

        /* renamed from: component4, reason: from getter */
        public final int getChunkCount() {
            return this.chunkCount;
        }

        public final BufferNetworkOptions copy(boolean http2, int parallelConnections, int chunkSizeMb, int chunkCount) {
            return new BufferNetworkOptions(http2, parallelConnections, chunkSizeMb, chunkCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BufferNetworkOptions)) {
                return false;
            }
            BufferNetworkOptions bufferNetworkOptions = (BufferNetworkOptions) other;
            return this.http2 == bufferNetworkOptions.http2 && this.parallelConnections == bufferNetworkOptions.parallelConnections && this.chunkSizeMb == bufferNetworkOptions.chunkSizeMb && this.chunkCount == bufferNetworkOptions.chunkCount;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.http2) * 31) + Integer.hashCode(this.parallelConnections)) * 31) + Integer.hashCode(this.chunkSizeMb)) * 31) + Integer.hashCode(this.chunkCount);
        }

        public String toString() {
            return "BufferNetworkOptions(http2=" + this.http2 + ", parallelConnections=" + this.parallelConnections + ", chunkSizeMb=" + this.chunkSizeMb + ", chunkCount=" + this.chunkCount + ")";
        }

        public BufferNetworkOptions(boolean http2, int parallelConnections, int chunkSizeMb, int chunkCount) {
            this.http2 = http2;
            this.parallelConnections = parallelConnections;
            this.chunkSizeMb = chunkSizeMb;
            this.chunkCount = chunkCount;
        }

        public /* synthetic */ BufferNetworkOptions(boolean z, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? true : z, (i4 & 2) != 0 ? 1 : i, (i4 & 4) != 0 ? 1 : i2, (i4 & 8) != 0 ? 1 : i3);
        }

        public final boolean getHttp2() {
            return this.http2;
        }

        public final int getParallelConnections() {
            return this.parallelConnections;
        }

        public final int getChunkSizeMb() {
            return this.chunkSizeMb;
        }

        public final int getChunkCount() {
            return this.chunkCount;
        }
    }

    /* compiled from: OkHttpVideoDataSource.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0015\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0019J\u0015\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/streamsync/app/ui/player/OkHttpVideoDataSource$Companion;", "", "()V", "DEBUG_LOG", "", "DEBUG_TAG", "", "IDLE_POOL_MAX", "", "IDLE_POOL_MINUTES", "", "READ_RESUME_ATTEMPTS", "cached", "Lokhttp3/OkHttpClient;", "cachedSignature", "sharedPool", "Lokhttp3/ConnectionPool;", "sharedPoolParallel", "build", "app", "Landroid/content/Context;", "store", "Lcom/streamsync/app/settings/PlaybackSettingsStore;", "clientFor", "context", "clientFor$app_mobileDebug", "sharedConnectionPool", "parallel", "sharedConnectionPool$app_mobileDebug", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConnectionPool sharedConnectionPool$app_mobileDebug(int parallel) {
            int idle = RangesKt.coerceIn(parallel * 2, 8, 32);
            synchronized (this) {
                ConnectionPool current = OkHttpVideoDataSource.sharedPool;
                if (current != null && OkHttpVideoDataSource.sharedPoolParallel == parallel) {
                    return current;
                }
                ConnectionPool built = new ConnectionPool(idle, 3L, TimeUnit.MINUTES);
                Companion companion = OkHttpVideoDataSource.INSTANCE;
                OkHttpVideoDataSource.sharedPool = built;
                Companion companion2 = OkHttpVideoDataSource.INSTANCE;
                OkHttpVideoDataSource.sharedPoolParallel = parallel;
                return built;
            }
        }

        public final OkHttpClient clientFor$app_mobileDebug(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Context app = context.getApplicationContext();
            Intrinsics.checkNotNull(app);
            PlaybackSettingsStore store = new PlaybackSettingsStore(app);
            String signature = CollectionsKt.joinToString$default(CollectionsKt.listOf(Integer.valueOf(store.getParallelConnections()), Boolean.valueOf(store.isDpiBypassEnabled()), Integer.valueOf(store.getDpiFragmentSize()), Integer.valueOf(store.getDpiDelayMs()), store.getDnsProvider().name()), ",", null, null, 0, null, null, 62, null);
            synchronized (this) {
                if (OkHttpVideoDataSource.cached != null && Intrinsics.areEqual(OkHttpVideoDataSource.cachedSignature, signature)) {
                    OkHttpClient okHttpClient = OkHttpVideoDataSource.cached;
                    Intrinsics.checkNotNull(okHttpClient);
                    return okHttpClient;
                }
                OkHttpClient built = OkHttpVideoDataSource.INSTANCE.build(app, store);
                Companion companion = OkHttpVideoDataSource.INSTANCE;
                OkHttpVideoDataSource.cachedSignature = signature;
                Companion companion2 = OkHttpVideoDataSource.INSTANCE;
                OkHttpVideoDataSource.cached = built;
                return built;
            }
        }

        private final OkHttpClient build(Context app, PlaybackSettingsStore store) {
            int parallel = store.getParallelConnections();
            OkHttpClient.Builder $this$build_u24lambda_u242 = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS);
            NetTuning.INSTANCE.apply(app, $this$build_u24lambda_u242);
            if (parallel > 1) {
                Dispatcher $this$build_u24lambda_u244_u24lambda_u243 = new Dispatcher();
                $this$build_u24lambda_u244_u24lambda_u243.setMaxRequests(parallel * 2);
                $this$build_u24lambda_u244_u24lambda_u243.setMaxRequestsPerHost(parallel);
                $this$build_u24lambda_u242.dispatcher($this$build_u24lambda_u244_u24lambda_u243);
            }
            OkHttpClient.Builder builder = $this$build_u24lambda_u242.connectionPool(sharedConnectionPool$app_mobileDebug(parallel));
            if (store.isHttp2Enabled()) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Companion companion2 = this;
                    Result.m6717constructorimpl(builder.connectionSpecs(CollectionsKt.listOf((Object[]) new ConnectionSpec[]{new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).allEnabledCipherSuites().build(), ConnectionSpec.CLEARTEXT})));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    Result.m6717constructorimpl(ResultKt.createFailure(th));
                }
            } else {
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    Companion companion5 = this;
                    Result.m6717constructorimpl(builder.connectionSpecs(CollectionsKt.listOf((Object[]) new ConnectionSpec[]{new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS).tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1).allEnabledCipherSuites().build(), ConnectionSpec.CLEARTEXT})));
                } catch (Throwable th2) {
                    Result.Companion companion6 = Result.INSTANCE;
                    Result.m6717constructorimpl(ResultKt.createFailure(th2));
                }
            }
            OkHttpClient it = builder.build();
            Companion companion7 = OkHttpVideoDataSource.INSTANCE;
            OkHttpVideoDataSource.cached = it;
            return it;
        }
    }
}
