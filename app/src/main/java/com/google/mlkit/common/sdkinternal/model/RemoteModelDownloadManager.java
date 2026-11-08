package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzmu;
import com.google.android.gms.internal.mlkit_common.zzna;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class RemoteModelDownloadManager {
    private static final GmsLogger zza = new GmsLogger("ModelDownloadManager", "");
    private static final Map zzb = new HashMap();
    private final LongSparseArray zzc = new LongSparseArray();
    private final LongSparseArray zzd = new LongSparseArray();
    private final MlKitContext zze;
    private final DownloadManager zzf;
    private final RemoteModel zzg;
    private final ModelType zzh;
    private final zzsh zzi;
    private final SharedPrefManager zzj;
    private final ModelFileHelper zzk;
    private final ModelInfoRetrieverInterop zzl;
    private final RemoteModelFileManager zzm;
    private DownloadConditions zzn;

    RemoteModelDownloadManager(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop, zzsh zzshVar) {
        this.zze = mlKitContext;
        this.zzh = remoteModel.getModelType();
        this.zzg = remoteModel;
        this.zzf = (DownloadManager) mlKitContext.getApplicationContext().getSystemService("download");
        this.zzi = zzshVar;
        if (this.zzf == null) {
            zza.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.zzk = modelFileHelper;
        this.zzj = SharedPrefManager.getInstance(mlKitContext);
        this.zzl = modelInfoRetrieverInterop;
        this.zzm = remoteModelFileManager;
    }

    public static synchronized RemoteModelDownloadManager getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop retrieverInterop) {
        RemoteModelDownloadManager remoteModelDownloadManager;
        synchronized (RemoteModelDownloadManager.class) {
            if (!zzb.containsKey(remoteModel)) {
                zzb.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, retrieverInterop, zzss.zzb("common")));
            }
            remoteModelDownloadManager = (RemoteModelDownloadManager) zzb.get(remoteModel);
        }
        return remoteModelDownloadManager;
    }

    private final Task zzj(long j) {
        MlKitContext mlKitContext = this.zze;
        ContextCompat.registerReceiver(mlKitContext.getApplicationContext(), zzm(j), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), null, MLTaskExecutor.getInstance().getHandler(), 2);
        return zzk(j).getTask();
    }

    private final synchronized TaskCompletionSource zzk(long j) {
        TaskCompletionSource taskCompletionSource;
        taskCompletionSource = (TaskCompletionSource) this.zzd.get(j);
        if (taskCompletionSource == null) {
            taskCompletionSource = new TaskCompletionSource();
            this.zzd.put(j, taskCompletionSource);
        }
        return taskCompletionSource;
    }

    private final synchronized zzc zzm(long j) {
        zzc zzcVar;
        zzcVar = (zzc) this.zzc.get(j);
        if (zzcVar == null) {
            zzcVar = new zzc(this, j, zzk(j), null);
            this.zzc.put(j, zzcVar);
        }
        return zzcVar;
    }

    private final synchronized Long zzn(DownloadManager.Request request, ModelInfo modelInfo) {
        DownloadManager downloadManager = this.zzf;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        zza.d("ModelDownloadManager", "Schedule a new downloading task: " + enqueue);
        this.zzj.setDownloadingModelInfo(enqueue, modelInfo);
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, modelInfo.getModelType(), zzna.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    private final synchronized Long zzo(ModelInfo modelInfo, DownloadConditions downloadConditions) throws MlKitException {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        String downloadingModelHash = this.zzj.getDownloadingModelHash(this.zzg);
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        if (downloadingModelHash == null || !downloadingModelHash.equals(modelInfo.getModelHash()) || downloadingModelStatusCode == null) {
            zza.d("ModelDownloadManager", "Need to download a new model.");
            removeOrCancelDownload();
            DownloadManager.Request request = new DownloadManager.Request(modelInfo.getModelUri());
            if (this.zzk.modelExistsLocally(modelInfo.getModelNameForPersist(), modelInfo.getModelType())) {
                zza.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
                this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, false, modelInfo.getModelType(), zzna.UPDATE_AVAILABLE);
            }
            request.setRequiresCharging(downloadConditions.isChargingRequired());
            if (downloadConditions.isWifiRequired()) {
                request.setAllowedNetworkTypes(2);
            }
            return zzn(request, modelInfo);
        }
        Integer downloadingModelStatusCode2 = getDownloadingModelStatusCode();
        if (downloadingModelStatusCode2 == null || (downloadingModelStatusCode2.intValue() != 8 && downloadingModelStatusCode2.intValue() != 16)) {
            zzsh zzshVar = this.zzi;
            RemoteModel remoteModel = this.zzg;
            zzshVar.zzf(zzsk.zzg(), remoteModel, zzmu.NO_ERROR, false, remoteModel.getModelType(), zzna.DOWNLOADING);
        }
        zza.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ad, code lost:
    
        r1 = zzo(r1, r13.zzn);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b3, code lost:
    
        if (r1 == null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bd, code lost:
    
        return zzj(r1.longValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00be, code lost:
    
        com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.zza.i("ModelDownloadManager", "Didn't schedule download for the updated model");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.gms.tasks.Task<java.lang.Void> ensureModelDownloaded() {
        /*
            r13 = this;
            com.google.android.gms.internal.mlkit_common.zzsh r0 = r13.zzi
            com.google.android.gms.internal.mlkit_common.zzry r1 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()
            com.google.mlkit.common.model.RemoteModel r2 = r13.zzg
            com.google.android.gms.internal.mlkit_common.zzmu r3 = com.google.android.gms.internal.mlkit_common.zzmu.NO_ERROR
            com.google.mlkit.common.sdkinternal.ModelType r5 = com.google.mlkit.common.sdkinternal.ModelType.UNKNOWN
            com.google.android.gms.internal.mlkit_common.zzna r6 = com.google.android.gms.internal.mlkit_common.zzna.EXPLICITLY_REQUESTED
            r4 = 0
            r0.zzf(r1, r2, r3, r4, r5, r6)
            r0 = 0
            com.google.mlkit.common.sdkinternal.ModelInfo r1 = r13.zzg()     // Catch: com.google.mlkit.common.MlKitException -> L1a
            r2 = r0
            goto L1d
        L1a:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L1d:
            r3 = 13
            java.lang.Integer r4 = r13.getDownloadingModelStatusCode()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            java.lang.Long r5 = r13.getDownloadingId()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            boolean r6 = r13.modelExistsLocally()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            if (r6 != 0) goto Lab
            if (r4 == 0) goto L39
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r7 = 8
            if (r6 != r7) goto L39
            goto Lab
        L39:
            if (r4 == 0) goto L4f
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r7 = 16
            if (r6 != r7) goto L4f
            com.google.mlkit.common.MlKitException r0 = r13.zzl(r5)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r13.removeOrCancelDownload()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            return r0
        L4f:
            if (r4 == 0) goto L8b
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r7 = 4
            if (r6 == r7) goto L66
            int r6 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r7 = 2
            if (r6 == r7) goto L66
            int r4 = r4.intValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r6 = 1
            if (r4 != r6) goto L8b
        L66:
            if (r5 == 0) goto L8b
            java.lang.String r4 = r13.getDownloadingModelHash()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            if (r4 == 0) goto L8b
            com.google.android.gms.internal.mlkit_common.zzsh r6 = r13.zzi     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.internal.mlkit_common.zzry r7 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.mlkit.common.model.RemoteModel r8 = r13.zzg     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.internal.mlkit_common.zzmu r9 = com.google.android.gms.internal.mlkit_common.zzmu.NO_ERROR     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.mlkit.common.sdkinternal.ModelType r11 = r8.getModelType()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.internal.mlkit_common.zzna r12 = com.google.android.gms.internal.mlkit_common.zzna.DOWNLOADING     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            r10 = 0
            r6.zzf(r7, r8, r9, r10, r11, r12)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            long r0 = r5.longValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.tasks.Task r0 = r13.zzj(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            return r0
        L8b:
            if (r1 != 0) goto L8e
            goto L94
        L8e:
            com.google.mlkit.common.model.DownloadConditions r0 = r13.zzn     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            java.lang.Long r0 = r13.zzo(r1, r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
        L94:
            if (r0 != 0) goto La2
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            java.lang.String r1 = "Failed to schedule the download task"
            r0.<init>(r1, r3, r2)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            return r0
        La2:
            long r0 = r0.longValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.tasks.Task r0 = r13.zzj(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            return r0
        Lab:
            if (r1 == 0) goto Lc7
            com.google.mlkit.common.model.DownloadConditions r2 = r13.zzn     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            java.lang.Long r1 = r13.zzo(r1, r2)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            if (r1 == 0) goto Lbe
            long r0 = r1.longValue()     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            com.google.android.gms.tasks.Task r0 = r13.zzj(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            return r0
        Lbe:
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.zza     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            java.lang.String r2 = "ModelDownloadManager"
            java.lang.String r4 = "Didn't schedule download for the updated model"
            r1.i(r2, r4)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
        Lc7:
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forResult(r0)     // Catch: com.google.mlkit.common.MlKitException -> Lcd
            return r0
        Lcd:
            r0 = move-exception
            com.google.mlkit.common.MlKitException r1 = new com.google.mlkit.common.MlKitException
            java.lang.String r2 = "Failed to ensure the model is downloaded."
            r1.<init>(r2, r3, r0)
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.ensureModelDownloaded():com.google.android.gms.tasks.Task");
    }

    public synchronized ParcelFileDescriptor getDownloadedFile() {
        ParcelFileDescriptor parcelFileDescriptor;
        DownloadManager downloadManager = this.zzf;
        Long downloadingId = getDownloadingId();
        parcelFileDescriptor = null;
        if (downloadManager != null && downloadingId != null) {
            try {
                parcelFileDescriptor = downloadManager.openDownloadedFile(downloadingId.longValue());
            } catch (FileNotFoundException e) {
                zza.e("ModelDownloadManager", "Downloaded file is not found");
            }
        }
        return parcelFileDescriptor;
    }

    public synchronized Long getDownloadingId() {
        return this.zzj.getDownloadingModelId(this.zzg);
    }

    public synchronized String getDownloadingModelHash() {
        return this.zzj.getDownloadingModelHash(this.zzg);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x006a, code lost:
    
        if (r3.intValue() != 16) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0048 A[Catch: all -> 0x003d, TRY_ENTER, TryCatch #1 {all -> 0x003d, blocks: (B:38:0x0028, B:40:0x002e, B:13:0x0048, B:15:0x004f, B:17:0x0056, B:19:0x005c, B:21:0x0064), top: B:37:0x0028, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.lang.Integer getDownloadingModelStatusCode() {
        /*
            r8 = this;
            monitor-enter(r8)
            android.app.DownloadManager r0 = r8.zzf     // Catch: java.lang.Throwable -> L7e
            java.lang.Long r1 = r8.getDownloadingId()     // Catch: java.lang.Throwable -> L7e
            r2 = 0
            if (r0 == 0) goto L7c
            if (r1 != 0) goto Lf
            goto L7c
        Lf:
            android.app.DownloadManager$Query r3 = new android.app.DownloadManager$Query     // Catch: java.lang.Throwable -> L7e
            r3.<init>()     // Catch: java.lang.Throwable -> L7e
            long r4 = r1.longValue()     // Catch: java.lang.Throwable -> L7e
            r1 = 1
            long[] r6 = new long[r1]     // Catch: java.lang.Throwable -> L7e
            r7 = 0
            r6[r7] = r4     // Catch: java.lang.Throwable -> L7e
            android.app.DownloadManager$Query r3 = r3.setFilterById(r6)     // Catch: java.lang.Throwable -> L7e
            android.database.Cursor r0 = r0.query(r3)     // Catch: java.lang.Throwable -> L7e
            if (r0 == 0) goto L3f
            boolean r3 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L3f
            java.lang.String r3 = "status"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L3d
            int r3 = r0.getInt(r3)     // Catch: java.lang.Throwable -> L3d
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L3d
            goto L40
        L3d:
            r1 = move-exception
            goto L73
        L3f:
            r3 = r2
        L40:
            if (r3 != 0) goto L48
            if (r0 == 0) goto L7c
            r0.close()     // Catch: java.lang.Throwable -> L7e
            goto L7c
        L48:
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L3d
            r5 = 2
            if (r4 == r5) goto L6d
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L3d
            r5 = 4
            if (r4 == r5) goto L6d
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L3d
            if (r4 == r1) goto L6d
            int r1 = r3.intValue()     // Catch: java.lang.Throwable -> L3d
            r4 = 8
            if (r1 == r4) goto L6d
            int r1 = r3.intValue()     // Catch: java.lang.Throwable -> L3d
            r4 = 16
            if (r1 == r4) goto L6d
            goto L6e
        L6d:
            r2 = r3
        L6e:
            r0.close()     // Catch: java.lang.Throwable -> L7e
            monitor-exit(r8)
            return r2
        L73:
            r0.close()     // Catch: java.lang.Throwable -> L77
            goto L7b
        L77:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch: java.lang.Throwable -> L7e
        L7b:
            throw r1     // Catch: java.lang.Throwable -> L7e
        L7c:
            monitor-exit(r8)
            return r2
        L7e:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L7e
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadingModelStatusCode():java.lang.Integer");
    }

    public boolean isModelDownloadedAndValid() throws MlKitException {
        try {
            if (modelExistsLocally()) {
                return true;
            }
        } catch (MlKitException e) {
            zza.d("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long downloadingId = getDownloadingId();
        String downloadingModelHash = getDownloadingModelHash();
        if (downloadingId == null || downloadingModelHash == null) {
            zza.d("ModelDownloadManager", "No new model is downloading.");
            removeOrCancelDownload();
            return false;
        }
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        GmsLogger gmsLogger = zza;
        StringBuilder sb = new StringBuilder();
        sb.append("Download Status code: ");
        sb.append(downloadingModelStatusCode);
        gmsLogger.d("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(downloadingModelStatusCode)));
        if (downloadingModelStatusCode != null) {
            return Objects.equal(downloadingModelStatusCode, 8) && zzi(downloadingModelHash) != null;
        }
        removeOrCancelDownload();
        return false;
    }

    public boolean modelExistsLocally() throws MlKitException {
        return this.zzk.modelExistsLocally(this.zzg.getUniqueModelNameForPersist(), this.zzh);
    }

    public synchronized void removeOrCancelDownload() throws MlKitException {
        DownloadManager downloadManager = this.zzf;
        Long downloadingId = getDownloadingId();
        if (downloadManager != null && downloadingId != null) {
            GmsLogger gmsLogger = zza;
            StringBuilder sb = new StringBuilder();
            sb.append("Cancel or remove existing downloading task: ");
            sb.append(downloadingId);
            gmsLogger.d("ModelDownloadManager", "Cancel or remove existing downloading task: ".concat(downloadingId.toString()));
            if (this.zzf.remove(downloadingId.longValue()) > 0 || getDownloadingModelStatusCode() == null) {
                ModelFileHelper modelFileHelper = this.zzk;
                RemoteModel remoteModel = this.zzg;
                modelFileHelper.deleteTempFilesInPrivateFolder(remoteModel.getUniqueModelNameForPersist(), remoteModel.getModelType());
                this.zzj.clearDownloadingModelInfo(this.zzg);
            }
        }
    }

    public void setDownloadConditions(DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        this.zzn = downloadConditions;
    }

    public synchronized void updateLatestModelHashAndType(String hash) throws MlKitException {
        this.zzj.setLatestModelHash(this.zzg, hash);
        removeOrCancelDownload();
    }

    final synchronized ModelInfo zzg() throws MlKitException {
        boolean z;
        boolean modelExistsLocally = modelExistsLocally();
        if (modelExistsLocally) {
            zzsh zzshVar = this.zzi;
            RemoteModel remoteModel = this.zzg;
            zzshVar.zzf(zzsk.zzg(), remoteModel, zzmu.NO_ERROR, false, remoteModel.getModelType(), zzna.LIVE);
        }
        ModelInfoRetrieverInterop modelInfoRetrieverInterop = this.zzl;
        if (modelInfoRetrieverInterop != null) {
            ModelInfo retrieveRemoteModelInfo = modelInfoRetrieverInterop.retrieveRemoteModelInfo(this.zzg);
            if (retrieveRemoteModelInfo == null) {
                return null;
            }
            MlKitContext mlKitContext = this.zze;
            RemoteModel remoteModel2 = this.zzg;
            String modelHash = retrieveRemoteModelInfo.getModelHash();
            SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(mlKitContext);
            boolean equals = modelHash.equals(sharedPrefManager.getIncompatibleModelHash(remoteModel2));
            boolean z2 = false;
            if (equals) {
                if (CommonUtils.getAppVersion(mlKitContext.getApplicationContext()).equals(sharedPrefManager.getPreviousAppVersion())) {
                    zza.e("ModelDownloadManager", "The model is incompatible with TFLite and the app is not upgraded, do not download");
                    z = false;
                } else {
                    z = true;
                }
            } else {
                z = true;
            }
            if (!modelExistsLocally) {
                this.zzj.clearLatestModelHash(this.zzg);
            }
            boolean z3 = !retrieveRemoteModelInfo.getModelHash().equals(SharedPrefManager.getInstance(this.zze).getLatestModelHash(this.zzg));
            if (!z) {
                z2 = z3;
            } else if (!modelExistsLocally || z3) {
                return retrieveRemoteModelInfo;
            }
            if (modelExistsLocally && (z2 ^ z)) {
                return null;
            }
            throw new MlKitException("The model " + this.zzg.getModelName() + " is incompatible with TFLite runtime", 100);
        }
        throw new MlKitException("Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase.", 14);
    }

    public final File zzi(String str) throws MlKitException {
        zza.d("ModelDownloadManager", "Model downloaded successfully");
        this.zzi.zzf(zzsk.zzg(), this.zzg, zzmu.NO_ERROR, true, this.zzh, zzna.SUCCEEDED);
        ParcelFileDescriptor downloadedFile = getDownloadedFile();
        if (downloadedFile == null) {
            removeOrCancelDownload();
            return null;
        }
        zza.d("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.zzm.moveModelToPrivateFolder(downloadedFile, str, this.zzg);
        } finally {
            removeOrCancelDownload();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MlKitException zzl(Long l) {
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (downloadManager != null && l != null) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        }
        int i = 13;
        String str = "Model downloading failed";
        if (cursor != null && cursor.moveToFirst()) {
            int i2 = cursor.getInt(cursor.getColumnIndex("reason"));
            if (i2 == 1006) {
                str = "Model downloading failed due to insufficient space on the device.";
                i = MlKitException.NOT_ENOUGH_SPACE;
            } else {
                str = "Model downloading failed due to error code: " + i2 + " from Android DownloadManager";
            }
        }
        return new MlKitException(str, i);
    }

    public int getFailureReason(Long downloadId) {
        int columnIndex;
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (downloadManager != null && downloadId != null) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(downloadId.longValue()));
        }
        if (cursor == null || !cursor.moveToFirst() || (columnIndex = cursor.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }
}
