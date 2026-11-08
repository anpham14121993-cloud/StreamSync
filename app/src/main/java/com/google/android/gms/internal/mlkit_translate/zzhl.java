package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzhl implements ObjectEncoder {
    static final zzhl zza = new zzhl();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("screenName");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("sessionId");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("timestampMs");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        builder3.withProperty(zzboVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("options");
        zzbo zzboVar4 = new zzbo();
        zzboVar4.zza(4);
        builder4.withProperty(zzboVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("elementName");
        zzbo zzboVar5 = new zzbo();
        zzboVar5.zza(5);
        builder5.withProperty(zzboVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("isAutoCaptureMode");
        zzbo zzboVar6 = new zzbo();
        zzboVar6.zza(6);
        builder6.withProperty(zzboVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("pageIndex");
        zzbo zzboVar7 = new zzbo();
        zzboVar7.zza(7);
        builder7.withProperty(zzboVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("appliedToAllPages");
        zzbo zzboVar8 = new zzbo();
        zzboVar8.zza(8);
        builder8.withProperty(zzboVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("errorCode");
        zzbo zzboVar9 = new zzbo();
        zzboVar9.zza(9);
        builder9.withProperty(zzboVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("callerAppId");
        zzbo zzboVar10 = new zzbo();
        zzboVar10.zza(10);
        builder10.withProperty(zzboVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("cleanUpStrokeSize");
        zzbo zzboVar11 = new zzbo();
        zzboVar11.zza(11);
        builder11.withProperty(zzboVar11.zzb()).build();
    }

    private zzhl() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
