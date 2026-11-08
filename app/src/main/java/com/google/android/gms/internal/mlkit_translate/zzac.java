package com.google.android.gms.internal.mlkit_translate;

import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzac extends zzt {
    final /* synthetic */ zzad zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(zzad zzadVar) {
        this.zza = zzadVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzf.zza(i, i2, "index");
        objArr = this.zza.zzb;
        int i3 = i + i;
        Object requireNonNull = Objects.requireNonNull(objArr[i3]);
        objArr2 = this.zza.zzb;
        return new AbstractMap.SimpleImmutableEntry(requireNonNull, Objects.requireNonNull(objArr2[i3 + 1]));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzc;
        return i;
    }
}
