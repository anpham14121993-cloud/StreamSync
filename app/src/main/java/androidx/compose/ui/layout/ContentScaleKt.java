package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Size;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: ContentScale.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0006\u001a\"\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u0006\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"computeFillHeight", "", "srcSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "computeFillHeight-iLBOSCw", "(JJ)F", "computeFillMaxDimension", "computeFillMaxDimension-iLBOSCw", "computeFillMinDimension", "computeFillMinDimension-iLBOSCw", "computeFillWidth", "computeFillWidth-iLBOSCw", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ContentScaleKt {
    /* renamed from: access$computeFillHeight-iLBOSCw, reason: not valid java name */
    public static final /* synthetic */ float m4672access$computeFillHeightiLBOSCw(long srcSize, long dstSize) {
        return m4676computeFillHeightiLBOSCw(srcSize, dstSize);
    }

    /* renamed from: access$computeFillMaxDimension-iLBOSCw, reason: not valid java name */
    public static final /* synthetic */ float m4673access$computeFillMaxDimensioniLBOSCw(long srcSize, long dstSize) {
        return m4677computeFillMaxDimensioniLBOSCw(srcSize, dstSize);
    }

    /* renamed from: access$computeFillMinDimension-iLBOSCw, reason: not valid java name */
    public static final /* synthetic */ float m4674access$computeFillMinDimensioniLBOSCw(long srcSize, long dstSize) {
        return m4678computeFillMinDimensioniLBOSCw(srcSize, dstSize);
    }

    /* renamed from: access$computeFillWidth-iLBOSCw, reason: not valid java name */
    public static final /* synthetic */ float m4675access$computeFillWidthiLBOSCw(long srcSize, long dstSize) {
        return m4679computeFillWidthiLBOSCw(srcSize, dstSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: computeFillMaxDimension-iLBOSCw, reason: not valid java name */
    public static final float m4677computeFillMaxDimensioniLBOSCw(long srcSize, long dstSize) {
        float widthScale = m4679computeFillWidthiLBOSCw(srcSize, dstSize);
        float heightScale = m4676computeFillHeightiLBOSCw(srcSize, dstSize);
        return Math.max(widthScale, heightScale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: computeFillMinDimension-iLBOSCw, reason: not valid java name */
    public static final float m4678computeFillMinDimensioniLBOSCw(long srcSize, long dstSize) {
        float widthScale = m4679computeFillWidthiLBOSCw(srcSize, dstSize);
        float heightScale = m4676computeFillHeightiLBOSCw(srcSize, dstSize);
        return Math.min(widthScale, heightScale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: computeFillWidth-iLBOSCw, reason: not valid java name */
    public static final float m4679computeFillWidthiLBOSCw(long srcSize, long dstSize) {
        return Size.m3237getWidthimpl(dstSize) / Size.m3237getWidthimpl(srcSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: computeFillHeight-iLBOSCw, reason: not valid java name */
    public static final float m4676computeFillHeightiLBOSCw(long srcSize, long dstSize) {
        return Size.m3234getHeightimpl(dstSize) / Size.m3234getHeightimpl(srcSize);
    }
}
