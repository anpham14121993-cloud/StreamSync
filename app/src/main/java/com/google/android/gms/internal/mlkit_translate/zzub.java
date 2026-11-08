package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzub {
    private static zzub zza;
    private final zztz zzb;
    private final zzug zzc;
    private final zzue zzd;
    private zzuh zze;

    public zzub(Context context, zzua zzuaVar) {
        this.zzd = new zzue(context);
        this.zzc = new zzug(context);
        this.zzb = new zztz(zzuaVar, this.zzd);
    }

    public static synchronized zzub zzb(Context context) {
        zzub zzubVar;
        synchronized (zzub.class) {
            if (zza == null) {
                zza = new zzub(context, zzui.zza);
            }
            zzubVar = zza;
        }
        return zzubVar;
    }

    public final zztw zza() {
        Preconditions.checkState(this.zze != null);
        return this.zze.zzb();
    }

    public final String zzc() throws InterruptedException {
        Preconditions.checkState(this.zze != null);
        Preconditions.checkState(this.zze != null);
        if (this.zze.zze()) {
            zzud zzudVar = new zzud();
            zzudVar.zzg();
            try {
                if (this.zzb.zzc(zzudVar)) {
                    this.zze = this.zzb.zza();
                }
            } finally {
                zzudVar.zze();
                this.zzd.zza(zzoq.INSTALLATION_ID_REFRESH_TEMPORARY_TOKEN, zzudVar);
            }
        }
        Preconditions.checkState(this.zze != null);
        return this.zze.zzd();
    }

    public final void zzd() throws IOException, InterruptedException {
        zzue zzueVar;
        zzoq zzoqVar;
        zzud zzudVar = new zzud();
        zzudVar.zzg();
        try {
            zzuh zza2 = this.zzc.zza(zzudVar);
            if (zza2 != null) {
                this.zze = zza2;
            } else {
                final zzud zzudVar2 = new zzud();
                zzudVar2.zzg();
                try {
                    int i = zzuc.zzb;
                    byte[] bArr = new byte[17];
                    zzuc.zza.nextBytes(bArr);
                    bArr[0] = (byte) ((bArr[0] & Ascii.SI) | 112);
                    String substring = Base64.encodeToString(bArr, 11).substring(0, 22);
                    Log.d("MLKitInstallationIdGenerator", "Generated installation id: ".concat(String.valueOf(substring)));
                    final zztw zztwVar = new zztw(substring);
                    final zztz zztzVar = this.zzb;
                    if (!zzwh.zza(new zzwg() { // from class: com.google.android.gms.internal.mlkit_translate.zzty
                        @Override // com.google.android.gms.internal.mlkit_translate.zzwg
                        public final boolean zza() {
                            return zztz.this.zzb(zztwVar, zzudVar2);
                        }
                    })) {
                        zzudVar2.zzd(zzsi.RPC_EXPONENTIAL_BACKOFF_FAILED);
                        zzudVar2.zzd(zzsi.RPC_ERROR);
                        zzudVar2.zze();
                        zzueVar = this.zzd;
                        zzoqVar = zzoq.INSTALLATION_ID_REGISTER_NEW_ID;
                    } else {
                        this.zze = this.zzb.zza();
                        zzuh zzuhVar = this.zze;
                        if (zzuhVar != null) {
                            this.zzc.zzc(zzuhVar, zzudVar2);
                        }
                        zzudVar2.zze();
                        zzueVar = this.zzd;
                        zzoqVar = zzoq.INSTALLATION_ID_REGISTER_NEW_ID;
                    }
                    zzueVar.zza(zzoqVar, zzudVar2);
                } catch (Throwable th) {
                    zzudVar2.zze();
                    this.zzd.zza(zzoq.INSTALLATION_ID_REGISTER_NEW_ID, zzudVar2);
                    throw th;
                }
            }
        } finally {
            zzudVar.zze();
            this.zzd.zza(zzoq.INSTALLATION_ID_INIT, zzudVar);
        }
    }
}
