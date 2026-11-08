package com.google.android.gms.internal.mlkit_common;

import android.content.Context;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzh {
    public static final zzh zza;
    private final boolean zzb;
    private final boolean zzc = false;
    private final zzaf zzd;

    static {
        zze zzeVar = null;
        zzf zzfVar = new zzf(zzeVar);
        zzfVar.zzb();
        zza = zzfVar.zzd();
        zzf zzfVar2 = new zzf(zzeVar);
        zzfVar2.zzb();
        zzfVar2.zza(new zzd());
        zzfVar2.zzd();
        zzf zzfVar3 = new zzf(zzeVar);
        zzfVar3.zzc();
        zzfVar3.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzh(boolean z, boolean z2, zzaf zzafVar, zzg zzgVar) {
        this.zzb = z;
        this.zzd = zzafVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zza(zzh zzhVar) {
        boolean z = zzhVar.zzc;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ int zzc(zzh zzhVar, Context context, zzj zzjVar) {
        zzaf zzafVar = zzhVar.zzd;
        int size = zzafVar.size();
        int i = 0;
        while (i < size) {
            int zza2 = ((zzk) zzafVar.get(i)).zza(context, zzjVar, zzhVar.zzb) - 1;
            i++;
            switch (zza2) {
                case 1:
                    return 2;
            }
        }
        return 3;
    }
}
