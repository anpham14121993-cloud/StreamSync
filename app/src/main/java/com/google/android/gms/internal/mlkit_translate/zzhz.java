package com.google.android.gms.internal.mlkit_translate;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzhz implements ObjectEncoder {
    static final zzhz zza = new zzhz();
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

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("durationMs");
        zzbo zzboVar = new zzbo();
        zzboVar.zza(1);
        zzb = builder.withProperty(zzboVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCode");
        zzbo zzboVar2 = new zzbo();
        zzboVar2.zza(2);
        zzc = builder2.withProperty(zzboVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzbo zzboVar3 = new zzbo();
        zzboVar3.zza(3);
        zzd = builder3.withProperty(zzboVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("autoManageModelOnBackground");
        zzbo zzboVar4 = new zzbo();
        zzboVar4.zza(4);
        zze = builder4.withProperty(zzboVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("autoManageModelOnLowMemory");
        zzbo zzboVar5 = new zzbo();
        zzboVar5.zza(5);
        zzf = builder5.withProperty(zzboVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("isNnApiEnabled");
        zzbo zzboVar6 = new zzbo();
        zzboVar6.zza(6);
        zzg = builder6.withProperty(zzboVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("eventsCount");
        zzbo zzboVar7 = new zzbo();
        zzboVar7.zza(7);
        zzh = builder7.withProperty(zzboVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("otherErrors");
        zzbo zzboVar8 = new zzbo();
        zzboVar8.zza(8);
        zzi = builder8.withProperty(zzboVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("remoteConfigValueForAcceleration");
        zzbo zzboVar9 = new zzbo();
        zzboVar9.zza(9);
        zzj = builder9.withProperty(zzboVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isAccelerated");
        zzbo zzboVar10 = new zzbo();
        zzboVar10.zza(10);
        zzk = builder10.withProperty(zzboVar10.zzb()).build();
    }

    private zzhz() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzoc zzocVar = (zzoc) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzocVar.zzc());
        objectEncoderContext2.add(zzc, zzocVar.zza());
        objectEncoderContext2.add(zzd, zzocVar.zzb());
        objectEncoderContext2.add(zze, (Object) null);
        objectEncoderContext2.add(zzf, (Object) null);
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, (Object) null);
        objectEncoderContext2.add(zzj, (Object) null);
        objectEncoderContext2.add(zzk, (Object) null);
    }
}
