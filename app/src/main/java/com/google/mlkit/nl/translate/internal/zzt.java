package com.google.mlkit.nl.translate.internal;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.google.android.gms.internal.mlkit_translate.zzce;
import com.google.android.gms.internal.mlkit_translate.zzcg;
import com.google.android.gms.internal.mlkit_translate.zzci;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.nl.translate.R;
import com.google.mlkit.nl.translate.TranslateRemoteModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public final class zzt {
    private static final int zza = R.raw.translate_models_metadata;
    private final zzs zzb;

    public zzt(zzs zzsVar) {
        this.zzb = zzsVar;
    }

    private static final ModelInfo zzb(String str, String str2, String str3) {
        int i = TranslateRemoteModel.zzb;
        return new ModelInfo(TranslateRemoteModel.zza(str), Uri.parse(String.format(str3, "v5", "r29", str)), str2, ModelType.TRANSLATE);
    }

    public final List zza(Context context, TranslateRemoteModel translateRemoteModel) throws MlKitException {
        String zzb = zzac.zzb(translateRemoteModel.getLanguage());
        try {
            InputStream openRawResource = context.getResources().openRawResource(zza);
            try {
                String next = new Scanner(openRawResource).useDelimiter("\\A").next();
                if (openRawResource != null) {
                    openRawResource.close();
                }
                try {
                    zzce zzb2 = zzcg.zzb(next).zzb();
                    zzce zzc = zzb2.zzc("PKG_HIGH");
                    zzce zzc2 = zzb2.zzc("PKG_LOW");
                    if (zzc.zzg(zzb) || zzc2.zzg(zzb)) {
                        try {
                            String zze = (zzc.zzg(zzb) ? zzc.zza(zzb) : zzc2.zza(zzb)).zzb().zzd("HASH").zze();
                            ArrayList arrayList = new ArrayList(2);
                            arrayList.add(zzb(zzb, zze, "https://redirector.gvt1.com/edgedl/translate/offline/%s/high/%s/%s.zip"));
                            arrayList.add(zzb(zzb, zze, "https://dl.google.com/translate/offline/%s/high/%s/%s.zip"));
                            return arrayList;
                        } catch (ClassCastException | IllegalStateException | NullPointerException e) {
                            this.zzb.zzq();
                            throw new MlKitException("Could not locate model's hash.", 13, e);
                        }
                    }
                    this.zzb.zzs();
                    throw new MlKitException("Could not locate the model metadata.", 13);
                } catch (zzci e2) {
                    this.zzb.zzr();
                    throw new MlKitException("Translate metadata could not be parsed.", 13, e2);
                }
            } finally {
            }
        } catch (Resources.NotFoundException | IOException e3) {
            this.zzb.zzp();
            throw new MlKitException("Translate metadata could not be located.", 13, e3);
        }
    }
}
