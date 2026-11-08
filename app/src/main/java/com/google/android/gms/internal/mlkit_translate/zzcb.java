package com.google.android.gms.internal.mlkit_translate;

import java.io.IOException;
import java.io.StringWriter;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public class zzcb {
    @Deprecated
    public zzcb() {
    }

    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            zzdc zzdcVar = new zzdc(stringWriter);
            zzdcVar.zzj(zzcj.LENIENT);
            zzcy.zza.zzb(zzdcVar, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final zzce zzb() {
        if (this instanceof zzce) {
            return (zzce) this;
        }
        throw new IllegalStateException("Not a JSON Object: ".concat(toString()));
    }
}
