package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzho implements ObjectEncoder {
    static final zzho zza = new zzho();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("source");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appliedFilter");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isAutoCaptureManuallyTriggered");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        builder3.withProperty(zzboVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isRotated");
        zzbo zzboVar4 = new zzbo();
        zzboVar4.zza(4);
        builder4.withProperty(zzboVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hasLowConfidenceProposedCorners");
        zzbo zzboVar5 = new zzbo();
        zzboVar5.zza(5);
        builder5.withProperty(zzboVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("autoCaptureTriggerLatencyMs");
        zzbo zzboVar6 = new zzbo();
        zzboVar6.zza(6);
        builder6.withProperty(zzboVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("galleryImportProcessingMs");
        zzbo zzboVar7 = new zzbo();
        zzboVar7.zza(7);
        builder7.withProperty(zzboVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("imageWidth");
        zzbo zzboVar8 = new zzbo();
        zzboVar8.zza(8);
        builder8.withProperty(zzboVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("imageHeight");
        zzbo zzboVar9 = new zzbo();
        zzboVar9.zza(9);
        builder9.withProperty(zzboVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("proposedCorners");
        zzbo zzboVar10 = new zzbo();
        zzboVar10.zza(10);
        builder10.withProperty(zzboVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("adjustedCorners");
        zzbo zzboVar11 = new zzbo();
        zzboVar11.zza(11);
        builder11.withProperty(zzboVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isShadowRemoved");
        zzbo zzboVar12 = new zzbo();
        zzboVar12.zza(12);
        builder12.withProperty(zzboVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("numOfAppliedCleanUpStrokes");
        zzbo zzboVar13 = new zzbo();
        zzboVar13.zza(13);
        builder13.withProperty(zzboVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("numOfAttemptedCleanUpStrokes");
        zzbo zzboVar14 = new zzbo();
        zzboVar14.zza(14);
        builder14.withProperty(zzboVar14.zzb()).build();
    }

    private zzho() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
