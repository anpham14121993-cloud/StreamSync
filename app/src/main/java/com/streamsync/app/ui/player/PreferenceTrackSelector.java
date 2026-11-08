package com.streamsync.app.ui.player;

import android.content.Context;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OpticalPassthroughRenderersFactory.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016J3\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016¢\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/streamsync/app/ui/player/PreferenceTrackSelector;", "Landroidx/media3/exoplayer/trackselection/TrackSelector;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "delegate", "Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;", "getParameters", "Landroidx/media3/common/TrackSelectionParameters;", "getRendererCapabilitiesListener", "Landroidx/media3/exoplayer/RendererCapabilities$Listener;", "init", "", "invalidationListener", "Landroidx/media3/exoplayer/trackselection/TrackSelector$InvalidationListener;", "bandwidthMeter", "Landroidx/media3/exoplayer/upstream/BandwidthMeter;", "isSetParametersSupported", "", "onSelectionActivated", "selection", "", "release", "selectTracks", "Landroidx/media3/exoplayer/trackselection/TrackSelectorResult;", "rendererCapabilities", "", "Landroidx/media3/exoplayer/RendererCapabilities;", "trackGroups", "Landroidx/media3/exoplayer/source/TrackGroupArray;", "mediaPeriodId", "Landroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;", "timeline", "Landroidx/media3/common/Timeline;", "([Landroidx/media3/exoplayer/RendererCapabilities;Landroidx/media3/exoplayer/source/TrackGroupArray;Landroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;Landroidx/media3/common/Timeline;)Landroidx/media3/exoplayer/trackselection/TrackSelectorResult;", "setAudioAttributes", "audioAttributes", "Landroidx/media3/common/AudioAttributes;", "setParameters", "parameters", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PreferenceTrackSelector extends TrackSelector {
    public static final int $stable = 8;
    private final DefaultTrackSelector delegate;

    public PreferenceTrackSelector(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.delegate = new DefaultTrackSelector(context);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void init(TrackSelector.InvalidationListener invalidationListener, BandwidthMeter bandwidthMeter) {
        Intrinsics.checkNotNullParameter(invalidationListener, "invalidationListener");
        Intrinsics.checkNotNullParameter(bandwidthMeter, "bandwidthMeter");
        this.delegate.init(invalidationListener, bandwidthMeter);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void release() {
        this.delegate.release();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public TrackSelectionParameters getParameters() {
        DefaultTrackSelector.Parameters parameters = this.delegate.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
        return parameters;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void setParameters(TrackSelectionParameters parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.delegate.setParameters(parameters);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public boolean isSetParametersSupported() {
        return true;
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        Intrinsics.checkNotNullParameter(audioAttributes, "audioAttributes");
        this.delegate.setAudioAttributes(audioAttributes);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public RendererCapabilities.Listener getRendererCapabilitiesListener() {
        return this.delegate.getRendererCapabilitiesListener();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public void onSelectionActivated(Object selection) {
        this.delegate.onSelectionActivated(selection);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector
    public TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilities, TrackGroupArray trackGroups, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
        Intrinsics.checkNotNullParameter(rendererCapabilities, "rendererCapabilities");
        Intrinsics.checkNotNullParameter(trackGroups, "trackGroups");
        Intrinsics.checkNotNullParameter(mediaPeriodId, "mediaPeriodId");
        Intrinsics.checkNotNullParameter(timeline, "timeline");
        TrackSelectorResult selectTracks = this.delegate.selectTracks(rendererCapabilities, trackGroups, mediaPeriodId, timeline);
        Intrinsics.checkNotNullExpressionValue(selectTracks, "selectTracks(...)");
        return selectTracks;
    }
}
