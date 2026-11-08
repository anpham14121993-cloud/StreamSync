package com.google.android.gms.internal.mlkit_translate;

import android.util.Log;
import java.net.HttpURLConnection;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvx implements zzwg {
    final /* synthetic */ zzvy zza;
    private final String zzb;
    private final Date zzc;
    private final zzud zzd;
    private zzw zze;
    private zzvq zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzvx(zzvy zzvyVar, String str, Date date, zzud zzudVar) {
        this.zza = zzvyVar;
        this.zzb = str;
        this.zzc = date;
        this.zzd = zzudVar;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzwg
    public final boolean zza() throws zzwa {
        zzvr zzvrVar;
        zzvr zzvrVar2;
        zzub zzubVar;
        zzw zzj;
        try {
            zzvrVar = this.zza.zzg;
            Log.d("MLKit RemoteConfigRestC", "Creating HTTP connection to remote config service");
            HttpURLConnection zzb = zzvrVar.zzb();
            zzvy zzvyVar = this.zza;
            zzvrVar2 = zzvyVar.zzg;
            zzubVar = zzvyVar.zzf;
            zzvt zza = zzvrVar2.zza(zzb, zzubVar.zza().zza(), this.zzb, zzw.zzc(), null, zzw.zzc(), this.zzc, "o:a:mlkit:1.0.0", null, this.zzd);
            Log.d("MLKit RemoteConfigRestC", "Got FetchResponse");
            this.zzf = zza.zza();
            JSONObject zzc = this.zzf.zzc();
            try {
                zzj = zzvy.zzj(zzc);
                this.zze = zzj;
                return true;
            } catch (JSONException e) {
                this.zzd.zzb(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                Log.e("MLKit RemoteConfigRestC", "Fetched remote config setting has invalid format: ".concat(String.valueOf(String.valueOf(zzc))), e);
                return false;
            }
        } catch (zzwa e2) {
            Log.e("MLKit RemoteConfigRestC", "Creating HTTP connection to remote config service failed", e2);
            this.zzd.zzb(zzsi.NO_CONNECTION);
            return false;
        }
    }

    public final zzw zzb() {
        return this.zze;
    }

    public final zzvq zzc() {
        return this.zzf;
    }
}
