package com.google.mlkit.nl.translate.internal;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzy {
    private double zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzb(zzy zzyVar) {
        double max = Math.max(zzyVar.zza, 0.5d);
        double d = max + max;
        zzyVar.zza = d;
        if (d > 60.0d) {
            zzyVar.zza = 60.0d;
            d = 60.0d;
        }
        zzyVar.zza = d + (Math.random() * zzyVar.zza);
    }
}
