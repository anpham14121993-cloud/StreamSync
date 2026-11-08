package com.google.android.gms.internal.mlkit_translate;

import java.util.Arrays;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzv {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzu zzc;

    public final zzv zza(Object obj, Object obj2) {
        int i = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            this.zza = Arrays.copyOf(objArr, zzo.zzb(length, i2));
        }
        zzl.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i3 = this.zzb;
        int i4 = i3 + i3;
        objArr2[i4] = obj;
        objArr2[i4 + 1] = obj2;
        this.zzb = i3 + 1;
        return this;
    }

    public final zzw zzb() {
        zzu zzuVar = this.zzc;
        if (zzuVar != null) {
            throw zzuVar.zza();
        }
        zzag zzi = zzag.zzi(this.zzb, this.zza, this);
        zzu zzuVar2 = this.zzc;
        if (zzuVar2 != null) {
            throw zzuVar2.zza();
        }
        return zzi;
    }
}
