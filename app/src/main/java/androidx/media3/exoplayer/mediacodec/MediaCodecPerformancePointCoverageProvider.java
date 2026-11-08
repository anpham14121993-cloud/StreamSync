package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodecInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class MediaCodecPerformancePointCoverageProvider {
    static final int COVERAGE_RESULT_NO = 1;
    static final int COVERAGE_RESULT_NO_PERFORMANCE_POINTS_UNSUPPORTED = 0;
    static final int COVERAGE_RESULT_YES = 2;
    private static Boolean shouldIgnorePerformancePoints;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    @interface PerformancePointCoverageResult {
    }

    private MediaCodecPerformancePointCoverageProvider() {
    }

    public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int width, int height, double frameRate) {
        if (Util.SDK_INT >= 29) {
            if (shouldIgnorePerformancePoints != null && shouldIgnorePerformancePoints.booleanValue()) {
                return 0;
            }
            return Api29.areResolutionAndFrameRateCovered(videoCapabilities, width, height, frameRate);
        }
        return 0;
    }

    /* loaded from: classes5.dex */
    private static final class Api29 {
        private Api29() {
        }

        public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities videoCapabilities, int width, int height, double frameRate) {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> performancePointList = videoCapabilities.getSupportedPerformancePoints();
            if (performancePointList == null || performancePointList.isEmpty()) {
                return 0;
            }
            MediaCodecInfo.VideoCapabilities.PerformancePoint targetPerformancePoint = new MediaCodecInfo.VideoCapabilities.PerformancePoint(width, height, (int) frameRate);
            int performancePointCoverageResult = evaluatePerformancePointCoverage(performancePointList, targetPerformancePoint);
            if (performancePointCoverageResult == 1 && MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints == null) {
                MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints = Boolean.valueOf(shouldIgnorePerformancePoints());
                if (MediaCodecPerformancePointCoverageProvider.shouldIgnorePerformancePoints.booleanValue()) {
                    return 0;
                }
            }
            return performancePointCoverageResult;
        }

        private static boolean shouldIgnorePerformancePoints() {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> performancePointListH264;
            if (Util.SDK_INT >= 35) {
                return false;
            }
            try {
                Format formatH264 = new Format.Builder().setSampleMimeType(MimeTypes.VIDEO_H264).build();
                if (formatH264.sampleMimeType != null) {
                    List<MediaCodecInfo> decoderInfos = MediaCodecUtil.getDecoderInfosSoftMatch(MediaCodecSelector.DEFAULT, formatH264, false, false);
                    for (int i = 0; i < decoderInfos.size(); i++) {
                        if (decoderInfos.get(i).capabilities != null && decoderInfos.get(i).capabilities.getVideoCapabilities() != null && (performancePointListH264 = decoderInfos.get(i).capabilities.getVideoCapabilities().getSupportedPerformancePoints()) != null && !performancePointListH264.isEmpty()) {
                            MediaCodecInfo.VideoCapabilities.PerformancePoint targetPerformancePointH264 = new MediaCodecInfo.VideoCapabilities.PerformancePoint(1280, 720, 60);
                            return evaluatePerformancePointCoverage(performancePointListH264, targetPerformancePointH264) == 1;
                        }
                    }
                }
                return true;
            } catch (MediaCodecUtil.DecoderQueryException e) {
                return true;
            }
        }

        private static int evaluatePerformancePointCoverage(List<MediaCodecInfo.VideoCapabilities.PerformancePoint> performancePointList, MediaCodecInfo.VideoCapabilities.PerformancePoint targetPerformancePoint) {
            for (int i = 0; i < performancePointList.size(); i++) {
                if (performancePointList.get(i).covers(targetPerformancePoint)) {
                    return 2;
                }
            }
            return 1;
        }
    }
}
