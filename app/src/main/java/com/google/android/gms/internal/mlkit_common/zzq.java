package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzq {
    private final String zza;
    private final zzo zzb = new zzo();
    private zzo zzc = this.zzb;

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzo zzoVar = this.zzb.zzc;
        String str = "";
        while (zzoVar != null) {
            Object obj = zzoVar.zzb;
            sb.append(str);
            String str2 = zzoVar.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r2.length() - 1);
            }
            zzoVar = zzoVar.zzc;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzq zza(String str, @CheckForNull Object obj) {
        zzo zzoVar = new zzo();
        this.zzc.zzc = zzoVar;
        this.zzc = zzoVar;
        zzoVar.zzb = obj;
        zzoVar.zza = str;
        return this;
    }

    public final zzq zzb(String str, boolean z) {
        String valueOf = String.valueOf(z);
        zzn zznVar = new zzn(null);
        this.zzc.zzc = zznVar;
        this.zzc = zznVar;
        zznVar.zzb = valueOf;
        zznVar.zza = "isManifestFile";
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzq(String str, zzp zzpVar) {
        if (str == null) {
            throw null;
        }
        this.zza = str;
    }
}
