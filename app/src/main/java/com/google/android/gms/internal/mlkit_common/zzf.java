package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzf {
    private final zzac zza = new zzac();
    private Boolean zzb;

    private zzf() {
    }

    public final zzf zza(zzk zzkVar) {
        zzt.zzc(this.zzb, "Must call internal() or external() before appending rules.");
        this.zza.zzb(zzkVar);
        return this;
    }

    public final zzf zzb() {
        zzt.zze(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = false;
        return this;
    }

    public final zzf zzc() {
        zzt.zze(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = true;
        return this;
    }

    public final zzh zzd() {
        zzt.zzc(this.zzb, "Must call internal() or external() when building a SourcePolicy.");
        return new zzh(this.zzb.booleanValue(), false, this.zza.zzc(), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzf(zze zzeVar) {
    }
}
