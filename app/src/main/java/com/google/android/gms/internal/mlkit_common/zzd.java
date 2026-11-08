package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.core.content.PermissionChecker;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
final class zzd extends zzk {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_common.zzk
    public final int zza(Context context, zzj zzjVar, boolean z) {
        return (zzjVar.zza.getAuthority().lastIndexOf(64) < 0 || PermissionChecker.checkSelfPermission(context, "android.permission.INTERACT_ACROSS_USERS") != 0) ? 3 : 2;
    }
}
