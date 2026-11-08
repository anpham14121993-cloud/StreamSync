package com.google.mlkit.nl.translate.internal;

import android.app.DownloadManager;
import android.content.Context;
import com.google.android.gms.internal.mlkit_translate.zztc;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.nl.translate.TranslateRemoteModel;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzg {
    private final MlKitContext zza;
    private final Context zzb;
    private final zzq zzc;
    private final zzae zzd;
    private final ModelFileHelper zze;
    private final SharedPrefManager zzf;

    public zzg(MlKitContext mlKitContext, Context context, zzq zzqVar, zzae zzaeVar, ModelFileHelper modelFileHelper, SharedPrefManager sharedPrefManager) {
        this.zza = mlKitContext;
        this.zzb = context;
        this.zzc = zzqVar;
        this.zzd = zzaeVar;
        this.zze = modelFileHelper;
        this.zzf = sharedPrefManager;
    }

    public final zzh zza(TranslateRemoteModel translateRemoteModel) {
        String[] split = translateRemoteModel.getUniqueModelNameForPersist().split("_");
        zztc zztcVar = new zztc();
        zztcVar.zza(split[0]);
        zztcVar.zzb(split[1]);
        zzs zza = this.zzc.zza(zztcVar.zzc());
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.zza, translateRemoteModel, null, this.zze, new zzaf(this.zza, zzac.zzb(translateRemoteModel.getLanguage())));
        zzt zztVar = new zzt(zza);
        DownloadManager downloadManager = (DownloadManager) this.zzb.getSystemService("download");
        zzb zzbVar = new zzb();
        return new zzh(this.zzb, remoteModelFileManager, translateRemoteModel, this.zzd, zza, zztVar, downloadManager, this.zze, this.zzf, zzbVar);
    }
}
