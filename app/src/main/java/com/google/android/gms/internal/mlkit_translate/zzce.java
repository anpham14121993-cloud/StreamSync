package com.google.android.gms.internal.mlkit_translate;

import java.util.Set;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzce extends zzcb {
    private final zzcu zza = new zzcu(false);

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzce) && ((zzce) obj).zza.equals(this.zza);
        }
        return true;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final zzcb zza(String str) {
        return (zzcb) this.zza.get(str);
    }

    public final zzce zzc(String str) {
        return (zzce) this.zza.get(str);
    }

    public final zzch zzd(String str) {
        return (zzch) this.zza.get(str);
    }

    public final Set zze() {
        return this.zza.entrySet();
    }

    public final void zzf(String str, zzcb zzcbVar) {
        this.zza.put(str, zzcbVar);
    }

    public final boolean zzg(String str) {
        return this.zza.containsKey(str);
    }
}
