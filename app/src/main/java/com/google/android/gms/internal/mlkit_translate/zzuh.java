package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzuh {
    private final zztw zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;

    public zzuh(zztw zztwVar, String str, String str2, long j) {
        this.zza = zztwVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = j;
    }

    public final long zza() {
        return this.zzd;
    }

    public final zztw zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        return System.currentTimeMillis() >= this.zzd;
    }
}
