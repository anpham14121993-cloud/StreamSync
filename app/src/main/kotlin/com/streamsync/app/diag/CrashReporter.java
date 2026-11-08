package com.streamsync.app.diag;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.FileLog;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: CrashReporter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\u000e\u001a\u00020\u0004H\u0002J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/diag/CrashReporter;", "", "()V", "FILE_NAME", "", "FIRST_FRAMES", "", "MAX_FRAMES", "hasReport", "", "context", "Landroid/content/Context;", "install", "", "memorySnapshot", "render", "thread", "Ljava/lang/Thread;", "throwable", "", "stackTraceOf", "takeReport", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes26.dex */
public final class CrashReporter {
    public static final int $stable = 0;
    private static final String FILE_NAME = "last_crash.txt";
    private static final int FIRST_FRAMES = 20;
    public static final CrashReporter INSTANCE = new CrashReporter();
    private static final int MAX_FRAMES = 40;

    private CrashReporter() {
    }

    public final void install(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context appContext = context.getApplicationContext();
        final Thread.UncaughtExceptionHandler previous = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.streamsync.app.diag.CrashReporter$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                CrashReporter.install$lambda$2(appContext, previous, thread, th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void install$lambda$2(Context $appContext, Thread.UncaughtExceptionHandler $previous, Thread thread, Throwable throwable) {
        boolean outOfMemory = throwable instanceof OutOfMemoryError;
        CrashReporter $this$install_u24lambda_u242_u24lambda_u240 = INSTANCE;
        Unit unit = null;
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNull(thread);
            Intrinsics.checkNotNull(throwable);
            String text = $this$install_u24lambda_u242_u24lambda_u240.render(thread, throwable);
            File fileStreamPath = $appContext.getFileStreamPath(FILE_NAME);
            Intrinsics.checkNotNullExpressionValue(fileStreamPath, "getFileStreamPath(...)");
            FilesKt.writeText$default(fileStreamPath, text, null, 2, null);
            if (!outOfMemory) {
                FileLog.w$default(FileLog.INSTANCE, "\n=== APP BI LOI KHONG XU LY ===\n" + text, null, 2, null);
            } else {
                FileLog.w$default(FileLog.INSTANCE, "\n=== APP CHET VI HET BO NHO ===\n" + $this$install_u24lambda_u242_u24lambda_u240.memorySnapshot() + "\n" + text, null, 2, null);
            }
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        CrashReporter crashReporter = INSTANCE;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            if ($previous != null) {
                $previous.uncaughtException(thread, throwable);
                unit = Unit.INSTANCE;
            }
            Result.m6717constructorimpl(unit);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th2));
        }
    }

    private final String memorySnapshot() {
        Runtime rt = Runtime.getRuntime();
        StringBuilder $this$memorySnapshot_u24lambda_u243 = new StringBuilder();
        $this$memorySnapshot_u24lambda_u243.append("heap toi da: ").append(memorySnapshot$mb(rt.maxMemory())).append(" MB\n");
        $this$memorySnapshot_u24lambda_u243.append("heap dang cap: ").append(memorySnapshot$mb(rt.totalMemory())).append(" MB\n");
        $this$memorySnapshot_u24lambda_u243.append("heap con trong: ").append(memorySnapshot$mb(rt.freeMemory())).append(" MB\n");
        $this$memorySnapshot_u24lambda_u243.append("so luong co the giu: ").append(rt.availableProcessors()).append(" loi\n");
        String sb = $this$memorySnapshot_u24lambda_u243.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    private static final long memorySnapshot$mb(long bytes) {
        return bytes / 1048576;
    }

    public final boolean hasReport(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File it = context.getFileStreamPath(FILE_NAME);
        return it.exists() && it.length() > 0;
    }

    public final String takeReport(Context context) {
        Object m6717constructorimpl;
        File f;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            CrashReporter crashReporter = this;
            f = context.getFileStreamPath(FILE_NAME);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (f.exists() && f.length() != 0) {
            Intrinsics.checkNotNull(f);
            String text = FilesKt.readText$default(f, null, 1, null);
            f.delete();
            m6717constructorimpl = Result.m6717constructorimpl(text);
            return (String) (Result.m6723isFailureimpl(m6717constructorimpl) ? null : m6717constructorimpl);
        }
        return null;
    }

    private final String render(Thread thread, Throwable throwable) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
        StringBuilder $this$render_u24lambda_u246 = new StringBuilder();
        $this$render_u24lambda_u246.append("Thời điểm: ").append(time).append('\n');
        $this$render_u24lambda_u246.append("Thread: ").append(thread.getName()).append('\n');
        $this$render_u24lambda_u246.append("PID: ").append(Process.myPid()).append('\n');
        $this$render_u24lambda_u246.append("Android: ").append(Build.VERSION.RELEASE);
        $this$render_u24lambda_u246.append(" (API ").append(Build.VERSION.SDK_INT).append(")\n");
        StringBuilder append = $this$render_u24lambda_u246.append("ABI: ");
        String[] SUPPORTED_ABIS = Build.SUPPORTED_ABIS;
        Intrinsics.checkNotNullExpressionValue(SUPPORTED_ABIS, "SUPPORTED_ABIS");
        append.append(ArraysKt.joinToString$default(SUPPORTED_ABIS, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null)).append('\n');
        if (throwable instanceof OutOfMemoryError) {
            $this$render_u24lambda_u246.append('\n').append(INSTANCE.memorySnapshot());
        }
        $this$render_u24lambda_u246.append('\n').append(INSTANCE.stackTraceOf(throwable));
        String sb = $this$render_u24lambda_u246.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    private final String stackTraceOf(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);
        try {
            PrintWriter writer = printWriter;
            try {
                Throwable root = (Throwable) SequencesKt.last(SequencesKt.generateSequence(throwable, new Function1<Throwable, Throwable>() { // from class: com.streamsync.app.diag.CrashReporter$stackTraceOf$1$root$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Throwable invoke(Throwable it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return it.getCause();
                    }
                }));
                writer.println(root.getClass().getName() + ": " + root.getMessage());
                StackTraceElement[] stackTrace = root.getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
                Iterable $this$forEach$iv = ArraysKt.take(stackTrace, 40);
                for (Object element$iv : $this$forEach$iv) {
                    StackTraceElement it = (StackTraceElement) element$iv;
                    writer.println("\tat " + it);
                }
                int skipped = root.getStackTrace().length - 40;
                if (skipped > 0) {
                    writer.println("\t... (" + skipped + " frame bi cat)");
                }
                StackTraceElement[] stackTrace2 = throwable.getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "getStackTrace(...)");
                Iterable $this$forEach$iv2 = ArraysKt.take(stackTrace2, 20);
                for (Object element$iv2 : $this$forEach$iv2) {
                    StackTraceElement it2 = (StackTraceElement) element$iv2;
                    writer.println("\tat " + it2);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(printWriter, null);
                String stringWriter = sw.toString();
                Intrinsics.checkNotNullExpressionValue(stringWriter, "toString(...)");
                return stringWriter;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(printWriter, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
