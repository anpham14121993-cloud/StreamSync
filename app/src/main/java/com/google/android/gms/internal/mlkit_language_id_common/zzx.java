package com.google.android.gms.internal.mlkit_language_id_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public abstract class zzx extends zzq implements Set {

    @CheckForNull
    private transient zzu zza;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException e) {
            } catch (NullPointerException e2) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzae.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: zzd */
    public abstract zzaf iterator();

    public final zzu zzf() {
        zzu zzuVar = this.zza;
        if (zzuVar != null) {
            return zzuVar;
        }
        zzu zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    zzu zzg() {
        return zzu.zzg(toArray());
    }
}
