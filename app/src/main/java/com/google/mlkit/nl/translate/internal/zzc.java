package com.google.mlkit.nl.translate.internal;

import java.io.File;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zza(File file) throws IOException {
        synchronized (zzc.class) {
            if (file.exists()) {
                if (!file.isDirectory()) {
                    throw new IOException(String.valueOf(String.valueOf(file)).concat(" exists, but is not a directory"));
                }
            } else if (!file.mkdirs()) {
                throw new IOException("Can not create directory ".concat(String.valueOf(String.valueOf(file))));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzc(File file, boolean z) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                zzc(file2, true);
            }
        }
        return !z || file.delete();
    }
}
