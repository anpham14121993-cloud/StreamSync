package androidx.media3.datasource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/* loaded from: classes24.dex */
public final class DataSourceBitmapLoader implements BitmapLoader {
    public static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new Supplier() { // from class: androidx.media3.datasource.DataSourceBitmapLoader$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Supplier
        public final Object get() {
            ListeningExecutorService listeningDecorator;
            listeningDecorator = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
            return listeningDecorator;
        }
    });
    private final DataSource.Factory dataSourceFactory;
    private final ListeningExecutorService listeningExecutorService;
    private final BitmapFactory.Options options;

    public DataSourceBitmapLoader(Context context) {
        this((ListeningExecutorService) Assertions.checkStateNotNull(DEFAULT_EXECUTOR_SERVICE.get()), new DefaultDataSource.Factory(context));
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService, DataSource.Factory dataSourceFactory) {
        this(listeningExecutorService, dataSourceFactory, null);
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService, DataSource.Factory dataSourceFactory, BitmapFactory.Options options) {
        this.listeningExecutorService = listeningExecutorService;
        this.dataSourceFactory = dataSourceFactory;
        this.options = options;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String mimeType) {
        return Util.isBitmapFactorySupportedMimeType(mimeType);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> decodeBitmap(final byte[] data) {
        return this.listeningExecutorService.submit(new Callable() { // from class: androidx.media3.datasource.DataSourceBitmapLoader$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DataSourceBitmapLoader.this.m6039xcc09b2d8(data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$decodeBitmap$1$androidx-media3-datasource-DataSourceBitmapLoader, reason: not valid java name */
    public /* synthetic */ Bitmap m6039xcc09b2d8(byte[] data) throws Exception {
        return BitmapUtil.decode(data, data.length, this.options);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> loadBitmap(final Uri uri) {
        return this.listeningExecutorService.submit(new Callable() { // from class: androidx.media3.datasource.DataSourceBitmapLoader$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DataSourceBitmapLoader.this.m6040x731908d1(uri);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadBitmap$2$androidx-media3-datasource-DataSourceBitmapLoader, reason: not valid java name */
    public /* synthetic */ Bitmap m6040x731908d1(Uri uri) throws Exception {
        return load(this.dataSourceFactory.createDataSource(), uri, this.options);
    }

    private static Bitmap load(DataSource dataSource, Uri uri, BitmapFactory.Options options) throws IOException {
        try {
            DataSpec dataSpec = new DataSpec(uri);
            dataSource.open(dataSpec);
            byte[] readData = DataSourceUtil.readToEnd(dataSource);
            return BitmapUtil.decode(readData, readData.length, options);
        } finally {
            dataSource.close();
        }
    }
}
