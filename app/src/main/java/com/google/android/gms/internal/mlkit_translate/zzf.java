package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzf {
    public static int zza(int i, int i2, String str) {
        String zza;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                zza = zzi.zza("%s (%s) must not be negative", "index", Integer.valueOf(i));
            } else {
                if (i2 < 0) {
                    throw new IllegalArgumentException("negative size: " + i2);
                }
                zza = zzi.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return i;
    }

    public static int zzb(int i, int i2, String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(zzg(i, i2, "index"));
        }
        return i;
    }

    public static void zzc(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzd(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(zzi.zza(str, Character.valueOf(c)));
        }
    }

    public static void zze(int i, int i2, int i3) {
        String zzg;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                zzg = zzg(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                zzg = zzg(i2, i3, "end index");
            } else {
                zzg = zzi.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(zzg);
        }
    }

    public static void zzf(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    private static String zzg(int i, int i2, String str) {
        if (i < 0) {
            return zzi.zza("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("negative size: " + i2);
        }
        return zzi.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
