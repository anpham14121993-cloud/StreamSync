package androidx.compose.material.icons.automirrored.outlined;

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

/* compiled from: MobileScreenShare.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mobileScreenShare", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MobileScreenShare", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getMobileScreenShare", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class MobileScreenShareKt {
    private static ImageVector _mobileScreenShare;

    public static final ImageVector getMobileScreenShare(Icons.AutoMirrored.Outlined $this$MobileScreenShare) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_mobileScreenShare != null) {
            ImageVector imageVector = _mobileScreenShare;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_MobileScreenShare__u24lambda_u241 = new ImageVector.Builder("AutoMirrored.Outlined.MobileScreenShare", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(17.0f, 1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(7.0f, 1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-1.1f, 0.0f, -1.99f, 0.85f, -1.99f, 1.95f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(18.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(5.01f, 22.05f, 5.9f, 23.0f, 7.0f, 23.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.1f, 0.0f, 2.0f, -0.95f, 2.0f, -2.05f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(-18.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveTo(19.0f, 1.85f, 18.1f, 1.0f, 17.0f, 1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(17.0f, 19.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(7.0f, 19.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(7.0f, 5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(10.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(14.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(12.8f, 13.24f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(1.75f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineTo(16.0f, 12.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.lineToRelative(-3.2f, -2.98f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(1.7f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(-3.11f, 0.43f, -4.35f, 2.56f, -4.8f, 4.7f);
        $this$PathData_u24lambda_u240$iv$iv$iv.curveToRelative(1.11f, -1.5f, 2.58f, -2.18f, 4.8f, -2.18f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        m4044addPathoIyEayM = $this$_get_MobileScreenShare__u24lambda_u241.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _mobileScreenShare = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _mobileScreenShare;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
