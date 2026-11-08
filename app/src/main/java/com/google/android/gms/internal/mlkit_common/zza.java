package com.google.android.gms.internal.mlkit_common;

import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zza extends Handler {
    public zza() {
        Looper.getMainLooper();
    }

    public zza(Looper looper) {
        super(looper);
        Looper.getMainLooper();
    }
}
