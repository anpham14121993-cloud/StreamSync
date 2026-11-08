package com.streamsync.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* loaded from: classes15.dex */
public final class LibraryDao_Impl implements LibraryDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<LibraryItemEntity> __deletionAdapterOfLibraryItemEntity;
    private final EntityInsertionAdapter<LibraryItemEntity> __insertionAdapterOfLibraryItemEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;

    public LibraryDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfLibraryItemEntity = new EntityInsertionAdapter<LibraryItemEntity>(__db) { // from class: com.streamsync.app.data.local.LibraryDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `library_items` (`mediaId`,`title`,`posterUrl`,`backgroundUrl`,`description`,`type`,`releaseInfo`,`imdbRating`,`genresJson`,`season`,`episode`,`addedAtEpochMs`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(final SupportSQLiteStatement statement, final LibraryItemEntity entity) {
                statement.bindString(1, entity.getMediaId());
                statement.bindString(2, entity.getTitle());
                if (entity.getPosterUrl() == null) {
                    statement.bindNull(3);
                } else {
                    statement.bindString(3, entity.getPosterUrl());
                }
                if (entity.getBackgroundUrl() == null) {
                    statement.bindNull(4);
                } else {
                    statement.bindString(4, entity.getBackgroundUrl());
                }
                if (entity.getDescription() == null) {
                    statement.bindNull(5);
                } else {
                    statement.bindString(5, entity.getDescription());
                }
                statement.bindString(6, entity.getType());
                if (entity.getReleaseInfo() == null) {
                    statement.bindNull(7);
                } else {
                    statement.bindString(7, entity.getReleaseInfo());
                }
                if (entity.getImdbRating() == null) {
                    statement.bindNull(8);
                } else {
                    statement.bindString(8, entity.getImdbRating());
                }
                statement.bindString(9, entity.getGenresJson());
                if (entity.getSeason() == null) {
                    statement.bindNull(10);
                } else {
                    statement.bindLong(10, entity.getSeason().intValue());
                }
                if (entity.getEpisode() == null) {
                    statement.bindNull(11);
                } else {
                    statement.bindLong(11, entity.getEpisode().intValue());
                }
                statement.bindLong(12, entity.getAddedAtEpochMs());
            }
        };
        this.__deletionAdapterOfLibraryItemEntity = new EntityDeletionOrUpdateAdapter<LibraryItemEntity>(__db) { // from class: com.streamsync.app.data.local.LibraryDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `library_items` WHERE `mediaId` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final LibraryItemEntity entity) {
                statement.bindString(1, entity.getMediaId());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) { // from class: com.streamsync.app.data.local.LibraryDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM library_items WHERE mediaId = ?";
            }
        };
    }

    @Override // com.streamsync.app.data.local.LibraryDao
    public Object upsert(final LibraryItemEntity item, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.local.LibraryDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                LibraryDao_Impl.this.__db.beginTransaction();
                try {
                    LibraryDao_Impl.this.__insertionAdapterOfLibraryItemEntity.insert((EntityInsertionAdapter) item);
                    LibraryDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    LibraryDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.local.LibraryDao
    public Object delete(final LibraryItemEntity item, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.local.LibraryDao_Impl.5
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                LibraryDao_Impl.this.__db.beginTransaction();
                try {
                    LibraryDao_Impl.this.__deletionAdapterOfLibraryItemEntity.handle(item);
                    LibraryDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    LibraryDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.local.LibraryDao
    public Object deleteById(final String mediaId, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.local.LibraryDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = LibraryDao_Impl.this.__preparedStmtOfDeleteById.acquire();
                _stmt.bindString(1, mediaId);
                try {
                    LibraryDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        LibraryDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        LibraryDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    LibraryDao_Impl.this.__preparedStmtOfDeleteById.release(_stmt);
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.local.LibraryDao
    public Flow<List<LibraryItemEntity>> observeAll() {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM library_items ORDER BY addedAtEpochMs DESC", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"library_items"}, new Callable<List<LibraryItemEntity>>() { // from class: com.streamsync.app.data.local.LibraryDao_Impl.7
            @Override // java.util.concurrent.Callable
            public List<LibraryItemEntity> call() throws Exception {
                String _tmpPosterUrl;
                String _tmpBackgroundUrl;
                String _tmpDescription;
                String _tmpReleaseInfo;
                String _tmpImdbRating;
                Integer _tmpSeason;
                Integer _tmpEpisode;
                Cursor _cursor = DBUtil.query(LibraryDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfMediaId = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaId");
                    int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
                    int _cursorIndexOfBackgroundUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "backgroundUrl");
                    int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
                    int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
                    int _cursorIndexOfReleaseInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseInfo");
                    int _cursorIndexOfImdbRating = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbRating");
                    int _cursorIndexOfGenresJson = CursorUtil.getColumnIndexOrThrow(_cursor, "genresJson");
                    int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
                    int _cursorIndexOfEpisode = CursorUtil.getColumnIndexOrThrow(_cursor, "episode");
                    int _cursorIndexOfAddedAtEpochMs = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAtEpochMs");
                    List<LibraryItemEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpMediaId = _cursor.getString(_cursorIndexOfMediaId);
                        String _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        if (_cursor.isNull(_cursorIndexOfPosterUrl)) {
                            _tmpPosterUrl = null;
                        } else {
                            _tmpPosterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
                        }
                        if (_cursor.isNull(_cursorIndexOfBackgroundUrl)) {
                            _tmpBackgroundUrl = null;
                        } else {
                            String _tmpBackgroundUrl2 = _cursor.getString(_cursorIndexOfBackgroundUrl);
                            _tmpBackgroundUrl = _tmpBackgroundUrl2;
                        }
                        if (_cursor.isNull(_cursorIndexOfDescription)) {
                            _tmpDescription = null;
                        } else {
                            String _tmpDescription2 = _cursor.getString(_cursorIndexOfDescription);
                            _tmpDescription = _tmpDescription2;
                        }
                        String _tmpType = _cursor.getString(_cursorIndexOfType);
                        if (_cursor.isNull(_cursorIndexOfReleaseInfo)) {
                            _tmpReleaseInfo = null;
                        } else {
                            String _tmpReleaseInfo2 = _cursor.getString(_cursorIndexOfReleaseInfo);
                            _tmpReleaseInfo = _tmpReleaseInfo2;
                        }
                        if (_cursor.isNull(_cursorIndexOfImdbRating)) {
                            _tmpImdbRating = null;
                        } else {
                            String _tmpImdbRating2 = _cursor.getString(_cursorIndexOfImdbRating);
                            _tmpImdbRating = _tmpImdbRating2;
                        }
                        String _tmpGenresJson = _cursor.getString(_cursorIndexOfGenresJson);
                        if (_cursor.isNull(_cursorIndexOfSeason)) {
                            _tmpSeason = null;
                        } else {
                            _tmpSeason = Integer.valueOf(_cursor.getInt(_cursorIndexOfSeason));
                        }
                        if (_cursor.isNull(_cursorIndexOfEpisode)) {
                            _tmpEpisode = null;
                        } else {
                            _tmpEpisode = Integer.valueOf(_cursor.getInt(_cursorIndexOfEpisode));
                        }
                        long _tmpAddedAtEpochMs = _cursor.getLong(_cursorIndexOfAddedAtEpochMs);
                        LibraryItemEntity _item = new LibraryItemEntity(_tmpMediaId, _tmpTitle, _tmpPosterUrl, _tmpBackgroundUrl, _tmpDescription, _tmpType, _tmpReleaseInfo, _tmpImdbRating, _tmpGenresJson, _tmpSeason, _tmpEpisode, _tmpAddedAtEpochMs);
                        int _cursorIndexOfMediaId2 = _cursorIndexOfMediaId;
                        _result.add(_item);
                        _cursorIndexOfMediaId = _cursorIndexOfMediaId2;
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // com.streamsync.app.data.local.LibraryDao
    public Flow<List<String>> observeIds() {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT mediaId FROM library_items", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"library_items"}, new Callable<List<String>>() { // from class: com.streamsync.app.data.local.LibraryDao_Impl.8
            @Override // java.util.concurrent.Callable
            public List<String> call() throws Exception {
                Cursor _cursor = DBUtil.query(LibraryDao_Impl.this.__db, _statement, false, null);
                try {
                    List<String> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _item = _cursor.getString(0);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // com.streamsync.app.data.local.LibraryDao
    public Object findId(final String mediaId, final Continuation<? super String> $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT mediaId FROM library_items WHERE mediaId = ? LIMIT 1", 1);
        _statement.bindString(1, mediaId);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<String>() { // from class: com.streamsync.app.data.local.LibraryDao_Impl.9
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                String _result;
                Cursor _cursor = DBUtil.query(LibraryDao_Impl.this.__db, _statement, false, null);
                try {
                    if (_cursor.moveToFirst()) {
                        if (_cursor.isNull(0)) {
                            _result = null;
                        } else {
                            _result = _cursor.getString(0);
                        }
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _cursor.close();
                    _statement.release();
                }
            }
        }, $completion);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
