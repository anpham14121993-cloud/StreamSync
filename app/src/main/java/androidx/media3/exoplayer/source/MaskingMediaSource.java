package androidx.media3.exoplayer.source;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes7.dex */
public final class MaskingMediaSource extends WrappingMediaSource {
    private boolean hasRealTimeline;
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final Timeline.Period period;
    private MaskingTimeline timeline;
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;

    public MaskingMediaSource(MediaSource mediaSource, boolean useLazyPreparation) {
        super(mediaSource);
        this.useLazyPreparation = useLazyPreparation && mediaSource.isSingleWindow();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline initialTimeline = mediaSource.getInitialTimeline();
        if (initialTimeline != null) {
            this.timeline = MaskingTimeline.createWithRealTimeline(initialTimeline, null, null);
            this.hasRealTimeline = true;
        } else {
            this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaSource.getMediaItem());
        }
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        return this.mediaSource.canUpdateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void updateMediaItem(MediaItem mediaItem) {
        if (this.hasRealTimeline) {
            this.timeline = this.timeline.cloneWithUpdatedTimeline(new TimelineWithUpdatedMediaItem(this.timeline.timeline, mediaItem));
        } else {
            this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaItem);
        }
        this.mediaSource.updateMediaItem(mediaItem);
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    public void prepareSourceInternal() {
        if (!this.useLazyPreparation) {
            this.hasStartedPreparing = true;
            prepareChildSource();
        }
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId id, Allocator allocator, long startPositionUs) {
        MaskingMediaPeriod mediaPeriod = new MaskingMediaPeriod(id, allocator, startPositionUs);
        mediaPeriod.setMediaSource(this.mediaSource);
        if (this.isPrepared) {
            MediaSource.MediaPeriodId idInSource = id.copyWithPeriodUid(getInternalPeriodUid(id.periodUid));
            mediaPeriod.createPeriod(idInSource);
        } else {
            this.unpreparedMaskingMediaPeriod = mediaPeriod;
            if (!this.hasStartedPreparing) {
                this.hasStartedPreparing = true;
                prepareChildSource();
            }
        }
        return mediaPeriod;
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource, androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    @Override // androidx.media3.exoplayer.source.CompositeMediaSource, androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009c  */
    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onChildSourceInfoRefreshed(androidx.media3.common.Timeline r12) {
        /*
            r11 = this;
            r6 = 0
            boolean r0 = r11.isPrepared
            if (r0 == 0) goto L1c
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r11.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r0.cloneWithUpdatedTimeline(r12)
            r11.timeline = r0
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            if (r0 == 0) goto Lbc
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            long r0 = r0.getPreparePositionOverrideUs()
            r11.setPreparePositionOverrideToUnpreparedMaskingPeriod(r0)
            goto Lbc
        L1c:
            boolean r0 = r12.isEmpty()
            if (r0 == 0) goto L3a
        L23:
            boolean r0 = r11.hasRealTimeline
            if (r0 == 0) goto L2e
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r11.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r0.cloneWithUpdatedTimeline(r12)
            goto L36
        L2e:
            java.lang.Object r0 = androidx.media3.common.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r1 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r12, r0, r1)
        L36:
            r11.timeline = r0
            goto Lbc
        L3a:
            androidx.media3.common.Timeline$Window r0 = r11.window
            r1 = 0
            r12.getWindow(r1, r0)
            androidx.media3.common.Timeline$Window r0 = r11.window
            long r2 = r0.getDefaultPositionUs()
            androidx.media3.common.Timeline$Window r0 = r11.window
            java.lang.Object r7 = r0.uid
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            if (r0 == 0) goto L7a
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            long r4 = r0.getPreparePositionUs()
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r11.timeline
            androidx.media3.exoplayer.source.MaskingMediaPeriod r8 = r11.unpreparedMaskingMediaPeriod
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r8 = r8.id
            java.lang.Object r8 = r8.periodUid
            androidx.media3.common.Timeline$Period r9 = r11.period
            r0.getPeriodByUid(r8, r9)
            androidx.media3.common.Timeline$Period r0 = r11.period
            long r8 = r0.getPositionInWindowUs()
            long r8 = r8 + r4
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r11.timeline
            androidx.media3.common.Timeline$Window r10 = r11.window
            androidx.media3.common.Timeline$Window r0 = r0.getWindow(r1, r10)
            long r0 = r0.getDefaultPositionUs()
            int r10 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r10 == 0) goto L7a
            r2 = r8
            goto L7b
        L7a:
            r8 = r2
        L7b:
            androidx.media3.common.Timeline$Window r1 = r11.window
            androidx.media3.common.Timeline$Period r2 = r11.period
            r3 = 0
            r0 = r12
            r4 = r8
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            java.lang.Object r1 = r0.first
            java.lang.Object r2 = r0.second
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            boolean r4 = r11.hasRealTimeline
            if (r4 == 0) goto L9c
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r4 = r11.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r4 = r4.cloneWithUpdatedTimeline(r12)
            goto La0
        L9c:
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r4 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r12, r7, r1)
        La0:
            r11.timeline = r4
            androidx.media3.exoplayer.source.MaskingMediaPeriod r4 = r11.unpreparedMaskingMediaPeriod
            if (r4 == 0) goto Lbc
            androidx.media3.exoplayer.source.MaskingMediaPeriod r4 = r11.unpreparedMaskingMediaPeriod
            boolean r5 = r11.setPreparePositionOverrideToUnpreparedMaskingPeriod(r2)
            if (r5 == 0) goto Lbc
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r4.id
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r10 = r4.id
            java.lang.Object r10 = r10.periodUid
            java.lang.Object r10 = r11.getInternalPeriodUid(r10)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r6 = r5.copyWithPeriodUid(r10)
        Lbc:
            r0 = 1
            r11.hasRealTimeline = r0
            r11.isPrepared = r0
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r11.timeline
            r11.refreshSourceInfo(r0)
            if (r6 == 0) goto Ld3
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            androidx.media3.exoplayer.source.MaskingMediaPeriod r0 = (androidx.media3.exoplayer.source.MaskingMediaPeriod) r0
            r0.createPeriod(r6)
        Ld3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.MaskingMediaSource.onChildSourceInfoRefreshed(androidx.media3.common.Timeline):void");
    }

    @Override // androidx.media3.exoplayer.source.WrappingMediaSource
    protected MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    private Object getInternalPeriodUid(Object externalPeriodUid) {
        if (this.timeline.replacedInternalPeriodUid != null && externalPeriodUid.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID)) {
            return this.timeline.replacedInternalPeriodUid;
        }
        return externalPeriodUid;
    }

    private Object getExternalPeriodUid(Object internalPeriodUid) {
        if (this.timeline.replacedInternalPeriodUid != null && this.timeline.replacedInternalPeriodUid.equals(internalPeriodUid)) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }
        return internalPeriodUid;
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private boolean setPreparePositionOverrideToUnpreparedMaskingPeriod(long preparePositionOverrideUs) {
        MaskingMediaPeriod maskingPeriod = this.unpreparedMaskingMediaPeriod;
        int maskingPeriodIndex = this.timeline.getIndexOfPeriod(maskingPeriod.id.periodUid);
        if (maskingPeriodIndex == -1) {
            return false;
        }
        long periodDurationUs = this.timeline.getPeriod(maskingPeriodIndex, this.period).durationUs;
        if (periodDurationUs != C.TIME_UNSET && preparePositionOverrideUs >= periodDurationUs) {
            preparePositionOverrideUs = Math.max(0L, periodDurationUs - 1);
        }
        maskingPeriod.overridePreparePositionUs(preparePositionOverrideUs);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = new Object();
        private final Object replacedInternalPeriodUid;
        private final Object replacedInternalWindowUid;

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, Object firstWindowUid, Object firstPeriodUid) {
            return new MaskingTimeline(timeline, firstWindowUid, firstPeriodUid);
        }

        private MaskingTimeline(Timeline timeline, Object replacedInternalWindowUid, Object replacedInternalPeriodUid) {
            super(timeline);
            this.replacedInternalWindowUid = replacedInternalWindowUid;
            this.replacedInternalPeriodUid = replacedInternalPeriodUid;
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Window getWindow(int windowIndex, Timeline.Window window, long defaultPositionProjectionUs) {
            this.timeline.getWindow(windowIndex, window, defaultPositionProjectionUs);
            if (Util.areEqual(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int periodIndex, Timeline.Period period, boolean setIds) {
            this.timeline.getPeriod(periodIndex, period, setIds);
            if (Util.areEqual(period.uid, this.replacedInternalPeriodUid) && setIds) {
                period.uid = MASKING_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object uid) {
            Object obj;
            Timeline timeline = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(uid) && this.replacedInternalPeriodUid != null) {
                obj = this.replacedInternalPeriodUid;
            } else {
                obj = uid;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        @Override // androidx.media3.exoplayer.source.ForwardingTimeline, androidx.media3.common.Timeline
        public Object getUidOfPeriod(int periodIndex) {
            Object uid = this.timeline.getUidOfPeriod(periodIndex);
            return Util.areEqual(uid, this.replacedInternalPeriodUid) ? MASKING_EXTERNAL_PERIOD_UID : uid;
        }
    }

    /* loaded from: classes7.dex */
    public static final class PlaceholderTimeline extends Timeline {
        private final MediaItem mediaItem;

        public PlaceholderTimeline(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int windowIndex, Timeline.Window window, long defaultPositionProjectionUs) {
            window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, false, true, null, 0L, C.TIME_UNSET, 0, 0, 0L);
            window.isPlaceholder = true;
            return window;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int periodIndex, Timeline.Period period, boolean setIds) {
            period.set(setIds ? 0 : null, setIds ? MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID : null, 0, C.TIME_UNSET, 0L, AdPlaybackState.NONE, true);
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object uid) {
            return uid == MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID ? 0 : -1;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int periodIndex) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }
    }
}
