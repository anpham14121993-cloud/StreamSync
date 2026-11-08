package com.google.android.gms.internal.mlkit_translate;

import java.util.Map;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzct implements Map.Entry {
    zzct zza;
    zzct zzb;
    zzct zzc;
    zzct zzd;
    zzct zze;
    final Object zzf;
    final boolean zzg;
    Object zzh;
    int zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzct(boolean z) {
        this.zzf = null;
        this.zzg = z;
        this.zze = this;
        this.zzd = this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzct(boolean z, zzct zzctVar, Object obj, zzct zzctVar2, zzct zzctVar3) {
        this.zza = zzctVar;
        this.zzf = obj;
        this.zzg = z;
        this.zzi = 1;
        this.zzd = zzctVar2;
        this.zze = zzctVar3;
        zzctVar3.zzd = this;
        zzctVar2.zze = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzf;
        if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
            Object obj3 = this.zzh;
            if (obj3 == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (obj3.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.zzf;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.zzh;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.zzf;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.zzh;
        return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj == null && !this.zzg) {
            throw new NullPointerException("value == null");
        }
        Object obj2 = this.zzh;
        this.zzh = obj;
        return obj2;
    }

    public final String toString() {
        return String.valueOf(this.zzf) + "=" + String.valueOf(this.zzh);
    }
}
