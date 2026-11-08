package com.google.mlkit.nl.translate.internal;

import com.google.android.gms.internal.mlkit_translate.zzte;
import com.google.android.gms.internal.mlkit_translate.zzuy;
import com.google.android.gms.internal.mlkit_translate.zzva;
import com.google.android.gms.internal.mlkit_translate.zzvl;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzq {
    private final zzuy zza;
    private final zzva zzb;

    public zzq() {
        zzuy zzb = zzvl.zzb("translate");
        zzva zza = zzva.zza(MlKitContext.getInstance().getApplicationContext());
        this.zza = zzb;
        this.zzb = zza;
    }

    public final zzs zza(zzte zzteVar) {
        return new zzs(this.zza, this.zzb, zzteVar, null);
    }
}
