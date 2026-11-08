package com.google.android.gms.internal.mlkit_translate;

import androidx.core.app.NotificationCompat;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzll implements ObjectEncoder {
    static final zzll zza = new zzll();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        zzb = builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("options");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        zzc = builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("inputLength");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        zzd = builder3.withProperty(zzboVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("outputLength");
        zzbo zzboVar4 = new zzbo();
        zzboVar4.zza(4);
        zze = builder4.withProperty(zzboVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("loadDictionaryErrorCode");
        zzbo zzboVar5 = new zzbo();
        zzboVar5.zza(5);
        zzf = builder5.withProperty(zzboVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("translateResultStatusCode");
        zzbo zzboVar6 = new zzbo();
        zzboVar6.zza(6);
        zzg = builder6.withProperty(zzboVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS);
        zzbo zzboVar7 = new zzbo();
        zzboVar7.zza(7);
        zzh = builder7.withProperty(zzboVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("downloadHttpResponseCode");
        zzbo zzboVar8 = new zzbo();
        zzboVar8.zza(8);
        zzi = builder8.withProperty(zzboVar8.zzb()).build();
    }

    private zzll() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzse zzseVar = (zzse) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzseVar.zza());
        objectEncoderContext2.add(zzc, zzseVar.zzc());
        objectEncoderContext2.add(zzd, zzseVar.zzd());
        objectEncoderContext2.add(zze, zzseVar.zzf());
        objectEncoderContext2.add(zzf, zzseVar.zze());
        objectEncoderContext2.add(zzg, zzseVar.zzg());
        objectEncoderContext2.add(zzh, zzseVar.zzb());
        objectEncoderContext2.add(zzi, (Object) null);
    }
}
