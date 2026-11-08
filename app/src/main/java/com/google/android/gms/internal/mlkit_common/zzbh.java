package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzbh {
    private final Map zza;
    private final Map zzb;
    private final ObjectEncoder zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbh(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = objectEncoder;
    }

    public final byte[] zza(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new zzbe(byteArrayOutputStream, this.zza, this.zzb, this.zzc).zzf(obj);
        } catch (IOException e) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
