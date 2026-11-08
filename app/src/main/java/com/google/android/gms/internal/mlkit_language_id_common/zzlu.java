package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public final class zzlu {
    private static zzlu zza;

    private zzlu() {
    }

    public static synchronized zzlu zza() {
        zzlu zzluVar;
        synchronized (zzlu.class) {
            if (zza == null) {
                zza = new zzlu();
            }
            zzluVar = zza;
        }
        return zzluVar;
    }
}
