package com.google.android.gms.internal.mlkit_translate;

import java.util.Objects;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzbz {
    public static final zzbz zza = new zzbz("", "", false);
    private final String zzb;
    private final String zzc;
    private final boolean zzd;

    static {
        new zzbz("\n", "  ", true);
    }

    private zzbz(String str, String str2, boolean z) {
        Objects.requireNonNull(str, "newline == null");
        Objects.requireNonNull(str2, "indent == null");
        if (!str.matches("[\r\n]*")) {
            throw new IllegalArgumentException("Only combinations of \\n and \\r are allowed in newline.");
        }
        if (!str2.matches("[ \t]*")) {
            throw new IllegalArgumentException("Only combinations of spaces and tabs are allowed in indent.");
        }
        this.zzb = str;
        this.zzc = str2;
        this.zzd = z;
    }

    public final String zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzd;
    }
}
