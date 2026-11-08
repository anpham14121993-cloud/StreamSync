package androidx.media3.database;

import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes24.dex */
public interface DatabaseProvider {
    public static final String TABLE_PREFIX = "ExoPlayer";

    SQLiteDatabase getReadableDatabase();

    SQLiteDatabase getWritableDatabase();
}
