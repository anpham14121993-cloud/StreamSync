package com.google.android.gms.internal.mlkit_translate;

import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.nl.translate.TranslateLanguage;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public abstract class zzw implements Map, Serializable {

    @CheckForNull
    private transient zzy zza;

    @CheckForNull
    private transient zzy zzb;

    @CheckForNull
    private transient zzp zzc;

    public static zzw zzc() {
        return zzag.zza;
    }

    public static zzw zzd(Object obj, Object obj2) {
        zzl.zza("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return zzag.zzi(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, null);
    }

    public static zzw zze(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        zzl.zza("iw", TranslateLanguage.HEBREW);
        zzl.zza("in", "id");
        zzl.zza("nb", TranslateLanguage.NORWEGIAN);
        return zzag.zzi(3, new Object[]{"iw", TranslateLanguage.HEBREW, "in", "id", "nb", TranslateLanguage.NORWEGIAN}, null);
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzai.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzy zzyVar = this.zzb;
        if (zzyVar != null) {
            return zzyVar;
        }
        zzy zzg = zzg();
        this.zzb = zzg;
        return zzg;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
            sb.append('{');
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append('}');
            return sb.toString();
        }
        throw new IllegalArgumentException("size cannot be negative but was: " + size);
    }

    abstract zzp zza();

    @Override // java.util.Map
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzp values() {
        zzp zzpVar = this.zzc;
        if (zzpVar != null) {
            return zzpVar;
        }
        zzp zza = zza();
        this.zzc = zza;
        return zza;
    }

    abstract zzy zzf();

    abstract zzy zzg();

    @Override // java.util.Map
    /* renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public final zzy entrySet() {
        zzy zzyVar = this.zza;
        if (zzyVar != null) {
            return zzyVar;
        }
        zzy zzf = zzf();
        this.zza = zzf;
        return zzf;
    }
}
