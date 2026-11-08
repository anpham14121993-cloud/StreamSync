package androidx.compose.material.icons.twotone;

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

/* compiled from: LocalTaxi.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localTaxi", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalTaxi", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalTaxi", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class LocalTaxiKt {
    private static ImageVector _localTaxi;

    public static final ImageVector getLocalTaxi(Icons.TwoTone $this$LocalTaxi) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_localTaxi != null) {
            ImageVector imageVector = _localTaxi;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_LocalTaxi__u24lambda_u244 = new ImageVector.Builder("TwoTone.LocalTaxi", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(5.12f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-0.12f, 0.34f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(5.0f, 17.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(14.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(-4.66f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-0.12f, -0.34f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(5.12f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(7.5f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveTo(6.67f, 13.0f, 7.5f, 13.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveTo(8.33f, 16.0f, 7.5f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(16.5f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$_get_LocalTaxi__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 0.3f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 0.3f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv2 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv2 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv2 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv2 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv2 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(17.5f, 5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineTo(15.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineTo(3.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineTo(9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineTo(6.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(-0.66f, 0.0f, -1.21f, 0.42f, -1.42f, 1.01f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(3.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(-1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(-8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-2.08f, -5.99f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveTo(18.72f, 5.42f, 18.16f, 5.0f, 17.5f, 5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(6.85f, 7.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(10.29f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.04f, 3.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineTo(5.81f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.04f, -3.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(19.0f, 17.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineTo(5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(-4.66f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(0.12f, -0.34f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(13.77f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(0.11f, 0.34f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineTo(17.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$_get_LocalTaxi__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv2.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv2, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv2, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv2, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv2, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv3 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv3 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv3 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv3 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv3 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv3.moveTo(7.5f, 14.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.moveToRelative(-1.5f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        $this$_get_LocalTaxi__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv3.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv3, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv3, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv3, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv3, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv4 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv4 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv4 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv4 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv4 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv4.moveTo(16.5f, 14.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.moveToRelative(-1.5f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        m4044addPathoIyEayM = $this$_get_LocalTaxi__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv4.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv4, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv4, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv4, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv4, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _localTaxi = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _localTaxi;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
