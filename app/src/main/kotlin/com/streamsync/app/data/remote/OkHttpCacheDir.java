package com.streamsync.app.data.remote;

import android.content.Context;
import com.streamsync.app.settings.BufferCeiling;
import java.io.File;
import kotlin.Metadata;

/* compiled from: OkHttpCacheDir.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\t"}, d2 = {"Lcom/streamsync/app/data/remote/OkHttpCacheDir;", "", "()V", "addon", "Ljava/io/File;", "context", "Landroid/content/Context;", "assets", "contextOrThrow", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class OkHttpCacheDir {
    public static final int $stable = 0;
    public static final OkHttpCacheDir INSTANCE = new OkHttpCacheDir();

    private OkHttpCacheDir() {
    }

    public final File addon(Context context) {
        return new File(contextOrThrow(context), "addon-http");
    }

    public final File assets(Context context) {
        return new File(contextOrThrow(context), "asset-http");
    }

    private final File contextOrThrow(Context context) {
        Context appContextOrNull = context == null ? RetrofitClient.INSTANCE.getAppContextOrNull() : context;
        File cacheDir = appContextOrNull != null ? appContextOrNull.getCacheDir() : null;
        if (cacheDir == null) {
            throw new IllegalStateException("Chua khoi dong Application nen chua co cacheDir".toString());
        }
        return cacheDir;
    }
}
