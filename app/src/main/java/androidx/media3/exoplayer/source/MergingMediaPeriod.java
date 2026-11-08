package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* loaded from: classes7.dex */
final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaPeriod[] periods;
    private TrackGroupArray trackGroups;
    private final ArrayList<MediaPeriod> childrenPendingPreparation = new ArrayList<>();
    private final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup = new HashMap<>();
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices = new IdentityHashMap<>();
    private MediaPeriod[] enabledPeriods = new MediaPeriod[0];

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, long[] periodTimeOffsetsUs, MediaPeriod... periods) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.periods = periods;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.empty();
        for (int i = 0; i < periods.length; i++) {
            if (periodTimeOffsetsUs[i] != 0) {
                this.periods[i] = new TimeOffsetMediaPeriod(periods[i], periodTimeOffsetsUs[i]);
            }
        }
    }

    public MediaPeriod getChildPeriod(int index) {
        if (this.periods[index] instanceof TimeOffsetMediaPeriod) {
            return ((TimeOffsetMediaPeriod) this.periods[index]).getWrappedMediaPeriod();
        }
        return this.periods[index];
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long positionUs) {
        this.callback = callback;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        for (MediaPeriod period : this.periods) {
            period.prepare(this, positionUs);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (MediaPeriod period : this.periods) {
            period.maybeThrowPrepareError();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v6 */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SampleStream sampleStream;
        int[] iArr = new int[exoTrackSelectionArr.length];
        int[] iArr2 = new int[exoTrackSelectionArr.length];
        int i = 0;
        while (true) {
            sampleStream = null;
            if (i >= exoTrackSelectionArr.length) {
                break;
            }
            Integer num = sampleStreamArr[i] != null ? this.streamPeriodIndices.get(sampleStreamArr[i]) : null;
            iArr[i] = num == null ? -1 : num.intValue();
            if (exoTrackSelectionArr[i] != null) {
                TrackGroup trackGroup = exoTrackSelectionArr[i].getTrackGroup();
                iArr2[i] = Integer.parseInt(trackGroup.id.substring(0, trackGroup.id.indexOf(":")));
            } else {
                iArr2[i] = -1;
            }
            i++;
        }
        this.streamPeriodIndices.clear();
        SampleStream[] sampleStreamArr2 = new SampleStream[exoTrackSelectionArr.length];
        SampleStream[] sampleStreamArr3 = new SampleStream[exoTrackSelectionArr.length];
        ExoTrackSelection[] exoTrackSelectionArr2 = new ExoTrackSelection[exoTrackSelectionArr.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j2 = j;
        int i2 = 0;
        ExoTrackSelection[] exoTrackSelectionArr3 = exoTrackSelectionArr2;
        while (i2 < this.periods.length) {
            for (int i3 = 0; i3 < exoTrackSelectionArr.length; i3++) {
                sampleStreamArr3[i3] = iArr[i3] == i2 ? sampleStreamArr[i3] : sampleStream;
                if (iArr2[i3] == i2) {
                    ExoTrackSelection exoTrackSelection = (ExoTrackSelection) Assertions.checkNotNull(exoTrackSelectionArr[i3]);
                    exoTrackSelectionArr3[i3] = new ForwardingTrackSelection(exoTrackSelection, (TrackGroup) Assertions.checkNotNull(this.childTrackGroupByMergedTrackGroup.get(exoTrackSelection.getTrackGroup())));
                } else {
                    exoTrackSelectionArr3[i3] = sampleStream;
                }
            }
            int i4 = i2;
            ExoTrackSelection[] exoTrackSelectionArr4 = exoTrackSelectionArr3;
            ArrayList arrayList2 = arrayList;
            long selectTracks = this.periods[i2].selectTracks(exoTrackSelectionArr3, zArr, sampleStreamArr3, zArr2, j2);
            if (i4 == 0) {
                j2 = selectTracks;
            } else if (selectTracks != j2) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z = false;
            for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
                if (iArr2[i5] == i4) {
                    SampleStream sampleStream2 = (SampleStream) Assertions.checkNotNull(sampleStreamArr3[i5]);
                    sampleStreamArr2[i5] = sampleStreamArr3[i5];
                    z = true;
                    this.streamPeriodIndices.put(sampleStream2, Integer.valueOf(i4));
                } else if (iArr[i5] == i4) {
                    Assertions.checkState(sampleStreamArr3[i5] == null);
                }
            }
            if (z) {
                arrayList2.add(this.periods[i4]);
            }
            i2 = i4 + 1;
            arrayList = arrayList2;
            exoTrackSelectionArr3 = exoTrackSelectionArr4;
            sampleStream = null;
        }
        ArrayList arrayList3 = arrayList;
        System.arraycopy(sampleStreamArr2, 0, sampleStreamArr, 0, sampleStreamArr2.length);
        this.enabledPeriods = (MediaPeriod[]) arrayList3.toArray(new MediaPeriod[0]);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(arrayList3, Lists.transform(arrayList3, new Function() { // from class: androidx.media3.exoplayer.source.MergingMediaPeriod$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                List trackTypes;
                trackTypes = ((MediaPeriod) obj).getTrackGroups().getTrackTypes();
                return trackTypes;
            }
        }));
        return j2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long positionUs, boolean toKeyframe) {
        for (MediaPeriod period : this.enabledPeriods) {
            period.discardBuffer(positionUs, toKeyframe);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long positionUs) {
        this.compositeSequenceableLoader.reevaluateBuffer(positionUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (!this.childrenPendingPreparation.isEmpty()) {
            int childrenPendingPreparationSize = this.childrenPendingPreparation.size();
            for (int i = 0; i < childrenPendingPreparationSize; i++) {
                this.childrenPendingPreparation.get(i).continueLoading(loadingInfo);
            }
            return false;
        }
        return this.compositeSequenceableLoader.continueLoading(loadingInfo);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0061, code lost:
    
        continue;
     */
    @Override // androidx.media3.exoplayer.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readDiscontinuity() {
        /*
            r17 = this;
            r0 = r17
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            androidx.media3.exoplayer.source.MediaPeriod[] r3 = r0.enabledPeriods
            int r4 = r3.length
            r5 = 0
            r6 = r5
        Lc:
            if (r6 >= r4) goto L64
            r7 = r3[r6]
            long r8 = r7.readDiscontinuity()
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            java.lang.String r13 = "Unexpected child seekToUs result."
            if (r12 == 0) goto L4e
            int r10 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r10 != 0) goto L41
            r1 = r8
            androidx.media3.exoplayer.source.MediaPeriod[] r10 = r0.enabledPeriods
            int r11 = r10.length
            r12 = r5
        L28:
            if (r12 >= r11) goto L40
            r14 = r10[r12]
            if (r14 != r7) goto L2f
            goto L40
        L2f:
            long r15 = r14.seekToUs(r1)
            int r15 = (r15 > r1 ? 1 : (r15 == r1 ? 0 : -1))
            if (r15 != 0) goto L3a
            int r12 = r12 + 1
            goto L28
        L3a:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>(r13)
            throw r3
        L40:
            goto L61
        L41:
            int r10 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r10 != 0) goto L46
            goto L61
        L46:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "Conflicting discontinuities."
            r3.<init>(r4)
            throw r3
        L4e:
            int r10 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r10 == 0) goto L61
            long r10 = r7.seekToUs(r1)
            int r10 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r10 != 0) goto L5b
            goto L61
        L5b:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>(r13)
            throw r3
        L61:
            int r6 = r6 + 1
            goto Lc
        L64:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.MergingMediaPeriod.readDiscontinuity():long");
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long positionUs) {
        long positionUs2 = this.enabledPeriods[0].seekToUs(positionUs);
        for (int i = 1; i < this.enabledPeriods.length; i++) {
            if (this.enabledPeriods[i].seekToUs(positionUs2) != positionUs2) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
        return positionUs2;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long positionUs, SeekParameters seekParameters) {
        MediaPeriod queryPeriod = this.enabledPeriods.length > 0 ? this.enabledPeriods[0] : this.periods[0];
        return queryPeriod.getAdjustedSeekPositionUs(positionUs, seekParameters);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod preparedPeriod) {
        this.childrenPendingPreparation.remove(preparedPeriod);
        if (!this.childrenPendingPreparation.isEmpty()) {
            return;
        }
        int totalTrackGroupCount = 0;
        for (MediaPeriod period : this.periods) {
            totalTrackGroupCount += period.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArray = new TrackGroup[totalTrackGroupCount];
        int trackGroupIndex = 0;
        for (int i = 0; i < this.periods.length; i++) {
            TrackGroupArray periodTrackGroups = this.periods[i].getTrackGroups();
            int periodTrackGroupCount = periodTrackGroups.length;
            int j = 0;
            while (j < periodTrackGroupCount) {
                TrackGroup childTrackGroup = periodTrackGroups.get(j);
                Format[] mergedFormats = new Format[childTrackGroup.length];
                for (int k = 0; k < childTrackGroup.length; k++) {
                    Format originalFormat = childTrackGroup.getFormat(k);
                    mergedFormats[k] = originalFormat.buildUpon().setId(i + ":" + (originalFormat.id == null ? "" : originalFormat.id)).build();
                }
                TrackGroup mergedTrackGroup = new TrackGroup(i + ":" + childTrackGroup.id, mergedFormats);
                this.childTrackGroupByMergedTrackGroup.put(mergedTrackGroup, childTrackGroup);
                trackGroupArray[trackGroupIndex] = mergedTrackGroup;
                j++;
                trackGroupIndex++;
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArray);
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod ignored) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    /* loaded from: classes7.dex */
    private static final class ForwardingTrackSelection implements ExoTrackSelection {
        private final TrackGroup trackGroup;
        private final ExoTrackSelection trackSelection;

        public ForwardingTrackSelection(ExoTrackSelection trackSelection, TrackGroup trackGroup) {
            this.trackSelection = trackSelection;
            this.trackGroup = trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int getType() {
            return this.trackSelection.getType();
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int length() {
            return this.trackSelection.length();
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public Format getFormat(int index) {
            return this.trackGroup.getFormat(this.trackSelection.getIndexInTrackGroup(index));
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int getIndexInTrackGroup(int index) {
            return this.trackSelection.getIndexInTrackGroup(index);
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(Format format) {
            return this.trackSelection.indexOf(this.trackGroup.indexOf(format));
        }

        @Override // androidx.media3.exoplayer.trackselection.TrackSelection
        public int indexOf(int indexInTrackGroup) {
            return this.trackSelection.indexOf(indexInTrackGroup);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void enable() {
            this.trackSelection.enable();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void disable() {
            this.trackSelection.disable();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Format getSelectedFormat() {
            return this.trackGroup.getFormat(this.trackSelection.getSelectedIndexInTrackGroup());
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndexInTrackGroup() {
            return this.trackSelection.getSelectedIndexInTrackGroup();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return this.trackSelection.getSelectedIndex();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return this.trackSelection.getSelectionReason();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return this.trackSelection.getSelectionData();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onPlaybackSpeed(float playbackSpeed) {
            this.trackSelection.onPlaybackSpeed(playbackSpeed);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onDiscontinuity() {
            this.trackSelection.onDiscontinuity();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onRebuffer() {
            this.trackSelection.onRebuffer();
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void onPlayWhenReadyChanged(boolean playWhenReady) {
            this.trackSelection.onPlayWhenReadyChanged(playWhenReady);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long playbackPositionUs, long bufferedDurationUs, long availableDurationUs, List<? extends MediaChunk> queue, MediaChunkIterator[] mediaChunkIterators) {
            this.trackSelection.updateSelectedTrack(playbackPositionUs, bufferedDurationUs, availableDurationUs, queue, mediaChunkIterators);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int evaluateQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
            return this.trackSelection.evaluateQueueSize(playbackPositionUs, queue);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean shouldCancelChunkLoad(long playbackPositionUs, Chunk loadingChunk, List<? extends MediaChunk> queue) {
            return this.trackSelection.shouldCancelChunkLoad(playbackPositionUs, loadingChunk, queue);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean excludeTrack(int index, long exclusionDurationMs) {
            return this.trackSelection.excludeTrack(index, exclusionDurationMs);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public boolean isTrackExcluded(int index, long nowMs) {
            return this.trackSelection.isTrackExcluded(index, nowMs);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public long getLatestBitrateEstimate() {
            return this.trackSelection.getLatestBitrateEstimate();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ForwardingTrackSelection)) {
                return false;
            }
            ForwardingTrackSelection that = (ForwardingTrackSelection) o;
            return this.trackSelection.equals(that.trackSelection) && this.trackGroup.equals(that.trackGroup);
        }

        public int hashCode() {
            int result = (17 * 31) + this.trackGroup.hashCode();
            return (result * 31) + this.trackSelection.hashCode();
        }
    }
}
