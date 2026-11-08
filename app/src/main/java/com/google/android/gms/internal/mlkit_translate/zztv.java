package com.google.android.gms.internal.mlkit_translate;

import android.os.SystemClock;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public class zztv implements Closeable {
    private static final Map zza = new HashMap();
    private final String zzb;
    private int zzc;
    private double zzd;
    private long zze;
    private long zzf;
    private long zzg;

    private zztv(String str) {
        this.zzf = 2147483647L;
        this.zzg = -2147483648L;
        this.zzb = str;
    }

    private final void zza() {
        this.zzc = 0;
        this.zzd = 0.0d;
        this.zzf = 2147483647L;
        this.zzg = -2147483648L;
    }

    public static zztv zzd(String str) {
        zztt zzttVar;
        zzwj.zza();
        if (!zzwj.zzb()) {
            zzttVar = zztt.zza;
            return zzttVar;
        }
        if (zza.get(str) == null) {
            zza.put(str, new zztv(str));
        }
        return (zztv) zza.get(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Did you forget to call start()?");
    }

    public void zzb(long j) {
        zzc(j * 1000);
    }

    public void zzc(long j) {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
        long j2 = this.zze;
        if (j2 != 0 && elapsedRealtimeNanos - j2 >= 1000000) {
            zza();
        }
        this.zze = elapsedRealtimeNanos;
        this.zzc++;
        this.zzd += j;
        this.zzf = Math.min(this.zzf, j);
        this.zzg = Math.max(this.zzg, j);
        if (this.zzc % 50 == 0) {
            String.format(Locale.US, "[%s] cur=%dus, counts=%d, min=%dus, max=%dus, avg=%dus", this.zzb, Long.valueOf(j), Integer.valueOf(this.zzc), Long.valueOf(this.zzf), Long.valueOf(this.zzg), Integer.valueOf((int) (this.zzd / this.zzc)));
            zzwj.zza();
        }
        if (this.zzc % 500 == 0) {
            zza();
        }
    }
}
