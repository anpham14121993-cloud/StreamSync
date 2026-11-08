package com.google.android.gms.internal.mlkit_translate;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzvk extends LazyInstanceMap {
    private zzvk() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzvk(zzvj zzvjVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzus zzusVar = (zzus) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzuy(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzut(MlKitContext.getInstance().getApplicationContext(), zzusVar), zzusVar.zzb());
    }
}
