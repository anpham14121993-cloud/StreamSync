package com.google.mlkit.nl.translate.internal;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.nl.translate.TranslatorOptions;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzi extends LazyInstanceMap {
    private final zzae zza;
    private final ModelFileHelper zzb;
    private final zzq zzc;

    public zzi(zzae zzaeVar, ModelFileHelper modelFileHelper, zzq zzqVar) {
        this.zza = zzaeVar;
        this.zzb = modelFileHelper;
        this.zzc = zzqVar;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        TranslatorOptions translatorOptions = (TranslatorOptions) obj;
        String zzb = translatorOptions.zzb();
        String zzc = translatorOptions.zzc();
        return new TranslateJni(this.zza, this.zzc.zza(translatorOptions.zza()), this.zzb, zzb, zzc);
    }
}
