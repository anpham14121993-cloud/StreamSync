package androidx.media3.exoplayer.util;

import android.os.Looper;
import android.widget.TextView;
import androidx.core.os.EnvironmentCompat;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.Locale;

/* loaded from: classes5.dex */
public class DebugTextViewHelper {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final ExoPlayer player;
    private boolean started;
    private final TextView textView;
    private final Updater updater;

    public DebugTextViewHelper(ExoPlayer player, TextView textView) {
        Assertions.checkArgument(player.getApplicationLooper() == Looper.getMainLooper());
        this.player = player;
        this.textView = textView;
        this.updater = new Updater();
    }

    public final void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        this.player.addListener(this.updater);
        updateAndPost();
    }

    public final void stop() {
        if (!this.started) {
            return;
        }
        this.started = false;
        this.player.removeListener(this.updater);
        this.textView.removeCallbacks(this.updater);
    }

    protected final void updateAndPost() {
        this.textView.setText(getDebugString());
        this.textView.removeCallbacks(this.updater);
        this.textView.postDelayed(this.updater, 1000L);
    }

    protected String getDebugString() {
        return getPlayerStateString() + getVideoString() + getAudioString();
    }

    protected String getPlayerStateString() {
        String playbackStateString;
        switch (this.player.getPlaybackState()) {
            case 1:
                playbackStateString = "idle";
                break;
            case 2:
                playbackStateString = "buffering";
                break;
            case 3:
                playbackStateString = "ready";
                break;
            case 4:
                playbackStateString = "ended";
                break;
            default:
                playbackStateString = EnvironmentCompat.MEDIA_UNKNOWN;
                break;
        }
        return String.format("playWhenReady:%s playbackState:%s item:%s", Boolean.valueOf(this.player.getPlayWhenReady()), playbackStateString, Integer.valueOf(this.player.getCurrentMediaItemIndex()));
    }

    protected String getVideoString() {
        Format format = this.player.getVideoFormat();
        VideoSize videoSize = this.player.getVideoSize();
        DecoderCounters decoderCounters = this.player.getVideoDecoderCounters();
        if (format == null || decoderCounters == null) {
            return "";
        }
        return "\n" + format.sampleMimeType + "(id:" + format.id + " r:" + videoSize.width + "x" + videoSize.height + getColorInfoString(format.colorInfo) + getPixelAspectRatioString(videoSize.pixelWidthHeightRatio) + getDecoderCountersBufferCountString(decoderCounters) + " vfpo: " + getVideoFrameProcessingOffsetAverageString(decoderCounters.totalVideoFrameProcessingOffsetUs, decoderCounters.videoFrameProcessingOffsetCount) + ")";
    }

    protected String getAudioString() {
        Format format = this.player.getAudioFormat();
        DecoderCounters decoderCounters = this.player.getAudioDecoderCounters();
        if (format == null || decoderCounters == null) {
            return "";
        }
        return "\n" + format.sampleMimeType + "(id:" + format.id + " hz:" + format.sampleRate + " ch:" + format.channelCount + getDecoderCountersBufferCountString(decoderCounters) + ")";
    }

    private static String getDecoderCountersBufferCountString(DecoderCounters counters) {
        if (counters == null) {
            return "";
        }
        counters.ensureUpdated();
        return " sib:" + counters.skippedInputBufferCount + " sb:" + counters.skippedOutputBufferCount + " rb:" + counters.renderedOutputBufferCount + " db:" + counters.droppedBufferCount + " mcdb:" + counters.maxConsecutiveDroppedBufferCount + " dk:" + counters.droppedToKeyframeCount;
    }

    private static String getColorInfoString(ColorInfo colorInfo) {
        return (colorInfo == null || !colorInfo.isValid()) ? "" : " colr:" + colorInfo.toLogString();
    }

    private static String getPixelAspectRatioString(float pixelAspectRatio) {
        if (pixelAspectRatio == -1.0f || pixelAspectRatio == 1.0f) {
            return "";
        }
        return " par:" + String.format(Locale.US, "%.02f", Float.valueOf(pixelAspectRatio));
    }

    private static String getVideoFrameProcessingOffsetAverageString(long totalOffsetUs, int frameCount) {
        if (frameCount == 0) {
            return "N/A";
        }
        long averageUs = (long) (totalOffsetUs / frameCount);
        return String.valueOf(averageUs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public final class Updater implements Player.Listener, Runnable {
        private Updater() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int playbackState) {
            DebugTextViewHelper.this.updateAndPost();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
            DebugTextViewHelper.this.updateAndPost();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
            DebugTextViewHelper.this.updateAndPost();
        }

        @Override // java.lang.Runnable
        public void run() {
            DebugTextViewHelper.this.updateAndPost();
        }
    }
}
