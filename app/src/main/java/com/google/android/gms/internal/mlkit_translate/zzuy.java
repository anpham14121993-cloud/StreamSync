package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzuy {
    private static zzt zza;
    private static final zzw zzb = zzw.zzd("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzuq zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;

    public zzuy(Context context, final SharedPrefManager sharedPrefManager, zzuq zzuqVar, String str) {
        new HashMap();
        new HashMap();
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzuqVar;
        zzwj.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_translate.zzuv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzuy.this.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        Objects.requireNonNull(sharedPrefManager);
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_translate.zzuw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        this.zzj = zzb.containsKey(this.zzi) ? DynamiteModule.getRemoteVersion(context, (String) zzb.get(this.zzi)) : -1;
    }

    private static synchronized zzt zzg() {
        synchronized (zzuy.class) {
            zzt zztVar = zza;
            if (zztVar != null) {
                return zztVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzq zzqVar = new zzq();
            for (int i = 0; i < locales.size(); i++) {
                zzqVar.zzc(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzt zzd = zzqVar.zzd();
            zza = zzd;
            return zzd;
        }
    }

    private final zzsu zzh(String str, String str2) {
        String mlSdkInstanceId;
        zzsu zzsuVar = new zzsu();
        zzsuVar.zzb(this.zzc);
        zzsuVar.zzc(this.zzd);
        zzsuVar.zzh(zzg());
        zzsuVar.zzg(true);
        zzsuVar.zzl(str);
        zzsuVar.zzj(str2);
        if (this.zzh.isSuccessful()) {
            mlSdkInstanceId = (String) this.zzh.getResult();
        } else {
            mlSdkInstanceId = this.zzf.getMlSdkInstanceId();
        }
        zzsuVar.zzi(mlSdkInstanceId);
        zzsuVar.zzd(10);
        zzsuVar.zzk(Integer.valueOf(this.zzj));
        return zzsuVar;
    }

    private final String zzi() {
        if (this.zzg.isSuccessful()) {
            return (String) this.zzg.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzup zzupVar, zzoq zzoqVar, String str) {
        zzupVar.zza(zzoqVar);
        zzupVar.zzc(zzh(zzupVar.zzd(), str));
        this.zze.zza(zzupVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzup zzupVar, zzvc zzvcVar, RemoteModel remoteModel) {
        zzupVar.zza(zzoq.MODEL_DOWNLOAD);
        zzupVar.zzc(zzh(zzvcVar.zze(), zzi()));
        zzupVar.zzb(zzvn.zza(remoteModel, this.zzf, zzvcVar));
        this.zze.zza(zzupVar);
    }

    public final void zzd(zzup zzupVar, zzoq zzoqVar) {
        zze(zzupVar, zzoqVar, zzi());
    }

    public final void zze(final zzup zzupVar, final zzoq zzoqVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_translate.zzuu
            @Override // java.lang.Runnable
            public final void run() {
                zzuy.this.zzb(zzupVar, zzoqVar, str);
            }
        });
    }

    public final void zzf(final zzup zzupVar, final RemoteModel remoteModel, final zzvc zzvcVar) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_translate.zzux
            @Override // java.lang.Runnable
            public final void run() {
                zzuy.this.zzc(zzupVar, zzvcVar, remoteModel);
            }
        });
    }
}
