package com.google.mlkit.common.sdkinternal.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import java.io.File;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class ModelFileHelper {
    public static final int INVALID_INDEX = -1;
    private final MlKitContext zze;
    private static final GmsLogger zzd = new GmsLogger("ModelFileHelper", "");
    public static final String zza = String.format("com.google.mlkit.%s.models", "translate");
    public static final String zzb = String.format("com.google.mlkit.%s.models", "custom");
    static final String zzc = String.format("com.google.mlkit.%s.models", TtmlNode.RUBY_BASE);

    public ModelFileHelper(MlKitContext mlKitContext) {
        this.zze = mlKitContext;
    }

    private final File zzc(String str, ModelType modelType, boolean z) throws MlKitException {
        File modelDirUnsafe = getModelDirUnsafe(str, modelType, z);
        if (!modelDirUnsafe.exists()) {
            zzd.d("ModelFileHelper", "model folder does not exist, creating one: ".concat(String.valueOf(modelDirUnsafe.getAbsolutePath())));
            if (!modelDirUnsafe.mkdirs()) {
                throw new MlKitException("Failed to create model folder: ".concat(String.valueOf(String.valueOf(modelDirUnsafe))), 13);
            }
        } else if (!modelDirUnsafe.isDirectory()) {
            throw new MlKitException("Can not create model folder, since an existing file has the same name: ".concat(String.valueOf(String.valueOf(modelDirUnsafe))), 6);
        }
        return modelDirUnsafe;
    }

    public synchronized void deleteAllModels(ModelType modelType, String modelName) {
        deleteRecursively(getModelDirUnsafe(modelName, modelType, false));
        deleteRecursively(getModelDirUnsafe(modelName, modelType, true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002b, code lost:
    
        if (r5 != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean deleteRecursively(java.io.File r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L5
            return r0
        L5:
            boolean r1 = r8.isDirectory()
            r2 = 1
            if (r1 == 0) goto L2d
            java.io.File[] r1 = r8.listFiles()
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            java.io.File[] r1 = (java.io.File[]) r1
            int r3 = r1.length
            r4 = r0
            r5 = r2
        L19:
            if (r4 >= r3) goto L2b
            r6 = r1[r4]
            if (r5 == 0) goto L27
            boolean r5 = r7.deleteRecursively(r6)
            if (r5 == 0) goto L27
            r5 = r2
            goto L28
        L27:
            r5 = r0
        L28:
            int r4 = r4 + 1
            goto L19
        L2b:
            if (r5 == 0) goto L34
        L2d:
            boolean r8 = r8.delete()
            if (r8 == 0) goto L34
            return r2
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.ModelFileHelper.deleteRecursively(java.io.File):boolean");
    }

    public void deleteTempFilesInPrivateFolder(String modelName, ModelType modelType) throws MlKitException {
        String str;
        File zzc2 = zzc(modelName, modelType, true);
        if (deleteRecursively(zzc2)) {
            return;
        }
        GmsLogger gmsLogger = zzd;
        if (zzc2 != null) {
            str = zzc2.getAbsolutePath();
        } else {
            str = null;
        }
        gmsLogger.e("ModelFileHelper", "Failed to delete the temp labels file directory: ".concat(String.valueOf(str)));
    }

    public int getLatestCachedModelVersion(File modelDir) {
        File[] listFiles = modelDir.listFiles();
        int i = -1;
        if (listFiles != null && (listFiles.length) != 0) {
            for (File file : listFiles) {
                try {
                    i = Math.max(i, Integer.parseInt(file.getName()));
                } catch (NumberFormatException e) {
                    zzd.d("ModelFileHelper", "Contains non-integer file name ".concat(String.valueOf(file.getName())));
                }
            }
        }
        return i;
    }

    public File getModelDir(String modelName, ModelType modelType) throws MlKitException {
        return zzc(modelName, modelType, false);
    }

    public File getModelDirUnsafe(String modelName, ModelType modelType, boolean isTemp) {
        String str;
        ModelType modelType2 = ModelType.UNKNOWN;
        switch (modelType.ordinal()) {
            case 1:
                str = zzc;
                break;
            case 2:
                str = zza;
                break;
            case 3:
            default:
                throw new IllegalArgumentException("Unknown model type " + modelType.name() + ". Cannot find a dir to store the downloaded model.");
            case 4:
                str = zzb;
                break;
        }
        File file = new File(this.zze.getApplicationContext().getNoBackupFilesDir(), str);
        if (isTemp) {
            file = new File(file, "temp");
        }
        return new File(file, modelName);
    }

    public File getModelTempDir(String modelName, ModelType modelType) throws MlKitException {
        return zzc(modelName, modelType, true);
    }

    public File getTempFileInPrivateFolder(String modelName, ModelType modelType, String fileName) throws MlKitException {
        File zzc2 = zzc(modelName, modelType, true);
        if (!zzc2.exists() || !zzc2.isFile() || zzc2.delete()) {
            if (!zzc2.exists()) {
                zzd.d("ModelFileHelper", "Temp labels folder does not exist, creating one: ".concat(String.valueOf(zzc2.getAbsolutePath())));
                if (!zzc2.mkdirs()) {
                    throw new MlKitException("Failed to create a directory to hold the AutoML model's labels file.", 13);
                }
            }
            return new File(zzc2, fileName);
        }
        throw new MlKitException("Failed to delete the temp labels file: ".concat(String.valueOf(zzc2.getAbsolutePath())), 13);
    }

    public boolean modelExistsLocally(String modelName, ModelType modelType) throws MlKitException {
        String zzb2;
        if (modelType == ModelType.UNKNOWN || (zzb2 = zzb(modelName, modelType)) == null) {
            return false;
        }
        File file = new File(zzb2);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(file, Constants.MODEL_FILE_NAME);
        zzd.i("ModelFileHelper", "Model file path: ".concat(String.valueOf(file2.getAbsolutePath())));
        return file2.exists();
    }

    public final File zza(String str, ModelType modelType) throws MlKitException {
        return zzc(str, modelType, true);
    }

    public final String zzb(String str, ModelType modelType) throws MlKitException {
        File modelDir = getModelDir(str, modelType);
        int latestCachedModelVersion = getLatestCachedModelVersion(modelDir);
        if (latestCachedModelVersion == -1) {
            return null;
        }
        return modelDir.getAbsolutePath() + "/" + latestCachedModelVersion;
    }
}
