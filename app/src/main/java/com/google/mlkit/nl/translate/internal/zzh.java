package com.google.mlkit.nl.translate.internal;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_translate.zzas;
import com.google.android.gms.internal.mlkit_translate.zzat;
import com.google.android.gms.internal.mlkit_translate.zzop;
import com.google.android.gms.internal.mlkit_translate.zzov;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzh {
    private static final GmsLogger zza = new GmsLogger("TranslateDLManager", "");
    private final Context zzb;
    private final RemoteModelFileManager zzc;
    private final TranslateRemoteModel zzd;
    private final zzs zze;
    private final zzt zzf;
    private final DownloadManager zzg;
    private final ModelFileHelper zzh;
    private final SharedPrefManager zzi;
    private final SharedPreferences zzj;
    private TaskCompletionSource zzk;
    private List zzl;
    private DownloadConditions zzm;
    private BroadcastReceiver zzn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(Context context, RemoteModelFileManager remoteModelFileManager, TranslateRemoteModel translateRemoteModel, zzae zzaeVar, zzs zzsVar, zzt zztVar, DownloadManager downloadManager, ModelFileHelper modelFileHelper, SharedPrefManager sharedPrefManager, zzb zzbVar) {
        this.zzb = context;
        this.zzc = remoteModelFileManager;
        this.zzd = translateRemoteModel;
        this.zze = zzsVar;
        this.zzf = zztVar;
        if (downloadManager == null) {
            zza.d("TranslateDLManager", "Download manager service is not available in the service.");
        }
        this.zzg = downloadManager;
        this.zzh = modelFileHelper;
        this.zzi = sharedPrefManager;
        this.zzj = context.getSharedPreferences("com.google.mlkit.translate.download_manager", 0);
        this.zzk = new TaskCompletionSource();
    }

    private final int zzk() {
        List list = this.zzl;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        List list2 = (List) Preconditions.checkNotNull(this.zzl);
        String string = this.zzj.getString("last_uri_for_".concat(String.valueOf(((ModelInfo) list2.get(0)).getModelHash())), null);
        if (string != null) {
            int i = 0;
            while (i < list2.size()) {
                int i2 = i + 1;
                if (string.equals(((ModelInfo) list2.get(i)).getModelUri().toString())) {
                    return i2;
                }
                i = i2;
            }
            zza.e("TranslateDLManager", "Stored LAST_URI_ATTEMPTED was not found in ModelInfo");
        }
        return 0;
    }

    private final Task zzl() {
        Preconditions.checkState(this.zzm != null);
        int zzk = zzk();
        List list = this.zzl;
        if (list == null || zzk >= list.size()) {
            return Tasks.forResult(com.google.android.gms.internal.mlkit_translate.zze.zzb());
        }
        ModelInfo modelInfo = (ModelInfo) this.zzl.get(zzk);
        try {
            Preconditions.checkState(this.zzm != null);
            DownloadConditions downloadConditions = (DownloadConditions) Preconditions.checkNotNull(this.zzm);
            String zze = zze();
            DownloadManager.Request request = null;
            if (zze == null || !zze.equals(modelInfo.getModelHash()) || zzc() == null) {
                zza.d("TranslateDLManager", "Need to download a new model.");
                boolean zzi = zzi(this.zzd, modelInfo.getModelHash());
                zzh();
                DownloadManager.Request request2 = new DownloadManager.Request(modelInfo.getModelUri());
                if (!zzj() || zzi) {
                    request2.setRequiresCharging(downloadConditions.isChargingRequired());
                    if (downloadConditions.isWifiRequired()) {
                        request2.setAllowedNetworkTypes(2);
                    }
                    request2.addRequestHeader(HttpHeaders.USER_AGENT, "TRANSLATE_OPM5_TEST_1");
                    request = request2;
                } else {
                    zza.d("TranslateDLManager", "Remote model hash is simliar to local model. Skipping download.");
                }
            } else {
                zza.d("TranslateDLManager", "New model is already in downloading, do nothing.");
            }
            if (request != null || zzd() != null) {
                if (request != null) {
                    Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
                    DownloadManager downloadManager = this.zzg;
                    if (downloadManager == null) {
                        this.zze.zzj();
                    } else {
                        long enqueue = downloadManager.enqueue(request);
                        zza.d("TranslateDLManager", "Schedule a new downloading task: " + enqueue);
                        this.zzi.setDownloadingModelInfo(enqueue, modelInfo);
                        this.zzj.edit().putString("last_uri_for_".concat(String.valueOf(modelInfo.getModelHash())), modelInfo.getModelUri().toString()).commit();
                    }
                }
                Integer zzc = zzc();
                if (zzc == null || !(zzc.intValue() == 4 || zzc.intValue() == 1 || zzc.intValue() == 2)) {
                    MLTaskExecutor.getInstance().getHandler().post(new Runnable() { // from class: com.google.mlkit.nl.translate.internal.zzd
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzh.this.zzg();
                        }
                    });
                } else if (this.zzn == null) {
                    this.zzn = new zzf(this, this);
                    ContextCompat.registerReceiver(this.zzb, this.zzn, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), 2);
                }
                return this.zzk.getTask();
            }
            return Tasks.forResult(com.google.android.gms.internal.mlkit_translate.zze.zzb());
        } catch (MlKitException e) {
            return Tasks.forException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Task zza(DownloadConditions downloadConditions) {
        try {
            List zza2 = this.zzf.zza(this.zzb, this.zzd);
            ModelInfo modelInfo = (ModelInfo) zza2.get(0);
            boolean z = !zzj();
            if (z) {
                this.zzi.clearLatestModelHash(this.zzd);
            }
            boolean zzi = zzi(this.zzd, modelInfo.getModelHash());
            if (!z && !zzi) {
                zza2 = null;
            }
            this.zzl = zza2;
            List list = this.zzl;
            if (list == null || list.isEmpty()) {
                zza.d("TranslateDLManager", "No model updates for model: ".concat(String.valueOf(zzac.zzb(this.zzd.getLanguage()))));
                return Tasks.forResult(com.google.android.gms.internal.mlkit_translate.zze.zzb());
            }
            this.zzk = new TaskCompletionSource();
            this.zzm = downloadConditions;
            return zzl();
        } catch (MlKitException e) {
            return Tasks.forException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Finally extract failed */
    public final File zzb() throws MlKitException {
        Cursor query;
        int columnIndex;
        ParcelFileDescriptor parcelFileDescriptor;
        boolean zzc;
        Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
        Long zzd = zzd();
        String zze = zze();
        File file = null;
        if (zzd == null || zze == null) {
            zza.d("TranslateDLManager", "No new model is downloading.");
            zzh();
            return null;
        }
        Integer zzc2 = zzc();
        if (zzc2 == null) {
            zzh();
            this.zzk.setException(new MlKitException("No download", 13));
            return null;
        }
        GmsLogger gmsLogger = zza;
        StringBuilder sb = new StringBuilder();
        sb.append("Download Status code: ");
        sb.append(zzc2);
        gmsLogger.d("TranslateDLManager", "Download Status code: ".concat(zzc2.toString()));
        int i = 0;
        if (zzc2.intValue() != 8) {
            if (zzc2.intValue() != 16) {
                return null;
            }
            DownloadManager downloadManager = this.zzg;
            if (downloadManager != null && (query = downloadManager.query(new DownloadManager.Query().setFilterById(zzd.longValue()))) != null && query.moveToFirst() && (columnIndex = query.getColumnIndex("reason")) != -1) {
                i = query.getInt(columnIndex);
            }
            this.zze.zzb(this.zzd, true, i);
            zzh();
            this.zzk.setException(new MlKitException(String.format(Locale.US, "Downloading error %d", Integer.valueOf(i)), 13));
            return null;
        }
        BroadcastReceiver broadcastReceiver = this.zzn;
        if (broadcastReceiver != null) {
            this.zzb.unregisterReceiver(broadcastReceiver);
            this.zzn = null;
        }
        zza.d("TranslateDLManager", "Model downloaded successfully");
        this.zze.zza(this.zzd, zzop.NO_ERROR, true, ModelType.TRANSLATE, zzov.SUCCEEDED);
        try {
            Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
            if (this.zzg == null) {
                this.zze.zzj();
                parcelFileDescriptor = null;
            } else {
                Long zzd2 = zzd();
                if (zzd2 == null) {
                    parcelFileDescriptor = null;
                } else {
                    try {
                        parcelFileDescriptor = this.zzg.openDownloadedFile(zzd2.longValue());
                    } catch (FileNotFoundException e) {
                        zza.e("TranslateDLManager", "Downloaded file is not found");
                        parcelFileDescriptor = null;
                    }
                }
            }
            if (parcelFileDescriptor == null) {
                this.zze.zzu();
            } else {
                zza.d("TranslateDLManager", "moving downloaded model from external storage to private folder.");
                File moveModelToPrivateFolder = this.zzc.moveModelToPrivateFolder(parcelFileDescriptor, zze, this.zzd);
                if (moveModelToPrivateFolder == null) {
                    this.zze.zzt();
                } else {
                    Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
                    String zzb = zzac.zzb(this.zzd.getLanguage());
                    File modelDirUnsafe = this.zzc.getModelDirUnsafe(false);
                    final String format = String.format("dict.%1$s_%2$s", zzb, "25");
                    try {
                        File zza2 = zzat.zza();
                        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(moveModelToPrivateFolder));
                        try {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            while (nextEntry != null) {
                                if (!nextEntry.isDirectory()) {
                                    String name = nextEntry.getName();
                                    File file2 = new File(zza2, name);
                                    if (!file2.getCanonicalPath().startsWith(zza2.getCanonicalPath())) {
                                        throw new ZipException("Illegal name: ".concat(String.valueOf(name)));
                                    }
                                    File parentFile = file2.getParentFile();
                                    if (parentFile != null) {
                                        zzc.zza(parentFile);
                                    }
                                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                    try {
                                        int i2 = zzas.zza;
                                        byte[] bArr = new byte[8192];
                                        while (true) {
                                            int read = zipInputStream.read(bArr);
                                            if (read == -1) {
                                                break;
                                            }
                                            fileOutputStream.write(bArr, 0, read);
                                        }
                                        fileOutputStream.close();
                                    } finally {
                                    }
                                }
                                nextEntry = zipInputStream.getNextEntry();
                            }
                            zipInputStream.close();
                            if (!moveModelToPrivateFolder.delete()) {
                                throw new IOException("Zip file could not be deleted.");
                            }
                            File[] listFiles = zza2.listFiles(new FilenameFilter() { // from class: com.google.mlkit.nl.translate.internal.zza
                                @Override // java.io.FilenameFilter
                                public final boolean accept(File file3, String str) {
                                    return str.equals(format);
                                }
                            });
                            if (listFiles == null || listFiles.length != 1) {
                                throw new IOException("Unexpected behavior for inZipFolder inside the zip archive.");
                            }
                            File file3 = listFiles[0];
                            File[] listFiles2 = file3.listFiles();
                            if (listFiles2 == null) {
                                throw new IOException("Failed to list zip content files.");
                            }
                            zzc = zzc.zzc(modelDirUnsafe, false);
                            if (!zzc) {
                                throw new IOException("Unable to delete existing models");
                            }
                            int length = listFiles2.length;
                            while (i < length) {
                                File file4 = listFiles2[i];
                                if (!file4.renameTo(new File(modelDirUnsafe, file4.getName()))) {
                                    throw new IOException("Zip content file could not be moved.");
                                }
                                i++;
                            }
                            if (!file3.delete()) {
                                throw new IOException("Unzipped folder could not be deleted.");
                            }
                            zza.d("TranslateDLManager", "Moved the downloaded model to private folder successfully: ".concat(moveModelToPrivateFolder.toString()));
                            this.zzi.setLatestModelHash(this.zzd, zze);
                            file = modelDirUnsafe;
                        } finally {
                        }
                    } catch (IOException e2) {
                        zza.d("TranslateDLManager", "Could not unzip translate model file", e2);
                        this.zze.zzv();
                        throw new MlKitException("Could not unzip translate model file", 13, e2);
                    }
                }
            }
            zzh();
            this.zze.zzn();
            this.zzk.setResult(com.google.android.gms.internal.mlkit_translate.zze.zzc(zzd));
            return file;
        } catch (Throwable th) {
            zzh();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00cd, code lost:
    
        if (r3.intValue() != 16) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004e A[Catch: all -> 0x0042, TRY_ENTER, TryCatch #0 {all -> 0x0042, blocks: (B:54:0x002d, B:56:0x0033, B:14:0x004e, B:16:0x0056, B:20:0x006e, B:21:0x0074, B:22:0x0077, B:23:0x00aa, B:24:0x007a, B:25:0x0080, B:26:0x0086, B:27:0x008c, B:28:0x0092, B:29:0x0098, B:30:0x009e, B:31:0x00a4, B:32:0x00ad, B:34:0x00b4, B:36:0x00bb, B:38:0x00c1, B:40:0x00c9), top: B:53:0x002d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.lang.Integer zzc() {
        /*
            r8 = this;
            android.app.DownloadManager r0 = r8.zzg
            r1 = 0
            if (r0 != 0) goto Lb
            com.google.mlkit.nl.translate.internal.zzs r0 = r8.zze
            r0.zzj()
            return r1
        Lb:
            java.lang.Long r0 = r8.zzd()
            if (r0 != 0) goto L12
            goto L4d
        L12:
            android.app.DownloadManager r2 = r8.zzg
            android.app.DownloadManager$Query r3 = new android.app.DownloadManager$Query
            r3.<init>()
            long r4 = r0.longValue()
            r0 = 1
            long[] r6 = new long[r0]
            r7 = 0
            r6[r7] = r4
            android.app.DownloadManager$Query r3 = r3.setFilterById(r6)
            android.database.Cursor r2 = r2.query(r3)
            if (r2 == 0) goto L45
            boolean r3 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L42
            if (r3 == 0) goto L45
            java.lang.String r3 = "status"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L42
            int r3 = r2.getInt(r3)     // Catch: java.lang.Throwable -> L42
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L42
            goto L46
        L42:
            r0 = move-exception
            goto Ld5
        L45:
            r3 = r1
        L46:
            if (r3 != 0) goto L4e
            if (r2 == 0) goto L4d
            r2.close()
        L4d:
            return r1
        L4e:
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L42
            r5 = 16
            if (r4 != r5) goto Lad
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch: java.lang.Throwable -> L42
            android.database.Cursor r4 = (android.database.Cursor) r4     // Catch: java.lang.Throwable -> L42
            java.lang.String r6 = "reason"
            int r6 = r4.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L42
            int r4 = r4.getInt(r6)     // Catch: java.lang.Throwable -> L42
            r6 = 100
            if (r4 < r6) goto L74
            r6 = 599(0x257, float:8.4E-43)
            if (r4 > r6) goto L74
            com.google.mlkit.nl.translate.internal.zzs r6 = r8.zze     // Catch: java.lang.Throwable -> L42
            r6.zzh(r4)     // Catch: java.lang.Throwable -> L42
            goto Lad
        L74:
            switch(r4) {
                case 1001: goto La4;
                case 1002: goto L9e;
                case 1003: goto L77;
                case 1004: goto L98;
                case 1005: goto L92;
                case 1006: goto L8c;
                case 1007: goto L86;
                case 1008: goto L80;
                case 1009: goto L7a;
                default: goto L77;
            }     // Catch: java.lang.Throwable -> L42
        L77:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            goto Laa
        L7a:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zze()     // Catch: java.lang.Throwable -> L42
            goto Lad
        L80:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzc()     // Catch: java.lang.Throwable -> L42
            goto Lad
        L86:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzd()     // Catch: java.lang.Throwable -> L42
            goto Lad
        L8c:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzi()     // Catch: java.lang.Throwable -> L42
            goto Lad
        L92:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzk()     // Catch: java.lang.Throwable -> L42
            goto Lad
        L98:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzg()     // Catch: java.lang.Throwable -> L42
            goto Lad
        L9e:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzl()     // Catch: java.lang.Throwable -> L42
            goto Lad
        La4:
            com.google.mlkit.nl.translate.internal.zzs r4 = r8.zze     // Catch: java.lang.Throwable -> L42
            r4.zzf()     // Catch: java.lang.Throwable -> L42
            goto Lad
        Laa:
            r4.zzm()     // Catch: java.lang.Throwable -> L42
        Lad:
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L42
            r6 = 2
            if (r4 == r6) goto Ld0
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L42
            r6 = 4
            if (r4 == r6) goto Ld0
            int r4 = r3.intValue()     // Catch: java.lang.Throwable -> L42
            if (r4 == r0) goto Ld0
            int r0 = r3.intValue()     // Catch: java.lang.Throwable -> L42
            r4 = 8
            if (r0 == r4) goto Ld0
            int r0 = r3.intValue()     // Catch: java.lang.Throwable -> L42
            if (r0 == r5) goto Ld0
            goto Ld1
        Ld0:
            r1 = r3
        Ld1:
            r2.close()
            return r1
        Ld5:
            r2.close()     // Catch: java.lang.Throwable -> Ld9
            goto Ldd
        Ld9:
            r1 = move-exception
            r0.addSuppressed(r1)
        Ldd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.nl.translate.internal.zzh.zzc():java.lang.Integer");
    }

    final Long zzd() {
        return this.zzi.getDownloadingModelId(this.zzd);
    }

    final String zze() {
        return this.zzi.getDownloadingModelHash(this.zzd);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf() throws MlKitException {
        zzh();
        this.zzi.clearLatestModelHash(this.zzd);
        String zzb = zzac.zzb(this.zzd.getLanguage());
        File modelDirUnsafe = this.zzc.getModelDirUnsafe(false);
        String[] zzd = zzac.zzd(zzb);
        zzae.zzg(modelDirUnsafe, zzd[0], zzd[1]);
        zzae.zzg(modelDirUnsafe, zzd[1], zzd[0]);
        com.google.android.gms.internal.mlkit_translate.zzq zzqVar = new com.google.android.gms.internal.mlkit_translate.zzq();
        com.google.android.gms.internal.mlkit_translate.zzt zza2 = zzac.zza(zzb);
        int size = zza2.size();
        for (int i = 0; i < size; i++) {
            String str = (String) zza2.get(i);
            File file = new File(modelDirUnsafe, str);
            if (file.exists() && !file.delete()) {
                zzqVar.zzc(str);
            }
        }
        com.google.android.gms.internal.mlkit_translate.zzt zzd2 = zzqVar.zzd();
        if (!zzd2.isEmpty()) {
            throw new MlKitException("Couldn't delete model files ".concat(String.valueOf(TextUtils.join(", ", zzd2))), 13);
        }
        this.zzk.trySetException(new MlKitException("Download canceled", 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg() {
        List list;
        Integer zzc = zzc();
        if (zzc != null) {
            try {
                if (zzc.intValue() == 16 && (list = this.zzl) != null && list.size() > zzk()) {
                    this.zzi.clearDownloadingModelInfo(this.zzd);
                    zzl();
                    return;
                }
            } catch (MlKitException e) {
                this.zzk.setException(e);
                return;
            }
        }
        zzb();
    }

    final void zzh() throws MlKitException {
        Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
        if (this.zzg == null) {
            this.zze.zzj();
            return;
        }
        Long zzd = zzd();
        if (zzd == null) {
            return;
        }
        GmsLogger gmsLogger = zza;
        StringBuilder sb = new StringBuilder();
        sb.append("Cancel or remove existing downloading task: ");
        sb.append(zzd);
        gmsLogger.d("TranslateDLManager", "Cancel or remove existing downloading task: ".concat(zzd.toString()));
        if (this.zzg.remove(zzd.longValue()) <= 0 && zzc() != null) {
            return;
        }
        this.zzh.deleteTempFilesInPrivateFolder(TranslateRemoteModel.zza(zzac.zzb(this.zzd.getLanguage())), this.zzd.getModelType());
        this.zzi.clearDownloadingModelInfo(this.zzd);
        List list = this.zzl;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.zzj.edit().remove("last_uri_for_".concat(String.valueOf(((ModelInfo) this.zzl.get(0)).getModelHash()))).commit();
    }

    final boolean zzi(RemoteModel remoteModel, String str) {
        return !str.equals(this.zzi.getLatestModelHash(remoteModel));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzj() {
        String zzb = zzac.zzb(this.zzd.getLanguage());
        File modelDirUnsafe = this.zzc.getModelDirUnsafe(false);
        com.google.android.gms.internal.mlkit_translate.zzt zza2 = zzac.zza(zzb);
        int size = zza2.size();
        int i = 0;
        while (i < size) {
            boolean exists = new File(modelDirUnsafe, (String) zza2.get(i)).exists();
            i++;
            if (!exists) {
                return false;
            }
        }
        return true;
    }
}
