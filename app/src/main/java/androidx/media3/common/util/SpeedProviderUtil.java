package androidx.media3.common.util;

import androidx.media3.common.C;
import androidx.media3.common.audio.SpeedProvider;

/* loaded from: classes24.dex */
public class SpeedProviderUtil {
    private SpeedProviderUtil() {
    }

    public static long getDurationAfterSpeedProviderApplied(SpeedProvider speedProvider, long durationUs) {
        long speedChangeTimeUs = 0;
        double outputDurationUs = 0.0d;
        while (speedChangeTimeUs < durationUs) {
            long nextSpeedChangeTimeUs = speedProvider.getNextSpeedChangeTimeUs(speedChangeTimeUs);
            if (nextSpeedChangeTimeUs == C.TIME_UNSET) {
                nextSpeedChangeTimeUs = Long.MAX_VALUE;
            }
            outputDurationUs += (Math.min(nextSpeedChangeTimeUs, durationUs) - speedChangeTimeUs) / speedProvider.getSpeed(speedChangeTimeUs);
            speedChangeTimeUs = nextSpeedChangeTimeUs;
        }
        return Math.round(outputDurationUs);
    }
}
