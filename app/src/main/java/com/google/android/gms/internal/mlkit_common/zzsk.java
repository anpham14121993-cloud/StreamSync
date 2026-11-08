package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzsk implements zzry {
    private final zzmw zza;
    private zzqt zzb = new zzqt();

    private zzsk(zzmw zzmwVar, int i) {
        this.zza = zzmwVar;
        zzsv.zza();
    }

    public static zzry zzf(zzmw zzmwVar) {
        return new zzsk(zzmwVar, 0);
    }

    public static zzry zzg() {
        return new zzsk(new zzmw(), 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final zzry zza(zzmv zzmvVar) {
        this.zza.zzf(zzmvVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final zzry zzb(zznc zzncVar) {
        this.zza.zzi(zzncVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final zzry zzc(zzqt zzqtVar) {
        this.zzb = zzqtVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final String zzd() {
        String zzk;
        zzqv zzf = this.zza.zzk().zzf();
        if (zzf == null || (zzk = zzf.zzk()) == null || zzk.isEmpty()) {
            return "NA";
        }
        return (String) Preconditions.checkNotNull(zzf.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzry
    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzsv.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzkr.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzmy zzk = this.zza.zzk();
            zzbg zzbgVar = new zzbg();
            zzkr.zza.configure(zzbgVar);
            return zzbgVar.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
