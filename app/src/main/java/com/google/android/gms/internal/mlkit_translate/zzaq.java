package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public abstract class zzaq {
    private static final zzaq zza = new zzan("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');

    static {
        char[] cArr;
        new zzan("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
        new zzap("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
        new zzap("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
        zzam zzamVar = new zzam("base16()", "0123456789ABCDEF".toCharArray());
        new zzap(zzamVar, null);
        char[] cArr2 = new char[512];
        cArr = zzamVar.zze;
        zzf.zzc(cArr.length == 16);
        for (int i = 0; i < 256; i++) {
            cArr2[i] = zzamVar.zza(i >>> 4);
            cArr2[i | 256] = zzamVar.zza(i & 15);
        }
    }

    public static zzaq zzd() {
        return zza;
    }

    abstract int zza(byte[] bArr, CharSequence charSequence) throws zzao;

    abstract int zzb(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence zzc(CharSequence charSequence) {
        throw null;
    }

    public final byte[] zze(CharSequence charSequence) {
        try {
            CharSequence zzc = zzc(charSequence);
            int zzb = zzb(zzc.length());
            byte[] bArr = new byte[zzb];
            int zza2 = zza(bArr, zzc);
            if (zza2 == zzb) {
                return bArr;
            }
            byte[] bArr2 = new byte[zza2];
            System.arraycopy(bArr, 0, bArr2, 0, zza2);
            return bArr2;
        } catch (zzao e) {
            throw new IllegalArgumentException(e);
        }
    }
}
