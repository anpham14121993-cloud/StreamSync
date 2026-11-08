package com.google.mlkit.nl.translate.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.DownloadConditions;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final /* synthetic */ class zzaq implements Continuation {
    public final /* synthetic */ TranslatorImpl zza;
    public final /* synthetic */ DownloadConditions zzb;

    public /* synthetic */ zzaq(TranslatorImpl translatorImpl, DownloadConditions downloadConditions) {
        this.zza = translatorImpl;
        this.zzb = downloadConditions;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return this.zza.zza(this.zzb, task);
    }
}
