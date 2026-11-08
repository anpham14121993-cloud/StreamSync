package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvl {
    private static zzvk zza;

    public static synchronized zzuy zza(zzus zzusVar) {
        zzuy zzuyVar;
        synchronized (zzvl.class) {
            if (zza == null) {
                zza = new zzvk(null);
            }
            zzuyVar = (zzuy) zza.get(zzusVar);
        }
        return zzuyVar;
    }

    public static synchronized zzuy zzb(String str) {
        zzuy zza2;
        synchronized (zzvl.class) {
            zza2 = zza(zzus.zzd("translate").zzd());
        }
        return zza2;
    }
}
