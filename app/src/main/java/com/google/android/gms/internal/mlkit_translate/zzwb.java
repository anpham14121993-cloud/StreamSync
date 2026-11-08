package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzwb extends zzue {
    public zzwb(Context context) {
        super(new zzuy(context, new SharedPrefManager(context), new zzut(context, zzus.zzd("shared-remote-config").zzd()), "shared-remote-config"));
    }

    public final void zzb(zzud zzudVar) {
        zza(zzoq.REMOTE_CONFIG_ACTIVATE, zzudVar);
    }

    public final void zzc(zzud zzudVar) {
        zza(zzoq.REMOTE_CONFIG_FETCH, zzudVar);
    }

    public final void zzd(zzud zzudVar) {
        zza(zzoq.REMOTE_CONFIG_FRC_FETCH, zzudVar);
    }
}
