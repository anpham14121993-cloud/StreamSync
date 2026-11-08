package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public final class zzlt {
    private static zzls zza;

    public static synchronized zzli zza(zzlc zzlcVar) {
        zzli zzliVar;
        synchronized (zzlt.class) {
            if (zza == null) {
                zza = new zzls(null);
            }
            zzliVar = (zzli) zza.get(zzlcVar);
        }
        return zzliVar;
    }

    public static synchronized zzli zzb(String str) {
        zzli zza2;
        synchronized (zzlt.class) {
            zza2 = zza(zzlc.zzd(str).zzd());
        }
        return zza2;
    }
}
