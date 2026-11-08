package com.google.android.gms.internal.mlkit_language_id_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
public final class zzld implements zzlh {
    final List zza = new ArrayList();

    public zzld(Context context, zzlc zzlcVar) {
        if (zzlcVar.zzc()) {
            this.zza.add(new zzlq(context, zzlcVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzlh
    public final void zza(zzla zzlaVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzlh) it.next()).zza(zzlaVar);
        }
    }
}
