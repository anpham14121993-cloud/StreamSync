package com.google.android.gms.internal.mlkit_translate;

import android.os.SystemClock;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzud {
    boolean zza;
    private long zzb;
    private long zzc;
    private final zzq zzd = new zzq();
    private final zzq zze = new zzq();
    private final zzq zzf = new zzq();
    private int zzg;

    public final zzsh zza() {
        zzf.zzf(this.zzb != 0);
        zzf.zzf(this.zzc != 0);
        long j = this.zzc - this.zzb;
        zzsh zzshVar = new zzsh();
        zzshVar.zzd(Long.valueOf(j));
        zzshVar.zzh(this.zzd.zzd());
        zzshVar.zzg(this.zze.zzd());
        zzshVar.zze(this.zzf.zzd());
        int i = this.zzg;
        if (i != 0) {
            zzshVar.zzf(Integer.valueOf(i));
        }
        return zzshVar;
    }

    public final void zzb(zzsi zzsiVar) {
        this.zzf.zzc(zzsiVar);
    }

    public final void zzc(zzsi zzsiVar) {
        this.zze.zzc(zzsiVar);
    }

    public final void zzd(zzsi zzsiVar) {
        if (this.zza) {
            this.zze.zzc(zzsiVar);
        } else {
            this.zzd.zzc(zzsiVar);
        }
    }

    public final void zze() {
        this.zzc = SystemClock.elapsedRealtime();
    }

    public final void zzf(int i) {
        this.zzg = i;
    }

    public final void zzg() {
        this.zzb = SystemClock.elapsedRealtime();
    }

    public final void zzh() {
        this.zza = true;
    }
}
