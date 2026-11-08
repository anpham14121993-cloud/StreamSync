package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzbn implements zzbs {
    private final int zza;
    private final zzbr zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbn(int i, zzbr zzbrVar) {
        this.zza = i;
        this.zzb = zzbrVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzbs.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbs)) {
            return false;
        }
        zzbs zzbsVar = (zzbs) obj;
        return this.zza == zzbsVar.zza() && this.zzb.equals(zzbsVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzbs
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzbs
    public final zzbr zzb() {
        return this.zzb;
    }
}
