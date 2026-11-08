package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzsh {
    private Long zza;
    private zzt zzb;
    private zzt zzc;
    private zzt zzd;
    private Integer zze;

    public final zzsh zzd(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzsh zze(zzt zztVar) {
        this.zzb = zztVar;
        return this;
    }

    public final zzsh zzf(Integer num) {
        this.zze = num;
        return this;
    }

    public final zzsh zzg(zzt zztVar) {
        this.zzc = zztVar;
        return this;
    }

    public final zzsh zzh(zzt zztVar) {
        this.zzd = zztVar;
        return this;
    }

    public final zzsk zzi() {
        return new zzsk(this, null);
    }
}
