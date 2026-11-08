package androidx.compose.runtime.collection;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScopeMap.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\u0011\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0002H\u0086\u0002J4\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\r0\u0017H\u0086\bJ\u001b\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u001dJ.\u0010\u001e\u001a\u00020\r2#\b\u0004\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00130\u0017H\u0086\bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006 "}, d2 = {"Landroidx/compose/runtime/collection/ScopeMap;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "map", "Landroidx/collection/MutableScatterMap;", "getMap", "()Landroidx/collection/MutableScatterMap;", "size", "", "getSize", "()I", "add", "", "key", "scope", "(Ljava/lang/Object;Ljava/lang/Object;)V", "clear", "contains", "", "element", "forEachScopeOf", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeScope", "(Ljava/lang/Object;)V", "removeScopeIf", "predicate", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ScopeMap<T> {
    public static final int $stable = 8;
    private final MutableScatterMap<Object, Object> map = ScatterMapKt.mutableScatterMapOf();

    public final MutableScatterMap<Object, Object> getMap() {
        return this.map;
    }

    public final int getSize() {
        return this.map.get_size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.collection.MutableScatterSet] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v3 */
    public final void add(Object key, T scope) {
        T t;
        MutableScatterMap<Object, Object> mutableScatterMap = this.map;
        int findInsertIndex = mutableScatterMap.findInsertIndex(key);
        boolean z = findInsertIndex < 0;
        MutableScatterSet mutableScatterSet = z ? null : mutableScatterMap.values[findInsertIndex];
        if (mutableScatterSet == null) {
            t = scope;
        } else {
            if (mutableScatterSet instanceof MutableScatterSet) {
                Intrinsics.checkNotNull(mutableScatterSet, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.add$lambda$0>");
                mutableScatterSet.add(scope);
            } else if (mutableScatterSet != scope) {
                ?? mutableScatterSet2 = new MutableScatterSet(0, 1, null);
                Intrinsics.checkNotNull(mutableScatterSet, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.add$lambda$0");
                mutableScatterSet2.add(mutableScatterSet);
                mutableScatterSet2.add(scope);
                t = mutableScatterSet2;
            }
            t = mutableScatterSet;
        }
        if (z) {
            int i = ~findInsertIndex;
            mutableScatterMap.keys[i] = key;
            mutableScatterMap.values[i] = t;
            return;
        }
        mutableScatterMap.values[findInsertIndex] = t;
    }

    public final boolean contains(Object element) {
        return this.map.containsKey(element);
    }

    public final void forEachScopeOf(Object key, Function1<? super T, Unit> block) {
        Object value = getMap().get(key);
        if (value != null) {
            if (value instanceof MutableScatterSet) {
                ScatterSet this_$iv = (MutableScatterSet) value;
                Object[] k$iv = this_$iv.elements;
                long[] m$iv$iv = this_$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 > lastIndex$iv$iv) {
                    return;
                }
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    ScatterSet this_$iv2 = this_$iv;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                block.invoke(k$iv[index$iv$iv]);
                            }
                            i = 8;
                            slot$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv != i) {
                            return;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        return;
                    }
                    i$iv$iv++;
                    this_$iv = this_$iv2;
                }
            } else {
                block.invoke(value);
            }
        }
    }

    public final void clear() {
        this.map.clear();
    }

    public final boolean remove(Object key, T scope) {
        Object value = this.map.get(key);
        if (value == null) {
            return false;
        }
        if (value instanceof MutableScatterSet) {
            MutableScatterSet set = (MutableScatterSet) value;
            boolean removed = set.remove(scope);
            if (removed && set.isEmpty()) {
                this.map.remove(key);
            }
            return removed;
        }
        if (!Intrinsics.areEqual(value, scope)) {
            return false;
        }
        this.map.remove(key);
        return true;
    }

    public final void removeScopeIf(Function1<? super T, Boolean> predicate) {
        int $i$f$removeScopeIf;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv;
        int lastIndex$iv$iv;
        int i$iv$iv;
        int $i$f$removeScopeIf2;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv2;
        int lastIndex$iv$iv2;
        int i$iv$iv2;
        long $this$maskEmptyOrDeleted$iv$iv$iv;
        boolean booleanValue;
        int $i$f$removeScopeIf3 = 0;
        MutableScatterMap this_$iv = getMap();
        int $i$f$removeIf3 = 0;
        MutableScatterMap this_$iv$iv3 = this_$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv3 = this_$iv$iv3.metadata;
        int lastIndex$iv$iv3 = m$iv$iv3.length - 2;
        int i$iv$iv3 = 0;
        if (0 > lastIndex$iv$iv3) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv3[i$iv$iv3];
            long $this$maskEmptyOrDeleted$iv$iv$iv2 = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv2 == -9187201950435737472L) {
                $i$f$removeScopeIf = $i$f$removeScopeIf3;
                $i$f$removeIf = $i$f$removeIf3;
                this_$iv$iv = this_$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv = m$iv$iv3;
                lastIndex$iv$iv = lastIndex$iv$iv3;
                i$iv$iv = i$iv$iv3;
            } else {
                int i = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv3 - lastIndex$iv$iv3)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = slot$iv$iv & 255;
                    if (!(value$iv$iv$iv < 128)) {
                        $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                        $i$f$removeIf2 = $i$f$removeIf3;
                        this_$iv$iv2 = this_$iv$iv3;
                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        m$iv$iv2 = m$iv$iv3;
                        lastIndex$iv$iv2 = lastIndex$iv$iv3;
                        i$iv$iv2 = i$iv$iv3;
                        $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                    } else {
                        int index$iv$iv = (i$iv$iv3 << 3) + j$iv$iv;
                        Object obj = this_$iv.keys[index$iv$iv];
                        Object value = this_$iv.values[index$iv$iv];
                        if (value instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1>");
                            MutableScatterSet set = (MutableScatterSet) value;
                            $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            Object[] elements$iv = set.elements;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            long[] m$iv$iv4 = set.metadata;
                            int lastIndex$iv$iv4 = m$iv$iv4.length - 2;
                            m$iv$iv2 = m$iv$iv3;
                            int i$iv$iv4 = 0;
                            if (0 <= lastIndex$iv$iv4) {
                                while (true) {
                                    long slot$iv$iv2 = m$iv$iv4[i$iv$iv4];
                                    lastIndex$iv$iv2 = lastIndex$iv$iv3;
                                    i$iv$iv2 = i$iv$iv3;
                                    $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv3 = ((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv3 != -9187201950435737472L) {
                                        int bitCount$iv$iv2 = 8 - ((~(i$iv$iv4 - lastIndex$iv$iv4)) >>> 31);
                                        for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                                            long value$iv$iv$iv2 = slot$iv$iv2 & 255;
                                            if (value$iv$iv$iv2 < 128) {
                                                int index$iv$iv2 = (i$iv$iv4 << 3) + j$iv$iv2;
                                                if (predicate.invoke(elements$iv[index$iv$iv2]).booleanValue()) {
                                                    set.removeElementAt(index$iv$iv2);
                                                }
                                            }
                                            slot$iv$iv2 >>= 8;
                                        }
                                        if (bitCount$iv$iv2 != 8) {
                                            break;
                                        }
                                    }
                                    if (i$iv$iv4 == lastIndex$iv$iv4) {
                                        break;
                                    }
                                    i$iv$iv4++;
                                    slot$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv;
                                    lastIndex$iv$iv3 = lastIndex$iv$iv2;
                                    i$iv$iv3 = i$iv$iv2;
                                }
                            } else {
                                lastIndex$iv$iv2 = lastIndex$iv$iv3;
                                i$iv$iv2 = i$iv$iv3;
                                $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                            }
                            booleanValue = set.isEmpty();
                        } else {
                            $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv2 = m$iv$iv3;
                            lastIndex$iv$iv2 = lastIndex$iv$iv3;
                            i$iv$iv2 = i$iv$iv3;
                            $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1");
                            booleanValue = predicate.invoke(value).booleanValue();
                        }
                        if (booleanValue) {
                            this_$iv.removeValueAt(index$iv$iv);
                        }
                    }
                    slot$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv >> 8;
                    j$iv$iv++;
                    i = 8;
                    $i$f$removeIf3 = $i$f$removeIf2;
                    $i$f$removeScopeIf3 = $i$f$removeScopeIf2;
                    this_$iv$iv3 = this_$iv$iv2;
                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    m$iv$iv3 = m$iv$iv2;
                    lastIndex$iv$iv3 = lastIndex$iv$iv2;
                    i$iv$iv3 = i$iv$iv2;
                }
                $i$f$removeScopeIf = $i$f$removeScopeIf3;
                $i$f$removeIf = $i$f$removeIf3;
                this_$iv$iv = this_$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv = m$iv$iv3;
                lastIndex$iv$iv = lastIndex$iv$iv3;
                i$iv$iv = i$iv$iv3;
                int $i$f$removeScopeIf4 = i;
                if (bitCount$iv$iv != $i$f$removeScopeIf4) {
                    return;
                }
            }
            lastIndex$iv$iv3 = lastIndex$iv$iv;
            int i$iv$iv5 = i$iv$iv;
            if (i$iv$iv5 == lastIndex$iv$iv3) {
                return;
            }
            i$iv$iv3 = i$iv$iv5 + 1;
            $i$f$removeIf3 = $i$f$removeIf;
            $i$f$removeScopeIf3 = $i$f$removeScopeIf;
            this_$iv$iv3 = this_$iv$iv;
            $i$f$forEachIndexed3 = $i$f$forEachIndexed;
            m$iv$iv3 = m$iv$iv;
        }
    }

    public final void removeScope(T scope) {
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        int lastIndex$iv$iv$iv;
        int i$iv$iv$iv;
        ScopeMap this_$iv;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        int lastIndex$iv$iv$iv2;
        int i$iv$iv$iv2;
        long slot$iv$iv$iv;
        ScopeMap this_$iv2;
        ScopeMap this_$iv3 = this;
        int $i$f$removeScopeIf = 0;
        MutableScatterMap this_$iv$iv = this_$iv3.getMap();
        int $i$f$removeIf3 = 0;
        MutableScatterMap this_$iv$iv$iv3 = this_$iv$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv3 = this_$iv$iv$iv3.metadata;
        int lastIndex$iv$iv$iv3 = m$iv$iv$iv3.length - 2;
        int i$iv$iv$iv3 = 0;
        if (0 > lastIndex$iv$iv$iv3) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv2 = m$iv$iv$iv3[i$iv$iv$iv3];
            ScopeMap this_$iv4 = this_$iv3;
            int $i$f$removeScopeIf2 = $i$f$removeScopeIf;
            if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$removeIf = $i$f$removeIf3;
                this_$iv$iv$iv = this_$iv$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv$iv = m$iv$iv$iv3;
                lastIndex$iv$iv$iv = lastIndex$iv$iv$iv3;
                i$iv$iv$iv = i$iv$iv$iv3;
                this_$iv = this_$iv4;
            } else {
                int i = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv3 - lastIndex$iv$iv$iv3)) >>> 31);
                int j$iv$iv$iv = 0;
                while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv2 & 255;
                    boolean z = false;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        $i$f$removeIf2 = $i$f$removeIf3;
                        this_$iv$iv$iv2 = this_$iv$iv$iv3;
                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        m$iv$iv$iv2 = m$iv$iv$iv3;
                        lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                        i$iv$iv$iv2 = i$iv$iv$iv3;
                        slot$iv$iv$iv = slot$iv$iv$iv2;
                        this_$iv2 = this_$iv4;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv3 << 3) + j$iv$iv$iv;
                        Object obj = this_$iv$iv.keys[index$iv$iv$iv];
                        Object value$iv = this_$iv$iv.values[index$iv$iv$iv];
                        if (value$iv instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1>");
                            MutableScatterSet set$iv = (MutableScatterSet) value$iv;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            Object[] elements$iv$iv = set$iv.elements;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                            long[] m$iv$iv$iv4 = set$iv.metadata;
                            int lastIndex$iv$iv$iv4 = m$iv$iv$iv4.length - 2;
                            this_$iv2 = this_$iv4;
                            int i$iv$iv$iv4 = 0;
                            if (0 <= lastIndex$iv$iv$iv4) {
                                while (true) {
                                    long slot$iv$iv$iv3 = m$iv$iv$iv4[i$iv$iv$iv4];
                                    long[] m$iv$iv$iv5 = m$iv$iv$iv4;
                                    lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                    slot$iv$iv$iv = slot$iv$iv$iv2;
                                    i$iv$iv$iv2 = i$iv$iv$iv3;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv3) << 7) & slot$iv$iv$iv3 & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                                        int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv4 - lastIndex$iv$iv$iv4)) >>> 31);
                                        for (int j$iv$iv$iv2 = 0; j$iv$iv$iv2 < bitCount$iv$iv$iv2; j$iv$iv$iv2++) {
                                            long value$iv$iv$iv$iv2 = slot$iv$iv$iv3 & 255;
                                            int $i$f$isFull = value$iv$iv$iv$iv2 < 128 ? 1 : 0;
                                            if ($i$f$isFull != 0) {
                                                int index$iv$iv$iv2 = (i$iv$iv$iv4 << 3) + j$iv$iv$iv2;
                                                Object it = elements$iv$iv[index$iv$iv$iv2];
                                                Object it2 = it == scope ? 1 : null;
                                                if (it2 != null) {
                                                    set$iv.removeElementAt(index$iv$iv$iv2);
                                                }
                                            }
                                            slot$iv$iv$iv3 >>= 8;
                                        }
                                        if (bitCount$iv$iv$iv2 != 8) {
                                            break;
                                        }
                                    }
                                    if (i$iv$iv$iv4 == lastIndex$iv$iv$iv4) {
                                        break;
                                    }
                                    i$iv$iv$iv4++;
                                    i$iv$iv$iv3 = i$iv$iv$iv2;
                                    lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv2;
                                    m$iv$iv$iv4 = m$iv$iv$iv5;
                                    slot$iv$iv$iv2 = slot$iv$iv$iv;
                                }
                            } else {
                                lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                i$iv$iv$iv2 = i$iv$iv$iv3;
                                slot$iv$iv$iv = slot$iv$iv$iv2;
                            }
                            z = set$iv.isEmpty();
                        } else {
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                            lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                            i$iv$iv$iv2 = i$iv$iv$iv3;
                            slot$iv$iv$iv = slot$iv$iv$iv2;
                            this_$iv2 = this_$iv4;
                            Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1");
                            if (value$iv == scope) {
                                z = true;
                            }
                        }
                        if (z) {
                            this_$iv$iv.removeValueAt(index$iv$iv$iv);
                        }
                    }
                    i = 8;
                    slot$iv$iv$iv2 = slot$iv$iv$iv >> 8;
                    j$iv$iv$iv++;
                    this_$iv$iv$iv3 = this_$iv$iv$iv2;
                    $i$f$removeIf3 = $i$f$removeIf2;
                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    m$iv$iv$iv3 = m$iv$iv$iv2;
                    this_$iv4 = this_$iv2;
                    i$iv$iv$iv3 = i$iv$iv$iv2;
                    lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv2;
                }
                $i$f$removeIf = $i$f$removeIf3;
                this_$iv$iv$iv = this_$iv$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv$iv = m$iv$iv$iv3;
                lastIndex$iv$iv$iv = lastIndex$iv$iv$iv3;
                i$iv$iv$iv = i$iv$iv$iv3;
                this_$iv = this_$iv4;
                if (bitCount$iv$iv$iv != i) {
                    return;
                }
            }
            int i$iv$iv$iv5 = i$iv$iv$iv;
            lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv;
            if (i$iv$iv$iv5 == lastIndex$iv$iv$iv3) {
                return;
            }
            i$iv$iv$iv3 = i$iv$iv$iv5 + 1;
            $i$f$removeScopeIf = $i$f$removeScopeIf2;
            this_$iv$iv$iv3 = this_$iv$iv$iv;
            $i$f$removeIf3 = $i$f$removeIf;
            $i$f$forEachIndexed3 = $i$f$forEachIndexed;
            m$iv$iv$iv3 = m$iv$iv$iv;
            this_$iv3 = this_$iv;
        }
    }
}
