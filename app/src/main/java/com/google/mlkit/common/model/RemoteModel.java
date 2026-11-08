package com.google.mlkit.common.model;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzq;
import com.google.android.gms.internal.mlkit_common.zzr;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.BaseModel;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public abstract class RemoteModel {
    private final String zzc;
    private final BaseModel zzd;
    private final ModelType zze;
    private String zzf;
    private static final Map zzb = new EnumMap(BaseModel.class);
    public static final Map zza = new EnumMap(BaseModel.class);

    /* JADX INFO: Access modifiers changed from: protected */
    public RemoteModel(String modelName, BaseModel baseModel, ModelType modelType) {
        Preconditions.checkArgument(TextUtils.isEmpty(modelName) == (baseModel != null), "One of cloud model name and base model cannot be empty");
        this.zzc = modelName;
        this.zzd = baseModel;
        this.zze = modelType;
    }

    public boolean baseModelHashMatches(String hash) {
        BaseModel baseModel = this.zzd;
        if (baseModel == null) {
            return false;
        }
        return hash.equals(zzb.get(baseModel));
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RemoteModel)) {
            return false;
        }
        RemoteModel remoteModel = (RemoteModel) o;
        return Objects.equal(this.zzc, remoteModel.zzc) && Objects.equal(this.zzd, remoteModel.zzd) && Objects.equal(this.zze, remoteModel.zze);
    }

    public String getModelHash() {
        return this.zzf;
    }

    public String getModelName() {
        return this.zzc;
    }

    public String getModelNameForBackend() {
        String str = this.zzc;
        if (str != null) {
            return str;
        }
        return (String) zza.get(this.zzd);
    }

    public ModelType getModelType() {
        return this.zze;
    }

    public String getUniqueModelNameForPersist() {
        String str = this.zzc;
        if (str != null) {
            return str;
        }
        return "COM.GOOGLE.BASE_".concat(String.valueOf((String) zza.get(this.zzd)));
    }

    public int hashCode() {
        return Objects.hashCode(this.zzc, this.zzd, this.zze);
    }

    public boolean isBaseModel() {
        return this.zzd != null;
    }

    public void setModelHash(String str) {
        this.zzf = str;
    }

    public String toString() {
        zzq zzb2 = zzr.zzb("RemoteModel");
        zzb2.zza("modelName", this.zzc);
        zzb2.zza("baseModel", this.zzd);
        zzb2.zza("modelType", this.zze);
        return zzb2.toString();
    }
}
