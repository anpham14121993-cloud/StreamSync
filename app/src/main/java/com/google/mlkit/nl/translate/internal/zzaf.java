package com.google.mlkit.nl.translate.internal;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover;
import java.io.File;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzaf implements RemoteModelFileMover {
    private static final GmsLogger zza = new GmsLogger("TranslateModelMover", "");
    private final MlKitContext zzb;
    private final String zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(MlKitContext mlKitContext, String str) {
        this.zzb = mlKitContext;
        this.zzc = str;
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover
    public final File getModelFileDestination() throws MlKitException {
        ModelFileHelper modelFileHelper = new ModelFileHelper(this.zzb);
        File modelDir = modelFileHelper.getModelDir(this.zzc, ModelType.TRANSLATE);
        return new File(modelDir, String.valueOf(modelFileHelper.getLatestCachedModelVersion(modelDir) + 1));
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover
    public final File moveAllFilesFromPrivateTempToPrivateDestination(File file) throws MlKitException {
        File modelFileDestination = getModelFileDestination();
        if (file.renameTo(modelFileDestination)) {
            zza.d("TranslateModelMover", "Rename to serving model successfully");
            modelFileDestination.setExecutable(false);
            modelFileDestination.setWritable(false);
            return modelFileDestination;
        }
        zza.d("TranslateModelMover", "Rename to serving model failed, remove the temp file.");
        if (file.delete()) {
            return null;
        }
        zza.d("TranslateModelMover", "Failed to delete the temp file: ".concat(String.valueOf(file.getAbsolutePath())));
        return null;
    }
}
