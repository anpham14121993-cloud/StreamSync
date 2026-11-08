package com.google.mlkit.common.model;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public abstract class RemoteModelSource {
    private final String zza;

    protected RemoteModelSource(String str) {
        this.zza = str;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!o.getClass().equals(getClass())) {
            return false;
        }
        return Objects.equal(this.zza, ((RemoteModelSource) o).zza);
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public String toString() {
        zzq zzb = zzr.zzb("RemoteModelSource");
        zzb.zza("firebaseModelName", this.zza);
        return zzb.toString();
    }

    public final String zza() {
        return this.zza;
    }
}
