package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzhf implements ObjectEncoder {
    static final zzhf zza = new zzhf();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("modelType");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        zzb = builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isSuccessful");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        zzc = builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("modelName");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        zzd = builder3.withProperty(zzboVar3.zzb()).build();
    }

    private zzhf() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zznj zznjVar = (zznj) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zznjVar.zza());
        objectEncoderContext2.add(zzc, zznjVar.zzb());
        objectEncoderContext2.add(zzd, (Object) null);
    }
}
