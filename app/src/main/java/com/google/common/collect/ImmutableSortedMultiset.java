package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public abstract class ImmutableSortedMultiset<E> extends ImmutableMultiset<E> implements SortedMultiset<E> {
    private static final long serialVersionUID = 912559;

    @CheckForNull
    @LazyInit
    transient ImmutableSortedMultiset<E> descendingMultiset;

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public abstract ImmutableSortedSet<E> elementSet();

    public abstract ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType);

    public abstract ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType);

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ SortedMultiset headMultiset(Object upperBound, BoundType boundType) {
        return headMultiset((ImmutableSortedMultiset<E>) upperBound, boundType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(Object lowerBound, BoundType lowerBoundType, Object upperBound, BoundType upperBoundType) {
        return subMultiset((BoundType) lowerBound, lowerBoundType, (BoundType) upperBound, upperBoundType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ SortedMultiset tailMultiset(Object lowerBound, BoundType boundType) {
        return tailMultiset((ImmutableSortedMultiset<E>) lowerBound, boundType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$toImmutableSortedMultiset$0(Object e) {
        return 1;
    }

    static <E> Collector<E, ?, ImmutableSortedMultiset<E>> toImmutableSortedMultiset(Comparator<? super E> comparator) {
        return toImmutableSortedMultiset(comparator, Function.identity(), new ToIntFunction() { // from class: com.google.common.collect.ImmutableSortedMultiset$$ExternalSyntheticLambda4
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ImmutableSortedMultiset.lambda$toImmutableSortedMultiset$0(obj);
            }
        });
    }

    static <T, E> Collector<T, ?, ImmutableSortedMultiset<E>> toImmutableSortedMultiset(final Comparator<? super E> comparator, final Function<? super T, ? extends E> elementFunction, final ToIntFunction<? super T> countFunction) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(elementFunction);
        Preconditions.checkNotNull(countFunction);
        return Collector.of(new Supplier() { // from class: com.google.common.collect.ImmutableSortedMultiset$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                Multiset create;
                create = TreeMultiset.create(comparator);
                return create;
            }
        }, new BiConsumer() { // from class: com.google.common.collect.ImmutableSortedMultiset$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ImmutableSortedMultiset.mapAndAdd(obj2, (Multiset) obj, elementFunction, countFunction);
            }
        }, new BinaryOperator() { // from class: com.google.common.collect.ImmutableSortedMultiset$$ExternalSyntheticLambda2
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ImmutableSortedMultiset.lambda$toImmutableSortedMultiset$3((Multiset) obj, (Multiset) obj2);
            }
        }, new Function() { // from class: com.google.common.collect.ImmutableSortedMultiset$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ImmutableSortedMultiset copyOfSortedEntries;
                copyOfSortedEntries = ImmutableSortedMultiset.copyOfSortedEntries(comparator, ((Multiset) obj).entrySet());
                return copyOfSortedEntries;
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Multiset lambda$toImmutableSortedMultiset$3(Multiset multiset1, Multiset multiset2) {
        multiset1.addAll(multiset2);
        return multiset1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E> void mapAndAdd(T t, Multiset<E> multiset, Function<? super T, ? extends E> elementFunction, ToIntFunction<? super T> countFunction) {
        multiset.add(Preconditions.checkNotNull(elementFunction.apply(t)), countFunction.applyAsInt(t));
    }

    public static <E> ImmutableSortedMultiset<E> of() {
        return (ImmutableSortedMultiset<E>) RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset of(Comparable element) {
        RegularImmutableSortedSet<E> elementSet = (RegularImmutableSortedSet) ImmutableSortedSet.of(element);
        long[] cumulativeCounts = {0, 1};
        return new RegularImmutableSortedMultiset(elementSet, cumulativeCounts, 0, 1);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset of(Comparable e1, Comparable e2) {
        return copyOf(Ordering.natural(), Arrays.asList(e1, e2));
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3) {
        return copyOf(Ordering.natural(), Arrays.asList(e1, e2, e3));
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3, Comparable e4) {
        return copyOf(Ordering.natural(), Arrays.asList(e1, e2, e3, e4));
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5) {
        return copyOf(Ordering.natural(), Arrays.asList(e1, e2, e3, e4, e5));
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;TE;[TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset of(Comparable e1, Comparable e2, Comparable e3, Comparable e4, Comparable e5, Comparable e6, Comparable... remaining) {
        int size = remaining.length + 6;
        List<E> all = Lists.newArrayListWithCapacity(size);
        Collections.addAll(all, e1, e2, e3, e4, e5, e6);
        Collections.addAll(all, remaining);
        return copyOf(Ordering.natural(), all);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>([TE;)Lcom/google/common/collect/ImmutableSortedMultiset<TE;>; */
    public static ImmutableSortedMultiset copyOf(Comparable[] elements) {
        return copyOf(Ordering.natural(), Arrays.asList(elements));
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterable<? extends E> elements) {
        Ordering<E> naturalOrder = Ordering.natural();
        return copyOf(naturalOrder, elements);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterator<? extends E> elements) {
        Ordering<E> naturalOrder = Ordering.natural();
        return copyOf(naturalOrder, elements);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> elements) {
        Preconditions.checkNotNull(comparator);
        return new Builder(comparator).addAll((Iterator) elements).build();
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> elements) {
        if (elements instanceof ImmutableSortedMultiset) {
            ImmutableSortedMultiset<E> multiset = (ImmutableSortedMultiset) elements;
            if (comparator.equals(multiset.comparator())) {
                if (multiset.isPartialView()) {
                    return copyOfSortedEntries(comparator, multiset.entrySet().asList());
                }
                return multiset;
            }
        }
        return new Builder(comparator).addAll((Iterable) elements).build();
    }

    public static <E> ImmutableSortedMultiset<E> copyOfSorted(SortedMultiset<E> sortedMultiset) {
        return copyOfSortedEntries(sortedMultiset.comparator(), Lists.newArrayList(sortedMultiset.entrySet()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> ImmutableSortedMultiset<E> copyOfSortedEntries(Comparator<? super E> comparator, Collection<Multiset.Entry<E>> entries) {
        if (entries.isEmpty()) {
            return emptyMultiset(comparator);
        }
        ImmutableList.Builder<E> elementsBuilder = new ImmutableList.Builder<>(entries.size());
        long[] cumulativeCounts = new long[entries.size() + 1];
        int i = 0;
        for (Multiset.Entry<E> entry : entries) {
            elementsBuilder.add((ImmutableList.Builder<E>) entry.getElement());
            cumulativeCounts[i + 1] = cumulativeCounts[i] + entry.getCount();
            i++;
        }
        return new RegularImmutableSortedMultiset(new RegularImmutableSortedSet(elementsBuilder.build(), comparator), cumulativeCounts, 0, entries.size());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> ImmutableSortedMultiset<E> emptyMultiset(Comparator<? super E> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return (ImmutableSortedMultiset<E>) RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
        }
        return new RegularImmutableSortedMultiset(comparator);
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public final Comparator<? super E> comparator() {
        return elementSet().comparator();
    }

    @Override // com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> descendingMultiset() {
        ImmutableSortedMultiset<E> descendingImmutableSortedMultiset;
        ImmutableSortedMultiset<E> result = this.descendingMultiset;
        if (result == null) {
            if (isEmpty()) {
                descendingImmutableSortedMultiset = emptyMultiset(Ordering.from(comparator()).reverse());
            } else {
                descendingImmutableSortedMultiset = new DescendingImmutableSortedMultiset<>(this);
            }
            this.descendingMultiset = descendingImmutableSortedMultiset;
            return descendingImmutableSortedMultiset;
        }
        return result;
    }

    @Override // com.google.common.collect.SortedMultiset
    @CheckForNull
    @Deprecated
    public final Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.SortedMultiset
    @CheckForNull
    @Deprecated
    public final Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> subMultiset(E lowerBound, BoundType lowerBoundType, E upperBound, BoundType upperBoundType) {
        Preconditions.checkArgument(comparator().compare(lowerBound, upperBound) <= 0, "Expected lowerBound <= upperBound but %s > %s", lowerBound, upperBound);
        return tailMultiset((ImmutableSortedMultiset<E>) lowerBound, lowerBoundType).headMultiset((ImmutableSortedMultiset<E>) upperBound, upperBoundType);
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<>(comparator);
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    /* loaded from: classes25.dex */
    public static class Builder<E> extends ImmutableMultiset.Builder<E> {
        private final Comparator<? super E> comparator;
        private int[] counts;
        E[] elements;
        private boolean forceCopyElements;
        private int length;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object element) {
            return add((Builder<E>) element);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableMultiset.Builder add(Object element) {
            return add((Builder<E>) element);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultiset.Builder
        public /* bridge */ /* synthetic */ ImmutableMultiset.Builder addCopies(Object element, int occurrences) {
            return addCopies((Builder<E>) element, occurrences);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMultiset.Builder
        public /* bridge */ /* synthetic */ ImmutableMultiset.Builder setCount(Object element, int count) {
            return setCount((Builder<E>) element, count);
        }

        public Builder(Comparator<? super E> comparator) {
            super(true);
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
            this.elements = (E[]) new Object[4];
            this.counts = new int[4];
        }

        private void maintenance() {
            if (this.length == this.elements.length) {
                dedupAndCoalesce(true);
            } else if (this.forceCopyElements) {
                this.elements = (E[]) Arrays.copyOf(this.elements, this.elements.length);
            }
            this.forceCopyElements = false;
        }

        private void dedupAndCoalesce(boolean z) {
            if (this.length == 0) {
                return;
            }
            Object[] objArr = (E[]) Arrays.copyOf(this.elements, this.length);
            Arrays.sort(objArr, this.comparator);
            int i = 1;
            for (int i2 = 1; i2 < objArr.length; i2++) {
                if (this.comparator.compare((Object) objArr[i - 1], (Object) objArr[i2]) < 0) {
                    objArr[i] = objArr[i2];
                    i++;
                }
            }
            Arrays.fill(objArr, i, this.length, (Object) null);
            if (z && i * 4 > this.length * 3) {
                objArr = (E[]) Arrays.copyOf(objArr, IntMath.saturatedAdd(this.length, (this.length / 2) + 1));
            }
            int[] iArr = new int[objArr.length];
            for (int i3 = 0; i3 < this.length; i3++) {
                int binarySearch = Arrays.binarySearch(objArr, 0, i, this.elements[i3], this.comparator);
                if (this.counts[i3] >= 0) {
                    iArr[binarySearch] = iArr[binarySearch] + this.counts[i3];
                } else {
                    iArr[binarySearch] = ~this.counts[i3];
                }
            }
            this.elements = (E[]) objArr;
            this.counts = iArr;
            this.length = i;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E element) {
            return addCopies((Builder<E>) element, 1);
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E... elements) {
            for (E element : elements) {
                add((Builder<E>) element);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder
        public Builder<E> addCopies(E element, int occurrences) {
            Preconditions.checkNotNull(element);
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return this;
            }
            maintenance();
            this.elements[this.length] = element;
            this.counts[this.length] = occurrences;
            this.length++;
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder
        public Builder<E> setCount(E element, int count) {
            Preconditions.checkNotNull(element);
            CollectPreconditions.checkNonnegative(count, "count");
            maintenance();
            this.elements[this.length] = element;
            this.counts[this.length] = ~count;
            this.length++;
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> elements) {
            if (elements instanceof Multiset) {
                for (Multiset.Entry<E> entry : ((Multiset) elements).entrySet()) {
                    addCopies((Builder<E>) entry.getElement(), entry.getCount());
                }
            } else {
                for (E e : elements) {
                    add((Builder<E>) e);
                }
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterator<? extends E> elements) {
            while (elements.hasNext()) {
                add((Builder<E>) elements.next());
            }
            return this;
        }

        private void dedupAndCoalesceAndDeleteEmpty() {
            dedupAndCoalesce(false);
            int size = 0;
            for (int i = 0; i < this.length; i++) {
                if (this.counts[i] > 0) {
                    this.elements[size] = this.elements[i];
                    this.counts[size] = this.counts[i];
                    size++;
                }
            }
            Arrays.fill(this.elements, size, this.length, (Object) null);
            Arrays.fill(this.counts, size, this.length, 0);
            this.length = size;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSortedMultiset<E> build() {
            dedupAndCoalesceAndDeleteEmpty();
            if (this.length == 0) {
                return ImmutableSortedMultiset.emptyMultiset(this.comparator);
            }
            RegularImmutableSortedSet<E> elementSet = (RegularImmutableSortedSet) ImmutableSortedSet.construct(this.comparator, this.length, this.elements);
            long[] cumulativeCounts = new long[this.length + 1];
            for (int i = 0; i < this.length; i++) {
                cumulativeCounts[i + 1] = cumulativeCounts[i] + this.counts[i];
            }
            this.forceCopyElements = true;
            return new RegularImmutableSortedMultiset(elementSet, cumulativeCounts, 0, this.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes25.dex */
    public static final class SerializedForm<E> implements Serializable {
        final Comparator<? super E> comparator;
        final int[] counts;
        final E[] elements;

        SerializedForm(SortedMultiset<E> sortedMultiset) {
            this.comparator = sortedMultiset.comparator();
            int size = sortedMultiset.entrySet().size();
            this.elements = (E[]) new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (Multiset.Entry<E> entry : sortedMultiset.entrySet()) {
                this.elements[i] = entry.getElement();
                this.counts[i] = entry.getCount();
                i++;
            }
        }

        Object readResolve() {
            int n = this.elements.length;
            Builder<E> builder = new Builder<>(this.comparator);
            for (int i = 0; i < n; i++) {
                builder.addCopies((Builder<E>) this.elements[i], this.counts[i]);
            }
            return builder.build();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Deprecated
    static <E> Collector<E, ?, ImmutableMultiset<E>> toImmutableMultiset() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(Function<? super T, ? extends E> elementFunction, ToIntFunction<? super T> countFunction) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> of(E element) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> of(E e1, E e2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> of(E e1, E e2, E e3) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> of(E e1, E e2, E e3, E e4) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> of(E e1, E e2, E e3, E e4, E e5) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <E> ImmutableSortedMultiset<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E... remaining) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <Z> ImmutableSortedMultiset<Z> copyOf(Z[] elements) {
        throw new UnsupportedOperationException();
    }
}
