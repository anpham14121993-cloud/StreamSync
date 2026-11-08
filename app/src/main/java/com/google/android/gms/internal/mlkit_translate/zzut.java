package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzut implements zzuq {
    final List zza = new ArrayList();

    public zzut(Context context, zzus zzusVar) {
        if (zzusVar.zzc()) {
            this.zza.add(new zzvi(context, zzusVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzuq
    public final void zza(zzup zzupVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzuq) it.next()).zza(zzupVar);
        }
    }
}
