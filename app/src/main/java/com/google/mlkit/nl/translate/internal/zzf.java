package com.google.mlkit.nl.translate.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import java.util.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
final class zzf extends BroadcastReceiver {
    final /* synthetic */ zzh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzh zzhVar, zzh zzhVar2) {
        this.zza = zzhVar2;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Handler handler = MLTaskExecutor.getInstance().getHandler();
        final zzh zzhVar = this.zza;
        Objects.requireNonNull(zzhVar);
        handler.post(new Runnable() { // from class: com.google.mlkit.nl.translate.internal.zze
            @Override // java.lang.Runnable
            public final void run() {
                zzh.this.zzg();
            }
        });
    }
}
