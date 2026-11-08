package com.google.android.gms.internal.mlkit_translate;

import java.util.Date;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzvt {
    private final zzvq zza;

    private zzvt(Date date, int i, zzvq zzvqVar, String str) {
        this.zza = zzvqVar;
    }

    public static zzvt zzb(Date date) {
        return new zzvt(date, 1, null, null);
    }

    public static zzvt zzc(zzvq zzvqVar, String str) {
        return new zzvt(zzvqVar.zzb(), 0, zzvqVar, str);
    }

    public final zzvq zza() {
        return this.zza;
    }
}
