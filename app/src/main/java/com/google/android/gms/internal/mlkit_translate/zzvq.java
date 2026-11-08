package com.google.android.gms.internal.mlkit_translate;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvq {
    private static final Date zza = new Date(0);
    private final JSONObject zzb;
    private final JSONObject zzc;
    private final Date zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzvq(JSONObject jSONObject, Date date, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("configs_key", jSONObject);
        jSONObject2.put("fetch_time_key", date.getTime());
        jSONObject2.put("abt_experiments_key", jSONArray);
        this.zzc = jSONObject;
        this.zzd = date;
        this.zzb = jSONObject2;
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final Date zzb() {
        return this.zzd;
    }

    public final JSONObject zzc() {
        return this.zzc;
    }
}
