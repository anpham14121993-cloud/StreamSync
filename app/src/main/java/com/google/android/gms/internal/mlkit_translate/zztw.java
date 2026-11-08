package com.google.android.gms.internal.mlkit_translate;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zztw {
    private final String zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zztw(String str) {
        Preconditions.checkNotNull(str, "Null FID");
        int length = str.length();
        Preconditions.checkArgument(length == 22, "Invalid FID: must be exactly 22 characters: ".concat(String.valueOf(str)));
        char charAt = str.charAt(0);
        Preconditions.checkArgument(charAt >= 'c' && charAt <= 'f', "Invalid FID: must start with [c-f]: ".concat(String.valueOf(str)));
        for (int i = 0; i < length; i++) {
            char charAt2 = str.charAt(i);
            Preconditions.checkArgument(((charAt2 < '0' || charAt2 > '9') && (charAt2 < 'a' || charAt2 > 'z') && ((charAt2 < 'A' || charAt2 > 'Z') && charAt2 != '-')) ? charAt2 == '_' : true, "Invalid FID: must contain only URL-safe base-64 characters: ".concat(String.valueOf(str)));
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zztw) {
            return this.zza.equals(((zztw) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String zza() {
        return this.zza;
    }
}
