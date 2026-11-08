package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.util.AtomicFile;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzwd {
    private static final Object zza;
    private final Context zzb;

    static {
        Component.builder(zzwd.class).add(Dependency.required(Context.class)).factory(new ComponentFactory() { // from class: com.google.android.gms.internal.mlkit_translate.zzwc
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzwd((Context) componentContainer.get(Context.class));
            }
        }).build();
        zza = new Object();
    }

    public zzwd(Context context) {
        this.zzb = context;
    }

    private final File zzc() {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(this.zzb);
        if (noBackupFilesDir == null || !noBackupFilesDir.isDirectory()) {
            Log.w("MLKitRemoteConfigSaver", "noBackupFilesDir doesn't exist, using regular files directory instead");
            File filesDir = this.zzb.getFilesDir();
            if (filesDir != null && !filesDir.isDirectory()) {
                try {
                    if (!filesDir.mkdirs()) {
                        Log.w("MLKitRemoteConfigSaver", "mkdirs failed: " + filesDir.toString());
                    }
                } catch (SecurityException e) {
                    Log.w("MLKitRemoteConfigSaver", "mkdirs threw an exception: ".concat(filesDir.toString()), e);
                }
            }
            noBackupFilesDir = filesDir;
        }
        return new File(noBackupFilesDir, "com.google.mlkit.RemoteConfig");
    }

    public final zzvq zza(zzud zzudVar) {
        zzvq zzvqVar;
        synchronized (zza) {
            File zzc = zzc();
            zzvqVar = null;
            try {
                String str = new String(new AtomicFile(zzc).readFully(), Charset.forName("UTF-8"));
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    try {
                        zzvqVar = new zzvq(jSONObject.getJSONObject("configs_key"), new Date(jSONObject.getLong("fetch_time_key")), jSONObject.getJSONArray("abt_experiments_key"));
                    } catch (JSONException e) {
                        zzudVar.zzc(zzsi.FILE_READ_RETURNED_INVALID_DATA);
                        Log.e("MLKitRemoteConfigSaver", "Error parsing remote config settings JSON object:\n".concat(jSONObject.toString()), e);
                    }
                } catch (JSONException e2) {
                    zzudVar.zzc(zzsi.FILE_READ_RETURNED_MALFORMED_DATA);
                    Log.e("MLKitRemoteConfigSaver", "Error parsing remote config settings JSON string:\n".concat(str), e2);
                }
            } catch (IOException e3) {
                if (zzc.exists()) {
                    zzudVar.zzc(zzsi.FILE_READ_FAILED);
                    Log.w("MLKitRemoteConfigSaver", "Error reading remote config settings file: " + zzc.toString(), e3);
                    return null;
                }
                Log.i("MLKitRemoteConfigSaver", "remote config settings file not yet present: " + zzc.toString());
                return null;
            }
        }
        return zzvqVar;
    }

    public final void zzb(zzvq zzvqVar, zzud zzudVar) {
        File file;
        AtomicFile atomicFile;
        FileOutputStream startWrite;
        String zzvqVar2 = zzvqVar.toString();
        synchronized (zza) {
            try {
                file = zzc();
            } catch (IOException e) {
                e = e;
                file = null;
            }
            try {
                try {
                    Log.i("MLKitRemoteConfigSaver", "Creating remote config settings: " + file.toString());
                    atomicFile = new AtomicFile(file);
                    startWrite = atomicFile.startWrite();
                } catch (IOException e2) {
                    e = e2;
                    zzudVar.zzc(zzsi.FILE_WRITE_FAILED);
                    Log.e("MLKitRemoteConfigSaver", "Error writing to remote config settings file " + String.valueOf(file), e);
                }
            } catch (IOException e3) {
                e = e3;
                zzudVar.zzc(zzsi.FILE_WRITE_FAILED);
                Log.e("MLKitRemoteConfigSaver", "Error writing to remote config settings file " + String.valueOf(file), e);
            }
            try {
                PrintWriter printWriter = new PrintWriter(startWrite);
                printWriter.println(zzvqVar2);
                printWriter.flush();
                atomicFile.finishWrite(startWrite);
                Log.d("MLKitRemoteConfigSaver", "Succeeded writing remote config settings: " + file.toString() + ":\n" + zzvqVar2);
            } catch (Throwable th) {
                atomicFile.failWrite(startWrite);
                throw th;
            }
        }
    }
}
