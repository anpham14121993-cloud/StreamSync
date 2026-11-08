package com.google.android.gms.internal.mlkit_translate;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzpc {
    private final String zza;
    private final String zzb;
    private final zzpa zzc;
    private final String zzd;
    private final String zze;
    private final zzoz zzf;
    private final Long zzg;
    private final Boolean zzh;
    private final Boolean zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzpc(zzoy zzoyVar, zzpb zzpbVar) {
        String str;
        zzpa zzpaVar;
        String str2;
        zzoz zzozVar;
        str = zzoyVar.zza;
        this.zza = str;
        this.zzb = null;
        zzpaVar = zzoyVar.zzb;
        this.zzc = zzpaVar;
        this.zzd = null;
        str2 = zzoyVar.zzc;
        this.zze = str2;
        zzozVar = zzoyVar.zzd;
        this.zzf = zzozVar;
        this.zzg = null;
        this.zzh = null;
        this.zzi = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzpc)) {
            return false;
        }
        zzpc zzpcVar = (zzpc) obj;
        if (Objects.equal(this.zza, zzpcVar.zza)) {
            String str = zzpcVar.zzb;
            if (Objects.equal(null, null) && Objects.equal(this.zzc, zzpcVar.zzc)) {
                String str2 = zzpcVar.zzd;
                if (Objects.equal(null, null) && Objects.equal(this.zze, zzpcVar.zze) && Objects.equal(this.zzf, zzpcVar.zzf)) {
                    Long l = zzpcVar.zzg;
                    if (Objects.equal(null, null)) {
                        Boolean bool = zzpcVar.zzh;
                        if (Objects.equal(null, null)) {
                            Boolean bool2 = zzpcVar.zzi;
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

    public final zzoz zza() {
        return this.zzf;
    }

    public final zzpa zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zza;
    }
}
