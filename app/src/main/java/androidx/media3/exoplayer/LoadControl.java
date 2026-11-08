package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;

/* loaded from: classes4.dex */
public interface LoadControl {

    @Deprecated
    public static final MediaSource.MediaPeriodId EMPTY_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());

    Allocator getAllocator();

    /* loaded from: classes4.dex */
    public static final class Parameters {
        public final long bufferedDurationUs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final boolean playWhenReady;
        public final long playbackPositionUs;
        public final float playbackSpeed;
        public final PlayerId playerId;
        public final boolean rebuffering;
        public final long targetLiveOffsetUs;
        public final Timeline timeline;

        public Parameters(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long playbackPositionUs, long bufferedDurationUs, float playbackSpeed, boolean playWhenReady, boolean rebuffering, long targetLiveOffsetUs) {
            this.playerId = playerId;
            this.timeline = timeline;
            this.mediaPeriodId = mediaPeriodId;
            this.playbackPositionUs = playbackPositionUs;
            this.bufferedDurationUs = bufferedDurationUs;
            this.playbackSpeed = playbackSpeed;
            this.playWhenReady = playWhenReady;
            this.rebuffering = rebuffering;
            this.targetLiveOffsetUs = targetLiveOffsetUs;
        }
    }

    default void onPrepared(PlayerId playerId) {
        onPrepared();
    }

    @Deprecated
    default void onPrepared() {
        throw new IllegalStateException("onPrepared not implemented");
    }

    default void onTracksSelected(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] renderers, TrackGroupArray trackGroups, ExoTrackSelection[] trackSelections) {
        onTracksSelected(timeline, mediaPeriodId, renderers, trackGroups, trackSelections);
    }

    @Deprecated
    default void onTracksSelected(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] renderers, TrackGroupArray trackGroups, ExoTrackSelection[] trackSelections) {
        onTracksSelected(renderers, trackGroups, trackSelections);
    }

    @Deprecated
    default void onTracksSelected(Renderer[] renderers, TrackGroupArray trackGroups, ExoTrackSelection[] trackSelections) {
        throw new IllegalStateException("onTracksSelected not implemented");
    }

    default void onStopped(PlayerId playerId) {
        onStopped();
    }

    @Deprecated
    default void onStopped() {
        throw new IllegalStateException("onStopped not implemented");
    }

    default void onReleased(PlayerId playerId) {
        onReleased();
    }

    @Deprecated
    default void onReleased() {
        throw new IllegalStateException("onReleased not implemented");
    }

    default long getBackBufferDurationUs(PlayerId playerId) {
        return getBackBufferDurationUs();
    }

    @Deprecated
    default long getBackBufferDurationUs() {
        throw new IllegalStateException("getBackBufferDurationUs not implemented");
    }

    default boolean retainBackBufferFromKeyframe(PlayerId playerId) {
        return retainBackBufferFromKeyframe();
    }

    @Deprecated
    default boolean retainBackBufferFromKeyframe() {
        throw new IllegalStateException("retainBackBufferFromKeyframe not implemented");
    }

    default boolean shouldContinueLoading(Parameters parameters) {
        return shouldContinueLoading(parameters.playbackPositionUs, parameters.bufferedDurationUs, parameters.playbackSpeed);
    }

    @Deprecated
    default boolean shouldContinueLoading(long playbackPositionUs, long bufferedDurationUs, float playbackSpeed) {
        throw new IllegalStateException("shouldContinueLoading not implemented");
    }

    default boolean shouldStartPlayback(Parameters parameters) {
        return shouldStartPlayback(parameters.timeline, parameters.mediaPeriodId, parameters.bufferedDurationUs, parameters.playbackSpeed, parameters.rebuffering, parameters.targetLiveOffsetUs);
    }

    @Deprecated
    default boolean shouldStartPlayback(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long bufferedDurationUs, float playbackSpeed, boolean rebuffering, long targetLiveOffsetUs) {
        return shouldStartPlayback(bufferedDurationUs, playbackSpeed, rebuffering, targetLiveOffsetUs);
    }

    @Deprecated
    default boolean shouldStartPlayback(long bufferedDurationUs, float playbackSpeed, boolean rebuffering, long targetLiveOffsetUs) {
        throw new IllegalStateException("shouldStartPlayback not implemented");
    }
}
