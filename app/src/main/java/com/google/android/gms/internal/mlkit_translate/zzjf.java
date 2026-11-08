package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzjf implements ObjectEncoder {
    static final zzjf zza = new zzjf();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("handwritingRecognitionStrategy");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("recognitionSchedulingStrategy");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        builder2.withProperty(zzboVar2.zzb()).build();
    }

    private zzjf() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
