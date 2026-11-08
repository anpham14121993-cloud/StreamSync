package androidx.media3.exoplayer.dash;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.BundledChunkExtractor;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.exoplayer.source.chunk.ContainerMediaChunk;
import androidx.media3.exoplayer.source.chunk.InitializationChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.SingleSampleMediaChunk;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes5.dex */
public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private final CmcdConfiguration cmcdConfiguration;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long lastChunkRequestRealtimeMs;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private ExoTrackSelection trackSelection;
    private final int trackType;

    /* loaded from: classes5.dex */
    public static final class Factory implements DashChunkSource.Factory {
        private final ChunkExtractor.Factory chunkExtractorFactory;
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory dataSourceFactory) {
            this(dataSourceFactory, 1);
        }

        public Factory(DataSource.Factory dataSourceFactory, int maxSegmentsPerLoad) {
            this(BundledChunkExtractor.FACTORY, dataSourceFactory, maxSegmentsPerLoad);
        }

        public Factory(ChunkExtractor.Factory chunkExtractorFactory, DataSource.Factory dataSourceFactory, int maxSegmentsPerLoad) {
            this.chunkExtractorFactory = chunkExtractorFactory;
            this.dataSourceFactory = dataSourceFactory;
            this.maxSegmentsPerLoad = maxSegmentsPerLoad;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory subtitleParserFactory) {
            this.chunkExtractorFactory.setSubtitleParserFactory(subtitleParserFactory);
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory experimentalParseSubtitlesDuringExtraction(boolean parseSubtitlesDuringExtraction) {
            this.chunkExtractorFactory.experimentalParseSubtitlesDuringExtraction(parseSubtitlesDuringExtraction);
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower manifestLoaderErrorThrower, DashManifest manifest, BaseUrlExclusionList baseUrlExclusionList, int periodIndex, int[] adaptationSetIndices, ExoTrackSelection trackSelection, int trackType, long elapsedRealtimeOffsetMs, boolean enableEventMessageTrack, List<Format> closedCaptionFormats, PlayerEmsgHandler.PlayerTrackEmsgHandler playerEmsgHandler, TransferListener transferListener, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
            DataSource dataSource = this.dataSourceFactory.createDataSource();
            if (transferListener != null) {
                dataSource.addTransferListener(transferListener);
            }
            return new DefaultDashChunkSource(this.chunkExtractorFactory, manifestLoaderErrorThrower, manifest, baseUrlExclusionList, periodIndex, adaptationSetIndices, trackSelection, trackType, dataSource, elapsedRealtimeOffsetMs, this.maxSegmentsPerLoad, enableEventMessageTrack, closedCaptionFormats, playerEmsgHandler, playerId, cmcdConfiguration);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Format getOutputTextFormat(Format sourceFormat) {
            return this.chunkExtractorFactory.getOutputTextFormat(sourceFormat);
        }
    }

    public DefaultDashChunkSource(ChunkExtractor.Factory chunkExtractorFactory, LoaderErrorThrower manifestLoaderErrorThrower, DashManifest manifest, BaseUrlExclusionList baseUrlExclusionList, int periodIndex, int[] adaptationSetIndices, ExoTrackSelection trackSelection, int trackType, DataSource dataSource, long elapsedRealtimeOffsetMs, int maxSegmentsPerLoad, boolean enableEventMessageTrack, List<Format> closedCaptionFormats, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
        DefaultDashChunkSource defaultDashChunkSource = this;
        BaseUrlExclusionList baseUrlExclusionList2 = baseUrlExclusionList;
        defaultDashChunkSource.manifestLoaderErrorThrower = manifestLoaderErrorThrower;
        defaultDashChunkSource.manifest = manifest;
        defaultDashChunkSource.baseUrlExclusionList = baseUrlExclusionList2;
        defaultDashChunkSource.adaptationSetIndices = adaptationSetIndices;
        defaultDashChunkSource.trackSelection = trackSelection;
        defaultDashChunkSource.trackType = trackType;
        defaultDashChunkSource.dataSource = dataSource;
        defaultDashChunkSource.periodIndex = periodIndex;
        defaultDashChunkSource.elapsedRealtimeOffsetMs = elapsedRealtimeOffsetMs;
        defaultDashChunkSource.maxSegmentsPerLoad = maxSegmentsPerLoad;
        defaultDashChunkSource.playerTrackEmsgHandler = playerTrackEmsgHandler;
        defaultDashChunkSource.cmcdConfiguration = cmcdConfiguration;
        defaultDashChunkSource.lastChunkRequestRealtimeMs = C.TIME_UNSET;
        long periodDurationUs = manifest.getPeriodDurationUs(periodIndex);
        List<Representation> representations = getRepresentations();
        defaultDashChunkSource.representationHolders = new RepresentationHolder[trackSelection.length()];
        int i = 0;
        while (i < defaultDashChunkSource.representationHolders.length) {
            Representation representation = representations.get(trackSelection.getIndexInTrackGroup(i));
            BaseUrl selectedBaseUrl = baseUrlExclusionList2.selectBaseUrl(representation.baseUrls);
            RepresentationHolder[] representationHolderArr = defaultDashChunkSource.representationHolders;
            BaseUrl baseUrl = selectedBaseUrl != null ? selectedBaseUrl : representation.baseUrls.get(0);
            int i2 = i;
            representationHolderArr[i2] = new RepresentationHolder(periodDurationUs, representation, baseUrl, chunkExtractorFactory.createProgressiveMediaExtractor(trackType, representation.format, enableEventMessageTrack, closedCaptionFormats, playerTrackEmsgHandler, playerId), 0L, representation.getIndex());
            i = i2 + 1;
            defaultDashChunkSource = this;
            baseUrlExclusionList2 = baseUrlExclusionList;
            representations = representations;
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public long getAdjustedSeekPositionUs(long positionUs, SeekParameters seekParameters) {
        long secondSyncUs;
        for (RepresentationHolder representationHolder : this.representationHolders) {
            if (representationHolder.segmentIndex != null) {
                long segmentCount = representationHolder.getSegmentCount();
                if (segmentCount != 0) {
                    long segmentNum = representationHolder.getSegmentNum(positionUs);
                    long firstSyncUs = representationHolder.getSegmentStartTimeUs(segmentNum);
                    if (firstSyncUs < positionUs && (segmentCount == -1 || segmentNum < (representationHolder.getFirstSegmentNum() + segmentCount) - 1)) {
                        secondSyncUs = representationHolder.getSegmentStartTimeUs(1 + segmentNum);
                    } else {
                        secondSyncUs = firstSyncUs;
                    }
                    return seekParameters.resolveSeekPositionUs(positionUs, firstSyncUs, secondSyncUs);
                }
            }
        }
        return positionUs;
    }

    @Override // androidx.media3.exoplayer.dash.DashChunkSource
    public void updateManifest(DashManifest newManifest, int newPeriodIndex) {
        try {
            this.manifest = newManifest;
            this.periodIndex = newPeriodIndex;
            long periodDurationUs = this.manifest.getPeriodDurationUs(this.periodIndex);
            List<Representation> representations = getRepresentations();
            for (int i = 0; i < this.representationHolders.length; i++) {
                Representation representation = representations.get(this.trackSelection.getIndexInTrackGroup(i));
                this.representationHolders[i] = this.representationHolders[i].copyWithNewRepresentation(periodDurationUs, representation);
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    @Override // androidx.media3.exoplayer.dash.DashChunkSource
    public void updateTrackSelection(ExoTrackSelection trackSelection) {
        this.trackSelection = trackSelection;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        if (this.fatalError != null) {
            throw this.fatalError;
        }
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public int getPreferredQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return queue.size();
        }
        return this.trackSelection.evaluateQueueSize(playbackPositionUs, queue);
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public boolean shouldCancelLoad(long playbackPositionUs, Chunk loadingChunk, List<? extends MediaChunk> queue) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(playbackPositionUs, loadingChunk, queue);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01d1  */
    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getNextChunk(androidx.media3.exoplayer.LoadingInfo r47, long r48, java.util.List<? extends androidx.media3.exoplayer.source.chunk.MediaChunk> r50, androidx.media3.exoplayer.source.chunk.ChunkHolder r51) {
        /*
            Method dump skipped, instructions count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.DefaultDashChunkSource.getNextChunk(androidx.media3.exoplayer.LoadingInfo, long, java.util.List, androidx.media3.exoplayer.source.chunk.ChunkHolder):void");
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        ChunkIndex chunkIndex;
        if (chunk instanceof InitializationChunk) {
            InitializationChunk initializationChunk = (InitializationChunk) chunk;
            int trackIndex = this.trackSelection.indexOf(initializationChunk.trackFormat);
            RepresentationHolder representationHolder = this.representationHolders[trackIndex];
            if (representationHolder.segmentIndex == null && (chunkIndex = ((ChunkExtractor) Assertions.checkStateNotNull(representationHolder.chunkExtractor)).getChunkIndex()) != null) {
                this.representationHolders[trackIndex] = representationHolder.copyWithNewSegmentIndex(new DashWrappingSegmentIndex(chunkIndex, representationHolder.representation.presentationTimeOffsetUs));
            }
        }
        if (this.playerTrackEmsgHandler != null) {
            this.playerTrackEmsgHandler.onChunkLoadCompleted(chunk);
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk chunk, boolean cancelable, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        LoadErrorHandlingPolicy.FallbackSelection fallbackSelection;
        if (!cancelable) {
            return false;
        }
        if (this.playerTrackEmsgHandler != null && this.playerTrackEmsgHandler.onChunkLoadError(chunk)) {
            return true;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk) && (loadErrorInfo.exception instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) loadErrorInfo.exception).responseCode == 404) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)];
            long segmentCount = representationHolder.getSegmentCount();
            if (segmentCount != -1 && segmentCount != 0) {
                long lastAvailableSegmentNum = (representationHolder.getFirstSegmentNum() + segmentCount) - 1;
                if (((MediaChunk) chunk).getNextChunkIndex() > lastAvailableSegmentNum) {
                    this.missingLastSegment = true;
                    return true;
                }
            }
        }
        int trackIndex = this.trackSelection.indexOf(chunk.trackFormat);
        RepresentationHolder representationHolder2 = this.representationHolders[trackIndex];
        BaseUrl newBaseUrl = this.baseUrlExclusionList.selectBaseUrl(representationHolder2.representation.baseUrls);
        if (newBaseUrl != null && !representationHolder2.selectedBaseUrl.equals(newBaseUrl)) {
            return true;
        }
        LoadErrorHandlingPolicy.FallbackOptions fallbackOptions = createFallbackOptions(this.trackSelection, representationHolder2.representation.baseUrls);
        if ((!fallbackOptions.isFallbackAvailable(2) && !fallbackOptions.isFallbackAvailable(1)) || (fallbackSelection = loadErrorHandlingPolicy.getFallbackSelectionFor(fallbackOptions, loadErrorInfo)) == null || !fallbackOptions.isFallbackAvailable(fallbackSelection.type)) {
            return false;
        }
        if (fallbackSelection.type == 2) {
            boolean cancelLoad = this.trackSelection.excludeTrack(this.trackSelection.indexOf(chunk.trackFormat), fallbackSelection.exclusionDurationMs);
            return cancelLoad;
        }
        if (fallbackSelection.type != 1) {
            return false;
        }
        this.baseUrlExclusionList.exclude(representationHolder2.selectedBaseUrl, fallbackSelection.exclusionDurationMs);
        return true;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void release() {
        for (RepresentationHolder representationHolder : this.representationHolders) {
            ChunkExtractor chunkExtractor = representationHolder.chunkExtractor;
            if (chunkExtractor != null) {
                chunkExtractor.release();
            }
        }
    }

    private LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection trackSelection, List<BaseUrl> baseUrls) {
        long nowMs = SystemClock.elapsedRealtime();
        int numberOfTracks = trackSelection.length();
        int numberOfExcludedTracks = 0;
        for (int i = 0; i < numberOfTracks; i++) {
            if (trackSelection.isTrackExcluded(i, nowMs)) {
                numberOfExcludedTracks++;
            }
        }
        int priorityCount = BaseUrlExclusionList.getPriorityCount(baseUrls);
        return new LoadErrorHandlingPolicy.FallbackOptions(priorityCount, priorityCount - this.baseUrlExclusionList.getPriorityCountAfterExclusion(baseUrls), numberOfTracks, numberOfExcludedTracks);
    }

    private long getSegmentNum(RepresentationHolder representationHolder, MediaChunk previousChunk, long loadPositionUs, long firstAvailableSegmentNum, long lastAvailableSegmentNum) {
        if (previousChunk != null) {
            return previousChunk.getNextChunkIndex();
        }
        return Util.constrainValue(representationHolder.getSegmentNum(loadPositionUs), firstAvailableSegmentNum, lastAvailableSegmentNum);
    }

    @RequiresNonNull({"manifest", "adaptationSetIndices"})
    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> manifestAdaptationSets = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> representations = new ArrayList<>();
        for (int adaptationSetIndex : this.adaptationSetIndices) {
            representations.addAll(manifestAdaptationSets.get(adaptationSetIndex).representations);
        }
        return representations;
    }

    private long getAvailableLiveDurationUs(long nowUnixTimeUs, long playbackPositionUs) {
        if (this.manifest.dynamic && this.representationHolders[0].getSegmentCount() != 0) {
            long lastSegmentNum = this.representationHolders[0].getLastAvailableSegmentNum(nowUnixTimeUs);
            long lastSegmentEndTimeUs = this.representationHolders[0].getSegmentEndTimeUs(lastSegmentNum);
            long nowPeriodTimeUs = getNowPeriodTimeUs(nowUnixTimeUs);
            long availabilityEndTimeUs = Math.min(nowPeriodTimeUs, lastSegmentEndTimeUs);
            return Math.max(0L, availabilityEndTimeUs - playbackPositionUs);
        }
        return C.TIME_UNSET;
    }

    private long getNowPeriodTimeUs(long nowUnixTimeUs) {
        return this.manifest.availabilityStartTimeMs == C.TIME_UNSET ? C.TIME_UNSET : nowUnixTimeUs - Util.msToUs(this.manifest.availabilityStartTimeMs + this.manifest.getPeriod(this.periodIndex).startMs);
    }

    @RequiresNonNull({"#1.chunkExtractor"})
    protected Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource, Format trackFormat, int trackSelectionReason, Object trackSelectionData, RangedUri initializationUri, RangedUri indexUri, CmcdData.Factory cmcdDataFactory) {
        RangedUri requestUri;
        Representation representation = representationHolder.representation;
        if (initializationUri != null) {
            requestUri = initializationUri.attemptMerge(indexUri, representationHolder.selectedBaseUrl.url);
            if (requestUri == null) {
                requestUri = initializationUri;
            }
        } else {
            requestUri = (RangedUri) Assertions.checkNotNull(indexUri);
        }
        DataSpec dataSpec = DashUtil.buildDataSpec(representation, representationHolder.selectedBaseUrl.url, requestUri, 0, ImmutableMap.of());
        if (cmcdDataFactory != null) {
            CmcdData cmcdData = cmcdDataFactory.setObjectType(CmcdData.Factory.OBJECT_TYPE_INIT_SEGMENT).createCmcdData();
            dataSpec = cmcdData.addToDataSpec(dataSpec);
        }
        return new InitializationChunk(dataSource, dataSpec, trackFormat, trackSelectionReason, trackSelectionData, representationHolder.chunkExtractor);
    }

    protected Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long firstSegmentNum, int maxSegmentCount, long seekTimeUs, long nowPeriodTimeUs, CmcdData.Factory cmcdDataFactory) {
        long j;
        RepresentationHolder representationHolder2;
        long clippedEndTimeUs;
        long segmentNum;
        DataSpec dataSpec;
        long sampleOffsetUs;
        DataSpec dataSpec2;
        Representation representation = representationHolder.representation;
        long startTimeUs = representationHolder.getSegmentStartTimeUs(firstSegmentNum);
        RangedUri segmentUri = representationHolder.getSegmentUrl(firstSegmentNum);
        if (representationHolder.chunkExtractor == null) {
            long endTimeUs = representationHolder.getSegmentEndTimeUs(firstSegmentNum);
            int flags = representationHolder.isSegmentAvailableAtFullNetworkSpeed(firstSegmentNum, nowPeriodTimeUs) ? 0 : 8;
            DataSpec dataSpec3 = DashUtil.buildDataSpec(representation, representationHolder.selectedBaseUrl.url, segmentUri, flags, ImmutableMap.of());
            if (cmcdDataFactory == null) {
                dataSpec2 = dataSpec3;
            } else {
                cmcdDataFactory.setChunkDurationUs(endTimeUs - startTimeUs).setObjectType(CmcdData.Factory.getObjectType(this.trackSelection));
                Pair<String, String> nextObjectAndRangeRequest = getNextObjectAndRangeRequest(firstSegmentNum, segmentUri, representationHolder);
                if (nextObjectAndRangeRequest != null) {
                    cmcdDataFactory.setNextObjectRequest((String) nextObjectAndRangeRequest.first).setNextRangeRequest((String) nextObjectAndRangeRequest.second);
                }
                CmcdData cmcdData = cmcdDataFactory.createCmcdData();
                dataSpec2 = cmcdData.addToDataSpec(dataSpec3);
            }
            return new SingleSampleMediaChunk(dataSource, dataSpec2, trackFormat, trackSelectionReason, trackSelectionData, startTimeUs, endTimeUs, firstSegmentNum, trackType, trackFormat);
        }
        long j2 = firstSegmentNum;
        int i = 1;
        int segmentCount = 1;
        RangedUri segmentUri2 = segmentUri;
        while (true) {
            if (i >= maxSegmentCount) {
                j = j2;
                representationHolder2 = representationHolder;
                break;
            }
            long j3 = i + j2;
            j = j2;
            representationHolder2 = representationHolder;
            RangedUri nextSegmentUri = representationHolder2.getSegmentUrl(j3);
            RangedUri mergedSegmentUri = segmentUri2.attemptMerge(nextSegmentUri, representationHolder2.selectedBaseUrl.url);
            if (mergedSegmentUri == null) {
                break;
            }
            segmentUri2 = mergedSegmentUri;
            segmentCount++;
            i++;
            j2 = j;
        }
        long segmentNum2 = (segmentCount + j) - 1;
        long endTimeUs2 = representationHolder2.getSegmentEndTimeUs(segmentNum2);
        long periodDurationUs = representationHolder.periodDurationUs;
        if (periodDurationUs != C.TIME_UNSET && periodDurationUs <= endTimeUs2) {
            clippedEndTimeUs = periodDurationUs;
        } else {
            clippedEndTimeUs = -9223372036854775807L;
        }
        int flags2 = representationHolder2.isSegmentAvailableAtFullNetworkSpeed(segmentNum2, nowPeriodTimeUs) ? 0 : 8;
        DataSpec dataSpec4 = DashUtil.buildDataSpec(representation, representationHolder2.selectedBaseUrl.url, segmentUri2, flags2, ImmutableMap.of());
        if (cmcdDataFactory != null) {
            segmentNum = segmentNum2;
            cmcdDataFactory.setChunkDurationUs(endTimeUs2 - startTimeUs).setObjectType(CmcdData.Factory.getObjectType(this.trackSelection));
            Pair<String, String> nextObjectAndRangeRequest2 = getNextObjectAndRangeRequest(j, segmentUri2, representationHolder2);
            if (nextObjectAndRangeRequest2 != null) {
                cmcdDataFactory.setNextObjectRequest((String) nextObjectAndRangeRequest2.first).setNextRangeRequest((String) nextObjectAndRangeRequest2.second);
            }
            CmcdData cmcdData2 = cmcdDataFactory.createCmcdData();
            dataSpec = cmcdData2.addToDataSpec(dataSpec4);
        } else {
            segmentNum = segmentNum2;
            dataSpec = dataSpec4;
        }
        long sampleOffsetUs2 = -representation.presentationTimeOffsetUs;
        if (!MimeTypes.isImage(trackFormat.sampleMimeType)) {
            sampleOffsetUs = sampleOffsetUs2;
        } else {
            sampleOffsetUs = sampleOffsetUs2 + startTimeUs;
        }
        return new ContainerMediaChunk(dataSource, dataSpec, trackFormat, trackSelectionReason, trackSelectionData, startTimeUs, endTimeUs2, seekTimeUs, clippedEndTimeUs, firstSegmentNum, segmentCount, sampleOffsetUs, representationHolder2.chunkExtractor);
    }

    private Pair<String, String> getNextObjectAndRangeRequest(long segmentNum, RangedUri segmentUri, RepresentationHolder representationHolder) {
        if (segmentNum + 1 >= representationHolder.getSegmentCount()) {
            return null;
        }
        RangedUri nextSegmentUri = representationHolder.getSegmentUrl(1 + segmentNum);
        Uri uri = segmentUri.resolveUri(representationHolder.selectedBaseUrl.url);
        Uri nextUri = nextSegmentUri.resolveUri(representationHolder.selectedBaseUrl.url);
        String nextObjectRequest = UriUtil.getRelativePath(uri, nextUri);
        String nextRangeRequest = nextSegmentUri.start + "-";
        if (nextSegmentUri.length != -1) {
            nextRangeRequest = nextRangeRequest + (nextSegmentUri.start + nextSegmentUri.length);
        }
        return new Pair<>(nextObjectRequest, nextRangeRequest);
    }

    private RepresentationHolder updateSelectedBaseUrl(int trackIndex) {
        RepresentationHolder representationHolder = this.representationHolders[trackIndex];
        BaseUrl selectedBaseUrl = this.baseUrlExclusionList.selectBaseUrl(representationHolder.representation.baseUrls);
        if (selectedBaseUrl != null && !selectedBaseUrl.equals(representationHolder.selectedBaseUrl)) {
            RepresentationHolder representationHolder2 = representationHolder.copyWithNewSelectedBaseUrl(selectedBaseUrl);
            this.representationHolders[trackIndex] = representationHolder2;
            return representationHolder2;
        }
        return representationHolder;
    }

    /* loaded from: classes5.dex */
    protected static final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final long nowPeriodTimeUs;
        private final RepresentationHolder representationHolder;

        public RepresentationSegmentIterator(RepresentationHolder representation, long firstAvailableSegmentNum, long lastAvailableSegmentNum, long nowPeriodTimeUs) {
            super(firstAvailableSegmentNum, lastAvailableSegmentNum);
            this.representationHolder = representation;
            this.nowPeriodTimeUs = nowPeriodTimeUs;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            int flags;
            checkInBounds();
            long currentIndex = getCurrentIndex();
            RangedUri segmentUri = this.representationHolder.getSegmentUrl(currentIndex);
            if (this.representationHolder.isSegmentAvailableAtFullNetworkSpeed(currentIndex, this.nowPeriodTimeUs)) {
                flags = 0;
            } else {
                flags = 8;
            }
            return DashUtil.buildDataSpec(this.representationHolder.representation, this.representationHolder.selectedBaseUrl.url, segmentUri, flags, ImmutableMap.of());
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentStartTimeUs(getCurrentIndex());
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentEndTimeUs(getCurrentIndex());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes5.dex */
    public static final class RepresentationHolder {
        final ChunkExtractor chunkExtractor;
        private final long periodDurationUs;
        public final Representation representation;
        public final DashSegmentIndex segmentIndex;
        private final long segmentNumShift;
        public final BaseUrl selectedBaseUrl;

        RepresentationHolder(long periodDurationUs, Representation representation, BaseUrl selectedBaseUrl, ChunkExtractor chunkExtractor, long segmentNumShift, DashSegmentIndex segmentIndex) {
            this.periodDurationUs = periodDurationUs;
            this.representation = representation;
            this.selectedBaseUrl = selectedBaseUrl;
            this.segmentNumShift = segmentNumShift;
            this.chunkExtractor = chunkExtractor;
            this.segmentIndex = segmentIndex;
        }

        RepresentationHolder copyWithNewRepresentation(long newPeriodDurationUs, Representation newRepresentation) throws BehindLiveWindowException {
            long newSegmentNumShift;
            DashSegmentIndex oldIndex = this.representation.getIndex();
            DashSegmentIndex newIndex = newRepresentation.getIndex();
            if (oldIndex == null) {
                return new RepresentationHolder(newPeriodDurationUs, newRepresentation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, oldIndex);
            }
            if (!oldIndex.isExplicit()) {
                return new RepresentationHolder(newPeriodDurationUs, newRepresentation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, newIndex);
            }
            long oldIndexSegmentCount = oldIndex.getSegmentCount(newPeriodDurationUs);
            if (oldIndexSegmentCount == 0) {
                return new RepresentationHolder(newPeriodDurationUs, newRepresentation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, newIndex);
            }
            Assertions.checkStateNotNull(newIndex);
            long oldIndexFirstSegmentNum = oldIndex.getFirstSegmentNum();
            long oldIndexStartTimeUs = oldIndex.getTimeUs(oldIndexFirstSegmentNum);
            long oldIndexLastSegmentNum = (oldIndexFirstSegmentNum + oldIndexSegmentCount) - 1;
            long oldIndexEndTimeUs = oldIndex.getTimeUs(oldIndexLastSegmentNum) + oldIndex.getDurationUs(oldIndexLastSegmentNum, newPeriodDurationUs);
            long newIndexFirstSegmentNum = newIndex.getFirstSegmentNum();
            long newIndexStartTimeUs = newIndex.getTimeUs(newIndexFirstSegmentNum);
            long oldIndexFirstSegmentNum2 = this.segmentNumShift;
            if (oldIndexEndTimeUs == newIndexStartTimeUs) {
                long newSegmentNumShift2 = oldIndexFirstSegmentNum2 + ((oldIndexLastSegmentNum + 1) - newIndexFirstSegmentNum);
                newSegmentNumShift = newSegmentNumShift2;
            } else {
                if (oldIndexEndTimeUs < newIndexStartTimeUs) {
                    throw new BehindLiveWindowException();
                }
                if (newIndexStartTimeUs < oldIndexStartTimeUs) {
                    long newSegmentNumShift3 = oldIndexFirstSegmentNum2 - (newIndex.getSegmentNum(oldIndexStartTimeUs, newPeriodDurationUs) - oldIndexFirstSegmentNum);
                    newSegmentNumShift = newSegmentNumShift3;
                } else {
                    long newSegmentNumShift4 = oldIndexFirstSegmentNum2 + (oldIndex.getSegmentNum(newIndexStartTimeUs, newPeriodDurationUs) - newIndexFirstSegmentNum);
                    newSegmentNumShift = newSegmentNumShift4;
                }
            }
            return new RepresentationHolder(newPeriodDurationUs, newRepresentation, this.selectedBaseUrl, this.chunkExtractor, newSegmentNumShift, newIndex);
        }

        RepresentationHolder copyWithNewSegmentIndex(DashSegmentIndex segmentIndex) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, segmentIndex);
        }

        RepresentationHolder copyWithNewSelectedBaseUrl(BaseUrl selectedBaseUrl) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, this.segmentIndex);
        }

        public long getFirstSegmentNum() {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getFirstSegmentNum() + this.segmentNumShift;
        }

        public long getFirstAvailableSegmentNum(long nowUnixTimeUs) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getFirstAvailableSegmentNum(this.periodDurationUs, nowUnixTimeUs) + this.segmentNumShift;
        }

        public long getSegmentCount() {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(long segmentNum) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getTimeUs(segmentNum - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(long segmentNum) {
            return getSegmentStartTimeUs(segmentNum) + ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getDurationUs(segmentNum - this.segmentNumShift, this.periodDurationUs);
        }

        public long getSegmentNum(long positionUs) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentNum(positionUs, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(long segmentNum) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentUrl(segmentNum - this.segmentNumShift);
        }

        public long getLastAvailableSegmentNum(long nowUnixTimeUs) {
            return (getFirstAvailableSegmentNum(nowUnixTimeUs) + ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getAvailableSegmentCount(this.periodDurationUs, nowUnixTimeUs)) - 1;
        }

        public boolean isSegmentAvailableAtFullNetworkSpeed(long segmentNum, long nowPeriodTimeUs) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).isExplicit() || nowPeriodTimeUs == C.TIME_UNSET || getSegmentEndTimeUs(segmentNum) <= nowPeriodTimeUs;
        }
    }
}
