package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzac extends zzz {
    public zzac() {
        super(4);
    }

    public final zzac zzb(Object obj) {
        if (obj != null) {
            int i = this.zzb + 1;
            Object[] objArr = this.zza;
            int length = objArr.length;
            if (length < i) {
                this.zza = Arrays.copyOf(objArr, zzz.zza(length, i));
                this.zzc = false;
            } else if (this.zzc) {
                this.zza = (Object[]) objArr.clone();
                this.zzc = false;
            }
            Object[] objArr2 = this.zza;
            int i2 = this.zzb;
            this.zzb = i2 + 1;
            objArr2[i2] = obj;
            return this;
        }
        throw null;
    }

    public final zzaf zzc() {
        this.zzc = true;
        return zzaf.zzg(this.zza, this.zzb);
    }
}
