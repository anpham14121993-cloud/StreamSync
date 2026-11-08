package com.streamsync.app.data.local;

import androidx.autofill.HintConstants;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.streamsync.app.data.addon.AddonDao;
import com.streamsync.app.data.addon.AddonDao_Impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes15.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile AddonDao _addonDao;
    private volatile LibraryDao _libraryDao;
    private volatile WatchProgressDao _watchProgressDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(final DatabaseConfiguration config) {
        SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(7) { // from class: com.streamsync.app.data.local.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(final SupportSQLiteDatabase db) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `watch_progress` (`mediaId` TEXT NOT NULL, `positionMs` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, `updatedAtEpochMs` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `pendingSync` INTEGER NOT NULL, `streamUrl` TEXT, `addonId` TEXT, `addonName` TEXT, PRIMARY KEY(`mediaId`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `installed_addons` (`baseUrl` TEXT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `version` TEXT NOT NULL, `description` TEXT, `typesCsv` TEXT NOT NULL, `catalogsJson` TEXT NOT NULL, `resourcesJson` TEXT NOT NULL, `idPrefixesCsv` TEXT NOT NULL, `logoUrl` TEXT, `sortOrder` INTEGER NOT NULL, PRIMARY KEY(`baseUrl`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `library_items` (`mediaId` TEXT NOT NULL, `title` TEXT NOT NULL, `posterUrl` TEXT, `backgroundUrl` TEXT, `description` TEXT, `type` TEXT NOT NULL, `releaseInfo` TEXT, `imdbRating` TEXT, `genresJson` TEXT NOT NULL, `season` INTEGER, `episode` INTEGER, `addedAtEpochMs` INTEGER NOT NULL, PRIMARY KEY(`mediaId`))");
                db.execSQL(RoomMasterTable.CREATE_QUERY);
                db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4201b4cf7d52e2b03ff5e210c14b7a60')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(final SupportSQLiteDatabase db) {
                db.execSQL("DROP TABLE IF EXISTS `watch_progress`");
                db.execSQL("DROP TABLE IF EXISTS `installed_addons`");
                db.execSQL("DROP TABLE IF EXISTS `library_items`");
                List<? extends RoomDatabase.Callback> _callbacks = AppDatabase_Impl.this.mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onDestructiveMigration(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(final SupportSQLiteDatabase db) {
                List<? extends RoomDatabase.Callback> _callbacks = AppDatabase_Impl.this.mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onCreate(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(final SupportSQLiteDatabase db) {
                AppDatabase_Impl.this.mDatabase = db;
                AppDatabase_Impl.this.internalInitInvalidationTracker(db);
                List<? extends RoomDatabase.Callback> _callbacks = AppDatabase_Impl.this.mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onOpen(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(final SupportSQLiteDatabase db) {
                DBUtil.dropFtsSyncTriggers(db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(final SupportSQLiteDatabase db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(final SupportSQLiteDatabase db) {
                HashMap<String, TableInfo.Column> _columnsWatchProgress = new HashMap<>(9);
                _columnsWatchProgress.put("mediaId", new TableInfo.Column("mediaId", "TEXT", true, 1, null, 1));
                _columnsWatchProgress.put("positionMs", new TableInfo.Column("positionMs", "INTEGER", true, 0, null, 1));
                _columnsWatchProgress.put("durationMs", new TableInfo.Column("durationMs", "INTEGER", true, 0, null, 1));
                _columnsWatchProgress.put("updatedAtEpochMs", new TableInfo.Column("updatedAtEpochMs", "INTEGER", true, 0, null, 1));
                _columnsWatchProgress.put("deviceId", new TableInfo.Column("deviceId", "TEXT", true, 0, null, 1));
                _columnsWatchProgress.put("pendingSync", new TableInfo.Column("pendingSync", "INTEGER", true, 0, null, 1));
                _columnsWatchProgress.put("streamUrl", new TableInfo.Column("streamUrl", "TEXT", false, 0, null, 1));
                _columnsWatchProgress.put("addonId", new TableInfo.Column("addonId", "TEXT", false, 0, null, 1));
                _columnsWatchProgress.put("addonName", new TableInfo.Column("addonName", "TEXT", false, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysWatchProgress = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesWatchProgress = new HashSet<>(0);
                TableInfo _infoWatchProgress = new TableInfo("watch_progress", _columnsWatchProgress, _foreignKeysWatchProgress, _indicesWatchProgress);
                TableInfo _existingWatchProgress = TableInfo.read(db, "watch_progress");
                if (!_infoWatchProgress.equals(_existingWatchProgress)) {
                    return new RoomOpenHelper.ValidationResult(false, "watch_progress(com.streamsync.app.data.local.WatchProgressEntity).\n Expected:\n" + _infoWatchProgress + "\n Found:\n" + _existingWatchProgress);
                }
                HashMap<String, TableInfo.Column> _columnsInstalledAddons = new HashMap<>(11);
                _columnsInstalledAddons.put("baseUrl", new TableInfo.Column("baseUrl", "TEXT", true, 1, null, 1));
                _columnsInstalledAddons.put("id", new TableInfo.Column("id", "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put(HintConstants.AUTOFILL_HINT_NAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_NAME, "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put("version", new TableInfo.Column("version", "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, 1));
                _columnsInstalledAddons.put("typesCsv", new TableInfo.Column("typesCsv", "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put("catalogsJson", new TableInfo.Column("catalogsJson", "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put("resourcesJson", new TableInfo.Column("resourcesJson", "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put("idPrefixesCsv", new TableInfo.Column("idPrefixesCsv", "TEXT", true, 0, null, 1));
                _columnsInstalledAddons.put("logoUrl", new TableInfo.Column("logoUrl", "TEXT", false, 0, null, 1));
                _columnsInstalledAddons.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysInstalledAddons = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesInstalledAddons = new HashSet<>(0);
                TableInfo _infoInstalledAddons = new TableInfo("installed_addons", _columnsInstalledAddons, _foreignKeysInstalledAddons, _indicesInstalledAddons);
                TableInfo _existingInstalledAddons = TableInfo.read(db, "installed_addons");
                if (!_infoInstalledAddons.equals(_existingInstalledAddons)) {
                    return new RoomOpenHelper.ValidationResult(false, "installed_addons(com.streamsync.app.data.addon.InstalledAddonEntity).\n Expected:\n" + _infoInstalledAddons + "\n Found:\n" + _existingInstalledAddons);
                }
                HashMap<String, TableInfo.Column> _columnsLibraryItems = new HashMap<>(12);
                _columnsLibraryItems.put("mediaId", new TableInfo.Column("mediaId", "TEXT", true, 1, null, 1));
                _columnsLibraryItems.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                _columnsLibraryItems.put("posterUrl", new TableInfo.Column("posterUrl", "TEXT", false, 0, null, 1));
                _columnsLibraryItems.put("backgroundUrl", new TableInfo.Column("backgroundUrl", "TEXT", false, 0, null, 1));
                _columnsLibraryItems.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, 1));
                _columnsLibraryItems.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, 1));
                _columnsLibraryItems.put("releaseInfo", new TableInfo.Column("releaseInfo", "TEXT", false, 0, null, 1));
                _columnsLibraryItems.put("imdbRating", new TableInfo.Column("imdbRating", "TEXT", false, 0, null, 1));
                _columnsLibraryItems.put("genresJson", new TableInfo.Column("genresJson", "TEXT", true, 0, null, 1));
                _columnsLibraryItems.put("season", new TableInfo.Column("season", "INTEGER", false, 0, null, 1));
                _columnsLibraryItems.put("episode", new TableInfo.Column("episode", "INTEGER", false, 0, null, 1));
                _columnsLibraryItems.put("addedAtEpochMs", new TableInfo.Column("addedAtEpochMs", "INTEGER", true, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysLibraryItems = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesLibraryItems = new HashSet<>(0);
                TableInfo _infoLibraryItems = new TableInfo("library_items", _columnsLibraryItems, _foreignKeysLibraryItems, _indicesLibraryItems);
                TableInfo _existingLibraryItems = TableInfo.read(db, "library_items");
                return !_infoLibraryItems.equals(_existingLibraryItems) ? new RoomOpenHelper.ValidationResult(false, "library_items(com.streamsync.app.data.local.LibraryItemEntity).\n Expected:\n" + _infoLibraryItems + "\n Found:\n" + _existingLibraryItems) : new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "4201b4cf7d52e2b03ff5e210c14b7a60", "9722f5039ee615ee608a2787b461fed9");
        SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
        SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        HashMap<String, String> _shadowTablesMap = new HashMap<>(0);
        HashMap<String, Set<String>> _viewTables = new HashMap<>(0);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "watch_progress", "installed_addons", "library_items");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `watch_progress`");
            _db.execSQL("DELETE FROM `installed_addons`");
            _db.execSQL("DELETE FROM `library_items`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<>();
        _typeConvertersMap.put(WatchProgressDao.class, WatchProgressDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(AddonDao.class, AddonDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(LibraryDao.class, LibraryDao_Impl.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<>();
        return _autoMigrationSpecsSet;
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        List<Migration> _autoMigrations = new ArrayList<>();
        return _autoMigrations;
    }

    @Override // com.streamsync.app.data.local.AppDatabase
    public WatchProgressDao watchProgressDao() {
        WatchProgressDao watchProgressDao;
        if (this._watchProgressDao != null) {
            return this._watchProgressDao;
        }
        synchronized (this) {
            if (this._watchProgressDao == null) {
                this._watchProgressDao = new WatchProgressDao_Impl(this);
            }
            watchProgressDao = this._watchProgressDao;
        }
        return watchProgressDao;
    }

    @Override // com.streamsync.app.data.local.AppDatabase
    public AddonDao addonDao() {
        AddonDao addonDao;
        if (this._addonDao != null) {
            return this._addonDao;
        }
        synchronized (this) {
            if (this._addonDao == null) {
                this._addonDao = new AddonDao_Impl(this);
            }
            addonDao = this._addonDao;
        }
        return addonDao;
    }

    @Override // com.streamsync.app.data.local.AppDatabase
    public LibraryDao libraryDao() {
        LibraryDao libraryDao;
        if (this._libraryDao != null) {
            return this._libraryDao;
        }
        synchronized (this) {
            if (this._libraryDao == null) {
                this._libraryDao = new LibraryDao_Impl(this);
            }
            libraryDao = this._libraryDao;
        }
        return libraryDao;
    }
}
