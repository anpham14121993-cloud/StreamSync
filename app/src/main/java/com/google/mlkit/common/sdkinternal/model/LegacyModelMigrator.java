package com.google.mlkit.common.sdkinternal.model;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import java.io.File;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public abstract class LegacyModelMigrator {
    protected final ModelFileHelper modelFileHelper;
    private final Context zzb;
    private final TaskCompletionSource zza = new TaskCompletionSource();
    private final Executor zzc = MLTaskExecutor.workerThreadExecutor();

    /* JADX INFO: Access modifiers changed from: protected */
    public LegacyModelMigrator(Context context, ModelFileHelper modelFileHelper) {
        this.zzb = context;
        this.modelFileHelper = modelFileHelper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void deleteIfEmpty(File modelDir) {
        File[] listFiles = modelDir.listFiles();
        if ((listFiles == null || listFiles.length == 0) && !modelDir.delete()) {
            Log.e("MlKitLegacyMigration", "Error deleting model directory ".concat(String.valueOf(String.valueOf(modelDir))));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isValidFirebasePersistenceKey(String persistenceKey) {
        String[] split = persistenceKey.split("\\+", -1);
        if (split.length != 2) {
            return false;
        }
        try {
            Base64Utils.decodeUrlSafeNoPadding(split[0]);
            try {
                Base64Utils.decodeUrlSafeNoPadding(split[1]);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } catch (IllegalArgumentException e2) {
            return false;
        }
    }

    public static void migrateFile(File modelFile, File newModelFile) {
        if (!modelFile.exists()) {
            return;
        }
        if (!newModelFile.exists() && !modelFile.renameTo(newModelFile)) {
            Log.e("MlKitLegacyMigration", "Error moving model file " + String.valueOf(modelFile) + " to " + String.valueOf(newModelFile));
        }
        if (!modelFile.exists() || modelFile.delete()) {
            return;
        }
        Log.e("MlKitLegacyMigration", "Error deleting model file ".concat(String.valueOf(String.valueOf(modelFile))));
    }

    protected abstract String getLegacyModelDirName();

    public File getLegacyRootDir() {
        Context context = this.zzb;
        return new File(context.getNoBackupFilesDir(), getLegacyModelDirName());
    }

    public Task<Void> getMigrationTask() {
        return this.zza.getTask();
    }

    protected abstract void migrateAllModelDirs(File file);

    public void start() {
        this.zzc.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.model.zza
            @Override // java.lang.Runnable
            public final void run() {
                LegacyModelMigrator.this.zza();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza() {
        File legacyRootDir = getLegacyRootDir();
        File[] listFiles = legacyRootDir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                migrateAllModelDirs(file);
            }
            deleteIfEmpty(legacyRootDir);
        }
        this.zza.setResult(null);
    }
}
