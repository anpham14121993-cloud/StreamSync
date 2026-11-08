package androidx.media3.exoplayer.dash.offline;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.dash.BaseUrlExclusionList;
import androidx.media3.exoplayer.dash.DashSegmentIndex;
import androidx.media3.exoplayer.dash.DashUtil;
import androidx.media3.exoplayer.dash.DashWrappingSegmentIndex;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.DashManifestParser;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.offline.SegmentDownloader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.ChunkIndex;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public final class DashDownloader extends SegmentDownloader<DashManifest> {
    private final BaseUrlExclusionList baseUrlExclusionList;

    public DashDownloader(MediaItem mediaItem, CacheDataSource.Factory cacheDataSourceFactory) {
        this(mediaItem, cacheDataSourceFactory, new Executor() { // from class: androidx.media3.exoplayer.dash.offline.DashDownloader$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        });
    }

    public DashDownloader(MediaItem mediaItem, CacheDataSource.Factory cacheDataSourceFactory, Executor executor) {
        this(mediaItem, new DashManifestParser(), cacheDataSourceFactory, executor, 20000L);
    }

    @Deprecated
    public DashDownloader(MediaItem mediaItem, ParsingLoadable.Parser<DashManifest> manifestParser, CacheDataSource.Factory cacheDataSourceFactory, Executor executor) {
        this(mediaItem, manifestParser, cacheDataSourceFactory, executor, 20000L);
    }

    public DashDownloader(MediaItem mediaItem, ParsingLoadable.Parser<DashManifest> manifestParser, CacheDataSource.Factory cacheDataSourceFactory, Executor executor, long maxMergedSegmentStartTimeDiffMs) {
        super(mediaItem, manifestParser, cacheDataSourceFactory, executor, maxMergedSegmentStartTimeDiffMs);
        this.baseUrlExclusionList = new BaseUrlExclusionList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.offline.SegmentDownloader
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, DashManifest manifest, boolean removing) throws IOException, InterruptedException {
        ArrayList<SegmentDownloader.Segment> segments = new ArrayList<>();
        for (int i = 0; i < manifest.getPeriodCount(); i++) {
            Period period = manifest.getPeriod(i);
            long periodStartUs = Util.msToUs(period.startMs);
            long periodDurationUs = manifest.getPeriodDurationUs(i);
            int j = 0;
            for (List<AdaptationSet> adaptationSets = period.adaptationSets; j < adaptationSets.size(); adaptationSets = adaptationSets) {
                addSegmentsForAdaptationSet(dataSource, adaptationSets.get(j), periodStartUs, periodDurationUs, removing, segments);
                j++;
            }
        }
        return segments;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void addSegmentsForAdaptationSet(androidx.media3.datasource.DataSource r32, androidx.media3.exoplayer.dash.manifest.AdaptationSet r33, long r34, long r36, boolean r38, java.util.ArrayList<androidx.media3.exoplayer.offline.SegmentDownloader.Segment> r39) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r31 = this;
            r7 = r31
            r8 = r33
            r9 = r38
            r10 = r39
            r0 = 0
            r11 = r0
        La:
            java.util.List<androidx.media3.exoplayer.dash.manifest.Representation> r0 = r8.representations
            int r0 = r0.size()
            if (r11 >= r0) goto Lc7
            java.util.List<androidx.media3.exoplayer.dash.manifest.Representation> r0 = r8.representations
            java.lang.Object r0 = r0.get(r11)
            r12 = r0
            androidx.media3.exoplayer.dash.manifest.Representation r12 = (androidx.media3.exoplayer.dash.manifest.Representation) r12
            int r0 = r8.type     // Catch: java.io.IOException -> Lba
            r13 = r32
            androidx.media3.exoplayer.dash.DashSegmentIndex r0 = r7.getSegmentIndex(r13, r0, r12, r9)     // Catch: java.io.IOException -> Lb8
            if (r0 == 0) goto Lac
        L26:
            r14 = r36
            long r16 = r0.getSegmentCount(r14)
            r1 = -1
            int r1 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r1 == 0) goto La4
            androidx.media3.exoplayer.dash.BaseUrlExclusionList r1 = r7.baseUrlExclusionList
            com.google.common.collect.ImmutableList<androidx.media3.exoplayer.dash.manifest.BaseUrl> r2 = r12.baseUrls
            androidx.media3.exoplayer.dash.manifest.BaseUrl r1 = r1.selectBaseUrl(r2)
            java.lang.Object r1 = androidx.media3.common.util.Util.castNonNull(r1)
            androidx.media3.exoplayer.dash.manifest.BaseUrl r1 = (androidx.media3.exoplayer.dash.manifest.BaseUrl) r1
            java.lang.String r6 = r1.url
            androidx.media3.exoplayer.dash.manifest.RangedUri r18 = r12.getInitializationUri()
            if (r18 == 0) goto L5a
            r1 = r31
            r2 = r12
            r3 = r6
            r4 = r34
            r19 = r6
            r6 = r18
            androidx.media3.exoplayer.offline.SegmentDownloader$Segment r1 = r1.createSegment(r2, r3, r4, r6)
            r10.add(r1)
            goto L5c
        L5a:
            r19 = r6
        L5c:
            androidx.media3.exoplayer.dash.manifest.RangedUri r20 = r12.getIndexUri()
            if (r20 == 0) goto L72
            r1 = r31
            r2 = r12
            r3 = r19
            r4 = r34
            r6 = r20
            androidx.media3.exoplayer.offline.SegmentDownloader$Segment r1 = r1.createSegment(r2, r3, r4, r6)
            r10.add(r1)
        L72:
            long r21 = r0.getFirstSegmentNum()
            long r1 = r21 + r16
            r23 = 1
            long r25 = r1 - r23
            r1 = r21
            r4 = r1
        L7f:
            int r1 = (r4 > r25 ? 1 : (r4 == r25 ? 0 : -1))
            if (r1 > 0) goto La1
        L84:
            long r1 = r0.getTimeUs(r4)
            long r27 = r34 + r1
            androidx.media3.exoplayer.dash.manifest.RangedUri r6 = r0.getSegmentUrl(r4)
            r1 = r31
            r2 = r12
            r3 = r19
            r29 = r4
            r4 = r27
            androidx.media3.exoplayer.offline.SegmentDownloader$Segment r1 = r1.createSegment(r2, r3, r4, r6)
            r10.add(r1)
            long r4 = r29 + r23
            goto L7f
        La1:
            r29 = r4
            goto Lc2
        La4:
            androidx.media3.exoplayer.offline.DownloadException r1 = new androidx.media3.exoplayer.offline.DownloadException
            java.lang.String r2 = "Unbounded segment index"
            r1.<init>(r2)
            throw r1
        Lac:
            r14 = r36
            androidx.media3.exoplayer.offline.DownloadException r1 = new androidx.media3.exoplayer.offline.DownloadException     // Catch: java.io.IOException -> Lb6
            java.lang.String r2 = "Missing segment index"
            r1.<init>(r2)     // Catch: java.io.IOException -> Lb6
            throw r1     // Catch: java.io.IOException -> Lb6
        Lb6:
            r0 = move-exception
            goto Lbf
        Lb8:
            r0 = move-exception
            goto Lbd
        Lba:
            r0 = move-exception
            r13 = r32
        Lbd:
            r14 = r36
        Lbf:
            if (r9 == 0) goto Lc6
        Lc2:
            int r11 = r11 + 1
            goto La
        Lc6:
            throw r0
        Lc7:
            r13 = r32
            r14 = r36
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.offline.DashDownloader.addSegmentsForAdaptationSet(androidx.media3.datasource.DataSource, androidx.media3.exoplayer.dash.manifest.AdaptationSet, long, long, boolean, java.util.ArrayList):void");
    }

    private SegmentDownloader.Segment createSegment(Representation representation, String baseUrl, long startTimeUs, RangedUri rangedUri) {
        DataSpec dataSpec = DashUtil.buildDataSpec(representation, baseUrl, rangedUri, 0, ImmutableMap.of());
        return new SegmentDownloader.Segment(startTimeUs, dataSpec);
    }

    private DashSegmentIndex getSegmentIndex(final DataSource dataSource, final int trackType, final Representation representation, boolean removing) throws IOException, InterruptedException {
        DashSegmentIndex index = representation.getIndex();
        if (index != null) {
            return index;
        }
        RunnableFutureTask<ChunkIndex, IOException> runnable = new RunnableFutureTask<ChunkIndex, IOException>() { // from class: androidx.media3.exoplayer.dash.offline.DashDownloader.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.common.util.RunnableFutureTask
            public ChunkIndex doWork() throws IOException {
                return DashUtil.loadChunkIndex(dataSource, trackType, representation);
            }
        };
        ChunkIndex seekMap = (ChunkIndex) execute(runnable, removing);
        if (seekMap == null) {
            return null;
        }
        return new DashWrappingSegmentIndex(seekMap, representation.presentationTimeOffsetUs);
    }
}
