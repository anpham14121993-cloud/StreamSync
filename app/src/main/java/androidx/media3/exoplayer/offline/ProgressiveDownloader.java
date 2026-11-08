package androidx.media3.exoplayer.offline;

import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.exoplayer.offline.Downloader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final class ProgressiveDownloader implements Downloader {
    private final CacheWriter cacheWriter;
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private volatile RunnableFutureTask<Void, IOException> downloadRunnable;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final PriorityTaskManager priorityTaskManager;
    private Downloader.ProgressListener progressListener;

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory cacheDataSourceFactory) {
        this(mediaItem, cacheDataSourceFactory, new DefaultDownloaderFactory$$ExternalSyntheticLambda0());
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory cacheDataSourceFactory, Executor executor) {
        this.executor = (Executor) Assertions.checkNotNull(executor);
        Assertions.checkNotNull(mediaItem.localConfiguration);
        this.dataSpec = new DataSpec.Builder().setUri(mediaItem.localConfiguration.uri).setKey(mediaItem.localConfiguration.customCacheKey).setFlags(4).build();
        this.dataSource = cacheDataSourceFactory.createDataSourceForDownloading();
        CacheWriter.ProgressListener progressListener = new CacheWriter.ProgressListener() { // from class: androidx.media3.exoplayer.offline.ProgressiveDownloader$$ExternalSyntheticLambda0
            @Override // androidx.media3.datasource.cache.CacheWriter.ProgressListener
            public final void onProgress(long j, long j2, long j3) {
                ProgressiveDownloader.this.onProgress(j, j2, j3);
            }
        };
        this.cacheWriter = new CacheWriter(this.dataSource, this.dataSpec, null, progressListener);
        this.priorityTaskManager = cacheDataSourceFactory.getUpstreamPriorityTaskManager();
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void download(Downloader.ProgressListener progressListener) throws IOException, InterruptedException {
        this.progressListener = progressListener;
        if (this.priorityTaskManager != null) {
            this.priorityTaskManager.add(-4000);
        }
        boolean finished = false;
        while (!finished) {
            try {
                if (this.isCanceled) {
                    break;
                }
                this.downloadRunnable = new RunnableFutureTask<Void, IOException>() { // from class: androidx.media3.exoplayer.offline.ProgressiveDownloader.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // androidx.media3.common.util.RunnableFutureTask
                    public Void doWork() throws IOException {
                        ProgressiveDownloader.this.cacheWriter.cache();
                        return null;
                    }

                    @Override // androidx.media3.common.util.RunnableFutureTask
                    protected void cancelWork() {
                        ProgressiveDownloader.this.cacheWriter.cancel();
                    }
                };
                if (this.priorityTaskManager != null) {
                    this.priorityTaskManager.proceed(-4000);
                }
                this.executor.execute(this.downloadRunnable);
                try {
                    this.downloadRunnable.get();
                    finished = true;
                } catch (ExecutionException e) {
                    Throwable cause = (Throwable) Assertions.checkNotNull(e.getCause());
                    if (!(cause instanceof PriorityTaskManager.PriorityTooLowException)) {
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        Util.sneakyThrow(cause);
                    }
                }
            } finally {
                ((RunnableFutureTask) Assertions.checkNotNull(this.downloadRunnable)).blockUntilFinished();
                if (this.priorityTaskManager != null) {
                    this.priorityTaskManager.remove(-4000);
                }
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void cancel() {
        this.isCanceled = true;
        RunnableFutureTask<Void, IOException> downloadRunnable = this.downloadRunnable;
        if (downloadRunnable != null) {
            downloadRunnable.cancel(true);
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void remove() {
        this.dataSource.getCache().removeResource(this.dataSource.getCacheKeyFactory().buildCacheKey(this.dataSpec));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgress(long contentLength, long bytesCached, long newBytesCached) {
        float percentDownloaded;
        if (this.progressListener == null) {
            return;
        }
        if (contentLength == -1 || contentLength == 0) {
            percentDownloaded = -1.0f;
        } else {
            percentDownloaded = (((float) bytesCached) * 100.0f) / ((float) contentLength);
        }
        this.progressListener.onProgress(contentLength, bytesCached, percentDownloaded);
    }
}
