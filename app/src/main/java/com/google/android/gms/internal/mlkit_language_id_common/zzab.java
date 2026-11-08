package com.google.android.gms.internal.mlkit_language_id_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes24.dex */
final class zzab extends zzx {
    private final transient zzw zza;
    private final transient zzu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(zzw zzwVar, zzu zzuVar) {
        this.zza = zzwVar;
        this.zzb = zzuVar;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzq, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzx, com.google.android.gms.internal.mlkit_language_id_common.zzq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzq
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzx, com.google.android.gms.internal.mlkit_language_id_common.zzq
    /* renamed from: zzd */
    public final zzaf iterator() {
        return this.zzb.listIterator(0);
    }
}
