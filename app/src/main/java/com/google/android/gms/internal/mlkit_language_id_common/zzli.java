package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public final class zzli {
    private static zzu zza;
    private static final zzw zzb = zzw.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzlh zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzli(Context context, final SharedPrefManager sharedPrefManager, zzlh zzlhVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzlhVar;
        zzlu.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzle
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzli.this.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzlf
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        this.zzj = zzb.containsKey(this.zzi) ? DynamiteModule.getRemoteVersion(context, (String) zzb.get(this.zzi)) : -1;
    }

    private static synchronized zzu zzf() {
        synchronized (zzli.class) {
            zzu zzuVar = zza;
            if (zzuVar != null) {
                return zzuVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzr zzrVar = new zzr();
            for (int i = 0; i < locales.size(); i++) {
                zzrVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzu zzc = zzrVar.zzc();
            zza = zzc;
            return zzc;
        }
    }

    private final String zzg() {
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
    public final /* synthetic */ void zzb(zzla zzlaVar, zzhy zzhyVar, String str) {
        String mlSdkInstanceId;
        zzlaVar.zzb(zzhyVar);
        String zzd = zzlaVar.zzd();
        zzkc zzkcVar = new zzkc();
        zzkcVar.zzb(this.zzc);
        zzkcVar.zzc(this.zzd);
        zzkcVar.zzh(zzf());
        zzkcVar.zzg(true);
        zzkcVar.zzl(zzd);
        zzkcVar.zzj(str);
        if (this.zzh.isSuccessful()) {
            mlSdkInstanceId = (String) this.zzh.getResult();
        } else {
            mlSdkInstanceId = this.zzf.getMlSdkInstanceId();
        }
        zzkcVar.zzi(mlSdkInstanceId);
        zzkcVar.zzd(10);
        zzkcVar.zzk(Integer.valueOf(this.zzj));
        zzlaVar.zzc(zzkcVar);
        this.zze.zza(zzlaVar);
    }

    public final void zzc(zzla zzlaVar, zzhy zzhyVar) {
        zzd(zzlaVar, zzhyVar, zzg());
    }

    public final void zzd(final zzla zzlaVar, final zzhy zzhyVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_language_id_common.zzlg
            @Override // java.lang.Runnable
            public final void run() {
                zzli.this.zzb(zzlaVar, zzhyVar, str);
            }
        });
    }

    public final void zze(com.google.mlkit.nl.languageid.internal.zzf zzfVar, zzhy zzhyVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzk.get(zzhyVar) != null && elapsedRealtime - ((Long) this.zzk.get(zzhyVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.zzk.put(zzhyVar, Long.valueOf(elapsedRealtime));
        zzd(zzfVar.zza(), zzhyVar, zzg());
    }
}
