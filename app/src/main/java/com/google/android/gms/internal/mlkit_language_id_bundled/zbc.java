package com.google.android.gms.internal.mlkit_language_id_bundled;

/* compiled from: com.google.mlkit:language-id@@17.0.6 */
/* loaded from: classes24.dex */
public final class zbc {
    public static int zba(int i, int i2, String str) {
        String zba;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                zba = zbd.zba("%s (%s) must not be negative", "index", Integer.valueOf(i));
            } else {
                if (i2 < 0) {
                    throw new IllegalArgumentException("negative size: " + i2);
                }
                zba = zbd.zba("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(zba);
        }
        return i;
    }

    public static int zbb(int i, int i2, String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(zbd(i, i2, "index"));
        }
        return i;
    }

    public static void zbc(int i, int i2, int i3) {
        String zbd;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                zbd = zbd(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                zbd = zbd(i2, i3, "end index");
            } else {
                zbd = zbd.zba("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(zbd);
        }
    }

    private static String zbd(int i, int i2, String str) {
        if (i < 0) {
            return zbd.zba("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("negative size: " + i2);
        }
        return zbd.zba("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
