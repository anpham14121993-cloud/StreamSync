package com.streamsync.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
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
public final class WatchProgressDao_Impl implements WatchProgressDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<WatchProgressEntity> __insertionAdapterOfWatchProgressEntity;
    private final SharedSQLiteStatement __preparedStmtOfMarkSynced;

    public WatchProgressDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfWatchProgressEntity = new EntityInsertionAdapter<WatchProgressEntity>(__db) { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `watch_progress` (`mediaId`,`positionMs`,`durationMs`,`updatedAtEpochMs`,`deviceId`,`pendingSync`,`streamUrl`,`addonId`,`addonName`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, WatchProgressEntity watchProgressEntity) {
                supportSQLiteStatement.bindString(1, watchProgressEntity.getMediaId());
                supportSQLiteStatement.bindLong(2, watchProgressEntity.getPositionMs());
                supportSQLiteStatement.bindLong(3, watchProgressEntity.getDurationMs());
                supportSQLiteStatement.bindLong(4, watchProgressEntity.getUpdatedAtEpochMs());
                supportSQLiteStatement.bindString(5, watchProgressEntity.getDeviceId());
                supportSQLiteStatement.bindLong(6, watchProgressEntity.getPendingSync() ? 1L : 0L);
                if (watchProgressEntity.getStreamUrl() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, watchProgressEntity.getStreamUrl());
                }
                if (watchProgressEntity.getAddonId() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, watchProgressEntity.getAddonId());
                }
                if (watchProgressEntity.getAddonName() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, watchProgressEntity.getAddonName());
                }
            }
        };
        this.__preparedStmtOfMarkSynced = new SharedSQLiteStatement(__db) { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE watch_progress SET pendingSync = 0 WHERE mediaId = ?";
            }
        };
    }

    @Override // com.streamsync.app.data.local.WatchProgressDao
    public Object upsert(final WatchProgressEntity progress, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.3
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                WatchProgressDao_Impl.this.__db.beginTransaction();
                try {
                    WatchProgressDao_Impl.this.__insertionAdapterOfWatchProgressEntity.insert((EntityInsertionAdapter) progress);
                    WatchProgressDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    WatchProgressDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.local.WatchProgressDao
    public Object markSynced(final String mediaId, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = WatchProgressDao_Impl.this.__preparedStmtOfMarkSynced.acquire();
                _stmt.bindString(1, mediaId);
                try {
                    WatchProgressDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        WatchProgressDao_Impl.this.__db.setTransactionSuccessful();
                        return Unit.INSTANCE;
                    } finally {
                        WatchProgressDao_Impl.this.__db.endTransaction();
                    }
                } finally {
                    WatchProgressDao_Impl.this.__preparedStmtOfMarkSynced.release(_stmt);
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.local.WatchProgressDao
    public Object get(final String mediaId, final Continuation<? super WatchProgressEntity> $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM watch_progress WHERE mediaId = ? LIMIT 1", 1);
        _statement.bindString(1, mediaId);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<WatchProgressEntity>() { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public WatchProgressEntity call() throws Exception {
                WatchProgressEntity _result;
                String _tmpStreamUrl;
                String _tmpAddonId;
                String _tmpAddonName;
                Cursor _cursor = DBUtil.query(WatchProgressDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfMediaId = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaId");
                    int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
                    int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
                    int _cursorIndexOfUpdatedAtEpochMs = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAtEpochMs");
                    int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
                    int _cursorIndexOfPendingSync = CursorUtil.getColumnIndexOrThrow(_cursor, "pendingSync");
                    int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
                    int _cursorIndexOfAddonId = CursorUtil.getColumnIndexOrThrow(_cursor, "addonId");
                    int _cursorIndexOfAddonName = CursorUtil.getColumnIndexOrThrow(_cursor, "addonName");
                    if (_cursor.moveToFirst()) {
                        String _tmpMediaId = _cursor.getString(_cursorIndexOfMediaId);
                        long _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
                        long _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
                        long _tmpUpdatedAtEpochMs = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMs);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        int _tmp = _cursor.getInt(_cursorIndexOfPendingSync);
                        boolean _tmpPendingSync = _tmp != 0;
                        if (_cursor.isNull(_cursorIndexOfStreamUrl)) {
                            _tmpStreamUrl = null;
                        } else {
                            _tmpStreamUrl = _cursor.getString(_cursorIndexOfStreamUrl);
                        }
                        if (_cursor.isNull(_cursorIndexOfAddonId)) {
                            _tmpAddonId = null;
                        } else {
                            String _tmpAddonId2 = _cursor.getString(_cursorIndexOfAddonId);
                            _tmpAddonId = _tmpAddonId2;
                        }
                        if (_cursor.isNull(_cursorIndexOfAddonName)) {
                            _tmpAddonName = null;
                        } else {
                            String _tmpAddonName2 = _cursor.getString(_cursorIndexOfAddonName);
                            _tmpAddonName = _tmpAddonName2;
                        }
                        _result = new WatchProgressEntity(_tmpMediaId, _tmpPositionMs, _tmpDurationMs, _tmpUpdatedAtEpochMs, _tmpDeviceId, _tmpPendingSync, _tmpStreamUrl, _tmpAddonId, _tmpAddonName);
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

    @Override // com.streamsync.app.data.local.WatchProgressDao
    public Flow<List<WatchProgressEntity>> observeAll() {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM watch_progress ORDER BY updatedAtEpochMs DESC", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"watch_progress"}, new Callable<List<WatchProgressEntity>>() { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<WatchProgressEntity> call() throws Exception {
                String _tmpStreamUrl;
                String _tmpAddonId;
                String _tmpAddonName;
                Cursor _cursor = DBUtil.query(WatchProgressDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfMediaId = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaId");
                    int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
                    int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
                    int _cursorIndexOfUpdatedAtEpochMs = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAtEpochMs");
                    int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
                    int _cursorIndexOfPendingSync = CursorUtil.getColumnIndexOrThrow(_cursor, "pendingSync");
                    int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
                    int _cursorIndexOfAddonId = CursorUtil.getColumnIndexOrThrow(_cursor, "addonId");
                    int _cursorIndexOfAddonName = CursorUtil.getColumnIndexOrThrow(_cursor, "addonName");
                    List<WatchProgressEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpMediaId = _cursor.getString(_cursorIndexOfMediaId);
                        long _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
                        long _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
                        long _tmpUpdatedAtEpochMs = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMs);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        int _tmp = _cursor.getInt(_cursorIndexOfPendingSync);
                        boolean _tmpPendingSync = _tmp != 0;
                        if (_cursor.isNull(_cursorIndexOfStreamUrl)) {
                            _tmpStreamUrl = null;
                        } else {
                            String _tmpStreamUrl2 = _cursor.getString(_cursorIndexOfStreamUrl);
                            _tmpStreamUrl = _tmpStreamUrl2;
                        }
                        if (_cursor.isNull(_cursorIndexOfAddonId)) {
                            _tmpAddonId = null;
                        } else {
                            String _tmpAddonId2 = _cursor.getString(_cursorIndexOfAddonId);
                            _tmpAddonId = _tmpAddonId2;
                        }
                        if (_cursor.isNull(_cursorIndexOfAddonName)) {
                            _tmpAddonName = null;
                        } else {
                            String _tmpAddonName2 = _cursor.getString(_cursorIndexOfAddonName);
                            _tmpAddonName = _tmpAddonName2;
                        }
                        WatchProgressEntity _item = new WatchProgressEntity(_tmpMediaId, _tmpPositionMs, _tmpDurationMs, _tmpUpdatedAtEpochMs, _tmpDeviceId, _tmpPendingSync, _tmpStreamUrl, _tmpAddonId, _tmpAddonName);
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

    @Override // com.streamsync.app.data.local.WatchProgressDao
    public Object getPendingSync(final Continuation<? super List<WatchProgressEntity>> $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM watch_progress WHERE pendingSync = 1", 0);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<WatchProgressEntity>>() { // from class: com.streamsync.app.data.local.WatchProgressDao_Impl.7
            @Override // java.util.concurrent.Callable
            public List<WatchProgressEntity> call() throws Exception {
                String _tmpStreamUrl;
                String _tmpAddonId;
                String _tmpAddonName;
                Cursor _cursor = DBUtil.query(WatchProgressDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfMediaId = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaId");
                    int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
                    int _cursorIndexOfDurationMs = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMs");
                    int _cursorIndexOfUpdatedAtEpochMs = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAtEpochMs");
                    int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
                    int _cursorIndexOfPendingSync = CursorUtil.getColumnIndexOrThrow(_cursor, "pendingSync");
                    int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
                    int _cursorIndexOfAddonId = CursorUtil.getColumnIndexOrThrow(_cursor, "addonId");
                    int _cursorIndexOfAddonName = CursorUtil.getColumnIndexOrThrow(_cursor, "addonName");
                    List<WatchProgressEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpMediaId = _cursor.getString(_cursorIndexOfMediaId);
                        long _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
                        long _tmpDurationMs = _cursor.getLong(_cursorIndexOfDurationMs);
                        long _tmpUpdatedAtEpochMs = _cursor.getLong(_cursorIndexOfUpdatedAtEpochMs);
                        String _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
                        int _tmp = _cursor.getInt(_cursorIndexOfPendingSync);
                        boolean _tmpPendingSync = _tmp != 0;
                        if (_cursor.isNull(_cursorIndexOfStreamUrl)) {
                            _tmpStreamUrl = null;
                        } else {
                            String _tmpStreamUrl2 = _cursor.getString(_cursorIndexOfStreamUrl);
                            _tmpStreamUrl = _tmpStreamUrl2;
                        }
                        if (_cursor.isNull(_cursorIndexOfAddonId)) {
                            _tmpAddonId = null;
                        } else {
                            String _tmpAddonId2 = _cursor.getString(_cursorIndexOfAddonId);
                            _tmpAddonId = _tmpAddonId2;
                        }
                        if (_cursor.isNull(_cursorIndexOfAddonName)) {
                            _tmpAddonName = null;
                        } else {
                            String _tmpAddonName2 = _cursor.getString(_cursorIndexOfAddonName);
                            _tmpAddonName = _tmpAddonName2;
                        }
                        WatchProgressEntity _item = new WatchProgressEntity(_tmpMediaId, _tmpPositionMs, _tmpDurationMs, _tmpUpdatedAtEpochMs, _tmpDeviceId, _tmpPendingSync, _tmpStreamUrl, _tmpAddonId, _tmpAddonName);
                        _result.add(_item);
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
