package com.streamsync.app.ui.player;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.media3.common.MimeTypes;
import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.cache.CacheDataSink;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import com.streamsync.app.settings.BufferCeiling;
import java.io.File;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: VodDiskCache.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u001e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J0\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/streamsync/app/ui/player/VodDiskCache;", "", "()V", "DIR_NAME", "", "FRAGMENT_BYTES", "", "FREE_SPACE_RESERVE_BYTES", "MAX_SIZE_MB", "", "MIN_SIZE_MB", "cache", "Landroidx/media3/datasource/cache/SimpleCache;", "clear", "", "context", "Landroid/content/Context;", "instance", "maxBytes", "isProgressive", "", "mimeType", "resolveMaxBytes", "freeSpaceBytes", "enabled", "manualSizeMb", "sizeBytes", "wrap", "Landroidx/media3/datasource/DataSource$Factory;", "upstream", "SwitchableFactory", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class VodDiskCache {
    private static final String DIR_NAME = "stream_cache";
    private static final long FRAGMENT_BYTES = 2097152;
    private static final long FREE_SPACE_RESERVE_BYTES = 1073741824;
    public static final int MAX_SIZE_MB = 8192;
    public static final int MIN_SIZE_MB = 256;
    private static volatile SimpleCache cache;
    public static final VodDiskCache INSTANCE = new VodDiskCache();
    public static final int $stable = 8;

    private VodDiskCache() {
    }

    private final SimpleCache instance(Context context, long maxBytes) {
        SimpleCache it;
        Object m6717constructorimpl;
        SimpleCache it2 = cache;
        if (it2 != null) {
            return it2;
        }
        synchronized (this) {
            it = cache;
            if (it == null) {
                VodDiskCache vodDiskCache = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    File dir = new File(context.getApplicationContext().getCacheDir(), DIR_NAME);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    m6717constructorimpl = Result.m6717constructorimpl(new SimpleCache(dir, new LeastRecentlyUsedCacheEvictor(maxBytes), new StandaloneDatabaseProvider(context.getApplicationContext())));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                    m6717constructorimpl = null;
                }
                it = (SimpleCache) m6717constructorimpl;
                if (it != null) {
                    cache = it;
                } else {
                    it = null;
                }
            }
        }
        return it;
    }

    public final long resolveMaxBytes(long freeSpaceBytes, boolean enabled, int manualSizeMb) {
        long j;
        if (!enabled || freeSpaceBytes <= 0) {
            return 0L;
        }
        if (freeSpaceBytes > FREE_SPACE_RESERVE_BYTES) {
            j = freeSpaceBytes - FREE_SPACE_RESERVE_BYTES;
        } else {
            j = (8 * freeSpaceBytes) / 10;
        }
        long ceiling = RangesKt.coerceIn(j, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, 8589934592L);
        if (ceiling < 268435456) {
            return 0L;
        }
        return RangesKt.coerceIn(manualSizeMb, 256, 8192) * 1024 * RangesKt.coerceAtMost(1024L, ceiling);
    }

    public final DataSource.Factory wrap(Context context, DataSource.Factory upstream, String mimeType, boolean enabled, int manualSizeMb) {
        Object m6717constructorimpl;
        SimpleCache simple;
        Object m6717constructorimpl2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        if (!enabled || !isProgressive(mimeType)) {
            return upstream;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            VodDiskCache vodDiskCache = this;
            m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(context.getApplicationContext().getCacheDir().getUsableSpace()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = 0L;
        }
        long maxBytes = resolveMaxBytes(((Number) m6717constructorimpl).longValue(), enabled, manualSizeMb);
        if (maxBytes <= 0 || (simple = instance(context, maxBytes)) == null) {
            return upstream;
        }
        CacheDataSink.Factory sink = new CacheDataSink.Factory().setCache(simple).setFragmentSize(2097152L);
        Intrinsics.checkNotNullExpressionValue(sink, "setFragmentSize(...)");
        try {
            Result.Companion companion3 = Result.INSTANCE;
            VodDiskCache vodDiskCache2 = this;
            m6717constructorimpl2 = Result.m6717constructorimpl(new CacheDataSource.Factory().setCache(simple).setCacheWriteDataSinkFactory(sink).setUpstreamDataSourceFactory(upstream).setFlags(2));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            m6717constructorimpl2 = Result.m6717constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl2)) {
            m6717constructorimpl2 = upstream;
        }
        return (DataSource.Factory) m6717constructorimpl2;
    }

    public final boolean isProgressive(String mimeType) {
        String value;
        if (mimeType != null) {
            String lowerCase = mimeType.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null && (value = StringsKt.trim((CharSequence) lowerCase).toString()) != null) {
                if ((value.length() == 0) || StringsKt.contains$default((CharSequence) value, (CharSequence) "mpegurl", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) value, (CharSequence) "dash+xml", false, 2, (Object) null)) {
                    return false;
                }
                return StringsKt.contains$default((CharSequence) value, (CharSequence) "mp2t", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) value, (CharSequence) "matroska", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) value, (CharSequence) "mp4", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) value, (CharSequence) "quicktime", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) value, (CharSequence) "webm", false, 2, (Object) null);
            }
        }
        return false;
    }

    /* compiled from: VodDiskCache.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/ui/player/VodDiskCache$SwitchableFactory;", "Landroidx/media3/datasource/DataSource$Factory;", "context", "Landroid/content/Context;", "upstream", "enabled", "", "manualSizeMb", "", "(Landroid/content/Context;Landroidx/media3/datasource/DataSource$Factory;ZI)V", "progressive", "getProgressive", "()Z", "setProgressive", "(Z)V", "createDataSource", "Landroidx/media3/datasource/DataSource;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final class SwitchableFactory implements DataSource.Factory {
        public static final int $stable = 8;
        private final Context context;
        private final boolean enabled;
        private final int manualSizeMb;
        private volatile boolean progressive;
        private final DataSource.Factory upstream;

        public SwitchableFactory(Context context, DataSource.Factory upstream, boolean enabled, int manualSizeMb) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(upstream, "upstream");
            this.context = context;
            this.upstream = upstream;
            this.enabled = enabled;
            this.manualSizeMb = manualSizeMb;
        }

        public final boolean getProgressive() {
            return this.progressive;
        }

        public final void setProgressive(boolean z) {
            this.progressive = z;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public DataSource createDataSource() {
            DataSource createDataSource = VodDiskCache.INSTANCE.wrap(this.context, this.upstream, this.progressive ? MimeTypes.VIDEO_MP4 : null, this.enabled, this.manualSizeMb).createDataSource();
            Intrinsics.checkNotNullExpressionValue(createDataSource, "createDataSource(...)");
            return createDataSource;
        }
    }

    public final void clear(Context context) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (this) {
            VodDiskCache vodDiskCache = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                SimpleCache simpleCache = cache;
                if (simpleCache != null) {
                    simpleCache.release();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                Result.m6717constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            cache = null;
            Unit unit2 = Unit.INSTANCE;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            VodDiskCache vodDiskCache2 = this;
            File dir = new File(context.getApplicationContext().getCacheDir(), DIR_NAME);
            Result.m6717constructorimpl(Boolean.valueOf(FilesKt.deleteRecursively(dir)));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th2));
        }
    }

    public final long sizeBytes(Context context) {
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        File dir = new File(context.getApplicationContext().getCacheDir(), DIR_NAME);
        if (!dir.isDirectory()) {
            return 0L;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            VodDiskCache vodDiskCache = this;
            long j = 0;
            for (File it : SequencesKt.filter(FilesKt.walkTopDown(dir), new Function1<File, Boolean>() { // from class: com.streamsync.app.ui.player.VodDiskCache$sizeBytes$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(File it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    return Boolean.valueOf(it2.isFile());
                }
            })) {
                j += it.length();
            }
            m6717constructorimpl = Result.m6717constructorimpl(Long.valueOf(j));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = 0L;
        }
        return ((Number) m6717constructorimpl).longValue();
    }
}
