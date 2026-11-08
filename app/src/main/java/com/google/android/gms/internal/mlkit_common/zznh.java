package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zznh {
    private final String zza;
    private final String zzb;
    private final zznf zzc;
    private final String zzd;
    private final String zze;
    private final zzne zzf;
    private final Long zzg;
    private final Boolean zzh;
    private final Boolean zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zznh(zznd zzndVar, zzng zzngVar) {
        String str;
        zznf zznfVar;
        String str2;
        zzne zzneVar;
        str = zzndVar.zza;
        this.zza = str;
        this.zzb = null;
        zznfVar = zzndVar.zzb;
        this.zzc = zznfVar;
        this.zzd = null;
        str2 = zzndVar.zzc;
        this.zze = str2;
        zzneVar = zzndVar.zzd;
        this.zzf = zzneVar;
        this.zzg = null;
        this.zzh = null;
        this.zzi = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zznh)) {
            return false;
        }
        zznh zznhVar = (zznh) obj;
        if (Objects.equal(this.zza, zznhVar.zza)) {
            String str = zznhVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zznhVar.zzc)) {
                String str2 = zznhVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zznhVar.zze) && Objects.equal(this.zzf, zznhVar.zzf)) {
                    Long l = zznhVar.zzg;
                    if (Objects.equal(null, null)) {
                        Boolean bool = zznhVar.zzh;
                        if (Objects.equal(null, null)) {
                            Boolean bool2 = zznhVar.zzi;
                            if (Objects.equal(null, null)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, null, null, null);
    }

    public final zzne zza() {
        return this.zzf;
    }

    public final zznf zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zza;
    }
}
