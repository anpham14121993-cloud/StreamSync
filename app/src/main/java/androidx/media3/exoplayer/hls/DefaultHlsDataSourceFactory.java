package androidx.media3.exoplayer.hls;

import androidx.media3.datasource.DataSource;

/* loaded from: classes12.dex */
public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final DataSource.Factory dataSourceFactory;

    public DefaultHlsDataSourceFactory(DataSource.Factory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override // androidx.media3.exoplayer.hls.HlsDataSourceFactory
    public DataSource createDataSource(int dataType) {
        return this.dataSourceFactory.createDataSource();
    }
}
