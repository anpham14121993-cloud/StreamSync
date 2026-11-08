package com.google.mlkit.nl.translate.internal;

import com.google.mlkit.nl.translate.TranslateRemoteModel;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzz {
    private final zzg zza;
    private final zzy zzb;
    private final Map zzc = new HashMap();

    public zzz(zzy zzyVar, zzg zzgVar) {
        this.zzb = zzyVar;
        this.zza = zzgVar;
    }

    public final zzab zza(TranslateRemoteModel translateRemoteModel, boolean z) {
        String zza = TranslateRemoteModel.zza(zzac.zzb(translateRemoteModel.getLanguage()));
        synchronized (this.zzc) {
            if (!this.zzc.containsKey(zza)) {
                zzab zzabVar = new zzab(this.zza.zza(translateRemoteModel), this.zzb, null);
                if (z) {
                    this.zzc.put(zza, zzabVar);
                }
                return zzabVar;
            }
            return (zzab) this.zzc.get(zza);
        }
    }
}
