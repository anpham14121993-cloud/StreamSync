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

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzug {
    private static final Object zza;
    private final Context zzb;

    static {
        Component.builder(zzug.class).add(Dependency.required(Context.class)).factory(new ComponentFactory() { // from class: com.google.android.gms.internal.mlkit_translate.zzuf
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzug((Context) componentContainer.get(Context.class));
            }
        }).build();
        zza = new Object();
    }

    public zzug(Context context) {
        this.zzb = context;
    }

    public final zzuh zza(zzud zzudVar) {
        zzuh zzuhVar;
        synchronized (zza) {
            File zzb = zzb(zzudVar);
            zzuhVar = null;
            try {
                String str = new String(new AtomicFile(zzb).readFully(), Charset.forName("UTF-8"));
                try {
                    zzcb zzb2 = zzcg.zzb(str);
                    if (!(zzb2 instanceof zzce)) {
                        Log.e("MLKitInstallationIdSaver", "Error parsing installation info JSON element:\n".concat(String.valueOf(String.valueOf(zzb2))));
                        zzudVar.zzc(zzsi.FILE_READ_RETURNED_MALFORMED_DATA);
                    } else {
                        zzce zzb3 = zzb2.zzb();
                        try {
                            zztw zztwVar = new zztw(zzb3.zzd("fid").zze());
                            String zze = zzb3.zzd("refreshToken").zze();
                            String zze2 = zzb3.zzd("temporaryToken").zze();
                            long zzc = zzb3.zzd("temporaryTokenExpiryTimestamp").zzc();
                            Log.d("MLKitInstallationIdSaver", "fid: " + zztwVar.toString());
                            Log.d("MLKitInstallationIdSaver", "refresh_token: " + zze);
                            Log.d("MLKitInstallationIdSaver", "temporary_token: " + zze2);
                            Log.d("MLKitInstallationIdSaver", "temporary token expiry: " + zzc);
                            zzuhVar = new zzuh(zztwVar, zze, zze2, zzc);
                        } catch (ClassCastException | IllegalStateException | NullPointerException e) {
                            zzudVar.zzc(zzsi.FILE_READ_RETURNED_INVALID_DATA);
                            Log.e("MLKitInstallationIdSaver", "Error traversing installation info JSON object:\nraw json:\n" + str + "\nparsed json:\n" + zzb3.toString(), e);
                        }
                    }
                } catch (zzci e2) {
                    Log.e("MLKitInstallationIdSaver", "Error parsing installation info JSON object:\n".concat(str), e2);
                    zzudVar.zzc(zzsi.FILE_READ_RETURNED_MALFORMED_DATA);
                }
            } catch (IOException e3) {
                if (zzb.exists()) {
                    zzudVar.zzc(zzsi.FILE_READ_FAILED);
                    Log.w("MLKitInstallationIdSaver", "Error reading installation id file: " + zzb.toString(), e3);
                    return null;
                }
                Log.i("MLKitInstallationIdSaver", "Installation id file not yet present: " + zzb.toString());
                return null;
            }
        }
        return zzuhVar;
    }

    final File zzb(zzud zzudVar) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(this.zzb);
        if (noBackupFilesDir == null || !noBackupFilesDir.isDirectory()) {
            Log.w("MLKitInstallationIdSaver", "noBackupFilesDir doesn't exist, using regular files directory instead");
            File filesDir = this.zzb.getFilesDir();
            if (filesDir != null && !filesDir.isDirectory()) {
                try {
                    if (!filesDir.mkdirs()) {
                        Log.w("MLKitInstallationIdSaver", "mkdirs failed: " + filesDir.toString());
                        zzudVar.zzd(zzsi.DIRECTORY_CREATION_FAILED);
                    }
                } catch (SecurityException e) {
                    Log.w("MLKitInstallationIdSaver", "mkdirs threw an exception: ".concat(filesDir.toString()), e);
                    zzudVar.zzd(zzsi.DIRECTORY_CREATION_FAILED);
                }
            }
            noBackupFilesDir = filesDir;
        }
        return new File(noBackupFilesDir, "com.google.mlkit.InstallationId");
    }

    public final void zzc(zzuh zzuhVar, zzud zzudVar) {
        File file;
        AtomicFile atomicFile;
        FileOutputStream startWrite;
        String format = String.format("{\n \"fid\": \"%s\",\n \"refreshToken\": \"%s\",\n \"temporaryToken\": \"%s\",\n \"temporaryTokenExpiryTimestamp\": \"%d\"\n}\n", zzuhVar.zzb().zza(), zzuhVar.zzc(), zzuhVar.zzd(), Long.valueOf(zzuhVar.zza()));
        synchronized (zza) {
            try {
                file = zzb(zzudVar);
            } catch (IOException e) {
                e = e;
                file = null;
            }
            try {
                try {
                    Log.i("MLKitInstallationIdSaver", "Creating installation id: " + file.toString());
                    atomicFile = new AtomicFile(file);
                    startWrite = atomicFile.startWrite();
                } catch (IOException e2) {
                    e = e2;
                    zzudVar.zzc(zzsi.FILE_WRITE_FAILED);
                    Log.e("MLKitInstallationIdSaver", "Error writing to installation id file " + String.valueOf(file), e);
                }
            } catch (IOException e3) {
                e = e3;
                zzudVar.zzc(zzsi.FILE_WRITE_FAILED);
                Log.e("MLKitInstallationIdSaver", "Error writing to installation id file " + String.valueOf(file), e);
            }
            try {
                PrintWriter printWriter = new PrintWriter(startWrite);
                printWriter.println(format);
                printWriter.flush();
                atomicFile.finishWrite(startWrite);
                Log.d("MLKitInstallationIdSaver", "Succeeded writing installation id: " + file.toString() + ":\n" + format);
            } catch (Throwable th) {
                atomicFile.failWrite(startWrite);
                throw th;
            }
        }
    }
}
