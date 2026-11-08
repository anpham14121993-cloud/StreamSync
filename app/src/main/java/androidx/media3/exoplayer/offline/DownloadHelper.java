package androidx.media3.exoplayer.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseIntArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.StreamKey;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.offline.DownloadHelper;
import androidx.media3.exoplayer.offline.DownloadRequest;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.BaseTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.extractor.ExtractorsFactory;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes3.dex */
public final class DownloadHelper {
    public static final DefaultTrackSelector.Parameters DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT = DefaultTrackSelector.Parameters.DEFAULT_WITHOUT_CONTEXT.buildUpon().setForceHighestSupportedBitrate(true).setConstrainAudioChannelCountToDeviceCapabilities(false).build();
    private Callback callback;
    private final Handler callbackHandler;
    private List<ExoTrackSelection>[][] immutableTrackSelectionsByPeriodAndRenderer;
    private boolean isPreparedWithMedia;
    private final MediaItem.LocalConfiguration localConfiguration;
    private MappingTrackSelector.MappedTrackInfo[] mappedTrackInfos;
    private MediaPreparer mediaPreparer;
    private final MediaSource mediaSource;
    private final RendererCapabilitiesList rendererCapabilities;
    private final SparseIntArray scratchSet;
    private TrackGroupArray[] trackGroupArrays;
    private List<ExoTrackSelection>[][] trackSelectionsByPeriodAndRenderer;
    private final DefaultTrackSelector trackSelector;
    private final Timeline.Window window;

    /* loaded from: classes3.dex */
    public interface Callback {
        void onPrepareError(DownloadHelper downloadHelper, IOException iOException);

        void onPrepared(DownloadHelper downloadHelper);
    }

    /* loaded from: classes3.dex */
    public static class LiveContentUnsupportedException extends IOException {
    }

    public static DefaultTrackSelector.Parameters getDefaultTrackSelectorParameters(Context context) {
        return DefaultTrackSelector.Parameters.getDefaults(context).buildUpon().setForceHighestSupportedBitrate(true).setConstrainAudioChannelCountToDeviceCapabilities(false).build();
    }

