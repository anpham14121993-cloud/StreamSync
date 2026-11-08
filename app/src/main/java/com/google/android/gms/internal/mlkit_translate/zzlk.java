package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzlk implements ObjectEncoder {
    static final zzlk zza = new zzlk();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(3);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(4);
        builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("sdkVersion");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(5);
        builder3.withProperty(zzboVar3.zzb()).build();
    }

    private zzlk() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
