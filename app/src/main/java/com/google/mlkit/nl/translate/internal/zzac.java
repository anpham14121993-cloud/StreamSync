package com.google.mlkit.nl.translate.internal;

import com.google.mlkit.nl.translate.TranslateLanguage;
import java.util.Arrays;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzac {
    public static final /* synthetic */ int zza = 0;
    private static final com.google.android.gms.internal.mlkit_translate.zzt zzb = com.google.android.gms.internal.mlkit_translate.zzt.zzl("merged_dict_%1$s_%2$s_update.bin", "merged_dict_%1$s_%2$s_both.bin", "merged_dict_%1$s_%2$s_from_%3$s.bin", "merged_dict_%1$s_%2$s_from_%4$s.bin");

    public static com.google.android.gms.internal.mlkit_translate.zzt zza(String str) {
        String[] zzd = zzd(str);
        String str2 = zzd[0];
        String str3 = zzd[1];
        String[] strArr = new String[zzb.size()];
        for (int i = 0; i < zzb.size(); i++) {
            strArr[i] = String.format((String) zzb.get(i), str, "25", str2, str3);
        }
        return com.google.android.gms.internal.mlkit_translate.zzt.zzh(strArr);
    }

    public static String zzb(String str) {
        return zzc(TranslateLanguage.ENGLISH, TranslateLanguage.zza(str));
    }

    public static String zzc(String str, String str2) {
        if ((str.equals(TranslateLanguage.ENGLISH) || str2.equals(TranslateLanguage.ENGLISH)) && !str.equals(str2)) {
            String[] strArr = {str, str2};
            Arrays.sort(strArr);
            return String.format("%s_%s", strArr[0], strArr[1]);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] zzd(String str) {
        if (!str.matches("[a-z]{2,3}_[a-z]{2,3}")) {
            throw new IllegalArgumentException(String.format("Model name expected to be matching %s", "[a-z]{2,3}_[a-z]{2,3}"));
        }
        return str.split("_", -1);
    }
}
