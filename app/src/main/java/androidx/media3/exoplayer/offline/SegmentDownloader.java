package androidx.media3.exoplayer.offline;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheKeyFactory;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.datasource.cache.ContentMetadata;
import androidx.media3.exoplayer.offline.Downloader;
import androidx.media3.exoplayer.offline.FilterableManifest;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class SegmentDownloader<M extends FilterableManifest<M>> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    public static final long DEFAULT_MAX_MERGED_SEGMENT_START_TIME_DIFF_MS = 20000;
    private final ArrayList<RunnableFutureTask<?, ?>> activeRunnables;
    private final Cache cache;
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final CacheKeyFactory cacheKeyFactory;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final DataSpec manifestDataSpec;
    private final ParsingLoadable.Parser<M> manifestParser;
    private final long maxMergedSegmentStartTimeDiffUs;
    private final PriorityTaskManager priorityTaskManager;
    private final ArrayList<StreamKey> streamKeys;

    protected abstract List<Segment> getSegments(DataSource dataSource, M m, boolean z) throws IOException, InterruptedException;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long startTimeUs, DataSpec dataSpec) {
            this.startTimeUs = startTimeUs;
            this.dataSpec = dataSpec;
        }

        @Override // java.lang.Comparable
        public int compareTo(Segment other) {
            return Util.compareLong(this.startTimeUs, other.startTimeUs);
        }
    }

    @Deprecated
    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> manifestParser, CacheDataSource.Factory cacheDataSourceFactory, Executor executor) {
        this(mediaItem, manifestParser, cacheDataSourceFactory, executor, 20000L);
    }

    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> manifestParser, CacheDataSource.Factory cacheDataSourceFactory, Executor executor, long maxMergedSegmentStartTimeDiffMs) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        this.manifestDataSpec = getCompressibleDataSpec(mediaItem.localConfiguration.uri);
        this.manifestParser = manifestParser;
        this.streamKeys = new ArrayList<>(mediaItem.localConfiguration.streamKeys);
        this.cacheDataSourceFactory = cacheDataSourceFactory;
        this.executor = executor;
        this.cache = (Cache) Assertions.checkNotNull(cacheDataSourceFactory.getCache());
        this.cacheKeyFactory = cacheDataSourceFactory.getCacheKeyFactory();
        this.priorityTaskManager = cacheDataSourceFactory.getUpstreamPriorityTaskManager();
        this.activeRunnables = new ArrayList<>();
        this.maxMergedSegmentStartTimeDiffUs = Util.msToUs(maxMergedSegmentStartTimeDiffMs);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void download(Downloader.ProgressListener progressListener) throws IOException, InterruptedException {
        int i;
        int size;
        CacheDataSource segmentDataSource;
        byte[] temporaryBuffer;
        CacheDataSource dataSource;
        ArrayDeque<Segment> pendingSegments = new ArrayDeque<>();
        ArrayDeque<SegmentDownloadRunnable> recycledRunnables = new ArrayDeque<>();
        if (this.priorityTaskManager != null) {
            this.priorityTaskManager.add(-4000);
        }
        try {
            CacheDataSource dataSource2 = this.cacheDataSourceFactory.createDataSourceForDownloading();
            FilterableManifest manifest = getManifest(dataSource2, this.manifestDataSpec, false);
            List<Segment> segments = getSegments(dataSource2, !this.streamKeys.isEmpty() ? (FilterableManifest) manifest.copy(this.streamKeys) : manifest, false);
            Collections.sort(segments);
            mergeSegments(segments, this.cacheKeyFactory, this.maxMergedSegmentStartTimeDiffUs);
            int totalSegments = segments.size();
            int i2 = segments.size() - 1;
            long contentLength = 0;
            long bytesDownloaded = 0;
            int segmentsDownloaded = 0;
            while (i2 >= 0) {
                DataSpec dataSpec = segments.get(i2).dataSpec;
                String cacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
                long segmentLength = dataSpec.length;
                if (segmentLength == -1) {
                    long resourceLength = ContentMetadata.getContentLength(this.cache.getContentMetadata(cacheKey));
                    if (resourceLength != -1) {
                        dataSource = dataSource2;
                        segmentLength = resourceLength - dataSpec.position;
                    } else {
                        dataSource = dataSource2;
                    }
                } else {
                    dataSource = dataSource2;
                }
                long segmentBytesDownloaded = this.cache.getCachedBytes(cacheKey, dataSpec.position, segmentLength);
                bytesDownloaded += segmentBytesDownloaded;
                if (segmentLength != -1) {
                    if (segmentLength == segmentBytesDownloaded) {
                        segmentsDownloaded++;
                        segments.remove(i2);
                    }
                    if (contentLength != -1) {
                        contentLength += segmentLength;
                    }
                } else {
                    contentLength = -1;
                }
                i2--;
                dataSource2 = dataSource;
            }
            ProgressNotifier progressNotifier = progressListener != null ? new ProgressNotifier(progressListener, contentLength, totalSegments, bytesDownloaded, segmentsDownloaded) : null;
            pendingSegments.addAll(segments);
            while (!this.isCanceled && !pendingSegments.isEmpty()) {
                if (this.priorityTaskManager != null) {
                    this.priorityTaskManager.proceed(-4000);
                }
                if (recycledRunnables.isEmpty()) {
                    CacheDataSource segmentDataSource2 = this.cacheDataSourceFactory.createDataSourceForDownloading();
                    segmentDataSource = segmentDataSource2;
                    temporaryBuffer = new byte[131072];
                } else {
                    SegmentDownloadRunnable recycledRunnable = recycledRunnables.removeFirst();
                    CacheDataSource segmentDataSource3 = recycledRunnable.dataSource;
                    byte[] temporaryBuffer2 = recycledRunnable.temporaryBuffer;
                    segmentDataSource = segmentDataSource3;
                    temporaryBuffer = temporaryBuffer2;
                }
                Segment segment = pendingSegments.removeFirst();
                SegmentDownloadRunnable downloadRunnable = new SegmentDownloadRunnable(segment, segmentDataSource, progressNotifier, temporaryBuffer);
                addActiveRunnable(downloadRunnable);
                this.executor.execute(downloadRunnable);
                for (int j = this.activeRunnables.size() - 1; j >= 0; j--) {
                    SegmentDownloadRunnable activeRunnable = (SegmentDownloadRunnable) this.activeRunnables.get(j);
                    if (pendingSegments.isEmpty() || activeRunnable.isDone()) {
                        try {
                            activeRunnable.get();
                            removeActiveRunnable(j);
                            recycledRunnables.addLast(activeRunnable);
                        } catch (ExecutionException e) {
                            Throwable cause = (Throwable) Assertions.checkNotNull(e.getCause());
                            if (cause instanceof PriorityTaskManager.PriorityTooLowException) {
                                pendingSegments.addFirst(activeRunnable.segment);
                                removeActiveRunnable(j);
                                recycledRunnables.addLast(activeRunnable);
                            } else {
                                if (cause instanceof IOException) {
                                    throw ((IOException) cause);
                                }
                                Util.sneakyThrow(cause);
                            }
                        }
                    }
                }
                downloadRunnable.blockUntilStarted();
            }
            while (true) {
                if (i >= size) {
                    break;
                }
            }
        } finally {
            for (i = 0; i < this.activeRunnables.size(); i++) {
                this.activeRunnables.get(i).cancel(true);
            }
            for (int i3 = this.activeRunnables.size() - 1; i3 >= 0; i3--) {
                this.activeRunnables.get(i3).blockUntilFinished();
                removeActiveRunnable(i3);
            }
            if (this.priorityTaskManager != null) {
                this.priorityTaskManager.remove(-4000);
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void cancel() {
        synchronized (this.activeRunnables) {
            this.isCanceled = true;
            for (int i = 0; i < this.activeRunnables.size(); i++) {
                this.activeRunnables.get(i).cancel(true);
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void remove() {
        CacheDataSource dataSource = this.cacheDataSourceFactory.createDataSourceForRemovingDownload();
        try {
            try {
                M manifest = getManifest(dataSource, this.manifestDataSpec, true);
                List<Segment> segments = getSegments(dataSource, manifest, true);
                for (int i = 0; i < segments.size(); i++) {
                    this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(segments.get(i).dataSpec));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e2) {
            }
        } finally {
            this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(this.manifestDataSpec));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final M getManifest(final DataSource dataSource, final DataSpec dataSpec, boolean removing) throws InterruptedException, IOException {
        return (M) execute(new RunnableFutureTask<M, IOException>() { // from class: androidx.media3.exoplayer.offline.SegmentDownloader.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.media3.common.util.RunnableFutureTask
            public M doWork() throws IOException {
                return (M) ParsingLoadable.load(dataSource, SegmentDownloader.this.manifestParser, dataSpec, 4);
            }
        }, removing);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <T> T execute(RunnableFutureTask<T, ?> runnable, boolean removing) throws InterruptedException, IOException {
        if (removing) {
            runnable.run();
            try {
                return runnable.get();
            } catch (ExecutionException e) {
                Throwable cause = (Throwable) Assertions.checkNotNull(e.getCause());
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                Util.sneakyThrow(e);
            }
        }
        while (!this.isCanceled) {
            if (this.priorityTaskManager != null) {
                this.priorityTaskManager.proceed(-4000);
            }
            addActiveRunnable(runnable);
            this.executor.execute(runnable);
            try {
                return runnable.get();
            } catch (ExecutionException e2) {
                Throwable cause2 = (Throwable) Assertions.checkNotNull(e2.getCause());
                if (!(cause2 instanceof PriorityTaskManager.PriorityTooLowException)) {
                    if (cause2 instanceof IOException) {
                        throw ((IOException) cause2);
                    }
                    Util.sneakyThrow(e2);
                }
            } finally {
                runnable.blockUntilFinished();
                removeActiveRunnable((RunnableFutureTask<?, ?>) runnable);
            }
        }
        throw new InterruptedException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static DataSpec getCompressibleDataSpec(Uri uri) {
        return new DataSpec.Builder().setUri(uri).setFlags(1).build();
    }

    private <T> void addActiveRunnable(RunnableFutureTask<T, ?> runnable) throws InterruptedException {
        synchronized (this.activeRunnables) {
            if (this.isCanceled) {
                throw new InterruptedException();
            }
            this.activeRunnables.add(runnable);
        }
    }

    private void removeActiveRunnable(RunnableFutureTask<?, ?> runnable) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(runnable);
        }
    }

    private void removeActiveRunnable(int index) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(index);
        }
    }

    private static void mergeSegments(List<Segment> segments, CacheKeyFactory keyFactory, long maxMergedSegmentStartTimeDiffUs) {
        HashMap<String, Integer> lastIndexByCacheKey = new HashMap<>();
        int nextOutIndex = 0;
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            String cacheKey = keyFactory.buildCacheKey(segment.dataSpec);
            Integer lastIndex = lastIndexByCacheKey.get(cacheKey);
            Segment lastSegment = lastIndex == null ? null : segments.get(lastIndex.intValue());
            if (lastSegment == null || segment.startTimeUs > lastSegment.startTimeUs + maxMergedSegmentStartTimeDiffUs || !canMergeSegments(lastSegment.dataSpec, segment.dataSpec)) {
                lastIndexByCacheKey.put(cacheKey, Integer.valueOf(nextOutIndex));
                segments.set(nextOutIndex, segment);
                nextOutIndex++;
            } else {
                long mergedLength = segment.dataSpec.length != -1 ? segment.dataSpec.length + lastSegment.dataSpec.length : -1L;
                DataSpec mergedDataSpec = lastSegment.dataSpec.subrange(0L, mergedLength);
                segments.set(((Integer) Assertions.checkNotNull(lastIndex)).intValue(), new Segment(lastSegment.startTimeUs, mergedDataSpec));
            }
        }
        int i2 = segments.size();
        Util.removeRange(segments, nextOutIndex, i2);
    }

    private static boolean canMergeSegments(DataSpec dataSpec1, DataSpec dataSpec2) {
        return dataSpec1.uri.equals(dataSpec2.uri) && dataSpec1.length != -1 && dataSpec1.position + dataSpec1.length == dataSpec2.position && Util.areEqual(dataSpec1.key, dataSpec2.key) && dataSpec1.flags == dataSpec2.flags && dataSpec1.httpMethod == dataSpec2.httpMethod && dataSpec1.httpRequestHeaders.equals(dataSpec2.httpRequestHeaders);
    }

    /* loaded from: classes3.dex */
    private static final class SegmentDownloadRunnable extends RunnableFutureTask<Void, IOException> {
        private final CacheWriter cacheWriter;
        public final CacheDataSource dataSource;
        private final ProgressNotifier progressNotifier;
        public final Segment segment;
        public final byte[] temporaryBuffer;

        public SegmentDownloadRunnable(Segment segment, CacheDataSource dataSource, ProgressNotifier progressNotifier, byte[] temporaryBuffer) {
            this.segment = segment;
            this.dataSource = dataSource;
            this.progressNotifier = progressNotifier;
            this.temporaryBuffer = temporaryBuffer;
            this.cacheWriter = new CacheWriter(dataSource, segment.dataSpec, temporaryBuffer, progressNotifier);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.media3.common.util.RunnableFutureTask
        public Void doWork() throws IOException {
            this.cacheWriter.cache();
            if (this.progressNotifier != null) {
                this.progressNotifier.onSegmentDownloaded();
                return null;
            }
            return null;
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        protected void cancelWork() {
            this.cacheWriter.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ProgressNotifier implements CacheWriter.ProgressListener {
        private long bytesDownloaded;
        private final long contentLength;
        private final Downloader.ProgressListener progressListener;
        private int segmentsDownloaded;
        private final int totalSegments;

        public ProgressNotifier(Downloader.ProgressListener progressListener, long contentLength, int totalSegments, long bytesDownloaded, int segmentsDownloaded) {
            this.progressListener = progressListener;
            this.contentLength = contentLength;
            this.totalSegments = totalSegments;
            this.bytesDownloaded = bytesDownloaded;
            this.segmentsDownloaded = segmentsDownloaded;
        }

        @Override // androidx.media3.datasource.cache.CacheWriter.ProgressListener
        public void onProgress(long requestLength, long bytesCached, long newBytesCached) {
            this.bytesDownloaded += newBytesCached;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }

        public void onSegmentDownloaded() {
            this.segmentsDownloaded++;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }

        private float getPercentDownloaded() {
            if (this.contentLength != -1 && this.contentLength != 0) {
                return (((float) this.bytesDownloaded) * 100.0f) / ((float) this.contentLength);
            }
            if (this.totalSegments != 0) {
                return (this.segmentsDownloaded * 100.0f) / this.totalSegments;
            }
            return -1.0f;
        }
    }
}
