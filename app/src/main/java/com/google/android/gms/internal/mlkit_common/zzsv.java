package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzsv {
    private static zzsv zza;

    private zzsv() {
    }

    public static synchronized zzsv zza() {
        zzsv zzsvVar;
        synchronized (zzsv.class) {
            if (zza == null) {
                zza = new zzsv();
            }
            zzsvVar = zza;
        }
        return zzsvVar;
    }

    public static void zzb() {
        zzsu.zza();
    }
}
