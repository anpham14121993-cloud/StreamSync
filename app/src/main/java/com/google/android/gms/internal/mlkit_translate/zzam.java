package com.google.android.gms.internal.mlkit_translate;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzam {
    final int zza;
    final int zzb;
    final int zzc;
    private final String zzd;
    private final char[] zze;
    private final byte[] zzf;
    private final boolean[] zzg;
    private final boolean zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public zzam(java.lang.String r10, char[] r11) {
        /*
            r9 = this;
            r0 = 128(0x80, float:1.8E-43)
            byte[] r1 = new byte[r0]
            r2 = -1
            java.util.Arrays.fill(r1, r2)
            r3 = 0
            r4 = r3
        La:
            int r5 = r11.length
            if (r4 >= r5) goto L2c
            char r5 = r11[r4]
            r6 = 1
            if (r5 >= r0) goto L14
            r7 = r6
            goto L15
        L14:
            r7 = r3
        L15:
            java.lang.String r8 = "Non-ASCII character: %s"
            com.google.android.gms.internal.mlkit_translate.zzf.zzd(r7, r8, r5)
            r7 = r1[r5]
            if (r7 != r2) goto L20
            goto L21
        L20:
            r6 = r3
        L21:
            java.lang.String r7 = "Duplicate character: %s"
            com.google.android.gms.internal.mlkit_translate.zzf.zzd(r6, r7, r5)
            byte r6 = (byte) r4
            r1[r5] = r6
            int r4 = r4 + 1
            goto La
        L2c:
            r9.<init>(r10, r11, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzam.<init>(java.lang.String, char[]):void");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzam) {
            zzam zzamVar = (zzam) obj;
            boolean z = zzamVar.zzh;
            if (Arrays.equals(this.zze, zzamVar.zze)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zze) + 1237;
    }

    public final String toString() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char zza(int i) {
        return this.zze[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzb(char c) throws zzao {
        if (c > 127) {
            throw new zzao("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(c))));
        }
        byte b = this.zzf[c];
        if (b != -1) {
            return b;
        }
        if (c <= ' ' || c == 127) {
            throw new zzao("Unrecognized character: 0x".concat(String.valueOf(Integer.toHexString(c))));
        }
        throw new zzao("Unrecognized character: " + c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzc(int i) {
        return this.zzg[i % this.zzb];
    }

    public final boolean zzd(char c) {
        return this.zzf[61] != -1;
    }

    private zzam(String str, char[] cArr, byte[] bArr, boolean z) {
        this.zzd = str;
        if (cArr != null) {
            this.zze = cArr;
            try {
                int zzb = zzbj.zzb(cArr.length, RoundingMode.UNNECESSARY);
                this.zza = zzb;
                int numberOfTrailingZeros = Integer.numberOfTrailingZeros(zzb);
                int i = 1 << (3 - numberOfTrailingZeros);
                this.zzb = i;
                this.zzc = this.zza >> numberOfTrailingZeros;
                this.zzf = bArr;
                boolean[] zArr = new boolean[i];
                for (int i2 = 0; i2 < this.zzc; i2++) {
                    zArr[zzbj.zza(i2 * 8, this.zza, RoundingMode.CEILING)] = true;
                }
                this.zzg = zArr;
                this.zzh = false;
                return;
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e);
            }
        }
        throw null;
    }
}
