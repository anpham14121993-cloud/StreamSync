package androidx.compose.material.icons.automirrored.sharp;

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

/* compiled from: Redo.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_redo", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Redo", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getRedo", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class RedoKt {
    private static ImageVector _redo;

    public static final ImageVector getRedo(Icons.AutoMirrored.Sharp $this$Redo) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_redo != null) {
            ImageVector imageVector = _redo;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_Redo__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.Sharp.Redo", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(18.4f, 10.6f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(16.55f, 8.99f, 14.15f, 8.0f, 11.5f, 8.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-4.65f, 0.0f, -8.58f, 3.03f, -9.96f, 7.22f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(3.9f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.05f, -3.19f, 4.05f, -5.5f, 7.6f, -5.5f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.95f, 0.0f, 3.73f, 0.72f, 5.12f, 1.88f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(13.0f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineTo(7.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-3.6f, 3.6f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        m4044addPathoIyEayM = $this$_get_Redo__u24lambda_u241.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _redo = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _redo;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
