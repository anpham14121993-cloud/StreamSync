package com.google.mlkit.nl.languageid;

import com.google.android.gms.internal.mlkit_language_id_common.zze;
import com.google.android.gms.internal.mlkit_language_id_common.zzf;
import com.google.android.gms.internal.mlkit_language_id_common.zzg;
import java.util.Arrays;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes25.dex */
public final class IdentifiedLanguage {
    private final String zza;
    private final float zzb;

    public IdentifiedLanguage(String str, float f) {
        this.zza = str;
        this.zzb = f;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdentifiedLanguage)) {
            return false;
        }
        IdentifiedLanguage identifiedLanguage = (IdentifiedLanguage) o;
        return Float.compare(identifiedLanguage.zzb, this.zzb) == 0 && zzg.zza(this.zza, identifiedLanguage.zza);
    }

    public float getConfidence() {
        return this.zzb;
    }

    public String getLanguageTag() {
        return this.zza;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Float.valueOf(this.zzb)});
    }

    public String toString() {
        zze zza = zzf.zza(this);
        zza.zzb("languageTag", this.zza);
        zza.zza("confidence", this.zzb);
        return zza.toString();
    }
}
