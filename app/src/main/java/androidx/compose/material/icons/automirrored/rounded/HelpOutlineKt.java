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

/* compiled from: HelpOutline.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_helpOutline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HelpOutline", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getHelpOutline", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class HelpOutlineKt {
    private static ImageVector _helpOutline;

    public static final ImageVector getHelpOutline(Icons.AutoMirrored.Rounded $this$HelpOutline) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_helpOutline != null) {
            ImageVector imageVector = _helpOutline;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_HelpOutline__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.Rounded.HelpOutline", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.0f, 20.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.reflectiveCurveToRelative(-3.59f, 8.0f, -8.0f, 8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(11.0f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(-2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.61f, 6.04f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-2.06f, -0.3f, -3.88f, 0.97f, -4.43f, 2.79f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.18f, 0.58f, 0.26f, 1.17f, 0.87f, 1.17f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(0.2f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.41f, 0.0f, 0.74f, -0.29f, 0.88f, -0.67f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.32f, -0.89f, 1.27f, -1.5f, 2.3f, -1.28f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.95f, 0.2f, 1.65f, 1.13f, 1.57f, 2.1f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.1f, 1.34f, -1.62f, 1.63f, -2.45f, 2.88f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 0.01f, -0.01f, 0.01f, -0.01f, 0.02f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.01f, 0.02f, -0.02f, 0.03f, -0.03f, 0.05f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.09f, 0.15f, -0.18f, 0.32f, -0.25f, 0.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.01f, 0.03f, -0.03f, 0.05f, -0.04f, 0.08f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.01f, 0.02f, -0.01f, 0.04f, -0.02f, 0.07f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.12f, 0.34f, -0.2f, 0.75f, -0.2f, 1.25f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, -0.42f, 0.11f, -0.77f, 0.28f, -1.07f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.02f, -0.03f, 0.03f, -0.06f, 0.05f, -0.09f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.08f, -0.14f, 0.18f, -0.27f, 0.28f, -0.39f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.01f, -0.01f, 0.02f, -0.03f, 0.03f, -0.04f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.1f, -0.12f, 0.21f, -0.23f, 0.33f, -0.34f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.96f, -0.91f, 2.26f, -1.65f, 1.99f, -3.56f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.24f, -1.74f, -1.61f, -3.21f, -3.35f, -3.47f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        m4044addPathoIyEayM = $this$_get_HelpOutline__u24lambda_u241.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _helpOutline = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _helpOutline;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
