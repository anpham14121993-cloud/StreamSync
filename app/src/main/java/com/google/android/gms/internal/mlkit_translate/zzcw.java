package com.google.android.gms.internal.mlkit_translate;

import java.io.EOFException;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzcw {
    public static zzcb zza(zzda zzdaVar) throws zzcf {
        boolean z;
        try {
            try {
                zzdaVar.zzn();
                z = false;
                try {
                } catch (EOFException e) {
                    e = e;
                }
                try {
                    return (zzcb) zzcy.zza.zza(zzdaVar);
                } catch (EOFException e2) {
                    e = e2;
                    if (z) {
                        return zzcd.zza;
                    }
                    throw new zzci(e);
                }
            } catch (EOFException e3) {
                e = e3;
                z = true;
            }
        } catch (zzdd e4) {
            throw new zzci(e4);
        } catch (IOException e5) {
            throw new zzcc(e5);
        } catch (NumberFormatException e6) {
            throw new zzci(e6);
        }
    }
}
