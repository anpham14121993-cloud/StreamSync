package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvy {
    public static final long zza;
    private static final ExecutorService zzb;
    private static zzvy zzc;
    private final Context zzd;
    private final Executor zze;
    private final zzub zzf;
    private final zzvr zzg;
    private volatile zzw zzh;
    private volatile zzw zzi;
    private final Map zzj;
    private final zzwd zzk;
    private final zzwb zzl;

    static {
        Executors.newSingleThreadExecutor();
        zzb = Executors.newSingleThreadExecutor();
        zza = TimeUnit.HOURS.toSeconds(12L);
    }

    private zzvy(Context context, zzub zzubVar, zzwb zzwbVar, zzua zzuaVar) {
        ExecutorService executorService = zzb;
        this.zzj = new TreeMap();
        this.zzd = context.getApplicationContext();
        this.zzf = zzubVar;
        this.zze = executorService;
        this.zzl = zzwbVar;
        this.zzg = new zzvr(context, zzuaVar.zzb(), zzuaVar.zza(), "firebase", 5L, 5L, zzwbVar);
        this.zzk = new zzwd(context);
    }

    public static synchronized zzvy zze(Context context) {
        zzvy zzvyVar;
        synchronized (zzvy.class) {
            if (zzc == null) {
                zzc = new zzvy(context, zzub.zzb(context), new zzwb(context), zzui.zza);
            }
            zzvyVar = zzc;
        }
        return zzvyVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzw zzj(JSONObject jSONObject) throws JSONException {
        String string;
        zzvu zzvuVar = new zzvu(jSONObject);
        zzv zzvVar = new zzv();
        Iterator<String> keys = zzvuVar.zza.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                String string2 = jSONObject.getString(next);
                if (string2 == null) {
                    string = null;
                } else if (string2.isEmpty()) {
                    string = "";
                } else {
                    string = new JSONObject("{ \"value\": " + string2 + " }").getString("value");
                }
                zzvVar.zza(next, string);
            } catch (JSONException e) {
                Log.e("MLKit RemoteConfigRestC", "Getting JSON string value for remote config key " + next + " failed", e);
                throw e;
            }
        }
        return zzvVar.zzb();
    }

    public final Task zza(final long j) {
        final Date date = new Date(System.currentTimeMillis());
        final zzud zzudVar = new zzud();
        zzudVar.zzg();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final boolean z = true;
        this.zze.execute(new Runnable(date, j, zzudVar, z, taskCompletionSource) { // from class: com.google.android.gms.internal.mlkit_translate.zzvw
            public final /* synthetic */ Date zzb;
            public final /* synthetic */ long zzc;
            public final /* synthetic */ zzud zzd;
            public final /* synthetic */ TaskCompletionSource zze;

            {
                this.zze = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzvy.this.zzh(this.zzb, this.zzc, this.zzd, true, this.zze);
            }
        });
        return taskCompletionSource.getTask().onSuccessTask(this.zze, new SuccessContinuation() { // from class: com.google.android.gms.internal.mlkit_translate.zzvv
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                zzvy.this.zzg();
                return Tasks.forResult(null);
            }
        });
    }

    public final String zzf(String str) {
        String str2;
        zzw zzwVar = this.zzh;
        if (zzwVar == null || !zzwVar.containsKey(str)) {
            synchronized (this.zzj) {
                str2 = (String) this.zzj.get(str);
            }
            return str2;
        }
        return (String) zzwVar.get(str);
    }

    public final void zzg() {
        zzud zzudVar = new zzud();
        zzudVar.zzg();
        this.zzh = this.zzi;
        zzudVar.zze();
        this.zzl.zzb(zzudVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(Date date, long j, zzud zzudVar, boolean z, TaskCompletionSource taskCompletionSource) {
        Pair pair;
        zzw zzwVar;
        try {
            try {
                zzvq zza2 = this.zzk.zza(zzudVar);
                if (zza2 == null) {
                    pair = null;
                } else {
                    JSONObject zzc2 = zza2.zzc();
                    try {
                        zzw zzj = zzj(zzc2);
                        this.zzi = zzj;
                        zzg();
                        zzudVar.zzh();
                        pair = Pair.create(zzj, zza2.zzb());
                    } catch (JSONException e) {
                        zzudVar.zzc(zzsi.FILE_READ_RETURNED_MALFORMED_DATA);
                        Log.e("MLKit RemoteConfigRestC", "Saved remote config setting has invalid format: ".concat(String.valueOf(String.valueOf(zzc2))), e);
                        pair = null;
                    }
                }
                if (pair == null) {
                    zzwVar = null;
                } else {
                    zzwVar = (zzw) pair.first;
                    if (date.after(new Date(((Date) pair.second).getTime() + TimeUnit.SECONDS.toMillis(j)))) {
                        Log.i("MLKit RemoteConfigRestC", "Saved remote config is past its expiration time.");
                        zzwVar = null;
                    }
                }
                if (zzwVar == null) {
                    try {
                        Log.d("MLKit RemoteConfigRestC", "Getting installation id...");
                        this.zzf.zzd();
                        Log.d("MLKit RemoteConfigRestC", "Got installation id. Checking temporary token for expiry...");
                        String zzc3 = this.zzf.zzc();
                        Log.d("MLKit RemoteConfigRestC", "Got valid temporary auth token. Fetching remote config...");
                        zzvx zzvxVar = new zzvx(this, zzc3, date, zzudVar);
                        if (!zzwh.zza(zzvxVar)) {
                            zzudVar.zzd(zzsi.RPC_EXPONENTIAL_BACKOFF_FAILED);
                            zzwVar = null;
                        } else {
                            zzwVar = zzvxVar.zzb();
                            Log.d("MLKit RemoteConfigRestC", "writeAndSetFetchedConfig: ".concat(String.valueOf(String.valueOf(zzwVar))));
                            this.zzk.zzb(zzvxVar.zzc(), zzudVar);
                            this.zzi = zzwVar;
                        }
                    } catch (IOException e2) {
                        zzudVar.zzd(zzsi.UNKNOWN_ERROR);
                        Log.e("MLKit RemoteConfigRestC", "Initializing installation id failed", e2);
                        zzwVar = null;
                    }
                }
                if (zzwVar == null) {
                    Log.e("MLKit RemoteConfigRestC", "Remote config was null!");
                } else {
                    Log.d("MLKit RemoteConfigRestC", "Got remote config.");
                }
                taskCompletionSource.setResult(null);
            } finally {
                zzudVar.zze();
                this.zzl.zzc(zzudVar);
            }
        } catch (zzwa | InterruptedException | RuntimeException e3) {
            Log.e("MLKit RemoteConfigRestC", "Fetch failed", e3);
            taskCompletionSource.setException(e3);
        }
    }

    public final void zzi(int i) {
        Map map = this.zzj;
        Map zza2 = zzvs.zza(this.zzd, i);
        synchronized (map) {
            this.zzj.putAll(zza2);
        }
    }
}
