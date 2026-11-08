package com.google.android.gms.internal.mlkit_language_id_bundled;

import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:language-id@@17.0.6 */
/* loaded from: classes24.dex */
public final class zbj extends zbi {
    static final zbi zba = new zbj(new Object[0], 0);
    final transient Object[] zbb;
    private final transient int zbc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zbj(Object[] objArr, int i) {
        this.zbb = objArr;
        this.zbc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zbc.zba(i, this.zbc, "index");
        return Objects.requireNonNull(this.zbb[i]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbi, com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    final int zba(Object[] objArr, int i) {
        System.arraycopy(this.zbb, 0, objArr, 0, this.zbc);
        return this.zbc;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    final int zbb() {
        return this.zbc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final int zbc() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_language_id_bundled.zbf
    public final Object[] zbe() {
        return this.zbb;
    }
}
