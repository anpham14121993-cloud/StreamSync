package com.google.android.gms.internal.mlkit_translate;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvn {
    private static final GmsLogger zza = new GmsLogger("RemoteModelUtils", "");

    public static zzox zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzvc zzvcVar) {
        zzoz zzozVar;
        ModelType zzb = zzvcVar.zzb();
        String modelHash = remoteModel.getModelHash();
        zzpd zzpdVar = new zzpd();
        zzoy zzoyVar = new zzoy();
        zzoyVar.zzc(remoteModel.getModelNameForBackend());
        zzoyVar.zzd(zzpa.CLOUD);
        if (modelHash == null) {
            modelHash = "";
        }
        zzoyVar.zza(modelHash);
        switch (zzvm.zza[zzb.ordinal()]) {
            case 1:
                zzozVar = zzoz.BASE_TRANSLATE;
                break;
            case 2:
                zzozVar = zzoz.BASE_DIGITAL_INK;
                break;
            case 3:
                zzozVar = zzoz.CUSTOM;
                break;
            default:
                zzozVar = zzoz.TYPE_UNKNOWN;
                break;
        }
        zzoyVar.zzb(zzozVar);
        zzpdVar.zzb(zzoyVar.zzg());
        zzpg zzc = zzpdVar.zzc();
        zzou zzouVar = new zzou();
        zzouVar.zzd(zzvcVar.zzc());
        zzouVar.zzc(zzvcVar.zzd());
        zzouVar.zzb(Long.valueOf(zzvcVar.zza()));
        zzouVar.zze(zzc);
        if (zzvcVar.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zzouVar.zzf(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        return zzouVar.zzh();
    }
}
