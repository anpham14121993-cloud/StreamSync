package androidx.media3.database;

import android.database.SQLException;
import java.io.IOException;

/* loaded from: classes24.dex */
public final class DatabaseIOException extends IOException {
    public DatabaseIOException(SQLException cause) {
        super(cause);
    }

    public DatabaseIOException(SQLException cause, String message) {
        super(message, cause);
    }
}
