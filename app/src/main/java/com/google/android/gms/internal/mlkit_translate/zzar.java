package com.google.android.gms.internal.mlkit_translate;

import java.io.OutputStream;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzar extends OutputStream {
    public final String toString() {
        return "ByteStreams.nullOutputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            zzf.zze(i, i2 + i, bArr.length);
            return;
        }
        throw null;
    }
}
