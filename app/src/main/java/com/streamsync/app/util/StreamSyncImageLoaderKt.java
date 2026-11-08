package com.streamsync.app.util;

import android.content.Context;
import coil.Coil;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import coil.request.CachePolicy;
import coil.request.ImageRequest;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/* compiled from: StreamSyncImageLoader.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\t"}, d2 = {"buildAppImageLoader", "Lcoil/ImageLoader;", "context", "Landroid/content/Context;", "prefetchPosters", "", "items", "", "Lcom/streamsync/app/data/model/MediaItem;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class StreamSyncImageLoaderKt {
    public static final ImageLoader buildAppImageLoader(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ImageLoader.Builder(context).callFactory(new OkHttpClient.Builder().cache(new Cache(new File(context.getCacheDir(), "image_http_cache"), ImageCacheBudget.INSTANCE.httpCacheBytes(context))).build()).memoryCache(new Function0<MemoryCache>() { // from class: com.streamsync.app.util.StreamSyncImageLoaderKt$buildAppImageLoader$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MemoryCache invoke() {
                return new MemoryCache.Builder(context).maxSizeBytes((int) RangesKt.coerceAtMost(ImageCacheBudget.INSTANCE.memoryCacheBytes(context), 2147483647L)).strongReferencesEnabled(!ImageCacheBudget.INSTANCE.useWeakOnly(context)).build();
            }
        }).diskCache(new Function0<DiskCache>() { // from class: com.streamsync.app.util.StreamSyncImageLoaderKt$buildAppImageLoader$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiskCache invoke() {
                return new DiskCache.Builder().directory(new File(context.getCacheDir(), "image_cache")).maxSizeBytes(ImageCacheBudget.INSTANCE.diskCacheBytes(context)).build();
            }
        }).allowHardware(true).crossfade(false).build();
    }

    public static final void prefetchPosters(Context context, List<MediaItem> items) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        if (items.isEmpty()) {
            return;
        }
        ImageLoader loader = Coil.imageLoader(context);
        Context app = context.getApplicationContext();
        List<MediaItem> $this$forEach$iv = items;
        for (Object element$iv : $this$forEach$iv) {
            MediaItem item = (MediaItem) element$iv;
            String url = item.getPosterUrl();
            String str = url;
            if (!(str == null || StringsKt.isBlank(str))) {
                Intrinsics.checkNotNull(app);
                loader.enqueue(new ImageRequest.Builder(app).data(url).memoryCachePolicy(CachePolicy.ENABLED).diskCachePolicy(CachePolicy.ENABLED).build());
            }
        }
    }
}
