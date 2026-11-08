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

/* compiled from: Layers.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_layers", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Layers", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLayers", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class LayersKt {
    private static ImageVector _layers;

    public static final ImageVector getLayers(Icons.TwoTone $this$Layers) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_layers != null) {
            ImageVector imageVector = _layers;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_Layers__u24lambda_u242 = new ImageVector.Builder("TwoTone.Layers", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(6.26f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(12.0f, 13.47f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(17.74f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(12.0f, 4.53f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$_get_Layers__u24lambda_u242.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 0.3f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 0.3f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv2 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv2 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv2 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv2 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv2 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(19.37f, 12.8f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-7.38f, 5.74f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-7.37f, -5.73f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(3.0f, 14.07f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(9.0f, 7.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(9.0f, -7.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(12.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(3.0f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.63f, 1.27f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(12.0f, 16.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(7.36f, -5.73f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(21.0f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-9.0f, -7.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(12.0f, 13.47f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(6.26f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(12.0f, 4.53f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(17.74f, 9.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(12.0f, 13.47f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        m4044addPathoIyEayM = $this$_get_Layers__u24lambda_u242.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv2.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv2, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv2, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv2, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv2, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _layers = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _layers;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
