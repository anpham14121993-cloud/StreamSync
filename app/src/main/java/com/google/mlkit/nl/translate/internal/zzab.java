package com.google.mlkit.nl.translate.internal;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_translate.zzbm;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzab {
    public static final /* synthetic */ int zza = 0;
    private static final GmsLogger zzb = new GmsLogger("TranslateModelLoader", "");
    private final zzh zzc;
    private final zzy zzd;
    private Task zze;
    private CancellationTokenSource zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzab(zzh zzhVar, zzy zzyVar, zzaa zzaaVar) {
        this.zzc = zzhVar;
        this.zzd = zzyVar;
    }

    private final void zzg() throws MlKitException {
        if (!this.zzc.zzj()) {
            zzb.d("TranslateModelLoader", "No existing model file");
            throw new MlKitException("No existing model file", 13);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(DownloadConditions downloadConditions, Task task) throws Exception {
        if (task.isCanceled()) {
            return Tasks.forResult(com.google.android.gms.internal.mlkit_translate.zze.zzb());
        }
        return this.zzc.zza(downloadConditions);
    }

    public final Task zzb(final DownloadConditions downloadConditions) {
        double d;
        Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
        if (this.zze == null) {
            zzb.d("TranslateModelLoader", "Initial loading, check for model updates.");
            this.zzf = new CancellationTokenSource();
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource(this.zzf.getToken());
            d = this.zzd.zza;
            MLTaskExecutor.getInstance().scheduleRunnableDelayed(new Runnable() { // from class: com.google.mlkit.nl.translate.internal.zzu
                @Override // java.lang.Runnable
                public final void run() {
                    int i = zzab.zza;
                    TaskCompletionSource.this.trySetResult(null);
                }
            }, (long) (d * 1000.0d));
            this.zze = taskCompletionSource.getTask().continueWithTask(zzbm.zza(), new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzv
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzab.this.zza(downloadConditions, task);
                }
            }).continueWith(zzbm.zza(), new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzw
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    zzab.this.zzc(task);
                    return null;
                }
            });
        }
        return this.zze.continueWith(zzbm.zza(), new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzx
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzab.this.zzd(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Void zzc(Task task) throws Exception {
        this.zze = null;
        Exception exception = task.getException();
        if (exception != null) {
            zzy.zzb(this.zzd);
        }
        if (exception != null || !((com.google.android.gms.internal.mlkit_translate.zze) task.getResult()).zza()) {
            throw new MlKitException("Model not downloaded.", 13, exception);
        }
        this.zzd.zza = 0.0d;
        zzg();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Void zzd(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Void) task.getResult();
        }
        try {
            zzb.d("TranslateModelLoader", "Try to load newly downloaded model file.");
            if (this.zzc.zzb() != null) {
                return null;
            }
            throw new MlKitException("Newly downloaded model file could not be loaded.", 13);
        } catch (MlKitException e) {
            zzb.d("TranslateModelLoader", "Loading existing model file.");
            zzg();
            return null;
        }
    }

    public final void zze() throws MlKitException {
        CancellationTokenSource cancellationTokenSource = this.zzf;
        if (cancellationTokenSource != null) {
            cancellationTokenSource.cancel();
        }
        this.zzc.zzf();
        this.zze = null;
    }

    public final boolean zzf() {
        return this.zzc.zzj();
    }
}
