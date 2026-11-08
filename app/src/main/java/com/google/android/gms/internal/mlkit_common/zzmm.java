package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public enum zzmm implements zzba {
    UNKNOWN(0),
    TRANSLATE(1);

    private final int zzd;

    zzmm(int i) {
        this.zzd = i;
    }

    public static zzmm zzb(int i) {
        for (zzmm zzmmVar : values()) {
            if (zzmmVar.zzd == i) {
                return zzmmVar;
            }
        }
        return UNKNOWN;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzba
    public final int zza() {
        return this.zzd;
    }
}
