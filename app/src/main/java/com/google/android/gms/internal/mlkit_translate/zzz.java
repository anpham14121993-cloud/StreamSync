package com.google.android.gms.internal.mlkit_translate;

import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzz extends zzak {
    private final Object zza;
    private boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(Object obj) {
        this.zza = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.zzb;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.zzb) {
            throw new NoSuchElementException();
        }
        this.zzb = true;
        return this.zza;
    }
}
