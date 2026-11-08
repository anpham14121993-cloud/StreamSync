package com.streamsync.app.data.addon;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.autofill.HintConstants;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
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

/* loaded from: classes14.dex */
public final class AddonDao_Impl implements AddonDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<InstalledAddonEntity> __deletionAdapterOfInstalledAddonEntity;
    private final EntityInsertionAdapter<InstalledAddonEntity> __insertionAdapterOfInstalledAddonEntity;

    public AddonDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfInstalledAddonEntity = new EntityInsertionAdapter<InstalledAddonEntity>(__db) { // from class: com.streamsync.app.data.addon.AddonDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `installed_addons` (`baseUrl`,`id`,`name`,`version`,`description`,`typesCsv`,`catalogsJson`,`resourcesJson`,`idPrefixesCsv`,`logoUrl`,`sortOrder`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(final SupportSQLiteStatement statement, final InstalledAddonEntity entity) {
                statement.bindString(1, entity.getBaseUrl());
                statement.bindString(2, entity.getId());
                statement.bindString(3, entity.getName());
                statement.bindString(4, entity.getVersion());
                if (entity.getDescription() == null) {
                    statement.bindNull(5);
                } else {
                    statement.bindString(5, entity.getDescription());
                }
                statement.bindString(6, entity.getTypesCsv());
                statement.bindString(7, entity.getCatalogsJson());
                statement.bindString(8, entity.getResourcesJson());
                statement.bindString(9, entity.getIdPrefixesCsv());
                if (entity.getLogoUrl() == null) {
                    statement.bindNull(10);
                } else {
                    statement.bindString(10, entity.getLogoUrl());
                }
                statement.bindLong(11, entity.getSortOrder());
            }
        };
        this.__deletionAdapterOfInstalledAddonEntity = new EntityDeletionOrUpdateAdapter<InstalledAddonEntity>(__db) { // from class: com.streamsync.app.data.addon.AddonDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `installed_addons` WHERE `baseUrl` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final InstalledAddonEntity entity) {
                statement.bindString(1, entity.getBaseUrl());
            }
        };
    }

    @Override // com.streamsync.app.data.addon.AddonDao
    public Object insert(final InstalledAddonEntity addon, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.addon.AddonDao_Impl.3
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                AddonDao_Impl.this.__db.beginTransaction();
                try {
                    AddonDao_Impl.this.__insertionAdapterOfInstalledAddonEntity.insert((EntityInsertionAdapter) addon);
                    AddonDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    AddonDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.addon.AddonDao
    public Object delete(final InstalledAddonEntity addon, final Continuation<? super Unit> $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.streamsync.app.data.addon.AddonDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                AddonDao_Impl.this.__db.beginTransaction();
                try {
                    AddonDao_Impl.this.__deletionAdapterOfInstalledAddonEntity.handle(addon);
                    AddonDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    AddonDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // com.streamsync.app.data.addon.AddonDao
    public Flow<List<InstalledAddonEntity>> observeAll() {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM installed_addons ORDER BY sortOrder ASC, name ASC", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"installed_addons"}, new Callable<List<InstalledAddonEntity>>() { // from class: com.streamsync.app.data.addon.AddonDao_Impl.5
            @Override // java.util.concurrent.Callable
            public List<InstalledAddonEntity> call() throws Exception {
                String _tmpDescription;
                String _tmpLogoUrl;
                Cursor _cursor = DBUtil.query(AddonDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfBaseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "baseUrl");
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, HintConstants.AUTOFILL_HINT_NAME);
                    int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
                    int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
                    int _cursorIndexOfTypesCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "typesCsv");
                    int _cursorIndexOfCatalogsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "catalogsJson");
                    int _cursorIndexOfResourcesJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resourcesJson");
                    int _cursorIndexOfIdPrefixesCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "idPrefixesCsv");
                    int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
                    int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
                    List<InstalledAddonEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpBaseUrl = _cursor.getString(_cursorIndexOfBaseUrl);
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpName = _cursor.getString(_cursorIndexOfName);
                        String _tmpVersion = _cursor.getString(_cursorIndexOfVersion);
                        if (_cursor.isNull(_cursorIndexOfDescription)) {
                            _tmpDescription = null;
                        } else {
                            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
                        }
                        String _tmpTypesCsv = _cursor.getString(_cursorIndexOfTypesCsv);
                        String _tmpCatalogsJson = _cursor.getString(_cursorIndexOfCatalogsJson);
                        String _tmpResourcesJson = _cursor.getString(_cursorIndexOfResourcesJson);
                        String _tmpIdPrefixesCsv = _cursor.getString(_cursorIndexOfIdPrefixesCsv);
                        if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
                            _tmpLogoUrl = null;
                        } else {
                            String _tmpLogoUrl2 = _cursor.getString(_cursorIndexOfLogoUrl);
                            _tmpLogoUrl = _tmpLogoUrl2;
                        }
                        int _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
                        InstalledAddonEntity _item = new InstalledAddonEntity(_tmpBaseUrl, _tmpId, _tmpName, _tmpVersion, _tmpDescription, _tmpTypesCsv, _tmpCatalogsJson, _tmpResourcesJson, _tmpIdPrefixesCsv, _tmpLogoUrl, _tmpSortOrder);
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

    @Override // com.streamsync.app.data.addon.AddonDao
    public Object getAll(final Continuation<? super List<InstalledAddonEntity>> $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM installed_addons", 0);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<InstalledAddonEntity>>() { // from class: com.streamsync.app.data.addon.AddonDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<InstalledAddonEntity> call() throws Exception {
                String _tmpDescription;
                String _tmpLogoUrl;
                Cursor _cursor = DBUtil.query(AddonDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfBaseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "baseUrl");
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, HintConstants.AUTOFILL_HINT_NAME);
                    int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
                    int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
                    int _cursorIndexOfTypesCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "typesCsv");
                    int _cursorIndexOfCatalogsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "catalogsJson");
                    int _cursorIndexOfResourcesJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resourcesJson");
                    int _cursorIndexOfIdPrefixesCsv = CursorUtil.getColumnIndexOrThrow(_cursor, "idPrefixesCsv");
                    int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
                    int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
                    List<InstalledAddonEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpBaseUrl = _cursor.getString(_cursorIndexOfBaseUrl);
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpName = _cursor.getString(_cursorIndexOfName);
                        String _tmpVersion = _cursor.getString(_cursorIndexOfVersion);
                        if (_cursor.isNull(_cursorIndexOfDescription)) {
                            _tmpDescription = null;
                        } else {
                            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
                        }
                        String _tmpTypesCsv = _cursor.getString(_cursorIndexOfTypesCsv);
                        String _tmpCatalogsJson = _cursor.getString(_cursorIndexOfCatalogsJson);
                        String _tmpResourcesJson = _cursor.getString(_cursorIndexOfResourcesJson);
                        String _tmpIdPrefixesCsv = _cursor.getString(_cursorIndexOfIdPrefixesCsv);
                        if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
                            _tmpLogoUrl = null;
                        } else {
                            String _tmpLogoUrl2 = _cursor.getString(_cursorIndexOfLogoUrl);
                            _tmpLogoUrl = _tmpLogoUrl2;
                        }
                        int _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
                        InstalledAddonEntity _item = new InstalledAddonEntity(_tmpBaseUrl, _tmpId, _tmpName, _tmpVersion, _tmpDescription, _tmpTypesCsv, _tmpCatalogsJson, _tmpResourcesJson, _tmpIdPrefixesCsv, _tmpLogoUrl, _tmpSortOrder);
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
