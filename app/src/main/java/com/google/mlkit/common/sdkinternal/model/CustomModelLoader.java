package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class CustomModelLoader {
    private static final GmsLogger zza = new GmsLogger("CustomModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final LocalModel zzd;
    private final CustomRemoteModel zze;
    private final RemoteModelDownloadManager zzf;
    private final RemoteModelFileManager zzg;
    private final zzsh zzh;
    private boolean zzi;

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    /* loaded from: classes25.dex */
    public interface CustomModelLoaderHelper {
        void logLoad() throws MlKitException;

        boolean tryLoad(LocalModel localModel) throws MlKitException;
    }

    private CustomModelLoader(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        if (customRemoteModel != null) {
            this.zzg = new RemoteModelFileManager(mlKitContext, customRemoteModel, null, new ModelFileHelper(mlKitContext), new com.google.mlkit.common.internal.model.zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.zzf = RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), this.zzg, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
            this.zzi = true;
        } else {
            this.zzg = null;
            this.zzf = null;
        }
        this.zzc = mlKitContext;
        this.zzd = localModel;
        this.zze = customRemoteModel;
        this.zzh = zzss.zzb("common");
    }

    public static synchronized CustomModelLoader getInstance(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel remoteModel) {
        CustomModelLoader customModelLoader;
        synchronized (CustomModelLoader.class) {
            String localModel2 = remoteModel == null ? ((LocalModel) Preconditions.checkNotNull(localModel)).toString() : remoteModel.getUniqueModelNameForPersist();
            if (!zzb.containsKey(localModel2)) {
                zzb.put(localModel2, new CustomModelLoader(mlKitContext, localModel, remoteModel));
            }
            customModelLoader = (CustomModelLoader) zzb.get(localModel2);
        }
        return customModelLoader;
    }

    private final File zza() throws MlKitException {
        String zzb2 = ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzb();
        if (zzb2 == null) {
            zza.d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(zzb2);
        File[] listFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(listFiles)).length == 1 ? listFiles[0] : file;
    }

    private final void zzb() throws MlKitException {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).removeOrCancelDownload();
    }

    private static final LocalModel zzc(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
            return builder.build();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.setAbsoluteFilePath(file.getAbsolutePath());
        return builder2.build();
    }

    public synchronized LocalModel createLocalModelByLatestExistingModel() throws MlKitException {
        zza.d("CustomModelLoader", "Try to get the latest existing model file.");
        File zza2 = zza();
        if (zza2 == null) {
            return null;
        }
        return zzc(zza2);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00ae A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00b0 A[Catch: all -> 0x00b6, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0023, B:9:0x002b, B:14:0x00b0, B:18:0x0031, B:20:0x0055, B:23:0x005f, B:24:0x007a, B:26:0x0082, B:28:0x009f), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.google.mlkit.common.model.LocalModel createLocalModelByNewlyDownloadedModel() throws com.google.mlkit.common.MlKitException {
        /*
            r7 = this;
            monitor-enter(r7)
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.zza     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r1 = "CustomModelLoader"
            java.lang.String r2 = "Try to get newly downloaded model file."
            r0.d(r1, r2)     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r7.zzf     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = (com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager) r0     // Catch: java.lang.Throwable -> Lb6
            java.lang.Long r0 = r0.getDownloadingId()     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.zzf     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r1 = r1.getDownloadingModelHash()     // Catch: java.lang.Throwable -> Lb6
            r2 = 0
            if (r0 == 0) goto L9f
            if (r1 != 0) goto L23
            goto L9f
        L23:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r3 = r7.zzf     // Catch: java.lang.Throwable -> Lb6
            java.lang.Integer r3 = r3.getDownloadingModelStatusCode()     // Catch: java.lang.Throwable -> Lb6
            if (r3 != 0) goto L31
            r7.zzb()     // Catch: java.lang.Throwable -> Lb6
            r0 = r2
            goto Lac
        L31:
            com.google.android.gms.common.internal.GmsLogger r4 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.zza     // Catch: java.lang.Throwable -> Lb6
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb6
            r5.<init>()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r6 = "Download Status code: "
            r5.append(r6)     // Catch: java.lang.Throwable -> Lb6
            r5.append(r3)     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r5 = r6.concat(r5)     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r6 = "CustomModelLoader"
            r4.d(r6, r5)     // Catch: java.lang.Throwable -> Lb6
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> Lb6
            r5 = 8
            if (r4 != r5) goto L7a
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r7.zzf     // Catch: java.lang.Throwable -> Lb6
            java.io.File r0 = r0.zzi(r1)     // Catch: java.lang.Throwable -> Lb6
            if (r0 != 0) goto L5f
            r0 = r2
            goto Lac
        L5f:
            com.google.android.gms.common.internal.GmsLogger r3 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.zza     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r4 = r0.getParent()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r5 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r6 = "CustomModelLoader"
            java.lang.String r4 = r5.concat(r4)     // Catch: java.lang.Throwable -> Lb6
            r3.d(r6, r4)     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r3 = r7.zzf     // Catch: java.lang.Throwable -> Lb6
            r3.updateLatestModelHashAndType(r1)     // Catch: java.lang.Throwable -> Lb6
            goto Lac
        L7a:
            int r1 = r3.intValue()     // Catch: java.lang.Throwable -> Lb6
            r3 = 16
            if (r1 != r3) goto L9d
            com.google.android.gms.internal.mlkit_common.zzsh r1 = r7.zzh     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.model.CustomRemoteModel r3 = r7.zze     // Catch: java.lang.Throwable -> Lb6
            com.google.android.gms.internal.mlkit_common.zzry r4 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch: java.lang.Throwable -> Lb6
            java.lang.Object r3 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.model.RemoteModel r3 = (com.google.mlkit.common.model.RemoteModel) r3     // Catch: java.lang.Throwable -> Lb6
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r5 = r7.zzf     // Catch: java.lang.Throwable -> Lb6
            int r0 = r5.getFailureReason(r0)     // Catch: java.lang.Throwable -> Lb6
            r5 = 0
            r1.zze(r4, r3, r5, r0)     // Catch: java.lang.Throwable -> Lb6
            r7.zzb()     // Catch: java.lang.Throwable -> Lb6
        L9d:
            r0 = r2
            goto Lac
        L9f:
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.zza     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r1 = "CustomModelLoader"
            java.lang.String r3 = "No new model is downloading."
            r0.d(r1, r3)     // Catch: java.lang.Throwable -> Lb6
            r7.zzb()     // Catch: java.lang.Throwable -> Lb6
            r0 = r2
        Lac:
            if (r0 != 0) goto Lb0
            monitor-exit(r7)
            return r2
        Lb0:
            com.google.mlkit.common.model.LocalModel r0 = zzc(r0)     // Catch: java.lang.Throwable -> Lb6
            monitor-exit(r7)
            return r0
        Lb6:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lb6
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.CustomModelLoader.createLocalModelByNewlyDownloadedModel():com.google.mlkit.common.model.LocalModel");
    }

    public void deleteLatestExistingModel() throws MlKitException {
        File zza2 = zza();
        if (zza2 != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzc(zza2);
            SharedPrefManager.getInstance(this.zzc).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.zze));
        }
    }

    public void deleteOldModels(LocalModel localModel) throws MlKitException {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            zza.d("CustomModelLoader", "All old models are deleted.");
            this.zzg.zza(parentFile);
        } else {
            zza.e("CustomModelLoader", "Failed to delete old models");
        }
    }

    public synchronized void load(CustomModelLoaderHelper helper) throws MlKitException {
        LocalModel localModel = this.zzd;
        if (localModel == null) {
            localModel = createLocalModelByNewlyDownloadedModel();
        }
        if (localModel == null) {
            localModel = createLocalModelByLatestExistingModel();
        }
        if (localModel == null) {
            throw new MlKitException("Model is not available.", 14);
        }
        while (!helper.tryLoad(localModel)) {
            if (this.zze != null) {
                deleteLatestExistingModel();
                localModel = createLocalModelByLatestExistingModel();
            } else {
                localModel = null;
            }
            if (localModel == null) {
                helper.logLoad();
                return;
            }
        }
        if (this.zze != null && this.zzi) {
            deleteOldModels((LocalModel) Preconditions.checkNotNull(localModel));
            this.zzi = false;
        }
        helper.logLoad();
    }
}
