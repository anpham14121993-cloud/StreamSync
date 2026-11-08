package com.google.mlkit.nl.translate.internal;

import android.content.Context;
import android.util.Log;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.LegacyModelMigrator;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import java.io.File;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzp extends LegacyModelMigrator {
    public zzp(Context context, ModelFileHelper modelFileHelper) {
        super(context, modelFileHelper);
    }

    private final void zzb(File file) {
        String name = file.getName();
        try {
            if (zzac.zzd(name).length != 2 || !file.isDirectory()) {
                return;
            }
            try {
                File modelDir = this.modelFileHelper.getModelDir(name, ModelType.TRANSLATE);
                com.google.android.gms.internal.mlkit_translate.zzt zza = zzac.zza(name);
                int size = zza.size();
                for (int i = 0; i < size; i++) {
                    String str = (String) zza.get(i);
                    migrateFile(new File(file, str), new File(modelDir, str));
                }
                deleteIfEmpty(file);
            } catch (MlKitException e) {
                Log.e("TranslateMigrator", "Error creating model directory for ".concat(String.valueOf(name)), e);
            }
        } catch (IllegalArgumentException e2) {
        }
    }

    @Override // com.google.mlkit.common.sdkinternal.model.LegacyModelMigrator
    protected final String getLegacyModelDirName() {
        return "com.google.firebase.ml.translate.models";
    }

    @Override // com.google.mlkit.common.sdkinternal.model.LegacyModelMigrator
    protected final void migrateAllModelDirs(File file) {
        File[] listFiles;
        if (!isValidFirebasePersistenceKey(file.getName()) || !file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            zzb(file2);
        }
        deleteIfEmpty(file);
    }
}
