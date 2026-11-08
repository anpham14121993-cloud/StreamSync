package com.google.android.gms.internal.mlkit_translate;

import android.content.Context;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzvr {
    private static final Pattern zza = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    private final Context zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final zzwb zzf;

    public zzvr(Context context, String str, String str2, String str3, long j, long j2, zzwb zzwbVar) {
        this.zzb = context;
        this.zzc = str;
        this.zzd = str2;
        Matcher matcher = zza.matcher(str);
        this.zze = matcher.matches() ? matcher.group(1) : null;
        this.zzf = zzwbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(13:(3:88|89|90)|91|92|93|94|(1:96)|97|98|(1:100)|101|102|89|90) */
    /* JADX WARN: Can't wrap try/catch for region: R(21:76|77|(2:78|(1:80)(1:81))|82|83|84|85|86|(3:88|89|90)|91|92|93|94|(1:96)|97|98|(1:100)|101|102|89|90) */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0371, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.mlkit_translate.zzvt zza(java.net.HttpURLConnection r17, java.lang.String r18, java.lang.String r19, java.util.Map r20, java.lang.String r21, java.util.Map r22, java.util.Date r23, java.lang.String r24, java.lang.String r25, com.google.android.gms.internal.mlkit_translate.zzud r26) throws com.google.android.gms.internal.mlkit_translate.zzwa {
        /*
            Method dump skipped, instructions count: 1195
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzvr.zza(java.net.HttpURLConnection, java.lang.String, java.lang.String, java.util.Map, java.lang.String, java.util.Map, java.util.Date, java.lang.String, java.lang.String, com.google.android.gms.internal.mlkit_translate.zzud):com.google.android.gms.internal.mlkit_translate.zzvt");
    }

    public final HttpURLConnection zzb() throws zzwa {
        try {
            return (HttpURLConnection) new URL(String.format("https://firebaseremoteconfig.googleapis.com/v1/projects/%s/namespaces/%s:fetch", this.zze, "firebase")).openConnection();
        } catch (IOException e) {
            throw new zzwa(e.getMessage());
        }
    }
}
