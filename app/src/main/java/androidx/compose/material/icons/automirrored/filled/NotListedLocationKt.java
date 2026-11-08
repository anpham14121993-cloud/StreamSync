package androidx.compose.material.icons.automirrored.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotListedLocation.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_notListedLocation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NotListedLocation", "Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;", "getNotListedLocation", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class NotListedLocationKt {
    private static ImageVector _notListedLocation;

    public static final ImageVector getNotListedLocation(Icons.AutoMirrored.Filled $this$NotListedLocation) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_notListedLocation != null) {
            ImageVector imageVector = _notListedLocation;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_NotListedLocation__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.Filled.NotListedLocation", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(8.14f, 2.0f, 5.0f, 5.14f, 5.0f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 5.25f, 7.0f, 13.0f, 7.0f, 13.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(7.0f, -7.75f, 7.0f, -13.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, -3.86f, -3.14f, -7.0f, -7.0f, -7.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.88f, 15.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(-1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(11.13f, 14.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.88f, 12.88f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(-1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, -2.84f, 2.62f, -2.62f, 2.62f, -4.38f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, -0.96f, -0.79f, -1.75f, -1.75f, -1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(-1.75f, 0.79f, -1.75f, 1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(8.5f, 8.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(8.5f, 6.57f, 10.07f, 5.0f, 12.0f, 5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 2.19f, -2.62f, 2.41f, -2.62f, 4.38f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        m4044addPathoIyEayM = $this$_get_NotListedLocation__u24lambda_u241.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _notListedLocation = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _notListedLocation;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
