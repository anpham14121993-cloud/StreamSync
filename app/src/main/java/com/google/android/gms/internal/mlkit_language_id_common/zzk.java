package com.google.android.gms.internal.mlkit_language_id_common;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public final class zzk {
    public static int zza(int i, int i2, String str) {
        String zza;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                zza = zzl.zza("%s (%s) must not be negative", "index", Integer.valueOf(i));
            } else {
                if (i2 < 0) {
                    throw new IllegalArgumentException("negative size: " + i2);
                }
                zza = zzl.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return i;
    }

    public static int zzb(int i, int i2, String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(zzd(i, i2, "index"));
        }
        return i;
    }

    public static void zzc(int i, int i2, int i3) {
        String zzd;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                zzd = zzd(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                zzd = zzd(i2, i3, "end index");
            } else {
                zzd = zzl.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(zzd);
        }
    }

    private static String zzd(int i, int i2, String str) {
        if (i < 0) {
            return zzl.zza("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("negative size: " + i2);
        }
        return zzl.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
