package com.google.mlkit.nl.translate.internal;

import java.io.File;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
final class zzo {
    String zza;
    String zzb;
    String zzc;
    final /* synthetic */ TranslateJni zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzo(TranslateJni translateJni, zzn zznVar) {
        this.zzd = translateJni;
    }

    private static final String zzb(File file) {
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(String str, String str2, String str3) {
        TranslateJni.zzc(this.zzd).zza(str2, str3);
        File file = new File(str, zzae.zzd(str2, str3));
        File file2 = new File(str, zzae.zzc(str2, str3));
        File file3 = new File(str, zzae.zze(str2, str3));
        this.zza = zzb(file);
        this.zzb = zzb(file2);
        this.zzc = zzb(file3);
    }
}
