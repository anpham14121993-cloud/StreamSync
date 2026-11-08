package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzoa {
    private Long zza;
    private zzop zzb;
    private Boolean zzc;

    public final zzoa zza(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzoa zzb(zzop zzopVar) {
        this.zzb = zzopVar;
        return this;
    }

    public final zzoa zzc(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzoc zzd() {
        return new zzoc(this, null);
    }
}
