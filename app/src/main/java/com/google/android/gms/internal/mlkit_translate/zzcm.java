package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzcm extends Number {
    private final String zza;

    public zzcm(String str) {
        this.zza = str;
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return Double.parseDouble(this.zza);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcm) {
            return this.zza.equals(((zzcm) obj).zza);
        }
        return false;
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return Float.parseFloat(this.zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        try {
            return Integer.parseInt(this.zza);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.zza);
            } catch (NumberFormatException e2) {
                return zzcv.zza(this.zza).intValue();
            }
        }
    }

    @Override // java.lang.Number
    public final long longValue() {
        try {
            return Long.parseLong(this.zza);
        } catch (NumberFormatException e) {
            return zzcv.zza(this.zza).longValue();
        }
    }

    public final String toString() {
        return this.zza;
    }
}
