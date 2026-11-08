package androidx.compose.material.icons.automirrored.rounded;

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

/* compiled from: NextPlan.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nextPlan", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NextPlan", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getNextPlan", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class NextPlanKt {
    private static ImageVector _nextPlan;

    public static final ImageVector getNextPlan(Icons.AutoMirrored.Rounded $this$NextPlan) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_nextPlan != null) {
            ImageVector imageVector = _nextPlan;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_NextPlan__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.Rounded.NextPlan", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(18.0f, 13.97f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(-5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(2.26f, -2.26f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.91f, -1.06f, -2.25f, -1.74f, -3.76f, -1.74f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-2.37f, 0.0f, -4.35f, 1.66f, -4.86f, 3.88f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-0.96f, -0.32f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.64f, -2.62f, 3.0f, -4.56f, 5.82f, -4.56f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.78f, 0.0f, 3.37f, 0.79f, 4.47f, 2.03f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(18.0f, 8.97f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineTo(13.97f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        m4044addPathoIyEayM = $this$_get_NextPlan__u24lambda_u241.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _nextPlan = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _nextPlan;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
