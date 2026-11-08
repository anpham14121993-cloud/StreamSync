package com.google.android.gms.internal.mlkit_translate;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzag extends zzw {
    static final zzw zza = new zzag(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzag(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzag zzi(int i, Object[] objArr, zzv zzvVar) {
        char c;
        int i2 = i;
        Object[] objArr2 = objArr;
        if (i2 == 0) {
            return (zzag) zza;
        }
        Object[] objArr3 = null;
        int i3 = 1;
        if (i2 == 1) {
            zzl.zza(Objects.requireNonNull(objArr2[0]), Objects.requireNonNull(objArr2[1]));
            return new zzag(null, objArr2, 1);
        }
        zzf.zzb(i2, objArr2.length >> 1, "index");
        int zzf = zzy.zzf(i);
        if (i2 == 1) {
            zzl.zza(Objects.requireNonNull(objArr2[0]), Objects.requireNonNull(objArr2[1]));
            i2 = 1;
            c = 2;
        } else {
            int i4 = zzf - 1;
            int i5 = -1;
            if (zzf <= 128) {
                byte[] bArr = new byte[zzf];
                Arrays.fill(bArr, (byte) -1);
                int i6 = 0;
                int i7 = 0;
                while (i6 < i2) {
                    int i8 = i7 + i7;
                    int i9 = i6 + i6;
                    Object requireNonNull = Objects.requireNonNull(objArr2[i9]);
                    Object requireNonNull2 = Objects.requireNonNull(objArr2[i9 ^ i3]);
                    zzl.zza(requireNonNull, requireNonNull2);
                    int zza2 = zzm.zza(requireNonNull.hashCode());
                    while (true) {
                        int i10 = zza2 & i4;
                        int i11 = bArr[i10] & 255;
                        if (i11 != 255) {
                            if (requireNonNull.equals(objArr2[i11])) {
                                int i12 = i11 ^ 1;
                                zzu zzuVar = new zzu(requireNonNull, requireNonNull2, Objects.requireNonNull(objArr2[i12]));
                                objArr2[i12] = requireNonNull2;
                                objArr3 = zzuVar;
                                break;
                            }
                            zza2 = i10 + 1;
                        } else {
                            bArr[i10] = (byte) i8;
                            if (i7 < i6) {
                                objArr2[i8] = requireNonNull;
                                objArr2[i8 ^ 1] = requireNonNull2;
                            }
                            i7++;
                        }
                    }
                    i6++;
                    i3 = 1;
                }
                if (i7 == i2) {
                    objArr3 = bArr;
                    c = 2;
                } else {
                    objArr3 = new Object[]{bArr, Integer.valueOf(i7), objArr3};
                    c = 2;
                }
            } else if (zzf <= 32768) {
                short[] sArr = new short[zzf];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = i13 + i13;
                    int i16 = i14 + i14;
                    Object requireNonNull3 = Objects.requireNonNull(objArr2[i16]);
                    Object requireNonNull4 = Objects.requireNonNull(objArr2[i16 ^ 1]);
                    zzl.zza(requireNonNull3, requireNonNull4);
                    int zza3 = zzm.zza(requireNonNull3.hashCode());
                    while (true) {
                        int i17 = zza3 & i4;
                        char c2 = (char) sArr[i17];
                        if (c2 != 65535) {
                            if (requireNonNull3.equals(objArr2[c2])) {
                                int i18 = c2 ^ 1;
                                zzu zzuVar2 = new zzu(requireNonNull3, requireNonNull4, Objects.requireNonNull(objArr2[i18]));
                                objArr2[i18] = requireNonNull4;
                                objArr3 = zzuVar2;
                                break;
                            }
                            zza3 = i17 + 1;
                        } else {
                            sArr[i17] = (short) i15;
                            if (i13 < i14) {
                                objArr2[i15] = requireNonNull3;
                                objArr2[i15 ^ 1] = requireNonNull4;
                            }
                            i13++;
                        }
                    }
                }
                if (i13 == i2) {
                    objArr3 = sArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{sArr, Integer.valueOf(i13), objArr3};
                }
            } else {
                int[] iArr = new int[zzf];
                Arrays.fill(iArr, -1);
                int i19 = 0;
                int i20 = 0;
                while (i19 < i2) {
                    int i21 = i20 + i20;
                    int i22 = i19 + i19;
                    Object requireNonNull5 = Objects.requireNonNull(objArr2[i22]);
                    Object requireNonNull6 = Objects.requireNonNull(objArr2[i22 ^ 1]);
                    zzl.zza(requireNonNull5, requireNonNull6);
                    int zza4 = zzm.zza(requireNonNull5.hashCode());
                    while (true) {
                        int i23 = zza4 & i4;
                        int i24 = iArr[i23];
                        if (i24 != i5) {
                            if (requireNonNull5.equals(objArr2[i24])) {
                                int i25 = i24 ^ 1;
                                zzu zzuVar3 = new zzu(requireNonNull5, requireNonNull6, Objects.requireNonNull(objArr2[i25]));
                                objArr2[i25] = requireNonNull6;
                                objArr3 = zzuVar3;
                                break;
                            }
                            zza4 = i23 + 1;
                            i5 = -1;
                        } else {
                            iArr[i23] = i21;
                            if (i20 < i19) {
                                objArr2[i21] = requireNonNull5;
                                objArr2[i21 ^ 1] = requireNonNull6;
                            }
                            i20++;
                        }
                    }
                    i19++;
                    i5 = -1;
                }
                if (i20 == i2) {
                    objArr3 = iArr;
                    c = 2;
                } else {
                    c = 2;
                    objArr3 = new Object[]{iArr, Integer.valueOf(i20), objArr3};
                }
            }
        }
        if (objArr3 instanceof Object[]) {
            Object[] objArr4 = objArr3;
            zzu zzuVar4 = (zzu) objArr4[c];
            if (zzvVar != null) {
                zzvVar.zzc = zzuVar4;
                Object obj = objArr4[0];
                int intValue = ((Integer) objArr4[1]).intValue();
                objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
                objArr3 = obj;
                i2 = intValue;
            } else {
                throw zzuVar4.zza();
            }
        }
        return new zzag(objArr3, objArr2, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzw, java.util.Map
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
                        int zza2 = zzm.zza(obj.hashCode());
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
                        int zza3 = zzm.zza(obj.hashCode());
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
                        int zza4 = zzm.zza(obj.hashCode());
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

    @Override // com.google.android.gms.internal.mlkit_translate.zzw
    final zzp zza() {
        return new zzaf(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzw
    final zzy zzf() {
        return new zzad(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzw
    final zzy zzg() {
        return new zzae(this, new zzaf(this.zzb, 0, this.zzd));
    }
}
