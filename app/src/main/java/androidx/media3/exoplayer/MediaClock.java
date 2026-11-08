package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;

/* loaded from: classes4.dex */
public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    void setPlaybackParameters(PlaybackParameters playbackParameters);

    default boolean hasSkippedSilenceSinceLastCall() {
        return false;
    }
}
