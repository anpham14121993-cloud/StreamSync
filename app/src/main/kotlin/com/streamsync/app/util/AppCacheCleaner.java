package com.streamsync.app.util;

import android.content.Context;
import coil.Coil;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import com.streamsync.app.data.local.CatalogDiskCache;
import com.streamsync.app.data.local.ContinueWatchingDiskCache;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.local.MediaItemDiskCache;
import com.streamsync.app.data.remote.OkHttpCacheDir;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.player.VodDiskCache;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.SequencesKt;

/* compiled from: AppCacheCleaner.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0017\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\"\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\u00140\u00132\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0018"}, d2 = {"Lcom/streamsync/app/util/AppCacheCleaner;", "", "()V", "clear", "Lcom/streamsync/app/util/AppCacheCleaner$CacheReport;", "context", "Landroid/content/Context;", "clearCoilCaches", "", "app", "clearVodCache", "", "coilCacheBytes", "(Landroid/content/Context;)Ljava/lang/Long;", "dirSize", "dir", "Ljava/io/File;", "inspect", "targets", "", "Lkotlin/Pair;", "", "vodCacheBytes", "CacheReport", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class AppCacheCleaner {
    public static final int $stable = 0;
    public static final AppCacheCleaner INSTANCE = new AppCacheCleaner();

    private AppCacheCleaner() {
    }

    /* compiled from: AppCacheCleaner.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u001b\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R#\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/streamsync/app/util/AppCacheCleaner$CacheReport;", "", "entries", "", "Lkotlin/Pair;", "", "", "totalBytes", "error", "(Ljava/util/List;JLjava/lang/String;)V", "getEntries", "()Ljava/util/List;", "getError", "()Ljava/lang/String;", "getTotalBytes", "()J", "totalMb", "getTotalMb", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class CacheReport {
        public static final int $stable = 8;
        private final List<Pair<String, Long>> entries;
        private final String error;
        private final long totalBytes;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CacheReport copy$default(CacheReport cacheReport, List list, long j, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = cacheReport.entries;
            }
            if ((i & 2) != 0) {
                j = cacheReport.totalBytes;
            }
            if ((i & 4) != 0) {
                str = cacheReport.error;
            }
            return cacheReport.copy(list, j, str);
        }

        public final List<Pair<String, Long>> component1() {
            return this.entries;
        }

        /* renamed from: component2, reason: from getter */
        public final long getTotalBytes() {
            return this.totalBytes;
        }

        /* renamed from: component3, reason: from getter */
        public final String getError() {
            return this.error;
        }

        public final CacheReport copy(List<Pair<String, Long>> entries, long totalBytes, String error) {
            Intrinsics.checkNotNullParameter(entries, "entries");
            return new CacheReport(entries, totalBytes, error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CacheReport)) {
                return false;
            }
            CacheReport cacheReport = (CacheReport) other;
            return Intrinsics.areEqual(this.entries, cacheReport.entries) && this.totalBytes == cacheReport.totalBytes && Intrinsics.areEqual(this.error, cacheReport.error);
        }

        public int hashCode() {
            return (((this.entries.hashCode() * 31) + Long.hashCode(this.totalBytes)) * 31) + (this.error == null ? 0 : this.error.hashCode());
        }

        public String toString() {
            return "CacheReport(entries=" + this.entries + ", totalBytes=" + this.totalBytes + ", error=" + this.error + ")";
        }

        public CacheReport(List<Pair<String, Long>> entries, long totalBytes, String error) {
            Intrinsics.checkNotNullParameter(entries, "entries");
            this.entries = entries;
            this.totalBytes = totalBytes;
            this.error = error;
        }

        public /* synthetic */ CacheReport(List list, long j, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, j, (i & 4) != 0 ? null : str);
        }

        public final List<Pair<String, Long>> getEntries() {
            return this.entries;
        }

        public final long getTotalBytes() {
            return this.totalBytes;
        }

        public final String getError() {
            return this.error;
        }

        public final String getTotalMb() {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((((float) this.totalBytes) / 1024.0f) / 1024.0f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
    }

    private final List<Pair<String, File>> targets(Context context) {
        Context app = context.getApplicationContext();
        File cache = app.getCacheDir();
        CatalogDiskCache catalogDiskCache = CatalogDiskCache.INSTANCE;
        Intrinsics.checkNotNull(app);
        List result = CollectionsKt.mutableListOf(TuplesKt.to("Danh mục đã tải", catalogDiskCache.file(app)), TuplesKt.to("Meta phim đã tải", MediaItemDiskCache.INSTANCE.file(app)), TuplesKt.to("Tiếp tục xem", ContinueWatchingDiskCache.INSTANCE.file(app)), TuplesKt.to("File tạm khác", new File(cache, "tmp")), TuplesKt.to("Dữ liệu addon tải về", OkHttpCacheDir.INSTANCE.addon(app)));
        return result;
    }

    public final long vodCacheBytes(Context context) {
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            AppCacheCleaner appCacheCleaner = this;
            VodDiskCache vodDiskCache = VodDiskCache.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(vodDiskCache.sizeBytes(applicationContext)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = 0L;
        }
        return ((Number) m6717constructorimpl).longValue();
    }

    public final void clearVodCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            AppCacheCleaner appCacheCleaner = this;
            VodDiskCache vodDiskCache = VodDiskCache.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            vodDiskCache.clear(applicationContext);
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final CacheReport inspect(Context context) {
        Pair pair;
        Intrinsics.checkNotNullParameter(context, "context");
        Context app = context.getApplicationContext();
        try {
            Intrinsics.checkNotNull(app);
            Iterable $this$map$iv = targets(app);
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Pair pair2 = (Pair) item$iv$iv;
                String label = (String) pair2.component1();
                File dir = (File) pair2.component2();
                destination$iv$iv.add(TuplesKt.to(label, Long.valueOf(INSTANCE.dirSize(dir))));
            }
            Collection collection = (List) destination$iv$iv;
            Long coilCacheBytes = coilCacheBytes(app);
            if (coilCacheBytes != null) {
                long it = coilCacheBytes.longValue();
                pair = TuplesKt.to("Ảnh poster đã tải", Long.valueOf(it));
            } else {
                pair = null;
            }
            Iterable $this$filter$iv = CollectionsKt.plus(collection, (Iterable) CollectionsKt.listOfNotNull(pair));
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                Pair it2 = (Pair) element$iv$iv;
                if (((Number) it2.getSecond()).longValue() > 0) {
                    destination$iv$iv2.add(element$iv$iv);
                }
            }
            List<Pair> entries = (List) destination$iv$iv2;
            long j = 0;
            for (Pair it3 : entries) {
                j += ((Number) it3.getSecond()).longValue();
            }
            return new CacheReport(entries, j, null, 4, null);
        } catch (Exception e) {
            return new CacheReport(CollectionsKt.emptyList(), 0L, e.getMessage());
        }
    }

    public final CacheReport clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context app = context.getApplicationContext();
        try {
            Intrinsics.checkNotNull(app);
            CacheReport before = inspect(app);
            long freed = 0;
            Iterable $this$forEach$iv = targets(app);
            for (Object element$iv : $this$forEach$iv) {
                File dir = (File) ((Pair) element$iv).component2();
                if (dir.exists()) {
                    long size = INSTANCE.dirSize(dir);
                    if (FilesKt.deleteRecursively(dir)) {
                        freed += size;
                    }
                }
            }
            MediaItemCache.INSTANCE.clear();
            return new CacheReport(before.getEntries(), freed + clearCoilCaches(app), null, 4, null);
        } catch (Exception e) {
            return new CacheReport(CollectionsKt.emptyList(), 0L, e.getMessage());
        }
    }

    private final Long coilCacheBytes(Context app) {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            AppCacheCleaner appCacheCleaner = this;
            DiskCache diskCache = Coil.imageLoader(app).getDiskCache();
            m6717constructorimpl = Result.m6717constructorimpl(diskCache != null ? Long.valueOf(diskCache.getSize()) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        return (Long) (Result.m6723isFailureimpl(m6717constructorimpl) ? null : m6717constructorimpl);
    }

    private final long clearCoilCaches(Context app) {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            AppCacheCleaner appCacheCleaner = this;
            ImageLoader loader = Coil.imageLoader(app);
            DiskCache disk = loader.getDiskCache();
            long freed = disk != null ? disk.getSize() : 0L;
            MemoryCache memoryCache = loader.getMemoryCache();
            if (memoryCache != null) {
                memoryCache.clear();
            }
            if (disk != null) {
                disk.clear();
            }
            m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(freed));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = 0L;
        }
        return ((Number) m6717constructorimpl).longValue();
    }

    private final long dirSize(File dir) {
        Object m6717constructorimpl;
        if (!dir.exists()) {
            return 0L;
        }
        if (dir.isFile()) {
            return dir.length();
        }
        long j = 0;
        for (File file : SequencesKt.filter(FilesKt.walkBottomUp(dir), new Function1<File, Boolean>() { // from class: com.streamsync.app.util.AppCacheCleaner$dirSize$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(File it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.isFile());
            }
        })) {
            AppCacheCleaner appCacheCleaner = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(file.length()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                m6717constructorimpl = 0L;
            }
            j += ((Number) m6717constructorimpl).longValue();
        }
        return j;
    }
}
