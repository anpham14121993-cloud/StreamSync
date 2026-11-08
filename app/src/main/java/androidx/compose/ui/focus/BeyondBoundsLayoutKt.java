package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeyondBoundsLayout.kt */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aA\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"searchBeyondBounds", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class BeyondBoundsLayoutKt {
    /* renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m3084searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> function1) {
        Modifier.Node node;
        T t;
        int m4666getBeforehoxUOeE;
        FocusTargetNode focusTargetNode2;
        int i2;
        int i3;
        FocusTargetNode focusTargetNode3;
        NodeChain nodes;
        int i4;
        int i5;
        FocusTargetNode focusTargetNode4;
        int i6;
        int i7;
        FocusTargetNode focusTargetNode5;
        int i8;
        MutableVector mutableVector;
        FocusTargetNode focusTargetNode6 = focusTargetNode;
        int m4886constructorimpl = NodeKind.m4886constructorimpl(1024);
        int i9 = 0;
        FocusTargetNode focusTargetNode7 = focusTargetNode6;
        if (!focusTargetNode7.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node parent = focusTargetNode7.getNode().getParent();
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode7);
        loop0: while (true) {
            if (requireLayoutNode != null) {
                if ((requireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & m4886constructorimpl) == 0) {
                    focusTargetNode2 = focusTargetNode6;
                    i2 = m4886constructorimpl;
                    i3 = i9;
                    focusTargetNode3 = focusTargetNode7;
                } else {
                    while (parent != null) {
                        if ((parent.getKindSet() & m4886constructorimpl) != 0) {
                            MutableVector mutableVector2 = null;
                            Modifier.Node node2 = parent;
                            while (node2 != null) {
                                FocusTargetNode focusTargetNode8 = focusTargetNode6;
                                if (node2 instanceof FocusTargetNode) {
                                    node = node2;
                                    break loop0;
                                }
                                if (((node2.getKindSet() & m4886constructorimpl) != 0 ? 1 : 0) == 0 || !(node2 instanceof DelegatingNode)) {
                                    i4 = m4886constructorimpl;
                                    i5 = i9;
                                    focusTargetNode4 = focusTargetNode7;
                                } else {
                                    int i10 = 0;
                                    Modifier.Node delegate = ((DelegatingNode) node2).getDelegate();
                                    while (delegate != null) {
                                        Modifier.Node node3 = delegate;
                                        if (!((node3.getKindSet() & m4886constructorimpl) != 0)) {
                                            i6 = m4886constructorimpl;
                                            i7 = i9;
                                            focusTargetNode5 = focusTargetNode7;
                                        } else {
                                            i10++;
                                            i6 = m4886constructorimpl;
                                            if (i10 == 1) {
                                                node2 = node3;
                                                i7 = i9;
                                                focusTargetNode5 = focusTargetNode7;
                                            } else {
                                                if (mutableVector2 != null) {
                                                    i8 = i10;
                                                    i7 = i9;
                                                    focusTargetNode5 = focusTargetNode7;
                                                    mutableVector = mutableVector2;
                                                } else {
                                                    i8 = i10;
                                                    i7 = i9;
                                                    focusTargetNode5 = focusTargetNode7;
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                MutableVector mutableVector3 = mutableVector;
                                                Modifier.Node node4 = node2;
                                                if (node4 != null) {
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(node4);
                                                    }
                                                    node2 = null;
                                                }
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(node3);
                                                }
                                                mutableVector2 = mutableVector3;
                                                i10 = i8;
                                            }
                                        }
                                        delegate = delegate.getChild();
                                        m4886constructorimpl = i6;
                                        i9 = i7;
                                        focusTargetNode7 = focusTargetNode5;
                                    }
                                    i4 = m4886constructorimpl;
                                    i5 = i9;
                                    focusTargetNode4 = focusTargetNode7;
                                    if (i10 == 1) {
                                        focusTargetNode6 = focusTargetNode8;
                                        m4886constructorimpl = i4;
                                        i9 = i5;
                                        focusTargetNode7 = focusTargetNode4;
                                    }
                                }
                                node2 = DelegatableNodeKt.pop(mutableVector2);
                                focusTargetNode6 = focusTargetNode8;
                                m4886constructorimpl = i4;
                                i9 = i5;
                                focusTargetNode7 = focusTargetNode4;
                            }
                        }
                        parent = parent.getParent();
                        focusTargetNode6 = focusTargetNode6;
                        m4886constructorimpl = m4886constructorimpl;
                        i9 = i9;
                        focusTargetNode7 = focusTargetNode7;
                    }
                    focusTargetNode2 = focusTargetNode6;
                    i2 = m4886constructorimpl;
                    i3 = i9;
                    focusTargetNode3 = focusTargetNode7;
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                parent = (requireLayoutNode == null || (nodes = requireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
                focusTargetNode6 = focusTargetNode2;
                m4886constructorimpl = i2;
                i9 = i3;
                focusTargetNode7 = focusTargetNode3;
            } else {
                node = null;
                break;
            }
        }
        FocusTargetNode focusTargetNode9 = (FocusTargetNode) node;
        if (focusTargetNode9 == null) {
            t = null;
        } else {
            if (Intrinsics.areEqual(focusTargetNode9.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) {
                return null;
            }
            t = null;
        }
        BeyondBoundsLayout beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent();
        if (beyondBoundsLayoutParent == null) {
            return t;
        }
        if (FocusDirection.m3088equalsimpl0(i, FocusDirection.INSTANCE.m3101getUpdhqQ8s())) {
            m4666getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4664getAbovehoxUOeE();
        } else if (FocusDirection.m3088equalsimpl0(i, FocusDirection.INSTANCE.m3094getDowndhqQ8s())) {
            m4666getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4667getBelowhoxUOeE();
        } else if (FocusDirection.m3088equalsimpl0(i, FocusDirection.INSTANCE.m3097getLeftdhqQ8s())) {
            m4666getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4668getLefthoxUOeE();
        } else if (FocusDirection.m3088equalsimpl0(i, FocusDirection.INSTANCE.m3100getRightdhqQ8s())) {
            m4666getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4669getRighthoxUOeE();
        } else if (FocusDirection.m3088equalsimpl0(i, FocusDirection.INSTANCE.m3098getNextdhqQ8s())) {
            m4666getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4665getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m3088equalsimpl0(i, FocusDirection.INSTANCE.m3099getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout".toString());
            }
            m4666getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m4666getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent.mo724layouto7g1Pn8(m4666getBeforehoxUOeE, function1);
    }
}
