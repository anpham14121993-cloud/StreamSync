package com.google.android.gms.internal.mlkit_translate;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvp {
    private JSONObject zza = new JSONObject();
    private Date zzb;
    private JSONArray zzc;

    private zzvp() {
        Date date;
        date = zzvq.zza;
        this.zzb = date;
        this.zzc = new JSONArray();
    }

    public final zzvp zza(JSONObject jSONObject) {
        try {
            this.zza = new JSONObject(jSONObject.toString());
        } catch (JSONException e) {
        }
        return this;
    }

    public final zzvp zzb(JSONArray jSONArray) {
        try {
            this.zzc = new JSONArray(jSONArray.toString());
        } catch (JSONException e) {
        }
        return this;
    }

    public final zzvp zzc(Date date) {
        this.zzb = date;
        return this;
    }

    public final zzvq zzd() throws JSONException {
        return new zzvq(this.zza, this.zzb, this.zzc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzvp(zzvo zzvoVar) {
        Date date;
        date = zzvq.zza;
        this.zzb = date;
        this.zzc = new JSONArray();
    }
}
