package com.google.mlkit.nl.translate.internal;

import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.ModelResource;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.nl.translate.TranslateLanguage;
import java.io.File;
import java.nio.charset.StandardCharsets;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public class TranslateJni extends ModelResource {
    private static boolean zza;
    private final zzae zzb;
    private final zzs zzc;
    private final ModelFileHelper zzd;
    private final String zze;
    private final String zzf;
    private long zzg;

    public TranslateJni(zzae zzaeVar, zzs zzsVar, ModelFileHelper modelFileHelper, String str, String str2) {
        this.zzb = zzaeVar;
        this.zzc = zzsVar;
        this.zzd = modelFileHelper;
        this.zze = str;
        this.zzf = str2;
    }

    private native void nativeDestroy(long j);

    private native long nativeInit(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) throws zzk;

    private static Exception newLoadingException(int errorCode) {
        return new zzk(errorCode, null);
    }

    private static Exception newTranslateException(int errorCode) {
        return new zzm(errorCode, null);
    }

    public static void zze() throws MlKitException {
        if (zza) {
            return;
        }
        try {
            System.loadLibrary("translate_jni");
            zza = true;
        } catch (UnsatisfiedLinkError e) {
            throw new MlKitException("Couldn't load translate native code library.", 12, e);
        }
    }

    private final File zzf(String str) {
        return this.zzd.getModelDirUnsafe(str, ModelType.TRANSLATE, false);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void load() throws MlKitException {
        com.google.android.gms.internal.mlkit_translate.zzt zzj;
        String str;
        Exception exc;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            Preconditions.checkState(this.zzg == 0);
            zze();
            String str2 = this.zze;
            String str3 = this.zzf;
            int i = zzac.zza;
            if (str2.equals(str3)) {
                zzj = com.google.android.gms.internal.mlkit_translate.zzt.zzi(str2);
            } else {
                if (!str2.equals(TranslateLanguage.ENGLISH) && !str3.equals(TranslateLanguage.ENGLISH)) {
                    zzj = com.google.android.gms.internal.mlkit_translate.zzt.zzk(str2, TranslateLanguage.ENGLISH, str3);
                }
                zzj = com.google.android.gms.internal.mlkit_translate.zzt.zzj(str2, str3);
            }
            if (zzj.size() < 2) {
                exc = null;
            } else {
                String absolutePath = zzf(zzac.zzc((String) zzj.get(0), (String) zzj.get(1))).getAbsolutePath();
                zzo zzoVar = new zzo(this, null);
                zzoVar.zza(absolutePath, (String) zzj.get(0), (String) zzj.get(1));
                zzo zzoVar2 = new zzo(this, null);
                if (zzj.size() > 2) {
                    String absolutePath2 = zzf(zzac.zzc((String) zzj.get(1), (String) zzj.get(2))).getAbsolutePath();
                    zzoVar2.zza(absolutePath2, (String) zzj.get(1), (String) zzj.get(2));
                    str = absolutePath2;
                } else {
                    str = null;
                }
                try {
                    exc = null;
                    long nativeInit = nativeInit(this.zze, this.zzf, absolutePath, str, zzoVar.zza, zzoVar2.zza, zzoVar.zzb, zzoVar2.zzb, zzoVar.zzc, zzoVar2.zzc);
                    this.zzg = nativeInit;
                    Preconditions.checkState(nativeInit != 0);
                } catch (zzk e) {
                    if (e.zza() != 1 && e.zza() != 8) {
                        throw new MlKitException("Error loading translation model", 2, e);
                    }
                    throw new MlKitException("Translation model files not found. Make sure to call downloadModelIfNeeded and if that fails, delete the models and retry.", 5, e);
                }
            }
            this.zzc.zzo(elapsedRealtime, exc);
        } catch (Exception e2) {
            this.zzc.zzo(elapsedRealtime, e2);
            throw e2;
        }
    }

    public native byte[] nativeTranslate(long j, byte[] bArr) throws zzm;

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void release() {
        long j = this.zzg;
        if (j == 0) {
            return;
        }
        nativeDestroy(j);
        this.zzg = 0L;
    }

    public final String zzd(String str) throws MlKitException {
        if (this.zze.equals(this.zzf)) {
            return str;
        }
        try {
            return new String(nativeTranslate(this.zzg, str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (zzm e) {
            throw new MlKitException("Error translating", 2, e);
        }
    }
}
