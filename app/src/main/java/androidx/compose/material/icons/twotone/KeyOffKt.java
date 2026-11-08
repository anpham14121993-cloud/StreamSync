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

/* compiled from: KeyOff.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_keyOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "KeyOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getKeyOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class KeyOffKt {
    private static ImageVector _keyOff;

    public static final ImageVector getKeyOff(Icons.TwoTone $this$KeyOff) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_keyOff != null) {
            ImageVector imageVector = _keyOff;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_KeyOff__u24lambda_u242 = new ImageVector.Builder("TwoTone.KeyOff", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(10.7f, 13.53f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-1.71f, -1.71f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(9.0f, 11.88f, 9.0f, 11.94f, 9.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.06f, 0.0f, 0.12f, 0.0f, 0.18f, 0.01f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(5.47f, 8.3f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(4.02f, 8.9f, 3.0f, 10.33f, 3.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(8.67f, 16.0f, 10.1f, 14.98f, 10.7f, 13.53f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(16.26f, 13.43f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(1.24f, -0.93f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(1.81f, 1.36f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(21.17f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-1.0f, -1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-6.34f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(16.26f, 13.43f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$_get_KeyOff__u24lambda_u242.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 0.3f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 0.3f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv2 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv2 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv2 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv2 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv2 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(10.7f, 13.53f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-1.71f, -1.71f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveTo(9.0f, 11.88f, 9.0f, 11.94f, 9.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.06f, 0.0f, 0.12f, 0.0f, 0.18f, 0.01f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(5.47f, 8.3f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveTo(4.02f, 8.9f, 3.0f, 10.33f, 3.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveTo(8.67f, 16.0f, 10.1f, 14.98f, 10.7f, 13.53f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(12.19f, 15.02f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveTo(11.15f, 16.8f, 9.21f, 18.0f, 7.0f, 18.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.0f, -2.21f, 1.2f, -4.15f, 2.98f, -5.19f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(1.39f, 4.22f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.41f, -1.41f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(18.38f, 18.38f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-1.41f, 1.41f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(12.19f, 15.02f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(16.26f, 13.43f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.24f, -0.93f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.81f, 1.36f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(21.17f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-1.0f, -1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-6.34f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(21.0f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(0.0f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(3.0f, 3.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-4.5f, 4.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-0.69f, -0.51f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(16.26f, 13.43f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        m4044addPathoIyEayM = $this$_get_KeyOff__u24lambda_u242.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv2.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv2, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv2, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv2, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv2, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _keyOff = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _keyOff;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
