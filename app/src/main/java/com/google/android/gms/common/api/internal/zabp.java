package com.google.android.gms.common.api.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes24.dex */
public final class zabp implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ zabq zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabp(zabq zabqVar) {
        this.zaa = zabqVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        Handler handler;
        handler = this.zaa.zaa.zar;
        handler.post(new zabo(this));
    }
}
