package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzmc implements ObjectEncoder {
    static final zzmc zza = new zzmc();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appId");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        zzb = builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appVersion");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        zzc = builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        zzd = builder3.withProperty(zzboVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzbo zzboVar4 = new zzbo();
        zzboVar4.zza(4);
        zze = builder4.withProperty(zzboVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzbo zzboVar5 = new zzbo();
        zzboVar5.zza(5);
        zzf = builder5.withProperty(zzboVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzbo zzboVar6 = new zzbo();
        zzboVar6.zza(6);
        zzg = builder6.withProperty(zzboVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzbo zzboVar7 = new zzbo();
        zzboVar7.zza(7);
        zzh = builder7.withProperty(zzboVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzbo zzboVar8 = new zzbo();
        zzboVar8.zza(8);
        zzi = builder8.withProperty(zzboVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzbo zzboVar9 = new zzbo();
        zzboVar9.zza(9);
        zzj = builder9.withProperty(zzboVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzbo zzboVar10 = new zzbo();
        zzboVar10.zza(10);
        zzk = builder10.withProperty(zzboVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzbo zzboVar11 = new zzbo();
        zzboVar11.zza(11);
        zzl = builder11.withProperty(zzboVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzbo zzboVar12 = new zzbo();
        zzboVar12.zza(12);
        zzm = builder12.withProperty(zzboVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzbo zzboVar13 = new zzbo();
        zzboVar13.zza(13);
        zzn = builder13.withProperty(zzboVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzbo zzboVar14 = new zzbo();
        zzboVar14.zza(14);
        zzo = builder14.withProperty(zzboVar14.zzb()).build();
    }

    private zzmc() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzsw zzswVar = (zzsw) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzswVar.zzg());
        objectEncoderContext2.add(zzc, zzswVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzswVar.zzj());
        objectEncoderContext2.add(zzf, zzswVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzswVar.zza());
        objectEncoderContext2.add(zzj, zzswVar.zzi());
        objectEncoderContext2.add(zzk, zzswVar.zzb());
        objectEncoderContext2.add(zzl, zzswVar.zzd());
        objectEncoderContext2.add(zzm, zzswVar.zzc());
        objectEncoderContext2.add(zzn, zzswVar.zze());
        objectEncoderContext2.add(zzo, zzswVar.zzf());
    }
}
