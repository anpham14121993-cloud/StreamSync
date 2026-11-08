package com.google.android.gms.internal.mlkit_translate;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzuo extends zzvc {
    private final zzop zza;
    private final String zzb;
    private final boolean zzc;
    private final ModelType zzd;
    private final zzov zze;
    private final int zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzuo(zzop zzopVar, String str, boolean z, boolean z2, ModelType modelType, zzov zzovVar, int i, zzun zzunVar) {
        this.zza = zzopVar;
        this.zzb = str;
        this.zzc = z;
        this.zzd = modelType;
        this.zze = zzovVar;
        this.zzf = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzvc) {
            zzvc zzvcVar = (zzvc) obj;
            if (this.zza.equals(zzvcVar.zzc()) && this.zzb.equals(zzvcVar.zze()) && this.zzc == zzvcVar.zzg()) {
                zzvcVar.zzf();
                if (this.zzd.equals(zzvcVar.zzb()) && this.zze.equals(zzvcVar.zzd()) && this.zzf == zzvcVar.zza()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ (true != this.zzc ? 1237 : 1231)) * 1000003) ^ 1237) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf;
    }

    public final String toString() {
        zzov zzovVar = this.zze;
        ModelType modelType = this.zzd;
        return "RemoteModelLoggingOptions{errorCode=" + this.zza.toString() + ", tfliteSchemaVersion=" + this.zzb + ", shouldLogRoughDownloadTime=" + this.zzc + ", shouldLogExactDownloadTime=false, modelType=" + modelType.toString() + ", downloadStatus=" + zzovVar.toString() + ", failureStatusCode=" + this.zzf + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final int zza() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final ModelType zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final zzop zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final zzov zzd() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final String zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvc
    public final boolean zzg() {
        return this.zzc;
    }
}
