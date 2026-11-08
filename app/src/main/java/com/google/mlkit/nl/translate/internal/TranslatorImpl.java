package com.google.mlkit.nl.translate.internal;

import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.sdkinternal.CloseGuard;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class TranslatorImpl implements Translator {
    public static final /* synthetic */ int zza = 0;
    private static final DownloadConditions zzb = new DownloadConditions.Builder().build();
    private final TranslatorOptions zzc;
    private final Provider zzd;
    private final AtomicReference zze;
    private final zzs zzf;
    private final Executor zzg;
    private final Task zzh;
    private final CancellationTokenSource zzi = new CancellationTokenSource();
    private CloseGuard zzj;

    /* compiled from: com.google.mlkit:translate@@17.0.3 */
    /* loaded from: classes25.dex */
    public static class Factory {
        private final Provider zza;
        private final zzi zzb;
        private final zzq zzc;
        private final zzae zzd;
        private final ExecutorSelector zze;
        private final zzp zzf;
        private final CloseGuard.Factory zzg;

        public Factory(Provider provider, zzi zziVar, zzq zzqVar, zzae zzaeVar, ExecutorSelector executorSelector, zzp zzpVar, CloseGuard.Factory factory) {
            this.zze = executorSelector;
            this.zzf = zzpVar;
            this.zza = provider;
            this.zzc = zzqVar;
            this.zzb = zziVar;
            this.zzd = zzaeVar;
            this.zzg = factory;
        }

        public final Translator zza(TranslatorOptions translatorOptions) {
            zzs zza = this.zzc.zza(translatorOptions.zza());
            TranslatorImpl translatorImpl = new TranslatorImpl(translatorOptions, this.zza, (TranslateJni) this.zzb.get(translatorOptions), zza, this.zze.getExecutorToUse(translatorOptions.zzf()), this.zzf, null);
            TranslatorImpl.zzb(translatorImpl, this.zzg, this.zzd);
            return translatorImpl;
        }
    }

    /* synthetic */ TranslatorImpl(TranslatorOptions translatorOptions, Provider provider, TranslateJni translateJni, zzs zzsVar, Executor executor, zzp zzpVar, zzar zzarVar) {
        this.zzc = translatorOptions;
        this.zzd = provider;
        this.zze = new AtomicReference(translateJni);
        this.zzf = zzsVar;
        this.zzg = executor;
        this.zzh = zzpVar.getMigrationTask();
    }

    static /* bridge */ /* synthetic */ void zzb(final TranslatorImpl translatorImpl, CloseGuard.Factory factory, zzae zzaeVar) {
        translatorImpl.zzj = factory.create(translatorImpl, 1, new Runnable() { // from class: com.google.mlkit.nl.translate.internal.zzan
            @Override // java.lang.Runnable
            public final void run() {
                TranslatorImpl.this.zzc();
            }
        });
        ((TranslateJni) translatorImpl.zze.get()).pin();
        translatorImpl.zzf.zzx();
        zzaeVar.zzb();
    }

    @Override // com.google.mlkit.nl.translate.Translator, java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void close() {
        this.zzj.close();
    }

    @Override // com.google.mlkit.nl.translate.Translator
    public final Task<Void> downloadModelIfNeeded() {
        DownloadConditions downloadConditions = zzb;
        return this.zzh.continueWithTask(MLTaskExecutor.workerThreadExecutor(), new zzaq(this, downloadConditions));
    }

    @Override // com.google.mlkit.nl.translate.Translator
    public final Task<String> translate(final String str) {
        Preconditions.checkNotNull(str, "Input can't be null");
        final TranslateJni translateJni = (TranslateJni) this.zze.get();
        Preconditions.checkState(translateJni != null, "Translator has been closed");
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final boolean z = !translateJni.isLoaded();
        return translateJni.callAfterLoad(this.zzg, new Callable() { // from class: com.google.mlkit.nl.translate.internal.zzao
            @Override // java.util.concurrent.Callable
            public final Object call() {
                int i = TranslatorImpl.zza;
                return TranslateJni.this.zzd(str);
            }
        }, this.zzi.getToken()).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.nl.translate.internal.zzap
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TranslatorImpl.this.zzd(str, z, elapsedRealtime, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(DownloadConditions downloadConditions, Task task) throws Exception {
        com.google.android.gms.internal.mlkit_translate.zzy zzd;
        Preconditions.checkHandlerThread(MLTaskExecutor.getInstance().getHandler());
        com.google.android.gms.internal.mlkit_translate.zzq zzqVar = new com.google.android.gms.internal.mlkit_translate.zzq();
        TranslatorOptions translatorOptions = this.zzc;
        String zzd2 = translatorOptions.zzd();
        String zze = translatorOptions.zze();
        int i = zzac.zza;
        if (zzd2.equals(zze)) {
            zzd = com.google.android.gms.internal.mlkit_translate.zzy.zzj();
        } else {
            com.google.android.gms.internal.mlkit_translate.zzx zzxVar = new com.google.android.gms.internal.mlkit_translate.zzx();
            if (!zzd2.equals(TranslateLanguage.ENGLISH)) {
                zzxVar.zzc(zzd2);
            }
            if (!zze.equals(TranslateLanguage.ENGLISH)) {
                zzxVar.zzc(zze);
            }
            zzd = zzxVar.zzd();
        }
        com.google.android.gms.internal.mlkit_translate.zzak it = zzd.iterator();
        while (it.hasNext()) {
            zzqVar.zzc(((zzz) this.zzd.get()).zza(new TranslateRemoteModel.Builder((String) it.next()).build(), true).zzb(downloadConditions));
        }
        return Tasks.whenAll(zzqVar.zzd());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc() {
        this.zzi.cancel();
        TranslateJni translateJni = (TranslateJni) this.zze.getAndSet(null);
        Preconditions.checkState(translateJni != null);
        translateJni.unpin(this.zzg);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(String str, boolean z, long j, Task task) {
        this.zzf.zzy(str, z, SystemClock.elapsedRealtime() - j, task);
    }

    @Override // com.google.mlkit.nl.translate.Translator
    public final Task<Void> downloadModelIfNeeded(DownloadConditions downloadConditions) {
        return this.zzh.continueWithTask(MLTaskExecutor.workerThreadExecutor(), new zzaq(this, downloadConditions));
    }
}
