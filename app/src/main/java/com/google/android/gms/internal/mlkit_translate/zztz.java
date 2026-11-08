package com.google.android.gms.internal.mlkit_translate;

import android.util.Log;
import androidx.autofill.HintConstants;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zztz {
    private static final MediaType zzb = MediaType.parse("application/json; charset=utf-8");
    public final zzua zza;
    private final zzue zze;
    private final OkHttpClient zzc = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS).readTimeout(10000, TimeUnit.MILLISECONDS).writeTimeout(10000, TimeUnit.MILLISECONDS).build();
    private zzuh zzd = null;
    private final String zzf = "https://firebaseinstallations.googleapis.com/v1";

    public zztz(zzua zzuaVar, zzue zzueVar) {
        this.zza = zzuaVar;
        this.zze = zzueVar;
    }

    private static long zze(long j, String str) {
        return j + (Long.parseLong(str.replaceFirst("s$", "")) * 1000);
    }

    private final String zzf(Headers headers, String str, String str2, zzud zzudVar, zzud zzudVar2) {
        String str3;
        ResponseBody body;
        try {
            Response execute = this.zzc.newCall(new Request.Builder().headers(headers).url(str).post(RequestBody.create(zzb, str2)).build()).execute();
            int code = execute.code();
            zzudVar2.zzf(code);
            if (code >= 200 && code < 300) {
                try {
                    body = execute.body();
                    try {
                        String string = body.string();
                        if (body != null) {
                            body.close();
                        }
                        return string;
                    } finally {
                    }
                } catch (IOException e) {
                    Log.e("MLKitFbInstsRestClient", "Error retrieving response body from HTTPS POST request to <" + str + ">", e);
                    zzudVar2.zzd(zzsi.RPC_ERROR);
                    zzudVar.zzb(zzsi.RPC_ERROR);
                    return null;
                }
            }
            Log.e("MLKitFbInstsRestClient", "Got HTTP status " + code + " from HTTPS POST request to <" + str + ">");
            try {
                body = execute.body();
                try {
                    str3 = body.string();
                    if (body != null) {
                        body.close();
                    }
                } finally {
                    if (body != null) {
                        try {
                            body.close();
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                }
            } catch (IOException e2) {
                str3 = "<none>";
            }
            Log.d("MLKitFbInstsRestClient", "HTTP Response Body:\n".concat(String.valueOf(str3)));
            zzudVar2.zzd(zzsi.RPC_ERROR);
            zzudVar.zzb(zzsi.RPC_ERROR);
            return null;
        } catch (IOException e3) {
            Log.e("MLKitFbInstsRestClient", "Connection error (or timeout) sending HTTPS POST request to <" + str + ">", e3);
            zzudVar2.zzd(zzsi.NO_CONNECTION);
            zzudVar.zzb(zzsi.NO_CONNECTION);
            return null;
        }
    }

    public final zzuh zza() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ boolean zzb(zztw zztwVar, zzud zzudVar) throws zzwf, IOException, InterruptedException {
        boolean z;
        String format = String.format("%s/projects/%s/installations", this.zzf, this.zza.zzc());
        Headers build = new Headers.Builder().add("x-goog-api-key", this.zza.zza()).build();
        String format2 = String.format("{fid: '%s', appId: '%s', authVersion: '%s', sdkVersion: '%s'}", zztwVar.zza(), this.zza.zzb(), "FIS_v2", "o:a:mlkit:1.0.0");
        long currentTimeMillis = System.currentTimeMillis();
        zzud zzudVar2 = new zzud();
        zzudVar2.zzg();
        String zzf = zzf(build, format, format2, zzudVar, zzudVar2);
        zzudVar2.zze();
        try {
            if (zzf == null) {
                z = false;
            } else {
                try {
                    zzce zzb2 = zzcg.zzb(zzf).zzb();
                    try {
                        String zze = zzb2.zzd(HintConstants.AUTOFILL_HINT_NAME).zze();
                        zztw zztwVar2 = new zztw(zzb2.zzd("fid").zze());
                        String zze2 = zzb2.zzd("refreshToken").zze();
                        zzce zzc = zzb2.zzc("authToken");
                        String zze3 = zzc.zzd("token").zze();
                        String zze4 = zzc.zzd("expiresIn").zze();
                        long zze5 = zze(currentTimeMillis, zze4);
                        Log.i("MLKitFbInstsRestClient", "installation name: " + zze);
                        Log.d("MLKitFbInstsRestClient", "fid: " + zztwVar2.zza());
                        Log.d("MLKitFbInstsRestClient", "refresh_token: " + zze2);
                        Log.d("MLKitFbInstsRestClient", "auth token: " + String.valueOf(zzc));
                        Log.d("MLKitFbInstsRestClient", "auth token expires in: " + zze4);
                        Log.d("MLKitFbInstsRestClient", "auth token expiry: " + zze5);
                        this.zzd = new zzuh(zztwVar2, zze2, zze3, zze5);
                        z = true;
                    } catch (ClassCastException | IllegalStateException | NullPointerException e) {
                        Log.e("MLKitFbInstsRestClient", "Error traversing JSON object returned from url <" + format + ">:\nraw json:\n" + zzf + "\nparsed json:\n" + zzb2.toString(), e);
                        zzudVar2.zzd(zzsi.RPC_RETURNED_INVALID_RESULT);
                        zzudVar.zzb(zzsi.RPC_RETURNED_INVALID_RESULT);
                        z = false;
                    }
                } catch (zzci e2) {
                    e = e2;
                    Log.e("MLKitFbInstsRestClient", "Error parsing JSON object returned from <" + format + ">:\n" + zzf, e);
                    zzudVar2.zzd(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    zzudVar.zzb(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    z = false;
                    this.zze.zza(zzoq.INSTALLATION_ID_FIS_CREATE_INSTALLATION, zzudVar2);
                    return z;
                } catch (IllegalStateException e3) {
                    e = e3;
                    Log.e("MLKitFbInstsRestClient", "Error parsing JSON object returned from <" + format + ">:\n" + zzf, e);
                    zzudVar2.zzd(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    zzudVar.zzb(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    z = false;
                    this.zze.zza(zzoq.INSTALLATION_ID_FIS_CREATE_INSTALLATION, zzudVar2);
                    return z;
                } catch (NullPointerException e4) {
                    e = e4;
                    Log.e("MLKitFbInstsRestClient", "Error parsing JSON object returned from <" + format + ">:\n" + zzf, e);
                    zzudVar2.zzd(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    zzudVar.zzb(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    z = false;
                    this.zze.zza(zzoq.INSTALLATION_ID_FIS_CREATE_INSTALLATION, zzudVar2);
                    return z;
                }
            }
            this.zze.zza(zzoq.INSTALLATION_ID_FIS_CREATE_INSTALLATION, zzudVar2);
            return z;
        } catch (Throwable th) {
            this.zze.zza(zzoq.INSTALLATION_ID_FIS_CREATE_INSTALLATION, zzudVar2);
            throw th;
        }
    }

    public final boolean zzc(final zzud zzudVar) throws InterruptedException {
        if (this.zzd == null) {
            return false;
        }
        boolean zza = zzwh.zza(new zzwg() { // from class: com.google.android.gms.internal.mlkit_translate.zztx
            @Override // com.google.android.gms.internal.mlkit_translate.zzwg
            public final boolean zza() {
                return zztz.this.zzd(zzudVar);
            }
        });
        if (!zza) {
            zzudVar.zzc(zzsi.RPC_EXPONENTIAL_BACKOFF_FAILED);
        }
        return zza;
    }

    public final boolean zzd(zzud zzudVar) {
        String format = String.format("%s/projects/%s/installations/%s/authTokens:generate", this.zzf, this.zza.zzc(), this.zzd.zzb().zza());
        Headers build = new Headers.Builder().add("authorization", "FIS_v2 ".concat(String.valueOf(this.zzd.zzc()))).add("x-goog-api-key", this.zza.zza()).build();
        String format2 = String.format("{installation:{sdkVersion:'%s'}}", "o:a:mlkit:1.0.0");
        long currentTimeMillis = System.currentTimeMillis();
        zzud zzudVar2 = new zzud();
        zzudVar2.zzg();
        String zzf = zzf(build, format, format2, zzudVar, zzudVar2);
        zzudVar2.zze();
        boolean z = false;
        try {
            if (zzf != null) {
                try {
                    zzce zzb2 = zzcg.zzb(zzf).zzb();
                    try {
                        String zze = zzb2.zzd("token").zze();
                        String zze2 = zzb2.zzd("expiresIn").zze();
                        long zze3 = zze(currentTimeMillis, zze2);
                        Log.d("MLKitFbInstsRestClient", "refreshed auth token: " + zze);
                        Log.d("MLKitFbInstsRestClient", "auth token expires in: " + zze2);
                        Log.d("MLKitFbInstsRestClient", "auth token expiry: " + zze3);
                        this.zzd = new zzuh(this.zzd.zzb(), this.zzd.zzc(), zze, zze3);
                        z = true;
                    } catch (ClassCastException | IllegalStateException | NullPointerException e) {
                        zzudVar2.zzd(zzsi.RPC_RETURNED_INVALID_RESULT);
                        zzudVar.zzb(zzsi.RPC_RETURNED_INVALID_RESULT);
                        Log.e("MLKitFbInstsRestClient", "Error traversing JSON object returned from <" + format + ">:\nraw json:\n" + zzf + "\nparsed json:\n" + zzb2.toString(), e);
                    }
                } catch (zzci e2) {
                    Log.e("MLKitFbInstsRestClient", "Error parsing JSON object returned from <" + format + ">:\n" + zzf, e2);
                    zzudVar2.zzd(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                    zzudVar.zzb(zzsi.RPC_RETURNED_MALFORMED_RESULT);
                }
            }
            this.zze.zza(zzoq.INSTALLATION_ID_FIS_GENERATE_AUTH_TOKEN, zzudVar2);
            return z;
        } catch (Throwable th) {
            this.zze.zza(zzoq.INSTALLATION_ID_FIS_GENERATE_AUTH_TOKEN, zzudVar2);
            throw th;
        }
    }
}
