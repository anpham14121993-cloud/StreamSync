package coil.util;

import com.streamsync.app.settings.BufferCeiling;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import okio.FileSystem;
import okio.Path;

/* compiled from: FileSystems.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H\u0000¨\u0006\u0007"}, d2 = {"createFile", "", "Lokio/FileSystem;", "file", "Lokio/Path;", "deleteContents", "directory", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* renamed from: coil.util.-FileSystems, reason: invalid class name */
/* loaded from: classes24.dex */
public final class FileSystems {
    public static final void createFile(FileSystem $this$createFile, Path file) {
        if (!$this$createFile.exists(file)) {
            Utils.closeQuietly($this$createFile.sink(file));
        }
    }

    public static final void deleteContents(FileSystem $this$deleteContents, Path directory) {
        IOException exception = null;
        try {
            List files = $this$deleteContents.list(directory);
            for (Path file : files) {
                try {
                    if ($this$deleteContents.metadata(file).getIsDirectory()) {
                        deleteContents($this$deleteContents, file);
                    }
                    $this$deleteContents.delete(file);
                } catch (IOException e) {
                    if (exception == null) {
                        exception = e;
                    }
                }
            }
            if (exception != null) {
                throw exception;
            }
        } catch (FileNotFoundException e2) {
        }
    }
}
