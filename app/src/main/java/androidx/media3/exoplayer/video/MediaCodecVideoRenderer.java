package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.video.CompositingVideoSinkProvider;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.android.gms.common.Scopes;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.MoreExecutors;
import com.streamsync.app.subtitle.audio.PlayerAudioCapture;
import java.nio.ByteBuffer;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes7.dex */
public class MediaCodecVideoRenderer extends MediaCodecRenderer implements VideoFrameReleaseControl.FrameTimingEvaluator {
    private static final int HEVC_MAX_INPUT_SIZE_THRESHOLD = 2097152;
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final long MIN_EARLY_US_LATE_THRESHOLD = -30000;
    private static final long MIN_EARLY_US_VERY_LATE_THRESHOLD = -500000;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, PlayerAudioCapture.CHUNK_SAMPLES, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static final long TUNNELING_EOS_PRESENTATION_TIME_US = Long.MAX_VALUE;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private int buffersInCodecCount;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private VideoSize decodedVideoSize;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private Surface displaySurface;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private boolean hasSetVideoSink;
    private boolean haveReportedFirstFrameRenderedForCurrentSurface;
    private long lastFrameReleaseTimeNs;
    private final int maxDroppedFramesToNotify;
    private Size outputResolution;
    private final boolean ownsVideoSink;
    private PlaceholderSurface placeholderSurface;
    private int rendererPriority;
    private VideoSize reportedVideoSize;
    private int scalingMode;
    private long totalVideoFrameProcessingOffsetUs;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    private List<Effect> videoEffects;
    private int videoFrameProcessingOffsetCount;
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo;
    private VideoSink videoSink;
    private final VideoSinkProvider videoSinkProvider;

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector) {
        this(context, mediaCodecSelector, 0L);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long allowedJoiningTimeMs) {
        this(context, mediaCodecSelector, allowedJoiningTimeMs, null, null, 0);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long allowedJoiningTimeMs, Handler eventHandler, VideoRendererEventListener eventListener, int maxDroppedFramesToNotify) {
        this(context, MediaCodecAdapter.Factory.getDefault(context), mediaCodecSelector, allowedJoiningTimeMs, false, eventHandler, eventListener, maxDroppedFramesToNotify, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long allowedJoiningTimeMs, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, int maxDroppedFramesToNotify) {
        this(context, MediaCodecAdapter.Factory.getDefault(context), mediaCodecSelector, allowedJoiningTimeMs, enableDecoderFallback, eventHandler, eventListener, maxDroppedFramesToNotify, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory codecAdapterFactory, MediaCodecSelector mediaCodecSelector, long allowedJoiningTimeMs, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, int maxDroppedFramesToNotify) {
        this(context, codecAdapterFactory, mediaCodecSelector, allowedJoiningTimeMs, enableDecoderFallback, eventHandler, eventListener, maxDroppedFramesToNotify, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory codecAdapterFactory, MediaCodecSelector mediaCodecSelector, long allowedJoiningTimeMs, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, int maxDroppedFramesToNotify, float assumedMinimumCodecOperatingRate) {
        this(context, codecAdapterFactory, mediaCodecSelector, allowedJoiningTimeMs, enableDecoderFallback, eventHandler, eventListener, maxDroppedFramesToNotify, assumedMinimumCodecOperatingRate, null);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecAdapter.Factory codecAdapterFactory, MediaCodecSelector mediaCodecSelector, long allowedJoiningTimeMs, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, int maxDroppedFramesToNotify, float assumedMinimumCodecOperatingRate, VideoSinkProvider videoSinkProvider) {
        super(2, codecAdapterFactory, mediaCodecSelector, enableDecoderFallback, assumedMinimumCodecOperatingRate);
        this.context = context.getApplicationContext();
        this.maxDroppedFramesToNotify = maxDroppedFramesToNotify;
        this.videoSinkProvider = videoSinkProvider;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(eventHandler, eventListener);
        this.ownsVideoSink = videoSinkProvider == null;
        if (videoSinkProvider == null) {
            this.videoFrameReleaseControl = new VideoFrameReleaseControl(this.context, this, allowedJoiningTimeMs);
        } else {
            this.videoFrameReleaseControl = videoSinkProvider.getVideoFrameReleaseControl();
        }
        this.videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.outputResolution = Size.UNKNOWN;
        this.scalingMode = 1;
        this.decodedVideoSize = VideoSize.UNKNOWN;
        this.tunnelingAudioSessionId = 0;
        this.reportedVideoSize = null;
        this.rendererPriority = -1000;
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldForceReleaseFrame(long earlyUs, long elapsedSinceLastReleaseUs) {
        return shouldForceRenderOutputBuffer(earlyUs, elapsedSinceLastReleaseUs);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldDropFrame(long earlyUs, long elapsedRealtimeUs, boolean isLastFrame) {
        return shouldDropOutputBuffer(earlyUs, elapsedRealtimeUs, isLastFrame);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameReleaseControl.FrameTimingEvaluator
    public boolean shouldIgnoreFrame(long earlyUs, long positionUs, long elapsedRealtimeUs, boolean isLastFrame, boolean treatDroppedBuffersAsSkipped) throws ExoPlaybackException {
        return shouldDropBuffersToKeyframe(earlyUs, elapsedRealtimeUs, isLastFrame) && maybeDropBuffersToKeyframe(positionUs, treatDroppedBuffersAsSkipped);
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        int adaptiveSupport;
        int hardwareAccelerationSupport;
        String mimeType = format.sampleMimeType;
        if (!MimeTypes.isVideo(mimeType)) {
            return RendererCapabilities.create(0);
        }
        DrmInitData drmInitData = format.drmInitData;
        boolean requiresSecureDecryption = drmInitData != null;
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(this.context, mediaCodecSelector, format, requiresSecureDecryption, false);
        if (requiresSecureDecryption && decoderInfos.isEmpty()) {
            decoderInfos = getDecoderInfos(this.context, mediaCodecSelector, format, false, false);
        }
        if (decoderInfos.isEmpty()) {
            return RendererCapabilities.create(1);
        }
        if (!supportsFormatDrm(format)) {
            return RendererCapabilities.create(2);
        }
        MediaCodecInfo decoderInfo = decoderInfos.get(0);
        boolean isFormatSupported = decoderInfo.isFormatSupported(format);
        boolean isPreferredDecoder = true;
        if (!isFormatSupported) {
            int i = 1;
            while (true) {
                if (i >= decoderInfos.size()) {
                    break;
                }
                MediaCodecInfo otherDecoderInfo = decoderInfos.get(i);
                if (!otherDecoderInfo.isFormatSupported(format)) {
                    i++;
                } else {
                    decoderInfo = otherDecoderInfo;
                    isFormatSupported = true;
                    isPreferredDecoder = false;
                    break;
                }
            }
        }
        int formatSupport = isFormatSupported ? 4 : 3;
        if (decoderInfo.isSeamlessAdaptationSupported(format)) {
            adaptiveSupport = 16;
        } else {
            adaptiveSupport = 8;
        }
        if (decoderInfo.hardwareAccelerated) {
            hardwareAccelerationSupport = 64;
        } else {
            hardwareAccelerationSupport = 0;
        }
        int decoderSupport = isPreferredDecoder ? 128 : 0;
        if (Util.SDK_INT >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && !Api26.doesDisplaySupportDolbyVision(this.context)) {
            decoderSupport = 256;
        }
        int tunnelingSupport = 0;
        if (isFormatSupported) {
            List<MediaCodecInfo> tunnelingDecoderInfos = getDecoderInfos(this.context, mediaCodecSelector, format, requiresSecureDecryption, true);
            if (!tunnelingDecoderInfos.isEmpty()) {
                MediaCodecInfo tunnelingDecoderInfo = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(tunnelingDecoderInfos, format).get(0);
                if (tunnelingDecoderInfo.isFormatSupported(format) && tunnelingDecoderInfo.isSeamlessAdaptationSupported(format)) {
                    tunnelingSupport = 32;
                }
            }
        }
        return RendererCapabilities.create(formatSupport, adaptiveSupport, tunnelingSupport, hardwareAccelerationSupport, decoderSupport);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean requiresSecureDecoder) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(this.context, mediaCodecSelector, format, requiresSecureDecoder, this.tunneling), format);
    }

    private static List<MediaCodecInfo> getDecoderInfos(Context context, MediaCodecSelector mediaCodecSelector, Format format, boolean requiresSecureDecoder, boolean requiresTunnelingDecoder) throws MediaCodecUtil.DecoderQueryException {
        if (format.sampleMimeType == null) {
            return ImmutableList.of();
        }
        if (Util.SDK_INT >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && !Api26.doesDisplaySupportDolbyVision(context)) {
            List<MediaCodecInfo> alternativeDecoderInfos = MediaCodecUtil.getAlternativeDecoderInfos(mediaCodecSelector, format, requiresSecureDecoder, requiresTunnelingDecoder);
            if (!alternativeDecoderInfos.isEmpty()) {
                return alternativeDecoderInfos;
            }
        }
        return MediaCodecUtil.getDecoderInfosSoftMatch(mediaCodecSelector, format, requiresSecureDecoder, requiresTunnelingDecoder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class Api26 {
        private Api26() {
        }

        public static boolean doesDisplaySupportDolbyVision(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Display display = displayManager != null ? displayManager.getDisplay(0) : null;
            if (display == null || !display.isHdr()) {
                return false;
            }
            int[] supportedHdrTypes = display.getHdrCapabilities().getSupportedHdrTypes();
            for (int hdrType : supportedHdrTypes) {
                if (hdrType == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onInit() {
        super.onInit();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onEnabled(boolean joining, boolean mayRenderStartOfStream) throws ExoPlaybackException {
        VideoSinkProvider videoSinkProvider;
        super.onEnabled(joining, mayRenderStartOfStream);
        boolean tunneling = getConfiguration().tunneling;
        Assertions.checkState((tunneling && this.tunnelingAudioSessionId == 0) ? false : true);
        if (this.tunneling != tunneling) {
            this.tunneling = tunneling;
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        if (!this.hasSetVideoSink) {
            if ((this.videoEffects != null || !this.ownsVideoSink) && this.videoSink == null) {
                if (this.videoSinkProvider != null) {
                    videoSinkProvider = this.videoSinkProvider;
                } else {
                    videoSinkProvider = new CompositingVideoSinkProvider.Builder(this.context, this.videoFrameReleaseControl).setClock(getClock()).build();
                }
                this.videoSink = videoSinkProvider.getSink();
            }
            this.hasSetVideoSink = true;
        }
        if (this.videoSink != null) {
            this.videoSink.setListener(new VideoSink.Listener() { // from class: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.1
                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onFirstFrameRendered(VideoSink videoSink) {
                    Assertions.checkStateNotNull(MediaCodecVideoRenderer.this.displaySurface);
                    MediaCodecVideoRenderer.this.notifyRenderedFirstFrame();
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onFrameDropped(VideoSink videoSink) {
                    MediaCodecVideoRenderer.this.updateDroppedBufferCounters(0, 1);
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onVideoSizeChanged(VideoSink videoSink, VideoSize videoSize) {
                }

                @Override // androidx.media3.exoplayer.video.VideoSink.Listener
                public void onError(VideoSink videoSink, VideoSink.VideoSinkException videoSinkException) {
                    MediaCodecVideoRenderer.this.setPendingPlaybackException(MediaCodecVideoRenderer.this.createRendererException(videoSinkException, videoSinkException.format, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED));
                }
            }, MoreExecutors.directExecutor());
            if (this.frameMetadataListener != null) {
                this.videoSink.setVideoFrameMetadataListener(this.frameMetadataListener);
            }
            if (this.displaySurface != null && !this.outputResolution.equals(Size.UNKNOWN)) {
                this.videoSink.setOutputSurfaceInfo(this.displaySurface, this.outputResolution);
            }
            this.videoSink.setPlaybackSpeed(getPlaybackSpeed());
            if (this.videoEffects != null) {
                this.videoSink.setVideoEffects(this.videoEffects);
            }
            this.videoSink.onRendererEnabled(mayRenderStartOfStream);
            return;
        }
        this.videoFrameReleaseControl.setClock(getClock());
        this.videoFrameReleaseControl.onEnabled(mayRenderStartOfStream);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void enableMayRenderStartOfStream() {
        if (this.videoSink != null) {
            this.videoSink.enableMayRenderStartOfStream();
        } else {
            this.videoFrameReleaseControl.allowReleaseFirstFrameBeforeStarted();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onPositionReset(long positionUs, boolean joining) throws ExoPlaybackException {
        if (this.videoSink != null) {
            this.videoSink.flush(true);
            this.videoSink.setStreamOffsetAndAdjustmentUs(getOutputStreamOffsetUs(), getBufferTimestampAdjustmentUs());
        }
        super.onPositionReset(positionUs, joining);
        if (this.videoSink == null) {
            this.videoFrameReleaseControl.reset();
        }
        if (joining) {
            this.videoFrameReleaseControl.join(false);
        }
        maybeSetupTunnelingForFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return super.isEnded() && (this.videoSink == null || this.videoSink.isEnded());
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        boolean readyToReleaseFrames = super.isReady() && (this.videoSink == null || this.videoSink.isReady());
        if (readyToReleaseFrames && ((this.placeholderSurface != null && this.displaySurface == this.placeholderSurface) || getCodec() == null || this.tunneling)) {
            return true;
        }
        return this.videoFrameReleaseControl.isReady(readyToReleaseFrames);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        long elapsedRealtimeMs = getClock().elapsedRealtime();
        this.droppedFrameAccumulationStartTimeMs = elapsedRealtimeMs;
        this.totalVideoFrameProcessingOffsetUs = 0L;
        this.videoFrameProcessingOffsetCount = 0;
        if (this.videoSink != null) {
            this.videoSink.onRendererStarted();
        } else {
            this.videoFrameReleaseControl.onStarted();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onStopped() {
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        if (this.videoSink != null) {
            this.videoSink.onRendererStopped();
        } else {
            this.videoFrameReleaseControl.onStopped();
        }
        super.onStopped();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onDisabled() {
        this.reportedVideoSize = null;
        if (this.videoSink != null) {
            this.videoSink.onRendererDisabled();
        } else {
            this.videoFrameReleaseControl.onDisabled();
        }
        maybeSetupTunnelingForFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
            this.eventDispatcher.videoSizeChanged(VideoSize.UNKNOWN);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer
    protected void onReset() {
        try {
            super.onReset();
        } finally {
            this.hasSetVideoSink = false;
            if (this.placeholderSurface != null) {
                releasePlaceholderSurface();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        super.onRelease();
        if (this.videoSink != null && this.ownsVideoSink) {
            this.videoSink.release();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int messageType, Object message) throws ExoPlaybackException {
        switch (messageType) {
            case 1:
                setOutput(message);
                return;
            case 4:
                this.scalingMode = ((Integer) Assertions.checkNotNull(message)).intValue();
                MediaCodecAdapter codec = getCodec();
                if (codec != null) {
                    codec.setVideoScalingMode(this.scalingMode);
                    return;
                }
                return;
            case 5:
                this.videoFrameReleaseControl.setChangeFrameRateStrategy(((Integer) Assertions.checkNotNull(message)).intValue());
                return;
            case 7:
                this.frameMetadataListener = (VideoFrameMetadataListener) Assertions.checkNotNull(message);
                if (this.videoSink != null) {
                    this.videoSink.setVideoFrameMetadataListener(this.frameMetadataListener);
                    return;
                }
                return;
            case 10:
                int tunnelingAudioSessionId = ((Integer) Assertions.checkNotNull(message)).intValue();
                if (this.tunnelingAudioSessionId != tunnelingAudioSessionId) {
                    this.tunnelingAudioSessionId = tunnelingAudioSessionId;
                    if (this.tunneling) {
                        releaseCodec();
                        return;
                    }
                    return;
                }
                return;
            case 13:
                List<Effect> videoEffects = (List) Assertions.checkNotNull(message);
                setVideoEffects(videoEffects);
                return;
            case 14:
                Size outputResolution = (Size) Assertions.checkNotNull(message);
                if (outputResolution.getWidth() != 0 && outputResolution.getHeight() != 0) {
                    this.outputResolution = outputResolution;
                    if (this.videoSink != null) {
                        this.videoSink.setOutputSurfaceInfo((Surface) Assertions.checkStateNotNull(this.displaySurface), outputResolution);
                        return;
                    }
                    return;
                }
                return;
            case 16:
                this.rendererPriority = ((Integer) Assertions.checkNotNull(message)).intValue();
                updateCodecImportance();
                return;
            default:
                super.handleMessage(messageType, message);
                return;
        }
    }

    private void setOutput(Object output) throws ExoPlaybackException {
        Surface displaySurface = output instanceof Surface ? (Surface) output : null;
        if (displaySurface == null) {
            if (this.placeholderSurface != null) {
                displaySurface = this.placeholderSurface;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUsePlaceholderSurface(codecInfo)) {
                    this.placeholderSurface = PlaceholderSurface.newInstance(this.context, codecInfo.secure);
                    displaySurface = this.placeholderSurface;
                }
            }
        }
        if (this.displaySurface != displaySurface) {
            this.displaySurface = displaySurface;
            if (this.videoSink == null) {
                this.videoFrameReleaseControl.setOutputSurface(displaySurface);
            }
            this.haveReportedFirstFrameRenderedForCurrentSurface = false;
            int state = getState();
            MediaCodecAdapter codec = getCodec();
            if (codec != null && this.videoSink == null) {
                if (Util.SDK_INT >= 23 && displaySurface != null && !this.codecNeedsSetOutputSurfaceWorkaround) {
                    setOutputSurfaceV23(codec, displaySurface);
                } else {
                    releaseCodec();
                    maybeInitCodecOrBypass();
                }
            }
            if (displaySurface != null && displaySurface != this.placeholderSurface) {
                maybeRenotifyVideoSizeChanged();
                if (state == 2) {
                    this.videoFrameReleaseControl.join(true);
                }
            } else {
                this.reportedVideoSize = null;
                if (this.videoSink != null) {
                    this.videoSink.clearOutputSurfaceInfo();
                }
            }
            maybeSetupTunnelingForFirstFrame();
            return;
        }
        if (displaySurface != null && displaySurface != this.placeholderSurface) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected boolean shouldInitCodec(MediaCodecInfo codecInfo) {
        return this.displaySurface != null || shouldUsePlaceholderSurface(codecInfo);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo codecInfo, Format format, MediaCrypto crypto, float codecOperatingRate) {
        if (this.placeholderSurface != null && this.placeholderSurface.secure != codecInfo.secure) {
            releasePlaceholderSurface();
        }
        String codecMimeType = codecInfo.codecMimeType;
        this.codecMaxValues = getCodecMaxValues(codecInfo, format, getStreamFormats());
        MediaFormat mediaFormat = getMediaFormat(format, codecMimeType, this.codecMaxValues, codecOperatingRate, this.deviceNeedsNoPostProcessWorkaround, this.tunneling ? this.tunnelingAudioSessionId : 0);
        if (this.displaySurface == null) {
            if (!shouldUsePlaceholderSurface(codecInfo)) {
                throw new IllegalStateException();
            }
            if (this.placeholderSurface == null) {
                this.placeholderSurface = PlaceholderSurface.newInstance(this.context, codecInfo.secure);
            }
            this.displaySurface = this.placeholderSurface;
        }
        maybeSetKeyAllowFrameDrop(mediaFormat);
        return MediaCodecAdapter.Configuration.createForVideoDecoding(codecInfo, mediaFormat, format, this.videoSink != null ? this.videoSink.getInputSurface() : this.displaySurface, crypto);
    }

    private void maybeSetKeyAllowFrameDrop(MediaFormat mediaFormat) {
        if (this.videoSink != null && !this.videoSink.isFrameDropAllowedOnInput()) {
            mediaFormat.setInteger("allow-frame-drop", 0);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected DecoderReuseEvaluation canReuseCodec(MediaCodecInfo codecInfo, Format oldFormat, Format newFormat) {
        DecoderReuseEvaluation evaluation = codecInfo.canReuseCodec(oldFormat, newFormat);
        int discardReasons = evaluation.discardReasons;
        CodecMaxValues codecMaxValues = (CodecMaxValues) Assertions.checkNotNull(this.codecMaxValues);
        if (newFormat.width > codecMaxValues.width || newFormat.height > codecMaxValues.height) {
            discardReasons |= 256;
        }
        if (getMaxInputSize(codecInfo, newFormat) > codecMaxValues.inputSize) {
            discardReasons |= 64;
        }
        return new DecoderReuseEvaluation(codecInfo.name, oldFormat, newFormat, discardReasons != 0 ? 0 : evaluation.result, discardReasons);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public void render(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        super.render(positionUs, elapsedRealtimeUs);
        if (this.videoSink != null) {
            try {
                this.videoSink.render(positionUs, elapsedRealtimeUs);
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, e.format, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.buffersInCodecCount = 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer, androidx.media3.exoplayer.Renderer
    public void setPlaybackSpeed(float currentPlaybackSpeed, float targetPlaybackSpeed) throws ExoPlaybackException {
        super.setPlaybackSpeed(currentPlaybackSpeed, targetPlaybackSpeed);
        if (this.videoSink != null) {
            this.videoSink.setPlaybackSpeed(currentPlaybackSpeed);
        } else {
            this.videoFrameReleaseControl.setPlaybackSpeed(currentPlaybackSpeed);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0064, code lost:
    
        if (r3.equals(androidx.media3.common.MimeTypes.VIDEO_MP4V) != false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getCodecMaxInputSize(androidx.media3.exoplayer.mediacodec.MediaCodecInfo r9, androidx.media3.common.Format r10) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.getCodecMaxInputSize(androidx.media3.exoplayer.mediacodec.MediaCodecInfo, androidx.media3.common.Format):int");
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected float getCodecOperatingRateV23(float targetPlaybackSpeed, Format format, Format[] streamFormats) {
        float maxFrameRate = -1.0f;
        for (Format streamFormat : streamFormats) {
            float streamFrameRate = streamFormat.frameRate;
            if (streamFrameRate != -1.0f) {
                maxFrameRate = Math.max(maxFrameRate, streamFrameRate);
            }
        }
        if (maxFrameRate == -1.0f) {
            return -1.0f;
        }
        return maxFrameRate * targetPlaybackSpeed;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onReadyToInitializeCodec(Format format) throws ExoPlaybackException {
        if (this.videoSink != null && !this.videoSink.isInitialized()) {
            try {
                this.videoSink.initialize(format);
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, format, 7000);
            }
        }
    }

    public void setVideoEffects(List<Effect> effects) {
        this.videoEffects = effects;
        if (this.videoSink != null) {
            this.videoSink.setVideoEffects(effects);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onCodecInitialized(String name, MediaCodecAdapter.Configuration configuration, long initializedTimestampMs, long initializationDurationMs) {
        this.eventDispatcher.decoderInitialized(name, initializedTimestampMs, initializationDurationMs);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(name);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
        maybeSetupTunnelingForFirstFrame();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onCodecReleased(String name) {
        this.eventDispatcher.decoderReleased(name);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onCodecError(Exception codecError) {
        Log.e(TAG, "Video codec error", codecError);
        this.eventDispatcher.videoCodecError(codecError);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation evaluation = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(formatHolder.format), evaluation);
        return evaluation;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onQueueInputBuffer(DecoderInputBuffer buffer) throws ExoPlaybackException {
        if (!this.tunneling) {
            this.buffersInCodecCount++;
        }
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(buffer.timeUs);
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected int getCodecBufferFlags(DecoderInputBuffer buffer) {
        if (Util.SDK_INT >= 34 && this.tunneling && buffer.timeUs < getLastResetPositionUs()) {
            return 32;
        }
        return 0;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onOutputFormatChanged(Format format, MediaFormat mediaFormat) {
        int width;
        int integer;
        int height;
        MediaCodecAdapter codec = getCodec();
        if (codec != null) {
            codec.setVideoScalingMode(this.scalingMode);
        }
        int unappliedRotationDegrees = 0;
        if (this.tunneling) {
            width = format.width;
            height = format.height;
        } else {
            Assertions.checkNotNull(mediaFormat);
            boolean hasCrop = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
            if (hasCrop) {
                width = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
            } else {
                width = mediaFormat.getInteger("width");
            }
            if (hasCrop) {
                integer = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
            } else {
                integer = mediaFormat.getInteger("height");
            }
            height = integer;
        }
        float pixelWidthHeightRatio = format.pixelWidthHeightRatio;
        if (codecAppliesRotation()) {
            if (format.rotationDegrees == 90 || format.rotationDegrees == 270) {
                int rotatedHeight = width;
                width = height;
                height = rotatedHeight;
                pixelWidthHeightRatio = 1.0f / pixelWidthHeightRatio;
            }
        } else if (this.videoSink == null) {
            unappliedRotationDegrees = format.rotationDegrees;
        }
        this.decodedVideoSize = new VideoSize(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
        if (this.videoSink != null) {
            onReadyToRegisterVideoSinkInputStream();
            this.videoSink.registerInputStream(1, format.buildUpon().setWidth(width).setHeight(height).setRotationDegrees(unappliedRotationDegrees).setPixelWidthHeightRatio(pixelWidthHeightRatio).build());
        } else {
            this.videoFrameReleaseControl.setFrameRate(format.frameRate);
        }
    }

    protected void onReadyToRegisterVideoSinkInputStream() {
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void handleInputBufferSupplementalData(DecoderInputBuffer buffer) throws ExoPlaybackException {
        if (!this.codecHandlesHdr10PlusOutOfBandMetadata) {
            return;
        }
        ByteBuffer data = (ByteBuffer) Assertions.checkNotNull(buffer.supplementalData);
        if (data.remaining() >= 7) {
            byte ituTT35CountryCode = data.get();
            int ituTT35TerminalProviderCode = data.getShort();
            int ituTT35TerminalProviderOrientedCode = data.getShort();
            byte applicationIdentifier = data.get();
            byte applicationVersion = data.get();
            data.position(0);
            if (ituTT35CountryCode == -75 && ituTT35TerminalProviderCode == 60 && ituTT35TerminalProviderOrientedCode == 1 && applicationIdentifier == 4) {
                if (applicationVersion == 0 || applicationVersion == 1) {
                    byte[] hdr10PlusInfo = new byte[data.remaining()];
                    data.get(hdr10PlusInfo);
                    data.position(0);
                    setHdr10PlusInfoV29((MediaCodecAdapter) Assertions.checkNotNull(getCodec()), hdr10PlusInfo);
                }
            }
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected boolean processOutputBuffer(long positionUs, long elapsedRealtimeUs, MediaCodecAdapter codec, ByteBuffer buffer, int bufferIndex, int bufferFlags, int sampleCount, long bufferPresentationTimeUs, boolean isDecodeOnlyBuffer, boolean isLastBuffer, Format format) throws ExoPlaybackException {
        Assertions.checkNotNull(codec);
        long outputStreamOffsetUs = getOutputStreamOffsetUs();
        long presentationTimeUs = bufferPresentationTimeUs - outputStreamOffsetUs;
        int frameReleaseAction = this.videoFrameReleaseControl.getFrameReleaseAction(bufferPresentationTimeUs, positionUs, elapsedRealtimeUs, getOutputStreamStartPositionUs(), isLastBuffer, this.videoFrameReleaseInfo);
        if (frameReleaseAction == 4) {
            return false;
        }
        if (!isDecodeOnlyBuffer || isLastBuffer) {
            if (this.displaySurface == this.placeholderSurface && this.videoSink == null) {
                if (this.videoFrameReleaseInfo.getEarlyUs() >= 30000) {
                    return false;
                }
                skipOutputBuffer(codec, bufferIndex, presentationTimeUs);
                updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                return true;
            }
            if (this.videoSink != null) {
                try {
                } catch (VideoSink.VideoSinkException e) {
                    e = e;
                }
                try {
                    this.videoSink.render(positionUs, elapsedRealtimeUs);
                    long releaseTimeNs = this.videoSink.registerInputFrame(bufferPresentationTimeUs + getBufferTimestampAdjustmentUs(), isLastBuffer);
                    if (releaseTimeNs == C.TIME_UNSET) {
                        return false;
                    }
                    renderOutputBuffer(codec, bufferIndex, presentationTimeUs, releaseTimeNs);
                    return true;
                } catch (VideoSink.VideoSinkException e2) {
                    e = e2;
                    throw createRendererException(e, e.format, PlaybackException.ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED);
                }
            }
            switch (frameReleaseAction) {
                case 0:
                    long releaseTimeNs2 = getClock().nanoTime();
                    notifyFrameMetadataListener(presentationTimeUs, releaseTimeNs2, format);
                    renderOutputBuffer(codec, bufferIndex, presentationTimeUs, releaseTimeNs2);
                    updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                    return true;
                case 1:
                    return maybeReleaseFrame((MediaCodecAdapter) Assertions.checkStateNotNull(codec), bufferIndex, presentationTimeUs, format);
                case 2:
                    dropOutputBuffer(codec, bufferIndex, presentationTimeUs);
                    updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                    return true;
                case 3:
                    skipOutputBuffer(codec, bufferIndex, presentationTimeUs);
                    updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                    return true;
                case 4:
                default:
                    throw new IllegalStateException(String.valueOf(frameReleaseAction));
                case 5:
                    return false;
            }
        }
        skipOutputBuffer(codec, bufferIndex, presentationTimeUs);
        return true;
    }

    protected long getBufferTimestampAdjustmentUs() {
        return 0L;
    }

    private boolean maybeReleaseFrame(MediaCodecAdapter codec, int bufferIndex, long presentationTimeUs, Format format) {
        long releaseTimeNs = this.videoFrameReleaseInfo.getReleaseTimeNs();
        long earlyUs = this.videoFrameReleaseInfo.getEarlyUs();
        if (Util.SDK_INT >= 21) {
            if (shouldSkipBuffersWithIdenticalReleaseTime() && releaseTimeNs == this.lastFrameReleaseTimeNs) {
                skipOutputBuffer(codec, bufferIndex, presentationTimeUs);
            } else {
                notifyFrameMetadataListener(presentationTimeUs, releaseTimeNs, format);
                renderOutputBufferV21(codec, bufferIndex, presentationTimeUs, releaseTimeNs);
            }
            updateVideoFrameProcessingOffsetCounters(earlyUs);
            this.lastFrameReleaseTimeNs = releaseTimeNs;
            return true;
        }
        if (earlyUs >= 30000) {
            return false;
        }
        if (earlyUs > 11000) {
            try {
                Thread.sleep((earlyUs - 10000) / 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        notifyFrameMetadataListener(presentationTimeUs, releaseTimeNs, format);
        renderOutputBuffer(codec, bufferIndex, presentationTimeUs);
        updateVideoFrameProcessingOffsetCounters(earlyUs);
        return true;
    }

    private void notifyFrameMetadataListener(long presentationTimeUs, long releaseTimeNs, Format format) {
        if (this.frameMetadataListener != null) {
            this.frameMetadataListener.onVideoFrameAboutToBeRendered(presentationTimeUs, releaseTimeNs, format, getCodecOutputMediaFormat());
        }
    }

    protected void onProcessedTunneledBuffer(long presentationTimeUs) throws ExoPlaybackException {
        updateOutputFormatForTime(presentationTimeUs);
        maybeNotifyVideoSizeChanged(this.decodedVideoSize);
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(presentationTimeUs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onProcessedOutputBuffer(long presentationTimeUs) {
        super.onProcessedOutputBuffer(presentationTimeUs);
        if (!this.tunneling) {
            this.buffersInCodecCount--;
        }
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        if (this.videoSink != null) {
            this.videoSink.setStreamOffsetAndAdjustmentUs(getOutputStreamOffsetUs(), getBufferTimestampAdjustmentUs());
        } else {
            this.videoFrameReleaseControl.onProcessedStreamChange();
        }
        maybeSetupTunnelingForFirstFrame();
    }

    protected boolean shouldDropOutputBuffer(long earlyUs, long elapsedRealtimeUs, boolean isLastBuffer) {
        return earlyUs < MIN_EARLY_US_LATE_THRESHOLD && !isLastBuffer;
    }

    protected boolean shouldDropBuffersToKeyframe(long earlyUs, long elapsedRealtimeUs, boolean isLastBuffer) {
        return earlyUs < MIN_EARLY_US_VERY_LATE_THRESHOLD && !isLastBuffer;
    }

    protected boolean shouldSkipBuffersWithIdenticalReleaseTime() {
        return true;
    }

    protected boolean shouldForceRenderOutputBuffer(long earlyUs, long elapsedSinceLastRenderUs) {
        return earlyUs < MIN_EARLY_US_LATE_THRESHOLD && elapsedSinceLastRenderUs > 100000;
    }

    protected void skipOutputBuffer(MediaCodecAdapter codec, int index, long presentationTimeUs) {
        TraceUtil.beginSection("skipVideoBuffer");
        codec.releaseOutputBuffer(index, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    protected void dropOutputBuffer(MediaCodecAdapter codec, int index, long presentationTimeUs) {
        TraceUtil.beginSection("dropVideoBuffer");
        codec.releaseOutputBuffer(index, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    protected boolean maybeDropBuffersToKeyframe(long positionUs, boolean treatDroppedBuffersAsSkipped) throws ExoPlaybackException {
        int droppedSourceBufferCount = skipSource(positionUs);
        if (droppedSourceBufferCount == 0) {
            return false;
        }
        if (treatDroppedBuffersAsSkipped) {
            this.decoderCounters.skippedInputBufferCount += droppedSourceBufferCount;
            this.decoderCounters.skippedOutputBufferCount += this.buffersInCodecCount;
        } else {
            this.decoderCounters.droppedToKeyframeCount++;
            updateDroppedBufferCounters(droppedSourceBufferCount, this.buffersInCodecCount);
        }
        flushOrReinitializeCodec();
        if (this.videoSink != null) {
            this.videoSink.flush(false);
        }
        return true;
    }

    protected void updateDroppedBufferCounters(int droppedInputBufferCount, int droppedDecoderBufferCount) {
        this.decoderCounters.droppedInputBufferCount += droppedInputBufferCount;
        int totalDroppedBufferCount = droppedInputBufferCount + droppedDecoderBufferCount;
        this.decoderCounters.droppedBufferCount += totalDroppedBufferCount;
        this.droppedFrames += totalDroppedBufferCount;
        this.consecutiveDroppedFrameCount += totalDroppedBufferCount;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        if (this.maxDroppedFramesToNotify > 0 && this.droppedFrames >= this.maxDroppedFramesToNotify) {
            maybeNotifyDroppedFrames();
        }
    }

    protected void updateVideoFrameProcessingOffsetCounters(long processingOffsetUs) {
        this.decoderCounters.addVideoFrameProcessingOffset(processingOffsetUs);
        this.totalVideoFrameProcessingOffsetUs += processingOffsetUs;
        this.videoFrameProcessingOffsetCount++;
    }

    private void renderOutputBuffer(MediaCodecAdapter codec, int index, long presentationTimeUs, long releaseTimeNs) {
        if (Util.SDK_INT >= 21) {
            renderOutputBufferV21(codec, index, presentationTimeUs, releaseTimeNs);
        } else {
            renderOutputBuffer(codec, index, presentationTimeUs);
        }
    }

    protected void renderOutputBuffer(MediaCodecAdapter codec, int index, long presentationTimeUs) {
        TraceUtil.beginSection("releaseOutputBuffer");
        codec.releaseOutputBuffer(index, true);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    protected void renderOutputBufferV21(MediaCodecAdapter codec, int index, long presentationTimeUs, long releaseTimeNs) {
        TraceUtil.beginSection("releaseOutputBuffer");
        codec.releaseOutputBuffer(index, releaseTimeNs);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    private boolean shouldUsePlaceholderSurface(MediaCodecInfo codecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(codecInfo.name) && (!codecInfo.secure || PlaceholderSurface.isSecureSupported(this.context));
    }

    private void releasePlaceholderSurface() {
        if (this.displaySurface == this.placeholderSurface) {
            this.displaySurface = null;
        }
        if (this.placeholderSurface != null) {
            this.placeholderSurface.release();
            this.placeholderSurface = null;
        }
    }

    private void maybeSetupTunnelingForFirstFrame() {
        MediaCodecAdapter codec;
        if (!this.tunneling || Util.SDK_INT < 23 || (codec = getCodec()) == null) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        if (Util.SDK_INT >= 33) {
            Bundle codecParameters = new Bundle();
            codecParameters.putInt("tunnel-peek", 1);
            codec.setParameters(codecParameters);
        }
    }

    private void updateCodecImportance() {
        MediaCodecAdapter codec = getCodec();
        if (codec != null && Util.SDK_INT >= 35) {
            Bundle codecParameters = new Bundle();
            codecParameters.putInt("importance", Math.max(0, -this.rendererPriority));
            codec.setParameters(codecParameters);
        }
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame() && this.displaySurface != null) {
            notifyRenderedFirstFrame();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresNonNull({"displaySurface"})
    public void notifyRenderedFirstFrame() {
        this.eventDispatcher.renderedFirstFrame(this.displaySurface);
        this.haveReportedFirstFrameRenderedForCurrentSurface = true;
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.displaySurface != null && this.haveReportedFirstFrameRenderedForCurrentSurface) {
            this.eventDispatcher.renderedFirstFrame(this.displaySurface);
        }
    }

    private void maybeNotifyVideoSizeChanged(VideoSize newOutputSize) {
        if (!newOutputSize.equals(VideoSize.UNKNOWN) && !newOutputSize.equals(this.reportedVideoSize)) {
            this.reportedVideoSize = newOutputSize;
            this.eventDispatcher.videoSizeChanged(this.reportedVideoSize);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedVideoSize != null) {
            this.eventDispatcher.videoSizeChanged(this.reportedVideoSize);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long now = getClock().elapsedRealtime();
            long elapsedMs = now - this.droppedFrameAccumulationStartTimeMs;
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = now;
        }
    }

    private void maybeNotifyVideoFrameProcessingOffset() {
        if (this.videoFrameProcessingOffsetCount != 0) {
            this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, this.videoFrameProcessingOffsetCount);
            this.totalVideoFrameProcessingOffsetUs = 0L;
            this.videoFrameProcessingOffsetCount = 0;
        }
    }

    private static void setHdr10PlusInfoV29(MediaCodecAdapter codec, byte[] hdr10PlusInfo) {
        Bundle codecParameters = new Bundle();
        codecParameters.putByteArray("hdr10-plus-info", hdr10PlusInfo);
        codec.setParameters(codecParameters);
    }

    protected void setOutputSurfaceV23(MediaCodecAdapter codec, Surface surface) {
        codec.setOutputSurface(surface);
    }

    private static void configureTunnelingV21(MediaFormat mediaFormat, int tunnelingAudioSessionId) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", tunnelingAudioSessionId);
    }

    protected MediaFormat getMediaFormat(Format format, String codecMimeType, CodecMaxValues codecMaxValues, float codecOperatingRate, boolean deviceNeedsNoPostProcessWorkaround, int tunnelingAudioSessionId) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", codecMimeType);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues.width);
        mediaFormat.setInteger("max-height", codecMaxValues.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (codecOperatingRate != -1.0f) {
                mediaFormat.setFloat("operating-rate", codecOperatingRate);
            }
        }
        if (deviceNeedsNoPostProcessWorkaround) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (tunnelingAudioSessionId != 0) {
            configureTunnelingV21(mediaFormat, tunnelingAudioSessionId);
        }
        if (Util.SDK_INT >= 35) {
            mediaFormat.setInteger("importance", Math.max(0, -this.rendererPriority));
        }
        return mediaFormat;
    }

    protected CodecMaxValues getCodecMaxValues(MediaCodecInfo codecInfo, Format format, Format[] streamFormats) {
        int codecMaxInputSize;
        int maxWidth = format.width;
        int maxHeight = format.height;
        int maxInputSize = getMaxInputSize(codecInfo, format);
        if (streamFormats.length == 1) {
            if (maxInputSize != -1 && (codecMaxInputSize = getCodecMaxInputSize(codecInfo, format)) != -1) {
                int scaledMaxInputSize = (int) (maxInputSize * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR);
                maxInputSize = Math.min(scaledMaxInputSize, codecMaxInputSize);
            }
            return new CodecMaxValues(maxWidth, maxHeight, maxInputSize);
        }
        boolean haveUnknownDimensions = false;
        int length = streamFormats.length;
        for (int i = 0; i < length; i++) {
            Format streamFormat = streamFormats[i];
            if (format.colorInfo != null && streamFormat.colorInfo == null) {
                streamFormat = streamFormat.buildUpon().setColorInfo(format.colorInfo).build();
            }
            if (codecInfo.canReuseCodec(format, streamFormat).result != 0) {
                haveUnknownDimensions |= streamFormat.width == -1 || streamFormat.height == -1;
                maxWidth = Math.max(maxWidth, streamFormat.width);
                maxHeight = Math.max(maxHeight, streamFormat.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(codecInfo, streamFormat));
            }
        }
        if (haveUnknownDimensions) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + maxWidth + "x" + maxHeight);
            Point codecMaxSize = getCodecMaxSize(codecInfo, format);
            if (codecMaxSize != null) {
                maxWidth = Math.max(maxWidth, codecMaxSize.x);
                maxHeight = Math.max(maxHeight, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(codecInfo, format.buildUpon().setWidth(maxWidth).setHeight(maxHeight).build()));
                Log.w(TAG, "Codec max resolution adjusted to: " + maxWidth + "x" + maxHeight);
            }
        }
        return new CodecMaxValues(maxWidth, maxHeight, maxInputSize);
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecRenderer
    protected MediaCodecDecoderException createDecoderException(Throwable cause, MediaCodecInfo codecInfo) {
        return new MediaCodecVideoDecoderException(cause, codecInfo, this.displaySurface);
    }

    private static Point getCodecMaxSize(MediaCodecInfo codecInfo, Format format) {
        int formatLongEdgePx;
        int formatShortEdgePx;
        int i = 0;
        boolean isVerticalVideo = format.height > format.width;
        int formatLongEdgePx2 = isVerticalVideo ? format.height : format.width;
        int formatShortEdgePx2 = isVerticalVideo ? format.width : format.height;
        float aspectRatio = formatShortEdgePx2 / formatLongEdgePx2;
        int[] iArr = STANDARD_LONG_EDGE_VIDEO_PX;
        int length = iArr.length;
        while (i < length) {
            int longEdgePx = iArr[i];
            int shortEdgePx = (int) (longEdgePx * aspectRatio);
            if (longEdgePx > formatLongEdgePx2 && shortEdgePx > formatShortEdgePx2) {
                if (Util.SDK_INT >= 21) {
                    Point alignedSize = codecInfo.alignVideoSizeV21(isVerticalVideo ? shortEdgePx : longEdgePx, isVerticalVideo ? longEdgePx : shortEdgePx);
                    float frameRate = format.frameRate;
                    if (alignedSize == null) {
                        formatLongEdgePx = formatLongEdgePx2;
                        formatShortEdgePx = formatShortEdgePx2;
                    } else {
                        formatLongEdgePx = formatLongEdgePx2;
                        formatShortEdgePx = formatShortEdgePx2;
                        if (codecInfo.isVideoSizeAndRateSupportedV21(alignedSize.x, alignedSize.y, frameRate)) {
                            return alignedSize;
                        }
                    }
                } else {
                    formatLongEdgePx = formatLongEdgePx2;
                    formatShortEdgePx = formatShortEdgePx2;
                    try {
                        int longEdgePx2 = Util.ceilDivide(longEdgePx, 16) * 16;
                        int shortEdgePx2 = Util.ceilDivide(shortEdgePx, 16) * 16;
                        if (longEdgePx2 * shortEdgePx2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                            return new Point(isVerticalVideo ? shortEdgePx2 : longEdgePx2, isVerticalVideo ? longEdgePx2 : shortEdgePx2);
                        }
                    } catch (MediaCodecUtil.DecoderQueryException e) {
                        return null;
                    }
                }
                i++;
                formatLongEdgePx2 = formatLongEdgePx;
                formatShortEdgePx2 = formatShortEdgePx;
            }
            return null;
        }
        return null;
    }

    protected static int getMaxInputSize(MediaCodecInfo codecInfo, Format format) {
        if (format.maxInputSize != -1) {
            int totalInitializationDataSize = 0;
            int initializationDataCount = format.initializationData.size();
            for (int i = 0; i < initializationDataCount; i++) {
                totalInitializationDataSize += format.initializationData.get(i).length;
            }
            int i2 = format.maxInputSize;
            return i2 + totalInitializationDataSize;
        }
        int totalInitializationDataSize2 = getCodecMaxInputSize(codecInfo, format);
        return totalInitializationDataSize2;
    }

    private static boolean codecAppliesRotation() {
        return Util.SDK_INT >= 21;
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    protected boolean codecNeedsSetOutputSurfaceWorkaround(String name) {
        if (name.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();
                evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    protected Surface getSurface() {
        return this.displaySurface;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes7.dex */
    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int width, int height, int inputSize) {
            this.width = width;
            this.height = height;
            this.inputSize = inputSize;
        }
    }

    private static int getMaxSampleSize(int pixelCount, int minCompressionRatio) {
        return (pixelCount * 3) / (minCompressionRatio * 2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x0650, code lost:
    
        if (r0.equals("A10-70F") != false) goto L499;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        /*
            Method dump skipped, instructions count: 2914
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.evaluateDeviceNeedsSetOutputSurfaceWorkaround():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        private static final int HANDLE_FRAME_RENDERED = 0;
        private final Handler handler = Util.createHandlerForCurrentLooper(this);

        public OnFrameRenderedListenerV23(MediaCodecAdapter codec) {
            codec.setOnFrameRenderedListener(this, this.handler);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.OnFrameRenderedListener
        public void onFrameRendered(MediaCodecAdapter codec, long presentationTimeUs, long nanoTime) {
            if (Util.SDK_INT < 30) {
                Message message = Message.obtain(this.handler, 0, (int) (presentationTimeUs >> 32), (int) presentationTimeUs);
                this.handler.sendMessageAtFrontOfQueue(message);
            } else {
                handleFrameRendered(presentationTimeUs);
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    handleFrameRendered(Util.toLong(message.arg1, message.arg2));
                    return true;
                default:
                    return false;
            }
        }

        private void handleFrameRendered(long presentationTimeUs) {
            if (this != MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener || MediaCodecVideoRenderer.this.getCodec() == null) {
                return;
            }
            if (presentationTimeUs == Long.MAX_VALUE) {
                MediaCodecVideoRenderer.this.onProcessedTunneledEndOfStream();
                return;
            }
            try {
                MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(presentationTimeUs);
            } catch (ExoPlaybackException e) {
                MediaCodecVideoRenderer.this.setPendingPlaybackException(e);
            }
        }
    }
}
