package com.google.android.gms.internal.mlkit_translate;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzpg {
    private final zzpc zza;
    private final zzpe zzb;
    private final zzpe zzc;
    private final Boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzpg(zzpd zzpdVar, zzpf zzpfVar) {
        zzpc zzpcVar;
        zzpcVar = zzpdVar.zza;
        this.zza = zzpcVar;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzpg)) {
            return false;
        }
        zzpg zzpgVar = (zzpg) obj;
        if (Objects.equal(this.zza, zzpgVar.zza)) {
            zzpe zzpeVar = zzpgVar.zzb;
            if (Objects.equal(null, null)) {
                zzpe zzpeVar2 = zzpgVar.zzc;
                if (Objects.equal(null, null)) {
                    Boolean bool = zzpgVar.zzd;
                    if (Objects.equal(null, null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    public final zzpc zza() {
        return this.zza;
    }
}
