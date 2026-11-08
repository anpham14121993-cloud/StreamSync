package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzsr extends LazyInstanceMap {
    private zzsr() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzsr(zzsq zzsqVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzsb zzsbVar = (zzsb) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzsh(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzsc(MlKitContext.getInstance().getApplicationContext(), zzsbVar), zzsbVar.zzb());
    }
}
