package com.google.android.gms.internal.mlkit_translate;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public class zzap extends zzaq {
    final zzam zza;

    @CheckForNull
    final Character zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzap(zzam zzamVar, @CheckForNull Character ch) {
        this.zza = zzamVar;
        if (ch != null) {
            ch.charValue();
            if (zzamVar.zzd('=')) {
                throw new IllegalArgumentException(zzi.zza("Padding character %s was already in alphabet", ch));
            }
        }
        this.zzb = ch;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzap) {
            zzap zzapVar = (zzap) obj;
            if (this.zza.equals(zzapVar.zza) && Objects.equals(this.zzb, zzapVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Character ch = this.zzb;
        return Objects.hashCode(ch) ^ this.zza.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zza);
        if (8 % this.zza.zza != 0) {
            if (this.zzb == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzb);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzaq
    int zza(byte[] bArr, CharSequence charSequence) throws zzao {
        zzam zzamVar;
        CharSequence zzc = zzc(charSequence);
        if (this.zza.zzc(zzc.length())) {
            int i = 0;
            int i2 = 0;
            while (i < zzc.length()) {
                long j = 0;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    zzamVar = this.zza;
                    if (i3 >= zzamVar.zzb) {
                        break;
                    }
                    j <<= zzamVar.zza;
                    if (i + i3 < zzc.length()) {
                        j |= this.zza.zzb(zzc.charAt(i4 + i));
                        i4++;
                    }
                    i3++;
                }
                int i5 = zzamVar.zzc;
                int i6 = i4 * zzamVar.zza;
                int i7 = (i5 - 1) * 8;
                while (i7 >= (i5 * 8) - i6) {
                    bArr[i2] = (byte) ((j >>> i7) & 255);
                    i7 -= 8;
                    i2++;
                }
                i += this.zza.zzb;
            }
            return i2;
        }
        throw new zzao("Invalid input length " + zzc.length());
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzaq
    final int zzb(int i) {
        return (int) (((this.zza.zza * i) + 7) / 8);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzaq
    final CharSequence zzc(CharSequence charSequence) {
        if (charSequence == null) {
            throw null;
        }
        Character ch = this.zzb;
        if (ch == null) {
            return charSequence;
        }
        ch.charValue();
        int length = charSequence.length() - 1;
        while (length >= 0 && charSequence.charAt(length) == '=') {
            length--;
        }
        return charSequence.subSequence(0, length + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzap(String str, String str2, @CheckForNull Character ch) {
        this(new zzam(str, str2.toCharArray()), ch);
    }
}
