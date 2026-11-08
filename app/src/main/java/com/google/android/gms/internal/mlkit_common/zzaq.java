package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzaq extends zzai {
    static final zzai zza = new zzaq(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzaq(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaq zzg(int i, Object[] objArr, zzah zzahVar) {
        int i2;
        char c;
        int i3 = i;
        Object[] objArr2 = objArr;
        if (i3 == 0) {
            return (zzaq) zza;
        }
        Object[] objArr3 = null;
        int i4 = 1;
        if (i3 == 1) {
            zzw.zza(Objects.requireNonNull(objArr2[0]), Objects.requireNonNull(objArr2[1]));
            return new zzaq(null, objArr2, 1);
        }
        zzt.zzb(i3, objArr2.length >> 1, "index");
        int max = Math.max(i3, 2);
        if (max < 751619276) {
            i2 = Integer.highestOneBit(max - 1);
            do {
                i2 += i2;
            } while (i2 * 0.7d < max);
        } else {
            i2 = 1073741824;
            if (max >= 1073741824) {
                throw new IllegalArgumentException("collection too large");
            }
        }
        if (i3 == 1) {
            zzw.zza(Objects.requireNonNull(objArr2[0]), Objects.requireNonNull(objArr2[1]));
            i3 = 1;
            c = 2;
        } else {
            int i5 = i2 - 1;
            int i6 = -1;
            if (i2 <= 128) {
                byte[] bArr = new byte[i2];
                Arrays.fill(bArr, (byte) -1);
                int i7 = 0;
                int i8 = 0;
                while (i7 < i3) {
                    int i9 = i8 + i8;
                    int i10 = i7 + i7;
                    Object requireNonNull = Objects.requireNonNull(objArr2[i10]);
                    Object requireNonNull2 = Objects.requireNonNull(objArr2[i10 ^ i4]);
                    zzw.zza(requireNonNull, requireNonNull2);
                    int zza2 = zzy.zza(requireNonNull.hashCode());
                    while (true) {
                        int i11 = zza2 & i5;
                        int i12 = bArr[i11] & 255;
                        if (i12 != 255) {
                            if (requireNonNull.equals(objArr2[i12])) {
                                int i13 = i12 ^ 1;
                                zzag zzagVar = new zzag(requireNonNull, requireNonNull2, Objects.requireNonNull(objArr2[i13]));
                                objArr2[i13] = requireNonNull2;
                                objArr3 = zzagVar;
                                break;
                            }
                            zza2 = i11 + 1;
                        } else {
                            bArr[i11] = (byte) i9;
                            if (i8 < i7) {
                                objArr2[i9] = requireNonNull;
                                objArr2[i9 ^ 1] = requireNonNull2;
                            }
                            i8++;
                        }
                    }
                    i7++;
                    i4 = 1;
                }
                if (i8 == i3) {
                    objArr3 = bArr;
                    c = 2;
                } else {
                    objArr3 = new Object[]{bArr, Integer.valueOf(i8), objArr3};
                    c = 2;
                }
            } else if (i2 <= 32768) {
                short[] sArr = new short[i2];
                Arrays.fill(sArr, (short) -1);
                int i14 = 0;
                for (int i15 = 0; i15 < i3; i15++) {
                    int i16 = i14 + i14;
                    int i17 = i15 + i15;
                    Object requireNonNull3 = Objects.requireNonNull(objArr2[i17]);
                    Object requireNonNull4 = Objects.requireNonNull(objArr2[i17 ^ 1]);
                    zzw.zza(requireNonNull3, requireNonNull4);
                    int zza3 = zzy.zza(requireNonNull3.hashCode());
                    while (true) {
                        int i18 = zza3 & i5;
                        char c2 = (char) sArr[i18];
                        if (c2 != 65535) {
                            if (requireNonNull3.equals(objArr2[c2])) {
                                int i19 = c2 ^ 1;
                                zzag zzagVar2 = new zzag(requireNonNull3, requireNonNull4, Objects.requireNonNull(objArr2[i19]));
                                objArr2[i19] = requireNonNull4;
                                objArr3 = zzagVar2;
                                break;
                            }
                            zza3 = i18 + 1;
                        } else {
                            sArr[i18] = (short) i16;
                            if (i14 < i15) {
                                objArr2[i16] = requireNonNull3;
                                objArr2[i16 ^ 1] = requireNonNull4;
                            }
                            i14++;
                        }
                    }
                }
                if (i14 == i3) {
                    objArr3 = sArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{sArr, Integer.valueOf(i14), objArr3};
                }
            } else {
                int[] iArr = new int[i2];
                Arrays.fill(iArr, -1);
                int i20 = 0;
                int i21 = 0;
                while (i20 < i3) {
                    int i22 = i21 + i21;
                    int i23 = i20 + i20;
                    Object requireNonNull5 = Objects.requireNonNull(objArr2[i23]);
                    Object requireNonNull6 = Objects.requireNonNull(objArr2[i23 ^ 1]);
                    zzw.zza(requireNonNull5, requireNonNull6);
                    int zza4 = zzy.zza(requireNonNull5.hashCode());
                    while (true) {
                        int i24 = zza4 & i5;
                        int i25 = iArr[i24];
                        if (i25 != i6) {
                            if (requireNonNull5.equals(objArr2[i25])) {
                                int i26 = i25 ^ 1;
                                zzag zzagVar3 = new zzag(requireNonNull5, requireNonNull6, Objects.requireNonNull(objArr2[i26]));
                                objArr2[i26] = requireNonNull6;
                                objArr3 = zzagVar3;
                                break;
                            }
                            zza4 = i24 + 1;
                            i6 = -1;
                        } else {
                            iArr[i24] = i22;
                            if (i21 < i20) {
                                objArr2[i22] = requireNonNull5;
                                objArr2[i22 ^ 1] = requireNonNull6;
                            }
                            i21++;
                        }
                    }
                    i20++;
                    i6 = -1;
                }
                if (i21 == i3) {
                    objArr3 = iArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{iArr, Integer.valueOf(i21), objArr3};
                }
            }
        }
        if (objArr3 instanceof Object[]) {
            Object[] objArr4 = objArr3;
            zzag zzagVar4 = (zzag) objArr4[c];
            if (zzahVar != null) {
                zzahVar.zzc = zzagVar4;
                Object obj = objArr4[0];
                int intValue = ((Integer) objArr4[1]).intValue();
                objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
                objArr3 = obj;
                i3 = intValue;
            } else {
                throw zzagVar4.zza();
            }
        }
        return new zzaq(objArr3, objArr2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        if (obj == null) {
            obj2 = null;
        } else {
            int i = this.zzd;
            Object[] objArr = this.zzb;
            if (i == 1) {
                if (Objects.requireNonNull(objArr[0]).equals(obj)) {
                    obj2 = Objects.requireNonNull(objArr[1]);
                }
                obj2 = null;
            } else {
                Object obj3 = this.zzc;
                if (obj3 != null) {
                    if (obj3 instanceof byte[]) {
                        byte[] bArr = (byte[]) obj3;
                        int length = bArr.length - 1;
                        int zza2 = zzy.zza(obj.hashCode());
                        while (true) {
                            int i2 = zza2 & length;
                            int i3 = bArr[i2] & 255;
                            if (i3 == 255) {
                                obj2 = null;
                                break;
                            }
                            if (obj.equals(objArr[i3])) {
                                obj2 = objArr[i3 ^ 1];
                                break;
                            }
                            zza2 = i2 + 1;
                        }
                    } else if (obj3 instanceof short[]) {
                        short[] sArr = (short[]) obj3;
                        int length2 = sArr.length - 1;
                        int zza3 = zzy.zza(obj.hashCode());
                        while (true) {
                            int i4 = zza3 & length2;
                            char c = (char) sArr[i4];
                            if (c == 65535) {
                                obj2 = null;
                                break;
                            }
                            if (obj.equals(objArr[c])) {
                                obj2 = objArr[c ^ 1];
                                break;
                            }
                            zza3 = i4 + 1;
                        }
                    } else {
                        int[] iArr = (int[]) obj3;
                        int length3 = iArr.length - 1;
                        int zza4 = zzy.zza(obj.hashCode());
                        while (true) {
                            int i5 = zza4 & length3;
                            int i6 = iArr[i5];
                            if (i6 == -1) {
                                obj2 = null;
                                break;
                            }
                            if (obj.equals(objArr[i6])) {
                                obj2 = objArr[i6 ^ 1];
                                break;
                            }
                            zza4 = i5 + 1;
                        }
                    }
                }
                obj2 = null;
            }
        }
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final zzab zza() {
        return new zzap(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final zzaj zzd() {
        return new zzan(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final zzaj zze() {
        return new zzao(this, new zzap(this.zzb, 0, this.zzd));
    }
}
