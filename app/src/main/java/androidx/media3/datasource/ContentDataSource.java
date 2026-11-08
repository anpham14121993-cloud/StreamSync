package androidx.media3.datasource;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* loaded from: classes24.dex */
public final class ContentDataSource extends BaseDataSource {
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private FileInputStream inputStream;
    private boolean opened;
    private final ContentResolver resolver;
    private Uri uri;

    /* loaded from: classes24.dex */
    public static class ContentDataSourceException extends DataSourceException {
        @Deprecated
        public ContentDataSourceException(IOException cause) {
            this(cause, 2000);
        }

        public ContentDataSourceException(IOException cause, int errorCode) {
            super(cause, errorCode);
        }
    }

    public ContentDataSource(Context context) {
        super(false);
        this.resolver = context.getContentResolver();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws ContentDataSourceException {
        int i;
        int i2;
        AssetFileDescriptor assetFileDescriptor;
        try {
            try {
                Uri uri = dataSpec.uri.normalizeScheme();
                this.uri = uri;
                transferInitializing(dataSpec);
                if ("content".equals(uri.getScheme())) {
                    Bundle providerOptions = new Bundle();
                    providerOptions.putBoolean("android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT", true);
                    assetFileDescriptor = this.resolver.openTypedAssetFileDescriptor(uri, "*/*", providerOptions);
                } else {
                    assetFileDescriptor = this.resolver.openAssetFileDescriptor(uri, "r");
                }
                this.assetFileDescriptor = assetFileDescriptor;
                if (assetFileDescriptor != null) {
                    long assetFileDescriptorLength = assetFileDescriptor.getLength();
                    FileInputStream inputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
                    this.inputStream = inputStream;
                    if (assetFileDescriptorLength != -1 && dataSpec.position > assetFileDescriptorLength) {
                        throw new ContentDataSourceException(null, 2008);
                    }
                    long assetFileDescriptorOffset = assetFileDescriptor.getStartOffset();
                    long skipped = inputStream.skip(dataSpec.position + assetFileDescriptorOffset) - assetFileDescriptorOffset;
                    if (skipped != dataSpec.position) {
                        throw new ContentDataSourceException(null, 2008);
                    }
                    if (assetFileDescriptorLength != -1) {
                        this.bytesRemaining = assetFileDescriptorLength - skipped;
                        if (this.bytesRemaining < 0) {
                            throw new ContentDataSourceException(null, 2008);
                        }
                    } else {
                        FileChannel channel = inputStream.getChannel();
                        long channelSize = channel.size();
                        if (channelSize != 0) {
                            this.bytesRemaining = channelSize - channel.position();
                            if (this.bytesRemaining < 0) {
                                throw new ContentDataSourceException(null, 2008);
                            }
                        } else {
                            this.bytesRemaining = -1L;
                        }
                    }
                    if (dataSpec.length != -1) {
                        this.bytesRemaining = this.bytesRemaining == -1 ? dataSpec.length : Math.min(this.bytesRemaining, dataSpec.length);
                    }
                    this.opened = true;
                    transferStarted(dataSpec);
                    return dataSpec.length != -1 ? dataSpec.length : this.bytesRemaining;
                }
                i = 2000;
                try {
                    throw new ContentDataSourceException(new IOException("Could not open file descriptor for: " + uri), 2000);
                } catch (IOException e) {
                    e = e;
                    if (e instanceof FileNotFoundException) {
                        i2 = PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND;
                    } else {
                        i2 = i;
                    }
                    throw new ContentDataSourceException(e, i2);
                }
            } catch (IOException e2) {
                e = e2;
                i = 2000;
            }
        } catch (ContentDataSourceException e3) {
            throw e3;
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] buffer, int offset, int length) throws ContentDataSourceException {
        if (length == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int bytesToRead = this.bytesRemaining == -1 ? length : (int) Math.min(this.bytesRemaining, length);
            int bytesRead = ((FileInputStream) Util.castNonNull(this.inputStream)).read(buffer, offset, bytesToRead);
            if (bytesRead == -1) {
                return -1;
            }
            if (this.bytesRemaining != -1) {
                this.bytesRemaining -= bytesRead;
            }
            bytesTransferred(bytesRead);
            return bytesRead;
        } catch (IOException e) {
            throw new ContentDataSourceException(e, 2000);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws ContentDataSourceException {
        this.uri = null;
        try {
            try {
                if (this.inputStream != null) {
                    this.inputStream.close();
                }
                this.inputStream = null;
                try {
                    try {
                        if (this.assetFileDescriptor != null) {
                            this.assetFileDescriptor.close();
                        }
                    } catch (IOException e) {
                        throw new ContentDataSourceException(e, 2000);
                    }
                } finally {
                    this.assetFileDescriptor = null;
                    if (this.opened) {
                        this.opened = false;
                        transferEnded();
                    }
                }
            } catch (Throwable th) {
                this.inputStream = null;
                try {
                    try {
                        if (this.assetFileDescriptor != null) {
                            this.assetFileDescriptor.close();
                        }
                        this.assetFileDescriptor = null;
                        if (this.opened) {
                            this.opened = false;
                            transferEnded();
                        }
                        throw th;
                    } finally {
                        this.assetFileDescriptor = null;
                        if (this.opened) {
                            this.opened = false;
                            transferEnded();
                        }
                    }
                } catch (IOException e2) {
                    throw new ContentDataSourceException(e2, 2000);
                }
            }
        } catch (IOException e3) {
            throw new ContentDataSourceException(e3, 2000);
        }
    }
}
