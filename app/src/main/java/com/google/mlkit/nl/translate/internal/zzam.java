package com.google.mlkit.nl.translate.internal;

import com.google.android.gms.internal.mlkit_translate.zznh;
import com.google.android.gms.internal.mlkit_translate.zzoe;
import com.google.android.gms.internal.mlkit_translate.zzoq;
import com.google.android.gms.internal.mlkit_translate.zzor;
import com.google.android.gms.internal.mlkit_translate.zzoz;
import com.google.android.gms.internal.mlkit_translate.zzuy;
import com.google.android.gms.internal.mlkit_translate.zzvd;
import com.google.android.gms.internal.mlkit_translate.zzvl;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzam implements RemoteModelManagerInterface {
    public static final /* synthetic */ int zza = 0;
    private final zzuy zzb = zzvl.zzb("translate");
    private final zzz zzc;
    private final Task zzd;

    public zzam(zzz zzzVar, zzp zzpVar) {
        this.zzc = zzzVar;
        this.zzd = zzpVar.getMigrationTask();
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task deleteDownloadedModel(RemoteModel remoteModel) {
        final TranslateRemoteModel translateRemoteModel = (TranslateRemoteModel) remoteModel;
        if (translateRemoteModel.getLanguage().equals(TranslateLanguage.ENGLISH)) {
            return Tasks.forResult(null);
        }
        return this.zzd.continueWith(MLTaskExecutor.workerThreadExecutor(), new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzak
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzam.this.zzd(translateRemoteModel, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.nl.translate.internal.zzal
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzam.this.zze(task);
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final /* bridge */ /* synthetic */ Task download(RemoteModel remoteModel, final DownloadConditions downloadConditions) {
        final TranslateRemoteModel translateRemoteModel = (TranslateRemoteModel) remoteModel;
        if (translateRemoteModel.getLanguage().equals(TranslateLanguage.ENGLISH)) {
            return Tasks.forResult(null);
        }
        return this.zzd.continueWithTask(MLTaskExecutor.workerThreadExecutor(), new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzag
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzam.this.zzb(translateRemoteModel, downloadConditions, task);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    public final Task getDownloadedModels() {
        List<String> allLanguages = TranslateLanguage.getAllLanguages();
        final ArrayList arrayList = new ArrayList(allLanguages.size());
        ArrayList arrayList2 = new ArrayList(allLanguages.size());
        com.google.android.gms.internal.mlkit_translate.zzal listIterator = ((com.google.android.gms.internal.mlkit_translate.zzt) allLanguages).listIterator(0);
        while (listIterator.hasNext()) {
            TranslateRemoteModel build = new TranslateRemoteModel.Builder((String) listIterator.next()).build();
            arrayList.add(build);
            arrayList2.add(isModelDownloaded(build));
        }
        return Tasks.whenAllSuccess(arrayList2).continueWith(new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzaj
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                int i = zzam.zza;
                List list = (List) task.getResult();
                HashSet hashSet = new HashSet();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (((Boolean) list.get(i2)).booleanValue()) {
                        hashSet.add((TranslateRemoteModel) arrayList.get(i2));
                    }
                }
                return hashSet;
            }
        });
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final Task isModelDownloaded(final TranslateRemoteModel translateRemoteModel) {
        if (translateRemoteModel.getLanguage().equals(TranslateLanguage.ENGLISH)) {
            return Tasks.forResult(true);
        }
        return this.zzd.continueWith(MLTaskExecutor.workerThreadExecutor(), new Continuation() { // from class: com.google.mlkit.nl.translate.internal.zzah
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzam.this.zzc(translateRemoteModel, task);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.nl.translate.internal.zzai
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzam.this.zzf(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zzb(TranslateRemoteModel translateRemoteModel, DownloadConditions downloadConditions, Task task) throws Exception {
        return this.zzc.zza(translateRemoteModel, true).zzb(downloadConditions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zzc(TranslateRemoteModel translateRemoteModel, Task task) throws Exception {
        return Boolean.valueOf(this.zzc.zza(translateRemoteModel, false).zzf());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Void zzd(TranslateRemoteModel translateRemoteModel, Task task) throws Exception {
        this.zzc.zza(translateRemoteModel, true).zze();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zze(Task task) {
        boolean isSuccessful = task.isSuccessful();
        zzor zzorVar = new zzor();
        zznh zznhVar = new zznh();
        zznhVar.zzb(zzoz.BASE_TRANSLATE);
        zznhVar.zza(Boolean.valueOf(isSuccessful));
        zzorVar.zzf(zznhVar.zzc());
        this.zzb.zzd(zzvd.zzf(zzorVar), zzoq.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Task task) {
        boolean booleanValue = ((Boolean) task.getResult()).booleanValue();
        zzor zzorVar = new zzor();
        zzoe zzoeVar = new zzoe();
        zzoeVar.zzb(zzoz.BASE_TRANSLATE);
        zzoeVar.zza(Boolean.valueOf(booleanValue));
        zzorVar.zzh(zzoeVar.zzc());
        this.zzb.zzd(zzvd.zzf(zzorVar), zzoq.REMOTE_MODEL_IS_DOWNLOADED);
    }
}
