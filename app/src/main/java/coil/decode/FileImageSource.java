package coil.decode;

import androidx.media3.extractor.text.ttml.TtmlNode;
import coil.decode.ImageSource;
import coil.util.Utils;
import com.streamsync.app.settings.BufferCeiling;
import java.io.Closeable;
import kotlin.Metadata;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0018H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcoil/decode/FileImageSource;", "Lcoil/decode/ImageSource;", "file", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "diskCacheKey", "", "closeable", "Ljava/io/Closeable;", TtmlNode.TAG_METADATA, "Lcoil/decode/ImageSource$Metadata;", "(Lokio/Path;Lokio/FileSystem;Ljava/lang/String;Ljava/io/Closeable;Lcoil/decode/ImageSource$Metadata;)V", "getDiskCacheKey$coil_base_release", "()Ljava/lang/String;", "getFile$coil_base_release", "()Lokio/Path;", "getFileSystem", "()Lokio/FileSystem;", "isClosed", "", "getMetadata", "()Lcoil/decode/ImageSource$Metadata;", "source", "Lokio/BufferedSource;", "assertNotClosed", "", "close", "fileOrNull", "sourceOrNull", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class FileImageSource extends ImageSource {
    private final Closeable closeable;
    private final String diskCacheKey;
    private final Path file;
    private final FileSystem fileSystem;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private BufferedSource source;

    /* renamed from: getFile$coil_base_release, reason: from getter */
    public final Path getFile() {
        return this.file;
    }

    @Override // coil.decode.ImageSource
    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    /* renamed from: getDiskCacheKey$coil_base_release, reason: from getter */
    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    @Override // coil.decode.ImageSource
    public ImageSource.Metadata getMetadata() {
        return this.metadata;
    }

    public FileImageSource(Path file, FileSystem fileSystem, String diskCacheKey, Closeable closeable, ImageSource.Metadata metadata) {
        super(null);
        this.file = file;
        this.fileSystem = fileSystem;
        this.diskCacheKey = diskCacheKey;
        this.closeable = closeable;
        this.metadata = metadata;
    }

    @Override // coil.decode.ImageSource
    public synchronized BufferedSource source() {
        assertNotClosed();
        BufferedSource it = this.source;
        if (it != null) {
            return it;
        }
        BufferedSource it2 = Okio.buffer(getFileSystem().source(this.file));
        this.source = it2;
        return it2;
    }

    @Override // coil.decode.ImageSource
    public synchronized BufferedSource sourceOrNull() {
        assertNotClosed();
        return this.source;
    }

    @Override // coil.decode.ImageSource
    public synchronized Path file() {
        assertNotClosed();
        return this.file;
    }

    @Override // coil.decode.ImageSource
    public Path fileOrNull() {
        return file();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.isClosed = true;
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            Utils.closeQuietly(bufferedSource);
        }
        Closeable closeable = this.closeable;
        if (closeable != null) {
            Utils.closeQuietly(closeable);
        }
    }

    private final void assertNotClosed() {
        if (!(!this.isClosed)) {
            throw new IllegalStateException("closed".toString());
        }
    }
}
