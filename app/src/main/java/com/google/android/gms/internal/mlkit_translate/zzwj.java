package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzwj {
    private static zzwj zza;

    private zzwj() {
    }

    public static synchronized zzwj zza() {
        zzwj zzwjVar;
        synchronized (zzwj.class) {
            if (zza == null) {
                zza = new zzwj();
            }
            zzwjVar = zza;
        }
        return zzwjVar;
    }

    public static final boolean zzb() {
        zza();
        return Boolean.parseBoolean("");
    }
}
