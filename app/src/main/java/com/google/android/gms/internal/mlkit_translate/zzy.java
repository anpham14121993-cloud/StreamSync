package com.google.android.gms.internal.mlkit_translate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public abstract class zzy extends zzp implements Set {

    @CheckForNull
    private transient zzt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int i) {
        int max = Math.max(i, 2);
        if (max >= 751619276) {
            if (max < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int highestOneBit = Integer.highestOneBit(max - 1);
        do {
            highestOneBit += highestOneBit;
        } while (highestOneBit * 0.7d < max);
        return highestOneBit;
    }

    public static zzy zzj() {
        return zzah.zza;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzy) && zzk() && ((zzy) obj).zzk() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException e) {
            } catch (NullPointerException e2) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzai.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: zzd */
    public abstract zzak iterator();

    public final zzt zzg() {
        zzt zztVar = this.zza;
        if (zztVar != null) {
            return zztVar;
        }
        zzt zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    zzt zzh() {
        Object[] array = toArray();
        int i = zzt.zzd;
        return zzt.zzg(array, array.length);
    }

    boolean zzk() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzy zzl(int i, Object... objArr) {
        switch (i) {
            case 0:
                return zzah.zza;
            case 1:
                return new zzaj(Objects.requireNonNull(objArr[0]));
            default:
                int zzf = zzf(i);
                Object[] objArr2 = new Object[zzf];
                int i2 = zzf - 1;
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < i; i5++) {
                    Object obj = objArr[i5];
                    zzaa.zza(obj, i5);
                    int hashCode = obj.hashCode();
                    int zza = zzm.zza(hashCode);
                    while (true) {
                        int i6 = zza & i2;
                        Object obj2 = objArr2[i6];
                        if (obj2 != null) {
                            if (!obj2.equals(obj)) {
                                zza++;
                            }
                        } else {
                            objArr[i4] = obj;
                            objArr2[i6] = obj;
                            i3 += hashCode;
                            i4++;
                        }
                    }
                }
                Arrays.fill(objArr, i4, i, (Object) null);
                if (i4 == 1) {
                    return new zzaj(Objects.requireNonNull(objArr[0]));
                }
                if (zzf(i4) < zzf / 2) {
                    return zzl(i4, objArr);
                }
                int length = objArr.length;
                if (i4 < (length >> 1) + (length >> 2)) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new zzah(objArr, i3, objArr2, i2, i4);
        }
    }
}
