package com.google.android.gms.internal.mlkit_translate;

import java.nio.file.attribute.FileAttribute;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzba implements FileAttribute {
    final /* synthetic */ zzt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzt zztVar) {
        this.zza = zztVar;
    }

    @Override // java.nio.file.attribute.FileAttribute
    public final String name() {
        return "acl:acl";
    }

    @Override // java.nio.file.attribute.FileAttribute
    public final /* synthetic */ Object value() {
        return this.zza;
    }
}
