package androidx.compose.runtime;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SnapshotFlow.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a?\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\b\b\u0000\u0010\u0002*\u0002H\u0007\"\u0004\b\u0001\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u0002H\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000b\u001a-\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\r\u001a%\u0010\u000e\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0002\b\u0014¨\u0006\u0015"}, d2 = {"snapshotFlow", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "collectAsState", "Landroidx/compose/runtime/State;", "R", "initial", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Lkotlinx/coroutines/flow/StateFlow;", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "intersects", "", "Landroidx/collection/MutableScatterSet;", "", "set", "", "intersects$SnapshotStateKt__SnapshotFlowKt", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB, xs = "androidx/compose/runtime/SnapshotStateKt")
/* loaded from: classes.dex */
public final /* synthetic */ class SnapshotStateKt__SnapshotFlowKt {
    public static final <T> State<T> collectAsState(StateFlow<? extends T> stateFlow, CoroutineContext context, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(-1439883919);
        ComposerKt.sourceInformation($composer, "C(collectAsState)46@1796L30:SnapshotFlow.kt#9igjgp");
        if ((i & 1) != 0) {
            CoroutineContext context2 = EmptyCoroutineContext.INSTANCE;
            context = context2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1439883919, $changed, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:46)");
        }
        State<T> collectAsState = SnapshotStateKt.collectAsState(stateFlow, stateFlow.getValue(), context, $composer, 520, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return collectAsState;
    }

    public static final <T extends R, R> State<R> collectAsState(Flow<? extends T> flow, R r, CoroutineContext context, Composer $composer, int $changed, int i) {
        $composer.startReplaceableGroup(-606625098);
        ComposerKt.sourceInformation($composer, "C(collectAsState)P(1)61@2338L186:SnapshotFlow.kt#9igjgp");
        if ((i & 2) != 0) {
            CoroutineContext context2 = EmptyCoroutineContext.INSTANCE;
            context = context2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-606625098, $changed, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:61)");
        }
        State<R> produceState = SnapshotStateKt.produceState(r, flow, context, new SnapshotStateKt__SnapshotFlowKt$collectAsState$1(context, flow, null), $composer, (($changed >> 3) & 8) | 4672 | (($changed >> 3) & 14));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return produceState;
    }

    public static final <T> Flow<T> snapshotFlow(Function0<? extends T> function0) {
        return FlowKt.flow(new SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1(function0, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean intersects$SnapshotStateKt__SnapshotFlowKt(MutableScatterSet<Object> mutableScatterSet, Set<? extends Object> set) {
        ScatterSet this_$iv;
        ScatterSet this_$iv2;
        MutableScatterSet<Object> this_$iv3 = mutableScatterSet;
        Object[] k$iv$iv = this_$iv3.elements;
        long[] m$iv$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                long slot$iv$iv$iv2 = slot$iv$iv$iv;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv = 0;
                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv2 & 255;
                        if (value$iv$iv$iv$iv < 128) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object element$iv = k$iv$iv[index$iv$iv$iv];
                            this_$iv2 = this_$iv3;
                            if (set.contains(element$iv)) {
                                return true;
                            }
                        } else {
                            this_$iv2 = this_$iv3;
                        }
                        slot$iv$iv$iv2 >>= 8;
                        j$iv$iv$iv++;
                        i = 8;
                        this_$iv3 = this_$iv2;
                    }
                    this_$iv = this_$iv3;
                    if (bitCount$iv$iv$iv != i) {
                        break;
                    }
                } else {
                    this_$iv = this_$iv3;
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                this_$iv3 = this_$iv;
            }
        }
        return false;
    }
}
