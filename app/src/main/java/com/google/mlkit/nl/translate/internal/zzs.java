package com.google.mlkit.nl.translate.internal;

import android.os.SystemClock;
import com.google.android.gms.internal.mlkit_translate.zzoa;
import com.google.android.gms.internal.mlkit_translate.zzoc;
import com.google.android.gms.internal.mlkit_translate.zzoo;
import com.google.android.gms.internal.mlkit_translate.zzop;
import com.google.android.gms.internal.mlkit_translate.zzoq;
import com.google.android.gms.internal.mlkit_translate.zzor;
import com.google.android.gms.internal.mlkit_translate.zzov;
import com.google.android.gms.internal.mlkit_translate.zzsb;
import com.google.android.gms.internal.mlkit_translate.zzsc;
import com.google.android.gms.internal.mlkit_translate.zzte;
import com.google.android.gms.internal.mlkit_translate.zztv;
import com.google.android.gms.internal.mlkit_translate.zzup;
import com.google.android.gms.internal.mlkit_translate.zzuy;
import com.google.android.gms.internal.mlkit_translate.zzva;
import com.google.android.gms.internal.mlkit_translate.zzvb;
import com.google.android.gms.internal.mlkit_translate.zzvc;
import com.google.android.gms.internal.mlkit_translate.zzvd;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzs {
    private final zzuy zza;
    private final zzva zzb;
    private final zzte zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzs(zzuy zzuyVar, zzva zzvaVar, zzte zzteVar, zzr zzrVar) {
        this.zza = zzuyVar;
        this.zzc = zzteVar;
        this.zzb = zzvaVar;
    }

    private final void zzA(zzsb zzsbVar, zzoq zzoqVar) {
        zzor zzorVar = new zzor();
        zzorVar.zze(zzoo.TYPE_THICK);
        zzorVar.zzj(zzsbVar.zzj());
        this.zza.zzd(zzvd.zzf(zzorVar), zzoqVar);
    }

    private final void zzB(zzsc zzscVar, zzoq zzoqVar) {
        zzsb zzsbVar = new zzsb();
        zzsbVar.zze(this.zzc);
        zzsbVar.zzg(zzscVar);
        zzA(zzsbVar, zzoqVar);
    }

    private final zzsb zzz(zzoc zzocVar) {
        zzsb zzsbVar = new zzsb();
        zzsbVar.zze(this.zzc);
        zzsbVar.zzb(zzocVar);
        return zzsbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(RemoteModel remoteModel, zzop zzopVar, boolean z, ModelType modelType, zzov zzovVar) {
        zzup zzg = zzvd.zzg();
        zzvb zzh = zzvc.zzh();
        zzh.zzf(true);
        zzh.zzd(modelType);
        zzh.zzb(zzopVar);
        zzh.zza(zzovVar);
        this.zza.zzf(zzg, remoteModel, zzh.zzh());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(RemoteModel remoteModel, boolean z, int i) {
        zzup zzg = zzvd.zzg();
        zzvb zzh = zzvc.zzh();
        zzh.zzf(true);
        zzh.zzd(remoteModel.getModelType());
        zzh.zza(zzov.FAILED);
        zzh.zzb(zzop.DOWNLOAD_FAILED);
        zzh.zzc(i);
        this.zza.zzf(zzg, remoteModel, zzh.zzh());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc() {
        zzB(zzsc.DOWNLOAD_MANAGER_CANNOT_RESUME, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd() {
        zzB(zzsc.DOWNLOAD_MANAGER_DEVICE_NOT_FOUND, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze() {
        zzB(zzsc.DOWNLOAD_MANAGER_FILE_ALREADY_EXISTS, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf() {
        zzB(zzsc.DOWNLOAD_MANAGER_FILE_ERROR, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg() {
        zzB(zzsc.DOWNLOAD_MANAGER_HTTP_DATA_ERROR, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(int i) {
        zzsc zzb = zzsc.zzb(i);
        if (zzb != zzsc.NO_ERROR) {
            zzB(zzb, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
        } else {
            zzB(zzsc.DOWNLOAD_MANAGER_HTTP_UNKNOWN_STATUS, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi() {
        zzB(zzsc.DOWNLOAD_MANAGER_INSUFFICIENT_SPACE, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj() {
        zzB(zzsc.DOWNLOAD_MANAGER_SERVICE_MISSING, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk() {
        zzB(zzsc.DOWNLOAD_MANAGER_TOO_MANY_REDIRECTS, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzl() {
        zzB(zzsc.DOWNLOAD_MANAGER_UNHANDLED_HTTP_CODE, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzm() {
        zzB(zzsc.DOWNLOAD_MANAGER_UNKNOWN_ERROR, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzn() {
        zzB(zzsc.NO_ERROR, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    public final void zzo(long j, Exception exc) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        zztv.zzd("translate-load").zzb(elapsedRealtime);
        zzoa zzoaVar = new zzoa();
        zzoaVar.zza(Long.valueOf(elapsedRealtime));
        if (exc != null) {
            zzoaVar.zzb(zzop.UNKNOWN_ERROR);
        }
        zzsb zzz = zzz(zzoaVar.zzd());
        if (exc != null && (exc.getCause() instanceof zzk)) {
            zzz.zzd(Integer.valueOf(((zzk) exc.getCause()).zza()));
        }
        zzA(zzz, zzoq.ON_DEVICE_TRANSLATOR_LOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzp() {
        zzB(zzsc.METADATA_FILE_UNAVAILABLE, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzq() {
        zzB(zzsc.METADATA_HASH_NOT_FOUND, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzr() {
        zzB(zzsc.METADATA_JSON_INVALID, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzs() {
        zzB(zzsc.METADATA_ENTRY_NOT_FOUND, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzt() {
        zzB(zzsc.POST_DOWNLOAD_MOVE_FILE_FAILED, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzu() {
        zzB(zzsc.POST_DOWNLOAD_FILE_NOT_FOUND, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzv() {
        zzB(zzsc.POST_DOWNLOAD_UNZIP_FAILED, zzoq.ON_DEVICE_TRANSLATOR_DOWNLOAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzw() {
        zzB(zzsc.RAPID_RESPONSE_COULD_NOT_BE_WRITTEN, zzoq.ON_DEVICE_TRANSLATOR_LOAD);
    }

    public final void zzx() {
        zzA(zzz(new zzoa().zzd()), zzoq.ON_DEVICE_TRANSLATOR_CREATE);
    }

    public final void zzy(String str, boolean z, long j, Task task) {
        zztv.zzd("translate-inference").zzb(j);
        zzop zzopVar = task.isSuccessful() ? zzop.NO_ERROR : zzop.UNKNOWN_ERROR;
        zzoa zzoaVar = new zzoa();
        zzoaVar.zza(Long.valueOf(j));
        zzoaVar.zzc(Boolean.valueOf(z));
        zzoaVar.zzb(zzopVar);
        zzsb zzz = zzz(zzoaVar.zzd());
        zzz.zzc(Integer.valueOf(str.length()));
        zzz.zzf(Integer.valueOf(task.isSuccessful() ? ((String) task.getResult()).length() : -1));
        Exception exception = task.getException();
        if (exception != null) {
            if (exception.getCause() instanceof zzk) {
                zzz.zzd(Integer.valueOf(((zzk) exception.getCause()).zza()));
            } else if (exception.getCause() instanceof zzm) {
                zzz.zzh(Integer.valueOf(((zzm) exception.getCause()).zza()));
            }
        }
        zzA(zzz, zzoq.ON_DEVICE_TRANSLATOR_TRANSLATE);
        zzva zzvaVar = this.zzb;
        long currentTimeMillis = System.currentTimeMillis();
        zzvaVar.zzc(24605, zzopVar.zza(), currentTimeMillis - j, currentTimeMillis);
    }
}
