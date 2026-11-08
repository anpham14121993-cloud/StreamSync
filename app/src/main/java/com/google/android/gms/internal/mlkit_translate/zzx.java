package com.google.android.gms.internal.mlkit_translate;

import java.util.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzx extends zzn {
    public zzx() {
        super(4);
    }

    public final zzx zzc(Object obj) {
        if (obj != null) {
            super.zza(obj);
            return this;
        }
        throw null;
    }

    public final zzy zzd() {
        zzy zzl;
        int i = this.zzb;
        switch (i) {
            case 0:
                return zzah.zza;
            case 1:
                return new zzaj(Objects.requireNonNull(this.zza[0]));
            default:
                zzl = zzy.zzl(i, this.zza);
                this.zzb = zzl.size();
                this.zzc = true;
                return zzl;
        }
    }
}
