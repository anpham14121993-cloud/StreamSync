package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;

/* loaded from: classes7.dex */
public interface MediaSource {

    /* loaded from: classes7.dex */
    public interface MediaSourceCaller {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline);
    }

    void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener);

    void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j);

    void disable(MediaSourceCaller mediaSourceCaller);

    void enable(MediaSourceCaller mediaSourceCaller);

    MediaItem getMediaItem();

    void maybeThrowSourceInfoRefreshError() throws IOException;

    void prepareSource(MediaSourceCaller mediaSourceCaller, TransferListener transferListener, PlayerId playerId);

    void releasePeriod(MediaPeriod mediaPeriod);

    void releaseSource(MediaSourceCaller mediaSourceCaller);

    void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener);

    void removeEventListener(MediaSourceEventListener mediaSourceEventListener);

    /* loaded from: classes7.dex */
    public interface Factory {
        public static final Factory UNSUPPORTED = MediaSourceFactory.UNSUPPORTED;

        MediaSource createMediaSource(MediaItem mediaItem);

        int[] getSupportedTypes();

        Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider);

        Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy);

        default Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory cmcdConfigurationFactory) {
            return this;
        }

        @Deprecated
        default Factory experimentalParseSubtitlesDuringExtraction(boolean parseSubtitlesDuringExtraction) {
            return this;
        }

        default Factory setSubtitleParserFactory(SubtitleParser.Factory subtitleParserFactory) {
            return this;
        }
    }

    /* loaded from: classes7.dex */
    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final int nextAdGroupIndex;
        public final Object periodUid;
        public final long windowSequenceNumber;

        public MediaPeriodId(Object periodUid) {
            this(periodUid, -1L);
        }

        public MediaPeriodId(Object periodUid, long windowSequenceNumber) {
            this(periodUid, -1, -1, windowSequenceNumber, -1);
        }

        public MediaPeriodId(Object periodUid, long windowSequenceNumber, int nextAdGroupIndex) {
            this(periodUid, -1, -1, windowSequenceNumber, nextAdGroupIndex);
        }

        public MediaPeriodId(Object periodUid, int adGroupIndex, int adIndexInAdGroup, long windowSequenceNumber) {
            this(periodUid, adGroupIndex, adIndexInAdGroup, windowSequenceNumber, -1);
        }

        private MediaPeriodId(Object periodUid, int adGroupIndex, int adIndexInAdGroup, long windowSequenceNumber, int nextAdGroupIndex) {
            this.periodUid = periodUid;
            this.adGroupIndex = adGroupIndex;
            this.adIndexInAdGroup = adIndexInAdGroup;
            this.windowSequenceNumber = windowSequenceNumber;
            this.nextAdGroupIndex = nextAdGroupIndex;
        }

        public MediaPeriodId copyWithPeriodUid(Object newPeriodUid) {
            if (this.periodUid.equals(newPeriodUid)) {
                return this;
            }
            return new MediaPeriodId(newPeriodUid, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
        }

        public MediaPeriodId copyWithWindowSequenceNumber(long windowSequenceNumber) {
            if (this.windowSequenceNumber == windowSequenceNumber) {
                return this;
            }
            return new MediaPeriodId(this.periodUid, this.adGroupIndex, this.adIndexInAdGroup, windowSequenceNumber, this.nextAdGroupIndex);
        }

        public boolean isAd() {
            return this.adGroupIndex != -1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaPeriodId)) {
                return false;
            }
            MediaPeriodId periodId = (MediaPeriodId) obj;
            return this.periodUid.equals(periodId.periodUid) && this.adGroupIndex == periodId.adGroupIndex && this.adIndexInAdGroup == periodId.adIndexInAdGroup && this.windowSequenceNumber == periodId.windowSequenceNumber && this.nextAdGroupIndex == periodId.nextAdGroupIndex;
        }

        public int hashCode() {
            int result = (17 * 31) + this.periodUid.hashCode();
            return (((((((result * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
        }
    }

    default Timeline getInitialTimeline() {
        return null;
    }

    default boolean isSingleWindow() {
        return true;
    }

    default boolean canUpdateMediaItem(MediaItem mediaItem) {
        return false;
    }

    default void updateMediaItem(MediaItem mediaItem) {
    }

    @Deprecated
    default void prepareSource(MediaSourceCaller caller, TransferListener mediaTransferListener) {
        prepareSource(caller, mediaTransferListener, PlayerId.UNSET);
    }
}
