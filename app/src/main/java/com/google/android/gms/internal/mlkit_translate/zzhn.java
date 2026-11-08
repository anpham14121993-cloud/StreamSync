package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzhn implements ObjectEncoder {
    static final zzhn zza = new zzhn();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("initialImageUriCount");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("defaultCaptureMode");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("flashModeChangeAllowed");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        builder3.withProperty(zzboVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("galleryImportAllowed");
        zzbo zzboVar4 = new zzbo();
        zzboVar4.zza(4);
        builder4.withProperty(zzboVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("multiPageAllowed");
        zzbo zzboVar5 = new zzbo();
        zzboVar5.zza(5);
        builder5.withProperty(zzboVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("filterAllowed");
        zzbo zzboVar6 = new zzbo();
        zzboVar6.zza(6);
        builder6.withProperty(zzboVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("targetResolutionWidth");
        zzbo zzboVar7 = new zzbo();
        zzboVar7.zza(7);
        builder7.withProperty(zzboVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("targetResolutionHeight");
        zzbo zzboVar8 = new zzbo();
        zzboVar8.zza(8);
        builder8.withProperty(zzboVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("resultFormats");
        zzbo zzboVar9 = new zzbo();
        zzboVar9.zza(9);
        builder9.withProperty(zzboVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("pageEditListenerSet");
        zzbo zzboVar10 = new zzbo();
        zzboVar10.zza(10);
        builder10.withProperty(zzboVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("shadowRemovalAllowed");
        zzbo zzboVar11 = new zzbo();
        zzboVar11.zza(11);
        builder11.withProperty(zzboVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("stainRemovalAllowed");
        zzbo zzboVar12 = new zzbo();
        zzboVar12.zza(12);
        builder12.withProperty(zzboVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("enableAllNewFeaturesByDefault");
        zzbo zzboVar13 = new zzbo();
        zzboVar13.zza(13);
        builder13.withProperty(zzboVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("pageLimitMax");
        zzbo zzboVar14 = new zzbo();
        zzboVar14.zza(14);
        builder14.withProperty(zzboVar14.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("enableGalleryImportAutoTransform");
        zzbo zzboVar15 = new zzbo();
        zzboVar15.zza(15);
        builder15.withProperty(zzboVar15.zzb()).build();
    }

    private zzhn() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
