package com.google.android.gms.internal.mlkit_translate;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzcx extends zzck {
    private static final zzcb zze(zzda zzdaVar, int i) throws IOException {
        switch (i - 1) {
            case 0:
                zzdaVar.zzf();
                return new zzca();
            case 1:
            default:
                return null;
            case 2:
                zzdaVar.zzg();
                return new zzce();
        }
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzck
    public final /* bridge */ /* synthetic */ Object zza(zzda zzdaVar) throws IOException {
        int zzn = zzdaVar.zzn();
        zzcb zze = zze(zzdaVar, zzn);
        if (zze == null) {
            return zzd(zzdaVar, zzn);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (zzdaVar.zzl()) {
                String zzd = zze instanceof zzce ? zzdaVar.zzd() : null;
                int zzn2 = zzdaVar.zzn();
                zzcb zze2 = zze(zzdaVar, zzn2);
                zzcb zzd2 = zze2 == null ? zzd(zzdaVar, zzn2) : zze2;
                if (zze instanceof zzca) {
                    ((zzca) zze).zza(zzd2);
                } else {
                    ((zzce) zze).zzf(zzd, zzd2);
                }
                if (zze2 != null) {
                    arrayDeque.addLast(zze);
                    zze = zzd2;
                }
            } else {
                if (zze instanceof zzca) {
                    zzdaVar.zzh();
                } else {
                    zzdaVar.zzi();
                }
                if (arrayDeque.isEmpty()) {
                    return zze;
                }
                zze = (zzcb) arrayDeque.removeLast();
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzck
    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final void zzb(zzdc zzdcVar, zzcb zzcbVar) throws IOException {
        if (zzcbVar == null || (zzcbVar instanceof zzcd)) {
            zzdcVar.zzf();
            return;
        }
        if (zzcbVar instanceof zzch) {
            zzch zzchVar = (zzch) zzcbVar;
            if (zzchVar.zzj()) {
                zzdcVar.zzg(zzchVar.zzd());
                return;
            } else if (zzchVar.zzi()) {
                zzdcVar.zzi(zzchVar.zzh());
                return;
            } else {
                zzdcVar.zzh(zzchVar.zze());
                return;
            }
        }
        if (zzcbVar instanceof zzca) {
            zzdcVar.zza();
            Iterator it = ((zzca) zzcbVar).iterator();
            while (it.hasNext()) {
                zzb(zzdcVar, (zzcb) it.next());
            }
            zzdcVar.zzc();
            return;
        }
        if (zzcbVar instanceof zzce) {
            zzdcVar.zzb();
            for (Map.Entry entry : zzcbVar.zzb().zze()) {
                zzdcVar.zze((String) entry.getKey());
                zzb(zzdcVar, (zzcb) entry.getValue());
            }
            zzdcVar.zzd();
            return;
        }
        throw new IllegalArgumentException("Couldn't write ".concat(String.valueOf(String.valueOf(zzcbVar.getClass()))));
    }

    private static final zzcb zzd(zzda zzdaVar, int i) throws IOException {
        switch (i - 1) {
            case 5:
                return new zzch(zzdaVar.zze());
            case 6:
                return new zzch(new zzcm(zzdaVar.zze()));
            case 7:
                return new zzch(Boolean.valueOf(zzdaVar.zzm()));
            case 8:
                zzdaVar.zzj();
                return zzcd.zza;
            default:
                throw new IllegalStateException("Unexpected token: ".concat(zzdb.zza(i)));
        }
    }
}
