package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectIntMap;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.RecomposeScopeObserver;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecomposeScopeImpl.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 R2\u00020\u00012\u00020\u0002:\u0001RB\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010;\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u000eJ\u001c\u0010>\u001a\u0010\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u0010\u0018\u00010?2\u0006\u0010A\u001a\u00020\u000fJ\b\u0010B\u001a\u00020\u0010H\u0016J\u0010\u0010C\u001a\u00020D2\b\u0010\u0016\u001a\u0004\u0018\u000103J\u0016\u0010E\u001a\u00020\u00122\u000e\u0010F\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010GJ\u0015\u0010H\u001a\u00020I2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\bJJ\u000e\u0010K\u001a\u00020\u00122\u0006\u0010L\u001a\u000203J\u0006\u0010M\u001a\u00020\u0010J\u0006\u0010N\u001a\u00020\u0010J\u0006\u0010O\u001a\u00020\u0010J\u000e\u0010P\u001a\u00020\u00102\u0006\u0010A\u001a\u00020\u000fJ\"\u0010Q\u001a\u00020\u00102\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\rH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u0014\"\u0004\b\u001d\u0010\u001aR\u000e\u0010\u001e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u001aR\u0011\u0010\"\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0014R\u001a\u0010#\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010&R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010'\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010\u0014\"\u0004\b)\u0010\u001aR$\u0010*\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b+\u0010\u0014\"\u0004\b,\u0010\u001aR$\u0010-\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128@@BX\u0080\u000e¢\u0006\f\u001a\u0004\b.\u0010\u0014\"\u0004\b/\u0010\u001aR\"\u00100\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u000302\u0012\u0006\u0012\u0004\u0018\u000103\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\n\u0012\u0004\u0012\u000203\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00106\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u0010\u0014\"\u0004\b8\u0010\u001aR\u0011\u00109\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b:\u0010\u0014¨\u0006S"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "owner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "(Landroidx/compose/runtime/RecomposeScopeOwner;)V", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "", "canRecompose", "", "getCanRecompose", "()Z", "currentToken", "value", "defaultsInScope", "getDefaultsInScope", "setDefaultsInScope", "(Z)V", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "flags", "forcedRecompose", "getForcedRecompose", "setForcedRecompose", "isConditional", "observer", "Landroidx/compose/runtime/tooling/RecomposeScopeObserver;", "getObserver$annotations", "()V", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "rereading", "getRereading", "setRereading", "skipped", "getSkipped$runtime_release", "setSkipped", "trackedDependencies", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/DerivedState;", "", "trackedInstances", "Landroidx/collection/MutableObjectIntMap;", "used", "getUsed", "setUsed", "valid", "getValid", "adoptedBy", "compose", "composer", TtmlNode.END, "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "token", "invalidate", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "isInvalidFor", "instances", "Landroidx/compose/runtime/collection/IdentityArraySet;", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observe$runtime_release", "recordRead", "instance", "release", "rereadTrackedInstances", "scopeSkipped", TtmlNode.START, "updateScope", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private int currentToken;
    private int flags;
    private RecomposeScopeObserver observer;
    private RecomposeScopeOwner owner;
    private MutableScatterMap<DerivedState<?>, Object> trackedDependencies;
    private MutableObjectIntMap<Object> trackedInstances;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    private static /* synthetic */ void getObserver$annotations() {
    }

    public RecomposeScopeImpl(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final boolean getValid() {
        if (this.owner == null) {
            return false;
        }
        Anchor anchor = this.anchor;
        return anchor != null ? anchor.getValid() : false;
    }

    public final boolean getCanRecompose() {
        return this.block != null;
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final void setUsed(boolean value) {
        if (value) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final void setDefaultsInScope(boolean value) {
        if (value) {
            this.flags |= 2;
        } else {
            this.flags &= -3;
        }
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final void setDefaultsInvalid(boolean value) {
        if (value) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final void setRequiresRecompose(boolean value) {
        if (value) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void compose(Composer composer) {
        Unit unit;
        Function2 block = this.block;
        RecomposeScopeObserver observer = this.observer;
        if (observer != null && block != null) {
            observer.onBeginScopeComposition(this);
            try {
                block.invoke(composer, 1);
                return;
            } finally {
                observer.onEndScopeComposition(this);
            }
        }
        if (block != null) {
            block.invoke(composer, 1);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
    }

    public final CompositionObserverHandle observe$runtime_release(final RecomposeScopeObserver observer) {
        Object lock$iv;
        lock$iv = RecomposeScopeImplKt.callbackLock;
        synchronized (lock$iv) {
            this.observer = observer;
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.RecomposeScopeImpl$observe$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2;
                RecomposeScopeObserver recomposeScopeObserver;
                lock$iv2 = RecomposeScopeImplKt.callbackLock;
                RecomposeScopeImpl recomposeScopeImpl = RecomposeScopeImpl.this;
                RecomposeScopeObserver recomposeScopeObserver2 = observer;
                synchronized (lock$iv2) {
                    recomposeScopeObserver = recomposeScopeImpl.observer;
                    if (Intrinsics.areEqual(recomposeScopeObserver, recomposeScopeObserver2)) {
                        recomposeScopeImpl.observer = null;
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    public final InvalidationResult invalidateForResult(Object value) {
        InvalidationResult invalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        return (recomposeScopeOwner == null || (invalidate = recomposeScopeOwner.invalidate(this, value)) == null) ? InvalidationResult.IGNORED : invalidate;
    }

    public final void release() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.recomposeScopeReleased(this);
        }
        this.owner = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
        RecomposeScopeObserver recomposeScopeObserver = this.observer;
        if (recomposeScopeObserver != null) {
            recomposeScopeObserver.onScopeDisposed(this);
        }
    }

    public final void adoptedBy(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.invalidate(this, null);
        }
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        this.block = block;
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean value) {
        if (value) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }

    public final boolean getForcedRecompose() {
        return (this.flags & 64) != 0;
    }

    public final void setForcedRecompose(boolean value) {
        if (value) {
            this.flags |= 64;
        } else {
            this.flags &= -65;
        }
    }

    public final boolean getSkipped$runtime_release() {
        return (this.flags & 16) != 0;
    }

    private final void setSkipped(boolean value) {
        if (value) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        setSkipped(true);
    }

    public final boolean recordRead(Object instance) {
        int i = 0;
        if (getRereading()) {
            return false;
        }
        MutableObjectIntMap it = this.trackedInstances;
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i2 = 1;
        if (it == null) {
            it = new MutableObjectIntMap(i, i2, defaultConstructorMarker);
            this.trackedInstances = it;
        }
        int token = it.put(instance, this.currentToken, -1);
        if (token == this.currentToken) {
            return true;
        }
        if (instance instanceof DerivedState) {
            MutableScatterMap it2 = this.trackedDependencies;
            if (it2 == null) {
                it2 = new MutableScatterMap(i, i2, defaultConstructorMarker);
                this.trackedDependencies = it2;
            }
            MutableScatterMap tracked = it2;
            tracked.set(instance, ((DerivedState) instance).getCurrentRecord().getCurrentValue());
        }
        return false;
    }

    public final boolean isConditional() {
        return this.trackedDependencies != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[LOOP:0: B:18:0x0028->B:32:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isInvalidFor(androidx.compose.runtime.collection.IdentityArraySet<java.lang.Object> r15) {
        /*
            r14 = this;
            r0 = 1
            if (r15 != 0) goto L4
            return r0
        L4:
            androidx.collection.MutableScatterMap<androidx.compose.runtime.DerivedState<?>, java.lang.Object> r1 = r14.trackedDependencies
            if (r1 != 0) goto L9
            return r0
        L9:
            boolean r2 = r15.isNotEmpty()
            if (r2 == 0) goto L64
            r2 = r15
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r3 = 0
            boolean r4 = r2 instanceof java.util.Collection
            r5 = 0
            if (r4 == 0) goto L24
            r4 = r2
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L24
            r2 = r0
            goto L61
        L24:
            java.util.Iterator r4 = r2.iterator()
        L28:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L60
            java.lang.Object r6 = r4.next()
            r7 = r6
            r8 = 0
            boolean r9 = r7 instanceof androidx.compose.runtime.DerivedState
            if (r9 == 0) goto L5b
            r9 = r7
            androidx.compose.runtime.DerivedState r9 = (androidx.compose.runtime.DerivedState) r9
            r10 = 0
            androidx.compose.runtime.SnapshotMutationPolicy r11 = r9.getPolicy()
            if (r11 != 0) goto L47
            androidx.compose.runtime.SnapshotMutationPolicy r11 = androidx.compose.runtime.SnapshotStateKt.structuralEqualityPolicy()
        L47:
            androidx.compose.runtime.DerivedState$Record r12 = r9.getCurrentRecord()
            java.lang.Object r12 = r12.getCurrentValue()
            java.lang.Object r13 = r1.get(r9)
            boolean r9 = r11.equivalent(r12, r13)
            if (r9 == 0) goto L5b
            r9 = r0
            goto L5c
        L5b:
            r9 = r5
        L5c:
            if (r9 != 0) goto L28
            r2 = r5
            goto L61
        L60:
            r2 = r0
        L61:
            if (r2 == 0) goto L64
            return r5
        L64:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(androidx.compose.runtime.collection.IdentityArraySet):boolean");
    }

    public final void rereadTrackedInstances() {
        ObjectIntMap trackedInstances;
        RecomposeScopeImpl recomposeScopeImpl = this;
        RecomposeScopeOwner owner = recomposeScopeImpl.owner;
        if (owner != null && (trackedInstances = recomposeScopeImpl.trackedInstances) != null) {
            recomposeScopeImpl.setRereading(true);
            try {
                ObjectIntMap this_$iv = trackedInstances;
                Object[] k$iv = this_$iv.keys;
                int[] v$iv = this_$iv.values;
                long[] m$iv$iv = this_$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        try {
                            long slot$iv$iv = m$iv$iv[i$iv$iv];
                            ObjectIntMap this_$iv2 = this_$iv;
                            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                                int i = 8;
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                                    long value$iv$iv$iv = slot$iv$iv & 255;
                                    int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                                    if ($i$f$isFull != 0) {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                        Object value = k$iv[index$iv$iv];
                                        int i2 = v$iv[index$iv$iv];
                                        owner.recordReadOf(value);
                                    }
                                    i = 8;
                                    slot$iv$iv >>= 8;
                                }
                                if (bitCount$iv$iv != i) {
                                    break;
                                }
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            this_$iv = this_$iv2;
                        } catch (Throwable th) {
                            th = th;
                            recomposeScopeImpl = this;
                            recomposeScopeImpl.setRereading(false);
                            throw th;
                        }
                    }
                }
                setRereading(false);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final Function1<Composition, Unit> end(final int token) {
        final MutableObjectIntMap instances = this.trackedInstances;
        if (instances == null) {
            return null;
        }
        int i = 0;
        if (!getSkipped$runtime_release()) {
            MutableObjectIntMap this_$iv = instances;
            int $i$f$any = 0;
            Object[] k$iv$iv = this_$iv.keys;
            int[] v$iv$iv = this_$iv.values;
            long[] m$iv$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            boolean z = false;
            if (0 <= lastIndex$iv$iv$iv) {
                loop0: while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    int i2 = i;
                    ObjectIntMap this_$iv2 = this_$iv;
                    int $i$f$any2 = $i$f$any;
                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                        int i3 = 8;
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        int j$iv$iv$iv = 0;
                        while (true) {
                            if (j$iv$iv$iv >= bitCount$iv$iv$iv) {
                                if (bitCount$iv$iv$iv != i3) {
                                    break;
                                }
                            } else {
                                long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                                if ($i$f$isFull != 0) {
                                    int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                    Object obj = k$iv$iv[index$iv$iv$iv];
                                    int value$iv = v$iv$iv[index$iv$iv$iv];
                                    int instanceToken = value$iv != token ? 1 : 0;
                                    if (instanceToken != 0) {
                                        z = true;
                                        break loop0;
                                    }
                                }
                                i3 = 8;
                                slot$iv$iv$iv >>= 8;
                                j$iv$iv$iv++;
                            }
                        }
                    }
                    if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                        break;
                    }
                    i$iv$iv$iv++;
                    this_$iv = this_$iv2;
                    $i$f$any = $i$f$any2;
                    i = i2;
                }
            }
            if (z) {
                return new Function1<Composition, Unit>() { // from class: androidx.compose.runtime.RecomposeScopeImpl$end$1$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Composition composition) {
                        invoke2(composition);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Composition composition) {
                        int i4;
                        MutableObjectIntMap mutableObjectIntMap;
                        int i5;
                        int $i$f$removeIf;
                        int i6;
                        int $i$f$removeIf2;
                        MutableScatterMap dependencies;
                        Composition composition2 = composition;
                        i4 = RecomposeScopeImpl.this.currentToken;
                        if (i4 == token) {
                            MutableObjectIntMap<Object> mutableObjectIntMap2 = instances;
                            mutableObjectIntMap = RecomposeScopeImpl.this.trackedInstances;
                            if (Intrinsics.areEqual(mutableObjectIntMap2, mutableObjectIntMap) && (composition2 instanceof CompositionImpl)) {
                                MutableObjectIntMap this_$iv3 = instances;
                                int i7 = token;
                                RecomposeScopeImpl recomposeScopeImpl = RecomposeScopeImpl.this;
                                int $i$f$removeIf3 = 0;
                                MutableObjectIntMap this_$iv$iv = this_$iv3;
                                long[] m$iv$iv = this_$iv$iv.metadata;
                                int lastIndex$iv$iv = m$iv$iv.length - 2;
                                int i$iv$iv = 0;
                                if (0 <= lastIndex$iv$iv) {
                                    while (true) {
                                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                                        long slot$iv$iv2 = slot$iv$iv;
                                        if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                            i5 = i7;
                                            $i$f$removeIf = $i$f$removeIf3;
                                        } else {
                                            int i8 = 8;
                                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                            int j$iv$iv = 0;
                                            while (j$iv$iv < bitCount$iv$iv) {
                                                long value$iv$iv$iv = slot$iv$iv2 & 255;
                                                if (!(value$iv$iv$iv < 128)) {
                                                    i6 = i7;
                                                    $i$f$removeIf2 = $i$f$removeIf3;
                                                } else {
                                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                                    Object instance = this_$iv3.keys[index$iv$iv];
                                                    $i$f$removeIf2 = $i$f$removeIf3;
                                                    int instanceToken2 = this_$iv3.values[index$iv$iv];
                                                    boolean z2 = instanceToken2 != i7;
                                                    boolean remove = z2;
                                                    if (!remove) {
                                                        i6 = i7;
                                                    } else {
                                                        i6 = i7;
                                                        ((CompositionImpl) composition2).removeObservation$runtime_release(instance, recomposeScopeImpl);
                                                        DerivedState it = instance instanceof DerivedState ? (DerivedState) instance : null;
                                                        if (it != null) {
                                                            ((CompositionImpl) composition2).removeDerivedStateObservation$runtime_release(it);
                                                            dependencies = recomposeScopeImpl.trackedDependencies;
                                                            if (dependencies != null) {
                                                                dependencies.remove(it);
                                                                if (dependencies.get_size() == 0) {
                                                                    recomposeScopeImpl.trackedDependencies = null;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (z2) {
                                                        this_$iv3.removeValueAt(index$iv$iv);
                                                    }
                                                }
                                                slot$iv$iv2 >>= 8;
                                                j$iv$iv++;
                                                i8 = 8;
                                                $i$f$removeIf3 = $i$f$removeIf2;
                                                i7 = i6;
                                                composition2 = composition;
                                            }
                                            i5 = i7;
                                            $i$f$removeIf = $i$f$removeIf3;
                                            if (bitCount$iv$iv != i8) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv == lastIndex$iv$iv) {
                                            break;
                                        }
                                        i$iv$iv++;
                                        composition2 = composition;
                                        $i$f$removeIf3 = $i$f$removeIf;
                                        i7 = i5;
                                    }
                                }
                                if (instances.get_size() == 0) {
                                    RecomposeScopeImpl.this.trackedInstances = null;
                                }
                            }
                        }
                    }
                };
            }
        }
        return null;
    }

    /* compiled from: RecomposeScopeImpl.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ#\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl$Companion;", "", "()V", "adoptAnchoredScopes", "", "slots", "Landroidx/compose/runtime/SlotWriter;", "anchors", "", "Landroidx/compose/runtime/Anchor;", "newOwner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "adoptAnchoredScopes$runtime_release", "hasAnchoredRecomposeScopes", "", "Landroidx/compose/runtime/SlotTable;", "hasAnchoredRecomposeScopes$runtime_release", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void adoptAnchoredScopes$runtime_release(SlotWriter slots, List<Anchor> anchors, RecomposeScopeOwner newOwner) {
            if (!(!anchors.isEmpty())) {
                return;
            }
            int size = anchors.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = anchors.get(index$iv);
                Anchor anchor = (Anchor) item$iv;
                Object slot = slots.slot(anchor, 0);
                RecomposeScopeImpl recomposeScope = slot instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) slot : null;
                if (recomposeScope != null) {
                    recomposeScope.adoptedBy(newOwner);
                }
            }
        }

        public final boolean hasAnchoredRecomposeScopes$runtime_release(SlotTable slots, List<Anchor> anchors) {
            List $this$fastAny$iv;
            if (!anchors.isEmpty()) {
                int index$iv$iv = 0;
                int size = anchors.size();
                while (true) {
                    if (index$iv$iv < size) {
                        Object item$iv$iv = anchors.get(index$iv$iv);
                        Anchor it = (Anchor) item$iv$iv;
                        if (((slots.ownsAnchor(it) && (slots.slot$runtime_release(slots.anchorIndex(it), 0) instanceof RecomposeScopeImpl)) ? 1 : null) != null) {
                            $this$fastAny$iv = 1;
                            break;
                        }
                        index$iv$iv++;
                    } else {
                        $this$fastAny$iv = null;
                        break;
                    }
                }
                if ($this$fastAny$iv != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
