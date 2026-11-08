package com.streamsync.app.diag;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.Window;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.FileLog;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FrameTimingLogger.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ8\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0002J\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u000eJ\u0006\u0010$\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/streamsync/app/diag/FrameTimingLogger;", "", "()V", "JANK_RATIO", "", "M_ANIMATION_DURATION", "", "M_DRAW_DURATION", "M_INPUT_DURATION", "M_LAYOUT_DURATION", "PERIOD_MS", "", "frames", "installed", "", "janky", "suppress", "thread", "Landroid/os/HandlerThread;", "worst", "worstDeadline", "worstPhases", "", "install", "", "activity", "Landroid/app/Activity;", "onFrame", "totalMs", "deadlineMs", "inputMs", "animMs", "layoutMs", "drawMs", "setSuppressed", "value", "stop", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes26.dex */
public final class FrameTimingLogger {
    private static final double JANK_RATIO = 1.5d;
    private static final int M_ANIMATION_DURATION = 2;
    private static final int M_DRAW_DURATION = 4;
    private static final int M_INPUT_DURATION = 1;
    private static final int M_LAYOUT_DURATION = 3;
    private static final long PERIOD_MS = 5000;
    private static int frames;
    private static boolean installed;
    private static int janky;
    private static volatile boolean suppress;
    private static HandlerThread thread;
    private static double worst;
    private static double worstDeadline;
    public static final FrameTimingLogger INSTANCE = new FrameTimingLogger();
    private static String worstPhases = "0|0|0|0";
    public static final int $stable = 8;

    private FrameTimingLogger() {
    }

    public final void install(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (installed) {
            return;
        }
        installed = true;
        Window window = activity.getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
        HandlerThread ht = new HandlerThread("frame-metrics");
        ht.start();
        thread = ht;
        window.addOnFrameMetricsAvailableListener(new Window.OnFrameMetricsAvailableListener() { // from class: com.streamsync.app.diag.FrameTimingLogger$install$1
            @Override // android.view.Window.OnFrameMetricsAvailableListener
            public void onFrameMetricsAvailable(Window window2, FrameMetrics metrics, int dropCountSinceLastInvocation) {
                Intrinsics.checkNotNullParameter(window2, "window");
                Intrinsics.checkNotNullParameter(metrics, "metrics");
                try {
                    double total = metrics.getMetric(8) / 1000000.0d;
                    double deadline = metrics.getMetric(13) / 1000000.0d;
                    double input = metrics.getMetric(1) / 1000000.0d;
                    double anim = metrics.getMetric(2) / 1000000.0d;
                    double layout = metrics.getMetric(3) / 1000000.0d;
                    double draw = metrics.getMetric(4) / 1000000.0d;
                    FrameTimingLogger.INSTANCE.onFrame(total, deadline, input, anim, layout, draw);
                } catch (Throwable t) {
                    Log.w("FrameTiming", "do khung that bai", t);
                }
            }
        }, new Handler(ht.getLooper()));
        FileLog.d$default(FileLog.INSTANCE, "Do khung hinh: BAT (FrameMetrics, API>=24)", null, 2, null);
    }

    public final void setSuppressed(boolean value) {
        suppress = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void onFrame(double totalMs, double deadlineMs, double inputMs, double animMs, double layoutMs, double drawMs) {
        if (suppress) {
            frames = 0;
            janky = 0;
            worst = 0.0d;
            return;
        }
        frames++;
        if (totalMs > worst) {
            worst = totalMs;
            worstDeadline = deadlineMs;
            worstPhases = ((int) inputMs) + "|" + ((int) animMs) + "|" + ((int) layoutMs) + "|" + ((int) drawMs);
        }
        if (deadlineMs > 0.0d && totalMs > JANK_RATIO * deadlineMs) {
            janky++;
        }
        if (frames < 30) {
            return;
        }
        if (janky > 0) {
            List split$default = StringsKt.split$default((CharSequence) worstPhases, new String[]{"|"}, false, 0, 6, (Object) null);
            String inp = (String) split$default.get(0);
            String an = (String) split$default.get(1);
            String lay = (String) split$default.get(2);
            String drw = (String) split$default.get(3);
            FileLog.d$default(FileLog.INSTANCE, "Khung: " + frames + " khung, " + janky + " tre (muc " + ((int) worstDeadline) + "), cham " + ((int) worst) + " ms [vao=" + Integer.parseInt(inp) + " hieu=" + Integer.parseInt(an) + " do=" + Integer.parseInt(lay) + " ve=" + Integer.parseInt(drw) + "]", null, 2, null);
        }
        frames = 0;
        janky = 0;
        worst = 0.0d;
        worstPhases = "0|0|0|0";
    }

    public final void stop() {
        HandlerThread handlerThread = thread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        thread = null;
        installed = false;
    }
}
