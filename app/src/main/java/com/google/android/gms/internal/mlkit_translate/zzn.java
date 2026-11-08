package com.google.android.gms.internal.mlkit_translate;

import java.util.Arrays;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
class zzn extends zzo {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(int i) {
    }

    public final zzn zza(Object obj) {
        if (obj != null) {
            int i = this.zzb + 1;
            Object[] objArr = this.zza;
            int length = objArr.length;
            if (length < i) {
                this.zza = Arrays.copyOf(objArr, zzb(length, i));
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
}
