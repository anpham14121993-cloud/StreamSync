package com.google.android.gms.internal.mlkit_translate;

import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzaa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(@CheckForNull Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] zzb(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            zza(objArr[i2], i2);
        }
        return objArr;
    }
}
