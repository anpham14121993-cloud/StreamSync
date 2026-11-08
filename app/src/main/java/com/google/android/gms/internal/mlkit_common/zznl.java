package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zznl {
    private final zznh zza;
    private final zznj zzb;
    private final zznj zzc;
    private final Boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zznl(zzni zzniVar, zznk zznkVar) {
        zznh zznhVar;
        zznhVar = zzniVar.zza;
        this.zza = zznhVar;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zznl)) {
            return false;
        }
        zznl zznlVar = (zznl) obj;
        if (Objects.equal(this.zza, zznlVar.zza)) {
            zznj zznjVar = zznlVar.zzb;
            if (Objects.equal(null, null)) {
                zznj zznjVar2 = zznlVar.zzc;
                if (Objects.equal(null, null)) {
                    Boolean bool = zznlVar.zzd;
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

    public final zznh zza() {
        return this.zza;
    }
}
