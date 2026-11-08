package com.streamsync.app.ui.player;

import android.content.Context;
import androidx.media3.datasource.DataSource;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.ui.player.OkHttpVideoDataSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlaybackDataSourceFactory.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n¨\u0006\f"}, d2 = {"Lcom/streamsync/app/ui/player/PlaybackDataSourceFactory;", "", "()V", "createFactory", "Landroidx/media3/datasource/DataSource$Factory;", "context", "Landroid/content/Context;", "manifest", "", "parallelConnections", "", "chunkSizeMb", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlaybackDataSourceFactory {
    public static final int $stable = 0;
    public static final PlaybackDataSourceFactory INSTANCE = new PlaybackDataSourceFactory();

    private PlaybackDataSourceFactory() {
    }

    public static /* synthetic */ DataSource.Factory createFactory$default(PlaybackDataSourceFactory playbackDataSourceFactory, Context context, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i = 1;
        }
        if ((i3 & 8) != 0) {
            i2 = 1;
        }
        return playbackDataSourceFactory.createFactory(context, z, i, i2);
    }

    public final DataSource.Factory createFactory(final Context context, final boolean manifest, final int parallelConnections, final int chunkSizeMb) {
        Intrinsics.checkNotNullParameter(context, "context");
        final PlaybackSettingsStore store = new PlaybackSettingsStore(context);
        return new DataSource.Factory() { // from class: com.streamsync.app.ui.player.PlaybackDataSourceFactory$createFactory$1
            @Override // androidx.media3.datasource.DataSource.Factory
            public DataSource createDataSource() {
                int i;
                int i2;
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                boolean isHttp2Enabled = store.isHttp2Enabled();
                if (store.isCustomBufferEnabled()) {
                    i = store.getParallelConnections();
                } else {
                    i = parallelConnections;
                }
                if (store.isCustomBufferEnabled()) {
                    i2 = store.getChunkSizeMb();
                } else {
                    i2 = chunkSizeMb;
                }
                return new OkHttpVideoDataSource(applicationContext, new OkHttpVideoDataSource.BufferNetworkOptions(isHttp2Enabled, i, i2, manifest ? store.getChunkCount() : 1));
            }
        };
    }
}
