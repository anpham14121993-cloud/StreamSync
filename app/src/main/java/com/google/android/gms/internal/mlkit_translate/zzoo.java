package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public enum zzoo implements zzbq {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);

    private final int zzf;

    zzoo(int i) {
        this.zzf = i;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzbq
    public final int zza() {
        return this.zzf;
    }
}
