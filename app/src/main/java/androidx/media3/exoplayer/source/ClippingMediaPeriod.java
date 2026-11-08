package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

/* loaded from: classes7.dex */
public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private ClippingMediaSource.IllegalClippingException clippingError;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    long startUs;

    public ClippingMediaPeriod(MediaPeriod mediaPeriod, boolean enableInitialDiscontinuity, long startUs, long endUs) {
        this.mediaPeriod = mediaPeriod;
        this.pendingInitialDiscontinuityPositionUs = enableInitialDiscontinuity ? startUs : C.TIME_UNSET;
        this.startUs = startUs;
        this.endUs = endUs;
    }

    public void updateClipping(long startUs, long endUs) {
        this.startUs = startUs;
        this.endUs = endUs;
    }

    public void setClippingError(ClippingMediaSource.IllegalClippingException clippingError) {
        this.clippingError = clippingError;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long positionUs) {
        this.callback = callback;
        this.mediaPeriod.prepare(this, positionUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        if (this.clippingError != null) {
            throw this.clippingError;
        }
        this.mediaPeriod.maybeThrowPrepareError();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> trackSelections) {
        return this.mediaPeriod.getStreamKeys(trackSelections);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] selections, boolean[] mayRetainStreamFlags, SampleStream[] streams, boolean[] streamResetFlags, long positionUs) {
        long j;
        this.sampleStreams = new ClippingSampleStream[streams.length];
        SampleStream[] childStreams = new SampleStream[streams.length];
        int i = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i >= streams.length) {
                break;
            }
            this.sampleStreams[i] = (ClippingSampleStream) streams[i];
            if (this.sampleStreams[i] != null) {
                sampleStream = this.sampleStreams[i].childStream;
            }
            childStreams[i] = sampleStream;
            i++;
        }
        long enablePositionUs = this.mediaPeriod.selectTracks(selections, mayRetainStreamFlags, childStreams, streamResetFlags, positionUs);
        if (isPendingInitialDiscontinuity() && positionUs == this.startUs && shouldKeepInitialDiscontinuity(this.startUs, selections)) {
            j = enablePositionUs;
        } else {
            j = C.TIME_UNSET;
        }
        this.pendingInitialDiscontinuityPositionUs = j;
        Assertions.checkState(enablePositionUs == positionUs || (enablePositionUs >= this.startUs && (this.endUs == Long.MIN_VALUE || enablePositionUs <= this.endUs)));
        for (int i2 = 0; i2 < streams.length; i2++) {
            if (childStreams[i2] == null) {
                this.sampleStreams[i2] = null;
            } else if (this.sampleStreams[i2] == null || this.sampleStreams[i2].childStream != childStreams[i2]) {
                this.sampleStreams[i2] = new ClippingSampleStream(childStreams[i2]);
            }
            streams[i2] = this.sampleStreams[i2];
        }
        return enablePositionUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long positionUs, boolean toKeyframe) {
        this.mediaPeriod.discardBuffer(positionUs, toKeyframe);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long positionUs) {
        this.mediaPeriod.reevaluateBuffer(positionUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long initialDiscontinuityUs = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = C.TIME_UNSET;
            long childDiscontinuityUs = readDiscontinuity();
            return childDiscontinuityUs != C.TIME_UNSET ? childDiscontinuityUs : initialDiscontinuityUs;
        }
        long discontinuityUs = this.mediaPeriod.readDiscontinuity();
        if (discontinuityUs == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        boolean z = true;
        Assertions.checkState(discontinuityUs >= this.startUs);
        if (this.endUs != Long.MIN_VALUE && discontinuityUs > this.endUs) {
            z = false;
        }
        Assertions.checkState(z);
        return discontinuityUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE || (this.endUs != Long.MIN_VALUE && bufferedPositionUs >= this.endUs)) {
            return Long.MIN_VALUE;
        }
        return bufferedPositionUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long positionUs) {
        this.pendingInitialDiscontinuityPositionUs = C.TIME_UNSET;
        boolean z = false;
        for (ClippingSampleStream sampleStream : this.sampleStreams) {
            if (sampleStream != null) {
                sampleStream.clearSentEos();
            }
        }
        long seekUs = this.mediaPeriod.seekToUs(positionUs);
        if (seekUs == positionUs || (seekUs >= this.startUs && (this.endUs == Long.MIN_VALUE || seekUs <= this.endUs))) {
            z = true;
        }
        Assertions.checkState(z);
        return seekUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long positionUs, SeekParameters seekParameters) {
        if (positionUs == this.startUs) {
            return this.startUs;
        }
        SeekParameters clippedSeekParameters = clipSeekParameters(positionUs, seekParameters);
        return this.mediaPeriod.getAdjustedSeekPositionUs(positionUs, clippedSeekParameters);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE || (this.endUs != Long.MIN_VALUE && nextLoadPositionUs >= this.endUs)) {
            return Long.MIN_VALUE;
        }
        return nextLoadPositionUs;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        if (this.clippingError != null) {
            return;
        }
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod source) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    boolean isPendingInitialDiscontinuity() {
        return this.pendingInitialDiscontinuityPositionUs != C.TIME_UNSET;
    }

    private SeekParameters clipSeekParameters(long positionUs, SeekParameters seekParameters) {
        long toleranceBeforeUs = Util.constrainValue(seekParameters.toleranceBeforeUs, 0L, positionUs - this.startUs);
        long toleranceAfterUs = Util.constrainValue(seekParameters.toleranceAfterUs, 0L, this.endUs == Long.MIN_VALUE ? Long.MAX_VALUE : this.endUs - positionUs);
        if (toleranceBeforeUs == seekParameters.toleranceBeforeUs && toleranceAfterUs == seekParameters.toleranceAfterUs) {
            return seekParameters;
        }
        return new SeekParameters(toleranceBeforeUs, toleranceAfterUs);
    }

    private static boolean shouldKeepInitialDiscontinuity(long startUs, ExoTrackSelection[] selections) {
        if (startUs != 0) {
            for (ExoTrackSelection trackSelection : selections) {
                if (trackSelection != null) {
                    Format selectedFormat = trackSelection.getSelectedFormat();
                    if (!MimeTypes.allSamplesAreSyncSamples(selectedFormat.sampleMimeType, selectedFormat.codecs)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* loaded from: classes7.dex */
    private final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(SampleStream childStream) {
            this.childStream = childStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return !ClippingMediaPeriod.this.isPendingInitialDiscontinuity() && this.childStream.isReady();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.childStream.maybeThrowError();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer buffer, int readFlags) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                buffer.setFlags(4);
                return -4;
            }
            long bufferedPositionUs = ClippingMediaPeriod.this.getBufferedPositionUs();
            int result = this.childStream.readData(formatHolder, buffer, readFlags);
            if (result == -5) {
                Format format = (Format) Assertions.checkNotNull(formatHolder.format);
                if (format.encoderDelay != 0 || format.encoderPadding != 0) {
                    int encoderDelay = ClippingMediaPeriod.this.startUs != 0 ? 0 : format.encoderDelay;
                    int encoderPadding = ClippingMediaPeriod.this.endUs == Long.MIN_VALUE ? format.encoderPadding : 0;
                    formatHolder.format = format.buildUpon().setEncoderDelay(encoderDelay).setEncoderPadding(encoderPadding).build();
                }
                return -5;
            }
            if (ClippingMediaPeriod.this.endUs != Long.MIN_VALUE && ((result == -4 && buffer.timeUs >= ClippingMediaPeriod.this.endUs) || (result == -3 && bufferedPositionUs == Long.MIN_VALUE && !buffer.waitingForKeys))) {
                buffer.clear();
                buffer.setFlags(4);
                this.sentEos = true;
                return -4;
            }
            return result;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long positionUs) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            return this.childStream.skipData(positionUs);
        }
    }
}
