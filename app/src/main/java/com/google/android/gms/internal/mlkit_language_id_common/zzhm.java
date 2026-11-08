package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public final class zzhm {
    private Long zza;
    private zzhx zzb;
    private Boolean zzc;

    public final zzhm zza(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzhm zzb(zzhx zzhxVar) {
        this.zzb = zzhxVar;
        return this;
    }

    public final zzhm zzc(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzho zzd() {
        return new zzho(this, null);
    }
}
