package com.google.android.gms.internal.mlkit_translate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzch extends zzcb {
    private final Object zza;

    public zzch(Boolean bool) {
        this.zza = Objects.requireNonNull(bool);
    }

    private static boolean zzk(zzch zzchVar) {
        Object obj = zzchVar.zza;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzch zzchVar = (zzch) obj;
            if (this.zza == null) {
                return zzchVar.zza == null;
            }
            if (zzk(this) && zzk(zzchVar)) {
                if ((this.zza instanceof BigInteger) || (zzchVar.zza instanceof BigInteger)) {
                    return zzg().equals(zzchVar.zzg());
                }
                return zzd().longValue() == zzchVar.zzd().longValue();
            }
            Object obj2 = this.zza;
            if (obj2 instanceof Number) {
                Object obj3 = zzchVar.zza;
                if (obj3 instanceof Number) {
                    if ((obj2 instanceof BigDecimal) && (obj3 instanceof BigDecimal)) {
                        return zzf().compareTo(zzchVar.zzf()) == 0;
                    }
                    double zza = zza();
                    double zza2 = zzchVar.zza();
                    if (zza != zza2) {
                        return Double.isNaN(zza) && Double.isNaN(zza2);
                    }
                    return true;
                }
            }
            return obj2.equals(zzchVar.zza);
        }
        return false;
    }

    public final int hashCode() {
        if (this.zza == null) {
            return 31;
        }
        if (zzk(this)) {
            long longValue = zzd().longValue();
            return (int) ((longValue >>> 32) ^ longValue);
        }
        Object obj = this.zza;
        if (obj instanceof Number) {
            long doubleToLongBits = Double.doubleToLongBits(zzd().doubleValue());
            return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
        }
        return obj.hashCode();
    }

    public final double zza() {
        return this.zza instanceof Number ? zzd().doubleValue() : Double.parseDouble(zze());
    }

    public final long zzc() {
        return this.zza instanceof Number ? zzd().longValue() : Long.parseLong(zze());
    }

    public final Number zzd() {
        Object obj = this.zza;
        if (obj instanceof Number) {
            return (Number) obj;
        }
        if (!(obj instanceof String)) {
            throw new UnsupportedOperationException("Primitive is neither a number nor a string");
        }
        return new zzcm((String) obj);
    }

    public final String zze() {
        Object obj = this.zza;
        if (!(obj instanceof String)) {
            if (!(obj instanceof Number)) {
                if (obj instanceof Boolean) {
                    return ((Boolean) obj).toString();
                }
                throw new AssertionError("Unexpected value type: ".concat(String.valueOf(String.valueOf(obj.getClass()))));
            }
            return zzd().toString();
        }
        return (String) obj;
    }

    public final BigDecimal zzf() {
        Object obj = this.zza;
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        return zzcv.zza(zze());
    }

    public final BigInteger zzg() {
        Object obj = this.zza;
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (zzk(this)) {
            return BigInteger.valueOf(zzd().longValue());
        }
        return zzcv.zzb(zze());
    }

    public final boolean zzh() {
        Object obj = this.zza;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return Boolean.parseBoolean(zze());
    }

    public final boolean zzi() {
        return this.zza instanceof Boolean;
    }

    public final boolean zzj() {
        return this.zza instanceof Number;
    }

    public zzch(Number number) {
        this.zza = Objects.requireNonNull(number);
    }

    public zzch(String str) {
        this.zza = Objects.requireNonNull(str);
    }
}
