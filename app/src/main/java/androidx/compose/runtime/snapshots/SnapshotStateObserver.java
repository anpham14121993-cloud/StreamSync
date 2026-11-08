package androidx.compose.runtime.snapshots;

import androidx.autofill.HintConstants;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: SnapshotStateObserver.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0001\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u00017B.\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\tJ\u0016\u0010\u001d\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0002J\u0006\u0010\u001f\u001a\u00020\u0005J\u000e\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0001J)\u0010!\u001a\u00020\u00052!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00150\u0003J\b\u0010#\u001a\u00020\u0015H\u0002J&\u0010$\u001a\u00020\u0011\"\b\b\u0000\u0010%*\u00020\u00012\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050\u0003H\u0002J\u001d\u0010'\u001a\u00020\u00052\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00050\u0003H\u0082\bJ\u001e\u0010)\u001a\u00020\u00052\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010+\u001a\u00020\rH\u0007J?\u0010,\u001a\u00020\u0005\"\b\b\u0000\u0010%*\u00020\u00012\u0006\u0010 \u001a\u0002H%2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u00020\u00050\u00032\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\fH\u0002J\u001d\u00100\u001a\u00020\u00052\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00150\u0003H\u0082\bJ\b\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020\u0005H\u0002J\u0006\u00104\u001a\u00020\u0005J\u0006\u00105\u001a\u00020\u0005J\u0016\u00106\u001a\u00020\u00052\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007R&\u0010\n\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "callback", "(Lkotlin/jvm/functions/Function1;)V", "applyObserver", "Lkotlin/Function2;", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "currentMap", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "currentMapThreadId", "", "isPaused", "", "observedScopeMaps", "Landroidx/compose/runtime/collection/MutableVector;", "pendingChanges", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "readObserver", "sendingNotifications", "addChanges", "set", "clear", "scope", "clearIf", "predicate", "drainChanges", "ensureMap", ExifInterface.GPS_DIRECTION_TRUE, "onChanged", "forEachScopeMap", "block", "notifyChanges", "changes", "snapshot", "observeReads", "onValueChangedForScope", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "removeChanges", "removeScopeMapIf", "report", "", "sendNotifications", TtmlNode.START, "stop", "withNoObservations", "ObservedScopeMap", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1<Function0<Unit>, Unit> onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference<Object> pendingChanges = new AtomicReference<>(null);
    private final Function2<Set<? extends Object>, Snapshot, Unit> applyObserver = new Function2<Set<? extends Object>, Snapshot, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$applyObserver$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Set<? extends Object> set, Snapshot snapshot) {
            invoke2(set, snapshot);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Set<? extends Object> set, Snapshot snapshot) {
            boolean drainChanges;
            SnapshotStateObserver.this.addChanges(set);
            drainChanges = SnapshotStateObserver.this.drainChanges();
            if (drainChanges) {
                SnapshotStateObserver.this.sendNotifications();
            }
        }
    };
    private final Function1<Object, Unit> readObserver = new Function1<Object, Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$readObserver$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object p1) {
            invoke2(p1);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Object state) {
            boolean z;
            MutableVector mutableVector;
            SnapshotStateObserver.ObservedScopeMap observedScopeMap;
            z = SnapshotStateObserver.this.isPaused;
            if (!z) {
                mutableVector = SnapshotStateObserver.this.observedScopeMaps;
                SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                synchronized (mutableVector) {
                    observedScopeMap = snapshotStateObserver.currentMap;
                    Intrinsics.checkNotNull(observedScopeMap);
                    observedScopeMap.recordRead(state);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    };
    private final MutableVector<ObservedScopeMap> observedScopeMaps = new MutableVector<>(new ObservedScopeMap[16], 0);
    private long currentMapThreadId = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(Function1<? super Function0<Unit>, Unit> function1) {
        this.onChangedExecutor = function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean drainChanges() {
        boolean z;
        boolean z2;
        synchronized (this.observedScopeMaps) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean hasValues = false;
        while (true) {
            Set notifications = removeChanges();
            if (notifications == null) {
                return hasValues;
            }
            synchronized (this.observedScopeMaps) {
                MutableVector this_$iv$iv = this.observedScopeMaps;
                int size$iv$iv = this_$iv$iv.getSize();
                if (size$iv$iv > 0) {
                    int i$iv$iv = 0;
                    Object[] content$iv$iv = this_$iv$iv.getContent();
                    do {
                        ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv$iv[i$iv$iv];
                        if (!scopeMap.recordInvalidation(notifications) && !hasValues) {
                            z2 = false;
                            hasValues = z2;
                            i$iv$iv++;
                        }
                        z2 = true;
                        hasValues = z2;
                        i$iv$iv++;
                    } while (i$iv$iv < size$iv$iv);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNotifications() {
        this.onChangedExecutor.invoke(new Function0<Unit>() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$sendNotifications$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MutableVector mutableVector;
                boolean z;
                boolean drainChanges;
                MutableVector this_$iv;
                do {
                    mutableVector = SnapshotStateObserver.this.observedScopeMaps;
                    SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                    synchronized (mutableVector) {
                        z = snapshotStateObserver.sendingNotifications;
                        if (!z) {
                            snapshotStateObserver.sendingNotifications = true;
                            try {
                                this_$iv = snapshotStateObserver.observedScopeMaps;
                                int size$iv = this_$iv.getSize();
                                if (size$iv > 0) {
                                    int i$iv = 0;
                                    Object[] content$iv = this_$iv.getContent();
                                    do {
                                        SnapshotStateObserver.ObservedScopeMap scopeMap = (SnapshotStateObserver.ObservedScopeMap) content$iv[i$iv];
                                        scopeMap.notifyInvalidatedScopes();
                                        i$iv++;
                                    } while (i$iv < size$iv);
                                }
                                snapshotStateObserver.sendingNotifications = false;
                            } finally {
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    drainChanges = SnapshotStateObserver.this.drainChanges();
                } while (drainChanges);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void addChanges(Set<? extends Object> set) {
        Object old;
        Collection plus;
        do {
            old = this.pendingChanges.get();
            if (old == null) {
                plus = set;
            } else if (old instanceof Set) {
                plus = CollectionsKt.listOf((Object[]) new Set[]{old, set});
            } else {
                if (!(old instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                plus = CollectionsKt.plus((Collection) old, (Iterable) CollectionsKt.listOf(set));
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, old, plus));
    }

    private final Set<Object> removeChanges() {
        Object old;
        Set result;
        Object obj;
        do {
            old = this.pendingChanges.get();
            Object obj2 = null;
            if (old == null) {
                return null;
            }
            if (old instanceof Set) {
                result = (Set) old;
                obj = null;
            } else if (old instanceof List) {
                result = (Set) ((List) old).get(0);
                if (((List) old).size() == 2) {
                    obj2 = ((List) old).get(1);
                } else if (((List) old).size() > 2) {
                    obj2 = ((List) old).subList(1, ((List) old).size());
                }
                obj = obj2;
            } else {
                report();
                throw new KotlinNothingValueException();
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, old, obj));
        return result;
    }

    private final Void report() {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    private final void forEachScopeMap(Function1<? super ObservedScopeMap, Unit> block) {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector this_$iv = this.observedScopeMaps;
                int size$iv = this_$iv.getSize();
                if (size$iv > 0) {
                    int i$iv = 0;
                    Object[] content$iv = this_$iv.getContent();
                    do {
                        block.invoke(content$iv[i$iv]);
                        i$iv++;
                    } while (i$iv < size$iv);
                }
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
    }

    private final void removeScopeMapIf(Function1<? super ObservedScopeMap, Boolean> block) {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector this_$iv = this.observedScopeMaps;
                int gap$iv = 0;
                int size$iv = this_$iv.getSize();
                for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                    if (block.invoke(this_$iv.getContent()[i$iv]).booleanValue()) {
                        gap$iv++;
                    } else if (gap$iv > 0) {
                        this_$iv.getContent()[i$iv - gap$iv] = this_$iv.getContent()[i$iv];
                    }
                }
                ArraysKt.fill(this_$iv.getContent(), (Object) null, size$iv - gap$iv, size$iv);
                this_$iv.setSize(size$iv - gap$iv);
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
    }

    public final <T> void observeReads(T scope, Function1<? super T, Unit> onValueChangedForScope, Function0<Unit> block) {
        ObservedScopeMap scopeMap;
        synchronized (this.observedScopeMaps) {
            scopeMap = ensureMap(onValueChangedForScope);
        }
        boolean oldPaused = this.isPaused;
        ObservedScopeMap oldMap = this.currentMap;
        long oldThreadId = this.currentMapThreadId;
        if (oldThreadId != -1) {
            if (!(oldThreadId == ActualJvm_jvmKt.currentThreadId())) {
                throw new IllegalArgumentException(("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + oldThreadId + "), currentThread={id=" + ActualJvm_jvmKt.currentThreadId() + ", name=" + ActualJvm_jvmKt.currentThreadName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.").toString());
            }
        }
        try {
            this.isPaused = false;
            this.currentMap = scopeMap;
            this.currentMapThreadId = Thread.currentThread().getId();
            scopeMap.observe(scope, this.readObserver, block);
        } finally {
            this.currentMap = oldMap;
            this.isPaused = oldPaused;
            this.currentMapThreadId = oldThreadId;
        }
    }

    @Deprecated(message = "Replace with Snapshot.withoutReadObservation()", replaceWith = @ReplaceWith(expression = "Snapshot.withoutReadObservation(block)", imports = {"androidx.compose.runtime.snapshots.Snapshot"}))
    public final void withNoObservations(Function0<Unit> block) {
        boolean oldPaused = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = oldPaused;
        }
    }

    public final void clear(Object scope) {
        synchronized (this.observedScopeMaps) {
            MutableVector this_$iv$iv = this.observedScopeMaps;
            int gap$iv$iv = 0;
            int size$iv$iv = this_$iv$iv.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap it = this_$iv$iv.getContent()[i$iv$iv];
                it.clearScopeObservations(scope);
                if (!it.hasScopeObservations()) {
                    gap$iv$iv++;
                } else if (gap$iv$iv > 0) {
                    this_$iv$iv.getContent()[i$iv$iv - gap$iv$iv] = this_$iv$iv.getContent()[i$iv$iv];
                }
            }
            ArraysKt.fill(this_$iv$iv.getContent(), (Object) null, size$iv$iv - gap$iv$iv, size$iv$iv);
            this_$iv$iv.setSize(size$iv$iv - gap$iv$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearIf(Function1<Object, Boolean> predicate) {
        synchronized (this.observedScopeMaps) {
            MutableVector this_$iv$iv = this.observedScopeMaps;
            int gap$iv$iv = 0;
            int size$iv$iv = this_$iv$iv.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap scopeMap = this_$iv$iv.getContent()[i$iv$iv];
                scopeMap.removeScopeIf(predicate);
                if (!scopeMap.hasScopeObservations()) {
                    gap$iv$iv++;
                } else if (gap$iv$iv > 0) {
                    this_$iv$iv.getContent()[i$iv$iv - gap$iv$iv] = this_$iv$iv.getContent()[i$iv$iv];
                }
            }
            ArraysKt.fill(this_$iv$iv.getContent(), (Object) null, size$iv$iv - gap$iv$iv, size$iv$iv);
            this_$iv$iv.setSize(size$iv$iv - gap$iv$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        this.applyObserver.invoke(changes, snapshot);
    }

    public final void clear() {
        synchronized (this.observedScopeMaps) {
            MutableVector this_$iv$iv = this.observedScopeMaps;
            int size$iv$iv = this_$iv$iv.getSize();
            if (size$iv$iv > 0) {
                int i$iv$iv = 0;
                Object[] content$iv$iv = this_$iv$iv.getContent();
                do {
                    ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv$iv[i$iv$iv];
                    scopeMap.clear();
                    i$iv$iv++;
                } while (i$iv$iv < size$iv$iv);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final <T> ObservedScopeMap ensureMap(Function1<? super T, Unit> onChanged) {
        Object item$iv;
        MutableVector this_$iv = this.observedScopeMaps;
        int size$iv = this_$iv.getSize();
        if (size$iv > 0) {
            int i$iv = 0;
            Object[] content$iv = this_$iv.getContent();
            do {
                item$iv = content$iv[i$iv];
                ObservedScopeMap it = (ObservedScopeMap) item$iv;
                if (it.getOnChanged() == onChanged) {
                    break;
                }
                i$iv++;
            } while (i$iv < size$iv);
        }
        item$iv = null;
        ObservedScopeMap scopeMap = (ObservedScopeMap) item$iv;
        if (scopeMap == null) {
            Intrinsics.checkNotNull(onChanged, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>");
            ObservedScopeMap map = new ObservedScopeMap((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(onChanged, 1));
            this.observedScopeMaps.add(map);
            return map;
        }
        return scopeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SnapshotStateObserver.kt */
    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u001f\u001a\u00020\u0004J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0001H\u0002J\u000e\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0001J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020\u0004J0\u0010&\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00012\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040)J\u0014\u0010*\u001a\u00020$2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010,J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0001J.\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00012\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0002J\u0018\u00100\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u0001H\u0002J)\u00101\u001a\u00020\u00042!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020$0\u0003J\u0012\u00105\u001a\u00020\u00042\n\u00106\u001a\u0006\u0012\u0002\b\u00030\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R6\u0010\u0017\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018j\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "onChanged", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "currentScope", "currentScopeReads", "Landroidx/collection/MutableObjectIntMap;", "currentToken", "", "dependencyToDerivedStates", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/DerivedState;", "deriveStateScopeCount", "derivedStateObserver", "Landroidx/compose/runtime/DerivedStateObserver;", "getDerivedStateObserver", "()Landroidx/compose/runtime/DerivedStateObserver;", "invalidated", "Landroidx/collection/MutableScatterSet;", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "recordedDerivedStateValues", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "scopeToValues", "Landroidx/collection/MutableScatterMap;", "statesToReread", "Landroidx/compose/runtime/collection/MutableVector;", "valueToScopes", "clear", "clearObsoleteStateReads", "scope", "clearScopeObservations", "hasScopeObservations", "", "notifyInvalidatedScopes", "observe", "readObserver", "block", "Lkotlin/Function0;", "recordInvalidation", "changes", "", "recordRead", "value", "recordedValues", "removeObservation", "removeScopeIf", "predicate", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "rereadDerivedState", "derivedState", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class ObservedScopeMap {
        private Object currentScope;
        private MutableObjectIntMap<Object> currentScopeReads;
        private int deriveStateScopeCount;
        private final MutableScatterSet<Object> invalidated;
        private final Function1<Object, Unit> onChanged;
        private final MutableScatterMap<Object, MutableObjectIntMap<Object>> scopeToValues;
        private int currentToken = -1;
        private final ScopeMap<Object> valueToScopes = new ScopeMap<>();
        private final MutableVector<DerivedState<?>> statesToReread = new MutableVector<>(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                int i;
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = SnapshotStateObserver.ObservedScopeMap.this;
                i = observedScopeMap.deriveStateScopeCount;
                observedScopeMap.deriveStateScopeCount = i + 1;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                int i;
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = SnapshotStateObserver.ObservedScopeMap.this;
                i = observedScopeMap.deriveStateScopeCount;
                observedScopeMap.deriveStateScopeCount = i - 1;
            }
        };
        private final ScopeMap<DerivedState<?>> dependencyToDerivedStates = new ScopeMap<>();
        private final HashMap<DerivedState<?>, Object> recordedDerivedStateValues = new HashMap<>();

        public ObservedScopeMap(Function1<Object, Unit> function1) {
            this.onChanged = function1;
            int i = 0;
            int i2 = 1;
            DefaultConstructorMarker defaultConstructorMarker = null;
            this.scopeToValues = new MutableScatterMap<>(i, i2, defaultConstructorMarker);
            this.invalidated = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        }

        public final Function1<Object, Unit> getOnChanged() {
            return this.onChanged;
        }

        public final DerivedStateObserver getDerivedStateObserver() {
            return this.derivedStateObserver;
        }

        public final void recordRead(Object value) {
            Object scope = this.currentScope;
            Intrinsics.checkNotNull(scope);
            int i = this.currentToken;
            MutableObjectIntMap it = this.currentScopeReads;
            if (it == null) {
                it = new MutableObjectIntMap(0, 1, null);
                this.currentScopeReads = it;
                this.scopeToValues.set(scope, it);
                Unit unit = Unit.INSTANCE;
            }
            recordRead(value, i, scope, it);
        }

        private final void recordRead(Object value, int currentToken, Object currentScope, MutableObjectIntMap<Object> recordedValues) {
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            int previousToken = recordedValues.put(value, currentToken, -1);
            if ((value instanceof DerivedState) && previousToken != currentToken) {
                DerivedState.Record record = ((DerivedState) value).getCurrentRecord();
                this.recordedDerivedStateValues.put(value, record.getCurrentValue());
                ObjectIntMap dependencies = record.getDependencies();
                ScopeMap dependencyToDerivedStates = this.dependencyToDerivedStates;
                dependencyToDerivedStates.removeScope(value);
                ObjectIntMap this_$iv = dependencies;
                int $i$f$forEachKey = 0;
                Object[] k$iv = this_$iv.keys;
                ObjectIntMap this_$iv$iv = this_$iv;
                int $i$f$forEachIndexed = 0;
                long[] m$iv$iv = this_$iv$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                        ObjectIntMap this_$iv2 = this_$iv;
                        int $i$f$forEachKey2 = $i$f$forEachKey;
                        ObjectIntMap this_$iv$iv2 = this_$iv$iv;
                        int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                        long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                        if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                            int i = 8;
                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                            int j$iv$iv = 0;
                            while (j$iv$iv < bitCount$iv$iv) {
                                long value$iv$iv$iv = slot$iv$iv & 255;
                                if (value$iv$iv$iv < 128) {
                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                    StateObject dependency = (StateObject) k$iv[index$iv$iv];
                                    if (dependency instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                        ((StateObjectImpl) dependency).m3044recordReadInh_f27i8$runtime_release(ReaderKind.m3031constructorimpl(2));
                                    }
                                    dependencyToDerivedStates.add(dependency, value);
                                }
                                slot$iv$iv >>= 8;
                                j$iv$iv++;
                                i = 8;
                            }
                            if (bitCount$iv$iv != i) {
                                break;
                            }
                        }
                        if (i$iv$iv == lastIndex$iv$iv) {
                            break;
                        }
                        i$iv$iv++;
                        this_$iv$iv = this_$iv$iv2;
                        $i$f$forEachIndexed = $i$f$forEachIndexed2;
                        this_$iv = this_$iv2;
                        $i$f$forEachKey = $i$f$forEachKey2;
                    }
                }
            }
            if (previousToken == -1) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m3044recordReadInh_f27i8$runtime_release(ReaderKind.m3031constructorimpl(2));
                }
                this.valueToScopes.add(value, currentScope);
            }
        }

        public final void observe(Object scope, Function1<Object, Unit> readObserver, Function0<Unit> block) {
            Object previousScope = this.currentScope;
            MutableObjectIntMap previousReads = this.currentScopeReads;
            int previousToken = this.currentToken;
            this.currentScope = scope;
            this.currentScopeReads = this.scopeToValues.get(scope);
            if (this.currentToken == -1) {
                this.currentToken = SnapshotKt.currentSnapshot().getId();
            }
            DerivedStateObserver observer$iv = this.derivedStateObserver;
            MutableVector observers$iv = SnapshotStateKt.derivedStateObservers();
            try {
                observers$iv.add(observer$iv);
                Snapshot.INSTANCE.observe(readObserver, null, block);
                observers$iv.removeAt(observers$iv.getSize() - 1);
                Object obj = this.currentScope;
                Intrinsics.checkNotNull(obj);
                clearObsoleteStateReads(obj);
                this.currentScope = previousScope;
                this.currentScopeReads = previousReads;
                this.currentToken = previousToken;
            } catch (Throwable th) {
                observers$iv.removeAt(observers$iv.getSize() - 1);
                throw th;
            }
        }

        private final void clearObsoleteStateReads(Object scope) {
            int currentToken;
            int $i$f$removeIf;
            int currentToken2;
            int $i$f$removeIf2;
            int currentToken3 = this.currentToken;
            MutableObjectIntMap this_$iv = this.currentScopeReads;
            if (this_$iv == null) {
                return;
            }
            int $i$f$removeIf3 = 0;
            MutableObjectIntMap this_$iv$iv = this_$iv;
            long[] m$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    currentToken = currentToken3;
                    $i$f$removeIf = $i$f$removeIf3;
                } else {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            currentToken2 = currentToken3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            Object value = this_$iv.keys[index$iv$iv];
                            $i$f$removeIf2 = $i$f$removeIf3;
                            int token = this_$iv.values[index$iv$iv];
                            boolean z = token != currentToken3;
                            boolean willRemove = z;
                            if (!willRemove) {
                                currentToken2 = currentToken3;
                            } else {
                                currentToken2 = currentToken3;
                                removeObservation(scope, value);
                            }
                            if (z) {
                                this_$iv.removeValueAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv >>= 8;
                        j$iv$iv++;
                        i = 8;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        currentToken3 = currentToken2;
                    }
                    currentToken = currentToken3;
                    $i$f$removeIf = $i$f$removeIf3;
                    int $i$f$removeIf4 = i;
                    if (bitCount$iv$iv != $i$f$removeIf4) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                $i$f$removeIf3 = $i$f$removeIf;
                currentToken3 = currentToken;
            }
        }

        public final void clearScopeObservations(Object scope) {
            MutableObjectIntMap recordedValues = this.scopeToValues.remove(scope);
            if (recordedValues == null) {
                return;
            }
            MutableObjectIntMap this_$iv = recordedValues;
            Object[] k$iv = this_$iv.keys;
            int[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                MutableObjectIntMap recordedValues2 = recordedValues;
                ObjectIntMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            Object value = k$iv[index$iv$iv];
                            int i2 = v$iv[index$iv$iv];
                            removeObservation(scope, value);
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
                recordedValues = recordedValues2;
                this_$iv = this_$iv2;
            }
        }

        public final void removeScopeIf(Function1<Object, Boolean> predicate) {
            int $i$f$removeIf;
            ScatterMap this_$iv$iv;
            int $i$f$forEachIndexed;
            long[] m$iv$iv;
            int lastIndex$iv$iv;
            int i$iv$iv;
            int $i$f$removeIf2;
            ScatterMap this_$iv$iv2;
            int $i$f$forEachIndexed2;
            long[] m$iv$iv2;
            int lastIndex$iv$iv2;
            int i$iv$iv2;
            long slot$iv$iv;
            char c;
            MutableScatterMap this_$iv = this.scopeToValues;
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
                long slot$iv$iv2 = m$iv$iv3[i$iv$iv3];
                char c2 = 7;
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv3 - lastIndex$iv$iv3)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv2 & 255;
                        if (!(value$iv$iv$iv < 128)) {
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv2 = m$iv$iv3;
                            lastIndex$iv$iv2 = lastIndex$iv$iv3;
                            i$iv$iv2 = i$iv$iv3;
                            slot$iv$iv = slot$iv$iv2;
                            c = c2;
                        } else {
                            int index$iv$iv = (i$iv$iv3 << 3) + j$iv$iv;
                            Object scope = this_$iv.keys[index$iv$iv];
                            ObjectIntMap valueSet = (MutableObjectIntMap) this_$iv.values[index$iv$iv];
                            Boolean invoke = predicate.invoke(scope);
                            boolean willRemove = invoke.booleanValue();
                            if (!willRemove) {
                                $i$f$removeIf2 = $i$f$removeIf3;
                                this_$iv$iv2 = this_$iv$iv3;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv2 = m$iv$iv3;
                                lastIndex$iv$iv2 = lastIndex$iv$iv3;
                                i$iv$iv2 = i$iv$iv3;
                                slot$iv$iv = slot$iv$iv2;
                                c = 7;
                            } else {
                                $i$f$removeIf2 = $i$f$removeIf3;
                                ObjectIntMap this_$iv2 = valueSet;
                                this_$iv$iv2 = this_$iv$iv3;
                                Object[] k$iv = this_$iv2.keys;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                int[] v$iv = this_$iv2.values;
                                m$iv$iv2 = m$iv$iv3;
                                long[] m$iv$iv4 = this_$iv2.metadata;
                                int lastIndex$iv$iv4 = m$iv$iv4.length - 2;
                                int i$iv$iv4 = 0;
                                if (0 <= lastIndex$iv$iv4) {
                                    while (true) {
                                        long slot$iv$iv3 = m$iv$iv4[i$iv$iv4];
                                        long[] m$iv$iv5 = m$iv$iv4;
                                        lastIndex$iv$iv2 = lastIndex$iv$iv3;
                                        slot$iv$iv = slot$iv$iv2;
                                        i$iv$iv2 = i$iv$iv3;
                                        c = 7;
                                        long $this$maskEmptyOrDeleted$iv$iv$iv2 = ((~slot$iv$iv3) << 7) & slot$iv$iv3 & (-9187201950435737472L);
                                        if ($this$maskEmptyOrDeleted$iv$iv$iv2 != -9187201950435737472L) {
                                            int bitCount$iv$iv2 = 8 - ((~(i$iv$iv4 - lastIndex$iv$iv4)) >>> 31);
                                            for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                                                long value$iv$iv$iv2 = slot$iv$iv3 & 255;
                                                if (value$iv$iv$iv2 < 128) {
                                                    int index$iv$iv2 = (i$iv$iv4 << 3) + j$iv$iv2;
                                                    Object value = k$iv[index$iv$iv2];
                                                    int i2 = v$iv[index$iv$iv2];
                                                    removeObservation(scope, value);
                                                }
                                                slot$iv$iv3 >>= 8;
                                            }
                                            if (bitCount$iv$iv2 != 8) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv4 == lastIndex$iv$iv4) {
                                            break;
                                        }
                                        i$iv$iv4++;
                                        i$iv$iv3 = i$iv$iv2;
                                        lastIndex$iv$iv3 = lastIndex$iv$iv2;
                                        m$iv$iv4 = m$iv$iv5;
                                        slot$iv$iv2 = slot$iv$iv;
                                    }
                                } else {
                                    lastIndex$iv$iv2 = lastIndex$iv$iv3;
                                    i$iv$iv2 = i$iv$iv3;
                                    slot$iv$iv = slot$iv$iv2;
                                    c = 7;
                                }
                            }
                            if (invoke.booleanValue()) {
                                this_$iv.removeValueAt(index$iv$iv);
                            }
                        }
                        j$iv$iv++;
                        i = 8;
                        c2 = c;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        m$iv$iv3 = m$iv$iv2;
                        i$iv$iv3 = i$iv$iv2;
                        lastIndex$iv$iv3 = lastIndex$iv$iv2;
                        slot$iv$iv2 = slot$iv$iv >> 8;
                        this_$iv$iv3 = this_$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    }
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv = m$iv$iv3;
                    lastIndex$iv$iv = lastIndex$iv$iv3;
                    i$iv$iv = i$iv$iv3;
                    int $i$f$removeIf4 = i;
                    if (bitCount$iv$iv != $i$f$removeIf4) {
                        return;
                    }
                } else {
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv = m$iv$iv3;
                    lastIndex$iv$iv = lastIndex$iv$iv3;
                    i$iv$iv = i$iv$iv3;
                }
                int i$iv$iv5 = i$iv$iv;
                lastIndex$iv$iv3 = lastIndex$iv$iv;
                if (i$iv$iv5 == lastIndex$iv$iv3) {
                    return;
                }
                i$iv$iv3 = i$iv$iv5 + 1;
                $i$f$removeIf3 = $i$f$removeIf;
                this_$iv$iv3 = this_$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv3 = m$iv$iv;
            }
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.isNotEmpty();
        }

        private final void removeObservation(Object scope, Object value) {
            this.valueToScopes.remove(value, scope);
            if ((value instanceof DerivedState) && !this.valueToScopes.contains(value)) {
                this.dependencyToDerivedStates.removeScope(value);
                this.recordedDerivedStateValues.remove(value);
            }
        }

        public final void clear() {
            this.valueToScopes.clear();
            this.scopeToValues.clear();
            this.dependencyToDerivedStates.clear();
            this.recordedDerivedStateValues.clear();
        }

        public final boolean recordInvalidation(Set<? extends Object> changes) {
            Iterable $this$forEach$iv$iv;
            int $i$f$forEach;
            HashMap recordedDerivedStateValues;
            Iterator it;
            Object value;
            HashMap recordedDerivedStateValues2;
            Object[] k$iv$iv;
            long[] m$iv$iv$iv;
            Object element$iv$iv;
            Object value$iv;
            ScatterSet this_$iv$iv;
            Object[] k$iv$iv2;
            long[] m$iv$iv$iv2;
            Object element$iv$iv2;
            Object value2;
            int bitCount$iv$iv$iv;
            Object value$iv2;
            ScatterSet this_$iv$iv2;
            ScopeMap dependencyToDerivedStates;
            ScopeMap valueToScopes;
            Set $this$fastForEach$iv;
            int $i$f$fastForEach;
            IdentityArraySet this_$iv$iv3;
            String str;
            int i;
            int i$iv$iv;
            Object value3;
            HashMap recordedDerivedStateValues3;
            int $i$f$forEachScopeOf;
            int $i$f$forEachScopeOf2;
            long[] m$iv$iv$iv3;
            long[] m$iv$iv$iv4;
            HashMap recordedDerivedStateValues4;
            int lastIndex$iv$iv$iv;
            Object[] k$iv$iv3;
            long[] m$iv$iv$iv5;
            ScopeMap dependencyToDerivedStates2;
            HashMap recordedDerivedStateValues5;
            ScopeMap valueToScopes2;
            Set $this$fastForEach$iv2;
            int lastIndex$iv$iv$iv2;
            Object[] k$iv$iv4;
            String str2;
            long[] m$iv$iv$iv6;
            boolean hasValues = false;
            ScopeMap dependencyToDerivedStates3 = this.dependencyToDerivedStates;
            HashMap recordedDerivedStateValues6 = this.recordedDerivedStateValues;
            ScopeMap valueToScopes3 = this.valueToScopes;
            MutableScatterSet invalidated = this.invalidated;
            Set $this$fastForEach$iv3 = changes;
            int $i$f$fastForEach2 = 0;
            String str3 = "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>";
            if ($this$fastForEach$iv3 instanceof IdentityArraySet) {
                IdentityArraySet this_$iv$iv4 = (IdentityArraySet) $this$fastForEach$iv3;
                Object[] values$iv$iv = this_$iv$iv4.getValues();
                int size = this_$iv$iv4.size();
                int i$iv$iv2 = 0;
                while (i$iv$iv2 < size) {
                    Object value4 = values$iv$iv[i$iv$iv2];
                    Intrinsics.checkNotNull(value4, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                    int i2 = 0;
                    if (value4 instanceof StateObjectImpl) {
                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                        if (!((StateObjectImpl) value4).m3043isReadInh_f27i8$runtime_release(ReaderKind.m3031constructorimpl(2))) {
                            dependencyToDerivedStates = dependencyToDerivedStates3;
                            valueToScopes = valueToScopes3;
                            $this$fastForEach$iv = $this$fastForEach$iv3;
                            $i$f$fastForEach = $i$f$fastForEach2;
                            this_$iv$iv3 = this_$iv$iv4;
                            str = str3;
                            i = size;
                            i$iv$iv = i$iv$iv2;
                            recordedDerivedStateValues3 = recordedDerivedStateValues6;
                            i$iv$iv2 = i$iv$iv + 1;
                            recordedDerivedStateValues6 = recordedDerivedStateValues3;
                            $i$f$fastForEach2 = $i$f$fastForEach;
                            this_$iv$iv4 = this_$iv$iv3;
                            size = i;
                            str3 = str;
                            dependencyToDerivedStates3 = dependencyToDerivedStates;
                            $this$fastForEach$iv3 = $this$fastForEach$iv;
                            valueToScopes3 = valueToScopes;
                        }
                    }
                    if (dependencyToDerivedStates3.contains(value4)) {
                        int $i$f$forEachScopeOf3 = 0;
                        boolean hasValues2 = hasValues;
                        Object value$iv3 = dependencyToDerivedStates3.getMap().get(value4);
                        if (value$iv3 != null) {
                            $i$f$fastForEach = $i$f$fastForEach2;
                            if (value$iv3 instanceof MutableScatterSet) {
                                ScatterSet this_$iv$iv5 = (MutableScatterSet) value$iv3;
                                this_$iv$iv3 = this_$iv$iv4;
                                Object[] k$iv$iv5 = this_$iv$iv5.elements;
                                long[] m$iv$iv$iv7 = this_$iv$iv5.metadata;
                                int lastIndex$iv$iv$iv3 = m$iv$iv$iv7.length - 2;
                                i = size;
                                int i$iv$iv$iv = 0;
                                if (0 <= lastIndex$iv$iv$iv3) {
                                    while (true) {
                                        long slot$iv$iv$iv = m$iv$iv$iv7[i$iv$iv$iv];
                                        int i3 = i2;
                                        int $i$f$forEachScopeOf4 = $i$f$forEachScopeOf3;
                                        i$iv$iv = i$iv$iv2;
                                        value3 = value4;
                                        if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv3)) >>> 31);
                                            int j$iv$iv$iv = 0;
                                            while (j$iv$iv$iv < bitCount$iv$iv$iv2) {
                                                long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                                int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                                                if ($i$f$isFull != 0) {
                                                    int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                    k$iv$iv4 = k$iv$iv5;
                                                    DerivedState derivedState = (DerivedState) k$iv$iv5[index$iv$iv$iv];
                                                    Intrinsics.checkNotNull(derivedState, str3);
                                                    m$iv$iv$iv6 = m$iv$iv$iv7;
                                                    Object previousValue = recordedDerivedStateValues6.get(derivedState);
                                                    SnapshotMutationPolicy policy = derivedState.getPolicy();
                                                    if (policy == null) {
                                                        policy = SnapshotStateKt.structuralEqualityPolicy();
                                                    }
                                                    if (policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), previousValue)) {
                                                        dependencyToDerivedStates2 = dependencyToDerivedStates3;
                                                        recordedDerivedStateValues5 = recordedDerivedStateValues6;
                                                        valueToScopes2 = valueToScopes3;
                                                        $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                        lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                                        str2 = str3;
                                                        this.statesToReread.add(derivedState);
                                                    } else {
                                                        Object value$iv4 = valueToScopes3.getMap().get(derivedState);
                                                        if (value$iv4 == null) {
                                                            dependencyToDerivedStates2 = dependencyToDerivedStates3;
                                                            recordedDerivedStateValues5 = recordedDerivedStateValues6;
                                                            valueToScopes2 = valueToScopes3;
                                                            $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                            lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                                            str2 = str3;
                                                        } else if (value$iv4 instanceof MutableScatterSet) {
                                                            ScatterSet this_$iv$iv6 = (MutableScatterSet) value$iv4;
                                                            Object[] k$iv$iv6 = this_$iv$iv6.elements;
                                                            str2 = str3;
                                                            long[] m$iv$iv$iv8 = this_$iv$iv6.metadata;
                                                            int lastIndex$iv$iv$iv4 = m$iv$iv$iv8.length - 2;
                                                            dependencyToDerivedStates2 = dependencyToDerivedStates3;
                                                            int i$iv$iv$iv2 = 0;
                                                            if (0 <= lastIndex$iv$iv$iv4) {
                                                                while (true) {
                                                                    long slot$iv$iv$iv2 = m$iv$iv$iv8[i$iv$iv$iv2];
                                                                    recordedDerivedStateValues5 = recordedDerivedStateValues6;
                                                                    valueToScopes2 = valueToScopes3;
                                                                    $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                                    lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L);
                                                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                                                                        int bitCount$iv$iv$iv3 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv4)) >>> 31);
                                                                        for (int j$iv$iv$iv2 = 0; j$iv$iv$iv2 < bitCount$iv$iv$iv3; j$iv$iv$iv2++) {
                                                                            long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                                            int $i$f$isFull2 = value$iv$iv$iv$iv2 < 128 ? 1 : 0;
                                                                            if ($i$f$isFull2 != 0) {
                                                                                int index$iv$iv$iv2 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv2;
                                                                                Object scope = k$iv$iv6[index$iv$iv$iv2];
                                                                                invalidated.add(scope);
                                                                                hasValues2 = true;
                                                                            }
                                                                            slot$iv$iv$iv2 >>= 8;
                                                                        }
                                                                        if (bitCount$iv$iv$iv3 != 8) {
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (i$iv$iv$iv2 != lastIndex$iv$iv$iv4) {
                                                                        i$iv$iv$iv2++;
                                                                        $this$fastForEach$iv3 = $this$fastForEach$iv2;
                                                                        lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv2;
                                                                        recordedDerivedStateValues6 = recordedDerivedStateValues5;
                                                                        valueToScopes3 = valueToScopes2;
                                                                    }
                                                                }
                                                            } else {
                                                                recordedDerivedStateValues5 = recordedDerivedStateValues6;
                                                                valueToScopes2 = valueToScopes3;
                                                                $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                                lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                                            }
                                                        } else {
                                                            dependencyToDerivedStates2 = dependencyToDerivedStates3;
                                                            recordedDerivedStateValues5 = recordedDerivedStateValues6;
                                                            valueToScopes2 = valueToScopes3;
                                                            $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                            lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                                            str2 = str3;
                                                            invalidated.add(value$iv4);
                                                            hasValues2 = true;
                                                        }
                                                    }
                                                } else {
                                                    dependencyToDerivedStates2 = dependencyToDerivedStates3;
                                                    recordedDerivedStateValues5 = recordedDerivedStateValues6;
                                                    valueToScopes2 = valueToScopes3;
                                                    $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                    lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                                    k$iv$iv4 = k$iv$iv5;
                                                    str2 = str3;
                                                    m$iv$iv$iv6 = m$iv$iv$iv7;
                                                }
                                                slot$iv$iv$iv >>= 8;
                                                j$iv$iv$iv++;
                                                k$iv$iv5 = k$iv$iv4;
                                                m$iv$iv$iv7 = m$iv$iv$iv6;
                                                str3 = str2;
                                                dependencyToDerivedStates3 = dependencyToDerivedStates2;
                                                $this$fastForEach$iv3 = $this$fastForEach$iv2;
                                                lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv2;
                                                recordedDerivedStateValues6 = recordedDerivedStateValues5;
                                                valueToScopes3 = valueToScopes2;
                                            }
                                            dependencyToDerivedStates = dependencyToDerivedStates3;
                                            recordedDerivedStateValues4 = recordedDerivedStateValues6;
                                            valueToScopes = valueToScopes3;
                                            $this$fastForEach$iv = $this$fastForEach$iv3;
                                            lastIndex$iv$iv$iv = lastIndex$iv$iv$iv3;
                                            k$iv$iv3 = k$iv$iv5;
                                            str = str3;
                                            m$iv$iv$iv5 = m$iv$iv$iv7;
                                            if (bitCount$iv$iv$iv2 != 8) {
                                                break;
                                            }
                                        } else {
                                            dependencyToDerivedStates = dependencyToDerivedStates3;
                                            recordedDerivedStateValues4 = recordedDerivedStateValues6;
                                            valueToScopes = valueToScopes3;
                                            $this$fastForEach$iv = $this$fastForEach$iv3;
                                            lastIndex$iv$iv$iv = lastIndex$iv$iv$iv3;
                                            k$iv$iv3 = k$iv$iv5;
                                            str = str3;
                                            m$iv$iv$iv5 = m$iv$iv$iv7;
                                        }
                                        lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv;
                                        if (i$iv$iv$iv == lastIndex$iv$iv$iv3) {
                                            break;
                                        }
                                        i$iv$iv$iv++;
                                        i$iv$iv2 = i$iv$iv;
                                        value4 = value3;
                                        i2 = i3;
                                        $i$f$forEachScopeOf3 = $i$f$forEachScopeOf4;
                                        k$iv$iv5 = k$iv$iv3;
                                        m$iv$iv$iv7 = m$iv$iv$iv5;
                                        str3 = str;
                                        dependencyToDerivedStates3 = dependencyToDerivedStates;
                                        $this$fastForEach$iv3 = $this$fastForEach$iv;
                                        recordedDerivedStateValues6 = recordedDerivedStateValues4;
                                        valueToScopes3 = valueToScopes;
                                    }
                                } else {
                                    dependencyToDerivedStates = dependencyToDerivedStates3;
                                    recordedDerivedStateValues4 = recordedDerivedStateValues6;
                                    valueToScopes = valueToScopes3;
                                    $this$fastForEach$iv = $this$fastForEach$iv3;
                                    str = str3;
                                    i$iv$iv = i$iv$iv2;
                                    value3 = value4;
                                }
                                hasValues = hasValues2;
                                recordedDerivedStateValues3 = recordedDerivedStateValues4;
                            } else {
                                dependencyToDerivedStates = dependencyToDerivedStates3;
                                valueToScopes = valueToScopes3;
                                $this$fastForEach$iv = $this$fastForEach$iv3;
                                this_$iv$iv3 = this_$iv$iv4;
                                str = str3;
                                i = size;
                                i$iv$iv = i$iv$iv2;
                                value3 = value4;
                                DerivedState derivedState2 = (DerivedState) value$iv3;
                                recordedDerivedStateValues3 = recordedDerivedStateValues6;
                                Object previousValue2 = recordedDerivedStateValues3.get(derivedState2);
                                SnapshotMutationPolicy policy2 = derivedState2.getPolicy();
                                if (policy2 == null) {
                                    policy2 = SnapshotStateKt.structuralEqualityPolicy();
                                }
                                if (policy2.equivalent(derivedState2.getCurrentRecord().getCurrentValue(), previousValue2)) {
                                    this.statesToReread.add(derivedState2);
                                    hasValues = hasValues2;
                                } else {
                                    ScopeMap this_$iv = valueToScopes;
                                    Object value$iv5 = this_$iv.getMap().get(derivedState2);
                                    if (value$iv5 == null) {
                                        hasValues = hasValues2;
                                    } else if (value$iv5 instanceof MutableScatterSet) {
                                        ScatterSet this_$iv$iv7 = (MutableScatterSet) value$iv5;
                                        int $i$f$forEach2 = 0;
                                        Object[] k$iv$iv7 = this_$iv$iv7.elements;
                                        long[] m$iv$iv$iv9 = this_$iv$iv7.metadata;
                                        int lastIndex$iv$iv$iv5 = m$iv$iv$iv9.length - 2;
                                        int i$iv$iv$iv3 = 0;
                                        if (0 <= lastIndex$iv$iv$iv5) {
                                            while (true) {
                                                long slot$iv$iv$iv3 = m$iv$iv$iv9[i$iv$iv$iv3];
                                                SnapshotMutationPolicy policy3 = policy2;
                                                ScopeMap this_$iv2 = this_$iv;
                                                ScatterSet this_$iv$iv8 = this_$iv$iv7;
                                                int $i$f$forEach3 = $i$f$forEach2;
                                                long $this$maskEmptyOrDeleted$iv$iv$iv$iv2 = ((~slot$iv$iv$iv3) << 7) & slot$iv$iv$iv3 & (-9187201950435737472L);
                                                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv2 != -9187201950435737472L) {
                                                    int bitCount$iv$iv$iv4 = 8 - ((~(i$iv$iv$iv3 - lastIndex$iv$iv$iv5)) >>> 31);
                                                    int j$iv$iv$iv3 = 0;
                                                    while (j$iv$iv$iv3 < bitCount$iv$iv$iv4) {
                                                        long value$iv$iv$iv$iv3 = slot$iv$iv$iv3 & 255;
                                                        int $i$f$isFull3 = value$iv$iv$iv$iv3 < 128 ? 1 : 0;
                                                        if ($i$f$isFull3 != 0) {
                                                            int index$iv$iv$iv3 = (i$iv$iv$iv3 << 3) + j$iv$iv$iv3;
                                                            m$iv$iv$iv4 = m$iv$iv$iv9;
                                                            Object scope2 = k$iv$iv7[index$iv$iv$iv3];
                                                            invalidated.add(scope2);
                                                            hasValues2 = true;
                                                        } else {
                                                            m$iv$iv$iv4 = m$iv$iv$iv9;
                                                        }
                                                        slot$iv$iv$iv3 >>= 8;
                                                        j$iv$iv$iv3++;
                                                        m$iv$iv$iv9 = m$iv$iv$iv4;
                                                    }
                                                    m$iv$iv$iv3 = m$iv$iv$iv9;
                                                    if (bitCount$iv$iv$iv4 != 8) {
                                                        break;
                                                    }
                                                } else {
                                                    m$iv$iv$iv3 = m$iv$iv$iv9;
                                                }
                                                if (i$iv$iv$iv3 == lastIndex$iv$iv$iv5) {
                                                    break;
                                                }
                                                i$iv$iv$iv3++;
                                                policy2 = policy3;
                                                this_$iv$iv7 = this_$iv$iv8;
                                                $i$f$forEach2 = $i$f$forEach3;
                                                this_$iv = this_$iv2;
                                                m$iv$iv$iv9 = m$iv$iv$iv3;
                                            }
                                        }
                                        hasValues2 = hasValues2;
                                        hasValues = hasValues2;
                                    } else {
                                        invalidated.add(value$iv5);
                                        hasValues = true;
                                    }
                                }
                            }
                        } else {
                            dependencyToDerivedStates = dependencyToDerivedStates3;
                            valueToScopes = valueToScopes3;
                            $this$fastForEach$iv = $this$fastForEach$iv3;
                            $i$f$fastForEach = $i$f$fastForEach2;
                            this_$iv$iv3 = this_$iv$iv4;
                            str = str3;
                            i = size;
                            i$iv$iv = i$iv$iv2;
                            value3 = value4;
                            recordedDerivedStateValues3 = recordedDerivedStateValues6;
                            hasValues = hasValues2;
                        }
                    } else {
                        dependencyToDerivedStates = dependencyToDerivedStates3;
                        valueToScopes = valueToScopes3;
                        $this$fastForEach$iv = $this$fastForEach$iv3;
                        $i$f$fastForEach = $i$f$fastForEach2;
                        this_$iv$iv3 = this_$iv$iv4;
                        str = str3;
                        i = size;
                        i$iv$iv = i$iv$iv2;
                        value3 = value4;
                        recordedDerivedStateValues3 = recordedDerivedStateValues6;
                    }
                    ScopeMap this_$iv3 = valueToScopes;
                    int $i$f$forEachScopeOf5 = 0;
                    Object value5 = value3;
                    Object value$iv6 = this_$iv3.getMap().get(value5);
                    if (value$iv6 != null) {
                        if (value$iv6 instanceof MutableScatterSet) {
                            ScatterSet this_$iv$iv9 = (MutableScatterSet) value$iv6;
                            Object[] k$iv$iv8 = this_$iv$iv9.elements;
                            long[] m$iv$iv$iv10 = this_$iv$iv9.metadata;
                            int lastIndex$iv$iv$iv6 = m$iv$iv$iv10.length - 2;
                            int i$iv$iv$iv4 = 0;
                            if (0 <= lastIndex$iv$iv$iv6) {
                                while (true) {
                                    long slot$iv$iv$iv4 = m$iv$iv$iv10[i$iv$iv$iv4];
                                    boolean hasValues3 = hasValues;
                                    ScopeMap this_$iv4 = this_$iv3;
                                    Object value6 = value5;
                                    ScatterSet this_$iv$iv10 = this_$iv$iv9;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv3 = ((~slot$iv$iv$iv4) << 7) & slot$iv$iv$iv4 & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv3 != -9187201950435737472L) {
                                        int bitCount$iv$iv$iv5 = 8 - ((~(i$iv$iv$iv4 - lastIndex$iv$iv$iv6)) >>> 31);
                                        int j$iv$iv$iv4 = 0;
                                        while (j$iv$iv$iv4 < bitCount$iv$iv$iv5) {
                                            long value$iv$iv$iv$iv4 = slot$iv$iv$iv4 & 255;
                                            int $i$f$isFull4 = value$iv$iv$iv$iv4 < 128 ? 1 : 0;
                                            if ($i$f$isFull4 != 0) {
                                                int index$iv$iv$iv4 = (i$iv$iv$iv4 << 3) + j$iv$iv$iv4;
                                                $i$f$forEachScopeOf2 = $i$f$forEachScopeOf5;
                                                Object scope3 = k$iv$iv8[index$iv$iv$iv4];
                                                invalidated.add(scope3);
                                                hasValues3 = true;
                                            } else {
                                                $i$f$forEachScopeOf2 = $i$f$forEachScopeOf5;
                                            }
                                            slot$iv$iv$iv4 >>= 8;
                                            j$iv$iv$iv4++;
                                            $i$f$forEachScopeOf5 = $i$f$forEachScopeOf2;
                                        }
                                        $i$f$forEachScopeOf = $i$f$forEachScopeOf5;
                                        if (bitCount$iv$iv$iv5 != 8) {
                                            hasValues = hasValues3;
                                            break;
                                        }
                                        hasValues = hasValues3;
                                    } else {
                                        $i$f$forEachScopeOf = $i$f$forEachScopeOf5;
                                        hasValues = hasValues3;
                                    }
                                    if (i$iv$iv$iv4 != lastIndex$iv$iv$iv6) {
                                        i$iv$iv$iv4++;
                                        this_$iv3 = this_$iv4;
                                        this_$iv$iv9 = this_$iv$iv10;
                                        $i$f$forEachScopeOf5 = $i$f$forEachScopeOf;
                                        value5 = value6;
                                    }
                                }
                            }
                        } else {
                            invalidated.add(value$iv6);
                            hasValues = true;
                        }
                    }
                    i$iv$iv2 = i$iv$iv + 1;
                    recordedDerivedStateValues6 = recordedDerivedStateValues3;
                    $i$f$fastForEach2 = $i$f$fastForEach;
                    this_$iv$iv4 = this_$iv$iv3;
                    size = i;
                    str3 = str;
                    dependencyToDerivedStates3 = dependencyToDerivedStates;
                    $this$fastForEach$iv3 = $this$fastForEach$iv;
                    valueToScopes3 = valueToScopes;
                }
            } else {
                ScopeMap dependencyToDerivedStates4 = dependencyToDerivedStates3;
                HashMap recordedDerivedStateValues7 = recordedDerivedStateValues6;
                Set $this$forEach$iv$iv2 = $this$fastForEach$iv3;
                int $i$f$forEach4 = 0;
                Iterator it2 = $this$forEach$iv$iv2.iterator();
                while (it2.hasNext()) {
                    Object element$iv$iv3 = it2.next();
                    Object value7 = element$iv$iv3;
                    int i4 = 0;
                    if (value7 instanceof StateObjectImpl) {
                        ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                        if (!((StateObjectImpl) value7).m3043isReadInh_f27i8$runtime_release(ReaderKind.m3031constructorimpl(2))) {
                            $this$forEach$iv$iv = $this$forEach$iv$iv2;
                            $i$f$forEach = $i$f$forEach4;
                            recordedDerivedStateValues = recordedDerivedStateValues7;
                            it = it2;
                            $i$f$forEach4 = $i$f$forEach;
                            $this$forEach$iv$iv2 = $this$forEach$iv$iv;
                            it2 = it;
                            recordedDerivedStateValues7 = recordedDerivedStateValues;
                        }
                    }
                    ScopeMap dependencyToDerivedStates5 = dependencyToDerivedStates4;
                    if (dependencyToDerivedStates5.contains(value7)) {
                        ScopeMap this_$iv5 = dependencyToDerivedStates5;
                        int $i$f$forEachScopeOf6 = 0;
                        Object value$iv7 = this_$iv5.getMap().get(value7);
                        if (value$iv7 == null) {
                            $this$forEach$iv$iv = $this$forEach$iv$iv2;
                            $i$f$forEach = $i$f$forEach4;
                            recordedDerivedStateValues = recordedDerivedStateValues7;
                            it = it2;
                            value = value7;
                            dependencyToDerivedStates4 = dependencyToDerivedStates5;
                        } else if (value$iv7 instanceof MutableScatterSet) {
                            ScatterSet this_$iv$iv11 = (MutableScatterSet) value$iv7;
                            boolean hasValues4 = hasValues;
                            Object[] k$iv$iv9 = this_$iv$iv11.elements;
                            $this$forEach$iv$iv = $this$forEach$iv$iv2;
                            $i$f$forEach = $i$f$forEach4;
                            long[] m$iv$iv$iv11 = this_$iv$iv11.metadata;
                            int lastIndex$iv$iv$iv7 = m$iv$iv$iv11.length - 2;
                            it = it2;
                            int i$iv$iv$iv5 = 0;
                            if (0 <= lastIndex$iv$iv$iv7) {
                                while (true) {
                                    long slot$iv$iv$iv5 = m$iv$iv$iv11[i$iv$iv$iv5];
                                    int i5 = i4;
                                    dependencyToDerivedStates4 = dependencyToDerivedStates5;
                                    ScopeMap this_$iv6 = this_$iv5;
                                    int $i$f$forEachScopeOf7 = $i$f$forEachScopeOf6;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv4 = ((~slot$iv$iv$iv5) << 7) & slot$iv$iv$iv5 & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv4 != -9187201950435737472L) {
                                        int bitCount$iv$iv$iv6 = 8 - ((~(i$iv$iv$iv5 - lastIndex$iv$iv$iv7)) >>> 31);
                                        int j$iv$iv$iv5 = 0;
                                        while (j$iv$iv$iv5 < bitCount$iv$iv$iv6) {
                                            long value$iv$iv$iv$iv5 = slot$iv$iv$iv5 & 255;
                                            int $i$f$isFull5 = value$iv$iv$iv$iv5 < 128 ? 1 : 0;
                                            if ($i$f$isFull5 != 0) {
                                                int index$iv$iv$iv5 = (i$iv$iv$iv5 << 3) + j$iv$iv$iv5;
                                                k$iv$iv2 = k$iv$iv9;
                                                DerivedState derivedState3 = (DerivedState) k$iv$iv9[index$iv$iv$iv5];
                                                m$iv$iv$iv2 = m$iv$iv$iv11;
                                                Intrinsics.checkNotNull(derivedState3, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
                                                Object previousValue3 = recordedDerivedStateValues7.get(derivedState3);
                                                SnapshotMutationPolicy policy4 = derivedState3.getPolicy();
                                                if (policy4 == null) {
                                                    policy4 = SnapshotStateKt.structuralEqualityPolicy();
                                                }
                                                element$iv$iv2 = element$iv$iv3;
                                                if (policy4.equivalent(derivedState3.getCurrentRecord().getCurrentValue(), previousValue3)) {
                                                    value2 = value7;
                                                    bitCount$iv$iv$iv = bitCount$iv$iv$iv6;
                                                    value$iv2 = value$iv7;
                                                    this_$iv$iv2 = this_$iv$iv11;
                                                    this.statesToReread.add(derivedState3);
                                                } else {
                                                    Object value$iv8 = valueToScopes3.getMap().get(derivedState3);
                                                    if (value$iv8 == null) {
                                                        value2 = value7;
                                                        bitCount$iv$iv$iv = bitCount$iv$iv$iv6;
                                                        value$iv2 = value$iv7;
                                                        this_$iv$iv2 = this_$iv$iv11;
                                                    } else if (value$iv8 instanceof MutableScatterSet) {
                                                        ScatterSet this_$iv$iv12 = (MutableScatterSet) value$iv8;
                                                        Object[] k$iv$iv10 = this_$iv$iv12.elements;
                                                        long[] m$iv$iv$iv12 = this_$iv$iv12.metadata;
                                                        int lastIndex$iv$iv$iv8 = m$iv$iv$iv12.length - 2;
                                                        this_$iv$iv2 = this_$iv$iv11;
                                                        int i$iv$iv$iv6 = 0;
                                                        if (0 <= lastIndex$iv$iv$iv8) {
                                                            while (true) {
                                                                long slot$iv$iv$iv6 = m$iv$iv$iv12[i$iv$iv$iv6];
                                                                long[] m$iv$iv$iv13 = m$iv$iv$iv12;
                                                                value$iv2 = value$iv7;
                                                                value2 = value7;
                                                                bitCount$iv$iv$iv = bitCount$iv$iv$iv6;
                                                                if ((((~slot$iv$iv$iv6) << 7) & slot$iv$iv$iv6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                    int bitCount$iv$iv$iv7 = 8 - ((~(i$iv$iv$iv6 - lastIndex$iv$iv$iv8)) >>> 31);
                                                                    for (int j$iv$iv$iv6 = 0; j$iv$iv$iv6 < bitCount$iv$iv$iv7; j$iv$iv$iv6++) {
                                                                        long value$iv$iv$iv$iv6 = slot$iv$iv$iv6 & 255;
                                                                        int $i$f$isFull6 = value$iv$iv$iv$iv6 < 128 ? 1 : 0;
                                                                        if ($i$f$isFull6 != 0) {
                                                                            int index$iv$iv$iv6 = (i$iv$iv$iv6 << 3) + j$iv$iv$iv6;
                                                                            Object scope4 = k$iv$iv10[index$iv$iv$iv6];
                                                                            invalidated.add(scope4);
                                                                            hasValues4 = true;
                                                                        }
                                                                        slot$iv$iv$iv6 >>= 8;
                                                                    }
                                                                    if (bitCount$iv$iv$iv7 != 8) {
                                                                        break;
                                                                    }
                                                                }
                                                                if (i$iv$iv$iv6 != lastIndex$iv$iv$iv8) {
                                                                    i$iv$iv$iv6++;
                                                                    value7 = value2;
                                                                    bitCount$iv$iv$iv6 = bitCount$iv$iv$iv;
                                                                    value$iv7 = value$iv2;
                                                                    m$iv$iv$iv12 = m$iv$iv$iv13;
                                                                }
                                                            }
                                                        } else {
                                                            value2 = value7;
                                                            bitCount$iv$iv$iv = bitCount$iv$iv$iv6;
                                                            value$iv2 = value$iv7;
                                                        }
                                                    } else {
                                                        value2 = value7;
                                                        bitCount$iv$iv$iv = bitCount$iv$iv$iv6;
                                                        value$iv2 = value$iv7;
                                                        this_$iv$iv2 = this_$iv$iv11;
                                                        invalidated.add(value$iv8);
                                                        hasValues4 = true;
                                                    }
                                                }
                                            } else {
                                                k$iv$iv2 = k$iv$iv9;
                                                m$iv$iv$iv2 = m$iv$iv$iv11;
                                                element$iv$iv2 = element$iv$iv3;
                                                value2 = value7;
                                                bitCount$iv$iv$iv = bitCount$iv$iv$iv6;
                                                value$iv2 = value$iv7;
                                                this_$iv$iv2 = this_$iv$iv11;
                                            }
                                            slot$iv$iv$iv5 >>= 8;
                                            j$iv$iv$iv5++;
                                            k$iv$iv9 = k$iv$iv2;
                                            m$iv$iv$iv11 = m$iv$iv$iv2;
                                            element$iv$iv3 = element$iv$iv2;
                                            this_$iv$iv11 = this_$iv$iv2;
                                            value7 = value2;
                                            bitCount$iv$iv$iv6 = bitCount$iv$iv$iv;
                                            value$iv7 = value$iv2;
                                        }
                                        k$iv$iv = k$iv$iv9;
                                        m$iv$iv$iv = m$iv$iv$iv11;
                                        element$iv$iv = element$iv$iv3;
                                        value = value7;
                                        value$iv = value$iv7;
                                        this_$iv$iv = this_$iv$iv11;
                                        if (bitCount$iv$iv$iv6 != 8) {
                                            break;
                                        }
                                    } else {
                                        k$iv$iv = k$iv$iv9;
                                        m$iv$iv$iv = m$iv$iv$iv11;
                                        element$iv$iv = element$iv$iv3;
                                        value = value7;
                                        value$iv = value$iv7;
                                        this_$iv$iv = this_$iv$iv11;
                                    }
                                    if (i$iv$iv$iv5 == lastIndex$iv$iv$iv7) {
                                        break;
                                    }
                                    i$iv$iv$iv5++;
                                    this_$iv5 = this_$iv6;
                                    $i$f$forEachScopeOf6 = $i$f$forEachScopeOf7;
                                    i4 = i5;
                                    k$iv$iv9 = k$iv$iv;
                                    m$iv$iv$iv11 = m$iv$iv$iv;
                                    element$iv$iv3 = element$iv$iv;
                                    this_$iv$iv11 = this_$iv$iv;
                                    dependencyToDerivedStates5 = dependencyToDerivedStates4;
                                    value7 = value;
                                    value$iv7 = value$iv;
                                }
                            } else {
                                value = value7;
                                dependencyToDerivedStates4 = dependencyToDerivedStates5;
                            }
                            hasValues4 = hasValues4;
                            recordedDerivedStateValues = recordedDerivedStateValues7;
                            hasValues = hasValues4;
                        } else {
                            boolean hasValues5 = hasValues;
                            $this$forEach$iv$iv = $this$forEach$iv$iv2;
                            $i$f$forEach = $i$f$forEach4;
                            it = it2;
                            value = value7;
                            dependencyToDerivedStates4 = dependencyToDerivedStates5;
                            DerivedState derivedState4 = (DerivedState) value$iv7;
                            Object previousValue4 = recordedDerivedStateValues7.get(derivedState4);
                            SnapshotMutationPolicy policy5 = derivedState4.getPolicy();
                            if (policy5 == null) {
                                policy5 = SnapshotStateKt.structuralEqualityPolicy();
                            }
                            if (policy5.equivalent(derivedState4.getCurrentRecord().getCurrentValue(), previousValue4)) {
                                recordedDerivedStateValues = recordedDerivedStateValues7;
                                this.statesToReread.add(derivedState4);
                            } else {
                                ScopeMap this_$iv7 = valueToScopes3;
                                Object value$iv9 = this_$iv7.getMap().get(derivedState4);
                                if (value$iv9 == null) {
                                    recordedDerivedStateValues = recordedDerivedStateValues7;
                                } else if (value$iv9 instanceof MutableScatterSet) {
                                    ScatterSet this_$iv$iv13 = (MutableScatterSet) value$iv9;
                                    int $i$f$forEach5 = 0;
                                    Object[] k$iv$iv11 = this_$iv$iv13.elements;
                                    long[] m$iv$iv$iv14 = this_$iv$iv13.metadata;
                                    int lastIndex$iv$iv$iv9 = m$iv$iv$iv14.length - 2;
                                    int i$iv$iv$iv7 = 0;
                                    if (0 <= lastIndex$iv$iv$iv9) {
                                        while (true) {
                                            long slot$iv$iv$iv7 = m$iv$iv$iv14[i$iv$iv$iv7];
                                            SnapshotMutationPolicy policy6 = policy5;
                                            ScopeMap this_$iv8 = this_$iv7;
                                            ScatterSet this_$iv$iv14 = this_$iv$iv13;
                                            int $i$f$forEach6 = $i$f$forEach5;
                                            long $this$maskEmptyOrDeleted$iv$iv$iv$iv5 = ((~slot$iv$iv$iv7) << 7) & slot$iv$iv$iv7 & (-9187201950435737472L);
                                            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv5 != -9187201950435737472L) {
                                                int bitCount$iv$iv$iv8 = 8 - ((~(i$iv$iv$iv7 - lastIndex$iv$iv$iv9)) >>> 31);
                                                int j$iv$iv$iv7 = 0;
                                                while (j$iv$iv$iv7 < bitCount$iv$iv$iv8) {
                                                    long value$iv$iv$iv$iv7 = slot$iv$iv$iv7 & 255;
                                                    int $i$f$isFull7 = value$iv$iv$iv$iv7 < 128 ? 1 : 0;
                                                    if ($i$f$isFull7 != 0) {
                                                        int index$iv$iv$iv7 = (i$iv$iv$iv7 << 3) + j$iv$iv$iv7;
                                                        recordedDerivedStateValues2 = recordedDerivedStateValues7;
                                                        Object scope5 = k$iv$iv11[index$iv$iv$iv7];
                                                        invalidated.add(scope5);
                                                        hasValues5 = true;
                                                    } else {
                                                        recordedDerivedStateValues2 = recordedDerivedStateValues7;
                                                    }
                                                    slot$iv$iv$iv7 >>= 8;
                                                    j$iv$iv$iv7++;
                                                    recordedDerivedStateValues7 = recordedDerivedStateValues2;
                                                }
                                                recordedDerivedStateValues = recordedDerivedStateValues7;
                                                if (bitCount$iv$iv$iv8 != 8) {
                                                    break;
                                                }
                                            } else {
                                                recordedDerivedStateValues = recordedDerivedStateValues7;
                                            }
                                            if (i$iv$iv$iv7 == lastIndex$iv$iv$iv9) {
                                                break;
                                            }
                                            i$iv$iv$iv7++;
                                            this_$iv$iv13 = this_$iv$iv14;
                                            $i$f$forEach5 = $i$f$forEach6;
                                            policy5 = policy6;
                                            this_$iv7 = this_$iv8;
                                            recordedDerivedStateValues7 = recordedDerivedStateValues;
                                        }
                                    } else {
                                        recordedDerivedStateValues = recordedDerivedStateValues7;
                                    }
                                } else {
                                    recordedDerivedStateValues = recordedDerivedStateValues7;
                                    invalidated.add(value$iv9);
                                    hasValues5 = true;
                                }
                            }
                            hasValues = hasValues5;
                        }
                    } else {
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        $i$f$forEach = $i$f$forEach4;
                        recordedDerivedStateValues = recordedDerivedStateValues7;
                        it = it2;
                        value = value7;
                        dependencyToDerivedStates4 = dependencyToDerivedStates5;
                    }
                    ScopeMap this_$iv9 = valueToScopes3;
                    Object value8 = value;
                    Object value$iv10 = this_$iv9.getMap().get(value8);
                    if (value$iv10 != null) {
                        if (value$iv10 instanceof MutableScatterSet) {
                            ScatterSet this_$iv$iv15 = (MutableScatterSet) value$iv10;
                            Object[] k$iv$iv12 = this_$iv$iv15.elements;
                            long[] m$iv$iv$iv15 = this_$iv$iv15.metadata;
                            int lastIndex$iv$iv$iv10 = m$iv$iv$iv15.length - 2;
                            int i$iv$iv$iv8 = 0;
                            if (0 <= lastIndex$iv$iv$iv10) {
                                while (true) {
                                    long slot$iv$iv$iv8 = m$iv$iv$iv15[i$iv$iv$iv8];
                                    boolean hasValues6 = hasValues;
                                    ScopeMap this_$iv10 = this_$iv9;
                                    Object value9 = value8;
                                    ScatterSet this_$iv$iv16 = this_$iv$iv15;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv6 = ((~slot$iv$iv$iv8) << 7) & slot$iv$iv$iv8 & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv6 != -9187201950435737472L) {
                                        int bitCount$iv$iv$iv9 = 8 - ((~(i$iv$iv$iv8 - lastIndex$iv$iv$iv10)) >>> 31);
                                        for (int j$iv$iv$iv8 = 0; j$iv$iv$iv8 < bitCount$iv$iv$iv9; j$iv$iv$iv8++) {
                                            long value$iv$iv$iv$iv8 = slot$iv$iv$iv8 & 255;
                                            int $i$f$isFull8 = value$iv$iv$iv$iv8 < 128 ? 1 : 0;
                                            if ($i$f$isFull8 != 0) {
                                                int index$iv$iv$iv8 = (i$iv$iv$iv8 << 3) + j$iv$iv$iv8;
                                                Object scope6 = k$iv$iv12[index$iv$iv$iv8];
                                                invalidated.add(scope6);
                                                hasValues6 = true;
                                            }
                                            slot$iv$iv$iv8 >>= 8;
                                        }
                                        if (bitCount$iv$iv$iv9 != 8) {
                                            hasValues = hasValues6;
                                            break;
                                        }
                                        hasValues = hasValues6;
                                    } else {
                                        hasValues = hasValues6;
                                    }
                                    if (i$iv$iv$iv8 != lastIndex$iv$iv$iv10) {
                                        i$iv$iv$iv8++;
                                        this_$iv9 = this_$iv10;
                                        this_$iv$iv15 = this_$iv$iv16;
                                        value8 = value9;
                                    }
                                }
                            }
                        } else {
                            invalidated.add(value$iv10);
                            hasValues = true;
                        }
                    }
                    $i$f$forEach4 = $i$f$forEach;
                    $this$forEach$iv$iv2 = $this$forEach$iv$iv;
                    it2 = it;
                    recordedDerivedStateValues7 = recordedDerivedStateValues;
                }
            }
            if (this.statesToReread.isNotEmpty()) {
                MutableVector this_$iv11 = this.statesToReread;
                int size$iv = this_$iv11.getSize();
                if (size$iv > 0) {
                    int i$iv = 0;
                    Object[] content$iv = this_$iv11.getContent();
                    do {
                        DerivedState it3 = (DerivedState) content$iv[i$iv];
                        rereadDerivedState(it3);
                        i$iv++;
                    } while (i$iv < size$iv);
                }
                this.statesToReread.clear();
            }
            return hasValues;
        }

        public final void rereadDerivedState(DerivedState<?> derivedState) {
            ScatterSet this_$iv$iv;
            Object[] k$iv$iv;
            ScatterSet this_$iv$iv$iv;
            ScatterSet this_$iv$iv2;
            Object[] k$iv$iv2;
            ScatterSet this_$iv$iv$iv2;
            MutableObjectIntMap it;
            MutableScatterMap scopeToValues = this.scopeToValues;
            int token = SnapshotKt.currentSnapshot().getId();
            ScopeMap this_$iv = this.valueToScopes;
            int $i$f$forEachScopeOf = 0;
            Object value$iv = this_$iv.getMap().get(derivedState);
            if (value$iv == null) {
                return;
            }
            if (!(value$iv instanceof MutableScatterSet)) {
                MutableObjectIntMap<Object> it2 = scopeToValues.get(value$iv);
                if (it2 == null) {
                    it2 = new MutableObjectIntMap(0, 1, null);
                    scopeToValues.set(value$iv, it2);
                    Unit unit = Unit.INSTANCE;
                }
                recordRead(derivedState, token, value$iv, it2);
                return;
            }
            ScatterSet this_$iv$iv3 = (MutableScatterSet) value$iv;
            int $i$f$forEach = 0;
            Object[] k$iv$iv3 = this_$iv$iv3.elements;
            ScatterSet this_$iv$iv$iv3 = this_$iv$iv3;
            long[] m$iv$iv$iv = this_$iv$iv$iv3.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 > lastIndex$iv$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                int $i$f$forEach2 = $i$f$forEach;
                ScopeMap this_$iv2 = this_$iv;
                int $i$f$forEachScopeOf2 = $i$f$forEachScopeOf;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv$iv = this_$iv$iv3;
                    k$iv$iv = k$iv$iv3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                } else {
                    int i = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv = 0;
                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull == 0) {
                            this_$iv$iv2 = this_$iv$iv3;
                            k$iv$iv2 = k$iv$iv3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object scope = k$iv$iv3[index$iv$iv$iv];
                            MutableObjectIntMap<Object> mutableObjectIntMap = scopeToValues.get(scope);
                            if (mutableObjectIntMap == null) {
                                this_$iv$iv2 = this_$iv$iv3;
                                k$iv$iv2 = k$iv$iv3;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                it = new MutableObjectIntMap(0, 1, null);
                                scopeToValues.set(scope, it);
                                Unit unit2 = Unit.INSTANCE;
                            } else {
                                this_$iv$iv2 = this_$iv$iv3;
                                k$iv$iv2 = k$iv$iv3;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                it = mutableObjectIntMap;
                            }
                            recordRead(derivedState, token, scope, it);
                        }
                        i = 8;
                        slot$iv$iv$iv >>= 8;
                        j$iv$iv$iv++;
                        this_$iv$iv3 = this_$iv$iv2;
                        k$iv$iv3 = k$iv$iv2;
                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                    }
                    this_$iv$iv = this_$iv$iv3;
                    k$iv$iv = k$iv$iv3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    if (bitCount$iv$iv$iv != i) {
                        return;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    return;
                }
                i$iv$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$forEachScopeOf = $i$f$forEachScopeOf2;
                $i$f$forEach = $i$f$forEach2;
                this_$iv$iv3 = this_$iv$iv;
                k$iv$iv3 = k$iv$iv;
                this_$iv$iv$iv3 = this_$iv$iv$iv;
            }
        }

        public final void notifyInvalidatedScopes() {
            MutableScatterSet invalidated = this.invalidated;
            MutableScatterSet this_$iv = invalidated;
            Function1 block$iv = this.onChanged;
            Object[] k$iv = this_$iv.elements;
            ScatterSet this_$iv$iv = this_$iv;
            int $i$f$forEachIndex = 0;
            long[] m$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    ScatterSet this_$iv$iv2 = this_$iv$iv;
                    int $i$f$forEachIndex2 = $i$f$forEachIndex;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                block$iv.invoke(k$iv[index$iv$iv]);
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
                    this_$iv$iv = this_$iv$iv2;
                    $i$f$forEachIndex = $i$f$forEachIndex2;
                }
            }
            invalidated.clear();
        }
    }
}
