package com.google.android.gms.internal.mlkit_translate;

import androidx.autofill.HintConstants;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzln implements ObjectEncoder {
    static final zzln zza = new zzln();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(HintConstants.AUTOFILL_HINT_NAME);
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("stages");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("runMiniBenchmark");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        builder3.withProperty(zzboVar3.zzb()).build();
    }

    private zzln() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
