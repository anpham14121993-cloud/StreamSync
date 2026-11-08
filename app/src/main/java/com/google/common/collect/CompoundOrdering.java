package com.google.common.collect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super T>[] comparators;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompoundOrdering(Comparator<? super T> primary, Comparator<? super T> secondary) {
        this.comparators = new Comparator[]{primary, secondary};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompoundOrdering(Iterable<? extends Comparator<? super T>> comparators) {
        this.comparators = (Comparator[]) Iterables.toArray(comparators, new Comparator[0]);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(@ParametricNullness T left, @ParametricNullness T right) {
        for (int i = 0; i < this.comparators.length; i++) {
            int result = this.comparators[i].compare(left, right);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(@CheckForNull Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof CompoundOrdering) {
            CompoundOrdering<?> that = (CompoundOrdering) object;
            return Arrays.equals(this.comparators, that.comparators);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.comparators);
    }

    public String toString() {
        return "Ordering.compound(" + Arrays.toString(this.comparators) + ")";
    }
}
