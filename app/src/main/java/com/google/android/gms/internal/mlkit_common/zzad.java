package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
final class zzad extends zzv {
    private final zzaf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(zzaf zzafVar, int i) {
        super(zzafVar.size(), i);
        this.zza = zzafVar;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzv
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
