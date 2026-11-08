package com.google.android.gms.internal.mlkit_translate;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public abstract class zzvc {
    public static zzvb zzh() {
        zzum zzumVar = new zzum();
        zzumVar.zzg("NA");
        zzumVar.zzf(false);
        zzumVar.zze(false);
        zzumVar.zzd(ModelType.UNKNOWN);
        zzumVar.zzb(zzop.NO_ERROR);
        zzumVar.zza(zzov.UNKNOWN_STATUS);
        zzumVar.zzc(0);
        return zzumVar;
    }

    public abstract int zza();

    public abstract ModelType zzb();

    public abstract zzop zzc();

    public abstract zzov zzd();

    public abstract String zze();

    public abstract boolean zzf();

    public abstract boolean zzg();
}
