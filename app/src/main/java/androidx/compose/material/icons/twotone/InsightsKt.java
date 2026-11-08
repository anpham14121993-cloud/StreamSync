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

/* compiled from: Insights.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_insights", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Insights", "Landroidx/compose/material/icons/Icons$TwoTone;", "getInsights", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class InsightsKt {
    private static ImageVector _insights;

    public static final ImageVector getInsights(Icons.TwoTone $this$Insights) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_insights != null) {
            ImageVector imageVector = _insights;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_Insights__u24lambda_u243 = new ImageVector.Builder("TwoTone.Insights", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(21.0f, 8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-1.45f, 0.0f, -2.26f, 1.44f, -1.93f, 2.51f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-3.55f, 3.56f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.3f, -0.09f, -0.74f, -0.09f, -1.04f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-2.55f, -2.55f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(12.27f, 10.45f, 11.46f, 9.0f, 10.0f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-1.45f, 0.0f, -2.27f, 1.44f, -1.93f, 2.52f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-4.56f, 4.55f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(2.44f, 15.74f, 1.0f, 16.55f, 1.0f, 18.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.45f, 0.0f, 2.26f, -1.44f, 1.93f, -2.51f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(4.55f, -4.56f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.3f, 0.09f, 0.74f, 0.09f, 1.04f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(2.55f, 2.55f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(12.73f, 16.55f, 13.54f, 18.0f, 15.0f, 18.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.45f, 0.0f, 2.27f, -1.44f, 1.93f, -2.52f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(3.56f, -3.55f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(21.56f, 12.26f, 23.0f, 11.45f, 23.0f, 10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(23.0f, 8.9f, 22.1f, 8.0f, 21.0f, 8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$_get_Insights__u24lambda_u243.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv2 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv2 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv2 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv2 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv2 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(15.0f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(0.94f, -2.07f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(2.06f, -0.93f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-2.06f, -0.93f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-0.94f, -2.07f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-0.92f, 2.07f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-2.08f, 0.93f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(2.08f, 0.93f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$_get_Insights__u24lambda_u243.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv2.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv2, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv2, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv2, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv2, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv3 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv3 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv3 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv3 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv3 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv3.moveTo(3.5f, 11.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(0.5f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(2.0f, -0.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(-2.0f, -0.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(-0.5f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(-0.5f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(-2.0f, 0.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(2.0f, 0.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.close();
        m4044addPathoIyEayM = $this$_get_Insights__u24lambda_u243.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv3.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv3, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv3, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv3, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv3, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _insights = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _insights;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
