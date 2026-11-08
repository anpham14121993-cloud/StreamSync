package androidx.media3.exoplayer.analytics;

import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.analytics.PlaybackStats;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class PlaybackStatsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    private Format audioFormat;
    private long bandwidthBytes;
    private long bandwidthTimeMs;
    private final Callback callback;
    private long discontinuityFromPositionMs;
    private String discontinuityFromSession;
    private int discontinuityReason;
    private int droppedFrames;
    private final boolean keepHistory;
    private Exception nonFatalException;
    private Format videoFormat;
    private final PlaybackSessionManager sessionManager = new DefaultPlaybackSessionManager();
    private final Map<String, PlaybackStatsTracker> playbackStatsTrackers = new HashMap();
    private final Map<String, AnalyticsListener.EventTime> sessionStartEventTimes = new HashMap();
    private PlaybackStats finishedPlaybackStats = PlaybackStats.EMPTY;
    private final Timeline.Period period = new Timeline.Period();
    private VideoSize videoSize = VideoSize.UNKNOWN;

    /* loaded from: classes9.dex */
    public interface Callback {
        void onPlaybackStatsReady(AnalyticsListener.EventTime eventTime, PlaybackStats playbackStats);
    }

    public PlaybackStatsListener(boolean keepHistory, Callback callback) {
        this.callback = callback;
        this.keepHistory = keepHistory;
        this.sessionManager.setListener(this);
    }

    public PlaybackStats getCombinedPlaybackStats() {
        PlaybackStats[] allPendingPlaybackStats = new PlaybackStats[this.playbackStatsTrackers.size() + 1];
        allPendingPlaybackStats[0] = this.finishedPlaybackStats;
        int index = 1;
        for (PlaybackStatsTracker tracker : this.playbackStatsTrackers.values()) {
            allPendingPlaybackStats[index] = tracker.build(false);
            index++;
        }
        return PlaybackStats.merge(allPendingPlaybackStats);
    }

    public PlaybackStats getPlaybackStats() {
        String activeSessionId = this.sessionManager.getActiveSessionId();
        PlaybackStatsTracker activeStatsTracker = activeSessionId == null ? null : this.playbackStatsTrackers.get(activeSessionId);
        if (activeStatsTracker == null) {
            return null;
        }
        return activeStatsTracker.build(false);
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String sessionId) {
        PlaybackStatsTracker tracker = new PlaybackStatsTracker(this.keepHistory, eventTime);
        this.playbackStatsTrackers.put(sessionId, tracker);
        this.sessionStartEventTimes.put(sessionId, eventTime);
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionActive(AnalyticsListener.EventTime eventTime, String sessionId) {
        ((PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.get(sessionId))).onForeground();
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String contentSessionId, String adSessionId) {
        ((PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.get(contentSessionId))).onInterruptedByAd();
    }

    @Override // androidx.media3.exoplayer.analytics.PlaybackSessionManager.Listener
    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String sessionId, boolean automaticTransitionToNextPlayback) {
        long discontinuityFromPositionMs;
        PlaybackStatsTracker tracker = (PlaybackStatsTracker) Assertions.checkNotNull(this.playbackStatsTrackers.remove(sessionId));
        AnalyticsListener.EventTime startEventTime = (AnalyticsListener.EventTime) Assertions.checkNotNull(this.sessionStartEventTimes.remove(sessionId));
        if (sessionId.equals(this.discontinuityFromSession)) {
            discontinuityFromPositionMs = this.discontinuityFromPositionMs;
        } else {
            discontinuityFromPositionMs = C.TIME_UNSET;
        }
        tracker.onFinished(eventTime, automaticTransitionToNextPlayback, discontinuityFromPositionMs);
        PlaybackStats playbackStats = tracker.build(true);
        this.finishedPlaybackStats = PlaybackStats.merge(this.finishedPlaybackStats, playbackStats);
        if (this.callback != null) {
            this.callback.onPlaybackStatsReady(startEventTime, playbackStats);
        }
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
        if (this.discontinuityFromSession == null) {
            this.discontinuityFromSession = this.sessionManager.getActiveSessionId();
            this.discontinuityFromPositionMs = oldPosition.positionMs;
        }
        this.discontinuityReason = reason;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int droppedFrames, long elapsedMs) {
        this.droppedFrames = droppedFrames;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException error, boolean wasCanceled) {
        this.nonFatalException = error;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception error) {
        this.nonFatalException = error;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int totalLoadTimeMs, long totalBytesLoaded, long bitrateEstimate) {
        this.bandwidthTimeMs = totalLoadTimeMs;
        this.bandwidthBytes = totalBytesLoaded;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        if (mediaLoadData.trackType == 2 || mediaLoadData.trackType == 0) {
            this.videoFormat = mediaLoadData.trackFormat;
        } else if (mediaLoadData.trackType == 1) {
            this.audioFormat = mediaLoadData.trackFormat;
        }
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        this.videoSize = videoSize;
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
    public void onEvents(Player player, AnalyticsListener.Events events) {
        if (events.size() == 0) {
            return;
        }
        maybeAddSessions(events);
        Iterator<String> it = this.playbackStatsTrackers.keySet().iterator();
        while (true) {
            VideoSize videoSize = null;
            if (!it.hasNext()) {
                break;
            }
            String session = it.next();
            Pair<AnalyticsListener.EventTime, Boolean> eventTimeAndBelongsToPlayback = findBestEventTime(events, session);
            PlaybackStatsTracker tracker = this.playbackStatsTrackers.get(session);
            boolean hasDiscontinuityToPlayback = hasEvent(events, session, 11);
            boolean hasDroppedFrames = hasEvent(events, session, 1018);
            boolean hasAudioUnderrun = hasEvent(events, session, 1011);
            boolean startedLoading = hasEvent(events, session, 1000);
            boolean hasFatalError = hasEvent(events, session, 10);
            boolean hasNonFatalException = hasEvent(events, session, 1003) || hasEvent(events, session, 1024);
            boolean hasBandwidthData = hasEvent(events, session, 1006);
            boolean hasFormatData = hasEvent(events, session, 1004);
            boolean hasVideoSize = hasEvent(events, session, 25);
            AnalyticsListener.EventTime eventTime = (AnalyticsListener.EventTime) eventTimeAndBelongsToPlayback.first;
            boolean booleanValue = ((Boolean) eventTimeAndBelongsToPlayback.second).booleanValue();
            long j = session.equals(this.discontinuityFromSession) ? this.discontinuityFromPositionMs : C.TIME_UNSET;
            int i = hasDroppedFrames ? this.droppedFrames : 0;
            PlaybackException playerError = hasFatalError ? player.getPlayerError() : null;
            Exception exc = hasNonFatalException ? this.nonFatalException : null;
            long j2 = hasBandwidthData ? this.bandwidthTimeMs : 0L;
            long j3 = hasBandwidthData ? this.bandwidthBytes : 0L;
            Format format = hasFormatData ? this.videoFormat : null;
            Format format2 = hasFormatData ? this.audioFormat : null;
            if (hasVideoSize) {
                videoSize = this.videoSize;
            }
            tracker.onEvents(player, eventTime, booleanValue, j, hasDiscontinuityToPlayback, i, hasAudioUnderrun, startedLoading, playerError, exc, j2, j3, format, format2, videoSize);
        }
        this.videoFormat = null;
        this.audioFormat = null;
        this.discontinuityFromSession = null;
        if (events.contains(AnalyticsListener.EVENT_PLAYER_RELEASED)) {
            this.sessionManager.finishAllSessions(events.getEventTime(AnalyticsListener.EVENT_PLAYER_RELEASED));
        }
    }

    private void maybeAddSessions(AnalyticsListener.Events events) {
        for (int i = 0; i < events.size(); i++) {
            int event = events.get(i);
            AnalyticsListener.EventTime eventTime = events.getEventTime(event);
            if (event == 0) {
                this.sessionManager.updateSessionsWithTimelineChange(eventTime);
            } else if (event == 11) {
                this.sessionManager.updateSessionsWithDiscontinuity(eventTime, this.discontinuityReason);
            } else {
                this.sessionManager.updateSessions(eventTime);
            }
        }
    }

    private Pair<AnalyticsListener.EventTime, Boolean> findBestEventTime(AnalyticsListener.Events events, String session) {
        boolean belongsToPlayback;
        AnalyticsListener.EventTime eventTime = null;
        boolean belongsToPlayback2 = false;
        for (int i = 0; i < events.size(); i++) {
            int event = events.get(i);
            AnalyticsListener.EventTime newEventTime = events.getEventTime(event);
            boolean newBelongsToPlayback = this.sessionManager.belongsToSession(newEventTime, session);
            if (eventTime == null || ((newBelongsToPlayback && !belongsToPlayback2) || (newBelongsToPlayback == belongsToPlayback2 && newEventTime.realtimeMs > eventTime.realtimeMs))) {
                eventTime = newEventTime;
                belongsToPlayback2 = newBelongsToPlayback;
            }
        }
        Assertions.checkNotNull(eventTime);
        if (!belongsToPlayback2 && eventTime.mediaPeriodId != null && eventTime.mediaPeriodId.isAd()) {
            long contentPeriodPositionUs = eventTime.timeline.getPeriodByUid(eventTime.mediaPeriodId.periodUid, this.period).getAdGroupTimeUs(eventTime.mediaPeriodId.adGroupIndex);
            if (contentPeriodPositionUs == Long.MIN_VALUE) {
                contentPeriodPositionUs = this.period.durationUs;
            }
            long contentWindowPositionUs = this.period.getPositionInWindowUs() + contentPeriodPositionUs;
            long j = eventTime.realtimeMs;
            Timeline timeline = eventTime.timeline;
            int i2 = eventTime.windowIndex;
            MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(eventTime.mediaPeriodId.periodUid, eventTime.mediaPeriodId.windowSequenceNumber, eventTime.mediaPeriodId.adGroupIndex);
            long usToMs = Util.usToMs(contentWindowPositionUs);
            Timeline timeline2 = eventTime.timeline;
            int i3 = eventTime.currentWindowIndex;
            MediaSource.MediaPeriodId mediaPeriodId2 = eventTime.currentMediaPeriodId;
            long contentWindowPositionUs2 = eventTime.currentPlaybackPositionMs;
            eventTime = new AnalyticsListener.EventTime(j, timeline, i2, mediaPeriodId, usToMs, timeline2, i3, mediaPeriodId2, contentWindowPositionUs2, eventTime.totalBufferedDurationMs);
            belongsToPlayback = this.sessionManager.belongsToSession(eventTime, session);
        } else {
            belongsToPlayback = belongsToPlayback2;
        }
        return Pair.create(eventTime, Boolean.valueOf(belongsToPlayback));
    }

    private boolean hasEvent(AnalyticsListener.Events events, String session, int event) {
        return events.contains(event) && this.sessionManager.belongsToSession(events.getEventTime(event), session);
    }

    /* loaded from: classes9.dex */
    private static final class PlaybackStatsTracker {
        private long audioFormatBitrateTimeProduct;
        private final List<PlaybackStats.EventTimeAndFormat> audioFormatHistory;
        private long audioFormatTimeMs;
        private long audioUnderruns;
        private long bandwidthBytes;
        private long bandwidthTimeMs;
        private Format currentAudioFormat;
        private float currentPlaybackSpeed;
        private int currentPlaybackState;
        private long currentPlaybackStateStartTimeMs;
        private Format currentVideoFormat;
        private long droppedFrames;
        private int fatalErrorCount;
        private final List<PlaybackStats.EventTimeAndException> fatalErrorHistory;
        private long firstReportedTimeMs;
        private boolean hasBeenReady;
        private boolean hasEnded;
        private boolean hasFatalError;
        private long initialAudioFormatBitrate;
        private long initialVideoFormatBitrate;
        private int initialVideoFormatHeight;
        private final boolean isAd;
        private boolean isForeground;
        private boolean isInterruptedByAd;
        private boolean isJoinTimeInvalid;
        private boolean isSeeking;
        private final boolean keepHistory;
        private long lastAudioFormatStartTimeMs;
        private long lastRebufferStartTimeMs;
        private long lastVideoFormatStartTimeMs;
        private long maxRebufferTimeMs;
        private final List<long[]> mediaTimeHistory;
        private int nonFatalErrorCount;
        private final List<PlaybackStats.EventTimeAndException> nonFatalErrorHistory;
        private int pauseBufferCount;
        private int pauseCount;
        private final long[] playbackStateDurationsMs = new long[16];
        private final List<PlaybackStats.EventTimeAndPlaybackState> playbackStateHistory;
        private int rebufferCount;
        private int seekCount;
        private boolean startedLoading;
        private long videoFormatBitrateTimeMs;
        private long videoFormatBitrateTimeProduct;
        private long videoFormatHeightTimeMs;
        private long videoFormatHeightTimeProduct;
        private final List<PlaybackStats.EventTimeAndFormat> videoFormatHistory;

        public PlaybackStatsTracker(boolean keepHistory, AnalyticsListener.EventTime startTime) {
            this.keepHistory = keepHistory;
            this.playbackStateHistory = keepHistory ? new ArrayList<>() : Collections.emptyList();
            this.mediaTimeHistory = keepHistory ? new ArrayList<>() : Collections.emptyList();
            this.videoFormatHistory = keepHistory ? new ArrayList<>() : Collections.emptyList();
            this.audioFormatHistory = keepHistory ? new ArrayList<>() : Collections.emptyList();
            this.fatalErrorHistory = keepHistory ? new ArrayList<>() : Collections.emptyList();
            this.nonFatalErrorHistory = keepHistory ? new ArrayList<>() : Collections.emptyList();
            boolean z = false;
            this.currentPlaybackState = 0;
            this.currentPlaybackStateStartTimeMs = startTime.realtimeMs;
            this.firstReportedTimeMs = C.TIME_UNSET;
            this.maxRebufferTimeMs = C.TIME_UNSET;
            if (startTime.mediaPeriodId != null && startTime.mediaPeriodId.isAd()) {
                z = true;
            }
            this.isAd = z;
            this.initialAudioFormatBitrate = -1L;
            this.initialVideoFormatBitrate = -1L;
            this.initialVideoFormatHeight = -1;
            this.currentPlaybackSpeed = 1.0f;
        }

        public void onForeground() {
            this.isForeground = true;
        }

        public void onInterruptedByAd() {
            this.isInterruptedByAd = true;
            this.isSeeking = false;
        }

        public void onFinished(AnalyticsListener.EventTime eventTime, boolean automaticTransition, long discontinuityFromPositionMs) {
            int i = 11;
            if (this.currentPlaybackState != 11 && !automaticTransition) {
                i = 15;
            }
            int finalPlaybackState = i;
            maybeUpdateMediaTimeHistory(eventTime.realtimeMs, discontinuityFromPositionMs);
            maybeRecordVideoFormatTime(eventTime.realtimeMs);
            maybeRecordAudioFormatTime(eventTime.realtimeMs);
            updatePlaybackState(finalPlaybackState, eventTime);
        }

        public void onEvents(Player player, AnalyticsListener.EventTime eventTime, boolean belongsToPlayback, long discontinuityFromPositionMs, boolean hasDiscontinuity, int droppedFrameCount, boolean hasAudioUnderun, boolean startedLoading, PlaybackException fatalError, Exception nonFatalException, long bandwidthTimeMs, long bandwidthBytes, Format videoFormat, Format audioFormat, VideoSize videoSize) {
            if (discontinuityFromPositionMs != C.TIME_UNSET) {
                maybeUpdateMediaTimeHistory(eventTime.realtimeMs, discontinuityFromPositionMs);
                this.isSeeking = true;
            }
            if (player.getPlaybackState() != 2) {
                this.isSeeking = false;
            }
            int playerPlaybackState = player.getPlaybackState();
            if (playerPlaybackState == 1 || playerPlaybackState == 4 || hasDiscontinuity) {
                this.isInterruptedByAd = false;
            }
            if (fatalError != null) {
                this.hasFatalError = true;
                this.fatalErrorCount++;
                if (this.keepHistory) {
                    this.fatalErrorHistory.add(new PlaybackStats.EventTimeAndException(eventTime, fatalError));
                }
            } else if (player.getPlayerError() == null) {
                this.hasFatalError = false;
            }
            if (this.isForeground && !this.isInterruptedByAd) {
                Tracks currentTracks = player.getCurrentTracks();
                if (!currentTracks.isTypeSelected(2)) {
                    maybeUpdateVideoFormat(eventTime, null);
                }
                if (!currentTracks.isTypeSelected(1)) {
                    maybeUpdateAudioFormat(eventTime, null);
                }
            }
            if (videoFormat != null) {
                maybeUpdateVideoFormat(eventTime, videoFormat);
            }
            if (audioFormat != null) {
                maybeUpdateAudioFormat(eventTime, audioFormat);
            }
            if (this.currentVideoFormat != null && this.currentVideoFormat.height == -1 && videoSize != null) {
                Format formatWithHeightAndWidth = this.currentVideoFormat.buildUpon().setWidth(videoSize.width).setHeight(videoSize.height).build();
                maybeUpdateVideoFormat(eventTime, formatWithHeightAndWidth);
            }
            if (startedLoading) {
                this.startedLoading = true;
            }
            if (hasAudioUnderun) {
                this.audioUnderruns++;
            }
            this.droppedFrames += droppedFrameCount;
            this.bandwidthTimeMs += bandwidthTimeMs;
            this.bandwidthBytes += bandwidthBytes;
            if (nonFatalException != null) {
                this.nonFatalErrorCount++;
                if (this.keepHistory) {
                    this.nonFatalErrorHistory.add(new PlaybackStats.EventTimeAndException(eventTime, nonFatalException));
                }
            }
            int newPlaybackState = resolveNewPlaybackState(player);
            float newPlaybackSpeed = player.getPlaybackParameters().speed;
            if (this.currentPlaybackState != newPlaybackState || this.currentPlaybackSpeed != newPlaybackSpeed) {
                maybeUpdateMediaTimeHistory(eventTime.realtimeMs, belongsToPlayback ? eventTime.eventPlaybackPositionMs : C.TIME_UNSET);
                maybeRecordVideoFormatTime(eventTime.realtimeMs);
                maybeRecordAudioFormatTime(eventTime.realtimeMs);
            }
            this.currentPlaybackSpeed = newPlaybackSpeed;
            if (this.currentPlaybackState != newPlaybackState) {
                updatePlaybackState(newPlaybackState, eventTime);
            }
        }

        public PlaybackStats build(boolean z) {
            long j;
            long[] jArr = this.playbackStateDurationsMs;
            List<long[]> list = this.mediaTimeHistory;
            if (!z) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                jArr = Arrays.copyOf(this.playbackStateDurationsMs, 16);
                long max = Math.max(0L, elapsedRealtime - this.currentPlaybackStateStartTimeMs);
                int i = this.currentPlaybackState;
                jArr[i] = jArr[i] + max;
                maybeUpdateMaxRebufferTimeMs(elapsedRealtime);
                maybeRecordVideoFormatTime(elapsedRealtime);
                maybeRecordAudioFormatTime(elapsedRealtime);
                list = new ArrayList(this.mediaTimeHistory);
                if (this.keepHistory && this.currentPlaybackState == 3) {
                    list.add(guessMediaTimeBasedOnElapsedRealtime(elapsedRealtime));
                }
            }
            boolean z2 = this.isJoinTimeInvalid || !this.hasBeenReady;
            if (z2) {
                j = -9223372036854775807L;
            } else {
                j = jArr[2];
            }
            boolean z3 = jArr[1] > 0;
            List arrayList = z ? this.videoFormatHistory : new ArrayList(this.videoFormatHistory);
            List arrayList2 = z ? this.audioFormatHistory : new ArrayList(this.audioFormatHistory);
            List arrayList3 = z ? this.playbackStateHistory : new ArrayList(this.playbackStateHistory);
            long j2 = this.firstReportedTimeMs;
            boolean z4 = this.isForeground;
            return new PlaybackStats(1, jArr, arrayList3, list, j2, z4 ? 1 : 0, !this.hasBeenReady ? 1 : 0, this.hasEnded ? 1 : 0, z3 ? 1 : 0, j, z2 ? 0 : 1, this.pauseCount, this.pauseBufferCount, this.seekCount, this.rebufferCount, this.maxRebufferTimeMs, this.isAd ? 1 : 0, arrayList, arrayList2, this.videoFormatHeightTimeMs, this.videoFormatHeightTimeProduct, this.videoFormatBitrateTimeMs, this.videoFormatBitrateTimeProduct, this.audioFormatTimeMs, this.audioFormatBitrateTimeProduct, this.initialVideoFormatHeight == -1 ? 0 : 1, this.initialVideoFormatBitrate == -1 ? 0 : 1, this.initialVideoFormatHeight, this.initialVideoFormatBitrate, this.initialAudioFormatBitrate == -1 ? 0 : 1, this.initialAudioFormatBitrate, this.bandwidthTimeMs, this.bandwidthBytes, this.droppedFrames, this.audioUnderruns, this.fatalErrorCount > 0 ? 1 : 0, this.fatalErrorCount, this.nonFatalErrorCount, this.fatalErrorHistory, this.nonFatalErrorHistory);
        }

        private void updatePlaybackState(int newPlaybackState, AnalyticsListener.EventTime eventTime) {
            Assertions.checkArgument(eventTime.realtimeMs >= this.currentPlaybackStateStartTimeMs);
            long stateDurationMs = eventTime.realtimeMs - this.currentPlaybackStateStartTimeMs;
            long[] jArr = this.playbackStateDurationsMs;
            int i = this.currentPlaybackState;
            jArr[i] = jArr[i] + stateDurationMs;
            if (this.firstReportedTimeMs == C.TIME_UNSET) {
                this.firstReportedTimeMs = eventTime.realtimeMs;
            }
            this.isJoinTimeInvalid |= isInvalidJoinTransition(this.currentPlaybackState, newPlaybackState);
            this.hasBeenReady |= isReadyState(newPlaybackState);
            this.hasEnded |= newPlaybackState == 11;
            if (!isPausedState(this.currentPlaybackState) && isPausedState(newPlaybackState)) {
                this.pauseCount++;
            }
            if (newPlaybackState == 5) {
                this.seekCount++;
            }
            if (!isRebufferingState(this.currentPlaybackState) && isRebufferingState(newPlaybackState)) {
                this.rebufferCount++;
                this.lastRebufferStartTimeMs = eventTime.realtimeMs;
            }
            if (isRebufferingState(this.currentPlaybackState) && this.currentPlaybackState != 7 && newPlaybackState == 7) {
                this.pauseBufferCount++;
            }
            maybeUpdateMaxRebufferTimeMs(eventTime.realtimeMs);
            this.currentPlaybackState = newPlaybackState;
            this.currentPlaybackStateStartTimeMs = eventTime.realtimeMs;
            if (this.keepHistory) {
                this.playbackStateHistory.add(new PlaybackStats.EventTimeAndPlaybackState(eventTime, this.currentPlaybackState));
            }
        }

        private int resolveNewPlaybackState(Player player) {
            int playerPlaybackState = player.getPlaybackState();
            if (this.isSeeking && this.isForeground) {
                return 5;
            }
            if (this.hasFatalError) {
                return 13;
            }
            if (!this.isForeground) {
                return this.startedLoading ? 1 : 0;
            }
            if (this.isInterruptedByAd) {
                return 14;
            }
            if (playerPlaybackState == 4) {
                return 11;
            }
            if (playerPlaybackState == 2) {
                if (this.currentPlaybackState == 0 || this.currentPlaybackState == 1 || this.currentPlaybackState == 2 || this.currentPlaybackState == 14) {
                    return 2;
                }
                if (!player.getPlayWhenReady()) {
                    return 7;
                }
                if (player.getPlaybackSuppressionReason() != 0) {
                    return 10;
                }
                return 6;
            }
            if (playerPlaybackState == 3) {
                if (!player.getPlayWhenReady()) {
                    return 4;
                }
                if (player.getPlaybackSuppressionReason() == 0) {
                    return 3;
                }
                return 9;
            }
            if (playerPlaybackState == 1 && this.currentPlaybackState != 0) {
                return 12;
            }
            return this.currentPlaybackState;
        }

        private void maybeUpdateMaxRebufferTimeMs(long nowMs) {
            if (isRebufferingState(this.currentPlaybackState)) {
                long rebufferDurationMs = nowMs - this.lastRebufferStartTimeMs;
                if (this.maxRebufferTimeMs == C.TIME_UNSET || rebufferDurationMs > this.maxRebufferTimeMs) {
                    this.maxRebufferTimeMs = rebufferDurationMs;
                }
            }
        }

        private void maybeUpdateMediaTimeHistory(long realtimeMs, long mediaTimeMs) {
            if (!this.keepHistory) {
                return;
            }
            if (this.currentPlaybackState != 3) {
                if (mediaTimeMs == C.TIME_UNSET) {
                    return;
                }
                if (!this.mediaTimeHistory.isEmpty()) {
                    long previousMediaTimeMs = this.mediaTimeHistory.get(this.mediaTimeHistory.size() - 1)[1];
                    if (previousMediaTimeMs != mediaTimeMs) {
                        this.mediaTimeHistory.add(new long[]{realtimeMs, previousMediaTimeMs});
                    }
                }
            }
            if (mediaTimeMs != C.TIME_UNSET) {
                this.mediaTimeHistory.add(new long[]{realtimeMs, mediaTimeMs});
            } else if (!this.mediaTimeHistory.isEmpty()) {
                this.mediaTimeHistory.add(guessMediaTimeBasedOnElapsedRealtime(realtimeMs));
            }
        }

        private long[] guessMediaTimeBasedOnElapsedRealtime(long realtimeMs) {
            long[] previousKnownMediaTimeHistory = this.mediaTimeHistory.get(this.mediaTimeHistory.size() - 1);
            long previousRealtimeMs = previousKnownMediaTimeHistory[0];
            long previousMediaTimeMs = previousKnownMediaTimeHistory[1];
            long elapsedMediaTimeEstimateMs = ((float) (realtimeMs - previousRealtimeMs)) * this.currentPlaybackSpeed;
            long mediaTimeEstimateMs = previousMediaTimeMs + elapsedMediaTimeEstimateMs;
            return new long[]{realtimeMs, mediaTimeEstimateMs};
        }

        private void maybeUpdateVideoFormat(AnalyticsListener.EventTime eventTime, Format newFormat) {
            if (Util.areEqual(this.currentVideoFormat, newFormat)) {
                return;
            }
            maybeRecordVideoFormatTime(eventTime.realtimeMs);
            if (newFormat != null) {
                if (this.initialVideoFormatHeight == -1 && newFormat.height != -1) {
                    this.initialVideoFormatHeight = newFormat.height;
                }
                if (this.initialVideoFormatBitrate == -1 && newFormat.bitrate != -1) {
                    this.initialVideoFormatBitrate = newFormat.bitrate;
                }
            }
            this.currentVideoFormat = newFormat;
            if (this.keepHistory) {
                this.videoFormatHistory.add(new PlaybackStats.EventTimeAndFormat(eventTime, this.currentVideoFormat));
            }
        }

        private void maybeUpdateAudioFormat(AnalyticsListener.EventTime eventTime, Format newFormat) {
            if (Util.areEqual(this.currentAudioFormat, newFormat)) {
                return;
            }
            maybeRecordAudioFormatTime(eventTime.realtimeMs);
            if (newFormat != null && this.initialAudioFormatBitrate == -1 && newFormat.bitrate != -1) {
                this.initialAudioFormatBitrate = newFormat.bitrate;
            }
            this.currentAudioFormat = newFormat;
            if (this.keepHistory) {
                this.audioFormatHistory.add(new PlaybackStats.EventTimeAndFormat(eventTime, this.currentAudioFormat));
            }
        }

        private void maybeRecordVideoFormatTime(long nowMs) {
            if (this.currentPlaybackState == 3 && this.currentVideoFormat != null) {
                long mediaDurationMs = ((float) (nowMs - this.lastVideoFormatStartTimeMs)) * this.currentPlaybackSpeed;
                if (this.currentVideoFormat.height != -1) {
                    this.videoFormatHeightTimeMs += mediaDurationMs;
                    this.videoFormatHeightTimeProduct += this.currentVideoFormat.height * mediaDurationMs;
                }
                if (this.currentVideoFormat.bitrate != -1) {
                    this.videoFormatBitrateTimeMs += mediaDurationMs;
                    this.videoFormatBitrateTimeProduct += this.currentVideoFormat.bitrate * mediaDurationMs;
                }
            }
            this.lastVideoFormatStartTimeMs = nowMs;
        }

        private void maybeRecordAudioFormatTime(long nowMs) {
            if (this.currentPlaybackState == 3 && this.currentAudioFormat != null && this.currentAudioFormat.bitrate != -1) {
                long mediaDurationMs = ((float) (nowMs - this.lastAudioFormatStartTimeMs)) * this.currentPlaybackSpeed;
                this.audioFormatTimeMs += mediaDurationMs;
                this.audioFormatBitrateTimeProduct += this.currentAudioFormat.bitrate * mediaDurationMs;
            }
            this.lastAudioFormatStartTimeMs = nowMs;
        }

        private static boolean isReadyState(int state) {
            return state == 3 || state == 4 || state == 9;
        }

        private static boolean isPausedState(int state) {
            return state == 4 || state == 7;
        }

        private static boolean isRebufferingState(int state) {
            return state == 6 || state == 7 || state == 10;
        }

        private static boolean isInvalidJoinTransition(int oldState, int newState) {
            return ((oldState != 1 && oldState != 2 && oldState != 14) || newState == 1 || newState == 2 || newState == 14 || newState == 3 || newState == 4 || newState == 9 || newState == 11) ? false : true;
        }
    }
}
