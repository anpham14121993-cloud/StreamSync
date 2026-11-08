package com.streamsync.app.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.streamsync.app.settings.BufferCeiling;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: FileLog.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000bJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u001a\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0012\u0010\u001c\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\b\u0010!\u001a\u00020\u0013H\u0002J\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u000bJ\u0006\u0010$\u001a\u00020\u0004J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\b\u0010&\u001a\u00020\u0004H\u0002J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u001a\u0010(\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0010\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u0004H\u0002J \u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/streamsync/app/util/FileLog;", "", "()V", "FILE_NAME", "", "MAX_BYTES", "", "PROBE", "", "TAG", "appContext", "Landroid/content/Context;", "fallbackFile", "Ljava/io/File;", "lock", "mediaUri", "Landroid/net/Uri;", "sinkLabel", "sinkReady", "", "appDirPath", "ctx", "cleanPendingLeaves", "", "d", "message", "throwable", "", "describe", "t", "ensureFileSinkLocked", "ensureSink", "fallbackToAppDir", "fallbackToPublicDir", "init", "context", "location", "purgeOldLogs", "stamp", "tryMediaStore", "w", "write", "line", "writeToMediaStore", "resolver", "Landroid/content/ContentResolver;", "uri", "bytes", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class FileLog {
    public static final int $stable;
    public static final String FILE_NAME = "StreamSync-log.txt";
    public static final FileLog INSTANCE = new FileLog();
    private static final long MAX_BYTES = 524288;
    private static final byte[] PROBE;
    private static final String TAG = "StreamSync";
    private static Context appContext;
    private static File fallbackFile;
    private static final Object lock;
    private static Uri mediaUri;
    private static volatile String sinkLabel;
    private static volatile boolean sinkReady;

    private FileLog() {
    }

    static {
        byte[] bytes = "# StreamSync log — tao luc mo app\n".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        PROBE = bytes;
        lock = new Object();
        sinkLabel = "Download/StreamSync-log.txt";
        $stable = 8;
    }

    public final String location() {
        return sinkLabel;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        appContext = context.getApplicationContext();
    }

    private final void purgeOldLogs(Context ctx) {
        Throwable th;
        FileLog $this$purgeOldLogs_u24lambda_u243;
        try {
            Result.Companion companion = Result.INSTANCE;
            FileLog $this$purgeOldLogs_u24lambda_u2432 = this;
            Cursor c = ctx.getContentResolver().query(MediaStore.Downloads.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_display_name LIKE ?", new String[]{"StreamSync-log.txt%"}, null);
            if (c != null) {
                Intrinsics.checkNotNull(c);
                Cursor cursor = c;
                try {
                    Cursor it = cursor;
                    int i = it.getColumnIndexOrThrow("_id");
                    ArrayList ids = new ArrayList();
                    while (it.moveToNext()) {
                        try {
                            ids.add(Long.valueOf(it.getLong(i)));
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (Throwable th3) {
                                CloseableKt.closeFinally(cursor, th);
                                throw th3;
                            }
                        }
                    }
                    ArrayList $this$forEach$iv = ids;
                    for (Object element$iv : $this$forEach$iv) {
                        long id = ((Number) element$iv).longValue();
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            $this$purgeOldLogs_u24lambda_u243 = $this$purgeOldLogs_u24lambda_u2432;
                            try {
                                Result.m6717constructorimpl(Integer.valueOf(ctx.getContentResolver().delete(MediaStore.Downloads.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build(), null, null)));
                            } catch (Throwable th4) {
                                th = th4;
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    Result.m6717constructorimpl(ResultKt.createFailure(th));
                                    $this$purgeOldLogs_u24lambda_u2432 = $this$purgeOldLogs_u24lambda_u243;
                                } catch (Throwable th5) {
                                    th = th5;
                                    throw th;
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            $this$purgeOldLogs_u24lambda_u243 = $this$purgeOldLogs_u24lambda_u2432;
                        }
                        $this$purgeOldLogs_u24lambda_u2432 = $this$purgeOldLogs_u24lambda_u243;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } catch (Throwable th7) {
                    th = th7;
                }
            }
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th8) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th8));
        }
    }

    private final void cleanPendingLeaves(Context ctx) {
        try {
            Result.Companion companion = Result.INSTANCE;
            FileLog fileLog = this;
            String[] col = {"_id"};
            Cursor query = ctx.getContentResolver().query(MediaStore.Downloads.EXTERNAL_CONTENT_URI, col, "_display_name LIKE ? AND is_pending = 1", new String[]{"StreamSync-log.txt%"}, null);
            Unit unit = null;
            if (query != null) {
                Cursor cursor = query;
                try {
                    Cursor c = cursor;
                    int i = c.getColumnIndexOrThrow("_id");
                    while (c.moveToNext()) {
                        long id = c.getLong(i);
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            Result.m6717constructorimpl(Integer.valueOf(ctx.getContentResolver().delete(MediaStore.Downloads.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build(), null, null)));
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            Result.m6717constructorimpl(ResultKt.createFailure(th));
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                    unit = Unit.INSTANCE;
                } finally {
                }
            }
            Result.m6717constructorimpl(unit);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th2));
        }
    }

    private final void ensureSink() {
        if (sinkReady) {
            return;
        }
        synchronized (lock) {
            if (sinkReady) {
                return;
            }
            Context ctx = appContext;
            if (ctx != null) {
                INSTANCE.cleanPendingLeaves(ctx);
                INSTANCE.purgeOldLogs(ctx);
            }
            if (ctx == null) {
                INSTANCE.fallbackToPublicDir();
            } else {
                boolean ok = INSTANCE.tryMediaStore(ctx);
                if (!ok && !INSTANCE.fallbackToAppDir(ctx) && !INSTANCE.fallbackToPublicDir()) {
                    sinkLabel = "KHONG GHI DUOC LOG (khong co quyen luu tru)";
                }
            }
            sinkReady = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final boolean writeToMediaStore(ContentResolver resolver, Uri uri, byte[] bytes) {
        Object m6717constructorimpl;
        boolean z;
        try {
            Result.Companion companion = Result.INSTANCE;
            FileLog fileLog = this;
            OutputStream out = resolver.openOutputStream(uri, "wa");
            if (out != null) {
                Intrinsics.checkNotNull(out);
                OutputStream outputStream = out;
                try {
                    OutputStream stream = outputStream;
                    stream.write(bytes);
                    stream.flush();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream, null);
                    z = true;
                } finally {
                }
            } else {
                z = false;
            }
            m6717constructorimpl = Result.m6717constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = false;
        }
        return ((Boolean) m6717constructorimpl).booleanValue();
    }

    private final boolean tryMediaStore(Context ctx) {
        Object m6717constructorimpl;
        boolean z;
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            FileLog $this$tryMediaStore_u24lambda_u2412 = this;
            ContentResolver resolver = ctx.getContentResolver();
            ContentValues values = new ContentValues();
            values.put("_display_name", FILE_NAME);
            values.put("mime_type", "text/plain");
            values.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
            Uri uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                Intrinsics.checkNotNull(uri);
                Intrinsics.checkNotNull(resolver);
                if (!$this$tryMediaStore_u24lambda_u2412.writeToMediaStore(resolver, uri, PROBE)) {
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        Result.m6717constructorimpl(Integer.valueOf(resolver.delete(uri, null, null)));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        Result.m6717constructorimpl(ResultKt.createFailure(th));
                    }
                    z = false;
                } else {
                    mediaUri = uri;
                    sinkLabel = "Download/StreamSync-log.txt";
                    z = true;
                }
            } else {
                z = false;
            }
            m6717constructorimpl = Result.m6717constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = false;
        }
        return ((Boolean) m6717constructorimpl).booleanValue();
    }

    public final String appDirPath(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        File externalFilesDir = ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        String str = (externalFilesDir != null ? externalFilesDir.getAbsolutePath() : null) + "/StreamSync-log.txt";
        return str == null ? FILE_NAME : str;
    }

    private final boolean fallbackToAppDir(Context ctx) {
        Object m6717constructorimpl;
        File dir;
        try {
            Result.Companion companion = Result.INSTANCE;
            FileLog fileLog = this;
            dir = ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (dir == null) {
            return false;
        }
        Intrinsics.checkNotNull(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(dir, FILE_NAME);
        f.createNewFile();
        fallbackFile = f;
        sinkLabel = String.valueOf(f.getAbsolutePath());
        m6717constructorimpl = Result.m6717constructorimpl(true);
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = false;
        }
        return ((Boolean) m6717constructorimpl).booleanValue();
    }

    private final boolean fallbackToPublicDir() {
        Object m6717constructorimpl;
        File dir;
        try {
            Result.Companion companion = Result.INSTANCE;
            FileLog fileLog = this;
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (!dir.canWrite()) {
            return false;
        }
        File f = new File(dir, FILE_NAME);
        f.createNewFile();
        fallbackFile = f;
        sinkLabel = "Download/StreamSync-log.txt";
        m6717constructorimpl = Result.m6717constructorimpl(true);
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = false;
        }
        return ((Boolean) m6717constructorimpl).booleanValue();
    }

    public static /* synthetic */ void d$default(FileLog fileLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        fileLog.d(str, th);
    }

    public final void d(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.d(TAG, message, throwable);
        write(stamp() + message + describe(throwable));
    }

    public static /* synthetic */ void w$default(FileLog fileLog, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        fileLog.w(str, th);
    }

    public final void w(String message, Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.w(TAG, message, throwable);
        write(stamp() + "[W] " + message + describe(throwable));
    }

    private final String stamp() {
        return new SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(new Date()) + " ";
    }

    private final String describe(Throwable t) {
        if (t == null) {
            return "";
        }
        String msg = t.getMessage();
        String str = msg;
        if (str == null || StringsKt.isBlank(str)) {
            return " | " + t.getClass().getSimpleName();
        }
        return " | " + t.getClass().getSimpleName() + ": " + msg;
    }

    private final void write(String line) {
        Object m6717constructorimpl;
        ensureSink();
        byte[] bytes = (line + "\n").getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        synchronized (lock) {
            Context ctx = appContext;
            Uri uri = mediaUri;
            if (uri != null && ctx != null) {
                FileLog fileLog = INSTANCE;
                ContentResolver contentResolver = ctx.getContentResolver();
                Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
                if (fileLog.writeToMediaStore(contentResolver, uri, bytes)) {
                    return;
                }
                mediaUri = null;
                sinkReady = false;
            }
            INSTANCE.ensureFileSinkLocked();
            File f = fallbackFile;
            if (f != null) {
                FileLog fileLog2 = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    if (f.length() > 524288) {
                        f.delete();
                    }
                    FilesKt.appendBytes(f, bytes);
                    m6717constructorimpl = Result.m6717constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
                }
                Throwable it = Result.m6720exceptionOrNullimpl(m6717constructorimpl);
                if (it != null) {
                    Log.w(TAG, "ghi log that bai: " + it.getMessage());
                }
                Result.m6716boximpl(m6717constructorimpl);
            } else {
                Integer.valueOf(Log.w(TAG, "khong co noi ghi log"));
            }
        }
    }

    private final void ensureFileSinkLocked() {
        Context ctx;
        if (fallbackFile == null && (ctx = appContext) != null && !fallbackToAppDir(ctx)) {
            fallbackToPublicDir();
        }
    }
}
