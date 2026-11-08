package com.google.android.gms.internal.mlkit_translate;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzcu extends AbstractMap implements Serializable {
    private static final Comparator zze = new zzcn();
    zzct zza;
    int zzb;
    int zzc;
    final zzct zzd;
    private final Comparator zzf;
    private final boolean zzg;
    private zzcp zzh;
    private zzcr zzi;

    public zzcu() {
        this(zze, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        if (r10 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x005c, code lost:
    
        if (r10 == false) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0083 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void zzf(com.google.android.gms.internal.mlkit_translate.zzct r9, boolean r10) {
        /*
            r8 = this;
        L1:
            if (r9 == 0) goto L87
            com.google.android.gms.internal.mlkit_translate.zzct r0 = r9.zzb
            com.google.android.gms.internal.mlkit_translate.zzct r1 = r9.zzc
            r2 = 0
            if (r0 == 0) goto Ld
            int r3 = r0.zzi
            goto Le
        Ld:
            r3 = r2
        Le:
            if (r1 == 0) goto L13
            int r4 = r1.zzi
            goto L14
        L13:
            r4 = r2
        L14:
            int r5 = r3 - r4
            r6 = -2
            r7 = 1
            if (r5 != r6) goto L44
            com.google.android.gms.internal.mlkit_translate.zzct r0 = r1.zzb
            com.google.android.gms.internal.mlkit_translate.zzct r3 = r1.zzc
            if (r3 == 0) goto L23
            int r3 = r3.zzi
            goto L24
        L23:
            r3 = r2
        L24:
            if (r0 == 0) goto L29
            int r0 = r0.zzi
            goto L2a
        L29:
            r0 = r2
        L2a:
            int r0 = r0 - r3
            r3 = -1
            if (r0 == r3) goto L3c
            if (r0 != 0) goto L34
            if (r10 != 0) goto L33
            goto L3d
        L33:
            goto L35
        L34:
            r7 = r10
        L35:
            r8.zzi(r1)
            r8.zzh(r9)
            goto L41
        L3c:
            r2 = r10
        L3d:
            r8.zzh(r9)
            r7 = r2
        L41:
            if (r7 != 0) goto L87
            goto L83
        L44:
            r1 = 2
            if (r5 != r1) goto L70
            com.google.android.gms.internal.mlkit_translate.zzct r1 = r0.zzb
            com.google.android.gms.internal.mlkit_translate.zzct r3 = r0.zzc
            if (r3 == 0) goto L50
            int r3 = r3.zzi
            goto L51
        L50:
            r3 = r2
        L51:
            if (r1 == 0) goto L56
            int r1 = r1.zzi
            goto L57
        L56:
            r1 = r2
        L57:
            int r1 = r1 - r3
            if (r1 == r7) goto L68
            if (r1 != 0) goto L60
            if (r10 != 0) goto L5f
            goto L69
        L5f:
            goto L61
        L60:
            r7 = r10
        L61:
            r8.zzh(r0)
            r8.zzi(r9)
            goto L6d
        L68:
            r2 = r10
        L69:
            r8.zzi(r9)
            r7 = r2
        L6d:
            if (r7 == 0) goto L83
            goto L87
        L70:
            if (r5 != 0) goto L79
            int r3 = r3 + 1
            r9.zzi = r3
            if (r10 == 0) goto L83
            goto L87
        L79:
            int r0 = java.lang.Math.max(r3, r4)
            int r0 = r0 + r7
            r9.zzi = r0
            if (r10 != 0) goto L83
            goto L87
        L83:
            com.google.android.gms.internal.mlkit_translate.zzct r9 = r9.zza
            goto L1
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzcu.zzf(com.google.android.gms.internal.mlkit_translate.zzct, boolean):void");
    }

    private final void zzg(zzct zzctVar, zzct zzctVar2) {
        zzct zzctVar3 = zzctVar.zza;
        zzctVar.zza = null;
        if (zzctVar2 != null) {
            zzctVar2.zza = zzctVar3;
        }
        if (zzctVar3 == null) {
            this.zza = zzctVar2;
        } else if (zzctVar3.zzb == zzctVar) {
            zzctVar3.zzb = zzctVar2;
        } else {
            zzctVar3.zzc = zzctVar2;
        }
    }

    private final void zzh(zzct zzctVar) {
        zzct zzctVar2 = zzctVar.zzb;
        zzct zzctVar3 = zzctVar.zzc;
        zzct zzctVar4 = zzctVar3.zzb;
        zzct zzctVar5 = zzctVar3.zzc;
        zzctVar.zzc = zzctVar4;
        if (zzctVar4 != null) {
            zzctVar4.zza = zzctVar;
        }
        zzg(zzctVar, zzctVar3);
        zzctVar3.zzb = zzctVar;
        zzctVar.zza = zzctVar3;
        int max = Math.max(zzctVar2 != null ? zzctVar2.zzi : 0, zzctVar4 != null ? zzctVar4.zzi : 0) + 1;
        zzctVar.zzi = max;
        zzctVar3.zzi = Math.max(max, zzctVar5 != null ? zzctVar5.zzi : 0) + 1;
    }

    private final void zzi(zzct zzctVar) {
        zzct zzctVar2 = zzctVar.zzb;
        zzct zzctVar3 = zzctVar.zzc;
        zzct zzctVar4 = zzctVar2.zzb;
        zzct zzctVar5 = zzctVar2.zzc;
        zzctVar.zzb = zzctVar5;
        if (zzctVar5 != null) {
            zzctVar5.zza = zzctVar;
        }
        zzg(zzctVar, zzctVar2);
        zzctVar2.zzc = zzctVar;
        zzctVar.zza = zzctVar2;
        int max = Math.max(zzctVar3 != null ? zzctVar3.zzi : 0, zzctVar5 != null ? zzctVar5.zzi : 0) + 1;
        zzctVar.zzi = max;
        zzctVar2.zzi = Math.max(max, zzctVar4 != null ? zzctVar4.zzi : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.zza = null;
        this.zzb = 0;
        this.zzc++;
        zzct zzctVar = this.zzd;
        zzctVar.zze = zzctVar;
        zzctVar.zzd = zzctVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return zzc(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        zzcp zzcpVar = this.zzh;
        if (zzcpVar != null) {
            return zzcpVar;
        }
        zzcp zzcpVar2 = new zzcp(this);
        this.zzh = zzcpVar2;
        return zzcpVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        zzct zzc = zzc(obj);
        if (zzc != null) {
            return zzc.zzh;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        zzcr zzcrVar = this.zzi;
        if (zzcrVar != null) {
            return zzcrVar;
        }
        zzcr zzcrVar2 = new zzcr(this);
        this.zzi = zzcrVar2;
        return zzcrVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        if (obj2 != null || this.zzg) {
            zzct zza = zza(obj, true);
            Object obj3 = zza.zzh;
            zza.zzh = obj2;
            return obj3;
        }
        throw new NullPointerException("value == null");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzct zzd = zzd(obj);
        if (zzd != null) {
            return zzd.zzh;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzb;
    }

    final zzct zza(Object obj, boolean z) {
        int i;
        zzct zzctVar;
        Comparator comparator = this.zzf;
        zzct zzctVar2 = this.zza;
        if (zzctVar2 != null) {
            Comparable comparable = comparator == zze ? (Comparable) obj : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(zzctVar2.zzf);
                } else {
                    i = comparator.compare(obj, zzctVar2.zzf);
                }
                if (i == 0) {
                    return zzctVar2;
                }
                zzct zzctVar3 = i < 0 ? zzctVar2.zzb : zzctVar2.zzc;
                if (zzctVar3 == null) {
                    break;
                }
                zzctVar2 = zzctVar3;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        zzct zzctVar4 = this.zzd;
        if (zzctVar2 == null) {
            if (comparator != zze || (obj instanceof Comparable)) {
                zzctVar = new zzct(this.zzg, null, obj, zzctVar4, zzctVar4.zze);
                this.zza = zzctVar;
            } else {
                throw new ClassCastException(String.valueOf(obj.getClass().getName()).concat(" is not Comparable"));
            }
        } else {
            zzctVar = new zzct(this.zzg, zzctVar2, obj, zzctVar4, zzctVar4.zze);
            if (i < 0) {
                zzctVar2.zzb = zzctVar;
            } else {
                zzctVar2.zzc = zzctVar;
            }
            zzf(zzctVar2, true);
        }
        this.zzb++;
        this.zzc++;
        return zzctVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzct zzb(Map.Entry entry) {
        zzct zzc = zzc(entry.getKey());
        if (zzc == null || !Objects.equals(zzc.zzh, entry.getValue())) {
            return null;
        }
        return zzc;
    }

    final zzct zzc(Object obj) {
        if (obj != null) {
            try {
                return zza(obj, false);
            } catch (ClassCastException e) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzct zzd(Object obj) {
        zzct zzc = zzc(obj);
        if (zzc != null) {
            zze(zzc, true);
        }
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
    
        zze(r0, false);
        r8 = r7.zzb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
    
        if (r8 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        r1 = r8.zzi;
        r0.zzb = r8;
        r8.zza = r0;
        r7.zzb = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
    
        r8 = r7.zzc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        if (r8 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        r2 = r8.zzi;
        r0.zzc = r8;
        r8.zza = r0;
        r7.zzc = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004f, code lost:
    
        r0.zzi = java.lang.Math.max(r1, r2) + 1;
        zzg(r7, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0026, code lost:
    
        r5 = r0;
        r0 = r0.zzb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002b, code lost:
    
        if (r0 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x002e, code lost:
    
        r0 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
    
        if (r8.zzi > r0.zzi) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        r0 = r8;
        r8 = r8.zzc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
    
        if (r8 == null) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zze(com.google.android.gms.internal.mlkit_translate.zzct r7, boolean r8) {
        /*
            r6 = this;
            if (r8 == 0) goto Lc
            com.google.android.gms.internal.mlkit_translate.zzct r8 = r7.zze
            com.google.android.gms.internal.mlkit_translate.zzct r0 = r7.zzd
            r8.zzd = r0
            com.google.android.gms.internal.mlkit_translate.zzct r0 = r7.zzd
            r0.zze = r8
        Lc:
            com.google.android.gms.internal.mlkit_translate.zzct r8 = r7.zzb
            com.google.android.gms.internal.mlkit_translate.zzct r0 = r7.zzc
            com.google.android.gms.internal.mlkit_translate.zzct r1 = r7.zza
            r2 = 0
            r3 = 0
            if (r8 == 0) goto L5b
            if (r0 == 0) goto L5b
            int r1 = r8.zzi
            int r4 = r0.zzi
            if (r1 <= r4) goto L26
        L1e:
            com.google.android.gms.internal.mlkit_translate.zzct r0 = r8.zzc
            r5 = r0
            r0 = r8
            r8 = r5
            if (r8 == 0) goto L2f
            goto L1e
        L26:
            com.google.android.gms.internal.mlkit_translate.zzct r8 = r0.zzb
            r5 = r0
            r0 = r8
            r8 = r5
            if (r0 == 0) goto L2e
            goto L26
        L2e:
            r0 = r8
        L2f:
            r6.zze(r0, r2)
            com.google.android.gms.internal.mlkit_translate.zzct r8 = r7.zzb
            if (r8 == 0) goto L40
            int r1 = r8.zzi
            r0.zzb = r8
            r8.zza = r0
            r7.zzb = r3
            goto L41
        L40:
            r1 = r2
        L41:
            com.google.android.gms.internal.mlkit_translate.zzct r8 = r7.zzc
            if (r8 == 0) goto L4e
            int r2 = r8.zzi
            r0.zzc = r8
            r8.zza = r0
            r7.zzc = r3
            goto L4f
        L4e:
        L4f:
            int r8 = java.lang.Math.max(r1, r2)
            int r8 = r8 + 1
            r0.zzi = r8
            r6.zzg(r7, r0)
            return
        L5b:
            if (r8 == 0) goto L63
            r6.zzg(r7, r8)
            r7.zzb = r3
            goto L6f
        L63:
            if (r0 == 0) goto L6b
            r6.zzg(r7, r0)
            r7.zzc = r3
            goto L6f
        L6b:
            r6.zzg(r7, r3)
        L6f:
            r6.zzf(r1, r2)
            int r7 = r6.zzb
            int r7 = r7 + (-1)
            r6.zzb = r7
            int r7 = r6.zzc
            int r7 = r7 + 1
            r6.zzc = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzcu.zze(com.google.android.gms.internal.mlkit_translate.zzct, boolean):void");
    }

    public zzcu(Comparator comparator, boolean z) {
        this.zzb = 0;
        this.zzc = 0;
        this.zzf = comparator;
        this.zzg = z;
        this.zzd = new zzct(z);
    }

    public zzcu(boolean z) {
        this(zze, false);
    }
}
