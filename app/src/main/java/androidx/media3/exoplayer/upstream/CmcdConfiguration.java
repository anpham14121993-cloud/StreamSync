package androidx.media3.exoplayer.upstream;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.google.common.collect.ImmutableListMultimap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

/* loaded from: classes10.dex */
public final class CmcdConfiguration {
    public static final String CMCD_QUERY_PARAMETER_KEY = "CMCD";
    public static final String KEY_BITRATE = "br";
    public static final String KEY_BUFFER_LENGTH = "bl";
    public static final String KEY_BUFFER_STARVATION = "bs";
    public static final String KEY_CMCD_OBJECT = "CMCD-Object";
    public static final String KEY_CMCD_REQUEST = "CMCD-Request";
    public static final String KEY_CMCD_SESSION = "CMCD-Session";
    public static final String KEY_CMCD_STATUS = "CMCD-Status";
    public static final String KEY_CONTENT_ID = "cid";
    public static final String KEY_DEADLINE = "dl";
    public static final String KEY_MAXIMUM_REQUESTED_BITRATE = "rtp";
    public static final String KEY_MEASURED_THROUGHPUT = "mtp";
    public static final String KEY_NEXT_OBJECT_REQUEST = "nor";
    public static final String KEY_NEXT_RANGE_REQUEST = "nrr";
    public static final String KEY_OBJECT_DURATION = "d";
    public static final String KEY_OBJECT_TYPE = "ot";
    public static final String KEY_PLAYBACK_RATE = "pr";
    public static final String KEY_SESSION_ID = "sid";
    public static final String KEY_STARTUP = "su";
    public static final String KEY_STREAMING_FORMAT = "sf";
    public static final String KEY_STREAM_TYPE = "st";
    public static final String KEY_TOP_BITRATE = "tb";
    public static final String KEY_VERSION = "v";
    public static final int MAX_ID_LENGTH = 64;
    public static final int MODE_QUERY_PARAMETER = 1;
    public static final int MODE_REQUEST_HEADER = 0;
    public final String contentId;
    public final int dataTransmissionMode;
    public final RequestConfig requestConfig;
    public final String sessionId;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface CmcdKey {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface DataTransmissionMode {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface HeaderKey {
    }

    /* loaded from: classes10.dex */
    public interface Factory {
        public static final Factory DEFAULT = new Factory() { // from class: androidx.media3.exoplayer.upstream.CmcdConfiguration$Factory$$ExternalSyntheticLambda0
            @Override // androidx.media3.exoplayer.upstream.CmcdConfiguration.Factory
            public final CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem) {
                return CmcdConfiguration.Factory.lambda$static$0(mediaItem);
            }
        };

        CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem);

        static /* synthetic */ CmcdConfiguration lambda$static$0(MediaItem mediaItem) {
            String str;
            String uuid = UUID.randomUUID().toString();
            if (mediaItem.mediaId != null) {
                str = mediaItem.mediaId;
            } else {
                str = "";
            }
            return new CmcdConfiguration(uuid, str, new RequestConfig() { // from class: androidx.media3.exoplayer.upstream.CmcdConfiguration.Factory.1
            });
        }
    }

    /* loaded from: classes10.dex */
    public interface RequestConfig {
        default boolean isKeyAllowed(String key) {
            return true;
        }

        default ImmutableListMultimap<String, String> getCustomData() {
            return ImmutableListMultimap.of();
        }

        default int getRequestedMaximumThroughputKbps(int throughputKbps) {
            return C.RATE_UNSET_INT;
        }
    }

    public CmcdConfiguration(String sessionId, String contentId, RequestConfig requestConfig) {
        this(sessionId, contentId, requestConfig, 0);
    }

    public CmcdConfiguration(String sessionId, String contentId, RequestConfig requestConfig, int dataTransmissionMode) {
        Assertions.checkArgument(sessionId == null || sessionId.length() <= 64);
        Assertions.checkArgument(contentId == null || contentId.length() <= 64);
        Assertions.checkNotNull(requestConfig);
        this.sessionId = sessionId;
        this.contentId = contentId;
        this.requestConfig = requestConfig;
        this.dataTransmissionMode = dataTransmissionMode;
    }

    public boolean isBitrateLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("br");
    }

    public boolean isBufferLengthLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_BUFFER_LENGTH);
    }

    public boolean isContentIdLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_CONTENT_ID);
    }

    public boolean isSessionIdLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_SESSION_ID);
    }

    public boolean isMaximumRequestThroughputLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_MAXIMUM_REQUESTED_BITRATE);
    }

    public boolean isStreamingFormatLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_STREAMING_FORMAT);
    }

    public boolean isStreamTypeLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_STREAM_TYPE);
    }

    public boolean isTopBitrateLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("tb");
    }

    public boolean isObjectDurationLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("d");
    }

    public boolean isMeasuredThroughputLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_MEASURED_THROUGHPUT);
    }

    public boolean isObjectTypeLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_OBJECT_TYPE);
    }

    public boolean isBufferStarvationLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_BUFFER_STARVATION);
    }

    public boolean isDeadlineLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_DEADLINE);
    }

    public boolean isPlaybackRateLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_PLAYBACK_RATE);
    }

    public boolean isStartupLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_STARTUP);
    }

    public boolean isNextObjectRequestLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_NEXT_OBJECT_REQUEST);
    }

    public boolean isNextRangeRequestLoggingAllowed() {
        return this.requestConfig.isKeyAllowed(KEY_NEXT_RANGE_REQUEST);
    }
}