    @Deprecated
    public static RendererCapabilities[] getRendererCapabilities(RenderersFactory renderersFactory) {
        Renderer[] renderers = renderersFactory.createRenderers(Util.createHandlerForCurrentOrMainLooper(), new VideoRendererEventListener() { // from class: androidx.media3.exoplayer.offline.DownloadHelper.1
        }, new AudioRendererEventListener() { // from class: androidx.media3.exoplayer.offline.DownloadHelper.2
        }, new TextOutput() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda1
            @Override // androidx.media3.exoplayer.text.TextOutput
            public final void onCues(CueGroup cueGroup) {
                DownloadHelper.lambda$getRendererCapabilities$0(cueGroup);
            }
        }, new MetadataOutput() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda2
            @Override // androidx.media3.exoplayer.metadata.MetadataOutput
            public final void onMetadata(Metadata metadata) {
                DownloadHelper.lambda$getRendererCapabilities$1(metadata);
            }
        });
        RendererCapabilities[] capabilities = new RendererCapabilities[renderers.length];
        for (int i = 0; i < renderers.length; i++) {
            capabilities[i] = renderers[i].getCapabilities();
        }
        return capabilities;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRendererCapabilities$0(CueGroup cues) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRendererCapabilities$1(Metadata metadata) {
    }

    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem) {
        Assertions.checkArgument(isProgressive((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)));
        return forMediaItem(mediaItem, getDefaultTrackSelectorParameters(context), null, null, null);
    }

    public static DownloadHelper forMediaItem(Context context, MediaItem mediaItem, RenderersFactory renderersFactory, DataSource.Factory dataSourceFactory) {
        return forMediaItem(mediaItem, getDefaultTrackSelectorParameters(context), renderersFactory, dataSourceFactory, null);
    }

    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory dataSourceFactory) {
        return forMediaItem(mediaItem, trackSelectionParameters, renderersFactory, dataSourceFactory, null);
    }

    public static DownloadHelper forMediaItem(MediaItem mediaItem, TrackSelectionParameters trackSelectionParameters, RenderersFactory renderersFactory, DataSource.Factory dataSourceFactory, DrmSessionManager drmSessionManager) {
        MediaSource createMediaSourceInternal;
        RendererCapabilitiesList unreleaseableRendererCapabilitiesList;
        boolean isProgressive = isProgressive((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration));
        Assertions.checkArgument(isProgressive || dataSourceFactory != null);
        if (isProgressive) {
            createMediaSourceInternal = null;
        } else {
            createMediaSourceInternal = createMediaSourceInternal(mediaItem, (DataSource.Factory) Util.castNonNull(dataSourceFactory), drmSessionManager);
        }
        if (renderersFactory != null) {
            unreleaseableRendererCapabilitiesList = new DefaultRendererCapabilitiesList.Factory(renderersFactory).createRendererCapabilitiesList();
        } else {
            unreleaseableRendererCapabilitiesList = new UnreleaseableRendererCapabilitiesList(new RendererCapabilities[0]);
        }
        return new DownloadHelper(mediaItem, createMediaSourceInternal, trackSelectionParameters, unreleaseableRendererCapabilitiesList);
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory dataSourceFactory) {
        return createMediaSource(downloadRequest, dataSourceFactory, null);
    }

    public static MediaSource createMediaSource(DownloadRequest downloadRequest, DataSource.Factory dataSourceFactory, DrmSessionManager drmSessionManager) {
        return createMediaSourceInternal(downloadRequest.toMediaItem(), dataSourceFactory, drmSessionManager);
    }

    @Deprecated
    public DownloadHelper(MediaItem mediaItem, MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilities[] rendererCapabilities) {
        this(mediaItem, mediaSource, trackSelectionParameters, new UnreleaseableRendererCapabilitiesList(rendererCapabilities));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DownloadHelper(MediaItem mediaItem, MediaSource mediaSource, TrackSelectionParameters trackSelectionParameters, RendererCapabilitiesList rendererCapabilitiesList) {
        this.localConfiguration = (MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration);
        this.mediaSource = mediaSource;
        this.trackSelector = new DefaultTrackSelector(trackSelectionParameters, new DownloadTrackSelection.Factory());
        this.rendererCapabilities = rendererCapabilitiesList;
        this.scratchSet = new SparseIntArray();
        this.trackSelector.init(new TrackSelector.InvalidationListener() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda6
            @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
            public final void onTrackSelectionsInvalidated() {
                DownloadHelper.lambda$new$2();
            }
        }, new FakeBandwidthMeter());
        this.callbackHandler = Util.createHandlerForCurrentOrMainLooper();
        this.window = new Timeline.Window();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$2() {
    }

    public void prepare(final Callback callback) {
        Assertions.checkState(this.callback == null);
        this.callback = callback;
        if (this.mediaSource != null) {
            this.mediaPreparer = new MediaPreparer(this.mediaSource, this);
        } else {
            this.callbackHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    DownloadHelper.this.lambda$prepare$3(callback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$3(Callback callback) {
        callback.onPrepared(this);
    }

    public void release() {
        if (this.mediaPreparer != null) {
            this.mediaPreparer.release();
        }
        this.trackSelector.release();
        this.rendererCapabilities.release();
    }

    public Object getManifest() {
        if (this.mediaSource == null) {
            return null;
        }
        assertPreparedWithMedia();
        if (this.mediaPreparer.timeline.getWindowCount() > 0) {
            return this.mediaPreparer.timeline.getWindow(0, this.window).manifest;
        }
        return null;
    }

    public int getPeriodCount() {
        if (this.mediaSource == null) {
            return 0;
        }
        assertPreparedWithMedia();
        return this.trackGroupArrays.length;
    }

    public Tracks getTracks(int periodIndex) {
        assertPreparedWithMedia();
        return TrackSelectionUtil.buildTracks(this.mappedTrackInfos[periodIndex], this.immutableTrackSelectionsByPeriodAndRenderer[periodIndex]);
    }

    public TrackGroupArray getTrackGroups(int periodIndex) {
        assertPreparedWithMedia();
        return this.trackGroupArrays[periodIndex];
    }

    public MappingTrackSelector.MappedTrackInfo getMappedTrackInfo(int periodIndex) {
        assertPreparedWithMedia();
        return this.mappedTrackInfos[periodIndex];
    }

    public List<ExoTrackSelection> getTrackSelections(int periodIndex, int rendererIndex) {
        assertPreparedWithMedia();
        return this.immutableTrackSelectionsByPeriodAndRenderer[periodIndex][rendererIndex];
    }

    public void clearTrackSelections(int periodIndex) {
        assertPreparedWithMedia();
        for (int i = 0; i < this.rendererCapabilities.size(); i++) {
            this.trackSelectionsByPeriodAndRenderer[periodIndex][i].clear();
        }
    }

    public void replaceTrackSelections(int periodIndex, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithMedia();
            clearTrackSelections(periodIndex);
            addTrackSelectionInternal(periodIndex, trackSelectionParameters);
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTrackSelection(int periodIndex, TrackSelectionParameters trackSelectionParameters) {
        try {
            assertPreparedWithMedia();
            addTrackSelectionInternal(periodIndex, trackSelectionParameters);
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addAudioLanguagesToSelection(String... languages) {
        try {
            assertPreparedWithMedia();
            TrackSelectionParameters.Builder parametersBuilder = DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT.buildUpon();
            parametersBuilder.setForceHighestSupportedBitrate(true);
            for (RendererCapabilities capabilities : this.rendererCapabilities.getRendererCapabilities()) {
                int trackType = capabilities.getTrackType();
                parametersBuilder.setTrackTypeDisabled(trackType, trackType != 1);
            }
            int periodCount = getPeriodCount();
            for (String language : languages) {
                TrackSelectionParameters parameters = parametersBuilder.setPreferredAudioLanguage(language).build();
                for (int periodIndex = 0; periodIndex < periodCount; periodIndex++) {
                    addTrackSelectionInternal(periodIndex, parameters);
                }
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTextLanguagesToSelection(boolean selectUndeterminedTextLanguage, String... languages) {
        try {
            assertPreparedWithMedia();
            TrackSelectionParameters.Builder parametersBuilder = DEFAULT_TRACK_SELECTOR_PARAMETERS_WITHOUT_CONTEXT.buildUpon();
            parametersBuilder.setSelectUndeterminedTextLanguage(selectUndeterminedTextLanguage);
            parametersBuilder.setForceHighestSupportedBitrate(true);
            for (RendererCapabilities capabilities : this.rendererCapabilities.getRendererCapabilities()) {
                int trackType = capabilities.getTrackType();
                parametersBuilder.setTrackTypeDisabled(trackType, trackType != 3);
            }
            int periodCount = getPeriodCount();
            for (String language : languages) {
                TrackSelectionParameters parameters = parametersBuilder.setPreferredTextLanguage(language).build();
                for (int periodIndex = 0; periodIndex < periodCount; periodIndex++) {
                    addTrackSelectionInternal(periodIndex, parameters);
                }
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public void addTrackSelectionForSingleRenderer(int periodIndex, int rendererIndex, DefaultTrackSelector.Parameters trackSelectorParameters, List<DefaultTrackSelector.SelectionOverride> overrides) {
        try {
            assertPreparedWithMedia();
            DefaultTrackSelector.Parameters.Builder builder = trackSelectorParameters.buildUpon();
            int i = 0;
            while (i < this.mappedTrackInfos[periodIndex].getRendererCount()) {
                builder.setRendererDisabled(i, i != rendererIndex);
                i++;
            }
            if (overrides.isEmpty()) {
                addTrackSelectionInternal(periodIndex, builder.build());
                return;
            }
            TrackGroupArray trackGroupArray = this.mappedTrackInfos[periodIndex].getTrackGroups(rendererIndex);
            for (int i2 = 0; i2 < overrides.size(); i2++) {
                builder.setSelectionOverride(rendererIndex, trackGroupArray, overrides.get(i2));
                addTrackSelectionInternal(periodIndex, builder.build());
            }
        } catch (ExoPlaybackException e) {
            throw new IllegalStateException(e);
        }
    }

    public DownloadRequest getDownloadRequest(byte[] data) {
        return getDownloadRequest(this.localConfiguration.uri.toString(), data);
    }

    public DownloadRequest getDownloadRequest(String id, byte[] data) {
        byte[] bArr;
        DownloadRequest.Builder mimeType = new DownloadRequest.Builder(id, this.localConfiguration.uri).setMimeType(this.localConfiguration.mimeType);
        if (this.localConfiguration.drmConfiguration != null) {
            bArr = this.localConfiguration.drmConfiguration.getKeySetId();
        } else {
            bArr = null;
        }
        DownloadRequest.Builder requestBuilder = mimeType.setKeySetId(bArr).setCustomCacheKey(this.localConfiguration.customCacheKey).setData(data);
        if (this.mediaSource == null) {
            return requestBuilder.build();
        }
        assertPreparedWithMedia();
        List<StreamKey> streamKeys = new ArrayList<>();
        List<ExoTrackSelection> allSelections = new ArrayList<>();
        int periodCount = this.trackSelectionsByPeriodAndRenderer.length;
        for (int periodIndex = 0; periodIndex < periodCount; periodIndex++) {
            allSelections.clear();
            int rendererCount = this.trackSelectionsByPeriodAndRenderer[periodIndex].length;
            for (int rendererIndex = 0; rendererIndex < rendererCount; rendererIndex++) {
                allSelections.addAll(this.trackSelectionsByPeriodAndRenderer[periodIndex][rendererIndex]);
            }
            streamKeys.addAll(this.mediaPreparer.mediaPeriods[periodIndex].getStreamKeys(allSelections));
        }
        return requestBuilder.setStreamKeys(streamKeys).build();
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    private void addTrackSelectionInternal(int periodIndex, TrackSelectionParameters trackSelectionParameters) throws ExoPlaybackException {
        this.trackSelector.setParameters(trackSelectionParameters);
        runTrackSelection(periodIndex);
        UnmodifiableIterator<TrackSelectionOverride> it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride override = it.next();
            this.trackSelector.setParameters(trackSelectionParameters.buildUpon().setOverrideForType(override).build());
            runTrackSelection(periodIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaPrepared() throws ExoPlaybackException {
        Assertions.checkNotNull(this.mediaPreparer);
        Assertions.checkNotNull(this.mediaPreparer.mediaPeriods);
        Assertions.checkNotNull(this.mediaPreparer.timeline);
        int periodCount = this.mediaPreparer.mediaPeriods.length;
        int rendererCount = this.rendererCapabilities.size();
        this.trackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, periodCount, rendererCount);
        this.immutableTrackSelectionsByPeriodAndRenderer = (List[][]) Array.newInstance((Class<?>) List.class, periodCount, rendererCount);
        for (int i = 0; i < periodCount; i++) {
            for (int j = 0; j < rendererCount; j++) {
                this.trackSelectionsByPeriodAndRenderer[i][j] = new ArrayList();
                this.immutableTrackSelectionsByPeriodAndRenderer[i][j] = Collections.unmodifiableList(this.trackSelectionsByPeriodAndRenderer[i][j]);
            }
        }
        this.trackGroupArrays = new TrackGroupArray[periodCount];
        this.mappedTrackInfos = new MappingTrackSelector.MappedTrackInfo[periodCount];
        for (int i2 = 0; i2 < periodCount; i2++) {
            this.trackGroupArrays[i2] = this.mediaPreparer.mediaPeriods[i2].getTrackGroups();
            TrackSelectorResult trackSelectorResult = runTrackSelection(i2);
            this.trackSelector.onSelectionActivated(trackSelectorResult.info);
            this.mappedTrackInfos[i2] = (MappingTrackSelector.MappedTrackInfo) Assertions.checkNotNull(this.trackSelector.getCurrentMappedTrackInfo());
        }
        setPreparedWithMedia();
        ((Handler) Assertions.checkNotNull(this.callbackHandler)).post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DownloadHelper.this.lambda$onMediaPrepared$4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onMediaPrepared$4() {
        ((Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onMediaPreparationFailed$5(IOException error) {
        ((Callback) Assertions.checkNotNull(this.callback)).onPrepareError(this, error);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMediaPreparationFailed(final IOException error) {
        ((Handler) Assertions.checkNotNull(this.callbackHandler)).post(new Runnable() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DownloadHelper.this.lambda$onMediaPreparationFailed$5(error);
            }
        });
    }

    @RequiresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void setPreparedWithMedia() {
        this.isPreparedWithMedia = true;
    }

    @EnsuresNonNull({"trackGroupArrays", "mappedTrackInfos", "trackSelectionsByPeriodAndRenderer", "immutableTrackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline", "mediaPreparer.mediaPeriods"})
    private void assertPreparedWithMedia() {
        Assertions.checkState(this.isPreparedWithMedia);
    }

    @RequiresNonNull({"trackGroupArrays", "trackSelectionsByPeriodAndRenderer", "mediaPreparer", "mediaPreparer.timeline"})
    private TrackSelectorResult runTrackSelection(int periodIndex) throws ExoPlaybackException {
        TrackSelectorResult trackSelectorResult = this.trackSelector.selectTracks(this.rendererCapabilities.getRendererCapabilities(), this.trackGroupArrays[periodIndex], new MediaSource.MediaPeriodId(this.mediaPreparer.timeline.getUidOfPeriod(periodIndex)), this.mediaPreparer.timeline);
        for (int i = 0; i < trackSelectorResult.length; i++) {
            ExoTrackSelection newSelection = trackSelectorResult.selections[i];
            if (newSelection != null) {
                List<ExoTrackSelection> existingSelectionList = this.trackSelectionsByPeriodAndRenderer[periodIndex][i];
                boolean mergedWithExistingSelection = false;
                int j = 0;
                while (true) {
                    if (j >= existingSelectionList.size()) {
                        break;
                    }
                    ExoTrackSelection existingSelection = existingSelectionList.get(j);
                    if (!existingSelection.getTrackGroup().equals(newSelection.getTrackGroup())) {
                        j++;
                    } else {
                        this.scratchSet.clear();
                        for (int k = 0; k < existingSelection.length(); k++) {
                            this.scratchSet.put(existingSelection.getIndexInTrackGroup(k), 0);
                        }
                        for (int k2 = 0; k2 < newSelection.length(); k2++) {
                            this.scratchSet.put(newSelection.getIndexInTrackGroup(k2), 0);
                        }
                        int[] mergedTracks = new int[this.scratchSet.size()];
                        for (int k3 = 0; k3 < this.scratchSet.size(); k3++) {
                            mergedTracks[k3] = this.scratchSet.keyAt(k3);
                        }
                        existingSelectionList.set(j, new DownloadTrackSelection(existingSelection.getTrackGroup(), mergedTracks));
                        mergedWithExistingSelection = true;
                    }
                }
                if (!mergedWithExistingSelection) {
                    existingSelectionList.add(newSelection);
                }
            }
        }
        return trackSelectorResult;
    }

    private static MediaSource createMediaSourceInternal(MediaItem mediaItem, DataSource.Factory dataSourceFactory, final DrmSessionManager drmSessionManager) {
        DefaultMediaSourceFactory mediaSourceFactory = new DefaultMediaSourceFactory(dataSourceFactory, ExtractorsFactory.EMPTY);
        if (drmSessionManager != null) {
            mediaSourceFactory.setDrmSessionManagerProvider(new DrmSessionManagerProvider() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$$ExternalSyntheticLambda0
                @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
                public final DrmSessionManager get(MediaItem mediaItem2) {
                    return DownloadHelper.lambda$createMediaSourceInternal$6(DrmSessionManager.this, mediaItem2);
                }
            });
        }
        return mediaSourceFactory.createMediaSource(mediaItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DrmSessionManager lambda$createMediaSourceInternal$6(DrmSessionManager drmSessionManager, MediaItem unusedMediaItem) {
        return drmSessionManager;
    }

    private static boolean isProgressive(MediaItem.LocalConfiguration localConfiguration) {
        return Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType) == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class MediaPreparer implements MediaSource.MediaSourceCaller, MediaPeriod.Callback, Handler.Callback {
        private static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_FAILED = 2;
        private static final int DOWNLOAD_HELPER_CALLBACK_MESSAGE_PREPARED = 1;
        private static final int MESSAGE_CHECK_FOR_FAILURE = 2;
        private static final int MESSAGE_CONTINUE_LOADING = 3;
        private static final int MESSAGE_PREPARE_SOURCE = 1;
        private static final int MESSAGE_RELEASE = 4;
        private final DownloadHelper downloadHelper;
        private final Handler downloadHelperHandler;
        public MediaPeriod[] mediaPeriods;
        private final MediaSource mediaSource;
        private final Handler mediaSourceHandler;
        private final HandlerThread mediaSourceThread;
        private boolean released;
        public Timeline timeline;
        private final Allocator allocator = new DefaultAllocator(true, 65536);
        private final ArrayList<MediaPeriod> pendingMediaPeriods = new ArrayList<>();

        public MediaPreparer(MediaSource mediaSource, DownloadHelper downloadHelper) {
            this.mediaSource = mediaSource;
            this.downloadHelper = downloadHelper;
            Handler downloadThreadHandler = Util.createHandlerForCurrentOrMainLooper(new Handler.Callback() { // from class: androidx.media3.exoplayer.offline.DownloadHelper$MediaPreparer$$ExternalSyntheticLambda0
                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    boolean handleDownloadHelperCallbackMessage;
                    handleDownloadHelperCallbackMessage = DownloadHelper.MediaPreparer.this.handleDownloadHelperCallbackMessage(message);
                    return handleDownloadHelperCallbackMessage;
                }
            });
            this.downloadHelperHandler = downloadThreadHandler;
            this.mediaSourceThread = new HandlerThread("ExoPlayer:DownloadHelper");
            this.mediaSourceThread.start();
            this.mediaSourceHandler = Util.createHandler(this.mediaSourceThread.getLooper(), this);
            this.mediaSourceHandler.sendEmptyMessage(1);
        }

        public void release() {
            if (this.released) {
                return;
            }
            this.released = true;
            this.mediaSourceHandler.sendEmptyMessage(4);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    this.mediaSource.prepareSource(this, null, PlayerId.UNSET);
                    this.mediaSourceHandler.sendEmptyMessage(2);
                    return true;
                case 2:
                    try {
                        if (this.mediaPeriods == null) {
                            this.mediaSource.maybeThrowSourceInfoRefreshError();
                        } else {
                            for (int i = 0; i < this.pendingMediaPeriods.size(); i++) {
                                this.pendingMediaPeriods.get(i).maybeThrowPrepareError();
                            }
                        }
                        this.mediaSourceHandler.sendEmptyMessageDelayed(2, 100L);
                    } catch (IOException e) {
                        this.downloadHelperHandler.obtainMessage(2, e).sendToTarget();
                    }
                    return true;
                case 3:
                    MediaPeriod mediaPeriod = (MediaPeriod) msg.obj;
                    if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                        mediaPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(0L).build());
                    }
                    return true;
                case 4:
                    if (this.mediaPeriods != null) {
                        for (MediaPeriod period : this.mediaPeriods) {
                            this.mediaSource.releasePeriod(period);
                        }
                    }
                    this.mediaSource.releaseSource(this);
                    this.mediaSourceHandler.removeCallbacksAndMessages(null);
                    this.mediaSourceThread.quit();
                    return true;
                default:
                    return false;
            }
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
        public void onSourceInfoRefreshed(MediaSource source, Timeline timeline) {
            if (this.timeline != null) {
                return;
            }
            if (timeline.getWindow(0, new Timeline.Window()).isLive()) {
                this.downloadHelperHandler.obtainMessage(2, new LiveContentUnsupportedException()).sendToTarget();
                return;
            }
            this.timeline = timeline;
            this.mediaPeriods = new MediaPeriod[timeline.getPeriodCount()];
            for (int i = 0; i < this.mediaPeriods.length; i++) {
                MediaPeriod mediaPeriod = this.mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(i)), this.allocator, 0L);
                this.mediaPeriods[i] = mediaPeriod;
                this.pendingMediaPeriods.add(mediaPeriod);
            }
            for (MediaPeriod mediaPeriod2 : this.mediaPeriods) {
                mediaPeriod2.prepare(this, 0L);
            }
        }

        @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
        public void onPrepared(MediaPeriod mediaPeriod) {
            this.pendingMediaPeriods.remove(mediaPeriod);
            if (this.pendingMediaPeriods.isEmpty()) {
                this.mediaSourceHandler.removeMessages(2);
                this.downloadHelperHandler.sendEmptyMessage(1);
            }
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            if (this.pendingMediaPeriods.contains(mediaPeriod)) {
                this.mediaSourceHandler.obtainMessage(3, mediaPeriod).sendToTarget();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean handleDownloadHelperCallbackMessage(Message msg) {
            if (this.released) {
                return false;
            }
            switch (msg.what) {
                case 1:
                    try {
                        this.downloadHelper.onMediaPrepared();
                    } catch (ExoPlaybackException e) {
                        this.downloadHelperHandler.obtainMessage(2, new IOException(e)).sendToTarget();
                    }
                    return true;
                case 2:
                    release();
                    this.downloadHelper.onMediaPreparationFailed((IOException) Util.castNonNull(msg.obj));
                    return true;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class DownloadTrackSelection extends BaseTrackSelection {

        /* loaded from: classes3.dex */
        private static final class Factory implements ExoTrackSelection.Factory {
            private Factory() {
            }

            @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection.Factory
            public ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitions, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
                DownloadTrackSelection downloadTrackSelection;
                ExoTrackSelection[] selections = new ExoTrackSelection[definitions.length];
                for (int i = 0; i < definitions.length; i++) {
                    if (definitions[i] == null) {
                        downloadTrackSelection = null;
                    } else {
                        downloadTrackSelection = new DownloadTrackSelection(definitions[i].group, definitions[i].tracks);
                    }
                    selections[i] = downloadTrackSelection;
                }
                return selections;
            }
        }

        public DownloadTrackSelection(TrackGroup trackGroup, int[] tracks) {
            super(trackGroup, tracks);
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectedIndex() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // androidx.media3.exoplayer.trackselection.ExoTrackSelection
        public void updateSelectedTrack(long playbackPositionUs, long bufferedDurationUs, long availableDurationUs, List<? extends MediaChunk> queue, MediaChunkIterator[] mediaChunkIterators) {
        }
    }

    /* loaded from: classes3.dex */
    private static final class FakeBandwidthMeter implements BandwidthMeter {
        private FakeBandwidthMeter() {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public long getBitrateEstimate() {
            return 0L;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public TransferListener getTransferListener() {
            return null;
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void addEventListener(Handler eventHandler, BandwidthMeter.EventListener eventListener) {
        }

        @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
        public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class UnreleaseableRendererCapabilitiesList implements RendererCapabilitiesList {
        private final RendererCapabilities[] rendererCapabilities;

        private UnreleaseableRendererCapabilitiesList(RendererCapabilities[] rendererCapabilities) {
            this.rendererCapabilities = rendererCapabilities;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public RendererCapabilities[] getRendererCapabilities() {
            return this.rendererCapabilities;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public int size() {
            return this.rendererCapabilities.length;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList
        public void release() {
        }
    }
}
