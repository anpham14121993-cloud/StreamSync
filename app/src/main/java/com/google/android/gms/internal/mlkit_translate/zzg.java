package com.google.android.gms.internal.mlkit_translate;

import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzg extends zze {
    private final Object zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzg) {
            return this.zza.equals(((zzg) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return "Optional.of(" + this.zza.toString() + ")";
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zze
    public final boolean zza() {
        return true;
    }
}
