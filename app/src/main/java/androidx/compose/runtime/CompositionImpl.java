package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.exifinterface.media.ExifInterface;
import androidx.navigation.compose.ComposeNavigator;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: Composition.kt */
@Metadata(d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002£\u0001B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010X\u001a\u00020\u0019H\u0002J\u001e\u0010Y\u001a\u00020\u00192\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010[\u001a\u00020\u0012H\u0002J\b\u0010\\\u001a\u00020\u0019H\u0016J\u0010\u0010]\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010^\u001a\u00020\u0019H\u0016J\b\u0010_\u001a\u00020\u0019H\u0016J\b\u0010`\u001a\u00020\u0019H\u0002J \u0010a\u001a\u00020\u00192\u0011\u0010b\u001a\r\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010\u001eJ \u0010c\u001a\u00020\u00192\u0011\u0010b\u001a\r\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001aH\u0002¢\u0006\u0002\u0010\u001eJ\b\u0010d\u001a\u00020\u0019H\u0016J3\u0010e\u001a\u0002Hf\"\u0004\b\u0000\u0010f2\b\u0010g\u001a\u0004\u0018\u00010\u00012\u0006\u0010h\u001a\u0002072\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hf0\u0018H\u0016¢\u0006\u0002\u0010jJ\b\u0010k\u001a\u00020\u0019H\u0016J\u0010\u0010l\u001a\u00020\u00192\u0006\u0010m\u001a\u00020nH\u0016J\b\u0010o\u001a\u00020\u0019H\u0002J\b\u0010p\u001a\u00020\u0019H\u0002J#\u0010q\u001a\u0004\u0018\u0001Hr\"\u0004\b\u0000\u0010r2\f\u0010s\u001a\b\u0012\u0004\u0012\u0002Hr0tH\u0016¢\u0006\u0002\u0010uJ\"\u0010v\u001a\u0002Hr\"\u0004\b\u0000\u0010r2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hr0\u0018H\u0082\b¢\u0006\u0002\u0010wJK\u0010x\u001a\u0002Hr\"\u0004\b\u0000\u0010r25\u0010i\u001a1\u0012'\u0012%\u0012\u0004\u0012\u00020$\u0012\f\u0012\n\u0012\u0004\u0012\u00020*\u0018\u00010:09¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u0002Hr0yH\u0082\b¢\u0006\u0002\u0010|J&\u0010}\u001a\u00020\u00192\u001c\u0010~\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0005\u0012\u00030\u0080\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u00010\u007f0#H\u0016J\u001e\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0083\u0001\u001a\u00020$2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010*H\u0016J\t\u0010\u0085\u0001\u001a\u00020\u0019H\u0016J(\u0010\u0086\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0083\u0001\u001a\u00020$2\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010*H\u0002J\u000f\u0010\u0089\u0001\u001a\u00020\u00192\u0006\u0010s\u001a\u000207J\u0012\u0010\u008a\u0001\u001a\u00020\u00192\u0007\u0010\u008b\u0001\u001a\u00020*H\u0002J\u001a\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0000¢\u0006\u0003\b\u0090\u0001J\f\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u0001H\u0002J\u0017\u0010\u0091\u0001\u001a\u00020\u00122\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020*0)H\u0016J\u0017\u0010\u0092\u0001\u001a\u00020\u00192\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\t\u0010\u0093\u0001\u001a\u00020\u0012H\u0016J\u0012\u0010\u0094\u0001\u001a\u00020\u00192\u0007\u0010\u0083\u0001\u001a\u00020$H\u0016J\u0017\u0010\u0095\u0001\u001a\u00020\u00192\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020*0)H\u0016J\u0012\u0010\u0096\u0001\u001a\u00020\u00192\u0007\u0010\u008b\u0001\u001a\u00020*H\u0016J\u0012\u0010\u0097\u0001\u001a\u00020\u00192\u0007\u0010\u008b\u0001\u001a\u00020*H\u0016J\u001b\u0010\u0098\u0001\u001a\u00020\u00192\n\u0010m\u001a\u0006\u0012\u0002\b\u00030/H\u0000¢\u0006\u0003\b\u0099\u0001J!\u0010\u009a\u0001\u001a\u00020\u00192\u0007\u0010\u0084\u0001\u001a\u00020*2\u0007\u0010\u0083\u0001\u001a\u00020$H\u0000¢\u0006\u0003\b\u009b\u0001J!\u0010\u009c\u0001\u001a\u00020\u00192\u0011\u0010b\u001a\r\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010\u001eJ!\u0010\u009d\u0001\u001a\u00020\u00192\u0011\u0010b\u001a\r\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u009e\u0001\u001a\u0016\u0012\u0004\u0012\u00020$\u0012\f\u0012\n\u0012\u0004\u0012\u00020*\u0018\u00010:09H\u0002J#\u0010\u009f\u0001\u001a\u0002Hr\"\u0004\b\u0000\u0010r2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hr0\u0018H\u0082\b¢\u0006\u0002\u0010wJ\u001d\u0010 \u0001\u001a\u00020\u00122\u0007\u0010\u0083\u0001\u001a\u00020$2\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010*H\u0002J\u0011\u0010¡\u0001\u001a\u00020\u00192\u0006\u0010S\u001a\u00020TH\u0002J\t\u0010¢\u0001\u001a\u00020\u0019H\u0016JE\u0010Y\u001a\u0016\u0012\u0004\u0012\u00020$\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020$\u0018\u0001`\u0010*\u0016\u0012\u0004\u0012\u00020$\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020$\u0018\u0001`\u00102\u0007\u0010\u008b\u0001\u001a\u00020*2\u0006\u0010[\u001a\u00020\u0012H\u0002R\u0010\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001aX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001e\u0010'\u001a\u0012\u0012\u0004\u0012\u00020$0\u000ej\b\u0012\u0004\u0012\u00020$`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0018\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030/0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u0014R\u0014\u00103\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u0014R\u0010\u00105\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u00108\u001a\u0016\u0012\u0004\u0012\u00020$\u0012\f\u0012\n\u0012\u0004\u0012\u00020*\u0018\u00010:09X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010;\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u0014R\u0014\u0010<\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u0014R\u0011\u0010=\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0014R\u000e\u0010>\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020$0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020$0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020*0)8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\bC\u0010,R\u0014\u0010D\u001a\u00020EX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010H\u001a\u00020\u0012X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bI\u0010J\u001a\u0004\bK\u0010\u0014\"\u0004\bL\u0010MR\"\u0010N\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010*0Oj\n\u0012\u0006\u0012\u0004\u0018\u00010*`PX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u001a\u0010S\u001a\u00020TX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bU\u0010J\u001a\u0004\bV\u0010W¨\u0006¤\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "_recomposeContext", "abandonSet", "Ljava/util/HashSet;", "Landroidx/compose/runtime/RememberObserver;", "Lkotlin/collections/HashSet;", "areChildrenComposing", "", "getAreChildrenComposing", "()Z", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", ComposeNavigator.NAME, "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "conditionalScopes", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getConditionalScopes$runtime_release", "()Ljava/util/List;", "conditionallyInvalidatedScopes", "derivedStateDependencies", "", "", "getDerivedStateDependencies$runtime_release", "()Ljava/util/Set;", "derivedStates", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/DerivedState;", "disposed", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges", "invalidationDelegate", "invalidationDelegateGroup", "", "invalidations", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/collection/IdentityArraySet;", "isComposing", "isDisposed", "isRoot", "lateChanges", "lock", "observations", "observationsProcessed", "observedObjects", "getObservedObjects$runtime_release", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "pendingInvalidScopes", "getPendingInvalidScopes$runtime_release$annotations", "()V", "getPendingInvalidScopes$runtime_release", "setPendingInvalidScopes$runtime_release", "(Z)V", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime_release$annotations", "getSlotTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "abandonChanges", "addPendingInvalidationsLocked", "values", "forgetConditionalScopes", "applyChanges", "applyChangesInLocked", "applyLateChanges", "changesApplied", "cleanUpDerivedStateObservations", "composeContent", "content", "composeInitial", "deactivate", "delegateInvalidations", "R", "to", "groupIndex", "block", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "dispose", "disposeUnusedMovableContent", "state", "Landroidx/compose/runtime/MovableContentState;", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "getCompositionService", ExifInterface.GPS_DIRECTION_TRUE, "key", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "guardChanges", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "guardInvalidationsLocked", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "insertMovableContent", "references", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "invalidateAll", "invalidateChecked", "anchor", "Landroidx/compose/runtime/Anchor;", "invalidateGroupsWithKey", "invalidateScopeOfLocked", "value", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "observe$runtime_release", "observesAnyOf", "prepareCompose", "recompose", "recomposeScopeReleased", "recordModificationsOf", "recordReadOf", "recordWriteOf", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime_release", "removeObservation", "removeObservation$runtime_release", "setContent", "setContentWithReuse", "takeInvalidations", "trackAbandonedValues", "tryImminentInvalidation", "validateRecomposeScopeAnchors", "verifyConsistent", "RememberEventDispatcher", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices {
    public static final int $stable = 8;
    private final CoroutineContext _recomposeContext;
    private final HashSet<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ChangeList changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final HashSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final ScopeMap<DerivedState<?>> derivedStates;
    private boolean disposed;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final ScopeMap<RecomposeScopeImpl> observations;
    private final ScopeMap<RecomposeScopeImpl> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private final SlotTable slotTable;

    public static /* synthetic */ void getPendingInvalidScopes$runtime_release$annotations() {
    }

    public static /* synthetic */ void getSlotTable$runtime_release$annotations() {
    }

    public CompositionImpl(CompositionContext parent, Applier<?> applier, CoroutineContext recomposeContext) {
        this.parent = parent;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        this.abandonSet = new HashSet<>();
        this.slotTable = new SlotTable();
        this.observations = new ScopeMap<>();
        this.conditionallyInvalidatedScopes = new HashSet<>();
        this.derivedStates = new ScopeMap<>();
        this.changes = new ChangeList();
        this.lateChanges = new ChangeList();
        this.observationsProcessed = new ScopeMap<>();
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        this.observerHolder = new CompositionObserverHolder(null, false, 3, null);
        ComposerImpl it = new ComposerImpl(this.applier, this.parent, this.slotTable, this.abandonSet, this.changes, this.lateChanges, this);
        this.parent.registerComposer$runtime_release(it);
        this.composer = it;
        this._recomposeContext = recomposeContext;
        this.isRoot = this.parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m2914getLambda1$runtime_release();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    /* renamed from: getSlotTable$runtime_release, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    public final Set<Object> getObservedObjects$runtime_release() {
        return this.observations.getMap().asMap().keySet();
    }

    public final Set<Object> getDerivedStateDependencies$runtime_release() {
        return this.derivedStates.getMap().asMap().keySet();
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime_release() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes);
    }

    /* renamed from: getPendingInvalidScopes$runtime_release, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime_release(boolean z) {
        this.pendingInvalidScopes = z;
    }

    /* renamed from: getObserverHolder$runtime_release, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime_release() : coroutineContext;
    }

    /* renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    /* renamed from: isDisposed, reason: from getter */
    public boolean getDisposed() {
        return this.disposed;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime_release;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            hasPendingChanges$runtime_release = this.composer.getHasPendingChanges$runtime_release();
        }
        return hasPendingChanges$runtime_release;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        composeInitial(content);
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composer.startReuseFromRoot();
        composeInitial(content);
        this.composer.endReuseFromRoot();
    }

    private final void composeInitial(Function2<? super Composer, ? super Integer, Unit> content) {
        if (!(!this.disposed)) {
            throw new IllegalStateException("The composition is disposed".toString());
        }
        this.composable = content;
        this.parent.composeInitial$runtime_release(this, this.composable);
    }

    public final CompositionObserverHandle observe$runtime_release(final CompositionObserver observer) {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl$observe$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2;
                lock$iv2 = CompositionImpl.this.lock;
                CompositionImpl compositionImpl = CompositionImpl.this;
                CompositionObserver compositionObserver = observer;
                synchronized (lock$iv2) {
                    if (Intrinsics.areEqual(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                        compositionImpl.getObserverHolder().setObserver(null);
                        compositionImpl.getObserverHolder().setRoot(false);
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    public final void invalidateGroupsWithKey(int key) {
        boolean forceComposition;
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                try {
                    List scopesToInvalidate = this.slotTable.invalidateGroupsWithKey$runtime_release(key);
                    if (scopesToInvalidate != null) {
                        int index$iv$iv = 0;
                        int size = scopesToInvalidate.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object item$iv$iv = scopesToInvalidate.get(index$iv$iv);
                                RecomposeScopeImpl it = (RecomposeScopeImpl) item$iv$iv;
                                if (it.invalidateForResult(null) == InvalidationResult.IGNORED) {
                                    z = true;
                                    break;
                                }
                                index$iv$iv++;
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            forceComposition = false;
                            if (!forceComposition && this.composer.forceRecomposeScopes$runtime_release()) {
                                this.parent.invalidate$runtime_release(this);
                                return;
                            }
                        }
                    }
                    forceComposition = true;
                    if (!forceComposition) {
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object obj;
        Object obj2;
        AtomicReference<Object> atomicReference = this.pendingModifications;
        obj = CompositionKt.PendingApplyNoModifications;
        Object toRecord = atomicReference.getAndSet(obj);
        if (toRecord != null) {
            obj2 = CompositionKt.PendingApplyNoModifications;
            if (Intrinsics.areEqual(toRecord, obj2)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set) toRecord, true);
                return;
            }
            if (!(toRecord instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set changed : (Set[]) toRecord) {
                addPendingInvalidationsLocked(changed, true);
            }
        }
    }

    private final void drainPendingModificationsLocked() {
        Object obj;
        Object toRecord = this.pendingModifications.getAndSet(null);
        obj = CompositionKt.PendingApplyNoModifications;
        if (!Intrinsics.areEqual(toRecord, obj)) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set) toRecord, false);
                return;
            }
            if (!(toRecord instanceof Object[])) {
                if (toRecord == null) {
                    ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
                    throw new KotlinNothingValueException();
                }
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set changed : (Set[]) toRecord) {
                addPendingInvalidationsLocked(changed, false);
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        CompositionObserver observer;
        Unit unit;
        CompositionImpl compositionImpl;
        try {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                try {
                    try {
                        drainPendingModificationsForCompositionLocked();
                        IdentityArrayMap invalidations$iv = takeInvalidations();
                        try {
                            try {
                                observer = observer();
                                if (observer != null) {
                                    try {
                                        compositionImpl = this;
                                    } catch (Exception e) {
                                        e$iv = e;
                                    } catch (Throwable th) {
                                        e$iv = th;
                                    }
                                    try {
                                        Map<RecomposeScopeImpl, IdentityArraySet<Object>> asMap = invalidations$iv.asMap();
                                        try {
                                            Intrinsics.checkNotNull(asMap, "null cannot be cast to non-null type kotlin.collections.Map<androidx.compose.runtime.RecomposeScope, kotlin.collections.Set<kotlin.Any>?>");
                                            observer.onBeginComposition(compositionImpl, asMap);
                                        } catch (Exception e2) {
                                            e$iv = e2;
                                            this.invalidations = invalidations$iv;
                                            throw e$iv;
                                        } catch (Throwable th2) {
                                            e$iv = th2;
                                            throw e$iv;
                                        }
                                    } catch (Exception e3) {
                                        e$iv = e3;
                                        this.invalidations = invalidations$iv;
                                        throw e$iv;
                                    } catch (Throwable th3) {
                                        e$iv = th3;
                                        throw e$iv;
                                    }
                                }
                            } catch (Exception e4) {
                                e$iv = e4;
                            }
                            try {
                                this.composer.composeContent$runtime_release(invalidations$iv, content);
                                if (observer != null) {
                                    observer.onEndComposition(this);
                                    unit = Unit.INSTANCE;
                                } else {
                                    unit = null;
                                }
                            } catch (Exception e5) {
                                e$iv = e5;
                                this.invalidations = invalidations$iv;
                                throw e$iv;
                            }
                        } catch (Throwable th4) {
                            e$iv = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (0 == 0) {
                            try {
                                if (!this.abandonSet.isEmpty()) {
                                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                                }
                            } catch (Exception e$iv) {
                                abandonChanges();
                                throw e$iv;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    e$iv = th6;
                }
            }
        } catch (Throwable th7) {
            th = th7;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!(!this.composer.getIsComposing())) {
                throw new IllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.".toString());
            }
            if (!this.disposed) {
                this.disposed = true;
                this.composable = ComposableSingletons$CompositionKt.INSTANCE.m2915getLambda2$runtime_release();
                ChangeList deferredChanges = this.composer.getDeferredChanges();
                if (deferredChanges != null) {
                    applyChangesInLocked(deferredChanges);
                }
                boolean nonEmptySlotTable = this.slotTable.getGroupsSize() > 0;
                if (nonEmptySlotTable || (true ^ this.abandonSet.isEmpty())) {
                    RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
                    if (nonEmptySlotTable) {
                        this.applier.onBeginChanges();
                        SlotTable this_$iv = this.slotTable;
                        SlotWriter writer$iv = this_$iv.openWriter();
                        try {
                            ComposerKt.removeCurrentGroup(writer$iv, manager);
                            Unit unit = Unit.INSTANCE;
                            writer$iv.close();
                            this.applier.clear();
                            this.applier.onEndChanges();
                            manager.dispatchRememberObservers();
                        } catch (Throwable th) {
                            writer$iv.close();
                            throw th;
                        }
                    }
                    manager.dispatchAbandons();
                }
                this.composer.dispose$runtime_release();
            }
            Unit unit2 = Unit.INSTANCE;
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.util.Set[]] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Object[]] */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object obj2;
        boolean areEqual;
        Set<? extends Object> set;
        do {
            obj = this.pendingModifications.get();
            if (obj != null) {
                obj2 = CompositionKt.PendingApplyNoModifications;
                areEqual = Intrinsics.areEqual(obj, obj2);
            } else {
                areEqual = true;
            }
            if (areEqual) {
                set = values;
            } else if (obj instanceof Set) {
                set = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                set = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, set));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        if (values instanceof IdentityArraySet) {
            IdentityArraySet this_$iv = (IdentityArraySet) values;
            Object[] values$iv = this_$iv.getValues();
            int size = this_$iv.size();
            for (int i$iv = 0; i$iv < size; i$iv++) {
                Object value = values$iv[i$iv];
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                if (this.observations.contains(value) || this.derivedStates.contains(value)) {
                    return true;
                }
            }
            return false;
        }
        for (Object value2 : values) {
            if (this.observations.contains(value2) || this.derivedStates.contains(value2)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        this.composer.prepareCompose$runtime_release(block);
    }

    private final HashSet<RecomposeScopeImpl> addPendingInvalidationsLocked(HashSet<RecomposeScopeImpl> hashSet, Object value, boolean forgetConditionalScopes) {
        int $i$f$forEachScopeOf;
        int $i$f$forEachScopeOf2;
        HashSet<RecomposeScopeImpl> hashSet2 = hashSet;
        ScopeMap this_$iv = this.observations;
        int $i$f$forEachScopeOf3 = 0;
        Object value$iv = this_$iv.getMap().get(value);
        if (value$iv == null) {
            return hashSet2;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (!this.observationsProcessed.remove(value, scope) && scope.invalidateForResult(value) != InvalidationResult.IGNORED) {
                if (scope.isConditional() && !forgetConditionalScopes) {
                    this.conditionallyInvalidatedScopes.add(scope);
                    return hashSet2;
                }
                if (hashSet2 == null) {
                    hashSet2 = new HashSet<>();
                }
                hashSet2.add(scope);
                return hashSet2;
            }
            return hashSet2;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        int $i$f$forEach = 0;
        Object[] k$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return hashSet2;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            HashSet<RecomposeScopeImpl> hashSet3 = hashSet2;
            ScopeMap this_$iv2 = this_$iv;
            ScatterSet this_$iv$iv2 = this_$iv$iv;
            int $i$f$forEach2 = $i$f$forEach;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                $i$f$forEachScopeOf = $i$f$forEachScopeOf3;
                hashSet2 = hashSet3;
            } else {
                int i = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv = 0;
                while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        $i$f$forEachScopeOf2 = $i$f$forEachScopeOf3;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) k$iv$iv[index$iv$iv$iv];
                        $i$f$forEachScopeOf2 = $i$f$forEachScopeOf3;
                        if (!this.observationsProcessed.remove(value, scope2) && scope2.invalidateForResult(value) != InvalidationResult.IGNORED) {
                            if (scope2.isConditional() && !forgetConditionalScopes) {
                                this.conditionallyInvalidatedScopes.add(scope2);
                            } else {
                                HashSet<RecomposeScopeImpl> hashSet4 = hashSet3 == null ? new HashSet<>() : hashSet3;
                                hashSet4.add(scope2);
                                hashSet3 = hashSet4;
                            }
                        }
                    }
                    i = 8;
                    slot$iv$iv$iv >>= 8;
                    j$iv$iv$iv++;
                    $i$f$forEachScopeOf3 = $i$f$forEachScopeOf2;
                }
                $i$f$forEachScopeOf = $i$f$forEachScopeOf3;
                if (bitCount$iv$iv$iv != i) {
                    return hashSet3;
                }
                hashSet2 = hashSet3;
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return hashSet2;
            }
            i$iv$iv$iv++;
            this_$iv$iv = this_$iv$iv2;
            $i$f$forEach = $i$f$forEach2;
            this_$iv = this_$iv2;
            $i$f$forEachScopeOf3 = $i$f$forEachScopeOf;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0366 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addPendingInvalidationsLocked(java.util.Set<? extends java.lang.Object> r50, boolean r51) {
        /*
            Method dump skipped, instructions count: 1619
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.addPendingInvalidationsLocked(java.util.Set, boolean):void");
    }

    private final void cleanUpDerivedStateObservations() {
        ScopeMap this_$iv;
        int $i$f$removeScopeIf;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        int lastIndex$iv$iv$iv;
        int i$iv$iv$iv;
        ScopeMap this_$iv2;
        int $i$f$removeScopeIf2;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        int lastIndex$iv$iv$iv2;
        int i$iv$iv$iv2;
        char c;
        boolean z;
        Object[] elements$iv$iv;
        Object[] elements$iv$iv2;
        ScopeMap this_$iv3 = this.derivedStates;
        int $i$f$removeScopeIf3 = 0;
        MutableScatterMap this_$iv$iv = this_$iv3.getMap();
        int $i$f$removeIf3 = 0;
        MutableScatterMap this_$iv$iv$iv3 = this_$iv$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv3 = this_$iv$iv$iv3.metadata;
        int lastIndex$iv$iv$iv3 = m$iv$iv$iv3.length - 2;
        int i$iv$iv$iv3 = 0;
        if (0 <= lastIndex$iv$iv$iv3) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv3[i$iv$iv$iv3];
                long slot$iv$iv$iv2 = slot$iv$iv$iv;
                char c2 = 7;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                    $i$f$removeScopeIf = $i$f$removeScopeIf3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv3;
                    lastIndex$iv$iv$iv = lastIndex$iv$iv$iv3;
                    i$iv$iv$iv = i$iv$iv$iv3;
                } else {
                    int i = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv3 - lastIndex$iv$iv$iv3)) >>> 31);
                    int j$iv$iv$iv = 0;
                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv2 & 255;
                        if (!(value$iv$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                            lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                            i$iv$iv$iv2 = i$iv$iv$iv3;
                            c = c2;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv3 << 3) + j$iv$iv$iv;
                            Object obj = this_$iv$iv.keys[index$iv$iv$iv];
                            Object value$iv = this_$iv$iv.values[index$iv$iv$iv];
                            if (value$iv instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1>");
                                MutableScatterSet set$iv = (MutableScatterSet) value$iv;
                                this_$iv2 = this_$iv3;
                                Object[] elements$iv$iv3 = set$iv.elements;
                                $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                long[] m$iv$iv$iv4 = set$iv.metadata;
                                int lastIndex$iv$iv$iv4 = m$iv$iv$iv4.length - 2;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                int i$iv$iv$iv4 = 0;
                                if (0 <= lastIndex$iv$iv$iv4) {
                                    while (true) {
                                        long slot$iv$iv$iv3 = m$iv$iv$iv4[i$iv$iv$iv4];
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        m$iv$iv$iv2 = m$iv$iv$iv3;
                                        lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                        i$iv$iv$iv2 = i$iv$iv$iv3;
                                        c = 7;
                                        long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv3) << 7) & slot$iv$iv$iv3 & (-9187201950435737472L);
                                        if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                                            elements$iv$iv = elements$iv$iv3;
                                        } else {
                                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv4 - lastIndex$iv$iv$iv4)) >>> 31);
                                            int j$iv$iv$iv2 = 0;
                                            while (j$iv$iv$iv2 < bitCount$iv$iv$iv2) {
                                                long value$iv$iv$iv$iv2 = slot$iv$iv$iv3 & 255;
                                                if (!(value$iv$iv$iv$iv2 < 128)) {
                                                    elements$iv$iv2 = elements$iv$iv3;
                                                } else {
                                                    int index$iv$iv$iv2 = (i$iv$iv$iv4 << 3) + j$iv$iv$iv2;
                                                    DerivedState derivedState = (DerivedState) elements$iv$iv3[index$iv$iv$iv2];
                                                    elements$iv$iv2 = elements$iv$iv3;
                                                    if (!this.observations.contains(derivedState)) {
                                                        set$iv.removeElementAt(index$iv$iv$iv2);
                                                    }
                                                }
                                                slot$iv$iv$iv3 >>= 8;
                                                j$iv$iv$iv2++;
                                                elements$iv$iv3 = elements$iv$iv2;
                                            }
                                            elements$iv$iv = elements$iv$iv3;
                                            if (bitCount$iv$iv$iv2 != 8) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv$iv4 == lastIndex$iv$iv$iv4) {
                                            break;
                                        }
                                        i$iv$iv$iv4++;
                                        lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv2;
                                        i$iv$iv$iv3 = i$iv$iv$iv2;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        m$iv$iv$iv3 = m$iv$iv$iv2;
                                        elements$iv$iv3 = elements$iv$iv;
                                    }
                                } else {
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv3;
                                    lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                    i$iv$iv$iv2 = i$iv$iv$iv3;
                                    c = 7;
                                }
                                z = set$iv.isEmpty();
                            } else {
                                this_$iv2 = this_$iv3;
                                $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv3;
                                lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv3;
                                i$iv$iv$iv2 = i$iv$iv$iv3;
                                c = c2;
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$1");
                                DerivedState derivedState2 = (DerivedState) value$iv;
                                z = !this.observations.contains(derivedState2);
                            }
                            if (z) {
                                this_$iv$iv.removeValueAt(index$iv$iv$iv);
                            }
                        }
                        slot$iv$iv$iv2 >>= 8;
                        j$iv$iv$iv++;
                        i = 8;
                        this_$iv3 = this_$iv2;
                        c2 = c;
                        $i$f$removeScopeIf3 = $i$f$removeScopeIf2;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                        lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv2;
                        i$iv$iv$iv3 = i$iv$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        m$iv$iv$iv3 = m$iv$iv$iv2;
                    }
                    this_$iv = this_$iv3;
                    $i$f$removeScopeIf = $i$f$removeScopeIf3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv3;
                    lastIndex$iv$iv$iv = lastIndex$iv$iv$iv3;
                    i$iv$iv$iv = i$iv$iv$iv3;
                    if (bitCount$iv$iv$iv != i) {
                        break;
                    }
                }
                lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv;
                int i$iv$iv$iv5 = i$iv$iv$iv;
                if (i$iv$iv$iv5 == lastIndex$iv$iv$iv3) {
                    break;
                }
                i$iv$iv$iv3 = i$iv$iv$iv5 + 1;
                this_$iv3 = this_$iv;
                $i$f$removeScopeIf3 = $i$f$removeScopeIf;
                $i$f$removeIf3 = $i$f$removeIf;
                this_$iv$iv$iv3 = this_$iv$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv$iv3 = m$iv$iv$iv;
            }
        }
        if (!this.conditionallyInvalidatedScopes.isEmpty()) {
            HashSet $this$removeValueIf$iv = this.conditionallyInvalidatedScopes;
            Iterator iter$iv = $this$removeValueIf$iv.iterator();
            while (iter$iv.hasNext()) {
                RecomposeScopeImpl scope = iter$iv.next();
                if (!scope.isConditional()) {
                    iter$iv.remove();
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl it;
        ObjectIntMap this_$iv;
        boolean z;
        ObjectIntMap this_$iv2;
        if (!getAreChildrenComposing() && (it = this.composer.getCurrentRecomposeScope$runtime_release()) != null) {
            int i = 0;
            it.setUsed(true);
            boolean alreadyRead = it.recordRead(value);
            if (!alreadyRead) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m3044recordReadInh_f27i8$runtime_release(ReaderKind.m3031constructorimpl(1));
                }
                this.observations.add(value, it);
                if (value instanceof DerivedState) {
                    this.derivedStates.removeScope(value);
                    ObjectIntMap this_$iv3 = ((DerivedState) value).getCurrentRecord().getDependencies();
                    Object[] k$iv = this_$iv3.keys;
                    long[] m$iv$iv = this_$iv3.metadata;
                    int lastIndex$iv$iv = m$iv$iv.length - 2;
                    int i$iv$iv = 0;
                    if (0 > lastIndex$iv$iv) {
                        return;
                    }
                    while (true) {
                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                        boolean alreadyRead2 = alreadyRead;
                        RecomposeScopeImpl it2 = it;
                        int i2 = i;
                        if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                            this_$iv = this_$iv3;
                            z = true;
                        } else {
                            int i3 = 8;
                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                            int j$iv$iv = 0;
                            while (j$iv$iv < bitCount$iv$iv) {
                                long value$iv$iv$iv = slot$iv$iv & 255;
                                if (!(value$iv$iv$iv < 128)) {
                                    this_$iv2 = this_$iv3;
                                } else {
                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                    StateObject dependency = (StateObject) k$iv[index$iv$iv];
                                    if (!(dependency instanceof StateObjectImpl)) {
                                        this_$iv2 = this_$iv3;
                                    } else {
                                        ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                        this_$iv2 = this_$iv3;
                                        ((StateObjectImpl) dependency).m3044recordReadInh_f27i8$runtime_release(ReaderKind.m3031constructorimpl(1));
                                    }
                                    this.derivedStates.add(dependency, value);
                                }
                                i3 = 8;
                                slot$iv$iv >>= 8;
                                j$iv$iv++;
                                this_$iv3 = this_$iv2;
                            }
                            this_$iv = this_$iv3;
                            z = true;
                            if (bitCount$iv$iv != i3) {
                                return;
                            }
                        }
                        if (i$iv$iv == lastIndex$iv$iv) {
                            return;
                        }
                        i$iv$iv++;
                        it = it2;
                        i = i2;
                        alreadyRead = alreadyRead2;
                        this_$iv3 = this_$iv;
                    }
                }
            }
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        Object[] k$iv$iv;
        Object[] k$iv$iv2;
        ScopeMap this_$iv = this.observations;
        int $i$f$forEachScopeOf = 0;
        Object value$iv = this_$iv.getMap().get(value);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (scope.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                this.observationsProcessed.add(value, scope);
                return;
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        int $i$f$forEach = 0;
        Object[] k$iv$iv3 = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            ScopeMap this_$iv2 = this_$iv;
            int $i$f$forEachScopeOf2 = $i$f$forEachScopeOf;
            ScatterSet this_$iv$iv2 = this_$iv$iv;
            int $i$f$forEach2 = $i$f$forEach;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                k$iv$iv = k$iv$iv3;
            } else {
                int i = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv = 0;
                while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        k$iv$iv2 = k$iv$iv3;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) k$iv$iv3[index$iv$iv$iv];
                        k$iv$iv2 = k$iv$iv3;
                        if (scope2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                            this.observationsProcessed.add(value, scope2);
                        }
                    }
                    i = 8;
                    slot$iv$iv$iv >>= 8;
                    j$iv$iv$iv++;
                    k$iv$iv3 = k$iv$iv2;
                }
                k$iv$iv = k$iv$iv3;
                if (bitCount$iv$iv$iv != i) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            this_$iv$iv = this_$iv$iv2;
            $i$f$forEach = $i$f$forEach2;
            this_$iv = this_$iv2;
            $i$f$forEachScopeOf = $i$f$forEachScopeOf2;
            k$iv$iv3 = k$iv$iv;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        Object lock$iv = this.lock;
        int $i$f$synchronized = 0;
        synchronized (lock$iv) {
            try {
                invalidateScopeOfLocked(value);
                ScopeMap this_$iv = this.derivedStates;
                Object value$iv = this_$iv.getMap().get(value);
                try {
                    if (value$iv != null) {
                        if (!(value$iv instanceof MutableScatterSet)) {
                            DerivedState it = (DerivedState) value$iv;
                            invalidateScopeOfLocked(it);
                        } else {
                            ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
                            Object[] k$iv$iv = this_$iv$iv.elements;
                            long[] m$iv$iv$iv = this_$iv$iv.metadata;
                            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                            int i$iv$iv$iv = 0;
                            if (0 <= lastIndex$iv$iv$iv) {
                                while (true) {
                                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                    int $i$f$synchronized2 = $i$f$synchronized;
                                    ScopeMap this_$iv2 = this_$iv;
                                    ScatterSet this_$iv$iv2 = this_$iv$iv;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                                        int i = 8;
                                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                        for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                            if (value$iv$iv$iv$iv < 128) {
                                                int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                DerivedState it2 = (DerivedState) k$iv$iv[index$iv$iv$iv];
                                                invalidateScopeOfLocked(it2);
                                            }
                                            i = 8;
                                            slot$iv$iv$iv >>= 8;
                                        }
                                        if (bitCount$iv$iv$iv != i) {
                                            break;
                                        }
                                    }
                                    if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                                        break;
                                    }
                                    i$iv$iv$iv++;
                                    this_$iv$iv = this_$iv$iv2;
                                    $i$f$synchronized = $i$f$synchronized2;
                                    this_$iv = this_$iv2;
                                }
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean recompose() {
        /*
            r20 = this;
            r1 = r20
            java.lang.Object r2 = r1.lock
            r3 = 0
            monitor-enter(r2)
            r4 = 0
            r20.drainPendingModificationsForCompositionLocked()     // Catch: java.lang.Throwable -> Lba
            r5 = r20
            r6 = 0
            r7 = r5
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = r20
            r12 = 0
            androidx.compose.runtime.collection.IdentityArrayMap r0 = r11.takeInvalidations()     // Catch: java.lang.Throwable -> L8f
            r13 = r0
            r0 = r13
            r14 = 0
            androidx.compose.runtime.tooling.CompositionObserver r15 = r20.observer()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L8f
            if (r15 == 0) goto L4f
            r16 = r3
            r3 = r1
            androidx.compose.runtime.Composition r3 = (androidx.compose.runtime.Composition) r3     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L49
            r17 = r4
            java.util.Map r4 = r0.asMap()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3f
            r18 = r6
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.collections.Map<androidx.compose.runtime.RecomposeScope, kotlin.collections.Set<kotlin.Any>?>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r6)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r15.onBeginComposition(r3, r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            goto L55
        L3b:
            r0 = move-exception
            r18 = r6
            goto L96
        L3f:
            r0 = move-exception
            r18 = r6
            goto L89
        L43:
            r0 = move-exception
            r17 = r4
            r18 = r6
            goto L96
        L49:
            r0 = move-exception
            r17 = r4
            r18 = r6
            goto L89
        L4f:
            r16 = r3
            r17 = r4
            r18 = r6
        L55:
            androidx.compose.runtime.ComposerImpl r3 = r1.composer     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            boolean r3 = r3.recompose$runtime_release(r0)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r4 = r3
            r6 = 0
            if (r4 != 0) goto L62
            r20.drainPendingModificationsLocked()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
        L62:
            if (r15 == 0) goto L6d
            r19 = r0
            r0 = r1
            androidx.compose.runtime.Composition r0 = (androidx.compose.runtime.Composition) r0     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r15.onEndComposition(r0)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            goto L6f
        L6d:
            r19 = r0
        L6f:
            r0 = r3
            r4 = 0
            r6 = 1
            monitor-exit(r2)
            return r3
        L80:
            r0 = move-exception
            goto L89
        L82:
            r0 = move-exception
            r16 = r3
            r17 = r4
            r18 = r6
        L89:
            r11.invalidations = r13     // Catch: java.lang.Throwable -> L8d
            throw r0     // Catch: java.lang.Throwable -> L8d
        L8d:
            r0 = move-exception
            goto L96
        L8f:
            r0 = move-exception
            r16 = r3
            r17 = r4
            r18 = r6
        L96:
            if (r9 != 0) goto Lb0
            java.util.HashSet<androidx.compose.runtime.RememberObserver> r3 = r7.abandonSet     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.util.Collection r3 = (java.util.Collection) r3     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r3 = r3 ^ 1
            if (r3 == 0) goto Lb0
            androidx.compose.runtime.CompositionImpl$RememberEventDispatcher r3 = new androidx.compose.runtime.CompositionImpl$RememberEventDispatcher     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.util.HashSet<androidx.compose.runtime.RememberObserver> r4 = r7.abandonSet     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            java.util.Set r4 = (java.util.Set) r4     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r3.<init>(r4)     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
            r3.dispatchAbandons()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
        Lb0:
            throw r0     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lb4
        Lb2:
            r0 = move-exception
            goto Lbd
        Lb4:
            r0 = move-exception
            r5.abandonChanges()     // Catch: java.lang.Throwable -> Lb2
            throw r0     // Catch: java.lang.Throwable -> Lb2
        Lba:
            r0 = move-exception
            r16 = r3
        Lbd:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.recompose():boolean");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        boolean z;
        int index$iv$iv = 0;
        int size = references.size();
        while (true) {
            if (index$iv$iv < size) {
                Pair item$iv$iv = references.get(index$iv$iv);
                Pair it = item$iv$iv;
                if (!Intrinsics.areEqual(it.getFirst().getComposition(), this)) {
                    z = false;
                    break;
                }
                index$iv$iv++;
            } else {
                z = true;
                break;
            }
        }
        ComposerKt.runtimeCheck(z);
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } finally {
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
        SlotTable slotTable = state.getSlotTable();
        SlotWriter writer$iv = slotTable.openWriter();
        try {
            ComposerKt.removeCurrentGroup(writer$iv, manager);
            Unit unit = Unit.INSTANCE;
            writer$iv.close();
            manager.dispatchRememberObservers();
        } catch (Throwable th) {
            writer$iv.close();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x0200, code lost:
    
        if (r3 == r4) goto L78;
     */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList r52) {
        /*
            Method dump skipped, instructions count: 697
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList):void");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                this.composer.changesApplied$runtime_release();
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>>, ? extends T> block) {
        IdentityArrayMap invalidations = takeInvalidations();
        try {
            return block.invoke(invalidations);
        } catch (Exception e) {
            this.invalidations = invalidations;
            throw e;
        }
    }

    private final <T> T guardChanges(Function0<? extends T> block) {
        try {
            try {
                T invoke = block.invoke();
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                return invoke;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (0 == 0 && (!this.abandonSet.isEmpty())) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                InlineMarker.finallyEnd(1);
                throw th;
            }
        } catch (Exception e) {
            abandonChanges();
            throw e;
        }
    }

    private final void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        this.abandonSet.clear();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            Object[] $this$forEach$iv = this.slotTable.getSlots();
            for (Object element$iv : $this$forEach$iv) {
                RecomposeScopeImpl recomposeScopeImpl = element$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime_release();
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        if (to != null && !Intrinsics.areEqual(to, this) && groupIndex >= 0) {
            this.invalidationDelegate = (CompositionImpl) to;
            this.invalidationDelegateGroup = groupIndex;
            try {
                return block.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return block.invoke();
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        CompositionImpl delegate;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotTable.ownsAnchor(anchor)) {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                delegate = this.invalidationDelegate;
            }
            if (delegate != null && delegate.tryImminentInvalidation(scope, instance)) {
                return InvalidationResult.IMMINENT;
            }
            return InvalidationResult.IGNORED;
        }
        if (!scope.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        return invalidateChecked(scope, anchor, instance);
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (Intrinsics.areEqual(key, CompositionKt.getCompositionImplServiceKey())) {
            return (T) this;
        }
        return null;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime_release(scope, instance);
    }

    private final InvalidationResult invalidateChecked(RecomposeScopeImpl scope, Anchor anchor, Object instance) {
        CompositionImpl compositionImpl;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            CompositionImpl changeDelegate = this.invalidationDelegate;
            if (changeDelegate == null) {
                compositionImpl = null;
            } else if (this.slotTable.groupContainsAnchor(this.invalidationDelegateGroup, anchor)) {
                compositionImpl = changeDelegate;
            } else {
                compositionImpl = null;
            }
            CompositionImpl delegate = compositionImpl;
            if (delegate == null) {
                if (tryImminentInvalidation(scope, instance)) {
                    return InvalidationResult.IMMINENT;
                }
                if (instance != null) {
                    CompositionKt.addValue(this.invalidations, scope, instance);
                } else {
                    this.invalidations.set(scope, null);
                }
            }
            if (delegate != null) {
                return delegate.invalidateChecked(scope, anchor, instance);
            }
            this.parent.invalidate$runtime_release(this);
            return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
        }
    }

    public final void removeObservation$runtime_release(Object instance, RecomposeScopeImpl scope) {
        this.observations.remove(instance, scope);
    }

    public final void removeDerivedStateObservation$runtime_release(DerivedState<?> state) {
        if (!this.observations.contains(state)) {
            this.derivedStates.removeScope(state);
        }
    }

    private final IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> takeInvalidations() {
        IdentityArrayMap invalidations = this.invalidations;
        this.invalidations = new IdentityArrayMap<>(0, 1, null);
        return invalidations;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        Object[] $this$mapNotNull$iv = slotTable.getSlots();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            RecomposeScopeImpl recomposeScopeImpl = element$iv$iv$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv$iv$iv : null;
            if (recomposeScopeImpl != null) {
                destination$iv$iv.add(recomposeScopeImpl);
            }
        }
        List scopes = (List) destination$iv$iv;
        int size = scopes.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = scopes.get(index$iv);
            RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv;
            Anchor anchor = scope.getAnchor();
            if (anchor != null && !slotTable.slotsOf$runtime_release(anchor.toIndexFor(slotTable)).contains(scope)) {
                int dataIndex = ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable.getSlots(), scope);
                throw new IllegalStateException(("Misaligned anchor " + anchor + " in scope " + scope + " encountered, scope found at " + dataIndex).toString());
            }
        }
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            T invoke = block.invoke();
            InlineMarker.finallyStart(1);
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (0 == 0 && (!this.abandonSet.isEmpty())) {
                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    private final CompositionObserver observer() {
        CompositionObserverHolder holder = this.observerHolder;
        if (holder.getRoot()) {
            return holder.getObserver();
        }
        CompositionObserverHolder parentHolder = this.parent.getObserverHolder();
        CompositionObserver parentObserver = parentHolder != null ? parentHolder.getObserver() : null;
        if (!Intrinsics.areEqual(parentObserver, holder.getObserver())) {
            holder.setObserver(parentObserver);
        }
        return parentObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Composition.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\u000fJ\u0006\u0010\u0014\u001a\u00020\u000fJ\u0010\u0010\u0006\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0016\u0010\u0015\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/CompositionImpl$RememberEventDispatcher;", "Landroidx/compose/runtime/RememberManager;", "abandoning", "", "Landroidx/compose/runtime/RememberObserver;", "(Ljava/util/Set;)V", "forgetting", "", "", "releasing", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "remembering", "sideEffects", "Lkotlin/Function0;", "", "deactivating", "instance", "dispatchAbandons", "dispatchRememberObservers", "dispatchSideEffects", "sideEffect", "effect", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes.dex */
    public static final class RememberEventDispatcher implements RememberManager {
        private final Set<RememberObserver> abandoning;
        private MutableScatterSet<ComposeNodeLifecycleCallback> releasing;
        private final List<RememberObserver> remembering = new ArrayList();
        private final List<Object> forgetting = new ArrayList();
        private final List<Function0<Unit>> sideEffects = new ArrayList();

        public RememberEventDispatcher(Set<RememberObserver> set) {
            this.abandoning = set;
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver instance) {
            this.remembering.add(instance);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver instance) {
            this.forgetting.add(instance);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(Function0<Unit> effect) {
            this.sideEffects.add(effect);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void deactivating(ComposeNodeLifecycleCallback instance) {
            this.forgetting.add(instance);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void releasing(ComposeNodeLifecycleCallback instance) {
            MutableScatterSet it = this.releasing;
            if (it == null) {
                it = ScatterSetKt.mutableScatterSetOf();
                this.releasing = it;
            }
            it.plusAssign((MutableScatterSet) instance);
            this.forgetting.add(instance);
        }

        public final void dispatchRememberObservers() {
            Object token$iv;
            if (!this.forgetting.isEmpty()) {
                token$iv = Trace.INSTANCE.beginSection("Compose:onForgotten");
                try {
                    MutableScatterSet releasing = this.releasing;
                    for (int i = this.forgetting.size() - 1; -1 < i; i--) {
                        Object instance = this.forgetting.get(i);
                        TypeIntrinsics.asMutableCollection(this.abandoning).remove(instance);
                        if (instance instanceof RememberObserver) {
                            ((RememberObserver) instance).onForgotten();
                        }
                        if (instance instanceof ComposeNodeLifecycleCallback) {
                            if (releasing != null && releasing.contains(instance)) {
                                ((ComposeNodeLifecycleCallback) instance).onRelease();
                            } else {
                                ((ComposeNodeLifecycleCallback) instance).onDeactivate();
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                }
            }
            if (!this.remembering.isEmpty()) {
                token$iv = Trace.INSTANCE.beginSection("Compose:onRemembered");
                try {
                    List $this$fastForEach$iv = this.remembering;
                    int size = $this$fastForEach$iv.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                        RememberObserver instance2 = (RememberObserver) item$iv;
                        this.abandoning.remove(instance2);
                        instance2.onRemembered();
                    }
                    Unit unit2 = Unit.INSTANCE;
                } finally {
                }
            }
        }

        public final void dispatchSideEffects() {
            if (!this.sideEffects.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:sideeffects");
                try {
                    List $this$fastForEach$iv = this.sideEffects;
                    int size = $this$fastForEach$iv.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                        Function0 sideEffect = (Function0) item$iv;
                        sideEffect.invoke();
                    }
                    this.sideEffects.clear();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
        }

        public final void dispatchAbandons() {
            if (!this.abandoning.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:abandons");
                try {
                    Iterator iterator = this.abandoning.iterator();
                    while (iterator.hasNext()) {
                        RememberObserver instance = iterator.next();
                        iterator.remove();
                        instance.onAbandoned();
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        boolean nonEmptySlotTable = this.slotTable.getGroupsSize() > 0;
        if (nonEmptySlotTable || (true ^ this.abandonSet.isEmpty())) {
            Object token$iv = Trace.INSTANCE.beginSection("Compose:deactivate");
            try {
                RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
                if (nonEmptySlotTable) {
                    this.applier.onBeginChanges();
                    SlotTable this_$iv = this.slotTable;
                    SlotWriter writer$iv = this_$iv.openWriter();
                    try {
                        ComposerKt.deactivateCurrentGroup(writer$iv, manager);
                        Unit unit = Unit.INSTANCE;
                        writer$iv.close();
                        this.applier.onEndChanges();
                        manager.dispatchRememberObservers();
                    } catch (Throwable th) {
                        writer$iv.close();
                        throw th;
                    }
                }
                manager.dispatchAbandons();
                Unit unit2 = Unit.INSTANCE;
            } finally {
                Trace.INSTANCE.endSection(token$iv);
            }
        }
        this.observations.clear();
        this.derivedStates.clear();
        this.invalidations.clear();
        this.changes.clear();
        this.composer.deactivate$runtime_release();
    }
}
