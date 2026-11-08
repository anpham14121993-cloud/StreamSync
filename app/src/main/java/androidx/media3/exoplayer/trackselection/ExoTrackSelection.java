package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import java.util.List;

/* loaded from: classes12.dex */
public interface ExoTrackSelection extends TrackSelection {

    /* loaded from: classes12.dex */
    public interface Factory {
        ExoTrackSelection[] createTrackSelections(Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline);
    }

    void disable();

    void enable();

    int evaluateQueueSize(long j, List<? extends MediaChunk> list);

    boolean excludeTrack(int i, long j);

    Format getSelectedFormat();

    int getSelectedIndex();

    int getSelectedIndexInTrackGroup();

    Object getSelectionData();

    int getSelectionReason();

    boolean isTrackExcluded(int i, long j);

    void onPlaybackSpeed(float f);

    void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr);

    /* loaded from: classes12.dex */
    public static final class Definition {
        private static final String TAG = "ETSDefinition";
        public final TrackGroup group;
        public final int[] tracks;
        public final int type;

        public Definition(TrackGroup group, int... tracks) {
            this(group, tracks, 0);
        }

        public Definition(TrackGroup group, int[] tracks, int type) {
            if (tracks.length == 0) {
                Log.e(TAG, "Empty tracks are not allowed", new IllegalArgumentException());
            }
            this.group = group;
            this.tracks = tracks;
            this.type = type;
        }
    }

    default void onDiscontinuity() {
    }

    default void onRebuffer() {
    }

    default void onPlayWhenReadyChanged(boolean playWhenReady) {
    }

    default boolean shouldCancelChunkLoad(long playbackPositionUs, Chunk loadingChunk, List<? extends MediaChunk> queue) {
        return false;
    }

    default long getLatestBitrateEstimate() {
        return -2147483647L;
    }
}
