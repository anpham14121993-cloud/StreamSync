package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public class zzue {
    private final zzuy zza;

    public zzue(Context context) {
        this.zza = new zzuy(context, new SharedPrefManager(context), new zzut(context, zzus.zzd("shared-installation-id").zzd()), "shared-installation-id");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public zzue(zzuy zzuyVar) {
        this.zza = zzuyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzoq zzoqVar, zzud zzudVar) {
        zzor zzorVar = new zzor();
        zzorVar.zzk(zzudVar.zza().zzi());
        this.zza.zze(zzvd.zzf(zzorVar), zzoqVar, "o:a:mlkit:1.0.0");
    }
}
