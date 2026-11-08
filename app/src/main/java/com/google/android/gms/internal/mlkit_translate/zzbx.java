package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzbx {
    private final Map zza;
    private final Map zzb;
    private final ObjectEncoder zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbx(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = objectEncoder;
    }

    public final byte[] zza(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new zzbu(byteArrayOutputStream, this.zza, this.zzb, this.zzc).zzf(obj);
        } catch (IOException e) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
