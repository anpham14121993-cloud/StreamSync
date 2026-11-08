package androidx.media3.common.util;

import android.os.Trace;

/* loaded from: classes24.dex */
public final class TraceUtil {
    private TraceUtil() {
    }

    public static void beginSection(String sectionName) {
        Trace.beginSection(sectionName);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
