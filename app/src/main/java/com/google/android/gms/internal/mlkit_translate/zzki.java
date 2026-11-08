package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzki implements ObjectEncoder {
    static final zzki zza = new zzki();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("maxLabels");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("confidenceThreshold");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("customLocalModelOptions");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        builder3.withProperty(zzboVar3.zzb()).build();
    }

    private zzki() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
