package com.google.mlkit.nl.translate;

import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.BaseModel;
import com.google.mlkit.nl.translate.internal.zzac;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public class TranslateRemoteModel extends RemoteModel {
    public static final /* synthetic */ int zzb = 0;
    private final String zzc;

    /* compiled from: com.google.mlkit:translate@@17.0.3 */
    /* loaded from: classes25.dex */
    public static class Builder {
        private final String zza;

        public Builder(String str) {
            this.zza = str;
        }

        public TranslateRemoteModel build() {
            return new TranslateRemoteModel(this.zza, null);
        }
    }

    /* synthetic */ TranslateRemoteModel(String str, zzk zzkVar) {
        super(null, BaseModel.TRANSLATE, ModelType.TRANSLATE);
        this.zzc = str;
    }

    public static String zza(String str) {
        return "COM.GOOGLE.BASE_TRANSLATE:".concat(String.valueOf(str));
    }

    @Override // com.google.mlkit.common.model.RemoteModel
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return (o instanceof TranslateRemoteModel) && super.equals(o) && getLanguage() == ((TranslateRemoteModel) o).getLanguage();
    }

    public String getLanguage() {
        return this.zzc;
    }

    @Override // com.google.mlkit.common.model.RemoteModel
    public final String getModelNameForBackend() {
        return zzac.zzb(getLanguage());
    }

    @Override // com.google.mlkit.common.model.RemoteModel
    public final String getUniqueModelNameForPersist() {
        return zza(zzac.zzb(getLanguage()));
    }

    @Override // com.google.mlkit.common.model.RemoteModel
    public int hashCode() {
        return (super.hashCode() * 31) + getLanguage().hashCode();
    }
}
