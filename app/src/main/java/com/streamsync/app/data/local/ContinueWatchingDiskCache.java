package com.streamsync.app.data.local;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContinueWatchingDiskCache.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/streamsync/app/data/local/ContinueWatchingDiskCache;", "", "()V", "FILE", "", "gson", "Lcom/google/gson/Gson;", "clear", "", "context", "Landroid/content/Context;", "file", "Ljava/io/File;", "load", "", "Lcom/streamsync/app/data/model/MediaItem;", "save", "items", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class ContinueWatchingDiskCache {
    private static final String FILE = "continue_watching_cache.json";
    public static final ContinueWatchingDiskCache INSTANCE = new ContinueWatchingDiskCache();
    private static final Gson gson = new Gson();
    public static final int $stable = 8;

    private ContinueWatchingDiskCache() {
    }

    public final File file(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new File(context.getApplicationContext().getFilesDir(), FILE);
    }

    public final void save(Context context, List<MediaItem> items) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        try {
            Result.Companion companion = Result.INSTANCE;
            ContinueWatchingDiskCache $this$save_u24lambda_u240 = this;
            File file = $this$save_u24lambda_u240.file(context);
            String json = gson.toJson(items);
            Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
            FilesKt.writeText$default(file, json, null, 2, null);
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final List<MediaItem> load(Context context) {
        Object m6717constructorimpl;
        File f;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            ContinueWatchingDiskCache $this$load_u24lambda_u241 = this;
            f = $this$load_u24lambda_u241.file(context);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (f.exists() && f.length() != 0) {
            Type type = new TypeToken<List<? extends MediaItem>>() { // from class: com.streamsync.app.data.local.ContinueWatchingDiskCache$load$1$type$1
            }.getType();
            List list = (List) gson.fromJson(FilesKt.readText$default(f, null, 1, null), type);
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            m6717constructorimpl = Result.m6717constructorimpl(list);
            List emptyList = CollectionsKt.emptyList();
            if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                m6717constructorimpl = emptyList;
            }
            return (List) m6717constructorimpl;
        }
        return CollectionsKt.emptyList();
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            ContinueWatchingDiskCache $this$clear_u24lambda_u242 = this;
            Result.m6717constructorimpl(Boolean.valueOf($this$clear_u24lambda_u242.file(context).delete()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
    }
}
