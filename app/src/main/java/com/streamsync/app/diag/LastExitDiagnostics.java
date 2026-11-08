package com.streamsync.app.diag;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import com.streamsync.app.settings.BufferCeiling;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: LastExitDiagnostics.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/diag/LastExitDiagnostics;", "", "()V", "ago", "", "timestamp", "", "importanceName", "importance", "", "mb", "bytes", "nativeTrace", "exit", "Landroid/app/ApplicationExitInfo;", "signalName", "sig", "summarize", "context", "Landroid/content/Context;", "systemReason", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes26.dex */
public final class LastExitDiagnostics {
    public static final int $stable = 0;
    public static final LastExitDiagnostics INSTANCE = new LastExitDiagnostics();

    private LastExitDiagnostics() {
    }

    public final String summarize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String captured = CrashReporter.INSTANCE.takeReport(context);
        String systemReason = systemReason(context);
        if (captured == null && systemReason == null) {
            return null;
        }
        StringBuilder $this$summarize_u24lambda_u241 = new StringBuilder();
        $this$summarize_u24lambda_u241.append(systemReason == null ? "Lần trước app đã bị đóng bất thường." : systemReason);
        if (captured != null) {
            $this$summarize_u24lambda_u241.append("\n\n");
            $this$summarize_u24lambda_u241.append(captured);
        }
        String sb = $this$summarize_u24lambda_u241.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    private final String systemReason(Context context) {
        String reason;
        if (Build.VERSION.SDK_INT < 30) {
            return null;
        }
        Object systemService = context.getSystemService("activity");
        ActivityManager am = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        if (am == null) {
            return null;
        }
        String pkg = context.getPackageName();
        try {
            List<ApplicationExitInfo> historicalProcessExitReasons = am.getHistoricalProcessExitReasons(pkg, 0, 5);
            Intrinsics.checkNotNullExpressionValue(historicalProcessExitReasons, "getHistoricalProcessExitReasons(...)");
            ApplicationExitInfo exit = (ApplicationExitInfo) CollectionsKt.firstOrNull((List) historicalProcessExitReasons);
            if (exit == null) {
                return null;
            }
            switch (exit.getReason()) {
                case 2:
                    reason = "bị tín hiệu từ hệ thống kết thúc";
                    break;
                case 3:
                    reason = "hệ thống kill vì hết RAM (không phải lỗi app)";
                    break;
                case 4:
                    reason = "lỗi Java/Kotlin (uncaught exception)";
                    break;
                case 5:
                    reason = "lỗi native (SIGSEGV/SIGABRT trong thư viện .so)";
                    break;
                case 6:
                    reason = "ứng dụng không phản hồi (ANR)";
                    break;
                case 7:
                case 8:
                case 10:
                default:
                    return null;
                case 9:
                    reason = "dùng tài nguyên quá mức";
                    break;
                case 11:
                    return null;
            }
            StringBuilder text = new StringBuilder("Lần trước app đã bị đóng vì: ").append(reason).append('.');
            String description = exit.getDescription();
            if (!(description == null || StringsKt.isBlank(description))) {
                text.append("\nMô tả: ").append(exit.getDescription());
            }
            text.append("\nRSS lúc chết: ").append(mb(exit.getRss())).append(", PSS ").append(mb(exit.getPss()));
            if (exit.getReason() == 2) {
                text.append("\nTín hiệu kết thúc: ").append(exit.getStatus() & 127).append(" (").append(signalName(exit.getStatus() & 127)).append(')');
            }
            text.append("\nApp lúc chết: ").append(importanceName(exit.getImportance()));
            text.append("\nMới xảy ra: ").append(ago(exit.getTimestamp()));
            String it = nativeTrace(exit);
            if (it != null) {
                text.append("\n\n--- Trace hệ thống ---\n").append(it);
            }
            return text.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private final String nativeTrace(ApplicationExitInfo exit) {
        Object m6717constructorimpl;
        String str;
        try {
            Result.Companion companion = Result.INSTANCE;
            LastExitDiagnostics lastExitDiagnostics = this;
            InputStream traceInputStream = exit.getTraceInputStream();
            if (traceInputStream == null) {
                str = null;
            } else {
                Intrinsics.checkNotNull(traceInputStream);
                Reader inputStreamReader = new InputStreamReader(traceInputStream, Charsets.UTF_8);
                BufferedReader it = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    str = StringsKt.take(StringsKt.trim((CharSequence) TextStreamsKt.readText(it)).toString(), 4000);
                    CloseableKt.closeFinally(it, null);
                } finally {
                }
            }
            m6717constructorimpl = Result.m6717constructorimpl(str);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = null;
        }
        String it2 = (String) m6717constructorimpl;
        if (it2 == null || !(!StringsKt.isBlank(it2))) {
            return null;
        }
        return it2;
    }

    private final String mb(long bytes) {
        if (bytes <= 0) {
            return "không rõ";
        }
        return (bytes / 1048576) + " MB";
    }

    private final String ago(long timestamp) {
        long minutes = (System.currentTimeMillis() - timestamp) / 60000;
        if (minutes < 0) {
            return "không rõ";
        }
        if (minutes < 1) {
            return "vừa xong";
        }
        if (minutes < 60) {
            return minutes + " phút trước";
        }
        return (minutes / 60) + " giờ trước";
    }

    private final String signalName(int sig) {
        switch (sig) {
            case 1:
                return "SIGHUP";
            case 6:
                return "SIGABRT";
            case 9:
                return "SIGKILL — hệ thống buộc dừng, thường do hết RAM";
            case 11:
                return "SIGSEGV — lỗi bộ nhớ trong code native";
            case 15:
                return "SIGTERM — hệ thống yêu cầu dừng";
            default:
                return "tín hiệu khác";
        }
    }

    private final String importanceName(int importance) {
        return importance <= 100 ? "đang ở foreground" : importance <= 125 ? "đang hiển thị" : importance <= 250 ? "còn nhận diện được (chạy nền)" : importance <= 500 ? "chỉ chạy service" : "nền/bị cache";
    }
}
