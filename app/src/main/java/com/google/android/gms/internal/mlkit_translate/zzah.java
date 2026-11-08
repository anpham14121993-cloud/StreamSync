package com.google.android.gms.internal.mlkit_translate;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzah extends zzy {
    static final zzah zza;
    private static final Object[] zzd = new Object[0];
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] objArr = zzd;
        zza = new zzah(objArr, 0, objArr, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zzb = objArr;
        this.zze = i;
        this.zzc = objArr2;
        this.zzf = i2;
        this.zzg = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        if (obj != null) {
            Object[] objArr = this.zzc;
            if (objArr.length != 0) {
                int zza2 = zzm.zza(obj.hashCode());
                while (true) {
                    int i = zza2 & this.zzf;
                    Object obj2 = objArr[i];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    zza2 = i + 1;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy, com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzg().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzg);
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    final int zzb() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy, com.google.android.gms.internal.mlkit_translate.zzp
    /* renamed from: zzd */
    public final zzak iterator() {
        return zzg().listIterator(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    public final Object[] zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy
    final zzt zzh() {
        return zzt.zzg(this.zzb, this.zzg);
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzy
    final boolean zzk() {
        return true;
    }
}
