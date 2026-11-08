package com.google.android.gms.internal.mlkit_translate;

import java.io.File;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzbf extends zzbh {
    private zzbf() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbf(zzbe zzbeVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzbh
    final File zza() {
        throw new IllegalStateException("Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().");
    }
}
