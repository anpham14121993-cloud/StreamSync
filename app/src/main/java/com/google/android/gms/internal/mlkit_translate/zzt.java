package com.google.android.gms.internal.mlkit_translate;

import com.google.mlkit.nl.translate.TranslateLanguage;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public abstract class zzt extends zzp implements List, RandomAccess {
    private static final zzal zza = new zzr(zzab.zza, 0);
    public static final /* synthetic */ int zzd = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzt zzg(Object[] objArr, int i) {
        return i == 0 ? zzab.zza : new zzab(objArr, i);
    }

    public static zzt zzh(Object[] objArr) {
        if (objArr.length == 0) {
            return zzab.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzaa.zzb(objArr2, length);
        return zzg(objArr2, length);
    }

    public static zzt zzi(Object obj) {
        Object[] objArr = {obj};
        zzaa.zzb(objArr, 1);
        return zzg(objArr, 1);
    }

    public static zzt zzj(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzaa.zzb(objArr, 2);
        return zzg(objArr, 2);
    }

    public static zzt zzk(Object obj, Object obj2, Object obj3) {
        Object[] objArr = {obj, TranslateLanguage.ENGLISH, obj3};
        zzaa.zzb(objArr, 3);
        return zzg(objArr, 3);
    }

    public static zzt zzl(Object obj, Object obj2, Object obj3, Object obj4) {
        Object[] objArr = {"merged_dict_%1$s_%2$s_update.bin", "merged_dict_%1$s_%2$s_both.bin", "merged_dict_%1$s_%2$s_from_%3$s.bin", "merged_dict_%1$s_%2$s_from_%4$s.bin"};
        zzaa.zzb(objArr, 4);
        return zzg(objArr, 4);
    }

    public static zzt zzm(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Object[] objArr = {obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10};
        zzaa.zzb(objArr, 10);
        return zzg(objArr, 10);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        if (list instanceof RandomAccess) {
            for (int i = 0; i < size; i++) {
                if (!zzd.zza(get(i), list.get(i))) {
                    return false;
                }
            }
            return true;
        }
        Iterator it = iterator();
        Iterator it2 = list.iterator();
        while (it.hasNext()) {
            if (it2.hasNext() && zzd.zza(it.next(), it2.next())) {
            }
            return false;
        }
        return !it2.hasNext();
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    public int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzp
    /* renamed from: zzd */
    public final zzak iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public zzt subList(int i, int i2) {
        zzf.zze(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 != 0) {
            return new zzs(this, i, i3);
        }
        return zzab.zza;
    }

    @Override // java.util.List
    /* renamed from: zzn, reason: merged with bridge method [inline-methods] */
    public final zzal listIterator(int i) {
        zzf.zzb(i, size(), "index");
        return isEmpty() ? zza : new zzr(this, i);
    }
}
