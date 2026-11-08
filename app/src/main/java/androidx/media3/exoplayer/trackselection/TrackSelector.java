package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* loaded from: classes12.dex */
public abstract class TrackSelector {
    private BandwidthMeter bandwidthMeter;
    private InvalidationListener listener;

    public abstract void onSelectionActivated(Object obj);

    public abstract TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException;

    /* loaded from: classes12.dex */
    public interface InvalidationListener {
        void onTrackSelectionsInvalidated();

        default void onRendererCapabilitiesChanged(Renderer renderer) {
        }
    }

    public void init(InvalidationListener listener, BandwidthMeter bandwidthMeter) {
        this.listener = listener;
        this.bandwidthMeter = bandwidthMeter;
    }

    public void release() {
        this.listener = null;
        this.bandwidthMeter = null;
    }

    public TrackSelectionParameters getParameters() {
        return TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
    }

    public void setParameters(TrackSelectionParameters parameters) {
    }

    public boolean isSetParametersSupported() {
        return false;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
    }

    public RendererCapabilities.Listener getRendererCapabilitiesListener() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void invalidate() {
        if (this.listener != null) {
            this.listener.onTrackSelectionsInvalidated();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void invalidateForRendererCapabilitiesChange(Renderer renderer) {
        if (this.listener != null) {
            this.listener.onRendererCapabilitiesChanged(renderer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BandwidthMeter getBandwidthMeter() {
        return (BandwidthMeter) Assertions.checkStateNotNull(this.bandwidthMeter);
    }
}
