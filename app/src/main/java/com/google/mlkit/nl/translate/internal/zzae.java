package com.google.mlkit.nl.translate.internal;

import com.google.android.gms.internal.mlkit_translate.zztc;
import com.google.android.gms.internal.mlkit_translate.zzvy;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzae {
    private final zzvy zza;
    private final zzq zzb;
    private final ModelFileHelper zzc;
    private final zzad zzd;

    public zzae(zzvy zzvyVar, zzad zzadVar, zzq zzqVar, ModelFileHelper modelFileHelper) {
        this.zza = zzvyVar;
        this.zzd = zzadVar;
        this.zzb = zzqVar;
        this.zzc = modelFileHelper;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String zzc(String str, String str2) {
        return String.format("fallback_to_pb_%s.pb.bin", zzh(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String zzd(String str, String str2) {
        return String.format("nmt_rapid_response_%s.pb.bin", zzh(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String zze(String str, String str2) {
        return String.format("stt_rapid_response_%s.pb.bin", zzh(str, str2));
    }

    static final void zzf(File file, String str, String str2) throws IOException {
        File file2 = new File(file, str);
        if (com.google.android.gms.internal.mlkit_translate.zzi.zzb(str2)) {
            if (file2.exists()) {
                file2.delete();
                return;
            }
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        try {
            fileOutputStream.write(com.google.android.gms.internal.mlkit_translate.zzaq.zzd().zze(str2));
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zzg(File file, String str, String str2) {
        new File(file, zzd(str, str2)).delete();
        new File(file, zze(str, str2)).delete();
        new File(file, zzc(str, str2)).delete();
    }

    private static String zzh(String str, String str2) {
        return String.format("%s_%s", str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(String str, String str2) {
        File modelDirUnsafe = this.zzc.getModelDirUnsafe(zzac.zzc(str, str2), ModelType.TRANSLATE, false);
        String zzh = zzh(str, str2);
        try {
            zzc.zza(modelDirUnsafe);
            zzad zzadVar = this.zzd;
            zzf(modelDirUnsafe, zzd(str, str2), zzadVar.zza.zzf(String.format("nl_translate_rapid_response_nmt_%s", zzh)));
            zzad zzadVar2 = this.zzd;
            zzf(modelDirUnsafe, zzc(str, str2), zzadVar2.zza.zzf(String.format("nl_translate_rapid_response_pbmt_%s", zzh)));
            zzad zzadVar3 = this.zzd;
            zzf(modelDirUnsafe, zze(str, str2), zzadVar3.zza.zzf(String.format("nl_translate_rapid_response_stt_%s", zzh)));
        } catch (IOException e) {
            zztc zztcVar = new zztc();
            zztcVar.zza(str);
            zztcVar.zzb(str2);
            this.zzb.zza(zztcVar.zzc()).zzw();
        }
    }

    public final void zzb() {
        this.zza.zza(zzvy.zza);
    }
}
