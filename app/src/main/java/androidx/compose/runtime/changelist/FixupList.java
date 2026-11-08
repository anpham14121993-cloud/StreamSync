package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: FixupList.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ&\u0010\f\u001a\u00020\u000b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u000bJ\"\u0010\u0014\u001a\u00020\u000b2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0016J>\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010#2\u0006\u0010$\u001a\u0002H\"2\u001d\u0010%\u001a\u0019\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u00020\u000b0&¢\u0006\u0002\b'¢\u0006\u0002\u0010(R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006)"}, d2 = {"Landroidx/compose/runtime/changelist/FixupList;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "()V", "operations", "Landroidx/compose/runtime/changelist/Operations;", "pendingOperations", "size", "", "getSize", "()I", "clear", "", "createAndInsertNode", "factory", "Lkotlin/Function0;", "", "insertIndex", "groupAnchor", "Landroidx/compose/runtime/Anchor;", "endNodeInsert", "executeAndFlushAllPendingFixups", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "isEmpty", "", "isNotEmpty", "toDebugString", "", "linePrefix", "updateNode", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.GPS_DIRECTION_TRUE, "value", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class FixupList implements OperationsDebugStringFormattable {
    public static final int $stable = 8;
    private final Operations operations = new Operations();
    private final Operations pendingOperations = new Operations();

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    public final void clear() {
        this.pendingOperations.clear();
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingFixups(Applier<?> applier, SlotWriter slots, RememberManager rememberManager) {
        boolean value$iv = this.pendingOperations.isEmpty();
        if (value$iv) {
            this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager);
        } else {
            ComposerKt.composeRuntimeError("FixupList has pending fixup operations that were not realized. Were there mismatched insertNode() and endNodeInsert() calls?".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void createAndInsertNode(Function0<? extends Object> factory, int insertIndex, Anchor groupAnchor) {
        Operations this_$iv;
        int i;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv;
        int i2;
        Operations this_$iv2;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv;
        int i3;
        int i4;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv2;
        Operations this_$iv3 = this.operations;
        Operation operation$iv = Operation.InsertNodeFixup.INSTANCE;
        this_$iv3.pushOp(operation$iv);
        Operations $this$createAndInsertNode_u24lambda_u241 = Operations.WriteScope.m3016constructorimpl(this_$iv3);
        Operation.InsertNodeFixup insertNodeFixup = Operation.InsertNodeFixup.INSTANCE;
        Operations.WriteScope.m3022setObjectDKhxnng($this$createAndInsertNode_u24lambda_u241, Operation.ObjectParameter.m2988constructorimpl(0), factory);
        Operation.InsertNodeFixup insertNodeFixup2 = Operation.InsertNodeFixup.INSTANCE;
        Operations.WriteScope.m3021setIntA6tL2VI($this$createAndInsertNode_u24lambda_u241, Operation.IntParameter.m2977constructorimpl(0), insertIndex);
        Operation.InsertNodeFixup insertNodeFixup3 = Operation.InsertNodeFixup.INSTANCE;
        Operations.WriteScope.m3022setObjectDKhxnng($this$createAndInsertNode_u24lambda_u241, Operation.ObjectParameter.m2988constructorimpl(1), groupAnchor);
        if (!(this_$iv3.pushedIntMask == this_$iv3.createExpectedArgMask(operation$iv.getInts()) && this_$iv3.pushedObjectMask == this_$iv3.createExpectedArgMask(operation$iv.getObjects()))) {
            StringBuilder sb = new StringBuilder();
            StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv3 = sb;
            int $i$f$push = operation$iv.getInts();
            int missingIntCount$iv = 0;
            int i5 = 0;
            while (i5 < $i$f$push) {
                int arg$iv = i5;
                if (((1 << arg$iv) & this_$iv3.pushedIntMask) != 0) {
                    if (missingIntCount$iv > 0) {
                        i4 = $i$f$push;
                        $this$push_u24lambda_u246_u24lambda_u243$iv2 = $this$push_u24lambda_u246_u24lambda_u243$iv3;
                        $this$push_u24lambda_u246_u24lambda_u243$iv2.append(", ");
                    } else {
                        i4 = $i$f$push;
                        $this$push_u24lambda_u246_u24lambda_u243$iv2 = $this$push_u24lambda_u246_u24lambda_u243$iv3;
                    }
                    $this$push_u24lambda_u246_u24lambda_u243$iv2.append(operation$iv.mo2951intParamNamew8GmfQM(Operation.IntParameter.m2977constructorimpl(arg$iv)));
                    missingIntCount$iv++;
                } else {
                    i4 = $i$f$push;
                    $this$push_u24lambda_u246_u24lambda_u243$iv2 = $this$push_u24lambda_u246_u24lambda_u243$iv3;
                }
                i5++;
                $this$push_u24lambda_u246_u24lambda_u243$iv3 = $this$push_u24lambda_u246_u24lambda_u243$iv2;
                $i$f$push = i4;
            }
            String missingInts$iv = sb.toString();
            Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder();
            StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv2 = sb2;
            int missingObjectCount$iv = operation$iv.getObjects();
            int missingObjectCount$iv2 = 0;
            int i6 = 0;
            while (i6 < missingObjectCount$iv) {
                int arg$iv2 = i6;
                if (((1 << arg$iv2) & this_$iv3.pushedObjectMask) != 0) {
                    if (missingIntCount$iv > 0) {
                        this_$iv2 = this_$iv3;
                        $this$push_u24lambda_u246_u24lambda_u245$iv = $this$push_u24lambda_u246_u24lambda_u245$iv2;
                        $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                    } else {
                        this_$iv2 = this_$iv3;
                        $this$push_u24lambda_u246_u24lambda_u245$iv = $this$push_u24lambda_u246_u24lambda_u245$iv2;
                    }
                    i3 = missingObjectCount$iv;
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo2952objectParamName31yXWZQ(Operation.ObjectParameter.m2988constructorimpl(arg$iv2)));
                    missingObjectCount$iv2++;
                } else {
                    this_$iv2 = this_$iv3;
                    $this$push_u24lambda_u246_u24lambda_u245$iv = $this$push_u24lambda_u246_u24lambda_u245$iv2;
                    i3 = missingObjectCount$iv;
                }
                i6++;
                missingObjectCount$iv = i3;
                $this$push_u24lambda_u246_u24lambda_u245$iv2 = $this$push_u24lambda_u246_u24lambda_u245$iv;
                this_$iv3 = this_$iv2;
            }
            String missingObjects$iv = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
            throw new IllegalStateException(("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv2 + " object arguments (" + missingObjects$iv + ").").toString());
        }
        Operations this_$iv4 = this.pendingOperations;
        Operation operation$iv2 = Operation.PostInsertNodeFixup.INSTANCE;
        this_$iv4.pushOp(operation$iv2);
        Operations $this$createAndInsertNode_u24lambda_u242 = Operations.WriteScope.m3016constructorimpl(this_$iv4);
        Operation.PostInsertNodeFixup postInsertNodeFixup = Operation.PostInsertNodeFixup.INSTANCE;
        Operations.WriteScope.m3021setIntA6tL2VI($this$createAndInsertNode_u24lambda_u242, Operation.IntParameter.m2977constructorimpl(0), insertIndex);
        Operation.PostInsertNodeFixup postInsertNodeFixup2 = Operation.PostInsertNodeFixup.INSTANCE;
        Operations.WriteScope.m3022setObjectDKhxnng($this$createAndInsertNode_u24lambda_u242, Operation.ObjectParameter.m2988constructorimpl(0), groupAnchor);
        if (this_$iv4.pushedIntMask == this_$iv4.createExpectedArgMask(operation$iv2.getInts()) && this_$iv4.pushedObjectMask == this_$iv4.createExpectedArgMask(operation$iv2.getObjects())) {
            return;
        }
        int i7 = 0;
        int missingIntCount$iv2 = 0;
        StringBuilder sb3 = new StringBuilder();
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv4 = sb3;
        int ints = operation$iv2.getInts();
        int $i$f$push2 = 0;
        while ($i$f$push2 < ints) {
            int arg$iv3 = $i$f$push2;
            if (((1 << arg$iv3) & this_$iv4.pushedIntMask) != 0) {
                if (missingIntCount$iv2 > 0) {
                    i = ints;
                    $this$push_u24lambda_u246_u24lambda_u243$iv = $this$push_u24lambda_u246_u24lambda_u243$iv4;
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                } else {
                    i = ints;
                    $this$push_u24lambda_u246_u24lambda_u243$iv = $this$push_u24lambda_u246_u24lambda_u243$iv4;
                }
                i2 = i7;
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv2.mo2951intParamNamew8GmfQM(Operation.IntParameter.m2977constructorimpl(arg$iv3)));
                missingIntCount$iv2++;
            } else {
                i = ints;
                $this$push_u24lambda_u246_u24lambda_u243$iv = $this$push_u24lambda_u246_u24lambda_u243$iv4;
                i2 = i7;
            }
            $i$f$push2++;
            i7 = i2;
            $this$push_u24lambda_u246_u24lambda_u243$iv4 = $this$push_u24lambda_u246_u24lambda_u243$iv;
            ints = i;
        }
        String missingInts$iv2 = sb3.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv2, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv3 = new StringBuilder();
        int missingObjectCount$iv3 = operation$iv2.getObjects();
        int missingObjectCount$iv4 = 0;
        int i8 = 0;
        while (i8 < missingObjectCount$iv3) {
            int arg$iv4 = i8;
            if (((1 << arg$iv4) & this_$iv4.pushedObjectMask) != 0) {
                if (missingIntCount$iv2 > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv3.append(", ");
                }
                this_$iv = this_$iv4;
                $this$push_u24lambda_u246_u24lambda_u245$iv3.append(operation$iv2.mo2952objectParamName31yXWZQ(Operation.ObjectParameter.m2988constructorimpl(arg$iv4)));
                missingObjectCount$iv4++;
            } else {
                this_$iv = this_$iv4;
            }
            i8++;
            this_$iv4 = this_$iv;
        }
        String missingObjects$iv2 = $this$push_u24lambda_u246_u24lambda_u245$iv3.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv2, "StringBuilder().apply(builderAction).toString()");
        throw new IllegalStateException(("Error while pushing " + operation$iv2 + ". Not all arguments were provided. Missing " + missingIntCount$iv2 + " int arguments (" + missingInts$iv2 + ") and " + missingObjectCount$iv4 + " object arguments (" + missingObjects$iv2 + ").").toString());
    }

    public final void endNodeInsert() {
        boolean value$iv = this.pendingOperations.isNotEmpty();
        if (value$iv) {
            this.pendingOperations.popInto(this.operations);
        } else {
            ComposerKt.composeRuntimeError("Cannot end node insertion, there are no pending operations that can be realized.".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final <V, T> void updateNode(V value, Function2<? super T, ? super V, Unit> block) {
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$updateNode_u24lambda_u244 = Operations.WriteScope.m3016constructorimpl(this_$iv);
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        Operations.WriteScope.m3022setObjectDKhxnng($this$updateNode_u24lambda_u244, Operation.ObjectParameter.m2988constructorimpl(0), value);
        Operation.UpdateNode updateNode2 = Operation.UpdateNode.INSTANCE;
        int i = 1;
        int m2988constructorimpl = Operation.ObjectParameter.m2988constructorimpl(1);
        Intrinsics.checkNotNull(block, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        Operations.WriteScope.m3022setObjectDKhxnng($this$updateNode_u24lambda_u244, m2988constructorimpl, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2));
        if (this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects())) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i2 = 0; i2 < ints; i2++) {
            int arg$iv = i2;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo2951intParamNamew8GmfQM(Operation.IntParameter.m2977constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo2952objectParamName31yXWZQ(Operation.ObjectParameter.m2988constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            }
            missingObjectCount$iv2++;
            i = 1;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        throw new IllegalStateException(("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").").toString());
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder $this$toDebugString_u24lambda_u245 = new StringBuilder();
        $this$toDebugString_u24lambda_u245.append("FixupList instance containing " + getSize() + " operations");
        if ($this$toDebugString_u24lambda_u245.length() > 0) {
            $this$toDebugString_u24lambda_u245.append(":\n" + this.operations.toDebugString(linePrefix));
        }
        String sb = $this$toDebugString_u24lambda_u245.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
