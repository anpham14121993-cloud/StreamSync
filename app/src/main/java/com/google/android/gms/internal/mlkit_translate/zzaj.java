package com.google.android.gms.internal.mlkit_translate;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzaj extends zzy {
    final transient Object zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(Object obj) {
        if (obj == null) {
            throw null;
        }
        this.zza = obj;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.equals(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy, com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzz(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.zza.toString() + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    public final int zza(Object[] objArr, int i) {
        objArr[0] = this.zza;
        return 1;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy, com.google.android.gms.internal.mlkit_translate.zzp
    /* renamed from: zzd */
    public final zzak iterator() {
        return new zzz(this.zza);
    }
}
