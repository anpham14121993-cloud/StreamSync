package androidx.media3.ui;

import android.graphics.Color;
import androidx.media3.common.util.Util;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes24.dex */
public final class HtmlUtils {
    private HtmlUtils() {
    }

    public static String toCssRgba(int color) {
        return Util.formatInvariant("rgba(%d,%d,%d,%.3f)", Integer.valueOf(Color.red(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.blue(color)), Double.valueOf(Color.alpha(color) / 255.0d));
    }

    public static String cssAllClassDescendantsSelector(String className) {
        return "." + className + ",." + className + " *";
    }
}
