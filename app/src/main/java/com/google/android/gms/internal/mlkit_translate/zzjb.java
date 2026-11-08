package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzjb implements ObjectEncoder {
    static final zzjb zza = new zzjb();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageCode");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
    }

    private zzjb() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
