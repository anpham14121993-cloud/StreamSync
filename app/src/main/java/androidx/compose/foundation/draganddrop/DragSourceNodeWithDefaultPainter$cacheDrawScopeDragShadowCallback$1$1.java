package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AndroidDragAndDropSource.android.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public /* synthetic */ class DragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1 extends FunctionReferenceImpl implements Function1<CacheDrawScope, DrawResult> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DragSourceNodeWithDefaultPainter$cacheDrawScopeDragShadowCallback$1$1(Object obj) {
        super(1, obj, CacheDrawScopeDragShadowCallback.class, "cachePicture", "cachePicture(Landroidx/compose/ui/draw/CacheDrawScope;)Landroidx/compose/ui/draw/DrawResult;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final DrawResult invoke(CacheDrawScope p0) {
        return ((CacheDrawScopeDragShadowCallback) this.receiver).cachePicture(p0);
    }
}
