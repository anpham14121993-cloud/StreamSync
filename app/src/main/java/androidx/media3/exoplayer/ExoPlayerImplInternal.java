package androidx.media3.exoplayer;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.exoplayer.DefaultMediaClock;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.MediaPeriodHolder;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender {
    private static final long BUFFERING_MAXIMUM_INTERVAL_MS = Util.usToMs(10000);
    private static final int MSG_ADD_MEDIA_SOURCES = 18;
    private static final int MSG_ATTEMPT_RENDERER_ERROR_RECOVERY = 25;
    private static final int MSG_DO_SOME_WORK = 2;
    private static final int MSG_MOVE_MEDIA_SOURCES = 19;
    private static final int MSG_PERIOD_PREPARED = 8;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PLAYLIST_UPDATE_REQUESTED = 22;
    private static final int MSG_PREPARE = 29;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_REMOVE_MEDIA_SOURCES = 20;
    private static final int MSG_RENDERER_CAPABILITIES_CHANGED = 26;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_FOREGROUND_MODE = 13;
    private static final int MSG_SET_MEDIA_SOURCES = 17;
    private static final int MSG_SET_PAUSE_AT_END_OF_WINDOW = 23;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_PRELOAD_CONFIGURATION = 28;
    private static final int MSG_SET_REPEAT_MODE = 11;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 12;
    private static final int MSG_SET_SHUFFLE_ORDER = 21;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 9;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 10;
    private static final int MSG_UPDATE_MEDIA_SOURCES_WITH_MEDIA_ITEMS = 27;
    private static final long PLAYBACK_BUFFER_EMPTY_THRESHOLD_US = 500000;
    private static final long PLAYBACK_STUCK_AFTER_MS = 4000;
    private static final long READY_MAXIMUM_INTERVAL_MS = 1000;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    private final boolean dynamicSchedulingEnabled;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private int enabledRendererCount;
    private boolean foregroundMode;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private boolean isRebuffering;
    private final LivePlaybackSpeedControl livePlaybackSpeedControl;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private final MediaSourceList mediaSourceList;
    private int nextPendingMessageIndexHint;
    private boolean offloadSchedulingEnabled;
    private boolean pauseAtEndOfWindow;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private boolean pendingPauseAtEndOfPeriod;
    private ExoPlaybackException pendingRecoverableRendererError;
    private final Timeline.Period period;
    private PlaybackInfo playbackInfo;
    private PlaybackInfoUpdate playbackInfoUpdate;
    private final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private final Looper playbackLooper;
    private final PlayerId playerId;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private final MediaPeriodQueue queue;
    private final long releaseTimeoutMs;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionElapsedRealtimeUs;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private final Set<Renderer> renderersToReset;
    private int repeatMode;
    private boolean requestForRendererSleep;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private long setForegroundModeTimeoutMs;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;
    private long playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
    private long lastRebufferRealtimeMs = C.TIME_UNSET;
    private Timeline lastPreloadPoolInvalidationTimeline = Timeline.EMPTY;

    /* loaded from: classes4.dex */
    public interface PlaybackInfoUpdateListener {
        void onPlaybackInfoUpdate(PlaybackInfoUpdate playbackInfoUpdate);
    }

    /* loaded from: classes4.dex */
    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        private boolean hasPendingChange;
        public int operationAcks;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo) {
            this.playbackInfo = playbackInfo;
        }

        public void incrementPendingOperationAcks(int operationAcks) {
            this.hasPendingChange |= operationAcks > 0;
            this.operationAcks += operationAcks;
        }

        public void setPlaybackInfo(PlaybackInfo playbackInfo) {
            this.hasPendingChange |= this.playbackInfo != playbackInfo;
            this.playbackInfo = playbackInfo;
        }

        public void setPositionDiscontinuity(int discontinuityReason) {
            if (this.positionDiscontinuity && this.discontinuityReason != 5) {
                Assertions.checkArgument(discontinuityReason == 5);
                return;
            }
            this.hasPendingChange = true;
            this.positionDiscontinuity = true;
            this.discontinuityReason = discontinuityReason;
        }
    }

    public ExoPlayerImplInternal(Renderer[] renderers, TrackSelector trackSelector, TrackSelectorResult emptyTrackSelectorResult, LoadControl loadControl, BandwidthMeter bandwidthMeter, int repeatMode, boolean shuffleModeEnabled, AnalyticsCollector analyticsCollector, SeekParameters seekParameters, LivePlaybackSpeedControl livePlaybackSpeedControl, long releaseTimeoutMs, boolean pauseAtEndOfWindow, boolean dynamicSchedulingEnabled, Looper applicationLooper, Clock clock, PlaybackInfoUpdateListener playbackInfoUpdateListener, PlayerId playerId, Looper playbackLooper, ExoPlayer.PreloadConfiguration preloadConfiguration) {
        this.playbackInfoUpdateListener = playbackInfoUpdateListener;
        this.renderers = renderers;
        this.trackSelector = trackSelector;
        this.emptyTrackSelectorResult = emptyTrackSelectorResult;
        this.loadControl = loadControl;
        this.bandwidthMeter = bandwidthMeter;
        this.repeatMode = repeatMode;
        this.shuffleModeEnabled = shuffleModeEnabled;
        this.seekParameters = seekParameters;
        this.livePlaybackSpeedControl = livePlaybackSpeedControl;
        this.releaseTimeoutMs = releaseTimeoutMs;
        this.setForegroundModeTimeoutMs = releaseTimeoutMs;
        this.pauseAtEndOfWindow = pauseAtEndOfWindow;
        this.dynamicSchedulingEnabled = dynamicSchedulingEnabled;
        this.clock = clock;
        this.playerId = playerId;
        this.preloadConfiguration = preloadConfiguration;
        this.backBufferDurationUs = loadControl.getBackBufferDurationUs(playerId);
        this.retainBackBufferFromKeyframe = loadControl.retainBackBufferFromKeyframe(playerId);
        this.playbackInfo = PlaybackInfo.createDummy(emptyTrackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        this.rendererCapabilities = new RendererCapabilities[renderers.length];
        RendererCapabilities.Listener rendererCapabilitiesListener = trackSelector.getRendererCapabilitiesListener();
        for (int i = 0; i < renderers.length; i++) {
            renderers[i].init(i, playerId, clock);
            this.rendererCapabilities[i] = renderers[i].getCapabilities();
            if (rendererCapabilitiesListener != null) {
                this.rendererCapabilities[i].setListener(rendererCapabilitiesListener);
            }
        }
        this.mediaClock = new DefaultMediaClock(this, clock);
        this.pendingMessages = new ArrayList<>();
        this.renderersToReset = Sets.newIdentityHashSet();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector.init(this, bandwidthMeter);
        this.deliverPendingMessageAtStartPositionRequired = true;
        HandlerWrapper eventHandler = clock.createHandler(applicationLooper, null);
        this.queue = new MediaPeriodQueue(analyticsCollector, eventHandler, new MediaPeriodHolder.Factory() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda2
            @Override // androidx.media3.exoplayer.MediaPeriodHolder.Factory
            public final MediaPeriodHolder create(MediaPeriodInfo mediaPeriodInfo, long j) {
                MediaPeriodHolder createMediaPeriodHolder;
                createMediaPeriodHolder = ExoPlayerImplInternal.this.createMediaPeriodHolder(mediaPeriodInfo, j);
                return createMediaPeriodHolder;
            }
        }, preloadConfiguration);
        this.mediaSourceList = new MediaSourceList(this, analyticsCollector, eventHandler, playerId);
        if (playbackLooper != null) {
            this.internalPlaybackThread = null;
            this.playbackLooper = playbackLooper;
        } else {
            this.internalPlaybackThread = new HandlerThread("ExoPlayer:Playback", -16);
            this.internalPlaybackThread.start();
            this.playbackLooper = this.internalPlaybackThread.getLooper();
        }
        this.handler = clock.createHandler(this.playbackLooper, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaPeriodHolder createMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, long rendererPositionOffsetUs) {
        return new MediaPeriodHolder(this.rendererCapabilities, rendererPositionOffsetUs, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, mediaPeriodInfo, this.emptyTrackSelectorResult);
    }

    public void experimentalSetForegroundModeTimeoutMs(long setForegroundModeTimeoutMs) {
        this.setForegroundModeTimeoutMs = setForegroundModeTimeoutMs;
    }

    public void prepare() {
        this.handler.obtainMessage(29).sendToTarget();
    }

    public void setPlayWhenReady(boolean z, int i, int i2) {
        this.handler.obtainMessage(1, z ? 1 : 0, (i2 << 4) | i).sendToTarget();
    }

    public void setPauseAtEndOfWindow(boolean z) {
        this.handler.obtainMessage(23, z ? 1 : 0, 0).sendToTarget();
    }

    public void setRepeatMode(int repeatMode) {
        this.handler.obtainMessage(11, repeatMode, 0).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z) {
        this.handler.obtainMessage(12, z ? 1 : 0, 0).sendToTarget();
    }

    public void setPreloadConfiguration(ExoPlayer.PreloadConfiguration preloadConfiguration) {
        this.handler.obtainMessage(28, preloadConfiguration).sendToTarget();
    }

    public void seekTo(Timeline timeline, int windowIndex, long positionUs) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, windowIndex, positionUs)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters) {
        this.handler.obtainMessage(5, seekParameters).sendToTarget();
    }

    public void stop() {
        this.handler.obtainMessage(6).sendToTarget();
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> mediaSources, int windowIndex, long positionUs, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(mediaSources, shuffleOrder, windowIndex, positionUs)).sendToTarget();
    }

    public void addMediaSources(int index, List<MediaSourceList.MediaSourceHolder> mediaSources, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(18, index, 0, new MediaSourceListUpdateMessage(mediaSources, shuffleOrder, -1, C.TIME_UNSET)).sendToTarget();
    }

    public void removeMediaSources(int fromIndex, int toIndex, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(20, fromIndex, toIndex, shuffleOrder).sendToTarget();
    }

    public void moveMediaSources(int fromIndex, int toIndex, int newFromIndex, ShuffleOrder shuffleOrder) {
        MoveMediaItemsMessage moveMediaItemsMessage = new MoveMediaItemsMessage(fromIndex, toIndex, newFromIndex, shuffleOrder);
        this.handler.obtainMessage(19, moveMediaItemsMessage).sendToTarget();
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(21, shuffleOrder).sendToTarget();
    }

    public void updateMediaSourcesWithMediaItems(int fromIndex, int toIndex, List<MediaItem> mediaItems) {
        this.handler.obtainMessage(27, fromIndex, toIndex, mediaItems).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Sender
    public synchronized void sendMessage(PlayerMessage message) {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            this.handler.obtainMessage(14, message).sendToTarget();
            return;
        }
        Log.w(TAG, "Ignoring messages sent after release.");
        message.markAsProcessed(false);
    }

    public synchronized boolean setForegroundMode(boolean foregroundMode) {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            if (foregroundMode) {
                this.handler.obtainMessage(13, 1, 0).sendToTarget();
                return true;
            }
            final AtomicBoolean processedFlag = new AtomicBoolean();
            this.handler.obtainMessage(13, 0, 0, processedFlag).sendToTarget();
            Objects.requireNonNull(processedFlag);
            waitUninterruptibly(new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda3
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    boolean z;
                    z = processedFlag.get();
                    return Boolean.valueOf(z);
                }
            }, this.setForegroundModeTimeoutMs);
            return processedFlag.get();
        }
        return true;
    }

    public synchronized boolean release() {
        if (!this.released && this.playbackLooper.getThread().isAlive()) {
            this.handler.sendEmptyMessage(7);
            waitUninterruptibly(new Supplier() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    Boolean lambda$release$0;
                    lambda$release$0 = ExoPlayerImplInternal.this.lambda$release$0();
                    return lambda$release$0;
                }
            }, this.releaseTimeoutMs);
            return this.released;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$release$0() {
        return Boolean.valueOf(this.released);
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    @Override // androidx.media3.exoplayer.MediaSourceList.MediaSourceListInfoRefreshListener
    public void onPlaylistUpdateRequested() {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessage(22);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod source) {
        this.handler.obtainMessage(8, source).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod source) {
        this.handler.obtainMessage(9, source).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onRendererCapabilitiesChanged(Renderer renderer) {
        this.handler.sendEmptyMessage(26);
    }

    @Override // androidx.media3.exoplayer.DefaultMediaClock.PlaybackParametersListener
    public void onPlaybackParametersChanged(PlaybackParameters newPlaybackParameters) {
        this.handler.obtainMessage(16, newPlaybackParameters).sendToTarget();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        int errorCode;
        ExoPlaybackException e;
        MediaPeriodHolder readingPeriod;
        int errorCode2;
        try {
            switch (msg.what) {
                case 1:
                    setPlayWhenReadyInternal(msg.arg1 != 0, msg.arg2 >> 4, true, msg.arg2 & 15);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) msg.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) msg.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) msg.obj);
                    break;
                case 6:
                    stopInternal(false, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) msg.obj);
                    break;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) msg.obj);
                    break;
                case 10:
                    reselectTracksInternal();
                    break;
                case 11:
                    setRepeatModeInternal(msg.arg1);
                    break;
                case 12:
                    setShuffleModeEnabledInternal(msg.arg1 != 0);
                    break;
                case 13:
                    setForegroundModeInternal(msg.arg1 != 0, (AtomicBoolean) msg.obj);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) msg.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) msg.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) msg.obj, false);
                    break;
                case 17:
                    setMediaItemsInternal((MediaSourceListUpdateMessage) msg.obj);
                    break;
                case 18:
                    addMediaItemsInternal((MediaSourceListUpdateMessage) msg.obj, msg.arg1);
                    break;
                case 19:
                    moveMediaItemsInternal((MoveMediaItemsMessage) msg.obj);
                    break;
                case 20:
                    removeMediaItemsInternal(msg.arg1, msg.arg2, (ShuffleOrder) msg.obj);
                    break;
                case 21:
                    setShuffleOrderInternal((ShuffleOrder) msg.obj);
                    break;
                case 22:
                    mediaSourceListUpdateRequestedInternal();
                    break;
                case 23:
                    setPauseAtEndOfWindowInternal(msg.arg1 != 0);
                    break;
                case 24:
                default:
                    return false;
                case 25:
                    attemptRendererErrorRecovery();
                    break;
                case 26:
                    reselectTracksInternalAndSeek();
                    break;
                case 27:
                    updateMediaSourcesWithMediaItemsInternal(msg.arg1, msg.arg2, (List) msg.obj);
                    break;
                case 28:
                    setPreloadConfigurationInternal((ExoPlayer.PreloadConfiguration) msg.obj);
                    break;
                case 29:
                    prepareInternal();
                    break;
            }
        } catch (ParserException e2) {
            if (e2.dataType == 1) {
                if (e2.contentIsMalformed) {
                    errorCode2 = PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED;
                } else {
                    errorCode2 = PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED;
                }
            } else {
                int errorCode3 = e2.dataType;
                if (errorCode3 == 4) {
                    if (e2.contentIsMalformed) {
                        errorCode2 = PlaybackException.ERROR_CODE_PARSING_MANIFEST_MALFORMED;
                    } else {
                        errorCode2 = PlaybackException.ERROR_CODE_PARSING_MANIFEST_UNSUPPORTED;
                    }
                } else {
                    errorCode2 = 1000;
                }
            }
            handleIoException(e2, errorCode2);
        } catch (DataSourceException e3) {
            handleIoException(e3, e3.reason);
        } catch (ExoPlaybackException e4) {
            ExoPlaybackException e5 = e4;
            if (e5.type == 1 && (readingPeriod = this.queue.getReadingPeriod()) != null) {
                e5 = e5.copyWithMediaPeriodId(readingPeriod.info.id);
            }
            if (e5.isRecoverable && (this.pendingRecoverableRendererError == null || e5.errorCode == 5004 || e5.errorCode == 5003)) {
                Log.w(TAG, "Recoverable renderer error", e5);
                if (this.pendingRecoverableRendererError != null) {
                    this.pendingRecoverableRendererError.addSuppressed(e5);
                    e5 = this.pendingRecoverableRendererError;
                } else {
                    this.pendingRecoverableRendererError = e5;
                }
                this.handler.sendMessageAtFrontOfQueue(this.handler.obtainMessage(25, e5));
            } else {
                if (this.pendingRecoverableRendererError == null) {
                    e = e5;
                } else {
                    this.pendingRecoverableRendererError.addSuppressed(e5);
                    e = this.pendingRecoverableRendererError;
                }
                Log.e(TAG, "Playback error", e);
                if (e.type == 1 && this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                    while (this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                        this.queue.advancePlayingPeriod();
                    }
                    MediaPeriodHolder newPlayingPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getPlayingPeriod());
                    maybeNotifyPlaybackInfoChanged();
                    this.playbackInfo = handlePositionDiscontinuity(newPlayingPeriodHolder.info.id, newPlayingPeriodHolder.info.startPositionUs, newPlayingPeriodHolder.info.requestedContentPositionUs, newPlayingPeriodHolder.info.startPositionUs, true, 0);
                }
                stopInternal(true, false);
                this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
            }
        } catch (DrmSession.DrmSessionException e6) {
            handleIoException(e6, e6.errorCode);
        } catch (BehindLiveWindowException e7) {
            handleIoException(e7, 1002);
        } catch (IOException e8) {
            handleIoException(e8, 2000);
        } catch (RuntimeException e9) {
            if ((e9 instanceof IllegalStateException) || (e9 instanceof IllegalArgumentException)) {
                errorCode = 1004;
            } else {
                errorCode = 1000;
            }
            ExoPlaybackException error = ExoPlaybackException.createForUnexpected(e9, errorCode);
            Log.e(TAG, "Playback error", error);
            stopInternal(true, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(error);
        }
        maybeNotifyPlaybackInfoChanged();
        return true;
    }

    private void handleIoException(IOException e, int errorCode) {
        ExoPlaybackException error = ExoPlaybackException.createForSource(e, errorCode);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            error = error.copyWithMediaPeriodId(playingPeriod.info.id);
        }
        Log.e(TAG, "Playback error", error);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(error);
    }

    private synchronized void waitUninterruptibly(Supplier<Boolean> condition, long timeoutMs) {
        long deadlineMs = this.clock.elapsedRealtime() + timeoutMs;
        long remainingMs = timeoutMs;
        boolean wasInterrupted = false;
        while (!condition.get().booleanValue() && remainingMs > 0) {
            try {
                this.clock.onThreadBlocked();
                wait(remainingMs);
            } catch (InterruptedException e) {
                wasInterrupted = true;
            }
            remainingMs = deadlineMs - this.clock.elapsedRealtime();
        }
        if (wasInterrupted) {
            Thread.currentThread().interrupt();
        }
    }

    private void setState(int state) {
        if (this.playbackInfo.playbackState != state) {
            if (state != 2) {
                this.playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(state);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        if (this.playbackInfoUpdate.hasPendingChange) {
            this.playbackInfoUpdateListener.onPlaybackInfoUpdate(this.playbackInfoUpdate);
            this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        }
    }

    private void prepareInternal() {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared(this.playerId);
        setState(this.playbackInfo.timeline.isEmpty() ? 4 : 2);
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (mediaSourceListUpdateMessage.windowIndex != -1) {
            this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), mediaSourceListUpdateMessage.windowIndex, mediaSourceListUpdateMessage.positionUs);
        }
        Timeline timeline = this.mediaSourceList.setMediaSources(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void addMediaItemsInternal(MediaSourceListUpdateMessage addMessage, int insertionIndex) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.addMediaSources(insertionIndex == -1 ? this.mediaSourceList.getSize() : insertionIndex, addMessage.mediaSourceHolders, addMessage.shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void moveMediaItemsInternal(MoveMediaItemsMessage moveMediaItemsMessage) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.moveMediaSourceRange(moveMediaItemsMessage.fromIndex, moveMediaItemsMessage.toIndex, moveMediaItemsMessage.newFromIndex, moveMediaItemsMessage.shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void removeMediaItemsInternal(int fromIndex, int toIndex, ShuffleOrder shuffleOrder) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.removeMediaSourceRange(fromIndex, toIndex, shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void mediaSourceListUpdateRequestedInternal() throws ExoPlaybackException {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    private void setShuffleOrderInternal(ShuffleOrder shuffleOrder) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.setShuffleOrder(shuffleOrder);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void updateMediaSourcesWithMediaItemsInternal(int fromIndex, int toIndex, List<MediaItem> mediaItems) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Timeline timeline = this.mediaSourceList.updateMediaSourcesWithMediaItems(fromIndex, toIndex, mediaItems);
        handleMediaSourceListInfoRefreshed(timeline, false);
    }

    private void notifyTrackSelectionPlayWhenReadyChanged(boolean playWhenReady) {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onPlayWhenReadyChanged(playWhenReady);
                }
            }
        }
    }

    private void setPlayWhenReadyInternal(boolean z, int i, boolean z2, int i2) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.playbackInfo = this.playbackInfo.copyWithPlayWhenReady(z, i2, i);
        updateRebufferingState(false, false);
        notifyTrackSelectionPlayWhenReadyChanged(z);
        if (!shouldPlayWhenReady()) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            this.mediaClock.start();
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setPauseAtEndOfWindowInternal(boolean pauseAtEndOfWindow) throws ExoPlaybackException {
        this.pauseAtEndOfWindow = pauseAtEndOfWindow;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod && this.queue.getReadingPeriod() != this.queue.getPlayingPeriod()) {
            seekToCurrentPosition(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void setOffloadSchedulingEnabled(boolean offloadSchedulingEnabled) {
        if (offloadSchedulingEnabled == this.offloadSchedulingEnabled) {
            return;
        }
        this.offloadSchedulingEnabled = offloadSchedulingEnabled;
        if (!offloadSchedulingEnabled && this.playbackInfo.sleepingForOffload) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int repeatMode) throws ExoPlaybackException {
        this.repeatMode = repeatMode;
        if (!this.queue.updateRepeatMode(this.playbackInfo.timeline, repeatMode)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean shuffleModeEnabled) throws ExoPlaybackException {
        this.shuffleModeEnabled = shuffleModeEnabled;
        if (!this.queue.updateShuffleModeEnabled(this.playbackInfo.timeline, shuffleModeEnabled)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setPreloadConfigurationInternal(ExoPlayer.PreloadConfiguration preloadConfiguration) {
        this.preloadConfiguration = preloadConfiguration;
        this.queue.updatePreloadConfiguration(this.playbackInfo.timeline, preloadConfiguration);
    }

    private void seekToCurrentPosition(boolean sendDiscontinuity) throws ExoPlaybackException {
        MediaSource.MediaPeriodId periodId = this.queue.getPlayingPeriod().info.id;
        long newPositionUs = seekToPeriodPosition(periodId, this.playbackInfo.positionUs, true, false);
        if (newPositionUs != this.playbackInfo.positionUs) {
            this.playbackInfo = handlePositionDiscontinuity(periodId, newPositionUs, this.playbackInfo.requestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, sendDiscontinuity, 5);
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (playingPeriodHolder == null) {
            return;
        }
        TrackSelectorResult trackSelectorResult = playingPeriodHolder.getTrackSelectorResult();
        for (int i = 0; i < this.renderers.length; i++) {
            if (trackSelectorResult.isRendererEnabled(i) && this.renderers[i].getState() == 1) {
                this.renderers[i].start();
            }
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                ensureStopped(renderer);
            }
        }
    }

    private void attemptRendererErrorRecovery() throws ExoPlaybackException {
        reselectTracksInternalAndSeek();
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        long j;
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (playingPeriodHolder == null) {
            return;
        }
        if (playingPeriodHolder.prepared) {
            j = playingPeriodHolder.mediaPeriod.readDiscontinuity();
        } else {
            j = -9223372036854775807L;
        }
        long discontinuityPositionUs = j;
        if (discontinuityPositionUs == C.TIME_UNSET) {
            this.rendererPositionUs = this.mediaClock.syncAndGetPositionUs(playingPeriodHolder != this.queue.getReadingPeriod());
            long periodPositionUs = playingPeriodHolder.toPeriodTime(this.rendererPositionUs);
            maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodPositionUs);
            if (this.mediaClock.hasSkippedSilenceSinceLastCall()) {
                boolean reportSilenceSkip = !this.playbackInfoUpdate.positionDiscontinuity;
                this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, periodPositionUs, this.playbackInfo.requestedContentPositionUs, periodPositionUs, reportSilenceSkip, 6);
            } else {
                this.playbackInfo.updatePositionUs(periodPositionUs);
            }
        } else {
            if (!playingPeriodHolder.isFullyBuffered()) {
                this.queue.removeAfter(playingPeriodHolder);
                handleLoadingMediaPeriodChanged(false);
                maybeContinueLoading();
            }
            resetRendererPosition(discontinuityPositionUs);
            if (discontinuityPositionUs != this.playbackInfo.positionUs) {
                this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, discontinuityPositionUs, this.playbackInfo.requestedContentPositionUs, discontinuityPositionUs, true, 5);
            }
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        this.playbackInfo.bufferedPositionUs = loadingPeriod.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if (this.playbackInfo.playWhenReady && this.playbackInfo.playbackState == 3 && shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, this.playbackInfo.periodId) && this.playbackInfo.playbackParameters.speed == 1.0f) {
            float adjustedSpeed = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), getTotalBufferedDurationUs());
            if (this.mediaClock.getPlaybackParameters().speed != adjustedSpeed) {
                setMediaClockPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(adjustedSpeed));
                handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
            }
        }
    }

    private void setMediaClockPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.removeMessages(16);
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void notifyTrackSelectionRebuffer() {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onRebuffer();
                }
            }
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        long operationStartTimeMs = this.clock.uptimeMillis();
        this.handler.removeMessages(2);
        updatePeriods();
        if (this.playbackInfo.playbackState == 1 || this.playbackInfo.playbackState == 4) {
            return;
        }
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (playingPeriodHolder == null) {
            scheduleNextWork(operationStartTimeMs);
            return;
        }
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        boolean renderersEnded = true;
        boolean renderersAllowPlayback = true;
        if (playingPeriodHolder.prepared) {
            this.rendererPositionElapsedRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
            playingPeriodHolder.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            for (int i = 0; i < this.renderers.length; i++) {
                Renderer renderer = this.renderers[i];
                if (isRendererEnabled(renderer)) {
                    renderer.render(this.rendererPositionUs, this.rendererPositionElapsedRealtimeUs);
                    renderersEnded = renderersEnded && renderer.isEnded();
                    boolean isReadingAhead = playingPeriodHolder.sampleStreams[i] != renderer.getStream();
                    boolean isWaitingForNextStream = !isReadingAhead && renderer.hasReadStreamToEnd();
                    boolean allowsPlayback = isReadingAhead || isWaitingForNextStream || renderer.isReady() || renderer.isEnded();
                    renderersAllowPlayback = renderersAllowPlayback && allowsPlayback;
                    if (!allowsPlayback) {
                        renderer.maybeThrowStreamError();
                    }
                }
            }
        } else {
            playingPeriodHolder.mediaPeriod.maybeThrowPrepareError();
        }
        long playingPeriodDurationUs = playingPeriodHolder.info.durationUs;
        boolean finishedRendering = renderersEnded && playingPeriodHolder.prepared && (playingPeriodDurationUs == C.TIME_UNSET || playingPeriodDurationUs <= this.playbackInfo.positionUs);
        if (finishedRendering && this.pendingPauseAtEndOfPeriod) {
            this.pendingPauseAtEndOfPeriod = false;
            setPlayWhenReadyInternal(false, this.playbackInfo.playbackSuppressionReason, false, 5);
        }
        if (finishedRendering && playingPeriodHolder.info.isFinal) {
            setState(4);
            stopRenderers();
        } else if (this.playbackInfo.playbackState != 2 || !shouldTransitionToReadyState(renderersAllowPlayback)) {
            if (this.playbackInfo.playbackState == 3 && (this.enabledRendererCount != 0 ? !renderersAllowPlayback : !isTimelineReady())) {
                updateRebufferingState(shouldPlayWhenReady(), false);
                setState(2);
                if (this.isRebuffering) {
                    notifyTrackSelectionRebuffer();
                    this.livePlaybackSpeedControl.notifyRebuffer();
                }
                stopRenderers();
            }
        } else {
            setState(3);
            this.pendingRecoverableRendererError = null;
            if (shouldPlayWhenReady()) {
                updateRebufferingState(false, false);
                this.mediaClock.start();
                startRenderers();
            }
        }
        boolean playbackMaybeStuck = false;
        if (this.playbackInfo.playbackState == 2) {
            for (int i2 = 0; i2 < this.renderers.length; i2++) {
                if (isRendererEnabled(this.renderers[i2]) && this.renderers[i2].getStream() == playingPeriodHolder.sampleStreams[i2]) {
                    this.renderers[i2].maybeThrowStreamError();
                }
            }
            if (!this.playbackInfo.isLoading && this.playbackInfo.totalBufferedDurationUs < PLAYBACK_BUFFER_EMPTY_THRESHOLD_US && isLoadingPossible()) {
                playbackMaybeStuck = true;
            }
        }
        if (!playbackMaybeStuck) {
            this.playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
        } else if (this.playbackMaybeBecameStuckAtMs == C.TIME_UNSET) {
            this.playbackMaybeBecameStuckAtMs = this.clock.elapsedRealtime();
        } else if (this.clock.elapsedRealtime() - this.playbackMaybeBecameStuckAtMs >= 4000) {
            throw new IllegalStateException("Playback stuck buffering and not loading");
        }
        boolean isPlaying = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
        boolean sleepingForOffload = this.offloadSchedulingEnabled && this.requestForRendererSleep && isPlaying;
        if (this.playbackInfo.sleepingForOffload != sleepingForOffload) {
            this.playbackInfo = this.playbackInfo.copyWithSleepingForOffload(sleepingForOffload);
        }
        this.requestForRendererSleep = false;
        if (!sleepingForOffload && this.playbackInfo.playbackState != 4 && (isPlaying || this.playbackInfo.playbackState == 2 || (this.playbackInfo.playbackState == 3 && this.enabledRendererCount != 0))) {
            scheduleNextWork(operationStartTimeMs);
        }
        TraceUtil.endSection();
    }

    private long getCurrentLiveOffsetUs() {
        return getLiveOffsetUs(this.playbackInfo.timeline, this.playbackInfo.periodId.periodUid, this.playbackInfo.positionUs);
    }

    private long getLiveOffsetUs(Timeline timeline, Object periodUid, long periodPositionUs) {
        int windowIndex = timeline.getPeriodByUid(periodUid, this.period).windowIndex;
        timeline.getWindow(windowIndex, this.window);
        return (this.window.windowStartTimeMs != C.TIME_UNSET && this.window.isLive() && this.window.isDynamic) ? Util.msToUs(this.window.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - (this.period.getPositionInWindowUs() + periodPositionUs) : C.TIME_UNSET;
    }

    private boolean shouldUseLivePlaybackSpeedControl(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (mediaPeriodId.isAd() || timeline.isEmpty()) {
            return false;
        }
        int windowIndex = timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex;
        timeline.getWindow(windowIndex, this.window);
        return this.window.isLive() && this.window.isDynamic && this.window.windowStartTimeMs != C.TIME_UNSET;
    }

    private void scheduleNextWork(long thisOperationStartTimeMs) {
        long wakeUpTimeIntervalMs;
        if (this.playbackInfo.playbackState == 3 && (this.dynamicSchedulingEnabled || !shouldPlayWhenReady())) {
            wakeUpTimeIntervalMs = 1000;
        } else {
            wakeUpTimeIntervalMs = BUFFERING_MAXIMUM_INTERVAL_MS;
        }
        if (this.dynamicSchedulingEnabled && shouldPlayWhenReady()) {
            for (Renderer renderer : this.renderers) {
                if (isRendererEnabled(renderer)) {
                    wakeUpTimeIntervalMs = Math.min(wakeUpTimeIntervalMs, Util.usToMs(renderer.getDurationToProgressUs(this.rendererPositionUs, this.rendererPositionElapsedRealtimeUs)));
                }
            }
        }
        this.handler.sendEmptyMessageAtTime(2, thisOperationStartTimeMs + wakeUpTimeIntervalMs);
    }

    private void seekToInternal(SeekPosition seekPosition) throws ExoPlaybackException {
        MediaSource.MediaPeriodId periodId;
        long requestedContentPositionUs;
        long periodPositionUs;
        boolean seekPositionAdjusted;
        long periodPositionUs2;
        long periodPositionUs3;
        long newPeriodPositionUs;
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Pair<Object, Long> resolvedSeekPosition = resolveSeekPositionUs(this.playbackInfo.timeline, seekPosition, true, this.repeatMode, this.shuffleModeEnabled, this.window, this.period);
        if (resolvedSeekPosition == null) {
            Pair<MediaSource.MediaPeriodId, Long> firstPeriodAndPositionUs = getPlaceholderFirstMediaPeriodPositionUs(this.playbackInfo.timeline);
            MediaSource.MediaPeriodId periodId2 = (MediaSource.MediaPeriodId) firstPeriodAndPositionUs.first;
            periodPositionUs = ((Long) firstPeriodAndPositionUs.second).longValue();
            boolean seekPositionAdjusted2 = !this.playbackInfo.timeline.isEmpty();
            seekPositionAdjusted = seekPositionAdjusted2;
            periodId = periodId2;
            requestedContentPositionUs = -9223372036854775807L;
        } else {
            Object periodUid = resolvedSeekPosition.first;
            long resolvedContentPositionUs = ((Long) resolvedSeekPosition.second).longValue();
            long requestedContentPositionUs2 = seekPosition.windowPositionUs == C.TIME_UNSET ? -9223372036854775807L : resolvedContentPositionUs;
            periodId = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(this.playbackInfo.timeline, periodUid, resolvedContentPositionUs);
            if (periodId.isAd()) {
                this.playbackInfo.timeline.getPeriodByUid(periodId.periodUid, this.period);
                if (this.period.getFirstAdIndexToPlay(periodId.adGroupIndex) == periodId.adIndexInAdGroup) {
                    periodPositionUs2 = this.period.getAdResumePositionUs();
                } else {
                    periodPositionUs2 = 0;
                }
                seekPositionAdjusted = true;
                requestedContentPositionUs = requestedContentPositionUs2;
                periodPositionUs = periodPositionUs2;
            } else {
                requestedContentPositionUs = requestedContentPositionUs2;
                periodPositionUs = resolvedContentPositionUs;
                seekPositionAdjusted = seekPosition.windowPositionUs == C.TIME_UNSET;
            }
        }
        try {
            if (this.playbackInfo.timeline.isEmpty()) {
                this.pendingInitialSeekPosition = seekPosition;
            } else if (resolvedSeekPosition == null) {
                if (this.playbackInfo.playbackState != 1) {
                    setState(4);
                }
                resetInternal(false, true, false, true);
            } else {
                long newPeriodPositionUs2 = periodPositionUs;
                if (periodId.equals(this.playbackInfo.periodId)) {
                    MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
                    if (playingPeriodHolder != null && playingPeriodHolder.prepared && newPeriodPositionUs2 != 0) {
                        newPeriodPositionUs = playingPeriodHolder.mediaPeriod.getAdjustedSeekPositionUs(newPeriodPositionUs2, this.seekParameters);
                    } else {
                        newPeriodPositionUs = newPeriodPositionUs2;
                    }
                    if (Util.usToMs(newPeriodPositionUs) == Util.usToMs(this.playbackInfo.positionUs) && (this.playbackInfo.playbackState == 2 || this.playbackInfo.playbackState == 3)) {
                        long periodPositionUs4 = this.playbackInfo.positionUs;
                        this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs4, requestedContentPositionUs, periodPositionUs4, seekPositionAdjusted, 2);
                        return;
                    }
                    newPeriodPositionUs2 = newPeriodPositionUs;
                }
                long newPeriodPositionUs3 = seekToPeriodPosition(periodId, newPeriodPositionUs2, this.playbackInfo.playbackState == 4);
                seekPositionAdjusted |= periodPositionUs != newPeriodPositionUs3;
                periodPositionUs3 = newPeriodPositionUs3;
                try {
                    updatePlaybackSpeedSettingsForNewPeriod(this.playbackInfo.timeline, periodId, this.playbackInfo.timeline, this.playbackInfo.periodId, requestedContentPositionUs, true);
                    this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs3, requestedContentPositionUs, periodPositionUs3, seekPositionAdjusted, 2);
                } catch (Throwable th) {
                    th = th;
                    this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs3, requestedContentPositionUs, periodPositionUs3, seekPositionAdjusted, 2);
                    throw th;
                }
            }
            periodPositionUs3 = periodPositionUs;
            this.playbackInfo = handlePositionDiscontinuity(periodId, periodPositionUs3, requestedContentPositionUs, periodPositionUs3, seekPositionAdjusted, 2);
        } catch (Throwable th2) {
            th = th2;
            periodPositionUs3 = periodPositionUs;
        }
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId periodId, long periodPositionUs, boolean forceBufferingState) throws ExoPlaybackException {
        return seekToPeriodPosition(periodId, periodPositionUs, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod(), forceBufferingState);
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId periodId, long periodPositionUs, boolean forceDisableRenderers, boolean forceBufferingState) throws ExoPlaybackException {
        stopRenderers();
        updateRebufferingState(false, true);
        if (forceBufferingState || this.playbackInfo.playbackState == 3) {
            setState(2);
        }
        MediaPeriodHolder oldPlayingPeriodHolder = this.queue.getPlayingPeriod();
        MediaPeriodHolder newPlayingPeriodHolder = oldPlayingPeriodHolder;
        while (newPlayingPeriodHolder != null && !periodId.equals(newPlayingPeriodHolder.info.id)) {
            newPlayingPeriodHolder = newPlayingPeriodHolder.getNext();
        }
        if (forceDisableRenderers || oldPlayingPeriodHolder != newPlayingPeriodHolder || (newPlayingPeriodHolder != null && newPlayingPeriodHolder.toRendererTime(periodPositionUs) < 0)) {
            for (Renderer renderer : this.renderers) {
                disableRenderer(renderer);
            }
            if (newPlayingPeriodHolder != null) {
                while (this.queue.getPlayingPeriod() != newPlayingPeriodHolder) {
                    this.queue.advancePlayingPeriod();
                }
                this.queue.removeAfter(newPlayingPeriodHolder);
                newPlayingPeriodHolder.setRendererOffset(MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US);
                enableRenderers();
            }
        }
        if (newPlayingPeriodHolder != null) {
            this.queue.removeAfter(newPlayingPeriodHolder);
            if (!newPlayingPeriodHolder.prepared) {
                newPlayingPeriodHolder.info = newPlayingPeriodHolder.info.copyWithStartPositionUs(periodPositionUs);
            } else if (newPlayingPeriodHolder.hasEnabledTracks) {
                periodPositionUs = newPlayingPeriodHolder.mediaPeriod.seekToUs(periodPositionUs);
                newPlayingPeriodHolder.mediaPeriod.discardBuffer(periodPositionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(periodPositionUs);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(periodPositionUs);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return periodPositionUs;
    }

    private void resetRendererPosition(long periodPositionUs) throws ExoPlaybackException {
        long rendererTime;
        MediaPeriodHolder playingMediaPeriod = this.queue.getPlayingPeriod();
        if (playingMediaPeriod == null) {
            rendererTime = MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US + periodPositionUs;
        } else {
            rendererTime = playingMediaPeriod.toRendererTime(periodPositionUs);
        }
        this.rendererPositionUs = rendererTime;
        this.mediaClock.resetPosition(this.rendererPositionUs);
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.resetPosition(this.rendererPositionUs);
            }
        }
        notifyTrackSelectionDiscontinuity();
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        setMediaClockPlaybackParameters(playbackParameters);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters) {
        this.seekParameters = seekParameters;
    }

    private void setForegroundModeInternal(boolean foregroundMode, AtomicBoolean processedFlag) {
        if (this.foregroundMode != foregroundMode) {
            this.foregroundMode = foregroundMode;
            if (!foregroundMode) {
                for (Renderer renderer : this.renderers) {
                    if (!isRendererEnabled(renderer) && this.renderersToReset.remove(renderer)) {
                        renderer.reset();
                    }
                }
            }
        }
        if (processedFlag != null) {
            synchronized (this) {
                processedFlag.set(true);
                notifyAll();
            }
        }
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(z || !this.foregroundMode, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.loadControl.onStopped(this.playerId);
        setState(1);
    }

    private void releaseInternal() {
        try {
            resetInternal(true, false, true, false);
            releaseRenderers();
            this.loadControl.onReleased(this.playerId);
            setState(1);
            if (this.internalPlaybackThread != null) {
                this.internalPlaybackThread.quit();
            }
            synchronized (this) {
                this.released = true;
                notifyAll();
            }
        } catch (Throwable th) {
            if (this.internalPlaybackThread != null) {
                this.internalPlaybackThread.quit();
            }
            synchronized (this) {
                this.released = true;
                notifyAll();
                throw th;
            }
        }
    }

    private void resetInternal(boolean resetRenderers, boolean resetPosition, boolean releaseMediaSourceList, boolean resetError) {
        long requestedContentPositionUs;
        long startPositionUs;
        long requestedContentPositionUs2;
        boolean resetTrackInfo;
        this.handler.removeMessages(2);
        ExoPlaybackException exoPlaybackException = null;
        this.pendingRecoverableRendererError = null;
        updateRebufferingState(false, true);
        this.mediaClock.stop();
        this.rendererPositionUs = MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US;
        for (Renderer renderer : this.renderers) {
            try {
                disableRenderer(renderer);
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e(TAG, "Disable failed.", e);
            }
        }
        if (resetRenderers) {
            for (Renderer renderer2 : this.renderers) {
                if (this.renderersToReset.remove(renderer2)) {
                    try {
                        renderer2.reset();
                    } catch (RuntimeException e2) {
                        Log.e(TAG, "Reset failed.", e2);
                    }
                }
            }
        }
        this.enabledRendererCount = 0;
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        long startPositionUs2 = this.playbackInfo.positionUs;
        if (this.playbackInfo.periodId.isAd() || isUsingPlaceholderPeriod(this.playbackInfo, this.period)) {
            requestedContentPositionUs = this.playbackInfo.requestedContentPositionUs;
        } else {
            requestedContentPositionUs = this.playbackInfo.positionUs;
        }
        if (!resetPosition) {
            startPositionUs = startPositionUs2;
            requestedContentPositionUs2 = requestedContentPositionUs;
            resetTrackInfo = false;
        } else {
            this.pendingInitialSeekPosition = null;
            Pair<MediaSource.MediaPeriodId, Long> firstPeriodAndPositionUs = getPlaceholderFirstMediaPeriodPositionUs(this.playbackInfo.timeline);
            mediaPeriodId = (MediaSource.MediaPeriodId) firstPeriodAndPositionUs.first;
            long startPositionUs3 = ((Long) firstPeriodAndPositionUs.second).longValue();
            if (!mediaPeriodId.equals(this.playbackInfo.periodId)) {
                startPositionUs = startPositionUs3;
                requestedContentPositionUs2 = -9223372036854775807L;
                resetTrackInfo = true;
            } else {
                startPositionUs = startPositionUs3;
                requestedContentPositionUs2 = -9223372036854775807L;
                resetTrackInfo = false;
            }
        }
        this.queue.clear();
        this.shouldContinueLoading = false;
        Timeline timeline = this.playbackInfo.timeline;
        if (releaseMediaSourceList && (timeline instanceof PlaylistTimeline)) {
            timeline = ((PlaylistTimeline) this.playbackInfo.timeline).copyWithPlaceholderTimeline(this.mediaSourceList.getShuffleOrder());
            if (mediaPeriodId.adGroupIndex != -1) {
                timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
                if (timeline.getWindow(this.period.windowIndex, this.window).isLive()) {
                    mediaPeriodId = new MediaSource.MediaPeriodId(mediaPeriodId.periodUid, mediaPeriodId.windowSequenceNumber);
                }
            }
        }
        int i = this.playbackInfo.playbackState;
        if (!resetError) {
            exoPlaybackException = this.playbackInfo.playbackError;
        }
        this.playbackInfo = new PlaybackInfo(timeline, mediaPeriodId, requestedContentPositionUs2, startPositionUs, i, exoPlaybackException, false, resetTrackInfo ? TrackGroupArray.EMPTY : this.playbackInfo.trackGroups, resetTrackInfo ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult, resetTrackInfo ? ImmutableList.of() : this.playbackInfo.staticMetadata, mediaPeriodId, this.playbackInfo.playWhenReady, this.playbackInfo.playWhenReadyChangeReason, this.playbackInfo.playbackSuppressionReason, this.playbackInfo.playbackParameters, startPositionUs, 0L, startPositionUs, 0L, false);
        if (releaseMediaSourceList) {
            this.queue.releasePreloadPool();
            this.mediaSourceList.release();
        }
    }

    private Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline timeline) {
        if (timeline.isEmpty()) {
            return Pair.create(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L);
        }
        int firstWindowIndex = timeline.getFirstWindowIndex(this.shuffleModeEnabled);
        Pair<Object, Long> firstPeriodAndPositionUs = timeline.getPeriodPositionUs(this.window, this.period, firstWindowIndex, C.TIME_UNSET);
        MediaSource.MediaPeriodId firstPeriodId = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline, firstPeriodAndPositionUs.first, 0L);
        long positionUs = ((Long) firstPeriodAndPositionUs.second).longValue();
        if (firstPeriodId.isAd()) {
            timeline.getPeriodByUid(firstPeriodId.periodUid, this.period);
            positionUs = firstPeriodId.adIndexInAdGroup == this.period.getFirstAdIndexToPlay(firstPeriodId.adGroupIndex) ? this.period.getAdResumePositionUs() : 0L;
        }
        return Pair.create(firstPeriodId, Long.valueOf(positionUs));
    }

    private void sendMessageInternal(PlayerMessage message) throws ExoPlaybackException {
        if (message.getPositionMs() == C.TIME_UNSET) {
            sendMessageToTarget(message);
            return;
        }
        if (this.playbackInfo.timeline.isEmpty()) {
            this.pendingMessages.add(new PendingMessageInfo(message));
            return;
        }
        PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(message);
        if (resolvePendingMessagePosition(pendingMessageInfo, this.playbackInfo.timeline, this.playbackInfo.timeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
            this.pendingMessages.add(pendingMessageInfo);
            Collections.sort(this.pendingMessages);
        } else {
            message.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage message) throws ExoPlaybackException {
        if (message.getLooper() == this.playbackLooper) {
            deliverMessage(message);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, message).sendToTarget();
    }

    private void sendMessageToTargetThread(final PlayerMessage message) {
        Looper looper = message.getLooper();
        if (!looper.getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            message.markAsProcessed(false);
        } else {
            this.clock.createHandler(looper, null).post(new Runnable() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ExoPlayerImplInternal.this.lambda$sendMessageToTargetThread$1(message);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendMessageToTargetThread$1(PlayerMessage message) {
        try {
            deliverMessage(message);
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage message) throws ExoPlaybackException {
        if (message.isCanceled()) {
            return;
        }
        try {
            message.getTarget().handleMessage(message.getType(), message.getPayload());
        } finally {
            message.markAsProcessed(true);
        }
    }

    private void resolvePendingMessagePositions(Timeline newTimeline, Timeline previousTimeline) {
        if (newTimeline.isEmpty() && previousTimeline.isEmpty()) {
            return;
        }
        for (int i = this.pendingMessages.size() - 1; i >= 0; i--) {
            if (!resolvePendingMessagePosition(this.pendingMessages.get(i), newTimeline, previousTimeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                this.pendingMessages.get(i).message.markAsProcessed(false);
                this.pendingMessages.remove(i);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void maybeTriggerPendingMessages(long r8, long r10) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        if (!isRendererEnabled(renderer)) {
            return;
        }
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
        this.enabledRendererCount--;
    }

    private void reselectTracksInternalAndSeek() throws ExoPlaybackException {
        reselectTracksInternal();
        seekToCurrentPosition(true);
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        TrackSelectorResult newPlayingPeriodTrackSelectorResult;
        boolean z;
        float playbackSpeed = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
        TrackSelectorResult newPlayingPeriodTrackSelectorResult2 = null;
        MediaPeriodHolder periodHolder2 = periodHolder;
        boolean selectionsChangedForReadPeriod = true;
        while (periodHolder2 != null && periodHolder2.prepared) {
            TrackSelectorResult newTrackSelectorResult = periodHolder2.selectTracks(playbackSpeed, this.playbackInfo.timeline);
            if (periodHolder2 != this.queue.getPlayingPeriod()) {
                newPlayingPeriodTrackSelectorResult = newPlayingPeriodTrackSelectorResult2;
            } else {
                newPlayingPeriodTrackSelectorResult = newTrackSelectorResult;
            }
            if (newTrackSelectorResult.isEquivalent(periodHolder2.getTrackSelectorResult())) {
                float playbackSpeed2 = playbackSpeed;
                MediaPeriodHolder readingPeriodHolder2 = readingPeriodHolder;
                boolean selectionsChangedForReadPeriod2 = selectionsChangedForReadPeriod;
                if (periodHolder2 != readingPeriodHolder2) {
                    selectionsChangedForReadPeriod = selectionsChangedForReadPeriod2;
                } else {
                    selectionsChangedForReadPeriod = false;
                }
                periodHolder2 = periodHolder2.getNext();
                readingPeriodHolder = readingPeriodHolder2;
                newPlayingPeriodTrackSelectorResult2 = newPlayingPeriodTrackSelectorResult;
                playbackSpeed = playbackSpeed2;
            } else {
                boolean z2 = false;
                if (!selectionsChangedForReadPeriod) {
                    z = true;
                    this.queue.removeAfter(periodHolder2);
                    if (periodHolder2.prepared) {
                        long loadingPeriodPositionUs = Math.max(periodHolder2.info.startPositionUs, periodHolder2.toPeriodTime(this.rendererPositionUs));
                        periodHolder2.applyTrackSelection(newTrackSelectorResult, loadingPeriodPositionUs, false);
                    }
                } else {
                    MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
                    boolean recreateStreams = this.queue.removeAfter(playingPeriodHolder);
                    boolean[] streamResetFlags = new boolean[this.renderers.length];
                    long periodPositionUs = playingPeriodHolder.applyTrackSelection((TrackSelectorResult) Assertions.checkNotNull(newPlayingPeriodTrackSelectorResult), this.playbackInfo.positionUs, recreateStreams, streamResetFlags);
                    if (this.playbackInfo.playbackState != 4 && periodPositionUs != this.playbackInfo.positionUs) {
                        z2 = true;
                    }
                    boolean hasDiscontinuity = z2;
                    z = true;
                    this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, periodPositionUs, this.playbackInfo.requestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, hasDiscontinuity, 5);
                    if (hasDiscontinuity) {
                        resetRendererPosition(periodPositionUs);
                    }
                    boolean[] rendererWasEnabledFlags = new boolean[this.renderers.length];
                    for (int i = 0; i < this.renderers.length; i++) {
                        Renderer renderer = this.renderers[i];
                        rendererWasEnabledFlags[i] = isRendererEnabled(renderer);
                        SampleStream sampleStream = playingPeriodHolder.sampleStreams[i];
                        if (rendererWasEnabledFlags[i]) {
                            if (sampleStream != renderer.getStream()) {
                                disableRenderer(renderer);
                            } else if (streamResetFlags[i]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                    }
                    enableRenderers(rendererWasEnabledFlags, this.rendererPositionUs);
                }
                handleLoadingMediaPeriodChanged(z);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float playbackSpeed) {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onPlaybackSpeed(playbackSpeed);
                }
            }
        }
    }

    private void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder periodHolder = this.queue.getPlayingPeriod(); periodHolder != null; periodHolder = periodHolder.getNext()) {
            for (ExoTrackSelection trackSelection : periodHolder.getTrackSelectorResult().selections) {
                if (trackSelection != null) {
                    trackSelection.onDiscontinuity();
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean renderersReadyOrEnded) {
        long targetLiveOffsetUs;
        if (this.enabledRendererCount == 0) {
            return isTimelineReady();
        }
        if (!renderersReadyOrEnded) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, playingPeriodHolder.info.id)) {
            targetLiveOffsetUs = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
        } else {
            targetLiveOffsetUs = -9223372036854775807L;
        }
        MediaPeriodHolder loadingHolder = this.queue.getLoadingPeriod();
        boolean isBufferedToEnd = loadingHolder.isFullyBuffered() && loadingHolder.info.isFinal;
        boolean isAdPendingPreparation = loadingHolder.info.id.isAd() && !loadingHolder.prepared;
        if (!isBufferedToEnd && !isAdPendingPreparation) {
            if (!this.loadControl.shouldStartPlayback(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, playingPeriodHolder.info.id, playingPeriodHolder.toPeriodTime(this.rendererPositionUs), getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, targetLiveOffsetUs))) {
                return false;
            }
        }
        return true;
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        long playingPeriodDurationUs = playingPeriodHolder.info.durationUs;
        return playingPeriodHolder.prepared && (playingPeriodDurationUs == C.TIME_UNSET || this.playbackInfo.positionUs < playingPeriodDurationUs || !shouldPlayWhenReady());
    }

    /* JADX WARN: Not initialized variable reg: 25, insn: 0x00f1: MOVE (r24 I:??[long, double]) = (r25 I:??[long, double] A[D('newRequestedContentPositionUs' long)]), block:B:100:0x00f1 */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01fb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0220  */
    /* JADX WARN: Type inference failed for: r9v0, types: [long] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline r30, boolean r31) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            Method dump skipped, instructions count: 605
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline, boolean):void");
    }

    private void updatePlaybackSpeedSettingsForNewPeriod(Timeline newTimeline, MediaSource.MediaPeriodId newPeriodId, Timeline oldTimeline, MediaSource.MediaPeriodId oldPeriodId, long positionForTargetOffsetOverrideUs, boolean forceSetTargetOffsetOverride) throws ExoPlaybackException {
        if (!shouldUseLivePlaybackSpeedControl(newTimeline, newPeriodId)) {
            PlaybackParameters targetPlaybackParameters = newPeriodId.isAd() ? PlaybackParameters.DEFAULT : this.playbackInfo.playbackParameters;
            if (!this.mediaClock.getPlaybackParameters().equals(targetPlaybackParameters)) {
                setMediaClockPlaybackParameters(targetPlaybackParameters);
                handlePlaybackParameters(this.playbackInfo.playbackParameters, targetPlaybackParameters.speed, false, false);
                return;
            }
            return;
        }
        int windowIndex = newTimeline.getPeriodByUid(newPeriodId.periodUid, this.period).windowIndex;
        newTimeline.getWindow(windowIndex, this.window);
        this.livePlaybackSpeedControl.setLiveConfiguration((MediaItem.LiveConfiguration) Util.castNonNull(this.window.liveConfiguration));
        if (positionForTargetOffsetOverrideUs != C.TIME_UNSET) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(newTimeline, newPeriodId.periodUid, positionForTargetOffsetOverrideUs));
            return;
        }
        Object windowUid = this.window.uid;
        Object oldWindowUid = null;
        if (!oldTimeline.isEmpty()) {
            int oldWindowIndex = oldTimeline.getPeriodByUid(oldPeriodId.periodUid, this.period).windowIndex;
            oldWindowUid = oldTimeline.getWindow(oldWindowIndex, this.window).uid;
        }
        if (!Util.areEqual(oldWindowUid, windowUid) || forceSetTargetOffsetOverride) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(C.TIME_UNSET);
        }
    }

    private long getMaxRendererReadPositionUs() {
        MediaPeriodHolder readingHolder = this.queue.getReadingPeriod();
        if (readingHolder == null) {
            return 0L;
        }
        long maxReadPositionUs = readingHolder.getRendererOffset();
        if (!readingHolder.prepared) {
            return maxReadPositionUs;
        }
        for (int i = 0; i < this.renderers.length; i++) {
            if (isRendererEnabled(this.renderers[i]) && this.renderers[i].getStream() == readingHolder.sampleStreams[i]) {
                long readingPositionUs = this.renderers[i].getReadingPositionUs();
                if (readingPositionUs == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                maxReadPositionUs = Math.max(readingPositionUs, maxReadPositionUs);
            }
        }
        return maxReadPositionUs;
    }

    private void updatePeriods() throws ExoPlaybackException {
        if (this.playbackInfo.timeline.isEmpty() || !this.mediaSourceList.isPrepared()) {
            return;
        }
        boolean loadingPeriodChanged = maybeUpdateLoadingPeriod();
        maybeUpdateReadingPeriod();
        maybeUpdateReadingRenderers();
        maybeUpdatePlayingPeriod();
        maybeUpdatePreloadPeriods(loadingPeriodChanged);
    }

    private boolean maybeUpdateLoadingPeriod() throws ExoPlaybackException {
        MediaPeriodInfo info;
        boolean loadingPeriodChanged = false;
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod() && (info = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo)) != null) {
            MediaPeriodHolder mediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(info);
            mediaPeriodHolder.mediaPeriod.prepare(this, info.startPositionUs);
            if (this.queue.getPlayingPeriod() == mediaPeriodHolder) {
                resetRendererPosition(info.startPositionUs);
            }
            handleLoadingMediaPeriodChanged(false);
            loadingPeriodChanged = true;
        }
        if (this.shouldContinueLoading) {
            this.shouldContinueLoading = isLoadingPossible();
            updateIsLoading();
        } else {
            maybeContinueLoading();
        }
        return loadingPeriodChanged;
    }

    private void maybeUpdateReadingPeriod() throws ExoPlaybackException {
        long streamEndPositionUs;
        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
        if (readingPeriodHolder == null) {
            return;
        }
        if (readingPeriodHolder.getNext() == null || this.pendingPauseAtEndOfPeriod) {
            if (readingPeriodHolder.info.isFinal || this.pendingPauseAtEndOfPeriod) {
                for (int i = 0; i < this.renderers.length; i++) {
                    Renderer renderer = this.renderers[i];
                    SampleStream sampleStream = readingPeriodHolder.sampleStreams[i];
                    if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                        if (readingPeriodHolder.info.durationUs != C.TIME_UNSET && readingPeriodHolder.info.durationUs != Long.MIN_VALUE) {
                            streamEndPositionUs = readingPeriodHolder.getRendererOffset() + readingPeriodHolder.info.durationUs;
                        } else {
                            streamEndPositionUs = -9223372036854775807L;
                        }
                        setCurrentStreamFinal(renderer, streamEndPositionUs);
                    }
                }
                return;
            }
            return;
        }
        if (!hasReadingPeriodFinishedReading()) {
            return;
        }
        if (!readingPeriodHolder.getNext().prepared && this.rendererPositionUs < readingPeriodHolder.getNext().getStartPositionRendererTime()) {
            return;
        }
        TrackSelectorResult oldTrackSelectorResult = readingPeriodHolder.getTrackSelectorResult();
        MediaPeriodHolder readingPeriodHolder2 = this.queue.advanceReadingPeriod();
        TrackSelectorResult newTrackSelectorResult = readingPeriodHolder2.getTrackSelectorResult();
        updatePlaybackSpeedSettingsForNewPeriod(this.playbackInfo.timeline, readingPeriodHolder2.info.id, this.playbackInfo.timeline, readingPeriodHolder.info.id, C.TIME_UNSET, false);
        if (readingPeriodHolder2.prepared && readingPeriodHolder2.mediaPeriod.readDiscontinuity() != C.TIME_UNSET) {
            setAllRendererStreamsFinal(readingPeriodHolder2.getStartPositionRendererTime());
            if (!readingPeriodHolder2.isFullyBuffered()) {
                this.queue.removeAfter(readingPeriodHolder2);
                handleLoadingMediaPeriodChanged(false);
                maybeContinueLoading();
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            boolean oldRendererEnabled = oldTrackSelectorResult.isRendererEnabled(i2);
            boolean newRendererEnabled = newTrackSelectorResult.isRendererEnabled(i2);
            if (oldRendererEnabled && !this.renderers[i2].isCurrentStreamFinal()) {
                boolean isNoSampleRenderer = this.rendererCapabilities[i2].getTrackType() == -2;
                RendererConfiguration oldConfig = oldTrackSelectorResult.rendererConfigurations[i2];
                RendererConfiguration newConfig = newTrackSelectorResult.rendererConfigurations[i2];
                if (!newRendererEnabled || !newConfig.equals(oldConfig) || isNoSampleRenderer) {
                    setCurrentStreamFinal(this.renderers[i2], readingPeriodHolder2.getStartPositionRendererTime());
                }
            }
        }
    }

    private void maybeUpdateReadingRenderers() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null && this.queue.getPlayingPeriod() != readingPeriod && !readingPeriod.allRenderersInCorrectState && replaceStreamsOrDisableRendererForTransition()) {
            enableRenderers();
        }
    }

    private void maybeUpdatePreloadPeriods(boolean loadingPeriodChanged) {
        if (this.preloadConfiguration.targetPreloadDurationUs != C.TIME_UNSET) {
            if (!loadingPeriodChanged && this.playbackInfo.timeline.equals(this.lastPreloadPoolInvalidationTimeline)) {
                return;
            }
            this.lastPreloadPoolInvalidationTimeline = this.playbackInfo.timeline;
            this.queue.invalidatePreloadPool(this.playbackInfo.timeline);
        }
    }

    private boolean replaceStreamsOrDisableRendererForTransition() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
        TrackSelectorResult newTrackSelectorResult = readingPeriodHolder.getTrackSelectorResult();
        boolean needsToWaitForRendererToEnd = false;
        for (int i = 0; i < this.renderers.length; i++) {
            Renderer renderer = this.renderers[i];
            if (isRendererEnabled(renderer)) {
                boolean rendererIsReadingOldStream = renderer.getStream() != readingPeriodHolder.sampleStreams[i];
                boolean rendererShouldBeEnabled = newTrackSelectorResult.isRendererEnabled(i);
                if (!rendererShouldBeEnabled || rendererIsReadingOldStream) {
                    if (!renderer.isCurrentStreamFinal()) {
                        Format[] formats = getFormats(newTrackSelectorResult.selections[i]);
                        renderer.replaceStream(formats, readingPeriodHolder.sampleStreams[i], readingPeriodHolder.getStartPositionRendererTime(), readingPeriodHolder.getRendererOffset(), readingPeriodHolder.info.id);
                        if (this.offloadSchedulingEnabled) {
                            setOffloadSchedulingEnabled(false);
                        }
                    } else if (renderer.isEnded()) {
                        disableRenderer(renderer);
                    } else {
                        needsToWaitForRendererToEnd = true;
                    }
                }
            }
        }
        return !needsToWaitForRendererToEnd;
    }

    private void maybeUpdatePlayingPeriod() throws ExoPlaybackException {
        boolean advancedPlayingPeriod = false;
        while (shouldAdvancePlayingPeriod()) {
            if (advancedPlayingPeriod) {
                maybeNotifyPlaybackInfoChanged();
            }
            MediaPeriodHolder newPlayingPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.advancePlayingPeriod());
            boolean isCancelledSSAIAdTransition = this.playbackInfo.periodId.periodUid.equals(newPlayingPeriodHolder.info.id.periodUid) && this.playbackInfo.periodId.adGroupIndex == -1 && newPlayingPeriodHolder.info.id.adGroupIndex == -1 && this.playbackInfo.periodId.nextAdGroupIndex != newPlayingPeriodHolder.info.id.nextAdGroupIndex;
            this.playbackInfo = handlePositionDiscontinuity(newPlayingPeriodHolder.info.id, newPlayingPeriodHolder.info.startPositionUs, newPlayingPeriodHolder.info.requestedContentPositionUs, newPlayingPeriodHolder.info.startPositionUs, !isCancelledSSAIAdTransition, 0);
            resetPendingPauseAtEndOfPeriod();
            updatePlaybackPositions();
            if (this.playbackInfo.playbackState == 3) {
                startRenderers();
            }
            allowRenderersToRenderStartOfStreams();
            advancedPlayingPeriod = true;
        }
    }

    private void maybeUpdateOffloadScheduling() {
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (playingPeriodHolder != null) {
            TrackSelectorResult trackSelectorResult = playingPeriodHolder.getTrackSelectorResult();
            boolean isAudioRendererEnabledAndOffloadPreferred = false;
            boolean isAudioOnly = true;
            int i = 0;
            while (true) {
                if (i >= this.renderers.length) {
                    break;
                }
                if (trackSelectorResult.isRendererEnabled(i)) {
                    if (this.renderers[i].getTrackType() != 1) {
                        isAudioOnly = false;
                        break;
                    } else if (trackSelectorResult.rendererConfigurations[i].offloadModePreferred != 0) {
                        isAudioRendererEnabledAndOffloadPreferred = true;
                    }
                }
                i++;
            }
            setOffloadSchedulingEnabled(isAudioRendererEnabledAndOffloadPreferred && isAudioOnly);
        }
    }

    private void allowRenderersToRenderStartOfStreams() {
        TrackSelectorResult playingTracks = this.queue.getPlayingPeriod().getTrackSelectorResult();
        for (int i = 0; i < this.renderers.length; i++) {
            if (playingTracks.isRendererEnabled(i)) {
                this.renderers[i].enableMayRenderStartOfStream();
            }
        }
    }

    private void resetPendingPauseAtEndOfPeriod() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        this.pendingPauseAtEndOfPeriod = playingPeriod != null && playingPeriod.info.isLastInTimelineWindow && this.pauseAtEndOfWindow;
    }

    private boolean shouldAdvancePlayingPeriod() {
        MediaPeriodHolder playingPeriodHolder;
        MediaPeriodHolder nextPlayingPeriodHolder;
        return shouldPlayWhenReady() && !this.pendingPauseAtEndOfPeriod && (playingPeriodHolder = this.queue.getPlayingPeriod()) != null && (nextPlayingPeriodHolder = playingPeriodHolder.getNext()) != null && this.rendererPositionUs >= nextPlayingPeriodHolder.getStartPositionRendererTime() && nextPlayingPeriodHolder.allRenderersInCorrectState;
    }

    private boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder readingPeriodHolder = this.queue.getReadingPeriod();
        if (!readingPeriodHolder.prepared) {
            return false;
        }
        for (int i = 0; i < this.renderers.length; i++) {
            Renderer renderer = this.renderers[i];
            SampleStream sampleStream = readingPeriodHolder.sampleStreams[i];
            if (renderer.getStream() != sampleStream || (sampleStream != null && !renderer.hasReadStreamToEnd() && !hasReachedServerSideInsertedAdsTransition(renderer, readingPeriodHolder))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder reading) {
        MediaPeriodHolder nextPeriod = reading.getNext();
        return reading.info.isFollowedByTransitionToSameStream && nextPeriod.prepared && ((renderer instanceof TextRenderer) || (renderer instanceof MetadataRenderer) || renderer.getReadingPositionUs() >= nextPeriod.getStartPositionRendererTime());
    }

    private void setAllRendererStreamsFinal(long streamEndPositionUs) {
        for (Renderer renderer : this.renderers) {
            if (renderer.getStream() != null) {
                setCurrentStreamFinal(renderer, streamEndPositionUs);
            }
        }
    }

    private void setCurrentStreamFinal(Renderer renderer, long streamEndPositionUs) {
        renderer.setCurrentStreamFinal();
        if (renderer instanceof TextRenderer) {
            ((TextRenderer) renderer).setFinalStreamEndPositionUs(streamEndPositionUs);
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (!this.queue.isLoading(mediaPeriod)) {
            return;
        }
        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
        loadingPeriodHolder.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline);
        updateLoadControlTrackSelection(loadingPeriodHolder.info.id, loadingPeriodHolder.getTrackGroups(), loadingPeriodHolder.getTrackSelectorResult());
        if (loadingPeriodHolder == this.queue.getPlayingPeriod()) {
            resetRendererPosition(loadingPeriodHolder.info.startPositionUs);
            enableRenderers();
            this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, loadingPeriodHolder.info.startPositionUs, this.playbackInfo.requestedContentPositionUs, loadingPeriodHolder.info.startPositionUs, false, 5);
        }
        maybeContinueLoading();
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (!this.queue.isLoading(mediaPeriod)) {
            return;
        }
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        maybeContinueLoading();
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean acknowledgeCommand) throws ExoPlaybackException {
        handlePlaybackParameters(playbackParameters, playbackParameters.speed, true, acknowledgeCommand);
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, float currentPlaybackSpeed, boolean updatePlaybackInfo, boolean acknowledgeCommand) throws ExoPlaybackException {
        if (updatePlaybackInfo) {
            if (acknowledgeCommand) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(1);
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
        }
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setPlaybackSpeed(currentPlaybackSpeed, playbackParameters.speed);
            }
        }
    }

    private void maybeContinueLoading() {
        this.shouldContinueLoading = shouldContinueLoading();
        if (this.shouldContinueLoading) {
            this.queue.getLoadingPeriod().continueLoading(this.rendererPositionUs, this.mediaClock.getPlaybackParameters().speed, this.lastRebufferRealtimeMs);
        }
        updateIsLoading();
    }

    private boolean shouldContinueLoading() {
        long playbackPositionUs;
        long targetLiveOffsetUs;
        if (!isLoadingPossible()) {
            return false;
        }
        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
        long bufferedDurationUs = getTotalBufferedDurationUs(loadingPeriodHolder.getNextLoadPositionUs());
        if (loadingPeriodHolder == this.queue.getPlayingPeriod()) {
            playbackPositionUs = loadingPeriodHolder.toPeriodTime(this.rendererPositionUs);
        } else {
            playbackPositionUs = loadingPeriodHolder.toPeriodTime(this.rendererPositionUs) - loadingPeriodHolder.info.startPositionUs;
        }
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, loadingPeriodHolder.info.id)) {
            targetLiveOffsetUs = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
        } else {
            targetLiveOffsetUs = -9223372036854775807L;
        }
        LoadControl.Parameters loadParameters = new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, loadingPeriodHolder.info.id, playbackPositionUs, bufferedDurationUs, this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, targetLiveOffsetUs);
        boolean shouldContinueLoading = this.loadControl.shouldContinueLoading(loadParameters);
        MediaPeriodHolder playingPeriodHolder = this.queue.getPlayingPeriod();
        if (!shouldContinueLoading && playingPeriodHolder.prepared && bufferedDurationUs < PLAYBACK_BUFFER_EMPTY_THRESHOLD_US) {
            if (this.backBufferDurationUs > 0 || this.retainBackBufferFromKeyframe) {
                playingPeriodHolder.mediaPeriod.discardBuffer(this.playbackInfo.positionUs, false);
                return this.loadControl.shouldContinueLoading(loadParameters);
            }
            return shouldContinueLoading;
        }
        return shouldContinueLoading;
    }

    private boolean isLoadingPossible() {
        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
        if (loadingPeriodHolder == null) {
            return false;
        }
        long nextLoadPositionUs = loadingPeriodHolder.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            return false;
        }
        return true;
    }

    private void updateIsLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        boolean isLoading = this.shouldContinueLoading || (loadingPeriod != null && loadingPeriod.mediaPeriod.isLoading());
        if (isLoading != this.playbackInfo.isLoading) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(isLoading);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private androidx.media3.exoplayer.PlaybackInfo handlePositionDiscontinuity(androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r19, long r20, long r22, long r24, boolean r26, int r27) {
        /*
            r18 = this;
            r0 = r18
            r14 = r19
            r12 = r22
            boolean r1 = r0.deliverPendingMessageAtStartPositionRequired
            if (r1 != 0) goto L1f
            androidx.media3.exoplayer.PlaybackInfo r1 = r0.playbackInfo
            long r1 = r1.positionUs
            int r1 = (r20 > r1 ? 1 : (r20 == r1 ? 0 : -1))
            if (r1 != 0) goto L1f
            androidx.media3.exoplayer.PlaybackInfo r1 = r0.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r1 = r1.periodId
            boolean r1 = r14.equals(r1)
            if (r1 != 0) goto L1d
            goto L1f
        L1d:
            r1 = 0
            goto L20
        L1f:
            r1 = 1
        L20:
            r0.deliverPendingMessageAtStartPositionRequired = r1
            r18.resetPendingPauseAtEndOfPeriod()
            androidx.media3.exoplayer.PlaybackInfo r1 = r0.playbackInfo
            androidx.media3.exoplayer.source.TrackGroupArray r1 = r1.trackGroups
            androidx.media3.exoplayer.PlaybackInfo r2 = r0.playbackInfo
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r2 = r2.trackSelectorResult
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            java.util.List<androidx.media3.common.Metadata> r3 = r3.staticMetadata
            androidx.media3.exoplayer.MediaSourceList r4 = r0.mediaSourceList
            boolean r4 = r4.isPrepared()
            if (r4 == 0) goto L6f
            androidx.media3.exoplayer.MediaPeriodQueue r4 = r0.queue
            androidx.media3.exoplayer.MediaPeriodHolder r4 = r4.getPlayingPeriod()
            if (r4 != 0) goto L44
            androidx.media3.exoplayer.source.TrackGroupArray r5 = androidx.media3.exoplayer.source.TrackGroupArray.EMPTY
            goto L48
        L44:
            androidx.media3.exoplayer.source.TrackGroupArray r5 = r4.getTrackGroups()
        L48:
            r1 = r5
            if (r4 != 0) goto L4e
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r5 = r0.emptyTrackSelectorResult
            goto L52
        L4e:
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r5 = r4.getTrackSelectorResult()
        L52:
            r2 = r5
            androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r5 = r2.selections
            com.google.common.collect.ImmutableList r3 = r0.extractMetadataFromTrackSelectionArray(r5)
            if (r4 == 0) goto L6b
            androidx.media3.exoplayer.MediaPeriodInfo r5 = r4.info
            long r5 = r5.requestedContentPositionUs
            int r5 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r5 == 0) goto L6b
            androidx.media3.exoplayer.MediaPeriodInfo r5 = r4.info
            androidx.media3.exoplayer.MediaPeriodInfo r5 = r5.copyWithRequestedContentPositionUs(r12)
            r4.info = r5
        L6b:
            r18.maybeUpdateOffloadScheduling()
            goto L87
        L6f:
            androidx.media3.exoplayer.PlaybackInfo r4 = r0.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r4 = r4.periodId
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L87
            androidx.media3.exoplayer.source.TrackGroupArray r1 = androidx.media3.exoplayer.source.TrackGroupArray.EMPTY
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r2 = r0.emptyTrackSelectorResult
            com.google.common.collect.ImmutableList r3 = com.google.common.collect.ImmutableList.of()
            r15 = r1
            r16 = r2
            r17 = r3
            goto L8c
        L87:
            r15 = r1
            r16 = r2
            r17 = r3
        L8c:
            if (r26 == 0) goto L96
            androidx.media3.exoplayer.ExoPlayerImplInternal$PlaybackInfoUpdate r1 = r0.playbackInfoUpdate
            r11 = r27
            r1.setPositionDiscontinuity(r11)
            goto L98
        L96:
            r11 = r27
        L98:
            androidx.media3.exoplayer.PlaybackInfo r1 = r0.playbackInfo
            long r9 = r18.getTotalBufferedDurationUs()
            r2 = r19
            r3 = r20
            r5 = r22
            r7 = r24
            r11 = r15
            r12 = r16
            r13 = r17
            androidx.media3.exoplayer.PlaybackInfo r1 = r1.copyWithNewPosition(r2, r3, r5, r7, r9, r11, r12, r13)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.handlePositionDiscontinuity(androidx.media3.exoplayer.source.MediaSource$MediaPeriodId, long, long, long, boolean, int):androidx.media3.exoplayer.PlaybackInfo");
    }

    private ImmutableList<Metadata> extractMetadataFromTrackSelectionArray(ExoTrackSelection[] trackSelections) {
        ImmutableList.Builder<Metadata> result = new ImmutableList.Builder<>();
        boolean seenNonEmptyMetadata = false;
        for (ExoTrackSelection trackSelection : trackSelections) {
            if (trackSelection != null) {
                Format format = trackSelection.getFormat(0);
                if (format.metadata == null) {
                    result.add((ImmutableList.Builder<Metadata>) new Metadata(new Metadata.Entry[0]));
                } else {
                    result.add((ImmutableList.Builder<Metadata>) format.metadata);
                    seenNonEmptyMetadata = true;
                }
            }
        }
        return seenNonEmptyMetadata ? result.build() : ImmutableList.of();
    }

    private void enableRenderers() throws ExoPlaybackException {
        enableRenderers(new boolean[this.renderers.length], this.queue.getReadingPeriod().getStartPositionRendererTime());
    }

    private void enableRenderers(boolean[] rendererWasEnabledFlags, long startPositionUs) throws ExoPlaybackException {
        MediaPeriodHolder readingMediaPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingMediaPeriod.getTrackSelectorResult();
        for (int i = 0; i < this.renderers.length; i++) {
            if (!trackSelectorResult.isRendererEnabled(i) && this.renderersToReset.remove(this.renderers[i])) {
                this.renderers[i].reset();
            }
        }
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            if (trackSelectorResult.isRendererEnabled(i2)) {
                enableRenderer(i2, rendererWasEnabledFlags[i2], startPositionUs);
            }
        }
        readingMediaPeriod.allRenderersInCorrectState = true;
    }

    private void enableRenderer(int rendererIndex, boolean wasRendererEnabled, long startPositionUs) throws ExoPlaybackException {
        Renderer renderer = this.renderers[rendererIndex];
        if (isRendererEnabled(renderer)) {
            return;
        }
        MediaPeriodHolder periodHolder = this.queue.getReadingPeriod();
        boolean arePlayingAndReadingTheSamePeriod = periodHolder == this.queue.getPlayingPeriod();
        TrackSelectorResult trackSelectorResult = periodHolder.getTrackSelectorResult();
        RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[rendererIndex];
        ExoTrackSelection newSelection = trackSelectorResult.selections[rendererIndex];
        Format[] formats = getFormats(newSelection);
        boolean playing = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
        boolean joining = !wasRendererEnabled && playing;
        this.enabledRendererCount++;
        this.renderersToReset.add(renderer);
        renderer.enable(rendererConfiguration, formats, periodHolder.sampleStreams[rendererIndex], this.rendererPositionUs, joining, arePlayingAndReadingTheSamePeriod, startPositionUs, periodHolder.getRendererOffset(), periodHolder.info.id);
        renderer.handleMessage(11, new Renderer.WakeupListener() { // from class: androidx.media3.exoplayer.ExoPlayerImplInternal.1
            @Override // androidx.media3.exoplayer.Renderer.WakeupListener
            public void onSleep() {
                ExoPlayerImplInternal.this.requestForRendererSleep = true;
            }

            @Override // androidx.media3.exoplayer.Renderer.WakeupListener
            public void onWakeup() {
                if (ExoPlayerImplInternal.this.dynamicSchedulingEnabled || ExoPlayerImplInternal.this.offloadSchedulingEnabled) {
                    ExoPlayerImplInternal.this.handler.sendEmptyMessage(2);
                }
            }
        });
        this.mediaClock.onRendererEnabled(renderer);
        if (playing && arePlayingAndReadingTheSamePeriod) {
            renderer.start();
        }
    }

    private void releaseRenderers() {
        for (int i = 0; i < this.renderers.length; i++) {
            this.rendererCapabilities[i].clearListener();
            this.renderers[i].release();
        }
    }

    private void handleLoadingMediaPeriodChanged(boolean loadingTrackSelectionChanged) {
        long bufferedPositionUs;
        MediaPeriodHolder loadingMediaPeriodHolder = this.queue.getLoadingPeriod();
        MediaSource.MediaPeriodId loadingMediaPeriodId = loadingMediaPeriodHolder == null ? this.playbackInfo.periodId : loadingMediaPeriodHolder.info.id;
        boolean loadingMediaPeriodChanged = !this.playbackInfo.loadingMediaPeriodId.equals(loadingMediaPeriodId);
        if (loadingMediaPeriodChanged) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(loadingMediaPeriodId);
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (loadingMediaPeriodHolder == null) {
            bufferedPositionUs = this.playbackInfo.positionUs;
        } else {
            bufferedPositionUs = loadingMediaPeriodHolder.getBufferedPositionUs();
        }
        playbackInfo.bufferedPositionUs = bufferedPositionUs;
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((loadingMediaPeriodChanged || loadingTrackSelectionChanged) && loadingMediaPeriodHolder != null && loadingMediaPeriodHolder.prepared) {
            updateLoadControlTrackSelection(loadingMediaPeriodHolder.info.id, loadingMediaPeriodHolder.getTrackGroups(), loadingMediaPeriodHolder.getTrackSelectorResult());
        }
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long bufferedPositionInLoadingPeriodUs) {
        MediaPeriodHolder loadingPeriodHolder = this.queue.getLoadingPeriod();
        if (loadingPeriodHolder == null) {
            return 0L;
        }
        long totalBufferedDurationUs = bufferedPositionInLoadingPeriodUs - loadingPeriodHolder.toPeriodTime(this.rendererPositionUs);
        return Math.max(0L, totalBufferedDurationUs);
    }

    private void updateLoadControlTrackSelection(MediaSource.MediaPeriodId mediaPeriodId, TrackGroupArray trackGroups, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.playerId, this.playbackInfo.timeline, mediaPeriodId, this.renderers, trackGroups, trackSelectorResult.selections);
    }

    private boolean shouldPlayWhenReady() {
        return this.playbackInfo.playWhenReady && this.playbackInfo.playbackSuppressionReason == 0;
    }

    private static PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(Timeline timeline, PlaybackInfo playbackInfo, SeekPosition pendingInitialSeekPosition, MediaPeriodQueue queue, int repeatMode, boolean shuffleModeEnabled, Timeline.Window window, Timeline.Period period) {
        long j;
        int i;
        MediaSource.MediaPeriodId oldPeriodId;
        int startAtDefaultPositionWindowIndex;
        boolean endPlayback;
        boolean setTargetLiveOffset;
        int startAtDefaultPositionWindowIndex2;
        long contentPositionForAdResolutionUs;
        MediaSource.MediaPeriodId newPeriodId;
        long j2;
        if (timeline.isEmpty()) {
            return new PositionUpdateForPlaylistChange(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L, C.TIME_UNSET, false, true, false);
        }
        MediaSource.MediaPeriodId oldPeriodId2 = playbackInfo.periodId;
        Object newPeriodUid = oldPeriodId2.periodUid;
        boolean isUsingPlaceholderPeriod = isUsingPlaceholderPeriod(playbackInfo, period);
        if (playbackInfo.periodId.isAd() || isUsingPlaceholderPeriod) {
            j = playbackInfo.requestedContentPositionUs;
        } else {
            j = playbackInfo.positionUs;
        }
        long oldContentPositionUs = j;
        long newContentPositionUs = oldContentPositionUs;
        int startAtDefaultPositionWindowIndex3 = -1;
        boolean forceBufferingState = false;
        boolean endPlayback2 = false;
        boolean setTargetLiveOffset2 = false;
        boolean z = false;
        if (pendingInitialSeekPosition != null) {
            i = -1;
            Pair<Object, Long> periodPosition = resolveSeekPositionUs(timeline, pendingInitialSeekPosition, true, repeatMode, shuffleModeEnabled, window, period);
            if (periodPosition == null) {
                endPlayback2 = true;
                startAtDefaultPositionWindowIndex3 = timeline.getFirstWindowIndex(shuffleModeEnabled);
            } else {
                if (pendingInitialSeekPosition.windowPositionUs == C.TIME_UNSET) {
                    startAtDefaultPositionWindowIndex3 = timeline.getPeriodByUid(periodPosition.first, period).windowIndex;
                } else {
                    newPeriodUid = periodPosition.first;
                    newContentPositionUs = ((Long) periodPosition.second).longValue();
                    setTargetLiveOffset2 = true;
                }
                forceBufferingState = playbackInfo.playbackState == 4;
            }
            startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex3;
            endPlayback = endPlayback2;
            setTargetLiveOffset = setTargetLiveOffset2;
            oldPeriodId = oldPeriodId2;
        } else {
            i = -1;
            if (playbackInfo.timeline.isEmpty()) {
                int startAtDefaultPositionWindowIndex4 = timeline.getFirstWindowIndex(shuffleModeEnabled);
                startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex4;
                endPlayback = false;
                setTargetLiveOffset = false;
                oldPeriodId = oldPeriodId2;
            } else if (timeline.getIndexOfPeriod(newPeriodUid) == -1) {
                int newWindowIndex = resolveSubsequentPeriod(window, period, repeatMode, shuffleModeEnabled, newPeriodUid, playbackInfo.timeline, timeline);
                if (newWindowIndex == -1) {
                    endPlayback2 = true;
                    startAtDefaultPositionWindowIndex2 = timeline.getFirstWindowIndex(shuffleModeEnabled);
                } else {
                    startAtDefaultPositionWindowIndex2 = newWindowIndex;
                }
                startAtDefaultPositionWindowIndex = startAtDefaultPositionWindowIndex2;
                endPlayback = endPlayback2;
                setTargetLiveOffset = false;
                oldPeriodId = oldPeriodId2;
            } else if (oldContentPositionUs == C.TIME_UNSET) {
                startAtDefaultPositionWindowIndex = timeline.getPeriodByUid(newPeriodUid, period).windowIndex;
                endPlayback = false;
                setTargetLiveOffset = false;
                oldPeriodId = oldPeriodId2;
            } else if (!isUsingPlaceholderPeriod) {
                oldPeriodId = oldPeriodId2;
                startAtDefaultPositionWindowIndex = -1;
                endPlayback = false;
                setTargetLiveOffset = false;
            } else {
                oldPeriodId = oldPeriodId2;
                playbackInfo.timeline.getPeriodByUid(oldPeriodId.periodUid, period);
                if (playbackInfo.timeline.getWindow(period.windowIndex, window).firstPeriodIndex == playbackInfo.timeline.getIndexOfPeriod(oldPeriodId.periodUid)) {
                    long windowPositionUs = oldContentPositionUs + period.getPositionInWindowUs();
                    int windowIndex = timeline.getPeriodByUid(newPeriodUid, period).windowIndex;
                    Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window, period, windowIndex, windowPositionUs);
                    newPeriodUid = periodPositionUs.first;
                    newContentPositionUs = ((Long) periodPositionUs.second).longValue();
                }
                startAtDefaultPositionWindowIndex = -1;
                endPlayback = false;
                setTargetLiveOffset = true;
            }
        }
        long contentPositionForAdResolutionUs2 = newContentPositionUs;
        if (startAtDefaultPositionWindowIndex != i) {
            Pair<Object, Long> defaultPositionUs = timeline.getPeriodPositionUs(window, period, startAtDefaultPositionWindowIndex, C.TIME_UNSET);
            newPeriodUid = defaultPositionUs.first;
            long contentPositionForAdResolutionUs3 = ((Long) defaultPositionUs.second).longValue();
            newContentPositionUs = C.TIME_UNSET;
            contentPositionForAdResolutionUs = contentPositionForAdResolutionUs3;
        } else {
            contentPositionForAdResolutionUs = contentPositionForAdResolutionUs2;
        }
        MediaSource.MediaPeriodId periodIdWithAds = queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline, newPeriodUid, contentPositionForAdResolutionUs);
        boolean earliestCuePointIsUnchangedOrLater = periodIdWithAds.nextAdGroupIndex == i || (oldPeriodId.nextAdGroupIndex != i && periodIdWithAds.nextAdGroupIndex >= oldPeriodId.nextAdGroupIndex);
        boolean sameOldAndNewPeriodUid = oldPeriodId.periodUid.equals(newPeriodUid);
        if (sameOldAndNewPeriodUid && !oldPeriodId.isAd() && !periodIdWithAds.isAd() && earliestCuePointIsUnchangedOrLater) {
            z = true;
        }
        boolean onlyNextAdGroupIndexIncreased = z;
        boolean isInStreamAdChange = isIgnorableServerSideAdInsertionPeriodChange(isUsingPlaceholderPeriod, oldPeriodId, oldContentPositionUs, periodIdWithAds, timeline.getPeriodByUid(newPeriodUid, period), newContentPositionUs);
        MediaSource.MediaPeriodId newPeriodId2 = (onlyNextAdGroupIndexIncreased || isInStreamAdChange) ? oldPeriodId : periodIdWithAds;
        long periodPositionUs2 = contentPositionForAdResolutionUs;
        if (newPeriodId2.isAd()) {
            newPeriodId = newPeriodId2;
            if (!newPeriodId.equals(oldPeriodId)) {
                timeline.getPeriodByUid(newPeriodId.periodUid, period);
                if (newPeriodId.adIndexInAdGroup == period.getFirstAdIndexToPlay(newPeriodId.adGroupIndex)) {
                    j2 = period.getAdResumePositionUs();
                } else {
                    j2 = 0;
                }
                periodPositionUs2 = j2;
            } else {
                periodPositionUs2 = playbackInfo.positionUs;
            }
        } else {
            newPeriodId = newPeriodId2;
        }
        return new PositionUpdateForPlaylistChange(newPeriodId, periodPositionUs2, newContentPositionUs, forceBufferingState, endPlayback, setTargetLiveOffset);
    }

    private static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean isUsingPlaceholderPeriod, MediaSource.MediaPeriodId oldPeriodId, long oldContentPositionUs, MediaSource.MediaPeriodId newPeriodId, Timeline.Period newPeriod, long newContentPositionUs) {
        if (!isUsingPlaceholderPeriod && oldContentPositionUs == newContentPositionUs && oldPeriodId.periodUid.equals(newPeriodId.periodUid)) {
            return (oldPeriodId.isAd() && newPeriod.isServerSideInsertedAdGroup(oldPeriodId.adGroupIndex)) ? (newPeriod.getAdState(oldPeriodId.adGroupIndex, oldPeriodId.adIndexInAdGroup) == 4 || newPeriod.getAdState(oldPeriodId.adGroupIndex, oldPeriodId.adIndexInAdGroup) == 2) ? false : true : newPeriodId.isAd() && newPeriod.isServerSideInsertedAdGroup(newPeriodId.adGroupIndex);
        }
        return false;
    }

    private static boolean isUsingPlaceholderPeriod(PlaybackInfo playbackInfo, Timeline.Period period) {
        MediaSource.MediaPeriodId periodId = playbackInfo.periodId;
        Timeline timeline = playbackInfo.timeline;
        return timeline.isEmpty() || timeline.getPeriodByUid(periodId.periodUid, period).isPlaceholder;
    }

    private void updateRebufferingState(boolean isRebuffering, boolean resetLastRebufferRealtimeMs) {
        this.isRebuffering = isRebuffering;
        this.lastRebufferRealtimeMs = (!isRebuffering || resetLastRebufferRealtimeMs) ? C.TIME_UNSET : this.clock.elapsedRealtime();
    }

    private static boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo, Timeline newTimeline, Timeline previousTimeline, int repeatMode, boolean shuffleModeEnabled, Timeline.Window window, Timeline.Period period) {
        long msToUs;
        if (pendingMessageInfo.resolvedPeriodUid == null) {
            if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                msToUs = C.TIME_UNSET;
            } else {
                msToUs = Util.msToUs(pendingMessageInfo.message.getPositionMs());
            }
            long requestPositionUs = msToUs;
            Pair<Object, Long> periodPosition = resolveSeekPositionUs(newTimeline, new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getMediaItemIndex(), requestPositionUs), false, repeatMode, shuffleModeEnabled, window, period);
            if (periodPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(newTimeline.getIndexOfPeriod(periodPosition.first), ((Long) periodPosition.second).longValue(), periodPosition.first);
            if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
                resolvePendingMessageEndOfStreamPosition(newTimeline, pendingMessageInfo, window, period);
            }
            return true;
        }
        int index = newTimeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
        if (index == -1) {
            return false;
        }
        if (pendingMessageInfo.message.getPositionMs() == Long.MIN_VALUE) {
            resolvePendingMessageEndOfStreamPosition(newTimeline, pendingMessageInfo, window, period);
            return true;
        }
        pendingMessageInfo.resolvedPeriodIndex = index;
        previousTimeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period);
        if (period.isPlaceholder && previousTimeline.getWindow(period.windowIndex, window).firstPeriodIndex == previousTimeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid)) {
            long windowPositionUs = pendingMessageInfo.resolvedPeriodTimeUs + period.getPositionInWindowUs();
            int windowIndex = newTimeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period).windowIndex;
            Pair<Object, Long> periodPositionUs = newTimeline.getPeriodPositionUs(window, period, windowIndex, windowPositionUs);
            pendingMessageInfo.setResolvedPosition(newTimeline.getIndexOfPeriod(periodPositionUs.first), ((Long) periodPositionUs.second).longValue(), periodPositionUs.first);
        }
        return true;
    }

    private static void resolvePendingMessageEndOfStreamPosition(Timeline timeline, PendingMessageInfo messageInfo, Timeline.Window window, Timeline.Period period) {
        int windowIndex = timeline.getPeriodByUid(messageInfo.resolvedPeriodUid, period).windowIndex;
        int lastPeriodIndex = timeline.getWindow(windowIndex, window).lastPeriodIndex;
        Object lastPeriodUid = timeline.getPeriod(lastPeriodIndex, period, true).uid;
        long positionUs = period.durationUs != C.TIME_UNSET ? period.durationUs - 1 : Long.MAX_VALUE;
        messageInfo.setResolvedPosition(lastPeriodIndex, positionUs, lastPeriodUid);
    }

    private static Pair<Object, Long> resolveSeekPositionUs(Timeline timeline, SeekPosition seekPosition, boolean trySubsequentPeriods, int repeatMode, boolean shuffleModeEnabled, Timeline.Window window, Timeline.Period period) {
        Timeline seekTimeline;
        int newWindowIndex;
        Timeline seekTimeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (!seekTimeline2.isEmpty()) {
            seekTimeline = seekTimeline2;
        } else {
            seekTimeline = timeline;
        }
        try {
            Pair<Object, Long> periodPositionUs = seekTimeline.getPeriodPositionUs(window, period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (!timeline.equals(seekTimeline)) {
                int periodIndex = timeline.getIndexOfPeriod(periodPositionUs.first);
                if (periodIndex != -1) {
                    if (seekTimeline.getPeriodByUid(periodPositionUs.first, period).isPlaceholder && seekTimeline.getWindow(period.windowIndex, window).firstPeriodIndex == seekTimeline.getIndexOfPeriod(periodPositionUs.first)) {
                        return timeline.getPeriodPositionUs(window, period, timeline.getPeriodByUid(periodPositionUs.first, period).windowIndex, seekPosition.windowPositionUs);
                    }
                    return periodPositionUs;
                }
                if (!trySubsequentPeriods || (newWindowIndex = resolveSubsequentPeriod(window, period, repeatMode, shuffleModeEnabled, periodPositionUs.first, seekTimeline, timeline)) == -1) {
                    return null;
                }
                return timeline.getPeriodPositionUs(window, period, newWindowIndex, C.TIME_UNSET);
            }
            return periodPositionUs;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int resolveSubsequentPeriod(Timeline.Window window, Timeline.Period period, int repeatMode, boolean shuffleModeEnabled, Object oldPeriodUid, Timeline oldTimeline, Timeline newTimeline) {
        int i;
        int oldWindowIndex = oldTimeline.getPeriodByUid(oldPeriodUid, period).windowIndex;
        Object oldWindowUid = oldTimeline.getWindow(oldWindowIndex, window).uid;
        for (int i2 = 0; i2 < newTimeline.getWindowCount(); i2++) {
            if (newTimeline.getWindow(i2, window).uid.equals(oldWindowUid)) {
                return i2;
            }
        }
        int oldPeriodIndex = oldTimeline.getIndexOfPeriod(oldPeriodUid);
        int maxIterations = oldTimeline.getPeriodCount();
        int oldPeriodIndex2 = oldPeriodIndex;
        int newPeriodIndex = -1;
        int i3 = 0;
        while (i3 < maxIterations && newPeriodIndex == -1) {
            i = -1;
            int i4 = i3;
            oldPeriodIndex2 = oldTimeline.getNextPeriodIndex(oldPeriodIndex2, period, window, repeatMode, shuffleModeEnabled);
            if (oldPeriodIndex2 == -1) {
                break;
            }
            newPeriodIndex = newTimeline.getIndexOfPeriod(oldTimeline.getUidOfPeriod(oldPeriodIndex2));
            i3 = i4 + 1;
        }
        i = -1;
        if (newPeriodIndex == i) {
            return i;
        }
        return newTimeline.getPeriod(newPeriodIndex, period).windowIndex;
    }

    private static Format[] getFormats(ExoTrackSelection newSelection) {
        int length = newSelection != null ? newSelection.length() : 0;
        Format[] formats = new Format[length];
        for (int i = 0; i < length; i++) {
            formats[i] = newSelection.getFormat(i);
        }
        return formats;
    }

    private static boolean isRendererEnabled(Renderer renderer) {
        return renderer.getState() != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline, int windowIndex, long windowPositionUs) {
            this.timeline = timeline;
            this.windowIndex = windowIndex;
            this.windowPositionUs = windowPositionUs;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class PositionUpdateForPlaylistChange {
        public final boolean endPlayback;
        public final boolean forceBufferingState;
        public final MediaSource.MediaPeriodId periodId;
        public final long periodPositionUs;
        public final long requestedContentPositionUs;
        public final boolean setTargetLiveOffset;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId periodId, long periodPositionUs, long requestedContentPositionUs, boolean forceBufferingState, boolean endPlayback, boolean setTargetLiveOffset) {
            this.periodId = periodId;
            this.periodPositionUs = periodPositionUs;
            this.requestedContentPositionUs = requestedContentPositionUs;
            this.forceBufferingState = forceBufferingState;
            this.endPlayback = endPlayback;
            this.setTargetLiveOffset = setTargetLiveOffset;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage message) {
            this.message = message;
        }

        public void setResolvedPosition(int periodIndex, long periodTimeUs, Object periodUid) {
            this.resolvedPeriodIndex = periodIndex;
            this.resolvedPeriodTimeUs = periodTimeUs;
            this.resolvedPeriodUid = periodUid;
        }

        @Override // java.lang.Comparable
        public int compareTo(PendingMessageInfo other) {
            if ((this.resolvedPeriodUid == null) != (other.resolvedPeriodUid == null)) {
                return this.resolvedPeriodUid != null ? -1 : 1;
            }
            if (this.resolvedPeriodUid == null) {
                return 0;
            }
            int comparePeriodIndex = this.resolvedPeriodIndex - other.resolvedPeriodIndex;
            if (comparePeriodIndex != 0) {
                return comparePeriodIndex;
            }
            return Util.compareLong(this.resolvedPeriodTimeUs, other.resolvedPeriodTimeUs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class MediaSourceListUpdateMessage {
        private final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
        private final long positionUs;
        private final ShuffleOrder shuffleOrder;
        private final int windowIndex;

        private MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> mediaSourceHolders, ShuffleOrder shuffleOrder, int windowIndex, long positionUs) {
            this.mediaSourceHolders = mediaSourceHolders;
            this.shuffleOrder = shuffleOrder;
            this.windowIndex = windowIndex;
            this.positionUs = positionUs;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class MoveMediaItemsMessage {
        public final int fromIndex;
        public final int newFromIndex;
        public final ShuffleOrder shuffleOrder;
        public final int toIndex;

        public MoveMediaItemsMessage(int fromIndex, int toIndex, int newFromIndex, ShuffleOrder shuffleOrder) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.newFromIndex = newFromIndex;
            this.shuffleOrder = shuffleOrder;
        }
    }
}
