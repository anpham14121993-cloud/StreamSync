package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
final class zzls extends LazyInstanceMap {
    private zzls() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzlc zzlcVar = (zzlc) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzli(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzld(MlKitContext.getInstance().getApplicationContext(), zzlcVar), zzlcVar.zzb());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzls(zzlr zzlrVar) {
    }
}
