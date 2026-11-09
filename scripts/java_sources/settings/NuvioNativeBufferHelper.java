package com.streamsync.app.settings;

import android.app.ActivityManager;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Native ExoPlayer memory budget calculator based on physical device RAM.
 * Matches Nuvio ATV / Mobile architecture (off-heap native allocation).
 */
public class NuvioNativeBufferHelper {
    private static volatile long sCachedTotalMem = 0L;

    public static long getDevicePhysicalRamBytes(Context context) {
        if (sCachedTotalMem > 0L) {
            return sCachedTotalMem;
        }
        try {
            if (context != null) {
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                if (am != null) {
                    ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
                    am.getMemoryInfo(mi);
                    if (mi.totalMem > 0L) {
                        sCachedTotalMem = mi.totalMem;
                        return mi.totalMem;
                    }
                }
            }
        } catch (Throwable ignored) {}

        try (BufferedReader reader = new BufferedReader(new FileReader("/proc/meminfo"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("MemTotal:")) {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 2) {
                        sCachedTotalMem = Long.parseLong(parts[1]) * 1024L;
                        return sCachedTotalMem;
                    }
                }
            }
        } catch (Throwable ignored) {}

        sCachedTotalMem = 2L * 1024L * 1024L * 1024L; // Default 2 GB
        return sCachedTotalMem;
    }

    public static int getSafeNativeMemoryLimitMb(Context context) {
        long totalMem = getDevicePhysicalRamBytes(context);
        long gb = 1024L * 1024L * 1024L;
        if (totalMem <= 0L) return 250;
        if (totalMem < 1.15 * gb) return 150;
        if (totalMem < 1.45 * gb) return 200;
        if (totalMem < 2.3 * gb) return 250;
        if (totalMem < 3.2 * gb) return 500;
        if (totalMem < 4.8 * gb) return 1000;
        if (totalMem < 6.8 * gb) return 1600;
        return 2000;
    }

    public static int getWarningNativeMemoryLimitMb(Context context) {
        long totalMem = getDevicePhysicalRamBytes(context);
        long gb = 1024L * 1024L * 1024L;
        if (totalMem <= 0L) return 325;
        if (totalMem < 1.15 * gb) return 180;
        if (totalMem < 1.45 * gb) return 250;
        if (totalMem < 2.3 * gb) return 325;
        if (totalMem < 3.2 * gb) return 650;
        if (totalMem < 4.8 * gb) return 1200;
        if (totalMem < 6.8 * gb) return 2000;
        return 2500;
    }

    public static int getAutoBufferMb(Context context) {
        long totalMem = getDevicePhysicalRamBytes(context);
        long gb = 1024L * 1024L * 1024L;
        if (totalMem <= 0L) return 200;
        if (totalMem < 1.15 * gb) return 100;
        if (totalMem < 1.45 * gb) return 125;
        if (totalMem < 2.3 * gb) return 200;
        if (totalMem < 3.2 * gb) return 500;
        if (totalMem < 4.8 * gb) return 1000;
        if (totalMem < 6.8 * gb) return 1600;
        return 2000;
    }
}
