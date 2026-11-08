package com.google.android.gms.internal.mlkit_translate;

import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzav extends zzbh {
    private zzav() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzav(zzau zzauVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzbh
    public final File zza() {
        File file = new File(zzh.JAVA_IO_TMPDIR.zza());
        String str = System.currentTimeMillis() + "-";
        for (int i = 0; i < 10000; i++) {
            File file2 = new File(file, str + i);
            if (file2.mkdir()) {
                return file2;
            }
        }
        throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + str + "0 to " + str + "9999)");
    }
}
