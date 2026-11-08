package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzan extends zzap {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public zzan(java.lang.String r2, java.lang.String r3, @javax.annotation.CheckForNull java.lang.Character r4) {
        /*
            r1 = this;
            com.google.android.gms.internal.mlkit_translate.zzam r0 = new com.google.android.gms.internal.mlkit_translate.zzam
            char[] r3 = r3.toCharArray()
            r0.<init>(r2, r3)
            r1.<init>(r0, r4)
            char[] r2 = com.google.android.gms.internal.mlkit_translate.zzam.zze(r0)
            int r2 = r2.length
            r3 = 64
            if (r2 != r3) goto L17
            r2 = 1
            goto L18
        L17:
            r2 = 0
        L18:
            com.google.android.gms.internal.mlkit_translate.zzf.zzc(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzan.<init>(java.lang.String, java.lang.String, java.lang.Character):void");
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzap, com.google.android.gms.internal.mlkit_translate.zzaq
    final int zza(byte[] bArr, CharSequence charSequence) throws zzao {
        CharSequence zzc = zzc(charSequence);
        if (this.zza.zzc(zzc.length())) {
            int i = 0;
            int i2 = 0;
            while (i < zzc.length()) {
                int i3 = i + 1;
                int i4 = i2 + 1;
                int zzb = (this.zza.zzb(zzc.charAt(i)) << 18) | (this.zza.zzb(zzc.charAt(i3)) << 12);
                bArr[i2] = (byte) (zzb >>> 16);
                int i5 = i3 + 1;
                if (i5 < zzc.length()) {
                    int i6 = i5 + 1;
                    int zzb2 = zzb | (this.zza.zzb(zzc.charAt(i5)) << 6);
                    i2 = i4 + 1;
                    bArr[i4] = (byte) ((zzb2 >>> 8) & 255);
                    if (i6 < zzc.length()) {
                        bArr[i2] = (byte) ((zzb2 | this.zza.zzb(zzc.charAt(i6))) & 255);
                        i2++;
                        i = i6 + 1;
                    } else {
                        i = i6;
                    }
                } else {
                    i = i5;
                    i2 = i4;
                }
            }
            return i2;
        }
        throw new zzao("Invalid input length " + zzc.length());
    }
}
