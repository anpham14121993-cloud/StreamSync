package androidx.media3.common.util;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes24.dex */
public interface BitmapLoader {
    ListenableFuture<Bitmap> decodeBitmap(byte[] bArr);

    ListenableFuture<Bitmap> loadBitmap(Uri uri);

    boolean supportsMimeType(String str);

    default ListenableFuture<Bitmap> loadBitmapFromMetadata(MediaMetadata metadata) {
        if (metadata.artworkData != null) {
            ListenableFuture<Bitmap> future = decodeBitmap(metadata.artworkData);
            return future;
        }
        if (metadata.artworkUri != null) {
            ListenableFuture<Bitmap> future2 = loadBitmap(metadata.artworkUri);
            return future2;
        }
        return null;
    }
}
