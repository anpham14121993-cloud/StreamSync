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

/* compiled from: Send.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_send", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Send", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getSend", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class SendKt {
    private static ImageVector _send;

    public static final ImageVector getSend(Icons.AutoMirrored.Rounded $this$Send) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_send != null) {
            ImageVector imageVector = _send;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_Send__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.Rounded.Send", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(3.4f, 20.4f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(17.45f, -7.48f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.81f, -0.35f, 0.81f, -1.49f, 0.0f, -1.84f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(3.4f, 3.6f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.66f, -0.29f, -1.39f, 0.2f, -1.39f, 0.91f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(2.0f, 9.12f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 0.5f, 0.37f, 0.93f, 0.87f, 0.99f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(17.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(2.87f, 13.88f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-0.5f, 0.07f, -0.87f, 0.5f, -0.87f, 1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(0.01f, 4.61f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(0.0f, 0.71f, 0.73f, 1.2f, 1.39f, 0.91f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        m4044addPathoIyEayM = $this$_get_Send__u24lambda_u241.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _send = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _send;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
