package com.google.android.gms.internal.mlkit_language_id_bundled;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:language-id@@17.0.6 */
/* loaded from: classes24.dex */
public abstract class zbf extends AbstractCollection implements Serializable {
    private static final Object[] zba = new Object[0];

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(zba);
    }

    int zba(Object[] objArr, int i) {
        throw null;
    }

    int zbb() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zbc() {
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zbd, reason: merged with bridge method [inline-methods] */
    public abstract zbk iterator();

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public Object[] zbe() {
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        if (objArr != null) {
            int size = size();
            int length = objArr.length;
            if (length < size) {
                Object[] zbe = zbe();
                if (zbe == null) {
                    if (length != 0) {
                        objArr = Arrays.copyOf(objArr, 0);
                    }
                    objArr = Arrays.copyOf(objArr, size);
                } else {
                    return Arrays.copyOfRange(zbe, zbc(), zbb(), objArr.getClass());
                }
            } else if (length > size) {
                objArr[size] = null;
            }
            zba(objArr, 0);
            return objArr;
        }
        throw null;
    }
}
