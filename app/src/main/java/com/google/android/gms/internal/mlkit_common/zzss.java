package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzss {
    private static zzsr zza;

    public static synchronized zzsh zza(zzsb zzsbVar) {
        zzsh zzshVar;
        synchronized (zzss.class) {
            if (zza == null) {
                zza = new zzsr(null);
            }
            zzshVar = (zzsh) zza.get(zzsbVar);
        }
        return zzshVar;
    }

    public static synchronized zzsh zzb(String str) {
        zzsh zza2;
        synchronized (zzss.class) {
            zza2 = zza(zzsb.zzd("common").zzd());
        }
        return zza2;
    }
}
