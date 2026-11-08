package com.google.android.gms.internal.mlkit_translate;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
abstract class zzcs implements Iterator {
    zzct zza;
    zzct zzb;
    int zzc;
    final /* synthetic */ zzcu zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcs(zzcu zzcuVar) {
        this.zzd = zzcuVar;
        zzcu zzcuVar2 = this.zzd;
        this.zza = zzcuVar2.zzd.zzd;
        this.zzb = null;
        this.zzc = zzcuVar2.zzc;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza != this.zzd.zzd;
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzct zzctVar = this.zzb;
        if (zzctVar == null) {
            throw new IllegalStateException();
        }
        this.zzd.zze(zzctVar, true);
        this.zzb = null;
        this.zzc = this.zzd.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzct zza() {
        zzcu zzcuVar = this.zzd;
        zzct zzctVar = this.zza;
        if (zzctVar == zzcuVar.zzd) {
            throw new NoSuchElementException();
        }
        if (zzcuVar.zzc != this.zzc) {
            throw new ConcurrentModificationException();
        }
        this.zza = zzctVar.zzd;
        this.zzb = zzctVar;
        return zzctVar;
    }
}
