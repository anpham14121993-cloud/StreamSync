package com.google.android.gms.internal.mlkit_translate;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzum extends zzvb {
    private zzop zza;
    private String zzb;
    private boolean zzc;
    private ModelType zzd;
    private zzov zze;
    private int zzf;
    private byte zzg;

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvb zza(zzov zzovVar) {
        if (zzovVar == null) {
            throw new NullPointerException("Null downloadStatus");
        }
        this.zze = zzovVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvb zzb(zzop zzopVar) {
        if (zzopVar == null) {
            throw new NullPointerException("Null errorCode");
        }
        this.zza = zzopVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvb zzc(int i) {
        this.zzf = i;
        this.zzg = (byte) (this.zzg | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvb zzd(ModelType modelType) {
        if (modelType == null) {
            throw new NullPointerException("Null modelType");
        }
        this.zzd = modelType;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvb zze(boolean z) {
        this.zzg = (byte) (this.zzg | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvb zzf(boolean z) {
        this.zzc = z;
        this.zzg = (byte) (this.zzg | 1);
        return this;
    }

    public final zzvb zzg(String str) {
        this.zzb = "NA";
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzvb
    public final zzvc zzh() {
        zzop zzopVar;
        String str;
        ModelType modelType;
        zzov zzovVar;
        if (this.zzg != 7 || (zzopVar = this.zza) == null || (str = this.zzb) == null || (modelType = this.zzd) == null || (zzovVar = this.zze) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" errorCode");
            }
            if (this.zzb == null) {
                sb.append(" tfliteSchemaVersion");
            }
            if ((this.zzg & 1) == 0) {
                sb.append(" shouldLogRoughDownloadTime");
            }
            if ((this.zzg & 2) == 0) {
                sb.append(" shouldLogExactDownloadTime");
            }
            if (this.zzd == null) {
                sb.append(" modelType");
            }
            if (this.zze == null) {
                sb.append(" downloadStatus");
            }
            if ((this.zzg & 4) == 0) {
                sb.append(" failureStatusCode");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzuo(zzopVar, str, this.zzc, false, modelType, zzovVar, this.zzf, null);
    }
}
