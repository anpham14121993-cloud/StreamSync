package androidx.media3.datasource;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.List;

/* loaded from: classes24.dex */
public final class RawResourceDataSource extends BaseDataSource {

    @Deprecated
    public static final String RAW_RESOURCE_SCHEME = "rawresource";
    private final Context applicationContext;
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private DataSpec dataSpec;
    private InputStream inputStream;
    private boolean opened;

    /* loaded from: classes24.dex */
    public static class RawResourceDataSourceException extends DataSourceException {
        @Deprecated
        public RawResourceDataSourceException(String message) {
            super(message, null, 2000);
        }

        @Deprecated
        public RawResourceDataSourceException(Throwable cause) {
            super(cause, 2000);
        }

        public RawResourceDataSourceException(String message, Throwable cause, int errorCode) {
            super(message, cause, errorCode);
        }
    }

    @Deprecated
    public static Uri buildRawResourceUri(int rawResourceId) {
        return Uri.parse("rawresource:///" + rawResourceId);
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.applicationContext = context.getApplicationContext();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws RawResourceDataSourceException {
        this.dataSpec = dataSpec;
        transferInitializing(dataSpec);
        this.assetFileDescriptor = openAssetFileDescriptor(this.applicationContext, dataSpec);
        long assetFileDescriptorLength = this.assetFileDescriptor.getLength();
        FileInputStream inputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
        this.inputStream = inputStream;
        if (assetFileDescriptorLength != -1) {
            try {
                if (dataSpec.position > assetFileDescriptorLength) {
                    throw new RawResourceDataSourceException(null, null, 2008);
                }
            } catch (RawResourceDataSourceException e) {
                throw e;
            } catch (IOException e2) {
                throw new RawResourceDataSourceException(null, e2, 2000);
            }
        }
        long assetFileDescriptorOffset = this.assetFileDescriptor.getStartOffset();
        long skipped = inputStream.skip(dataSpec.position + assetFileDescriptorOffset) - assetFileDescriptorOffset;
        if (skipped == dataSpec.position) {
            if (assetFileDescriptorLength != -1) {
                this.bytesRemaining = assetFileDescriptorLength - skipped;
                if (this.bytesRemaining < 0) {
                    throw new DataSourceException(2008);
                }
            } else {
                FileChannel channel = inputStream.getChannel();
                if (channel.size() != 0) {
                    this.bytesRemaining = channel.size() - channel.position();
                    if (this.bytesRemaining < 0) {
                        throw new RawResourceDataSourceException(null, null, 2008);
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
        throw new RawResourceDataSourceException(null, null, 2008);
    }

    private static AssetFileDescriptor openAssetFileDescriptor(Context applicationContext, DataSpec dataSpec) throws RawResourceDataSourceException {
        String packageName;
        Resources resources;
        int resourceId;
        Resources resources2;
        Uri normalizedUri = dataSpec.uri.normalizeScheme();
        if (TextUtils.equals(RAW_RESOURCE_SCHEME, normalizedUri.getScheme())) {
            resources2 = applicationContext.getResources();
            List<String> pathSegments = normalizedUri.getPathSegments();
            if (pathSegments.size() == 1) {
                resourceId = parseResourceId(pathSegments.get(0));
            } else {
                throw new RawResourceDataSourceException("rawresource:// URI must have exactly one path element, found " + pathSegments.size());
            }
        } else if (TextUtils.equals("android.resource", normalizedUri.getScheme())) {
            String path = (String) Assertions.checkNotNull(normalizedUri.getPath());
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            if (TextUtils.isEmpty(normalizedUri.getHost())) {
                packageName = applicationContext.getPackageName();
            } else {
                packageName = normalizedUri.getHost();
            }
            if (packageName.equals(applicationContext.getPackageName())) {
                resources = applicationContext.getResources();
            } else {
                try {
                    resources = applicationContext.getPackageManager().getResourcesForApplication(packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RawResourceDataSourceException("Package in android.resource:// URI not found. Check http://g.co/dev/packagevisibility.", e, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
                }
            }
            if (path.matches("\\d+")) {
                resourceId = parseResourceId(path);
            } else {
                int resourceIdFromName = resources.getIdentifier(packageName + ":" + path, "raw", null);
                if (resourceIdFromName != 0) {
                    resourceId = resourceIdFromName;
                } else {
                    throw new RawResourceDataSourceException("Resource not found.", null, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
                }
            }
            resources2 = resources;
        } else {
            throw new RawResourceDataSourceException("Unsupported URI scheme (" + normalizedUri.getScheme() + "). Only android.resource is supported.", null, 1004);
        }
        try {
            AssetFileDescriptor assetFileDescriptor = resources2.openRawResourceFd(resourceId);
            if (assetFileDescriptor == null) {
                throw new RawResourceDataSourceException("Resource is compressed: " + normalizedUri, null, 2000);
            }
            return assetFileDescriptor;
        } catch (Resources.NotFoundException e2) {
            throw new RawResourceDataSourceException(null, e2, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
        }
    }

    private static int parseResourceId(String resourceId) throws RawResourceDataSourceException {
        try {
            return Integer.parseInt(resourceId);
        } catch (NumberFormatException e) {
            throw new RawResourceDataSourceException("Resource identifier must be an integer.", null, 1004);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] buffer, int offset, int length) throws RawResourceDataSourceException {
        if (length == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int bytesToRead = this.bytesRemaining == -1 ? length : (int) Math.min(this.bytesRemaining, length);
            int bytesRead = ((InputStream) Util.castNonNull(this.inputStream)).read(buffer, offset, bytesToRead);
            if (bytesRead == -1) {
                if (this.bytesRemaining == -1) {
                    return -1;
                }
                throw new RawResourceDataSourceException("End of stream reached having not read sufficient data.", new EOFException(), 2000);
            }
            if (this.bytesRemaining != -1) {
                this.bytesRemaining -= bytesRead;
            }
            bytesTransferred(bytesRead);
            return bytesRead;
        } catch (IOException e) {
            throw new RawResourceDataSourceException(null, e, 2000);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        if (this.dataSpec != null) {
            return this.dataSpec.uri;
        }
        return null;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws RawResourceDataSourceException {
        this.dataSpec = null;
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
                        throw new RawResourceDataSourceException(null, e, 2000);
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
                    throw new RawResourceDataSourceException(null, e2, 2000);
                }
            }
        } catch (IOException e3) {
            throw new RawResourceDataSourceException(null, e3, 2000);
        }
    }
}
