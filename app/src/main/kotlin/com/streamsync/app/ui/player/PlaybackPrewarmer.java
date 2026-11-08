package com.streamsync.app.ui.player;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import com.google.common.net.HttpHeaders;
import com.streamsync.app.data.addon.AddonClientKt;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.LastStreamStore;
import com.streamsync.app.ui.player.OkHttpVideoDataSource;
import com.streamsync.app.ui.player.PlaybackWarmStore;
import com.streamsync.app.util.FileLog;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: PlaybackPrewarmer.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010\u0010J<\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0016H\u0007J6\u0010\u0017\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00042\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/ui/player/PlaybackPrewarmer;", "", "()V", "DEDUP_WINDOW_MS", "", "lastAtMs", "lastUrl", "", "rememberStreamForThroughputProbe", "", "context", "Landroid/content/Context;", "source", "Lcom/streamsync/app/data/model/StreamSource;", "totalOf", "contentRange", "(Ljava/lang/String;)Ljava/lang/Long;", "warm", "url", "withTail", "", LastStreamStore.KEY_HEADERS, "", "warmTail", "totalLength", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlaybackPrewarmer {
    private static final long DEDUP_WINDOW_MS = 60000;
    private static volatile long lastAtMs;
    private static volatile String lastUrl;
    public static final PlaybackPrewarmer INSTANCE = new PlaybackPrewarmer();
    public static final int $stable = 8;

    @JvmStatic
    public static final void warm(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        warm$default(context, str, false, null, 12, null);
    }

    @JvmStatic
    public static final void warm(Context context, String str, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        warm$default(context, str, z, null, 8, null);
    }

    private PlaybackPrewarmer() {
    }

    @JvmStatic
    public static final void rememberStreamForThroughputProbe(Context context, StreamSource source) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(source, "source");
        PlaybackPrewarmer playbackPrewarmer = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            new LastStreamStore(context).remember(source.getStreamUrl(), source.getRequestHeaders(), 0L, 0L);
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static /* synthetic */ void warm$default(Context context, String str, boolean z, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            map = null;
        }
        warm(context, str, z, map);
    }

    @JvmStatic
    public static final void warm(final Context context, String url, final boolean withTail, Map<String, String> headers) {
        Object m6717constructorimpl;
        Map it;
        Object m6717constructorimpl2;
        Intrinsics.checkNotNullParameter(context, "context");
        String obj = url != null ? StringsKt.trim((CharSequence) url).toString() : null;
        if (obj == null) {
            obj = "";
        }
        final String target = obj;
        if (StringsKt.startsWith(target, "http://", true) || StringsKt.startsWith(target, "https://", true)) {
            PlaybackPrewarmer playbackPrewarmer = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(Uri.parse(target));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                m6717constructorimpl = null;
            }
            if (((Uri) m6717constructorimpl) == null || StringsKt.contains((CharSequence) target, (CharSequence) ".m3u8", true) || StringsKt.contains((CharSequence) target, (CharSequence) ".mpd", true)) {
                return;
            }
            long now = SystemClock.elapsedRealtime();
            if (Intrinsics.areEqual(target, lastUrl)) {
                long j = now - lastAtMs;
                if (0 <= j && j < 60000) {
                    return;
                }
            }
            if (PlaybackWarmStore.INSTANCE.hasHead(target)) {
                return;
            }
            lastUrl = target;
            lastAtMs = now;
            if (headers == null || (it = StreamRequestHeaders.INSTANCE.sanitize(headers)) == null) {
                it = StreamRequestHeaders.INSTANCE.get();
            }
            final Map reqHeaders = it;
            PlaybackPrewarmer $this$warm_u24lambda_u245 = INSTANCE;
            try {
                Result.Companion companion3 = Result.INSTANCE;
                Request.Builder $this$warm_u24lambda_u245_u24lambda_u244 = new Request.Builder().url(target).header(HttpHeaders.ACCEPT, "*/*").header(HttpHeaders.ACCEPT_ENCODING, "identity");
                for (Map.Entry element$iv : reqHeaders.entrySet()) {
                    String k = element$iv.getKey();
                    String v = element$iv.getValue();
                    $this$warm_u24lambda_u245_u24lambda_u244.header(k, v);
                    $this$warm_u24lambda_u245 = $this$warm_u24lambda_u245;
                }
                m6717constructorimpl2 = Result.m6717constructorimpl($this$warm_u24lambda_u245_u24lambda_u244.header(HttpHeaders.RANGE, "bytes=0-262143").build());
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                m6717constructorimpl2 = Result.m6717constructorimpl(ResultKt.createFailure(th2));
            }
            Request request = (Request) (Result.m6723isFailureimpl(m6717constructorimpl2) ? null : m6717constructorimpl2);
            if (request == null) {
                return;
            }
            final PlaybackPrewarmer $this$warm_u24lambda_u246 = INSTANCE;
            try {
                Result.Companion companion5 = Result.INSTANCE;
                OkHttpVideoDataSource.Companion companion6 = OkHttpVideoDataSource.INSTANCE;
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                companion6.clientFor$app_mobileDebug(applicationContext).newCall(request).enqueue(new Callback() { // from class: com.streamsync.app.ui.player.PlaybackPrewarmer$warm$1$1
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException e) {
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(e, "e");
                        FileLog.INSTANCE.w("ham dau that bai: " + target, e);
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        Object m6717constructorimpl3;
                        Throwable th3;
                        Long l;
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(response, "response");
                        Response response2 = response;
                        Map<String, String> map = reqHeaders;
                        PlaybackPrewarmer playbackPrewarmer2 = $this$warm_u24lambda_u246;
                        String str = target;
                        boolean z = withTail;
                        Context context2 = context;
                        try {
                            Response res = response2;
                            if (res.code() != 206) {
                                FileLog.w$default(FileLog.INSTANCE, "ham dau vo nghia: ma " + res.code() + ", " + map.keySet() + " (thieu header se ra 403)", null, 2, null);
                                th3 = null;
                            } else {
                                ResponseBody body = res.body();
                                if (body == null) {
                                    th3 = null;
                                } else {
                                    try {
                                        Result.Companion companion7 = Result.INSTANCE;
                                        PlaybackPrewarmer$warm$1$1 playbackPrewarmer$warm$1$1 = this;
                                        m6717constructorimpl3 = Result.m6717constructorimpl(body.bytes());
                                    } catch (Throwable th4) {
                                        Result.Companion companion8 = Result.INSTANCE;
                                        m6717constructorimpl3 = Result.m6717constructorimpl(ResultKt.createFailure(th4));
                                    }
                                    if (Result.m6723isFailureimpl(m6717constructorimpl3)) {
                                        m6717constructorimpl3 = null;
                                    }
                                    byte[] bytes = (byte[]) m6717constructorimpl3;
                                    if (bytes == null) {
                                        th3 = null;
                                    } else {
                                        if (bytes.length == 0) {
                                            th3 = null;
                                        } else if (bytes.length > 262144) {
                                            th3 = null;
                                        } else {
                                            l = playbackPrewarmer2.totalOf(Response.header$default(res, HttpHeaders.CONTENT_RANGE, null, 2, null));
                                            if (l != null) {
                                                long total = l.longValue();
                                                th3 = null;
                                                PlaybackWarmStore.INSTANCE.putHead(new PlaybackWarmStore.Window(str, 0L, total, bytes, SystemClock.uptimeMillis(), AddonClientKt.normalizeMimeType(Response.header$default(res, HttpHeaders.CONTENT_TYPE, null, 2, null))));
                                                FileLog fileLog = FileLog.INSTANCE;
                                                int length = bytes.length / 1024;
                                                long j2 = total / 1048576;
                                                String normalizeMimeType = AddonClientKt.normalizeMimeType(Response.header$default(res, HttpHeaders.CONTENT_TYPE, null, 2, null));
                                                if (normalizeMimeType == null) {
                                                    normalizeMimeType = "?";
                                                }
                                                FileLog.d$default(fileLog, "ham dau xong: " + length + " KiB, tong " + j2 + " MB, " + normalizeMimeType, null, 2, null);
                                                if (z) {
                                                    playbackPrewarmer2.warmTail(context2, str, total, map);
                                                }
                                            } else {
                                                th3 = null;
                                            }
                                        }
                                    }
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(response2, th3);
                        } finally {
                        }
                    }
                });
                Result.m6717constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion7 = Result.INSTANCE;
                Result.m6717constructorimpl(ResultKt.createFailure(th3));
            }
        }
    }

    public static /* synthetic */ void warmTail$default(PlaybackPrewarmer playbackPrewarmer, Context context, String str, long j, Map map, int i, Object obj) {
        Map map2;
        if ((i & 8) == 0) {
            map2 = map;
        } else {
            map2 = null;
        }
        playbackPrewarmer.warmTail(context, str, j, map2);
    }

    public final void warmTail(Context context, final String url, final long totalLength, Map<String, String> headers) {
        Map it;
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        final long start = totalLength - 4194304;
        if (start > 262144 && !PlaybackWarmStore.INSTANCE.hasTail(url)) {
            if (headers == null || (it = StreamRequestHeaders.INSTANCE.sanitize(headers)) == null) {
                it = StreamRequestHeaders.INSTANCE.get();
            }
            Map reqHeaders = it;
            try {
                Result.Companion companion = Result.INSTANCE;
                PlaybackPrewarmer $this$warmTail_u24lambda_u2410 = this;
                Request.Builder $this$warmTail_u24lambda_u2410_u24lambda_u249 = new Request.Builder().url(url).header(HttpHeaders.ACCEPT, "*/*").header(HttpHeaders.ACCEPT_ENCODING, "identity");
                for (Map.Entry element$iv : reqHeaders.entrySet()) {
                    String k = element$iv.getKey();
                    String v = element$iv.getValue();
                    $this$warmTail_u24lambda_u2410_u24lambda_u249.header(k, v);
                    $this$warmTail_u24lambda_u2410 = $this$warmTail_u24lambda_u2410;
                }
                m6717constructorimpl = Result.m6717constructorimpl($this$warmTail_u24lambda_u2410_u24lambda_u249.header(HttpHeaders.RANGE, "bytes=" + start + "-" + (totalLength - 1)).build());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                m6717constructorimpl = null;
            }
            Request request = (Request) m6717constructorimpl;
            if (request == null) {
                return;
            }
            try {
                Result.Companion companion3 = Result.INSTANCE;
                PlaybackPrewarmer playbackPrewarmer = this;
                OkHttpVideoDataSource.Companion companion4 = OkHttpVideoDataSource.INSTANCE;
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                companion4.clientFor$app_mobileDebug(applicationContext).newCall(request).enqueue(new Callback() { // from class: com.streamsync.app.ui.player.PlaybackPrewarmer$warmTail$1$1
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException e) {
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(e, "e");
                        FileLog.INSTANCE.w("ham duoi that bai: " + url, e);
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        Response response2;
                        Throwable th2;
                        Response response3;
                        Throwable th3;
                        Object m6717constructorimpl2;
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(response, "response");
                        Response response4 = response;
                        String str = url;
                        long j = start;
                        long j2 = totalLength;
                        try {
                            Response res = response4;
                            if (res.code() != 206) {
                                try {
                                    FileLog.w$default(FileLog.INSTANCE, "ham duoi vo nghia: ma " + res.code(), null, 2, null);
                                    response3 = response4;
                                    th3 = null;
                                } catch (Throwable th4) {
                                    th2 = th4;
                                    response2 = response4;
                                    try {
                                        throw th2;
                                    } catch (Throwable th5) {
                                        CloseableKt.closeFinally(response2, th2);
                                        throw th5;
                                    }
                                }
                            } else {
                                try {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    PlaybackPrewarmer$warmTail$1$1 playbackPrewarmer$warmTail$1$1 = this;
                                    ResponseBody body = res.body();
                                    m6717constructorimpl2 = Result.m6717constructorimpl(body != null ? body.bytes() : null);
                                } catch (Throwable th6) {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    m6717constructorimpl2 = Result.m6717constructorimpl(ResultKt.createFailure(th6));
                                }
                                if (Result.m6723isFailureimpl(m6717constructorimpl2)) {
                                    m6717constructorimpl2 = null;
                                }
                                byte[] bytes = (byte[]) m6717constructorimpl2;
                                if (bytes == null) {
                                    response3 = response4;
                                    th3 = null;
                                } else if (bytes.length == 4194304) {
                                    try {
                                        th3 = null;
                                        response3 = response4;
                                        try {
                                            PlaybackWarmStore.INSTANCE.putTail(new PlaybackWarmStore.Window(str, j, j2, bytes, SystemClock.uptimeMillis(), AddonClientKt.normalizeMimeType(Response.header$default(res, HttpHeaders.CONTENT_TYPE, null, 2, null))));
                                            FileLog.d$default(FileLog.INSTANCE, "ham duoi xong: " + j + ".." + (j2 - 1), null, 2, null);
                                        } catch (Throwable th7) {
                                            th = th7;
                                            th2 = th;
                                            response2 = response3;
                                            throw th2;
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        response3 = response4;
                                    }
                                } else {
                                    response3 = response4;
                                    th3 = null;
                                }
                            }
                            try {
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(response3, th3);
                            } catch (Throwable th9) {
                                th = th9;
                                response2 = response3;
                                th2 = th;
                                throw th2;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                            response2 = response4;
                            th2 = th;
                            throw th2;
                        }
                    }
                });
                Result.m6717constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion5 = Result.INSTANCE;
                Result.m6717constructorimpl(ResultKt.createFailure(th2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Long totalOf(String contentRange) {
        int slash;
        if (contentRange == null || (slash = StringsKt.lastIndexOf$default((CharSequence) contentRange, '/', 0, false, 6, (Object) null)) < 0) {
            return null;
        }
        String substring = contentRange.substring(slash + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        Long longOrNull = StringsKt.toLongOrNull(StringsKt.trim((CharSequence) substring).toString());
        if (longOrNull == null) {
            return null;
        }
        long total = longOrNull.longValue();
        if (total > 0) {
            return Long.valueOf(total);
        }
        return null;
    }
}
