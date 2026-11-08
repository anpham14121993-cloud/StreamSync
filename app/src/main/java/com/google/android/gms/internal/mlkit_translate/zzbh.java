package com.google.android.gms.internal.mlkit_translate;

import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public abstract class zzbh {
    static final zzbh zzb = zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbh(zzbg zzbgVar) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static zzbh zzb() {
        zzbc zzbcVar = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        try {
            Class.forName("java.nio.file.Path");
            return new zzbd(zzbcVar);
        } catch (ClassNotFoundException e) {
            try {
                return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue() < ((Integer) Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get(null)).intValue() ? new zzbf(objArr3 == true ? 1 : 0) : new zzav(objArr2 == true ? 1 : 0);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                return new zzbf(objArr == true ? 1 : 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract File zza();
}
