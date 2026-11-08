package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public class ObjectCountHashMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    transient long[] entries;
    transient Object[] keys;
    private transient float loadFactor;
    transient int modCount;
    transient int size;
    private transient int[] table;
    private transient int threshold;
    transient int[] values;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int expectedSize) {
        return new ObjectCountHashMap<>(expectedSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(ObjectCountHashMap<? extends K> map) {
        init(map.size(), 1.0f);
        int i = map.firstIndex();
        while (i != -1) {
            put(map.getKey(i), map.getValue(i));
            i = map.nextIndex(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int capacity) {
        this(capacity, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int expectedSize, float loadFactor) {
        init(expectedSize, loadFactor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int expectedSize, float loadFactor) {
        Preconditions.checkArgument(expectedSize >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(loadFactor > 0.0f, "Illegal load factor");
        int buckets = Hashing.closedTableSize(expectedSize, loadFactor);
        this.table = newTable(buckets);
        this.loadFactor = loadFactor;
        this.keys = new Object[expectedSize];
        this.values = new int[expectedSize];
        this.entries = newEntries(expectedSize);
        this.threshold = Math.max(1, (int) (buckets * loadFactor));
    }

    private static int[] newTable(int size) {
        int[] array = new int[size];
        Arrays.fill(array, -1);
        return array;
    }

    private static long[] newEntries(int size) {
        long[] array = new long[size];
        Arrays.fill(array, -1L);
        return array;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndex(int index) {
        if (index + 1 < this.size) {
            return index + 1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndexAfterRemove(int oldNextIndex, int removedIndex) {
        return oldNextIndex - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ParametricNullness
    public K getKey(int i) {
        Preconditions.checkElementIndex(i, this.size);
        return (K) this.keys[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return this.values[index];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(int index, int newValue) {
        Preconditions.checkElementIndex(index, this.size);
        this.values[index] = newValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Multiset.Entry<K> getEntry(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return new MapEntry(index);
    }

    /* loaded from: classes25.dex */
    class MapEntry extends Multisets.AbstractEntry<K> {

        @ParametricNullness
        final K key;
        int lastKnownIndex;

        MapEntry(int i) {
            this.key = (K) ObjectCountHashMap.this.keys[i];
            this.lastKnownIndex = i;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @ParametricNullness
        public K getElement() {
            return this.key;
        }

        void updateLastKnownIndex() {
            if (this.lastKnownIndex == -1 || this.lastKnownIndex >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[this.lastKnownIndex];
        }

        public int setCount(int count) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                ObjectCountHashMap.this.put(this.key, count);
                return 0;
            }
            int old = ObjectCountHashMap.this.values[this.lastKnownIndex];
            ObjectCountHashMap.this.values[this.lastKnownIndex] = count;
            return old;
        }
    }

    private static int getHash(long entry) {
        return (int) (entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int) entry;
    }

    private static long swapNext(long entry, int newNext) {
        return (HASH_MASK & entry) | (NEXT_MASK & newNext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > this.entries.length) {
            resizeEntries(minCapacity);
        }
        if (minCapacity >= this.threshold) {
            int newTableSize = Math.max(2, Integer.highestOneBit(minCapacity - 1) << 1);
            resizeTable(newTableSize);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int put(@com.google.common.collect.ParametricNullness K r14, int r15) {
        /*
            r13 = this;
            java.lang.String r0 = "count"
            com.google.common.collect.CollectPreconditions.checkPositive(r15, r0)
            long[] r0 = r13.entries
            java.lang.Object[] r1 = r13.keys
            int[] r2 = r13.values
            int r3 = com.google.common.collect.Hashing.smearedHash(r14)
            int r4 = r13.hashTableMask()
            r4 = r4 & r3
            int r5 = r13.size
            int[] r6 = r13.table
            r6 = r6[r4]
            r7 = -1
            if (r6 != r7) goto L22
            int[] r7 = r13.table
            r7[r4] = r5
            goto L44
        L22:
            r8 = r6
            r9 = r0[r6]
            int r11 = getHash(r9)
            if (r11 != r3) goto L38
            r11 = r1[r6]
            boolean r11 = com.google.common.base.Objects.equal(r14, r11)
            if (r11 == 0) goto L38
            r7 = r2[r6]
            r2[r6] = r15
            return r7
        L38:
            int r6 = getNext(r9)
            if (r6 != r7) goto L22
            long r11 = swapNext(r9, r5)
            r0[r8] = r11
        L44:
            r7 = 2147483647(0x7fffffff, float:NaN)
            if (r5 == r7) goto L67
            int r7 = r5 + 1
            r13.resizeMeMaybe(r7)
            r13.insertEntry(r5, r14, r15, r3)
            r13.size = r7
            int r8 = r13.threshold
            if (r5 < r8) goto L5f
            int[] r8 = r13.table
            int r8 = r8.length
            int r8 = r8 * 2
            r13.resizeTable(r8)
        L5f:
            int r8 = r13.modCount
            int r8 = r8 + 1
            r13.modCount = r8
            r8 = 0
            return r8
        L67:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Cannot contain more than Integer.MAX_VALUE elements!"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ObjectCountHashMap.put(java.lang.Object, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int entryIndex, @ParametricNullness K key, int value, int hash) {
        this.entries[entryIndex] = (hash << 32) | NEXT_MASK;
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
    }

    private void resizeMeMaybe(int newSize) {
        int entriesSize = this.entries.length;
        if (newSize > entriesSize) {
            int newCapacity = Math.max(1, entriesSize >>> 1) + entriesSize;
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            if (newCapacity != entriesSize) {
                resizeEntries(newCapacity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeEntries(int newCapacity) {
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
        long[] entries = this.entries;
        int oldCapacity = entries.length;
        long[] entries2 = Arrays.copyOf(entries, newCapacity);
        if (newCapacity > oldCapacity) {
            Arrays.fill(entries2, oldCapacity, newCapacity, -1L);
        }
        this.entries = entries2;
    }

    private void resizeTable(int newCapacity) {
        int[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        if (oldCapacity >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int newThreshold = ((int) (newCapacity * this.loadFactor)) + 1;
        int[] newTable = newTable(newCapacity);
        long[] entries = this.entries;
        int mask = newTable.length - 1;
        int i = 0;
        while (i < this.size) {
            long oldEntry = entries[i];
            int hash = getHash(oldEntry);
            int tableIndex = hash & mask;
            int next = newTable[tableIndex];
            newTable[tableIndex] = i;
            entries[i] = (next & NEXT_MASK) | (hash << 32);
            i++;
            oldTable = oldTable;
            oldCapacity = oldCapacity;
        }
        this.threshold = newThreshold;
        this.table = newTable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOf(@CheckForNull Object key) {
        int hash = Hashing.smearedHash(key);
        int next = this.table[hashTableMask() & hash];
        while (next != -1) {
            long entry = this.entries[next];
            if (getHash(entry) == hash && Objects.equal(key, this.keys[next])) {
                return next;
            }
            next = getNext(entry);
        }
        return -1;
    }

    public boolean containsKey(@CheckForNull Object key) {
        return indexOf(key) != -1;
    }

    public int get(@CheckForNull Object key) {
        int index = indexOf(key);
        if (index == -1) {
            return 0;
        }
        return this.values[index];
    }

    public int remove(@CheckForNull Object key) {
        return remove(key, Hashing.smearedHash(key));
    }

    private int remove(@CheckForNull Object key, int hash) {
        int tableIndex = hashTableMask() & hash;
        int next = this.table[tableIndex];
        if (next == -1) {
            return 0;
        }
        int last = -1;
        do {
            if (getHash(this.entries[next]) == hash && Objects.equal(key, this.keys[next])) {
                int oldValue = this.values[next];
                if (last == -1) {
                    this.table[tableIndex] = getNext(this.entries[next]);
                } else {
                    this.entries[last] = swapNext(this.entries[last], getNext(this.entries[next]));
                }
                moveLastEntry(next);
                this.size--;
                this.modCount++;
                return oldValue;
            }
            last = next;
            next = getNext(this.entries[next]);
        } while (next != -1);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int removeEntry(int entryIndex) {
        return remove(this.keys[entryIndex], getHash(this.entries[entryIndex]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int dstIndex) {
        int previous;
        long entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            this.keys[dstIndex] = this.keys[srcIndex];
            this.values[dstIndex] = this.values[srcIndex];
            this.keys[srcIndex] = null;
            this.values[srcIndex] = 0;
            long lastEntry = this.entries[srcIndex];
            this.entries[dstIndex] = lastEntry;
            this.entries[srcIndex] = -1;
            int tableIndex = getHash(lastEntry) & hashTableMask();
            int lastNext = this.table[tableIndex];
            if (lastNext == srcIndex) {
                this.table[tableIndex] = dstIndex;
                return;
            }
            do {
                previous = lastNext;
                entry = this.entries[lastNext];
                lastNext = getNext(entry);
            } while (lastNext != srcIndex);
            this.entries[previous] = swapNext(entry, dstIndex);
            return;
        }
        this.keys[dstIndex] = null;
        this.values[dstIndex] = 0;
        this.entries[dstIndex] = -1;
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }
}
