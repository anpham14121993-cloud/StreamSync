package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzsc implements zzrz {
    final List zza = new ArrayList();

    public zzsc(Context context, zzsb zzsbVar) {
        if (zzsbVar.zzc()) {
            this.zza.add(new zzsp(context, zzsbVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzrz
    public final void zza(zzry zzryVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzrz) it.next()).zza(zzryVar);
        }
    }
}
