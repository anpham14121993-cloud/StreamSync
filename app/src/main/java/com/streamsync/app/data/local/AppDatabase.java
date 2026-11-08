package com.streamsync.app.data.local;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.streamsync.app.data.addon.AddonDao;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppDatabase.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lcom/streamsync/app/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "addonDao", "Lcom/streamsync/app/data/addon/AddonDao;", "libraryDao", "Lcom/streamsync/app/data/local/LibraryDao;", "watchProgressDao", "Lcom/streamsync/app/data/local/WatchProgressDao;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public abstract class AppDatabase extends RoomDatabase {
    public static final int $stable = 0;
    private static volatile AppDatabase INSTANCE;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AppDatabase$Companion$MIGRATION_2_3$1 MIGRATION_2_3 = new Migration() { // from class: com.streamsync.app.data.local.AppDatabase$Companion$MIGRATION_2_3$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE installed_addons ADD COLUMN resourcesJson TEXT NOT NULL DEFAULT '[]'");
            db.execSQL("ALTER TABLE installed_addons ADD COLUMN idPrefixesCsv TEXT NOT NULL DEFAULT ''");
        }
    };
    private static final AppDatabase$Companion$MIGRATION_3_4$1 MIGRATION_3_4 = new Migration() { // from class: com.streamsync.app.data.local.AppDatabase$Companion$MIGRATION_3_4$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("CREATE TABLE IF NOT EXISTS `library_items` (\n    `mediaId` TEXT NOT NULL,\n    `title` TEXT NOT NULL,\n    `posterUrl` TEXT,\n    `backgroundUrl` TEXT,\n    `description` TEXT,\n    `type` TEXT NOT NULL,\n    `releaseInfo` TEXT,\n    `imdbRating` TEXT,\n    `genresJson` TEXT NOT NULL,\n    `season` INTEGER,\n    `episode` INTEGER,\n    `addedAtEpochMs` INTEGER NOT NULL,\n    PRIMARY KEY(`mediaId`)\n)");
            db.execSQL("CREATE INDEX IF NOT EXISTS `index_library_items_addedAtEpochMs` ON `library_items` (`addedAtEpochMs`)");
        }
    };
    private static final AppDatabase$Companion$MIGRATION_4_5$1 MIGRATION_4_5 = new Migration() { // from class: com.streamsync.app.data.local.AppDatabase$Companion$MIGRATION_4_5$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE installed_addons ADD COLUMN logoUrl TEXT");
        }
    };
    private static final AppDatabase$Companion$MIGRATION_5_6$1 MIGRATION_5_6 = new Migration() { // from class: com.streamsync.app.data.local.AppDatabase$Companion$MIGRATION_5_6$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE installed_addons ADD COLUMN sortOrder INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final AppDatabase$Companion$MIGRATION_6_7$1 MIGRATION_6_7 = new Migration() { // from class: com.streamsync.app.data.local.AppDatabase$Companion$MIGRATION_6_7$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            db.execSQL("ALTER TABLE watch_progress ADD COLUMN streamUrl TEXT");
            db.execSQL("ALTER TABLE watch_progress ADD COLUMN addonId TEXT");
            db.execSQL("ALTER TABLE watch_progress ADD COLUMN addonName TEXT");
        }
    };

    public abstract AddonDao addonDao();

    public abstract LibraryDao libraryDao();

    public abstract WatchProgressDao watchProgressDao();

    /* compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0005\u0006\t\f\u000f\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/local/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/streamsync/app/data/local/AppDatabase;", "MIGRATION_2_3", "com/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_2_3$1", "Lcom/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_2_3$1;", "MIGRATION_3_4", "com/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_3_4$1", "Lcom/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_3_4$1;", "MIGRATION_4_5", "com/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_4_5$1", "Lcom/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_4_5$1;", "MIGRATION_5_6", "com/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_5_6$1", "Lcom/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_5_6$1;", "MIGRATION_6_7", "com/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_6_7$1", "Lcom/streamsync/app/data/local/AppDatabase$Companion$MIGRATION_6_7$1;", "get", "context", "Landroid/content/Context;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes15.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppDatabase get(Context context) {
            AppDatabase appDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            AppDatabase appDatabase2 = AppDatabase.INSTANCE;
            if (appDatabase2 == null) {
                synchronized (this) {
                    appDatabase = AppDatabase.INSTANCE;
                    if (appDatabase == null) {
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        RoomDatabase build = Room.databaseBuilder(applicationContext, AppDatabase.class, "streamsync.db").addMigrations(AppDatabase.MIGRATION_2_3, AppDatabase.MIGRATION_3_4, AppDatabase.MIGRATION_4_5, AppDatabase.MIGRATION_5_6, AppDatabase.MIGRATION_6_7).build();
                        AppDatabase it = (AppDatabase) build;
                        Companion companion = AppDatabase.INSTANCE;
                        AppDatabase.INSTANCE = it;
                        appDatabase = (AppDatabase) build;
                    }
                }
                return appDatabase;
            }
            return appDatabase2;
        }
    }
}
