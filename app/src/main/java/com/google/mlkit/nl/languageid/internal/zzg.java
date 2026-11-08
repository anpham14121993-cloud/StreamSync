package com.google.mlkit.nl.languageid.internal;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.ModelResource;
import com.google.mlkit.nl.languageid.IdentifiedLanguage;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes25.dex */
public final class zzg extends ModelResource {
    private LanguageIdentifierDelegate zza;
    private LanguageIdentificationOptions zzb;
    private final Context zzc;
    private final LanguageIdentifierCreatorDelegate zzd;
    private final boolean zze;

    public zzg(Context context, LanguageIdentifierCreatorDelegate languageIdentifierCreatorDelegate) {
        this.zzc = context;
        this.zzd = languageIdentifierCreatorDelegate;
        this.zze = languageIdentifierCreatorDelegate.getPriority() == 100;
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void load() throws MlKitException {
        this.taskQueue.checkIsRunningOnCurrentThread();
        if (this.zza == null) {
            this.zza = this.zzd.create(this.zzc, this.zzb);
            this.zza.init();
        }
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final void release() {
        this.taskQueue.checkIsRunningOnCurrentThread();
        LanguageIdentifierDelegate languageIdentifierDelegate = this.zza;
        if (languageIdentifierDelegate != null) {
            languageIdentifierDelegate.release();
            this.zza = null;
        }
    }

    public final String zzc(String str, float f) throws MlKitException {
        String str2;
        if (this.zza == null) {
            load();
        }
        if (str.isEmpty()) {
            return "und";
        }
        Iterator<IdentifiedLanguage> it = ((LanguageIdentifierDelegate) Preconditions.checkNotNull(this.zza)).identifyPossibleLanguages(str, f).iterator();
        while (true) {
            if (it.hasNext()) {
                IdentifiedLanguage next = it.next();
                if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(next.getLanguageTag())) {
                    str2 = next.getLanguageTag();
                    break;
                }
            } else {
                str2 = "";
                break;
            }
        }
        return str2.isEmpty() ? "und" : "iw".equals(str2) ? TranslateLanguage.HEBREW : str2;
    }

    public final List zzd(String str, float f) throws MlKitException {
        if (this.zza == null) {
            load();
        }
        ArrayList arrayList = new ArrayList();
        if (str.isEmpty()) {
            arrayList.add(new IdentifiedLanguage("und", 1.0f));
            return arrayList;
        }
        for (IdentifiedLanguage identifiedLanguage : ((LanguageIdentifierDelegate) Preconditions.checkNotNull(this.zza)).identifyPossibleLanguages(str, f)) {
            if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(identifiedLanguage.getLanguageTag())) {
                arrayList.add(new IdentifiedLanguage("iw".equals(identifiedLanguage.getLanguageTag()) ? TranslateLanguage.HEBREW : identifiedLanguage.getLanguageTag(), identifiedLanguage.getConfidence()));
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new IdentifiedLanguage("und", 1.0f));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(LanguageIdentificationOptions languageIdentificationOptions) {
        this.zzb = languageIdentificationOptions;
    }

    public final boolean zzf() {
        return this.zze;
    }
}
